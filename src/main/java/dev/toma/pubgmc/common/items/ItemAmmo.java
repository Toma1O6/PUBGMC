package dev.toma.pubgmc.common.items;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.common.items.guns.AmmoType;

public class ItemAmmo extends PMCItem {

    public final AmmoType type;

    public ItemAmmo(String name, AmmoType type) {
        super(name);
        this.type = type;
        setCreativeTab(PMCTabs.TAB_ACCESSORIES);
    }

    public AmmoType getAmmoType() {
        return type;
    }
}
