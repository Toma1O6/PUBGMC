package dev.toma.pubgmc.data;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class SimpleObjectRegistry<V> {

    private final Map<ResourceLocation, V> map = new HashMap<>();
    private final Function<V, ResourceLocation> keyProvider;

    public SimpleObjectRegistry() {
        this(null);
    }

    public SimpleObjectRegistry(Function<V, ResourceLocation> keyProvider) {
        this.keyProvider = keyProvider;
    }

    public void register(V value) {
        if (keyProvider == null) {
            throw new IllegalStateException("Unable to register");
        }
        ResourceLocation key = keyProvider.apply(value);
        if (key == null) {
            throw new IllegalArgumentException("Unable to register null key");
        }
        register(key, value);
    }

    public void register(ResourceLocation location, V value) {
        if (map.put(location, value) != null) {
            throw new IllegalStateException("Duplicate registry value for key: " + location);
        }
    }

    @Nullable
    public V getValue(ResourceLocation key) {
        return map.get(key);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public <R> R getGenericValue(ResourceLocation key) {
        return (R) getValue(key);
    }
}
