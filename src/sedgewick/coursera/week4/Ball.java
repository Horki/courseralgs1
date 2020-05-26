package sedgewick.coursera.week4;

import edu.princeton.cs.algs4.StdDraw;

public class Ball {
    // position
    private double rx, ry;
    // velocity
    private double vx, vy;
    // radius
    private final double radius = .012;

    public Ball() {
        rx = getRandom();
        ry = getRandom();
        vx = 0.015;
        vy = 0.023;
    }

    public void move(double dt) {
        if (((rx + vx * dt) < radius) || ((rx + vx * dt) > (1.0 - radius))) {
            vx = -vx;
        }
        if (((ry + vy * dt) < radius) || ((ry + vy * dt) > (1.0 - radius))) {
            vy = -vy;
        }
        rx += (vx * dt);
        ry += (vy * dt);
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }

    private static double getRandom() {
        return Math.random() * ((1.0 - 0.0));
    }
}
