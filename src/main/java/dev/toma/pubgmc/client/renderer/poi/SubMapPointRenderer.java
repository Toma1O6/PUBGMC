package dev.toma.pubgmc.client.renderer.poi;

import dev.toma.pubgmc.api.client.util.PlayzoneRenderer;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.common.games.map.PartialPlayAreaPoint;

public class SubMapPointRenderer extends SimplePoiRenderer<PartialPlayAreaPoint> {

    @Override
    public void renderInDebugMode(PartialPlayAreaPoint point, double x, double y, double z, float partialTicks) {
        super.renderInDebugMode(point, x, y, z, partialTicks);
        PlayzoneRenderer.render(x, y, z, point, 0x44FF0000, partialTicks);
    }

    @Override
    public void renderPointInGame(PartialPlayAreaPoint point, Game<?> game, double x, double y, double z, float partialTicks) {
    }
}
