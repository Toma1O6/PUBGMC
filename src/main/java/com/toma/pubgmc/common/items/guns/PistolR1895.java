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

public class PistolR1895 extends GunBase
{

	public PistolR1895(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setDamage(cfg.r1895);
		this.setVelocity(7.5);
		this.setGravityModifier(0.01);
		this.setGravityStartTime(5);
		this.setReloadTime(14);
		this.setAmmoType(AmmoType.AMMO762);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.SINGLE);
		this.setFireRate(13);
		this.canSwitchMode(false);
		this.setGunType(GunType.PISTOL);
		this.setVerticalRecoil(2.5f);
		this.setHorizontalRecoil(1.5f);
		
		this.setGunSound(PMCSounds.gun_r1895);
		this.setGunSilencedSound(PMCSounds.gun_r1895_silenced);
		this.setGunSoundVolume(5f);
		this.setGunSilencedSoundVolume(4f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return 7;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addAttachment(PMCRegistry.PMCItems.SILENCER_PISTOL);
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_r1895;
	}
	
	@Override
	public ModelGun getWeaponModel()
	{
		return ((WeaponTEISR)this.getTileEntityItemStackRenderer()).r1895;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<>();
		rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 20));
		rec.add(new ItemStack(Items.IRON_INGOT, 5));
		rec.add(new ItemStack(Blocks.PLANKS, 2));
		return rec;
	}
}
