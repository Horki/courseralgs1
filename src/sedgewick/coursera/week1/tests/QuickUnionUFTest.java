package sedgewick.coursera.week1.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sedgewick.coursera.week1.libs.QuickUnionUF;

import static org.junit.Assert.*;

public class QuickUnionUFTest {
    private QuickUnionUF quickUnionUF;
    @Before
    public void setUp() throws Exception {
        quickUnionUF = new QuickUnionUF(10);
    }

    @Test
    public void union() {
    }

    @Test
    public void connected() {
        quickUnionUF.union(3, 4);
        quickUnionUF.union(2, 9);
        quickUnionUF.union(3, 9);
        quickUnionUF.union(4, 9);
        quickUnionUF.union(5, 6);
        Assert.assertTrue(quickUnionUF.connected(3, 9));
    }

    @Test
    public void find() {
    }

    @Test
    public void count() {
    }
}