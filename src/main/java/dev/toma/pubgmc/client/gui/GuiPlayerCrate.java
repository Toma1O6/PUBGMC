package dev.toma.pubgmc.client.gui;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.container.ContainerPlayerCrate;
import dev.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPlayerCrate extends GuiContainer {
    private static final ResourceLocation TEXTURES = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/player_crate.png");
    private final InventoryPlayer player;
    private final TileEntityPlayerCrate te;

    public GuiPlayerCrate(InventoryPlayer player, TileEntityPlayerCrate te) {
        super(new ContainerPlayerCrate(player, te));
        this.te = te;
        this.player = player;
        this.xSize = 175;
        this.ySize = 192;
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
