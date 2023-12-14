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

    public boolean addFormal(VariableSymbol formal) {
        if (!methodScope.add(formal)) return false;
        formals.add(formal);
        return true;
    }

    public ClassSymbol getReturnType() {
        return returnType;
    }

    public ClassSymbol getReturnType(Scope<?> scope) {
        return returnType == null ? scope.getCurrentClass() : returnType;
    }

    public ClassSymbol getReturnType(ClassSymbol currentClass) {
        return returnType == null ? currentClass : returnType;
    }

    public String getReturnTypeName() {
        return returnType == null ? "SELF_TYPE" : returnType.name;
    }

    public List<VariableSymbol> getFormals() {
        return formals;
    }

    public Scope<VariableSymbol> getMethodScope() {
        return methodScope;
    }
}
