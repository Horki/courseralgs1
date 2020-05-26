package sedgewick.coursera.week4;

import edu.princeton.cs.algs4.StdDraw;

public class Ball {
    // position
    private double rx, ry;
    // velocity
    private double vx, vy;
    // radius
    private final double radius = .012;

    public Ball(double r_x, double r_y, double v_x, double v_y) {
        rx = r_x;
        ry = r_y;
        vx = v_x;
        vy = v_y;
    }

    public Ball() {
        rx = getRandomPosition();
        ry = getRandomPosition();
        vx = getRandomVelocity();
        vy = getRandomVelocity();
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

    private static double getRandomPosition() {
        return Math.random() * ((.99 - .01));
    }

    private static double getRandomVelocity() {
        return Math.random() * ((.02 - .01));
    }
}
