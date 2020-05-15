package sedgewick.coursera.week3.tasks;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length; i++) {
            Point a = points[i];
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                Point b = points[j];
                double aSlopeToB = a.slopeTo(b);
                int aCompareToB = a.compareTo(b);

                for (int k = 0; k < points.length; k++) {
                    if (k == j || k == i) {
                        continue;
                    }
                    Point c = points[k];
                    double bSlopeToC = b.slopeTo(c);
                    int bCompareToC = b.compareTo(c);
                    if (aSlopeToB != bSlopeToC) {
                        continue;
                    }

                    for (int l = 0; l < points.length; l++) {
                        if (l == k || l == j || l == i) {
                            continue;
                        }
                        Point d = points[l];
                        double cSlopeToD = c.slopeTo(d);
                        int cCompareToD = c.compareTo(d);

                        boolean sameSlopes = (aSlopeToB == bSlopeToC) && (bSlopeToC == cSlopeToD);
                        boolean sameDirection = (aCompareToB > 0) && (bCompareToC > 0) && (cCompareToD > 0);
                        boolean notSamePoint = aSlopeToB > Double.NEGATIVE_INFINITY;

                        if (sameSlopes && sameDirection && notSamePoint) {
                            lineSegments.add(new LineSegment(a, d));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        int cnt = 0;
        LineSegment[] result = new LineSegment[numberOfSegments()];
        for (LineSegment line : lineSegments) {
            if (line != null) {
                result[cnt++] = line;
            }
        }
        return result;
    }

    // Redirect input from:
    // input6.txt
    // input8.txt
    public static void main(String[] args) {
//        checkExceptions();
        // read the n points from a file
//        In in = new In(args[0]);
        int n = StdIn.readInt();
//        StdOut.println("Count: " + n);
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i] = new Point(x, y);
//            StdOut.println("Point[" + i + "]: (" + x + ", " + y + ")");
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
//        StdOut.println("Number of Segments: " + collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
