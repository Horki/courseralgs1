package sedgewick.coursera.week5.bonus;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private Node root;
    private int size;

    private class Node {
        private final Point2D point;
        private final RectHV rect;
        private Node left;
        private Node right;

        private Node(Point2D p, RectHV re, Node l, Node r) {
            point = p;
            rect = re;
            left = l;
            right = r;
        }
    }

    public KdTree() {
        root = null;
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    private Node insert(Node n, Node parent, Point2D p, boolean isX) {
        if (n == null) {
            RectHV rect;
            if (parent == null) {
                rect = new RectHV(0, 0, 1, 1);
            } else {
                double xMin = parent.rect.xmin();
                double yMin = parent.rect.ymin();
                double xMax = parent.rect.xmax();
                double yMax = parent.rect.ymax();

                if (isX) {
                    double parentY = parent.point.y();
                    int pointsCmpRes = Double.compare(p.y(), parentY);
                    if (pointsCmpRes == 1) {
                        rect = new RectHV(xMin, parentY, xMax, yMax);
                    } else {
                        rect = new RectHV(xMin, yMin, xMax, parentY);
                    }
                } else {
                    double parentX = parent.point.x();
                    int pointsCmpRes = Double.compare(p.x(), parentX);
                    if (pointsCmpRes == 1) {
                        rect = new RectHV(parentX, yMin, xMax, yMax);
                    } else {
                        rect = new RectHV(xMin, yMin, parentX, yMax);
                    }
                }
            }
            Node result = new Node(p, rect, null, null);
            return result;
        }

        if (isX) {
            int cmp = Double.compare(p.x(), n.point.x());
            if (cmp == 1) {
                n.right = insert(n.right, n, p, !isX);
            } else {
                n.left = insert(n.left, n, p, !isX);
            }

        } else {
            int cmpRes = Double.compare(p.y(), n.point.y());
            if (cmpRes == 1) {
                n.right = insert(n.right, n, p, !isX);
            } else {
                n.left = insert(n.left, n, p, !isX);
            }
        }
        return n;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("insert error!");
        }

        if (!contains(p)) {
            root = insert(root, null, p, true);
            ++size;
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("contains error!");
        }
        return contains(root, p);
    }

    private boolean contains(Node node, Point2D p) {
        if (node == null || !node.rect.contains(p)) {
            return false;
        }
        if (node.point.equals(p)) {
            return true;
        }

        boolean isLeft = contains(node.left, p);
        boolean isRight = contains(node.right, p);

        return isLeft || isRight;
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.01);

        draw(root, true);
        StdDraw.show();
    }

    private void draw(Node n, boolean isX) {
        if (n == null) {
            return;
        }
        if (isX) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.point.x(), n.rect.ymin(), n.point.x(), n.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(n.rect.xmin(), n.point.y(), n.rect.xmax(), n.point.y());
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(n.point.x(), n.point.y());
        draw(n.left, !isX);
        draw(n.right, !isX);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("range error!");
        }

        ArrayList<Point2D> points = new ArrayList<>();
        range(root, points, rect);
        return points;
    }

    private void range(Node node, ArrayList<Point2D> points, RectHV rect) {
        if (node == null || !node.rect.intersects(rect)) {
            return;
        }

        if (rect.contains(node.point) && !points.contains(node.point)) {
            points.add(node.point);
        }
        range(node.left, points, rect);
        range(node.right, points, rect);
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D queryPoint) {
        if (queryPoint == null) {
            throw new IllegalArgumentException("nearest error!");
        }
        if (size == 0) {
            return null;
        }
        return nearest(root, root.point, queryPoint);
    }

    private Point2D nearest(Node node, Point2D point, Point2D query) {
        if (node == null ||
                (Double.compare(node.rect.distanceSquaredTo(query), 0.0) != 0 &&
                        Double.compare(point.distanceSquaredTo(query), node.rect.distanceSquaredTo(query)) == -1)) {
            return point;
        }
        if (Double.compare(point.distanceSquaredTo(query), node.point.distanceSquaredTo(query)) == 1) {
            point = node.point;
        }

        Point2D rightClosest = nearest(node.right, point, query);
        Point2D leftClosest = nearest(node.left, point, query);

        if (leftClosest == null) {
            return rightClosest;
        }
        if (rightClosest == null) {
            return leftClosest;
        }
        if (Double.compare(leftClosest.distanceSquaredTo(query), rightClosest.distanceSquaredTo(query)) == -1) {
            return leftClosest;
        }
        return rightClosest;
    }

    // input10.txt
    public static void main(String[] args) {
        Point2D[] points = new Point2D[]{
                new Point2D(0.158530, 0.486901),
                new Point2D(0.792202, 0.762825),
                new Point2D(0.738013, 0.827616),
                new Point2D(0.615232, 0.064454),
                new Point2D(0.107092, 0.863317),
                new Point2D(0.395908, 0.043916),
                new Point2D(0.848473, 0.112317),
                new Point2D(0.095420, 0.786050),
                new Point2D(0.684045, 0.631946),
                new Point2D(0.771410, 0.386939),
        };

        KdTree kdTree = new KdTree();
        StdOut.println("Is empty:" + kdTree.isEmpty());
//        while (!StdIn.isEmpty()) {
//            double x = StdIn.readDouble();
//            double y = StdIn.readDouble();
//            Point2D p = new Point2D(x, y);
//            kdTree.insert(p);
//        }
        for (Point2D p : points) {
            StdOut.println("adding point: " + p);
            kdTree.insert(p);
            StdOut.println("contains point: " + kdTree.contains(p));
        }
        StdOut.println("Is empty:" + kdTree.isEmpty());
        for (Point2D p : points) {
            StdOut.println("Nearest: " + (kdTree.nearest(p) == p));
        }
        Point2D tmp = new Point2D(0.154530, 0.476901);
        StdOut.println("Nearest: " + (kdTree.nearest(tmp) == points[0]));

        kdTree.draw();
    }
}
