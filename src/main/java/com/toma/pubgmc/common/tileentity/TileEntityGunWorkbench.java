package com.toma.pubgmc.common.tileentity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.toma.pubgmc.util.recipes.ICraftingInventory;
import com.toma.pubgmc.util.recipes.PMCIngredient;
import com.toma.pubgmc.util.recipes.PMCRecipe;
import com.toma.pubgmc.util.recipes.PMCRecipe.CraftingCategory;
import com.toma.pubgmc.util.recipes.RecipeRegistry;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class TileEntityGunWorkbench extends TileEntity implements ICraftingInventory
{
	private static List<PMCRecipe> GUNS;
	private static List<PMCRecipe> AMMO;
	private static List<PMCRecipe> ATTACHMENT;
	private static List<PMCRecipe> CLOTHING;
	private static List<PMCRecipe> HEALING;
	private static List<PMCRecipe> THROWABLES;
	private static List<PMCRecipe> VEHICLES;
	public static ArrayList<List<PMCRecipe>> RECIPES = new ArrayList<>(CraftingCategory.values().length);
	
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	public CraftingCategory selectedCat = CraftingCategory.GUNS;
	public int selectedIndex = 0;
	private static final int OUTPUT = 8;
	
	/** Splits all recipes from registry into it's categories */
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
	public NonNullList<ItemStack> getInventory()
	{
		return inventory;
	}
	
	@Override
	public String getName() 
	{
		return "container.gun_workbench";
	}
	
	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentString(this.getName());
	}
	
	@Override
	public int getSizeInventory()
	{
		return inventory.size();
	}
	
	public void craft(PMCRecipe recipe) {
		for(PMCIngredient ing : recipe.ingredients) {
			int amount = 0;
			// TODO: modify container for the new version and iterate through it's input slots, put recipe in and reduce the right amount of items
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		selectedIndex = compound.hasKey("selectedIndex") ? compound.getInteger("selectedIndex") : 0;
		selectedCat = compound.hasKey("selectedCategory") ? CraftingCategory.values()[compound.getInteger("selectedCategory")] : CraftingCategory.GUNS;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		compound.setInteger("selectedIndex", selectedIndex);
		compound.setInteger("selectedCategory", selectedCat.ordinal());
		return compound;
	}
}
