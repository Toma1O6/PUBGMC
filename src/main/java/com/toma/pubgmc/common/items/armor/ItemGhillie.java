package com.toma.pubgmc.common.items.armor;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.models.ModelGhillie;
import com.toma.pubgmc.common.items.PMCItem;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGhillie extends PMCItem implements ICraftable
{
	
	public ItemGhillie(String name)
	{
		super(name);
		/*setUnlocalizedName(name);
		setRegistryName(name);*/
		this.setMaxStackSize(1);
		this.setCreativeTab(Pubgmc.pmcitemstab);
		TileEntityGunWorkbench.CLOTHING.add(this);
	}
    
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	tooltip.add(TextFormatting.GREEN + "Right click to get whole ghillie armor set!");
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack stack = playerIn.getHeldItem(handIn);
    	if(!worldIn.isRemote)
    	{
			playerIn.addItemStackToInventory(new ItemStack(PMCItems.GHILLIEHELMET));
			playerIn.addItemStackToInventory(new ItemStack(PMCItems.GHILLIEBODY));
			playerIn.addItemStackToInventory(new ItemStack(PMCItems.GHILLIELEGS));
			playerIn.addItemStackToInventory(new ItemStack(PMCItems.GHILLIEBOOTS));
			
			if(!playerIn.capabilities.isCreativeMode)
			{
				stack.shrink(1);
			}
		}
    	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
    
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> recipe = new ArrayList<ItemStack>();
		recipe.add(new ItemStack(Items.LEATHER_HELMET));
		recipe.add(new ItemStack(Items.LEATHER_CHESTPLATE));
		recipe.add(new ItemStack(Items.LEATHER_LEGGINGS));
		recipe.add(new ItemStack(Blocks.LEAVES, 45));
		return recipe;
	}
	
	@Override
	public CraftMode getCraftMode()
	{
		return CraftMode.Clothing;
	}
}
