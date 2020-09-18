package sedgewick.coursera.week1.interfaces;

public interface UF<T> {
    void union(T p, T q);
    boolean connected(T p, T q);
    T find(T p);
    int count();
    void debug_output();
}
