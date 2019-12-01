package com.toma.pubgmc.api.games;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.GameObjectiveBased;
import com.toma.pubgmc.api.objectives.ObjectiveReachScore;
import com.toma.pubgmc.api.settings.EntityDeathManager;
import com.toma.pubgmc.api.settings.GameBotManager;
import com.toma.pubgmc.api.settings.GameManager;
import com.toma.pubgmc.api.settings.TeamManager;
import com.toma.pubgmc.api.teams.Team;
import com.toma.pubgmc.util.game.ZoneSettings;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameBombDefuse extends GameObjectiveBased {

    private static final int ROUND_TIME = 2600;

    private final GameManager gameManager;
    private final GameBotManager gameBotManager;
    private final TeamManager teamManager;
    private final EntityDeathManager deathManager;

    private Map<UUID, Integer> playerMoneyMap = new HashMap<>();
    private TeamStats teamStats = new TeamStats();
    private int roundsLeft = 30;
    private BombStats bombStats;

    public GameBombDefuse(String name) {
        super(name);
        this.gameManager = GameManager.Builder.create(this)
                .waitTime(5)
                .objective(() -> new ObjectiveReachScore<>(this, team -> teamStats.getScore(team)).setRequiredScore(16))
                .verification(game -> game.roundsLeft <= 0 || game.teamStats.getHighestScore() >= 16)
                .build();
        this.gameBotManager = GameBotManager.Builder.create(this)
                .build();
        this.teamManager = TeamManager.Builder.create(this)
                .build();
        this.deathManager = EntityDeathManager.Builder.create()
                .build();
    }

    @Override
    public <T extends Game> GameManager<T> getGameManager() {
        return gameManager;
    }

    @Override
    public <T extends Game> GameBotManager<T> getBotManager() {
        return gameBotManager;
    }

    @Override
    public <T extends Game> TeamManager<T> getTeamManager() {
        return teamManager;
    }

    @Override
    public EntityDeathManager getEntityDeathManager() {
        return deathManager;
    }

    @Override
    public void populatePlayerList(World world) {
        world.playerEntities.forEach(player -> {
            UUID uuid = player.getUniqueID();
            this.getJoinedPlayers().add(uuid);
            this.playerMoneyMap.put(uuid, 800);
        });
    }

    @Nonnull
    @Override
    public BlueZone initializeZone(World world) {
        return new BlueZone(this.getGameData(world), ZoneSettings.Builder.create().setStatic().damage(20).setAlwaysCentered().build());
    }

    @Override
    public void onGameStart(World world) {

    }

    @Override
    public void onGameStopped(World world) {

    }

    @Override
    public void onGameObjectiveReached(World world, @Nullable Team team) {

    }

    private class TeamStats {
        public Team terroristTeam;
        public Team counterTerroristTeam;
        public int tWins;
        public int ctWins;

        public int getScore(Team team) {
            if(team.name.equals("t")) {
                return tWins;
            } else return ctWins;
        }

        public int getHighestScore() {
            return Math.max(tWins, ctWins);
        }

        @Nullable
        public Team getWinningTeam() {
            return tWins > ctWins ? terroristTeam : ctWins > tWins ? counterTerroristTeam : null;
        }
    }

    private class BombStats {

        private boolean isPlanted;
        private int ticksLeft = 800;
        private BlockPos plantPos;
    }
}
