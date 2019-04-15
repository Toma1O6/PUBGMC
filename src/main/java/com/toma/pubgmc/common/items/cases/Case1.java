package com.toma.pubgmc.common.items.cases;

import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.items.PMCItem;
import com.toma.pubgmc.init.PMCRegistry;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class Case1 extends PMCItem
{

	public Case1(String name)
	{
		super(name);
		this.setCreativeTab(Pubgmc.pmcitemstab);
	}
	
    @Override
    public int getItemStackLimit()
    {
    	return 5;
    }
   
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand hand)
	{
		ItemStack stack = entity.getHeldItem(hand);
		
		//LEGENDARY
		if(!world.isRemote)
		{
			if(Math.random() * 100 <= 0.25)
			{		
				entity.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.OFFICIAL_LEGS, 64));
			}
			
			//VERY RARE
			else if (Math.random() * 100 <= 1.75)
			{
				entity.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.BLACK_GLASSES, 64));
			}
			
			//RARE
			else if(Math.random() * 100 <= 10)
			{
				entity.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.WHITE_BOOTS, 64));
			}
			
			//COMMON
			else if(Math.random() * 100 <= 40)
			{
				entity.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.GRAY_TOP, 64));
			}
			
			else if(Math.random() * 100 <= 40)
			{
				entity.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.YELLOW_TSHIRT, 64));
			}
			
			else
			{
				entity.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.BROWN_CAP, 64));
			}
		}
		
		entity.playSound(SoundEvents.BLOCK_CHEST_OPEN, 0.5f, 0f);
		stack.shrink(1);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.GOLD + "Legendary PMC Leggings" + TextFormatting.RESET + " - 0.25%");
		tooltip.add(TextFormatting.RED + "Sunglasses" + TextFormatting.RESET + " - 1.75%");
		tooltip.add(TextFormatting.AQUA + "White Boots" + TextFormatting.RESET + " - 10%");
		tooltip.add(TextFormatting.GRAY + "Gray Top" + TextFormatting.RESET + " - ~30%");
		tooltip.add(TextFormatting.GRAY + "Yellow T-Shirt" + TextFormatting.RESET + " - ~30%");
		tooltip.add(TextFormatting.GRAY + "Brown Cap" + TextFormatting.RESET + " - ~30%");
	}
}
