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
}
