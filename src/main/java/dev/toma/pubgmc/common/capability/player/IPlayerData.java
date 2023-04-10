package dev.toma.pubgmc.common.capability.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IPlayerData extends INBTSerializable<NBTTagCompound> {

    BoostStats getBoostStats();

    AimInfo getAimInfo();

    ReloadInfo getReloadInfo();

    void tick();

    EntityPlayer getPlayer();

    void setEquipmentItem(SpecialEquipmentSlot slot, ItemStack stack);

    ItemStack getEquipmentItem(SpecialEquipmentSlot slot);

    IInventory getEquipmentInventory();

    @Deprecated
    boolean isAiming();

    @Deprecated
    boolean isReloading();

    void setNightVisionActive(boolean status);

    boolean isNightVisionActive();

    @Deprecated
    void setBackpackLevel(int level);

    @Deprecated
    int getBackpackLevel();

    @Deprecated
    boolean getEquippedNV();

    boolean isProne();

    void setProne(boolean proning);

    /**
     * @deprecated Rework the distance logic, move out of player data
     */
    @Deprecated
    double getDistance();

    /**
     * @deprecated Rework the distance logic, move out of player data
     */
    @Deprecated
    void setDistance(double dist);

    void sync();
}
