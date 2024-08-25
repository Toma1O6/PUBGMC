package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.init.CommonRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class PMCBlock extends Block {

    private List<String> desc;

    public PMCBlock(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(PMCTabs.TAB_BLOCKS);
        CommonRegistry.registerItemBlock(this);
        this.setHardness(0.7f);
    }

    public void setDescription(String... lines) {
        this.desc = Arrays.asList(lines);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        if (desc == null) {
            return;
        }
        desc.stream().map(I18n::format).forEach(tooltip::add);
    }
}
