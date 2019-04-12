package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class ArBerylM762 extends GunBase
{

	public ArBerylM762(String name) 
	{
		super(name);
		this.setDamage(cfg.m762);
		this.setVelocity(9);
		this.setGravityModifier(0.025);
		this.setGravityStartTime(6);
		this.setAmmoType(AmmoType.AMMO762);
		this.setReloadTime(50);
		this.setReloadDelay(15);
		this.setFireRate(2);
		this.setFiremode(Firemode.AUTO);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.AR);
		this.setHorizontalRecoil(2.0f);
		this.setVerticalRecoil(4f);
		this.canSwitchMode(true);
		setValidFiremodes(Firemode.SINGLE, Firemode.BURST, Firemode.AUTO);
		
		this.setGunSound(PMCSounds.gun_m762);
		this.setGunSilencedSound(PMCSounds.gun_m762_silenced);
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
		return PMCSounds.reload_m762;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCRegistry.Items.STEEL_INGOT, 35));
		rec.add(new ItemStack(Items.IRON_INGOT, 40));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 1));
		return rec;
	}
}
