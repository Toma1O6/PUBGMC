package dev.toma.pubgmc.api.client.event;

import dev.toma.pubgmc.api.client.game.MapPointRenderer;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import net.minecraftforge.fml.common.eventhandler.Event;

public class RegisterMapPointRendererEvent extends Event {

    private final RegistrationHandler registrationHandler;

    public RegisterMapPointRendererEvent(RegistrationHandler registrationHandler) {
        this.registrationHandler = registrationHandler;
    }

    public <P extends GameMapPoint> void registerRenderer(GameMapPointType<P> pointType, MapPointRenderer<P> renderer) {
        this.registrationHandler.register(pointType, renderer);
    }

    @FunctionalInterface
    public interface RegistrationHandler {
        <P extends GameMapPoint> void register(GameMapPointType<P> pointType, MapPointRenderer<P> renderer);
    }
}
