package sedgewick.coursera.week2.tasks;

import java.util.Comparator;
import java.util.PriorityQueue;

interface Stack<T> {
    void push(T n);

    T top();

    T pop();

    int size();

    boolean isEmpty();
}

public class Task2<Integer> implements Stack<Integer> {
    private class Node {
        Integer item;
        Node next;
    }

    private class Largest implements Comparator<Integer> {
        public int compare(Integer x, Integer y) {
            return (int) y - (int) x;
        }
    }

    private Node first;
    private int size;
    private PriorityQueue<Integer> pq;

    public Task2() {
        size = 0;
        first = null;
        pq = new PriorityQueue<Integer>(new Largest());
    }

    public void push(Integer n) {
        Node tmp = first;
        pq.add(n);
        first = new Node();
        first.item = n;
        first.next = tmp;
        ++size;
    }

    public Integer top() {
        return first.item;
    }

    public Integer pop() {
        Integer res = first.item;
        if (res == pq.peek()) {
            pq.remove();
        }
        first = first.next;
        --size;
        return res;
    }

    public Integer return_the_maximum() {
        return pq.peek();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Task2 stack = new Task2();
        stack.push(22);
        stack.push(5555);
        stack.push(11);
        stack.push(33);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + " maximum is " + stack.return_the_maximum());
        }
    }
}
