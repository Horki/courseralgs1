package sedgewick.coursera.week2;

import sedgewick.coursera.week2.interfaces.Stack;

public class FixedCapacityStack<T> implements Stack<T> {
    private int size;
    private int capacity;
    private T data[];

    public FixedCapacityStack(int c) {
        size = 0;
        capacity = c;
//        data = new T[capacity]; // not allowed in jav
        // Warning: Unchecked cast
        data = (T[]) new Object[capacity];
    }

    public void push(T n) {
        if (size == capacity) {
            // TODO: throw stack overflow err!
            return;
        }
        data[size++] = n;
    }

    public T top() {
        if (isEmpty()) {
            // TODO: throw underflow exception!
        }
        return data[size - 1];
    }

    public T pop() {
        if (isEmpty()) {
            // TODO: throw underflow exception!
        }
        return data[--size];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Stack s = new FixedCapacityStack<String>(5);
        s.push("one");
        s.push("two");
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}
