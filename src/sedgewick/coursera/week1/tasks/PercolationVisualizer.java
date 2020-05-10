package sedgewick.coursera.week1.tasks;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.awt.*;

// Visual from (including small patches)
// https://github.com/maxgillett/coursera/blame/master/Algorithms%20Part%201/week1/percolation/src/main/java/PercolationVisualizer.java
public class PercolationVisualizer {
    // delay in milliseconds (controls animation speed)
    private static final int DELAY = 20;

    // draw N-by-N percolation system
    private static void draw(Percolation percolation, int N) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        StdDraw.filledSquare(N / 2.0, N / 2.0, N / 2.0);

        // draw N-by-N grid
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (percolation.isFull(row, col)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                } else if (percolation.isOpen(row, col)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                }
                StdDraw.filledSquare(col - 0.5, N - row + 0.5, 0.45);
            }
        }

        // write status text
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(.25 * N, -N * .025, percolation.numberOfOpenSites() + " open sites");
        if (percolation.percolates()) {
            StdDraw.text(.75 * N, -N * .025, "percolates");
        } else {
            StdDraw.text(.75 * N, -N * .025, "does not percolate");
        }

    }

    // from (input20.txt)
    public static void main(String[] args) {
        int N = StdIn.readInt();
        // turn on animation mode
        StdDraw.show();

        // repeatedly read in sites to open and draw resulting system
        Percolation percolation = new Percolation(N);
        draw(percolation, N);
        StdDraw.show();
        while (!StdIn.isEmpty()) {
            int i = StdIn.readInt();
            int j = StdIn.readInt();
            percolation.open(i, j);
            draw(percolation, N);
            StdDraw.show(DELAY);
        }
    }
}
