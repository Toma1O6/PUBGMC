package dev.toma.pubgmc.common.items.attachment;

public class ScopeData {

    final int zoom;
    final float mouseSens;

    public ScopeData() {
        this(-1, 1.0F);
    }

    public ScopeData(int zoom, float mouseSens) {
        this.zoom = zoom;
        this.mouseSens = mouseSens;
    }

    public int getZoom() {
        return zoom;
    }

    public float getMouseSens() {
        return mouseSens;
    }
}
