package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.common.items.guns.GunBase.Firemode;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class ArAKM extends GunBase
{

	public ArAKM(String name) 
	{
		super(name);
		this.setDamage(cfg.akm);
		this.setVelocity(9);
		this.setGravityModifier(0.025);
		this.setGravityStartTime(6);
		this.setAmmoType(AmmoType.AMMO762);
		this.setReloadTime(60);
		this.setReloadDelay(15);
		this.setFireRate(2);
		this.setFiremode(Firemode.AUTO);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.AR);
		this.setHorizontalRecoil(2f);
		this.setVerticalRecoil(4f);
		this.canSwitchMode(true);
		setValidFiremodes(Firemode.SINGLE, Firemode.AUTO);
		
		this.setGunSound(PMCSounds.gun_akm);
		this.setGunSilencedSound(PMCSounds.gun_akm_silenced);
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
		addARAttachments(false);
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound() 
	{
		return PMCSounds.reload_akm;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCRegistry.Items.STEEL_INGOT, 35));
		rec.add(new ItemStack(Items.IRON_INGOT, 40));
		rec.add(new ItemStack(Blocks.PLANKS, 15));
		return rec;
	}
}
