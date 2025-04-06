package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.DoubleType;
import dev.toma.configuration.api.type.ObjectType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class GlobalWeaponDamageConfig extends ObjectType implements INBTSerializable<NBTTagCompound> {

    public DoubleType pistolHeadshotMultiplier;
    public DoubleType shotgunHeadshotMultiplier;
    public DoubleType smgHeadshotMultiplier;
    public DoubleType arHeadshotMultiplier;
    public DoubleType lmgHeadshotMultiplier;
    public DoubleType dmrHeadshotMultiplier;
    public DoubleType srHeadshotMultiplier;

    public GlobalWeaponDamageConfig() {
        super("WeaponDamage");
    }

    @Override
    public void buildStructure(ConfigCreator builder) {
        pistolHeadshotMultiplier = builder.createDouble("pistolHeadshotMultiplier", 2.1, 0.0, 10.0);
        shotgunHeadshotMultiplier = builder.createDouble("shotgunHeadshotMultiplier", 1.5, 0.0, 10.0);
        smgHeadshotMultiplier = builder.createDouble("smgHeadshotMultiplier", 2.3, 0.0, 10.0);
        arHeadshotMultiplier = builder.createDouble("arHeadshotMultiplier", 2.35, 0.0, 10.0);
        lmgHeadshotMultiplier = builder.createDouble("lmgHeadshotMultiplier", 2.1, 0.0, 10.0);
        dmrHeadshotMultiplier = builder.createDouble("dmrHeadshotMultiplier", 2.35, 0.0, 10.0);
        srHeadshotMultiplier = builder.createDouble("srHeadshotMultiplier", 2.5, 0.0, 10.0);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound data = new NBTTagCompound();
        data.setDouble("pistol", pistolHeadshotMultiplier.get());
        data.setDouble("shotgun", shotgunHeadshotMultiplier.get());
        data.setDouble("smg", smgHeadshotMultiplier.get());
        data.setDouble("ar", arHeadshotMultiplier.get());
        data.setDouble("lmg", lmgHeadshotMultiplier.get());
        data.setDouble("dmr", dmrHeadshotMultiplier.get());
        data.setDouble("sr", srHeadshotMultiplier.get());
        return data;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        pistolHeadshotMultiplier.set(nbt.getDouble("pistol"));
        shotgunHeadshotMultiplier.set(nbt.getDouble("shotgun"));
        smgHeadshotMultiplier.set(nbt.getDouble("smg"));
        arHeadshotMultiplier.set(nbt.getDouble("ar"));
        lmgHeadshotMultiplier.set(nbt.getDouble("lmg"));
        dmrHeadshotMultiplier.set(nbt.getDouble("dmr"));
        srHeadshotMultiplier.set(nbt.getDouble("sr"));
    }
}
