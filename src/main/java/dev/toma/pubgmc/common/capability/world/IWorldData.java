package dev.toma.pubgmc.common.capability.world;

import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

// TODO this can be possibly completely removed
public interface IWorldData extends INBTSerializable<NBTTagCompound> {

    // TODO to be removed with implementation of dynamic data driven loot config
    @Deprecated
    void toggleAirdropWeapons(boolean enable);

    @Deprecated
    boolean hasAirdropWeapons();

    @Deprecated
    void toggleAmmoLoot(boolean enableAmmo);

    @Deprecated
    boolean isAmmoLootEnabled();

    @Deprecated
    void toggleRandomAmmoCount(boolean randomAmmo);

    @Deprecated
    boolean isRandomAmmoCountEnabled();

    @Deprecated
    double getLootChanceMultiplier();

    @Deprecated
    void setLootChanceMultiplier(double chance);

    @Deprecated
    void addWeaponTypeToLootGeneration(GunBase.GunType typeToAdd);

    @Deprecated
    void removeWeaponTypeFromLootGeneration(GunBase.GunType typeToRemove);

    @Deprecated
    List<GunBase.GunType> getWeaponList();

    @Deprecated
    void setWeaponList(int[] enumIDs);

    @Deprecated
    void resetWeaponLootGeneration();

    @Deprecated
    void setGhillieSuitsColorVariants(List<Integer> list);

    @Deprecated
    List<Integer> getGhillieSuitsColorVariants();

    @Deprecated
    void addColorVariant(int color);

    @Deprecated
    void removeColorVariant(int color);

    class WorldData implements IWorldData {

        private boolean airdropWep = false, ammoLoot = true, randomAmmoCount = false;
        private double chance = 1;
        private List<GunBase.GunType> weaponTypes = new ArrayList<GunBase.GunType>(GunBase.GunType.toCollection());
        List<Integer> ghillieColors = new ArrayList<>(1);

        public WorldData() {
            ghillieColors.add(0x00FF00);
        }

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
        public void addWeaponTypeToLootGeneration(GunBase.GunType typeToAdd) {
            if (!weaponTypes.contains(typeToAdd) && typeToAdd != GunBase.GunType.LMG)
                weaponTypes.add(typeToAdd);
        }

        @Override
        public void removeWeaponTypeFromLootGeneration(GunBase.GunType typeToRemove) {
            if (weaponTypes.contains(typeToRemove))
                weaponTypes.remove(typeToRemove);
        }

        @Override
        public List<GunBase.GunType> getWeaponList() {
            return weaponTypes;
        }

        @Override
        public void setWeaponList(int[] enumIDs) {
            if (weaponTypes != null && !weaponTypes.isEmpty()) {
                weaponTypes.clear();
                for (int i = 0; i < enumIDs.length; i++) {
                    GunBase.GunType type = GunBase.GunType.values()[i];
                    if (!weaponTypes.contains(type)) {
                        weaponTypes.add(type);
                    }
                }
            }
        }

        @Override
        public void resetWeaponLootGeneration() {
            weaponTypes.clear();
            for (int i = 0; i < GunBase.GunType.values().length; i++) {
                weaponTypes.add(GunBase.GunType.values()[i]);
            }

            //serves no purpose for loot gen
            if (weaponTypes.contains(GunBase.GunType.LMG))
                removeWeaponTypeFromLootGeneration(GunBase.GunType.LMG);
        }

        @Override
        public void setGhillieSuitsColorVariants(List<Integer> list) {
            this.ghillieColors = list;
        }

        @Override
        public List<Integer> getGhillieSuitsColorVariants() {
            return ghillieColors;
        }

        @Override
        public void addColorVariant(int color) {
            ghillieColors.add(color);
        }

        @Override
        public void removeColorVariant(int color) {
            ghillieColors.remove(color);
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
            NBTTagList colors = new NBTTagList();
            for(int i = 0; i < this.ghillieColors.size(); i++) {
                colors.appendTag(new NBTTagInt(this.ghillieColors.get(i)));
            }
            c.setTag("list", weaponlist);
            c.setTag("colors", colors);
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
                this.weaponTypes.add(GunBase.GunType.values()[list.getIntAt(i)]);
            }
            NBTTagList colors = nbt.getTagList("colors", Constants.NBT.TAG_INT);
            this.ghillieColors = new ArrayList<>();
            for(int i = 0; i < colors.tagCount(); i++) {
                this.ghillieColors.add(colors.getIntAt(i));
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
