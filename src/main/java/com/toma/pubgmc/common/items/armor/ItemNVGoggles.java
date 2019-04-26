package com.toma.pubgmc.common.items.armor;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.items.PMCItem;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemNVGoggles extends PMCItem implements ICraftable
{
	private ArrayList<ItemStack> recipe = new ArrayList<ItemStack>();
	
	public ItemNVGoggles(String name) 
	{
		super(name);
		this.setMaxStackSize(1);
		this.setCreativeTab(Pubgmc.pmcitemstab);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack stack = playerIn.getHeldItem(handIn);
		IPlayerData data = playerIn.getCapability(PlayerDataProvider.PLAYER_DATA, null);
		
		if(!data.getEquippedNV())
		{
			data.hasEquippedNV(true);
			playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0f, 0f);
				
			if(!playerIn.capabilities.isCreativeMode)
			{
				stack.shrink(1);
			}
				
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
	}
	
	@Override
	public void initCraftingRecipe()
	{
		List<ItemStack> recipe = new ArrayList<ItemStack>();
		recipe.add(new ItemStack(Blocks.GLASS_PANE, 4));
		recipe.add(new ItemStack(Blocks.GLOWSTONE));
		recipe.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 8));
		recipe.add(new ItemStack(Blocks.WOOL, 2));
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
