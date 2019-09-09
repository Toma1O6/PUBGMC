package com.toma.pubgmc.common.capability;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.Lobby;
import com.toma.pubgmc.init.GameRegistry;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.world.MapLocation;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Preparation for customizable map zone etc
 */
public interface IGameData extends INBTSerializable<NBTTagCompound> {
    boolean isPlaying();

    void setPlaying(boolean play);

    void addSpawnLocation(MapLocation location);

    boolean removeSpawnLocation(String name);

    List<MapLocation> getSpawnLocations();

    void setMapCenter(double x, double z, int size);

    BlockPos getMapCenter();

    int getMapSize();

    String getGameID();

    void setGameID(String id);

    void createGameID();

    void setGame(Game game);

    Game getCurrentGame();

    void setLobby(Lobby lobby);

    Lobby getLobby();

    boolean isInactiveGame();


    class GameDataStorage implements IStorage<IGameData> {
        @Override
        public void readNBT(Capability<IGameData> capability, IGameData instance, EnumFacing side, NBTBase nbt) {
            instance.deserializeNBT(nbt instanceof NBTTagCompound ? (NBTTagCompound)nbt : new NBTTagCompound());
        }

        @Override
        public NBTBase writeNBT(Capability<IGameData> capability, IGameData instance, EnumFacing side) {
            return instance.serializeNBT();
        }
    }

    class GameData implements IGameData {
        boolean isPlaying;
        List<MapLocation> locations = new ArrayList<>();
        List<String> names = new ArrayList<>();
        BlockPos gameZoneCenter;
        int mapSize;
        String gameHash;
        Game game;
        Lobby lobby;

        @Override
        public boolean isPlaying() {
            return isPlaying;
        }

        @Override
        public void setPlaying(boolean play) {
            this.isPlaying = play;
        }

        @Override
        public void addSpawnLocation(MapLocation location) {
            locations.add(location);
        }

        @Override
        public boolean removeSpawnLocation(String name) {
            MapLocation location = this.findLocationByName(name);
            if(location == null) {
                Pubgmc.logger.error("Attempted to remove location with name {} which doesn't exist!", name);
                return false;
            }
            locations.remove(location);
            return true;
        }

        @Override
        public List<MapLocation> getSpawnLocations() {
            return locations;
        }

        @Override
        public void setMapCenter(double x, double z, int size) {
            gameZoneCenter = new BlockPos(x, 0d, z);
            this.mapSize = size;
        }

        @Override
        public BlockPos getMapCenter() {
            return gameZoneCenter;
        }

        @Override
        public int getMapSize() {
            return mapSize;
        }

        @Override
        public String getGameID() {
            return gameHash == null || gameHash.isEmpty() ? "undefined" : gameHash;
        }

        @Override
        public void setGameID(String id) {
            this.gameHash = id;
        }

        @Override
        public void createGameID() {
            gameHash = PUBGMCUtil.generateID(16);
        }

        @Override
        public void setGame(Game game) {
            this.game = game;
        }

        @Override
        public Game getCurrentGame() {
            if(game == null) {
                this.setGame(GameRegistry.findGameInRegistry("inactive"));
            }
            return game;
        }

        @Override
        public void setLobby(Lobby lobby) {
            this.lobby = lobby;
        }

        @Override
        public Lobby getLobby() {
            return lobby;
        }

        @Override
        public boolean isInactiveGame() {
            return game.registryName.getResourcePath().equals("inactive");
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound c = new NBTTagCompound();
            NBTTagList positions = new NBTTagList();

            if (gameZoneCenter == null) {
                this.setMapCenter(0, 0, 0);
            }

            c.setBoolean("isPlaying", isPlaying);
            c.setInteger("mapCenterX", gameZoneCenter.getX());
            c.setInteger("mapCenterZ", gameZoneCenter.getZ());
            c.setInteger("mapSize", mapSize);
            NBTTagList locationsList = new NBTTagList();
            for (int i = 0; i < locations.size(); i++) {
                locationsList.appendTag(locations.get(i).serializeNBT());
            }

            c.setTag("list", locationsList);
            c.setString("gameID", gameHash);
            c.setTag("lobby", Lobby.toNBT(lobby));
            c.setString("gameMode", this.getCurrentGame().registryName.getResourcePath());
            c.setTag("game", this.getCurrentGame().serializeNBT());
            return c;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            isPlaying = nbt.getBoolean("isPlaying");
            gameZoneCenter = new BlockPos(nbt.getInteger("mapCenterX"), 0, nbt.getInteger("mapCenterZ"));
            mapSize = nbt.getInteger("mapSize");
            NBTTagList locList = nbt.getTagList("list", Constants.NBT.TAG_COMPOUND);
            for(int i = 0; i < locList.tagCount(); i++) {
                MapLocation location = new MapLocation(null, null);
                location.deserializeNBT(locList.getCompoundTagAt(i));
                this.addSpawnLocation(location);
            }
            gameHash = nbt.getString("gameID");
            lobby = Lobby.fromNBT(nbt.getCompoundTag("lobby"));
            this.setGame(GameRegistry.findGameInRegistry(nbt.getString("gameMode")));
            this.getCurrentGame().deserializeNBT(nbt.getCompoundTag("game"));
        }

        private MapLocation findLocationByName(String name) {
            for(MapLocation mapLocation : locations) {
                if(mapLocation.name().equalsIgnoreCase(name)) {
                    return mapLocation;
                }
            }
            return null;
        }
    }

    class GameDataProvider implements ICapabilitySerializable<NBTBase> {
        @CapabilityInject(IGameData.class)
        public static final Capability<IGameData> GAMEDATA = null;

        private IGameData instance = GAMEDATA.getDefaultInstance();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability == GAMEDATA;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return capability == GAMEDATA ? GAMEDATA.cast(instance) : null;
        }

        @Override
        public NBTBase serializeNBT() {
            return GAMEDATA.getStorage().writeNBT(GAMEDATA, instance, null);
        }

        @Override
        public void deserializeNBT(NBTBase nbt) {
            GAMEDATA.getStorage().readNBT(GAMEDATA, instance, null, nbt);
        }
    }
}
