package dev.toma.pubgmc.api;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Set;

public interface PubgmcRegistry<V> {

    void register(V value);

    void register(ResourceLocation key, V value);

    boolean hasValue(ResourceLocation key);

    @Nullable
    V getValue(ResourceLocation key);

    @Nullable
    <R> R getUnsafeGenericValue(ResourceLocation key);

    Set<ResourceLocation> getKeys();

    Collection<V> getValues();

    void lock();
}
