package com.toma.pubgmc.common.items.guns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.common.items.guns.GunBase.Firemode;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import com.toma.pubgmc.common.items.guns.GunBase.ReloadType;
import com.toma.pubgmc.common.items.guns.attachments.ItemAttachment;
import com.toma.pubgmc.event.GunRegisterEvent;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GunBuilder 
{
	String name;
	int gravityStart, reloadTime, firerate, maxAmmo, exMaxAmmo;
	float damage, vertical, horizontal, volumeNormal, volumeSilenced;
	double velocity, gravity;
	boolean twoRoundBurst;
	GunType weaponType;
	ReloadType reloadType;
	AmmoType ammoType;
	Firemode defFiremode;
	Firemode[] validFiremodes;
	SoundEvent reloadSound, shootNormal, shootSilenced;
	ItemAttachment[] barrelAttachments, gripAttachments, magazineAttachments, stockAttachments, scopeAttachments;
	final List<ItemStack> craftingRecipe = new ArrayList<ItemStack>();
	
	@SideOnly(Side.CLIENT)
	ModelGun model;
	
	private GunBuilder() {}
	
	public static GunBuilder create()
	{
		GunBuilder builder = new GunBuilder();
		builder.init();
		return builder;
	}
	
	private void init()
	{
		barrelAttachments = new ItemAttachment[0];
		gripAttachments = new ItemAttachment[0];
		magazineAttachments = new ItemAttachment[0];
		stockAttachments = new ItemAttachment[0];
		scopeAttachments = new ItemAttachment[0];
		
		defFiremode = Firemode.SINGLE;
		validFiremodes = new Firemode[] {Firemode.SINGLE};
		weaponType = GunType.PISTOL;
		reloadType = ReloadType.MAGAZINE;
	}
	
	public GunBuilder name(String name)
	{
		this.name = name;
		return this;
	}
	
	public GunBuilder damage(float damage)
	{
		this.damage = damage;
		return this;
	}
	
	public GunBuilder velocity(double velocity)
	{
		this.velocity = velocity;
		return this;
	}
	
	public GunBuilder gravity(double gravityEffect, int startTime)
	{
		this.gravity = gravityEffect;
		this.gravityStart = startTime;
		return this;
	}
	
	public GunBuilder recoil(float vertical, float horizontal)
	{
		this.vertical = vertical;
		this.horizontal = horizontal;
		return this;
	}
	
	public GunBuilder reload(ReloadType typeOfReload, int reloadTime, SoundEvent reloadSound)
	{
		this.reloadType = typeOfReload;
		this.reloadTime = reloadTime;
		this.reloadSound = reloadSound;
		return this;
	}
	
	public GunBuilder firerate(int firerate)
	{
		this.firerate = firerate;
		return this;
	}
	
	public GunBuilder ammo(AmmoType ammoType, int maxAmmo, int exMaxAmmo)
	{
		this.ammoType = ammoType;
		this.maxAmmo = maxAmmo;
		this.exMaxAmmo = exMaxAmmo;
		return this;
	}
	
	public GunBuilder ammo(AmmoType ammoType, int maxAmmo)
	{
		this.ammoType = ammoType;
		this.maxAmmo = maxAmmo;
		this.exMaxAmmo = maxAmmo;
		return this;
	}
	
	public GunBuilder firemode(Firemode defaultFiremode, Firemode... validFiremodes)
	{
		this.defFiremode = defaultFiremode;
		this.validFiremodes = validFiremodes;
		return this;
	}
	
	public GunBuilder setTwoRoundBurst()
	{
		this.twoRoundBurst = true;
		return this;
	}
	
	public GunBuilder weaponType(GunType type)
	{
		this.weaponType = type;
		return this;
	}
	
	public GunBuilder sound(SoundEvent shootNormal, float volume, SoundEvent shootSilenced, float silencedVolume)
	{
		this.shootNormal = shootNormal;
		this.volumeNormal = volume;
		this.shootSilenced = shootSilenced;
		this.volumeSilenced = silencedVolume;
		return this;
	}
	
	public GunBuilder sound(SoundEvent normal, float volume)
	{
		this.shootNormal = normal;
		this.volumeNormal = volume;
		this.shootSilenced = normal;
		this.volumeSilenced = volume;
		return this;
	}
	
	public GunBuilder craftRecipe(ItemStack... stacks)
	{
		craftingRecipe.clear();
		
		for(ItemStack s : stacks)
			craftingRecipe.add(s);
		return this;
	}
	
	public GunBuilder barrel(ItemAttachment... attachments)
	{
		this.barrelAttachments = attachments;
		return this;
	}
	
	public GunBuilder grip(ItemAttachment... attachments)
	{
		this.gripAttachments = attachments;
		return this;
	}
	
	public GunBuilder magazine(ItemAttachment... attachments)
	{
		this.magazineAttachments = attachments;
		return this;
	}
	
	public GunBuilder stock(ItemAttachment... attachments)
	{
		this.stockAttachments = attachments;
		return this;
	}
	
	public GunBuilder scope(ItemAttachment... attachments)
	{
		this.scopeAttachments = attachments;
		return this;
	}
	
	public GunBuilder model(ModelGun model)
	{
		this.model = model;
		return this;
	}
	
	public GunBase build()
	{
		//TODO: validate
		
		GunBase gun = new GunBase(this.name);
		gun.setDamage(damage);
		gun.setVelocity(velocity);
		gun.setGravityModifier(gravity);
		gun.setGravityStartTime(gravityStart);
		gun.setVerticalRecoil(vertical);
		gun.setHorizontalRecoil(horizontal);
		gun.setReloadType(reloadType);
		gun.setReloadTime(reloadTime);
		gun.setFireRate(firerate);
		gun.setAmmoType(ammoType);
		gun.setMaxAmmo(maxAmmo, exMaxAmmo);
		gun.setFiremode(defFiremode);
		gun.setValidFiremodes(validFiremodes);
		gun.setHasTwoRoundBurst(twoRoundBurst);
		gun.setGunType(weaponType);
		gun.setGunSound(shootNormal);
		gun.setGunSoundVolume(volumeNormal);
		gun.setGunSilencedSound(shootSilenced);
		gun.setGunSilencedSoundVolume(volumeSilenced);
		gun.setReloadSound(reloadSound);
		gun.setBarrelAttachments(barrelAttachments);
		gun.setGripAttachments(gripAttachments);
		gun.setMagazineAttachments(magazineAttachments);
		gun.setStockAttachments(stockAttachments);
		gun.setScopeAttachments(scopeAttachments);
		gun.setCraftingRecipe(craftingRecipe);
		return gun;
	}
}
