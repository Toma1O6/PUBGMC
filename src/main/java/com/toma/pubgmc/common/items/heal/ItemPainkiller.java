package com.toma.pubgmc.common.items.heal;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPainkiller extends ItemHealing
{

	public ItemPainkiller(String name) 
	{
		super(name);
		setMaxStackSize(3);
	}
	
	@Override
	public Action getAction()
	{
		return Action.BOOST;
	}
	
	@Override
	public EnumAction getUseAction()
	{
		return EnumAction.EAT;
	}
	
	@Override
	public int getUseTime() 
	{
		return 120;
	}
	
	@Override
	public float getBoostAmount()
	{
		return 60f;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> recipe = new ArrayList<ItemStack>();
		recipe.add(new ItemStack(Items.GLASS_BOTTLE));
		recipe.add(new ItemStack(Items.APPLE));
		recipe.add(new ItemStack(Items.CARROT, 3));
		recipe.add(new ItemStack(Items.SUGAR, 5));
		return recipe;
	}
}
