package dev.toma.pubgmc.api.game;

import dev.toma.pubgmc.api.game.loot.LootableContainer;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface LootGenerator extends GameObject, LootableContainer {

    String getLootConfigurationId();

    void fillWithLoot(List<ItemStack> items);
}
