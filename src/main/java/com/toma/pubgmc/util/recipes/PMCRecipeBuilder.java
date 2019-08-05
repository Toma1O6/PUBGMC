package com.toma.pubgmc.util.recipes;

import com.google.common.base.Preconditions;
import com.toma.pubgmc.util.IBuilder;
import com.toma.pubgmc.util.recipes.PMCRecipe.CraftingCategory;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class PMCRecipeBuilder {

    private Item result = null;
    private int amount = 1;
    private Item returnItem = Items.AIR;
    private ArrayList<PMCIngredient> ingredients = new ArrayList<>();
    private CraftingCategory category;
    private int slotIndex = 0;

    private PMCRecipeBuilder() {
    }

    public static PMCRecipeBuilder createGun() {
        return create(CraftingCategory.GUNS);
    }

    public static PMCRecipeBuilder createAmmo() {
        return create(CraftingCategory.AMMO);
    }

    public static PMCRecipeBuilder createAttachment() {
        return create(CraftingCategory.ATTACHMENTS);
    }

    public static PMCRecipeBuilder createHeal() {
        return create(CraftingCategory.HEALS);
    }

    public static PMCRecipeBuilder createGrenade() {
        return create(CraftingCategory.THROWABLES);
    }

    public static PMCRecipeBuilder createWearable() {
        return create(CraftingCategory.WEARABLES);
    }

    public static PMCRecipeBuilder createVehicle() {
        return create(CraftingCategory.VEHICLES);
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

    public PMCRecipeBuilder ingredient(Block block, int amount, int meta) {
        ingredients.add(new PMCIngredient(slotIndex, block, amount, meta));
        ++slotIndex;
        return this;
    }

    public PMCRecipeBuilder returns(Item item) {
        this.returnItem = item;
        return this;
    }

    public PMCRecipeBuilder resultAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public PMCRecipe build() {
        Preconditions.checkNotNull(result);
        Preconditions.checkState(amount > 0 && amount <= 64);
        Preconditions.checkNotNull(category);
        Preconditions.checkState(!ingredients.isEmpty());
        Preconditions.checkState(result != null && result != Items.AIR);
        PMCIngredient[] ingredient = ingredients.toArray(new PMCIngredient[0]);
        return returnItem == Items.AIR ? new PMCRecipe(result, amount, ingredient, category) : new PMCRecipe(result, amount, ingredient, category, new ItemStack(returnItem));
    }
}
