package dev.toma.pubgmc.api.capability;

import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ReloadInfo {

    void onTick();

    void startReload(EntityPlayer player, GunBase gun, ItemStack stack);

    void interrupt();

    void setReloading(boolean reloading);

    boolean isReloading();
}
