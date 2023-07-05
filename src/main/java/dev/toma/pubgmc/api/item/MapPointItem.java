package dev.toma.pubgmc.api.item;

public interface MapPointItem {

    default boolean shouldRenderAllPoints() {
        return true;
    }
}
