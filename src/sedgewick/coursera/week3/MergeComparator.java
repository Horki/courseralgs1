package sedgewick.coursera.week3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class MergeComparator {
    private static final Comparator<Integer> DESCENDING_INT = new DescendingInt();

    private static class DescendingInt implements Comparator<Integer> {
        public int compare(Integer x, Integer y) {
            return y.compareTo(x);
        }
    }

    private static boolean less(Comparator c, Object a, Object b) {
        return c.compare(a, b) < 0;
    }

    private static void exch(Object[] a, int x, int y) {
        Object swap = a[x];
        a[x] = a[y];
        a[y] = swap;
    }

    private static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        for (int i = 0; i < N; ++i) {
            for (int j = i; j > 0 && less(comparator, a[j], a[j - 1]); --j) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {10, 1, 3, 2, 4};
        sort(a, DESCENDING_INT);
        for (int i : a) {
            StdOut.print(i + ", ");
        }
        StdOut.println();
    }
}
