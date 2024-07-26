package dev.toma.pubgmc.common.games.game.tournament;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.loadout.EntityLoadout;
import dev.toma.pubgmc.api.game.loadout.GameLoadoutManager;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import dev.toma.pubgmc.api.game.map.GameDoor;
import dev.toma.pubgmc.api.game.map.GameLobby;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;
import dev.toma.pubgmc.api.game.team.SimpleTeamInviteManager;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamInviteManager;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.util.GameRuleStorage;
import dev.toma.pubgmc.api.game.util.PlayerPropertyHolder;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.api.properties.SharedProperties;
import dev.toma.pubgmc.common.ai.*;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.game.SimpleLoadoutManager;
import dev.toma.pubgmc.common.games.map.*;
import dev.toma.pubgmc.common.games.playzone.AbstractDamagingPlayzone;
import dev.toma.pubgmc.common.games.playzone.StaticPlayzone;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.init.PMCDamageSources;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_PacketLoadoutSelect;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.Pair;
import dev.toma.pubgmc.util.helper.GameHelper;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TournamentGame implements TeamGame<TournamentGameConfiguration>, GameMenuProvider, LoadoutHandler, CaptureZones {

    private final UUID gameId;
    private final TournamentGameConfiguration configuration;
    private final List<GameEventListener> gameEventListeners;
    private final TournamentTeamManager teamManager;
    private final SimpleTeamInviteManager inviteManager;
    private final List<TournamentMatch> matches;
    private final GameRuleStorage ruleStorage;
    private final PlayerPropertyHolder playerProperties;
    private final TournamentGameAiManager aiManager;
    private final SimpleLoadoutManager loadoutManager;
    private final Object2IntMap<Team> teamScores;
    private final TournamentGameCaptureManager captureManager;

    private TournamentGameState gameState;
    private TournamentMatch activeMatch;
    private AbstractDamagingPlayzone playzone;
    private int eventTimer;
    private long tickTime;
    private boolean started;

    public TournamentGame(UUID gameId, TournamentGameConfiguration cfg) {
        this.gameId = gameId;
        this.configuration = cfg;
        this.gameEventListeners = new ArrayList<>();
        this.teamManager = new TournamentTeamManager(cfg.teamSize);
        this.inviteManager = new SimpleTeamInviteManager(teamManager);
        this.matches = new ArrayList<>();
        this.ruleStorage = new GameRuleStorage();
        this.playerProperties = new PlayerPropertyHolder();
        this.aiManager = new TournamentGameAiManager();
        this.loadoutManager = new SimpleLoadoutManager(EntityLoadout.EMPTY, getAvailableLoadouts(), false);
        this.captureManager = new TournamentGameCaptureManager();
        this.teamScores = new Object2IntOpenHashMap<>();
        this.setGameState(null, TournamentGameState.NEW);

        this.addListener(new EventListener(this));
    }

    @Override
    public void validateAndSetupForMap(World world, GameMap map) throws GameException {
        // Require team spawns based on team size
        int teamSize = configuration.teamSize;
        long red = map.getPoints(GameMapPoints.TEAM_SPAWNER, spawner -> spawner.getTeamType() == TeamType.RED).count();
        long blue = map.getPoints(GameMapPoints.TEAM_SPAWNER, spawner -> spawner.getTeamType() == TeamType.BLUE).count();
        if (red < teamSize) {
            throw new GameException("Not enough spawns for RED team. Required " + teamSize + ", found " + red);
        }
        if (blue < teamSize) {
            throw new GameException("Not enough spawns for BLUE team. Required " + teamSize + ", found " + blue);
        }
        // Require capture zone if capturing is enabled in config
        if (configuration.hasCaptureZones()) {
            Collection<CaptureZonePoint> points = map.getPoints(GameMapPoints.CAPTURE_ZONE);
            if (points.isEmpty()) {
                throw new GameException("Incorrectly setup capture map point! At least one capture zone is required");
            }
        }
        // Spectator area
        if (map.getPoints(GameMapPoints.SPECTATOR_POINT).size() == 0) {
            throw new GameException("You must define at least single spectator point");
        }
        StaticPlayzone staticPlayzone = map.bounds();
        staticPlayzone.setDamageOptions(new AbstractDamagingPlayzone.DamageOptions(100, 20));
        this.playzone = staticPlayzone;

        if (this.loadoutManager.getAvailableLoadouts().size() == 0) {
            throw new GameException("Game must support at least one loadout");
        }
        if (this.configuration.hasAiSpawns()) {
            if (map.getPoints(GameMapPoints.SPAWNER).size() < 1) {
                throw new GameException("Game has deathsquad enabled, you must include at least one generic entity spawn on map");
            }
        }
    }

    @Override
    public void onGameInit(World world) {
        // properties
        playerProperties.registerProperty(SharedProperties.KILLS, 0);
        playerProperties.registerProperty(SharedProperties.DEATHS, 0);
        // move players to lobby
        List<EntityPlayer> players = world.playerEntities;
        Team fillTeam = null;
        for (EntityPlayer player : players) {
            GameHelper.moveToLobby(player);
            GameHelper.resetPlayerData(player);
            playerProperties.register(player);
            if (fillTeam == null || fillTeam.getSize() >= configuration.teamSize) {
                fillTeam = teamManager.createNewTeam(player);
            } else {
                teamManager.join(fillTeam, player);
            }
        }
    }

    @Override
    public void onGameStart(World world) throws GameException {
        this.started = true;
        this.inviteManager.cancelPendingInvites();
        // Move all to spectator position
        if (!world.isRemote) {
            WorldServer server = (WorldServer) world;
            for (Team team : teamManager.getTeams()) {
                setSpectating(server, team);
            }

            ruleStorage.storeValueAndSet(world, GameRuleStorage.NATURAL_REGENERATION, GameRuleStorage.FALSE);
            ruleStorage.storeValueAndSet(world, GameRuleStorage.SHOW_DEATH_MESSAGES, GameRuleStorage.FALSE);
            ruleStorage.storeValueAndSet(world, GameRuleStorage.MOB_SPAWNING, GameRuleStorage.FALSE);
            ruleStorage.storeValueAndSet(world, GameRuleStorage.MOB_LOOT, GameRuleStorage.FALSE);
            configuration.worldConfiguration.apply(server, ruleStorage);

            teamManager.getAllActivePlayers(world).forEach(player -> player.setGameType(net.minecraft.world.GameType.ADVENTURE));
        }

        this.setGameState(world, TournamentGameState.LOADOUT_SELECT);
    }

    @Override
    public void onGameTick(World world) {
        switch (gameState) {
            case LOADOUT_SELECT:
                Objects.requireNonNull(world);
                List<UUID> players = teamManager.getRegisteredMembersOfType(Team.MemberType.PLAYER);
                int playerCount = players.size();
                int selectedCount = loadoutManager.getSelectedLoadoutsCount();
                if (++tickTime >= eventTimer || selectedCount >= playerCount) {
                    loadoutManager.selectRandomly(players, world.rand, true);
                    if (!world.isRemote) {
                        WorldServer server = (WorldServer) world;
                        createAiTeams(server);
                        updateTeamScores();
                        this.matches.addAll(this.generateMatches(new ArrayList<>(teamManager.getTeams()), TournamentMatchType.PLACEMENT));
                    }
                    loadoutManager.setLocked(true);
                    this.setGameState(world, TournamentGameState.WAITING);
                }
                break;
            case WAITING:
                if (++tickTime >= eventTimer) {
                    this.setGameState(world, TournamentGameState.STARTING);
                }
                break;
            case STARTING:
                if (++tickTime >= eventTimer) {
                    this.setGameState(world, TournamentGameState.KILL_ROUND);
                }
                break;
            case KILL_ROUND:
                if (++tickTime >= eventTimer) {
                    TournamentGameConfiguration.MatchConfiguration configuration = getMatchConfig();
                    TournamentGameState state = configuration.hasCaptureZoneEnabled() ? TournamentGameState.CAPTURE_ROUND : TournamentGameState.END_ROUND;
                    this.setGameState(world, state);
                }
                tickPlayZone(world);
                break;
            case CAPTURE_ROUND:
                tickCaptureManager(world);
                if (++tickTime >= eventTimer) {
                    TournamentGameConfiguration.MatchConfiguration matchConfig = getMatchConfig();
                    if (matchConfig.endRound) {
                        this.setGameState(world, TournamentGameState.END_ROUND);
                    } else {
                        this.activeMatch.setMatchStatus(world, TournamentMatchStatus.DRAW);
                    }
                }
                tickPlayZone(world);
                break;
            case END_ROUND:
                TournamentGameConfiguration.MatchConfiguration matchConfig = getMatchConfig();
                if (matchConfig.endOfRoundDamageInterval > 0 && ++tickTime % matchConfig.endOfRoundDamageInterval == 0) {
                    if (!world.isRemote) {
                        WorldServer server = (WorldServer) world;
                        teamManager.getActiveMatchEntities(server, activeMatch).forEach(entity -> entity.attackEntityFrom(PMCDamageSources.ZONE, matchConfig.endOfRoundDamage));
                    }
                }
                if (matchConfig.endRoundAiSpawnInterval > 0 && eventTimer > 0 && eventTimer % matchConfig.endRoundAiSpawnInterval == 0) {
                    createDeathSquadAi(world);
                }
                if (matchConfig.hasCaptureZoneEnabled()) {
                    tickCaptureManager(world);
                }
                tickPlayZone(world);
                break;
            case ENDING:
                // TODO notify about winner
                if (++tickTime >= eventTimer) {
                    if (this.activeMatch.isCompleted(configuration)) {
                        this.setActiveMatch(null);
                    } else {
                        this.activeMatch.setMatchStatus(world, TournamentMatchStatus.WAITING);
                    }
                    setGameState(world, TournamentGameState.WAITING);
                }
                break;
            case GAME_FINISHED:
                if (++tickTime >= eventTimer) {
                    GameHelper.stopGame(world);
                }
                break;
        }
    }

    public void setGameState(@Nullable World world, TournamentGameState state) {
        this.gameState = state;
        switch (state) {
            case LOADOUT_SELECT:
                Objects.requireNonNull(world);
                this.setEventTimer(configuration.loadoutSelectTime);
                if (!world.isRemote) {
                    teamManager.getAllActivePlayers(world).forEach(player -> openMenu((EntityPlayerMP) player));
                }
                break;
            case WAITING:
                Objects.requireNonNull(world);
                this.setEventTimer(configuration.matchWaitTime);
                if (!world.isRemote) {
                    WorldServer server = (WorldServer) world;
                    teamManager.getTeams().forEach(team -> setSpectating(server, team));
                }
                if (activeMatch == null) {
                    TournamentMatch match = matches.stream().filter(placementMatch -> placementMatch.getMatchStatus() == TournamentMatchStatus.WAITING)
                            .findFirst().orElse(null);
                    if (match != null) {
                        this.setActiveMatch(match);
                        this.activeMatch.setCallback(this::matchFinished);
                    } else {
                        scheduleQualificationOrFinalRound(world);
                    }
                }

                GameHelper.requestClientGameDataSynchronization(world);
                break;
            case STARTING:
                Objects.requireNonNull(world);
                Objects.requireNonNull(activeMatch);
                GameHelper.updateLoadedGameObjects(world, getGeneratorContext(), true);
                this.activeMatch.setMatchStatus(world, TournamentMatchStatus.PLAYING);
                if (!world.isRemote) {
                    WorldServer server = (WorldServer) world;
                    spawnPlayersInArena(server, TeamType.RED);
                    spawnPlayersInArena(server, TeamType.BLUE);
                    setGateState(world, false);
                    this.setEventTimer(configuration.matchStartTime);
                    GameHelper.requestClientGameDataSynchronization(world);
                }
                break;
            case KILL_ROUND:
                Objects.requireNonNull(world);
                Objects.requireNonNull(activeMatch);
                setGateState(world, true);
                setEventTimer(getMatchConfig().killRoundDuration);
                GameHelper.requestClientGameDataSynchronization(world);
                break;
            case CAPTURE_ROUND:
                // TODO send notification about capture zone
                Objects.requireNonNull(world);
                Objects.requireNonNull(captureManager);
                TournamentGameConfiguration.MatchConfiguration matchConfig = getMatchConfig();
                this.setEventTimer(matchConfig.captureRoundDuration);
                this.captureManager.selectRandomCaptureZone(world, GameHelper.getActiveGameMap(world));
                GameHelper.requestClientGameDataSynchronization(world);
                break;
            case END_ROUND:
                // TODO send notification about end phase
                break;
            case ENDING:
                Objects.requireNonNull(world);
                this.setEventTimer(configuration.matchEndTime);
                this.captureManager.clearData();
                GameHelper.requestClientGameDataSynchronization(world);
                break;
            case GAME_FINISHED:
                Objects.requireNonNull(world);
                setEventTimer(300);
                this.captureManager.clearData();
                GameHelper.requestClientGameDataSynchronization(world);
                break;
        }
    }

    private void tickCaptureManager(World world) {
        if (world.isRemote) {
            if (world.getTotalWorldTime() % 3L == 0L) {
                captureManager.doParticles(world);
            }
        } else {
            WorldServer server = (WorldServer) world;
            captureManager.updateCapture(server, teamManager, activeMatch);
            int captureTime = captureManager.getCapturingTime();
            int captureTimeLimit = getMatchConfig().captureTime;
            if (captureTime >= captureTimeLimit && captureManager.getCapturingTeam() != null) {
                TeamType type = activeMatch.getTeamType(captureManager.getCapturingTeam());
                Objects.requireNonNull(type, "Not participating team cannot capture zone");
                TournamentMatchStatus status = type == TeamType.RED ? TournamentMatchStatus.RED_WIN : TournamentMatchStatus.BLUE_WIN;
                activeMatch.setMatchStatus(world, status);
            }
        }
    }

    private void matchFinished(World world, TournamentMatch match) {
        match.completeRound();
        updateTeamScores();
        despawnAiEntities(world);
        if (match.getMatchType() == TournamentMatchType.FINAL && match.isCompleted(configuration)) {
            TournamentMatchStatus status = match.getMatchStatus();
            TeamType winningTeamType = status.getWinningTeam();
            if (winningTeamType == null) {
                // TODO match draw
            } else {
                Team winningTeam = match.getTeam(winningTeamType);
                // TODO match win
            }
            this.setGameState(world, TournamentGameState.GAME_FINISHED);
        } else {
            this.setGameState(world, TournamentGameState.ENDING);
        }
        GameHelper.requestClientGameDataSynchronization(world);
    }

    private void tickPlayZone(World world) {
        if (!world.isRemote) {
            WorldServer server = (WorldServer) world;
            if (activeMatch != null) {
                List<Entity> entities = teamManager.getActiveMatchEntities(server, activeMatch);
                if (playzone != null) {
                    playzone.hurtAllOutside(server, entities);
                }
            }
        }
    }

    @Override
    public void onGameStopped(World world, GameData data) {
        this.started = false;
        this.ruleStorage.restoreGameRules(world);

        GameLobby lobby = data.getGameLobby();
        if (lobby != null) {
            teamManager.getAllActivePlayers(world).forEach(player -> {
                GameHelper.resetPlayerData(player);
                lobby.teleport(player);
            });
        }
    }

    @Override
    public void openMenu(EntityPlayerMP player) {
        if (gameState != TournamentGameState.LOADOUT_SELECT) {
            player.sendMessage(new TextComponentString(TextFormatting.RED + "You cannot change loadout after game start"));
        } else {
            PacketHandler.sendToClient(new S2C_PacketLoadoutSelect(loadoutManager.getAvailableLoadouts()), player);
        }
    }

    @Override
    public boolean playerLeaveGame(EntityPlayer player) {
        playerProperties.delete(player.getUniqueID());
        List<Team> emptyTeams = teamManager.getTeams().stream()
                .filter(Team::isTeamEliminated)
                .collect(Collectors.toList());
        for (Team team : emptyTeams) {
            for (TournamentMatch match : matches) {
                match.setMatchStatus(player.world, TournamentMatchStatus.CANCELLED);
            }
            if (activeMatch != null && activeMatch.containsTeam(team)) {
                TeamType teamType = activeMatch.getTeamType(team).getEnemy();
                TournamentMatchStatus status = TournamentMatchStatus.getWinStatusByTeamType(teamType);
                activeMatch.cancelActive(player.world, configuration, status);
            }
            teamScores.remove(team);
        }
        GameHelper.requestClientGameDataSynchronization(player.world);
        return true;
    }

    @Override
    public boolean playerJoinGame(EntityPlayer player) {
        return false;
    }

    @Override
    public void addListener(GameEventListener listener) {
        this.gameEventListeners.add(listener);
    }

    @Override
    public void invokeEvent(Consumer<GameEventListener> consumer) {
        this.gameEventListeners.forEach(consumer);
    }

    @Nullable
    @Override
    public CaptureData getCapturePointData(BlockPos pos) {
        return null;
    }

    @Override
    public boolean shouldCaptureOrDefend(BlockPos pos, EntityLivingBase entity) {
        Team capturingTeam = captureManager.getCapturingTeam();
        Team entityTeam = teamManager.getEntityTeam(entity);
        return capturingTeam == null || !capturingTeam.equals(entityTeam) || captureManager.getCapturingTime() == 0;
    }

    @Override
    public GenerationType.Context getGeneratorContext() {
        return GenerationType.empty();
    }

    @Override
    public UUID getGameId() {
        return gameId;
    }

    @Override
    public TournamentGameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public GameType<TournamentGameConfiguration, ?> getGameType() {
        return GameTypes.TOURNAMENT;
    }

    @Override
    public TeamManager getTeamManager() {
        return teamManager;
    }

    @Override
    public TeamInviteManager getInviteManager() {
        return inviteManager;
    }

    public AbstractDamagingPlayzone getPlayzone() {
        return playzone;
    }

    public TournamentGameState getGameState() {
        return gameState;
    }

    @Nullable
    public TournamentGameCaptureManager getCaptureManager() {
        return captureManager;
    }

    public int getEventTimer() {
        return eventTimer;
    }

    public long getTickTimer() {
        return tickTime;
    }

    public TournamentMatch getActiveMatch() {
        return activeMatch;
    }

    @Override
    public GameLoadoutManager getLoadoutManager() {
        return loadoutManager;
    }

    public Object2IntMap<Team> getTeamScores() {
        return teamScores;
    }

    public void setEventTimer(int targetTime) {
        this.eventTimer = targetTime;
        this.tickTime = 0;
    }

    public void updateTeamScores() {
        teamManager.getTeams().forEach(team -> teamScores.put(team, 0));
        for (TournamentMatch match : matches) {
            if (!match.isCompleted(configuration)) {
                continue;
            }
            TournamentMatchStatus status = match.getFinalStatus(configuration);
            TeamType teamType = status.getWinningTeam();
            if (teamType == null) {
                increaseScore(teamScores, match.getTeam(TeamType.RED), configuration.drawScore);
                increaseScore(teamScores, match.getTeam(TeamType.BLUE), configuration.drawScore);
            } else {
                increaseScore(teamScores, match.getTeam(teamType), configuration.winScore);
            }
        }
    }

    public void setSpectating(WorldServer server, Team team) {
        SpectatorPoint point = getRandomSpectatorPoint(server);
        team.getAllMembers().values().forEach(member -> {
            Entity entity = member.getEntity(server);
            if (entity != null) {
                point.teleportOn(entity);
                clearInventory(entity);
            }
        });
    }

    public void setSpectating(WorldServer server, Entity entity) {
        SpectatorPoint point = getRandomSpectatorPoint(server);
        point.teleportOn(entity);
        clearInventory(entity);
    }

    public void clearInventory(Entity entity) {
        if (entity instanceof EntityPlayer) {
            GameHelper.resetPlayerData((EntityPlayer) entity);
        } else if (entity instanceof EntityAIPlayer) {
            EntityAIPlayer aiPlayer = (EntityAIPlayer) entity;
            aiPlayer.getInventory().clear();
            aiPlayer.getSpecialEquipmentInventory().clear();
        }
    }

    public static SpectatorPoint getRandomSpectatorPoint(WorldServer server) {
        List<SpectatorPoint> spectatorPoints = GameHelper.getActiveGameMapOrSubMap(server)
                .getPoints(GameMapPoints.SPECTATOR_POINT, t -> true)
                .collect(Collectors.toList());
        return PUBGMCUtil.randomListElement(spectatorPoints, server.rand);
    }

    public static void initAi(EntityAIPlayer player, TournamentGame game) {
        player.clearAI();
        EntityAIPlayer.addDefaultTasks(player);
        EntityAIGunAttack shootTask = new EntityAIGunAttack(player);
        shootTask.setReactionTime(5);
        player.tasks.addTask(2, shootTask);
        player.tasks.addTask(3, new EntityAICapturePoint(player, () -> game.captureManager.getCaptureZone() != null ?
                Collections.singletonList(game.captureManager.getCaptureZone().getPointPosition()) :
                Collections.emptyList()));
        player.tasks.addTask(4, new EntityAIHeal<>(player, game::shouldHeal, EntityAIPlayer::getInventory));
        player.tasks.addTask(5, new EntityAIVisitMapPoint<>(player, GameMapPoints.POINT_OF_INTEREST, 1.0));
        player.targetTasks.addTask(0, new EntityAIHurtByTargetTeamAware(player, false));
        player.targetTasks.addTask(1, new EntityAICallTeamForHelp(player));
        player.targetTasks.addTask(2, new EntityAITeamAwareNearestAttackableTarget<>(player, EntityPlayer.class, true));
        player.targetTasks.addTask(2, new EntityAITeamAwareNearestAttackableTarget<>(player, EntityAIPlayer.class, true));
    }

    private boolean shouldHeal(EntityLivingBase entity) {
        return entity.getHealth() < (entity.getMaxHealth() / 2.0F);
    }

    private List<EntityLoadout> getAvailableLoadouts() {
        List<EntityLoadout> list = new ArrayList<>();
        for (String path : configuration.availableLoadouts) {
            LoadoutManager.getLoadout(path).ifPresent(list::add);
        }
        return list;
    }

    private void spawnPlayersInArena(WorldServer world, TeamType teamType) {
        respawnAiEntities(world);
        List<TeamSpawnerPoint> spawners = GameHelper.getActiveGameMapOrSubMap(world)
                .getPoints(GameMapPoints.TEAM_SPAWNER, p -> p.getTeamType() == teamType)
                .collect(Collectors.toList());
        Team team = activeMatch.getTeam(teamType);
        List<Entity> entities = team.getActiveMemberStream().map(m -> m.getEntity(world))
                .filter(e -> e instanceof EntityLivingBase).collect(Collectors.toList());
        for (int i = 0; i < entities.size(); i++) {
            TeamSpawnerPoint spawnerPoint = spawners.get(i);
            EntityLivingBase entity = (EntityLivingBase) entities.get(i);
            entity.isDead = false;
            spawnerPoint.teleportOn(entity);
            loadoutManager.applyLoadout(entity);
        }
    }

    private void setGateState(World world, boolean state) {
        GameHelper.getLoadedTileGameObjects(world, t -> t instanceof GameDoor, t -> (GameDoor) t)
                .forEach(door -> door.setDoorState(state));
    }

    private List<TournamentMatch> generateMatches(List<Team> teams, TournamentMatchType matchType) {
        List<TournamentMatch> matches = new ArrayList<>();
        int index = 1;
        for (Team red : teams) {
            if (red.isTeamEliminated())
                continue;
            for (int i = index; i < teams.size(); i++) {
                Team blue = teams.get(i);
                if (blue.isTeamEliminated())
                    continue;
                TournamentMatch match = new TournamentMatch(red, blue, matchType);
                match.setMatchStatus(null, TournamentMatchStatus.WAITING);
                matches.add(match);
            }
            ++index;
        }
        Collections.shuffle(matches);
        return matches;
    }

    private void scheduleQualificationOrFinalRound(World world) {
        Multimap<Integer, Team> teamScores = this.getScoreboard();
        List<Integer> sortedScoreSet = teamScores.keySet().stream()
                .sorted(Comparator.<Integer>comparingInt(t -> t).reversed())
                .collect(Collectors.toList());
        List<Team> qualifiedTeams = new ArrayList<>();
        for (Integer key : sortedScoreSet) {
            qualifiedTeams.addAll(teamScores.get(key));
            if (qualifiedTeams.size() >= 2) {
                break;
            }
        }
        if (qualifiedTeams.size() > 2) {
            // schedule another qualification matches
            this.matches.addAll(generateMatches(qualifiedTeams, TournamentMatchType.QUALIFICATION));
        } else {
            // schedule final match
            TournamentMatch finalMatch = new TournamentMatch(qualifiedTeams.get(0), qualifiedTeams.get(1), TournamentMatchType.FINAL);
            finalMatch.setMatchStatus(world, TournamentMatchStatus.WAITING);
            finalMatch.setCallback(this::matchFinished);
            this.setActiveMatch(finalMatch);
        }
        this.setGameState(world, TournamentGameState.WAITING);
    }

    private Multimap<Integer, Team> getScoreboard() {
        Multimap<Integer, Team> map = ArrayListMultimap.create();
        updateTeamScores();
        Object2IntMap<Team> preliminaryScores = getTeamScores();
        for (Map.Entry<Team, Integer> entry : preliminaryScores.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        return map;
    }

    private void increaseScore(Object2IntMap<Team> map, Team team, int value) {
        if (!map.containsKey(team)) {
            map.put(team, value);
        } else {
            int v = map.getInt(team);
            map.put(team, v + value);
        }
    }

    private void despawnAiEntities(World world) {
        if (!world.isRemote) {
            WorldServer server = (WorldServer) world;
            Objects.requireNonNull(activeMatch);
            removeAiEntities(server, activeMatch.getTeam(TeamType.RED));
            removeAiEntities(server, activeMatch.getTeam(TeamType.BLUE));

            aiManager.getAdditionalEntities().forEach(uuid -> {
                Entity entity = server.getEntityFromUuid(uuid);
                if (entity != null) {
                    world.removeEntity(entity);
                }
            });
            aiManager.getAdditionalEntities().clear();
        }
    }

    private void removeAiEntities(WorldServer server, Team team) {
        team.getAllMembers().forEach((uuid, member) -> {
            if (member.getMemberType() == Team.MemberType.AI) {
                Entity entity = server.getEntityFromUuid(uuid);
                if (entity != null) {
                    aiManager.despawn(entity);
                }
            }
        });
    }

    private void respawnAiEntities(World world) {
        if (activeMatch != null) {
            respawnAiEntities(world, activeMatch.getTeam(TeamType.RED));
            respawnAiEntities(world, activeMatch.getTeam(TeamType.BLUE));
        } else {
            Pubgmc.logger.warn("Unable to respawn AI as there is no active match");
        }
    }

    private void respawnAiEntities(World world, Team team) {
        if (world.isRemote)
            return;
        WorldServer server = (WorldServer) world;
        team.getAllMembers().forEach((uuid, member) -> {
            if (member.getMemberType() == Team.MemberType.AI) {
                if (aiManager.getDeadAIs().contains(uuid)) {
                    aiManager.markAlive(uuid);
                    restoreAiEntity(server, uuid, player -> {
                        setSpectating(server, player);
                    });
                }
            }
        });
    }

    private void restoreAiEntity(WorldServer world, UUID entity, Consumer<EntityAIPlayer> event) {
        Pubgmc.logger.debug("Restoring entity ID {}", entity);
        EntityAIPlayer player = new EntityAIPlayer(world);
        NBTTagCompound compound = aiManager.getNBTData(entity);
        if (compound != null) {
            player.readFromNBT(compound);
        }
        player.setUniqueId(entity);
        player.assignGameId(gameId);
        event.accept(player);
        aiManager.markAlive(entity);
        Team team = teamManager.getEntityTeam(player);
        if (team == null) {
            throw new IllegalStateException("Attempted to respawn entity without team");
        }
        team.addActiveMember(player.getUniqueID());
        if (!world.spawnEntity(player)) {
            throw new IllegalStateException("Unable to respawn AI in world");
        }
    }

    private void createAiTeams(WorldServer world) {
        int activeTeamCount = teamManager.getTeams().size();
        int requiredTeamCount = configuration.requiredTeamCount;
        int aiTeams = requiredTeamCount - activeTeamCount;
        if (aiTeams > 0) {
            for (int i = 0; i < aiTeams; i++) {
                Team team = null;
                SpectatorPoint point = getRandomSpectatorPoint(world);
                BlockPos pointPos = point.getPointPosition();
                for (int j = 0; j < configuration.teamSize; j++) {
                    EntityAIPlayer player = new EntityAIPlayer(world);
                    player.setPosition(pointPos.getX(), pointPos.getY() + 1, pointPos.getZ());
                    player.assignGameId(gameId);

                    if (!world.spawnEntity(player)) {
                        throw new IllegalStateException("Creation of AI has failed, try again");
                    }
                    playerProperties.register(player);
                    loadoutManager.selectRandomly(player.getUniqueID(), world.rand);
                    aiManager.register(player);

                    if (team == null) {
                        team = teamManager.createNewTeam(player);
                    } else {
                        teamManager.join(team, player);
                    }
                }
            }
        }
    }

    private void createDeathSquadAi(World world) {
        if (world.isRemote) {
            return;
        }
        Pubgmc.logger.debug("Attempting to spawn death squad entity");
        EntityLoadout loadout = LoadoutManager.getLoadout(configuration.deathSquadEntityLoadout).orElse(EntityLoadout.EMPTY);
        EntityAIPlayer player = new EntityAIPlayer(world);
        player.assignGameId(gameId);
        player.setCustomNameTag("DeathSquad");

        GameMap map = GameHelper.getActiveGameMapOrSubMap(world);
        Objects.requireNonNull(map);
        List<SpawnerPoint> spawnPoints = new ArrayList<>(map.getPoints(GameMapPoints.SPAWNER));
        SpawnerPoint point = PUBGMCUtil.randomListElement(spawnPoints, world.rand);
        BlockPos pos = point.getPointPosition();
        player.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);

        if (!world.spawnEntity(player)) {
            Pubgmc.logger.error("Could not spawn deathsquad entity: {}", player);
            return;
        }
        aiManager.addExtraEntity(player.getUniqueID());
        LoadoutManager.apply(player, loadout);
    }

    private void setActiveMatch(@Nullable TournamentMatch match) {
        this.activeMatch = match;
        this.teamManager.setMatch(match);
    }

    public TournamentGameConfiguration.MatchConfiguration getMatchConfig() {
        return activeMatch.getMatchType().getMatchConfig(configuration);
    }

    private static final class EventListener implements GameEventListener {

        private final TournamentGame game;

        public EventListener(TournamentGame game) {
            this.game = game;
        }

        @Override
        public void onEntityHurt(LivingHurtEvent event) {
            EntityLivingBase livingBase = event.getEntityLiving();
            if (!game.gameState.isPlayState()) {
                event.setCanceled(true);
                return;
            }
            boolean matchParticipant = game.activeMatch != null && (game.activeMatch.isMatchParticipant(livingBase) || game.teamManager.getEntityTeam(livingBase) == null);
            if (!matchParticipant) {
                event.setCanceled(true);
            } else if (game.activeMatch != null) {
                CaptureZonePoint point = game.captureManager.getCaptureZone();
                TournamentGameConfiguration.MatchConfiguration configuration = game.getMatchConfig();
                if (point != null && point.isWithin(livingBase)) {
                    game.captureManager.applyCapturePenalty(configuration);
                    GameHelper.requestClientGameDataSynchronization(livingBase.world);
                }
            }
        }

        @Override
        public void onEntityAttack(LivingAttackEvent event) {
            DamageSource source = event.getSource();
            Entity sourceEntity = source.getTrueSource();
            if (!game.gameState.isPlayState()) {
                event.setCanceled(true);
                return;
            }
            if (sourceEntity != null && (game.activeMatch == null || (!game.activeMatch.isMatchParticipant(sourceEntity)) && game.teamManager.getEntityTeam(sourceEntity) != null)) {
                event.setCanceled(true);
            }
        }

        @Override
        public void onEntityDeath(LivingDeathEvent event) {
            if (event.isCanceled())
                return;
            // TODO implement death messages
            EntityLivingBase entity = event.getEntityLiving();
            DamageSource source = event.getSource();
            TournamentMatch match = game.getActiveMatch();
            if (match == null)
                return;
            TournamentTeamManager teamManager = game.teamManager;
            Pair<Team, TeamType> teamDataPair = teamManager.getTeamFromActiveMatch(entity, match);
            boolean isPlayState = game.gameState.isPlayState() && match.getMatchStatus() == TournamentMatchStatus.PLAYING;

            if (entity instanceof EntityAIPlayer && isPlayState) {
                if (game.aiManager.contains(entity.getUniqueID())) {
                    game.aiManager.markDead(entity.getUniqueID());
                }
            }

            if (teamDataPair != null && isPlayState) {
                game.playerProperties.increaseInt(entity.getUniqueID(), SharedProperties.DEATHS);
                Team team = teamDataPair.getLeft();
                team.eliminate(entity.getUniqueID());

                if (team.isTeamEliminated()) {
                    // end round
                    TeamType type = teamDataPair.getRight().getEnemy();
                    TournamentMatchStatus status = TournamentMatchStatus.getWinStatusByTeamType(type);
                    match.setMatchStatus(entity.world, status);
                }
            }

            Entity killer = source.getTrueSource();
            if (killer != null) {
                Pair<Team, TeamType> killerTeamData = teamManager.getTeamFromActiveMatch(killer, match);
                if (killerTeamData != null && isPlayState) {
                    game.playerProperties.increaseInt(killer.getUniqueID(), SharedProperties.KILLS);
                }
            }

            if (!event.isCanceled() && entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                player.inventory.clear();
                PlayerDataProvider.getOptional(player).ifPresent(data -> {
                    data.getEquipmentInventory().clear();
                    data.sync();
                });
            }
        }

        @Override
        public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
            EntityPlayer player = event.player;
            if (!player.world.isRemote) {
                WorldServer server = (WorldServer) player.world;
                game.setSpectating(server, player);

                Team team = game.teamManager.getEntityTeam(player);
                if (team != null) {
                    team.addActiveMember(player.getUniqueID());
                }
                GameHelper.reloadChunks(player);
            }
        }

        @Override
        public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            GameHelper.resetPlayerData(event.player);
            GameHelper.moveToLobby(event.player);

            TournamentGameConfiguration gameConfiguration = game.getConfiguration();
            Team joinedTeam = game.getTeamManager().getTeams().stream()
                    .filter(team -> team.getSize() < gameConfiguration.teamSize)
                    .findFirst()
                    .orElseGet(() -> {
                        game.getTeamManager().createNewTeam(event.player);
                        return null;
                    });
            if (joinedTeam != null) {
                game.teamManager.join(joinedTeam, event.player);
            }
            Pubgmc.logger.debug("Newly joined player {} has been added to team {}", event.player, game.teamManager.getEntityTeam(event.player));
            game.playerProperties.register(event.player);
            GameHelper.requestClientGameDataSynchronization(event.player.world);
        }

        @Override
        public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
            Team team = game.teamManager.getEntityTeam(event.player);
            if (team != null) {
                GameHelper.resetPlayerData(event.player);
                team.removeMemberById(event.player.getUniqueID());
                if (game.isStarted()) {
                    game.playerLeaveGame(event.player);
                } else if (team.isTeamEliminated()) {
                    game.teamManager.removeTeam(team.getTeamId());
                }
                GameHelper.requestClientGameDataSynchronization(event.player.world);
            }
        }
    }

    public static final class Serializer implements GameDataSerializer<TournamentGameConfiguration, TournamentGame> {

        @Override
        public NBTTagCompound serializeGameData(TournamentGame game) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setUniqueId("gameId", game.gameId);
            nbt.setBoolean("started", game.started);
            nbt.setTag("teamManager", game.teamManager.serializeNBT());
            nbt.setTag("inviteManager", game.inviteManager.serializeNBT());
            nbt.setTag("captureManager", game.captureManager.serialize());
            nbt.setTag("matches", SerializationHelper.collectionToNbt(game.matches, TournamentMatch::serialize));
            if (game.activeMatch != null) {
                nbt.setTag("activeMatch", game.activeMatch.serialize());
            }
            if (game.playzone != null) {
                nbt.setTag("playzone", PlayzoneType.serialize(game.playzone));
            }
            nbt.setInteger("gameState", game.gameState.ordinal());
            nbt.setInteger("eventTimer", game.eventTimer);
            nbt.setLong("tickTime", game.tickTime);
            nbt.setTag("gameRules", game.ruleStorage.serialize());
            nbt.setTag("properties", game.playerProperties.serialize());
            nbt.setTag("aiManager", game.aiManager.serialize());
            nbt.setTag("loadoutManager", game.loadoutManager.serialize());
            nbt.setTag("scoreboard", SerializationHelper.mapToNbt(game.teamScores, team -> team.getTeamId().toString(), NBTTagInt::new));
            return nbt;
        }

        @Override
        public TournamentGame deserializeGameData(NBTTagCompound nbt, TournamentGameConfiguration configuration, World world) {
            UUID gameId = nbt.getUniqueId("gameId");
            TournamentGame game = new TournamentGame(gameId, configuration);
            game.started = nbt.getBoolean("started");
            game.teamManager.deserializeNBT(nbt.getCompoundTag("teamManager"));
            game.inviteManager.deserializeNBT(nbt.getCompoundTag("inviteManager"));
            game.captureManager.deserialize(nbt.getCompoundTag("captureManager"), game.teamManager, world);
            SerializationHelper.collectionFromNbt(game.matches, nbt.getTagList("matches", Constants.NBT.TAG_COMPOUND), nbtBase -> TournamentMatch.deserialize((NBTTagCompound) nbtBase, game.teamManager));
            game.setActiveMatch(nbt.hasKey("activeMatch") ? TournamentMatch.deserialize(nbt.getCompoundTag("activeMatch"), game.teamManager) : null);
            if (game.activeMatch != null) {
                game.activeMatch.setCallback(game::matchFinished);
            }
            if (nbt.hasKey("playzone")) {
                game.playzone = PlayzoneType.deserialize(nbt.getCompoundTag("playzone"));
            }
            game.gameState = TournamentGameState.values()[nbt.getInteger("gameState")];
            game.eventTimer = nbt.getInteger("eventTimer");
            game.tickTime = nbt.getLong("tickTime");
            game.ruleStorage.deserialize(nbt.getCompoundTag("gameRules"));
            game.playerProperties.deserialize(nbt.getCompoundTag("properties"));
            game.aiManager.deserialize(nbt.getCompoundTag("aiManager"));
            game.loadoutManager.deserialize(nbt.getCompoundTag("loadoutManager"));
            SerializationHelper.mapFromNbt(game.teamScores, nbt.getCompoundTag("scoreboard"), key -> {
                UUID teamId = UUID.fromString(key);
                return game.teamManager.getTeamById(teamId);
            }, nbtBase -> ((NBTTagInt) nbtBase).getInt());
            return game;
        }

        @Override
        public void serializeGameConfiguration(TournamentGameConfiguration configuration, DataWriter<?> writer) {
            configuration.serialize(writer);
        }

        @Override
        public TournamentGameConfiguration deserializeGameConfiguration(DataReader<?> reader) {
            return TournamentGameConfiguration.deserialize(reader);
        }
    }
}
