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

public class DmrSKS extends GunBase
{

	public DmrSKS(String name) 
	{
		super(name);
		this.setDamage(ConfigHandler.sks);
		this.setVelocity(10);
		this.setGravityModifier(0.035);
		this.setGravityStartTime(8);
		this.setAmmoType(AmmoType.AMMO762);
		this.setReloadTime(32);
		this.setReloadDelay(15);
		this.setFireRate(3);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.SNIPER);
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
	public boolean isAtachmentAccepted(Item attachment)
	{
		return attachment == PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER || attachment == PMCItems.EXTENDED_MAG_SNIPER || attachment == PMCItems.QUICKDRAW_MAG_SNIPER
				|| ((ItemAttachment)attachment).getType() == Type.SCOPE
				|| attachment == PMCItems.COMPENSATOR_SNIPER || attachment == PMCItems.SILENCER_SNIPER
				|| ((ItemAttachment)attachment).getType() == Type.GRIP
				|| attachment == PMCItems.CHEEKPAD;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_sks;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 50));
		rec.add(new ItemStack(Items.IRON_INGOT, 35));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 3));
		rec.add(new ItemStack(Blocks.STONE, 5));
		return rec;
	}
}
