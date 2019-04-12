package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class SmgVector extends GunBase
{	
	public SmgVector(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setDamage(cfg.vector);
		this.setVelocity(8);
		this.setGravityModifier(0.035);
		this.setGravityStartTime(5);
		this.setReloadTime(30);
		this.setAmmoType(AmmoType.AMMO9MM);
		this.setFiremode(Firemode.AUTO);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setFireRate(1);
		this.canSwitchMode(true);
		setValidFiremodes(Firemode.SINGLE, Firemode.BURST, Firemode.AUTO);
		this.setHasTwoRoundBurst(true);
		this.setGunType(GunType.SMG);
		this.setVerticalRecoil(2f);
		this.setHorizontalRecoil(1f);
		
		this.setGunSound(PMCSounds.gun_vector);
		this.setGunSilencedSound(PMCSounds.gun_vector_silenced);
		this.setGunSoundVolume(8f);
		this.setGunSilencedSoundVolume(4f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") > 1 ? 33 : 19;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addSMGAttachments();
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_vector;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCRegistry.Items.STEEL_INGOT, 25));
		rec.add(new ItemStack(Items.IRON_INGOT, 35));
		return rec;
	}
}
