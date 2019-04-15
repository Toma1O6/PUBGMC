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

public class DmrQBU extends GunBase
{

	public DmrQBU(String name) 
	{
		super(name);
		this.setDamage(cfg.qbu);
		this.setVelocity(12);
		this.setGravityModifier(0.015);
		this.setGravityStartTime(6);
		this.setAmmoType(AmmoType.AMMO556);
		this.setReloadTime(44);
		this.setFireRate(1);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.DMR);
		this.setHorizontalRecoil(2.25f);
		this.setVerticalRecoil(3.5f);
		this.canSwitchMode(false);
		
		this.setGunSound(PMCSounds.gun_qbu);
		this.setGunSilencedSound(PMCSounds.gun_qbu_silenced);
		this.setGunSoundVolume(12f);
		this.setGunSilencedSoundVolume(8f);
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addSniperAttachments();
		return super.acceptedAttachments();
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") > 1 ? 20 : 10;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_qbu;
	}
	
	@Override
	public ModelGun getWeaponModel()
	{
		return ((WeaponTEISR)this.getTileEntityItemStackRenderer()).qbu;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 50));
		rec.add(new ItemStack(Items.IRON_INGOT, 35));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 3));
		rec.add(new ItemStack(Blocks.PLANKS, 5));
		return rec;
	}
}
