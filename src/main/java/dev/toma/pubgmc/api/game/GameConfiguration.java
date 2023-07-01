package dev.toma.pubgmc.api.game;

public interface GameConfiguration {

    /**
     * @return Whether enemy nameplates should be rendered
     */
    default boolean shouldShowNameplates() {
        return false;
    }

    void performCorrections();
}
