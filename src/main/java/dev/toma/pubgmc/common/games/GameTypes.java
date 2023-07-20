package dev.toma.pubgmc.common.games;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.GameException;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGame;
import dev.toma.pubgmc.common.games.game.battleroyale.BattleRoyaleGameConfiguration;
import dev.toma.pubgmc.common.games.game.domination.DominationGame;
import dev.toma.pubgmc.common.games.game.domination.DominationGameConfiguration;
import dev.toma.pubgmc.common.games.game.ffa.FFAGame;
import dev.toma.pubgmc.common.games.game.ffa.FFAGameConfiguration;

import java.util.UUID;

public final class GameTypes {

    public static final GameType<NoGame.NoConfiguration, NoGame> NO_GAME = GameType.create(Pubgmc.getResource("none"), (UUID id, NoGame.NoConfiguration cfg) -> {
        throw new GameException("This type of game cannot be started");
    }, new NoGame.Serializer(), () -> NoGame.NoConfiguration.INSTANCE);
    public static final GameType<BattleRoyaleGameConfiguration, BattleRoyaleGame> BATTLE_ROYALE = GameType.create(Pubgmc.getResource("battle_royale"), BattleRoyaleGame::new, new BattleRoyaleGame.Serializer(), BattleRoyaleGameConfiguration::new);
    public static final GameType<FFAGameConfiguration, FFAGame> FFA = GameType.create(Pubgmc.getResource("ffa"), FFAGame::new, new FFAGame.Serializer(), FFAGameConfiguration::new);
    public static final GameType<DominationGameConfiguration, DominationGame> DOMINATION = GameType.create(Pubgmc.getResource("domination"), DominationGame::new, new DominationGame.Serializer(), DominationGameConfiguration::new);
}
