package dev.toma.pubgmc.api.client.game;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.map.GameMapPoint;

public interface MapPointRenderer<P extends GameMapPoint> {

    void renderInDebugMode(P point, double x, double y, double z, float partialTicks);

    void renderPointInGame(P point, Game<?> game, double x, double y, double z, float partialTicks);
}
