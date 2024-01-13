package cool.compiler.mips;

import cool.compiler.Compiler;
import cool.compiler.ast.ASTNode;
import cool.compiler.ast.expression.*;
import cool.parser.CoolParser;
import cool.structures.DefaultScope;
import cool.structures.Scope;
import cool.structures.SymbolTable;
import cool.structures.VariableSymbol;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Expressions {
    public static Scope<AddressSymbol> createAttributeScope(Classes.Class cls) {
        var attributeScope = new DefaultScope<AddressSymbol>();
        int index = 0;
        for (var attribute : cls.getSymbol().getAttributeScope())
            attributeScope.add(new AddressSymbol(attribute.getName(), index++, AddressSymbol.Type.ATTRIBUTE));
        return attributeScope;
    }

    public static Scope<AddressSymbol> createMethodScope(Classes.Class cls, List<VariableSymbol> formals) {
        // Attribute scope
        var attributeScope = createAttributeScope(cls);

        // Method scope
        var methodScope = new DefaultScope<>(attributeScope);
        int index = 0;
        for (var formal : formals) methodScope.add(new AddressSymbol(formal.getName(), index++, AddressSymbol.Type.FORMAL));

        return methodScope;
    }

    private final StringBuilder builder;
    private final String prefix;
    private final Classes classes;
    private final Literals literals;
    private Scope<AddressSymbol> currentScope;
    private final Classes.Class ownerClass;
    private int exprCounter = 0, localIndex = 0, depth = -1;

    public Expressions(StringBuilder builder, String prefix, Classes classes, Literals literals,
                       Scope<AddressSymbol> mainScope, Classes.Class ownerClass) {
        this.builder = builder;
        this.prefix = prefix;
        this.classes = classes;
        this.literals = literals;
        this.currentScope = mainScope;
        this.ownerClass = ownerClass;
    }

    public void eval(Expression expr) {
        builder.append(K.SEP);
        depth++;
        if (expr instanceof Arithmetic) arithmetic((Arithmetic) expr);
        else if (expr instanceof Assign) assign((Assign) expr);
        else if (expr instanceof Block) block((Block) expr);
        else if (expr instanceof Case) casee((Case) expr);
        else if (expr instanceof Comparison) comparison((Comparison) expr);
        else if (expr instanceof Complement) complement((Complement) expr);
        else if (expr instanceof If) iff((If) expr);
        else if (expr instanceof Instantiation) instantiation((Instantiation) expr);
        else if (expr instanceof IsVoid) isvoid((IsVoid) expr);
        else if (expr instanceof Let) let((Let) expr);
        else if (expr instanceof Literal) literal((Literal) expr);
        else if (expr instanceof MethodCall) methodCall((MethodCall) expr);
        else if (expr instanceof SelfMethodCall) selfMethodCall((SelfMethodCall) expr);
        else if (expr instanceof Variable) variable((Variable) expr);
        else if (expr instanceof While) whilee((While) expr);
        else throw new RuntimeException("Unknown expression " + expr.getClass().getCanonicalName() + ": " + expr);
        builder.append(K.SEP);
        depth--;
    }

    private void arithmetic(Arithmetic arithmetic) {
        K.comment(builder, depth, arithmetic);
        // Evaluate left & right into $a0 and $a1
        eval(arithmetic.getLeft());
        K.push(builder, "$a0");
        localIndex++;
        eval(arithmetic.getRight());
        K.move(builder, "$a1", "$a0");
        K.pop(builder, "$a0");
        localIndex--;
        // Extract int32 values from objects
        K.lw(builder, "$a0", "12($a0)");
        K.lw(builder, "$a1", "12($a1)");
        // Perform operation
        switch (arithmetic.getOperation()) {
            case ADD -> builder.append(K.ADD).append("$a0 $a0 $a1").append(K.SEP);
            case SUBTRACT -> builder.append(K.SUB).append("$a0 $a0 $a1").append(K.SEP);
            case MULTIPLY -> builder.append(K.MUL).append("$a0 $a0 $a1").append(K.SEP);
            case DIVIDE -> {
                builder.append(K.DIV).append("$a0 $a1").append(K.SEP);
                K.move(builder, "$a0", "$low");
            }
        }
        int32ToObject();
    }

    private void assign(Assign assign) {
        K.comment(builder, depth, assign);
        eval(assign.getExpression());
        K.sw(builder, "$a0", currentScope.lookup(assign.getId()).getAddress());
    }

    private void block(Block block) {
        K.comment(builder, depth, block);
        for (var expr : block.getExpressions()) eval(expr);
    }

    private void casee(Case casee) {
        K.comment(builder, depth, casee);
        // Prefix for labels in this case & label for jump to end of case
        String prefix = this.prefix + "_case" + this.exprCounter++;
        String expressionOk = prefix + "_notVoid", endJump = prefix + "_end";

        // Evaluate target expression and ensure it's not void first
        eval(casee.getTarget());
        K.branch(builder, "$a0", K.Condition.NotEqual, "$zero", expressionOk);
        K.jal(builder, "_case_abort2");
        // Expression result will stay in $a0 until a branch is selected
        K.label(builder, expressionOk);

        // Place tag of expression type in $t0
        K.lw(builder, "$t0", "0($a0)");

        // Type testing & jump to branches
        int index = 0;
        // Need -1 as constant
        K.li(builder, "$t3", -1);
        for (var branch : casee.getBranches()) {
            K.comment(builder, depth, branch);
            /* == Pseudocode ==

            Variables:

                EXPR_TYPE (type of expression evaluated earlier) = $t0
                TYPE (copy of EXPR_TYPE that we can modify) = $t1
                BRANCH_TYPE (the tag of the declared case type) = $t2


            For every branch the code is the following (they are concatenated):

                TYPE <- EXPR_TYPE  // copy so we can modify it
                CASE_BRANCH = get_tag(branch.type)  // get tag of branch declared type
                do:
                    if TYPE == BRANCH_TYPE: goto branch.code_label
                    TYPE = get_parent(TYPE)
                while TYPE != -1  // Object.parent == -1 (tag)

            After all codes are executed, if none of them matches, _case_abort is called
             */
            K.move(builder, "$t1", "$t0");
            K.li(builder, "$t2", classes.get(branch.getClassType()).getTag());
            String loopLabel = prefix + "_branchLoop" + index;
            K.label(builder, loopLabel);
            K.branch(builder, "$t1", K.Condition.Equal, "$t2", prefix + "_branch" + index);
            K.lw(builder, "$t1", "class_parentTab($t1)");
            K.branch(builder, "$t1", K.Condition.NotEqual, "$t3", loopLabel);

            index++;
        }
        K.jal(builder, "_case_abort");

        // Branch bodies
        index = 0;
        for (var branch : casee.getBranches()) {
            K.label(builder, prefix + "_branch" + index);
            // We have expression result in $a0
            // We want to move it to a variable (onto the stack) with the name the chosen branch expects
            var branchVariable = new AddressSymbol(branch.getId(), localIndex++, AddressSymbol.Type.LOCAL);
            Scope<AddressSymbol> branchScope = K.allocScope(builder, currentScope, List.of(branchVariable));
            K.sw(builder, "$a0", branchVariable.getAddress());

            eval(branch.getBody());

            K.freeScope(builder, branchScope);
            localIndex--;

            K.j(builder, endJump);
            index++;
        }

        // Add the label for jump to end of case
        K.label(builder, endJump);
    }

    private void comparison(Comparison comparison) {
        K.comment(builder, depth, comparison);
        String prefix = this.prefix + "_cmp" + this.exprCounter++;
        /* == Pseudocode ==
        $t1 <- left()
        $t2 <- right()

        For 'equals':

            if $t1 == $t2 jump true_label  // referencial equality test
            $a0 = true
            $a1 = false
            equality_check()  // this places correct result in $a0


       For numeric comparison (non-equals):

            if (condition) jump true_label
            $a0 = false
            jump end_label

        There are 2 common labels:

            true_label:
            $a1 = true
            end_label:
         */
        String trueLabel = prefix + "_true", endLabel = prefix + "_end";

        eval(comparison.getLeft());
        // Save on stack so we can execute right side as well
        K.push(builder, "$a0");
        localIndex++;
        eval(comparison.getRight());
        K.pop(builder, "$t1");
        localIndex--;
        K.move(builder, "$t2", "$a0");

        // Comparison is between $t1 and $t2
        if (comparison.getOperation() == Comparison.Op.EQUAL) {
            // Check reference equality
            K.branch(builder, "$t1", K.Condition.Equal, "$t2", trueLabel);
            // Then, call build-in equality test
            K.li(builder, "$a0", literals.getLabel(true));
            K.li(builder, "$a1", literals.getLabel(false));
            K.jal(builder, "equality_test");
        } else {
            // Extract int32 values from objects
            K.lw(builder, "$t1", "12($t1)");
            K.lw(builder, "$t2", "12($t2)");
            // Perform comparison
            K.branch(builder, "$t1", switch (comparison.getOperation()) {
                case LESS -> K.Condition.Less;
                case LESS_OR_EQUAL -> K.Condition.LessEqual;
                case EQUAL -> throw new RuntimeException("impossible");
            }, "$t2", trueLabel);
            // If jump was not made, then condition is false
            K.li(builder, "$a0", literals.getLabel(false));
            K.j(builder, endLabel);
        }

        // Common labels
        K.label(builder, trueLabel);
        K.li(builder, "$a0", literals.getLabel(true));
        K.label(builder, endLabel);
    }

    private void complement(Complement complement) {
        K.comment(builder, depth, complement);
        eval(complement.getOperand());
        // Extract int32 value from int
        K.lw(builder, "$a0", "12($a0)");
        // Calculate complement
        builder.append(K.SUB).append("$a0 $zero $a0");
        int32ToObject();
    }

    private void iff(If iff) {
        K.comment(builder, depth, iff);
        String prefix = this.prefix + this.exprCounter++;
        String trueLabel = prefix + "_true", endLabel = prefix + "_end";

        // Evaluate condition & extract Bool attribute
        eval(iff.getCondition());
        K.lw(builder, "$a0", "12($a0)");
        // Test condition result
        K.branch(builder, "$a0", K.Condition.NotEqual, "$zero", trueLabel);

        // False branch
        eval(iff.getElseBranch());
        K.j(builder, endLabel);

        // True branch
        K.label(builder, trueLabel);
        eval(iff.getThenBranch());
        K.label(builder, endLabel);
    }

    private void instantiation(Instantiation instantiation) {
        K.comment(builder, depth, instantiation);
        // Handle dynamic object creation
        if (instantiation.getClassType() == SymbolTable.SelfType) {
            // Get address of protObj and save it to stack
            K.lw(builder, "$a0", "0($s0)");
            K.li(builder, "$t0", 8);
            builder.append(K.MUL).append("$a0 $a0 $t0").append(K.SEP);
            K.push(builder, "$a0");
            // Copy the protObj
            K.lw(builder, "$a0", "class_objTab($a0)");
            K.jal(builder, "Object.copy");
            // Call the initialization method
            K.pop(builder, "$t0");
            builder.append(K.ADDIU).append("$t0 $t0 4").append(K.SEP);
            K.lw(builder, "$t0", "class_objTab($t0)");
            K.jalr(builder, "$t0");
        } else {
            // Regular object creation
            Classes.Class cls = classes.get(instantiation.getClassType());
            K.la(builder, "$a0", cls.getName() + "_protObj");
            K.jal(builder, "Object.copy");
            K.jal(builder, cls.getName() + "_init");
        }
    }

    private void isvoid(IsVoid isVoid) {
        K.comment(builder, depth, isVoid);
        String prefix = this.prefix + this.exprCounter++;
        String trueLabel = prefix + "_true", endLabel = prefix + "_end";

        eval(isVoid.getTarget());
        K.branch(builder, "$a0", K.Condition.Equal, "$zero", trueLabel);

        // False branch
        K.li(builder, "$a0", literals.getLabel(false));

        // True branch
        K.label(builder, trueLabel);
        K.li(builder, "$a0", literals.getLabel(true));
        K.label(builder, endLabel);
    }

    private void literal(Literal literal) {
        K.comment(builder, depth, literal);
        K.la(builder, "$a0", switch (literal.getType()) {
            case INTEGER -> literals.getLabel(Integer.parseInt(literal.getContent()));
            case STRING -> literals.getLabel(literal.getContent());
            case BOOLEAN -> literals.getLabel(Boolean.parseBoolean(literal.getContent()));
        });
    }

    private void let(Let let) {
        K.comment(builder, depth, let);
        var initialScope = currentScope;

        // Allocate variables 1 by 1 as variables can depend on previous ones
        for (var local : let.getLocals()) {
            var localVariable = new AddressSymbol(local.getId(), localIndex++, AddressSymbol.Type.LOCAL);
            var type = local.getDeclaredType();
            currentScope = K.allocScope(builder, currentScope, List.of(localVariable));

            if (local.getInitializer() != null) {
                K.comment(builder, depth, local);
                eval(local.getInitializer());
            } else if (type == SymbolTable.Int) K.la(builder, "$a0", literals.getLabel(0));
            else if (type == SymbolTable.String) K.la(builder, "$a0", literals.getLabel(""));
            else if (type == SymbolTable.Bool) K.la(builder, "$a0", literals.getLabel(false));
            else K.move(builder, "$a0", "$zero");
            K.sw(builder, "$a0", localVariable.getAddress());
        }

        // Execute the let body
        eval(let.getBody());

        // Restore the stack
        for (var ignored: let.getLocals()) currentScope = K.freeScope(builder, currentScope);

        if (currentScope != initialScope) throw new RuntimeException("impossible");
    }

    private void methodCall(MethodCall call) {
        K.comment(builder, depth, call);
        var target = call.getTargetObject();
        var cls = classes.get(target.getExpressionType(null)); // type is cached, so it's ok to not pass a scope
        if (cls == null) cls = ownerClass;
        eval(target);

        if (call.getTargetType() == null) // Dynamic dispatch
            doMethodCall(call.getContext(), cls, call.getName(), call.getArguments(), false);
        else // Static dispatch
            doMethodCall(call.getContext(), classes.get(call.getTargetClassType()), call.getName(), call.getArguments(), true);
    }

    private void selfMethodCall(SelfMethodCall call) {
        K.comment(builder, depth, call);
        // Target object is self, move it to $a0
        K.move(builder, "$a0", "$s0");
        doMethodCall(call.getContext(), ownerClass, call.getName(), call.getArguments(), false);
    }

    private void doMethodCall(ParserRuleContext ctx, Classes.Class cls, String methodName, List<Expression> arguments, boolean isStaticDispatch) {
        // If target is void, abort (report file name and line)
        String okLabel = prefix + this.exprCounter++ + "_notVoid";
        K.branch(builder, "$a0", K.Condition.NotEqual, "$zero", okLabel);
        K.li(builder, "$t1", ctx.start.getLine());
        while (!(ctx.getParent() instanceof CoolParser.ProgramContext)) ctx = ctx.getParent();
        K.la(builder, "$a0", literals.getLabel(Compiler.fileNamesMap.get(ctx)));
        K.jal(builder, "_dispatch_abort");
        K.label(builder, okLabel);
        // Target object is in $a0, save it
        K.push(builder, "$a0");
        int targetObjectIndex = localIndex++;
        // Add arguments to stack in reverse order
        for (int i = arguments.size() - 1; i >= 0; i--) {
            K.comment(builder, depth, "argument %d: [ %s ]".formatted(i, ASTNode.getContentText(arguments.get(i))));
            eval(arguments.get(i));
            K.push(builder, "$a0");
            localIndex++;
        }
        // Put target object back in $a0
        K.lw(builder, "$a0", -4 * (targetObjectIndex + 1) + "($fp)");
        if (isStaticDispatch) {
            builder.append(K.JAL).append(cls.getName()).append('.').append(methodName).append(K.SEP);
        } else {
            K.lw(builder, "$t0", "8($a0)");
            K.lw(builder, "$t0", 4 * cls.getMethodIndex(methodName) + "($t0)");
            K.jalr(builder, "$t0");
        }
        // Remove target object from stack (arguments are removed by callee)
        K.pop(builder, 1);
        localIndex -= arguments.size() + 1;
    }

    private void variable(Variable variable) {
        K.comment(builder, depth, variable);
        if (variable.getId().equals("self")) K.move(builder, "$a0", "$s0");
        else K.lw(builder, "$a0", currentScope.lookup(variable.getId()).getAddress());
    }

    private void whilee(While whilee) {
        K.comment(builder, depth, whilee);
        String prefix = this.prefix + this.exprCounter++;
        String startLabel = prefix + "_start", endLabel = prefix + "_end";
        K.label(builder, startLabel);

        eval(whilee.getCondition());
        // If condition is false, jump to end
        K.lw(builder, "$a0", "12($a0)");
        K.branch(builder, "$a0", K.Condition.Equal, "$zero", endLabel);
        // Else, evaluate the body
        eval(whilee.getBody());
        // And loop back to condition testing
        K.j(builder, startLabel);

        K.label(builder, endLabel);
        // While always returns void
        K.li(builder, "$a0", 0);
    }

    // Utils

    // Convert $a0 from being a int32 to address of an Int object representing the int32 value
    private void int32ToObject() {
        // Save int32 on stack
        K.push(builder, "$a0");
        // Create a new int
        instantiation(new Instantiation(null, "Int"));
        K.pop(builder, "$a1");
        // Populate object's int32 attribute
        K.sw(builder, "$a1", "12($a0)");
        // Int address is already in $a0
    }
}
