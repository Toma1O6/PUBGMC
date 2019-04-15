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

public class DmrSKS extends GunBase
{

	public DmrSKS(String name) 
	{
		super(name);
		this.setDamage(cfg.sks);
		this.setVelocity(10);
		this.setGravityModifier(0.035);
		this.setGravityStartTime(8);
		this.setAmmoType(AmmoType.AMMO762);
		this.setReloadTime(32);
		this.setFireRate(2);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.DMR);
		this.setHorizontalRecoil(1.25f);
		this.setVerticalRecoil(5.5f);
		this.canSwitchMode(false);
		
		this.setGunSound(PMCSounds.gun_sks);
		this.setGunSilencedSound(PMCSounds.gun_sks_silenced);
		this.setGunSoundVolume(12f);
		this.setGunSilencedSoundVolume(8f);
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
		addGrips();
		addAttachment(PMCRegistry.PMCItems.CHEEKPAD);
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_sks;
	}
	
	@Override
	public ModelGun getWeaponModel()
	{
		return ((WeaponTEISR)this.getTileEntityItemStackRenderer()).sks;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 50));
		rec.add(new ItemStack(Items.IRON_INGOT, 35));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 3));
		rec.add(new ItemStack(Blocks.STONE, 5));
		return rec;
	}
}
