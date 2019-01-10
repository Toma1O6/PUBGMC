package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.common.items.guns.attachments.ItemAttachment;
import com.toma.pubgmc.common.items.guns.attachments.IAttachment.Type;
import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.handlers.ConfigHandler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class SrAWM extends GunBase
{

	public SrAWM(String name) 
	{
		super(name);
		this.setDamage(ConfigHandler.awm);
		this.setVelocity(13);
		this.setGravityModifier(0.005);
		this.setGravityStartTime(10);
		this.setAmmoType(AmmoType.AMMO300M);
		this.setReloadTime(78);
		this.setReloadDelay(15);
		this.setFireRate(35);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.SNIPER);
		this.setHorizontalRecoil(1.25f);
		this.setVerticalRecoil(4.5f);
		this.canSwitchMode(false);
		
		this.setGunSound(PMCSounds.gun_awm);
		this.setGunSoundVolume(15f);
		this.setGunSilencedSound(PMCSounds.gun_awm_silenced);
		this.setGunSilencedSoundVolume(12f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		if(stack.hasTagCompound())
		{
			if(stack.getTagCompound().getInteger("magazine") > 1)
			{
				return 7;
			}
			
			else
			{
				return 5;
			}
		}
		
		else return 5;
	}
	
	@Override
	public boolean isAtachmentAccepted(Item attachment) 
	{
		return attachment == PMCItems.QUICKDRAW_MAG_SNIPER || attachment == PMCItems.EXTENDED_MAG_SNIPER || attachment == PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER
				|| attachment == PMCItems.COMPENSATOR_SNIPER || attachment == PMCItems.SILENCER_SNIPER
				|| ((ItemAttachment)attachment).getType() == Type.SCOPE
				|| attachment == PMCItems.CHEEKPAD;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_awm;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 50));
		rec.add(new ItemStack(Items.IRON_INGOT, 50));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 2));
		rec.add(new ItemStack(Items.DIAMOND, 3));
		return rec;
	}
}
