package com.toma.pubgmc.common.items.heal;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.items.PMCItem;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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

public class ItemMedkit extends PMCItem implements ICraftable
{	
	public ItemMedkit(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		this.setCreativeTab(Pubgmc.pmcitemstab);
		TileEntityGunWorkbench.HEALING.add(this);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
		if(entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(!player.capabilities.isCreativeMode && player.getHealth() < 20)
			{
				stack.shrink(1);
			}
			
			if(player.getHealth() < 20 && !worldIn.isRemote)
			{
				player.setHealth(20);
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
			if(this.canHeal(playerIn))
			{		
				playerIn.setActiveHand(handIn);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
	}
	
	private boolean canHeal(EntityPlayer player)
	{
		if(player.getHealth() < 20)
		{
			return true;
		}
		
		else
		{
			player.sendMessage(new TextComponentString(TextFormatting.RED + "You can't recover health above your max health"));
			return false;
		}
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 160;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.NONE;
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
		recipe.add(new ItemStack(PMCItems.FIRSTAIDKIT));
		recipe.add(new ItemStack(PMCItems.BANDAGE, 2));
		recipe.add(new ItemStack(Items.PAPER, 3));
		return recipe;
	}
}
