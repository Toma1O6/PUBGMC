package com.toma.pubgmc.common.items;

import com.toma.pubgmc.Pubgmc;

import net.minecraft.item.Item;

public class PMCItem extends Item
{
	public PMCItem(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Pubgmc.pmcitemstab);
	}
}
