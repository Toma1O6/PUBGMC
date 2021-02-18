package dev.toma.pubgmc.common.items;

import dev.toma.pubgmc.PMCTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PMCItem extends Item {
    public List<String> desc = null;

    public PMCItem(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(PMCTabs.TAB_ITEMS);
    }

    public Item addDescription(String... strings) {
        desc = new ArrayList<>();
        desc.addAll(Arrays.asList(strings));
        return this;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (desc != null) {
            tooltip.addAll(desc);
        }
    }

    public void warnPlayer(EntityPlayer player, String warning) {
        if (player != null && !player.world.isRemote) {
            player.sendMessage(new TextComponentString(TextFormatting.RED + warning));
        }
    }
}
