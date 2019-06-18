package com.toma.pubgmc.util.recipes;

import com.toma.pubgmc.common.tileentity.IInventoryTileEntity;

public interface ICraftingInventory extends IInventoryTileEntity {
	
	int getOutputSlot();
}
