package dev.toma.pubgmc.api.game.mutator;

import dev.toma.pubgmc.api.event.RegisterGameMutatorEvent;
import dev.toma.pubgmc.api.game.GameType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class GameMutatorManager implements RegisterGameMutatorEvent.RegistrationHandler {

    public static final GameMutatorManager INSTANCE = new GameMutatorManager();
    private final Map<GameType<?, ?>, Map<GameMutatorType<?>, GameMutator>> mutators;

    private GameMutatorManager() {
        this.mutators = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public <M extends GameMutator> Optional<M> getMutator(GameType<?, ?> gameType, GameMutatorType<M> type) {
        Map<GameMutatorType<?>, GameMutator> map = mutators.get(gameType);
        if (map == null)
            return Optional.empty();
        GameMutator mutator = map.get(type);
        return Optional.ofNullable((M) mutator);
    }

    public boolean hasMutator(GameType<?, ?> gameType, GameMutatorType<?> type) {
        return getMutator(gameType, type).isPresent();
    }

    @Override
    public <M extends GameMutator> void register(GameType<?, ?> gameType, GameMutatorType<M> mutatorType, M mutator) {
        Map<GameMutatorType<?>, GameMutator> map = mutators.computeIfAbsent(gameType, t -> new HashMap<>());
        map.put(mutatorType, mutator);
    }

    @Override
    public void delete(GameType<?, ?> gameType, GameMutatorType<?> mutatorType) {
        Map<GameMutatorType<?>, GameMutator> map = mutators.get(gameType);
        if (map != null) {
            map.remove(mutatorType);
        }
    }
}
