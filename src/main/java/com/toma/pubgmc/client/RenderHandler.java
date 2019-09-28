package com.toma.pubgmc.client;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.animation.HeldAnimation;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHandler {

    private final ModelBiped ghillie = new ModelBiped(1.0F);
    private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/models/armor/ghillie_layer_1.png");

    private double interpolate(double current, double previous, double partial) {
        return previous + (current - previous) * partial;
    }

    @SubscribeEvent
    public void onFOVChanged(FOVUpdateEvent e) {
        EntityPlayer player = e.getEntity();
        IPlayerData data = player.getCapability(IPlayerData.PlayerDataProvider.PLAYER_DATA, null);
        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        if(data.isAiming()) {
            ItemStack stack = player.getHeldItemMainhand();
            if(stack.getItem() instanceof GunBase) {
                int scope = stack.hasTagCompound() ? stack.getTagCompound().getInteger("scope") : 0;
                switch (scope) {
                    case 1: case 2: settings.fovSetting = 45; break;
                    case 3: settings.fovSetting = 35; break;
                    case 4: settings.fovSetting = 25; break;
                    case 5: settings.fovSetting = 10; break;
                    case 6: settings.fovSetting = 3; break;
                }
            }
        } else {
            if(settings.fovSetting < 70) settings.fovSetting = 70;
        }
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

            tessellator.draw();
            bufferBuilder.setTranslation(0, 0, 0);
            GlStateManager.enableTexture2D();
            GlStateManager.enableCull();
            GlStateManager.disableBlend();
        }
    }

    @SubscribeEvent
    public void onPlayerRenderPost(RenderPlayerEvent.Post e) {
        EntityPlayer player = e.getEntityPlayer();
        if (player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == PMCRegistry.PMCItems.GHILLIE_SUIT) {
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            ModelPlayer main = e.getRenderer().getMainModel();
            ghillie.swingProgress = player.getSwingProgress(e.getPartialRenderTick());
            boolean shouldSit = player.isRiding() && (player.getRidingEntity() != null && player.getRidingEntity().shouldRiderSit());
            boolean sneak = main.isSneak;
            ghillie.bipedBody.offsetZ = sneak ? -0.028f : 0;
            ghillie.bipedBody.offsetY = sneak ? 0.3f : 0;
            ghillie.bipedHeadwear.offsetY = sneak ? 0.32f : 0;
            ghillie.bipedRightArm.offsetY = sneak ? 0.23f : 0;
            ghillie.bipedLeftArm.offsetY = sneak ? 0.23f : 0;
            ghillie.bipedRightLeg.offsetY = sneak ? 0.7f : 0.0f;
            ghillie.bipedLeftLeg.offsetY = sneak ? 0.7f : 0.0f;
            ghillie.bipedRightLeg.offsetZ = sneak ? 0.12f : 0f;
            ghillie.bipedLeftLeg.offsetZ = sneak ? 0.12f : 0f;
            ghillie.isRiding = shouldSit;
            ghillie.isSneak = main.isSneak;
            ghillie.leftArmPose = main.leftArmPose;
            ghillie.rightArmPose = main.rightArmPose;
            ghillie.bipedLeftArm.offsetX = 0.075f;
            ghillie.bipedRightArm.offsetX = -0.075f;
            ghillie.bipedHead.showModel = false;
            float partial = e.getPartialRenderTick();
            float f = this.normalizeAndInterpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, partial);
            float f1 = this.normalizeAndInterpolateRotation(player.prevRotationYawHead, player.rotationYawHead, partial);
            float yaw = f1 - f;
            if (shouldSit && player.getRidingEntity() instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase = (EntityLivingBase) player.getRidingEntity();
                f = this.normalizeAndInterpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partial);
                yaw = f1 - f;
                float f3 = MathHelper.wrapDegrees(yaw);
                if (f3 < -85.0F) {
                    f3 = -85.0F;
                }
                if (f3 >= 85.0F) {
                    f3 = 85.0F;
                }
                f = f1 - f3;
                if (f3 * f3 > 2500.0F) {
                    f += f3 * 0.2F;
                }
                yaw = f1 - f;
            }
            float f7 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * partial;
            float f8 = player.ticksExisted + partial;
            GlStateManager.rotate(180.0F - f, 0.0F, 1.0F, 0.0F);
            float f4 = this.prepareScale(player, partial);
            float f5 = 0.0F;
            float f6 = 0.0F;
            if (!player.isRiding()) {
                f5 = player.prevLimbSwingAmount + (player.limbSwingAmount - player.prevLimbSwingAmount) * partial;
                f6 = player.limbSwing - player.limbSwingAmount * (1.0F - partial);
                if (player.isChild()) {
                    f6 *= 3.0F;
                }
                if (f5 > 1.0F) {
                    f5 = 1.0F;
                }
                yaw = f1 - f;
            }
            GlStateManager.enableAlpha();
            ghillie.setLivingAnimations(player, f6, f5, partial);
            ghillie.setRotationAngles(f6, f5, f8, yaw, f7, f4, player);
            Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
            ghillie.render(player, f6, f5, f8, yaw, f7, f4);
            GlStateManager.disableRescaleNormal();
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.enableTexture2D();
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            GlStateManager.enableCull();
            GlStateManager.popMatrix();
        }
    }

    //@SubscribeEvent
    public void renderHand(RenderHandEvent e) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        ItemStack stack = player.getHeldItemMainhand();
        if(!(stack.getItem() instanceof GunBase)) {
            IPlayerData data = player.getCapability(IPlayerData.PlayerDataProvider.PLAYER_DATA, null);
            boolean aim = data.isAiming();
            //boolean oneHand = ((GunBase)stack.getItem()).getWeaponModel().heldAnimation.getHeldStyle() == HeldAnimation.HeldStyle.SMALL;
            RenderManager manager = mc.getRenderManager();
            Render<AbstractClientPlayer> abstractClientPlayerRender = manager.getEntityRenderObject(player);
            RenderPlayer renderPlayer = (RenderPlayer) abstractClientPlayerRender;
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();

            GlStateManager.rotate(90F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(92.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(41.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(-0.3F, -1.1F, 0.45F);
            renderPlayer.renderLeftArm(mc.player);
            GlStateManager.enableCull();
            GlStateManager.popMatrix();
        }
    }

    public void renderHand() {

    }

    public float prepareScale(EntityPlayer player, float partial) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.scale(1.9F, 1.9F, 1.9F);
        GlStateManager.translate(0.0F, -1.5F, 0.0F);
        return 0.0625F;
    }

    public float normalizeAndInterpolateRotation(float prev, float current, float partial) {
        float f = current - prev;
        while (f < -180F) f += 360F;
        while (f >= 180.0F) f -= 360.0F;
        return prev + partial * f;
    }

    public boolean isCloseToBorder(EntityPlayerSP player, BlueZone zone, double maxDist) {
        return player.posX >= zone.maxX(1.0F) - maxDist || player.posX <= zone.minX(1.0F) + maxDist || player.posZ >= zone.maxZ(1.0F) - maxDist || player.posZ <= zone.minZ(1.0F) + maxDist;
    }
}
