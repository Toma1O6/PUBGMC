package dev.toma.pubgmc;

import dev.toma.pubgmc.init.PMCRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PMCTabs {

    public static final CreativeTabs TAB_ITEMS = new CreativeTabs("tab_items") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(PMCRegistry.PMCItems.ARMOR3HELMET);
        }
    };
    public static final CreativeTabs TAB_BLOCKS = new CreativeTabs("tab_blocks") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(PMCRegistry.PMCBlocks.AIRDROP);
        }
    };
    public static final CreativeTabs TAB_GUNS = new CreativeTabs("tab_guns") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(PMCRegistry.PMCItems.MK14);
        }
    };
    public static final CreativeTabs TAB_ACCESSORIES = new CreativeTabs("tab_accessories") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(PMCRegistry.PMCItems.SCOPE4X);
        }
    };
}
