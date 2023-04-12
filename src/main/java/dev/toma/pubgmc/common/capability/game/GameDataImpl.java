package dev.toma.pubgmc.common.capability.game;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class GameDataImpl implements GameData {

    private final World world;
    private Game gameInstance;
    private GameType<?> selectedGameType;

    public GameDataImpl(World world) {
        this.world = world;
    }

    @Override
    public Game getCurrentGame() {
        return gameInstance;
    }

    @Override
    public GameType<?> getSelectedGameType() {
        return selectedGameType;
    }

    @Override
    public void setSelectedGameType(GameType<?> gameType) {
        this.selectedGameType = gameType;
    }

    @Override
    public void sendGameDataToClients() {
        // TODO implement
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return new NBTTagCompound();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }
}
