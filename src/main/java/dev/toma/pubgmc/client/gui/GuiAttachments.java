package dev.toma.pubgmc.client.gui;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.container.ContainerAttachments;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import dev.toma.pubgmc.common.items.guns.GunAttachments;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class GuiAttachments extends GuiContainer {

    private ItemStack stack;
    private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/gui/attachments.png");

    public GuiAttachments(EntityPlayer player) {
        super(new ContainerAttachments(player));
        ySize = 197;
        stack = ((ContainerAttachments) inventorySlots).stack.copy();
        stack.setTagCompound(new NBTTagCompound());
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1f, 1f, 1f);
        mc.renderEngine.bindTexture(TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        ItemStack dragging = mc.player.inventory.getItemStack();
        int highlighted = -1;
        if(dragging.getItem() instanceof ItemAttachment) {
            ItemAttachment item = (ItemAttachment) dragging.getItem();
            GunAttachments attachments = ((GunBase) stack.getItem()).getAttachments();
            if(attachments.supports(item)) {
                highlighted = item.getType().getIndex();
            }
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate(guiLeft, guiTop, 0);
        GlStateManager.disableTexture2D();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();
        builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i < AttachmentType.allTypes.length; i++) {
            Slot slot = inventorySlots.inventorySlots.get(i);
            boolean compat = highlighted == i;
            drawSlot(slot, compat, dragging.isEmpty(), builder);
        }
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    void drawSlot(Slot slot, boolean compatible, boolean drawDefaultSlot, BufferBuilder bufferBuilder) {
        if(drawDefaultSlot) {
            addShapeToBuffer(slot.xPos - 1, slot.yPos - 1, slot.xPos + 17, slot.yPos + 17, 0.545F, 0.545F, 0.545F, bufferBuilder);
            addShapeToBuffer(slot.xPos - 1, slot.yPos - 1, slot.xPos + 16, slot.yPos + 16, 0.216F, 0.216F, 0.216F, bufferBuilder);
            addShapeToBuffer(slot.xPos, slot.yPos, slot.xPos + 17, slot.yPos + 17, 1.0F, 1.0F, 1.0F, bufferBuilder);
            addShapeToBuffer(slot.xPos, slot.yPos, slot.xPos + 16, slot.yPos + 16, 0.545F, 0.545F, 0.545F, bufferBuilder);
        } else {
            addShapeToBuffer(slot.xPos - 1, slot.yPos - 1, slot.xPos + 17, slot.yPos + 17, compatible ? 0.0F : 0.8F, compatible ? 0.8F : 0.0F, 0.0F, bufferBuilder);
            addShapeToBuffer(slot.xPos - 1, slot.yPos - 1, slot.xPos + 16, slot.yPos + 16, compatible ? 0.0F : 0.65F, compatible ? 0.65F : 0.0F, 0.0F, bufferBuilder);
            addShapeToBuffer(slot.xPos, slot.yPos, slot.xPos + 17, slot.yPos + 17, compatible ? 0.0F : 0.9F, compatible ? 0.9F : 0.0F, 0.0F, bufferBuilder);
            addShapeToBuffer(slot.xPos, slot.yPos, slot.xPos + 16, slot.yPos + 16, 0.545F, 0.545F, 0.545F, bufferBuilder);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    public void renderItem(ItemStack stack, int x, int y) {
        GlStateManager.pushMatrix();
        IBakedModel bakedModel = mc.getRenderItem().getItemModelWithOverrides(stack, null, null);
        mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.setupGuiTransform(x, y, bakedModel.isGui3d());
        bakedModel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(bakedModel, ItemCameraTransforms.TransformType.GUI, false);
        mc.getRenderItem().renderItem(stack, bakedModel);
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
        GlStateManager.scale(80.0F, 80.0F, 80.0F);
        if (isGui3d) {
            GlStateManager.enableLighting();
        } else {
            GlStateManager.disableLighting();
        }
    }

    void addShapeToBuffer(int x1, int y1, int x2, int y2, float r, float g, float b, BufferBuilder builder) {
        builder.pos(x1, y2, 0).color(r, g, b, 1.0F).endVertex();
        builder.pos(x2, y2, 0).color(r, g, b, 1.0F).endVertex();
        builder.pos(x2, y1, 0).color(r, g, b, 1.0F).endVertex();
        builder.pos(x1, y1, 0).color(r, g, b, 1.0F).endVertex();
    }
}
