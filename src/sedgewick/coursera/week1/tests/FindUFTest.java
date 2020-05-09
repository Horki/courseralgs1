package sedgewick.coursera.week1.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sedgewick.coursera.week1.libs.QuickFindUF;

public class FindUFTest {
    private QuickFindUF quickFindUF;
    private final int N = 10;

    @Before
    public void setUp() throws Exception {
        quickFindUF = new QuickFindUF(N);
        quickFindUF.union(4, 3);
        quickFindUF.union(3, 8);
        quickFindUF.union(6, 5);
        quickFindUF.union(9, 4);
        quickFindUF.union(2, 1);
    }

    @Test
    public void count() {
        Assert.assertEquals(N, quickFindUF.count());
    }

    @Test
    public void connected() {
        Assert.assertFalse(quickFindUF.connected(0, 7));
        Assert.assertTrue(quickFindUF.connected(8, 9));
        quickFindUF.union(5, 0);
        quickFindUF.union(7, 2);
        quickFindUF.union(6, 1);
        quickFindUF.union(1, 0);
        Assert.assertTrue(quickFindUF.connected(0, 7));
    }

    @Test
    public void find() {
        Integer ex_8 = 8;
        Assert.assertEquals(ex_8, quickFindUF.find(4));
        Integer ex_5 = 5;
        Assert.assertEquals(ex_5, quickFindUF.find(6));
    }
}