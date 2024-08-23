package dev.toma.pubgmc.common.items.attachment;

import dev.toma.configuration.api.type.DoubleType;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class StaticScopeZoom implements ScopeZoom {

    private final float fov;
    private final Supplier<Float> sensitivity;

    public StaticScopeZoom(float fov, DoubleType sensitivity) {
        this.fov = fov;
        this.sensitivity = sensitivity::getAsFloat;
    }

    @Override
    public float getCurrentZoom(Item item, float deltaTicks) {
        return this.fov;
    }

    @Override
    public float getSensitivity(Item item) {
        return this.sensitivity.get();
    }

    @Override
    public boolean zoomIn(Item item) {
        return false;
    }

    @Override
    public boolean zoomOut(Item item) {
        return false;
    }

    @Override
    public boolean hasMouseScrollOverrides() {
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(this.fov);
    }
}
