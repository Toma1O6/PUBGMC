package com.toma.pubgmc.common.items.armor;

import java.util.ArrayList;
import java.util.List;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench.CraftMode;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.ICraftable;

import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ArmorBase extends ItemArmor implements ICraftable
{
	private ArmorLevel level = null;
	private ArrayList<ItemStack> recipe = new ArrayList<ItemStack>();
	
	public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Pubgmc.pmcitemstab);
	}
	
	public ArmorBase setArmorLevel(ArmorLevel level)
	{
		this.level = level;
		return this;
	}
	
	public ArmorLevel armorLevel()
	{
		return level;
	}
	
	@Override
	public CraftMode getCraftMode() 
	{
		return CraftMode.CLOTHING;
	}
	
	@Override
	public List<ItemStack> getCraftingRecipe() 
	{
		return recipe;
	}
	
	@Override
	public void initCraftingRecipe()
	{
		if(this == PMCRegistry.PMCItems.ARMOR1BODY)
		{
			recipe.add(new ItemStack(Items.LEATHER_CHESTPLATE));
			recipe.add(new ItemStack(Items.IRON_INGOT, 3));
		}
		
		else if(this == PMCRegistry.PMCItems.ARMOR1HELMET)
		{
			recipe.add(new ItemStack(Items.LEATHER_HELMET));
			recipe.add(new ItemStack(Items.IRON_INGOT, 3));
		}
		
		else if(this == PMCRegistry.PMCItems.ARMOR2BODY)
		{
			recipe.add(new ItemStack(PMCRegistry.PMCItems.ARMOR1BODY));
			recipe.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 3));
			recipe.add(new ItemStack(Items.GOLD_INGOT, 1));
		}
		
		else if(this == PMCRegistry.PMCItems.ARMOR2HELMET)
		{
			recipe.add(new ItemStack(PMCRegistry.PMCItems.ARMOR1HELMET));
			recipe.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 3));
			recipe.add(new ItemStack(Items.GOLD_INGOT, 1));
		}
		
		else if(this == PMCRegistry.PMCItems.ARMOR3BODY)
		{
			recipe.add(new ItemStack(PMCRegistry.PMCItems.ARMOR2BODY));
			recipe.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 6));
			recipe.add(new ItemStack(Items.GOLD_INGOT, 2));
		}
		
		else if(this == PMCRegistry.PMCItems.ARMOR3HELMET)
		{
			recipe.add(new ItemStack(PMCRegistry.PMCItems.ARMOR2HELMET));
			recipe.add(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 6));
			recipe.add(new ItemStack(Items.GOLD_INGOT, 2));
		}
	}
	
	public enum ArmorLevel
	{
		LEVEL_ONE(0),
		LEVEL_TWO(1),
		LEVEL_THREE(2);
		
		int level;
		static final ResourceLocation[][] ICONS =
		{
			{
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest1_full.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest1_damaged.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest1_broken.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet1_full.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet1_damaged.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet1_broken.png")
			},
			
			{
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest2_full.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest2_damaged.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest2_broken.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet2_full.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet2_damaged.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet2_broken.png")
			},
			
			{
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest3_full.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest3_damaged.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vest3_broken.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet3_full.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet3_damaged.png"),
				new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/helmet3_broken.png")
			}
		};
		
		ArmorLevel(int level)
		{
			this.level = level;
		}
		
		public ResourceLocation[][] getIcons()
		{
			return ICONS;
		}
		
		/**
		 * @param helmet
		 * @param armorLevel [0-2]
		 * @param damagePhase [0-2] (full_hp, damaged, broken)
		 * @return icon
		 */
		public ResourceLocation getIcon(boolean helmet, int armorLevel, int damagePhase)
		{
			int gear = helmet ? 3 : 0;
			return ICONS[armorLevel][gear + damagePhase];
		}
		
		public int getArmorLevel()
		{
			return level;
		}
	}
}
