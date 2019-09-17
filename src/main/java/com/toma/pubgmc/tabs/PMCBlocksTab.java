package com.toma.pubgmc.tabs;

import com.toma.pubgmc.init.PMCRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PMCBlocksTab extends CreativeTabs {
    public PMCBlocksTab(String label) {
        super(label);
    }

    public ItemStack getTabIconItem() {
        return new ItemStack(PMCRegistry.PMCBlocks.CRATE);
    }
}
