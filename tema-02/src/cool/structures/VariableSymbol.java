package cool.structures;

public class VariableSymbol extends Symbol {
    private final ClassSymbol type;

    public VariableSymbol(String name, ClassSymbol type) {
        super(name);
        this.type = type;
    }

    public ClassSymbol getType() {
        return type;
    }

    public ClassSymbol getType(Scope<?> scope) {
        return type == null ? scope.getCurrentClass() : type;
    }

    public String getTypeName() {
        return type == null ? "SELF_TYPE" : type.name;
    }
}
