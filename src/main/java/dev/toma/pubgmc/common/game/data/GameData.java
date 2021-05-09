package dev.toma.pubgmc.common.game.data;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.common.game.AbstractGame;
import dev.toma.pubgmc.common.game.GameLaunchConfig;
import dev.toma.pubgmc.common.game.map.Area2D;
import dev.toma.pubgmc.common.game.map.Area3D;
import dev.toma.pubgmc.common.game.map.GameMap;
import dev.toma.pubgmc.common.game.map.MapCollection;
import dev.toma.pubgmc.util.IEventHandler;
import dev.toma.pubgmc.util.handlers.EventHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameData implements IGameData {

    private final World world;
    private final IEventHandler<INBTSerializable<NBTTagCompound>> eventHandler;
    private final MapCollection maps;
    private Map<Long, AbstractGame> activeGames;
    private Area3D globalLobby;

    public GameData(World world) {
        this.world = world;
        this.eventHandler = EventHandler.emptyEventHandler();
        this.maps = new MapCollection();
        this.activeGames = new HashMap<>();

        eventHandler.subscribe(maps);
    }

    @Override
    public void tickGames() {

    }

    @Override
    public MapCollection getMaps() {
        return maps;
    }

    @Override
    public void launchGame(GameLaunchConfig<?> config) {
        AbstractGame game = config.launch();
        long gameID = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        activeGames.put(gameID, game);
    }

    @Override
    public Area3D getGlobalLobby() {
        return globalLobby;
    }

    @Override
    public Map<Long, AbstractGame> getGames() {
        return activeGames;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList activeGameList = new NBTTagList();
        for (Map.Entry<Long, AbstractGame> entry : activeGames.entrySet()) {
            NBTTagCompound gameData = new NBTTagCompound();
            AbstractGame game = entry.getValue();
            gameData.setLong("id", entry.getKey());
            gameData.setString("type", game.getGameType().getRegistryKey().toString());
            // TODO save game state
            activeGameList.appendTag(gameData);
        }
        nbt.setTag("activeGames", activeGameList);
        if(globalLobby != null) {
            // TODO save global lobby
        }
        NBTTagList gameMapList = new NBTTagList();

        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }

    private boolean checkOverlaps(GameMap map) {
        Area2D area2D = map.getArea();
        for (GameMap gameMap : maps) {
            Area2D mapArea = gameMap.getArea();
            if(mapArea.overlaps(area2D)) {
                return true;
            }
        }
        return false;
    }

    public static IGameData getData(World world) {
        return null;
    }
}
