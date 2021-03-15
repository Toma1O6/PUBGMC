package dev.toma.pubgmc.client.renderer.item;

import net.minecraft.client.renderer.GlStateManager;

public interface IRenderConfig {

    void applyTransforms();

    static IRenderConfig translated(float x, float y, float z) {
        return new TranslationRenderConfig(x, y, z);
    }

    static IRenderConfig translatedScaled(float x, float y, float z, float scaleX, float scaleY, float scaleZ) {
        return new ScaledRenderConfig(x, y, z, scaleX, scaleY, scaleZ);
    }

    class TranslationRenderConfig implements IRenderConfig {

        final float x, y, z;

        TranslationRenderConfig(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public void applyTransforms() {
            GlStateManager.translate(x, y, z);
        }
    }

    class ScaledRenderConfig extends TranslationRenderConfig {

        final float sx, sy, sz;

        ScaledRenderConfig(float x, float y, float z, float sx, float sy, float sz) {
            super(x, y, z);
            this.sx = sx;
            this.sy = sy;
            this.sz = sz;
        }

        @Override
        public void applyTransforms() {
            super.applyTransforms();
            GlStateManager.scale(sx, sy, sz);
        }
    }
}
