package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class ArM416 extends GunBase
{

	public ArM416(String name) 
	{
		super(name);
		this.setDamage(cfg.m416);
		this.setVelocity(12);
		this.setGravityModifier(0.005);
		this.setGravityStartTime(8);
		this.setAmmoType(AmmoType.AMMO556);
		this.setReloadTime(66);
		this.setReloadDelay(15);
		this.setFireRate(2);
		this.setFiremode(Firemode.AUTO);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.AR);
		this.setHorizontalRecoil(1.25f);
		this.setVerticalRecoil(3.5f);
		this.canSwitchMode(true);
		setValidFiremodes(Firemode.SINGLE, Firemode.AUTO);
		
		this.setGunSound(PMCSounds.gun_m416);
		this.setGunSilencedSound(PMCSounds.gun_m416_silenced);
		this.setGunSoundVolume(10f);
		this.setGunSilencedSoundVolume(7f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") > 1 ? 40 : 30;
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
		return PMCSounds.reload_m416;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCRegistry.Items.STEEL_INGOT, 35));
		rec.add(new ItemStack(Items.IRON_INGOT, 50));
		return rec;
	}
}
