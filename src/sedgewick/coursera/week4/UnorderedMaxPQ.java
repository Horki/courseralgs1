package sedgewick.coursera.week4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedMaxPQ<Key extends Comparable<Key>> implements Iterable<Key> {
    private Key[] pq;
    private int N;

    public UnorderedMaxPQ(int c) {
        pq = (Key[]) new Comparable[c];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key k) {
        pq[N++] = k;
    }

    public int size() {
        return N;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (less(max, i)) {
                max = i;
            }
        }
        exch(max, N - 1);
        return pq[--N];
    }

    private boolean less(int x, int y) {
        return pq[x].compareTo(pq[y]) < 0;
    }

    // primitive swap
    private void exch(int x, int y) {
        Key tmp = pq[x];
        pq[x] = pq[y];
        pq[y] = tmp;
    }

    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        // create a new pq
        private final UnorderedMaxPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new UnorderedMaxPQ<>(size());
            for (int i = 0; i < N; ++i) {
                copy.insert(pq[i]);
            }
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }

    public static void main(String[] args) {
        int M = 5;
        UnorderedMaxPQ<Transaction> pq = new UnorderedMaxPQ<>(100);
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction item = new Transaction(line);
            pq.insert(item);
            if (pq.size() > M) {
                StdOut.println("Deleted " + pq.delMax());
            }
        }
        StdOut.println("Top + " + M + ", cheapest transactions");
        for (Transaction t : pq) {
            StdOut.println(t.toString());
        }
    }
}
