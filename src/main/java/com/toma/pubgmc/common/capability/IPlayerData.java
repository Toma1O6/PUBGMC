package com.toma.pubgmc.common.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public interface IPlayerData {
    public boolean isReloading();

    public void setReloading(boolean reloading);

    public boolean isAiming();

    public void setAiming(boolean aiming);

    public void setNV(boolean nv);

    public boolean isUsingNV();

    public int getReloadingTime();

    public void setReloadingTime(int rt);

    public int getTimer();

    public void setTimer(int t);

    public void addBoost(float boost);

    public void removeBoost(float boost);

    public float getBoost();

    public void setBoost(float boost);

    public int setBackpackLevel(int level);

    public int getBackpackLevel();

    public boolean hasEquippedNV(boolean nv);

    public boolean getEquippedNV();

    boolean isProning();

    // proning
    void setProning(boolean proning);

    //grenades
    public boolean setGrenadeCooking(boolean cooking);

    public boolean isGrenadeCooking();

    public int setCookingTime(int time);

    public int getCookingTime();

    public int getScopeType();

    //scope variants
    public void setScopeType(int type);

    public int getScopeColor();

    public void setScopeColor(int color);

    public double getDistance();

    // map drop location distance
    public void setDistance(double dist);

    public class PlayerDataStorage implements IStorage<IPlayerData> {
        @Override
        public NBTBase writeNBT(Capability<IPlayerData> capability, IPlayerData instance, EnumFacing side) {
            NBTTagCompound c = new NBTTagCompound();
            c.setBoolean("reloading", instance.isReloading());
            c.setBoolean("aiming", instance.isAiming());
            c.setBoolean("nv", instance.isUsingNV());
            c.setInteger("reloading_time", instance.getReloadingTime());
            c.setInteger("timer", instance.getTimer());
            c.setFloat("boost", instance.getBoost());
            c.setInteger("level", instance.getBackpackLevel());
            c.setBoolean("eqnv", instance.getEquippedNV());
            c.setBoolean("cooking", instance.isGrenadeCooking());
            c.setInteger("cookTime", instance.getCookingTime());
            c.setInteger("scopetype", instance.getScopeType());
            c.setInteger("scopecolor", instance.getScopeColor());
            return c;
        }

        @Override
        public void readNBT(Capability<IPlayerData> capability, IPlayerData instance, EnumFacing side, NBTBase nbt) {
            instance.setReloading(((NBTTagCompound) nbt).getBoolean("reloading"));
            instance.setAiming(((NBTTagCompound) nbt).getBoolean("aiming"));
            instance.setNV(((NBTTagCompound) nbt).getBoolean("nv"));
            instance.setReloadingTime(((NBTTagCompound) nbt).getInteger("reloading_time"));
            instance.setTimer(((NBTTagCompound) nbt).getInteger("timer"));
            instance.setBoost(((NBTTagCompound) nbt).getFloat("boost"));
            instance.setBackpackLevel(((NBTTagCompound) nbt).getInteger("level"));
            instance.hasEquippedNV(((NBTTagCompound) nbt).getBoolean("eqnv"));
            instance.setGrenadeCooking(((NBTTagCompound) nbt).getBoolean("cooking"));
            instance.setCookingTime(((NBTTagCompound) nbt).getInteger("cookingTime"));
            instance.setScopeType(((NBTTagCompound) nbt).getInteger("scopetype"));
            instance.setScopeColor(((NBTTagCompound) nbt).getInteger("scopecolor"));
        }
    }

    public class PlayerData implements IPlayerData {
        private boolean reloading;
        private boolean aiming;
        private boolean nv;
        private int reloading_time;
        private int timer;
        private float boost;

        private boolean isProne;

        private int level;
        private boolean eqNV;
        private boolean cooking;
        private int cookTime;

        private int scopetype;
        private int scopecolor;

        private double dist;

        public static IPlayerData get(EntityPlayer player) {
            if (player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
                return player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            }

            return null;
        }

        @Override
        public int getCookingTime() {
            return this.cookTime;
        }

        @Override
        public boolean isGrenadeCooking() {
            return this.cooking;
        }

        @Override
        public boolean setGrenadeCooking(boolean cooking) {
            return this.cooking = cooking;
        }

        @Override
        public int setCookingTime(int time) {
            return this.cookTime = time;
        }

        @Override
        public int setBackpackLevel(int level) {
            return this.level = level;
        }

        @Override
        public int getBackpackLevel() {
            return this.level;
        }

        @Override
        public boolean hasEquippedNV(boolean nv) {
            return this.eqNV = nv;
        }

        @Override
        public boolean getEquippedNV() {
            return this.eqNV;
        }

        @Override
        public boolean isReloading() {
            return this.reloading;
        }

        @Override
        public void setReloading(boolean reloading) {
            this.reloading = reloading;
        }

        @Override
        public boolean isAiming() {
            return this.aiming;
        }

        @Override
        public void setAiming(boolean aiming) {
            this.aiming = aiming;
        }

        @Override
        public boolean isUsingNV() {
            return this.nv;
        }

        @Override
        public int getReloadingTime() {
            return this.reloading_time;
        }

        @Override
        public void setReloadingTime(int rt) {
            this.reloading_time = rt;
        }

        @Override
        public int getTimer() {
            return this.timer;
        }

        @Override
        public void setTimer(int t) {
            this.timer = t;
        }

        @Override
        public float getBoost() {
            return this.boost;
        }

        @Override
        public void setBoost(float boost) {
            this.boost = boost;
        }

        @Override
        public void setNV(boolean nv) {
            this.nv = nv;
        }

        @Override
        public void addBoost(float boost) {
            this.boost += boost;
        }

        @Override
        public void removeBoost(float boost) {
            this.boost -= boost;
        }

        @Override
        public int getScopeType() {
            return scopetype;
        }

        @Override
        public void setScopeType(int type) {
            this.scopetype = type;
        }

        @Override
        public int getScopeColor() {
            return scopecolor;
        }

        @Override
        public void setScopeColor(int color) {
            this.scopecolor = color;
        }

        @Override
        public double getDistance() {
            return dist;
        }

        @Override
        public void setDistance(double dist) {
            this.dist = dist;
        }

        @Override
        public boolean isProning() {
            return isProne;
        }

        @Override
        public void setProning(boolean proning) {
            this.isProne = proning;
        }
    }

    public class PlayerDataProvider implements ICapabilitySerializable<NBTBase> {
        @CapabilityInject(IPlayerData.class)
        public static final Capability<IPlayerData> PLAYER_DATA = null;

        private IPlayerData instance = PLAYER_DATA.getDefaultInstance();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability == PLAYER_DATA;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return capability == PLAYER_DATA ? PLAYER_DATA.<T>cast(this.instance) : null;
        }

        @Override
        public NBTBase serializeNBT() {
            return PLAYER_DATA.getStorage().writeNBT(PLAYER_DATA, this.instance, null);
        }

        @Override
        public void deserializeNBT(NBTBase nbt) {
            PLAYER_DATA.getStorage().readNBT(PLAYER_DATA, this.instance, null, nbt);
        }
    }
}
