package com.toma.pubgmc.init;

import java.util.Random;

import javax.annotation.Nullable;

import com.toma.pubgmc.common.items.guns.GunBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class DamageSourceGun extends EntityDamageSourceIndirect
{
	private ItemStack weapon;
	private Entity source;
	private Entity indirect;
	private static final String[] HS_MESSAGES = new String[] {"headshotted", "shot right into head", "blew head of"};
	private static final String[] DEATH_MESSAGES = {"shot", "killed", "shredded", "sniped", "obliterated"};
	private final Random rand = new Random();
	private boolean headshot;
	
	public DamageSourceGun(String damageType, Entity source, @Nullable Entity indirect, ItemStack weapon, boolean headshot)
	{
		super(damageType, source, indirect);
		this.weapon = weapon;
		this.source = source;
		this.indirect = indirect;
		this.headshot = headshot;
	}
	
	private String[] getMessageType()
	{
		return headshot ? HS_MESSAGES : DEATH_MESSAGES;
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn)
	{
		TextComponentString message = null;
		GunBase gun = (GunBase)weapon.getItem();
		String[] s = getMessageType();
		int i = rand.nextInt(s.length);
		return message = new TextComponentString(source.getName() + " " + s[i] + " " + indirect.getName() + " using " + weapon.getDisplayName());
	}
	
	@Override
	public Entity getTrueSource()
	{
		return source;
	}
}
