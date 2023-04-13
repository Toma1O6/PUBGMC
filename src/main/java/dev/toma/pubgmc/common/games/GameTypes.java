package dev.toma.pubgmc.common.games;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameDataSerializer;
import dev.toma.pubgmc.api.game.GameType;

public final class GameTypes {

    public static final GameType<NoGame> NO_GAME = create("none", new NoGame.Serializer());

    private static <G extends Game> GameType<G> create(String id, GameDataSerializer<G> serializer) {
        return GameType.create(Pubgmc.getResource(id), serializer);
    }
}
