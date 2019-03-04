package com.toma.pubgmc.common.items.guns;

import com.toma.pubgmc.common.items.ItemAmmo;
import com.toma.pubgmc.init.PMCItems;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum AmmoType
{
	AMMO9MM(I18n.format("ammo.9mm")),
	AMMO45ACP(I18n.format("ammo.45acp")),
	AMMO12G(I18n.format("ammo.12g")),
	AMMO556(I18n.format("ammo.556mm")),
	AMMO762(I18n.format("ammo.762mm")),
	AMMO300M(I18n.format("ammo.300m")),
	FLARE(I18n.format("ammo.flare"));
	
	private String name;
	
	private AmmoType(String name)
	{
		this.name = name;
	}
	
	public String translatedName()
	{
		return name;
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
