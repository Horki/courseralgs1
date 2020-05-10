package sedgewick.coursera.week2;

import sedgewick.coursera.week2.interfaces.Stack;

public class FixedCapacityStackOfStrings implements Stack<String> {
    private int size;
    private int capacity;
    private String data[];

    public FixedCapacityStackOfStrings(int c) {
        size = 0;
        capacity = c;
        data = new String[capacity];
    }

    public void push(String n) {
        if (size == capacity) {
            // TODO: throw stack overflow err!
            return;
        }
        data[size++] = n;
    }

    public String top() {
        if (isEmpty()) {
            // TODO: throw underflow exception!
        }
        return data[size - 1];
    }

    public String pop() {
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
        Stack s = new FixedCapacityStackOfStrings(5);
        s.push("one");
        s.push("two");
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}
