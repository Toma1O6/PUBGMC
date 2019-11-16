package com.toma.pubgmc.util.game.loot;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface ILootSpawner {

    NonNullList<ItemStack> getInventory();

    boolean isAirdropContainer();
}
