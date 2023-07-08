package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.client.gui.widget.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class TextureButtonWidget extends Widget {

    final TextureDefinition def;
    final ResourceLocation texture;
    final Callback callback;

    public TextureButtonWidget(int x, int y, int width, int height, ResourceLocation texture, int xTex, int yTex, int texLength, Callback callback) {
        this(x, y, width, height, texture, new TextureDefinition(xTex, yTex, texLength), callback);
    }

    public TextureButtonWidget(int x, int y, int width, int height, ResourceLocation texture, TextureDefinition def, Callback callback) {
        super(x, y, width, height);
        this.texture = texture;
        this.def = def;
        this.callback = callback;
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(texture);
        drawTexturedShape(x, y, x + width, y + height, def.u1, def.v1, def.u2, def.v2);
        if (isMouseOver(mouseX, mouseY)) {
            drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 1.0F, 0.3F);
        }
    }

    @Override
    public void onClick(int mouseX, int mouseY, int button) {
        callback.call(this, button);
    }

    interface Callback {
        void call(TextureButtonWidget widget, int button);
    }

    public static class TextureDefinition {
        final double u1, v1, u2, v2;

        public TextureDefinition(int xTex, int yTex, int texLength) {
            double step = 1.0D / texLength;
            double u = xTex * step;
            double v = yTex * step;
            this.u1 = u;
            this.v1 = v;
            this.u2 = u + step;
            this.v2 = v + step;
        }

        public TextureDefinition(double u1, double v1, double u2, double v2) {
            this.u1 = u1;
            this.v1 = v1;
            this.u2 = u2;
            this.v2 = v2;
        }
    }
}
