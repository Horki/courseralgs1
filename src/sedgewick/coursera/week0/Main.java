package sedgewick.coursera.week0;

import sedgewick.coursera.week0.interfaces.Binary;

public class Main {
    public static int bin_search(int[] n, int val) {
        Binary s = (int min, int max) -> {
            while (min <= max) {
                int pivot = (min + max) / 2;
                if (val == n[pivot]) {
                    return pivot;
                }

                if (val > n[pivot]) {
                    min = pivot + 1;
                } else {
                    max = pivot - 1;
                }
            }
            return -1;
        };

        return s.search(0, n.length - 1);
    }

    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5, 6};
        int k = bin_search(a, 6);
        System.out.println("result = " + k);
        System.out.println("done");
    }
}