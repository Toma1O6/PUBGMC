package com.toma.pubgmc.client.gui;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.container.ContainerLootSpawner;
import com.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiLootSpawner extends GuiContainer {
    private static final ResourceLocation TEXTURES = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/loot_spawner.png");
    private final InventoryPlayer player;
    private final TileEntityLootGenerator tileentity;

    public GuiLootSpawner(InventoryPlayer player, TileEntityLootGenerator tileentity) {
        super(new ContainerLootSpawner(player, tileentity));
        this.player = player;
        this.tileentity = tileentity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
