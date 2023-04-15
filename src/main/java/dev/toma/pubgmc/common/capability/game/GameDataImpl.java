package dev.toma.pubgmc.common.capability.game;

import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.NoGame;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GameDataImpl implements GameData {

    private final World world;
    private Game<?> gameInstance;
    private GameType<?, ?> selectedGameType;

    public GameDataImpl() {
        this(null);
    }

    public GameDataImpl(World world) {
        this.world = world;
        this.gameInstance = NoGame.INSTANCE;
        this.selectedGameType = gameInstance.getGameType();
    }

    @Override
    public void tick() {
        gameInstance.onGameTick(world);
    }

    @Override
    public Game<?> getCurrentGame() {
        return gameInstance;
    }

    @Override
    public GameType<?, ?> getSelectedGameType() {
        return selectedGameType;
    }

    @Override
    public void setSelectedGameType(GameType<?, ?> gameType) {
        this.selectedGameType = gameType;
    }

    @Override
    public void sendGameDataToClients() {
        // TODO implement
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("selectedGameType", selectedGameType.getIdentifier().toString());
        nbt.setTag("game", GameType.serialize(gameInstance));
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        ResourceLocation gameType = new ResourceLocation(nbt.getString("selectedGameType"));
        GameType<?, ?> type = PubgmcRegistries.GAME_TYPES.getValue(gameType);
        selectedGameType = type != null ? type : GameTypes.NO_GAME;
        gameInstance = GameType.deserialize(nbt.getCompoundTag("game"));
        if (gameInstance == null) {
            gameInstance = NoGame.INSTANCE;
        }
    }
}
