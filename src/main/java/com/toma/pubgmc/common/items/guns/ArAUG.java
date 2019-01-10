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

public class ArAUG extends GunBase
{

	public ArAUG(String name) 
	{
		super(name);
		this.setDamage(ConfigHandler.aug);
		this.setVelocity(11.5);
		this.setGravityModifier(0.007);
		this.setGravityStartTime(8);
		this.setAmmoType(AmmoType.AMMO556);
		this.setReloadTime(69);
		this.setReloadDelay(10);
		this.setFireRate(2);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.AR);
		this.setHorizontalRecoil(1.25f);
		this.setVerticalRecoil(3.5f);
		this.canSwitchMode(true);
		this.setAutoFiremode(true);
		
		this.setGunSound(PMCSounds.gun_aug);
		this.setGunSilencedSound(PMCSounds.gun_aug_silenced);
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
	public boolean isAtachmentAccepted(Item attachment)
	{
		return attachment == PMCItems.EXTENDED_QUICKDRAW_MAG_AR || attachment == PMCItems.EXTENDED_MAG_AR || attachment == PMCItems.QUICKDRAW_MAG_AR
				|| (((ItemAttachment)attachment).getType() == Type.SCOPE && attachment != PMCItems.SCOPE8X && attachment != PMCItems.SCOPE15X)
				|| attachment == PMCItems.COMPENSATOR_AR || attachment == PMCItems.SILENCER_AR
				|| ((ItemAttachment)attachment).getType() == Type.GRIP;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_aug;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 35));
		rec.add(new ItemStack(Items.IRON_INGOT, 40));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 1));
		return rec;
	}
}
