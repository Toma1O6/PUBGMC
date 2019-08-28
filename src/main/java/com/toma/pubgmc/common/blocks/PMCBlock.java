package com.toma.pubgmc.common.blocks;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.init.PMCRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class PMCBlock extends Block {
    public PMCBlock(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Pubgmc.pmcblockstab);
        PMCRegistry.Registry.registerItemBlock(this);
        this.setHardness(0.7f);
    }
}
