package cool.compiler.ast.expression;

import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.Scope;
import cool.structures.SymbolTable;
import cool.structures.VariableSymbol;

public class Variable extends Expression {
    private final CoolParser.VarContext context;
    private final String id;

    public Variable(CoolParser.VarContext context, String id) {
        this.context = context;
        this.id = id;
    }

    @Override
    public CoolParser.VarContext getContext() {
        return context;
    }

    public String getId() {
        return id;
    }

    @Override
    protected void printTitle() {
        print(id);
    }

    @Override
    protected void printChildren() {
    }

    @Override
    public ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope) {
        if (id.equals("self")) return SymbolTable.SelfType;
        VariableSymbol var = scope.lookup(id);
        if (var == null) {
            SymbolTable.error(this, context.start, "Undefined identifier %s".formatted(id));
            return null;
        } else return var.getType();
    }

    @Override
    public String toString() {
        return "access variable " + id;
    }
}
