package dev.toma.pubgmc.config.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.util.INBTSerializable;

public final class CFGVehicle implements INBTSerializable<NBTTagCompound> {

    @Config.Name("Max Health")
    @Config.Comment("Vehicle maximum health")
    @Config.RangeDouble(min = 1.0D, max = 1500.0D)
    public float maxHealth;

    @Config.Name("Max Speed")
    @Config.Comment("Vehicle max speed")
    @Config.RangeDouble(min = 0.5D, max = 3.0D)
    public float maxSpeed;

    @Config.Name("Acceleration")
    @Config.Comment({"Vehicle speed acceleration", "This also applies for braking, which is Acceleration*2"})
    @Config.RangeDouble(min = 0.001D, max = 1.0D)
    public float acceleration;

    @Config.Name("Turning Speed")
    @Config.Comment("Turning angle increase per tick")
    @Config.RangeDouble(min = 0.1D, max = 1.0D)
    public float turningSpeed;

    @Config.Name("Max Turning Angle")
    @Config.Comment("Maximal angle at which can vehicle turn")
    @Config.RangeDouble(min = 1.0D, max = 10.0D)
    public float maxTurningAngle;

    public CFGVehicle(float health, float speed, float angle, float acceleration, float turningAcceleration) {
        this.maxHealth = health;
        this.maxSpeed = speed;
        this.maxTurningAngle = angle;
        this.acceleration = acceleration;
        this.turningSpeed = turningAcceleration;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound c = new NBTTagCompound();
        c.setFloat("health", maxHealth);
        c.setFloat("speed", maxSpeed);
        c.setFloat("acceleration", acceleration);
        c.setFloat("turningSpeed", turningSpeed);
        c.setFloat("maxAngle", maxTurningAngle);
        return c;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        maxHealth = nbt.getFloat("health");
        maxSpeed = nbt.getFloat("speed");
        acceleration = nbt.getFloat("acceleration");
        turningSpeed = nbt.getFloat("turningSpeed");
        maxTurningAngle = nbt.getFloat("maxAngle");
    }
}
