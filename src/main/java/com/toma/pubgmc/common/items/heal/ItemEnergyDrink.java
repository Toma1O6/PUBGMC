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

public class ItemEnergyDrink extends ItemHealing
{

	public ItemEnergyDrink(String name) 
	{
		super(name);
		this.setMaxStackSize(3);
	}
	
	@Override
	public Action getAction()
	{
		return Action.BOOST;
	}
	
	@Override
	public EnumAction getUseAction() 
	{
		return EnumAction.DRINK;
	}
	
	@Override
	public int getUseTime()
	{
		return 80;
	}
	
	@Override
	public float getBoostAmount()
	{
		return 40f;
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
}
