package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.client.game.GameRenderer;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unchecked")
public final class GameRendererManager {

    public static final GameRendererManager INSTANCE = new GameRendererManager();

    private final Map<GameType<?, ?>, GameRenderer<?>> rendererByType;

    private GameRendererManager() {
        rendererByType = new HashMap<>();
    }

    public <G extends Game<?>> void registerGameRenderer(GameType<?, G> gameType, GameRenderer<G> renderer) {
        rendererByType.put(
                Objects.requireNonNull(gameType),
                Objects.requireNonNull(renderer)
        );
    }

    public <G extends Game<?>> GameRenderer<G> getGameRendererForType(GameType<?, G> type) {
        return (GameRenderer<G>) rendererByType.get(type);
    }

    public <G extends Game<?>> void renderCurrentGameHUDOverlay(RenderGameOverlayEvent.Pre event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        World world = minecraft.world;
        G game = getActiveGame(world);
        if (game != null && game.isStarted()) {
            GameType<?, G> type = (GameType<?, G>) game.getGameType();
            GameRenderer<G> renderer = getGameRendererForType(type);
            if (renderer == null)
                return;
            RenderGameOverlayEvent.ElementType elementType = event.getType();
            float partialTicks = event.getPartialTicks();
            ScaledResolution resolution = event.getResolution();
            if (renderer.renderHudOverlay(minecraft.player, game, resolution, elementType, partialTicks)) {
                event.setCanceled(true);
            }
        }
    }

    public <G extends Game<?>> void renderWorldOverlay(float partialTicks) {
        Minecraft minecraft = Minecraft.getMinecraft();
        World world = minecraft.world;
        G game = getActiveGame(world);
        if (game != null && game.isStarted()) {
            GameType<?, G> type = (GameType<?, G>) game.getGameType();
            GameRenderer<G> renderer = getGameRendererForType(type);
            if (renderer == null)
                return;
            Entity entity = minecraft.getRenderViewEntity();
            if (entity == null)
                return;
            double x = PUBGMCUtil.interpolate(entity.lastTickPosX, entity.posX, partialTicks);
            double y = PUBGMCUtil.interpolate(entity.lastTickPosY, entity.posY, partialTicks);
            double z = PUBGMCUtil.interpolate(entity.lastTickPosZ, entity.posZ, partialTicks);
            renderer.renderInWorld(world, game, x, y, z, partialTicks);
        }
    }

    private <G extends Game<?>> G getActiveGame(World world) {
        return GameDataProvider.getGameData(world)
                .map(data -> (G) data.getCurrentGame())
                .orElse(null);
    }
}
