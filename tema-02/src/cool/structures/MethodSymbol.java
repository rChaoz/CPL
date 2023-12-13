package cool.structures;

public class MethodSymbol extends Symbol {
    private final ClassSymbol returnType;
    private final Scope<VariableSymbol> formalScope = new DefaultScope<>(null);

    public MethodSymbol(String name, ClassSymbol returnType) {
        super(name);
        this.returnType = returnType;
    }

    MethodSymbol(String name, ClassSymbol returnType, VariableSymbol ...formals) {
        this(name, returnType);
        for (var formal : formals) formalScope.add(formal);
    }

    public ClassSymbol getReturnType() {
        return returnType;
    }

    public Scope<VariableSymbol> getFormalScope() {
        return formalScope;
    }
}
