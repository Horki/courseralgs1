package sedgewick.coursera.week3.bonus;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3 {
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
        int lt = lo;
        int gt = hi;
        Comparable v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                ++i;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        StdOut.println("Quick 3 way sort");
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
