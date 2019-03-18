package com.toma.pubgmc.common.items.guns;

import com.toma.pubgmc.common.items.ItemAmmo;
import com.toma.pubgmc.init.PMCItems;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum AmmoType
{
	AMMO9MM("ammo.9mm"),
	AMMO45ACP("ammo.45acp"),
	AMMO12G("ammo.12g"),
	AMMO556("ammo.556mm"),
	AMMO762("ammo.762mm"),
	AMMO300M("ammo.300m"),
	FLARE("ammo.flare");
	
	private String name;
	
	private AmmoType(String name)
	{
		this.name = name;
	}
	
	@SideOnly(Side.CLIENT)
	public String translatedName()
	{
		return I18n.format(name);
	}
	
	public Item ammo()
	{
		switch(this)
		{
			case AMMO9MM: return PMCItems.AMMO_9MM;
			case AMMO45ACP: return PMCItems.AMMO_45ACP;
			case AMMO556: return PMCItems.AMMO_556;
			case AMMO762: return PMCItems.AMMO_762;
			case AMMO300M: return PMCItems.AMMO_300M;
			case AMMO12G: return PMCItems.AMMO_SHOTGUN;
			case FLARE: return PMCItems.AMMO_FLARE;
			default: return PMCItems.AMMO_9MM;
		}
	}
	
	public ItemStack ammoStack()
	{
		return new ItemStack(ammo());
	}
}
