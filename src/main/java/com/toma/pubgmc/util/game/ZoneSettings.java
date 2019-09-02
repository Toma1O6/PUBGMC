package com.toma.pubgmc.util.game;

import com.toma.pubgmc.util.IBuilder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public final class ZoneSettings implements INBTSerializable<NBTTagCompound> {

    public float damagePerSecond;
    public float speedModifier;
    public boolean isStatic;

    private ZoneSettings() {}

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setFloat("damage", damagePerSecond);
        nbt.setFloat("speed", speedModifier);
        nbt.setBoolean("isStatic", isStatic);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        damagePerSecond = nbt.getFloat("damage");
        speedModifier = nbt.getFloat("speed");
        isStatic = nbt.getBoolean("isStatic");
    }

    public static final class Builder implements IBuilder<ZoneSettings> {

        float dmg;
        float speed;
        boolean keepStatic;

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

        @Override
        public ZoneSettings build() {
            checkFloat(dmg, 0.1F, 15.0F);
            checkFloat(speed, 0, Float.MAX_VALUE);
            ZoneSettings settings = new ZoneSettings();
            settings.damagePerSecond = dmg;
            settings.speedModifier = speed;
            settings.isStatic = keepStatic;
            return settings;
        }
    }
}
