package dev.toma.pubgmc.common.games.game.ffa;

import com.google.gson.JsonObject;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.loadout.EntityLoadout;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;
import dev.toma.pubgmc.api.game.util.GameRuleStorage;
import dev.toma.pubgmc.api.game.util.PlayerPropertyHolder;
import dev.toma.pubgmc.api.properties.SharedProperties;
import dev.toma.pubgmc.common.ai.EntityAIGunAttack;
import dev.toma.pubgmc.common.ai.EntityAIMoveIntoPlayzone;
import dev.toma.pubgmc.common.ai.EntityAITeamAwareNearestAttackableTarget;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.games.map.SpawnerPoint;
import dev.toma.pubgmc.common.games.playzone.AbstractDamagingPlayzone;
import dev.toma.pubgmc.common.games.playzone.StaticPlayzone;
import dev.toma.pubgmc.common.games.util.SpawnPointSelector;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class FFAGame implements Game<FFAGameConfiguration>, GameMenuProvider {

    private final List<GameEventListener> listeners = new ArrayList<>();
    private final UUID gameId;
    private final FFAGameConfiguration configuration;
    private final GameRuleStorage gameRuleStorage;
    private final FFAParticipantManager participantManager;
    private final FFALoadoutManager loadoutManager;
    private final PlayerPropertyHolder properties;
    private final SpawnPointSelector<SpawnerPoint> spawnerSelector;
    private AbstractDamagingPlayzone playzone;
    private boolean started;
    private long gametime;
    private int timeRemaining;

    public FFAGame(UUID gameId, FFAGameConfiguration configuration) {
        this.gameId = gameId;
        this.configuration = configuration;
        this.gameRuleStorage = new GameRuleStorage();
        this.participantManager = new FFAParticipantManager();
        this.loadoutManager = new FFALoadoutManager(EntityLoadout.EMPTY, getAvailableLoadouts());
        this.properties = new PlayerPropertyHolder();
        this.spawnerSelector = new SpawnPointSelector<>(GameMapPoints.SPAWNER, GameHelper::getActiveGameMap);

        this.addListener(new EventListener(this));
    }

    @Override
    public UUID getGameId() {
        return gameId;
    }

    @Override
    public GameType<FFAGameConfiguration, ?> getGameType() {
        return GameTypes.FFA;
    }

    @Override
    public FFAGameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void validateAndSetupForMap(World world, GameMap map) throws GameException {
        int participantCount = configuration.entityCount;
        Collection<SpawnerPoint> spawners = map.getPoints(GameMapPoints.SPAWNER);
        if (participantCount > spawners.size()) {
            throw new GameException(String.format("%s map does not provide enough spawn points for this game configuration. Required %d points, got %s", map.getMapName(), participantCount, spawners.size()));
        }
        playzone = new StaticPlayzone(map.bounds());
        playzone.setDamageOptions(AbstractDamagingPlayzone.DamageOptions.BOUNDS);
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public void onGameInit(World world) {
        properties.registerProperty(SharedProperties.KILLS, 0);
        properties.registerProperty(SharedProperties.GAME_TIMESTAMP, 0L);
        timeRemaining = configuration.gameDuration;
        List<EntityPlayer> playerList = world.playerEntities;
        for (int i = 0; i < Math.min(playerList.size(), configuration.entityCount); i++) {
            EntityPlayer player = playerList.get(i);
            participantManager.registerPlayer(player);
            GameHelper.moveToLobby(player);
            properties.register(player);
        }
    }

    @Override
    public void onGameStart(World world) {
        started = true;
        GameHelper.updateLoadedGameObjects(world, GenerationType.empty());
        List<EntityPlayer> participants = participantManager.getPlayerParticipants(world);
        for (EntityPlayer player : participants) {
            SpawnerPoint point = spawnerSelector.getPoint(world, participants);
            BlockPos pos = point.getPointPosition();
            GameHelper.teleport(player, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            if (!world.isRemote) {
                openMenu((EntityPlayerMP) player);
            }
        }
        if (!world.isRemote) {
            WorldServer worldServer = (WorldServer) world;
            configuration.worldConfiguration.apply(worldServer, gameRuleStorage);
            gameRuleStorage.storeValueAndSet(world, "naturalRegeneration", "false");
            gameRuleStorage.storeValueAndSet(world, "doMobSpawning", "false");
            gameRuleStorage.storeValueAndSet(world, "doMobLoot", "false");
            gameRuleStorage.storeValueAndSet(world, "showDeathMessages", "false");
            int aiCount = configuration.allowAi ? configuration.entityCount - participants.size() : 0;
            for (int i = 0; i < aiCount; i++) {
                createAi(worldServer);
            }
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
            playzone.hurtAllOutside(server, () -> participantManager.getLoadedParticipants(server));
        }
        ++gametime;
    }

    @Override
    public void onGameStopped(World world, GameData data) {
        started = false;
        gameRuleStorage.restoreGameRules(world);
        participantManager.getPlayerParticipants(world)
                .forEach(GameHelper::moveToLobby);
    }

    @Override
    public void openMenu(EntityPlayerMP player) {
        // TODO implement loadout selection
    }

    @Override
    public boolean playerLeaveGame(EntityPlayer player) {
        if (participantManager.isParticipant(player)) {
            participantManager.removePlayer(player.getUniqueID());
            GameHelper.moveToLobby(player);
            if (started) {
                // TODO add AI backup
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean playerJoinGame(EntityPlayer player) {
        if (participantManager.isParticipant(player)) {
            return false;
        }
        int players = participantManager.getPlayerParticipantsCount();
        if (players < configuration.entityCount) {
            participantManager.registerPlayer(player);
            if (started) {
                // TODO mark player for respawn
                // TODO replace one AI with this player
            } else {
                GameHelper.moveToLobby(player);
            }
            return true;
        }
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

    private void createAi(WorldServer world) {
        EntityAIPlayer aiPlayer = new EntityAIPlayer(world);
        List<Entity> entities = participantManager.getLoadedParticipants(world);
        SpawnerPoint point = spawnerSelector.getPoint(world, entities);
        BlockPos pos = point.getPointPosition();
        aiPlayer.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
        initAi(aiPlayer);
        aiPlayer.assignGameId(gameId);
        participantManager.registerAi(aiPlayer);
        world.spawnEntity(aiPlayer);
        List<EntityLoadout> available = getAvailableLoadouts();
        EntityLoadout loadout = PUBGMCUtil.randomListElement(available, world.rand);
        if (loadout != null) {
            UUID uuid = aiPlayer.getUniqueID();
            loadoutManager.select(uuid, loadout);
            applySelectedLoadout(aiPlayer);
        }
    }

    private void initAi(EntityAIPlayer player) {
        player.clearAI();
        EntityAIPlayer.addDefaultTasks(player);
        player.tasks.addTask(1, new EntityAIMoveIntoPlayzone(player, level -> playzone, 1.20F));
        player.tasks.addTask(2, new EntityAIGunAttack(player));
        player.targetTasks.addTask(1, new EntityAITeamAwareNearestAttackableTarget<>(player, EntityPlayer.class, true));
        player.targetTasks.addTask(1, new EntityAITeamAwareNearestAttackableTarget<>(player, EntityAIPlayer.class, true));
    }

    private List<EntityLoadout> getAvailableLoadouts() {
        List<EntityLoadout> list = new ArrayList<>();
        for (String key : configuration.loadoutFiles) {
            LoadoutManager.getLoadout(key).ifPresent(list::add);
        }
        return list;
    }

    private void applySelectedLoadout(EntityLivingBase entity) {
        EntityLoadout loadout = loadoutManager.getLoadout(entity.getUniqueID());
        LoadoutManager.apply(entity, loadout);
    }

    public static final class EventListener implements GameEventListener {

        private final FFAGame game;

        public EventListener(FFAGame game) {
            this.game = game;
        }
    }

    public static final class Serializer implements GameDataSerializer<FFAGameConfiguration, FFAGame> {

        @Override
        public NBTTagCompound serializeGameData(FFAGame game) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setUniqueId("gameId", game.gameId);
            nbt.setInteger("timeRemaining", game.timeRemaining);
            nbt.setLong("gameTime", game.gametime);
            nbt.setBoolean("started", game.started);
            nbt.setTag("gamerules", game.gameRuleStorage.serialize());
            nbt.setTag("participants", game.participantManager.serialize());
            nbt.setTag("props", game.properties.serialize());
            if (game.playzone != null) {
                nbt.setTag("playzone", PlayzoneType.serialize(game.playzone));
            }
            nbt.setTag("loadouts", game.loadoutManager.serialize());
            return nbt;
        }

        @Override
        public FFAGame deserializeGameData(NBTTagCompound nbt, FFAGameConfiguration configuration) {
            UUID gameId = nbt.getUniqueId("gameId");
            FFAGame ffaGame = new FFAGame(gameId, configuration);
            ffaGame.timeRemaining = nbt.getInteger("timeRemaining");
            ffaGame.gametime = nbt.getLong("gameTime");
            ffaGame.started = nbt.getBoolean("started");
            ffaGame.gameRuleStorage.deserialize(nbt.getCompoundTag("gamerules"));
            ffaGame.participantManager.deserialize(nbt.getCompoundTag("participants"));
            ffaGame.properties.deserialize(nbt.getCompoundTag("props"));
            if (nbt.hasKey("playzone")) {
                ffaGame.playzone = PlayzoneType.deserialize(nbt.getCompoundTag("playzone"));
            }
            ffaGame.loadoutManager.deserialize(nbt.getCompoundTag("loadouts"));
            return ffaGame;
        }

        @Override
        public NBTTagCompound serializeGameConfiguration(FFAGameConfiguration configuration) {
            return configuration.serialize();
        }

        @Override
        public FFAGameConfiguration deserializeGameConfiguration(NBTTagCompound nbt) {
            return FFAGameConfiguration.deserialize(nbt);
        }

        @Override
        public JsonObject serializeConfigurationToJson(FFAGameConfiguration configuration) {
            return configuration.jsonSerialize();
        }

        @Override
        public FFAGameConfiguration deserializeConfigurationFromJson(JsonObject object) {
            return FFAGameConfiguration.jsonDeserialize(object);
        }
    }
}
