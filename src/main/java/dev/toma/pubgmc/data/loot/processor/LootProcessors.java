package dev.toma.pubgmc.data.loot.processor;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.loot.LootProcessor;
import dev.toma.pubgmc.api.game.loot.LootProcessorSerializer;
import dev.toma.pubgmc.api.game.loot.LootProcessorType;
import net.minecraft.util.ResourceLocation;

public final class LootProcessors {

    public static final LootProcessorType<AmmoPackProcessor> AMMO_PROCESSOR = create("ammo_processor", new AmmoPackProcessor.Serializer());
    public static final LootProcessorType<GhillieColorProcessor> GHILLIE_COLOR_PROCESSOR = create("ghillie_color_processor", new GhillieColorProcessor.Serializer());
    public static final LootProcessorType<AttachmentProcessor> ATTACHMENT_PROCESSOR = create("attachment_processor", new AttachmentProcessor.Serializer());
    public static final LootProcessorType<WeaponAmmoProcessor> WEAPON_AMMO_PROCESOR = create("weapon_ammo_processor", new WeaponAmmoProcessor.Serializer());

    private static <P extends LootProcessor> LootProcessorType<P> create(String id, LootProcessorSerializer<P> serializer) {
        ResourceLocation key = Pubgmc.getResource(id);
        return LootProcessorType.create(key, serializer);
    }
}
