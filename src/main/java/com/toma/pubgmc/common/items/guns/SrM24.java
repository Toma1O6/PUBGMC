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

public class SrM24 extends GunBase
{

	public SrM24(String name) 
	{
		super(name);
		this.setDamage(cfg.m24);
		this.setVelocity(11);
		this.setGravityModifier(0.035);
		this.setGravityStartTime(9);
		this.setAmmoType(AmmoType.AMMO762);
		this.setReloadTime(71);
		this.setReloadDelay(15);
		this.setFireRate(35);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.SR);
		this.setHorizontalRecoil(1.25f);
		this.setVerticalRecoil(4.5f);
		this.canSwitchMode(false);
		
		this.setGunSound(PMCSounds.gun_m24);
		this.setGunSoundVolume(15f);
		this.setGunSilencedSound(PMCSounds.gun_m24_silenced);
		this.setGunSilencedSoundVolume(12f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") > 1 ? 7 : 5;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addSniperAttachments();
		addAttachment(PMCRegistry.Items.CHEEKPAD);
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_m24;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCRegistry.Items.STEEL_INGOT, 60));
		rec.add(new ItemStack(Items.IRON_INGOT, 50));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 1));
		return rec;
	}
}
