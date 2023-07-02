package dev.toma.pubgmc.common.games.game.battleroyale;

import com.google.gson.JsonObject;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.GameDataSerializer;
import dev.toma.pubgmc.api.game.GameEventListener;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import dev.toma.pubgmc.api.game.map.GameLobby;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;
import dev.toma.pubgmc.api.game.team.*;
import dev.toma.pubgmc.api.game.util.DeathMessage;
import dev.toma.pubgmc.api.game.util.DeathMessageContainer;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.api.util.GameRuleStorage;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.entity.EntityPlane;
import dev.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import dev.toma.pubgmc.common.entity.bot.ai.*;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.playzone.AbstractDamagingPlayzone;
import dev.toma.pubgmc.common.games.playzone.DynamicPlayzone;
import dev.toma.pubgmc.common.games.playzone.StaticPlayzone;
import dev.toma.pubgmc.common.games.util.TeamAIManager;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BattleRoyaleGame implements TeamGame<BattleRoyaleGameConfiguration> {

    public static final AbstractDamagingPlayzone.DamageOptions OUT_OF_BOUNDS_DAMAGE = new AbstractDamagingPlayzone.DamageOptions(5.0F, 20);
    public static final String PLAYER_INITIAL_LOOT_PATH = "battleroyale/player_start_gear";
    public static final String AI_INITIAL_LOOT_PATH = "battleroyale/initial_loot";
    public static final String AI_EARLY_GAME_LOOT_PATH = "battleroyale/early_game_loot";
    public static final String AI_MID_GAME_LOOT_PATH = "battleroyale/mid_game_loot";
    public static final String AI_LATE_GAME_LOOT_PATH = "battleroyale/late_game_loot";

    private final UUID gameId;
    private final BattleRoyaleGameConfiguration configuration;
    private final TeamManager teamManager;
    private final TeamInviteManager inviteManager;
    private final TeamAIManager aiManager;
    private final GameRuleStorage ruleStorage;
    private final DeathMessageContainer deathMessages;
    private final List<GameEventListener> listeners;
    private boolean started;
    private boolean completed;
    private int completedTimer;
    private StaticPlayzone mapPlayzone;
    private DynamicPlayzone playzone;
    private int firstShrinkDelay;
    private boolean playzoneReady;
    private int phase;

    public BattleRoyaleGame(UUID gameId, BattleRoyaleGameConfiguration configuration) {
        this.gameId = gameId;
        this.configuration = configuration;
        this.teamManager = new SizeLimitedTeamManager(configuration.teamSize);
        this.inviteManager = new SimpleTeamInviteManager(teamManager);
        this.ruleStorage = new GameRuleStorage();
        this.aiManager = new TeamAIManager(teamManager);
        this.deathMessages = new DeathMessageContainer(5, 100);
        this.listeners = new ArrayList<>();
        this.firstShrinkDelay = configuration.playzoneGenerationDelay;
        this.completedTimer = 200;

        this.addListener(new EventListener(this));
    }

    @Override
    public UUID getGameId() {
        return gameId;
    }

    @Override
    public GameType<BattleRoyaleGameConfiguration, ?> getGameType() {
        return GameTypes.BATTLE_ROYALE;
    }

    @Override
    public BattleRoyaleGameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public void performGameMapValidations(World world, GameMap map) {
        mapPlayzone = new StaticPlayzone(map.bounds());
        mapPlayzone.setDamageOptions(OUT_OF_BOUNDS_DAMAGE);
        playzone = new DynamicPlayzone(map.bounds());
        playzone.onResizeCompleted(this::playzoneResizeCompleted);
    }

    @Override
    public void onGameInit(World world) {
        List<EntityPlayer> playerList = world.playerEntities;
        if (playerList.size() <= EntityPlane.PLANE_CAPACITY) {
            GameDataProvider.getGameData(world)
                    .map(GameData::getGameLobby)
                    .ifPresent(lobby -> {
                        Team team = null;
                        for (EntityPlayer player : playerList) {
                            lobby.teleport(player);
                            if (!configuration.automaticGameJoining || team == null || team.getSize() >= configuration.teamSize) {
                                team = teamManager.createNewTeam(player);
                                continue;
                            }
                            teamManager.join(team, player);
                        }
                    });
        }
    }

    @Override
    public void onGameStart(World world) {
        started = true;
        int playerCount = (int) teamManager.getAllActivePlayers(world).count();
        int aiCount = configuration.allowAi ? configuration.entityCount - playerCount : 0;
        aiManager.setAllowedAiSpawnCount(aiCount);
        GameHelper.updateLoadedGameObjects(world);
        if (!world.isRemote) {
            WorldServer worldServer = (WorldServer) world;
            GameHelper.clearEmptyTeams((WorldServer) world, teamManager);
            EntityPlane plane = GameHelper.initializePlaneWithPath(gameId, world, mapPlayzone, 1200);
            plane.setMovementSpeedMultiplier(configuration.planeSpeed);
            plane.setFlightHeight(configuration.planeFlightHeight);
            GameHelper.spawnPlaneWithPlayers(plane, teamManager, world, player -> {
                GameHelper.resetPlayerData(player);
                LoadoutManager.apply(player, PLAYER_INITIAL_LOOT_PATH);
                player.setGameType(net.minecraft.world.GameType.ADVENTURE);
            });
            configuration.worldConfiguration.apply(worldServer, ruleStorage);
            ruleStorage.storeValueAndSet(world, "naturalRegeneration", "false");
            ruleStorage.storeValueAndSet(world, "doMobSpawning", "false");
            ruleStorage.storeValueAndSet(world, "doMobLoot", "false");
            ruleStorage.storeValueAndSet(world, "showDeathMessages", "false");
            GameHelper.requestClientGameDataSynchronization(world);
        }
    }

    @Override
    public void onGameTick(World world) {
        if (completed) {
            if (--completedTimer < 0) {
                GameHelper.stopGame(world);
            }
            return;
        }
        if (world.getTotalWorldTime() % 200L == 0) {
            teamManager.getAllActivePlayers(world).forEach(GameHelper::fillPlayerHunger);
        }
        if (!playzoneReady && --firstShrinkDelay <= 0) {
            playzoneReady = true;
            playzoneResizeCompleted(playzone, world);
            BattleRoyaleGameConfiguration.ZonePhaseConfiguration[] configurations = configuration.zonePhases;
            if (configurations != null && configurations.length > 0) {
                playzone.setDamageOptions(configurations[0].getDamageOptions());
            } else {
                playzone.setDamageOptions(OUT_OF_BOUNDS_DAMAGE);
            }
        }
        if (!world.isRemote) {
            // server only tick
            WorldServer worldServer = (WorldServer) world;
            List<Entity> activeEntities = teamManager.getAllActiveEntities(worldServer).collect(Collectors.toList());
            mapPlayzone.hurtAllOutside(worldServer, activeEntities);
            playzone.hurtAllOutside(worldServer, activeEntities);
            tickAIEntities(worldServer);
            if (world.getTotalWorldTime() % 20 == 0) {
                int teamCount = teamManager.getTeams().size();
                int playerCount = (int) teamManager.getAllActivePlayers(world).count();
                int aiCount = aiManager.getRemainingAliveEntityCount();
                if (playerCount == 0 || (teamCount == 1 && aiCount <= 0)) {
                    completed = true;
                    GameHelper.requestClientGameDataSynchronization(world);
                    teamManager.getAllActivePlayers(world).forEach(player -> {
                        EntityPlayerMP playerMP = (EntityPlayerMP) player;
                        playerMP.connection.sendPacket(new SPacketTitle(SPacketTitle.Type.TITLE, TextComponentHelper.GAME_WON, 20, 120, 60));
                    });
                    return;
                }
            }
        }
        deathMessages.tick();
        playzone.tickPlayzone(world);
    }

    @Override
    public void onGameStopped(World world, GameData data) {
        started = false;
        ruleStorage.restoreGameRules(world);

        GameLobby lobby = data.getGameLobby();
        if (lobby != null) {
            teamManager.getAllActivePlayers(world).forEach(player -> {
                lobby.teleport(player);
                GameHelper.resetPlayerData(player);
            });
        }
    }

    @Override
    public boolean playerLeaveGame(EntityPlayer player) {
        Team team = teamManager.getEntityTeam(player);
        if (team != null && team.isMember(player.getUniqueID())) {
            teamManager.eliminate(player);
            return true;
        }
        return false;
    }

    @Override
    public boolean playerJoinGame(EntityPlayer player) {
        if (!started && teamManager.getAllActivePlayers(player.world).count() < EntityPlane.PLANE_CAPACITY) {
            teamManager.createNewTeam(player);
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
    public TeamManager getTeamManager() {
        return teamManager;
    }

    @Override
    public TeamInviteManager getInviteManager() {
        return inviteManager;
    }

    public DynamicPlayzone getPlayzone() {
        return playzone;
    }

    public DeathMessageContainer getDeathMessageContainer() {
        return deathMessages;
    }

    public boolean isZoneShrinking() {
        return playzoneReady && playzone.isResizing();
    }

    public int getRemainingTimeBeforeShrinking() {
        return playzoneReady ? playzone.getRemainingStationaryTime() : -1;
    }

    public int getAlivePlayerCount(World world) {
        int players = (int) teamManager.getAllActivePlayers(world).count();
        int ai = aiManager.getRemainingAliveEntityCount();
        return players + ai;
    }

    private void playzoneResizeCompleted(DynamicPlayzone playzone, World world) {
        BattleRoyaleGameConfiguration.ZonePhaseConfiguration[] configurations = configuration.zonePhases;
        // airdrop
        if (!world.isRemote && phase > 0) {
            Playzone airdropPlayzone = this.playzone.getResultingPlayzone();
            List<EntityPlayer> playerList = teamManager.getAllActivePlayers(world).collect(Collectors.toList());
            Position2 pos = GameHelper.findLoadedPositionWithinPlayzone(airdropPlayzone, world, playerList, 0, 128);
            if (pos != null) {
                int x = (int) pos.getX();
                int z = (int) pos.getZ();
                int y = world.getHeight(x, z) + 80;
                PUBGMCUtil.spawnAirdrop(world, new BlockPos(x, y, z), false);
            }
        }
        // Zone resize
        if (configurations != null && configurations.length > phase) {
            DynamicPlayzone.ResizeTarget target = configurations[phase++].createNewShrinkTarget(playzone, Pubgmc.rng());
            playzone.setTarget(target);
        }
        GameHelper.requestClientGameDataSynchronization(world);
    }

    private void tickAIEntities(WorldServer world) {
        long total = world.getTotalWorldTime();
        if (total % 20L == 0L) {
            aiManager.checkForUnloadedEntities(world);
        }
        int mod = configuration.aiSpawnInterval * configuration.teamSize;
        if (total % mod == 0L) {
            if (aiManager.canSpawnEntity()) {
                Playzone shrunkPlayzone = playzone.getResultingPlayzone();
                List<EntityPlayer> playerList = teamManager.getAllActivePlayers(world).collect(Collectors.toList());
                int memberCount = Math.min(configuration.teamSize, aiManager.getRemainingAliveEntityCount()) - 1;
                Pubgmc.logger.debug("Attempting to spawn AI with default rules");
                Position2 spawnPosition = GameHelper.findLoadedPositionWithinPlayzone(shrunkPlayzone, world, playerList, 32, 96);
                if (spawnPosition == null) {
                    Pubgmc.logger.debug("Attempting to spawn AI while ignoring player range");
                    spawnPosition = GameHelper.findLoadedPositionWithinPlayzone(shrunkPlayzone, world, playerList, 0, 96, false);
                }
                if (spawnPosition == null) {
                    Pubgmc.logger.debug("Attempting to spawn AI while ignoring player range and playzone");
                    spawnPosition = GameHelper.findLoadedPositionWithinPlayzone(shrunkPlayzone, world, playerList, 0, 96, true);
                }
                if (spawnPosition != null) {
                    EntityAIPlayer leader = initAi(world, spawnPosition);
                    Team team = teamManager.createNewTeam(leader);
                    world.spawnEntity(leader);
                    Pubgmc.logger.debug("AI spawned: {}", leader);
                    aiManager.entitySpawned(leader);
                    if (memberCount > 0) {
                        for (int i = 0; i < memberCount; i++) {
                            EntityAIPlayer member = initAi(world, spawnPosition.around(world.rand, 6.0));
                            teamManager.join(team, member);
                            world.spawnEntity(member);
                            Pubgmc.logger.debug("AI spawned: {}", member);
                            aiManager.entitySpawned(member);
                        }
                    }
                }
                GameHelper.requestClientGameDataSynchronization(world);
            }
        }
    }

    private EntityAIPlayer initAi(World world, Position2 spawnPos) {
        EntityAIPlayer player = new EntityAIPlayer(world);
        int height = world.getHeight((int) spawnPos.getX(), (int) spawnPos.getZ());
        player.setPosition(spawnPos.getX(), height + 1, spawnPos.getZ());
        LoadoutManager.apply(player, getAiLoadoutType());
        addAiTasks(player);
        player.assignGameId(gameId);
        return player;
    }

    private void addAiTasks(EntityAIPlayer player) {
        player.clearAI();
        EntityAIPlayer.addDefaultTasks(player);
        player.tasks.addTask(0, new EntityAIMoveIntoPlayzone(player, level -> playzone, 1.25F));
        player.tasks.addTask(1, new EntityAIGunAttack(player));
        player.tasks.addTask(2, new EntityAIMoveToTeamLeader(player, 32));
        player.tasks.addTask(3, new EntityAISearchLoot(player, 5));
        player.tasks.addTask(5, new EntityAIMoveIntoPlayzone(player, level -> playzone.getResultingPlayzone()));
        player.targetTasks.addTask(0, new EntityAICallTeamForHelp(player));
        player.targetTasks.addTask(1, new EntityAITeamAwareNearestAttackableTarget<>(player, EntityPlayer.class, true));
        player.targetTasks.addTask(2, new EntityAITeamAwareNearestAttackableTarget<>(player, EntityAIPlayer.class, true));
    }

    private String getAiLoadoutType() {
        if (!playzoneReady) {
            return AI_INITIAL_LOOT_PATH;
        }
        float progression = phase / Math.max((float) configuration.zonePhases.length, 1.0F);
        if (progression >= 0.40F) {
            return AI_LATE_GAME_LOOT_PATH;
        } else if (progression > 0.15F) {
            return AI_MID_GAME_LOOT_PATH;
        } else {
            return AI_EARLY_GAME_LOOT_PATH;
        }
    }

    private static final class EventListener implements GameEventListener {

        private final BattleRoyaleGame game;

        public EventListener(BattleRoyaleGame game) {
            this.game = game;
        }

        @Override
        public void onPlayerLoggedIn(EntityPlayerMP player) {
            boolean joined = false;
            if (game.configuration.automaticGameJoining && !game.started) {
                if (game.playerJoinGame(player)) {
                    joined = true;
                    GameHelper.requestClientGameDataSynchronization(player.world);
                }
            }
            if (joined || game.mapPlayzone.isWithin(player)) {
                GameHelper.moveToLobby(player);
            }
        }

        @Override
        public void onPlayerLoggedOut(EntityPlayerMP player) {
            if (player != null && game.started) {
                game.teamManager.eliminate(player);
                GameHelper.requestClientGameDataSynchronization(player.world);
            }
        }

        @Override
        public void onEntityDeath(EntityLivingBase entity, DamageSource source) {
            World world = entity.world;
            if (world.isRemote)
                return;
            Team team = game.teamManager.getEntityTeam(entity);
            if (team == null || !team.isMember(entity.getUniqueID())) {
                return;
            }
            game.teamManager.eliminate(entity);
            DeathMessage deathMessage = GameHelper.createDefaultDeathMessage(entity, source);
            game.deathMessages.push(deathMessage);
            if (entity instanceof EntityPlayer) {
                GameHelper.spawnPlayerDeathCrate(game.gameId, (EntityPlayer) entity);
            } else if (entity instanceof EntityAIPlayer) {
                EntityAIPlayer aiPlayer = (EntityAIPlayer) entity;
                GameHelper.spawnAiPlayerDeathCrate(game.gameId, aiPlayer);
                game.aiManager.onAiEntityDied(aiPlayer);
            }
            GameHelper.requestClientGameDataSynchronization(world);
        }

        @Override
        public void onPlayerRespawn(EntityPlayer player) {
            GameHelper.moveToLobby(player);
            GameHelper.requestClientGameDataSynchronization(player.world);
        }

        @Override
        public boolean onEntitySpawnInWorld(Entity entity, World world) {
            if (entity instanceof EntityAIPlayer) {
                game.addAiTasks((EntityAIPlayer) entity);
            }
            return true;
        }
    }

    public static final class Serializer implements GameDataSerializer<BattleRoyaleGameConfiguration, BattleRoyaleGame> {

        @Override
        public NBTTagCompound serializeGameData(BattleRoyaleGame game) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setUniqueId("gameId", game.gameId);
            nbt.setTag("teams", game.teamManager.serializeNBT());
            nbt.setTag("invites", game.inviteManager.serializeNBT());
            nbt.setTag("rules", game.ruleStorage.serialize());
            nbt.setTag("deathMessages", game.deathMessages.serialize());
            nbt.setTag("aiManager", game.aiManager.serialize());
            nbt.setBoolean("started", game.started);
            nbt.setBoolean("completed", game.completed);
            nbt.setInteger("completedTimer", game.completedTimer);
            if (game.mapPlayzone != null) {
                nbt.setTag("mapPlayzone", PlayzoneType.serialize(game.mapPlayzone));
            }
            if (game.playzone != null) {
                nbt.setTag("playzone", PlayzoneType.serialize(game.playzone));
            }
            nbt.setInteger("playzoneStart", game.firstShrinkDelay);
            nbt.setBoolean("playzoneReady", game.playzoneReady);
            nbt.setInteger("phase", game.phase);
            return nbt;
        }

        @Override
        public BattleRoyaleGame deserializeGameData(NBTTagCompound nbt, BattleRoyaleGameConfiguration configuration) {
            UUID gameId = nbt.getUniqueId("gameId");
            BattleRoyaleGame game = new BattleRoyaleGame(gameId, configuration);
            game.teamManager.deserializeNBT(nbt.getCompoundTag("teams"));
            game.inviteManager.deserializeNBT(nbt.getCompoundTag("invites"));
            game.ruleStorage.deserialize(nbt.getCompoundTag("rules"));
            game.deathMessages.deserialize(nbt.getCompoundTag("deathMessages"));
            game.aiManager.deserialize(nbt.getCompoundTag("aiManager"));
            game.started = nbt.getBoolean("started");
            game.completed = nbt.getBoolean("completed");
            game.completedTimer = nbt.getInteger("completedTimer");
            if (nbt.hasKey("mapPlayzone", Constants.NBT.TAG_COMPOUND)) {
                game.mapPlayzone = PlayzoneType.deserialize(nbt.getCompoundTag("mapPlayzone"));
            }
            if (nbt.hasKey("playzone", Constants.NBT.TAG_COMPOUND)) {
                game.playzone = PlayzoneType.deserialize(nbt.getCompoundTag("playzone"));
                game.playzone.onResizeCompleted(game::playzoneResizeCompleted);
            }
            game.firstShrinkDelay = nbt.getInteger("playzoneStart");
            game.playzoneReady = nbt.getBoolean("playzoneReady");
            game.phase = nbt.getInteger("phase");
            return game;
        }

        @Override
        public NBTTagCompound serializeGameConfiguration(BattleRoyaleGameConfiguration configuration) {
            return configuration.serialize();
        }

        @Override
        public BattleRoyaleGameConfiguration deserializeGameConfiguration(NBTTagCompound nbt) {
            return BattleRoyaleGameConfiguration.deserialize(nbt);
        }

        @Override
        public JsonObject serializeConfigurationToJson(BattleRoyaleGameConfiguration configuration) {
            return configuration.jsonSerialize();
        }

        @Override
        public BattleRoyaleGameConfiguration deserializeConfigurationFromJson(JsonObject object) {
            return BattleRoyaleGameConfiguration.jsonDeserialize(object);
        }
    }
}
