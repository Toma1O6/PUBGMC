package com.toma.pubgmc.common.items.heal;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.init.PMCItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFirstAidKit extends ItemHealing
{

	public ItemFirstAidKit(String name)
	{
		super(name);
		this.setMaxStackSize(1);
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
		return 120;
	}
	
	@Override
	public boolean canPlayerHeal(EntityPlayer player)
	{
		return player.getHealth() < 15f;
	}
	
	@Override
	public float getHealAmount(EntityPlayer player)
	{
		return 15 - player.getHealth();
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> recipe = new ArrayList<ItemStack>();
		recipe.add(new ItemStack(PMCItems.BANDAGE, 3));
		recipe.add(new ItemStack(Blocks.WOOL));
		recipe.add(new ItemStack(Items.IRON_NUGGET));
		return recipe;
	}
}
