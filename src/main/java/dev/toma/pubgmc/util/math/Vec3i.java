package dev.toma.pubgmc.util.math;

public class Vec3i {

    public int x;
    public int y;
    public int z;

    public Vec3i(Vec3i vec3i) {
        this(vec3i.x, vec3i.y, vec3i.z);
    }

    public Vec3i(int x, int y, int z) {
        this.set(x, y, z);
    }

    public void set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3i add(int x, int y, int z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3i add(int n) {
        return this.add(n, n, n);
    }

    public Vec3i subtract(int x, int y, int z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3i subtract(int n) {
        return this.subtract(n, n, n);
    }

    public Vec3i multiply(int x, int y, int z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec3i multiply(int n) {
        return this.multiply(n, n, n);
    }

    public Vec3i divide(int x, int y, int z) {
        if(x == 0 || y == 0 || z == 0) throw new IllegalArgumentException("Cannot divide by zero!");
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec3i divide(int n) {
        return this.divide(n, n, n);
    }

    public double getDistanceTo(int x, int y, int z) {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double getDistanceTo(Vec3i vec3i) {
        return this.getDistanceTo(vec3i.x, vec3i.y, vec3i.z);
    }
}
