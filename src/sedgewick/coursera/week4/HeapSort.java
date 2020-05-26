package sedgewick.coursera.week4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class HeapSort {
    // This class should not be instantiated.
    private HeapSort() {
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        while (N > 1) {
            exch(a, 1, N);
            sink(a, 1, --N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) {
                j++;
            }
            if (!less(a, k, j)) {
                break;
            }
            exch(a, k, j);
            k = j;
        }

    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = tmp;
    }

    public static void print(Comparable[] a) {
        for (int i = 0; i < a.length; ++i) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
        int N = 16;
        Transaction[] a = new Transaction[N];
        int i = 0;
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction item = new Transaction(line);
            a[i++] = item;
            if (i >= N) {
                break;
            }
        }
        StdOut.println("Unsorted");
        print(a);
        sort(a);
        StdOut.println("Sorted");
        print(a);
    }
}
