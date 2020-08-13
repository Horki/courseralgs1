package sedgewick.coursera.week5.tests;

import com.google.gson.Gson;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Assert;
import sedgewick.coursera.week5.RedBlackTree;

public class RedBlackTreeTest {
    @org.junit.Test
    public void my_rbtree() {
        RedBlackTree<Integer, Integer> rbtree = new RedBlackTree<>();
        Assert.assertTrue(rbtree.isEmpty());
        rbtree.put(4, 40);
        Assert.assertEquals(1, rbtree.size());
        rbtree.put(1, 10);
        rbtree.put(3, 30);
        rbtree.put(8, 80);
        rbtree.put(7, 70);
        rbtree.put(5, 50);
        rbtree.put(6, 60);
        Assert.assertEquals(7, rbtree.size());
        Assert.assertFalse(rbtree.isEmpty());
        Assert.assertTrue(rbtree.contains(3));
        Assert.assertEquals(30, (int) rbtree.get(3));
        Assert.assertEquals(1, (int) rbtree.min());
        Assert.assertEquals(8, (int) rbtree.max());
        Assert.assertFalse(rbtree.contains(33));
        {
            Gson gson = new Gson();
            StdOut.println(gson.toJson(rbtree));
        }
    }

    @org.junit.Test
    public void my_rbtree_left_rotate_thousand() {
        RedBlackTree<Integer, Integer> rbtree = new RedBlackTree<>();
        Assert.assertTrue(rbtree.isEmpty());
        for (int i = 1; i <= 1000; ++i) {
            rbtree.put(i, i);
        }
        Assert.assertEquals(1000, rbtree.size());
        Assert.assertEquals(9, rbtree.height());
        Assert.assertFalse(rbtree.isEmpty());
        Assert.assertEquals((Integer) 500, rbtree.get(500));
        Assert.assertEquals((Integer) 1, rbtree.min());
        Assert.assertEquals((Integer) 1000, rbtree.max());
    }

    @org.junit.Test
    public void my_rbtree_right_rotate_thousand() {
        RedBlackTree<Integer, Integer> rbtree = new RedBlackTree<>();
        Assert.assertTrue(rbtree.isEmpty());
        for (int i = 1000; i > 0; --i) {
            rbtree.put(i, i);
        }
        Assert.assertEquals(1000, rbtree.size());
        Assert.assertEquals(14, rbtree.height());
        Assert.assertFalse(rbtree.isEmpty());
        Assert.assertEquals((Integer) 500, rbtree.get(500));
        Assert.assertEquals((Integer) 1, rbtree.min());
        Assert.assertEquals((Integer) 1000, rbtree.max());
    }

    @org.junit.Test
    public void algs4_rbtree_left_rotate_thousand() {
        RedBlackBST<Integer, Integer> rbtree = new RedBlackBST<>();
        Assert.assertTrue(rbtree.isEmpty());
        for (int i = 1; i <= 1000; ++i) {
            rbtree.put(i, i);
        }
        Assert.assertEquals(1000, rbtree.size());
        Assert.assertEquals(9, rbtree.height());
        Assert.assertFalse(rbtree.isEmpty());
        Assert.assertEquals((Integer) 500, rbtree.get(500));
        Assert.assertEquals((Integer) 1, rbtree.min());
        Assert.assertEquals((Integer) 1000, rbtree.max());
    }

    @org.junit.Test
    public void algs4_rbtree_right_rotate_thousand() {
        RedBlackBST<Integer, Integer> rbtree = new RedBlackBST<>();
        Assert.assertTrue(rbtree.isEmpty());
        for (int i = 1000; i > 0; --i) {
            rbtree.put(i, i);
        }
        Assert.assertEquals(1000, rbtree.size());
        Assert.assertEquals(14, rbtree.height());
        Assert.assertFalse(rbtree.isEmpty());
        Assert.assertEquals((Integer) 500, rbtree.get(500));
        Assert.assertEquals((Integer) 1, rbtree.min());
        Assert.assertEquals((Integer) 1000, rbtree.max());
    }

    @org.junit.Test
    public void my_left_rotate_pre_order() {
        int j = 1;
        RedBlackTree<Character, Integer> my_rbtree = new RedBlackTree<>();
        char[] result = {'d', 'b', 'a', 'c', 'h', 'f', 'e', 'g', 'i'};
        for (char i = 'a'; i <= 'i'; ++i, ++j) {
            my_rbtree.put(i, j);
        }
        j = 0;
        for (Character item : my_rbtree.preOrder()) {
            Assert.assertEquals(item, (Character) result[j++]);
        }
    }

    @org.junit.Test
    public void my_right_rotate_pre_order() {
        int j = 1;
        RedBlackTree<Character, Integer> my_rbtree = new RedBlackTree<>();
        char[] result = {'f', 'd', 'b', 'a', 'c', 'e', 'h', 'g', 'i'};
        for (char i = 'i'; i >= 'a'; --i, ++j) {
            my_rbtree.put(i, j);
        }
        j = 0;
        for (Character item : my_rbtree.preOrder()) {
            Assert.assertEquals(item, (Character) result[j++]);
        }
    }

    @org.junit.Test
    public void my_left_rotate_in_order() {
        int j = 1;
        RedBlackTree<Character, Integer> my_rbtree = new RedBlackTree<>();
        char[] result = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
        for (char i = 'a'; i <= 'i'; ++i, ++j) {
            my_rbtree.put(i, j);
        }
        j = 0;
        for (Character item : my_rbtree.inOrder()) {
            Assert.assertEquals(item, (Character) result[j++]);
        }
    }

    @org.junit.Test
    public void my_right_rotate_in_order() {
        int j = 1;
        RedBlackTree<Character, Integer> my_rbtree = new RedBlackTree<>();
        char[] result = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
        for (char i = 'i'; i >= 'a'; --i, ++j) {
            my_rbtree.put(i, j);
        }
        j = 0;
        for (Character item : my_rbtree.inOrder()) {
            Assert.assertEquals(item, (Character) result[j++]);
        }
    }

    @org.junit.Test
    public void my_left_rotate_post_order() {
        int j = 1;
        RedBlackTree<Character, Integer> my_rbtree = new RedBlackTree<>();
        char[] result = {'a', 'c', 'b', 'e', 'g', 'f', 'i', 'h', 'd'};
        for (char i = 'a'; i <= 'i'; ++i, ++j) {
            my_rbtree.put(i, j);
        }
        j = 0;
        for (Character item : my_rbtree.postOrder()) {
            Assert.assertEquals(item, (Character) result[j++]);
        }
    }

    @org.junit.Test
    public void my_right_rotate_post_order() {
        int j = 1;
        RedBlackTree<Character, Integer> my_rbtree = new RedBlackTree<>();
        char[] result = {'a', 'c', 'b', 'e', 'd', 'g', 'i', 'h', 'f'};
        for (char i = 'i'; i >= 'a'; --i, ++j) {
            my_rbtree.put(i, j);
        }
        j = 0;
        for (Character item : my_rbtree.postOrder()) {
            Assert.assertEquals(item, (Character) result[j++]);
        }
    }

    @org.junit.Test
    public void my_left_rotate_level_order() {
        int j = 1;
        RedBlackTree<Character, Integer> my_rbtree = new RedBlackTree<>();
        char[] result = {'d', 'b', 'h', 'a', 'c', 'f', 'i', 'e', 'g'};
        for (char i = 'a'; i <= 'i'; ++i, ++j) {
            my_rbtree.put(i, j);
        }
        j = 0;
        for (Character item : my_rbtree.levelOrder()) {
            Assert.assertEquals(item, (Character) result[j++]);
        }
    }

    @org.junit.Test
    public void my_right_rotate_level_order() {
        int j = 1;
        RedBlackTree<Character, Integer> my_rbtree = new RedBlackTree<>();
        char[] result = {'f', 'd', 'h', 'b', 'e', 'g', 'i', 'a', 'c'};
        for (char i = 'i'; i >= 'a'; --i, ++j) {
            my_rbtree.put(i, j);
        }
        j = 0;
        for (Character item : my_rbtree.levelOrder()) {
            Assert.assertEquals(item, (Character) result[j++]);
        }
    }
}
