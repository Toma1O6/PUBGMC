package dev.toma.pubgmc.common.capability.player;

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

    @Deprecated
    boolean isAiming();

    @Deprecated
    boolean isReloading();

    void setNightVisionActive(boolean status);

    boolean isNightVisionActive();

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
