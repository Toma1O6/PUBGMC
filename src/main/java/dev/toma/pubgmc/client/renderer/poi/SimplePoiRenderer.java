package dev.toma.pubgmc.client.renderer.poi;

import dev.toma.pubgmc.api.client.game.MapPointRenderer;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class SimplePoiRenderer<T extends GameMapPoint> implements MapPointRenderer<T> {

    private static final SimplePoiRenderer<?> POI_RENDERER = new SimplePoiRenderer<>();

    @SuppressWarnings("unchecked")
    public static <T extends GameMapPoint> MapPointRenderer<T> simpleRenderer() {
        return (MapPointRenderer<T>) POI_RENDERER;
    }

    @Override
    public void renderInDebugMode(T point, double x, double y, double z, float partialTicks) {
        startColorState();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();
        BlockPos pos = point.getPointPosition();
        builder.setTranslation(-x, -y, -z);
        builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        addColorBounds(builder, pos.getX() + 0.1, pos.getY(), pos.getZ() + 0.1, pos.getX() + 0.9, pos.getY() + 0.5, pos.getZ() + 0.9, 0xFFFFFF00, 0x44AAAA00);
        builder.sortVertexData((float) x, (float) y, (float) z);
        tessellator.draw();
        builder.setTranslation(0, 0, 0);
        endColorState();
    }

    @Override
    public void renderPointInGame(T point, @Nullable Game<?> game, double x, double y, double z, float partialTicks) {
    }

    protected static void addColorBounds(BufferBuilder builder, double x1, double y1, double z1, double x2, double y2, double z2, int color1, int color2) {
        float[] rgba1 = ImageUtil.decomposeRGBA(color1);
        float[] rgba2 = ImageUtil.decomposeRGBA(color2);

        builder.pos(x1, y1, z1).color(rgba1[0], rgba1[1], rgba1[2], rgba1[3]).endVertex();
        builder.pos(x1, y2, z1).color(rgba2[0], rgba2[1], rgba2[2], rgba2[3]).endVertex();
        builder.pos(x2, y2, z1).color(rgba2[0], rgba2[1], rgba2[2], rgba2[3]).endVertex();
        builder.pos(x2, y1, z1).color(rgba1[0], rgba1[1], rgba1[2], rgba1[3]).endVertex();

        builder.pos(x1, y1, z2).color(rgba1[0], rgba1[1], rgba1[2], rgba1[3]).endVertex();
        builder.pos(x1, y2, z2).color(rgba2[0], rgba2[1], rgba2[2], rgba2[3]).endVertex();
        builder.pos(x2, y2, z2).color(rgba2[0], rgba2[1], rgba2[2], rgba2[3]).endVertex();
        builder.pos(x2, y1, z2).color(rgba1[0], rgba1[1], rgba1[2], rgba1[3]).endVertex();

        builder.pos(x1, y1, z1).color(rgba1[0], rgba1[1], rgba1[2], rgba1[3]).endVertex();
        builder.pos(x1, y2, z1).color(rgba2[0], rgba2[1], rgba2[2], rgba2[3]).endVertex();
        builder.pos(x1, y2, z2).color(rgba2[0], rgba2[1], rgba2[2], rgba2[3]).endVertex();
        builder.pos(x1, y1, z2).color(rgba1[0], rgba1[1], rgba1[2], rgba1[3]).endVertex();

        builder.pos(x2, y1, z1).color(rgba1[0], rgba1[1], rgba1[2], rgba1[3]).endVertex();
        builder.pos(x2, y2, z1).color(rgba2[0], rgba2[1], rgba2[2], rgba2[3]).endVertex();
        builder.pos(x2, y2, z2).color(rgba2[0], rgba2[1], rgba2[2], rgba2[3]).endVertex();
        builder.pos(x2, y1, z2).color(rgba1[0], rgba1[1], rgba1[2], rgba1[3]).endVertex();
    }

    protected static void startColorState() {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.disableCull();
        GlStateManager.disableTexture2D();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    protected static void endColorState() {
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.enableTexture2D();
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
    }
}
