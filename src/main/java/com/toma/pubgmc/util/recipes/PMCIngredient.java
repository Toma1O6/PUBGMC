package com.toma.pubgmc.util.recipes;

import net.minecraft.item.Item;

public final class PMCIngredient {

	public final int slotIndex;
	public final int count;
	private final Item ingredient;
	
	public PMCIngredient(int slotIndex, Item item, int count) {
		this.slotIndex = slotIndex;
		this.ingredient = item;
		this.count = count;
	}
	
	public Item getIngredient() {
		return ingredient;
	}
}
