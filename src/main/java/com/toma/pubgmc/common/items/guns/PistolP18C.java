package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.handlers.ConfigHandler;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class PistolP18C extends GunBase
{	
	public PistolP18C(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setDamage(ConfigHandler.p18c);
		this.setVelocity(7);
		this.setGravityModifier(0.01);
		this.setGravityStartTime(5);
		this.setReloadTime(34);
		this.setAmmoType(AmmoType.AMMO9MM);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setFireRate(1);
		this.canSwitchMode(true);
		this.setAutoFiremode(true);
		this.setGunType(GunType.PISTOL);
		this.setVerticalRecoil(1.5f);
		this.setHorizontalRecoil(1f);
		
		this.setGunSound(PMCSounds.gun_p18c);
		this.setGunSilencedSound(PMCSounds.gun_p18c_silenced);
		this.setGunSoundVolume(5f);
		this.setGunSilencedSoundVolume(4f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		if(stack.hasTagCompound())
		{
			if(stack.getTagCompound().getInteger("magazine") > 1)
			{
				return 25;
			}
			
			else
			{
				return 17;
			}
		}
		
		else return 17;
	}
	
	@Override
	public List<Item> acceptedAttachments()
	{
		addPistolAttachments();
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_p18c;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 8));
		rec.add(new ItemStack(Items.IRON_INGOT, 20));
		return rec;
	}
}
