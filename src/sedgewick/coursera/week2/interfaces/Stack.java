package sedgewick.coursera.week2.interfaces;

public interface Stack<T> {
    void push(T n);

    T top();

    T pop();

    int size();

    boolean isEmpty();
}