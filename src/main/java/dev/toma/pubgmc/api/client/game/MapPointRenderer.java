package dev.toma.pubgmc.api.client.game;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;

public interface MapPointRenderer<P extends GameMapPoint> {

    void renderInDebugMode(P point, double x, double y, double z, float partialTicks);

    void renderPointInGame(P point, Game<?> game, double x, double y, double z, float partialTicks);

    void renderInHud(P point, EntityPlayer player, Game<?> game, ScaledResolution resolution, float partialTicks);
}
