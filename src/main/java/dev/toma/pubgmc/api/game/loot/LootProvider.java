package dev.toma.pubgmc.api.game.loot;

import dev.toma.pubgmc.data.loot.LootGenerationContext;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface LootProvider {

    List<ItemStack> generateItems(LootGenerationContext context);

    LootProviderType<?> getType();
}
