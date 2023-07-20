package dev.toma.pubgmc.common.games.game.domination;

import com.google.gson.JsonObject;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.team.NoInvitesManager;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamInviteManager;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.util.GameRuleStorage;
import dev.toma.pubgmc.api.game.util.PlayerPropertyHolder;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.api.properties.SharedProperties;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.games.map.SpawnerPoint;
import dev.toma.pubgmc.common.games.map.TeamSpawnerPoint;
import dev.toma.pubgmc.common.games.playzone.AbstractDamagingPlayzone;
import dev.toma.pubgmc.common.games.util.SpawnPointSelector;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.command.CommandException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DominationGame implements TeamGame<DominationGameConfiguration>, GameMenuProvider, CaptureZones {

    private final UUID gameId;
    private final DominationGameConfiguration configuration;
    private final DominationTeamManager teamManager;
    private final PlayerPropertyHolder properties;
    private final DominationCapturePointManager pointManager;
    private final SpawnPointSelector<TeamSpawnerPoint> redSpawns;
    private final SpawnPointSelector<TeamSpawnerPoint> blueSpawns;
    private final SpawnPointSelector<SpawnerPoint> spawns;
    private final GameRuleStorage ruleStorage;
    private final List<GameEventListener> listeners;
    private AbstractDamagingPlayzone playzone;

    private int redTickets;
    private int blueTickets;
    private boolean started;
    private boolean completed;
    private long gameTime;

    public DominationGame(UUID gameId, DominationGameConfiguration configuration) {
        this.gameId = gameId;
        this.configuration = configuration;
        this.teamManager = new DominationTeamManager();
        this.properties = new PlayerPropertyHolder();
        this.pointManager = new DominationCapturePointManager(this.teamManager, this.configuration.captureSpeed);
        this.redSpawns = new SpawnPointSelector<>(GameMapPoints.TEAM_SPAWNER, teamSpawnerPoint -> teamSpawnerPoint.getTeamType() == TeamType.RED, GameHelper::getActiveGameMap);
        this.blueSpawns = new SpawnPointSelector<>(GameMapPoints.TEAM_SPAWNER, teamSpawnerPoint -> teamSpawnerPoint.getTeamType() == TeamType.BLUE, GameHelper::getActiveGameMap);
        this.spawns = new SpawnPointSelector<>(GameMapPoints.SPAWNER, GameHelper::getActiveGameMap);
        this.ruleStorage = new GameRuleStorage();
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
        for (EntityPlayer player : world.playerEntities) {
            registerEntity(player);
            GameHelper.moveToLobby(player);
        }
        redTickets = configuration.totalTicketCount;
        blueTickets = configuration.totalTicketCount;
        // TODO implement
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
        moveToGame(world, redPlayers, redSpawns);
        moveToGame(world, bluePlayers, blueSpawns);
        // TODO spawn AI
        ruleStorage.storeValueAndSet(world, GameRuleStorage.NATURAL_REGENERATION, GameRuleStorage.FALSE);
        ruleStorage.storeValueAndSet(world, GameRuleStorage.MOB_SPAWNING, GameRuleStorage.FALSE);
        ruleStorage.storeValueAndSet(world, GameRuleStorage.MOB_LOOT, GameRuleStorage.FALSE);
        ruleStorage.storeValueAndSet(world, GameRuleStorage.SHOW_DEATH_MESSAGES, GameRuleStorage.FALSE);
    }

    @Override
    public void onGameStopped(World world, GameData data) {
        // TODO implement
    }

    @Override
    public void onGameTick(World world) {
        // TODO implement
        if (completed) {

        }
        if (!world.isRemote) {
            WorldServer server = (WorldServer) world;
            if (gameTime % 10 == 0) {
                List<Entity> entityList = teamManager.getAllActiveEntities(server).collect(Collectors.toList());
                pointManager.update(entityList, server);
            }
        }
        if (gameTime % 100L == 0L) {
            int redZones = pointManager.getCapturedPoints(TeamType.RED);
            int blueZones = pointManager.getCapturedPoints(TeamType.BLUE);
            redTickets -= blueZones * configuration.pointTicketLoss;
            blueTickets -= redZones * configuration.pointTicketLoss;
            tryCompleteGame(world);
        }
        if (++gameTime >= configuration.gameDuration) {
            tryCompleteGame(world);
        }
    }

    @Override
    public boolean playerJoinGame(EntityPlayer player) {
        // TODO implement
        return false;
    }

    @Override
    public boolean playerLeaveGame(EntityPlayer player) {
        // TODO implement
        return false;
    }

    @Override
    public void openMenu(EntityPlayerMP player) throws CommandException {
        // TODO implement
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
    public TeamManager getTeamManager() {
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

    private void registerEntity(EntityLivingBase entity) {
        properties.register(entity);
        Team team = teamManager.autoJoinTeam(entity);
        TeamType type = teamManager.getTeamType(team);
        entity.sendMessage(new TextComponentTranslation("message.pubgmc.game.domination.team_joined", type.getTitle()));
    }

    private void moveToGame(World world, List<EntityPlayer> players, SpawnPointSelector<TeamSpawnerPoint> selector) {
        for (EntityPlayer player : players) {
            TeamSpawnerPoint point = selector.getPoint(world, players);
            BlockPos pos = point.getPointPosition();
            GameHelper.teleport(player, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            GameHelper.resetPlayerData(player);
            player.setGameType(net.minecraft.world.GameType.ADVENTURE);
            // TODO loadouts
        }
    }

    private void tryCompleteGame(World world) {

    }

    private static final class EventHandler implements GameEventListener {

        private final DominationGame game;

        public EventHandler(DominationGame game) {
            this.game = game;
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
            nbt.setTag("teams", game.teamManager.serializeNBT());
            nbt.setTag("points", game.pointManager.serialize());
            return nbt;
        }

        @Override
        public DominationGame deserializeGameData(NBTTagCompound nbt, DominationGameConfiguration configuration) {
            UUID gameId = nbt.getUniqueId("gameId");
            DominationGame game = new DominationGame(gameId, configuration);
            game.gameTime = nbt.getLong("gameTime");
            game.started = nbt.getBoolean("started");
            game.completed = nbt.getBoolean("completed");
            game.teamManager.deserializeNBT(nbt.getCompoundTag("teams"));
            game.pointManager.deserialize(nbt.getCompoundTag("points"));
            return game;
        }

        @Override
        public NBTTagCompound serializeGameConfiguration(DominationGameConfiguration configuration) {
            return new NBTTagCompound();
        }

        @Override
        public DominationGameConfiguration deserializeGameConfiguration(NBTTagCompound nbt) {
            return new DominationGameConfiguration();
        }

        @Override
        public JsonObject serializeConfigurationToJson(DominationGameConfiguration configuration) {
            return new JsonObject();
        }

        @Override
        public DominationGameConfiguration deserializeConfigurationFromJson(JsonObject object) {
            return new DominationGameConfiguration();
        }
    }
}
