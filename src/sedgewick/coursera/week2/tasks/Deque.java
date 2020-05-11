package sedgewick.coursera.week2.tasks;

import java.util.Iterator;

/*
Write a generic data type for a deque and a randomized queue.

The goal of this assignment is to implement elementary data
    structures using arrays and linked lists,
    and to introduce you to generics and iterators.

Dequeue.
A double-ended queue or deque (pronounced “deck”) is a generalization of a
    stack and a queue that supports adding and removing items
    from either the front or the back of the data structure.
 */
public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private class DequeIter implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException("No more items to return!");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Can't remove from iterator");
        }
    }

    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Add First: NULL item");
        }
        Node tmp = first;
        first = new Node();
        first.item = item;
        first.next = tmp;
        first.prev = null;
        if (isEmpty()) {
            last = first;
        } else {
            tmp.prev = first;
        }
        ++size;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Add Last: NULL item");
        }
        Node tmp = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = tmp;
        if (isEmpty()) {
            first = last;
        } else {
            tmp.next = last;
        }
        ++size;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Remove First: Deque is empty!");
        }
        Item result = first.item;
        first = first.next;
        --size;
        if (isEmpty()) {
            last = first;
        } else {
            first.prev = null;
        }
        return result;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Remove Last: Deque is empty!");
        }
        Item result = last.item;
        last = last.prev;
        --size;
        if (isEmpty()) {
            first = last;
        } else {
            last.next = null;
        }
        return result;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIter();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(4);
        deque.addFirst(5);
        // 5 4 1 2
        for (Integer i : deque) {
            System.out.println(i);
        }
    }

}
