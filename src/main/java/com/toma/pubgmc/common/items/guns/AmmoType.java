package com.toma.pubgmc.common.items.guns;

import com.toma.pubgmc.init.PMCRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum AmmoType {
    AMMO9MM("ammo.9mm", 30),
    AMMO45ACP("ammo.45acp", 20),
    AMMO12G("ammo.12g", 5),
    AMMO556("ammo.556mm", 20),
    AMMO762("ammo.762mm", 15),
    AMMO300M("ammo.300m", 5),
    FLARE("ammo.flare", 1);

    private final String name;
    private final int amount;

    AmmoType(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    @SideOnly(Side.CLIENT)
    public String translatedName() {
        return I18n.format(name);
    }

    public int craftAmount() {
        return amount;
    }

    public Item ammo() {
        switch (this) {
            case AMMO9MM: default:
                return PMCRegistry.PMCItems.AMMO_9MM;
            case AMMO45ACP:
                return PMCRegistry.PMCItems.AMMO_45ACP;
            case AMMO556:
                return PMCRegistry.PMCItems.AMMO_556;
            case AMMO762:
                return PMCRegistry.PMCItems.AMMO_762;
            case AMMO300M:
                return PMCRegistry.PMCItems.AMMO_300M;
            case AMMO12G:
                return PMCRegistry.PMCItems.AMMO_SHOTGUN;
            case FLARE:
                return PMCRegistry.PMCItems.AMMO_FLARE;
        }
    }

    public ItemStack ammoStack() {
        return new ItemStack(ammo());
    }

    public ItemStack ammoStack(int i) {
        return new ItemStack(ammo(), i);
    }
}
