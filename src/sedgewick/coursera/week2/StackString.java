package sedgewick.coursera.week2;

interface Stack<T> {
    void push(T n);

    T top();

    T pop();

    int size();

    boolean isEmpty();
}

public class StackString implements Stack<String> {
    private int capacity;
    private int size;
    private String data[];

    public StackString(int c) {
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
        if (capacity > 0 && capacity == (size    / 4)) {
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
        StackString s = new StackString(5);
        s.push("one");
        s.push("two");
        while (s.size() != 0) {
            System.out.println(s.pop());
        }
    }
}
