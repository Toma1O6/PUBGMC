package com.toma.pubgmc.util.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PMCRecipe {
	
	public final Item result;
	public final PMCIngredient[] ingredients;
	
	protected PMCRecipe(Item result, PMCIngredient[] ingredients) {
		this.result = result;
		this.ingredients = ingredients;
	}
	
	public static boolean isRecipeReady(PMCRecipe recipe, ICraftingInventory inv) {
		if(recipe == null || recipe.ingredients.length == 0) {
			return false;
		}
		for(PMCIngredient ingredient : recipe.ingredients) {
			ItemStack stack = inv.getStackInSlot(ingredient.slotIndex);
			if(stack.isEmpty() || stack.getItem() != ingredient.getIngredient().getItem() || stack.getCount() < ingredient.count) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean areSameRecipes(PMCRecipe r1, PMCRecipe r2) {
		if(r1.ingredients.length != r2.ingredients.length) {
			return false;
		} else {
			for(int i = 0; i < r1.ingredients.length; i++) {
				PMCIngredient i1 = r1.ingredients[i];
				PMCIngredient i2 = r2.ingredients[i];
				if(i1.getIngredient() == i2.getIngredient()) {
					if(i1.slotIndex == i2.slotIndex) {
						if(i1.count == i2.count) {
							return true;
						}
					}
				}
			}
			return false;
		}
	}
}
