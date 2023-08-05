package dev.toma.pubgmc.client.renderer.poi;

import dev.toma.pubgmc.api.game.CaptureZones;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.common.games.map.CaptureZonePoint;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.Locale;

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

        String label = point.getLabel() != null ? point.getLabel().toUpperCase(Locale.ROOT) : null;
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
        String label = point.getLabel() != null ? point.getLabel().toUpperCase(Locale.ROOT) : null;
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
                int width = font.getStringWidth(label);
                drawPointLabel(captureData, font, label, -width, -3, width, 10, 0.7F);
                GlStateManager.enableDepth();
                GlStateManager.depthMask(true);
            });
        }
    }

    public static void drawPointLabel(CaptureZones.CaptureData data, FontRenderer font, String label, float x1, float y1, float x2, float y2, float opacity) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();
        float progress = data.getCaptureProgress();
        boolean capturing = progress > 0.0F;
        GlStateManager.disableTexture2D();
        builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        builder.pos(x1 - 1, y1 - 1, 0).color(0.0F, 0.0F, 0.0F, opacity).endVertex();
        builder.pos(x1 - 1, y2 + 1, 0).color(0.0F, 0.0F, 0.0F, opacity).endVertex();
        builder.pos(x2 + 1, y2 + 1, 0).color(0.0F, 0.0F, 0.0F, opacity).endVertex();
        builder.pos(x2 + 1, y1 - 1, 0).color(0.0F, 0.0F, 0.0F, opacity).endVertex();
        float[] bg = ImageUtil.decomposeRGB(data.getBackground());
        builder.pos(x1, y1, 0).color(bg[0], bg[1], bg[2], opacity).endVertex();
        builder.pos(x1, y2, 0).color(bg[0], bg[1], bg[2], opacity).endVertex();
        builder.pos(x2, y2, 0).color(bg[0], bg[1], bg[2], opacity).endVertex();
        builder.pos(x2, y1, 0).color(bg[0], bg[1], bg[2], opacity).endVertex();
        int textColor = 0xFFFFFF;
        if (capturing) {
            float[] fg = ImageUtil.decomposeRGB(data.getForeground());
            float diff = Math.abs(y1 - y2);
            float min = y1 + diff * (1.0F - progress);
            builder.pos(x1, min, 0).color(fg[0], fg[1], fg[2], opacity).endVertex();
            builder.pos(x1, y2, 0).color(fg[0], fg[1], fg[2], opacity).endVertex();
            builder.pos(x2, y2, 0).color(fg[0], fg[1], fg[2], opacity).endVertex();
            builder.pos(x2, min, 0).color(fg[0], fg[1], fg[2], opacity).endVertex();

            long time = System.currentTimeMillis();
            float partial = (time % 1000L) / 1000.0F;
            float f = partial <= 0.5F ? partial / 0.5F : 1.0F - (partial - 0.5F) / 0.5F;
            float eased = f < 0.5F ? 2 * f * f : 1 - (float) Math.pow(-2 * f + 2, 2) / 2;
            int blue = (int) (255 * eased);
            textColor = 0xFFFF << 8 | blue;
        }
        tessellator.draw();
        GlStateManager.enableTexture2D();
        float w = x2 - x1;
        float h = y2 - y1;
        font.drawStringWithShadow(label, x1 + (w - font.getStringWidth(label)) / 2.0F, y1 + (h - font.FONT_HEIGHT) / 2f + 1, textColor);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
