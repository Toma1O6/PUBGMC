package dev.toma.pubgmc.animation_old;

import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;
import javax.vecmath.Vector3f;

public abstract class Animation {
    public static final Vector3f EMPTYVEC = new Vector3f(0f, 0f, 0f);
    public float movementX, movementY, movementZ;

    public static float calculateMovement(float baseMovement) {
        return baseMovement * 60 / Minecraft.getDebugFPS();
    }

    public static float getPartialMovement(final float movement, final float finalPos, final float modifier) {
        return Math.abs(movement - finalPos) < modifier ? finalPos : movement < finalPos ? movement + calculateMovement(modifier) : movement - calculateMovement(modifier);
    }

    public static float decreasePartialMovement(final float movement, final float finalPos, final float modifier) {
        return movement > finalPos ? movement - calculateMovement(modifier) : finalPos;
    }

    public static float increasePartialMovement(final float movement, final float finalPos, final float modifier) {
        return movement < finalPos ? movement + calculateMovement(modifier) : finalPos;
    }

    public static boolean isPartMovementFinished(IPartAnimated part) {
        Vector3f transl = part.animationSteps()[part.currentStep()];
        Vector3f magTransl = part.getPartMovement();
        boolean b = magTransl.x == transl.x && magTransl.y == transl.y && magTransl.z == transl.z;
        return b;
    }

    public static boolean isPartReturned(IPartAnimated part) {
        Vector3f translation = part.getPartMovement();
        return translation.x == 0f && translation.y == 0f && translation.z == 0f;
    }

    public static boolean canExecuteNextStep(int current, Vector3f[] group) {
        return current + 1 < group.length;
    }

    @Nonnull
    public abstract Vector3f getMovementVec();

    @Nonnull
    public abstract Vector3f getRotationVector();

    public final void calculateMovementVariables(float x, float y, float z) {
        movementY = 0.0064f;
        movementX = Math.abs((movementY * x) / y);
        movementZ = Math.abs((movementY * z) / y);
    }

    public void onAnimationFinished() {
        movementX = 0f;
        movementY = 0f;
    }
}
