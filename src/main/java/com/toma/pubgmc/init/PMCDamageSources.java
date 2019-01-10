package com.toma.pubgmc.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class PMCDamageSources extends DamageSource
{
	public static final DamageSource WEAPON_GENERIC = new DamageSource("genericWeapon");
	public static final DamageSource VEHICLE = new DamageSource("vehicle");
	
	private Entity ent;
	
	private PMCDamageSources(String type, Entity damagedEntity)
	{
		super(type);
		this.ent = damagedEntity;
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn)
	{
		TextComponentString message = new TextComponentString("");
		DamageSource dmgSrc = entityLivingBaseIn.getLastDamageSource();
		
		if(ent instanceof EntityPlayer)
		{
			if(dmgSrc == PMCDamageSources.WEAPON_GENERIC)
			{
				message = new TextComponentString(ent.getName() + " has been killed."); 
			}
			
			else if(dmgSrc == PMCDamageSources.VEHICLE)
			{
				message = new TextComponentString(ent.getName() + " has been run over by a vehicle.");
			}
		}
		
		return message;
	}
}
