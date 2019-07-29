package com.toma.pubgmc.client.gui;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.container.ContainerBigAirdrop;
import com.toma.pubgmc.common.tileentity.TileEntityBigAirdrop;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiBigAirdrop extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/big_airdrop.png");
    private final InventoryPlayer player;
    private final TileEntityBigAirdrop te;

    public GuiBigAirdrop(InventoryPlayer player, TileEntityBigAirdrop te) {
        super(new ContainerBigAirdrop(player, te));
        this.player = player;
        this.te = te;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
