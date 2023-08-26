package dev.toma.pubgmc.integrations.journeymap;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.map.Area;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGame;
import dev.toma.pubgmc.common.games.game.domination.DominationGame;
import dev.toma.pubgmc.common.games.game.ffa.FFAGame;
import dev.toma.pubgmc.common.games.playzone.DynamicPlayzone;
import dev.toma.pubgmc.config.ConfigPMC;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.display.PolygonOverlay;
import journeymap.client.api.model.MapPolygon;
import journeymap.client.api.model.ShapeProperties;
import net.minecraft.util.math.BlockPos;

public class JourneyMapOverlays {

    public static final String OVERLAYS_ID = Pubgmc.MOD_ID;
    protected static final ShapeProperties MAP_BORDER;

    private static void showArea(IClientAPI api, Area area, ShapeProperties properties, String name) {
        BlockPos[] edges = getPolygonCorners(area);
        MapPolygon polygon = new MapPolygon(edges);
        PolygonOverlay overlay = new PolygonOverlay(OVERLAYS_ID, name, 0, properties, polygon);
        try {
            api.show(overlay);
        } catch (Exception e) {
            Pubgmc.logger.error("Failed to generate JEI map overlay due to error", e);
        }
    }

    private static BlockPos[] getPolygonCorners(Area area) {
        Position2 min = area.getPositionMin(1.0F);
        Position2 max = area.getPositionMax(1.0F);
        int y = 256;
        BlockPos p1 = new BlockPos(min.getX(), y, min.getZ());
        BlockPos p2 = new BlockPos(max.getX(), y, min.getZ());
        BlockPos p3 = new BlockPos(max.getX(), y, max.getZ());
        BlockPos p4 = new BlockPos(min.getX(), y, max.getZ());
        return new BlockPos[]{p1, p2, p3, p4};
    }

    public static final class BattleRoyaleJourneyMapOverlay implements JourneyMapGameOverlay.JourneyMapOverlay<BattleRoyaleGame> {

        private static final ShapeProperties SAFE;
        private static final ShapeProperties UNSAFE;

        @Override
        public void apply(BattleRoyaleGame game, GameData data, IClientAPI api) {
            DynamicPlayzone playzone = game.getPlayzone();
            if (playzone == null)
                return;
            DynamicPlayzone.ResizeTarget target = playzone.getTarget();
            if (target == null) {
                showArea(api, playzone, SAFE, "edge");
            } else {
                Playzone resultArea = playzone.getResultingPlayzone();
                if (resultArea == playzone) {
                    showArea(api, playzone, SAFE, "edge");
                } else {
                    showArea(api, resultArea, SAFE, "edge");
                    showArea(api, playzone, UNSAFE, "shrinking");
                }
            }
        }

        static {
            int safeArea = ConfigPMC.client.overlays.jmBorderColor.getColor();
            int edgeArea = ConfigPMC.client.overlays.jmShrinkingColor.getColor();
            SAFE = new ShapeProperties()
                    .setStrokeWidth(2.0F)
                    .setStrokeColor(safeArea).setStrokeOpacity(1.0F)
                    .setFillColor(safeArea).setFillOpacity(1.0F);
            UNSAFE = new ShapeProperties()
                    .setStrokeWidth(2.0F)
                    .setStrokeColor(edgeArea).setStrokeOpacity(1.0F)
                    .setFillColor(edgeArea).setFillOpacity(1.0F);
        }
    }

    public static final class FreeForAllJourneyMapOverlay implements JourneyMapGameOverlay.JourneyMapOverlay<FFAGame> {

        @Override
        public void apply(FFAGame game, GameData data, IClientAPI api) {
            Playzone playzone = game.getPlayzone();
            showArea(api, playzone, MAP_BORDER, "bounds");
        }
    }

    public static final class DominationJourneyMapOverlay implements JourneyMapGameOverlay.JourneyMapOverlay<DominationGame> {

        @Override
        public void apply(DominationGame game, GameData data, IClientAPI api) {
            Playzone playzone = game.getPlayzone();
            showArea(api, playzone, MAP_BORDER, "bounds");
        }
    }

    private JourneyMapOverlays() {}

    static {
        int mapColor = ConfigPMC.client.overlays.jmMapBorderColor.getColor();
        MAP_BORDER = new ShapeProperties()
                .setStrokeWidth(1.0F)
                .setStrokeColor(mapColor).setStrokeOpacity(1.0F)
                .setFillColor(mapColor).setFillOpacity(1.0F);
    }
}
