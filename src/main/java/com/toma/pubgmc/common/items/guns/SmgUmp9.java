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

public class SmgUmp9 extends GunBase
{	
	public SmgUmp9(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setDamage(ConfigHandler.ump9);
		this.setVelocity(8.5);
		this.setGravityModifier(0.02);
		this.setGravityStartTime(5);
		this.setReloadTime(52);
		this.setAmmoType(AmmoType.AMMO9MM);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setFireRate(2);
		this.canSwitchMode(true);
		this.setBurstFire(true);
		this.setHasTwoRoundBurst(true);
		this.setAutoFiremode(true);
		this.setGunType(GunType.SMG);
		this.setVerticalRecoil(1.75f);
		this.setHorizontalRecoil(1f);
		
		this.setGunSound(PMCSounds.gun_ump9);
		this.setGunSilencedSound(PMCSounds.gun_ump9_silenced);
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
	public List<Item> acceptedAttachments()
	{
		addSMGAttachments();
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_ump9;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 25));
		rec.add(new ItemStack(Items.IRON_INGOT, 40));
		return rec;
	}
}
