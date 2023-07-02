package dev.toma.pubgmc.api.game.loot;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public interface LootableContainer {

    BlockPos getWorldPosition();

    int getSize();

    ItemStack getItemStackInSlot(int index);

    void setItemStackToSlot(int index, ItemStack stack);

    void onLootContentsChanged();
}
