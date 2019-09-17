package com.toma.pubgmc.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class PMCDamageSources extends DamageSource {

    public static final DamageSource VEHICLE = new DamageSource("vehicle").setDamageBypassesArmor();

    private Entity ent;

    private PMCDamageSources(String type, Entity damagedEntity) {
        super(type);
        this.ent = damagedEntity;
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
        TextComponentString message = new TextComponentString("%s has been killed by wild vehicle!");
        return message;
    }
}
