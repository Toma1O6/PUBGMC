package dev.toma.pubgmc.api.game.mutator;

import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public final class GameMutatorType<M extends GameMutator> {

    private final ResourceLocation identifier;

    private GameMutatorType(ResourceLocation identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameMutatorType<?> that = (GameMutatorType<?>) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    public static <M extends GameMutator> GameMutatorType<M> createMutatorType(ResourceLocation identifier) {
        return new GameMutatorType<>(identifier);
    }
}
