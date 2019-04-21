package com.toma.pubgmc.event;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.attachments.IAttachment;
import com.toma.pubgmc.common.items.guns.attachments.ItemAttachment;
import com.toma.pubgmc.common.items.guns.attachments.IAttachment.Type;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;

public class GunPostInitializeEvent extends Event
{
	private final GunBase gun;
	
	public GunPostInitializeEvent(GunBase gun)
	{
		this.gun = gun;
	}
	
	public GunBase getGun()
	{
		return gun;
	}
	
	public void addCraftingIngredient(ItemStack stack)
	{
		gun.getCraftingRecipe().add(stack);
	}
	
	public void initBarrelAttachments(ItemAttachment... attachments)
	{
		List<ItemAttachment> list = new ArrayList<ItemAttachment>();
		for(ItemAttachment a : attachments)
		{
			list.add(a);
		}
		
		this.gun.setBarrelAttachments(list.toArray(new ItemAttachment[0]));
	}
	
	public void initGripAttachments(ItemAttachment... attachments)
	{
		List<ItemAttachment> list = new ArrayList<ItemAttachment>();
		for(ItemAttachment a : attachments)
		{
			list.add(a);
		}
		
		this.gun.setGripAttachments(list.toArray(new ItemAttachment[0]));
	}
	
	public void initMagazineAttachments(ItemAttachment... attachments)
	{
		List<ItemAttachment> list = new ArrayList<ItemAttachment>();
		for(ItemAttachment a : attachments)
		{
			list.add(a);
		}
		
		this.gun.setMagazineAttachments(list.toArray(new ItemAttachment[0]));
	}
	
	public void initStockAttachments(ItemAttachment... attachments)
	{
		List<ItemAttachment> list = new ArrayList<ItemAttachment>();
		for(ItemAttachment a : attachments)
		{
			list.add(a);
		}
		
		this.gun.setStockAttachments(list.toArray(new ItemAttachment[0]));
	}
	
	public void initScopeAttachments(ItemAttachment... attachments)
	{
		List<ItemAttachment> list = new ArrayList<ItemAttachment>();
		for(ItemAttachment a : attachments)
		{
			list.add(a);
		}
		
		this.gun.setScopeAttachments(list.toArray(new ItemAttachment[0]));
	}
}
