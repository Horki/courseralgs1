package sedgewick.coursera.week1.bonus;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class ThreeSumFast {
    private static int count(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                if (BinarySearch.indexOf(a, -a[i] - a[j]) > j) {
                    ++count;
                }
            }
        }
        return count;
    }

    // 1Kints.txt 2Kints.txt 4Kints.txt 8Kints.txt
    public static void main(String[] args) {
        StdOut.println("Threesum faster, N^2 log N");
        StdOut.println(args);
        int[] a = new In(args[0]).readAllInts();
        int[] b = new In(args[1]).readAllInts();
        int[] c = new In(args[2]).readAllInts();
        int[] d = new In(args[3]).readAllInts();
        {
            StdOut.println("1K ints");
            Stopwatch stopwatch = new Stopwatch();
            StdOut.println(count(a));
            StdOut.println(count(a) == 0);
            double time = stopwatch.elapsedTime();
            StdOut.println("elapsed time: " + time);
        }
        {
            StdOut.println("2K ints");
            Stopwatch stopwatch = new Stopwatch();
            StdOut.println(count(b));
            StdOut.println(count(b) == 2);
            double time = stopwatch.elapsedTime();
            StdOut.println("elapsed time: " + time);
        }
        {
            StdOut.println("4K ints");
            Stopwatch stopwatch = new Stopwatch();
            StdOut.println(count(c));
            StdOut.println(count(c) == 2);
            double time = stopwatch.elapsedTime();
            StdOut.println("elapsed time: " + time);
        }
        {
            StdOut.println("8K ints");
            Stopwatch stopwatch = new Stopwatch();
            StdOut.println(count(d));
            StdOut.println(count(d) == 18);
            double time = stopwatch.elapsedTime();
            StdOut.println("elapsed time: " + time);
        }
    }
}
