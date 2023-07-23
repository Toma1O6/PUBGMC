package dev.toma.pubgmc.api.capability;

/**
 * Manages aiming state of entities
 *
 * @author Toma
 * @since 1.9.0
 */
public interface AimInfo {

    /**
     * General tick method
     */
    void onTick();

    /**
     * @return Whether the entity has started aiming
     */
    boolean isAiming();

    /**
     * @return Whether the entity is in "full" aiming state
     */
    boolean isFullyAds();

    /**
     * Used by aiming animation for updating current aiming progress
     * @return Current progress of aiming
     */
    default float getProgress() {
        return getProgress(1.0F);
    }

    /**
     * Used by reticles / scopes to render it's model
     * @param partialTicks Partial render tick time for interpolation
     * @return Interpolated aiming progress
     */
    float getProgress(float partialTicks);

    /**
     * Set aiming state for entity
     * @param aiming Aiming status
     * @param speed Aiming speed
     */
    void setAiming(boolean aiming, float speed);
}
