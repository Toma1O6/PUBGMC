package com.toma.pubgmc.util.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class ImageUtil {
    public static void drawFullScreenImage(Minecraft minecraft, ScaledResolution resolution, ResourceLocation imageLocation, boolean transparent) {
        minecraft.getTextureManager().bindTexture(imageLocation);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(0, resolution.getScaledHeight(), 0).tex(0, 1).endVertex();
        buffer.pos(resolution.getScaledWidth(), resolution.getScaledHeight(), 0).tex(1, 1).endVertex();
        buffer.pos(resolution.getScaledWidth(), 0, 0).tex(1, 0).endVertex();
        buffer.pos(0, 0, 0).tex(0, 0).endVertex();

        if (transparent) {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        } else tessellator.draw();
    }

    public static void drawFullScreenImage(Minecraft minecraft, ScaledResolution resolution, ResourceLocation imageLocation, float f) {
        minecraft.getTextureManager().bindTexture(imageLocation);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(0, resolution.getScaledHeight(), 0).tex(0, 1).endVertex();
        buffer.pos(resolution.getScaledWidth(), resolution.getScaledHeight(), 0).tex(1, 1).endVertex();
        buffer.pos(resolution.getScaledWidth(), 0, 0).tex(1, 0).endVertex();
        buffer.pos(0, 0, 0).tex(0, 0).endVertex();

        GlStateManager.pushMatrix();
        GlStateManager.color(1f, 1f, 1f, f);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.color(1f, 1f, 1f);
        GlStateManager.popMatrix();
    }

    public static void drawCustomSizedImage(Minecraft minecraft, ResourceLocation imageLocation, double startX, double startY, double width, double height, boolean transparent) {
        minecraft.getTextureManager().bindTexture(imageLocation);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(startX, startY + height, 0).tex(0, 1).endVertex();
        buffer.pos(startX + width, startY + height, 0).tex(1, 1).endVertex();
        buffer.pos(startX + width, startY, 0).tex(1, 0).endVertex();
        buffer.pos(startX, startY, 0).tex(0, 0).endVertex();

        if (transparent) {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        } else tessellator.draw();
    }

    public static void drawImageWithUV(Minecraft minecraft, ResourceLocation imageLocation, int startX, int startY, double width, double height, double startU, double startV, double u, double v, boolean transparent) {
        minecraft.getTextureManager().bindTexture(imageLocation);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        if (u > 1) u = 1;
        if (v > 1) v = 1;

        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(startX, startY + height, 0).tex(startU, v).endVertex();
        buffer.pos(startX + width, startY + height, 0).tex(u, v).endVertex();
        buffer.pos(startX + width, startY, 0).tex(u, startV).endVertex();
        buffer.pos(startX, startY, 0).tex(startU, startV).endVertex();

        if (transparent) {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        } else tessellator.draw();
    }
}
