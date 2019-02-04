package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.handlers.ConfigHandler;

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
		this.setDamage(ConfigHandler.mk14);
		this.setVelocity(11);
		this.setGravityModifier(0.020);
		this.setGravityStartTime(8);
		this.setAmmoType(AmmoType.AMMO762);
		this.setReloadTime(39);
		this.setReloadDelay(15);
		this.setFireRate(2);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.DMR);
		this.setHorizontalRecoil(3.25f);
		this.setVerticalRecoil(7.5f);
		this.canSwitchMode(true);
		this.setBurstFire(false);
		this.setAutoFiremode(true);
		
		this.setGunSound(PMCSounds.gun_mk14);
		this.setGunSilencedSound(PMCSounds.gun_mk14_silenced);
		this.setGunSoundVolume(12f);
		this.setGunSilencedSoundVolume(9f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		if(stack.hasTagCompound())
		{
			if(stack.getTagCompound().getInteger("magazine") > 1)
			{
				return 20;
			}
			
			else
			{
				return 10;
			}
		}
		
		else return 10;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addSniperAttachments();
		attachments.add(PMCItems.CHEEKPAD);
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_mk14;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 45));
		rec.add(new ItemStack(Items.IRON_INGOT, 25));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 5));
		return rec;
	}
}
