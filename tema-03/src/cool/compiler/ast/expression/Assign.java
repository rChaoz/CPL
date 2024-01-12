package cool.compiler.ast.expression;

import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.Scope;
import cool.structures.SymbolTable;
import cool.structures.VariableSymbol;

public class Assign extends Expression {
    private final CoolParser.VarAssignContext context;
    private final String id;
    private final Expression expression;

    public Assign(CoolParser.VarAssignContext context, String id, Expression expression) {
        this.context = context;
        this.id = id;
        this.expression = expression;
    }

    @Override
    public CoolParser.VarAssignContext getContext() {
        return context;
    }

    public String getId() {
        return id;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    protected void printTitle() {
        print("<-");
    }

    @Override
    protected void printChildren() {
        print(id);
        print(expression);
    }

    @Override
    public ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope) {
        ClassSymbol expressionType = expression.getExpressionType(scope);
        if (id.equals("self")) {
            SymbolTable.error(this, context.ID().getSymbol(), "Cannot assign to self");
            return expressionType;
        }
        VariableSymbol var = scope.lookup(id);
        if (var == null) {
            SymbolTable.error(this, context.ID().getSymbol(), "Undefined identifier %s".formatted(id));
            return expressionType;
        }
        ClassSymbol varType = var.getType();
        if (expressionType == null || varType == null) return expressionType;
        if (!expressionType.canBeAssignedTo(varType, scope.getCurrentClass()))
            SymbolTable.error(this, context.expr().start,
                        "Type %s of assigned expression is incompatible with declared type %s of identifier %s"
                                .formatted(expressionType.getName(), varType.getName(), id));
        return expressionType;
    }

    @Override
    public String toString() {
        return "%s <- %s".formatted(id, getContentText(expression));
    }
}
