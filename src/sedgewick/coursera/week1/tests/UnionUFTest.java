package sedgewick.coursera.week1.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sedgewick.coursera.week1.interfaces.UF;
import sedgewick.coursera.week1.libs.QuickUnionUF;
import sedgewick.coursera.week1.libs.QuickUnionWeightedUF;

import java.util.ArrayList;

public class UnionUFTest {
    private final ArrayList<UF> unionFind = new ArrayList(2);
    private final int N = 10;

    @Before
    public void setUp() throws Exception {
        unionFind.add(new QuickUnionUF(N));
        unionFind.add(new QuickUnionWeightedUF(N));
        for (UF uf : unionFind) {
            uf.union(3, 4);
//          idx: 0 1 2 [3] 4 5 6 7 8 9
//          val: 0 1 2 [4] 4 5 6 7 8 9

            uf.union(2, 9);
//          idx: 0 1 [2] 3 4 5 6 7 8 9
//          val: 0 1 [9] 4 4 5 6 7 8 9

            uf.union(3, 9);
            // P = 3 -> redirects to 4
//          idx: 0 1 2 |3| /*[4] 5 6 7 8 9
//          val: 0 1 9 |4|/  [9] 5 6 7 8 9

            uf.union(4, 9);
            // nothing, already connected
//          idx: 0 1 2 3 4 5 6 7 8 9
//          val: 0 1 9 4 9 5 6 7 8 9

            uf.union(5, 6);
//          idx: 0 1 2 3 4 [5] 6 7 8 9
//          val: 0 1 9 4 9 [6] 6 7 8 9
        }
    }

    @Test
    public void union() {
        for (UF uf : unionFind) {
            Assert.assertEquals(6, uf.count());
            uf.union(8, 9);
//          idx: 0 1 2 3 4 5 6 7 [8] 9
//          val: 0 1 9 4 9 6 6 7 [9] 9
            Assert.assertTrue(uf.connected(8, 9));
            Integer ex_9 = 9;
            Assert.assertEquals(ex_9, uf.find(8));
            Assert.assertEquals(5, uf.count());
        }
    }

    @Test
    public void connected() {
//       0    1    9    6    7    8
//                / \   |
//               2   4  5
//                   |
//                   3
        for (UF uf : unionFind) {
            Assert.assertTrue(uf.connected(3, 9));
            Assert.assertTrue(uf.connected(9, 3));
            Assert.assertFalse(uf.connected(0, 1));
            Assert.assertFalse(uf.connected(8, 7));
            Assert.assertFalse(uf.connected(3, 5));
        }
    }

    @Test
    public void find() {
//      idx: 0 1 2 3 4 5 6 7 8 9
//      val: 0 1 9 4 9 6 6 7 8 9
        for (UF uf : unionFind) {
            Integer ex_9 = 9;
            Integer ex_4 = 4;
            Integer ex_6 = 6;
            Assert.assertEquals(ex_9, uf.find(3));
            Assert.assertEquals(ex_9, uf.find(4));
            Assert.assertNotEquals(ex_4, uf.find(3));
            Assert.assertEquals(ex_6, uf.find(5));
        }
    }

    @Test
    public void count() {
        for (UF uf : unionFind) {
            Assert.assertEquals(6, uf.count());
            Assert.assertNotEquals(N, uf.count());
        }
    }
}