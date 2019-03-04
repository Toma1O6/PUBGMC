package com.toma.pubgmc.common.items;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.Pubgmc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PMCItem extends Item
{
	public List<String> desc = new ArrayList<String>();
	
	public PMCItem(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Pubgmc.pmcitemstab);
	}
	
	public Item addDescription(String... strings)
	{
		for(String s : strings)
		{
			desc.add(s);
		}
		
		return this;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if(!desc.isEmpty())
		{
			for(int i = 0; i < desc.size(); i++)
			{
				tooltip.add(desc.get(i));
			}
		}
	}
}
