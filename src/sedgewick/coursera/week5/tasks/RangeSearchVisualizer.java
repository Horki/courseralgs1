package sedgewick.coursera.week5.tasks;

// source
// https://coursera.cs.princeton.edu/algs4/assignments/kdtree/files/RangeSearchVisualizer.java

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class RangeSearchVisualizer {
    public static void main(String[] args) {
        // initialize the data structures from file
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

        // initial endpoint of rectangle
        double x0 = 0.0, y0 = 0.0;
        // current location of mouse
        double x1 = 0.0, y1 = 0.0;
        // is the user dragging a rectangle
        boolean isDragging = false;

        // draw the points
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        brute.draw();
        StdDraw.show();

        // process range search queries
        StdDraw.enableDoubleBuffering();
        while (true) {
            // user starts to drag a rectangle
            if (StdDraw.isMousePressed() && !isDragging) {
                x0 = x1 = StdDraw.mouseX();
                y0 = y1 = StdDraw.mouseY();
                isDragging = true;
            } else if (StdDraw.isMousePressed() && isDragging) {
                // user is dragging a rectangle
                x1 = StdDraw.mouseX();
                y1 = StdDraw.mouseY();
            } else if (!StdDraw.isMousePressed() && isDragging) {
                // user stops dragging rectangle
                isDragging = false;
            }

            // draw the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            brute.draw();

            // draw the rectangle
            RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
                    Math.max(x0, x1), Math.max(y0, y1));
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius();
            rect.draw();

            // draw the range search results for brute-force data structure in red
            StdDraw.setPenRadius(0.03);
            StdDraw.setPenColor(StdDraw.RED);
            for (Point2D p : brute.range(rect)) {
                p.draw();
            }

            // draw the range search results for kd-tree in blue
            StdDraw.setPenRadius(0.02);
            StdDraw.setPenColor(StdDraw.BLUE);
            for (Point2D p : kdtree.range(rect)) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}
