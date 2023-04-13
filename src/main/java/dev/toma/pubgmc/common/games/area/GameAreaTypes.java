package dev.toma.pubgmc.common.games.area;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.game.area.GameArea;
import dev.toma.pubgmc.api.game.area.GameAreaSerializer;
import dev.toma.pubgmc.api.game.area.GameAreaType;

public final class GameAreaTypes {

    public static final GameAreaType<StaticGameArea> STATIC_AREA = create("static", new StaticGameArea.Serializer());
    public static final GameAreaType<DynamicGameArea> DYNAMIC_AREA = create("dynamic", new DynamicGameArea.Serializer());

    public static void register() {
        register(STATIC_AREA);
        register(DYNAMIC_AREA);
    }

    private static void register(GameAreaType<?> type) {
        PubgmcRegistries.GAME_AREA_TYPES.register(type);
    }

    private static <A extends GameArea> GameAreaType<A> create(String path, GameAreaSerializer<A> serializer) {
        return GameAreaType.create(Pubgmc.getResource(path), serializer);
    }
}
