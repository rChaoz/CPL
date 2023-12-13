package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultScope<T extends Symbol> implements Scope<T> {

    private final Map<String, T> symbols = new LinkedHashMap<>();

    private final Scope<T> parent;

    public DefaultScope(Scope<T> parent) {
        this.parent = parent;
    }

    @Override
    public boolean add(T sym) {
        // Reject duplicates in the same scope.
        if (symbols.containsKey(sym.getName()))
            return false;

        symbols.put(sym.getName(), sym);

        return true;
    }

    @Override
    public T lookup(String name) {
        var sym = symbols.get(name);

        if (sym != null)
            return sym;

        if (parent != null)
            return parent.lookup(name);

        return null;
    }

    @Override
    public Scope<T> getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return symbols.values().toString();
    }
}
