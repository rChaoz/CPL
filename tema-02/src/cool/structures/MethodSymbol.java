package cool.structures;

import java.util.ArrayList;
import java.util.List;

public class MethodSymbol extends Symbol {
    private final ClassSymbol returnType;
    private final List<VariableSymbol> formals = new ArrayList<>();
    private final Scope<VariableSymbol> formalScope = new DefaultScope<>(null);

    public MethodSymbol(String name, ClassSymbol returnType) {
        super(name);
        this.returnType = returnType;
    }

    public MethodSymbol(String name, ClassSymbol returnType, VariableSymbol ...formals) {
        this(name, returnType);
        for (var formal : formals) {
            this.formals.add(formal);
            formalScope.add(formal);
        }
    }

    public boolean addFormal(VariableSymbol formal) {
        if (!formalScope.add(formal)) return false;
        formals.add(formal);
        return true;
    }

    public ClassSymbol getReturnType() {
        return returnType;
    }

    public List<VariableSymbol> getFormals() {
        return formals;
    }

    public Scope<VariableSymbol> getFormalScope() {
        return formalScope;
    }
}
