package com.toma.pubgmc.common.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.entity.EntityParachute;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemParachute extends PMCItem implements ICraftable
{
	private static final Random rand = new Random();
	
	public ItemParachute(String name) 
	{
		super(name);
		setCreativeTab(Pubgmc.pmcitemstab);
		setMaxStackSize(1);
		TileEntityGunWorkbench.CLOTHING.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack stack = playerIn.getHeldItem(handIn);
		
		if(!playerIn.isRiding())
		{
			EntityParachute chute = new EntityParachute(worldIn, playerIn);
			if(!worldIn.isRemote)
			{
				worldIn.spawnEntity(chute);
				
				if(!playerIn.capabilities.isCreativeMode)
				{
					stack.shrink(1);
				}
			}
			
			else playerIn.playSound(PMCSounds.chute_open, 1f, 1f);
			
			playerIn.startRiding(chute);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe()
	{
		List<ItemStack> recipe = new ArrayList<ItemStack>();
		recipe.add(new ItemStack(Blocks.WOOL, 15));
		recipe.add(new ItemStack(Items.STRING, 20));
		return recipe;
	}
	
	@Override
	public CraftMode getCraftMode()
	{
		return CraftMode.CLOTHING;
	}
}
