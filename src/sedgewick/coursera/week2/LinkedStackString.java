package sedgewick.coursera.week2;

import sedgewick.coursera.week2.interfaces.Stack;

public class LinkedStackString implements Stack<String> {
    private class Node {
        String item;
        Node next;
    }

    private Node first;
    private int size;

    public LinkedStackString() {
        size = 0;
        first = null;
    }

    public void push(String n) {
        Node tmp = first;
        first = new Node();
        first.item = n;
        first.next = tmp;
        ++size;
    }

    public String top() {
        return first.item;
    }

    public String pop() {
        String res = first.item;
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
        Stack s = new LinkedStackString();
        s.push("one");
        s.push("two");
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}
