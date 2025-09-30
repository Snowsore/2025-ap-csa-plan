package org.example.core;

public record Vec3(double x, double y, double z) {
    public Vec3 add(Vec3 o) {
        return new Vec3(x + o.x, y + o.y, z + o.z);
    }

    public Vec3 mul(double s) {
        return new Vec3(x * s, y * s, z * s);
    }

    public double len() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vec3 norm() {
        double L = len();
        return L > 1e-9 ? mul(1.0 / L) : this;
    }

    public Vec3 cross(Vec3 o) {
        return new Vec3(y * o.z - z * o.y, z * o.x - x * o.z, x * o.y - y * o.x);
    }

    public double dot(Vec3 o) {
        return x * o.x + y * o.y + z * o.z;
    }

}
