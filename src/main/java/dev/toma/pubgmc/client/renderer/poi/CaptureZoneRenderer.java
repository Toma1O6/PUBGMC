package dev.toma.pubgmc.client.renderer.poi;

import dev.toma.pubgmc.api.game.CaptureZones;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.common.games.map.CaptureZonePoint;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class CaptureZoneRenderer extends SimplePoiRenderer<CaptureZonePoint> {

    @Override
    public void renderInDebugMode(CaptureZonePoint point, double x, double y, double z, float partialTicks) {
        startColorState();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();
        BlockPos pos = point.getPointPosition();
        builder.setTranslation(-x, -y, -z);
        builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        Vec3d n = point.getLeftScale();
        Vec3d p = point.getRightScale();
        addBox(builder, pos.getX() + 0.5 + n.x, pos.getY() + 0.5 + n.y, pos.getZ() + 0.5 + n.z, pos.getX() + 0.5 + p.x, pos.getY() + 0.5 + p.y, pos.getZ() + 0.5 + p.z, 0x44FFFFFF, 0x44FFFFFF);
        addColorBounds(builder, pos.getX() + 0.1, pos.getY(), pos.getZ() + 0.1, pos.getX() + 0.9, pos.getY() + 0.5, pos.getZ() + 0.9, 0xFFFFFFFF, 0x44FFFFFF);
        builder.sortVertexData((float) x, (float) y, (float) z);
        tessellator.draw();
        builder.setTranslation(0, 0, 0);
        endColorState();

        String label = point.getLabel() != null ? point.getLabel().toUpperCase() : null;
        if (label != null) {
            Minecraft mc = Minecraft.getMinecraft();
            RenderManager manager = mc.getRenderManager();
            FontRenderer font = mc.fontRenderer;
            double distance = pos.distanceSq(manager.viewerPosX, manager.viewerPosY, manager.viewerPosZ);
            int renderDistance = manager.options.renderDistanceChunks << 4;
            if (distance > renderDistance * renderDistance) {
                return;
            }
            float factor = (float) (distance * 0.00001F + 0.05F);
            renderTowardsViewer(mc, manager, x, y, z, pos.getX() + 0.5, pos.getY() + 5.0 + p.y, pos.getZ() + 0.5, factor, () -> {
                font.drawString(label, -font.getStringWidth(label) / 2, 0, 0x66ffffff);
                GlStateManager.enableDepth();
                GlStateManager.depthMask(true);
                font.drawString(label, -font.getStringWidth(label) / 2, 0, -1);
            });
        }
    }

    @Override
    public void renderPointInGame(CaptureZonePoint point, @Nullable Game<?> game, double x, double y, double z, float partialTicks) {
        if (!game.isStarted())
            return;
        if (!(game instanceof CaptureZones))
            return;
        BlockPos pos = point.getPointPosition();
        CaptureZones.CaptureData captureData = ((CaptureZones) game).getCapturePointData(pos);
        if (captureData == null)
            return;
        String label = point.getLabel() != null ? point.getLabel().toUpperCase() : null;
        if (label != null) {
            Minecraft mc = Minecraft.getMinecraft();
            FontRenderer font = mc.fontRenderer;
            RenderManager manager = mc.getRenderManager();
            double distance = pos.distanceSq(manager.viewerPosX, manager.viewerPosY, manager.viewerPosZ);
            int renderDistance = manager.options.renderDistanceChunks << 4;
            if (distance > renderDistance * renderDistance) {
                return;
            }
            float factor = (float) (distance * 0.00001F + 0.05F);
            renderTowardsViewer(mc, manager, x, y, z, pos.getX() + 0.5, pos.getY() + 5.0 + point.getRightScale().y, pos.getZ() + 0.5, factor, () -> {
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder builder = tessellator.getBuffer();
                float progress = captureData.getCaptureProgress();
                boolean capturing = progress > 0.0F;
                int width = font.getStringWidth(label);
                GlStateManager.disableTexture2D();
                builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
                builder.pos(-width - 1, -4, 0).color(0.0F, 0.0F, 0.0F, 0.7F).endVertex();
                builder.pos(-width - 1, 11, 0).color(0.0F, 0.0F, 0.0F, 0.7F).endVertex();
                builder.pos(width + 1, 11, 0).color(0.0F, 0.0F, 0.0F, 0.7F).endVertex();
                builder.pos(width + 1, -4, 0).color(0.0F, 0.0F, 0.0F, 0.7F).endVertex();
                float[] bg = ImageUtil.decomposeRGB(captureData.getBackground());
                builder.pos(-width, -3, 0).color(bg[0], bg[1], bg[2], 0.7F).endVertex();
                builder.pos(-width, 10, 0).color(bg[0], bg[1], bg[2], 0.7F).endVertex();
                builder.pos(width, 10, 0).color(bg[0], bg[1], bg[2], 0.7F).endVertex();
                builder.pos(width, -3, 0).color(bg[0], bg[1], bg[2], 0.7F).endVertex();
                int textColor = 0xFFFFFF;
                if (capturing) {
                    float[] fg = ImageUtil.decomposeRGB(captureData.getForeground());
                    float min = -3.0F + 13.0F * (1.0F - progress);
                    float max = 10.0F;
                    builder.pos(-width, min, 0).color(fg[0], fg[1], fg[2], 0.7F).endVertex();
                    builder.pos(-width, max, 0).color(fg[0], fg[1], fg[2], 0.7F).endVertex();
                    builder.pos(width, max, 0).color(fg[0], fg[1], fg[2], 0.7F).endVertex();
                    builder.pos(width, min, 0).color(fg[0], fg[1], fg[2], 0.7F).endVertex();

                    long time = System.currentTimeMillis();
                    float partial = (time % 1000L) / 1000.0F;
                    float f = partial <= 0.5F ? partial / 0.5F : 1.0F - (partial - 0.5F) / 0.5F;
                    float eased = f < 0.5F ? 2 * f * f : 1 - (float) Math.pow(-2 * f + 2, 2) / 2;
                    int blue = (int) (255 * eased);
                    textColor = 0xFFFF << 8 | blue;
                }
                tessellator.draw();
                GlStateManager.enableTexture2D();
                font.drawStringWithShadow(label, -width / 2f, 0, textColor);
                GlStateManager.enableDepth();
                GlStateManager.depthMask(true);
            });
        }
    }

    @Override
    public void renderInHud(CaptureZonePoint point, EntityPlayer player, Game<?> game, ScaledResolution resolution, float partialTicks) {
        if (!game.isStarted())
            return;
        if (!(game instanceof CaptureZones))
            return;
        CaptureZones zones = (CaptureZones) game;
        CaptureZones.CaptureData data = zones.getEntityCaptureData(player);
        if (data == null)
            return;
        int width = 120;
        int height = 25;
        float left = (resolution.getScaledWidth() - width) / 2.0F;
        float top = 5.0F;
        ImageUtil.drawShape(left, top, left + width, top + height, 0x66 << 24);
        ImageUtil.drawShape(left + 2, top + height - 7, left + width - 2, top + height - 2, 0x66 << 24 | data.getBackground());
        if (data.getCaptureProgress() > 0.0F) {
            float right = left + (width - 2) * data.getCaptureProgress();
            ImageUtil.drawShape(left + 2, top + height - 7, right, top + height - 2, 0x66 << 24 | data.getForeground());
        }
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        StringBuilder text = new StringBuilder();
        String label = point.getLabel();
        if (label != null) {
            text.append(label.toUpperCase()).append(": ");
        }
        text.append(data.getStatus().getTitle().getFormattedText());
        font.drawStringWithShadow(TextFormatting.UNDERLINE + text.toString(), left + 5, top + 5, 0xFFFFFF);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
