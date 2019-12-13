package com.toma.pubgmc.api.games;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.GameObjectiveBased;
import com.toma.pubgmc.api.GamePlayerData;
import com.toma.pubgmc.api.objectives.ObjectiveReachScore;
import com.toma.pubgmc.api.objectives.types.GameArea;
import com.toma.pubgmc.api.settings.EntityDeathManager;
import com.toma.pubgmc.api.settings.GameBotManager;
import com.toma.pubgmc.api.settings.GameManager;
import com.toma.pubgmc.api.settings.TeamManager;
import com.toma.pubgmc.api.teams.Team;
import com.toma.pubgmc.api.teams.TeamSettings;
import com.toma.pubgmc.api.util.GameUtils;
import com.toma.pubgmc.util.game.ZoneSettings;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class GameBombDefuse extends GameObjectiveBased {

    private static final int ROUND_TIME = 2600;

    private final GameManager gameManager;
    private final GameBotManager gameBotManager;
    private final TeamManager teamManager;
    private final EntityDeathManager deathManager;

    // TODO
    private TeamStats teamStats = new TeamStats();
    private int roundsLeft = 30;
    private BombStats bombStats;
    private GameArea ctSpawn, tSpawn;
    private GameArea bombsiteA, bombsiteB;

    public GameBombDefuse(String name) {
        super(name);
        this.gameManager = GameManager.Builder.create(this)
                .waitTime(5)
                .objective(() -> new ObjectiveReachScore<>(this, team -> teamStats.getScore(team)).setRequiredScore(16))
                .verification(game -> game.roundsLeft <= 0 || game.teamStats.getHighestScore() >= 16)
                .build();
        this.gameBotManager = GameBotManager.Builder.create(this)
                .lootFactory(entityAIPlayer -> {})
                .spawnValidator(game -> true)
                .addBotLogic(GameUtils::addBaseTasks)
                .build();
        this.teamManager = TeamManager.Builder.create(this)
                .settings(new TeamSettings(5, true, false))
                .creator(this::createTeams)
                .fillFactory(this::getTeamFillFactory)
                .build();
        this.deathManager = EntityDeathManager.Builder.create()
                .build();
    }

    @Override
    public GameManager<Game> getGameManager() {
        return gameManager;
    }

    @Override
    public GameBotManager<Game> getBotManager() {
        return gameBotManager;
    }

    @Override
    public TeamManager<Game> getTeamManager() {
        return teamManager;
    }

    @Override
    public EntityDeathManager getEntityDeathManager() {
        return deathManager;
    }

    @Override
    public void populatePlayerList(World world) {
        world.playerEntities.forEach(player -> this.getJoinedPlayers().add(player.getUniqueID()));
    }

    @Override
    public boolean canAddObjective(BlockPos pos, GameArea objective) {
        return true;
    }

    @Nonnull
    @Override
    public BlueZone initializeZone(World world) {
        return new BlueZone(this.getGameData(world), ZoneSettings.Builder.create().setStatic().damage(15).setAlwaysCentered().build());
    }

    @Override
    public void onGameTick(World world) {
        super.onGameTick(world);
    }

    @Override
    public void onGameStart(World world) {
        for(EntityPlayer player : this.getOnlinePlayers(world)) {
            GamePlayerData data = this.getPlayerData().get(player.getUniqueID());
            if(data == null) continue;
            boolean t = data.getTeam().name.equals("t");
            GameArea area = t ? tSpawn : ctSpawn;
            BlockPos pos = area.getRandomPos(world);
            player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
        }
    }

    @Override
    public void onGameStopped(World world) {

    }

    @Override
    public void onGameObjectiveReached(World world, @Nullable Team team) {

    }

    @Nullable
    @Override
    public CommandException onGameStartCommandExecuted(ICommandSender sender, MinecraftServer server, String[] additionalArgs) {
        boolean[] objectives = new boolean[4];
        for(Map.Entry<BlockPos, GameArea> entry : this.getObjectives().entrySet()) {
            if(entry.getValue().getAreaType() == GameArea.Types.BOMBSITE) {
                if(!objectives[0]) {
                    objectives[0] = true;
                    this.bombsiteA = entry.getValue();
                } else if(!objectives[1]) {
                    objectives[1] = true;
                    this.bombsiteB = entry.getValue();
                } else {
                    return new CommandException("You cannot have more than 2 bombsites!");
                }
            } else if(entry.getValue().getAreaType() == GameArea.Types.BD_SPAWN) {
                if(!objectives[2]) {
                    objectives[2] = true;
                    this.ctSpawn = entry.getValue();
                } else if(!objectives[3]) {
                    objectives[3] = true;
                    this.tSpawn = entry.getValue();
                } else {
                    return new CommandException("You cannot have more than 2 spawn areas!");
                }
            }
        }
        return null;
    }

    @Override
    public boolean respawnPlayer(EntityPlayer player) {
        return true;
    }

    private List<Team> createTeams(GameBombDefuse game) {
        List<Team> teams = new ArrayList<>(2);
        teams.add(new Team(5, 0x008888, "ct"));
        teams.add(new Team(5, 0xAAAA00, "t"));
        return teams;
    }

    private void getTeamFillFactory(Iterator<UUID> players, Iterator<Team> teams, GameBombDefuse game) {
        boolean t = Pubgmc.rng().nextBoolean();
        while(players.hasNext()) {
            UUID uuid = players.next();
            Team team = this.getTeamList().get(t ? 1 : 0);
            if(!team.add(uuid)) {
                Pubgmc.logger.fatal("Couldn't add player with UUID {}, team is full!", uuid);
                return;
            }
            this.getPlayerData().put(uuid, new PlayerStats(team));
        }
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

        public void plantBomb(final BlockPos pos) {
            this.plantPos = pos;
            this.isPlanted = true;
        }

        public BlockPos getPlantPos() {
            return plantPos;
        }

        public void tickBomb() {
            --this.ticksLeft;
        }

        public int getTicksLeft() {
            return ticksLeft;
        }

        public boolean isPlanted() {
            return isPlanted;
        }
    }

    private class PlayerStats extends GamePlayerData {

        private int deaths = 0;
        private int mvps = 0;

        public PlayerStats(final Team team) {
            super(team);
            this.setData(800);
        }

        public boolean canBuy(int price) {
            return this.getData() >= price;
        }

        public void addMoney(int amount) {
            this.addData(amount);
            if(this.getData() > 16000) this.setData(16000);
        }

        public void removeMoney(int amount) {
            this.addMoney(-amount);
        }

        public int getDeaths() {
            return deaths;
        }

        public int getMvps() {
            return mvps;
        }
    }
}
