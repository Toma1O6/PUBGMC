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

public class SmgVector extends GunBase
{	
	public SmgVector(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setDamage(ConfigHandler.vector);
		this.setVelocity(8.5);
		this.setGravityModifier(0.03);
		this.setGravityStartTime(7);
		this.setReloadTime(30);
		this.setAmmoType(AmmoType.AMMO45ACP);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setFireRate(1);
		this.canSwitchMode(true);
		this.setAutoFiremode(true);
		this.setBurstFire(true);
		this.setHasTwoRoundBurst(true);
		this.setGunType(GunType.SMG);
		this.setVerticalRecoil(2.5f);
		this.setHorizontalRecoil(1f);
		
		this.setGunSound(PMCSounds.gun_vector);
		this.setGunSilencedSound(PMCSounds.gun_vector_silenced);
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
				return 25;
			}
			
			else
			{
				return 13;
			}
		}
		
		else return 13;
	}
	
	@Override
	public boolean isAtachmentAccepted(Item at)
	{
		return at == PMCItems.QUICKDRAW_MAG_SMG || at == PMCItems.EXTENDED_MAG_SMG || at == PMCItems.EXTENDED_QUICKDRAW_MAG_SMG
				|| at == PMCItems.COMPENSATOR_SMG || at == PMCItems.SILENCER_SMG
				|| at == PMCItems.RED_DOT || at == PMCItems.HOLOGRAPHIC || at == PMCItems.SCOPE2X || at == PMCItems.SCOPE4X
				|| at == PMCItems.GRIP_VERTICAL;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_vector;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 25));
		rec.add(new ItemStack(Items.IRON_INGOT, 35));
		return rec;
	}
}
