package dev.toma.pubgmc.api.games;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.GamePlayerData;
import dev.toma.pubgmc.api.objectives.ObjectiveReachScore;
import dev.toma.pubgmc.api.objectives.types.GameArea;
import dev.toma.pubgmc.api.settings.EntityDeathManager;
import dev.toma.pubgmc.api.settings.GameBotManager;
import dev.toma.pubgmc.api.settings.GameManager;
import dev.toma.pubgmc.api.settings.TeamManager;
import dev.toma.pubgmc.api.teams.Team;
import dev.toma.pubgmc.api.teams.TeamSettings;
import dev.toma.pubgmc.api.util.GameUtils;
import dev.toma.pubgmc.util.game.ZoneSettings;
import dev.toma.pubgmc.world.BlueZone;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class GameBombDefuse extends GameObjectiveBased {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Pubgmc.MOD_ID, "textures/gui/game.png");
    private static final int ROUND_TIME = 2600;

    private final GameManager gameManager;
    private final GameBotManager gameBotManager;
    private final TeamManager teamManager;
    private final EntityDeathManager deathManager;

    // TODO
    private final TeamStats teamStats = new TeamStats();
    private final int roundsLeft = 30;
    private BombStats bombStats;
    private GameArea ctSpawn, tSpawn;
    private GameArea bombsiteA, bombsiteB;

    public GameBombDefuse(String name) {
        super(name, TEXTURE);
        this.gameManager = GameManager.Builder.create(this)
                .waitTime(5)
                .objective(() -> new ObjectiveReachScore<>(this, team -> teamStats.getScore(team)).setRequiredScore(16))
                .verification(game -> game.roundsLeft <= 0 || game.teamStats.getHighestScore() >= 16)
                .build();
        this.gameBotManager = GameBotManager.Builder.create(this)
                .lootFactory(entityAIPlayer -> {
                })
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
    public boolean addObjective(World world, BlockPos pos, GameArea area) {
        if (area.getAreaType() == GameArea.Types.BOMBSITE) {
            if (bombsiteA == null) {
                bombsiteA = area;
                return true;
            } else if (bombsiteB == null) {
                bombsiteB = area;
                return true;
            }
        } else if (area.getAreaType() == GameArea.Types.CT_SPAWN && ctSpawn == null) {
            this.ctSpawn = area;
            return true;
        } else if (area.getAreaType() == GameArea.Types.T_SPAWN && tSpawn == null) {
            this.tSpawn = area;
            return true;
        }
        return false;
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
        for (EntityPlayer player : this.getOnlinePlayers(world)) {
            GamePlayerData data = this.getPlayerData().get(player.getUniqueID());
            if (data == null) continue;
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
        // TODO
    }

    @Nullable
    @Override
    public CommandException onGameStartCommandExecuted(ICommandSender sender, MinecraftServer server, String[] additionalArgs) {
        if (this.bombsiteA == null || this.bombsiteB == null) return new CommandException("You have to add bomsite!");
        if (this.ctSpawn == null) return new CommandException("You have to add CT Spawn");
        if (this.tSpawn == null) return new CommandException("You have to add T Spawn");
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
        while (players.hasNext()) {
            UUID uuid = players.next();
            Team team = this.getTeamList().get(t ? 1 : 0);
            if (!team.add(uuid)) {
                Pubgmc.logger.fatal("Couldn't add player with UUID {}, team is full!", uuid);
                return;
            }
            this.getPlayerData().put(uuid, new PlayerStats(team));
        }
    }

    @Override
    public void writeDataToNBT(NBTTagCompound compound) {
        super.writeDataToNBT(compound);
        if (this.ctSpawn != null) compound.setTag("ctSpawn", NBTUtil.createPosTag(this.ctSpawn.getCenter()));
        if (this.tSpawn != null) compound.setTag("tSpawn", NBTUtil.createPosTag(this.tSpawn.getCenter()));
        if (this.bombsiteA != null) compound.setTag("bombsiteA", NBTUtil.createPosTag(this.bombsiteA.getCenter()));
        if (this.bombsiteB != null) compound.setTag("bombsiteB", NBTUtil.createPosTag(this.bombsiteB.getCenter()));
    }

    @Override
    public void readDataFromNBT(NBTTagCompound compound) {
        super.readDataFromNBT(compound);
        this.ctSpawn = compound.hasKey("ctSpawn") ? this.getObjectives().get(NBTUtil.getPosFromTag(compound.getCompoundTag("ctSpawn"))) : null;
        this.tSpawn = compound.hasKey("tSpawn") ? this.getObjectives().get(NBTUtil.getPosFromTag(compound.getCompoundTag("tSpawn"))) : null;
        this.bombsiteA = compound.hasKey("bombsiteA") ? this.getObjectives().get(NBTUtil.getPosFromTag(compound.getCompoundTag("bombsiteA"))) : null;
        this.bombsiteB = compound.hasKey("bombsiteB") ? this.getObjectives().get(NBTUtil.getPosFromTag(compound.getCompoundTag("bombsiteB"))) : null;
    }

    private class TeamStats {

        public Team terroristTeam;
        public Team counterTerroristTeam;
        public int tWins;
        public int ctWins;

        public int getScore(Team team) {
            if (team.name.equals("t")) {
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

        private final int deaths = 0;
        private final int mvps = 0;

        public PlayerStats(final Team team) {
            super(team);
            this.setData(800);
        }

        public boolean canBuy(int price) {
            return this.getData() >= price;
        }

        public void addMoney(int amount) {
            this.addData(amount);
            if (this.getData() > 16000) this.setData(16000);
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
