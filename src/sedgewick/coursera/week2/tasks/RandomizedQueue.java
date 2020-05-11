package sedgewick.coursera.week2.tasks;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/*
Randomized queue.

A randomized queue is similar to a stack or queue,
    except that the item removed is chosen uniformly at
    random among items in the data structure.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
    private class DequeIter implements Iterator<Item> {
        private int s;
        private int[] randomizedArr;

        public DequeIter() {
            s = size;
            for (int i = 0; i < s; ++i) {
                randomizedArr[i] = i;
            }
            StdRandom.shuffle(randomizedArr);
        }

        public boolean hasNext() {
            return s > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more items to return!");
            }
            return arr[randomizedArr[--s]];
        }

        public void remove() {
            throw new UnsupportedOperationException("Can't remove from iterator");
        }
    }

    private int size;
    private Item[] arr;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        arr = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Enqeue: NULL item");
        }
        if (arr.length == size) {
            resize(2 * size);
        }
        arr[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Dequeue: Queue is empty!");
        }
        int idx = StdRandom.uniform(size);
        Item r = arr[idx];
        arr[idx] = arr[size];
        arr[size] = null; // remove last item
        --size;
        if ((arr.length / 4) == size) {
            resize(arr.length / 2);
        }
        return r;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Sample: Queue is empty!");
        }
        int idx = StdRandom.uniform(size);
        return arr[idx];
    }

    // Same as in ResizingArrayStackOfStrings example
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; ++i) {
            copy[i] = arr[i];
        }
        arr = copy;
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new DequeIter();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 1; i <= 20; ++i) {
            rq.enqueue(i);
        }
        for (Integer i : rq) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

}