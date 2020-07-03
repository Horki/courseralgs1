package sedgewick.coursera.week4.bonus2bst;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class BinarySearchTree<K extends Comparable<K>, V> {
    private class Node<K, V> {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int size;

        public Node(K k, V v, int size) {
            key = k;
            value = v;
            this.size = size;
            left = null;
            right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void put(K k, V v) {
        root = put(root, k, v);
    }

    private Node put(Node x, K k, V v) {
        if (x == null) {
            return new Node(k, v, 1);
        }
        int cmp = k.compareTo((K) x.key);
        if (cmp < 0) {
            x.left = put(x.left, k, v);
        } else if (cmp > 0) {
            x.right = put(x.right, k, v);
        } else {
            x.value = v;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public V get(K k) {
        Node x = root;
        while (x != null) {
            int cmp = k.compareTo((K) x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return (V) x.value;
            }
        }
        return null;
    }

    public void delete(K k) {
        if (k == null) {
            throw new IllegalArgumentException("Delete fails");
        }
        root = delete(root, k);
    }

    public K max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Max fail");
        }
        return (K) max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    public K min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return (K) min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }


    private Node delete(Node x, K k) {
        if (x == null) {
            return null;
        }
        int cmp = k.compareTo((K) x.key);
        if (cmp < 0) {
            x.left = delete(x.left, k);
        } else if (cmp > 0) {
            x.right = delete(x.right, k);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node tmp = x;
            x = min(tmp.right);
            x.right = deleteMin(tmp.right);
            x.left = tmp.left;
        }
        x.size = size(x.left) + size(x.right) + 1;

        return x;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
//        assert check();
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table underflow");
        }
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }


    public Iterable<K> iterator() {
        return null;
    }

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

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.size;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public int rank(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        return rank(key, root);
    }

    private int rank(K key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo((K) x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }

    public K select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    private K select(Node x, int rank) {
        if (x == null) {
            return null;
        }
        int leftSize = size(x.left);
        if (leftSize > rank) {
            return (K) select(x.left, rank);
        } else if (leftSize < rank) {
            return (K) select(x.right, rank - leftSize - 1);
        } else {
            return (K) x.key;
        }
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
            throw new NoSuchElementException("argument to floor() is too large");
        } else {
            return (K) x.key;
        }
    }

    private Node ceiling(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo((K) x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) {
                return t;
            } else {
                return x;
            }
        }
        return ceiling(x.right, key);
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
        } else {
            return (K) x.key;
        }
    }

    private Node floor(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo((K) x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    public K floor2(K key) {
        K x = (K) floor2(root, key, null);
        if (x == null) {
            throw new NoSuchElementException("argument to floor() is too small");
        } else {
            return x;
        }
    }

    private Comparable floor2(Node x, K key, K best) {
        if (x == null) {
            return best;
        }
        int cmp = key.compareTo((K) x.key);
        if (cmp < 0) {
            return (K) floor2(x.left, key, best);
        } else if (cmp > 0) {
            return floor2(x.right, key, (K) x.key);
        } else {
            return (K) x.key;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Character, Integer> bst = new BinarySearchTree<>();
        bst.put('a', 1);
        bst.put('b', 2);
        bst.put('c', 3);
        bst.put('d', 4);

        StdOut.println("min = " + bst.min());
        StdOut.println("max = " + bst.max());
        StdOut.println("size = " + bst.size());
        StdOut.println("inorder");
        for (char l : bst.inOrder()) {
            StdOut.print(l + ",");
        }
        StdOut.println();
        StdOut.println("preorder");
        for (char l : bst.preOrder()) {
            StdOut.print(l + ",");
        }
        StdOut.println();

        StdOut.println("postorder");
        for (char l : bst.postOrder()) {
            StdOut.print(l + ",");
        }
        StdOut.println();

        StdOut.println("level order");
        for (char l : bst.levelOrder()) {
            StdOut.print(l + ",");
        }
        StdOut.println();
    }
}
