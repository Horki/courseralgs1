package sedgewick.coursera.week3.tasks;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(points);
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            Comparator<Point> slopeOrder = points[i].slopeOrder();
            Arrays.sort(points, slopeOrder);
            int j = 2;
            while (j < points.length) {
                // find segments with a given slope with origin at points[0]
                int start = j - 1;
                while (j < points.length
                        && slopeOrder.compare(points[j - 1], points[j]) == 0) {
                    j++;
                }
                // add segments with >= 4 points, but avoid duplicates
                if (j - start >= 3 && points[0].compareTo(points[start]) < 0) {
                    lineSegments.add(new LineSegment(points[0], points[j - 1]));
                }
                j++;
            }
            Arrays.sort(points);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
//        StdOut.println("Number of Segments: " + collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
