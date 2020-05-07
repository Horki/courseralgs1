package sedgewick.coursera.week1.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sedgewick.coursera.week1.libs.QuickFindUF;

public class QuickFindUFTest {
    private QuickFindUF quickFindUF;

    @Before
    public void setUp() throws Exception {
        quickFindUF = new QuickFindUF(10);
    }

    @Test
    public void union() {
    }

    @Test
    public void connected() {
        quickFindUF.union(4, 3);
        quickFindUF.union(3, 8);
        quickFindUF.union(6, 5);
        quickFindUF.union(9, 4);
        quickFindUF.union(2, 1);
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
    }
}