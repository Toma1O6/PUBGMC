package dev.toma.pubgmc.integrations.journeymap;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.games.playzone.DynamicPlayzone;
import dev.toma.pubgmc.config.ConfigPMC;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.display.PolygonOverlay;
import journeymap.client.api.model.MapPolygon;
import journeymap.client.api.model.ShapeProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public final class JourneyMapEventListener {

    private final IClientAPI api;

    public JourneyMapEventListener(IClientAPI api) {
        this.api = api;
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        WorldClient world = minecraft.world;
        if (event.phase == TickEvent.Phase.START || world == null) {
            return;
        }
        GameData data = GameDataProvider.getGameData(world).orElse(null);
        if (data == null)
            return;
        Game<?> game = data.getCurrentGame();
        GameMapInstance instance = data.getActiveGameMap().orElse(null);
        if (game == null || !game.isStarted() || instance == null) {
            reset();
            return;
        }
        Playzone mapPlayzone = game.getMapArea();
        Playzone playzone = game.getPlayArea();
        if (mapPlayzone == null) {
            reset();
            return;
        }
        try {
            int edgeColor = ConfigPMC.client.overlays.jmBorderColor.getColor();
            ShapeProperties edge = new ShapeProperties().setStrokeWidth(1.0F)
                    .setStrokeColor(edgeColor).setStrokeOpacity(1.0F)
                    .setFillColor(edgeColor).setFillOpacity(1.0F);
            if (playzone != null) {
                // Game contains dynamic playzone
                ShapeProperties outer = new ShapeProperties()
                        .setStrokeWidth(1.0F)
                        .setStrokeColor(edgeColor).setStrokeOpacity(1.0F)
                        .setFillColor(edgeColor).setFillOpacity(1.0F);
                if (playzone instanceof DynamicPlayzone) {
                    DynamicPlayzone dynamic = (DynamicPlayzone) playzone;
                    Playzone target = dynamic.getResultingPlayzone();
                    if (target != null) {
                        int innerColor = ConfigPMC.client.overlays.jmShrinkingColor.getColor();
                        outer.setStrokeColor(innerColor).setFillColor(innerColor);
                        MapPolygon targetPolygon = new MapPolygon(getPlayzoneCorners(target));
                        api.show(new PolygonOverlay(Pubgmc.MOD_ID, "target", 0, edge, targetPolygon));
                    }
                }
                MapPolygon zone = new MapPolygon(getPlayzoneCorners(playzone));
                api.show(new PolygonOverlay(Pubgmc.MOD_ID, "outer", 0, outer, zone));
            } else {
                // Game contains simple playzone
                MapPolygon polygon = new MapPolygon(getPlayzoneCorners(mapPlayzone));
                api.show(new PolygonOverlay(Pubgmc.MOD_ID, "map", 0, edge, polygon));
            }
        } catch (Exception e) {
            Pubgmc.logger.error("Failed to generate JEI map overlay due to error", e);
        }
    }

    private BlockPos[] getPlayzoneCorners(Playzone playzone) {
        return getPlayzoneCorners(playzone, 0);
    }

    private BlockPos[] getPlayzoneCorners(Playzone playzone, int offset) {
        Position2 min = playzone.getPositionMin(1.0F);
        Position2 max = playzone.getPositionMax(1.0F);
        int y = 256;
        BlockPos p1 = new BlockPos(min.getX() + offset, y, min.getZ() + offset);
        BlockPos p2 = new BlockPos(max.getX() - offset, y, min.getZ() + offset);
        BlockPos p3 = new BlockPos(max.getX() - offset, y, max.getZ() - offset);
        BlockPos p4 = new BlockPos(min.getX() + offset, y, max.getZ() - offset);
        return new BlockPos[]{p1, p2, p3, p4};
    }

    private void reset() {
        api.removeAll(Pubgmc.MOD_ID);
    }
}
