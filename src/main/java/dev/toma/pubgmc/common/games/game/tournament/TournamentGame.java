package dev.toma.pubgmc.common.games.game.tournament;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import dev.toma.pubgmc.api.game.*;
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
import dev.toma.pubgmc.common.ai.EntityAIGunAttack;
import dev.toma.pubgmc.common.ai.EntityAIHurtByTargetTeamAware;
import dev.toma.pubgmc.common.ai.EntityAITeamAwareNearestAttackableTarget;
import dev.toma.pubgmc.common.ai.EntityAIVisitMapPoint;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.map.CaptureZonePoint;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.games.map.SpectatorPoint;
import dev.toma.pubgmc.common.games.map.TeamSpawnerPoint;
import dev.toma.pubgmc.common.games.playzone.AbstractDamagingPlayzone;
import dev.toma.pubgmc.common.games.playzone.StaticPlayzone;
import dev.toma.pubgmc.common.games.util.SimpleAiManager;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.init.PMCDamageSources;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.Pair;
import dev.toma.pubgmc.util.helper.GameHelper;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
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

public class TournamentGame implements TeamGame<TournamentGameConfiguration> {

    private final UUID gameId;
    private final TournamentGameConfiguration configuration;
    private final List<GameEventListener> gameEventListeners;
    private final TournamentTeamManager teamManager;
    private final SimpleTeamInviteManager inviteManager;
    private final List<TournamentMatch> matches;
    private final GameRuleStorage ruleStorage;
    private final PlayerPropertyHolder playerProperties;
    private final SimpleAiManager aiManager;

    private TournamentGameState gameState;
    private TournamentGameCaptureManager captureManager;
    private TournamentMatch activeMatch;
    private AbstractDamagingPlayzone playzone;
    private int eventTimer;
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
        this.aiManager = new SimpleAiManager();
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
        if (configuration.placementRoundConfig.captureRoundDuration > 0 || configuration.finalRoundConfig.captureRoundDuration > 0) {
            Collection<CaptureZonePoint> points = map.getPoints(GameMapPoints.CAPTURE_ZONE);
            if (points.size() != 1) {
                throw new GameException("Incorrectly setup capture map point! Required capture points: 1, found " + points.size());
            }
            this.captureManager = new TournamentGameCaptureManager(points.toArray(new CaptureZonePoint[0])[0]);
        }
        // Spectator area
        if (map.getPoints(GameMapPoints.SPECTATOR_POINT).size() == 0) {
            throw new GameException("You must define at least single spectator point");
        }
        StaticPlayzone staticPlayzone = map.bounds();
        staticPlayzone.setDamageOptions(new AbstractDamagingPlayzone.DamageOptions(100, 20));
        this.playzone = staticPlayzone;
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
        // Move all to spectator position
        if (!world.isRemote) {
            WorldServer server = (WorldServer) world;
            for (Team team : teamManager.getTeams()) {
                setSpectating(server, team);
            }

            // Create AI teams
            int activeTeamCount = teamManager.getTeams().size();
            int requiredTeamCount = configuration.requiredTeamCount;
            int aiTeams = requiredTeamCount - activeTeamCount;
            if (aiTeams > 0) {
                for (int i = 0; i < aiTeams; i++) {
                    Team team = null;
                    SpectatorPoint point = getRandomSpectatorPoint(server);
                    BlockPos pointPos = point.getPointPosition();
                    for (int j = 0; j < configuration.teamSize; j++) {
                        EntityAIPlayer player = new EntityAIPlayer(world);
                        player.setPosition(pointPos.getX(), pointPos.getY() + 1, pointPos.getZ());
                        player.assignGameId(gameId);

                        if (!world.spawnEntity(player)) {
                            throw new GameException("Creation of AI has failed, try again");
                        }
                        playerProperties.register(player);
                        // TODO select and save loadout
                        // TODO save entity data
                        aiManager.register(player);

                        if (team == null) {
                            team = teamManager.createNewTeam(player);
                        } else {
                            teamManager.join(team, player);
                        }
                    }
                }
            }
            ruleStorage.storeValueAndSet(world, GameRuleStorage.NATURAL_REGENERATION, GameRuleStorage.FALSE);
            ruleStorage.storeValueAndSet(world, GameRuleStorage.SHOW_DEATH_MESSAGES, GameRuleStorage.FALSE);
            ruleStorage.storeValueAndSet(world, GameRuleStorage.MOB_SPAWNING, GameRuleStorage.FALSE);
            ruleStorage.storeValueAndSet(world, GameRuleStorage.MOB_LOOT, GameRuleStorage.FALSE);
            configuration.worldConfiguration.apply(server, ruleStorage);
        }

        this.matches.addAll(this.generateMatches(new ArrayList<>(teamManager.getTeams()), TournamentMatchType.PLACEMENT));
        this.setGameState(world, TournamentGameState.WAITING);
    }

    @Override
    public void onGameTick(World world) {
        switch (gameState) {
            case WAITING:
                if (--eventTimer <= 0) {
                    this.setGameState(world, TournamentGameState.STARTING);
                }
                break;
            case STARTING:
                if (--eventTimer <= 0) {
                    this.setGameState(world, TournamentGameState.KILL_ROUND);
                }
                break;
            case KILL_ROUND:
                if (--eventTimer <= 0) {
                    TournamentGameState state = captureManager != null ? TournamentGameState.CAPTURE_ROUND : TournamentGameState.END_ROUND;
                    this.setGameState(world, state);
                }
                // TODO tick game, once time runs out move either to capture phase or end phase
                tickPlayZone(world);
                break;
            case CAPTURE_ROUND:
                // TODO tick game, tick captures, once time runs our move to end phase
                if (--eventTimer <= 0) {
                    // TODO if endRoundTime in config == 0 then end active match as draw
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
                // TODO spawn hostile AI (?)
                TournamentGameConfiguration.MatchConfiguration matchConfig = getMatchConfig();
                if (matchConfig.endOfRoundDamageInterval > 0 && ++eventTimer % matchConfig.endOfRoundDamageInterval == 0) {
                    if (!world.isRemote) {
                        WorldServer server = (WorldServer) world;
                        teamManager.getActiveMatchEntities(server, activeMatch).forEach(entity -> entity.attackEntityFrom(PMCDamageSources.ZONE, matchConfig.endOfRoundDamage));
                    }
                }
                tickPlayZone(world);
                break;
            case ENDING:
                // TODO notify about winner, tick count down and then move back to spectator menu and schedule another match
                if (--eventTimer <= 0) {
                    if (this.activeMatch.isCompleted(configuration)) {
                        this.activeMatch = null;
                    } else {
                        this.activeMatch.setMatchStatus(world, TournamentMatchStatus.WAITING);
                    }
                    setGameState(world, TournamentGameState.WAITING);
                }
                break;
            case GAME_FINISHED:
                if (--eventTimer <= 0) {
                    GameHelper.stopGame(world);
                }
                break;
        }
    }

    public void setGameState(@Nullable World world, TournamentGameState state) {
        this.gameState = state;
        switch (state) {
            case WAITING:
                Objects.requireNonNull(world);
                this.eventTimer = configuration.matchWaitTime;
                if (!world.isRemote) {
                    WorldServer server = (WorldServer) world;
                    teamManager.getTeams().forEach(team -> setSpectating(server, team));
                }
                if (activeMatch == null) {
                    TournamentMatch match = matches.stream().filter(placementMatch -> placementMatch.getMatchStatus() == TournamentMatchStatus.WAITING)
                            .findFirst().orElse(null);
                    if (match != null) {
                        this.activeMatch = match;
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
                this.activeMatch.setMatchStatus(world, TournamentMatchStatus.PLAYING);
                if (!world.isRemote) {
                    WorldServer server = (WorldServer) world;
                    spawnPlayersInArena(server, TeamType.RED);
                    spawnPlayersInArena(server, TeamType.BLUE);
                    setGateState(world, false);
                    // TODO apply loadouts
                    this.eventTimer = configuration.matchStartTime;
                    GameHelper.requestClientGameDataSynchronization(world);
                }
                break;
            case KILL_ROUND:
                Objects.requireNonNull(world);
                Objects.requireNonNull(activeMatch);
                setGateState(world, true);
                eventTimer = getMatchConfig().killRoundDuration;
                GameHelper.requestClientGameDataSynchronization(world);
                break;
            case CAPTURE_ROUND:
                // TODO send notification about capture zone, unlock arena gate
                Objects.requireNonNull(world);
                TournamentGameConfiguration.MatchConfiguration matchConfig = getMatchConfig();
                this.eventTimer = matchConfig.captureRoundDuration;
                GameHelper.requestClientGameDataSynchronization(world);
                break;
            case END_ROUND:
                // TODO send notification about end phase
                break;
            case ENDING:
                // TODO set end timer
                Objects.requireNonNull(world);
                this.eventTimer = configuration.matchEndTime;
                GameHelper.requestClientGameDataSynchronization(world);
                break;
            case GAME_FINISHED:
                Objects.requireNonNull(world);
                eventTimer = 300;
                GameHelper.requestClientGameDataSynchronization(world);
                break;
        }
    }

    private void matchFinished(World world, TournamentMatch match) {
        match.completeRound();
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
    public boolean playerLeaveGame(EntityPlayer player) {
        return false;
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

    public int getEventTimer() {
        return eventTimer;
    }

    public TournamentMatch getActiveMatch() {
        return activeMatch;
    }

    public void setSpectating(WorldServer server, Team team) {
        SpectatorPoint point = getRandomSpectatorPoint(server);
        team.getAllMembers().values().forEach(member -> {
            Entity entity = member.getEntity(server);
            if (entity != null) {
                point.teleportOn(entity);
            }
        });
    }

    public void setSpectating(WorldServer server, Entity entity) {
        SpectatorPoint point = getRandomSpectatorPoint(server);
        point.teleportOn(entity);
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
        player.tasks.addTask(4, new EntityAIVisitMapPoint<>(player, GameMapPoints.POINT_OF_INTEREST, 1.0));
        player.targetTasks.addTask(0, new EntityAIHurtByTargetTeamAware(player, false));
        player.targetTasks.addTask(1, new EntityAITeamAwareNearestAttackableTarget<>(player, EntityPlayer.class, true));
        player.targetTasks.addTask(1, new EntityAITeamAwareNearestAttackableTarget<>(player, EntityAIPlayer.class, true));
    }

    private void spawnPlayersInArena(WorldServer world, TeamType teamType) {
        respawnAiEntities(world);
        List<TeamSpawnerPoint> spawners = GameHelper.getActiveGameMapOrSubMap(world)
                .getPoints(GameMapPoints.TEAM_SPAWNER, p -> p.getTeamType() == teamType)
                .collect(Collectors.toList());
        Team team = activeMatch.getTeam(teamType);
        List<Entity> entities = team.getActiveMemberStream().map(m -> m.getEntity(world))
                .filter(Objects::nonNull).collect(Collectors.toList());
        for (int i = 0; i < entities.size(); i++) {
            TeamSpawnerPoint spawnerPoint = spawners.get(i);
            spawnerPoint.teleportOn(entities.get(i));
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
            for (int i = index; i < teams.size(); i++) {
                Team blue = teams.get(i);
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
        Multimap<Integer, Team> teamScores = this.getTeamScores();
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
            this.activeMatch = new TournamentMatch(qualifiedTeams.get(0), qualifiedTeams.get(1), TournamentMatchType.FINAL);
            this.activeMatch.setMatchStatus(world, TournamentMatchStatus.WAITING);
            this.activeMatch.setCallback(this::matchFinished);
        }
        this.setGameState(world, TournamentGameState.WAITING);
    }

    private Multimap<Integer, Team> getTeamScores() {
        Multimap<Integer, Team> map = ArrayListMultimap.create();
        Object2IntMap<Team> preliminaryScores = new Object2IntOpenHashMap<>();
        teamManager.getTeams().forEach(team -> preliminaryScores.put(team, 0));
        for (TournamentMatch match : matches) {
            TournamentMatchStatus status = match.getMatchStatus();
            if (!status.isFinalState()) {
                continue;
            }
            TeamType teamType = status.getWinningTeam();
            if (teamType == null) {
                increaseScore(preliminaryScores, match.getTeam(TeamType.RED), configuration.drawScore);
                increaseScore(preliminaryScores, match.getTeam(TeamType.BLUE), configuration.drawScore);
            } else {
                increaseScore(preliminaryScores, match.getTeam(teamType), configuration.winScore);
            }
        }
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
        // TODO loadout
        if (!world.spawnEntity(player)) {
            throw new IllegalStateException("Unable to respawn AI in world");
        }
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
            boolean matchParticipant = game.activeMatch != null && game.activeMatch.isMatchParticipant(livingBase);
            if (!matchParticipant) {
                event.setCanceled(true);
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
            if (sourceEntity != null && (game.activeMatch == null || !game.activeMatch.isMatchParticipant(sourceEntity))) {
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
        }

        @Override
        public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
            EntityPlayer player = event.player;
            if (!player.world.isRemote) {
                WorldServer server = (WorldServer) player.world;
                game.setSpectating(server, player);
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
            if (game.captureManager != null) {
                nbt.setTag("captureManager", game.captureManager.serialize());
            }
            nbt.setTag("matches", SerializationHelper.collectionToNbt(game.matches, TournamentMatch::serialize));
            if (game.activeMatch != null) {
                nbt.setTag("activeMatch", game.activeMatch.serialize());
            }
            if (game.playzone != null) {
                nbt.setTag("playzone", PlayzoneType.serialize(game.playzone));
            }
            nbt.setInteger("gameState", game.gameState.ordinal());
            nbt.setInteger("eventTimer", game.eventTimer);
            nbt.setTag("gameRules", game.ruleStorage.serialize());
            nbt.setTag("properties", game.playerProperties.serialize());
            nbt.setTag("aiManager", game.aiManager.serialize());
            return nbt;
        }

        @Override
        public TournamentGame deserializeGameData(NBTTagCompound nbt, TournamentGameConfiguration configuration, World world) {
            UUID gameId = nbt.getUniqueId("gameId");
            TournamentGame game = new TournamentGame(gameId, configuration);
            game.started = nbt.getBoolean("started");
            game.teamManager.deserializeNBT(nbt.getCompoundTag("teamManager"));
            game.inviteManager.deserializeNBT(nbt.getCompoundTag("inviteManager"));
            if (game.captureManager != null) {
                game.captureManager.deserialize(nbt.getCompoundTag("captureManager"), game.teamManager);
            }
            SerializationHelper.collectionFromNbt(game.matches, nbt.getTagList("matches", Constants.NBT.TAG_COMPOUND), nbtBase -> TournamentMatch.deserialize((NBTTagCompound) nbtBase, game.teamManager));
            game.activeMatch = nbt.hasKey("activeMatch") ? TournamentMatch.deserialize(nbt.getCompoundTag("activeMatch"), game.teamManager) : null;
            if (game.activeMatch != null) {
                game.activeMatch.setCallback(game::matchFinished);
            }
            if (nbt.hasKey("playzone")) {
                game.playzone = PlayzoneType.deserialize(nbt.getCompoundTag("playzone"));
            }
            game.gameState = TournamentGameState.values()[nbt.getInteger("gameState")];
            game.eventTimer = nbt.getInteger("eventTimer");
            game.ruleStorage.deserialize(nbt.getCompoundTag("gameRules"));
            game.playerProperties.deserialize(nbt.getCompoundTag("properties"));
            game.aiManager.deserialize(nbt.getCompoundTag("aiManager"));
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
