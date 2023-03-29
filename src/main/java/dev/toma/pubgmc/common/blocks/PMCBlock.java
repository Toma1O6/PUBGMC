package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.init.CommonRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class PMCBlock extends Block {

    private String[] desc;

    public PMCBlock(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(PMCTabs.TAB_BLOCKS);
        CommonRegistry.registerItemBlock(this);
        this.setHardness(0.7f);
    }

    public PMCBlock addDescription(String... lines) {
        this.desc = lines;
        return this;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        if (desc == null) {
            return;
        }
        Collections.addAll(tooltip, desc);
    }
}
