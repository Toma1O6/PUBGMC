package dev.toma.pubgmc.init;

import dev.toma.pubgmc.config.ConfigPMC;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;

public class PMCDamageSources {

    public static final DamageSource ZONE = new DamageSource("zone");

    public static DamageSource vehicle(@Nullable Entity driver) {
        DamageSource source = new ByEntityDamageSource("vehicle", driver);
        if (ConfigPMC.common.world.damages.vehiclePenetration.get()) {
            source.setDamageBypassesArmor();
        }
        return source;
    }

    public static DamageSource molotov(@Nullable Entity thrower) {
        DamageSource source = new ByEntityDamageSource("molotov", thrower);
        if (ConfigPMC.common.world.damages.molotovPenetration.get()) {
            source.setDamageBypassesArmor();
        }
        return source;
    }

    public static DamageSource fuelcan(@Nullable Entity owner) {
        DamageSource source = new ByEntityDamageSource("fuelcan", owner);
        if (ConfigPMC.common.world.damages.fuelcanPenetration.get()) {
            source.setDamageBypassesArmor();
        }
        return source;
    }
}
