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

public class DmrSLR extends GunBase
{

	public DmrSLR(String name) 
	{
		super(name);
		this.setDamage(ConfigHandler.slr);
		this.setVelocity(10);
		this.setGravityModifier(0.035);
		this.setGravityStartTime(8);
		this.setAmmoType(AmmoType.AMMO762);
		this.setReloadTime(53);
		this.setReloadDelay(15);
		this.setFireRate(4);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.DMR);
		this.setHorizontalRecoil(2.25f);
		this.setVerticalRecoil(5.5f);
		this.canSwitchMode(false);
		
		this.setGunSound(PMCSounds.gun_slr);
		this.setGunSilencedSound(PMCSounds.gun_slr_silenced);
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
				|| attachment == PMCItems.CHEEKPAD;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_slr;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 60));
		rec.add(new ItemStack(Items.IRON_INGOT, 35));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 1));
		return rec;
	}
}
