package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.DoubleType;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.configuration.api.util.NumberDisplayType;
import dev.toma.pubgmc.common.items.guns.WeaponStats;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.text.DecimalFormat;

public final class CFGWeapon extends ObjectType implements INBTSerializable<NBTTagCompound>, WeaponStats {

    public DoubleType damage;
    public DoubleType velocity;
    public DoubleType gravityModifier;
    public IntType gravityEffectStart;
    public IntType damagedropEffectStart;
    public DoubleType damagedropModifier;
    public DoubleType mindamage;
    public DoubleType horizontalRecoilMultiplier;
    public DoubleType verticalRecoilMultiplier;

    float dmg, vel, grav, dmgd, mdmg;
    int gravst, dmgdst;

    public CFGWeapon(String name, float damage, float velocity, float gravity, int gravitystart, int damagedropstart, float damagedrop, float mindamage) {
        super(name);
        this.dmg = damage;
        this.vel = velocity;
        this.grav = gravity;
        this.gravst = gravitystart;
        this.dmgdst = damagedropstart;
        this.dmgd = damagedrop;
        this.mdmg = mindamage;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        DecimalFormat format = new DecimalFormat("#.###");
        damage = configCreator.createDouble("Damage", dmg, 1, 100).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER).setFormatting(format);
        velocity = configCreator.createDouble("Velocity", vel, 0.1, 50.0, "Velocity applied to bullet each tick").setFormatting(format);
        gravityModifier = configCreator.createDouble("Gravity modifier", grav, 0, 0.2).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER).setFormatting(format);
        gravityEffectStart = configCreator.createInt("Gravity effect delay", gravst, 0, Integer.MAX_VALUE, "Ticks before gravity effect is applied on bullet");
        damagedropEffectStart = configCreator.createInt("Damagedrop effect delay", dmgdst, 0, Integer.MAX_VALUE, "Ticks before damagedrop effect is applied on bullet");
        damagedropModifier = configCreator.createDouble("Damagedrop modifier", dmgd, 0, 100).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER).setFormatting(format);
        mindamage = configCreator.createDouble("Min damage", mdmg, 0, 100).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER).setFormatting(format);
        horizontalRecoilMultiplier = configCreator.createDouble("Horizontal recoil multiplier", 1.0, 0.0, 5.0).setFormatting(format);
        verticalRecoilMultiplier = configCreator.createDouble("Vertical recoil multiplier", 1.0, 0.0, 5.0).setFormatting(format);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setFloat("horizontal", horizontalRecoilMultiplier.getAsFloat());
        c.setFloat("vertical", verticalRecoilMultiplier.getAsFloat());
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        horizontalRecoilMultiplier.set((double) nbt.getFloat("horizontal"));
        verticalRecoilMultiplier.set((double) nbt.getFloat("vertical"));
    }

    @Override
    public float getDamage() {
        return damage.getAsFloat();
    }

    @Override
    public float getVelocity() {
        return velocity.getAsFloat();
    }

    @Override
    public float getGravityModifier() {
        return gravityModifier.getAsFloat();
    }

    @Override
    public int getGravityEffectStart() {
        return gravityEffectStart.get();
    }

    @Override
    public int getDamagedropEffectStart() {
        return damagedropEffectStart.get();
    }

    @Override
    public float getDamagedropModifier() {
        return damagedropModifier.getAsFloat();
    }

    @Override
    public float getMinDamage() {
        return mindamage.getAsFloat();
    }

    @Override
    public float getVerticalRecoil() {
        return verticalRecoilMultiplier.getAsFloat();
    }

    @Override
    public float getHorizontalRecoil() {
        return horizontalRecoilMultiplier.getAsFloat();
    }
}
