package dev.toma.pubgmc.common.games.game.domination;

import com.google.gson.JsonObject;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.game.*;
import dev.toma.pubgmc.api.game.map.GameMap;
import dev.toma.pubgmc.api.game.team.NoInvitesManager;
import dev.toma.pubgmc.api.game.team.TeamGame;
import dev.toma.pubgmc.api.game.team.TeamInviteManager;
import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import net.minecraft.command.CommandException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class DominationGame implements TeamGame<DominationGameConfiguration>, GameMenuProvider {

    private final UUID gameId;
    private final DominationGameConfiguration configuration;
    private final DominationTeamManager teamManager;
    private final List<GameEventListener> listeners;

    private boolean started;
    private boolean completed;
    private long gameTime;

    public DominationGame(UUID gameId, DominationGameConfiguration configuration) {
        this.gameId = gameId;
        this.configuration = configuration;
        this.teamManager = new DominationTeamManager();
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
    }

    @Override
    public void onGameInit(World world) {
        // TODO implement
    }

    @Override
    public void onGameStart(World world) {
        started = true;
        // TODO implement
    }

    @Override
    public void onGameStopped(World world, GameData data) {
        // TODO implement
    }

    @Override
    public void onGameTick(World world) {
        // TODO implement
        ++gameTime;
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
