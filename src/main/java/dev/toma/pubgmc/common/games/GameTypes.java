package dev.toma.pubgmc.common.games;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGame;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGameConfiguration;

public final class GameTypes {

    public static final GameType<NoGame.NoConfiguration, NoGame> NO_GAME = GameType.create(Pubgmc.getResource("none"), new NoGame.Serializer(), () -> NoGame.NoConfiguration.INSTANCE);
    public static final GameType<BattleRoyaleGameConfiguration, BattleRoyaleGame> BATTLE_ROYALE = GameType.create(Pubgmc.getResource("battle_royale"), new BattleRoyaleGame.Serializer(), BattleRoyaleGameConfiguration::new);
}
