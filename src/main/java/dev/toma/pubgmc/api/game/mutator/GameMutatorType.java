package dev.toma.pubgmc.api.game.mutator;

import java.util.Objects;

public final class GameMutatorType<M extends GameMutator> {

    private final Class<M> type;

    private GameMutatorType(Class<M> type) {
        this.type = type;
    }

    public Class<M> getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameMutatorType<?> that = (GameMutatorType<?>) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    public static <M extends GameMutator> GameMutatorType<M> createMutator(Class<M> type) {
        return new GameMutatorType<>(type);
    }
}
