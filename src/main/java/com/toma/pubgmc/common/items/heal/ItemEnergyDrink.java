package com.toma.pubgmc.common.items.heal;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemEnergyDrink extends ItemHealing
{

	public ItemEnergyDrink(String name) 
	{
		super(name);
		this.setMaxStackSize(3);
	}
	
	@Override
	public Action getAction()
	{
		return Action.BOOST;
	}
	
	@Override
	public EnumAction getUseAction() 
	{
		return EnumAction.DRINK;
	}
	
	@Override
	public int getUseTime()
	{
		return 80;
	}
	
	@Override
	public float getBoostAmount()
	{
		return 40f;
	}
	
    @Override
    public List<ItemStack> getCraftingRecipe(Item item)
    {
    	List<ItemStack> recipe = new ArrayList<ItemStack>();
    	recipe.add(new ItemStack(Items.GLASS_BOTTLE));
    	recipe.add(new ItemStack(Items.SUGAR, 10));
    	recipe.add(new ItemStack(Items.APPLE, 3));
    	recipe.add(new ItemStack(Items.GOLD_NUGGET));
    	recipe.add(new ItemStack(Items.MELON, 5));
    	return recipe;
    }
}
