package sedgewick.coursera.week0.libs;

import sedgewick.coursera.week0.interfaces.Binary;

public class BinSearch {
    public int bin_search(int[] n, int val) {
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

}
