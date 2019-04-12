package com.toma.pubgmc.common.items.guns;

import com.toma.pubgmc.init.PMCRegistry;

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
			case AMMO9MM: return PMCRegistry.Items.AMMO_9MM;
			case AMMO45ACP: return PMCRegistry.Items.AMMO_45ACP;
			case AMMO556: return PMCRegistry.Items.AMMO_556;
			case AMMO762: return PMCRegistry.Items.AMMO_762;
			case AMMO300M: return PMCRegistry.Items.AMMO_300M;
			case AMMO12G: return PMCRegistry.Items.AMMO_SHOTGUN;
			case FLARE: return PMCRegistry.Items.AMMO_FLARE;
			default: return PMCRegistry.Items.AMMO_9MM;
		}
	}
	
	public ItemStack ammoStack()
	{
		return new ItemStack(ammo());
	}
}
