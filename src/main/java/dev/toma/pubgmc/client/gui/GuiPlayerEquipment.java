package dev.toma.pubgmc.client.gui;

import dev.toma.pubgmc.Pubgmc;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiPlayerEquipment extends GuiContainer {

    private static final ResourceLocation TEXTURE = Pubgmc.getResource("textures/gui/player_equipment_inventory.png");
    private int oldMouseX, oldMouseY;

    public GuiPlayerEquipment(Container container) {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        GuiInventory.drawEntityOnScreen(guiLeft + 145, guiTop + 70, 30, guiLeft + 145 - oldMouseX, guiTop + 70 - oldMouseY, mc.player);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
        oldMouseX = mouseX;
        oldMouseY = mouseY;
    }
}
