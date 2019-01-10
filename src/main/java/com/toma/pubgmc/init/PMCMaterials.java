package com.toma.pubgmc.init;

import com.toma.pubgmc.Pubgmc;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class PMCMaterials
{
	public static final ToolMaterial MATERIAL_PAN = EnumHelper.addToolMaterial("material_pan", 0, -1, 0.0F, 15.0F, 0);
	
	public static final ArmorMaterial GHILLIE_SUIT = EnumHelper.addArmorMaterial("ghillie_suit", Pubgmc.MOD_ID + ":ghillie_suit", -1, new int[] {0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0);
	public static final ArmorMaterial GHILLIE = EnumHelper.addArmorMaterial("ghillie", Pubgmc.MOD_ID + ":ghillie", -1, new int[] {1, 1, 1, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	public static final ArmorMaterial LVL1 = EnumHelper.addArmorMaterial("lvl1", Pubgmc.MOD_ID + ":lvl1", 1, new int[] {0, 0, 3, 3}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	public static final ArmorMaterial LVL2 = EnumHelper.addArmorMaterial("lvl2", Pubgmc.MOD_ID + ":lvl2", 1, new int[] {0, 0, 6, 6}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	public static final ArmorMaterial LVL3 = EnumHelper.addArmorMaterial("lvl3", Pubgmc.MOD_ID + ":lvl3", 1, new int[] {0, 0, 10, 10}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	public static final ArmorMaterial CLOTH1 = EnumHelper.addArmorMaterial("set1", Pubgmc.MOD_ID + ":set1", -1, new int[] {0,  0,  0,  0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	public static final ArmorMaterial CLOTH2 = EnumHelper.addArmorMaterial("set2", Pubgmc.MOD_ID + ":set2", -1, new int[] {0,  0,  0,  0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	public static final ArmorMaterial CLOTH3 = EnumHelper.addArmorMaterial("set3", Pubgmc.MOD_ID + ":set3", -1, new int[] {0,  0,  0,  0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	public static final ArmorMaterial HELMET3 = EnumHelper.addArmorMaterial("l3helmet", Pubgmc.MOD_ID + ":level3helmet", 1, new int[] {0, 0, 0, 10}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);
}
