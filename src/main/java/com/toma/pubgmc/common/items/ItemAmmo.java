package com.toma.pubgmc.common.items;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.items.guns.AmmoType;
import com.toma.pubgmc.init.PMCRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemAmmo extends PMCItem {
    public final AmmoType type;

    public ItemAmmo(String name, AmmoType type) {
        super(name);
        this.setMaxStackSize(30);
        this.type = type;
        setCreativeTab(Pubgmc.pmcitemstab);
    }

    public Item getAmmoItem() {
        switch (type) {
            case AMMO9MM:
                return PMCRegistry.PMCItems.AMMO_9MM;
            case AMMO45ACP:
                return PMCRegistry.PMCItems.AMMO_45ACP;
            case AMMO12G:
                return PMCRegistry.PMCItems.AMMO_SHOTGUN;
            case AMMO556:
                return PMCRegistry.PMCItems.AMMO_556;
            case AMMO762:
                return PMCRegistry.PMCItems.AMMO_762;
            case AMMO300M:
                return PMCRegistry.PMCItems.AMMO_300M;
            case FLARE:
                return PMCRegistry.PMCItems.AMMO_FLARE;
        }

        return Items.AIR;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        switch (type) {
            case AMMO9MM:
                tooltip.add(TextFormatting.GOLD + "For guns: P92, P18C, Scorpion, PP-19 Bizon, Micro-Uzi, Ump-9, VSS");
                break;
            case AMMO45ACP:
                tooltip.add(TextFormatting.AQUA + "For guns: P1911, R45, Vector, Tommy Gun, Win-94");
                break;
            case AMMO12G:
                tooltip.add(TextFormatting.RED + "For guns: S1897, S686, Sawed-off, S12K");
                break;
            case AMMO556:
                tooltip.add(TextFormatting.GREEN + "For guns: M16A4, M416, Scar-L, G36C, QBZ, QBU, Mini-14, Aug, M249");
                break;
            case AMMO762:
                tooltip.add(TextFormatting.YELLOW + "For guns: R1815, AKM, MK47, M762, SKS, SLR, Kar98-k, M24");
                break;
            case AMMO300M:
                tooltip.add(TextFormatting.DARK_GREEN + "For guns: AWM");
                break;
            case FLARE:
                tooltip.add(TextFormatting.DARK_RED + "For guns: Flare gun");
                break;
        }
    }
}
