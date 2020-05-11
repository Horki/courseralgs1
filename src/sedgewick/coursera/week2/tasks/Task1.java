package sedgewick.coursera.week2.tasks;

import java.util.Stack;

/*
||||||||||||||||||||||||||||
|| Queue with two stacks. ||
||||||||||||||||||||||||||||

Implement a queue with two stacks so that each queue operations
    takes a constant amortized number of stack operations.
 */

interface Queue<T> {
    void enqueue(T t);

    T dequeue();

    boolean isEmpty();

    int size();
}

public class Task1<T> implements Queue<T> {
    private Stack<T> first;
    private Stack<T> last;
    private int size;

    public Task1() {
        first = new Stack<T>();
        last = new Stack<T>();
        size = 0;
    }

    public void enqueue(T item) {
        first.push(item);
        ++size;
    }

    /*
    Best case O(1)
    Worst case O(n)
     */
    public T dequeue() {
        if (last.isEmpty()) {
            while (!first.isEmpty()) {
                last.push(first.pop());
            }
        }
        --size;
        return last.pop();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Task1<String> queue = new Task1<String>();
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
