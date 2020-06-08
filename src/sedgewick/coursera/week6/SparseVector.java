package sedgewick.coursera.week6;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

public class SparseVector {
    //    private LinearProbingHashST<Integer, Double> map;
    private SeparateChainingHashST<Integer, Double> map;

    public SparseVector() {
//        map = new LinearProbingHashST<>();
        map = new SeparateChainingHashST<>();
    }

    public void put(int k, double v) {
        map.put(k, v);
    }

    public double get(int k) {
        if (!map.contains(k)) {
            return 0.0;
        }
        return map.get(k);
    }

    public Iterable<Integer> indices() {
        return map.keys();
    }

    public double dot(double[] that) {
        double sum = .0;
        for (int k : indices()) {
            double t = .0;
            if (that.length >= k) {
                t = that[k];
            }
            sum += t * get(k);
        }
        return sum;
    }

    public double dot(SparseVector that) {
        double sum = .0;
        for (int k : indices()) {
            sum += that.get(k) * get(k);
        }
        return sum;
    }

    public static void main(String[] args) {
        SparseVector a = new SparseVector();
        SparseVector b = new SparseVector();
        a.put(3, 0.50);
        a.put(9, 0.75);
        a.put(6, 0.11);
        a.put(6, 0.00);
        b.put(3, 0.60);
        b.put(4, 0.90);
        StdOut.println("a dot b = " + a.dot(b));
    }
}
