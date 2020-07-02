package sedgewick.coursera.week3;

import edu.princeton.cs.algs4.StdOut;

public class MergeSort {
    private static final int CUTOFF = 7;

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi - 1; ++i) {
            if (less((int) a[i + i], (int) a[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean less(int x, int y) {
        return x < y;
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);
        // Copy
        for (int k = lo; k <= hi; ++k) {
            aux[k] = a[k];
        }

        // Merge
        int i = lo, j = mid + 1;
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
            // Eliminate copy to auxiliary array!
//            if (i > mid) {
//                aux[k] = a[j++];
//            } else if (j > hi) {
//                aux[k] = a[i++];
//            } else if (less((int) aux[j], (int) aux[i])) {
//                aux[k] = a[j++];
//            } else {
//                aux[k] = a[i++];
//            }

        }
        assert isSorted(a, lo, hi);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        // For smaller arrays
//        if (hi <= lo + CUTOFF - 1) {
//            Insertion.sort(a, lo, hi);
//            return;
//        }
        int mid = lo + ((hi - lo) / 2);
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
//        sort(aux, a, lo, mid);
//        sort(aux, a, mid + 1, hi);
        // Optimisation
        if (!less((int) a[mid + 1], (int) a[mid])) {
            return;
        }
        merge(a, aux, lo, mid, hi);
//        merge(aux, a, lo, mid, hi);
    }

    private static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    // prog args -ea # enable assertion; -da # disable assertion (default)
    public static void main(String[] args) {
        Integer[] a = {10, 1, 3, 2, 4};
        sort(a);
        for (int i : a) {
            StdOut.print(i + ", ");
        }
        StdOut.println();
        StdOut.println(isSorted(a, 0, a.length - 1));
    }
}
