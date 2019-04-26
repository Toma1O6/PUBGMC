package com.toma.pubgmc.common.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBackpack extends PMCItem implements ICraftable
{
	private ArrayList<ItemStack> recipe = new ArrayList<ItemStack>();
	
	public ItemBackpack(String name)
	{
		super(name);
		this.setCreativeTab(Pubgmc.pmcitemstab);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack stack = playerIn.getHeldItem(handIn);
		IPlayerData data = playerIn.getCapability(PlayerDataProvider.PLAYER_DATA, null);

		if(stack.getItem() == PMCRegistry.PMCItems.BACKPACK1 && data.getBackpackLevel() == 0)
		{
			if(!playerIn.capabilities.isCreativeMode)
			{
				stack.shrink(1);
			}
				
			data.setBackpackLevel(1);
		}
			
		if(stack.getItem() == PMCRegistry.PMCItems.BACKPACK2 && (data.getBackpackLevel() == 0 || data.getBackpackLevel() == 1))
		{
			if(!playerIn.capabilities.isCreativeMode)
			{
				stack.shrink(1);
			}
				
			if(data.getBackpackLevel() == 1)
			{
				playerIn.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.BACKPACK1));
			}
				
			data.setBackpackLevel(2);
		}
			
		if(stack.getItem() == PMCRegistry.PMCItems.BACKPACK3 && data.getBackpackLevel() < 3)
		{
			if(!playerIn.capabilities.isCreativeMode)
			{
				stack.shrink(1);
			}
				
			if(data.getBackpackLevel() == 1)
			{
				playerIn.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.BACKPACK1));
			}
				
			if(data.getBackpackLevel() == 2)
			{
				playerIn.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.BACKPACK2));
			}
				
			data.setBackpackLevel(3);	
		}
		
		clearIcons(playerIn.inventory);
		
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
	}
	
	private static void clearIcons(InventoryPlayer inv)
	{
		inv.clearMatchingItems(PMCRegistry.PMCItems.IBLOCK, 0, inv.getSizeInventory() * 64, null);
	}
	
	@Override
	public void initCraftingRecipe()
	{
		recipe.clear();
		if(this == PMCRegistry.PMCItems.BACKPACK1)
		{
			recipe.add(new ItemStack(Blocks.CHEST));
			recipe.add(new ItemStack(Items.LEATHER, 10));
		}
		
		else if(this == PMCRegistry.PMCItems.BACKPACK2)
		{
			recipe.add(new ItemStack(PMCRegistry.PMCItems.BACKPACK1));
			recipe.add(new ItemStack(Items.LEATHER, 20));
		}
		
		else if(this == PMCRegistry.PMCItems.BACKPACK3)
		{
			recipe.add(new ItemStack(PMCRegistry.PMCItems.BACKPACK2));
			recipe.add(new ItemStack(Items.LEATHER, 30));
		}
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe()
	{
		return recipe;
	}
	
	@Override
	public CraftMode getCraftMode() 
	{
		return CraftMode.CLOTHING;
	}
}
