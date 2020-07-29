package sedgewick.coursera.week5;

import edu.princeton.cs.algs4.StdOut;
import sedgewick.coursera.week5.interfaces.TwoThreeTree;

import java.util.NoSuchElementException;

// Balanced Tree
public class BTree<K extends Comparable<K>, V> implements TwoThreeTree<K, V> {
    private static final class Node {
        // number of children
        private int m;
        // the array of children
        private Entry[] children = new Entry[M];

        // create a node with k children
        private Node(int k) {
            m = k;
        }
    }

    private static class Entry {
        private Comparable key;
        private final Object val;
        // helper field to iterate over array entries
        private Node next;

        public Entry(Comparable key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }


    // max children per B-tree node = M-1
    // (must be even and greater than 2)
    private static final int M = 4;

    // root of the B-tree
    private Node root;
    // height of the B-tree
    private int height;
    // number of key-value pairs in the B-tree
    private int n;

    public BTree() {
        root = new Node(0);
        height = 0;
        n = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return n;
    }

    public int height() {
        return height;
    }

    public V get(K k) {
        if (k == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        return search(root, k, height);
    }

    public boolean contains(K k) {
        return get(k) != null;
    }

    public K min() {
        return min(root);
    }

    private K min(Node x) {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        while (true) {
            Node next = x.children[0].next;
            if (next != null) {
                x = next;
            } else {
                return (K) x.children[0].key;
            }
        }
    }

    public K max() {
        return max(root);
    }

    private K max(Node x) {
        if (isEmpty()) {
            throw new NoSuchElementException("calls max() with empty symbol table");
        }
        while (true) {
            Node next = x.children[x.m - 1].next;
            if (next != null) {
                x = next;
            } else {
                return (K) x.children[x.m - 1].key;
            }
        }
    }

    private V search(Node x, K k, int ht) {
        Entry[] children = x.children;
        if (ht == 0) {
            for (int j = 0; j < x.m; ++j) {
                if (eq(k, children[j].key)) {
                    return (V) children[j].val;
                }
            }
        } else {
            for (int j = 0; j < x.m; ++j) {
                if (j + 1 == x.m || less(k, children[j + 1].key)) {
                    return search(children[j].next, k, ht - 1);
                }
            }
        }
        return null;
    }

    public void put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("argument key to put() is null");
        }
        Node u = insert(root, key, val, height);
        ++n;
        if (u == null) {
            return;
        }

        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        root = t;
        ++height;
    }

    private Node insert(Node h, K key, V val, int ht) {
        int j;
        Entry t = new Entry(key, val, null);

        // external node
        if (ht == 0) {
            for (j = 0; j < h.m; ++j) {
                if (less(key, h.children[j].key)) {
                    break;
                }
            }
        } else {
            // internal node
            for (j = 0; j < h.m; ++j) {
                if ((j + 1 == h.m) || less(key, h.children[j + 1].key)) {
                    Node u = insert(h.children[j++].next, key, val, ht - 1);
                    if (u == null) {
                        return null;
                    }
                    t.key = u.children[0].key;
                    t.next = u;
                    break;
                }
            }
        }

        if (h.m - j >= 0) {
            System.arraycopy(h.children, j, h.children, j + 1, h.m - j);
        }
        h.children[j] = t;
        h.m++;
        if (h.m < M) {
            return null;
        } else {
            return split(h);
        }
    }

    private Node split(Node h) {
        Node t = new Node(M / 2);
        h.m = M / 2;
        System.arraycopy(h.children, 2, t.children, 0, M / 2);
        return t;
    }

    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }

    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(Node h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.children;

        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s.append(indent).append(children[j].key).append(" ").append(children[j].val).append("\n");
            }
        } else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) {
                    s.append(indent).append("(").append(children[j].key).append(")\n");
                }
                s.append(toString(children[j].next, ht - 1, indent + "     "));
            }
        }
        return s.toString();
    }

    public static void main(String[] args) {
        BTree<String, String> st = new BTree<>();

        st.put("www.cs.princeton.edu", "128.112.136.12");
        st.put("www.cs.princeton.edu", "128.112.136.11");
        st.put("www.princeton.edu", "128.112.128.15");
        st.put("www.yale.edu", "130.132.143.21");
        st.put("www.simpsons.com", "209.052.165.60");
        st.put("www.apple.com", "17.112.152.32");
        st.put("www.amazon.com", "207.171.182.16");
        st.put("www.ebay.com", "66.135.192.87");
        st.put("www.cnn.com", "64.236.16.20");
        st.put("www.google.com", "216.239.41.99");
        st.put("www.nytimes.com", "199.239.136.200");
        st.put("www.microsoft.com", "207.126.99.140");
        st.put("www.dell.com", "143.166.224.230");
        st.put("www.slashdot.org", "66.35.250.151");
        st.put("www.espn.com", "199.181.135.201");
        st.put("www.weather.com", "63.111.66.11");
        st.put("www.yahoo.com", "216.109.118.65");


        StdOut.println("cs.princeton.edu:  " + st.get("www.cs.princeton.edu"));
        StdOut.println("hardvardsucks.com: " + st.get("www.harvardsucks.com"));
        StdOut.println("simpsons.com:      " + st.get("www.simpsons.com"));
        StdOut.println("apple.com:         " + st.get("www.apple.com"));
        StdOut.println("ebay.com:          " + st.get("www.ebay.com"));
        StdOut.println("dell.com:          " + st.get("www.dell.com"));
        StdOut.println();

        StdOut.println("size:    " + st.size());
        StdOut.println("height:  " + st.height());
        StdOut.println(st);
        StdOut.println();
        StdOut.println("min = " + st.min());
        StdOut.println("max = " + st.max());

        {
            BTree<Integer, Integer> btree = new BTree<>();
            btree.put(4, 4);
            btree.put(1, 1);
            btree.put(3, 3);
            StdOut.println("contains 3 = " + btree.contains(3));
            StdOut.println("min = " + btree.min());
            StdOut.println("max = " + btree.max());
        }
    }
}
