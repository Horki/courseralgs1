package sedgewick.coursera.week3.bonus;

import edu.princeton.cs.algs4.StdOut;

public class QuickSort {
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi;
        while (true) {
            // Find item to left swap
            while (less(a[++i], a[lo])) {
                if (i == hi) {
                    break;
                }
            }
            // Find item to right swap
            while (less(a[lo], a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            // check if pointers cross
            if (i >= j) {
                break;
            }
            // swap
            exch(a, i, j);
        }
        // swap with partitioning item
        exch(a, lo, hi);
        // return index of item now known to be in place
        return j;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; ++i) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        StdOut.println("tusam");
    }
}
