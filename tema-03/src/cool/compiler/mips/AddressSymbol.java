package cool.compiler.mips;

import cool.structures.Symbol;

public class AddressSymbol extends Symbol  {
    public enum Type {
        ATTRIBUTE, FORMAL, LOCAL
    }

    private final String address;

    public AddressSymbol(String name, int index, Type type) {
        super(name);
        address = switch (type) {
            case ATTRIBUTE -> 4 * (index + 3) + "($s0)";
            case FORMAL -> 4 * (index + 3) + "($fp)";
            case LOCAL -> (-4) * (index + 1) + "($fp)";
        };
    }

    public String getAddress() {
        return address;
    }
}
