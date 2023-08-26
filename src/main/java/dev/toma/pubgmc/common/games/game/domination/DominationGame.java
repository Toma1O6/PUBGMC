package dev.toma.pubgmc.common.games.game.domination;

import com.google.gson.JsonObject;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.event.GameEvent;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.loadout.EntityLoadout;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.mutator.GameMutatorHelper;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;
import dev.toma.pubgmc.api.game.team.NoInvitesManager;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamInviteManager;
import dev.toma.pubgmc.api.game.team.TeamRelations;
import dev.toma.pubgmc.api.game.util.*;
import dev.toma.pubgmc.api.properties.SharedProperties;
import dev.toma.pubgmc.common.ai.*;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.game.SimpleLoadoutManager;
import dev.toma.pubgmc.common.games.map.CaptureZonePoint;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.games.map.SpawnerPoint;
import dev.toma.pubgmc.common.games.map.TeamSpawnerPoint;
import dev.toma.pubgmc.common.games.playzone.AbstractDamagingPlayzone;
import dev.toma.pubgmc.common.games.util.SpawnPointSelector;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.S2C_PacketLoadoutSelect;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DominationGame implements TeamGame<DominationGameConfiguration>, GameMenuProvider, CaptureZones, LoadoutHandler {

    private static final ITextComponent LOADOUT_MESSAGE = new TextComponentTranslation("message.pubgmc.game.select_loadout");

    private final UUID gameId;
    private final DominationGameConfiguration configuration;
    private final DominationTeamManager teamManager;
    private final PlayerPropertyHolder properties;
    private final DominationCapturePointManager pointManager;
    private final SimpleLoadoutManager loadoutManager;
    private final SpawnPointSelector<TeamSpawnerPoint> redSpawns;
    private final SpawnPointSelector<TeamSpawnerPoint> blueSpawns;
    private final SpawnPointSelector<SpawnerPoint> spawns;
    private final GameRuleStorage ruleStorage;
    private final DominationAIManager aiManager;
    private final DeathMessageContainer deathMessages;
    private final List<GameEventListener> listeners;
    private AbstractDamagingPlayzone playzone;

    private int redTickets;
    private int blueTickets;
    private boolean started;
    private boolean completed;
    private int completionTimer;
    private long gameTime;

    public DominationGame(UUID gameId, DominationGameConfiguration configuration) {
        this.gameId = gameId;
        this.configuration = configuration;
        this.teamManager = new DominationTeamManager();
        this.properties = new PlayerPropertyHolder();
        this.pointManager = new DominationCapturePointManager(this.teamManager, this.configuration.captureSpeed, this::onPointCaptured);
        this.loadoutManager = new SimpleLoadoutManager(EntityLoadout.EMPTY, getAvailableLoadouts());
        this.redSpawns = new SpawnPointSelector<>(GameMapPoints.TEAM_SPAWNER, teamSpawnerPoint -> teamSpawnerPoint.getTeamType() == TeamType.RED, GameHelper::getActiveGameMapOrSubMap);
        this.blueSpawns = new SpawnPointSelector<>(GameMapPoints.TEAM_SPAWNER, teamSpawnerPoint -> teamSpawnerPoint.getTeamType() == TeamType.BLUE, GameHelper::getActiveGameMapOrSubMap);
        this.spawns = new SpawnPointSelector<>(GameMapPoints.SPAWNER, GameHelper::getActiveGameMapOrSubMap);
        this.ruleStorage = new GameRuleStorage();
        this.aiManager = new DominationAIManager();
        this.deathMessages = new DeathMessageContainer(7, 60);
        this.listeners = new ArrayList<>();

        this.addListener(new EventHandler(this));
    }

    @Override
    public void validateAndSetupForMap(World world, GameMap map) throws GameException {
        if (map.getPoints(GameMapPoints.CAPTURE_ZONE).isEmpty()) {
            throw new GameException("You must define atleast 1 capture zone on this map");
        }
        int spawnsCount = map.getPoints(GameMapPoints.SPAWNER).size();
        if (spawnsCount < configuration.playerCount) {
            throw new GameException("Not enough player spawns. You must include atleast " + configuration.playerCount + " spawns");
        }
        int halfPlayerCount = (configuration.playerCount / 2) + 2;
        int redSpawns = (int) map.getPoints(GameMapPoints.TEAM_SPAWNER).stream().filter(t -> t.getTeamType() == TeamType.RED).count();
        if (redSpawns < halfPlayerCount) {
            throw new GameException(String.format("Not enough team spawns for red team. You must include at least %d", halfPlayerCount));
        }
        int blueSpawns = (int) map.getPoints(GameMapPoints.TEAM_SPAWNER).stream().filter(t -> t.getTeamType() == TeamType.BLUE).count();
        if (blueSpawns < halfPlayerCount) {
            throw new GameException(String.format("Not enough team spawns for blue team. You must include at least %d", halfPlayerCount));
        }
        this.playzone = map.bounds();
        this.playzone.setDamageOptions(AbstractDamagingPlayzone.DamageOptions.BOUNDS);
        this.pointManager.init(map);
    }

    @Override
    public void onGameInit(World world) {
        properties.registerProperty(SharedProperties.KILLS, 0);
        properties.registerProperty(SharedProperties.DEATHS, 0);
        properties.registerProperty(SharedProperties.CAPTURES, 0);
        properties.registerProperty(SharedProperties.SCORE, 0);
        properties.registerProperty(SharedProperties.GAME_TIMESTAMP, 0L);
        for (EntityPlayer player : world.playerEntities) {
            properties.register(player);
            teamManager.autoJoinTeam(player);
            player.sendMessage(LOADOUT_MESSAGE);
            GameHelper.moveToLobby(player);
        }
        redTickets = configuration.totalTicketCount;
        blueTickets = configuration.totalTicketCount;
    }

    @Override
    public void onGameStart(World world) {
        started = true;
        GameHelper.updateLoadedGameObjects(world, getGeneratorContext());
        List<EntityPlayer> redPlayers = teamManager.getTeamById(DominationTeamManager.RED_TEAM).getAllMembers().values().stream()
                .map(member -> member.getPlayer(world))
                .collect(Collectors.toList());
        List<EntityPlayer> bluePlayers = teamManager.getTeamById(DominationTeamManager.BLUE_TEAM).getAllMembers().values().stream()
                .map(member -> member.getPlayer(world))
                .collect(Collectors.toList());
        ruleStorage.storeValueAndSet(world, GameRuleStorage.NATURAL_REGENERATION, GameRuleStorage.FALSE);
        ruleStorage.storeValueAndSet(world, GameRuleStorage.MOB_SPAWNING, GameRuleStorage.FALSE);
        ruleStorage.storeValueAndSet(world, GameRuleStorage.MOB_LOOT, GameRuleStorage.FALSE);
        ruleStorage.storeValueAndSet(world, GameRuleStorage.SHOW_DEATH_MESSAGES, GameRuleStorage.FALSE);
        if (!world.isRemote) {
            WorldServer server = (WorldServer) world;
            configuration.worldConfiguration.apply(server, ruleStorage);
            moveToGame(server, redPlayers, redSpawns);
            moveToGame(server, bluePlayers, blueSpawns);
            if (configuration.allowAi) {
                int aiCount = configuration.playerCount - (redPlayers.size() + bluePlayers.size());
                for (int i = 0; i < aiCount; i++) {
                    createAi(server);
                }
            }
        }
    }

    @Override
    public void onGameStopped(World world, GameData data) {
        started = false;
        ruleStorage.restoreGameRules(world);
        teamManager.getAllActivePlayers(world).forEach(player -> {
            GameHelper.resetPlayerData(player);
            GameHelper.moveToLobby(player);
        });
    }

    @Override
    public void onGameTick(World world) {
        if (completed && ++completionTimer > 100) {
            GameHelper.stopGame(world);
            return;
        }
        if (!world.isRemote) {
            WorldServer server = (WorldServer) world;
            if (gameTime % 5 == 0) {
                List<Entity> entityList = teamManager.getAllActiveEntities(server).collect(Collectors.toList());
                pointManager.update(entityList, server);
            }
            if (gameTime % 50 == 0) {
                deadAiTick(server);
            }
            playzone.hurtAllOutside(server, () -> teamManager.getAllActiveEntities(server).collect(Collectors.toList()));
        }
        if (gameTime % 100L == 0L) {
            int redZones = pointManager.getCapturedPoints(TeamType.RED).size();
            int blueZones = pointManager.getCapturedPoints(TeamType.BLUE).size();
            int pointsDiff = Math.abs(redZones - blueZones);
            if (!configuration.requirePointSuperiority || blueZones > redZones) {
                int value = configuration.requirePointSuperiority ? pointsDiff : blueZones;
                redTickets = redTickets - value * configuration.pointTicketLoss;
            }
            if (!configuration.requirePointSuperiority || redZones > blueZones) {
                int value = configuration.requirePointSuperiority ? pointsDiff : redZones;
                blueTickets = blueTickets - value * configuration.pointTicketLoss;
            }
            tryCompleteGame(world);
        }
        deathMessages.tick();
        if (++gameTime >= configuration.gameDuration) {
            tryCompleteGame(world);
        }
    }

    @Override
    public boolean playerJoinGame(EntityPlayer player) {
        if (tryJoin(player)) {
            player.inventory.clear();
            if (started && !player.world.isRemote) {
                WorldServer server = (WorldServer) player.world;
                respawn(player);
                SpawnerPoint point = getRespawnPoint(server, player);
                point.teleportOn(player);
                Team team = teamManager.getEntityTeam(player);
                Team.Member ai = team.getMemberByType(Team.MemberType.AI, false);
                team.removeMemberById(ai.getId());
                properties.delete(ai.getId());
                GameHelper.requestClientGameDataSynchronization(player.world);
            } else {
                GameHelper.moveToLobby(player);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean playerLeaveGame(EntityPlayer player) {
        Team playerTeam = teamManager.getEntityTeam(player);
        if (playerTeam != null) {
            playerTeam.removeMemberById(player.getUniqueID());
            properties.delete(player.getUniqueID());
            GameHelper.moveToLobby(player);
            if (started && !completed && !player.world.isRemote) {
                createAi((WorldServer) player.world);
            }
            player.inventory.clear();
            return true;
        }
        return false;
    }

    @Override
    public void openMenu(EntityPlayerMP player) {
        PacketHandler.sendToClient(new S2C_PacketLoadoutSelect(loadoutManager.getAvailableLoadouts()), player);
    }

    @Override
    public boolean isStarted() {
        return started;
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
    public UUID getGameId() {
        return gameId;
    }

    @Override
    public DominationGameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public DominationTeamManager getTeamManager() {
        return teamManager;
    }

    @Override
    public TeamInviteManager getInviteManager() {
        return NoInvitesManager.noInvites();
    }

    @Override
    public GameType<DominationGameConfiguration, ?> getGameType() {
        return GameTypes.DOMINATION;
    }

    @Override
    public GenerationType.Context getGeneratorContext() {
        return GenerationType.empty();
    }

    @Nullable
    @Override
    public CaptureData getCapturePointData(BlockPos pos) {
        return pointManager.getCaptureData(pos);
    }

    @Override
    public boolean shouldCaptureOrDefend(BlockPos pos, EntityLivingBase entity) {
        return pointManager.shouldCaptureOrDefend(pos, entity, teamManager);
    }

    @Override
    public SimpleLoadoutManager getLoadoutManager() {
        return loadoutManager;
    }

    public DeathMessageContainer getDeathMessageHolder() {
        return deathMessages;
    }

    public PlayerPropertyHolder getProperties() {
        return properties;
    }

    public int getRedTicketCount() {
        return redTickets;
    }

    public int getBlueTicketCount() {
        return blueTickets;
    }

    public Playzone getPlayzone() {
        return playzone;
    }

    public int getTimeRemaining() {
        return (int) Math.max(configuration.gameDuration - gameTime, 0);
    }

    public DominationCapturePointManager getPointManager() {
        return pointManager;
    }

    private boolean tryJoin(EntityPlayer player) {
        Team team = teamManager.getEntityTeam(player);
        if (team != null) {
            return false;
        }
        int playerCount = (int) teamManager.getAllActivePlayers(player.world).count();
        if (playerCount >= configuration.playerCount) {
            return false;
        }
        int redTeamCountAi = teamManager.getTeamByType(TeamType.RED).memberCountByType(Team.MemberType.AI, false);
        int blueTeamCountAi = teamManager.getTeamByType(TeamType.RED).memberCountByType(Team.MemberType.AI, false);
        if (redTeamCountAi == 0 && blueTeamCountAi == 0) {
            throw new UnsupportedOperationException("This should never happen, why did it happen? Please report to mod author");
        }
        Team targetTeam = teamManager.getTeamByType(redTeamCountAi > blueTeamCountAi ? TeamType.RED : TeamType.BLUE);
        teamManager.join(targetTeam, player);
        properties.register(player);
        return true;
    }

    private void respawn(EntityPlayer player) {
        World world = player.world;
        if (world.isRemote)
            return;
        bleedRespawnTickets(player);
        SpawnerPoint point = getRespawnPoint((WorldServer) world, player);
        point.teleportOn(player);
        GameHelper.reloadChunks(player);
        if (loadoutManager.hasSelectedLoadout(player.getUniqueID())) {
            loadoutManager.applyLoadout(player);
        } else {
            openMenu((EntityPlayerMP) player);
        }
    }

    private SpawnerPoint getRespawnPoint(WorldServer server, EntityLivingBase entity) {
        TeamType type = teamManager.getTeamType(entity);
        List<Entity> entities = teamManager.getAllActiveEntities(server).collect(Collectors.toList());
        if (gameTime >= 600L) {
            return spawns.getPoint(server, entities);
        } else {
            SpawnPointSelector<TeamSpawnerPoint> selector = type == TeamType.RED ? redSpawns : blueSpawns;
            return selector.getPoint(server, entities);
        }
    }

    private void moveToGame(WorldServer world, List<EntityPlayer> players, SpawnPointSelector<TeamSpawnerPoint> selector) {
        for (EntityPlayer player : players) {
            TeamSpawnerPoint point = selector.getPoint(world, players);
            BlockPos pos = point.getPointPosition();
            GameHelper.teleport(player, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            GameHelper.resetPlayerData(player);
            player.setGameType(net.minecraft.world.GameType.ADVENTURE);
            if (!loadoutManager.hasSelectedLoadout(player.getUniqueID())) {
                openMenu((EntityPlayerMP) player);
            } else {
                loadoutManager.applyLoadout(player);
            }
        }
    }

    private void tryCompleteGame(World world) {
        int playerCount = (int) teamManager.getAllActivePlayers(world).count();
        if (world.isRemote || completed)
            return;
        if (gameTime >= configuration.gameDuration || redTickets <= 0 || blueTickets <= 0 || playerCount == 0) {
            completed = true;
            ITextComponent component;
            TeamType winnerTeam;
            if (redTickets == blueTickets) {
                // draw
                winnerTeam = null;
                component = new TextComponentTranslation("message.pubgmc.game.domination.match_end.draw");
            } else {
                winnerTeam = redTickets < blueTickets ? TeamType.BLUE : TeamType.RED;
                component = new TextComponentTranslation("message.pubgmc.game.domination.match_end", winnerTeam.getTitle());
            }
            teamManager.getAllActivePlayers(world).forEach(player -> {
                TeamType playerTeam = teamManager.getTeamType(player);
                boolean won = winnerTeam == playerTeam;
                player.sendMessage(component);
                MinecraftForge.EVENT_BUS.post(new GameEvent.PlayerCompleteGame(this, player, won));
            });
        }
    }

    private List<EntityLoadout> getAvailableLoadouts() {
        List<EntityLoadout> list = new ArrayList<>();
        for (String key : configuration.availableLoadouts) {
            LoadoutManager.getLoadout(key).ifPresent(list::add);
        }
        return list;
    }

    private void onPointCaptured(CaptureZonePoint point, World world, List<Entity> entities) {
        entities.forEach(entity -> {
            properties.increaseInt(entity.getUniqueID(), SharedProperties.CAPTURES);
            properties.increaseInt(entity.getUniqueID(), SharedProperties.SCORE, 250);
        });
    }

    private void createAi(WorldServer world) {
        EntityAIPlayer player = new EntityAIPlayer(world);
        Team team = teamManager.autoJoinTeam(player);
        SpawnPointSelector<TeamSpawnerPoint> spawnPointSelector = teamManager.getTeamType(team) == TeamType.RED ? redSpawns : blueSpawns;
        TeamSpawnerPoint point = spawnPointSelector.getPoint(world, teamManager.getTeamEntities(team, world));
        BlockPos pos = point.getPointPosition();
        player.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
        player.assignGameId(gameId);
        player.forceSpawn = true;
        properties.register(player);
        world.spawnEntity(player);
        aiManager.register(player);
        List<EntityLoadout> availableLoadouts = loadoutManager.getAvailableLoadouts();
        EntityLoadout loadout = PUBGMCUtil.randomListElement(availableLoadouts, world.rand);
        if (loadout != null) {
            UUID playerId = player.getUniqueID();
            loadoutManager.select(playerId, loadout);
            loadoutManager.applyLoadout(player);
        }
    }

    public static void initAi(EntityAIPlayer player, DominationGame game) {
        player.clearAI();
        EntityAIPlayer.addDefaultTasks(player);
        EntityAIGunAttack shootTask = new EntityAIGunAttack(player);
        shootTask.setReactionTime(10);
        player.tasks.addTask(1, new EntityAIMoveIntoPlayzone(player, level -> game.playzone, 1.20F));
        player.tasks.addTask(2, shootTask);
        player.tasks.addTask(3, new EntityAICapturePoint(player, game.pointManager.getCaptureablePoints()));
        player.targetTasks.addTask(0, new EntityAIHurtByTargetTeamAware(player, false));
        player.targetTasks.addTask(1, new EntityAITeamAwareNearestAttackableTarget<>(player, EntityPlayer.class, true));
        player.targetTasks.addTask(1, new EntityAITeamAwareNearestAttackableTarget<>(player, EntityAIPlayer.class, true));
    }

    private void deadAiTick(WorldServer world) {
        aiManager.getDeadAIs().forEach(uuid -> {
            long time = properties.getProperty(uuid, SharedProperties.GAME_TIMESTAMP, 0L);
            if (gameTime - time < 60L) {
                return;
            }
            EntityAIPlayer player = new EntityAIPlayer(world);
            NBTTagCompound compound = aiManager.getNBTData(uuid);
            if (compound != null) {
                player.readFromNBT(compound);
            }
            player.setUniqueId(uuid);
            player.assignGameId(gameId);
            player.forceSpawn = true;
            SpawnerPoint point = getRespawnPoint(world, player);
            BlockPos pos = point.getPointPosition();
            player.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            loadoutManager.applyLoadout(player);
            aiManager.markAlive(uuid);
            properties.setProperty(uuid, SharedProperties.GAME_TIMESTAMP, gameTime);
            world.spawnEntity(player);
            bleedRespawnTickets(player);
        });
        GameHelper.requestClientGameDataSynchronization(world);
    }

    private void bleedRespawnTickets(EntityLivingBase respawningEntity) {
        properties.increaseInt(respawningEntity.getUniqueID(), SharedProperties.DEATHS);
        TeamType type = teamManager.getTeamType(respawningEntity);
        if (type == TeamType.RED) {
            redTickets -= configuration.killTicketLoss;
        } else if (type == TeamType.BLUE) {
            blueTickets -= configuration.killTicketLoss;
        }
        tryCompleteGame(respawningEntity.world);
    }

    private static final class EventHandler implements GameEventListener {

        private final DominationGame game;

        public EventHandler(DominationGame game) {
            this.game = game;
        }

        @Override
        public void onEntityDeath(LivingDeathEvent event) {
            if (event.isCanceled())
                return;
            EntityLivingBase entity = event.getEntityLiving();
            DamageSource source = event.getSource();
            Entity killer = source.getTrueSource();
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                if (game.teamManager.getEntityTeam(player) != null) {
                    player.inventory.clear();
                    awardKill(killer, entity, source);
                }
            } else if (game.teamManager.getEntityTeam(entity) != null) {
                awardKill(killer, entity, source);
                game.properties.setProperty(entity.getUniqueID(), SharedProperties.GAME_TIMESTAMP, game.gameTime);
                game.aiManager.markDead(entity.getUniqueID());
            }
            GameMutatorHelper.giveKillReward(entity, source);
            GameHelper.requestClientGameDataSynchronization(entity.world);
        }

        @Override
        public void onEntityAttack(LivingAttackEvent event) {
            if (!game.started || game.completed) {
                event.setCanceled(true);
            }
        }

        @Override
        public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
            EntityPlayer player = event.player;
            if (game.teamManager.getEntityTeam(player) != null) {
                if (game.started) {
                    game.respawn(player);
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
            if (game.tryJoin(player)) {
                player.sendMessage(new TextComponentTranslation("message.pubgmc.game.joinable_game_in_progress"));
            }
        }

        private void awardKill(@Nullable Entity killer, EntityLivingBase victim, DamageSource source) {
            if (!victim.world.isRemote) {
                DeathMessage message = GameHelper.createDefaultDeathMessage(victim, source);
                game.deathMessages.push(message);
            }
            if (!(killer instanceof EntityLivingBase) || game.completed || !game.started)
                return;
            if (game.teamManager.getEntityTeam(killer) != null) {
                TeamRelations relations = GameHelper.getEntityRelations((EntityLivingBase) killer, victim);
                if (relations != TeamRelations.UNKNOWN && relations != TeamRelations.FRIENDLY) {
                    game.properties.increaseInt(killer.getUniqueID(), SharedProperties.KILLS);
                    game.properties.increaseInt(killer.getUniqueID(), SharedProperties.SCORE, 25);
                    if (!killer.world.isRemote) {
                        game.tryCompleteGame(killer.world);
                        GameHelper.requestClientGameDataSynchronization(killer.world);
                    }
                }
            }
        }
    }

    public static final class Serializer implements GameDataSerializer<DominationGameConfiguration, DominationGame> {

        @Override
        public NBTTagCompound serializeGameData(DominationGame game) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setUniqueId("gameId", game.gameId);
            nbt.setLong("gameTime", game.gameTime);
            nbt.setBoolean("started", game.started);
            nbt.setBoolean("completed", game.completed);
            nbt.setInteger("completionTimer", game.completionTimer);
            nbt.setInteger("redTickets", game.redTickets);
            nbt.setInteger("blueTickets", game.blueTickets);
            nbt.setTag("teams", game.teamManager.serializeNBT());
            nbt.setTag("points", game.pointManager.serialize());
            nbt.setTag("ruleStorage", game.ruleStorage.serialize());
            nbt.setTag("properties", game.properties.serialize());
            nbt.setTag("loadouts", game.loadoutManager.serialize());
            nbt.setTag("ai", game.aiManager.serialize());
            nbt.setTag("deathMessages", game.deathMessages.serialize());
            if (game.playzone != null) {
                nbt.setTag("playzone", PlayzoneType.serialize(game.playzone));
            }
            return nbt;
        }

        @Override
        public DominationGame deserializeGameData(NBTTagCompound nbt, DominationGameConfiguration configuration, World world) {
            UUID gameId = nbt.getUniqueId("gameId");
            DominationGame game = new DominationGame(gameId, configuration);
            game.gameTime = nbt.getLong("gameTime");
            game.started = nbt.getBoolean("started");
            game.completed = nbt.getBoolean("completed");
            game.completionTimer = nbt.getInteger("completionTimer");
            game.redTickets = nbt.getInteger("redTickets");
            game.blueTickets = nbt.getInteger("blueTickets");
            game.teamManager.deserializeNBT(nbt.getCompoundTag("teams"));
            game.pointManager.deserialize(nbt.getCompoundTag("points"), world);
            game.ruleStorage.deserialize(nbt.getCompoundTag("ruleStorage"));
            game.properties.deserialize(nbt.getCompoundTag("properties"));
            game.loadoutManager.deserialize(nbt.getCompoundTag("loadouts"));
            game.aiManager.deserialize(nbt.getCompoundTag("ai"));
            game.deathMessages.deserialize(nbt.getCompoundTag("deathMessages"));
            if (nbt.hasKey("playzone")) {
                game.playzone = PlayzoneType.deserialize(nbt.getCompoundTag("playzone"));
            }
            return game;
        }

        @Override
        public NBTTagCompound serializeGameConfiguration(DominationGameConfiguration configuration) {
            return configuration.serialize();
        }

        @Override
        public DominationGameConfiguration deserializeGameConfiguration(NBTTagCompound nbt) {
            return DominationGameConfiguration.deserialize(nbt);
        }

        @Override
        public JsonObject serializeConfigurationToJson(DominationGameConfiguration configuration) {
            return configuration.jsonSerialize();
        }

        @Override
        public DominationGameConfiguration deserializeConfigurationFromJson(JsonObject object) {
            return DominationGameConfiguration.jsonDeserialize(object);
        }
    }

    static {
        Style style = LOADOUT_MESSAGE.getStyle();
        style.setColor(TextFormatting.YELLOW);
        style.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/game menu"));
    }
}
