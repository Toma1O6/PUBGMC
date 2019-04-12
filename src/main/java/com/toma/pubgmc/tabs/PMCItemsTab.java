package com.toma.pubgmc.tabs;

import com.toma.pubgmc.init.PMCRegistry;

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
		return new ItemStack(PMCRegistry.Items.MINI14);
	}
}
