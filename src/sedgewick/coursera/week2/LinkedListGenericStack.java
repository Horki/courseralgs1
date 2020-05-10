package sedgewick.coursera.week2;

import sedgewick.coursera.week2.interfaces.Stack;

public class LinkedListGenericStack<T> implements Stack<T> {
    private class Node {
        T item;
        Node next;
    }

    private Node first;
    private int size;

    public LinkedListGenericStack() {
        size = 0;
        first = null;
    }

    public void push(T n) {
        Node tmp = first;
        first = new Node();
        first.item = n;
        first.next = tmp;
        ++size;
    }

    public T top() {
        return first.item;
    }

    public T pop() {
        T res = first.item;
        first = first.next;
        --size;
        return res;
    }

    public boolean isEmpty() {
//        return first == null;
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Stack s = new LinkedListGenericStack<String>();
        s.push("one");
        s.push("two");
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}
