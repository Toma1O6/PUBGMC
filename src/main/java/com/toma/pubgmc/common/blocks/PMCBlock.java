package com.toma.pubgmc.common.blocks;

import com.toma.pubgmc.PMCTabs;
import com.toma.pubgmc.init.PMCRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PMCBlock extends Block {

    private String[] desc;

    public PMCBlock(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(PMCTabs.TAB_BLOCKS);
        PMCRegistry.Registry.registerItemBlock(this);
        this.setHardness(0.7f);
    }

    public PMCBlock addDescription(String... lines) {
        this.desc = lines;
        return this;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        if(desc == null) {
            return;
        }
        for(int i = 0; i < desc.length; i++) {
            tooltip.add(desc[i]);
        }
    }
}
