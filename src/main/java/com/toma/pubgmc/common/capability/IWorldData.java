package com.toma.pubgmc.common.capability;

import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

public interface IWorldData extends INBTSerializable<NBTTagCompound> {
    void toggleAirdropWeapons(boolean enable);

    boolean hasAirdropWeapons();

    void toggleAmmoLoot(boolean enableAmmo);

    boolean isAmmoLootEnabled();

    void toggleRandomAmmoCount(boolean randomAmmo);

    boolean isRandomAmmoCountEnabled();

    double getLootChanceMultiplier();

    void setLootChanceMultiplier(double chance);

    void addWeaponTypeToLootGeneration(GunType typeToAdd);

    void removeWeaponTypeFromLootGeneration(GunType typeToRemove);

    List<GunType> getWeaponList();

    void setWeaponList(int[] enumIDs);

    void resetWeaponLootGeneration();

    class WorldDataStorage implements IStorage<IWorldData> {
        @Override
        public NBTBase writeNBT(Capability<IWorldData> cap, IWorldData i, EnumFacing side) {
            return i.serializeNBT();
        }

        @Override
        public void readNBT(Capability<IWorldData> capability, IWorldData instance, EnumFacing side, NBTBase nbt) {
            instance.deserializeNBT(nbt instanceof NBTTagCompound ? (NBTTagCompound)nbt : new NBTTagCompound());
        }
    }

    class WorldData implements IWorldData {

        private boolean airdropWep = false, ammoLoot = true, randomAmmoCount = false;
        private double chance = 1;
        private List<GunType> weaponTypes = new ArrayList<GunType>(GunType.toCollection());

        @Override
        public void toggleAirdropWeapons(boolean enable) {
            this.airdropWep = enable;
        }

        @Override
        public boolean hasAirdropWeapons() {
            return airdropWep;
        }

        @Override
        public void toggleAmmoLoot(boolean enableAmmo) {
            this.ammoLoot = enableAmmo;
        }

        @Override
        public boolean isAmmoLootEnabled() {
            return ammoLoot;
        }

        @Override
        public void toggleRandomAmmoCount(boolean randomAmmo) {
            this.randomAmmoCount = randomAmmo;
        }

        @Override
        public boolean isRandomAmmoCountEnabled() {
            return randomAmmoCount;
        }

        @Override
        public double getLootChanceMultiplier() {
            return chance;
        }

        @Override
        public void setLootChanceMultiplier(double chance) {
            this.chance = chance;
        }

        @Override
        public void addWeaponTypeToLootGeneration(GunType typeToAdd) {
            if (!weaponTypes.contains(typeToAdd) && typeToAdd != GunType.LMG)
                weaponTypes.add(typeToAdd);
        }

        @Override
        public void removeWeaponTypeFromLootGeneration(GunType typeToRemove) {
            if (weaponTypes.contains(typeToRemove))
                weaponTypes.remove(typeToRemove);
        }

        @Override
        public List<GunType> getWeaponList() {
            return weaponTypes;
        }

        @Override
        public void setWeaponList(int[] enumIDs) {
            if (weaponTypes != null && !weaponTypes.isEmpty()) {
                weaponTypes.clear();
                for (int i = 0; i < enumIDs.length; i++) {
                    GunType type = GunType.values()[i];
                    if (!weaponTypes.contains(type)) {
                        weaponTypes.add(type);
                    }
                }
            }
        }

        @Override
        public void resetWeaponLootGeneration() {
            weaponTypes.clear();
            for (int i = 0; i < GunType.values().length; i++) {
                weaponTypes.add(GunType.values()[i]);
            }

            //serves no purpose for loot gen
            if (weaponTypes.contains(GunType.LMG))
                removeWeaponTypeFromLootGeneration(GunType.LMG);
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound c = new NBTTagCompound();
            c.setBoolean("airdropWeapons", this.airdropWep);
            c.setBoolean("ammoLoot", this.ammoLoot);
            c.setBoolean("randomAmmo", this.randomAmmoCount);
            c.setDouble("chance", this.chance);
            NBTTagList weaponlist = new NBTTagList();
            for(int i = 0; i < this.weaponTypes.size(); i++) {
                weaponlist.appendTag(new NBTTagInt(weaponTypes.get(i).ordinal()));
            }
            c.setTag("list", weaponlist);
            return c;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            this.weaponTypes.clear();
            this.airdropWep = nbt.getBoolean("airdropWeapons");
            this.ammoLoot = nbt.getBoolean("ammoLoot");
            this.randomAmmoCount = nbt.getBoolean("randomAmmo");
            this.chance = nbt.getDouble("chance");
            NBTTagList list = nbt.getTagList("list", Constants.NBT.TAG_INT);
            for(int i = 0; i < list.tagCount(); i++) {
                this.weaponTypes.add(GunType.values()[list.getIntAt(i)]);
            }
        }
    }

    class WorldDataProvider implements ICapabilitySerializable<NBTBase> {
        @CapabilityInject(IWorldData.class)
        public static final Capability<IWorldData> WORLD_DATA = null;

        private IWorldData instance = WORLD_DATA.getDefaultInstance();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability == WORLD_DATA;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return capability == WORLD_DATA ? WORLD_DATA.<T>cast(instance) : null;
        }

        @Override
        public NBTBase serializeNBT() {
            return WORLD_DATA.getStorage().writeNBT(WORLD_DATA, instance, null);
        }

        @Override
        public void deserializeNBT(NBTBase nbt) {
            WORLD_DATA.getStorage().readNBT(WORLD_DATA, instance, null, nbt);
        }
    }
}
