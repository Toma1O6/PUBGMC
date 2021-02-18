package dev.toma.pubgmc.util.recipes;

import dev.toma.pubgmc.common.tileentity.IInventoryTileEntity;

public interface ICraftingInventory extends IInventoryTileEntity {

    int getOutputSlot();
}
