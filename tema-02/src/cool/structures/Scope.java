package cool.structures;

public interface Scope<T extends Symbol> {
    public boolean add(T sym);

    public T lookup(String str);

    public Scope<T> getParent();
}
