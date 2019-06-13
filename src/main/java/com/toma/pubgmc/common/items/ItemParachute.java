package com.toma.pubgmc.common.items;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.entity.EntityParachute;
import com.toma.pubgmc.init.PMCSounds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemParachute extends PMCItem
{
	public ItemParachute(String name) 
	{
		super(name);
		setCreativeTab(Pubgmc.pmcitemstab);
		setMaxStackSize(1);
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
}
