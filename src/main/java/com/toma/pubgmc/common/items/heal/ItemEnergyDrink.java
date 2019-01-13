package com.toma.pubgmc.common.items.heal;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.items.PMCItem;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemEnergyDrink extends PMCItem implements ICraftable
{

	public ItemEnergyDrink(String name) 
	{
		super(name);
		this.setCreativeTab(Pubgmc.pmcitemstab);
		this.setMaxStackSize(3);
		TileEntityGunWorkbench.HEALING.add(this);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
		if(entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entityLiving;
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			data.addBoost(40f);
			
			if(!player.capabilities.isCreativeMode)
			{
				stack.shrink(1);
			}
			
			player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0f, 0.0f);
		}
		
		return stack;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		return 80;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) 
	{
		return EnumAction.DRINK;
	}
	
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
	
    @Override
    public List<ItemStack> getCraftingRecipe(Item item)
    {
    	List<ItemStack> recipe = new ArrayList<ItemStack>();
    	recipe.add(new ItemStack(Items.GLASS_BOTTLE));
    	recipe.add(new ItemStack(Items.SUGAR, 10));
    	recipe.add(new ItemStack(Items.APPLE, 3));
    	return recipe;
    }
    
    @Override
    public CraftMode getCraftMode()
    {
    	return CraftMode.Healing;
    }
}
