package com.toma.pubgmc.util.recipes;

import java.util.ArrayList;
import java.util.HashSet;

import com.google.common.collect.Lists;
import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.init.PMCRegistry.PMCItems;
import com.toma.pubgmc.util.recipes.PMCRecipe.CraftingCategory;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public final class RecipeRegistry {

	public static final HashSet<PMCRecipe> RECIPES = new HashSet<>();
	
	public static void registerWorkbenchRecipes() {
		long started = System.currentTimeMillis();
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
		
		// wearables
		registerRecipe(PMCRecipeBuilder.createWearable()
				.result(PMCItems.ARMOR1BODY)
				.ingredient(Items.LEATHER_CHESTPLATE, 1)
				.ingredient(Items.IRON_INGOT, 3)
				.build());
		registerRecipe(PMCRecipeBuilder.createWearable()
				.result(PMCItems.ARMOR1HELMET)
				.ingredient(Items.LEATHER_HELMET, 1)
				.ingredient(Items.IRON_INGOT, 3)
				.build());
		registerRecipe(PMCRecipeBuilder.createWearable()
				.result(PMCItems.ARMOR2BODY)
				.ingredient(PMCItems.ARMOR1BODY, 1)
				.ingredient(PMCItems.STEEL_INGOT, 3)
				.ingredient(Items.GOLD_INGOT, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createWearable()
				.result(PMCItems.ARMOR2HELMET)
				.ingredient(PMCItems.ARMOR1HELMET, 1)
				.ingredient(PMCItems.STEEL_INGOT, 3)
				.ingredient(Items.GOLD_INGOT, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createWearable()
				.result(PMCItems.ARMOR3BODY)
				.ingredient(PMCItems.ARMOR2BODY, 1)
				.ingredient(PMCItems.STEEL_INGOT, 6)
				.ingredient(Items.GOLD_INGOT, 2)
				.build());
		registerRecipe(PMCRecipeBuilder.createWearable()
				.result(PMCItems.ARMOR3HELMET)
				.ingredient(PMCItems.ARMOR2HELMET, 1)
				.ingredient(PMCItems.STEEL_INGOT, 6)
				.ingredient(Items.GOLD_INGOT, 2)
				.build());
		registerRecipe(PMCRecipeBuilder.createWearable()
				.result(PMCItems.GHILLIE_SUIT)
				.ingredient(Items.LEATHER_HELMET, 1)
				.ingredient(Items.LEATHER_CHESTPLATE, 1)
				.ingredient(Items.LEATHER_LEGGINGS, 1)
				.ingredient(Blocks.LEAVES, 45)
				.build());
		registerRecipe(PMCRecipeBuilder.createWearable()
				.result(PMCItems.NV_GOGGLES)
				.ingredient(Blocks.GLASS_PANE, 4)
				.ingredient(Blocks.GLOWSTONE, 1)
				.ingredient(PMCItems.STEEL_INGOT, 8)
				.ingredient(Blocks.WOOL, 2)
				.build());
		registerRecipe(PMCRecipeBuilder.createWearable()
				.result(PMCItems.BACKPACK1)
				.ingredient(Blocks.CHEST, 1)
				.ingredient(Items.LEATHER, 10)
				.build());
		registerRecipe(PMCRecipeBuilder.createWearable()
				.result(PMCItems.BACKPACK2)
				.ingredient(PMCItems.BACKPACK1, 1)
				.ingredient(Items.LEATHER, 20)
				.build());
		registerRecipe(PMCRecipeBuilder.createWearable()
				.result(PMCItems.BACKPACK3)
				.ingredient(PMCItems.BACKPACK2, 1)
				.ingredient(Items.LEATHER, 30)
				.build());
		registerRecipe(PMCRecipeBuilder.createWearable()
				.result(PMCItems.PARACHUTE)
				.ingredient(Blocks.WOOL, 15)
				.ingredient(Items.STRING, 20)
				.build());
		
		// Grenades
		registerRecipe(PMCRecipeBuilder.createGrenade()
				.result(PMCItems.GRENADE)
				.ingredient(Blocks.TNT, 1)
				.ingredient(Items.GUNPOWDER, 10)
				.ingredient(Items.IRON_INGOT, 3)
				.build());
		registerRecipe(PMCRecipeBuilder.createGrenade()
				.result(PMCItems.SMOKE)
				.ingredient(Items.COAL, 5)
				.ingredient(Items.GUNPOWDER, 10)
				.ingredient(Items.IRON_INGOT, 3)
				.build());
		registerRecipe(PMCRecipeBuilder.createGrenade()
				.result(PMCItems.MOLOTOV)
				.ingredient(Items.PAPER, 9)
				.ingredient(Items.GUNPOWDER, 10)
				.ingredient(Items.COAL, 5)
				.ingredient(Items.GLASS_BOTTLE, 1)
				.build());
		// TODO : FlashBang
		
		// Healing
		registerRecipe(PMCRecipeBuilder.createHeal()
				.result(PMCItems.BANDAGE)
				.ingredient(Items.PAPER, 5)
				.ingredient(Blocks.WOOL, 1)
				.ingredient(Items.REDSTONE, 5)
				.ingredient(Items.GOLD_NUGGET, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createHeal()
				.result(PMCItems.FIRSTAIDKIT)
				.ingredient(PMCItems.BANDAGE, 2)
				.ingredient(Blocks.WOOL, 1)
				.ingredient(Items.IRON_NUGGET, 1)
				.ingredient(Items.LEATHER, 2)
				.ingredient(Items.GOLD_NUGGET, 3)
				.ingredient(Items.SPECKLED_MELON, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createHeal()
				.result(PMCItems.MEDKIT)
				.ingredient(PMCItems.FIRSTAIDKIT, 1)
				.ingredient(PMCItems.BANDAGE, 2)
				.ingredient(Items.PAPER, 3)
				.ingredient(Items.GOLDEN_CARROT, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createHeal()
				.result(PMCItems.ENERGYDRINK)
				.ingredient(Items.GLASS_BOTTLE, 1)
				.ingredient(Items.SUGAR, 10)
				.ingredient(Items.APPLE, 3)
				.ingredient(Items.GOLD_NUGGET, 1)
				.ingredient(Items.MELON, 5)
				.build());
		registerRecipe(PMCRecipeBuilder.createHeal()
				.result(PMCItems.PAINKILLERS)
				.ingredient(Items.GLASS_BOTTLE, 1)
				.ingredient(Items.APPLE, 1)
				.ingredient(Items.CARROT, 3)
				.ingredient(Items.SUGAR, 5)
				.ingredient(Items.GOLD_NUGGET, 3)
				.ingredient(Items.SPECKLED_MELON, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createHeal()
				.result(PMCItems.ADRENALINESYRINGE)
				.ingredient(Items.GLASS_BOTTLE, 1)
				.ingredient(PMCItems.ENERGYDRINK, 1)
				.ingredient(PMCItems.PAINKILLERS, 1)
				.ingredient(Items.SPECKLED_MELON, 1)
				.ingredient(Items.GOLDEN_CARROT, 1)
				.build());
		
		// Attachments
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.SILENCER_PISTOL)
				.ingredient(Blocks.WOOL, 5, 15)
				.ingredient(Items.IRON_INGOT, 3)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.SILENCER_SMG)
				.ingredient(Blocks.WOOL, 7, 15)
				.ingredient(Items.IRON_INGOT, 4)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.SILENCER_AR)
				.ingredient(Blocks.WOOL, 10, 15)
				.ingredient(Items.IRON_INGOT, 7)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.SILENCER_SNIPER)
				.ingredient(Blocks.WOOL, 15, 15)
				.ingredient(Items.IRON_INGOT, 10)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.COMPENSATOR_SMG)
				.ingredient(PMCItems.STEEL_INGOT, 3)
				.ingredient(Items.IRON_INGOT, 3)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.COMPENSATOR_AR)
				.ingredient(PMCItems.STEEL_INGOT, 6)
				.ingredient(Items.IRON_INGOT, 6)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.COMPENSATOR_SNIPER)
				.ingredient(PMCItems.STEEL_INGOT, 8)
				.ingredient(Items.IRON_INGOT, 8)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.RED_DOT)
				.ingredient(PMCItems.STEEL_INGOT, 5)
				.ingredient(Blocks.GLASS_PANE, 1)
				.ingredient(Items.DYE, 1, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.HOLOGRAPHIC)
				.ingredient(PMCItems.STEEL_INGOT, 7)
				.ingredient(Blocks.GLASS_PANE, 1)
				.ingredient(Items.DYE, 2, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.SCOPE2X)
				.ingredient(PMCItems.STEEL_INGOT, 10)
				.ingredient(Blocks.GLASS_PANE, 4)
				.ingredient(Items.DYE, 3, 10)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.SCOPE4X)
				.ingredient(PMCItems.STEEL_INGOT, 20)
				.ingredient(Blocks.GLASS_PANE, 6)
				.ingredient(Items.DYE, 6, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.SCOPE8X)
				.ingredient(PMCItems.STEEL_INGOT, 30)
				.ingredient(Blocks.GLASS_PANE, 10)
				.ingredient(Items.DYE, 5)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.SCOPE15X)
				.ingredient(PMCItems.STEEL_INGOT, 50)
				.ingredient(Blocks.GLASS_PANE, 16)
				.ingredient(Items.DYE, 15)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.GRIP_ANGLED)
				.ingredient(PMCItems.STEEL_INGOT, 10)
				.ingredient(Items.IRON_INGOT, 10)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.GRIP_VERTICAL)
				.ingredient(PMCItems.STEEL_INGOT, 10)
				.ingredient(Items.IRON_INGOT, 10)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.QUICKDRAW_MAG_PISTOL)
				.ingredient(PMCItems.STEEL_INGOT, 3)
				.ingredient(Items.IRON_INGOT, 3)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.QUICKDRAW_MAG_SMG)
				.ingredient(PMCItems.STEEL_INGOT, 5)
				.ingredient(Items.IRON_INGOT, 5)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.QUICKDRAW_MAG_AR)
				.ingredient(PMCItems.STEEL_INGOT, 8)
				.ingredient(Items.IRON_INGOT, 8)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.QUICKDRAW_MAG_SNIPER)
				.ingredient(PMCItems.STEEL_INGOT, 15)
				.ingredient(Items.IRON_INGOT, 15)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.EXTENDED_MAG_PISTOL)
				.ingredient(PMCItems.STEEL_INGOT, 3)
				.ingredient(Items.IRON_INGOT, 5)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.EXTENDED_MAG_SMG)
				.ingredient(PMCItems.STEEL_INGOT, 5)
				.ingredient(Items.IRON_INGOT, 8)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.EXTENDED_MAG_AR)
				.ingredient(PMCItems.STEEL_INGOT, 8)
				.ingredient(Items.IRON_INGOT, 15)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.EXTENDED_MAG_SNIPER)
				.ingredient(PMCItems.STEEL_INGOT, 15)
				.ingredient(Items.IRON_INGOT, 25)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL)
				.ingredient(PMCItems.QUICKDRAW_MAG_PISTOL, 1)
				.ingredient(PMCItems.EXTENDED_MAG_PISTOL, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.EXTENDED_QUICKDRAW_MAG_SMG)
				.ingredient(PMCItems.QUICKDRAW_MAG_SMG, 1)
				.ingredient(PMCItems.EXTENDED_MAG_SMG, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.EXTENDED_QUICKDRAW_MAG_AR)
				.ingredient(PMCItems.QUICKDRAW_MAG_AR, 1)
				.ingredient(PMCItems.EXTENDED_MAG_AR, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER)
				.ingredient(PMCItems.QUICKDRAW_MAG_SNIPER, 1)
				.ingredient(PMCItems.EXTENDED_MAG_SNIPER, 1)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.BULLET_LOOPS_SHOTGUN)
				.ingredient(Blocks.PLANKS, 5)
				.ingredient(Items.IRON_INGOT, 3)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.BULLET_LOOPS_SNIPER)
				.ingredient(PMCItems.STEEL_INGOT, 3)
				.ingredient(Items.IRON_INGOT, 5)
				.build());
		registerRecipe(PMCRecipeBuilder.createAttachment()
				.result(PMCItems.CHEEKPAD)
				.ingredient(PMCItems.STEEL_INGOT, 10)
				.ingredient(Items.IRON_INGOT, 10)
				.build());
		
		// Vehicles
		registerRecipe(PMCRecipeBuilder.createVehicle()
				.result(PMCItems.FUELCAN)
				.ingredient(Items.IRON_INGOT, 2)
				.ingredient(Items.BLAZE_POWDER, 4)
				.ingredient(Items.GLOWSTONE_DUST, 4)
				.ingredient(Items.REDSTONE, 2)
				.ingredient(Items.LAVA_BUCKET, 1)
				.returns(Items.BUCKET)
				.build());
		registerRecipe(PMCRecipeBuilder.createVehicle()
				.result(PMCItems.VEHICLE_UAZ)
				.ingredient(PMCItems.STEEL_INGOT, 50)
				.ingredient(Blocks.IRON_BLOCK, 10)
				.ingredient(Items.LEATHER, 20)
				.ingredient(Blocks.GLASS, 10)
				.ingredient(Items.REDSTONE, 40)
				.ingredient(Items.GOLD_INGOT, 45)
				.build());
		registerRecipe(PMCRecipeBuilder.createVehicle()
				.result(PMCItems.VEHICLE_DACIA)
				.ingredient(PMCItems.STEEL_INGOT, 50)
				.ingredient(Blocks.IRON_BLOCK, 10)
				.ingredient(Items.LEATHER, 20)
				.ingredient(Blocks.GLASS, 10)
				.ingredient(Items.REDSTONE, 40)
				.ingredient(Items.GOLD_INGOT, 45)
				.build());
		
		TileEntityGunWorkbench.init();
		Pubgmc.logger.info("Workbench recipes initialized. Recipes: {}, took {} miliseconds.", RECIPES.size(), System.currentTimeMillis()-started);
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
