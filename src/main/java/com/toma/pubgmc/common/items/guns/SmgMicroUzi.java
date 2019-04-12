package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class SmgMicroUzi extends GunBase
{	
	public SmgMicroUzi(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setDamage(cfg.microuzi);
		this.setVelocity(8.5);
		this.setGravityModifier(0.02);
		this.setGravityStartTime(5);
		this.setReloadTime(56);
		this.setAmmoType(AmmoType.AMMO9MM);
		this.setFiremode(Firemode.AUTO);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setFireRate(1);
		this.canSwitchMode(true);
		setValidFiremodes(Firemode.SINGLE, Firemode.AUTO);
		this.setGunType(GunType.SMG);
		this.setVerticalRecoil(2f);
		this.setHorizontalRecoil(1f);
		
		this.setGunSound(PMCSounds.gun_micro_uzi);
		this.setGunSilencedSound(PMCSounds.gun_micro_uzi_silenced);
		this.setGunSoundVolume(8f);
		this.setGunSilencedSoundVolume(4f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") > 1 ? 35 : 25;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addMagazines();
		addAttachment(PMCRegistry.Items.SILENCER_SMG);
		addAttachment(PMCRegistry.Items.COMPENSATOR_SMG);
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_microuzi;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCRegistry.Items.STEEL_INGOT, 15));
		rec.add(new ItemStack(Items.IRON_INGOT, 40));
		return rec;
	}
}
