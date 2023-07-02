package dev.toma.pubgmc.util;

import dev.toma.pubgmc.api.event.LootEvent;
import dev.toma.pubgmc.api.game.LootGenerator;
import dev.toma.pubgmc.data.loot.LootConfiguration;
import dev.toma.pubgmc.data.loot.LootManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;

public final class EventDispatcher {

    public static <T extends LootGenerator> List<ItemStack> getModifiedLoot(T generator, List<ItemStack> originalLoot) {
        LootManager manager = LootManager.getInstance();
        LootConfiguration configuration = manager.getConfigurationById(generator.getLootConfigurationId());
        LootEvent.Generated<T> event = new LootEvent.Generated<>(generator.getLootConfigurationId(), configuration, generator, originalLoot);
        MinecraftForge.EVENT_BUS.post(event);
        List<ItemStack> loot = event.getLoot();
        return loot != null ? loot : originalLoot;
    }
}
