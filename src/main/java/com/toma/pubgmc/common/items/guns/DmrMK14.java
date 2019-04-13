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

public class DmrMK14 extends GunBase
{

	public DmrMK14(String name) 
	{
		super(name);
		this.setDamage(cfg.mk14);
		this.setVelocity(11);
		this.setGravityModifier(0.020);
		this.setGravityStartTime(8);
		this.setAmmoType(AmmoType.AMMO762);
		this.setReloadTime(39);
		this.setReloadDelay(15);
		this.setFireRate(2);
		this.setFiremode(Firemode.AUTO);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.DMR);
		this.setHorizontalRecoil(3.25f);
		this.setVerticalRecoil(7.5f);
		this.canSwitchMode(true);
		setValidFiremodes(Firemode.SINGLE, Firemode.AUTO);
		
		this.setGunSound(PMCSounds.gun_mk14);
		this.setGunSilencedSound(PMCSounds.gun_mk14_silenced);
		this.setGunSoundVolume(12f);
		this.setGunSilencedSoundVolume(9f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") > 1 ? 20 : 10;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addSniperAttachments();
		addAttachment(PMCRegistry.Items.CHEEKPAD);
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_mk14;
	}
	
	@Override
	public ModelGun getWeaponModel()
	{
		return ((WeaponTEISR)this.getTileEntityItemStackRenderer()).mk14;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCRegistry.Items.STEEL_INGOT, 45));
		rec.add(new ItemStack(Items.IRON_INGOT, 25));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 5));
		return rec;
	}
}
