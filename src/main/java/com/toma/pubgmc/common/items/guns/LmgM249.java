package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.renderer.WeaponTEISR;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class LmgM249 extends GunBase
{

	public LmgM249(String name) 
	{
		super(name);
		this.setDamage(cfg.m249);
		this.setVelocity(11);
		this.setGravityModifier(0.015);
		this.setGravityStartTime(7);
		this.setAmmoType(AmmoType.AMMO556);
		this.setReloadTime(148);
		this.setFireRate(2);
		this.setFiremode(Firemode.AUTO);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.LMG);
		this.setHorizontalRecoil(1f);
		this.setVerticalRecoil(3.5f);
		this.canSwitchMode(false);
		setValidFiremodes(Firemode.AUTO);
		
		this.setGunSound(PMCSounds.gun_m249);
		this.setGunSoundVolume(10f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return 100;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addCloseRangeScopes();
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_m249;
	}
	
	@Override
	public ModelGun getWeaponModel()
	{
		return ((WeaponTEISR)this.getTileEntityItemStackRenderer()).m249;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 60));
		rec.add(new ItemStack(Items.IRON_INGOT, 60));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 3));
		rec.add(new ItemStack(Blocks.STONE, 5));
		return rec;
	}
}
