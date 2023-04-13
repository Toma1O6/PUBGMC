package dev.toma.pubgmc.api.game;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface LootGenerator extends GameObject {

    String getLootConfigurationId();

    void fillWithLoot(List<ItemStack> items);
}
