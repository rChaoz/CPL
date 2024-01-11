package cool.compiler.mips;

import cool.compiler.ast.expression.*;
import cool.structures.*;

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

    public static void generateAttributeBody(StringBuilder builder, Classes.Class cls, Classes classes,Literals literals, Expression attributeBody) {
        // Create attribute scope
        var attributeScope = createAttributeScope(cls);

        // Generate code
        new Expressions(builder, cls.getName() + "_init_", classes, literals, attributeScope).expression(attributeBody);
    }

    public static void generateMethodBody(StringBuilder builder, Classes.Class cls, Classes classes,Literals literals, MethodSymbol method) {
        // Create method scope
        var methodScope = createMethodScope(cls, method.getFormals());

        // Generate code
        new Expressions(builder, cls.getName() + "." + method.getName(), classes, literals, methodScope).expression(method.getBody());
    }

    private final StringBuilder builder;
    private final String prefix;
    private final Classes classes;
    private final Literals literals;
    private final Scope<AddressSymbol> mainScope;
    private int exprCounter = 0, localIndex = 0;

    public Expressions(StringBuilder builder, String prefix, Classes classes, Literals literals, Scope<AddressSymbol> mainScope) {
        this.builder = builder;
        this.prefix = prefix;
        this.classes = classes;
        this.literals = literals;
        this.mainScope = mainScope;
    }

    private void expression(Expression expr) {
        if (expr instanceof Arithmetic) arithmetic((Arithmetic) expr);
        else if (expr instanceof Assign) assign((Assign) expr);
        else if (expr instanceof Block) block((Block) expr);
        else if (expr instanceof Case) casee((Case) expr);
        else if (expr instanceof Comparison) comparison((Comparison) expr);
        else if (expr instanceof Complement) complement((Complement) expr);
        else if (expr instanceof If) iff((If) expr);
        else if (expr instanceof Instantiation) instantiation((Instantiation) expr);
        else if (expr instanceof IsVoid) isvoid((IsVoid) expr);
        else if (expr instanceof Let);
        else if (expr instanceof Literal) literal((Literal) expr);
        else if (expr instanceof MethodCall);
        else if (expr instanceof SelfMethodCall);
        else if (expr instanceof Variable);
        else if (expr instanceof While);
        else throw new RuntimeException("Unknown expression " + expr.getClass().getCanonicalName() + ": " + expr);
    }

    private void arithmetic(Arithmetic arithmetic) {
        // Evaluate left & right into $a0 and $a1
        expression(arithmetic.getLeft());
        K.push(builder, "$a0");
        localIndex++;
        expression(arithmetic.getRight());
        K.move(builder, "$a1", "$a0");
        K.pop(builder, "$a0");
        localIndex--;
        // Extract int32 values from objects
        K.lw(builder, "$a0", "12($a0)");
        K.lw(builder, "$a1", "12($a1)");
        // Perform operation
        switch (arithmetic.getOperation()) {
            case ADD -> builder.append(K.ADD).append("$a0").append(' ').append("$a0").append(' ').append("$a1").append(K.SEP);
            case SUBTRACT -> builder.append(K.SUB).append("$a0").append(' ').append("$a0").append(' ').append("$a1").append(K.SEP);
            case MULTIPLY -> builder.append(K.MUL).append("$a0").append(' ').append("$a0").append(' ').append("$a1").append(K.SEP);
            case DIVIDE -> {
                builder.append(K.DIV).append("$a0").append(' ').append("$a1").append(K.SEP);
                K.move(builder, "$a0", "$low");
            }
        }
        int32ToObject();
    }

    private void assign(Assign assign) {
        expression(assign.getExpression());
        K.sw(builder, "$a0", mainScope.lookup(assign.getId()).getAddress());
    }

    private void block(Block block) {
        for (var expr : block.getExpressions()) expression(expr);
    }

    private void casee(Case casee) {
        // Prefix for labels in this case & label for jump to end of case
        String prefix = this.prefix + "_case" + this.exprCounter++;
        String endJump = prefix + "_end";

        // Expression result will stay in $a0 until a branch is selected, ensure it's not void first
        expression(casee.getTarget());
        K.branch(builder, "$a0", K.Condition.Equal, "$zero", "_case_abort2");

        // Place tag of expression type in $t0
        K.lw(builder, "$t0", "0($a0)");

        // Type testing & jump to branches
        int index = 0;
        // Need -1 as constant
        K.li(builder, "$t3", -1);
        for (var branch : casee.getBranches()) {
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
            K.li(builder, "$t2", classes.get(SymbolTable.lookupClass(branch.getType())).getTag());
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
            Scope<AddressSymbol> branchScope = K.allocScope(builder, mainScope, List.of(branchVariable));
            K.sw(builder, "$a0", branchVariable.getAddress());

            expression(branch.getBody());

            K.freeScope(builder, branchScope);
            localIndex--;

            K.j(builder, endJump);
            index++;
        }

        // Add the label for jump to end of case
        K.label(builder, endJump);
    }

    private void comparison(Comparison comparison) {
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

        expression(comparison.getLeft());
        // Save on stack so we can execute right side as well
        K.push(builder, "$a0");
        localIndex++;
        expression(comparison.getRight());
        K.pop(builder, "$t1");
        localIndex--;
        K.move(builder, "$t2", "$a0");

        // Comparison is between $t1 and $t2
        if (comparison.getOperation() == Comparison.Op.EQUAL) {
            // Check reference equality
            K.branch(builder, "$t1", K.Condition.Equal, "$t2", trueLabel);
            // Then, call build-in equality test
            K.li(builder, "$a0", literals.getName(true));
            K.li(builder, "$a1", literals.getName(false));
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
            K.li(builder, "$a0", literals.getName(false));
            K.j(builder, endLabel);
        }

        // Common labels
        K.label(builder, trueLabel);
        K.li(builder, "$a0", literals.getName(true));
        K.label(builder, endLabel);
    }

    private void complement(Complement complement) {
        // Extract int32 value from int
        K.lw(builder, "$a0", "12($a0)");
        // Calculate complement
        builder.append(K.SUB).append("$a0").append(' ').append("$zero").append(' ').append("$a0");
        int32ToObject();
    }

    private void iff(If iff) {
        String prefix = this.prefix + this.exprCounter++;
        String trueLabel = prefix + "_true", endLabel = prefix + "_end";

        // Evaluate condition & extract Bool attribute
        expression(iff.getCondition());
        K.lw(builder, "$a0", "12($a0)");
        // Test condition result
        K.branch(builder, "$a0", K.Condition.NotEqual, "$zero", trueLabel);

        // False branch
        expression(iff.getElseBranch());
        K.j(builder, endLabel);

        // True branch
        K.label(builder, trueLabel);
        expression(iff.getThenBranch());
        K.label(builder, endLabel);
    }

    private void instantiation(Instantiation instantiation) {
        ClassSymbol symbol = SymbolTable.lookupClass(instantiation.getType());

        // Handle dynamic object creation
        if (symbol == SymbolTable.SelfType) {
            // Get address of protObj and save it to stack
            K.lw(builder, "$a0", "0($a0)");
            K.li(builder, "$t0", 8);
            builder.append(K.MUL).append("$a0 $a0 $t0").append(K.SEP);
            K.push(builder, "$a0");
            // Copy the protObj
            K.lw(builder, "$a0", "class_objTab($a0)");
            K.jal(builder, "Object.copy");
            // Call the initialization method
            K.pop(builder, "$t0");
            builder.append(K.ADDIU).append("$t0 $t0 4").append(K.SEP);
            K.lw(builder, "t0", "class_objTab($t0)");
            K.jr(builder, "$t0");
        } else {
            // Regular object creation
            Classes.Class cls = classes.get(symbol);
            K.lw(builder, "$a0", cls.getName() + "_protObj");
            K.jal(builder, "Object.copy");
            K.jal(builder, cls.getName() + "_init");
        }
    }

    private void isvoid(IsVoid isVoid) {
        String prefix = this.prefix + this.exprCounter++;
        String trueLabel = prefix + "_true", endLabel = prefix + "_end";

        expression(isVoid.getTarget());
        K.branch(builder, "$a0", K.Condition.Equal, "$zero", trueLabel);

        // False branch
        K.li(builder, "$a0", literals.getName(false));

        // True branch
        K.label(builder, trueLabel);
        K.li(builder, "$a0", literals.getName(true));
        K.label(builder, endLabel);
    }

    private void literal(Literal literal) {
        K.la(builder, "$a0", switch (literal.getType()) {
            case INTEGER -> literals.getName(Integer.parseInt(literal.getContent()));
            case STRING -> literals.getName(literal.getContent());
            case BOOLEAN -> literals.getName(Boolean.parseBoolean(literal.getContent()));
        });
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