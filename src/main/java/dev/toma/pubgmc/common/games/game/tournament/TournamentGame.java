package dev.toma.pubgmc.common.games.game.tournament;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamInviteManager;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.games.GameTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.UUID;
import java.util.function.Consumer;

public class TournamentGame implements TeamGame<TournamentGameConfiguration> {

    private final UUID gameId;
    private final TournamentGameConfiguration configuration;

    public TournamentGame(UUID gameId, TournamentGameConfiguration cfg) {
        this.gameId = gameId;
        this.configuration = cfg;
    }

    @Override
    public void validateAndSetupForMap(World world, GameMap map) throws GameException {

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
    public void onGameStopped(World world, GameData data) {

    }

    @Override
    public boolean playerLeaveGame(EntityPlayer player) {
        return false;
    }

    @Override
    public boolean playerJoinGame(EntityPlayer player) {
        return false;
    }

    @Override
    public void addListener(GameEventListener listener) {

    }

    @Override
    public void invokeEvent(Consumer<GameEventListener> consumer) {

    }

    @Override
    public GenerationType.Context getGeneratorContext() {
        return GenerationType.empty();
    }

    @Override
    public UUID getGameId() {
        return gameId;
    }

    @Override
    public TournamentGameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public GameType<TournamentGameConfiguration, ?> getGameType() {
        return GameTypes.TOURNAMENT;
    }

    @Override
    public TeamManager getTeamManager() {
        return null;
    }

    @Override
    public TeamInviteManager getInviteManager() {
        return null;
    }

    public static void initAi(EntityAIPlayer player, TournamentGame game) {

    }

    public static final class Serializer implements GameDataSerializer<TournamentGameConfiguration, TournamentGame> {

        @Override
        public NBTTagCompound serializeGameData(TournamentGame game) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setUniqueId("gameId", game.gameId);
            return nbt;
        }

        @Override
        public TournamentGame deserializeGameData(NBTTagCompound nbt, TournamentGameConfiguration configuration, World world) {
            UUID gameId = nbt.getUniqueId("gameId");
            TournamentGame game = new TournamentGame(gameId, configuration);
            return game;
        }

        @Override
        public void serializeGameConfiguration(TournamentGameConfiguration configuration, DataWriter<?> writer) {
            configuration.serialize(writer);
        }

        @Override
        public TournamentGameConfiguration deserializeGameConfiguration(DataReader<?> reader) {
            return TournamentGameConfiguration.deserialize(reader);
        }
    }
}
