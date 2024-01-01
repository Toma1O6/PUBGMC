package dev.toma.pubgmc.common.games.game.tournament;

import dev.toma.pubgmc.api.game.team.TeamManager;
import dev.toma.pubgmc.api.game.util.Team;
import dev.toma.pubgmc.common.games.util.TeamType;
import dev.toma.pubgmc.data.serialization.NbtSerializer;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TournamentMatch {

    private final Team redTeam;
    private final Team blueTeam;
    private final boolean placementMatch;

    private TournamentMatchStatus matchStatus;
    private int roundNumber;
    private final List<TournamentMatchStatus> matchHistory = new ArrayList<>();

    public TournamentMatch(Team redTeam, Team blueTeam, boolean placementMatch) {
        this.redTeam = redTeam;
        this.blueTeam = blueTeam;
        this.matchStatus = TournamentMatchStatus.WAITING;
        this.placementMatch = placementMatch;
    }

    public boolean isMatchParticipant(Entity entity) {
        UUID uuid = entity.getUniqueID();
        return redTeam.isMember(uuid) || blueTeam.isMember(uuid);
    }

    public boolean isPlacementMatch() {
        return placementMatch;
    }

    public boolean isCompleted(TournamentGameConfiguration cfg, TournamentGameConfiguration.MatchConfiguration configuration) {
        int matchLimit = configuration.matchCount;
        int remainingMatchCount = matchLimit - roundNumber;
        if (remainingMatchCount == 0) {
            return true;
        }
        int availableScorePool = remainingMatchCount * cfg.placementWinScore;
        int redPoints = getTeamPoints(TeamType.RED, cfg);
        int bluePoints = getTeamPoints(TeamType.BLUE, cfg);
        return redPoints > bluePoints + availableScorePool || bluePoints > redPoints + availableScorePool;
    }

    public int getTeamPoints(TeamType type, TournamentGameConfiguration configuration) {
        TournamentMatchStatus winStatus = type == TeamType.RED ? TournamentMatchStatus.RED_WIN : TournamentMatchStatus.BLUE_WIN;
        return matchHistory.stream()
                .mapToInt(status -> status == winStatus ? configuration.placementWinScore : status == TournamentMatchStatus.DRAW ? configuration.placementDrawScore : 0)
                .sum();
    }

    public int getMatchesByStatus(TournamentMatchStatus status) {
        return (int) matchHistory.stream().filter(s -> s == status).count();
    }

    public Team getTeam(TeamType type) {
        return type == TeamType.RED ? redTeam : blueTeam;
    }

    public void setMatchStatus(TournamentMatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    public TournamentMatchStatus getMatchStatus() {
        return matchStatus;
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setUniqueId("red", redTeam.getTeamId());
        nbt.setUniqueId("blue", blueTeam.getTeamId());
        nbt.setInteger("status", matchStatus.ordinal());
        nbt.setBoolean("placement", placementMatch);
        nbt.setInteger("roundNumber", roundNumber);
        nbt.setTag("history", SerializationHelper.collectionToNbt(matchHistory, t -> new NBTTagInt(t.ordinal())));
        return nbt;
    }

    public static TournamentMatch deserialize(NBTTagCompound nbt, TeamManager manager) {
        UUID redTeamId = nbt.getUniqueId("red");
        UUID blueTeamId = nbt.getUniqueId("blue");
        TournamentMatchStatus status = TournamentMatchStatus.values()[nbt.getInteger("status")];
        boolean placementMatch = nbt.getBoolean("placement");
        TournamentMatch match = new TournamentMatch(manager.getTeamById(redTeamId), manager.getTeamById(blueTeamId), placementMatch);
        match.setMatchStatus(status);
        SerializationHelper.collectionFromNbt(match.matchHistory, nbt.getTagList("history", Constants.NBT.TAG_INT), t -> TournamentMatchStatus.values()[((NBTTagInt) t).getInt()]);
        return match;
    }
}
