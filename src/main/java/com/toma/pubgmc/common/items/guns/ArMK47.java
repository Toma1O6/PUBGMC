package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.handlers.ConfigHandler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class ArMK47 extends GunBase
{

	public ArMK47(String name) 
	{
		super(name);
		this.setDamage(ConfigHandler.mk47);
		this.setVelocity(9);
		this.setGravityModifier(0.025);
		this.setGravityStartTime(6);
		this.setAmmoType(AmmoType.AMMO762);
		this.setReloadTime(66);
		this.setReloadDelay(15);
		this.setFireRate(2);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.AR);
		this.setHorizontalRecoil(1.75f);
		this.setVerticalRecoil(3.5f);
		this.canSwitchMode(true);
		this.setBurstFire(true);
		this.setHasTwoRoundBurst(true);
		this.setAutoFiremode(false);
		
		this.setGunSound(PMCSounds.gun_mk47);
		this.setGunSilencedSound(PMCSounds.gun_mk47_silenced);
		this.setGunSoundVolume(10f);
		this.setGunSilencedSoundVolume(7f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		if(stack.hasTagCompound())
		{
			if(stack.getTagCompound().getInteger("magazine") > 1)
			{
				return 30;
			}
			
			else
			{
				return 20;
			}
		}
		
		else return 20;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addARAttachments(true);
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_mk47;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 35));
		rec.add(new ItemStack(Items.IRON_INGOT, 35));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 1));
		return rec;
	}
}
