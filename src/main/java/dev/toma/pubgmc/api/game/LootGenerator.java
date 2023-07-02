package dev.toma.pubgmc.api.game;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public interface LootGenerator extends GameObject {

    BlockPos getPositionInWorld();

    String getLootConfigurationId();

    void fillWithLoot(List<ItemStack> items);

    List<ItemStack> getGeneratorItems();

    void finishedLooting();
}
