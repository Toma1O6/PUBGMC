package dev.toma.pubgmc.common.capability.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.util.INBTSerializable;

public interface IPlayerData extends INBTSerializable<NBTTagCompound> {

    BoostStats getBoostStats();

    AimInfo getAimInfo();

    ReloadInfo getReloadInfo();

    void tick();

    EntityPlayer getPlayer();

    @Deprecated
    boolean isAiming();

    boolean isReloading();

    void setNV(boolean nv);

    boolean isUsingNV();

    void setBackpackLevel(int level);

    int getBackpackLevel();

    void hasEquippedNV(boolean nv);

    boolean getEquippedNV();

    boolean isProning();

    void setProning(boolean proning);

    double getDistance();

    void setDistance(double dist);

    void sync();

    class PlayerDataStorage implements IStorage<IPlayerData> {

        @Override
        public NBTBase writeNBT(Capability<IPlayerData> capability, IPlayerData instance, EnumFacing side) {
            return instance.serializeNBT();
        }

        @Override
        public void readNBT(Capability<IPlayerData> capability, IPlayerData instance, EnumFacing side, NBTBase nbt) {
            instance.deserializeNBT(nbt instanceof NBTTagCompound ? (NBTTagCompound)nbt : new NBTTagCompound());
        }
    }
}
