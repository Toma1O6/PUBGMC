package com.toma.pubgmc.util.recipes;

import com.toma.pubgmc.common.tileentity.IInventoryTileEntity;

import net.minecraft.inventory.IInventory;

public interface ICraftingInventory extends IInventoryTileEntity {
	
	int getOutputSlot();
}
