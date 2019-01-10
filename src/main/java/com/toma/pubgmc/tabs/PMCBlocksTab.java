package com.toma.pubgmc.tabs;

import com.toma.pubgmc.init.PMCBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PMCBlocksTab extends CreativeTabs 
{
	public PMCBlocksTab(String label)
	{
		super("pmcblockstab");
	}

	public ItemStack getTabIconItem()
	{
		return new ItemStack(PMCBlocks.CRATE);
	}
}
