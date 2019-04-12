package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.common.items.guns.GunBase.Firemode;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class PistolScorpion extends GunBase
{
	public PistolScorpion(String name)
	{
		super(name);
		setMaxStackSize(1);
		
		setDamage(cfg.scorpion);
		setVelocity(7);
		setGravityModifier(0.015f);
		setGravityStartTime(3);
		setFireRate(1);
		setReloadTime(57);
		setVerticalRecoil(1.3f);
		setHorizontalRecoil(0.25f);
		
		canSwitchMode(true);
		setValidFiremodes(Firemode.SINGLE, Firemode.AUTO);
		setReloadType(ReloadType.MAGAZINE);
		setFiremode(Firemode.AUTO);
		setAmmoType(AmmoType.AMMO9MM);
		setGunType(GunType.PISTOL);
		
		setGunSound(PMCSounds.gun_scorpion);
		setGunSilencedSound(PMCSounds.gun_scorpion_silenced);
		setGunSoundVolume(5f);
		setGunSilencedSoundVolume(4f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") == 2 ? 40 : 20;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_scorpion;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addAttachment(PMCRegistry.Items.SILENCER_PISTOL);
		addAttachment(PMCRegistry.Items.GRIP_VERTICAL);
		addAttachment(PMCRegistry.Items.EXTENDED_MAG_PISTOL);
		addAttachment(PMCRegistry.Items.RED_DOT);
		return super.acceptedAttachments();
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item) 
	{
		List<ItemStack> r = new ArrayList<ItemStack>();
		r.add(new ItemStack(Items.IRON_INGOT, 30));
		r.add(new ItemStack(PMCRegistry.Items.STEEL_INGOT, 25));
		return r;
	}
}
