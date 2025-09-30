package org.example.core;

public class Asteroid {
    public final Pose pose = new Pose();
    public Vec3 vel;
    public Vec3 rotVel; // deg/s around X,Y,Z
    public double radius;

    public Asteroid(double radius, Vec3 pos, Vec3 vel, Vec3 rotVel) {
        this.radius = radius;
        this.pose.p = pos;
        this.vel = vel;
        this.rotVel = rotVel;
    }

    public void update(double dt) {
        pose.p = pose.p.add(vel.mul(dt));
        pose.pitch += rotVel.x() * dt;
        pose.yaw += rotVel.y() * dt;
        pose.roll += rotVel.z() * dt;
    }
}
