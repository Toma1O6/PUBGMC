package dev.toma.pubgmc.common.games;

import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameDataSerializer;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.UUID;

public final class NoGame implements Game {

    public static final Game INSTANCE = new NoGame();

    private NoGame() {}

    @Override
    public GameType<?> getGameType() {
        return null;
    }

    @Override
    public UUID getGameId() {
        return GameHelper.DEFAULT_UUID;
    }

    @Override
    public void onGameInit(World world) {
    }

    @Override
    public void onGameStart(World world) {
    }

    @Override
    public void onGameStopped(World world) {
    }

    @Override
    public void onGameTick(World world) {
    }

    public static final class Serializer implements GameDataSerializer<NoGame> {

        @Override
        public NBTTagCompound serializeGameData(NoGame game) {
            return new NBTTagCompound();
        }

        @Override
        public NoGame deserializeGameData(NBTTagCompound nbt) {
            return (NoGame) INSTANCE;
        }
    }
}
