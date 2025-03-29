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
    void addBoost(int amount);

    /**
     * @return Current boost
     */
    int getBoost();

    /**
     *
     * @return boostLimit
     */
    int getBoostLimit();
    /**
     * Sets boost to provided value
     * @param percentage Boost to set
     */
    void setBoost(float percentage);

    /**
     * @return Current boost capability level
     */
    int getBoostLevel();

    /**
     *
     * @param level
     * @return percentage
     */
    float getLevelPercentage(int level);
    /**
     * Resets boost to zero
     */
    void reset();

    /**
     * @return Whether boost equal to zero
     */
    default boolean isEmpty() {
        return getBoost() == 0;
    }
}
