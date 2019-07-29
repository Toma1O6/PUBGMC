package com.toma.pubgmc.util;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.RequiresWorldRestart;

public final class VehicleConfiguration {
    @Name("Max Health")
    @Comment("Vehicle maximum health")
    @RangeDouble(min = 1.0D, max = 1500.0D)
    @RequiresWorldRestart
    public float maxHealth = 250.0F;

    @Name("Max Speed")
    @Comment("Vehicle max speed")
    @RangeDouble(min = 0.5D, max = 3.0D)
    @RequiresWorldRestart
    public float maxSpeed = 1.6F;

    @Name("Acceleration")
    @Comment({"Vehicle speed acceleration", "This also applies for braking, which is Acceleration*2"})
    @RangeDouble(min = 0.001D, max = 1.0D)
    @RequiresWorldRestart
    public float acceleration = 0.015F;

    @Name("Turning Speed")
    @Comment("Turning angle increase per tick")
    @RangeDouble(min = 0.1D, max = 1.0D)
    @RequiresWorldRestart
    public float turningSpeed = 0.3F;

    @Name("Max Turning Angle")
    @Comment("Maximal angle at which can vehicle turn")
    @RangeDouble(min = 1.0D, max = 10.0D)
    public float maxTurningAngle = 3.0F;

    public VehicleConfiguration(float health, float speed, float angle, float acceleration, float turningAcceleration) {
        this.maxHealth = health;
        this.maxSpeed = speed;
        this.maxTurningAngle = angle;
        this.acceleration = acceleration;
        this.turningSpeed = turningAcceleration;
    }

    public static void writeBuffer(ByteBuf buf, VehicleConfiguration configuration) {
        buf.writeFloat(configuration.maxHealth);
        buf.writeFloat(configuration.maxSpeed);
        buf.writeFloat(configuration.acceleration);
        buf.writeFloat(configuration.turningSpeed);
        buf.writeFloat(configuration.maxTurningAngle);
    }

    public static VehicleConfiguration readBuffer(ByteBuf buf) {
        VehicleConfiguration cfg = new VehicleConfiguration(0f, 0f, 0f, 0f, 0f);
        cfg.maxHealth = buf.readFloat();
        cfg.maxSpeed = buf.readFloat();
        cfg.acceleration = buf.readFloat();
        cfg.turningSpeed = buf.readFloat();
        cfg.maxTurningAngle = buf.readFloat();
        return cfg;
    }
}
