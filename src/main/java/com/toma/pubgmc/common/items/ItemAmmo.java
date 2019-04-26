package com.toma.pubgmc.common.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.items.guns.AmmoType;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemAmmo extends PMCItem implements ICraftable
{
	public final AmmoType type;
	private ArrayList<ItemStack> rec = new ArrayList<ItemStack>();
	
	public ItemAmmo(String name, AmmoType type)
	{
		super(name);
		this.setMaxStackSize(30);
		this.type = type;
		setCreativeTab(Pubgmc.pmcitemstab);
	}
	
	public Item getAmmoItem()
	{
		switch(type)
		{
			case AMMO9MM: return PMCRegistry.PMCItems.AMMO_9MM;
			case AMMO45ACP: return PMCRegistry.PMCItems.AMMO_45ACP;
			case AMMO12G: return PMCRegistry.PMCItems.AMMO_SHOTGUN;
			case AMMO556: return PMCRegistry.PMCItems.AMMO_556;
			case AMMO762: return PMCRegistry.PMCItems.AMMO_762;
			case AMMO300M: return PMCRegistry.PMCItems.AMMO_300M;
			case FLARE: return PMCRegistry.PMCItems.AMMO_FLARE;
		}
		
		return Items.AIR;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		switch(type)
		{
			case AMMO9MM: tooltip.add(TextFormatting.GOLD + "For guns: P92, P18C, Scorpion, PP-19 Bizon, Micro-Uzi, Ump-9, VSS"); break;
			case AMMO45ACP: tooltip.add(TextFormatting.AQUA + "For guns: P1911, R45, Vector, Tommy Gun, Win-94"); break;
			case AMMO12G: tooltip.add(TextFormatting.RED + "For guns: S1897, S686, Sawed-off, S12K"); break;
			case AMMO556: tooltip.add(TextFormatting.GREEN + "For guns: M16A4, M416, Scar-L, G36C, QBZ, QBU, Mini-14, Aug, M249"); break;
			case AMMO762: tooltip.add(TextFormatting.YELLOW + "For guns: R1815, AKM, MK47, M762, SKS, SLR, Kar98-k, M24"); break;
			case AMMO300M: tooltip.add(TextFormatting.DARK_GREEN + "For guns: AWM"); break;
			case FLARE: tooltip.add(TextFormatting.DARK_RED + "For guns: Flare gun"); break;
		}
	}
	
	@Override
	public void initCraftingRecipe()
	{
		rec.clear();
		if(this == PMCRegistry.PMCItems.AMMO_9MM)
		{
			rec.add(new ItemStack(Items.GOLD_NUGGET, 15));
			rec.add(new ItemStack(PMCRegistry.PMCItems.COPPER_INGOT, 1));
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 1));
			rec.add(new ItemStack(Items.GUNPOWDER, 1));
		}
		
		else if(this == PMCRegistry.PMCItems.AMMO_SHOTGUN)
		{
			rec.add(new ItemStack(Items.GOLD_NUGGET, 10));
			rec.add(new ItemStack(Items.IRON_NUGGET, 8));
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 1));
			rec.add(new ItemStack(Items.GUNPOWDER, 3));
		}
		
		else if(this == PMCRegistry.PMCItems.AMMO_45ACP)
		{
			rec.add(new ItemStack(Items.IRON_NUGGET, 5));
			rec.add(new ItemStack(Items.GOLD_NUGGET, 15));
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 1));
			rec.add(new ItemStack(Items.GUNPOWDER, 1));
		}
		
		else if(this == PMCRegistry.PMCItems.AMMO_556)
		{
			rec.add(new ItemStack(Items.IRON_NUGGET, 5));
			rec.add(new ItemStack(Items.GOLD_NUGGET, 5));
			rec.add(new ItemStack(PMCRegistry.PMCItems.COPPER_INGOT, 1));
			rec.add(new ItemStack(Items.GUNPOWDER, 2));
		}
		
		else if(this == PMCRegistry.PMCItems.AMMO_762)
		{
			rec.add(new ItemStack(Items.IRON_NUGGET, 7));
			rec.add(new ItemStack(Items.GOLD_NUGGET, 7));
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT));
			rec.add(new ItemStack(Items.GUNPOWDER, 2));
		}
		
		else if(this == PMCRegistry.PMCItems.AMMO_300M)
		{
			rec.add(new ItemStack(Items.IRON_INGOT));
			rec.add(new ItemStack(Items.GOLD_INGOT));
			rec.add(new ItemStack(PMCRegistry.PMCItems.COPPER_INGOT));
			rec.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 3));
			rec.add(new ItemStack(Items.DIAMOND));
			rec.add(new ItemStack(Items.GUNPOWDER, 5));
		}
		
		else if(this == PMCRegistry.PMCItems.AMMO_FLARE)
		{
			rec.add(new ItemStack(Items.DIAMOND, 5));
			rec.add(new ItemStack(Items.IRON_INGOT));
			rec.add(new ItemStack(Items.GUNPOWDER, 10));
			rec.add(new ItemStack(Items.BLAZE_POWDER, 3));
			rec.add(new ItemStack(Items.DYE, 5, 1));
		}
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe()
	{
		return rec;
	}
	
	public int getCraftCount(Item item)
	{
		int i = 0;
		switch(type)
		{
			case AMMO9MM: return i = 30;
			case AMMO45ACP: return i = 15;
			case AMMO12G: return i = 5;
			case AMMO556: return i = 20;
			case AMMO762: return i = 15;
			case AMMO300M: return i = 5;
			case FLARE: return i = 1;
		}
		return i;
	}
	
	@Override
	public CraftMode getCraftMode()
	{
		return CraftMode.AMMO;
	}
}
