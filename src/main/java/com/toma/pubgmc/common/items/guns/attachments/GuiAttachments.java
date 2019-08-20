package com.toma.pubgmc.common.items.guns.attachments;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.util.ModelHelper;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.util.ImageUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;

import java.util.ArrayList;
import java.util.List;

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
        mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GlStateManager.pushMatrix();
        GlStateManager.rotate(-30, 0, 0, 1);
        GlStateManager.rotate(-45, 0, 1, 0);
        GlStateManager.scale(6, 6, 6);
        GlStateManager.translate(95, -10, 0);
        mc.getRenderItem().renderItemIntoGUI(stack, 13, 15);
        GlStateManager.popMatrix();
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.renderEngine.bindTexture(TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    public void update(ItemStack stack) {
        if(stack.getItem() instanceof GunBase) {
            this.stack = stack.copy();
        } else {
            mc.displayGuiScreen(null);
        }
    }
}
