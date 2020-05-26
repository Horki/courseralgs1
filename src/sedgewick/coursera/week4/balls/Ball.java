package sedgewick.coursera.week4.balls;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

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
        rx = StdRandom.uniform(0.0, 1.0);
        ry = StdRandom.uniform(0.0, 1.0);
        vx = StdRandom.uniform(-.005, .005);
        vy = StdRandom.uniform(-.005, .005);
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
}
