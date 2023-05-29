package dev.toma.pubgmc.common.games.game.battleroyale;

import com.google.gson.JsonObject;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.area.GameArea;
import dev.toma.pubgmc.api.game.area.GameAreaType;
import dev.toma.pubgmc.api.game.map.GameLobby;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.util.DeathMessage;
import dev.toma.pubgmc.api.game.util.DeathMessageContainer;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.api.util.GameRuleStorage;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.common.entity.EntityPlane;
import dev.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.area.AbstractDamagingArea;
import dev.toma.pubgmc.common.games.area.DynamicGameArea;
import dev.toma.pubgmc.common.games.area.StaticGameArea;
import dev.toma.pubgmc.common.games.util.TeamAIManager;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import dev.toma.pubgmc.util.helper.TextComponentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
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

    public static final AbstractDamagingArea.DamageOptions OUT_OF_BOUNDS_DAMAGE = new AbstractDamagingArea.DamageOptions(5.0F, 20);
    private final UUID gameId;
    private final BattleRoyaleGameConfiguration configuration;
    private final SimpleTeamManager teamManager;
    private final SimpleTeamInviteManager inviteManager;
    private final TeamAIManager aiManager;
    private final GameRuleStorage ruleStorage;
    private final DeathMessageContainer deathMessages;
    private final List<GameEventListener> listeners;
    private boolean started;
    private boolean completed;
    private int completedTimer;
    private StaticGameArea mapArea;
    private DynamicGameArea zone;
    private int firstShrinkDelay;
    private boolean areaReady;
    private int phase;

    public BattleRoyaleGame(UUID gameId, BattleRoyaleGameConfiguration configuration) {
        this.gameId = gameId;
        this.configuration = configuration;
        this.teamManager = new SimpleTeamManager();
        this.inviteManager = new SimpleTeamInviteManager(teamManager);
        this.ruleStorage = new GameRuleStorage();
        this.aiManager = new TeamAIManager(teamManager);
        this.deathMessages = new DeathMessageContainer(5, 100);
        this.listeners = new ArrayList<>();
        this.firstShrinkDelay = configuration.areaGenerationDelay;
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
        mapArea = new StaticGameArea(map.bounds());
        mapArea.setDamageOptions(OUT_OF_BOUNDS_DAMAGE);
        zone = new DynamicGameArea(map.bounds());
        zone.onResizeCompleted(this::gameAreaResizeCompleted);
    }

    @Override
    public void onGameInit(World world) {
        if (configuration.automaticGameJoining) {
            List<EntityPlayer> playerList = world.playerEntities;
            if (playerList.size() <= EntityPlane.PLANE_CAPACITY) {
                GameDataProvider.getGameData(world)
                        .map(GameData::getGameLobby)
                        .ifPresent(lobby -> {
                            Team team = null;
                            for (EntityPlayer player : playerList) {
                                if (team == null || team.getSize() >= configuration.teamSize) {
                                    team = teamManager.createNewTeam(player);
                                    continue;
                                }
                                teamManager.join(team, player);
                            }
                        });
            }
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
            GameHelper.clearEmptyTeams((WorldServer) world, teamManager);
            EntityPlane plane = GameHelper.initializePlaneWithPath(gameId, world, mapArea, 1200);
            plane.setMovementSpeedMultiplier(configuration.planeSpeed);
            plane.setFlightHeight(configuration.planeFlightHeight);
            GameHelper.spawnPlaneWithPlayers(plane, teamManager, world, player -> {
                GameHelper.resetPlayerData(player);
                player.addItemStackToInventory(new ItemStack(PMCItems.PARACHUTE));
            });
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
        if (!areaReady && --firstShrinkDelay <= 0) {
            areaReady = true;
            gameAreaResizeCompleted(zone, world);
            BattleRoyaleGameConfiguration.ZonePhaseConfiguration[] configurations = configuration.zonePhases;
            if (configurations != null && configurations.length > 0) {
                zone.setDamageOptions(configurations[0].getDamageOptions());
            } else {
                zone.setDamageOptions(OUT_OF_BOUNDS_DAMAGE);
            }
        }
        if (!world.isRemote) {
            // server only tick
            WorldServer worldServer = (WorldServer) world;
            List<Entity> activeEntities = teamManager.getAllActiveEntities(worldServer).collect(Collectors.toList());
            mapArea.hurtAllOutsideArea(worldServer, activeEntities);
            zone.hurtAllOutsideArea(worldServer, activeEntities);
            tickAIEntities(worldServer);
            if (world.getTotalWorldTime() % 20 == 0) {
                int teamCount = teamManager.getTeams().size();
                int aiCount = aiManager.getRemainingAliveEntityCount();
                if (teamCount <= 1 && aiCount <= 0) {
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
        zone.tickGameArea(world);
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
    public TeamInviteManager getInviteHandler() {
        return inviteManager;
    }

    public DynamicGameArea getZone() {
        return zone;
    }

    public DeathMessageContainer getDeathMessageContainer() {
        return deathMessages;
    }

    public boolean isZoneShrinking() {
        return areaReady && zone.isResizing();
    }

    public int getRemainingTimeBeforeShrinking() {
        return areaReady ? zone.getRemainingStationaryTime() : -1;
    }

    public int getAlivePlayerCount(World world) {
        int players = (int) teamManager.getAllActivePlayers(world).count();
        int ai = aiManager.getRemainingAliveEntityCount();
        return players + ai;
    }

    private void gameAreaResizeCompleted(DynamicGameArea gameArea, World world) {
        BattleRoyaleGameConfiguration.ZonePhaseConfiguration[] configurations = configuration.zonePhases;
        // airdrop
        if (!world.isRemote) {
            GameArea airdropArea = zone.getResultingGameArea();
            List<EntityPlayer> playerList = teamManager.getAllActivePlayers(world).collect(Collectors.toList());
            Position2 pos = GameHelper.findLoadedPositionWithinArea(airdropArea, world, playerList, 0, 128);
            if (pos != null) {
                int x = (int) pos.getX();
                int z = (int) pos.getZ();
                int y = world.getHeight(x, z) + 80;
                PUBGMCUtil.spawnAirdrop(world, new BlockPos(x, y, z), false);
            }
        }
        // Zone resize
        if (configurations != null && configurations.length > phase) {
            DynamicGameArea.AreaTarget target = configurations[phase++].createNewShrinkTarget(gameArea, Pubgmc.rng());
            gameArea.setTarget(target);
        }
        GameHelper.requestClientGameDataSynchronization(world);
    }

    private void tickAIEntities(WorldServer world) {
        long total = world.getTotalWorldTime();
        if (total % 20L == 0L) {
            aiManager.checkForUnloadedEntities(world);
        }
        if (total % configuration.aiSpawnInterval == 0L) {
            if (aiManager.canSpawnEntity()) {
                GameArea shrunkZone = zone.getResultingGameArea();
                List<EntityPlayer> playerList = teamManager.getAllActivePlayers(world).collect(Collectors.toList());
                int memberCount = Math.min(configuration.teamSize, aiManager.getRemainingAliveEntityCount()) - 1;
                Position2 spawnPosition = GameHelper.findLoadedPositionWithinArea(shrunkZone, world, playerList, 32, 96);
                if (spawnPosition == null) {
                    spawnPosition = GameHelper.findLoadedPositionWithinArea(shrunkZone, world, playerList, 0, 96, true);
                }
                if (spawnPosition != null) {
                    EntityAIPlayer leader = initAi(world, spawnPosition);
                    Team team = teamManager.createNewTeam(leader);
                    world.spawnEntity(leader);
                    aiManager.entitySpawned(leader);
                    if (memberCount > 0) {
                        for (int i = 0; i < memberCount; i++) {
                            EntityAIPlayer member = initAi(world, spawnPosition.around(world.rand, 6.0));
                            team.add(member);
                            world.spawnEntity(member);
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
        // TODO loadout
        addAiTasks(player);
        player.assignGameId(gameId);
        return player;
    }

    private void addAiTasks(EntityAIPlayer player) {
        // TODO Tasks
        // Loot
        // Target non-team members
        // Move to zone
        // Heal
        // Attack non-team members
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
            if (joined || game.mapArea.isWithin(player)) {
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
            if (team == null) {
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
            nbt.setTag("teams", game.teamManager.serialize());
            nbt.setTag("rules", game.ruleStorage.serialize());
            nbt.setTag("deathMessages", game.deathMessages.serialize());
            nbt.setTag("aiManager", game.aiManager.serialize());
            nbt.setBoolean("started", game.started);
            nbt.setBoolean("completed", game.completed);
            nbt.setInteger("completedTimer", game.completedTimer);
            if (game.mapArea != null) {
                nbt.setTag("mapArea", GameAreaType.serialize(game.mapArea));
            }
            if (game.zone != null) {
                nbt.setTag("zone", GameAreaType.serialize(game.zone));
            }
            nbt.setInteger("areaStart", game.firstShrinkDelay);
            nbt.setBoolean("areaReady", game.areaReady);
            nbt.setInteger("phase", game.phase);
            return nbt;
        }

        @Override
        public BattleRoyaleGame deserializeGameData(NBTTagCompound nbt, BattleRoyaleGameConfiguration configuration) {
            UUID gameId = nbt.getUniqueId("gameId");
            BattleRoyaleGame game = new BattleRoyaleGame(gameId, configuration);
            game.teamManager.deserialize(nbt.getCompoundTag("teams"));
            game.ruleStorage.deserialize(nbt.getCompoundTag("rules"));
            game.deathMessages.deserialize(nbt.getCompoundTag("deathMessages"));
            game.aiManager.deserialize(nbt.getCompoundTag("aiManager"));
            game.started = nbt.getBoolean("started");
            game.completed = nbt.getBoolean("completed");
            game.completedTimer = nbt.getInteger("completedTimer");
            if (nbt.hasKey("mapArea", Constants.NBT.TAG_COMPOUND)) {
                game.mapArea = GameAreaType.deserialize(nbt.getCompoundTag("mapArea"));
            }
            if (nbt.hasKey("zone", Constants.NBT.TAG_COMPOUND)) {
                game.zone = GameAreaType.deserialize(nbt.getCompoundTag("zone"));
                game.zone.onResizeCompleted(game::gameAreaResizeCompleted);
            }
            game.firstShrinkDelay = nbt.getInteger("areaStart");
            game.areaReady = nbt.getBoolean("areaReady");
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
