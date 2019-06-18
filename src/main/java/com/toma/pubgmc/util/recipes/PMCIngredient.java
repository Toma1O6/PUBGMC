package com.toma.pubgmc.util.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class PMCIngredient {

	public final int slotIndex;
	private final ItemStack ingredient;
	
	public PMCIngredient(int slotIndex, Item item, int count) {
		this.slotIndex = slotIndex;
		this.ingredient = new ItemStack(item, count);
	}
	
	public PMCIngredient(int slotIndex, Block block, int count) {
		this.slotIndex = slotIndex;
		this.ingredient = new ItemStack(block, count);
	}
	
	public PMCIngredient(int slotIndex, Item item, int count, int meta) {
		this.slotIndex = slotIndex;
		this.ingredient = new ItemStack(item, count, meta);
	}
	
	public PMCIngredient(int slotIndex, Block block, int count, int meta) {
		this.slotIndex = slotIndex;
		this.ingredient = new ItemStack(block, count, meta);
	}
	
	public ItemStack getIngredient() {
		return ingredient.copy();
	}
}
