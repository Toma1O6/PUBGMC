package dev.toma.pubgmc.client;

import dev.toma.pubgmc.api.games.Game;
import dev.toma.pubgmc.api.games.GameObjectiveBased;
import dev.toma.pubgmc.api.objectives.types.GameArea;
import dev.toma.pubgmc.common.capability.game.IGameData;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import dev.toma.pubgmc.common.items.attachment.ScopeData;
import dev.toma.pubgmc.common.items.game.GameControlItem;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.world.BlueZone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHandler {

    public static float fovBackup;
    public static float sensBackup;

    private double interpolate(double current, double previous, double partial) {
        return previous + (current - previous) * partial;
    }

    public RenderHandler() {
        Minecraft mc = Minecraft.getMinecraft();
        GameSettings settings = mc.gameSettings;
        fovBackup = settings.fovSetting;
        sensBackup = settings.mouseSensitivity;
    }

    @SubscribeEvent
    public void onFOVChanged(FOVUpdateEvent e) {
        EntityPlayer player = e.getEntity();
        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        if (data.isAiming()) {
            ItemStack stack = player.getHeldItemMainhand();
            if (stack.getItem() instanceof GunBase) {
                GunBase gunBase = (GunBase) stack.getItem();
                ScopeData scopeData = gunBase.getScopeData(stack);
                if(scopeData != null && scopeData.getZoom() > 0) {
                    settings.fovSetting = scopeData.getZoom();
                }
            }
        }
    }

    @SubscribeEvent
    public void renderBlueZone(RenderWorldLastEvent e) {
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.world;
        IGameData gameData = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        Game game = gameData.getCurrentGame();
        EntityPlayerSP player = mc.player;
        double partialTicks = e.getPartialTicks();
        double interpolatedPlayerX = interpolate(player.posX, player.lastTickPosX, partialTicks);
        double interpolatedPlayerY = interpolate(player.posY, player.lastTickPosY, partialTicks);
        double interpolatedPlayerZ = interpolate(player.posZ, player.lastTickPosZ, partialTicks);
        if(Game.isDebugMode && player.getHeldItemMainhand().getItem() instanceof GameControlItem) {
            if(game instanceof GameObjectiveBased) {
                for(GameArea area : ((GameObjectiveBased) game).getObjectives().values()) {
                    if(area.isLoaded(world)) {
                        BlockPos pos = area.getCenter();
                        Tessellator tessellator = Tessellator.getInstance();
                        BufferBuilder bb = tessellator.getBuffer();
                        GlStateManager.disableCull();
                        GlStateManager.disableTexture2D();
                        bb.setTranslation(-interpolatedPlayerX, -interpolatedPlayerY, -interpolatedPlayerZ);
                        bb.begin(7, DefaultVertexFormats.POSITION_COLOR);
                        float a = 1.0F;
                        bb.pos(pos.getX(), pos.getY() + 2, pos.getZ()).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX() + 1, pos.getY() + 2, pos.getZ()).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX() + 1, pos.getY() + 1, pos.getZ()).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX(), pos.getY() + 1, pos.getZ()).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX(), pos.getY() + 2, pos.getZ() + 1).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX(), pos.getY() + 1, pos.getZ() + 1).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX(), pos.getY() + 2, pos.getZ()).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX(), pos.getY() + 2, pos.getZ() + 1).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX(), pos.getY() + 1, pos.getZ() + 1).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX(), pos.getY() + 1, pos.getZ()).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX() + 1, pos.getY() + 2, pos.getZ()).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX() + 1, pos.getY() + 1, pos.getZ()).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX(), pos.getY() + 2, pos.getZ() + 1).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX() + 1, pos.getY() + 2, pos.getZ()).color(1f, 1f, 1f, a).endVertex();
                        bb.pos(pos.getX(), pos.getY() + 2, pos.getZ()).color(1f, 1f, 1f, a).endVertex();
                        bb.sortVertexData(-(float)player.posX, -(float)player.posY, -(float)player.posZ);
                        tessellator.draw();
                        bb.setTranslation(0, 0, 0);
                        GlStateManager.enableTexture2D();
                        GlStateManager.enableCull();
                    }
                }
            }
        }
        if (!gameData.getCurrentGame().isRunning() || gameData.isInactiveGame()) {
            return;
        }
        BlueZone zone = game.zone;
        if (zone == null) {
            return;
        }
        double maxClientRenderDist = mc.gameSettings.renderDistanceChunks * 16;
        if (isCloseToBorder(player, zone, maxClientRenderDist)) {
            int clientZoneColor = ConfigPMC.client.other.zoneColor.getColor();
            float a = 0.25F;
            float r = ((clientZoneColor >> 16) & 255) / 255.0F;
            float g = ((clientZoneColor >> 8) & 255) / 255.0F;
            float b = (clientZoneColor & 255) / 255.0F;
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
            if (interpolatedPlayerX < zone.minX(partialTicks) + maxClientRenderDist) {
                bufferBuilder.pos(zone.minX(partialTicks), 256D, minRenderPosZ).color(r, g, b, a).endVertex();
                bufferBuilder.pos(zone.minX(partialTicks), 256D, maxRenderPosZ).color(r, g, b, a).endVertex();
                bufferBuilder.pos(zone.minX(partialTicks), 0D, maxRenderPosZ).color(r, g, b, a).endVertex();
                bufferBuilder.pos(zone.minX(partialTicks), 0D, minRenderPosZ).color(r, g, b, a).endVertex();
            }
            if (interpolatedPlayerZ > zone.maxZ(partialTicks) - maxClientRenderDist) {
                bufferBuilder.pos(minRenderPosX, 256D, zone.maxZ(partialTicks)).color(r, g, b, a).endVertex();
                bufferBuilder.pos(maxRenderPosX, 256D, zone.maxZ(partialTicks)).color(r, g, b, a).endVertex();
                bufferBuilder.pos(maxRenderPosX, 0D, zone.maxZ(partialTicks)).color(r, g, b, a).endVertex();
                bufferBuilder.pos(minRenderPosX, 0D, zone.maxZ(partialTicks)).color(r, g, b, a).endVertex();
            }
            if (interpolatedPlayerZ < zone.minZ(partialTicks) + maxClientRenderDist) {
                bufferBuilder.pos(minRenderPosX, 256D, zone.minZ(partialTicks)).color(r, g, b, a).endVertex();
                bufferBuilder.pos(maxRenderPosX, 256D, zone.minZ(partialTicks)).color(r, g, b, a).endVertex();
                bufferBuilder.pos(maxRenderPosX, 0D, zone.minZ(partialTicks)).color(r, g, b, a).endVertex();
                bufferBuilder.pos(minRenderPosX, 0D, zone.minZ(partialTicks)).color(r, g, b, a).endVertex();
            }
            bufferBuilder.sortVertexData((float)player.posX, (float)player.posY, (float)player.posZ);
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
