package dev.toma.pubgmc.common.games.game.tournament;

import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TournamentMatch {

    private final Team redTeam;
    private final Team blueTeam;
    private final TournamentMatchType matchType;

    private TournamentMatchStatus matchStatus;
    private int roundNumber;
    private final List<TournamentMatchStatus> matchHistory = new ArrayList<>();

    private FinishCallback callback;

    public TournamentMatch(Team redTeam, Team blueTeam, TournamentMatchType matchType) {
        this.redTeam = redTeam;
        this.blueTeam = blueTeam;
        this.matchStatus = TournamentMatchStatus.WAITING;
        this.matchType = matchType;
    }

    public void setCallback(FinishCallback callback) {
        this.callback = callback;
    }

    public void completeRound() {
        this.matchHistory.add(matchStatus);
        ++this.roundNumber;
    }

    public boolean isMatchParticipant(Entity entity) {
        UUID uuid = entity.getUniqueID();
        return redTeam.isMember(uuid) || blueTeam.isMember(uuid);
    }

    public boolean containsTeam(Team team) {
        return redTeam.equals(team) || blueTeam.equals(team);
    }

    public TournamentMatchType getMatchType() {
        return matchType;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public List<TournamentMatchStatus> getMatchHistory() {
        return matchHistory;
    }

    public TournamentMatchStatus getFinalStatus(TournamentGameConfiguration configuration) {
        if (!isCompleted(configuration)) {
            throw new UnsupportedOperationException("Cannot obtain final match status for incomplete match");
        }
        int red = getTeamPoints(TeamType.RED, configuration);
        int blue = getTeamPoints(TeamType.BLUE, configuration);
        return red == blue ? TournamentMatchStatus.DRAW : red > blue ? TournamentMatchStatus.RED_WIN : TournamentMatchStatus.BLUE_WIN;
    }

    public boolean isCompleted(TournamentGameConfiguration cfg) {
        TournamentGameConfiguration.MatchConfiguration configuration = matchType.getMatchConfig(cfg);
        int matchLimit = configuration.matchCount;
        int remainingMatchCount = matchLimit - roundNumber;
        if (remainingMatchCount <= 0) {
            return true;
        }
        int availableScorePool = remainingMatchCount * cfg.winScore;
        int redPoints = getTeamPoints(TeamType.RED, cfg);
        int bluePoints = getTeamPoints(TeamType.BLUE, cfg);
        return redPoints > bluePoints + availableScorePool || bluePoints > redPoints + availableScorePool;
    }

    public int getTeamPoints(TeamType type, TournamentGameConfiguration configuration) {
        TournamentMatchStatus winStatus = type == TeamType.RED ? TournamentMatchStatus.RED_WIN : TournamentMatchStatus.BLUE_WIN;
        return matchHistory.stream()
                .mapToInt(status -> status == winStatus ? configuration.winScore : status == TournamentMatchStatus.DRAW ? configuration.drawScore : 0)
                .sum();
    }

    public int getMatchesByStatus(TournamentMatchStatus status) {
        return (int) matchHistory.stream().filter(s -> s == status).count();
    }

    public Team getTeam(TeamType type) {
        return type == TeamType.RED ? redTeam : blueTeam;
    }

    public void setMatchStatus(World world, TournamentMatchStatus matchStatus) {
        this.matchStatus = matchStatus;
        if (matchStatus.isFinalState() && callback != null) {
            this.callback.onMatchFinished(world, this);
        }
    }

    public TournamentMatchStatus getMatchStatus() {
        return matchStatus;
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setUniqueId("red", redTeam.getTeamId());
        nbt.setUniqueId("blue", blueTeam.getTeamId());
        nbt.setInteger("status", matchStatus.ordinal());
        nbt.setInteger("type", matchType.ordinal());
        nbt.setInteger("roundNumber", roundNumber);
        nbt.setTag("history", SerializationHelper.collectionToNbt(matchHistory, t -> new NBTTagInt(t.ordinal())));
        return nbt;
    }

    public static TournamentMatch deserialize(NBTTagCompound nbt, TeamManager manager) {
        UUID redTeamId = nbt.getUniqueId("red");
        UUID blueTeamId = nbt.getUniqueId("blue");
        TournamentMatchStatus status = TournamentMatchStatus.values()[nbt.getInteger("status")];
        TournamentMatchType matchType = TournamentMatchType.values()[nbt.getInteger("type")];
        TournamentMatch match = new TournamentMatch(manager.getTeamById(redTeamId), manager.getTeamById(blueTeamId), matchType);
        match.matchStatus = status;
        match.roundNumber = nbt.getInteger("roundNumber");
        SerializationHelper.collectionFromNbt(match.matchHistory, nbt.getTagList("history", Constants.NBT.TAG_INT), t -> TournamentMatchStatus.values()[((NBTTagInt) t).getInt()]);
        return match;
    }

    @FunctionalInterface
    public interface FinishCallback {
        void onMatchFinished(World world, TournamentMatch match);
    }
}
