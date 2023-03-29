package dev.toma.pubgmc.common.capability.player;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AimInfo implements INBTSerializable<NBTTagCompound> {

    public static final float STOP_AIMING_SPEED = 0.3F;
    final IPlayerData data;
    int slot;
    boolean aiming;
    float progress, progressPrev;
    float aimSpeed = STOP_AIMING_SPEED;

    public AimInfo(IPlayerData data) {
        this.data = data;
    }

    public void onTick() {
        EntityPlayer player = data.getPlayer();
        boolean server = !player.world.isRemote;
        int equippedSlot = player.inventory.currentItem;
        if (aiming && server && (!(player.getHeldItemMainhand().getItem() instanceof GunBase) || equippedSlot != slot || player.isSprinting() || data.isReloading())) {
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

    public boolean isAiming() {
        return aiming;
    }

    public boolean isFullyAds() {
        return progress == 1.0F;
    }

    public float getProgress() {
        return progress;
    }

    @SideOnly(Side.CLIENT)
    public float getProgress(float renderTickTime) {
        return DevUtil.lerp(progress, progressPrev, renderTickTime);
    }

    public void setAiming(boolean aim, float aimSpeed) {
        if (aim) {
            slot = data.getPlayer().inventory.currentItem;
        }
        this.aiming = aim;
        this.aimSpeed = aimSpeed;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("slot", slot);
        nbt.setBoolean("aiming", aiming);
        nbt.setFloat("progress", progress);
        nbt.setFloat("aimSpeed", aimSpeed);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        slot = nbt.getInteger("slot");
        aiming = nbt.getBoolean("aiming");
        progress = nbt.getFloat("progress");
        aimSpeed = nbt.getFloat("aimSpeed");
        aimSpeed = aimSpeed <= 0.0F ? STOP_AIMING_SPEED : aimSpeed;
    }
}
