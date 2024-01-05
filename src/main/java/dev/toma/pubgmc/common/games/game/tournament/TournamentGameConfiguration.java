package dev.toma.pubgmc.common.games.game.tournament;

import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import dev.toma.pubgmc.api.game.GameWorldConfiguration;
import dev.toma.pubgmc.api.game.team.TeamGameConfiguration;

public class TournamentGameConfiguration implements TeamGameConfiguration {

    public static final String LOADOUT_DEAGLE = "tournament/deagle";
    public static final String LOADOUT_CQB = "tournament/cqb";
    public static final String LOADOUT_TANK = "tournament/tank";
    public static final String LOADOUT_ASSAULT = "tournament/assault";
    public static final String LOADOUT_ASSAULT2 = "tournament/assault2";
    public static final String LOADOUT_MARKSMAN = "tournament/marksman";
    public static final String LOADOUT_SNIPER = "tournament/sniper";

    public static final String LOADOUT_DEATH_SQUAD = "tournament/deathsquad_ai";

    public int teamSize = 2;
    public int requiredTeamCount = 5;
    public boolean allowPlacementMatchDraws = true;
    public int winScore = 3;
    public int drawScore = 1;
    public boolean hardcoreMode;
    public int loadoutSelectTime = 1200;
    public int matchWaitTime = 200;
    public int matchStartTime = 100;
    public int matchEndTime = 100;
    public String[] availableLoadouts = {
            LOADOUT_DEAGLE, LOADOUT_CQB, LOADOUT_TANK, LOADOUT_ASSAULT, LOADOUT_ASSAULT2, LOADOUT_MARKSMAN, LOADOUT_SNIPER
    };
    public String deathSquadEntityLoadout = LOADOUT_DEATH_SQUAD;
    public MatchConfiguration placementRoundConfig = MatchConfiguration.DEFAULT_PLACEMENT;
    public MatchConfiguration qualificationRoundConfig = MatchConfiguration.DEFAULT_QUALIFICATION;
    public MatchConfiguration finalRoundConfig = MatchConfiguration.DEFAULT_FINAL;
    public GameWorldConfiguration worldConfiguration = new GameWorldConfiguration();

    @Override
    public boolean shouldShowTeamNameplates() {
        return !hardcoreMode;
    }

    public boolean hasAiSpawns() {
        return placementRoundConfig.hasAiEnabled() || qualificationRoundConfig.hasAiEnabled() || finalRoundConfig.hasAiEnabled();
    }

    @Override
    public void performCorrections() {
        this.teamSize = Math.max(1, teamSize);
        this.requiredTeamCount = Math.max(2, requiredTeamCount);
        this.winScore = Math.max(1, winScore);
        this.drawScore = Math.max(0, drawScore);
        this.matchWaitTime = Math.max(100, matchWaitTime);
        this.matchStartTime = Math.max(100, matchStartTime);
        this.matchEndTime = Math.max(100, matchEndTime);
        this.placementRoundConfig.correct();
        this.qualificationRoundConfig.correct();
        this.finalRoundConfig.correct();
        this.worldConfiguration.correct();
    }

    public void serialize(DataWriter<?> writer) {
        writer.writeInt("teamSize", teamSize);
        writer.writeInt("requiredTeamCount", requiredTeamCount);
        writer.writeBoolean("allowPlacementMatchDraws", allowPlacementMatchDraws);
        writer.writeInt("winScore", winScore);
        writer.writeInt("drawScore", drawScore);
        writer.writeBoolean("hardcoreMode", hardcoreMode);
        writer.writeInt("matchWaitTime", matchWaitTime);
        writer.writeInt("matchStartTime", matchStartTime);
        writer.writeInt("matchEndTime", matchEndTime);
        writer.writeStringArray("defaultLoadouts", availableLoadouts);
        writer.writeString("deathSquadAiLoadout", deathSquadEntityLoadout);
        writer.write("placementRound", placementRoundConfig, MatchConfiguration::serialize);
        writer.write("qualificationRound", qualificationRoundConfig, MatchConfiguration::serialize);
        writer.write("finalRound", finalRoundConfig, MatchConfiguration::serialize);
        writer.write("worldConfig", worldConfiguration, GameWorldConfiguration::serialize);
    }

    public static TournamentGameConfiguration deserialize(DataReader<?> reader) {
        TournamentGameConfiguration cfg = new TournamentGameConfiguration();
        cfg.teamSize = reader.readInt("teamSize", cfg.teamSize);
        cfg.requiredTeamCount = reader.readInt("requiredTeamCount", cfg.requiredTeamCount);
        cfg.allowPlacementMatchDraws = reader.readBoolean("allowPlacementMatchDraws", cfg.allowPlacementMatchDraws);
        cfg.winScore = reader.readInt("winScore", cfg.winScore);
        cfg.drawScore = reader.readInt("drawScore", cfg.drawScore);
        cfg.hardcoreMode = reader.readBoolean("hardcoreMode", cfg.hardcoreMode);
        cfg.matchWaitTime = reader.readInt("matchWaitTime", cfg.matchWaitTime);
        cfg.matchStartTime = reader.readInt("matchStartTime", cfg.matchStartTime);
        cfg.matchEndTime = reader.readInt("matchEndTime", cfg.matchEndTime);
        cfg.availableLoadouts = reader.readStringArray("defaultLoadouts", cfg.availableLoadouts);
        cfg.deathSquadEntityLoadout = reader.readString("deathSquadAiLoadout", cfg.deathSquadEntityLoadout);
        cfg.placementRoundConfig = reader.read("placementRound", MatchConfiguration::deserialize, cfg.placementRoundConfig);
        cfg.qualificationRoundConfig = reader.read("qualificationRound", MatchConfiguration::deserialize, cfg.qualificationRoundConfig);
        cfg.finalRoundConfig = reader.read("finalRound", MatchConfiguration::deserialize, cfg.finalRoundConfig);
        cfg.worldConfiguration = reader.read("worldConfig", GameWorldConfiguration::deserialize, cfg.worldConfiguration);
        return cfg;
    }

    public static final class MatchConfiguration {

        public static final MatchConfiguration DEFAULT_PLACEMENT = new MatchConfiguration(3, 2400, 2400, 200, false, 0, 0, 0);
        public static final MatchConfiguration DEFAULT_QUALIFICATION = new MatchConfiguration(1, 2400, 2400, 200, true, 600, 1, 50);
        public static final MatchConfiguration DEFAULT_FINAL = new MatchConfiguration(5, 3600, 2400, 200, true, 600, 1, 40);

        public int matchCount;
        public int killRoundDuration;
        public int captureRoundDuration;
        public int captureTime;
        public boolean endRound;
        public int endRoundAiSpawnInterval;
        public int endOfRoundDamage;
        public int endOfRoundDamageInterval;

        public MatchConfiguration(int matchCount, int killRoundDuration, int captureRoundDuration, int captureTime, boolean endRound, int endRoundAiSpawnInterval, int endOfRoundDamage, int endOfRoundDamageInterval) {
            this.matchCount = matchCount;
            this.killRoundDuration = killRoundDuration;
            this.captureRoundDuration = captureRoundDuration;
            this.captureTime = captureTime;
            this.endRound = endRound;
            this.endRoundAiSpawnInterval = endRoundAiSpawnInterval;
            this.endOfRoundDamage = endOfRoundDamage;
            this.endOfRoundDamageInterval = endOfRoundDamageInterval;
        }

        public void correct() {
            this.matchCount = Math.max(1, matchCount);
            // TODO
            //this.killRoundDuration = Math.max(1200, killRoundDuration);
            this.captureRoundDuration = Math.max(0, captureRoundDuration);
            this.captureTime = captureRoundDuration > 0 ? Math.max(100, captureTime) : captureTime;
            this.endRoundAiSpawnInterval = Math.max(0, endOfRoundDamageInterval);
            this.endOfRoundDamage = Math.max(0, endOfRoundDamage);
            this.endOfRoundDamageInterval = Math.max(0, endOfRoundDamageInterval);
        }

        public boolean hasAiEnabled() {
            return endRound && endRoundAiSpawnInterval > 0;
        }

        public void serialize(DataWriter<?> writer) {
            writer.writeInt("matchCount", matchCount);
            writer.writeInt("killRoundDuration", killRoundDuration);
            writer.writeInt("captureRoundDuration", captureRoundDuration);
            writer.writeInt("captureTime", captureTime);
            writer.writeBoolean("allowEndRound", endRound);
            writer.writeInt("endRoundAiSpawnInterval", endRoundAiSpawnInterval);
            writer.writeInt("endOfRoundDamage", endOfRoundDamage);
            writer.writeInt("endOfRoundDamageInterval", endOfRoundDamageInterval);
        }

        public static MatchConfiguration deserialize(DataReader<?> reader) {
            int matchCount = reader.readInt("matchCount");
            int killRoundDuration = reader.readInt("killRoundDuration");
            int captureRoundDuration = reader.readInt("captureRoundDuration");
            int captureTime = reader.readInt("captureTime");
            boolean allowEndRound = reader.readBoolean("allowEndRound");
            int endRoundAiSpawnInterval = reader.readInt("endRoundAiSpawnInterval");
            int endOfRoundDamage = reader.readInt("endOfRoundDamage");
            int endOfRoundDamageInterval = reader.readInt("endOfRoundDamageInterval");
            return new MatchConfiguration(matchCount, killRoundDuration, captureRoundDuration, captureTime, allowEndRound, endRoundAiSpawnInterval, endOfRoundDamage, endOfRoundDamageInterval);
        }
    }
}
