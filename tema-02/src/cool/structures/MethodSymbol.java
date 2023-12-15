package cool.structures;

import java.util.ArrayList;
import java.util.List;

public class MethodSymbol extends Symbol {
    private final ClassSymbol returnType;
    private final List<VariableSymbol> formals = new ArrayList<>();
    private final Scope<VariableSymbol> methodScope;

    public MethodSymbol(String name, ClassSymbol cls, ClassSymbol returnType) {
        super(name);
        this.returnType = returnType;
        this.methodScope = new DefaultScope<>(cls.getAttributeScope());
    }

    public MethodSymbol(String name, ClassSymbol cls, ClassSymbol returnType, VariableSymbol ...formals) {
        this(name, cls, returnType);
        for (var formal : formals) {
            this.formals.add(formal);
            methodScope.add(formal);
        }
    }

    public void addFormal(VariableSymbol formal, boolean addToScope) {
        formals.add(formal);
        if (addToScope) methodScope.add(formal);
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
