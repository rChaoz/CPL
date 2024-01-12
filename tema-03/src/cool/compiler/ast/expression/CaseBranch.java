package cool.compiler.ast.expression;

import cool.compiler.ast.ASTNode;
import cool.parser.CoolParser;
import cool.structures.*;

public class CaseBranch extends ASTNode {
    private final CoolParser.Case_branchContext context;
    private final String id;
    private final String type;
    private final Expression body;

    public CaseBranch(CoolParser.Case_branchContext context, String id, String type, Expression body) {
        this.context = context;
        this.id = id;
        this.type = type;
        this.body = body;
    }

    @Override
    public CoolParser.Case_branchContext getContext() {
        return context;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Expression getBody() {
        return body;
    }

    private ClassSymbol classType;

    public ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope) {
        if (id.equals("self")) {
            SymbolTable.error(this, context.ID().getSymbol(), "Case variable has illegal name self");
            return null;
        }

        classType = SymbolTable.lookupClass(type);

        if (classType == SymbolTable.SelfType) {
            SymbolTable.error(this, context.TYPE().getSymbol(), "Case variable %s has illegal type SELF_TYPE".formatted(id));
            classType = null;
        } else if (classType == null)
            SymbolTable.error(this, context.TYPE().getSymbol(), "Case variable %s has undefined type %s".formatted(id, type));

        Scope<VariableSymbol> branchScope = new DefaultScope<>(scope);
        branchScope.add(new VariableSymbol(id, classType, null));
        return body.getExpressionType(branchScope);
    }

    public ClassSymbol getClassType() {
        return classType;
    }

    @Override
    protected void printTitle() {
        print("case branch");
    }

    @Override
    protected void printChildren() {
        print(id);
        print(type);
        print(body);
    }

    @Override
    public String toString() {
        return "branch %s: %s -> [ %s ]".formatted(id, type, getContentText(body));
    }
}
