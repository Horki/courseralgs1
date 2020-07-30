package sedgewick.coursera.week5.tests;

import com.google.gson.Gson;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Assert;
import sedgewick.coursera.week5.BTree;

public class BTreeTest {

    @org.junit.Test
    public void my_btree() {
        BTree<Integer, Integer> btree = new BTree<>();
        Assert.assertTrue(btree.isEmpty());
        btree.put(4, 40);
        Assert.assertEquals(1, btree.size());
        btree.put(1, 10);
        btree.put(3, 30);
        btree.put(8, 80);
        btree.put(7, 70);
        btree.put(5, 50);
        btree.put(6, 60);
        Assert.assertEquals(7, btree.size());
        Assert.assertFalse(btree.isEmpty());
        Assert.assertTrue(btree.contains(3));
        Assert.assertEquals(30, (int) btree.get(3));
        Assert.assertEquals(1, (int) btree.min());
        Assert.assertEquals(8, (int) btree.max());
        Assert.assertFalse(btree.contains(33));
        {
            Gson gson = new Gson();
            StdOut.println(gson.toJson(btree));
        }
    }

    @org.junit.Test
    public void algs4_btree() {
        edu.princeton.cs.algs4.BTree btree = new edu.princeton.cs.algs4.BTree();
        Assert.assertTrue(btree.isEmpty());
        btree.put(4, 40);
        Assert.assertEquals(1, btree.size());
        btree.put(1, 10);
        btree.put(3, 30);
        btree.put(8, 80);
        btree.put(7, 70);
        btree.put(5, 50);
        btree.put(6, 60);
        Assert.assertEquals(7, btree.size());
        Assert.assertFalse(btree.isEmpty());
//        Assert.assertTrue(btree.contains(3));
//        Assert.assertEquals(1, (int) btree.min());
//        Assert.assertEquals(5, (int) btree.max());
//        Assert.assertFalse(btree.contains(33));
        Assert.assertEquals(30, (int) btree.get(3));
        {
            Gson gson = new Gson();
            StdOut.println(gson.toJson(btree));
        }
    }

}
