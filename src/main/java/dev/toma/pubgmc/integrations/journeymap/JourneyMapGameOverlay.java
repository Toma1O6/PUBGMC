package dev.toma.pubgmc.integrations.journeymap;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameType;
import journeymap.client.api.IClientAPI;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public final class JourneyMapGameOverlay {

    private static final Map<GameType<?, ?>, JourneyMapOverlay<?>> OVERLAY_MAP = new HashMap<>();
    private final IClientAPI api;

    public JourneyMapGameOverlay(IClientAPI api) {
        this.api = api;
    }

    public static <G extends Game<?>> void registerJourneyMapOverlay(GameType<?, G> type, JourneyMapOverlay<G> overlay) {
        OVERLAY_MAP.put(type, overlay);
    }

    @SuppressWarnings("unchecked")
    public <G extends Game<?>> void update(GameData data) {
        Game<?> game = data.getCurrentGame();
        GameType<?, G> type = (GameType<?, G>) game.getGameType();
        JourneyMapOverlay<G> overlay = getOverlayHandler(type);
        if (overlay == null)
            return;
        overlay.apply((G) game, data, api);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    private static <G extends Game<?>> JourneyMapOverlay<G> getOverlayHandler(GameType<?, G> type) {
        return (JourneyMapOverlay<G>) OVERLAY_MAP.get(type);
    }

    @FunctionalInterface
    public interface JourneyMapOverlay<G extends Game<?>> {
        void apply(G game, GameData data, IClientAPI api);
    }
}
