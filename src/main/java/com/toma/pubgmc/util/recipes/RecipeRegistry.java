package com.toma.pubgmc.util.recipes;

import java.util.ArrayList;
import java.util.HashSet;

import com.google.common.collect.Lists;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.init.PMCRegistry.PMCItems;
import com.toma.pubgmc.util.recipes.PMCRecipe.CraftingCategory;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public final class RecipeRegistry {

	public static final HashSet<PMCRecipe> RECIPES = new HashSet<>();
	
	public static void registerWorkbenchRecipes() {
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.FLARE_GUN)
				.ingredient(Items.IRON_INGOT, 15)
				.ingredient(PMCItems.STEEL_INGOT, 10)
				.ingredient(Items.DYE, 10, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.P92)
				.ingredient(Items.IRON_INGOT, 15)
				.ingredient(PMCItems.STEEL_INGOT, 10)
				.ingredient(Blocks.PLANKS, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.P1911)
				.ingredient(Items.IRON_INGOT, 20)
				.ingredient(PMCItems.STEEL_INGOT, 12)
				.ingredient(Blocks.STONE, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.P18C)
				.ingredient(Items.IRON_INGOT, 20)
				.ingredient(PMCItems.STEEL_INGOT, 8)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.R1895)
				.ingredient(Items.IRON_INGOT, 5)
				.ingredient(PMCItems.STEEL_INGOT, 20)
				.ingredient(Blocks.PLANKS, 2)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.R45)
				.ingredient(Items.IRON_INGOT, 5)
				.ingredient(PMCItems.STEEL_INGOT, 18)
				.ingredient(Blocks.PLANKS, 2)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.SCORPION)
				.ingredient(Items.IRON_INGOT, 30)
				.ingredient(PMCItems.STEEL_INGOT, 25)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.WIN94)
				.ingredient(Items.IRON_INGOT, 25)
				.ingredient(Blocks.PLANKS, 5)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.SAWED_OFF)
				.ingredient(Items.IRON_INGOT, 10)
				.ingredient(PMCItems.STEEL_INGOT, 25)
				.ingredient(Blocks.PLANKS, 7)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.S1897)
				.ingredient(Items.IRON_INGOT, 15)
				.ingredient(PMCItems.STEEL_INGOT, 25)
				.ingredient(Blocks.PLANKS, 10)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.S686)
				.ingredient(Items.IRON_INGOT, 20)
				.ingredient(PMCItems.STEEL_INGOT, 20)
				.ingredient(Blocks.PLANKS, 10)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.S12K)
				.ingredient(Items.IRON_INGOT, 40)
				.ingredient(PMCItems.STEEL_INGOT, 35)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.MICROUZI)
				.ingredient(Items.IRON_INGOT, 40)
				.ingredient(PMCItems.STEEL_INGOT, 15)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.VECTOR)
				.ingredient(Items.IRON_INGOT, 35)
				.ingredient(PMCItems.STEEL_INGOT, 25)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.BIZON)
				.ingredient(Items.IRON_INGOT, 40)
				.ingredient(PMCItems.STEEL_INGOT, 25)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.TOMMY_GUN)
				.ingredient(Items.IRON_INGOT, 25)
				.ingredient(PMCItems.STEEL_INGOT, 15)
				.ingredient(Blocks.PLANKS, 3)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.UMP45)
				.ingredient(Items.IRON_INGOT, 40)
				.ingredient(PMCItems.STEEL_INGOT, 25)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.M16A4)
				.ingredient(Items.IRON_INGOT, 35)
				.ingredient(PMCItems.STEEL_INGOT, 50)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.M416)
				.ingredient(Items.IRON_INGOT, 50)
				.ingredient(PMCItems.STEEL_INGOT, 35)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.SCAR_L)
				.ingredient(Items.IRON_INGOT, 50)
				.ingredient(PMCItems.STEEL_INGOT, 35)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.QBZ)
				.ingredient(Items.IRON_INGOT, 50)
				.ingredient(PMCItems.STEEL_INGOT, 35)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.G36C)
				.ingredient(Items.IRON_INGOT, 50)
				.ingredient(PMCItems.STEEL_INGOT, 35)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.AUG)
				.ingredient(Items.IRON_INGOT, 50)
				.ingredient(PMCItems.STEEL_INGOT, 40)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.AKM)
				.ingredient(Items.IRON_INGOT, 50)
				.ingredient(PMCItems.STEEL_INGOT, 40)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.BERYL_M762)
				.ingredient(Items.IRON_INGOT, 50)
				.ingredient(PMCItems.STEEL_INGOT, 40)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.MK47_MUTANT)
				.ingredient(Items.IRON_INGOT, 50)
				.ingredient(PMCItems.STEEL_INGOT, 40)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.GROZA)
				.ingredient(Items.IRON_INGOT, 50)
				.ingredient(PMCItems.STEEL_INGOT, 40)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.DP28)
				.ingredient(Items.IRON_INGOT, 50)
				.ingredient(PMCItems.STEEL_INGOT, 50)
				.ingredient(Blocks.IRON_BLOCK, 3)
				.ingredient(Blocks.PLANKS, 10)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.M249)
				.ingredient(Items.IRON_INGOT, 60)
				.ingredient(PMCItems.STEEL_INGOT, 60)
				.ingredient(Blocks.IRON_BLOCK, 3)
				.ingredient(Blocks.STONE, 5)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.VSS)
				.ingredient(Items.IRON_INGOT, 35)
				.ingredient(PMCItems.STEEL_INGOT, 30)
				.ingredient(Blocks.PLANKS, 20)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.MINI14)
				.ingredient(Items.IRON_INGOT, 35)
				.ingredient(PMCItems.STEEL_INGOT, 50)
				.ingredient(Blocks.PLANKS, 5)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.QBU)
				.ingredient(Items.IRON_INGOT, 35)
				.ingredient(PMCItems.STEEL_INGOT, 50)
				.ingredient(Blocks.PLANKS, 5)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.SKS)
				.ingredient(Items.IRON_INGOT, 35)
				.ingredient(PMCItems.STEEL_INGOT, 50)
				.ingredient(Blocks.STONE, 5)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.SLR)
				.ingredient(Items.IRON_INGOT, 40)
				.ingredient(PMCItems.STEEL_INGOT, 60)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.MK14)
				.ingredient(Items.IRON_INGOT, 40)
				.ingredient(PMCItems.STEEL_INGOT, 60)
				.ingredient(Blocks.STONE, 5)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.KAR98K)
				.ingredient(Items.IRON_INGOT, 40)
				.ingredient(PMCItems.STEEL_INGOT, 50)
				.ingredient(Blocks.IRON_BLOCK, 1)
				.ingredient(Blocks.PLANKS, 15)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.M24)
				.ingredient(Items.IRON_INGOT, 50)
				.ingredient(PMCItems.STEEL_INGOT, 60)
				.ingredient(Blocks.IRON_BLOCK, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createGun()
				.result(PMCItems.AWM)
				.ingredient(Items.IRON_INGOT, 50)
				.ingredient(PMCItems.STEEL_INGOT, 50)
				.ingredient(Items.DIAMOND, 5)
				.ingredient(Blocks.IRON_BLOCK, 2)
				.build());
		
		// Ammo
		registerRecipe(PMCRecipeBuilder.createAmmo()
				.result(PMCItems.AMMO_FLARE)
				.ingredient(Items.DIAMOND, 5)
				.ingredient(Items.IRON_INGOT, 1)
				.ingredient(Items.GUNPOWDER, 10)
				.ingredient(Items.BLAZE_POWDER, 3)
				.ingredient(Items.DYE, 5, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createAmmo()
				.result(PMCItems.AMMO_9MM)
				.ingredient(Items.GOLD_NUGGET, 5)
				.ingredient(PMCItems.COPPER_INGOT, 1)
				.ingredient(PMCItems.STEEL_INGOT, 1)
				.ingredient(Items.GUNPOWDER, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createAmmo()
				.result(PMCItems.AMMO_SHOTGUN)
				.ingredient(Items.GOLD_NUGGET, 10)
				.ingredient(Items.IRON_NUGGET, 8)
				.ingredient(PMCItems.STEEL_INGOT, 1)
				.ingredient(Items.GUNPOWDER, 3)
				.build());
		registerRecipe(PMCRecipeBuilder.createAmmo()
				.result(PMCItems.AMMO_45ACP)
				.ingredient(Items.GOLD_NUGGET, 5)
				.ingredient(Items.IRON_NUGGET, 5)
				.ingredient(PMCItems.STEEL_INGOT, 1)
				.ingredient(Items.GUNPOWDER, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createAmmo()
				.result(PMCItems.AMMO_556)
				.ingredient(Items.GOLD_NUGGET, 10)
				.ingredient(Items.IRON_NUGGET, 10)
				.ingredient(PMCItems.COPPER_INGOT, 1)
				.ingredient(Items.GUNPOWDER, 2)
				.build());
		registerRecipe(PMCRecipeBuilder.createAmmo()
				.result(PMCItems.AMMO_762)
				.ingredient(Items.GOLD_NUGGET, 15)
				.ingredient(Items.IRON_NUGGET, 10)
				.ingredient(PMCItems.STEEL_INGOT, 1)
				.ingredient(Items.GUNPOWDER, 2)
				.build());
		registerRecipe(PMCRecipeBuilder.createAmmo()
				.result(PMCItems.AMMO_300M)
				.ingredient(Items.GOLD_INGOT, 3)
				.ingredient(Items.IRON_INGOT, 3)
				.ingredient(PMCItems.COPPER_INGOT, 2)
				.ingredient(PMCItems.STEEL_INGOT, 5)
				.ingredient(Items.GUNPOWDER, 5)
				.build());
		
		TileEntityGunWorkbench.init();
	}
	
	public static void registerRecipe(PMCRecipe recipe) {
		RECIPES.add(recipe);
	}
	
	public static void registerAll(PMCRecipe[] recipes) {
		for(PMCRecipe recipe : recipes) {
			registerRecipe(recipe);
		}
	}
	
	public static ArrayList<PMCRecipe> asList(CraftingCategory cat) {
		ArrayList<PMCRecipe> list = Lists.newArrayList();
		for(PMCRecipe rec : RECIPES) {
			if(rec.category == cat) {
				list.add(rec);
			}
		}
		return list;
	}
}
