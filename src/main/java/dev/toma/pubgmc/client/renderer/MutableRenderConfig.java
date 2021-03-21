package dev.toma.pubgmc.client.renderer;

import net.minecraft.client.renderer.GlStateManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class MutableRenderConfig implements IRenderConfig {

    public float x;
    public float y;
    public float z;
    public float scaleX = 1.0F;
    public float scaleY = 1.0F;
    public float scaleZ = 1.0F;
    public float rx;
    public float ry;
    public float rz;

    @Override
    public void applyTransforms() {
        GlStateManager.translate(x, y, z);
        GlStateManager.scale(scaleX, scaleY, scaleZ);
        if(rx != 0)
            GlStateManager.rotate(rx, 1.0F, 0.0F, 0.0F);
        if(ry != 0)
            GlStateManager.rotate(ry, 0.0F, 1.0F, 0.0F);
        if(rz != 0)
            GlStateManager.rotate(rz, 0.0F, 0.0F, 1.0F);

    }

    public void reset() {
        setTranslation(0, 0, 0);
        setRotation(0, 0, 0);
        setScale(1.0F, 1.0F, 1.0F);
    }

    public void addPosition(float x, float y, float z) {
        setTranslation(this.x + x, this.y + y, this.z + z);
    }

    public void addRotation(float x, float y, float z) {
        setRotation(this.rx + x, this.ry + y, this.rz + z);
    }

    public void addScale(float x, float y, float z) {
        setScale(scaleX + x, scaleY + y, scaleZ + z);
    }

    public void setTranslation(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setScale(float x, float y, float z) {
        this.scaleX = x;
        this.scaleY = y;
        this.scaleZ = z;
    }

    public void setRotation(float x, float y, float z) {
        this.rx = x;
        this.ry = y;
        this.rz = z;
    }

    public boolean hasModifiedScale() {
        return scaleX != 1.0F || scaleY != 1.0F || scaleZ != 1.0F;
    }

    public boolean hasModifiedRotation() {
        return rx != 0.0F || ry != 0.0F || rz != 0.0F;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.####");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(symbols);
        boolean rotated = hasModifiedRotation();
        boolean scaled = hasModifiedScale();
        boolean positioned = x != 0.0 || y != 0.0 || z != 0;
        int flag = 0b000;
        if(!scaled && !rotated) {
            if(!positioned) {
                return "IRenderConfig.empty()";
            }
            return String.format("IRenderConfig.positioned(%sF, %sF, %sF)", df.format(x), df.format(y), df.format(z));
        } else {
            if(scaled && !rotated) {
                return String.format("IRenderConfig.positionedScaled(%sF, %sF, %sF, %sF, %sF, %sF)", df.format(x), df.format(y), df.format(z), df.format(scaleX), df.format(scaleY), df.format(scaleZ));
            } else if(scaled && rotated) {
                return String.format("IRenderConfig.rotatedScaled(%sF, %sF, %sF, %sF, %sF, %sF, %sF, %sF, %sF)", df.format(x), df.format(y), df.format(z), df.format(scaleX), df.format(scaleY), df.format(scaleZ), df.format(rx), df.format(ry), df.format(rz));
            } else {
                return String.format("IRenderConfig.rotated(%sF, %sF, %sF, %sF, %sF, %sF)", df.format(x), df.format(y), df.format(z), df.format(rx), df.format(ry), df.format(rz));
            }
        }
    }
}
