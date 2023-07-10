package dev.toma.pubgmc.util.handlers;

import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.api.entity.EntityDebuffs;
import dev.toma.pubgmc.common.entity.throwables.EntityFlashBang;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Optional;

public final class FlashHandler {

    public static final int MAX_FLASH_DEBUFF_TIME = 180;
    public static final int MIN_FLASH_DEBUFF_TIME = 40;
    public static final int MAX_FLASH_RANGE = 20;

    public static Optional<EntityDebuffs> getEntityDebuffs(EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            return PlayerDataProvider.getOptional((EntityPlayer) entity).map(IPlayerData::getDebuffs);
        }
        if (entity instanceof EntityDebuffs) {
            return Optional.of((EntityDebuffs) entity);
        }
        return Optional.empty();
    }

    public static void flashEntity(EntityLivingBase entity, EntityFlashBang from) {
        Optional<EntityDebuffs> optional = getEntityDebuffs(entity);
        optional.ifPresent(debuffs -> {
            boolean isPlayer = entity instanceof EntityPlayer;
            int amount = getFlashAmountFor(entity, from);
            if (amount > 0) {
                if (isInRangeToFlash(entity, from)) {
                    debuffs.setBlindTime(amount);
                }
                debuffs.setDeafTime(amount);
                if (isPlayer && entity.world.isRemote) {
                    SoundEvent event = amount > MIN_FLASH_DEBUFF_TIME ? PMCSounds.flash : PMCSounds.flash_short;
                    entity.playSound(event, 1.0F, 1.0F);
                }
            }
            if (isPlayer) {
                PlayerDataProvider.getOptional((EntityPlayer) entity).ifPresent(IPlayerData::sync);
            }
        });
    }

    public static int getFlashAmountFor(EntityLivingBase entity, EntityFlashBang flash) {
        float f0 = (float) PUBGMCUtil.getDistanceToBlockPos3D(entity.getPosition(), flash.getPosition());
        int amount = MAX_FLASH_DEBUFF_TIME;
        if (f0 > MAX_FLASH_RANGE + 5) {
            return 0;
        } else if (f0 > MAX_FLASH_RANGE - 5) {
            amount = MIN_FLASH_DEBUFF_TIME;
        }
        return amount;
    }

    // TODO wtf
    public static boolean isInRangeToFlash(EntityLivingBase player, EntityFlashBang flash) {
        Vec3d playerPos = PUBGMCUtil.getPositionVec(player);
        Vec3d playerLook = player.getLookVec();
        Vec3d playerLookNegativeYaw = playerPos.add(playerLook.rotateYaw(-40f));
        Vec3d playerLookPositiveYaw = playerPos.add(playerLook.rotateYaw(40f));
        Vec3d flashPos = PUBGMCUtil.getPositionVec(flash);
        double d0 = area(playerPos, playerLookNegativeYaw, playerLookPositiveYaw) * 10000;
        double d1 = area(flashPos, playerLookNegativeYaw, playerLookPositiveYaw) * 10000;
        double d2 = area(playerPos, flashPos, playerLookPositiveYaw) * 10000;
        double d3 = area(playerPos, playerLookNegativeYaw, flashPos) * 10000;
        return ((int) d1 == (int) (d0 + d2 + d3));
    }

    private static double area(Vec3d yawNeg, Vec3d yawPos, Vec3d flash) {
        return Math.abs((yawNeg.x * (yawPos.z - flash.z) + yawPos.x * (flash.z - yawNeg.z) + flash.x * (yawNeg.z - yawPos.z)) / 2.0D);
    }

    @EventBusSubscriber(Side.CLIENT)
    public static class ClientHandler {

        @SubscribeEvent(priority = EventPriority.LOW)
        public static void renderBlindnessOverlay(RenderGameOverlayEvent.Post event) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            PlayerDataProvider.getOptional(player).ifPresent(data -> {
                EntityDebuffs debuffs = data.getDebuffs();
                int blindTime = debuffs.getRemainingBlindTime();
                if (blindTime <= 0)
                    return;
                float blindnessAmount = blindTime / (MAX_FLASH_DEBUFF_TIME / 2.0F);
                ScaledResolution resolution = event.getResolution();
                int screenWidth = resolution.getScaledWidth();
                int screenHeight = resolution.getScaledWidth();
                ImageUtil.drawShape(0, 0, screenWidth, screenHeight, 1.0F, 1.0F, 1.0F, Math.min(1.0F, blindnessAmount));
            });
        }

        @SubscribeEvent
        public static void playSoundEvent(PlaySoundEvent event) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            String name = event.getName();
            if ("flash.full".equals(name) || "flash.short".equals(name))
                return;
            PlayerDataProvider.getOptional(player).ifPresent(data -> {
                EntityDebuffs debuffs = data.getDebuffs();
                if (debuffs.isDeaf()) {
                    event.setResultSound(null);
                }
            });
        }
    }
}
