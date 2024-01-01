package dev.toma.pubgmc.common.games;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.UUID;
import java.util.function.Consumer;

public final class NoGame implements Game<NoGame.NoConfiguration> {

    public static final Game<?> INSTANCE = new NoGame();

    private NoGame() {
    }

    @Override
    public GameType<NoConfiguration, ?> getGameType() {
        return GameTypes.NO_GAME;
    }

    @Override
    public UUID getGameId() {
        return GameHelper.DEFAULT_UUID;
    }

    @Override
    public NoConfiguration getConfiguration() {
        return NoConfiguration.INSTANCE;
    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public void validateAndSetupForMap(World world, GameMap map) throws GameException {
        throw new GameException("This type of game cannot be started");
    }

    @Override
    public void onGameInit(World world) {
    }

    @Override
    public void onGameStart(World world) {
    }

    @Override
    public void onGameStopped(World world, GameData data) {
    }

    @Override
    public void onGameTick(World world) {
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

    public static final class Serializer implements GameDataSerializer<NoConfiguration, NoGame> {

        @Override
        public NBTTagCompound serializeGameData(NoGame game) {
            return new NBTTagCompound();
        }

        @Override
        public NoGame deserializeGameData(NBTTagCompound nbt, NoConfiguration configuration, World world) {
            return (NoGame) INSTANCE;
        }

        @Override
        public void serializeGameConfiguration(NoConfiguration configuration, DataWriter<?> writer) {
        }

        @Override
        public NoConfiguration deserializeGameConfiguration(DataReader<?> reader) {
            return NoConfiguration.INSTANCE;
        }
    }

    public static final class NoConfiguration implements GameConfiguration {

        public static final NoConfiguration INSTANCE = new NoConfiguration();

        private NoConfiguration() {
        }

        @Override
        public void performCorrections() {
        }
    }
}
