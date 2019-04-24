package com.toma.pubgmc.common.items.heal;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class ItemMedkit extends ItemHealing
{
	private ArrayList<ItemStack> recipe = new ArrayList<ItemStack>();
	
	public ItemMedkit(String name)
	{
		super(name);
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
		return 160;
	}
	
	@Override
	public boolean canPlayerHeal(EntityPlayer player)
	{
		return player.getHealth() < 20f;
	}
	
	@Override
	public float getHealAmount(EntityPlayer player)
	{
		return 20 - player.getHealth();
	}
	
	@Override
	public void initCraftingRecipe()
	{
		recipe.add(new ItemStack(PMCRegistry.PMCItems.FIRSTAIDKIT));
		recipe.add(new ItemStack(PMCRegistry.PMCItems.BANDAGE, 2));
		recipe.add(new ItemStack(Items.PAPER, 3));
		recipe.add(new ItemStack(Items.GOLDEN_CARROT));
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe()
	{
		return recipe;
	}
}
