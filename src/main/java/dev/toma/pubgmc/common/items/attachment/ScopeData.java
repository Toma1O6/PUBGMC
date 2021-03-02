package dev.toma.pubgmc.common.items.attachment;

import net.minecraft.util.ResourceLocation;

public class ScopeData {

    final int zoom;
    final float mouseSens;
    final ResourceLocation texture;
    final boolean builtInRenderer;

    public ScopeData(int zoom, float mouseSens, ResourceLocation texture) {
        this(zoom, mouseSens, texture, false);
    }

    public ScopeData(int zoom, float mouseSens) {
        this(zoom, mouseSens, null, true);
    }

    protected ScopeData(int zoom, float mouseSens, ResourceLocation texture, boolean builtInRenderer) {
        this.zoom = zoom;
        this.mouseSens = mouseSens;
        this.texture = texture;
        this.builtInRenderer = builtInRenderer;
    }

    public int getZoom() {
        return zoom;
    }

    public float getMouseSens() {
        return mouseSens;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public boolean isBuiltInRenderer() {
        return builtInRenderer;
    }
}
