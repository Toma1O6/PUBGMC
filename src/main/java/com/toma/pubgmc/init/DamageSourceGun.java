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
	private static final String[] DEATH_MESSAGES = {"shot", "killed", "shredded", "sniped", "obliterated"};
	private final Random rand = new Random();
	
	public DamageSourceGun(String damageType, Entity source, @Nullable Entity indirect, ItemStack weapon)
	{
		super(damageType, source, indirect);
		this.weapon = weapon;
		this.source = source;
		this.indirect = indirect;
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn)
	{
		TextComponentString message = null;
		GunBase gun = (GunBase)weapon.getItem();
		switch(gun.getGunType())
		{
			case PISTOL: 
			{
				int i = rand.nextInt(2);
				return message = new TextComponentString(source.getName() + " " + DEATH_MESSAGES[i] + " " + indirect.getName() + " using " + weapon.getDisplayName());
			}
			
			case SMG:
			{
				int i = rand.nextInt(2);
				return message = new TextComponentString(source.getName() + " " + DEATH_MESSAGES[i] + " " + indirect.getName() + " using " + weapon.getDisplayName());
			}
			
			case AR:
			{
				int i = rand.nextInt(2);
				return message = new TextComponentString(source.getName() + " " + DEATH_MESSAGES[i] + " " + indirect.getName() + " using " + weapon.getDisplayName());
			}
			
			case LMG: 
			{
				int i = rand.nextInt(3);
				return message = new TextComponentString(source.getName() + " " + DEATH_MESSAGES[i] + " " + indirect.getName() + " using " + weapon.getDisplayName());
			}
			
			case SHOTGUN: 
			{
				return message = new TextComponentString(source.getName() + " " + DEATH_MESSAGES[4] + " " + indirect.getName() + " using " + weapon.getDisplayName());
			}
			
			case SNIPER:
			{
				return message = new TextComponentString(source.getName() + " " + DEATH_MESSAGES[3] + " " + indirect.getName() + " using " + weapon.getDisplayName());
			}
			
			default: return message;
		}
	}
	
	@Override
	public Entity getTrueSource()
	{
		return source;
	}
}
