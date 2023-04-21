package dev.toma.pubgmc.api.client.util;

import dev.toma.pubgmc.api.game.area.GameArea;
import dev.toma.pubgmc.api.util.Position2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public final class GameAreaRenderer {

    public static void renderGameArea(GameArea area, int color, double x, double y, double z, float partialTicks) {
        Position2 min = area.getPositionMin(partialTicks);
        Position2 max = area.getPositionMax(partialTicks);
        double minX = min.getX();
        double minZ = min.getZ();
        double maxX = max.getX();
        double maxZ = max.getZ();
        Minecraft minecraft = Minecraft.getMinecraft();
        double maxRenderDistance = minecraft.gameSettings.renderDistanceChunks << 4;
        Entity entity = minecraft.getRenderViewEntity();

        if (entity.posX >= maxX - maxRenderDistance || entity.posX <= minX + maxRenderDistance || entity.posZ >= maxZ - maxRenderDistance || entity.posZ <= minZ + maxRenderDistance) {
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.disableTexture2D();
            GlStateManager.disableCull();
            GlStateManager.depthMask(false);
            int alpha = (color >> 24) & 0xff;
            int red = (color >> 16) & 0xff;
            int green = (color >> 8) & 0xff;
            int blue = color & 0xff;
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder builder = tessellator.getBuffer();
            builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
            builder.setTranslation(-x, -y, -z);

            double minRenderX = Math.max(MathHelper.floor(x - maxRenderDistance), minX);
            double maxRenderX = Math.min(MathHelper.ceil(x + maxRenderDistance), maxX);
            double minRenderZ = Math.max(MathHelper.floor(z - maxRenderDistance), minZ);
            double maxRenderZ = Math.min(MathHelper.ceil(z + maxRenderDistance), maxZ);
            if (x > maxX - maxRenderDistance) {
                builder.pos(maxX, 256.0, minRenderZ).color(red, green, blue, alpha).endVertex();
                builder.pos(maxX, 256.0, maxRenderZ).color(red, green, blue, alpha).endVertex();
                builder.pos(maxX,   0.0, maxRenderZ).color(red, green, blue, alpha).endVertex();
                builder.pos(maxX,   0.0, minRenderZ).color(red, green, blue, alpha).endVertex();
            }
            if (x < minX + maxRenderDistance) {
                builder.pos(minX, 256.0, minRenderZ).color(red, green, blue, alpha).endVertex();
                builder.pos(minX, 256.0, maxRenderZ).color(red, green, blue, alpha).endVertex();
                builder.pos(minX,   0.0, maxRenderZ).color(red, green, blue, alpha).endVertex();
                builder.pos(minX,   0.0, minRenderZ).color(red, green, blue, alpha).endVertex();
            }
            if (z > maxZ - maxRenderDistance) {
                builder.pos(minRenderX, 256.0, maxZ).color(red, green, blue, alpha).endVertex();
                builder.pos(maxRenderX, 256.0, maxZ).color(red, green, blue, alpha).endVertex();
                builder.pos(maxRenderX,   0.0, maxZ).color(red, green, blue, alpha).endVertex();
                builder.pos(minRenderX,   0.0, maxZ).color(red, green, blue, alpha).endVertex();
            }
            if (z < minZ + maxRenderDistance) {
                builder.pos(minRenderX, 256.0, minZ).color(red, green, blue, alpha).endVertex();
                builder.pos(maxRenderX, 256.0, minZ).color(red, green, blue, alpha).endVertex();
                builder.pos(maxRenderX,   0.0, minZ).color(red, green, blue, alpha).endVertex();
                builder.pos(minRenderX,   0.0, minZ).color(red, green, blue, alpha).endVertex();
            }

            tessellator.draw();
            builder.setTranslation(0, 0, 0);
            GlStateManager.enableCull();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
