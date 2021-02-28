package dev.toma.pubgmc.common.items.attachment;

public class ItemScope extends ItemAttachment implements Scope {

    final int zoom;
    final float mouseSens;

    public ItemScope(String name, int zoom) {
        this(name, zoom, 1.0F);
    }

    public ItemScope(String name, int zoom, float mouseSens) {
        super(name);
        this.zoom = zoom;
        this.mouseSens = mouseSens;
    }

    @Override
    public int getZoom(int fov) {
        return Math.min(fov, zoom);
    }

    @Override
    public float getMouseSensMultiplier() {
        return mouseSens;
    }
}
