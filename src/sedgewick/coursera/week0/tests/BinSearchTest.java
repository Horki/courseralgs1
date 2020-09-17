package sedgewick.coursera.week0.tests;

import org.junit.Assert;
import sedgewick.coursera.week0.libs.BinSearch;

public class BinSearchTest {

    @org.junit.Test
    public void bin_search() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        BinSearch binSearch = new BinSearch();
        int r1 = binSearch.bin_search(nums, 6);
        Assert.assertEquals(r1, 5);
    }

    @org.junit.Test
    public void bin_search_fails() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        BinSearch binSearch = new BinSearch();
        int r1 = binSearch.bin_search(nums, 66);
        Assert.assertEquals(r1, -1);
    }

}