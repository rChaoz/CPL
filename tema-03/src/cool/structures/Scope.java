package cool.structures;

import java.util.Collection;

public interface Scope<T extends Symbol> extends Iterable<T> {
    boolean add(T sym);

    T lookup(String str);

    T lookup(String str, boolean recursive);

    Scope<T> getParent();

    void setParent(Scope<T> parent);

    ClassSymbol getCurrentClass();

    Collection<T> asCollection();
}
