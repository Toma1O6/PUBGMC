package dev.toma.pubgmc.api.client.event;

import dev.toma.pubgmc.api.client.game.GameRenderer;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameType;
import net.minecraftforge.fml.common.eventhandler.Event;

public final class RegisterGameRendererEvent extends Event {

    private final RegistrationHandler registrationHandler;

    public RegisterGameRendererEvent(RegistrationHandler registrationHandler) {
        this.registrationHandler = registrationHandler;
    }

    public <G extends Game<?>> void registerRenderer(GameType<?, G> type, GameRenderer<G> renderer) {
        registrationHandler.register(type, renderer);
    }

    @FunctionalInterface
    public interface RegistrationHandler {
        <G extends Game<?>> void register(GameType<?, G> type, GameRenderer<G> renderer);
    }
}
