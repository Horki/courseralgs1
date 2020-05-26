package sedgewick.coursera.week4.particles;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.Color;

public class Particle {
    private double rx, ry;
    private double vx, vy;
    private double radius;
    private double mass;
    private Color color;
    private int count;


    public Particle(double r_x, double r_y, double v_x, double v_y, double r, double m, Color c) {
        rx = r_x;
        ry = r_y;
        vx = v_x;
        vy = v_y;
        radius = r;
        mass = m;
        color = c;
        count = 0;
    }

    // create a random particle in the unit box (overlaps not checked)
    public Particle() {
        rx = StdRandom.uniform(.0, 1.0);
        ry = StdRandom.uniform(.0, 1.0);
        vx = StdRandom.uniform(-.005, 0.005);
        vy = StdRandom.uniform(-.005, 0.005);
        radius = 0.012;
        mass = 0.5;
        color = Color.BLACK;
        count = 0;
    }

    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
    }

    // draw the particle
    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }

    public int getCount() {
        return count;
    }


    // how long into future until collision between this particle a and b?
    public double timeToHit(Particle that) {
        if (this == that) {
            return Double.POSITIVE_INFINITY;
        }
        double dx = that.rx - rx;
        double dy = that.ry - ry;
        double dvx = that.vx - vx;
        double dvy = that.vy - vy;
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > .0) {
            return Double.POSITIVE_INFINITY;
        }
        double dvdv = dvx * dvx + dvy * dvy;
        double drdr = dx * dx + dy * dy;
        double sigma = radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);

        if (d < .0) {
            return Double.POSITIVE_INFINITY;
        }
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitVerticalWall() {
        if (vx > .0) {
            return (1.0 - rx - radius) / vx;
        } else if (vx < .0) {
            return (radius - rx) / vx;
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    // how long into future until this particle collides with a horizontal wall?
    public double timeToHitHorizontalWall() {
        if (vy > .0) {
            return (1.0 - ry - radius) / vy;
        } else if (vy < .0) {
            return (radius - ry) / vy;
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    public void bounceOff(Particle that) {
        double dx = that.rx - rx;
        double dy = that.ry - ry;
        double dvx = that.vx - vx;
        double dvy = that.vy - vy;
        double dvdr = dx * dvx + dy * dvy;
        double dist = radius + that.radius;

        double F = 2 * mass * that.mass * dvdr / ((mass + that.mass) * dist);
        double fx = F * dx / dist;
        double fy = F * dy / dist;

        // update velocities according to normal force
        vx += fx / mass;
        vy += fy / mass;
        that.vx -= fx / that.mass;
        that.vy -= fy / that.mass;

        // update collision counts
        ++count;
        ++that.count;
    }

    public void bounceOffVerticalWall() {
        vx = -vx;
        ++count;
    }

    public void bounceOffHorizontalWall() {
        vy = -vy;
        ++count;
    }
}