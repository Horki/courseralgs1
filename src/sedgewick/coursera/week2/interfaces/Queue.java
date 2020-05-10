package sedgewick.coursera.week2.interfaces;

public interface Queue<T> {
    void enqueue(T t);
    T dequeue();
    boolean isEmpty();
    int size();
}
