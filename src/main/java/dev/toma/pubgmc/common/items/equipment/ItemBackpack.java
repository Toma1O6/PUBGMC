package dev.toma.pubgmc.common.items.equipment;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.items.PMCItem;
import dev.toma.pubgmc.util.game.loot.LootManager;
import dev.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.util.ResourceLocation;

public final class ItemBackpack extends PMCItem implements Backpack {

    private static final ResourceLocation[] ICONS = {
            Pubgmc.getResource("textures/overlay/backpack1.png"),
            Pubgmc.getResource("textures/overlay/backpack2.png"),
            Pubgmc.getResource("textures/overlay/backpack3.png")
    };
    private final int backpackLevel;

    public ItemBackpack(String name, int backpackLevel) {
        super(name);
        this.backpackLevel = backpackLevel;
        this.setMaxStackSize(1);
        LootManager.register(LootType.ARMOR, new LootManager.LootEntry(this, 10, false));
    }

    @Override
    public int unlockSlotCount() {
        return backpackLevel * 9;
    }

    @Override
    public ResourceLocation getHotbarIconPath() {
        return ICONS[backpackLevel - 1];
    }
}
