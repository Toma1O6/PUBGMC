package dev.toma.pubgmc.api;

import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import dev.toma.pubgmc.api.util.RegistryObject;
import dev.toma.pubgmc.api.game.loot.LootProviderType;
import dev.toma.pubgmc.api.game.loot.LootProcessorType;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public final class PubgmcRegistries {

    public static final PubgmcRegistry<LootProviderType<?>> LOOT_PROVIDERS = new Registry<LootProviderType<?>>()
            .withKeyExtractor(RegistryObject::getIdentifier);
    public static final PubgmcRegistry<LootProcessorType<?>> LOOT_PROCESSORS = new Registry<LootProcessorType<?>>()
            .withKeyExtractor(RegistryObject::getIdentifier);
    public static final PubgmcRegistry<GameType<?, ?>> GAME_TYPES = new Registry<GameType<?, ?>>()
            .withKeyExtractor(RegistryObject::getIdentifier);
    public static final PubgmcRegistry<PlayzoneType<?>> PLAYZONE_TYPES = new Registry<PlayzoneType<?>>()
            .withKeyExtractor(RegistryObject::getIdentifier);
    public static final PubgmcRegistry<GameMapPointType<?>> GAME_MAP_POINTS = new Registry<GameMapPointType<?>>()
            .withKeyExtractor(RegistryObject::getIdentifier);

    private static final class Registry<V> implements PubgmcRegistry<V> {

        private final boolean allowOverrides;
        private final Map<ResourceLocation, V> map;
        private Function<V, ResourceLocation> keyExtractor;
        private boolean locked;

        public Registry() {
            this(false);
        }

        public Registry(boolean allowOverrides) {
            this.allowOverrides = allowOverrides;
            this.map = new HashMap<>();
        }

        public Registry<V> withKeyExtractor(Function<V, ResourceLocation> keyExtractor) {
            this.keyExtractor = keyExtractor;
            return this;
        }

        @Override
        public void register(V value) {
            ResourceLocation key = keyExtractor != null ? keyExtractor.apply(value) : null;
            if (key == null) {
                throw new IllegalArgumentException("Unable to register value without defined key!");
            }
            register(key, value);
        }

        @Override
        public void register(ResourceLocation key, V value) {
            if (locked) {
                throw new IllegalStateException("Attempted to register new object into locked registry!");
            }
            V previousValue = map.put(key, value);
            if (previousValue != null && !allowOverrides) {
                throw new IllegalArgumentException("Duplicate registry entry key: " + key);
            }
        }

        @Override
        public boolean hasValue(ResourceLocation key) {
            return map.containsKey(key);
        }

        @Nullable
        @Override
        public V getValue(ResourceLocation key) {
            return map.get(key);
        }

        @Nullable
        @Override
        @SuppressWarnings("unchecked")
        public <R> R getUnsafeGenericValue(ResourceLocation key) {
            V value = getValue(key);
            return value != null ? (R) value : null;
        }

        @Override
        public Set<ResourceLocation> getKeys() {
            return map.keySet();
        }

        @Override
        public Collection<V> getValues() {
            return map.values();
        }

        @Override
        public void lock() {
            locked = true;
        }
    }
}
