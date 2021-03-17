package dev.toma.pubgmc.client.animation.util;

public class Range {

    final float min;
    final float max;

    public Range(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public boolean isIn(float value) {
        return value >= min && value < max;
    }
}
