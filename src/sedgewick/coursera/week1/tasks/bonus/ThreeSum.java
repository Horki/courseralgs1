package sedgewick.coursera.week1.tasks.bonus;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class ThreeSum {
    // cube time N^3
    private static int cube_sum(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                for (int k = j + 1; k < arr.length; ++k) {
                    if (arr[i] + arr[j] + arr[k] == 0) {
//                        StdOut.println("(" + arr[i] + ") + (" + arr[j] + ") + (" + arr[k] + ") = 0");
                        ++count;
                    }
                }
            }
        }
        return count;
    }

    /*
    Worst case N^2
     */
    private static int better_sum(int[] arr) {
        int count = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                int k = Arrays.binarySearch(arr, -(arr[i] + arr[j]));
                if (k > j) {
                    ++count;
                }
            }
        }
        return count;
    }


    /*
    # Generate sequence of 8K random (and unique) integers
    $ seq -999999 999999 | shuf -n 8000
     */
    public static void main(String[] args) {
        StdOut.println("using unique integers!!");
        int[] arr = In.readInts();
        int[] arr_cp = Arrays.copyOf(arr, arr.length);
        {
            Stopwatch watch_cubsub = new Stopwatch();
            int c = cube_sum(arr);
            StdOut.println("solutions = " + c);
            double time = watch_cubsub.elapsedTime();
            StdOut.println("elapsed time for N3 = " + time);
        }
        {
            Stopwatch watch_bettersum = new Stopwatch();
            int c = better_sum(arr_cp);
            StdOut.println("solutions = " + c);
            double time = watch_bettersum.elapsedTime();
            StdOut.println("elapsed time for N2 = " + time);
        }

    }
}
