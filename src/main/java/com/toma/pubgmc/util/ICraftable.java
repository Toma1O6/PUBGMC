package com.toma.pubgmc.util;

import java.util.List;

import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ICraftable
{
	public List<ItemStack> getCraftingRecipe();
	
	public CraftMode getCraftMode();
}
