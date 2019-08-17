package com.toma.pubgmc.config.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.INBTSerializable;

public final class CFGWeapon implements INBTSerializable<NBTTagCompound> {

    @Config.Name("Damage")
    @Config.Comment("Weapon damage")
    @Config.RangeDouble(min = 1, max = 100)
    public float damage;

    @Config.Name("Bullet velocity")
    @Config.Comment("Bullet movement per tick")
    @Config.RangeDouble(min = 0.1, max = 50)
    public float velocity;

    @Config.Name("Gravity modifier")
    @Config.Comment("Amount of -Y movement per tick")
    public float gravityModifier;

    @Config.Name("Gravity apply time")
    @Config.Comment("Amount of ticks to start applying gravity effect")
    public int gravityEffectStart;

    @Config.Name("Horizontal recoil scale")
    @Config.Comment("Multiplier which is applied to current weapon's recoil")
    public float recoilHorizontalMultiplier;

    @Config.Name("Vertical recoil scale")
    @Config.Comment("Multiplier which is applied to current weapon's recoil")
    public float recoilVerticalMultiplier;

    public CFGWeapon(float damage, float velocity, float gravity, int time) {
        this.damage = damage;
        this.velocity = velocity;
        this.gravityModifier = gravity;
        this.gravityEffectStart = time;
        this.recoilHorizontalMultiplier = 1f;
        this.recoilVerticalMultiplier = 1f;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setFloat("damage", damage);
        c.setFloat("velocity", velocity);
        c.setFloat("gravity", gravityModifier);
        c.setInteger("gravityStart", gravityEffectStart);
        c.setFloat("horizontal", recoilHorizontalMultiplier);
        c.setFloat("vertical", recoilVerticalMultiplier);
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        damage = nbt.getFloat("damage");
        velocity = nbt.getFloat("velocity");
        gravityModifier = nbt.getFloat("gravity");
        gravityEffectStart = nbt.getInteger("gravityStart");
        recoilHorizontalMultiplier = nbt.getFloat("horizontal");
        recoilVerticalMultiplier = nbt.getFloat("vertical");
    }
}
