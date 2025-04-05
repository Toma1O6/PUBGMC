package dev.toma.pubgmc.util.recipes;

import com.google.common.collect.Lists;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.recipes.WorkbenchRecipe.CraftingCategory;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class RecipeRegistry {

    public static final List<WorkbenchRecipe> RECIPES = new ArrayList<>();

    public static void registerWorkbenchRecipes() {
        long started = System.currentTimeMillis();
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.FLARE_GUN)
                .ingredient(Items.IRON_INGOT, 15)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 10))
                .ingredient(Items.DYE, 10, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.P92)
                .ingredient(Items.IRON_INGOT, 15)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 10))
                .ingredient(Blocks.PLANKS, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.P1911)
                .ingredient(Items.IRON_INGOT, 20)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 12))
                .ingredient(Blocks.STONE, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.P18C)
                .ingredient(Items.IRON_INGOT, 20)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 8))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.R1895)
                .ingredient(Items.IRON_INGOT, 5)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 20))
                .ingredient(Blocks.PLANKS, 2)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.R45)
                .ingredient(Items.IRON_INGOT, 5)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 18))
                .ingredient(Blocks.PLANKS, 2)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.SCORPION)
                .ingredient(Items.IRON_INGOT, 30)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 25))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.DEAGLE)
                .ingredient(Items.IRON_INGOT, 35)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 40))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.WIN94)
                .ingredient(Items.IRON_INGOT, 25)
                .ingredient(Blocks.PLANKS, 5)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.SAWED_OFF)
                .ingredient(Items.IRON_INGOT, 10)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 25))
                .ingredient(Blocks.PLANKS, 7)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.S1897)
                .ingredient(Items.IRON_INGOT, 15)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 25))
                .ingredient(Blocks.PLANKS, 10)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.S686)
                .ingredient(Items.IRON_INGOT, 20)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 20))
                .ingredient(Blocks.PLANKS, 10)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.S12K)
                .ingredient(Items.IRON_INGOT, 40)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 35))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.MICROUZI)
                .ingredient(Items.IRON_INGOT, 40)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 15))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.VECTOR)
                .ingredient(Items.IRON_INGOT, 35)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 25))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.BIZON)
                .ingredient(Items.IRON_INGOT, 40)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 25))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.MP5K)
                .ingredient(Items.IRON_INGOT, 15)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 45))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.TOMMY_GUN)
                .ingredient(Items.IRON_INGOT, 25)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 15))
                .ingredient(Blocks.PLANKS, 3)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.UMP45)
                .ingredient(Items.IRON_INGOT, 40)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 25))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.M16A4)
                .ingredient(Items.IRON_INGOT, 35)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 50))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.M416)
                .ingredient(Items.IRON_INGOT, 50)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 35))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.SCAR_L)
                .ingredient(Items.IRON_INGOT, 50)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 35))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.QBZ)
                .ingredient(Items.IRON_INGOT, 50)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 35))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.G36C)
                .ingredient(Items.IRON_INGOT, 50)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 35))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.AUG)
                .ingredient(Items.IRON_INGOT, 50)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 40))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.AKM)
                .ingredient(Items.IRON_INGOT, 50)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 40))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.BERYL_M762)
                .ingredient(Items.IRON_INGOT, 50)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 40))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.MK47_MUTANT)
                .ingredient(Items.IRON_INGOT, 50)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 40))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.GROZA)
                .ingredient(Items.IRON_INGOT, 50)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 40))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.DP28)
                .ingredient(Items.IRON_INGOT, 50)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 50))
                .ingredient(Blocks.IRON_BLOCK, 3)
                .ingredient(Blocks.PLANKS, 10)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.M249)
                .ingredient(Items.IRON_INGOT, 60)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 60))
                .ingredient(Blocks.IRON_BLOCK, 3)
                .ingredient(Blocks.STONE, 5)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.VSS)
                .ingredient(Items.IRON_INGOT, 35)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 30))
                .ingredient(Blocks.PLANKS, 20)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.MINI14)
                .ingredient(Items.IRON_INGOT, 35)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 50))
                .ingredient(Blocks.PLANKS, 5)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.QBU)
                .ingredient(Items.IRON_INGOT, 35)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 50))
                .ingredient(Blocks.PLANKS, 5)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.SKS)
                .ingredient(Items.IRON_INGOT, 35)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 50))
                .ingredient(Blocks.STONE, 5)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.SLR)
                .ingredient(Items.IRON_INGOT, 40)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 60))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.MK14)
                .ingredient(Items.IRON_INGOT, 40)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 60))
                .ingredient(Blocks.STONE, 5)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.KAR98K)
                .ingredient(Items.IRON_INGOT, 40)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 50))
                .ingredient(Blocks.IRON_BLOCK, 1)
                .ingredient(Blocks.PLANKS, 15)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.M24)
                .ingredient(Items.IRON_INGOT, 50)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 60))
                .ingredient(Blocks.IRON_BLOCK, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGun()
                .result(PMCItems.AWM)
                .ingredient(Items.IRON_INGOT, 50)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 50))
                .ingredient(Items.DIAMOND, 5)
                .ingredient(Blocks.IRON_BLOCK, 2)
                .build());

        // Ammo
        registerRecipe(WorkbenchRecipeBuilder.createAmmo()
                .result(PMCItems.AMMO_FLARE)
                .ingredient(Items.DIAMOND, 5)
                .ingredient(Items.IRON_INGOT, 1)
                .ingredient(Items.GUNPOWDER, 10)
                .ingredient(Items.BLAZE_POWDER, 3)
                .ingredient(Items.DYE, 5, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAmmo()
                .result(new ItemStack(PMCItems.AMMO_9MM, 30))
                .ingredient(Items.GOLD_NUGGET, 5)
                .ingredient(new OreDictEntryCraftingIngredient("ingotCopper", 1))
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 1))
                .ingredient(Items.GUNPOWDER, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAmmo()
                .result(new ItemStack(PMCItems.AMMO_SHOTGUN, 30))
                .ingredient(Items.GOLD_NUGGET, 10)
                .ingredient(Items.IRON_NUGGET, 8)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 1))
                .ingredient(Items.GUNPOWDER, 3)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAmmo()
                .result(new ItemStack(PMCItems.AMMO_45ACP, 30))
                .ingredient(Items.GOLD_NUGGET, 5)
                .ingredient(Items.IRON_NUGGET, 5)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 1))
                .ingredient(Items.GUNPOWDER, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAmmo()
                .result(new ItemStack(PMCItems.AMMO_556, 30))
                .ingredient(Items.GOLD_NUGGET, 10)
                .ingredient(Items.IRON_NUGGET, 10)
                .ingredient(new OreDictEntryCraftingIngredient("ingotCopper", 1))
                .ingredient(Items.GUNPOWDER, 2)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAmmo()
                .result(new ItemStack(PMCItems.AMMO_762, 30))
                .ingredient(Items.GOLD_NUGGET, 15)
                .ingredient(Items.IRON_NUGGET, 10)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 1))
                .ingredient(Items.GUNPOWDER, 2)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAmmo()
                .result(new ItemStack(PMCItems.AMMO_300M, 30))
                .ingredient(Items.GOLD_INGOT, 3)
                .ingredient(Items.IRON_INGOT, 3)
                .ingredient(new OreDictEntryCraftingIngredient("ingotCopper", 2))
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 5))
                .ingredient(Items.GUNPOWDER, 5)
                .build());

        // wearables
        registerRecipe(WorkbenchRecipeBuilder.createWearable()
                .result(PMCItems.ARMOR1BODY)
                .ingredient(Items.LEATHER_CHESTPLATE, 1)
                .ingredient(Items.IRON_INGOT, 3)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createWearable()
                .result(PMCItems.ARMOR1HELMET)
                .ingredient(Items.LEATHER_HELMET, 1)
                .ingredient(Items.IRON_INGOT, 3)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createWearable()
                .result(PMCItems.ARMOR2BODY)
                .ingredient(PMCItems.ARMOR1BODY, 1)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 3))
                .ingredient(Items.GOLD_INGOT, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createWearable()
                .result(PMCItems.ARMOR2HELMET)
                .ingredient(PMCItems.ARMOR1HELMET, 1)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 3))
                .ingredient(Items.GOLD_INGOT, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createWearable()
                .result(PMCItems.ARMOR3BODY)
                .ingredient(PMCItems.ARMOR2BODY, 1)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 6))
                .ingredient(Items.GOLD_INGOT, 2)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createWearable()
                .result(PMCItems.ARMOR3HELMET)
                .ingredient(PMCItems.ARMOR2HELMET, 1)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 6))
                .ingredient(Items.GOLD_INGOT, 2)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createWearable()
                .result(PMCItems.GHILLIE_SUIT)
                .ingredient(Items.LEATHER_HELMET, 1)
                .ingredient(Items.LEATHER_CHESTPLATE, 1)
                .ingredient(Items.LEATHER_LEGGINGS, 1)
                .ingredient(Blocks.LEAVES, 45)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createWearable()
                .result(PMCItems.NV_GOGGLES)
                .ingredient(Blocks.GLASS_PANE, 4)
                .ingredient(Blocks.GLOWSTONE, 1)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 8))
                .ingredient(Blocks.WOOL, 2)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createWearable()
                .result(PMCItems.SMALL_BACKPACK_FOREST)
                .ingredient(Blocks.CHEST, 1)
                .ingredient(Items.LEATHER, 10)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createWearable()
                .result(PMCItems.MEDIUM_BACKPACK_FOREST)
                .ingredient(PMCItems.SMALL_BACKPACK_FOREST, 1)
                .ingredient(Items.LEATHER, 20)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createWearable()
                .result(PMCItems.LARGE_BACKPACK_FOREST)
                .ingredient(PMCItems.MEDIUM_BACKPACK_FOREST, 1)
                .ingredient(Items.LEATHER, 30)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createWearable()
                .result(PMCItems.PARACHUTE)
                .ingredient(Blocks.WOOL, 15)
                .ingredient(Items.STRING, 20)
                .build());

        // Grenades
        registerRecipe(WorkbenchRecipeBuilder.createGrenade()
                .result(PMCItems.GRENADE)
                .ingredient(Blocks.TNT, 1)
                .ingredient(Items.GUNPOWDER, 10)
                .ingredient(Items.IRON_INGOT, 3)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGrenade()
                .result(PMCItems.SMOKE)
                .ingredient(Items.COAL, 5)
                .ingredient(Items.GUNPOWDER, 10)
                .ingredient(Items.IRON_INGOT, 3)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGrenade()
                .result(PMCItems.MOLOTOV)
                .ingredient(Items.PAPER, 9)
                .ingredient(Items.GUNPOWDER, 10)
                .ingredient(Items.COAL, 5)
                .ingredient(Items.GLASS_BOTTLE, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createGrenade()
                .result(PMCItems.FLASHBANG)
                .ingredient(Items.GLOWSTONE_DUST, 5)
                .ingredient(Items.GUNPOWDER, 10)
                .ingredient(Items.IRON_INGOT, 3)
                .ingredient(Blocks.SAND, 1)
                .build());

        // Healing
        registerRecipe(WorkbenchRecipeBuilder.createHeal()
                .result(new ItemStack(PMCItems.BANDAGE, 5))
                .ingredient(Items.PAPER, 5)
                .ingredient(Blocks.WOOL, 1)
                .ingredient(Items.REDSTONE, 5)
                .ingredient(Items.GOLD_NUGGET, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createHeal()
                .result(PMCItems.FIRSTAIDKIT)
                .ingredient(PMCItems.BANDAGE, 2)
                .ingredient(Blocks.WOOL, 1)
                .ingredient(Items.IRON_NUGGET, 1)
                .ingredient(Items.LEATHER, 2)
                .ingredient(Items.GOLD_NUGGET, 3)
                .ingredient(Items.SPECKLED_MELON, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createHeal()
                .result(PMCItems.MEDKIT)
                .ingredient(PMCItems.FIRSTAIDKIT, 1)
                .ingredient(PMCItems.BANDAGE, 2)
                .ingredient(Items.PAPER, 3)
                .ingredient(Items.GOLDEN_CARROT, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createHeal()
                .result(PMCItems.ENERGYDRINK)
                .ingredient(Items.GLASS_BOTTLE, 1)
                .ingredient(Items.SUGAR, 10)
                .ingredient(Items.APPLE, 3)
                .ingredient(Items.GOLD_NUGGET, 1)
                .ingredient(Items.MELON, 5)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createHeal()
                .result(PMCItems.PAINKILLERS)
                .ingredient(Items.GLASS_BOTTLE, 1)
                .ingredient(Items.APPLE, 1)
                .ingredient(Items.CARROT, 3)
                .ingredient(Items.SUGAR, 5)
                .ingredient(Items.GOLD_NUGGET, 3)
                .ingredient(Items.SPECKLED_MELON, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createHeal()
                .result(PMCItems.ADRENALINESYRINGE)
                .ingredient(Items.GLASS_BOTTLE, 1)
                .ingredient(PMCItems.ENERGYDRINK, 1)
                .ingredient(PMCItems.PAINKILLERS, 1)
                .ingredient(Items.SPECKLED_MELON, 1)
                .ingredient(Items.GOLDEN_CARROT, 1)
                .build());

        // Attachments
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.SILENCER_SMG)
                .ingredient(Blocks.WOOL, 7, 15)
                .ingredient(Items.IRON_INGOT, 4)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.SILENCER_AR)
                .ingredient(Blocks.WOOL, 10, 15)
                .ingredient(Items.IRON_INGOT, 7)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.SILENCER_SNIPER)
                .ingredient(Blocks.WOOL, 15, 15)
                .ingredient(Items.IRON_INGOT, 10)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.COMPENSATOR_SMG)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 3))
                .ingredient(Items.IRON_INGOT, 3)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.COMPENSATOR_AR)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 6))
                .ingredient(Items.IRON_INGOT, 6)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.COMPENSATOR_SNIPER)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 8))
                .ingredient(Items.IRON_INGOT, 8)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.RED_DOT)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 5))
                .ingredient(Blocks.GLASS_PANE, 1)
                .ingredient(Items.DYE, 1, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.HOLOGRAPHIC)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 7))
                .ingredient(Blocks.GLASS_PANE, 1)
                .ingredient(Items.DYE, 2, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.SCOPE2X)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 10))
                .ingredient(Blocks.GLASS_PANE, 4)
                .ingredient(Items.DYE, 3, 10)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.SCOPE4X)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 20))
                .ingredient(Blocks.GLASS_PANE, 6)
                .ingredient(Items.DYE, 6, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.SCOPE8X)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 30))
                .ingredient(Blocks.GLASS_PANE, 10)
                .ingredient(Items.DYE, 5)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.SCOPE15X)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 50))
                .ingredient(Blocks.GLASS_PANE, 16)
                .ingredient(Items.DYE, 15)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.GRIP_ANGLED)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 10))
                .ingredient(Items.IRON_INGOT, 10)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.GRIP_VERTICAL)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 10))
                .ingredient(Items.IRON_INGOT, 10)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.QUICKDRAW_MAG_SMG)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 5))
                .ingredient(Items.IRON_INGOT, 5)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.QUICKDRAW_MAG_AR)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 8))
                .ingredient(Items.IRON_INGOT, 8)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.QUICKDRAW_MAG_SNIPER)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 15))
                .ingredient(Items.IRON_INGOT, 15)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.EXTENDED_MAG_SMG)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 5))
                .ingredient(Items.IRON_INGOT, 8)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.EXTENDED_MAG_AR)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 8))
                .ingredient(Items.IRON_INGOT, 15)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.EXTENDED_MAG_SNIPER)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 15))
                .ingredient(Items.IRON_INGOT, 25)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.EXTENDED_QUICKDRAW_MAG_SMG)
                .ingredient(PMCItems.QUICKDRAW_MAG_SMG, 1)
                .ingredient(PMCItems.EXTENDED_MAG_SMG, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.EXTENDED_QUICKDRAW_MAG_AR)
                .ingredient(PMCItems.QUICKDRAW_MAG_AR, 1)
                .ingredient(PMCItems.EXTENDED_MAG_AR, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER)
                .ingredient(PMCItems.QUICKDRAW_MAG_SNIPER, 1)
                .ingredient(PMCItems.EXTENDED_MAG_SNIPER, 1)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.BULLET_LOOPS)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 3))
                .ingredient(Items.IRON_INGOT, 5)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createAttachment()
                .result(PMCItems.CHEEKPAD)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 10))
                .ingredient(Items.IRON_INGOT, 10)
                .build());

        // Vehicles
        registerRecipe(WorkbenchRecipeBuilder.createVehicle()
                .result(PMCItems.FUELCAN)
                .ingredient(Items.IRON_INGOT, 2)
                .ingredient(Items.BLAZE_POWDER, 4)
                .ingredient(Items.GLOWSTONE_DUST, 4)
                .ingredient(Items.REDSTONE, 2)
                .ingredient(Items.LAVA_BUCKET, 1)
                .returns(new ItemStack(Items.BUCKET))
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createVehicle()
                .result(PMCItems.VEHICLE_UAZ)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 50))
                .ingredient(Blocks.IRON_BLOCK, 10)
                .ingredient(Items.LEATHER, 20)
                .ingredient(Blocks.GLASS, 10)
                .ingredient(Items.REDSTONE, 40)
                .ingredient(Items.GOLD_INGOT, 45)
                .build());
        registerRecipe(WorkbenchRecipeBuilder.createVehicle()
                .result(PMCItems.VEHICLE_DACIA)
                .ingredient(new OreDictEntryCraftingIngredient("ingotSteel", 50))
                .ingredient(Blocks.IRON_BLOCK, 10)
                .ingredient(Items.LEATHER, 20)
                .ingredient(Blocks.GLASS, 10)
                .ingredient(Items.REDSTONE, 40)
                .ingredient(Items.GOLD_INGOT, 45)
                .build());

        TileEntityGunWorkbench.init();
        Pubgmc.logger.info("Workbench recipes initialized. Recipes: {}, took {} miliseconds.", RECIPES.size(), System.currentTimeMillis() - started);
    }

    public static synchronized void registerRecipe(WorkbenchRecipe recipe) {
        RECIPES.add(recipe);
    }

    public static ArrayList<WorkbenchRecipe> asList(CraftingCategory cat) {
        ArrayList<WorkbenchRecipe> list = Lists.newArrayList();
        for (WorkbenchRecipe recipe : RECIPES) {
            if (recipe.category == cat) {
                list.add(recipe);
            }
        }
        return list;
    }
}
