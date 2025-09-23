package org.example.core;

import java.util.ArrayList;
import java.util.List;

public class World {
    public final Ship ship = new Ship();
    public final List<Asteroid> asteroids = new ArrayList<>();
    public double worldSize = 6000;

    public void update(double dt, InputState input) {
        ship.update(dt, input);
        for (Asteroid a : asteroids) {
            a.update(dt);
            wrap(a.pose);
        }
        // simple collision: sphere vs ship AABB-ish (use radius)
        for (Asteroid a : asteroids) {
            double dx = a.pose.p.x() - ship.pose.p.x();
            double dy = a.pose.p.y() - ship.pose.p.y();
            double dz = a.pose.p.z() - ship.pose.p.z();
            if (dx * dx + dy * dy + dz * dz <= (a.radius + 60) * (a.radius + 60)) {
                ship.resetWithBounce();
                break;
            }
        }
        wrap(ship.pose);
    }

    private void wrap(Pose pose) {
        double L = worldSize;
        double x = pose.p.x(), y = pose.p.y(), z = pose.p.z();
        if (x < -L)
            x += 2 * L;
        if (x > L)
            x -= 2 * L;
        if (y < -L)
            y += 2 * L;
        if (y > L)
            y -= 2 * L;
        if (z < -L)
            z += 2 * L;
        if (z > L)
            z -= 2 * L;
        pose.p = new Vec3(x, y, z);
    }
}
