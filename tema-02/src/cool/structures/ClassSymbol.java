package cool.structures;

import java.util.List;

public class ClassSymbol extends Symbol {
    private ClassSymbol parent;
    private final Scope<VariableSymbol> attributeScope = new DefaultScope<>(this);
    private final Scope<MethodSymbol> methodScope = new DefaultScope<>(this);

    public ClassSymbol(String name) {
        this(name, SymbolTable.Object);
        attributeScope.add(new VariableSymbol("self", this));
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
        if (parent != null) {
            attributeScope.setParent(parent.getAttributeScope());
            methodScope.setParent(parent.getMethodScope());
        } else {
            attributeScope.setParent(null);
            methodScope.setParent(null);
        }
    }

    public boolean isSuperTypeOf(ClassSymbol cls) {
        ClassSymbol c = cls;
        while (c != null) {
            if (c == this) return true;
            c = c.parent;
        }
        return false;
    }

    public static ClassSymbol joinTypes(ClassSymbol first, ClassSymbol second) {
        while (!first.isSuperTypeOf(second)) first = first.parent;
        return first;
    }

    public static ClassSymbol joinTypes(List<ClassSymbol> classes) {
        if (classes.isEmpty()) return SymbolTable.Object;
        ClassSymbol type = classes.get(0);
        for (int i = 1; i < classes.size(); ++i) type = joinTypes(type, classes.get(i));
        return type;
    }
}
