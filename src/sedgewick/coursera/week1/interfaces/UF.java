package sedgewick.coursera.week1.interfaces;

public interface UF<T> {
    public void union(T p, T q);
    public boolean connected(T p, T q);
    public T find(T p);
    public int count();
    public void debug_output();
}
