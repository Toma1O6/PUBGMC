package dev.toma.pubgmc.init;

import dev.toma.pubgmc.Pubgmc;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public final class ToolMaterials {
    public static final Item.ToolMaterial MATERIAL_PAN = EnumHelper.addToolMaterial("material_pan", 0, -1, 0.0F, 15.0F, 0);
    public static final ItemArmor.ArmorMaterial GHILLIE_SUIT = EnumHelper.addArmorMaterial("ghillie_suit", Pubgmc.MOD_ID + ":ghillie_suit", -1, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0);
    public static final ItemArmor.ArmorMaterial LV1_HELMET = EnumHelper.addArmorMaterial("lvl1", Pubgmc.MOD_ID + ":lvl1", 8, new int[]{0, 0, 0, 3}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
    public static final ItemArmor.ArmorMaterial LV1_VEST = EnumHelper.addArmorMaterial("lvl1", Pubgmc.MOD_ID + ":lvl1", 12, new int[]{0, 0, 8, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
    public static final ItemArmor.ArmorMaterial LV2_HELMET = EnumHelper.addArmorMaterial("lvl2", Pubgmc.MOD_ID + ":lvl2", 13, new int[]{0, 0, 0, 5}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
    public static final ItemArmor.ArmorMaterial LV2_VEST = EnumHelper.addArmorMaterial("lvl1", Pubgmc.MOD_ID + ":lvl1", 14, new int[]{0, 0, 10, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
    public static final ItemArmor.ArmorMaterial LV3_HELMET = EnumHelper.addArmorMaterial("lvl3", Pubgmc.MOD_ID + ":lvl3", 21, new int[]{0, 0, 0, 7}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2);
    public static final ItemArmor.ArmorMaterial LV3_VEST = EnumHelper.addArmorMaterial("lvl3", Pubgmc.MOD_ID + ":lvl3", 16, new int[]{0, 0, 13, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2);
}
