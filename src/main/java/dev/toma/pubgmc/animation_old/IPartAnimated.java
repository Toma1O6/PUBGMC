package dev.toma.pubgmc.animation_old;

import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.client.model.ModelRenderer;

import javax.vecmath.Vector3f;

public interface IPartAnimated<T extends Animation> {
    ModelRenderer getPart();

    Vector3f getPartMovement();

    float getSpeed();

    T initMovement(Vector3f[] mutablePairs);

    int currentStep();

    void setCurrentStep(int step);

    Vector3f[] animationSteps();

    void setMovement(float x, float y, float z);

    default void process(boolean bool) {
        if (this.getPart() == null || animationSteps() == null) {
            return;
        }

        Vector3f step = this.animationSteps()[this.currentStep()];
        Vector3f movement = this.getPartMovement();
        if (bool) {
            if (!Animation.isPartMovementFinished(this)) {
                setMovement(
                        Animation.getPartialMovement(movement.x, step.x, 0.8F * this.getSpeed()),
                        Animation.getPartialMovement(movement.y, step.y, 0.8F * this.getSpeed()),
                        Animation.getPartialMovement(movement.z, step.z, 0.8F * this.getSpeed()));

            } else if (Animation.canExecuteNextStep(currentStep(), animationSteps())) {
                setCurrentStep(currentStep() + 1);
            }
        } else if (!bool) {
            setCurrentStep(0);
            if (!Animation.isPartReturned(this)) {
                setMovement(
                        Animation.getPartialMovement(movement.x, 0f, 0.8F * this.getSpeed()),
                        Animation.getPartialMovement(movement.y, 0f, 0.8F * this.getSpeed()),
                        Animation.getPartialMovement(movement.z, 0f, 0.8F * this.getSpeed()));
            }
        }

        PUBGMCUtil.setModelPosition(getPart(), movement.x, movement.y, movement.z);
    }
}
