package com.toma.pubgmc.common.items.armor;

import com.toma.pubgmc.Pubgmc;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemClothing extends ItemArmor {
    public ItemClothing(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Pubgmc.TAB_ITEMS);
    }
}
