package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.renderer.WeaponTEISR;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class SmgUmp45 extends GunBase
{	
	public SmgUmp45(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setDamage(cfg.ump9);
		this.setVelocity(8.75);
		this.setGravityModifier(0.02);
		this.setGravityStartTime(5);
		this.setReloadTime(52);
		this.setAmmoType(AmmoType.AMMO45ACP);
		this.setFiremode(Firemode.AUTO);
		this.setReloadType(ReloadType.MAGAZINE);
		this.setFireRate(2);
		this.canSwitchMode(true);
		setValidFiremodes(Firemode.SINGLE, Firemode.BURST, Firemode.AUTO);
		this.setHasTwoRoundBurst(true);
		this.setGunType(GunType.SMG);
		this.setVerticalRecoil(1.9f);
		this.setHorizontalRecoil(1.2f);
		
		this.setGunSound(PMCSounds.gun_ump9);
		this.setGunSilencedSound(PMCSounds.gun_ump9_silenced);
		this.setGunSoundVolume(8f);
		this.setGunSilencedSoundVolume(4f);
	}
	
	@Override
	public int getWeaponAmmoLimit(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") > 1 ? 35 : 25;
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
	public ModelGun getWeaponModel()
	{
		return ((WeaponTEISR)this.getTileEntityItemStackRenderer()).ump;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		rec.add(new ItemStack(PMCRegistry.Items.STEEL_INGOT, 25));
		rec.add(new ItemStack(Items.IRON_INGOT, 40));
		return rec;
	}
}
