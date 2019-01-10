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

public class PistolR45 extends GunBase
{

	public PistolR45(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setDamage(ConfigHandler.r45);
		this.setVelocity(7.75);
		this.setGravityModifier(0.01);
		this.setGravityStartTime(5);
		this.setReloadTime(40);
		this.setAmmoType(AmmoType.AMMO45ACP);
		this.setFireRate(15);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.canSwitchMode(false);
		this.setGunType(GunType.PISTOL);
		this.setVerticalRecoil(2.0f);
		this.setHorizontalRecoil(2.5f);
		
		this.setGunSound(PMCSounds.gun_r45);
		this.setGunSoundVolume(5f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return 6;
	}
	
	@Override
	public boolean isAtachmentAccepted(Item attachment)
	{
		return attachment == PMCItems.RED_DOT;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_r45;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 18));
		rec.add(new ItemStack(Items.IRON_INGOT, 5));
		rec.add(new ItemStack(Blocks.PLANKS, 2));
		return rec;
	}
}
