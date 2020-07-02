package sedgewick.coursera.week2.bonus;

import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

public class Shuffle {
    public static void sort(Comparable[] a) {
        int N = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < N; ++i) {
            int change = (i + random.nextInt(N - 1)) % N;
            exch(a, i, change);
        }
        if (!isSorted(a)) {
            sort(a);
        }
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
        StdOut.println("Shuffling sort");
        Integer[] a = {22, 11, 33, 2, 8, 14};
        StdOut.print("before sort: ");
        for (int i = 0; i < a.length; ++i) {
            StdOut.print(a[i] + ", ");
        }
        StdOut.println();
        sort(a);
        StdOut.print("after sort: ");
        for (int i = 0; i < a.length; ++i) {
            StdOut.print(a[i] + ", ");
        }

        StdOut.println();
        StdOut.println(isSorted(a));
    }
}
