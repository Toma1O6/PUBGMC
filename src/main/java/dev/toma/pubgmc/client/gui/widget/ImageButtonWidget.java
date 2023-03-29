package dev.toma.pubgmc.client.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ImageButtonWidget extends Widget {

    final ResourceLocation texture;
    final IPressable pressable;

    public ImageButtonWidget(int x, int y, int width, int height, ResourceLocation texture, IPressable pressable) {
        super(x, y, width, height);
        this.texture = texture;
        this.pressable = pressable;
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(texture);
        drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.4F);
        drawTexturedShape(x + 2, y + 2, x + width - 2, y + height - 2);
        if (isMouseOver(mouseX, mouseY)) {
            drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 1.0F, 0.5F);
        }
    }

    @Override
    public void onClick(int mouseX, int mouseY, int button) {
        pressable.processClicked(this, mouseX, mouseY, button);
    }

    public interface IPressable {

        void processClicked(ImageButtonWidget widget, int x, int y, int button);
    }
}
