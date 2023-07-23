package dev.toma.pubgmc.common.capability;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.api.capability.AimInfo;
import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerAimInfo implements AimInfo {

    public static final float STOP_AIMING_SPEED = 0.3F;
    final IPlayerData data;
    int slot;
    boolean aiming;
    float progress, progressPrev;
    float aimSpeed = STOP_AIMING_SPEED;

    public PlayerAimInfo(IPlayerData data) {
        this.data = data;
    }

    @Override
    public void onTick() {
        EntityPlayer player = data.getPlayer();
        boolean server = !player.world.isRemote;
        int equippedSlot = player.inventory.currentItem;
        float fallDistance = player.fallDistance;
        if (aiming && server && (!(player.getHeldItemMainhand().getItem() instanceof GunBase) || equippedSlot != slot || player.isSprinting() || data.getReloadInfo().isReloading() || fallDistance > 0.5F)) {
            setAiming(false, STOP_AIMING_SPEED);
            data.sync();
        }
        progressPrev = progress;
        if (aiming && progress < 1.0F) {
            progress = Math.min(1.0F, progress + aimSpeed);
        } else if (!aiming && progress > 0.0F) {
            progress = Math.max(0.0F, progress - aimSpeed);
        }
    }

    @Override
    public boolean isAiming() {
        return aiming;
    }

    @Override
    public boolean isFullyAds() {
        return progress == 1.0F;
    }

    @Override
    public float getProgress() {
        return progress;
    }

    @Override
    public float getProgress(float renderTickTime) {
        return DevUtil.lerp(progress, progressPrev, renderTickTime);
    }

    @Override
    public void setAiming(boolean aim, float aimSpeed) {
        if (aim) {
            slot = data.getPlayer().inventory.currentItem;
        }
        this.aiming = aim;
        this.aimSpeed = aimSpeed;
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("slot", slot);
        nbt.setBoolean("aiming", aiming);
        nbt.setFloat("progress", progress);
        nbt.setFloat("aimSpeed", aimSpeed);
        return nbt;
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        slot = nbt.getInteger("slot");
        aiming = nbt.getBoolean("aiming");
        progress = nbt.getFloat("progress");
        aimSpeed = nbt.getFloat("aimSpeed");
        aimSpeed = aimSpeed <= 0.0F ? STOP_AIMING_SPEED : aimSpeed;
    }
}
