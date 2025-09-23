package org.example.core;

/** Yaw(Y), Pitch(X), Roll(Z) in degrees, right-handed. */
public class Pose {
    public Vec3 p = new Vec3(0, 0, 0);
    public double yaw = 0, pitch = 0, roll = 0;
}
