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
                if (BinarySearch.rank(-a[i] - a[j], a) > j) {
                    ++count;
                }
            }
        }
        return count;
    }

    // 1Kints.txt 2Kints.txt 4Kints.txt 8Kints.txt
    public static void main(String[] args) {
        StdOut.println("Threesum faster, N^2 log N");
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
