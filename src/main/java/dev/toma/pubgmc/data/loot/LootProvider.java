package dev.toma.pubgmc.data.loot;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface LootProvider {

    List<ItemStack> generateItems(LootGenerationContext context);

    LootProviderType<?> getType();
}
