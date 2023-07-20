package dev.toma.pubgmc.client.gui.widget;

import net.minecraft.client.Minecraft;

public class ButtonWidget extends Widget {

    final IPressable pressable;
    protected String text;

    public ButtonWidget(int x, int y, int width, int height, String text, IPressable pressable) {
        super(x, y, width, height);
        this.text = text;
        this.pressable = pressable;
    }

    @Override
    public void onClick(int mouseX, int mouseY, int button) {
        pressable.processClicked(this, mouseX, mouseY, button);
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        boolean isHovered = this.isMouseOver(mouseX, mouseY);
        drawColorShape(x, y, x + width, y + height, 0.0F, 0.0F, 0.0F, 0.5F);
        if (isHovered) {
            drawColorShape(x, y, x + width, y + height, 1.0F, 1.0F, 1.0F, 0.5F);
        }
        int w = mc.fontRenderer.getStringWidth(text);
        mc.fontRenderer.drawStringWithShadow(text, x + (width - w) / 2.0F, y + (height - mc.fontRenderer.FONT_HEIGHT) / 2.0F, isHovered ? 0xffff00 : 0xffffff);
    }

    public interface IPressable {

        void processClicked(ButtonWidget widget, int mouseX, int mouseY, int button);
    }
}
