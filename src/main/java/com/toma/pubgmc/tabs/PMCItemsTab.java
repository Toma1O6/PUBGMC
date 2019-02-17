package com.toma.pubgmc.tabs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.toma.pubgmc.common.items.PMCItem;
import com.toma.pubgmc.init.PMCItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PMCItemsTab extends CreativeTabs
{
	public PMCItemsTab(String label)
	{
		super("pmcitemstab");
	}
	
	public ItemStack getTabIconItem()
	{
		return new ItemStack(PMCItems.MINI14);
	}
}
