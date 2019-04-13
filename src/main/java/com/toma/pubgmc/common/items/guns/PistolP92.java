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

public class PistolP92 extends GunBase
{	
	public PistolP92(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		
		this.setDamage(cfg.p92);
		this.setVelocity(7);
		this.setGravityModifier(0.015);
		this.setGravityStartTime(3);
		this.setFireRate(2);
		this.setReloadTime(25);
		this.canSwitchMode(false);
		this.setVerticalRecoil(2.0f);
		this.setHorizontalRecoil(0.5f);
		
		this.setAmmoType(AmmoType.AMMO9MM);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.PISTOL);
		
		this.setGunSound(PMCSounds.gun_p92);
		this.setGunSoundVolume(5f);
		this.setGunSilencedSound(PMCSounds.gun_p92_silenced);
		this.setGunSilencedSoundVolume(4f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") > 1 ? 20 : 15;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addPistolAttachments();
		return super.acceptedAttachments();
	}
	
	@Override
	public ModelGun getWeaponModel()
	{
		return ((WeaponTEISR)this.getTileEntityItemStackRenderer()).p92;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<>();
		rec.add(new ItemStack(PMCRegistry.Items.STEEL_INGOT, 10));
		rec.add(new ItemStack(Items.IRON_INGOT, 15));
		rec.add(new ItemStack(Blocks.PLANKS, 1));
		return rec;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_p92;
	}
}
