package com.toma.pubgmc.init;

import com.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.Random;

public class DamageSourceGun extends EntityDamageSourceIndirect {
    private static final String[] HS_MESSAGES = new String[]{"headshotted", "shot right into head", "blew head of"};
    private static final String[] DEATH_MESSAGES = {"shot", "killed", "shredded", "sniped", "obliterated"};
    private final Random rand = new Random();
    private ItemStack weapon;
    private Entity source;
    private Entity indirect;
    private boolean headshot;

    public DamageSourceGun(String damageType, Entity source, @Nullable Entity indirect, ItemStack weapon, boolean headshot) {
        super(damageType, source, indirect);
        this.weapon = weapon;
        this.source = source;
        this.indirect = indirect;
        this.headshot = headshot;
    }

    private String[] getMessageType() {
        return headshot ? HS_MESSAGES : DEATH_MESSAGES;
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
        GunBase gun = (GunBase) weapon.getItem();
        String[] s = getMessageType();
        int i = rand.nextInt(s.length);
        return new TextComponentString(source.getName() + " " + s[i] + " " + indirect.getName() + " using " + weapon.getDisplayName());
    }

    @Nullable
    public ItemStack getGun() {
        return weapon.getItem() instanceof GunBase ? weapon : ItemStack.EMPTY;
    }

    public boolean wasHeadshot() {
        return headshot;
    }

    @Override
    public Entity getTrueSource() {
        return source;
    }
}
