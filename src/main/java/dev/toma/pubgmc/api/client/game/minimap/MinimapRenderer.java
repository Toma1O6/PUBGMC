package dev.toma.pubgmc.api.client.game.minimap;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;

import java.util.HashMap;
import java.util.Map;

public class MinimapRenderer {

    private static final Map<String, MinimapHolder> CACHE = new HashMap<>();
    // TODO game type -> map point renderer provider

    public MinimapRenderer() {
    }

    public void renderMinimap(Game<?> game, double x, double y, double width, double height, float partialTicks) {
        Minecraft minecraft = Minecraft.getMinecraft();
        WorldClient client = minecraft.world;
        GameMap gameMap = GameHelper.getActiveGameMap(client);
        if (gameMap == null)
            return;
        String mapName = gameMap.getMapName();
        if (!CACHE.containsKey(mapName)) {
            cache(gameMap);
        }
        MinimapHolder minimapHolder = CACHE.get(mapName);
        if (minimapHolder == null)
            return;
        // TODO add list of overlay drawables to method arguments
        // TODO scale appropriately
        double mapWidth = width * minimapHolder.mapRatioX;
        double mapHeight = height * minimapHolder.mapRatioZ;
        minimapHolder.minimap.renderBackground(x, y, mapWidth, mapHeight);
    }

    private static void cache(GameMap map) {
        String name = map.getMapName();
        Minimap minimap = Minimap.forGameMap(map);
        if (minimap != null) {
            float xRatio = 1.0F;
            float zRatio = 1.0F;
            CACHE.put(name, new MinimapHolder(xRatio, zRatio, minimap));
        } else {
            CACHE.put(name, null);
        }
    }

    private static final class MinimapHolder {

        private final float mapRatioX;
        private final float mapRatioZ;
        private final Minimap minimap;

        public MinimapHolder(float mapRatioX, float mapRatioZ, Minimap minimap) {
            this.mapRatioX = mapRatioX;
            this.mapRatioZ = mapRatioZ;
            this.minimap = minimap;
        }
    }
}
