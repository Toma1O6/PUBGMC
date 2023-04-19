package dev.toma.pubgmc.common.games.game.battleroyale;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.area.GameAreaType;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.util.GameRuleStorage;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.area.AbstractDamagingArea;
import dev.toma.pubgmc.common.games.area.DynamicGameArea;
import dev.toma.pubgmc.common.games.area.StaticGameArea;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;

import java.util.List;
import java.util.UUID;

public class BattleRoyaleGame implements TeamGame<BattleRoyaleGameConfiguration> {

    public static final AbstractDamagingArea.DamageOptions OUT_OF_BOUNDS_DAMAGE = new AbstractDamagingArea.DamageOptions(5.0F, 20);
    private final UUID gameId;
    private final BattleRoyaleGameConfiguration configuration;
    private final BattleRoyaleTeamManager teamManager;
    private final GameRuleStorage ruleStorage;

    private boolean started;
    private StaticGameArea mapArea;
    private DynamicGameArea zone;
    private int firstShrinkDelay;
    private boolean areaReady;
    private int phase;

    public BattleRoyaleGame(UUID gameId, BattleRoyaleGameConfiguration configuration) {
        this.gameId = gameId;
        this.configuration = configuration;
        this.teamManager = new BattleRoyaleTeamManager(this, configuration.teamSize);
        this.ruleStorage = new GameRuleStorage();
        this.firstShrinkDelay = configuration.areaGenerationDelay;
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
    public void performGameMapValidations(World world, GameMap map) throws GameException {
        mapArea = new StaticGameArea(map.bounds());
        mapArea.setDamageOptions(OUT_OF_BOUNDS_DAMAGE);
        zone = new DynamicGameArea(map.bounds());
        zone.onResizeCompleted(this::gameAreaResizeCompleted);
    }

    @Override
    public void onGameInit(World world) {
        if (configuration.automaticGameJoining) {
            List<EntityPlayer> playerList = world.playerEntities;
            if (playerList.size() <= 50) {
                // TODO rework to add players to existing teams
                GameDataProvider.getGameData(world)
                        .map(GameData::getGameLobby)
                        .ifPresent(lobby -> playerList.forEach(player -> {
                            playerJoinGame(player);
                            lobby.teleport(player);
                        }));
            }
        }
    }

    @Override
    public void onGameStart(World world) {
        started = true;
        GameHelper.updateLoadedGameObjects(world);
        if (!world.isRemote) {
            GameHelper.clearEmptyTeams((WorldServer) world, teamManager);
        }
        // TODO check teams and prepare plane
        teamManager.getAllActivePlayers(world).forEach(player -> {
            GameHelper.resetPlayerData(player);
            // TODO teleport to plane
            // TODO board plane
            // TODO give parachute
        });
        ruleStorage.storeValueAndSet(world, "naturalRegeneration", "false");
        GameHelper.requestClientGameDataSynchronization(world);
    }

    @Override
    public void onGameTick(World world) {
        if (world.getTotalWorldTime() % 200L == 0) {
            teamManager.getAllActivePlayers(world).forEach(GameHelper::fillPlayerHunger);
        }
        // TODO tick death messages
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
            mapArea.hurtAllOutsideArea(worldServer, teamManager);
            zone.hurtAllOutsideArea(worldServer, teamManager);
        } else {
            // client only tick
        }
        // TODO run ai spawner tick
        // TODO run airdrop tick
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
        if (!started && teamManager.getAllActivePlayers(player.world).count() < 50) {
            teamManager.createNewTeam(player);
            return true;
        }
        return false;
    }

    @Override
    public TeamManager getTeamManager() {
        return teamManager;
    }

    public DynamicGameArea getZone() {
        return zone;
    }

    private void gameAreaResizeCompleted(DynamicGameArea gameArea, World world) {
        BattleRoyaleGameConfiguration.ZonePhaseConfiguration[] configurations = configuration.zonePhases;
        if (configurations != null && configurations.length > phase) {
            DynamicGameArea.AreaTarget target = configurations[phase++].createNewShrinkTarget(gameArea, Pubgmc.rng());
            gameArea.setTarget(target);
        }
        GameHelper.requestClientGameDataSynchronization(world);
    }

    public static final class Serializer implements GameDataSerializer<BattleRoyaleGameConfiguration, BattleRoyaleGame> {

        @Override
        public NBTTagCompound serializeGameData(BattleRoyaleGame game) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setUniqueId("gameId", game.gameId);
            nbt.setTag("teams", game.teamManager.serialize());
            nbt.setTag("rules", game.ruleStorage.serialize());
            nbt.setBoolean("started", game.started);
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
            game.started = nbt.getBoolean("started");
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
    }
}
