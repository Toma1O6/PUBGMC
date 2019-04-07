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

public class PistolP1911 extends GunBase
{	
	public PistolP1911(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setDamage(cfg.p1911);
		this.setVelocity(7.25);
		this.setGravityModifier(0.01);
		this.setGravityStartTime(5);
		this.setReloadTime(25);
		this.setAmmoType(AmmoType.AMMO45ACP);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setFireRate(2);
		this.canSwitchMode(false);
		this.setGunType(GunType.PISTOL);
		this.setVerticalRecoil(2.0f);
		this.setHorizontalRecoil(0.5f);
		
		this.setGunSound(PMCSounds.gun_p1911);
		this.setGunSilencedSound(PMCSounds.gun_p1911_silenced);
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
				return 12;
			}
			
			else
			{
				return 7;
			}
		}
		
		else return 7;
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
		return PMCSounds.reload_p1911;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 12));
		rec.add(new ItemStack(Items.IRON_INGOT, 20));
		rec.add(new ItemStack(Blocks.STONE, 1));
		return rec;
	}
}
