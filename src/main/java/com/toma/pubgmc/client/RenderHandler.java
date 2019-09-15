package com.toma.pubgmc.client;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.client.models.ModelGhillie;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHandler {

    private final ModelGhillie ghillie = new ModelGhillie();

    private double interpolate(double current, double previous, double partial) {
        return previous + (current - previous) * partial;
    }

    @SubscribeEvent
    public void renderBlueZone(RenderWorldLastEvent e) {
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.world;
        IGameData gameData = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        Game game = gameData.getCurrentGame();
        if (!gameData.isPlaying() || gameData.isInactiveGame()) {
            return;
        }
        BlueZone zone = game.zone;
        if (zone == null) {
            return;
        }
        EntityPlayerSP player = mc.player;
        double maxClientRenderDist = mc.gameSettings.renderDistanceChunks * 16;
        if (isCloseToBorder(player, zone, maxClientRenderDist)) {
            double partialTicks = e.getPartialTicks();
            double interpolatedPlayerX = interpolate(player.posX, player.lastTickPosX, partialTicks);
            double interpolatedPlayerY = interpolate(player.posY, player.lastTickPosY, partialTicks);
            double interpolatedPlayerZ = interpolate(player.posZ, player.lastTickPosZ, partialTicks);
            int clientZoneColor = ConfigPMC.client.other.zoneColor;
            float a = 0.25F;
            float r = ((clientZoneColor >> 16) & 255) / 255.0F;
            float g = ((clientZoneColor >>  8) & 255) / 255.0F;
            float b = (clientZoneColor & 255) / 255.0F;
            //actual rendering
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();
            GlStateManager.enableBlend();
            GlStateManager.disableTexture2D();
            GlStateManager.disableCull();
            bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
            bufferBuilder.setTranslation(-interpolatedPlayerX, -interpolatedPlayerY, -interpolatedPlayerZ);
            double minRenderPosZ = Math.max(Math.floor(interpolatedPlayerZ - maxClientRenderDist), zone.minZ(partialTicks));
            double maxRenderPosZ = Math.min(Math.ceil(interpolatedPlayerZ + maxClientRenderDist), zone.maxZ(partialTicks));
            double minRenderPosX = Math.max(Math.floor(interpolatedPlayerX - maxClientRenderDist), zone.minX(partialTicks));
            double maxRenderPosX = Math.min(Math.ceil(interpolatedPlayerX + maxClientRenderDist), zone.maxX(partialTicks));
            if (interpolatedPlayerX > zone.maxX(partialTicks) - maxClientRenderDist) {
                bufferBuilder.pos(zone.maxX(partialTicks), 256D, minRenderPosZ).color(r, g, b, a).endVertex();
                bufferBuilder.pos(zone.maxX(partialTicks), 256D, maxRenderPosZ).color(r, g, b, a).endVertex();
                bufferBuilder.pos(zone.maxX(partialTicks), 0D, maxRenderPosZ).color(r, g, b, a).endVertex();
                bufferBuilder.pos(zone.maxX(partialTicks), 0D, minRenderPosZ).color(r, g, b, a).endVertex();
            }
            if(interpolatedPlayerX < zone.minX(partialTicks) + maxClientRenderDist) {
                bufferBuilder.pos(zone.minX(partialTicks), 256D, minRenderPosZ).color(r, g, b, a).endVertex();
                bufferBuilder.pos(zone.minX(partialTicks), 256D, maxRenderPosZ).color(r, g, b, a).endVertex();
                bufferBuilder.pos(zone.minX(partialTicks), 0D, maxRenderPosZ).color(r, g, b, a).endVertex();
                bufferBuilder.pos(zone.minX(partialTicks), 0D, minRenderPosZ).color(r, g, b, a).endVertex();
            }
            if(interpolatedPlayerZ > zone.maxZ(partialTicks) - maxClientRenderDist) {
                bufferBuilder.pos(minRenderPosX, 256D, zone.maxZ(partialTicks)).color(r, g, b, a).endVertex();
                bufferBuilder.pos(maxRenderPosX, 256D, zone.maxZ(partialTicks)).color(r, g, b, a).endVertex();
                bufferBuilder.pos(maxRenderPosX, 0D, zone.maxZ(partialTicks)).color(r, g, b, a).endVertex();
                bufferBuilder.pos(minRenderPosX, 0D, zone.maxZ(partialTicks)).color(r, g, b, a).endVertex();
            }
            if(interpolatedPlayerZ < zone.minZ(partialTicks) + maxClientRenderDist) {
                bufferBuilder.pos(minRenderPosX, 256D, zone.minZ(partialTicks)).color(r, g, b, a).endVertex();
                bufferBuilder.pos(maxRenderPosX, 256D, zone.minZ(partialTicks)).color(r, g, b, a).endVertex();
                bufferBuilder.pos(maxRenderPosX, 0D, zone.minZ(partialTicks)).color(r, g, b, a).endVertex();
                bufferBuilder.pos(minRenderPosX, 0D, zone.minZ(partialTicks)).color(r, g, b, a).endVertex();
            }

            tessellator.draw();
            bufferBuilder.setTranslation(0, 0, 0);
            GlStateManager.enableTexture2D();
            GlStateManager.enableCull();
            GlStateManager.disableBlend();
        }
    }

    public boolean isCloseToBorder(EntityPlayerSP player, BlueZone zone, double maxDist) {
        return player.posX >= zone.maxX(1.0F) - maxDist || player.posX <= zone.minX(1.0F) + maxDist || player.posZ >= zone.maxZ(1.0F) - maxDist || player.posZ <= zone.minZ(1.0F) + maxDist;
    }
}
