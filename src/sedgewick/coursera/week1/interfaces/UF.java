package sedgewick.coursera.week1.interfaces;

public interface UF<T> {
//    public UF(int N);
    public void union(T p, T q);
    public boolean connected(T p, T q);
    public T find(T p);
    int count();
}
