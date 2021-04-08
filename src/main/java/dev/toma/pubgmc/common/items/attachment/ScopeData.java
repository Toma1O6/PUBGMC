package dev.toma.pubgmc.common.items.attachment;

import java.util.function.Supplier;

public class ScopeData {

    final int zoom;
    final Supplier<Float> mouseSens;

    public ScopeData() {
        this(-1, () -> 1.0F);
    }

    public ScopeData(int zoom, Supplier<Float> mouseSens) {
        this.zoom = zoom;
        this.mouseSens = mouseSens;
    }

    public int getZoom() {
        return zoom;
    }

    public float getMouseSens() {
        return mouseSens.get();
    }
}
