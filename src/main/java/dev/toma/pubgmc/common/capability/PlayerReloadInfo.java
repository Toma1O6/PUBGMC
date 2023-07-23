package dev.toma.pubgmc.common.capability;

import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.ReloadInfo;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.items.guns.IReloader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerReloadInfo implements ReloadInfo {

    final IPlayerData data;
    boolean reloading;
    int reloadingSlot;
    int reloadingTime;

    public PlayerReloadInfo(IPlayerData data) {
        this.data = data;
    }

    @Override
    public void onTick() {
        if (reloading) {
            EntityPlayer player = data.getPlayer();
            ItemStack stack = player.getHeldItemMainhand();
            int slot = player.inventory.currentItem;
            if (stack.getItem() instanceof GunBase) {
                GunBase gun = (GunBase) stack.getItem();
                if (slot != reloadingSlot) {
                    interrupt();
                    return;
                }
                if (--reloadingTime < 0) {
                    IReloader reloader = gun.getReloader();
                    boolean finished = reloader.finishCycle(gun, stack, player);
                    if (finished) {
                        reloading = false;
                    } else {
                        reloadingTime = reloader.getReloadTime(gun, stack);
                    }
                    data.sync();
                }
            } else {
                interrupt();
            }
        }
    }

    @Override
    public void startReload(EntityPlayer player, GunBase gun, ItemStack stack) {
        IReloader reloader = gun.getReloader();
        if (reloader.canReload(player, gun, stack)) {
            setReloading(true);
            reloadingSlot = player.inventory.currentItem;
            reloadingTime = gun.getReloader().getReloadTime(gun, stack);
        }
    }

    @Override
    public void interrupt() {
        reloading = false;
        data.sync();
    }

    @Override
    public void setReloading(boolean reloading) {
        this.reloading = reloading;
    }

    @Override
    public boolean isReloading() {
        return reloading;
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("reloading", reloading);
        nbt.setInteger("reloadingSlot", reloadingSlot);
        nbt.setInteger("reloadingTime", reloadingTime);
        return nbt;
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        reloading = nbt.getBoolean("reloading");
        reloadingSlot = nbt.getInteger("reloadingSlot");
        reloadingTime = nbt.getInteger("reloadingTime");
    }
}
