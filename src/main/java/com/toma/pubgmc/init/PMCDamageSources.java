package com.toma.pubgmc.init;

import net.minecraft.util.DamageSource;

public class PMCDamageSources {

    public static final DamageSource VEHICLE = new DamageSource("vehicle").setDamageBypassesArmor();
    public static final DamageSource ZONE = new DamageSource("zone").setDamageBypassesArmor();
}
