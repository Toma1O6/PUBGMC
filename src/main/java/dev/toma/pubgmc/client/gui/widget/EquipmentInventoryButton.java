package dev.toma.pubgmc.client.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class EquipmentInventoryButton extends GuiButton {

    public static final int SPECIAL_INVENTORY_BUTTON_ID = 8521;

    public EquipmentInventoryButton(int x, int y, String text) {
        super(SPECIAL_INVENTORY_BUTTON_ID, x, y, 8, 8, text);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.mouseDragged(mc, mouseX, mouseY);
            int color = 0xe0e0e0;

            if (packedFGColour != 0)
            {
                color = packedFGColour;
            }
            else
            if (!this.enabled)
            {
                color = 0xa0a0a0;
            }
            else if (this.hovered)
            {
                color = 0xffffa0;
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, color);
        }
    }
}
