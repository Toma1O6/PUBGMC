package dev.toma.pubgmc.util.recipes;

import com.google.common.base.Preconditions;
import dev.toma.pubgmc.util.recipes.WorkbenchRecipe.CraftingCategory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class WorkbenchRecipeBuilder {

    private ItemStack result;
    private ItemStack returnItem = ItemStack.EMPTY;
    private final ArrayList<CraftingIngredient> ingredients = new ArrayList<>();
    private CraftingCategory category;

    private WorkbenchRecipeBuilder() {
    }

    public static WorkbenchRecipeBuilder createGun() {
        return create(CraftingCategory.GUNS);
    }

    public static WorkbenchRecipeBuilder createAmmo() {
        return create(CraftingCategory.AMMO);
    }

    public static WorkbenchRecipeBuilder createAttachment() {
        return create(CraftingCategory.ATTACHMENTS);
    }

    public static WorkbenchRecipeBuilder createHeal() {
        return create(CraftingCategory.HEALS);
    }

    public static WorkbenchRecipeBuilder createGrenade() {
        return create(CraftingCategory.THROWABLES);
    }

    public static WorkbenchRecipeBuilder createWearable() {
        return create(CraftingCategory.WEARABLES);
    }

    public static WorkbenchRecipeBuilder createVehicle() {
        return create(CraftingCategory.VEHICLES);
    }

    private static WorkbenchRecipeBuilder create(CraftingCategory category) {
        WorkbenchRecipeBuilder builder = new WorkbenchRecipeBuilder();
        builder.category = category;
        return builder;
    }

    public WorkbenchRecipeBuilder result(Item item) {
        return this.result(new ItemStack(item));
    }

    public WorkbenchRecipeBuilder result(ItemStack itemStack) {
        this.result = itemStack;
        return this;
    }

    public WorkbenchRecipeBuilder ingredient(ItemStack itemStack) {
        return this.ingredient(new ItemCraftingIngredient(itemStack));
    }

    public WorkbenchRecipeBuilder ingredient(Item item, int amount) {
        return this.ingredient(new ItemStack(item, amount));
    }

    public WorkbenchRecipeBuilder ingredient(Block block, int amount) {
        return this.ingredient(new ItemStack(block, amount));
    }

    public WorkbenchRecipeBuilder ingredient(Item item, int amount, int meta) {
        return this.ingredient(new ItemStack(item, amount, meta));
    }

    public WorkbenchRecipeBuilder ingredient(Block block, int amount, int meta) {
        return this.ingredient(new ItemStack(block, amount, meta));
    }

    public WorkbenchRecipeBuilder ingredient(CraftingIngredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public WorkbenchRecipeBuilder returns(ItemStack itemStack) {
        this.returnItem = itemStack;
        return this;
    }

    public WorkbenchRecipe build() {
        Preconditions.checkNotNull(result);
        Preconditions.checkNotNull(category);
        Preconditions.checkState(!ingredients.isEmpty());
        Preconditions.checkState(!result.isEmpty());
        return new WorkbenchRecipe(this.result, this.ingredients, this.category, this.returnItem);
    }
}
