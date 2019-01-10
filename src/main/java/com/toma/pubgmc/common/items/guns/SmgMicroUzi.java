package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.PMCItemBlock;
import com.toma.pubgmc.util.handlers.ConfigHandler;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class SmgMicroUzi extends GunBase
{	
	public SmgMicroUzi(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setDamage(ConfigHandler.microuzi);
		this.setVelocity(8.5);
		this.setGravityModifier(0.02);
		this.setGravityStartTime(5);
		this.setReloadTime(56);
		this.setAmmoType(AmmoType.AMMO9MM);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setFireRate(1);
		this.canSwitchMode(true);
		this.setAutoFiremode(true);
		this.setGunType(GunType.SMG);
		this.setVerticalRecoil(1.5f);
		this.setHorizontalRecoil(1f);
		
		this.setGunSound(PMCSounds.gun_micro_uzi);
		this.setGunSilencedSound(PMCSounds.gun_micro_uzi_silenced);
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
				return 35;
			}
			
			else
			{
				return 25;
			}
		}
		
		else return 25;
	}
	
	@Override
	public boolean isAtachmentAccepted(Item at)
	{
		return at == PMCItems.QUICKDRAW_MAG_SMG || at == PMCItems.EXTENDED_MAG_SMG || at == PMCItems.EXTENDED_QUICKDRAW_MAG_SMG
				|| at == PMCItems.COMPENSATOR_SMG || at == PMCItems.SILENCER_SMG;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_microuzi;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 15));
		rec.add(new ItemStack(Items.IRON_INGOT, 40));
		return rec;
	}
}
