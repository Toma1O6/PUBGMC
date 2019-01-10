package com.toma.pubgmc.common.items.heal;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.items.PMCItem;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBandage extends PMCItem implements ICraftable
{
	public ItemBandage(String name)
	{
		super(name);
		this.setMaxStackSize(5);
		this.setCreativeTab(Pubgmc.pmcitemstab);
		TileEntityGunWorkbench.HEALING.add(this);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
		if(entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(player.getHealth() == 14)
			{
				if(!player.capabilities.isCreativeMode)
				{
					stack.shrink(1);
				}
				
				if(!worldIn.isRemote)
				{
					player.setHealth(15);
				}
			}
			
			if(player.getHealth() < 14)
			{
				if(!player.capabilities.isCreativeMode)
				{
					stack.shrink(1);
				}
				
				if(!worldIn.isRemote)
				{
					player.heal(2);
				}
			}
		}
		
		return stack;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(!worldIn.isRemote)
		{
			if(playerIn.getHealth() == 14)
			{
				playerIn.setActiveHand(handIn);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
			}
			
			if(playerIn.getHealth() < 14)
			{
				playerIn.setActiveHand(handIn);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
			}
			
			if(playerIn.getHealth() > 14)
			{
				warnPlayer(playerIn);
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 80;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.NONE;
	}
	
	private void warnPlayer(EntityPlayer player)
	{
		player.sendMessage(new TextComponentString(TextFormatting.RED + "You can't heal above 75% of your health!"));
	}
	
	@Override
	public CraftMode getCraftMode()
	{
		return CraftMode.Healing;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> recipe = new ArrayList<ItemStack>();
		recipe.add(new ItemStack(Items.PAPER, 5));
		recipe.add(new ItemStack(Blocks.WOOL));
		return recipe;
	}
}
