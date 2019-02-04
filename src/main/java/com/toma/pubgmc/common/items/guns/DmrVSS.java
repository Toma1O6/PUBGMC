package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.handlers.ConfigHandler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class DmrVSS extends GunBase
{

	public DmrVSS(String name) 
	{
		super(name);
		this.setDamage(ConfigHandler.vss);
		this.setVelocity(7);
		this.setGravityModifier(0.035);
		this.setGravityStartTime(2);
		this.setAmmoType(AmmoType.AMMO9MM);
		this.setReloadTime(40);
		this.setReloadDelay(10);
		this.setFireRate(2);
		this.setFiremode(Firemode.SINGLE);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.DMR);
		this.setHorizontalRecoil(0.5f);
		this.setVerticalRecoil(1.5f);
		this.canSwitchMode(true);
		this.setBurstFire(false);
		this.setAutoFiremode(true);
		
		this.setGunSound(PMCSounds.gun_vss);
		this.setGunSoundVolume(2f);
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
	public List<Item> acceptedAttachments()
	{
		addMagazines();
		attachments.add(PMCItems.CHEEKPAD);
		return super.acceptedAttachments();
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_vss;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 30));
		rec.add(new ItemStack(Items.IRON_INGOT, 35));
		rec.add(new ItemStack(Blocks.PLANKS, 20));
		return rec;
	}
}
