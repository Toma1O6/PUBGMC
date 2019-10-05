package com.toma.pubgmc.animation;

import com.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.client.model.ModelRenderer;
import org.apache.commons.lang3.tuple.MutablePair;

import javax.vecmath.Vector3f;

public interface IPartAnimated<T extends Animation> {
    ModelRenderer getPart();

    Vector3f getPartMovement();

    Vector3f getPartRotation();

    float getSpeed();

    float[] getDefaultRotationAngles();

    T initMovement(MutablePair<Vector3f, Vector3f>[] mutablePairs);

    int currentStep();

    void setCurrentStep(int step);

    MutablePair<Vector3f, Vector3f> animationSteps()[];

    void setMovement(float x, float y, float z);

    void setRotation(float x, float y, float z);

    default void process(boolean bool) {
        if (this.getPart() == null || animationSteps() == null) {
            return;
        }

        MutablePair<Vector3f, Vector3f> step = this.animationSteps()[this.currentStep()];
        Vector3f movement = this.getPartMovement();
        Vector3f rotation = this.getPartRotation();
        if (bool) {
            if (!Animation.isPartMovementFinished(this)) {
                setMovement(movement.x, Animation.getPartialMovement(movement.y, step.getRight().y, 0.9F * getSpeed()), Animation.getPartialMovement(movement.z, step.getRight().z, 0.8F * getSpeed()));
                setRotation(Animation.getPartialMovement(rotation.x, step.getLeft().x, 0.02f * this.getSpeed()), rotation.y, Animation.getPartialMovement(rotation.z, step.getLeft().z, 0.02f * this.getSpeed()));
            } else if (Animation.canExecuteNextStep(currentStep(), animationSteps())) {
                setCurrentStep(currentStep() + 1);
            }
        } else if (!bool) {
            setCurrentStep(0);
            if (!Animation.isPartReturned(this)) {
                setMovement(
                        Animation.getPartialMovement(movement.x, 0f, 0.9F * getSpeed()),
                        Animation.getPartialMovement(movement.y, 0f, 0.9F * getSpeed()),
                        Animation.getPartialMovement(movement.z, 0f, 0.9F * getSpeed()));
                setRotation(
                        Animation.getPartialMovement(rotation.x, this.getDefaultRotationAngles()[0], 0.02F * this.getSpeed()),
                        Animation.getPartialMovement(rotation.y, this.getDefaultRotationAngles()[1], 0.02F * this.getSpeed()),
                        Animation.getPartialMovement(rotation.z, this.getDefaultRotationAngles()[2], 0.02F * this.getSpeed())
                );
            }
        }

        PUBGMCUtil.setModelPosition(getPart(), movement.x, movement.y, movement.z);
        PUBGMCUtil.setModelRotation(getPart(), rotation.x, rotation.y, rotation.z);
    }
}
