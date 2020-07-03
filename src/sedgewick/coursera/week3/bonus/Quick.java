package sedgewick.coursera.week3.bonus;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
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
        exch(a, lo, j);
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

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static void main(String[] args) {
        StdOut.println("Quick sort");
        Integer[] a = {22, 11, 33, 2, 8, 14};
        StdOut.print("before sort: ");
        for (int v : a) {
            StdOut.print(v + ", ");
        }

        StdOut.println();
        sort(a);
        StdOut.print("after sort: ");
        for (int v : a) {
            StdOut.print(v + ", ");
        }

        StdOut.println();
        StdOut.println(isSorted(a));
    }
}
