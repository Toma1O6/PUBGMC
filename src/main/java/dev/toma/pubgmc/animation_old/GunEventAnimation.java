package dev.toma.pubgmc.animation_old;

import net.minecraft.client.model.ModelRenderer;

import javax.vecmath.Vector3f;

public class GunEventAnimation extends Animation implements IPartAnimated<GunEventAnimation> {

    private final ModelRenderer part;
    private int step;
    private float x, y, z;
    private Vector3f[] steps;
    private float speed;

    public GunEventAnimation(ModelRenderer part) {
        this.part = part;
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
    public Vector3f[] animationSteps() {
        return steps;
    }

    @Override
    public int currentStep() {
        return step;
    }

    @Override
    public Vector3f getPartMovement() {
        return new Vector3f(x, y, z);
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public GunEventAnimation initMovement(Vector3f[] mutablePairs) {
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
}
