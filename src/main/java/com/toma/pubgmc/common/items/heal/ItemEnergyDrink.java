package com.toma.pubgmc.common.items.heal;

import net.minecraft.item.EnumAction;

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
}
