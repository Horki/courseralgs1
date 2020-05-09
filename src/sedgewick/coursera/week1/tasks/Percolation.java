package sedgewick.coursera.week1.tasks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int len;
    private int w;
    private int cnt_open;
    private int matrix[];
    private WeightedQuickUnionUF weightedQuickUnionUF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Error init matrix; should be 1 or more");
        }
        cnt_open = 0;
        len = (n * n) + 1;
        w = n;
        matrix = new int[len];
        weightedQuickUnionUF = new WeightedQuickUnionUF(len + 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        int i = idx(row, col);
        matrix[i] = 1;
        ++cnt_open;
        if (col < w) {
            tryUnion(row, col + 1, i);
        }
        if (col > 1) {
            tryUnion(row, col - 1, i);
        }
        if (row < w) {
            tryUnion(row + 1, col, i);
        } else {
            weightedQuickUnionUF.union(i, len + 1);
        }
        if (row > 1) {
            tryUnion(row - 1, col, i);
        } else {
            weightedQuickUnionUF.union(i, len);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return matrix[idx(row, col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return weightedQuickUnionUF.find(len) == weightedQuickUnionUF.find(idx(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return cnt_open;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.find(len) == weightedQuickUnionUF.find(len + 1);
    }

    private void isInBounds(int row, int col) {
        if (row <= 0 || row > w) {
            throw new IllegalArgumentException("Row outside bounds:" + row);
        }
        if (col <= 0 || col > w) {
            throw new IllegalArgumentException("Col outside bounds:" + col);
        }
    }

    private int idx(int row, int col) {
        isInBounds(row, col);
        return w * (row - 1) + col;
    }

    private void tryUnion(int row, int col, int i) {
        if (isOpen(row, col)) {
            weightedQuickUnionUF.union(idx(row, col), i);
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation perc = new Percolation(3);
        perc.open(1, 2);
        perc.open(2, 2);
        perc.open(2, 3);
        perc.open(3, 3);
        boolean c = perc.isFull(1, 1);
        boolean c2 = perc.percolates();
        StdOut.println(c);
        StdOut.println(c2);
    }
}