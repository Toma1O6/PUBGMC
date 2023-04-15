package dev.toma.pubgmc.common.games.game.battleroyale;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameDataSerializer;
import dev.toma.pubgmc.api.game.GameStartException;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.map.GameMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.UUID;

public class BattleRoyaleGame implements Game<BattleRoyaleGameConfiguration> {

    private final UUID gameId;
    private final BattleRoyaleGameConfiguration configuration;

    public BattleRoyaleGame(UUID gameId) {
        this.gameId = gameId;
        this.configuration = new BattleRoyaleGameConfiguration(); // TODO
    }

    @Override
    public UUID getGameId() {
        return gameId;
    }

    @Override
    public GameType<?> getGameType() {
        return null; // TODO
    }

    @Override
    public BattleRoyaleGameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void performGameMapValidations(World world, GameMap map) throws GameStartException {
    }

    @Override
    public void onGameInit(World world) {

    }

    @Override
    public void onGameStart(World world) {

    }

    @Override
    public void onGameTick(World world) {

    }

    @Override
    public void onGameStopped(World world) {

    }

    public static final class Serializer implements GameDataSerializer<BattleRoyaleGame> {

        @Override
        public NBTTagCompound serializeGameData(BattleRoyaleGame game) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setUniqueId("gameId", game.gameId);

            return nbt;
        }

        @Override
        public BattleRoyaleGame deserializeGameData(NBTTagCompound nbt) {
            UUID gameId = nbt.getUniqueId("gameId");

            return new BattleRoyaleGame(gameId);
        }
    }
}
