package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class ArScarL extends GunBase
{

	public ArScarL(String name) 
	{
		super(name);
		this.setDamage(cfg.scarl);
		this.setVelocity(11);
		this.setGravityModifier(0.0075);
		this.setGravityStartTime(7);
		this.setAmmoType(AmmoType.AMMO556);
		this.setReloadTime(65);
		this.setReloadDelay(10);
		this.setFireRate(2);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.AR);
		this.setHorizontalRecoil(1f);
		this.setVerticalRecoil(2.5f);
		this.canSwitchMode(true);
		this.setAutoFiremode(true);
		
		this.setGunSound(PMCSounds.gun_scarl);
		this.setGunSilencedSound(PMCSounds.gun_scarl_silenced);
		this.setGunSoundVolume(10f);
		this.setGunSilencedSoundVolume(7f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		if(stack.hasTagCompound())
		{
			if(stack.getTagCompound().getInteger("magazine") > 1)
			{
				return 40;
			}
			
			else
			{
				return 30;
			}
		}
		
		else return 30;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addARAttachments(true);
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_scarl;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 35));
		rec.add(new ItemStack(Items.IRON_INGOT, 50));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 1));
		return rec;
	}
}
