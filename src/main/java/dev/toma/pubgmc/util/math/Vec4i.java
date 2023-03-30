package dev.toma.pubgmc.util.math;

public class Vec4i {

    public int x;
    public int y;
    public int w;
    public int h;

    public void set(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Vec4i add(int x, int y, int w, int h) {
        this.x += x;
        this.y += y;
        this.w += w;
        this.h += h;
        return this;
    }

    public Vec4i add(int n) {
        return this.add(n, n, n, n);
    }

    public Vec4i subtract(int x, int y, int w, int h) {
        this.x -= x;
        this.y -= y;
        this.w -= w;
        this.h -= h;
        return this;
    }

    public Vec4i subtract(int n) {
        return this.subtract(n, n, n, n);
    }

    public Vec4i multiply(int x, int y, int w, int h) {
        this.x *= x;
        this.y *= y;
        this.w *= w;
        this.h *= h;
        return this;
    }

    public Vec4i multiply(int n) {
        return this.multiply(n, n, n, n);
    }

    public Vec4i divide(int x, int y, int w, int h) {
        if(x == 0 || y == 0 || w == 0 || h == 0) throw new IllegalArgumentException("Cannot divide by zero!");
        this.x /= x;
        this.y /= y;
        this.w /= w;
        this.h /= h;
        return this;
    }

    public Vec4i divide(int n) {
        return this.divide(n, n, n, n);
    }

    public double getDistanceTo(int x, int y, int w, int h) {
        return Math.sqrt(x * x + y * y + w * w + h * h);
    }

    public double getDistanceTo(Vec4i vec4i) {
        return this.getDistanceTo(vec4i.x, vec4i.y, vec4i.w, vec4i.h);
    }

    public int getArea() {
        return 2 * (w - x + h - y);
    }
}
