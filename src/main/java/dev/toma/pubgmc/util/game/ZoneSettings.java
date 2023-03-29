package dev.toma.pubgmc.util.game;

import com.google.common.base.Preconditions;
import dev.toma.pubgmc.util.IBuilder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

public final class ZoneSettings implements INBTSerializable<NBTTagCompound> {

    public float damagePerSecond;
    public float speedModifier;
    public boolean isStatic;
    public boolean alwaysCentered;
    public int customSize = 0;
    /**
     * Array of zone size modifiers per different stage. Has have length of 7!
     **/
    public float[] shrinkModifiers = new float[7];

    public ZoneSettings() {
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setFloat("damage", damagePerSecond);
        nbt.setFloat("speed", speedModifier);
        nbt.setBoolean("isStatic", isStatic);
        nbt.setBoolean("centered", alwaysCentered);
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < shrinkModifiers.length; i++) {
            list.appendTag(new NBTTagFloat(shrinkModifiers[i]));
        }
        nbt.setTag("modifiers", list);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        damagePerSecond = nbt.getFloat("damage");
        speedModifier = nbt.getFloat("speed");
        isStatic = nbt.getBoolean("isStatic");
        alwaysCentered = nbt.getBoolean("centered");
        shrinkModifiers = new float[7];
        NBTTagList list = nbt.getTagList("modifiers", Constants.NBT.TAG_FLOAT);
        for (int i = 0; i < list.tagCount(); i++) {
            shrinkModifiers[i] = list.getFloatAt(i);
        }
    }

    public static final class Builder implements IBuilder<ZoneSettings> {

        int size;
        float dmg;
        float speed;
        boolean keepStatic = false, centered = false;
        float[] floats = new float[]{0.7f, 0.4f, 0.24f, 0.1f, 0.036f, 0.016f, 0.002f};

        private Builder() {
        }

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

        public Builder modifiers(float[] floats) {
            this.floats = floats;
            return this;
        }

        public Builder modifiers(float stage1, float stage2, float stage3, float stage4, float stage5, float stage6, float stage7) {
            floats[0] = stage1;
            floats[1] = stage2;
            floats[2] = stage3;
            floats[3] = stage4;
            floats[4] = stage5;
            floats[5] = stage6;
            floats[6] = stage7;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        @Override
        public ZoneSettings build() {
            if (keepStatic) speed = 0.1F;
            checkFloat(dmg, 0.1F, 15.0F);
            checkFloat(speed, 0.1F, 1.0F);
            checkBoolean(floats.length == 7);
            Preconditions.checkState(size >= 0, "Zone size cannot be negative number!");
            ZoneSettings settings = new ZoneSettings();
            settings.damagePerSecond = dmg;
            settings.speedModifier = speed;
            settings.isStatic = keepStatic;
            settings.alwaysCentered = centered;
            settings.shrinkModifiers = floats;
            settings.customSize = size;
            return settings;
        }
    }
}
