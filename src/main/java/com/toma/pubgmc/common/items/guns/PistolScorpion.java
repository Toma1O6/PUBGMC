package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.ICraftable;
import com.toma.pubgmc.util.handlers.ConfigHandler;

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
		
		setDamage(ConfigHandler.scorpion);
		setVelocity(7);
		setGravityModifier(0.015f);
		setGravityStartTime(3);
		setFireRate(1);
		setReloadTime(50);
		setVerticalRecoil(1.3f);
		setHorizontalRecoil(0.25f);
		
		canSwitchMode(true);
		setAutoFiremode(true);
		setBurstFire(false);
		setReloadType(ReloadType.MAGAZINE);
		setFiremode(Firemode.SINGLE);
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
		attachments.add(PMCItems.SILENCER_PISTOL);
		attachments.add(PMCItems.GRIP_VERTICAL);
		attachments.add(PMCItems.EXTENDED_MAG_PISTOL);
		attachments.add(PMCItems.RED_DOT);
		return super.acceptedAttachments();
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item) 
	{
		List<ItemStack> r = new ArrayList<ItemStack>();
		r.add(new ItemStack(Items.IRON_INGOT, 30));
		r.add(new ItemStack(PMCItems.STEEL_INGOT, 25));
		return r;
	}
}
