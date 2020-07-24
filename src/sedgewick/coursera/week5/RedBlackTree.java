package sedgewick.coursera.week5;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;
import sedgewick.coursera.week4.bonus2bst.BinarySearchTree;
import sedgewick.coursera.week5.interfaces.TwoThreeTree;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class RedBlackTree<K extends Comparable<K>, V> implements TwoThreeTree<K, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left;
        private Node right;
        private int size;
        private boolean color;

        public Node(K k, V v, int cnt, boolean c) {
            key = k;
            val = v;
            size = cnt;
            color = c;
            left = null;
            right = null;
        }
    }

    public RedBlackTree() {
        root = null;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    public V get(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
    }

    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        h.color = RED;
        x.left = h;
        x.color = h.color;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        h.color = RED;
        x.right = h;
        x.color = h.color;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        assert isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(K k, V v) {
        // search for key
        // update value if find, grow table if new
        root = put(root, k, v);
        root.color = BLACK;
    }

    private Node put(Node x, K k, V v) {
        // insert at bottom (and color red)
        if (x == null) {
            return new Node(k, v, 1, RED);
        }
        int cmp = k.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, k, v);
        } else if (cmp > 0) {
            x.right = put(x.right, k, v);
        } else {
            x.val = v;
        }
        // Lean left
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        // Balance 4-node
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        // Split 4-node
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        x.size = size(x.left) + size(x.right) + 1;

        return x;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) {
            return 0;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    public boolean contains(K k) {
        return get(k) != null;
    }

    public K min() {
        if (isEmpty()) {
            throw new NoSuchElementException("Missing min, tree is empty");
        }
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    public K max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Missing max, tree is empty");
        }
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    public K floor(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls floor() with empty symbol table");
        }
        Node x = floor(root, key);
        if (x == null) {
            throw new NoSuchElementException("argument to floor() is too small");
        }
        return x.key;
    }

    private Node floor(Node x, K k) {
        if (x == null) {
            return null;
        }
        int cmp = k.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, k);
        }
        Node t = floor(x.right, k);
        if (t != null) {
            return t;
        }
        return x;
    }

    public K ceiling(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls ceiling() with empty symbol table");
        }
        Node x = ceiling(root, key);
        if (x == null) {
            throw new NoSuchElementException("argument to ceiling() is too small");
        }
        return x.key;
    }

    private Node ceiling(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        }
        return x;
    }


    public boolean isEmpty() {
        return root == null;
    }

    // Deletes
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("BST underflow");
        }

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMin(Node h) {
        if (h.left == null) {
            return null;
        }

        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }

        h.left = deleteMin(h.left);
        return balance(h);
    }

    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("BST underflow");
        }

        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = deleteMax(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left)) {
            h = rotateRight(h);
        }
        if (h.right == null) {
            return null;
        }
        if (!isRed(h.right) && !isRed(h.right.left)) {
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }


    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    // default iterable is inOrder
    public Iterable<K> keys() {
        Queue<K> q = new Queue<>();
        inOrder(root, q);
        return q;
    }

    // Iterators, same as in BST
    public Iterable<K> inOrder() {
        Queue<K> q = new Queue<>();
        inOrder(root, q);
        return q;
    }

    private void inOrder(Node x, Queue<K> q) {
        if (x == null) {
            return;
        }
        inOrder(x.left, q);
        q.enqueue((K) x.key);
        inOrder(x.right, q);
    }

    public Iterable<K> postOrder() {
        Queue<K> q = new Queue<>();
        postOrder(root, q);
        return q;
    }

    private void postOrder(Node x, Queue<K> q) {
        if (x == null) {
            return;
        }
        postOrder(x.left, q);
        postOrder(x.right, q);
        q.enqueue((K) x.key);
    }

    public Iterable<K> preOrder() {
        Queue<K> q = new Queue<>();
        preOrder(root, q);
        return q;
    }

    private void preOrder(Node x, Queue<K> q) {
        if (x == null) {
            return;
        }
        q.enqueue((K) x.key);
        preOrder(x.left, q);
        preOrder(x.right, q);
    }

    public Iterable<K> levelOrder() {
        Queue<K> q = new Queue<>();
        Queue<Node> tmp = new Queue<>();
        tmp.enqueue(root);
        while (!tmp.isEmpty()) {
            Node n = tmp.dequeue();
            q.enqueue((K) n.key);
            if (n.left != null) {
                tmp.enqueue(n.left);
            }
            if (n.right != null) {
                tmp.enqueue(n.right);
            }
        }
        return q;
    }

    public static void main(String[] args) {
        RedBlackTree<Integer, Integer> rbTree = new RedBlackTree<>();
        StdOut.println("init, isEmpty: " + rbTree.isEmpty());
        rbTree.put(1, 2);
        StdOut.println("size: " + rbTree.size());
        StdOut.println("height: " + rbTree.height());
        StdOut.println("init, isEmpty: " + rbTree.isEmpty());
        rbTree.put(3, 4);
        StdOut.println("size: " + rbTree.size());
        StdOut.println("height: " + rbTree.height());
        rbTree.put(5, 6);
        StdOut.println("size: " + rbTree.size());
        StdOut.println("height: " + rbTree.height());
        rbTree.put(7, 8);
        StdOut.println("size: " + rbTree.size());
        StdOut.println("height: " + rbTree.height());
        rbTree.put(9, 10);
        StdOut.println("contains 9: " + rbTree.contains(9));
        StdOut.println("size: " + rbTree.size());
        StdOut.println("height: " + rbTree.height());
        StdOut.println(rbTree.get(1) == 2);
        StdOut.println(rbTree.get(3) == 4);
        StdOut.println(rbTree.get(5) == 6);
        StdOut.println(rbTree.get(7) == 8);
        StdOut.println(rbTree.get(9) == 10);

        StdOut.println("max key is: " + rbTree.max() + ", min key is: " + rbTree.min());
        StdOut.println("floor of 7 is: " + rbTree.floor(7));
        StdOut.println("ceiling of 7 is: " + rbTree.ceiling(7));
        StdOut.println("in order print");
        for (Integer key : rbTree.keys()) {
            StdOut.println(key + ":" + rbTree.get(key));
        }
        StdOut.println();
        StdOut.println("Deleted min");
        StdOut.println("Deleted max");
        rbTree.deleteMin();
        rbTree.deleteMax();
        StdOut.println("in order print");
        for (Integer key : rbTree.keys()) {
            StdOut.println(key + ":" + rbTree.get(key));
        }

        RedBlackTree<Character, Integer> rbTreeLeftRotate = new RedBlackTree<>();
        rbTreeLeftRotate.put('a', 1);
        rbTreeLeftRotate.put('b', 2);
        rbTreeLeftRotate.put('c', 3);
        rbTreeLeftRotate.put('d', 4);
        rbTreeLeftRotate.put('e', 5);
        rbTreeLeftRotate.put('f', 6);
        rbTreeLeftRotate.put('g', 7);
        rbTreeLeftRotate.put('h', 8);
        rbTreeLeftRotate.put('i', 9);

        // PreOrder
        StdOut.println("Height size: " + rbTreeLeftRotate.height() + ", Size: " + rbTreeLeftRotate.size());
        StdOut.print("PreOrder: ");
        for (Character c : rbTreeLeftRotate.preOrder()) {
            StdOut.print(c + ", ");
        }
        StdOut.print("\nInOrder: ");
        for (Character c : rbTreeLeftRotate.inOrder()) {
            StdOut.print(c + ", ");
        }
        StdOut.print("\nPostOrder: ");
        for (Character c : rbTreeLeftRotate.postOrder()) {
            StdOut.print(c + ", ");
        }
        StdOut.print("\nLevelOrder: ");
        for (Character c : rbTreeLeftRotate.levelOrder()) {
            StdOut.print(c + ", ");
        }

    }
}
