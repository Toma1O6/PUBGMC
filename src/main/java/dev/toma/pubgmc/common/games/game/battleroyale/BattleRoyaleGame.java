package dev.toma.pubgmc.common.games.game.battleroyale;

import dev.toma.pubgmc.api.game.GameDataSerializer;
import dev.toma.pubgmc.api.game.GameException;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.TeamGame;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.common.games.GameTypes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.UUID;

public class BattleRoyaleGame implements TeamGame<BattleRoyaleGameConfiguration> {

    private final UUID gameId;
    private final BattleRoyaleGameConfiguration configuration;

    private boolean started;

    public BattleRoyaleGame(UUID gameId, BattleRoyaleGameConfiguration configuration) {
        this.gameId = gameId;
        this.configuration = configuration;
    }

    @Override
    public UUID getGameId() {
        return gameId;
    }

    @Override
    public GameType<BattleRoyaleGameConfiguration, ?> getGameType() {
        return GameTypes.BATTLE_ROYALE;
    }

    @Override
    public BattleRoyaleGameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public void performGameMapValidations(World world, GameMap map) throws GameException {
    }

    @Override
    public void onGameInit(World world) {

    }

    @Override
    public void onGameStart(World world) {
        started = true;
    }

    @Override
    public void onGameTick(World world) {

    }

    @Override
    public void onGameStopped(World world) {

    }

    public static final class Serializer implements GameDataSerializer<BattleRoyaleGameConfiguration, BattleRoyaleGame> {

        @Override
        public NBTTagCompound serializeGameData(BattleRoyaleGame game) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setUniqueId("gameId", game.gameId);
            nbt.setBoolean("started", game.started);

            return nbt;
        }

        @Override
        public BattleRoyaleGame deserializeGameData(NBTTagCompound nbt, BattleRoyaleGameConfiguration configuration) {
            UUID gameId = nbt.getUniqueId("gameId");
            boolean started = nbt.getBoolean("started");
            BattleRoyaleGame game = new BattleRoyaleGame(gameId, configuration);
            game.started = started;
            return game;
        }

        @Override
        public NBTTagCompound serializeGameConfiguration(BattleRoyaleGameConfiguration configuration) {
            return new NBTTagCompound(); // TODO
        }

        @Override
        public BattleRoyaleGameConfiguration deserializeGameConfiguration(NBTTagCompound nbt) {
            return new BattleRoyaleGameConfiguration(); // TODO
        }
    }
}
