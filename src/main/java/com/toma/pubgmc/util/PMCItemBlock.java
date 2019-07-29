package com.toma.pubgmc.util;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * For registry stuff only
 **/
public class PMCItemBlock extends ItemBlock {
    public PMCItemBlock(Block block) {
        super(block);
        setUnlocalizedName(block.getUnlocalizedName());
        setRegistryName(block.getRegistryName());
    }
}
