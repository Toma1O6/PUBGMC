package dev.toma.pubgmc.common.capability.game;

import com.google.common.collect.ImmutableMap;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameConfiguration;
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
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

public class GameDataImpl implements GameData {

    private final World world;
    private Game<?> gameInstance;
    private GameType<?, ?> selectedGameType;
    @Nullable
    private GameLobby lobby;
    private final Map<String, GameMap> maps;
    private final Map<GameType<?, ?>, GameConfiguration> configurationMap;

    public GameDataImpl() {
        this(null);
    }

    public GameDataImpl(World world) {
        this.world = world;
        this.gameInstance = NoGame.INSTANCE;
        this.selectedGameType = gameInstance.getGameType();
        this.maps = new HashMap<>();
        this.configurationMap = new IdentityHashMap<>();
    }

    @Override
    public void tick() {
        if (gameInstance.isStarted()) {
            gameInstance.onGameTick(world);
        }
    }

    @Override
    public Game<?> getCurrentGame() {
        return gameInstance;
    }

    @Override
    public void setActiveGame(@Nullable Game<?> game) {
        gameInstance = game != null ? game : NoGame.INSTANCE;
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
    public void deleteGameMap(String name) {
        maps.remove(name);
    }

    @Override
    public Map<String, GameMap> getRegisteredGameMaps() {
        return ImmutableMap.copyOf(maps);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <CFG extends GameConfiguration, G extends Game<CFG>> CFG getGameConfiguration(GameType<CFG, G> type) {
        return (CFG) configurationMap.getOrDefault(type, type.getGameConfiguration());
    }

    @Override
    public <CFG extends GameConfiguration, G extends Game<CFG>> void saveGameConfiguration(GameType<CFG, G> type, CFG config) {
        configurationMap.put(type, config);
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
        NBTTagCompound configs = new NBTTagCompound();
        serializeConfigs(configs);
        nbt.setTag("configs", configs);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        ResourceLocation gameType = new ResourceLocation(nbt.getString("selectedGameType"));
        GameType<?, ?> type = PubgmcRegistries.GAME_TYPES.getValue(gameType);
        selectedGameType = type != null ? type : GameTypes.NO_GAME;
        NBTTagCompound configs = new NBTTagCompound();
        deserializeConfigs(configs);
        gameInstance = GameType.deserialize(nbt.getCompoundTag("game"), this::getGameConfiguration);
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

    @SuppressWarnings("unchecked")
    private <CFG extends GameConfiguration> void serializeConfigs(NBTTagCompound nbt) {
        for (Map.Entry<GameType<?, ?>, GameConfiguration> entry : configurationMap.entrySet()) {
            GameType<CFG, ?> type = (GameType<CFG, ?>) entry.getKey();
            ResourceLocation identifier = type.getIdentifier();
            CFG config = (CFG) entry.getValue();
            nbt.setTag(identifier.toString(), GameType.serializeConfiguration(type, config));
        }
    }

    private void deserializeConfigs(NBTTagCompound nbt) {
        configurationMap.clear();
        Set<String> keys = nbt.getKeySet();
        for (String key : keys) {
            ResourceLocation gameTypeId = new ResourceLocation(key);
            GameType<?, ?> type = PubgmcRegistries.GAME_TYPES.getValue(gameTypeId);
            if (type == null) {
                continue;
            }
            NBTTagCompound compound = nbt.getCompoundTag(key);
            GameConfiguration configuration = GameType.deserializeConfiguration(type, compound);
            configurationMap.put(type, configuration);
        }
    }
}
