package dev.toma.pubgmc.client.renderer;

import net.minecraft.client.renderer.GlStateManager;

public interface IRenderConfig {

    IRenderConfig EMPTY = () -> {};

    void applyTransforms();

    static IRenderConfig empty() {
        return EMPTY;
    }

    static IRenderConfig positioned(float x, float y, float z) {
        return new PositionedRenderConfig(x, y, z);
    }

    static IRenderConfig positionedScaled(float x, float y, float z, float scaleX, float scaleY, float scaleZ) {
        return new ScaledRenderConfig(x, y, z, scaleX, scaleY, scaleZ);
    }

    static IRenderConfig rotated(float x, float y, float z, float rx, float ry, float rz) {
        return new PositionRotatedConfig(x, y, z, rx, ry, rz);
    }

    static IRenderConfig rotatedScaled(float x, float y, float z, float scaleX, float scaleY, float scaleZ, float rx, float ry, float rz) {
        return new RotatedRenderConfig(x, y, z, scaleX, scaleY, scaleZ, rx, ry, rz);
    }

    class PositionedRenderConfig implements IRenderConfig {

        final float x, y, z;

        PositionedRenderConfig(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public void applyTransforms() {
            GlStateManager.translate(x, y, z);
        }
    }

    class ScaledRenderConfig extends PositionedRenderConfig {

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

    class RotatedRenderConfig extends ScaledRenderConfig {

        final float rx, ry, rz;

        RotatedRenderConfig(float x, float y, float z, float scaleX, float scaleY, float scaleZ, float rx, float ry, float rz) {
            super(x, y, z, scaleX, scaleY, scaleZ);
            this.rx = rx;
            this.ry = ry;
            this.rz = rz;
        }

        @Override
        public void applyTransforms() {
            super.applyTransforms();
            if(rx != 0)
                GlStateManager.rotate(rx, 1.0F, 0.0F, 0.0F);
            if(ry != 0)
                GlStateManager.rotate(ry, 0.0F, 1.0F, 0.0F);
            if(rz != 0)
                GlStateManager.rotate(rz, 0.0F, 0.0F, 1.0F);
        }
    }

    class PositionRotatedConfig extends PositionedRenderConfig {

        final float rx, ry, rz;

        public PositionRotatedConfig(float x, float y, float z, float rx, float ry, float rz) {
            super(x, y, z);
            this.rx = rx;
            this.ry = ry;
            this.rz = rz;
        }

        @Override
        public void applyTransforms() {
            super.applyTransforms();
            if(rx != 0)
                GlStateManager.rotate(rx, 1.0F, 0.0F, 0.0F);
            if(ry != 0)
                GlStateManager.rotate(ry, 0.0F, 1.0F, 0.0F);
            if(rz != 0)
                GlStateManager.rotate(rz, 0.0F, 0.0F, 1.0F);
        }
    }
}
