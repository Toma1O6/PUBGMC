package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.DoubleType;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.configuration.api.util.NumberDisplayType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.text.DecimalFormat;

public final class CFGWeapon extends ObjectType implements INBTSerializable<NBTTagCompound> {

    public DoubleType damage;
    public DoubleType velocity;
    public DoubleType gravityModifier;
    public IntType gravityEffectStart;
    public DoubleType horizontalRecoilMultiplier;
    public DoubleType verticalRecoilMultiplier;

    float dmg, vel, grav;
    int t;

    public CFGWeapon(String name, float damage, float velocity, float gravity, int time) {
        super(name);
        this.dmg = damage;
        this.vel = velocity;
        this.grav = gravity;
        this.t = time;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        DecimalFormat format = new DecimalFormat("#.###");
        damage = configCreator.createDouble("Damage", dmg, 1, 100).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER).setFormatting(format);
        velocity = configCreator.createDouble("Velocity", vel, 0.1, 50.0, "Velocity applied to bullet each tick").setFormatting(format);
        gravityModifier = configCreator.createDouble("Gravity modifier", grav, 0, 0.2).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER).setFormatting(format);
        gravityEffectStart = configCreator.createInt("Gravity effect delay", t, 0, Integer.MAX_VALUE, "Ticks before gravity effect is applied on bullet");
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
}
