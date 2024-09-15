package dev.toma.pubgmc.api.capability;

import dev.toma.pubgmc.api.entity.EntityDebuffs;
import dev.toma.pubgmc.api.inventory.SpecialInventoryProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IPlayerData extends INBTSerializable<NBTTagCompound>, SpecialInventoryProvider {

    BoostStats getBoostStats();

    AimInfo getAimInfo();

    ReloadInfo getReloadInfo();

    void tick();

    EntityPlayer getPlayer();

    IInventory getEquipmentInventory();

    EntityDebuffs getDebuffs();

    void setNightVisionActive(boolean status);

    boolean isNightVisionActive();

    boolean isProne();

    void setProne(boolean proning, boolean force);

    void setActiveSlot(int slot);

    void clearActiveSlot();

    int getActiveSlot();

    void sync();
}
