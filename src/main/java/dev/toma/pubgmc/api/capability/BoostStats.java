package dev.toma.pubgmc.api.capability;

/**
 * Manages boost values of entity
 *
 * @author Toma
 * @since 1.9.0
 */
public interface BoostStats {

    /**
     * Generic tick method
     */
    void onTick();

    /**
     * Adds specified amount of boost level to this entity
     * @param amount Amount to be added
     */
    void add(int amount);

    /**
     * @return Current boost level
     */
    int getBoostLevel();

    /**
     * Sets boost level to provided value
     * @param level Boost level to set
     */
    void setBoostLevel(int level);

    /**
     * @return Current boost level saturation
     */
    float getSaturation();

    /**
     * Resets boost values to zero values
     */
    void reset();

    /**
     * @return Whether all boost values are equal to zero
     */
    default boolean isEmpty() {
        return getBoostLevel() == 0 && getSaturation() == 0.0F;
    }
}
