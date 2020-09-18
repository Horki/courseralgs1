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
    private final ArrayList<UF> ufs = new ArrayList<>(3);
    private final int capacity = 10;

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
        for (UF uf : ufs) {
            uf.union(4, 3);
            uf.union(3, 8);
            uf.union(9, 4);
            Assert.assertTrue(uf.connected(3, 9));
            Assert.assertTrue(uf.connected(3, 8));
            Assert.assertTrue(uf.connected(3, 4));
            Assert.assertTrue(uf.connected(9, 4));
            Assert.assertTrue(uf.connected(4, 8));
        }
    }

    @Test
    public void connected() {
        for (UF uf : ufs) {
            uf.union(4, 3);
            Assert.assertFalse(uf.connected(0, 7));
            Assert.assertTrue(uf.connected(3, 4));
            Assert.assertTrue(uf.connected(4, 3));
        }
    }

    @Test
    public void find() {
        for (UF uf : ufs) {
            for (int i = 0; i < capacity; ++i) {
                Assert.assertEquals(i, uf.find(i));
            }
        }
    }

    @Test
    public void count() {
        for (UF uf : ufs) {
            Assert.assertEquals(uf.count(), capacity);
        }
    }
}