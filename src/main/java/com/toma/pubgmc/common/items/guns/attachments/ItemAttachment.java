package com.toma.pubgmc.common.items.guns.attachments;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.items.PMCItem;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemAttachment extends PMCItem implements IAttachment, ICraftable
{
	
	private final Type type;
	
	public ItemAttachment(String name, Type attachment) 
	{
		super(name);
		setMaxStackSize(1);
		setCreativeTab(Pubgmc.pmcitemstab);
		
		this.type = attachment;
		TileEntityGunWorkbench.ATTACHMENT.add(this);
	}
	
	@Override
	public Type getType()
	{
		return type;
	}
	
	/**
	 * Get the ID of an attachment for NBT
	 * @param item - the item which is being checked
	 * @return ID of an attachment
	 */
	public int getID(Item item)
	{
		int id = 0;
		
		if(item == PMCRegistry.PMCItems.SILENCER_PISTOL || item == PMCRegistry.PMCItems.SILENCER_SMG || item == PMCRegistry.PMCItems.SILENCER_AR || item == PMCRegistry.PMCItems.SILENCER_SNIPER) id = 1;
		if(item == PMCRegistry.PMCItems.COMPENSATOR_SMG || item == PMCRegistry.PMCItems.COMPENSATOR_AR || item == PMCRegistry.PMCItems.COMPENSATOR_SNIPER) id = 2;
		if(item == PMCRegistry.PMCItems.RED_DOT) id = 1;
		if(item == PMCRegistry.PMCItems.HOLOGRAPHIC) id = 2;
		if(item == PMCRegistry.PMCItems.SCOPE2X) id = 3;
		if(item == PMCRegistry.PMCItems.SCOPE4X) id = 4;
		if(item == PMCRegistry.PMCItems.SCOPE8X) id = 5;
		if(item == PMCRegistry.PMCItems.SCOPE15X) id = 6;
		if(item == PMCRegistry.PMCItems.GRIP_VERTICAL) id = 1;
		if(item == PMCRegistry.PMCItems.GRIP_ANGLED) id = 2;
		if(item == PMCRegistry.PMCItems.QUICKDRAW_MAG_PISTOL || item == PMCRegistry.PMCItems.QUICKDRAW_MAG_SMG || item == PMCRegistry.PMCItems.QUICKDRAW_MAG_AR || item == PMCRegistry.PMCItems.QUICKDRAW_MAG_SNIPER) id = 1;
		if(item == PMCRegistry.PMCItems.EXTENDED_MAG_PISTOL || item == PMCRegistry.PMCItems.EXTENDED_MAG_SMG || item == PMCRegistry.PMCItems.EXTENDED_MAG_AR || item == PMCRegistry.PMCItems.EXTENDED_MAG_SNIPER) id = 2;
		if(item == PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL || item == PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SMG || item == PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_AR || item == PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER) id = 3;
		if(item == PMCRegistry.PMCItems.BULLET_LOOPS_SHOTGUN || item == PMCRegistry.PMCItems.BULLET_LOOPS_SNIPER) id = 1;
		if(item == PMCRegistry.PMCItems.CHEEKPAD) id = 2;
		
		return id;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.BOLD + "Add attachment to weapon throught attachment GUI - press P");
		switch(type)
		{
			case SCOPE: {
				
				tooltip.add("Scopes are used for better accuracy on longer ranges."); break;
			}
			
			case MAGAZINE: {
				
				tooltip.add("Quickdraw magazine: Reduces reloading time by 30%");
				tooltip.add("Extended magazine: Increases bullet capacity for the weapon");
				tooltip.add("Extended quickdraw magazine: Reduces reloading time by 30% and increases bullet capacity."); break;
			}

			case GRIP: {
				
				tooltip.add("Vertical grip: Reduces vertical recoil");
				tooltip.add("Angled grip: Reduces horizontal recoil"); break;
			}

			case BARREL: {
				
				tooltip.add("Compensators greatly reduce both vertical and horizontal recoil"); break;
			}
			
			case STOCK: {
				
				tooltip.add("Bullet loops: Reduces reloading time by 30%");
				tooltip.add("Cheekpad: Slightly reduces both vertical and horizontal recoil"); break;
			}
		}
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe(Item item)
	{
		List<ItemStack> rec = new ArrayList<ItemStack>();
		
		//Barrel
		if(item == PMCRegistry.PMCItems.SILENCER_PISTOL)
		{
			rec.add(new ItemStack(Blocks.WOOL, 5, 15));
			rec.add(new ItemStack(Items.IRON_INGOT, 3));
			return rec;
		}
		
		else if(item == PMCRegistry.PMCItems.SILENCER_SMG)
		{
			rec.add(new ItemStack(Blocks.WOOL, 7, 15));
			rec.add(new ItemStack(Items.IRON_INGOT, 4));
			return rec;
		}
		
		else if(item == PMCRegistry.PMCItems.SILENCER_AR)
		{
			rec.add(new ItemStack(Blocks.WOOL, 10, 15));
			rec.add(new ItemStack(Items.IRON_INGOT, 7));
		}
		
		else if(item == PMCRegistry.PMCItems.SILENCER_SNIPER)
		{
			rec.add(new ItemStack(Blocks.WOOL, 15, 15));
			rec.add(new ItemStack(Items.IRON_INGOT, 10));
		}
		
		else if(item == PMCRegistry.PMCItems.COMPENSATOR_SMG)
		{
			rec.add(new ItemStack(Items.IRON_INGOT, 3));
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 3));
		}
		
		else if(item == PMCRegistry.PMCItems.COMPENSATOR_AR)
		{
			rec.add(new ItemStack(Items.IRON_INGOT, 6));
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 6));
		}
		
		else if(item == PMCRegistry.PMCItems.COMPENSATOR_SNIPER)
		{
			rec.add(new ItemStack(Items.IRON_INGOT, 8));
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 8));
		}
		
		//Scope
		else if(item == PMCRegistry.PMCItems.RED_DOT)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 5));
			rec.add(new ItemStack(Blocks.GLASS_PANE));
			rec.add(new ItemStack(Items.DYE, 1, 1));
		}
		
		else if(item == PMCRegistry.PMCItems.HOLOGRAPHIC)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 7));
			rec.add(new ItemStack(Blocks.GLASS_PANE));
			rec.add(new ItemStack(Items.DYE, 2, 1));
		}
		
		else if(item == PMCRegistry.PMCItems.SCOPE2X)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 10));
			rec.add(new ItemStack(Blocks.GLASS_PANE, 4));
			rec.add(new ItemStack(Items.DYE, 3, 10));
		}
		
		else if(item == PMCRegistry.PMCItems.SCOPE4X)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 20));
			rec.add(new ItemStack(Blocks.GLASS_PANE, 6));
			rec.add(new ItemStack(Items.DYE, 6, 1));
		}
		
		else if(item == PMCRegistry.PMCItems.SCOPE8X)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 30));
			rec.add(new ItemStack(Blocks.GLASS_PANE, 10));
			rec.add(new ItemStack(Items.DYE, 5));
		}
		
		else if(item == PMCRegistry.PMCItems.SCOPE15X)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 50));
			rec.add(new ItemStack(Blocks.GLASS_PANE, 16));
			rec.add(new ItemStack(Items.DYE, 15));
		}
		
		//Grips
		else if(item == PMCRegistry.PMCItems.GRIP_ANGLED || item == PMCRegistry.PMCItems.GRIP_VERTICAL)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 10));
			rec.add(new ItemStack(Items.IRON_INGOT, 10));
		}
		
		//Magazines
		else if(item == PMCRegistry.PMCItems.QUICKDRAW_MAG_PISTOL)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 3));
			rec.add(new ItemStack(Items.IRON_INGOT, 3));
		}
		
		else if(item == PMCRegistry.PMCItems.EXTENDED_MAG_PISTOL)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 3));
			rec.add(new ItemStack(Items.IRON_INGOT, 5));
		}
		
		else if(item == PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.QUICKDRAW_MAG_PISTOL));
			rec.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_MAG_PISTOL));
		}
		
		else if(item == PMCRegistry.PMCItems.QUICKDRAW_MAG_SMG)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 5));
			rec.add(new ItemStack(Items.IRON_INGOT, 5));
		}
		
		else if(item == PMCRegistry.PMCItems.EXTENDED_MAG_SMG)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 5));
			rec.add(new ItemStack(Items.IRON_INGOT, 8));
		}
		
		else if(item == PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SMG)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.QUICKDRAW_MAG_SMG));
			rec.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_MAG_SMG));
		}
		
		else if(item == PMCRegistry.PMCItems.QUICKDRAW_MAG_AR)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 8));
			rec.add(new ItemStack(Items.IRON_INGOT, 8));
		}
		
		else if(item == PMCRegistry.PMCItems.EXTENDED_MAG_AR)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 8));
			rec.add(new ItemStack(Items.IRON_INGOT, 15));
		}
		
		else if(item == PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_AR)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.QUICKDRAW_MAG_AR));
			rec.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_MAG_AR));
		}
		
		else if(item == PMCRegistry.PMCItems.QUICKDRAW_MAG_SNIPER)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 15));
			rec.add(new ItemStack(Items.IRON_INGOT, 15));
		}
		
		else if(item == PMCRegistry.PMCItems.EXTENDED_MAG_SNIPER)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 15));
			rec.add(new ItemStack(Items.IRON_INGOT, 25));
		}
		
		else if(item == PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER)
		{
			rec.add(new ItemStack(PMCRegistry.PMCItems.QUICKDRAW_MAG_SNIPER));
			rec.add(new ItemStack(PMCRegistry.PMCItems.EXTENDED_MAG_SNIPER));
		}
		
		else if(item == PMCRegistry.PMCItems.BULLET_LOOPS_SHOTGUN)
		{
			rec.add(new ItemStack(Blocks.PLANKS, 5));
			rec.add(new ItemStack(Items.IRON_INGOT, 3));
		}
		
		else if(item == PMCRegistry.PMCItems.BULLET_LOOPS_SNIPER)
		{
			rec.add(new ItemStack(Items.IRON_INGOT, 5));
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 3));
		}
		
		else if(item == PMCRegistry.PMCItems.CHEEKPAD)
		{
			rec.add(new ItemStack(Items.IRON_INGOT, 10));
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 10));
		}
		
		return rec;
	}
	
	@Override
	public CraftMode getCraftMode()
	{
		return CraftMode.Atachment;
	}
}
