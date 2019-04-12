package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class PistolP18C extends GunBase
{	
	public PistolP18C(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setDamage(cfg.p18c);
		this.setVelocity(7);
		this.setGravityModifier(0.01);
		this.setGravityStartTime(5);
		this.setReloadTime(34);
		this.setAmmoType(AmmoType.AMMO9MM);
		this.setFiremode(Firemode.AUTO);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setFireRate(1);
		this.canSwitchMode(true);
		setValidFiremodes(Firemode.SINGLE, Firemode.AUTO);
		this.setGunType(GunType.PISTOL);
		this.setVerticalRecoil(1.5f);
		this.setHorizontalRecoil(1f);
		
		this.setGunSound(PMCSounds.gun_p18c);
		this.setGunSilencedSound(PMCSounds.gun_p18c_silenced);
		this.setGunSoundVolume(5f);
		this.setGunSilencedSoundVolume(4f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") > 1 ? 25 : 17;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addPistolAttachments();
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_p18c;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<>();
		rec.add(new ItemStack(PMCRegistry.Items.STEEL_INGOT, 8));
		rec.add(new ItemStack(Items.IRON_INGOT, 20));
		return rec;
	}
}
