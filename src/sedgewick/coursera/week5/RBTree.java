package sedgewick.coursera.week5;


public class RBTree<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean color;

        public Node(Key k, Value v, int cnt, boolean c) {
            key = k;
            val = v;
            N = cnt;
            color = c;
        }
    }


    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    public Value get(Key key) {
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
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
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

    public void put(Key k, Value v) {
        // search for key
        // update value if find, grow table if new
        root = put(root, k, v);
        root.color = BLACK;
    }

    private Node put(Node h, Key k, Value v) {
        // insert at bottom (and color red)
        if (h == null) {
            return new Node(k, v, 1, RED);
        }
        int cmp = k.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, k, v);
        } else if (cmp > 0) {
            h.right = put(h.right, k, v);
        } else {
            h.val = v;
        }
        // Lean left
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        // Balance 4-node
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        // Split 4-node
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        return h;
    }
    public static void main(String[] args) {
        RBTree rbTree = new RBTree();
        rbTree.put(1, 2);
        rbTree.put(3, 4);
        rbTree.put(5, 6);
        rbTree.put(7, 8);
        rbTree.put(9, 10);
        System.out.println(rbTree.get(1));
        System.out.println(rbTree.get(3));
        System.out.println(rbTree.get(5));
        System.out.println(rbTree.get(7));
        System.out.println(rbTree.get(9));
    }
}
