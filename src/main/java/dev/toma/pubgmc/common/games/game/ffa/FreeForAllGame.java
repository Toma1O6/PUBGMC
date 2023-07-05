package dev.toma.pubgmc.common.games.game.ffa;

import com.google.gson.JsonObject;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.util.GameRuleStorage;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.games.map.SpawnerPoint;
import dev.toma.pubgmc.common.games.playzone.AbstractDamagingPlayzone;
import dev.toma.pubgmc.common.games.playzone.StaticPlayzone;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.*;
import java.util.function.Consumer;

public class FreeForAllGame implements Game<FreeForAllGameConfiguration> {

    private static final AbstractDamagingPlayzone.DamageOptions BOUNDS_DAMAGE = new AbstractDamagingPlayzone.DamageOptions(5.0F, 20);
    private final List<GameEventListener> listeners = new ArrayList<>();
    private final UUID gameId;
    private final FreeForAllGameConfiguration configuration;
    private final Set<UUID> participatingPlayers;
    private final GameRuleStorage gameRuleStorage;
    private AbstractDamagingPlayzone playzone;
    private boolean started;
    private int timeRemaining;

    public FreeForAllGame(UUID gameId, FreeForAllGameConfiguration configuration) {
        this.gameId = gameId;
        this.configuration = configuration;
        this.participatingPlayers = new HashSet<>();
        this.gameRuleStorage = new GameRuleStorage();

        this.addListener(new EventListener());
    }

    @Override
    public UUID getGameId() {
        return gameId;
    }

    @Override
    public GameType<FreeForAllGameConfiguration, ?> getGameType() {
        return GameTypes.FFA;
    }

    @Override
    public FreeForAllGameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void performGameMapValidations(World world, GameMap map) throws GameException {
        int participantCount = configuration.entityCount;
        Collection<SpawnerPoint> spawners = map.getPoints(GameMapPoints.SPAWNER);
        if (participantCount > spawners.size()) {
            throw new GameException(String.format("%s map does not provide enough spawn points for this game configuration. Required %d points, got %s", map.getMapName(), participantCount, spawners.size()));
        }
        playzone = new StaticPlayzone(map.bounds());
        playzone.setDamageOptions(BOUNDS_DAMAGE);
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public void onGameInit(World world) {
        timeRemaining = configuration.gameDuration;
        List<EntityPlayer> playerList = world.playerEntities;
        for (int i = 0; i < Math.min(playerList.size(), configuration.entityCount); i++) {
            EntityPlayer player = playerList.get(i);
            participatingPlayers.add(player.getUniqueID());
        }
    }

    @Override
    public void onGameStart(World world) {
        started = true;
        GameHelper.updateLoadedGameObjects(world);
        if (!world.isRemote) {
            WorldServer worldServer = (WorldServer) world;
            configuration.worldConfiguration.apply(worldServer, gameRuleStorage);
            gameRuleStorage.storeValueAndSet(world, "naturalRegeneration", "false");
            gameRuleStorage.storeValueAndSet(world, "doMobSpawning", "false");
            gameRuleStorage.storeValueAndSet(world, "doMobLoot", "false");
            gameRuleStorage.storeValueAndSet(world, "showDeathMessages", "false");
        }
    }

    @Override
    public void onGameTick(World world) {
        if (started) {
            if (--timeRemaining < 0) {
                // TODO end game
                GameHelper.stopGame(world);
            }
        }
        if (!world.isRemote) {
            WorldServer server = (WorldServer) world;
            playzone.hurtAllOutside(server, Collections.emptyList()); // TODO all entities
        }
    }

    @Override
    public void onGameStopped(World world, GameData data) {
        // TODO implement
        started = false;
        gameRuleStorage.restoreGameRules(world);
        participatingPlayers.forEach(uuid -> {
            EntityPlayer player = world.getPlayerEntityByUUID(uuid);
            if (player != null) {
                GameHelper.moveToLobby(player);
            }
        });
    }

    @Override
    public boolean playerLeaveGame(EntityPlayer player) {
        // TODO implement
        return false;
    }

    @Override
    public boolean playerJoinGame(EntityPlayer player) {
        // TODO implement
        return false;
    }

    @Override
    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void invokeEvent(Consumer<GameEventListener> consumer) {
        listeners.forEach(consumer);
    }

    public static final class EventListener implements GameEventListener {


    }

    public static final class Serializer implements GameDataSerializer<FreeForAllGameConfiguration, FreeForAllGame> {

        @Override
        public NBTTagCompound serializeGameData(FreeForAllGame game) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setUniqueId("gameId", game.gameId);
            return nbt;
        }

        @Override
        public FreeForAllGame deserializeGameData(NBTTagCompound nbt, FreeForAllGameConfiguration configuration) {
            UUID gameId = nbt.getUniqueId("gameId");
            return new FreeForAllGame(gameId, configuration);
        }

        @Override
        public NBTTagCompound serializeGameConfiguration(FreeForAllGameConfiguration configuration) {
            return configuration.serialize();
        }

        @Override
        public FreeForAllGameConfiguration deserializeGameConfiguration(NBTTagCompound nbt) {
            return FreeForAllGameConfiguration.deserialize(nbt);
        }

        @Override
        public JsonObject serializeConfigurationToJson(FreeForAllGameConfiguration configuration) {
            return configuration.jsonSerialize();
        }

        @Override
        public FreeForAllGameConfiguration deserializeConfigurationFromJson(JsonObject object) {
            return FreeForAllGameConfiguration.jsonDeserialize(object);
        }
    }
}
