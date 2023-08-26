package dev.toma.pubgmc.common.games.game.ffa;

import com.google.gson.JsonObject;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.event.GameEvent;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.loadout.EntityLoadout;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.mutator.GameMutatorHelper;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;
import dev.toma.pubgmc.api.game.util.DeathMessage;
import dev.toma.pubgmc.api.game.util.DeathMessageContainer;
import dev.toma.pubgmc.api.game.util.GameRuleStorage;
import dev.toma.pubgmc.api.game.util.PlayerPropertyHolder;
import dev.toma.pubgmc.api.properties.SharedProperties;
import dev.toma.pubgmc.common.ai.EntityAIGunAttack;
import dev.toma.pubgmc.common.ai.EntityAIMoveIntoPlayzone;
import dev.toma.pubgmc.common.ai.EntityAITeamAwareNearestAttackableTarget;
import dev.toma.pubgmc.common.ai.EntityAIVisitMapPoint;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.game.SimpleLoadoutManager;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.games.map.SpawnerPoint;
import dev.toma.pubgmc.common.games.playzone.AbstractDamagingPlayzone;
import dev.toma.pubgmc.common.games.playzone.StaticPlayzone;
import dev.toma.pubgmc.common.games.util.SpawnPointSelector;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.S2C_PacketLoadoutSelect;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;

public class FFAGame implements Game<FFAGameConfiguration>, GameMenuProvider, LoadoutHandler {

    private static final ITextComponent LOADOUT_MESSAGE = new TextComponentTranslation("message.pubgmc.game.select_loadout");
    private static final String GAME_ENDED_KEY = "message.pubgmc.game.ffa.game_ended";

    private final List<GameEventListener> listeners = new ArrayList<>();
    private final UUID gameId;
    private final FFAGameConfiguration configuration;
    private final GameRuleStorage gameRuleStorage;
    private final FFAParticipantManager participantManager;
    private final SimpleLoadoutManager loadoutManager;
    private final PlayerPropertyHolder properties;
    private final SpawnPointSelector<SpawnerPoint> spawnerSelector;
    private final DeathMessageContainer deathMessages;
    private AbstractDamagingPlayzone playzone;
    private boolean started;
    private long gametime;
    private int timeRemaining;
    private boolean completed;
    private int completionTimer = 60;

    public FFAGame(UUID gameId, FFAGameConfiguration configuration) {
        this.gameId = gameId;
        this.configuration = configuration;
        this.gameRuleStorage = new GameRuleStorage();
        this.participantManager = new FFAParticipantManager();
        this.loadoutManager = new SimpleLoadoutManager(EntityLoadout.EMPTY, getAvailableLoadouts());
        this.properties = new PlayerPropertyHolder();
        this.spawnerSelector = new SpawnPointSelector<>(GameMapPoints.SPAWNER, GameHelper::getActiveGameMapOrSubMap);
        this.deathMessages = new DeathMessageContainer(7, 60);

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
            player.sendMessage(LOADOUT_MESSAGE);
            GameHelper.moveToLobby(player);
            properties.register(player);
        }
    }

    @Override
    public void onGameStart(World world) {
        started = true;
        GameHelper.updateLoadedGameObjects(world, getGeneratorContext());
        List<EntityPlayer> participants = participantManager.getPlayerParticipants(world);
        for (EntityPlayer player : participants) {
            SpawnerPoint point = spawnerSelector.getPoint(world, participants);
            BlockPos pos = point.getPointPosition();
            GameHelper.teleport(player, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            GameHelper.resetPlayerData(player);
            player.setGameType(net.minecraft.world.GameType.ADVENTURE);
            if (!world.isRemote) {
                if (!loadoutManager.hasSelectedLoadout(player.getUniqueID())) {
                    openMenu((EntityPlayerMP) player);
                } else {
                    loadoutManager.applyLoadout(player);
                }
            }
        }
        if (!world.isRemote) {
            WorldServer worldServer = (WorldServer) world;
            configuration.worldConfiguration.apply(worldServer, gameRuleStorage);
            gameRuleStorage.storeValueAndSet(world, GameRuleStorage.NATURAL_REGENERATION, GameRuleStorage.FALSE);
            gameRuleStorage.storeValueAndSet(world, GameRuleStorage.MOB_SPAWNING, GameRuleStorage.FALSE);
            gameRuleStorage.storeValueAndSet(world, GameRuleStorage.MOB_LOOT, GameRuleStorage.FALSE);
            gameRuleStorage.storeValueAndSet(world, GameRuleStorage.SHOW_DEATH_MESSAGES, GameRuleStorage.FALSE);
            int aiCount = configuration.allowAi ? configuration.entityCount - participants.size() : 0;
            for (int i = 0; i < aiCount; i++) {
                createAi(worldServer);
            }
        }
    }

    @Override
    public void onGameTick(World world) {
        if (completed && --completionTimer < 0) {
            GameHelper.stopGame(world);
            return;
        }
        if (started) {
            if (--timeRemaining < 0) {
                verifyGameCompletion(world);
                return;
            }
        }
        if (!world.isRemote) {
            WorldServer server = (WorldServer) world;
            playzone.hurtAllOutside(server, () -> participantManager.getLoadedParticipants(server));
            if (gametime % 20L == 0L) {
                respawnAiEntities(server);
            }
        }
        deathMessages.tick();
        ++gametime;
    }

    @Override
    public void onGameStopped(World world, GameData data) {
        started = false;
        gameRuleStorage.restoreGameRules(world);
        participantManager.getPlayerParticipants(world)
                .forEach(player -> {
                    GameHelper.resetPlayerData(player);
                    GameHelper.moveToLobby(player);
                });
    }

    @Override
    public void openMenu(EntityPlayerMP player) {
        PacketHandler.sendToClient(new S2C_PacketLoadoutSelect(loadoutManager.getAvailableLoadouts()), player);
    }

    @Override
    public boolean playerLeaveGame(EntityPlayer player) {
        if (participantManager.isParticipant(player)) {
            participantManager.removePlayer(player.getUniqueID());
            properties.delete(player.getUniqueID());
            GameHelper.moveToLobby(player);
            if (started && !player.world.isRemote) {
                createAi((WorldServer) player.world);
            }
            player.inventory.clear();
            return true;
        }
        return false;
    }

    @Override
    public boolean playerJoinGame(EntityPlayer player) {
        if (isJoinable(player)) {
            player.inventory.clear();
            participantManager.registerPlayer(player);
            properties.register(player);
            if (started && !player.world.isRemote) {
                respawnPlayer(player);
                SpawnerPoint point = spawnerSelector.getPoint(player.world, participantManager.getLoadedParticipants((WorldServer) player.world));
                BlockPos pos = point.getPointPosition();
                GameHelper.teleport(player, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
                UUID removedAi = participantManager.removeSingleAi((WorldServer) player.world);
                properties.delete(removedAi);
                GameHelper.requestClientGameDataSynchronization(player.world);
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

    @Override
    public GenerationType.Context getGeneratorContext() {
        return GenerationType.empty();
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public AbstractDamagingPlayzone getPlayzone() {
        return playzone;
    }

    public PlayerPropertyHolder getProperties() {
        return properties;
    }

    public DeathMessageContainer getDeathMessageHolder() {
        return deathMessages;
    }

    private void createAi(WorldServer world) {
        EntityAIPlayer aiPlayer = new EntityAIPlayer(world);
        List<Entity> entities = participantManager.getLoadedParticipants(world);
        SpawnerPoint point = spawnerSelector.getPoint(world, entities);
        BlockPos pos = point.getPointPosition();
        aiPlayer.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
        aiPlayer.assignGameId(gameId);
        aiPlayer.forceSpawn = true;
        participantManager.registerAi(aiPlayer);
        properties.register(aiPlayer);
        world.spawnEntity(aiPlayer);
        List<EntityLoadout> available = getAvailableLoadouts();
        EntityLoadout loadout = PUBGMCUtil.randomListElement(available, world.rand);
        if (loadout != null) {
            UUID uuid = aiPlayer.getUniqueID();
            loadoutManager.select(uuid, loadout);
            loadoutManager.applyLoadout(aiPlayer);
        }
    }

    public static void onEntityKilled(EntityLivingBase victim, DamageSource source) {
        Entity killSrc = source.getTrueSource();
        if (!(killSrc instanceof EntityLivingBase))
            return;
        GameDataProvider.getGameData(victim.world).ifPresent(data -> {
            if (data.getCurrentGame().getGameType() == GameTypes.FFA) {
                EntityLivingBase killer = (EntityLivingBase) killSrc;
                FFAGame ffaGame = (FFAGame) data.getCurrentGame();
                FFAGameConfiguration configuration = ffaGame.configuration;
                if (configuration.healPerKill > 0) {
                    killer.heal(configuration.healPerKill);
                }
            }
        });
    }

    public static void initAi(EntityAIPlayer player, FFAGame game) {
        player.clearAI();
        EntityAIPlayer.addDefaultTasks(player);
        EntityAIGunAttack shootTask = new EntityAIGunAttack(player);
        shootTask.setIgnoresFriendlies();
        shootTask.setReactionTime(10);
        player.tasks.addTask(1, new EntityAIMoveIntoPlayzone(player, level -> game.playzone, 1.20F));
        player.tasks.addTask(2, shootTask);
        player.tasks.addTask(4, new EntityAIVisitMapPoint<>(player, GameMapPoints.POINT_OF_INTEREST, 1.0));
        player.targetTasks.addTask(0, new EntityAIHurtByTarget(player, false));
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

    @Override
    public SimpleLoadoutManager getLoadoutManager() {
        return loadoutManager;
    }

    private void verifyGameCompletion(World world) {
        int highestKillScore = properties.compareAndGet(SharedProperties.KILLS, 0, Comparator.<Integer>comparingInt(value -> value).reversed());
        if (highestKillScore >= configuration.killTarget || timeRemaining <= 0) {
            completed = true;
            PlayerPropertyHolder.Leaderboard leaderboard = properties.computeLeaderboard(SharedProperties.KILLS, Comparator.<Integer>comparingInt(val -> val).reversed(), 0);
            List<EntityPlayer> playerList = participantManager.getPlayerParticipants(world);
            for (EntityPlayer player : playerList) {
                int index = leaderboard.getParticipantIndex(player.getUniqueID());
                int score = leaderboard.getProperty(player.getUniqueID(), SharedProperties.KILLS, 0);
                if (index >= 0) {
                    ITextComponent component = new TextComponentTranslation(GAME_ENDED_KEY, index + 1, score);
                    component.getStyle().setColor(index == 0 ? TextFormatting.GREEN : TextFormatting.RED);
                    player.sendStatusMessage(component, true);
                    MinecraftForge.EVENT_BUS.post(new GameEvent.PlayerCompleteGame(this, player, index == 0));
                }
            }
        }
    }

    private void respawnAiEntities(WorldServer world) {
        Set<UUID> aiForRespawn = participantManager.getAiForRespawn();
        aiForRespawn.forEach(uuid -> {
            long time = properties.getProperty(uuid, SharedProperties.GAME_TIMESTAMP, 0L);
            if (gametime - time < 60L) {
                return;
            }
            EntityAIPlayer player = new EntityAIPlayer(world);
            NBTTagCompound compound = participantManager.getAiData(uuid);
            if (compound != null) {
                player.readFromNBT(compound);
            }
            List<Entity> entities = participantManager.getLoadedParticipants(world);
            SpawnerPoint point = spawnerSelector.getPoint(world, entities);
            BlockPos pos = point.getPointPosition();
            player.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            player.setUniqueId(uuid);
            player.assignGameId(gameId);
            player.forceSpawn = true;
            loadoutManager.applyLoadout(player);
            participantManager.markRespawned(uuid);
            properties.setProperty(uuid, SharedProperties.GAME_TIMESTAMP, gametime);
            world.spawnEntity(player);
        });
        GameHelper.requestClientGameDataSynchronization(world);
    }

    private void respawnPlayer(EntityPlayer player) {
        World world = player.world;
        if (world.isRemote)
            return;
        WorldServer server = (WorldServer) world;
        List<Entity> participants = participantManager.getLoadedParticipants(server);
        SpawnerPoint point = spawnerSelector.getPoint(world, participants);
        point.teleportOn(player);
        GameHelper.reloadChunks(player);
        properties.setProperty(player.getUniqueID(), SharedProperties.GAME_TIMESTAMP, gametime);
        if (loadoutManager.hasSelectedLoadout(player.getUniqueID())) {
            loadoutManager.applyLoadout(player);
        } else {
            openMenu((EntityPlayerMP) player);
        }
    }

    private boolean isJoinable(EntityPlayer player) {
        if (participantManager.isParticipant(player)) {
            return false;
        }
        int players = participantManager.getPlayerParticipantsCount();
        return players < configuration.entityCount;
    }

    public static final class EventListener implements GameEventListener {

        private final FFAGame game;

        public EventListener(FFAGame game) {
            this.game = game;
        }

        @Override
        public void onEntityDeath(LivingDeathEvent event) {
            if (event.isCanceled())
                return;
            EntityLivingBase entity = event.getEntityLiving();
            DamageSource source = event.getSource();
            Entity killer = source.getTrueSource();
            boolean deathMessage = false;
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                if (game.participantManager.isParticipant(player)) {
                    player.inventory.clear();
                    awardKill(entity, source, killer);
                    deathMessage = true;
                }
            } else if (game.participantManager.isAiParticipant(entity)) {
                awardKill(entity, source, killer);
                game.properties.setProperty(entity.getUniqueID(), SharedProperties.GAME_TIMESTAMP, game.gametime);
                game.participantManager.markAiAsDead(entity.getUniqueID());
                deathMessage = true;
            }
            if (deathMessage && !entity.world.isRemote) {
                DeathMessage message = GameHelper.createDefaultDeathMessage(entity, source);
                game.deathMessages.push(message);
            }
            GameHelper.requestClientGameDataSynchronization(entity.world);
        }

        @Override
        public void onEntityAttack(LivingAttackEvent event) {
            EntityLivingBase entityLivingBase = event.getEntityLiving();
            UUID uuid = entityLivingBase.getUniqueID();
            if (game.completed) {
                event.setCanceled(true);
                return;
            }
            if (game.participantManager.isEntityParticipant(entityLivingBase)) {
                long spawnTimestamp = game.properties.getProperty(uuid, SharedProperties.GAME_TIMESTAMP, 0L);
                long timeDiff = game.gametime - spawnTimestamp;
                if (timeDiff < game.configuration.spawnProtectionTime) {
                    event.setCanceled(true);
                }
            }
        }

        @Override
        public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
            EntityPlayer player = event.player;
            if (game.participantManager.isParticipant(player)) {
                if (game.started) {
                    game.respawnPlayer(player);
                    player.hurtResistantTime = 0;
                    player.hurtTime = 0;
                } else {
                    GameHelper.moveToLobby(player);
                }
            }
        }

        @Override
        public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
            EntityPlayer player = event.player;
            if (player != null && game.isStarted()) {
                game.playerLeaveGame(player);
            }
        }

        @Override
        public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            EntityPlayer player = event.player;
            if (game.isJoinable(player)) {
                player.sendMessage(new TextComponentTranslation("message.pubgmc.game.joinable_game_in_progress"));
            }
        }

        private void awardKill(EntityLivingBase victim, DamageSource source, @Nullable Entity killer) {
            if (killer == null || game.completed || !game.started)
                return;
            if (game.participantManager.isEntityParticipant(killer)) {
                game.properties.increaseInt(killer.getUniqueID(), SharedProperties.KILLS);
                if (!killer.world.isRemote) {
                    game.verifyGameCompletion(killer.world);
                    GameHelper.requestClientGameDataSynchronization(killer.world);
                }
                GameMutatorHelper.giveKillReward(victim, source);
            }
        }
    }

    public static final class Serializer implements GameDataSerializer<FFAGameConfiguration, FFAGame> {

        @Override
        public NBTTagCompound serializeGameData(FFAGame game) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setUniqueId("gameId", game.gameId);
            nbt.setInteger("timeRemaining", game.timeRemaining);
            nbt.setInteger("completionTimer", game.completionTimer);
            nbt.setLong("gameTime", game.gametime);
            nbt.setBoolean("started", game.started);
            nbt.setBoolean("completed", game.completed);
            nbt.setTag("gamerules", game.gameRuleStorage.serialize());
            nbt.setTag("participants", game.participantManager.serialize());
            nbt.setTag("props", game.properties.serialize());
            if (game.playzone != null) {
                nbt.setTag("playzone", PlayzoneType.serialize(game.playzone));
            }
            nbt.setTag("loadouts", game.loadoutManager.serialize());
            nbt.setTag("deathMessages", game.deathMessages.serialize());
            return nbt;
        }

        @Override
        public FFAGame deserializeGameData(NBTTagCompound nbt, FFAGameConfiguration configuration, World world) {
            UUID gameId = nbt.getUniqueId("gameId");
            FFAGame ffaGame = new FFAGame(gameId, configuration);
            ffaGame.timeRemaining = nbt.getInteger("timeRemaining");
            ffaGame.completionTimer = nbt.getInteger("completionTimer");
            ffaGame.gametime = nbt.getLong("gameTime");
            ffaGame.started = nbt.getBoolean("started");
            ffaGame.completed = nbt.getBoolean("completed");
            ffaGame.gameRuleStorage.deserialize(nbt.getCompoundTag("gamerules"));
            ffaGame.participantManager.deserialize(nbt.getCompoundTag("participants"));
            ffaGame.properties.deserialize(nbt.getCompoundTag("props"));
            if (nbt.hasKey("playzone")) {
                ffaGame.playzone = PlayzoneType.deserialize(nbt.getCompoundTag("playzone"));
            }
            ffaGame.loadoutManager.deserialize(nbt.getCompoundTag("loadouts"));
            ffaGame.deathMessages.deserialize(nbt.getCompoundTag("deathMessages"));
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

    static {
        Style style = LOADOUT_MESSAGE.getStyle();
        style.setColor(TextFormatting.YELLOW);
        style.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/game menu"));
    }
}
