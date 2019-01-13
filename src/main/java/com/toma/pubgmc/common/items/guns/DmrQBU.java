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

public class DmrQBU extends GunBase
{

	public DmrQBU(String name) 
	{
		super(name);
		this.setDamage(ConfigHandler.qbu);
		this.setVelocity(12);
		this.setGravityModifier(0.015);
		this.setGravityStartTime(6);
		this.setAmmoType(AmmoType.AMMO556);
		this.setReloadTime(44);
		this.setReloadDelay(10);
		this.setFireRate(3);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.SNIPER);
		this.setHorizontalRecoil(2.25f);
		this.setVerticalRecoil(3.5f);
		this.canSwitchMode(false);
		
		this.setGunSound(PMCSounds.gun_qbu);
		this.setGunSilencedSound(PMCSounds.gun_qbu_silenced);
		this.setGunSoundVolume(12f);
		this.setGunSilencedSoundVolume(8f);
	}
	
	@Override
	public boolean isAtachmentAccepted(Item attachment)
	{
		return attachment == PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER || attachment == PMCItems.EXTENDED_MAG_SNIPER || attachment == PMCItems.QUICKDRAW_MAG_SNIPER
				|| ((ItemAttachment)attachment).getType() == Type.SCOPE
				|| attachment == PMCItems.COMPENSATOR_SNIPER || attachment == PMCItems.SILENCER_SNIPER;
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
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_qbu;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 50));
		rec.add(new ItemStack(Items.IRON_INGOT, 35));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 3));
		rec.add(new ItemStack(Blocks.PLANKS, 5));
		return rec;
	}
}
