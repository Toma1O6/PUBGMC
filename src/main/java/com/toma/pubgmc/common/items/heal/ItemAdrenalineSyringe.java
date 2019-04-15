package com.toma.pubgmc.common.items.heal;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCRegistry;

import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAdrenalineSyringe extends ItemHealing
{
	public ItemAdrenalineSyringe(String name)
	{
		super(name);
	}
	
	@Override
	public Action getAction()
	{
		return Action.BOOST;
	}
	
	@Override
	public EnumAction getUseAction()
	{
		return EnumAction.NONE;
	}
	
	@Override
	public int getUseTime() 
	{
		return 120;
	}
	
	@Override
	public float getBoostAmount()
	{
		return 100f;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> recipe = new ArrayList<ItemStack>();
		recipe.add(new ItemStack(Items.GLASS_BOTTLE));
		recipe.add(new ItemStack(PMCRegistry.PMCItems.ENERGYDRINK));
		recipe.add(new ItemStack(PMCRegistry.PMCItems.PAINKILLERS));
		return recipe;
	}
}
