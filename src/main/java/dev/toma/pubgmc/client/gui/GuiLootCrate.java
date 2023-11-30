package dev.toma.pubgmc.client.gui;

import dev.toma.pubgmc.common.container.ContainerLootCrate;
import dev.toma.pubgmc.common.tileentity.TileEntityLootCrate;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiLootCrate extends GuiContainer {

    public GuiLootCrate(InventoryPlayer player, TileEntityLootCrate tileentity) {
        super(new ContainerLootCrate(player, tileentity));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(GuiLootSpawner.TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
