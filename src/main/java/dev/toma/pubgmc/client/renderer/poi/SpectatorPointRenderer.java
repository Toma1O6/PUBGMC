package dev.toma.pubgmc.client.renderer.poi;

import dev.toma.pubgmc.common.games.map.SpectatorPoint;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class SpectatorPointRenderer extends SimplePoiRenderer<SpectatorPoint> {

    @Override
    public void renderInDebugMode(SpectatorPoint point, double x, double y, double z, float partialTicks) {
        startColorState();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();
        BlockPos pos = point.getPointPosition();
        builder.setTranslation(-x, -y, -z);
        builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        addColorBounds(builder, pos.getX() + 0.1, pos.getY(), pos.getZ() + 0.1, pos.getX() + 0.9, pos.getY() + 45, pos.getZ() + 0.9, 0x44FFFFFF, 0x44FFFFFF);
        builder.sortVertexData((float) x, (float) y, (float) z);
        tessellator.draw();
        builder.setTranslation(0, 0, 0);
        endColorState();
    }
}
