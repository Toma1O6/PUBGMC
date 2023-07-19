package dev.toma.pubgmc.api.event;

import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.mutator.GameMutator;
import dev.toma.pubgmc.api.game.mutator.GameMutatorType;
import net.minecraftforge.fml.common.eventhandler.Event;

public class RegisterGameMutatorEvent extends Event {

    private final RegistrationHandler handler;

    public RegisterGameMutatorEvent(RegistrationHandler handler) {
        this.handler = handler;
    }

    public <M extends GameMutator> void registerMutator(GameType<?, ?> gameType, GameMutatorType<M> mutatorType, M mutator) {
        this.handler.register(gameType, mutatorType, mutator);
    }

    public void deleteMutator(GameType<?, ?> gameType, GameMutatorType<?> mutatorType) {
        this.handler.delete(gameType, mutatorType);
    }

    public interface RegistrationHandler {

        <M extends GameMutator> void register(GameType<?, ?> gameType, GameMutatorType<M> mutatorType, M mutator);

        void delete(GameType<?, ?> gameType, GameMutatorType<?> mutatorType);
    }
}
