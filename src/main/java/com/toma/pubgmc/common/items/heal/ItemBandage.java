package com.toma.pubgmc.common.items.heal;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class ItemBandage extends ItemHealing
{
	private ArrayList<ItemStack> recipe = new ArrayList<ItemStack>();
	
	public ItemBandage(String name)
	{
		super(name);
		this.setMaxStackSize(5);
	}
	
	@Override
	public Action getAction()
	{
		return Action.HEAL;
	}
	
	@Override
	public EnumAction getUseAction()
	{
		return EnumAction.NONE;
	}
	
	@Override
	public int getUseTime()
	{
		return 80;
	}
	
	@Override
	public float getHealAmount(EntityPlayer player) 
	{
		return canPlayerHeal(player) ? player.getHealth() == 14 ? 1f : 2f : 0f;
	}
	
	@Override
	public boolean canPlayerHeal(EntityPlayer player)
	{
		return player.getHealth() < 15f;
	}
	
	@Override
	public void initCraftingRecipe()
	{
		recipe.add(new ItemStack(Items.PAPER, 5));
		recipe.add(new ItemStack(Blocks.WOOL));
		recipe.add(new ItemStack(Items.REDSTONE, 5));
		recipe.add(new ItemStack(Items.GOLD_NUGGET, 1));
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe()
	{
		return recipe;
	}
}
