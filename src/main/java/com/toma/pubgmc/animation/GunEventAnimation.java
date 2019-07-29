package com.toma.pubgmc.animation;

import net.minecraft.client.model.ModelRenderer;
import org.apache.commons.lang3.tuple.MutablePair;

import javax.vecmath.Vector3f;

public class GunEventAnimation extends Animation implements IPartAnimated<GunEventAnimation> {

    private final ModelRenderer part;
    private final float[] defaultRotation;
    private int step;
    private float x, y, z, rx, ry, rz;
    private MutablePair<Vector3f, Vector3f>[] steps;
    private float speed;

    public GunEventAnimation(ModelRenderer part) {
        this.part = part;
        defaultRotation = new float[]{part.rotateAngleX, part.rotateAngleY, part.rotateAngleZ};
    }

    @Override
    public Vector3f getMovementVec() {
        return new Vector3f();
    }

    @Override
    public Vector3f getRotationVector() {
        return new Vector3f();
    }

    @Override
    public ModelRenderer getPart() {
        return part;
    }

    @Override
    public MutablePair<Vector3f, Vector3f>[] animationSteps() {
        return steps;
    }

    @Override
    public int currentStep() {
        return step;
    }

    @Override
    public float[] getDefaultRotationAngles() {
        return defaultRotation;
    }

    @Override
    public Vector3f getPartMovement() {
        return new Vector3f(x, y, z);
    }

    @Override
    public Vector3f getPartRotation() {
        return new Vector3f(rx, ry, rz);
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public GunEventAnimation initMovement(MutablePair<Vector3f, Vector3f>[] mutablePairs) {
        this.steps = mutablePairs;
        return this;
    }

    @Override
    public void setCurrentStep(int step) {
        this.step = step;
    }

    @Override
    public void setMovement(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void setRotation(float x, float y, float z) {
        this.rx = x;
        this.ry = y;
        this.rz = z;
    }
}
