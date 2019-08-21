package com.toma.pubgmc.common.items.guns.attachments;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.util.ImageUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiAttachments extends GuiContainer {

    private ItemStack stack;
    private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/attachmentinv.png");
    private static final ResourceLocation SLOT = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/slot.png");

    public GuiAttachments(InventoryPlayer inventory, EntityPlayer player) {
        super(new ContainerAttachments(inventory, player));
        xSize = 176;
        ySize = 170;
        stack = ((ContainerAttachments)inventorySlots).stack;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.renderEngine.bindTexture(TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        GlStateManager.pushMatrix();
        renderItem(stack, 15, -5);
        GlStateManager.popMatrix();
        for(Slot slot : this.inventorySlots.inventorySlots) {
            if(!slot.isEnabled() || slot.inventory instanceof InventoryPlayer) {
                continue;
            }
            ImageUtil.drawCustomSizedImage(mc, SLOT, guiLeft + slot.xPos - 2, guiTop + slot.yPos - 2, 20, 20, false);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    public void renderItem(ItemStack stack, int x, int y) {
        IBakedModel bakedmodel = mc.getRenderItem().getItemModelWithOverrides(stack, null, null);
        GlStateManager.pushMatrix();
        mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.setupGuiTransform(x, y, bakedmodel.isGui3d());
        GlStateManager.translate(x - 0.25, y - 0.5, 0);
        GlStateManager.scale(2.5, 2.5, 2.5);
        GlStateManager.rotate(70, 0, 1, 0);
        GlStateManager.rotate(20, 1, 0, 0);
        mc.getRenderItem().renderItem(stack, bakedmodel);
        GlStateManager.disableAlpha();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.popMatrix();
        mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
    }

    private void setupGuiTransform(int xPosition, int yPosition, boolean isGui3d) {
        GlStateManager.translate((float)xPosition, (float)yPosition, 100.0F + this.zLevel);
        GlStateManager.translate(8.0F, 8.0F, 0.0F);
        GlStateManager.scale(1.0F, -1.0F, 1.0F);
        GlStateManager.scale(16.0F, 16.0F, 16.0F);

        if (isGui3d) {
            GlStateManager.enableLighting();
        } else {
            GlStateManager.disableLighting();
        }
    }
}
