package dev.toma.pubgmc.config.common;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.DoubleType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.configuration.api.util.NumberDisplayType;

import java.text.DecimalFormat;

public final class CFGVehicle extends ObjectType {

    public DoubleType maxHealth;
    public DoubleType maxSpeed;
    public DoubleType acceleration;
    public DoubleType turningSpeed;
    public DoubleType maxTurningAngle;

    float mh;
    float ms;
    float ac;
    float ts;
    float mt;

    public CFGVehicle(String name, float health, float speed, float angle, float acceleration, float turningAcceleration) {
        super(name);
        this.mh = health;
        this.ms = speed;
        this.ac = acceleration;
        this.ts = turningAcceleration;
        this.mt = angle;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        DecimalFormat format = new DecimalFormat("#.####");
        maxHealth = configCreator.createDouble("Health", mh, 1.0, 1000.0, "Set max health").setFormatting(format);
        maxSpeed = configCreator.createDouble("Speed", ms, 0.5, 3.0, "Set max speed").setFormatting(format).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER);
        acceleration = configCreator.createDouble("Acceleration", ac, 0.001, 1.0, "Set acceleration").setFormatting(format).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER);
        turningSpeed = configCreator.createDouble("Turn speed", ts, 0.1, 1.0, "Set turning speed").setFormatting(format).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER);
        maxTurningAngle = configCreator.createDouble("Max turning angle", mt, 1.0, 5.0, "Set max turning angle").setFormatting(format).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER);
    }
}
