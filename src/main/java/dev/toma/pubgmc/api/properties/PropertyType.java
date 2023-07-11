package dev.toma.pubgmc.api.properties;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public final class PropertyType<V> {

    private final ResourceLocation identifier;
    private final PropertyValueSerializer<V> serializer;

    public PropertyType(ResourceLocation identifier, PropertyValueSerializer<V> serializer) {
        this.identifier = identifier;
        this.serializer = serializer;
    }

    public ResourceLocation getIdentifier() {
        return identifier;
    }

    public void serializeTo(NBTTagCompound nbt, V value) {
        serializer.toNbt(nbt, identifier.toString(), value);
    }

    public V deserializeFrom(NBTTagCompound nbt, String key) {
        return serializer.fromNbt(nbt, key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyType<?> that = (PropertyType<?>) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return identifier.toString();
    }
}
