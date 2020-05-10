package sedgewick.coursera.week2;

import sedgewick.coursera.week2.interfaces.Stack;

public class ResizingArrayStackOfStrings implements Stack<String> {
    private int capacity;
    private int size;
    private String data[];

    public ResizingArrayStackOfStrings(int c) {
        capacity = c;
        size = 0;
        data = new String[capacity];
    }

    public void push(String n) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        data[size++] = n;
    }

    public String top() {
        return data[size - 1];
    }

    public String pop() {
        String result = data[--size];
        data[size] = null;
        // decrease capacity
        if (capacity > 0 && capacity == (size / 4)) {
            resize(capacity / 2);
        }
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < size; ++i) {
            copy[i] = data[i];
        }
        data = copy;
        this.capacity = capacity;
        this.size = data.length;
    }

    public static void main(String[] args) {
        Stack s = new ResizingArrayStackOfStrings(5);
        s.push("one");
        s.push("two");
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}
