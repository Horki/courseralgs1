package sedgewick.coursera.week0;

import sedgewick.coursera.week0.libs.BinSearch;

public class Main {
    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5, 6};
        BinSearch binSearch = new BinSearch();
        int k = binSearch.bin_search(a, 6);
        System.out.println("result = " + k);
        System.out.println("done");
    }
}