package dev.toma.pubgmc.api.event;

import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.entity.EntityProcessorType;
import dev.toma.pubgmc.api.entity.EntityProviderType;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.loot.LootProcessorType;
import dev.toma.pubgmc.api.game.loot.LootProviderType;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;
import dev.toma.pubgmc.api.util.RegistryObject;
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

    public static final class Game extends PubgmcRegistryEvent<GameType<?, ?>> {
        public Game() {
            super(PubgmcRegistries.GAME_TYPES::register);
        }
    }

    public static final class Playzone extends PubgmcRegistryEvent<PlayzoneType<?>> {
        public Playzone() {
            super(PubgmcRegistries.PLAYZONE_TYPES::register);
        }
    }

    public static final class PointType extends PubgmcRegistryEvent<GameMapPointType<?>> {
        public PointType() {
            super(PubgmcRegistries.GAME_MAP_POINTS::register);
        }
    }

    public static final class EntityProvider extends PubgmcRegistryEvent<EntityProviderType<?>> {
        public EntityProvider() {
            super(PubgmcRegistries.ENTITY_PROVIDERS::register);
        }
    }

    public static final class EntityProcessor extends PubgmcRegistryEvent<EntityProcessorType<?>> {
        public EntityProcessor() {
            super(PubgmcRegistries.ENTITY_PROCESSORS::register);
        }
    }

    @FunctionalInterface
    public interface RegistryHandler<T extends RegistryObject> {
        void register(T value);
    }
}
