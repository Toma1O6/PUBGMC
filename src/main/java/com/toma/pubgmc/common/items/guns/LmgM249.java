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

public class LmgM249 extends GunBase
{

	public LmgM249(String name) 
	{
		super(name);
		this.setDamage(ConfigHandler.m249);
		this.setVelocity(11);
		this.setGravityModifier(0.015);
		this.setGravityStartTime(7);
		this.setAmmoType(AmmoType.AMMO556);
		this.setReloadTime(148);
		this.setReloadDelay(20);
		this.setFireRate(2);
		this.setFiremode(Firemode.AUTO);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setGunType(GunType.LMG);
		this.setHorizontalRecoil(1f);
		this.setVerticalRecoil(3.5f);
		this.canSwitchMode(false);
		this.setBurstFire(false);
		this.setAutoFiremode(true);
		
		this.setGunSound(PMCSounds.gun_m249);
		this.setGunSoundVolume(10f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return 100;
	}
	
	@Override
	public boolean isAtachmentAccepted(Item attachment)
	{
		return ((ItemAttachment)attachment).getType() == Type.SCOPE && attachment != PMCItems.SCOPE15X && attachment != PMCItems.SCOPE8X;
	}
	
	@Override
	public SoundEvent getWeaponReloadSound()
	{
		return PMCSounds.reload_m249;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCItems.STEEL_INGOT, 60));
		rec.add(new ItemStack(Items.IRON_INGOT, 60));
		rec.add(new ItemStack(Blocks.IRON_BLOCK, 3));
		rec.add(new ItemStack(Blocks.STONE, 5));
		return rec;
	}
}
