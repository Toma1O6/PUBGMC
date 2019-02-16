package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCItems;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class SmgBizon extends GunBase
{
	public SmgBizon(String name)
	{
		super(name);
		setMaxStackSize(1);
		
		setDamage(0);
		setVelocity(0);
		setGravityModifier(0);
		setGravityStartTime(0);
		setFireRate(2);
		setReloadTime(0);
		setVerticalRecoil(0);
		setHorizontalRecoil(0);
		
		canSwitchMode(true);
		setAutoFiremode(true);
		setBurstFire(false);
		setReloadType(ReloadType.MAGAZINE);
		setFiremode(Firemode.SINGLE);
		setAmmoType(AmmoType.AMMO9MM);
		setGunType(GunType.SMG);
		
		setGunSound(null);
		setGunSilencedSound(null);
		setGunSoundVolume(0);
		setGunSilencedSoundVolume(0);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return 53;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return null;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item) 
	{
		List<ItemStack> r = new ArrayList<ItemStack>();
		r.add(new ItemStack(Items.IRON_INGOT, 40));
		r.add(new ItemStack(PMCItems.STEEL_INGOT, 25));
		return r;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		attachments.add(PMCItems.COMPENSATOR_SMG);
		attachments.add(PMCItems.SILENCER_SMG);
		addCloseRangeScopes();
		return super.acceptedAttachments();
	}
}
