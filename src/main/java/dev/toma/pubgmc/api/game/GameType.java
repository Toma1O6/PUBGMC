package dev.toma.pubgmc.api.game;

import net.minecraft.util.ResourceLocation;

public final class GameType<G extends Game> {

    private ResourceLocation registryId;

    public ResourceLocation getRegistryName() {
        return registryId;
    }

    public GameType<G> setRegistryName(ResourceLocation registryName) {
        this.registryId = registryName;
        return this;
    }
}
