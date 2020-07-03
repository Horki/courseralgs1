package sedgewick.coursera.week3;

import edu.princeton.cs.algs4.StdOut;

public class MergeBottomUp {
    private static Comparable[] aux;

    private static boolean less(int x, int y) {
        return x < y;
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        // Copy
        if (hi + 1 - lo >= 0) {
            System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
        }

        // Merge
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; ++k) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less((int) aux[j], (int) aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; ++sz) {
            for (int lo = 0; lo < (N - sz); ++lo) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {10, 1, 3, 2, 4};
        sort(a);
        for (int i : a) {
            StdOut.print(i + ", ");
        }
        StdOut.println();
    }

}
