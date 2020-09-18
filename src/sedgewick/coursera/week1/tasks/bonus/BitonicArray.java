package sedgewick.coursera.week1.tasks.bonus;

import edu.princeton.cs.algs4.StdOut;

public class BitonicArray {
    // O(ln N)
    private static int binary_search(int[] arr, int val, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + ((hi - lo) / 2);
            if (arr[mid] == val) {
                return mid;
            }
            if (arr[mid] > val) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1; // not found
    }

    // O(ln N)
    private static int binary_search_reverse(int[] arr, int val, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + ((hi - lo) / 2);
            if (arr[mid] == val) {
                return mid;
            }
            if (arr[mid] > val) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1; // not found
    }

    private static int find_max(int[] arr, int lo, int hi) {
        int mid = lo + ((hi - lo) / 2);
        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
            return mid;
        }
        if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
            find_max(arr, mid, hi);
        } else if (arr[mid] < arr[mid - 1] && arr[mid] > arr[mid + 1]) {
            find_max(arr, lo, mid);
        }
        return mid;
    }

    private static int bitonic_search(int[] arr, int val) {
        int mid_max = find_max(arr, 0, arr.length - 1);
        if (arr[mid_max] == val) {
            return mid_max;
        }
        int r1 = binary_search(arr, val, 0, mid_max);
        if (r1 != -1) {
            return r1;
        }
        return binary_search_reverse(arr, val, mid_max + 1, arr.length - 1);
    }


    public static void main(String[] args) {
        // "unit" tests
        {
            // Regular bin search
            int[] arr = {10, 22, 33, 44, 55};
            StdOut.println(binary_search(arr, 10, 0, arr.length - 1) == 0);
            StdOut.println(binary_search(arr, 22, 0, arr.length - 1) == 1);
            StdOut.println(binary_search(arr, 33, 0, arr.length - 1) == 2);
            StdOut.println(binary_search(arr, 44, 0, arr.length - 1) == 3);
            StdOut.println(binary_search(arr, 55, 0, arr.length - 1) == 4);
        }
        {
            // Reversed bin search
            int[] arr = {55, 44, 33, 22, 10};
            StdOut.println(binary_search_reverse(arr, 10, 0, arr.length - 1) == 4);
            StdOut.println(binary_search_reverse(arr, 22, 0, arr.length - 1) == 3);
            StdOut.println(binary_search_reverse(arr, 33, 0, arr.length - 1) == 2);
            StdOut.println(binary_search_reverse(arr, 44, 0, arr.length - 1) == 1);
            StdOut.println(binary_search_reverse(arr, 55, 0, arr.length - 1) == 0);
        }
        {
            int[] arr = {10, 22, 33, 44, 55, -33, -44, -88, -99};
            StdOut.println(bitonic_search(arr, 44) == 3);
            StdOut.println(bitonic_search(arr, -33) == 5);
            StdOut.println(bitonic_search(arr, -333) == -1);
        }

    }
}
