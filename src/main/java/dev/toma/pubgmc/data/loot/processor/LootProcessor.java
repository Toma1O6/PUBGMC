package dev.toma.pubgmc.data.loot.processor;

import dev.toma.pubgmc.data.loot.LootGenerationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public interface LootProcessor {

    void processItems(LootGenerationContext context, List<ItemStack> generated);

    LootProcessorType<?> getType();
}
