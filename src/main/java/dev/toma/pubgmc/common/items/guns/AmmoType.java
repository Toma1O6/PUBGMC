package dev.toma.pubgmc.common.items.guns;

import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum AmmoType {
    AMMO9MM("ammo.9mm", 30, 30),
    AMMO45ACP("ammo.45acp", 20, 30),
    AMMO12G("ammo.12g", 5, 10),
    AMMO556("ammo.556mm", 20, 30),
    AMMO762("ammo.762mm", 15, 30),
    AMMO300M("ammo.300m", 5, 5),
    FLARE("ammo.flare", 1, 1);

    private final String name;
    private final int amount;
    private final int lootAmount;

    AmmoType(String name, int amount, int lootAmount) {
        this.name = name;
        this.amount = amount;
        this.lootAmount = lootAmount;
    }

    @SideOnly(Side.CLIENT)
    public String translatedName() {
        return I18n.format(name);
    }

    public int craftAmount() {
        return amount;
    }

    public int lootAmount() {
        return lootAmount;
    }

    public Item ammo() {
        switch (this) {
            case AMMO9MM:
            default:
                return PMCItems.AMMO_9MM;
            case AMMO45ACP:
                return PMCItems.AMMO_45ACP;
            case AMMO556:
                return PMCItems.AMMO_556;
            case AMMO762:
                return PMCItems.AMMO_762;
            case AMMO300M:
                return PMCItems.AMMO_300M;
            case AMMO12G:
                return PMCItems.AMMO_SHOTGUN;
            case FLARE:
                return PMCItems.AMMO_FLARE;
        }
    }

    public ItemStack ammoStack() {
        return new ItemStack(ammo());
    }

    public ItemStack ammoStack(int i) {
        return new ItemStack(ammo(), i);
    }
}
