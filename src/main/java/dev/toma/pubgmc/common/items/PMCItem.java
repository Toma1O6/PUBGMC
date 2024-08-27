package dev.toma.pubgmc.common.items;

import dev.toma.pubgmc.PMCTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class PMCItem extends Item {

    public PMCItem(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(PMCTabs.TAB_ITEMS);
    }

    public void warnPlayer(EntityPlayer player, String warning) {
        if (player != null && !player.world.isRemote) {
            player.sendStatusMessage(new TextComponentString(TextFormatting.RED + warning), true);
        }
    }
}
