package dev.toma.pubgmc;

import dev.toma.pubgmc.init.PMCBlocks;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PMCTabs {

    public static final CreativeTabs TAB_ITEMS = new CreativeTabs("tab_items") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(PMCItems.ARMOR3HELMET);
        }
    };
    public static final CreativeTabs TAB_BLOCKS = new CreativeTabs("tab_blocks") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(PMCBlocks.AIRDROP);
        }
    };
    public static final CreativeTabs TAB_GUNS = new CreativeTabs("tab_guns") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(PMCItems.MK14);
        }
    };
    public static final CreativeTabs TAB_ACCESSORIES = new CreativeTabs("tab_accessories") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(PMCItems.SCOPE4X);
        }
    };
    public static final CreativeTabs TAB_MAP = new CreativeTabs("tab_map") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(PMCItems.MAPITEM_SPAWNER);
        }
    };
}
