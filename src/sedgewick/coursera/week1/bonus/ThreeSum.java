package sedgewick.coursera.week1.bonus;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ThreeSum {
    private static int count(int[] a) {
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                for (int k = j + 1; k < N; ++k) {
                    if (a[i] + a[j] + a[k] == 0) {
                        ++count;
                    }
                }
            }
        }
        return count;
    }

    // 1Kints.txt 2Kints.txt 4Kints.txt 8Kints.txt
    public static void main(String[] args) {
        int[] a = new In(args[0]).readAllInts();
        int[] b = new In(args[1]).readAllInts();
        int[] c = new In(args[2]).readAllInts();
        int[] d = new In(args[3]).readAllInts();
        {
            StdOut.println("1K ints");
            Stopwatch stopwatch = new Stopwatch();
            StdOut.println(count(a));
            double time = stopwatch.elapsedTime();
            StdOut.println("elapsed time: " + time);
        }
        {
            StdOut.println("2K ints");
            Stopwatch stopwatch = new Stopwatch();
            StdOut.println(count(b));
            double time = stopwatch.elapsedTime();
            StdOut.println("elapsed time: " + time);
        }
        {
            StdOut.println("4K ints");
            Stopwatch stopwatch = new Stopwatch();
            StdOut.println(count(c));
            double time = stopwatch.elapsedTime();
            StdOut.println("elapsed time: " + time);
        }
        {
            StdOut.println("8K ints");
            Stopwatch stopwatch = new Stopwatch();
            StdOut.println(count(d));
            double time = stopwatch.elapsedTime();
            StdOut.println("elapsed time: " + time);
        }
    }
}
