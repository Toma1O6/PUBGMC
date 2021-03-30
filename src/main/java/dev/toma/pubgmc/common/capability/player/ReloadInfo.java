package dev.toma.pubgmc.common.capability.player;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.items.guns.IReloader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class ReloadInfo implements INBTSerializable<NBTTagCompound> {

    boolean reloading;
    int reloadingSlot;
    int reloadingTime;

    public void tick(IPlayerData data) {
        if(reloading) {
            EntityPlayer player = data.getPlayer();
            player.setSprinting(false);
            ItemStack stack = player.getHeldItemMainhand();
            int slot = player.inventory.currentItem;
            if(stack.getItem() instanceof GunBase) {
                GunBase gun = (GunBase) stack.getItem();
                if(slot != reloadingSlot) {
                    interrupt(data);
                    return;
                }
                if(--reloadingTime < 0) {
                    IReloader reloader = gun.getReloader();
                    boolean finished = reloader.finishCycle(gun, stack, player);
                    if(finished) {
                        reloading = false;
                    } else {
                        reloadingTime = reloader.getReloadTime(gun, stack);
                    }
                    data.sync();
                }
            } else {
                interrupt(data);
            }
        }
    }

    public void startReload(EntityPlayer player, GunBase gun, ItemStack stack) {
        IReloader reloader = gun.getReloader();
        if(reloader.canReload(player, gun, stack)) {
            setReloading(true);
            reloadingSlot = player.inventory.currentItem;
            reloadingTime = gun.getReloader().getReloadTime(gun, stack);
        }
    }

    public void interrupt(IPlayerData data) {
        reloading = false;
        data.sync();
    }

    public void setReloading(boolean reloading) {
        this.reloading = reloading;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("reloading", reloading);
        nbt.setInteger("reloadingSlot", reloadingSlot);
        nbt.setInteger("reloadingTime", reloadingTime);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        reloading = nbt.getBoolean("reloading");
        reloadingSlot = nbt.getInteger("reloadingSlot");
        reloadingTime = nbt.getInteger("reloadingTime");
    }
}
