package sedgewick.coursera.week2;

import sedgewick.coursera.week2.interfaces.Queue;

public class LinkedQueueOfStrings implements Queue<String> {
    private int size;
    private Node first;
    private Node last;

    private class Node {
        String item;
        Node next;
    }

    public LinkedQueueOfStrings() {
        first = null;
        last = null;
        size = 0;
    }

    public void enqueue(String s) {
        Node tmp = last;
        last = new Node();
        last.item = s;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            tmp.next = last;
        }
        ++size;
    }

    public String dequeue() {
        String result = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        --size;
        return result;
    }

    public boolean isEmpty() {
//        return first == null;
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Queue q = new LinkedQueueOfStrings();
        q.enqueue("one");
        q.enqueue("two");
        q.enqueue("three");
        while (!q.isEmpty()) {
            System.out.println(q.dequeue());
        }
    }
}
