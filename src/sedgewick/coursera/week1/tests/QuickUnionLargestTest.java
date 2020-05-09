package sedgewick.coursera.week1.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sedgewick.coursera.week1.interfaces.UF;
import sedgewick.coursera.week1.libs.QuickUnionLargest;

import static org.junit.Assert.*;

public class QuickUnionLargestTest {
    private UF largestUF;
    @Before
    public void setUp() throws Exception {
        largestUF = new QuickUnionLargest(10);
        largestUF.union(1, 9);
        largestUF.union(2, 9);
        largestUF.union(3, 9);
        largestUF.union(6, 9);
    }

    @Test
    public void find() {
        Integer ex_9 = 9;
        Assert.assertEquals(ex_9, largestUF.find(1));
        Assert.assertEquals(ex_9, largestUF.find(2));
        Assert.assertEquals(ex_9, largestUF.find(3));
    }

    @Test
    public void count() {
        Assert.assertEquals(6, largestUF.count());
    }
}