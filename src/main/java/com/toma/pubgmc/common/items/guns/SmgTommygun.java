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

public class SmgTommygun extends GunBase
{	
	public SmgTommygun(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setDamage(ConfigHandler.tommygun);
		this.setVelocity(8.5);
		this.setGravityModifier(0.02);
		this.setGravityStartTime(5);
		this.setReloadTime(60);
		this.setAmmoType(AmmoType.AMMO45ACP);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setFireRate(2);
		this.canSwitchMode(true);
		this.setAutoFiremode(true);
		this.setGunType(GunType.SMG);
		this.setVerticalRecoil(2f);
		this.setHorizontalRecoil(0.75f);
		
		this.setGunSound(PMCSounds.gun_tommy_gun);
		this.setGunSilencedSound(PMCSounds.gun_tommy_gun_silenced);
		this.setGunSoundVolume(8f);
		this.setGunSilencedSoundVolume(4f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		if(stack.hasTagCompound())
		{
			if(stack.getTagCompound().getInteger("magazine") > 1)
			{
				return 50;
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
		addMagazines();
		attachments.add(PMCItems.GRIP_VERTICAL);
		attachments.add(PMCItems.SILENCER_SMG);
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_tommygun;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 15));
		rec.add(new ItemStack(Items.IRON_INGOT, 25));
		rec.add(new ItemStack(Blocks.PLANKS, 3));
		return rec;
	}
}
