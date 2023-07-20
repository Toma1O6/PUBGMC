package dev.toma.pubgmc.client.renderer.poi;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.common.games.map.CaptureZonePoint;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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
        String label = point.getLabel();
        if (label != null) {
            // background color
            // foreground color
            // foreground progress
        }
    }

    @Override
    public void renderInHud(CaptureZonePoint point, EntityPlayer player, Game<?> game, ScaledResolution resolution, float partialTicks) {
        // TODO capture progress
    }

    public static void renderTowardsViewer(Minecraft mc, RenderManager manager, double x, double y, double z, double posX, double posY, double posZ, float scale, Runnable renderFn) {
        boolean thirdPersonFront = manager.options.thirdPersonView == 2;
        GlStateManager.pushMatrix();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.translate(-x, -y, -z);
        GlStateManager.translate(posX, posY, posZ);
        GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-manager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(thirdPersonFront ? -1 : 1) * manager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(-scale, -scale, scale);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        renderFn.run();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }
}
