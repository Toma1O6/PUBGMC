package com.toma.pubgmc.util.game;

import com.toma.pubgmc.util.IBuilder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public final class ZoneSettings implements INBTSerializable<NBTTagCompound> {

    public float damagePerSecond;
    public float speedModifier;
    public boolean isStatic;
    public boolean alwaysCentered;

    public ZoneSettings() {}

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setFloat("damage", damagePerSecond);
        nbt.setFloat("speed", speedModifier);
        nbt.setBoolean("isStatic", isStatic);
        nbt.setBoolean("centered", alwaysCentered);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        damagePerSecond = nbt.getFloat("damage");
        speedModifier = nbt.getFloat("speed");
        isStatic = nbt.getBoolean("isStatic");
        alwaysCentered = nbt.getBoolean("centered");
    }

    public static final class Builder implements IBuilder<ZoneSettings> {

        float dmg;
        float speed;
        boolean keepStatic = false, centered = false;

        private Builder(){}

        public static Builder create() {
            return new Builder();
        }

        public Builder damage(float damage) {
            dmg = damage;
            return this;
        }

        public Builder speed(float speed) {
            this.speed = speed;
            return this;
        }

        public Builder setStatic() {
            this.keepStatic = true;
            return this;
        }

        public Builder setAlwaysCentered() {
            this.centered = true;
            return this;
        }

        @Override
        public ZoneSettings build() {
            checkFloat(dmg, 0.1F, 15.0F);
            checkFloat(speed, 0.1F, 1.0F);
            ZoneSettings settings = new ZoneSettings();
            settings.damagePerSecond = dmg;
            settings.speedModifier = speed;
            settings.isStatic = keepStatic;
            settings.alwaysCentered = centered;
            return settings;
        }
    }
}
