package cool.structures;

import java.util.ArrayList;
import java.util.List;

public class MethodSymbol extends Symbol {
    private final ClassSymbol ownerClass, returnType;
    private final List<VariableSymbol> formals = new ArrayList<>();
    private final Scope<VariableSymbol> methodScope;

    public MethodSymbol(String name, ClassSymbol ownerClass, ClassSymbol returnType) {
        super(name);
        this.ownerClass = ownerClass;
        this.returnType = returnType;
        this.methodScope = new DefaultScope<>(ownerClass.getAttributeScope());
    }

    public MethodSymbol(String name, ClassSymbol ownerClass, ClassSymbol returnType, VariableSymbol ...formals) {
        this(name, ownerClass, returnType);
        for (var formal : formals) {
            this.formals.add(formal);
            methodScope.add(formal);
        }
    }

    public void addFormal(VariableSymbol formal, boolean addToScope) {
        formals.add(formal);
        if (addToScope) methodScope.add(formal);
    }

    public ClassSymbol getOwnerClass() {
        return ownerClass;
    }

    public ClassSymbol getReturnType() {
        return returnType;
    }

    public ClassSymbol getReturnType(ClassSymbol objectClass) {
        return returnType == SymbolTable.SelfType ? objectClass : returnType;
    }

    public List<VariableSymbol> getFormals() {
        return formals;
    }

    public Scope<VariableSymbol> getMethodScope() {
        return methodScope;
    }
}
