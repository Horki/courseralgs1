package sedgewick.coursera.week1.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sedgewick.coursera.week1.interfaces.UF;
import sedgewick.coursera.week1.libs.QuickFindUF;
import sedgewick.coursera.week1.libs.QuickUnionWeightedUF;
import sedgewick.coursera.week1.libs.QuickUnionUF;

import java.util.ArrayList;


public class UFTest {
    private ArrayList<UF> ufs = new ArrayList<UF>(3);

    @Before
    public void setUp() throws Exception {
        ufs.add(new QuickUnionUF(10));
        ufs.add(new QuickFindUF(10));
        ufs.add(new QuickUnionWeightedUF(10));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void union() {
        for (UF quickUF : ufs) {
            quickUF.union(4, 3);
            quickUF.union(3, 8);
            quickUF.union(9, 4);
            Assert.assertEquals(true, quickUF.connected(3, 9));
            Assert.assertEquals(true, quickUF.connected(3, 8));
            Assert.assertEquals(true, quickUF.connected(3, 4));
            Assert.assertEquals(true, quickUF.connected(9, 4));
            Assert.assertEquals(true, quickUF.connected(4, 8));
        }
    }

    @Test
    public void connected() {
        for (UF quickUF : ufs) {
            quickUF.union(4, 3);
            Assert.assertEquals(false, quickUF.connected(0, 7));
            Assert.assertEquals(true, quickUF.connected(3, 4));
            Assert.assertEquals(true, quickUF.connected(4, 3));
        }
    }

    @Test
    public void find() {
    }

    @Test
    public void count() {
        for (UF quickUF : ufs) {
            Assert.assertEquals(quickUF.count(), 10);
        }
    }
}