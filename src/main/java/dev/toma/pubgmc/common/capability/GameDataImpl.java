package dev.toma.pubgmc.common.capability;

import com.google.common.collect.ImmutableMap;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.map.GameLobby;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.map.GameMapInstance;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.NoGame;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_PacketSendGameData;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameDataImpl implements GameData {

    private final World world;
    private Game<?> gameInstance;
    private GameType<?, ?> selectedGameType;
    @Nullable
    private GameLobby lobby;
    private String activeMap = "";
    private String activeSubMap = "";
    private final Map<String, GameMapInstance> maps;

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
        if (gameInstance.isStarted()) {
            try {
                gameInstance.onGameTick(world);
            } catch (Exception e) {
                Pubgmc.logger.fatal("Fatal error occurred while during game tick, cancelling game", e);
                GameHelper.resetErroredGameData(world);
            }
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
    public GameMapInstance getGameMap(String mapName) {
        return maps.get(mapName);
    }

    @Override
    public void registerGameMap(GameMapInstance map) {
        maps.put(map.getMapName(), map);
    }

    @Override
    public void deleteGameMap(String name) {
        maps.remove(name);
    }

    @Override
    public Map<String, GameMapInstance> getRegisteredGameMaps() {
        return ImmutableMap.copyOf(maps);
    }

    @Override
    public void setActiveGameMapName(@Nullable String mapName, @Nullable String subMapName) {
        this.activeMap = mapName;
        this.activeSubMap = subMapName;
    }

    @Nullable
    @Override
    public String getActiveGameMapName() {
        return activeMap;
    }

    @Override
    public Optional<GameMap> getActiveGameMap() {
        if (StringUtils.isBlank(activeMap)) {
            return Optional.empty();
        }
        GameMapInstance instance = getGameMap(activeMap);
        if (instance == null) {
            return Optional.empty();
        }
        if (!StringUtils.isBlank(activeSubMap)) {
            return Optional.of(instance.getSubmapOrSelf(activeSubMap));
        }
        return Optional.of(instance);
    }

    @Override
    public void sendGameDataToClients() {
        if (!world.isRemote) {
            PacketHandler.sendToAllClients(new S2C_PacketSendGameData(serializeNBT()));
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
        if (activeMap != null) {
            nbt.setString("activeMap", activeMap);
        }
        if (activeSubMap != null) {
            nbt.setString("activeSubMap", activeSubMap);
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        ResourceLocation gameType = new ResourceLocation(nbt.getString("selectedGameType"));
        GameType<?, ?> type = PubgmcRegistries.GAME_TYPES.getValue(gameType);
        selectedGameType = type != null ? type : GameTypes.NO_GAME;
        gameInstance = GameType.deserialize(nbt.getCompoundTag("game"), world);
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
            GameMapInstance map = GameMapInstance.deserialize(mapData, world);
            maps.put(map.getMapName(), map);
        }
        activeMap = nbt.hasKey("activeMap") ? nbt.getString("activeMap") : null;
        activeSubMap = nbt.hasKey("activeSubMap") ? nbt.getString("activeSubMap") : null;
    }
}
