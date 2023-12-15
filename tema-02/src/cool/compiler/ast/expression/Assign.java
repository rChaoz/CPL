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
    public ClassSymbol getExpressionType(Scope<VariableSymbol> scope) {
        return expression.getExpressionType(scope);
    }

    @Override
    public void checkTypes(Scope<VariableSymbol> scope) {
        expression.checkTypes(scope);
        if (id.equals("self")) {
            SymbolTable.error(this, context.ID().getSymbol(), "Cannot assign to self");
            return;
        }
        VariableSymbol var = scope.lookup(id);
        if (var == null) {
            SymbolTable.error(this, context.ID().getSymbol(), "Undefined identifier %s".formatted(id));
            return;
        }
        ClassSymbol type = expression.getExpressionType(scope);
        if (type != null && !var.getType(scope).isSuperTypeOf(type))
            SymbolTable.error(this, context.expr().start,
                    "Type %s of assigned expression is incompatible with declared type %s of identifier %s"
                            .formatted(type.getName(), var.getTypeName(), id));
    }
}
