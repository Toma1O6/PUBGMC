package dev.toma.pubgmc.animation;

import javax.vecmath.Vector3f;

public class AnimationVec {

    public float x, y, z;
    public float rx, ry, rz;

    public AnimationVec(float f0, float f1, float f2, float f3, float f4, float f5) {
        this.x = f0;
        this.y = f1;
        this.z = f2;
        this.rx = f3;
        this.ry = f4;
        this.rz = f5;
    }

    public AnimationVec(Vector3f movement, Vector3f rotation) {
        this(movement.x, movement.y, movement.z, rotation.x, rotation.y, rotation.z);
    }

    public Vector3f getMovement() {
        return new Vector3f(x, y, z);
    }

    public void setMovement(Vector3f vec) {
        setMovement(vec.x, vec.y, vec.z);
    }

    public void setMovement(float f0) {
        setMovement(f0, f0, f0);
    }

    public void setMovement(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f getRotation() {
        return new Vector3f(rx, ry, rz);
    }

    public void setRotation(Vector3f vec) {
        setRotation(vec.x, vec.y, vec.z);
    }

    public void setRotation(float f) {
        setRotation(f, f, f);
    }

    public void setRotation(float x, float y, float z) {
        this.rx = x;
        this.ry = y;
        this.rz = z;
    }
}
