package dev.toma.pubgmc.integrations.journeymap;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.map.Area;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.games.NoGame;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGame;
import dev.toma.pubgmc.common.games.game.domination.DominationGame;
import dev.toma.pubgmc.common.games.game.ffa.FFAGame;
import dev.toma.pubgmc.common.games.playzone.DynamicPlayzone;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.util.helper.GameHelper;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.display.ImageOverlay;
import journeymap.client.api.display.PolygonOverlay;
import journeymap.client.api.model.MapImage;
import journeymap.client.api.model.MapPolygon;
import journeymap.client.api.model.ShapeProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.UUID;

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

    private static void showImage(IClientAPI api, BlockPos pos, int scale, String name, ResourceLocation image, int textureSize) {
        BlockPos north = pos.north(scale).west(scale);
        BlockPos south = pos.south(scale).east(scale);
        MapImage mapImage = new MapImage(image, textureSize, textureSize);
        mapImage.setRotation(0);
        mapImage.setAnchorX(0);
        mapImage.setAnchorY(0);
        ImageOverlay imageOverlay = new ImageOverlay(OVERLAYS_ID, name, north, south, mapImage);
        imageOverlay.setDimension(0);
        try {
            api.show(imageOverlay);
        } catch (Exception e) {
            Pubgmc.logger.error("Failed to generate JEI map image due to error", e);
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

        private static final ResourceLocation AIRDROP_IMAGE = Pubgmc.getResource("textures/overlay/airdrop.png");
        private static final ShapeProperties SAFE;
        private static final ShapeProperties UNSAFE;

        private UUID targetGameId = GameHelper.DEFAULT_UUID;
        private int displayedAirdrops = 0;

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

            UUID activeGameId = game.getGameId();
            if (!activeGameId.equals(targetGameId)) {
                displayedAirdrops = 0;
                targetGameId = activeGameId;
            }
            List<BlockPos> airdrops = game.getAirdrops();
            int diff = airdrops.size() - displayedAirdrops;
            for (int i = 0; i < diff; i++) {
                int index = displayedAirdrops + i;
                showImage(api, airdrops.get(index), 8, "airdrop" + index, AIRDROP_IMAGE, 16);
            }
            displayedAirdrops = airdrops.size();
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
