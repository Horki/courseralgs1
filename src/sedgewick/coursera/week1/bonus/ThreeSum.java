package sedgewick.coursera.week1.bonus;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ThreeSum {
    private static long count(int[] a) {
        int N = a.length;
        long count = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                for (int k = j + 1; k < N; ++k) {
                    ++count;
                }
            }
        }
        return count;
    }

    // 1Kints.txt 2Kints.txt 4Kints.txt 8Kints.txt
    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        int[] b = In.readInts(args[1]);
        int[] c = In.readInts(args[2]);
        int[] d = In.readInts(args[3]);
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
