package sedgewick.coursera.week1.tasks;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int len;
    private final int ex;
    private final double[] thresh;
    private final double s = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("PStats: error init n");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("PStats: error init trials");
        }
        len = n;
        ex = trials;
        thresh = new double[ex];
        for (int i = 0; i < ex; ++i) {
            thresh[i] = find();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresh);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (ex == 1.0) {
            return Double.NaN;
        }
        return StdStats.stddev(thresh);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double r = mean() - (s * stddev());
        return r / Math.sqrt(ex);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double r = mean() + (s * stddev());
        return r / Math.sqrt(ex);
    }

    private double find() {
        Percolation perc = new Percolation(len);
        int row, col;
        int cnt = 0;
        while (!perc.percolates()) {
            do {
                row = StdRandom.uniform(len) + 1;
                col = StdRandom.uniform(len) + 1;
            } while (perc.isOpen(row, col));
            ++cnt;
            perc.open(row, col);
        }
        return cnt / (Math.pow(len, 2));
    }

    public static void main(String[] args) {
        //
    }
}