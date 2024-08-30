package dev.toma.pubgmc.common.games.game.battleroyale;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import dev.toma.pubgmc.api.event.GameEvent;
import dev.toma.pubgmc.api.event.ParachuteEvent;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import dev.toma.pubgmc.api.game.map.GameLobby;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.mutator.GameMutatorHelper;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;
import dev.toma.pubgmc.api.game.team.*;
import dev.toma.pubgmc.api.game.util.DeathMessageContainer;
import dev.toma.pubgmc.api.game.util.GameRuleStorage;
import dev.toma.pubgmc.api.game.util.PlayerPropertyHolder;
import dev.toma.pubgmc.api.game.util.message.DeathMessages;
import dev.toma.pubgmc.api.properties.SharedProperties;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.ai.*;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.entity.EntityPlane;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.games.map.PointOfInterestPoint;
import dev.toma.pubgmc.common.games.playzone.AbstractDamagingPlayzone;
import dev.toma.pubgmc.common.games.playzone.DynamicPlayzone;
import dev.toma.pubgmc.common.games.playzone.StaticPlayzone;
import dev.toma.pubgmc.common.games.util.TeamAIManager;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BattleRoyaleGame implements TeamGame<BattleRoyaleGameConfiguration>, DiagnosticsDumpSupport {

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
    private final List<BlockPos> airdrops;
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
        this.airdrops = new ArrayList<>();
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
        playzone.onResizeStarted(this::playzoneResizeStarted);
        playzone.onResizeCompleted(this::playzoneResizeCompleted);
    }

    @Override
    public void onGameInit(World world) {
        playerProperties.registerProperty(SharedProperties.KILLS, 0);
        List<EntityPlayer> playerList = world.playerEntities.stream()
                .filter(EntityLivingBase::isEntityAlive)
                .collect(Collectors.toList());
        Team team = null;
        for (EntityPlayer player : playerList) {
            GameHelper.moveToLobby(player);
            if (!configuration.automaticGameJoining || team == null || team.getSize() >= configuration.teamSize) {
                team = teamManager.createNewTeam(player);
                playerProperties.register(player);
                continue;
            }
            teamManager.join(team, player);
            playerProperties.register(player);
        }
    }

    @Override
    public void onGameStart(World world) {
        started = true;
        this.inviteManager.cancelPendingInvites();
        int playerCount = (int) teamManager.getAllActivePlayers(world).count();
        int aiCount = configuration.allowAi ? configuration.entityCount - playerCount : 0;
        aiManager.setAllowedAiSpawnCount(aiCount);
        GameHelper.updateLoadedGameObjects(world, getGeneratorContext());
        if (!world.isRemote) {
            WorldServer worldServer = (WorldServer) world;
            GameHelper.clearEmptyTeams((WorldServer) world, teamManager);
            Supplier<EntityPlane> planeProvider = () -> {
                EntityPlane plane = GameHelper.initializePlaneWithPath(gameId, world, mapPlayzone, 1200);
                plane.setFlightDelay(configuration.planeFlightDelay);
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
            GameRuleStorage.applyDefaultGameRules(world, ruleStorage, configuration.displayChatDeathMessages);
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
                if (isGameCompleted(worldServer)) {
                    completed = true;
                    GameHelper.requestClientGameDataSynchronization(world);
                    List<Team> teams = new ArrayList<>(this.teamManager.getTeams());
                    Team winningTeam = teams.size() == 1 ? teams.get(0) : null;
                    for (EntityPlayerMP player : worldServer.getMinecraftServer().getPlayerList().getPlayers()) {
                        Team team = this.teamManager.getEntityTeam(player);
                        ITextComponent winningTeamInfo = null;
                        if (winningTeam != null) {
                            Team.Member teamLeader = winningTeam.getTeamLeader();
                            if (teamLeader != null) {
                                String username = winningTeam.getUsername(teamLeader.getId());
                                winningTeamInfo = new TextComponentTranslation("pubgmc.game.completed.winning_team", username);
                                winningTeamInfo.getStyle().setColor(TextFormatting.AQUA);
                            }
                        }
                        if (team != null && team.isMember(player.getUniqueID())) {
                            player.connection.sendPacket(new SPacketTitle(SPacketTitle.Type.TITLE, TextComponentHelper.GAME_WON, 20, 120, 60));
                        } else {
                            player.connection.sendPacket(new SPacketTitle(SPacketTitle.Type.TITLE, TextComponentHelper.GAME_COMPLETED, 20, 120, 60));
                            if (winningTeam != null) {
                                player.connection.sendPacket(new SPacketTitle(SPacketTitle.Type.SUBTITLE, winningTeamInfo, 20, 120, 60));
                            }
                        }
                    }
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
            Pubgmc.logger.debug("Player {}[{}] has left the game, removing them from game", player, player.getUniqueID());
            return true;
        }
        return false;
    }

    @Override
    public boolean playerJoinGame(EntityPlayer player) {
        if (!started) {
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

    public int getRemainingTimeBeforeShrinkComplete() {
        return this.isZoneShrinking() ? playzone.getRemainingResizingTime() : -1;
    }

    public int getRemainingTimeBeforeShrinking() {
        return playzoneReady ? playzone.getRemainingStationaryTime() : -1;
    }

    public int getAlivePlayerCount() {
        int players = teamManager.getAlivePlayerCount();
        int ai = aiManager.getRemainingAliveEntityCount();
        return players + ai;
    }

    public List<BlockPos> getAirdrops() {
        return airdrops;
    }

    private boolean isGameCompleted(WorldServer server) {
        int playerCount = (int) this.teamManager.getAllActivePlayers(server).count();
        // No players left, end the game. Does not allow AI vs AI games, but that does not matter right now
        if (playerCount <= 0) {
            return true;
        }
        int notSpawnedBots = this.aiManager.getAiEntitiesToSpawn();
        int activeTeamCount = this.teamManager.getTeams().size();
        // There is still some AI left to be spawned
        if (notSpawnedBots > 0) {
            return false;
        }
        return activeTeamCount <= 1;
    }

    private void playzoneResizeStarted(DynamicPlayzone playzone, World world) {
        Pubgmc.logger.debug("Triggering playzone resize start event");
        this.triggerAirdropGenerator(world, false);
        GameHelper.requestClientGameDataSynchronization(world);
    }

    private void playzoneResizeCompleted(DynamicPlayzone playzone, World world) {
        Pubgmc.logger.debug("Triggering playzone resize complete event");
        // airdrop
        this.triggerAirdropGenerator(world, true);

        // Zone resize
        BattleRoyaleGameConfiguration.ZonePhaseConfiguration[] configurations = configuration.zonePhases;
        if (configurations != null && configurations.length > phase) {
            DynamicPlayzone.ResizeTarget target = configurations[phase++].createNewShrinkTarget(playzone, Pubgmc.rng());
            playzone.setTarget(target);
        }
        GameHelper.requestClientGameDataSynchronization(world);
    }

    private void triggerAirdropGenerator(World world, boolean resizeCompleted) {
        BattleRoyaleGameConfiguration.ZonePhaseConfiguration[] configurations = configuration.zonePhases;
        if (!world.isRemote && phase < configurations.length && configurations[phase].getAirdropTrigger().shouldDrop(resizeCompleted)) {
            Playzone airdropPlayzone = this.playzone.getResultingPlayzone();
            List<EntityPlayer> playerList = teamManager.getAllActivePlayers(world).collect(Collectors.toList());
            Position2 pos = GameHelper.findLoadedPositionWithinPlayzone(airdropPlayzone, world, playerList, 0, 128);
            if (pos != null) {
                int x = (int) pos.getX();
                int z = (int) pos.getZ();
                int y = world.getHeight(x, z) + 80;
                PUBGMCUtil.spawnAirdrop(world, new BlockPos(x, y, z), false);
                this.airdrops.add(new BlockPos(x, y, z));
                playerList.forEach(player -> {
                    EntityPlayerMP serverPlayer = (EntityPlayerMP) player;
                    serverPlayer.connection.sendPacket(new SPacketTitle(SPacketTitle.Type.ACTIONBAR, TextComponentHelper.AIRDROP_SPAWNED, 20, 120, 60));
                });
            }
        }
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
                    this.spawnAi(world, leader);
                    if (memberCount > 0) {
                        for (int i = 0; i < memberCount; i++) {
                            EntityAIPlayer member = initAi(world, spawnPosition.around(world.rand, 6.0));
                            teamManager.join(team, member);
                            this.spawnAi(world, member);
                        }
                    }
                }
                GameHelper.requestClientGameDataSynchronization(world);
            }
        }
    }

    private void spawnAi(World world, EntityAIPlayer player) {
        world.spawnEntity(player);
        Pubgmc.logger.debug("AI spawned: {}", player);
        aiManager.entitySpawned(player);
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
        EntityAISearchLoot lootTask = new EntityAISearchLoot(player, 5, 1.10F);
        lootTask.setAreaProvider(game::getPlayzone);
        player.tasks.addTask(3, lootTask);
        player.tasks.addTask(4, new EntityAIMoveToTeamLeader(player, 32, 1.20F));
        player.tasks.addTask(5, new EntityAIHeal<>(player, game::shouldHeal, EntityAIPlayer::getInventory));
        player.tasks.addTask(6, new EntityAIMoveIntoPlayzone(player, level -> game.playzone.getResultingPlayzone()));
        EntityAIVisitMapPoint<PointOfInterestPoint> visitMapPointTask = new EntityAIVisitMapPoint<>(player, GameMapPoints.POINT_OF_INTEREST, 1.0F);
        visitMapPointTask.setAreaProvider(game::getPlayzone);
        player.tasks.addTask(7, visitMapPointTask);
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

    @Override
    public JsonElement createDump(Gson gson) {
        DiagnosticsData data = new DiagnosticsData();
        data.setTimestamp(ZonedDateTime.now());
        data.setConfiguration(this.configuration);
        data.setTeamManager(this.teamManager);
        data.setAiManager(this.aiManager);
        data.setStarted(this.started);
        data.setTimeElapsed(this.gameTime);
        data.setPhase(this.phase);
        data.setCompleted(this.completed);
        return gson.toJsonTree(data);
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
                Pubgmc.logger.debug("Player {}[{}] has disconnected from the game, removing them from player list", player, player.getUniqueID());
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
            game.deathMessages.push(DeathMessages.createMessage(entity, source));
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
            // TODO clean up
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
            Pubgmc.logger.debug("Entity death event processed, remaining players: {}, AIs: {} and not spawned AIs: {}", game.teamManager.getAlivePlayerCount(), game.aiManager.getRemainingAliveEntityCount(), game.aiManager.getAiEntitiesToSpawn());
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
            nbt.setTag("airdrops", SerializationHelper.collectionToNbt(game.airdrops, NBTUtil::createPosTag));
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
                game.playzone.onResizeStarted(game::playzoneResizeStarted);
                game.playzone.onResizeCompleted(game::playzoneResizeCompleted);
            }
            game.firstShrinkDelay = nbt.getInteger("playzoneStart");
            game.playzoneReady = nbt.getBoolean("playzoneReady");
            game.phase = nbt.getInteger("phase");
            SerializationHelper.collectionFromNbt(game.airdrops, nbt.getTagList("airdrops", Constants.NBT.TAG_COMPOUND), nbtBase -> NBTUtil.getPosFromTag((NBTTagCompound) nbtBase));
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

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private static final class DiagnosticsData {

        private ZonedDateTime timestamp;
        private BattleRoyaleGameConfiguration configuration;
        private TeamManager teamManager;
        private TeamAIManager aiManager;
        private boolean started;
        private long timeElapsed;
        private int phase;
        private boolean completed;

        public void setTimestamp(ZonedDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public void setConfiguration(BattleRoyaleGameConfiguration configuration) {
            this.configuration = configuration;
        }

        public void setTeamManager(TeamManager teamManager) {
            this.teamManager = teamManager;
        }

        public void setAiManager(TeamAIManager aiManager) {
            this.aiManager = aiManager;
        }

        public void setStarted(boolean started) {
            this.started = started;
        }

        public void setTimeElapsed(long timeElapsed) {
            this.timeElapsed = timeElapsed;
        }

        public void setPhase(int phase) {
            this.phase = phase;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
    }
}
