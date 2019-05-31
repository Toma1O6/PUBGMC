package com.toma.pubgmc.util.recipes;

import java.util.ArrayList;

import com.google.common.base.Preconditions;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class PMCRecipeBuilder {
	
	private Item result = null;
	private ArrayList<PMCIngredient> ingredients = new ArrayList<>();
	
	private PMCRecipeBuilder() {}
	
	public static PMCRecipeBuilder create() {
		return new PMCRecipeBuilder();
	}
	
	public PMCRecipeBuilder result(Item item) {
		this.result = item;
		return this;
	}
	
	public PMCRecipeBuilder ingredient(Item item, int slot, int amount) {
		ingredients.add(new PMCIngredient(slot, item, amount));
		return this;
	}
	
	public PMCRecipe build() {
		Preconditions.checkNotNull(result);
		Preconditions.checkState(!ingredients.isEmpty());
		Preconditions.checkState(result != null && result != Items.AIR);
		PMCIngredient[] ingredient = ingredients.toArray(new PMCIngredient[0]);
		return new PMCRecipe(result, ingredient);
	}
}
