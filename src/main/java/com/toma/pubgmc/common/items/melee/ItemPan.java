package com.toma.pubgmc.common.items.melee;

import com.toma.pubgmc.Pubgmc;
import net.minecraft.item.ItemSword;

public class ItemPan extends ItemSword {
    public ItemPan(String name, ToolMaterial material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Pubgmc.pmcitemstab);
    }
}
