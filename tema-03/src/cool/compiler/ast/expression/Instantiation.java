package cool.compiler.ast.expression;

import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.Scope;
import cool.structures.SymbolTable;
import cool.structures.VariableSymbol;

public class Instantiation extends Expression {
    private final CoolParser.InstantiationContext context;
    private final String type;

    public Instantiation(CoolParser.InstantiationContext context, String type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public CoolParser.InstantiationContext getContext() {
        return context;
    }

    public String getType() {
        return type;
    }

    @Override
    protected void printTitle() {
        print("new");
    }

    @Override
    protected void printChildren() {
        print(type);
    }

    private ClassSymbol classType;

    @Override
    public ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope) {
        classType = SymbolTable.lookupClass(this.type);
        if (classType == null)
            SymbolTable.error(this, context.TYPE().getSymbol(), "new is used with undefined type %s".formatted(this.type));
        return classType;
    }

    public ClassSymbol getClassType() {
        return classType;
    }

    @Override
    public String toString() {
        return "new " + type;
    }
}
