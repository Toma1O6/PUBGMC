package dev.toma.pubgmc.api.event;

import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.area.GameAreaType;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import dev.toma.pubgmc.api.util.RegistryObject;
import dev.toma.pubgmc.data.loot.LootProviderType;
import dev.toma.pubgmc.data.loot.processor.LootProcessorType;
import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class PubgmcRegistryEvent<T extends RegistryObject> extends Event {

    private final RegistryHandler<T> handler;

    protected PubgmcRegistryEvent(RegistryHandler<T> handler) {
        this.handler = handler;
    }

    public void register(T value) {
        handler.register(value);
    }

    public static final class LootProvider extends PubgmcRegistryEvent<LootProviderType<?>> {
        public LootProvider() {
            super(PubgmcRegistries.LOOT_PROVIDERS::register);
        }
    }

    public static final class LootProcessor extends PubgmcRegistryEvent<LootProcessorType<?>> {
        public LootProcessor() {
            super(PubgmcRegistries.LOOT_PROCESSORS::register);
        }
    }

    public static final class Game extends PubgmcRegistryEvent<GameType<?>> {
        public Game() {
            super(PubgmcRegistries.GAME_TYPES::register);
        }
    }

    public static final class Area extends PubgmcRegistryEvent<GameAreaType<?>> {
        public Area() {
            super(PubgmcRegistries.GAME_AREA_TYPES::register);
        }
    }

    public static final class PointType extends PubgmcRegistryEvent<GameMapPointType<?>> {
        public PointType() {
            super(PubgmcRegistries.GAME_MAP_POINTS::register);
        }
    }

    @FunctionalInterface
    public interface RegistryHandler<T extends RegistryObject> {
        void register(T value);
    }
}
