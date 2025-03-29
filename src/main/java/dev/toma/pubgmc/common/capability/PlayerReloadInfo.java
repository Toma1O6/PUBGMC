package dev.toma.pubgmc.common.capability;

import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.ReloadInfo;
import dev.toma.pubgmc.client.animation.AnimationType;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.items.guns.IReloader;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_PacketAnimation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerReloadInfo implements ReloadInfo {

    private final IPlayerData data;
    private boolean reloading;
    private int reloadingSlot;
    private int reloadingTime;
    private ItemStack reloadingItem = ItemStack.EMPTY;

    public PlayerReloadInfo(IPlayerData data) {
        this.data = data;
    }

    @Override
    public void onTick() {
        if (reloading) {
            EntityPlayer player = data.getPlayer();
            ItemStack stack = player.getHeldItemMainhand();
            int slot = player.inventory.currentItem;
            if (!(stack.getItem() instanceof GunBase)) {
                interrupt();
                return;
            }
            GunBase gun = (GunBase) stack.getItem();
            if (slot != reloadingSlot || !ItemStack.areItemsEqual(this.reloadingItem, stack)) {
                interrupt();
                return;
            }
            if (--reloadingTime < 0) {
                IReloader reloader = gun.getReloader();
                boolean finished = reloader.finishCycle(gun, stack, player);
                if (finished) {
                    reloading = false;
                    reloadingItem = ItemStack.EMPTY;
                } else {
                    reloadingTime = reloader.getReloadTime(gun, stack);
                }
                data.sync();
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
            reloadingItem = stack.copy();
        }
    }

    @Override
    public void interrupt() {
        reloading = false;
        reloadingItem = ItemStack.EMPTY;
        EntityPlayer player = data.getPlayer();
        if (!player.world.isRemote) {
            PacketHandler.sendToClient(new S2C_PacketAnimation(false, AnimationType.RELOAD_ANIMATION_TYPE), (EntityPlayerMP) player);
        }
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
        nbt.setTag("reloadingItemStack", reloadingItem.serializeNBT());
        return nbt;
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        reloading = nbt.getBoolean("reloading");
        reloadingSlot = nbt.getInteger("reloadingSlot");
        reloadingTime = nbt.getInteger("reloadingTime");
        reloadingItem = new ItemStack(nbt.getCompoundTag("reloadingItemStack"));
    }
}
