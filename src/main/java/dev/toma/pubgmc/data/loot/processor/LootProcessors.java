package dev.toma.pubgmc.data.loot.processor;

import dev.toma.pubgmc.Pubgmc;
import net.minecraft.util.ResourceLocation;

public final class LootProcessors {

    public static final LootProcessorType<AmmoProcessor> AMMO_PROCESSOR = create("ammo_processor", new AmmoProcessor.Serializer());
    public static final LootProcessorType<GhillieColorProcessor> GHILLIE_COLOR_PROCESSOR = create("ghillie_color_processor", new GhillieColorProcessor.Serializer());

    public static void registerLootProcessors() {
        register(AMMO_PROCESSOR);
        register(GHILLIE_COLOR_PROCESSOR);
    }

    private static void register(LootProcessorType<?> type) {
        Pubgmc.LOOT_PROCESSOR_TYPE_REGISTRY.register(type);
    }

    private static <P extends LootProcessor> LootProcessorType<P> create(String id, LootProcessorSerializer<P> serializer) {
        ResourceLocation key = Pubgmc.getResource(id);
        return LootProcessorType.create(key, serializer);
    }
}
