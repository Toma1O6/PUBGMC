package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.util.recipes.CraftingIngredient;
import dev.toma.pubgmc.util.recipes.WorkbenchRecipe;
import dev.toma.pubgmc.util.recipes.WorkbenchRecipe.CraftingCategory;
import dev.toma.pubgmc.util.recipes.RecipeRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TileEntityGunWorkbench extends TileEntity implements IInventoryTileEntity {
    private static final int OUTPUT = 8;
    public static ArrayList<List<WorkbenchRecipe>> RECIPES = new ArrayList<>(CraftingCategory.values().length);
    private static List<WorkbenchRecipe> GUNS;
    private static List<WorkbenchRecipe> AMMO;
    private static List<WorkbenchRecipe> ATTACHMENT;
    private static List<WorkbenchRecipe> CLOTHING;
    private static List<WorkbenchRecipe> HEALING;
    private static List<WorkbenchRecipe> THROWABLES;
    private static List<WorkbenchRecipe> VEHICLES;
    public CraftingCategory selectedCat = CraftingCategory.GUNS;
    public int selectedIndex = 0;
    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);

    /**
     * Splits all recipes from registry into it's categories
     */
    public static void init() {
        GUNS = RecipeRegistry.asList(CraftingCategory.GUNS);
        AMMO = RecipeRegistry.asList(CraftingCategory.AMMO);
        ATTACHMENT = RecipeRegistry.asList(CraftingCategory.ATTACHMENTS);
        CLOTHING = RecipeRegistry.asList(CraftingCategory.WEARABLES);
        HEALING = RecipeRegistry.asList(CraftingCategory.HEALS);
        THROWABLES = RecipeRegistry.asList(CraftingCategory.THROWABLES);
        VEHICLES = RecipeRegistry.asList(CraftingCategory.VEHICLES);
        RECIPES.add(GUNS);
        RECIPES.add(AMMO);
        RECIPES.add(ATTACHMENT);
        RECIPES.add(HEALING);
        RECIPES.add(THROWABLES);
        RECIPES.add(CLOTHING);
        RECIPES.add(VEHICLES);
    }

    @Override
    public NonNullList<ItemStack> getInventory() {
        return inventory;
    }

    @Override
    public String getName() {
        return "container.gun_workbench";
    }

    @Override
    public ITextComponent getDisplayName() {
        return hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    @Override
    public int getSizeInventory() {
        return inventory.size();
    }

    public void craft(WorkbenchRecipe recipe) {
        boolean valid = true;
        for (CraftingIngredient ingredient : recipe.ingredients) {
            int amount = 0;
            for (int i = 0; i < inventory.size() - 1; i++) {
                ItemStack stack = inventory.get(i);
                if (ingredient.isValidInput(stack)) {
                    amount += stack.getCount();
                }
            }
            if (amount < ingredient.requiredResourceSize()) {
                valid = false;
                break;
            }
        }
        if (valid) {
            for (CraftingIngredient ingredient : recipe.ingredients) {
                this.clearItems(ingredient);
            }
            this.setInventorySlotContents(8, recipe.result.copy());
            recipe.onCraft(world, pos);
        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inventory);
        selectedIndex = compound.hasKey("selectedIndex") ? compound.getInteger("selectedIndex") : 0;
        selectedCat = compound.hasKey("selectedCategory") ? CraftingCategory.values()[compound.getInteger("selectedCategory")] : CraftingCategory.GUNS;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.inventory);
        compound.setInteger("selectedIndex", selectedIndex);
        compound.setInteger("selectedCategory", selectedCat.ordinal());
        return compound;
    }

    private void clearItems(CraftingIngredient ingredient) {
        int remaining = ingredient.requiredResourceSize();
        for (ItemStack stack : this.inventory) {
            if (ingredient.isValidInput(stack)) {
                int consumeAmount = Math.min(remaining, stack.getCount());
                stack.shrink(consumeAmount);
                remaining -= consumeAmount;

                if (remaining == 0) {
                    break;
                }
            }
        }
    }
}
