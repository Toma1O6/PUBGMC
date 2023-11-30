package dev.toma.pubgmc.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;

public class PMCDamageSources {

    public static final DamageSource ZONE = new DamageSource("zone").setDamageBypassesArmor();

    public static DamageSource vehicle(@Nullable Entity driver) {
        return new ByEntityDamageSource("vehicle", driver).setDamageBypassesArmor();
    }

    public static DamageSource molotov(@Nullable Entity thrower) {
        return new ByEntityDamageSource("molotov", thrower).setDamageBypassesArmor();
    }
}
