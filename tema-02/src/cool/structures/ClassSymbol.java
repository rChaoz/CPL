package cool.structures;

public class ClassSymbol extends Symbol {
    private ClassSymbol parent;
    private final Scope<VariableSymbol> attributeScope = new DefaultScope<>(null);
    private final Scope<MethodSymbol> methodScope = new DefaultScope<>(null);

    public ClassSymbol(String name) {
        this(name, SymbolTable.objectSymbol);
    }

    ClassSymbol(String name, ClassSymbol parent) {
        super(name);
        this.parent = parent;
    }

    public Scope<VariableSymbol> getAttributeScope() {
        return attributeScope;
    }

    public Scope<MethodSymbol> getMethodScope() {
        return methodScope;
    }

    public ClassSymbol getParent() {
        return parent;
    }

    public void setParent(ClassSymbol parent) {
        this.parent = parent;
    }
}
