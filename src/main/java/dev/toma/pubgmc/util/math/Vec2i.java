package dev.toma.pubgmc.util.math;

public class Vec2i {

    public int x;
    public int y;

    public Vec2i(Vec2i vec2i) {
        this(vec2i.x, vec2i.y);
    }

    public Vec2i(final int x, final int y) {
        this.set(x, y);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec2i add(int n) {
        return this.add(n, n);
    }

    public Vec2i subtract(int x, int y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec2i subtract(int n) {
        return this.subtract(n, n);
    }

    public Vec2i multiply(int x, int y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec2i multiply(int n) {
        return this.multiply(n, n);
    }

    public Vec2i divide(int x, int y) {
        if (x == 0 || y == 0) throw new IllegalArgumentException("Cannot divide by zero!");
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec2i divide(int n) {
        return this.divide(n, n);
    }

    public double getDistanceTo(int x, int y) {
        return Math.sqrt(x * x + y * y);
    }

    public double getDistanceTo(Vec2i vec2i) {
        return this.getDistanceTo(vec2i.x, vec2i.y);
    }
}
