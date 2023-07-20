package dev.toma.pubgmc.common.games.game.domination;

import com.google.gson.JsonObject;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.event.GameEvent;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.loadout.EntityLoadout;
import dev.toma.pubgmc.api.game.loadout.LoadoutManager;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;
import dev.toma.pubgmc.api.game.team.NoInvitesManager;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamInviteManager;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.util.GameRuleStorage;
import dev.toma.pubgmc.api.game.util.PlayerPropertyHolder;
import dev.toma.pubgmc.api.properties.SharedProperties;
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
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;

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
            // TODO spawn AI
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
            playzone.hurtAllOutside(server, () -> teamManager.getAllActiveEntities(server).collect(Collectors.toList()));
        }
        if (gameTime % 100L == 0L) {
            int redZones = pointManager.getCapturedPoints(TeamType.RED).size();
            int blueZones = pointManager.getCapturedPoints(TeamType.BLUE).size();
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

    @Nullable
    @Override
    public CaptureData getEntityCaptureData(Entity entity) {
        return pointManager.getEntityCaptureData(entity);
    }

    @Override
    public SimpleLoadoutManager getLoadoutManager() {
        return loadoutManager;
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

    private void registerEntity(EntityLivingBase entity) {
        properties.register(entity);
        teamManager.autoJoinTeam(entity);
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
        if (gameTime >= configuration.gameDuration || redTickets < 0 || blueTickets < 0 || playerCount == 0) {
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
                player.sendStatusMessage(component, true);
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
            nbt.setInteger("completionTimer", game.completionTimer);
            nbt.setInteger("redTickets", game.redTickets);
            nbt.setInteger("blueTickets", game.blueTickets);
            nbt.setTag("teams", game.teamManager.serializeNBT());
            nbt.setTag("points", game.pointManager.serialize());
            nbt.setTag("ruleStorage", game.ruleStorage.serialize());
            nbt.setTag("properties", game.properties.serialize());
            nbt.setTag("loadouts", game.loadoutManager.serialize());
            if (game.playzone != null) {
                nbt.setTag("playzone", PlayzoneType.serialize(game.playzone));
            }
            return nbt;
        }

        @Override
        public DominationGame deserializeGameData(NBTTagCompound nbt, DominationGameConfiguration configuration) {
            UUID gameId = nbt.getUniqueId("gameId");
            DominationGame game = new DominationGame(gameId, configuration);
            game.gameTime = nbt.getLong("gameTime");
            game.started = nbt.getBoolean("started");
            game.completed = nbt.getBoolean("completed");
            game.completionTimer = nbt.getInteger("completionTimer");
            game.redTickets = nbt.getInteger("redTickets");
            game.blueTickets = nbt.getInteger("blueTickets");
            game.teamManager.deserializeNBT(nbt.getCompoundTag("teams"));
            game.pointManager.deserialize(nbt.getCompoundTag("points"));
            game.ruleStorage.deserialize(nbt.getCompoundTag("ruleStorage"));
            game.properties.deserialize(nbt.getCompoundTag("properties"));
            game.loadoutManager.deserialize(nbt.getCompoundTag("loadouts"));
            if (nbt.hasKey("playzone")) {
                game.playzone = PlayzoneType.deserialize(nbt.getCompoundTag("playzone"));
            }
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

    static {
        Style style = LOADOUT_MESSAGE.getStyle();
        style.setColor(TextFormatting.YELLOW);
        style.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/game menu"));
    }
}
