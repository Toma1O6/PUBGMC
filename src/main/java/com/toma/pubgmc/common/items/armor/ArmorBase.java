package com.toma.pubgmc.common.items.armor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ArmorBase extends ItemArmor implements ICraftable
{
	public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Pubgmc.pmcitemstab);
		TileEntityGunWorkbench.CLOTHING.add(this);
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> recipe = new ArrayList<ItemStack>();
		
		if(item == PMCItems.ARMOR1BODY)
		{
			recipe.add(new ItemStack(Items.LEATHER_CHESTPLATE));
			recipe.add(new ItemStack(Items.IRON_INGOT, 3));
			return recipe;
		}
		
		else if(item == PMCItems.ARMOR1HELMET)
		{
			recipe.add(new ItemStack(Items.LEATHER_HELMET));
			recipe.add(new ItemStack(Items.IRON_INGOT, 3));
			return recipe;
		}
		
		else if(item == PMCItems.ARMOR2BODY)
		{
			recipe.add(new ItemStack(PMCItems.ARMOR1BODY));
			recipe.add(new ItemStack(PMCItems.STEEL_INGOT, 3));
			return recipe;
		}
		
		else if(item == PMCItems.ARMOR2HELMET)
		{
			recipe.add(new ItemStack(PMCItems.ARMOR1HELMET));
			recipe.add(new ItemStack(PMCItems.STEEL_INGOT, 3));
			return recipe;
		}
		
		else if(item == PMCItems.ARMOR3BODY)
		{
			recipe.add(new ItemStack(PMCItems.ARMOR2BODY));
			recipe.add(new ItemStack(PMCItems.STEEL_INGOT, 6));
			return recipe;
		}
		
		else if(item == PMCItems.ARMOR3HELMET)
		{
			recipe.add(new ItemStack(PMCItems.ARMOR2HELMET));
			recipe.add(new ItemStack(PMCItems.STEEL_INGOT, 6));
			return recipe;
		}
		
		else return Collections.EMPTY_LIST;
	}
	
	@Override
	public CraftMode getCraftMode()
	{
		return CraftMode.Clothing;
	}
}
