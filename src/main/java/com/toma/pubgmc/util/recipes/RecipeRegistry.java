package com.toma.pubgmc.util.recipes;

import java.util.HashSet;

public final class RecipeRegistry {

	private static final RecipeRegistry INSTANCE = new RecipeRegistry();
	public static final HashSet<PMCRecipe> RECIPES = new HashSet<>();
	
	public static RecipeRegistry instance() {
		return INSTANCE;
	}
	
	public void registerRecipe(PMCRecipe recipe) {
		RECIPES.add(recipe);
	}
	
	public void registerAll(PMCRecipe[] recipes) {
		for(PMCRecipe recipe : recipes) {
			registerRecipe(recipe);
		}
	}
}
