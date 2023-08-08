package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.client.game.MapPointRenderer;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.api.game.map.GameMapPoint;
import dev.toma.pubgmc.api.game.map.GameMapPointType;
import dev.toma.pubgmc.api.item.MapPointItem;
import dev.toma.pubgmc.client.renderer.poi.SimplePoiRenderer;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unchecked")
public final class MapPointRendererManager {

    public static final MapPointRendererManager INSTANCE = new MapPointRendererManager();

    private final Map<GameMapPointType<?>, MapPointRenderer<?>> rendererMap;

    private MapPointRendererManager() {
        this.rendererMap = new HashMap<>();
    }

    public <P extends GameMapPoint> void registerRenderer(GameMapPointType<P> mapPointType, MapPointRenderer<P> mapPointRenderer) {
        rendererMap.put(
                Objects.requireNonNull(mapPointType),
                Objects.requireNonNull(mapPointRenderer)
        );
    }

    public <P extends GameMapPoint> MapPointRenderer<P> getRendererForType(GameMapPointType<P> pointType) {
        return (MapPointRenderer<P>) rendererMap.getOrDefault(pointType, SimplePoiRenderer.simpleRenderer());
    }

    public void renderInWorld(float partialTicks) {
        Minecraft minecraft = Minecraft.getMinecraft();
        World world = minecraft.world;
        GameDataProvider.getGameData(world).ifPresent(gameData -> {
            Entity entity = minecraft.getRenderViewEntity();
            Game<?> game = gameData.getCurrentGame();
            if (entity == null)
                return;
            double x = PUBGMCUtil.interpolate(entity.lastTickPosX, entity.posX, partialTicks);
            double y = PUBGMCUtil.interpolate(entity.lastTickPosY, entity.posY, partialTicks);
            double z = PUBGMCUtil.interpolate(entity.lastTickPosZ, entity.posZ, partialTicks);
            MapPointItem mapPointItem = null;
            if (entity instanceof EntityLivingBase) {
                ItemStack stack = ((EntityLivingBase) entity).getHeldItemMainhand();
                if (stack.getItem() instanceof MapPointItem) {
                    mapPointItem = (MapPointItem) stack.getItem();
                }
            }
            boolean debugMode = mapPointItem != null && mapPointItem.shouldRenderAllPoints();
            if (game != null && game.isStarted()) {
                String mapName = gameData.getActiveGameMapName();
                GameMapInstance map = gameData.getGameMap(mapName);
                if (map == null) {
                    return;
                }
                renderPoints(map.getPoints(), debugMode, game, x, y, z, partialTicks);
            } else {
                for (GameMapInstance map : gameData.getRegisteredGameMaps().values()) {
                    renderPoints(map.getPoints(), debugMode, game, x, y, z, partialTicks);
                }
            }
        });
    }

    private void renderPoints(Collection<GameMapPoint> points, boolean debugMode, Game<?> game, double x, double y, double z, float partialTicks) {
        for (GameMapPoint point : points) {
            renderPoint(point, debugMode, game, x, y, z, partialTicks);
        }
    }

    private <P extends GameMapPoint> void renderPoint(P point, boolean debugMode, Game<?> game, double x, double y, double z, float partialTicks) {
        GameMapPointType<P> type = (GameMapPointType<P>) point.getType();
        MapPointRenderer<P> renderer = this.getRendererForType(type);
        if (renderer == null)
            return;
        if (debugMode) {
            renderer.renderInDebugMode(point, x, y, z, partialTicks);
        } else {
            renderer.renderPointInGame(point, game, x, y, z, partialTicks);
        }
    }
}
