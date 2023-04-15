package dev.toma.pubgmc.common.capability.game;

import com.google.common.collect.ImmutableMap;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameLobby;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.NoGame;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.S2C_SendGameData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class GameDataImpl implements GameData {

    private final World world;
    private Game<?> gameInstance;
    private GameType<?, ?> selectedGameType;
    @Nullable
    private GameLobby lobby;
    private final Map<String, GameMap> maps;

    public GameDataImpl() {
        this(null);
    }

    public GameDataImpl(World world) {
        this.world = world;
        this.gameInstance = NoGame.INSTANCE;
        this.selectedGameType = gameInstance.getGameType();
        this.maps = new HashMap<>();
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

    @Nullable
    @Override
    public GameLobby getGameLobby() {
        return lobby;
    }

    @Override
    public void setGameLobby(GameLobby lobby) {
        this.lobby = lobby;
    }

    @Nullable
    @Override
    public GameMap getGameMap(String mapName) {
        return maps.get(mapName);
    }

    @Override
    public void registerGameMap(GameMap map) {
        maps.put(map.getMapName(), map);
    }

    @Override
    public Map<String, GameMap> getRegisteredGameMaps() {
        return ImmutableMap.copyOf(maps);
    }

    @Override
    public void sendGameDataToClients() {
        if (!world.isRemote) {
            PacketHandler.sendToAllClients(new S2C_SendGameData(serializeNBT()));
        }
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("selectedGameType", selectedGameType.getIdentifier().toString());
        nbt.setTag("game", GameType.serialize(gameInstance));
        if (lobby != null) {
            nbt.setTag("gameLobby", lobby.serialize());
        }
        NBTTagList mapsNbt = new NBTTagList();
        maps.values().forEach(map -> mapsNbt.appendTag(map.serialize()));
        nbt.setTag("maps", mapsNbt);
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
        if (nbt.hasKey("gameLobby", Constants.NBT.TAG_COMPOUND)) {
            lobby = GameLobby.deserialize(nbt.getCompoundTag("gameLobby"));
        }
        maps.clear();
        NBTTagList mapsNbt = nbt.getTagList("maps", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < mapsNbt.tagCount(); i++) {
            NBTTagCompound mapData = mapsNbt.getCompoundTagAt(i);
            GameMap map = GameMap.deserialize(mapData);
            maps.put(map.getMapName(), map);
        }
    }
}
