package sedgewick.coursera.week5.tasks;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class PointSET {
    // construct an empty set of points
    private final TreeSet<Point2D> data;

    public PointSET() {
        data = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // number of points in the set
    public int size() {
        return data.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("insert error!");
        }
        data.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("contains error!");
        }

        return data.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : data) {
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("range error!");
        }
        ArrayList<Point2D> rangePoints = new ArrayList<>();
        for (Point2D p : data) {
            if (rect.contains(p)) {
                rangePoints.add(p);
            }
        }
        return rangePoints;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("nearest error!");
        }
        Point2D result = null;
        Iterator<Point2D> iter = data.iterator();
        while (iter.hasNext()) {
            Point2D point = iter.next();
            if (result == null || result.distanceTo(p) > point.distanceTo(p)) {
                if (!point.equals(p)) {
                    result = point;
                }
            }
        }
        return result;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        PointSET pointSET = new PointSET();
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point2D p = new Point2D(x, y);
            pointSET.insert(p);
        }
        pointSET.draw();
    }
}
