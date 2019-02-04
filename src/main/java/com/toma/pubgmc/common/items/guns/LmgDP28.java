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

public class LmgDP28 extends GunBase
{

	public LmgDP28(String name) 
	{
		super(name);
		this.setDamage(ConfigHandler.dp28);
		this.setVelocity(9);
		this.setGravityModifier(0.03);
		this.setGravityStartTime(6);
		this.setAmmoType(AmmoType.AMMO762);
		this.setReloadTime(95);
		this.setReloadDelay(15);
		this.setFireRate(2);
		this.setFiremode(Firemode.AUTO);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.LMG);
		this.setHorizontalRecoil(2.5f);
		this.setVerticalRecoil(4.5f);
		this.canSwitchMode(false);
		this.setBurstFire(false);
		this.setAutoFiremode(true);
		
		this.setGunSound(PMCSounds.gun_dp28);
		this.setGunSoundVolume(10f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return 47;
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
		return PMCSounds.reload_dp28;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 50));
		rec.add(new ItemStack(Items.IRON_INGOT, 50));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 3));
		rec.add(new ItemStack(Blocks.PLANKS, 10));
		return rec;
	}
}
