package dev.toma.pubgmc.common.games;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.GameException;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGame;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGameConfiguration;

public final class GameTypes {

    public static final GameType<NoGame.NoConfiguration, NoGame> NO_GAME = GameType.create(Pubgmc.getResource("none"), (id, cfg) -> {
        throw new GameException("This type of game cannot be started");
    }, new NoGame.Serializer(), () -> NoGame.NoConfiguration.INSTANCE);
    public static final GameType<BattleRoyaleGameConfiguration, BattleRoyaleGame> BATTLE_ROYALE = GameType.create(Pubgmc.getResource("battle_royale"), BattleRoyaleGame::new, new BattleRoyaleGame.Serializer(), BattleRoyaleGameConfiguration::new);
}
