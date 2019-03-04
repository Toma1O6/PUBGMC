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

public class PistolWin94 extends GunBase
{	
	public PistolWin94(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		
		this.setDamage(ConfigHandler.win94);
		this.setVelocity(15);
		this.setGravityModifier(0.008);
		this.setGravityStartTime(10);
		this.setReloadTime(15);
		this.setAmmoType(AmmoType.AMMO45ACP);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.SINGLE);
		this.setFireRate(25);
		this.canSwitchMode(false);
		this.setGunType(GunType.PISTOL);
		this.setVerticalRecoil(5.5f);
		this.setHorizontalRecoil(3.5f);
		
		this.setGunSound(PMCSounds.gun_win94);
		this.setGunSoundVolume(10f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack) 
	{
		return 8;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addAttachment(PMCItems.BULLET_LOOPS_SNIPER);
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_win94;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(Items.IRON_INGOT, 25));
		rec.add(new ItemStack(Blocks.PLANKS, 5));
		return rec;
	}
}
