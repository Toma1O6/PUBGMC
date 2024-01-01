package dev.toma.pubgmc.common.games.game.battleroyale;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import dev.toma.pubgmc.api.event.GameEvent;
import dev.toma.pubgmc.api.event.ParachuteEvent;
import dev.toma.pubgmc.api.game.GameDataSerializer;
import dev.toma.pubgmc.api.game.GameEventListener;
import dev.toma.pubgmc.api.game.GameType;
import dev.toma.pubgmc.api.game.GenerationType;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import dev.toma.pubgmc.api.game.map.GameLobby;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.mutator.GameMutatorHelper;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;
import dev.toma.pubgmc.api.game.team.*;
import dev.toma.pubgmc.api.game.util.*;
import dev.toma.pubgmc.api.properties.SharedProperties;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.ai.*;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.entity.EntityPlane;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BattleRoyaleGame implements TeamGame<BattleRoyaleGameConfiguration> {

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
    private final PlayerPropertyHolder playerProperties;
    private final List<GameEventListener> listeners;
    private boolean started;
    private boolean completed;
    private int completedTimer;
    private StaticPlayzone mapPlayzone;
    private DynamicPlayzone playzone;
    private int firstShrinkDelay;
    private long gameTime;
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
        this.playerProperties = new PlayerPropertyHolder();
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
    public void validateAndSetupForMap(World world, GameMap map) {
        AbstractDamagingPlayzone damagingPlayzone = map.bounds();
        mapPlayzone = new StaticPlayzone(damagingPlayzone);
        mapPlayzone.setDamageOptions(AbstractDamagingPlayzone.DamageOptions.BOUNDS);
        playzone = new DynamicPlayzone(damagingPlayzone);
        playzone.onResizeCompleted(this::playzoneResizeCompleted);
    }

    @Override
    public void onGameInit(World world) {
        playerProperties.registerProperty(SharedProperties.KILLS, 0);
        List<EntityPlayer> playerList = world.playerEntities;
        GameDataProvider.getGameData(world)
                .map(GameData::getGameLobby)
                .ifPresent(lobby -> {
                    Team team = null;
                    for (EntityPlayer player : playerList) {
                        lobby.teleport(player);
                        if (!configuration.automaticGameJoining || team == null || team.getSize() >= configuration.teamSize) {
                            team = teamManager.createNewTeam(player);
                            playerProperties.register(player);
                            continue;
                        }
                        teamManager.join(team, player);
                        playerProperties.register(player);
                    }
                });
    }

    @Override
    public void onGameStart(World world) {
        started = true;
        int playerCount = (int) teamManager.getAllActivePlayers(world).count();
        int aiCount = configuration.allowAi ? configuration.entityCount - playerCount : 0;
        aiManager.setAllowedAiSpawnCount(aiCount);
        GameHelper.updateLoadedGameObjects(world, getGeneratorContext());
        if (!world.isRemote) {
            WorldServer worldServer = (WorldServer) world;
            GameHelper.clearEmptyTeams((WorldServer) world, teamManager);
            Supplier<EntityPlane> planeProvider = () -> {
                EntityPlane plane = GameHelper.initializePlaneWithPath(gameId, world, mapPlayzone, 1200);
                plane.setMovementSpeedMultiplier(configuration.planeSpeed);
                plane.setFlightHeight(configuration.planeFlightHeight);
                return plane;
            };
            GameHelper.spawnPlanesWithPlayers(teamManager, world, player -> {
                GameHelper.resetPlayerData(player);
                LoadoutManager.apply(player, PLAYER_INITIAL_LOOT_PATH);
                player.setGameType(net.minecraft.world.GameType.ADVENTURE);
            }, planeProvider);
            configuration.worldConfiguration.apply(worldServer, ruleStorage);
            ruleStorage.storeValueAndSet(world, GameRuleStorage.NATURAL_REGENERATION, GameRuleStorage.FALSE);
            ruleStorage.storeValueAndSet(world, GameRuleStorage.MOB_SPAWNING, GameRuleStorage.FALSE);
            ruleStorage.storeValueAndSet(world, GameRuleStorage.MOB_LOOT, GameRuleStorage.FALSE);
            ruleStorage.storeValueAndSet(world, GameRuleStorage.SHOW_DEATH_MESSAGES, GameRuleStorage.FALSE);
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
                playzone.setDamageOptions(AbstractDamagingPlayzone.DamageOptions.BOUNDS);
            }
        }
        if (!world.isRemote) {
            // server only tick
            WorldServer worldServer = (WorldServer) world;
            mapPlayzone.hurtAllOutside(worldServer, () -> teamManager.getAllActiveEntities(worldServer).collect(Collectors.toList()));
            playzone.hurtAllOutside(worldServer, () -> teamManager.getAllActiveEntities(worldServer).collect(Collectors.toList()));
            tickAIEntities(worldServer);
            if (world.getTotalWorldTime() % 20 == 0) {
                int teamCount = teamManager.getTeams().size();
                int playerCount = (int) teamManager.getAllActivePlayers(world).count();
                int aiCount = aiManager.getRemainingAliveEntityCount();
                int aiToSpawn = aiManager.getAiEntitiesToSpawn();
                if (isGameCompleted(playerCount, aiCount, aiToSpawn, teamCount)) {
                    completed = true;
                    GameHelper.requestClientGameDataSynchronization(world);
                    teamManager.getAllActivePlayers(world).forEach(player -> {
                        EntityPlayerMP playerMP = (EntityPlayerMP) player;
                        playerMP.connection.sendPacket(new SPacketTitle(SPacketTitle.Type.TITLE, TextComponentHelper.GAME_WON, 20, 120, 60));
                        MinecraftForge.EVENT_BUS.post(new GameEvent.PlayerCompleteGame(this, playerMP, true));
                    });
                    return;
                }
            }
        }
        deathMessages.tick();
        playzone.tickPlayzone(world);
        ++gameTime;
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
        if (started && team != null && team.isMember(player.getUniqueID())) {
            teamManager.eliminate(player);
            return true;
        }
        return false;
    }

    @Override
    public boolean playerJoinGame(EntityPlayer player) {
        if (!started && teamManager.getAllActivePlayers(player.world).count() < EntityPlane.PLANE_CAPACITY) {
            teamManager.createNewTeam(player);
            playerProperties.register(player);
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

    @Override
    public GenerationType.Context getGeneratorContext() {
        return GenerationType.create(GenerationType.ITEMS, GenerationType.ENTITIES);
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

    private boolean isGameCompleted(int players, int ai, int aiForSpawn, int teams) {
        if (players > 0 && (teams == 1 || ai <= 0 && aiForSpawn == 0)) {
            if (ai > 0) {
                Team team = new ArrayList<>(teamManager.getTeams()).get(0);
                int activeMembers = team.getActiveMemberCount();
                return activeMembers == players + ai;
            }
            return true;
        }
        return players <= 0;
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
        if (gameTime % 20L == 0L) {
            aiManager.checkForUnloadedEntities(world);
        }
        int mod = configuration.aiSpawnInterval * configuration.teamSize;
        if (gameTime >= configuration.initialAiSpawnDelay && gameTime % mod == 0L) {
            if (aiManager.canSpawnEntity()) {
                Playzone shrunkPlayzone = playzone.getResultingPlayzone();
                List<EntityPlayer> playerList = teamManager.getAllActivePlayers(world).collect(Collectors.toList());
                int memberCount = Math.min(configuration.teamSize, aiManager.getAiEntitiesToSpawn()) - 1;
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
        player.assignGameId(gameId);
        return player;
    }

    public static void addAiTasks(EntityAIPlayer player, BattleRoyaleGame game) {
        player.clearAI();
        EntityAIPlayer.addDefaultTasks(player);
        player.tasks.addTask(0, new EntityAIRideWithTeamLeader(player, 1.1F));
        player.tasks.addTask(1, new EntityAIMoveIntoPlayzone(player, level -> game.playzone, 1.20F));
        player.tasks.addTask(2, new EntityAIGunAttack(player));
        player.tasks.addTask(3, new EntityAISearchLoot(player, () -> game.playzone, 5, 1.10F));
        player.tasks.addTask(4, new EntityAIMoveToTeamLeader(player, 32, 1.20F));
        player.tasks.addTask(5, new EntityAIHeal<>(player, game::shouldHeal, EntityAIPlayer::getInventory));
        player.tasks.addTask(6, new EntityAIMoveIntoPlayzone(player, level -> game.playzone.getResultingPlayzone()));
        player.tasks.addTask(7, new EntityAIVisitMapPoint<>(player, GameMapPoints.POINT_OF_INTEREST, 1.0));
        player.targetTasks.addTask(0, new EntityAICallTeamForHelp(player));
        player.targetTasks.addTask(1, new EntityAITeamAwareNearestAttackableTarget<>(player, EntityPlayer.class, true));
        player.targetTasks.addTask(1, new EntityAITeamAwareNearestAttackableTarget<>(player, EntityAIPlayer.class, true));
    }

    private boolean shouldHeal(EntityAIPlayer player) {
        boolean withinPlayzone = playzone.isWithin(player);
        float health = player.getHealth();
        if (withinPlayzone) {
            return health < 12.0F;
        }
        if (health > 10)
            return false;
        BlockPos nearestPosition = playzone.findNearestPositionWithin(player.getPositionVector(), player.world);
        double distance = player.getDistanceSq(nearestPosition);
        return distance > 256;
    }

    private String getAiLoadoutType() {
        if (!playzoneReady) {
            return AI_INITIAL_LOOT_PATH;
        }
        float progression = phase / Math.max((float) configuration.zonePhases.length, 1.0F);
        if (progression >= 0.45F) {
            return AI_LATE_GAME_LOOT_PATH;
        } else if (progression > 0.25F) {
            return AI_MID_GAME_LOOT_PATH;
        } else {
            return AI_EARLY_GAME_LOOT_PATH;
        }
    }

    private boolean hasAiCompanions() {
        return configuration.allowAi && configuration.allowAiCompanions;
    }

    private static final class EventListener implements GameEventListener {

        private final BattleRoyaleGame game;

        public EventListener(BattleRoyaleGame game) {
            this.game = game;
        }

        @Override
        public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
            boolean joined = false;
            EntityPlayer player = event.player;
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
        public void onPlayerLoggedOut(PlayerLoggedOutEvent event) {
            EntityPlayer player = event.player;
            if (player != null && game.started) {
                game.teamManager.eliminate(player);
                GameHelper.moveToLobby(player);
                GameHelper.requestClientGameDataSynchronization(player.world);
            }
        }

        @Override
        public void onEntityDeath(LivingDeathEvent event) {
            if (event.isCanceled())
                return;
            EntityLivingBase entity = event.getEntityLiving();
            DamageSource source = event.getSource();
            World world = entity.world;
            if (world.isRemote)
                return;
            Team team = game.teamManager.getEntityTeam(entity);
            if (team == null || !team.isMember(entity.getUniqueID())) {
                return;
            }
            game.teamManager.eliminate(entity);
            if (!team.isTeamEliminated() && team.isTeamLeader(entity.getUniqueID())) {
                Optional<Team.Member> nextTL = team.getAliveTeamMember();
                nextTL.ifPresent(team::setTeamLeader);
            }
            DeathMessage deathMessage = GameHelper.createDefaultDeathMessage(entity, source);
            game.deathMessages.push(deathMessage);
            if (entity instanceof EntityPlayer) {
                GameHelper.spawnPlayerDeathCrate(game.gameId, (EntityPlayer) entity);
                MinecraftForge.EVENT_BUS.post(new GameEvent.PlayerCompleteGame(game, (EntityPlayer) entity, false));
            } else if (entity instanceof EntityAIPlayer) {
                EntityAIPlayer aiPlayer = (EntityAIPlayer) entity;
                GameHelper.spawnAiPlayerDeathCrate(game.gameId, aiPlayer);
                game.aiManager.onAiEntityDied(aiPlayer.getUniqueID());
            }
            GameMutatorHelper.giveKillReward(entity, source);
            Entity killer = source.getTrueSource();
            if (killer instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) killer;
                TeamRelations relations = GameHelper.getEntityRelations(player, entity);
                if (relations != TeamRelations.FRIENDLY) {
                    int kills = game.playerProperties.increaseInt(player.getUniqueID(), SharedProperties.KILLS);
                    String message = kills == 1 ? "label.pubgmc.game.battleroyale.kill.single" : "label.pubgmc.game.battleroyale.kill.multiple";
                    Vec3d damagePos = source.getDamageLocation();
                    ITextComponent component;
                    if (damagePos != null) {
                        message += ".distance";
                        double distance = PUBGMCUtil.getDistance(damagePos.x, damagePos.y, damagePos.z, entity.posX, entity.posY, entity.posZ);
                        String distanceText = String.format("%.2f", distance);
                        component = new TextComponentTranslation(message, kills, distanceText);
                    } else {
                        component = new TextComponentTranslation(message, kills);
                    }
                    Style style = component.getStyle();
                    style.setBold(true);
                    style.setColor(TextFormatting.RED);
                    player.sendStatusMessage(component, true);
                }
            }
            GameHelper.requestClientGameDataSynchronization(world);
        }

        @Override
        public void onPlayerRespawn(PlayerRespawnEvent event) {
            EntityPlayer player = event.player;
            GameHelper.moveToLobby(player);
            GameHelper.requestClientGameDataSynchronization(player.world);
        }

        @Override
        public void onEntityWithParachuteLanded(ParachuteEvent.Land event) {
            EntityLivingBase entity = event.getParachuteOwner();
            if (!game.hasAiCompanions())
                return;
            if (!(entity instanceof EntityPlayer)) {
                return;
            }
            EntityPlayer player = (EntityPlayer) entity;
            World world = player.world;
            if (world.isRemote)
                return;
            Team team = game.teamManager.getEntityTeam(player);
            int missingTeamMembers = team != null ? Math.max(0, game.configuration.teamSize - team.getSize()) : 0;
            if (!team.isTeamLeader(player) || missingTeamMembers == 0)
                return;
            TeamAIManager teamAIManager = game.aiManager;
            Position2 tlPos = new Position2(player.posX, player.posZ);
            for (int i = 0; i < missingTeamMembers; i++) {
                if (!teamAIManager.canSpawnEntity())
                    break;
                Position2 spawnPos = tlPos.around(world.rand, 8);
                EntityAIPlayer aiPlayer = game.initAi(world, spawnPos);
                world.spawnEntity(aiPlayer);
                game.teamManager.join(team, aiPlayer);
                teamAIManager.entitySpawned(aiPlayer);
            }
            GameHelper.requestClientGameDataSynchronization(world);
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
            nbt.setTag("props", game.playerProperties.serialize());
            nbt.setBoolean("started", game.started);
            nbt.setBoolean("completed", game.completed);
            nbt.setInteger("completedTimer", game.completedTimer);
            nbt.setLong("gameTime", game.gameTime);
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
        public BattleRoyaleGame deserializeGameData(NBTTagCompound nbt, BattleRoyaleGameConfiguration configuration, World world) {
            UUID gameId = nbt.getUniqueId("gameId");
            BattleRoyaleGame game = new BattleRoyaleGame(gameId, configuration);
            game.teamManager.deserializeNBT(nbt.getCompoundTag("teams"));
            game.inviteManager.deserializeNBT(nbt.getCompoundTag("invites"));
            game.ruleStorage.deserialize(nbt.getCompoundTag("rules"));
            game.deathMessages.deserialize(nbt.getCompoundTag("deathMessages"));
            game.aiManager.deserialize(nbt.getCompoundTag("aiManager"));
            game.playerProperties.deserialize(nbt.getCompoundTag("props"));
            game.started = nbt.getBoolean("started");
            game.completed = nbt.getBoolean("completed");
            game.completedTimer = nbt.getInteger("completedTimer");
            game.gameTime = nbt.getLong("gameTime");
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
        public void serializeGameConfiguration(BattleRoyaleGameConfiguration configuration, DataWriter<?> writer) {
            configuration.serialize(writer);
        }

        @Override
        public BattleRoyaleGameConfiguration deserializeGameConfiguration(DataReader<?> reader) {
            return BattleRoyaleGameConfiguration.deserialize(reader);
        }
    }
}
