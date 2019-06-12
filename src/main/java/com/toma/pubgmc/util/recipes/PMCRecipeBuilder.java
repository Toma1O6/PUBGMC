package com.toma.pubgmc.util.recipes;

import java.util.ArrayList;

import com.google.common.base.Preconditions;
import com.toma.pubgmc.util.recipes.PMCRecipe.CraftingCategory;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class PMCRecipeBuilder {
	
	private Item result = null;
	private ArrayList<PMCIngredient> ingredients = new ArrayList<>();
	private CraftingCategory category;
	private int slotIndex = 0;
	
	private PMCRecipeBuilder() {}
	
	public static PMCRecipeBuilder createGun() {
		return create(CraftingCategory.GUNS);
	}
	
	public static PMCRecipeBuilder createAmmo() {
		return create(CraftingCategory.AMMO);
	}
	
	private static PMCRecipeBuilder create(CraftingCategory category) {
		PMCRecipeBuilder builder = new PMCRecipeBuilder();
		builder.category = category;
		return builder;
	}
	
	public PMCRecipeBuilder result(Item item) {
		this.result = item;
		return this;
	}
	
	public PMCRecipeBuilder ingredient(Item item, int amount) {
		ingredients.add(new PMCIngredient(slotIndex, item, amount));
		++slotIndex;
		return this;
	}
	
	public PMCRecipeBuilder ingredient(Block block, int amount) {
		ingredients.add(new PMCIngredient(slotIndex, block, amount));
		++slotIndex;
		return this;
	}
	
	public PMCRecipeBuilder ingredient(Item item, int amount, int meta) {
		ingredients.add(new PMCIngredient(slotIndex, item, amount, meta));
		++slotIndex;
		return this;
	}
	
	public PMCRecipe build() {
		Preconditions.checkNotNull(result);
		Preconditions.checkNotNull(category);
		Preconditions.checkState(!ingredients.isEmpty());
		Preconditions.checkState(result != null && result != Items.AIR);
		PMCIngredient[] ingredient = ingredients.toArray(new PMCIngredient[0]);
		return new PMCRecipe(result, ingredient, category);
	}
}
