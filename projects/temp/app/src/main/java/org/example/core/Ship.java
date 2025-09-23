package org.example.core;

public class Ship {
    public final Pose pose = new Pose();
    public Vec3 vel = new Vec3(0, 0, 0);

    // Tunables
    public double thrustAcc = 240, reverseAcc = 180, maxSpeed = 950;
    public double yawSpeed = 80, pitchSpeed = 80, rollSpeed = 120;
    public double damping = 0.12;

    public void reset() {
        pose.p = new Vec3(0, 0, 0);
        pose.yaw = pose.pitch = pose.roll = 0;
        vel = new Vec3(0, 0, 0);
    }

    public void resetWithBounce() {
        vel = forward().mul(-300).add(new Vec3(0, 120, 0));
    }

    public void update(double dt, InputState in) {
        if (in.reset)
            reset();
        pose.yaw += in.yaw * yawSpeed * dt;
        pose.pitch += in.pitch * pitchSpeed * dt;
        pose.roll += in.roll * rollSpeed * dt;

        Vec3 f = forward();
        if (in.thrust > 0)
            vel = vel.add(f.mul(thrustAcc * in.thrust * dt));
        if (in.thrust < 0)
            vel = vel.add(f.mul(-reverseAcc * Math.abs(in.thrust) * dt));

        double damp = Math.pow(1.0 - damping, dt);
        vel = vel.mul(damp);

        double s = vel.len();
        if (s > maxSpeed)
            vel = vel.mul(maxSpeed / s);

        pose.p = pose.p.add(vel.mul(dt));
    }

    public Vec3 forward() {
        double yaw = Math.toRadians(pose.yaw);
        double pitch = Math.toRadians(pose.pitch);
        double sy = Math.sin(yaw), cy = Math.cos(yaw);
        double sp = Math.sin(pitch), cp = Math.cos(pitch);
        return new Vec3(-sy * cp, sp, -cy * cp).norm(); // -Z forward
    }

    public Vec3 up() {
        // roll-aware up vector via basis reconstruction
        double rx = Math.toRadians(pose.pitch);
        double ry = Math.toRadians(pose.yaw);
        double rz = Math.toRadians(pose.roll);
        // start (0,1,0), rotate Z->X->Y:
        Vec3 v = new Vec3(0, 1, 0);
        v = new Vec3(v.x() * Math.cos(rz) - v.y() * Math.sin(rz), v.x() * Math.sin(rz) + v.y() * Math.cos(rz), v.z());
        v = new Vec3(v.x(), v.y() * Math.cos(rx) - v.z() * Math.sin(rx), v.y() * Math.sin(rx) + v.z() * Math.cos(rx));
        v = new Vec3(v.x() * Math.cos(ry) + v.z() * Math.sin(ry), v.y(), -v.x() * Math.sin(ry) + v.z() * Math.cos(ry));
        return v.norm();
    }

    private static double clamp(double v, double lo, double hi) {
        return Math.max(lo, Math.min(hi, v));
    }
}
