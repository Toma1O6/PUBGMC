package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.common.items.guns.attachments.IAttachment.Type;
import com.toma.pubgmc.common.items.guns.attachments.ItemAttachment;
import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.handlers.ConfigHandler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class SrKar98K extends GunBase
{

	public SrKar98K(String name) 
	{
		super(name);
		this.setDamage(ConfigHandler.kar98k);
		this.setVelocity(11);
		this.setGravityModifier(0.04);
		this.setGravityStartTime(8);
		this.setAmmoType(AmmoType.AMMO762);
		this.setReloadTime(63);
		this.setReloadDelay(15);
		this.setFireRate(30);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.KAR98K);
		this.setGunType(GunType.SR);
		this.setHorizontalRecoil(1.25f);
		this.setVerticalRecoil(4.5f);
		this.canSwitchMode(false);
		
		this.setGunSound(PMCSounds.gun_kar98k);
		this.setGunSoundVolume(15f);
		this.setGunSilencedSound(PMCSounds.gun_kar98k_silenced);
		this.setGunSilencedSoundVolume(12f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack) 
	{
		return 5;
	}
	
	@Override
	public boolean isAtachmentAccepted(Item attachment)
	{
		return ((ItemAttachment)attachment).getType() == Type.SCOPE
				|| attachment == PMCItems.COMPENSATOR_SNIPER || attachment == PMCItems.SILENCER_SNIPER
				|| attachment == PMCItems.BULLET_LOOPS_SNIPER || attachment == PMCItems.CHEEKPAD;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_kar98k;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 50));
		rec.add(new ItemStack(Items.IRON_INGOT, 40));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 1));
		rec.add(new ItemStack(Blocks.PLANKS, 15));
		return rec;
	}
}
