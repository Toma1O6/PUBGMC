package dev.toma.pubgmc.api.util;

import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public abstract class RegistryObject {

    private final ResourceLocation identifier;

    protected RegistryObject(ResourceLocation identifier) {
        this.identifier = Objects.requireNonNull(identifier);
    }

    public ResourceLocation getIdentifier() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistryObject that = (RegistryObject) o;
        return identifier.equals(that.identifier);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public String toString() {
        return "[" + identifier + "]";
    }
}
