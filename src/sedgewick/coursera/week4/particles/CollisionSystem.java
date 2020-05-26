package sedgewick.coursera.week4.particles;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.awt.Color;

public class CollisionSystem {
    private MinPQ<Event> pq;
    private double t;
    private final double mass;
    private final Particle[] particles;
    private final double limit;

    public CollisionSystem(Particle[] p, double l) {
        particles = p.clone();
        limit = l;
        t = .0;
        mass = .5;
    }

    private void predict(Particle a) {
        if (a == null) {
            return;
        }
        for (int i = 0; i < particles.length; i++) {
            double dt = a.timeToHit(particles[i]);
            if (t + dt <= limit) {
                pq.insert(new Event(t + dt, a, particles[i]));
            }
        }
        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();
        if (t + dtX <= limit) {
            pq.insert(new Event(t + dtX, a, null));
        }
        if (t + dtY <= limit) {
            pq.insert(new Event(t + dtY, null, a));
        }
    }

    private void redraw() {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.show(20);
        if (t < limit) {
            pq.insert(new Event(t + 1.0 / mass, null, null));
        }
    }

    public void simulate() {
        pq = new MinPQ<>();
        for (int i = 0; i < particles.length; i++) {
            predict(particles[i]);
        }
        pq.insert(new Event(0, null, null));

        while (!pq.isEmpty()) {
            Event event = pq.delMin();
            if (!event.isValid()) {
                continue;
            }
            Particle a = event.a;
            Particle b = event.b;

            for (int i = 0; i < particles.length; i++) {
                particles[i].move(event.time - t);
            }
            t = event.time;

            if (a != null && b != null) {
                a.bounceOff(b);
            } else if (a != null && b == null) {
                a.bounceOffVerticalWall();
            } else if (a == null && b != null) {
                b.bounceOffHorizontalWall();
            } else if (a == null && b == null) {
                redraw();
            }

            predict(a);
            predict(b);
        }
    }

    private class Event implements Comparable<Event> {
        private final double time;
        private final Particle a, b;
        private final int countA, countB;

        // create a new event to occur at time t involving a and b
        public Event(double t, Particle pa, Particle pb) {
            time = t;
            a = pa;
            b = pb;
            if (a != null) {
                countA = a.getCount();
            } else {
                countA = -1;
            }
            if (b != null) {
                countB = b.getCount();
            } else {
                countB = -1;
            }
        }

        // compare times when two events will occur
        public int compareTo(Event that) {
            return (int) (time - that.time);
        }

        public boolean isValid() {
            if (a != null && a.getCount() != countA) {
                return false;
            }
            return b == null || b.getCount() == countB;
        }
    }

    // examples from
    // https://algs4.cs.princeton.edu/61event/
    //
    // brownian.txt
    // billiards2.txt
    // billiards5.txt
    // diffusion.txt
    public static void main(String[] args) {
        Particle[] particles;

        if (args.length == 1) {
            // Similar to Bouncing Balls example
            int N = Integer.parseInt(args[0]);
            particles = new Particle[N];
            for (int i = 0; i < N; i++) {
                particles[i] = new Particle();
            }
        } else {
            int N = StdIn.readInt();
            particles = new Particle[N];
            for (int i = 0; i < N; i++) {
                double rx = StdIn.readDouble();
                double ry = StdIn.readDouble();
                double vx = StdIn.readDouble();
                double vy = StdIn.readDouble();
                double radius = StdIn.readDouble();
                double mass = StdIn.readDouble();
                int r = StdIn.readInt();
                int g = StdIn.readInt();
                int b = StdIn.readInt();
                Color color = new Color(r, g, b);
                particles[i] = new Particle(rx, ry, vx, vy, radius, mass, color);
            }
        }

        CollisionSystem system = new CollisionSystem(particles, 1000);
        system.simulate();
    }
}
