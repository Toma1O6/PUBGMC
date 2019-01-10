package com.toma.pubgmc.tabs;

import com.toma.pubgmc.init.PMCItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PMCItemsTab extends CreativeTabs
{
	public PMCItemsTab(String label)
	{
		super("pmcitemstab");
	}
	
	public ItemStack getTabIconItem()
	{
		return new ItemStack(PMCItems.GRENADE);
	}  
}
