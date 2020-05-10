package sedgewick.coursera.week2;

import sedgewick.coursera.week2.interfaces.Stack;

import java.util.Iterator;

public class LinkedListGenericStackIter<T> implements Stack<T>, Iterable<T> {
    private class Node {
        T item;
        Node next;
    }

    private class ListIter implements Iterator<T> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    public Iterator<T> iterator() {
        return new ListIter();
    }

    private Node first;
    private int size;

    public LinkedListGenericStackIter() {
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
        LinkedListGenericStackIter<String> s = new LinkedListGenericStackIter<>();
        s.push("one");
        s.push("two");
        for (String i : s) {
            System.out.println(i);
        }
    }
}
