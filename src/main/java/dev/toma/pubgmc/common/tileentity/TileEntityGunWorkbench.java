package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.util.recipes.ICraftingInventory;
import dev.toma.pubgmc.util.recipes.PMCIngredient;
import dev.toma.pubgmc.util.recipes.PMCRecipe;
import dev.toma.pubgmc.util.recipes.PMCRecipe.CraftingCategory;
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
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TileEntityGunWorkbench extends TileEntity implements ICraftingInventory {
    private static final int OUTPUT = 8;
    public static ArrayList<List<PMCRecipe>> RECIPES = new ArrayList<>(CraftingCategory.values().length);
    private static List<PMCRecipe> GUNS;
    private static List<PMCRecipe> AMMO;
    private static List<PMCRecipe> ATTACHMENT;
    private static List<PMCRecipe> CLOTHING;
    private static List<PMCRecipe> HEALING;
    private static List<PMCRecipe> THROWABLES;
    private static List<PMCRecipe> VEHICLES;
    public CraftingCategory selectedCat = CraftingCategory.GUNS;
    public int selectedIndex = 0;
    private NonNullList<ItemStack> inventory = NonNullList.withSize(9, ItemStack.EMPTY);

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
    public int getOutputSlot() {
        return 0;
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
        return new TextComponentString(this.getName());
    }

    @Override
    public int getSizeInventory() {
        return inventory.size();
    }

    public void craft(PMCRecipe recipe) {
        boolean valid = true;
        for (PMCIngredient ing : recipe.ingredients) {
            int amount = 0;
            for (int i = 0; i < 8; i++) {
                if (this.getStackInSlot(i).getItem() == ing.getIngredient().getItem()) {
                    amount += this.getStackInSlot(i).getCount();
                }
            }
            if (amount < ing.getIngredient().getCount()) {
                valid = false;
            }
        }
        if (valid) {
            for (PMCIngredient ing : recipe.ingredients) {
                this.clearItems(ing.getIngredient(), ing.getIngredient().getCount());
            }
            this.setInventorySlotContents(8, new ItemStack(recipe.result, recipe.resultCount));
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
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
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

    private void clearItems(ItemStack itemStack, final int amount) {
        int remaining = amount;
        for (ItemStack stack : this.inventory) {
            if (stack.getItem() == itemStack.getItem()) {
                if (remaining > stack.getCount()) {
                    remaining -= stack.getCount();
                    stack.shrink(stack.getCount());
                } else {
                    stack.setCount(stack.getCount() - remaining);
                    remaining = 0;
                    break;
                }
            }
        }
        if (remaining > 0) {
            throw new IllegalStateException("Fatal error occured when attempted to remove right item count from inventory. Not enought items");
        }
    }
}
