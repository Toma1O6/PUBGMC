package dev.toma.pubgmc.common.games.game.tournament;

import dev.toma.pubgmc.api.data.DataReader;
import dev.toma.pubgmc.api.data.DataWriter;
import dev.toma.pubgmc.api.game.GameWorldConfiguration;
import dev.toma.pubgmc.api.game.team.TeamGameConfiguration;

public class TournamentGameConfiguration implements TeamGameConfiguration {

    public int teamSize = 2;
    public int requiredTeamCount = 5;
    public boolean allowPlacementMatchDraws = true;
    public int placementWinScore = 3;
    public int placementDrawScore = 1;
    public boolean hardcoreMode;
    public int matchWaitTime = 200;
    public int matchStartTime = 100;
    public int matchEndTime = 100;
    public String[] availableLoadouts = {};
    public MatchConfiguration placementRoundConfig = MatchConfiguration.DEFAULT_PLACEMENT;
    public MatchConfiguration finalRoundConfig = MatchConfiguration.DEFAULT_FINAL;
    public GameWorldConfiguration worldConfiguration = new GameWorldConfiguration();

    @Override
    public boolean shouldShowTeamNameplates() {
        return !hardcoreMode;
    }

    @Override
    public void performCorrections() {
        this.teamSize = Math.max(1, teamSize);
        this.requiredTeamCount = Math.max(2, requiredTeamCount);
        this.placementWinScore = Math.max(1, placementWinScore);
        this.placementDrawScore = Math.max(0, placementDrawScore);
        this.matchWaitTime = Math.max(100, matchWaitTime);
        this.matchStartTime = Math.max(100, matchStartTime);
        this.matchEndTime = Math.max(100, matchEndTime);
        this.placementRoundConfig.correct();
        this.finalRoundConfig.correct();
        this.worldConfiguration.correct();
    }

    public void serialize(DataWriter<?> writer) {
        writer.writeInt("teamSize", teamSize);
        writer.writeInt("requiredTeamCount", requiredTeamCount);
        writer.writeBoolean("allowPlacementMatchDraws", allowPlacementMatchDraws);
        writer.writeInt("placementWinScore", placementWinScore);
        writer.writeInt("placementDrawScore", placementDrawScore);
        writer.writeBoolean("hardcoreMode", hardcoreMode);
        writer.writeInt("matchWaitTime", matchWaitTime);
        writer.writeInt("matchStartTime", matchStartTime);
        writer.writeInt("matchEndTime", matchEndTime);
        writer.writeStringArray("defaultLoadouts", availableLoadouts);
        writer.write("placementRound", placementRoundConfig, MatchConfiguration::serialize);
        writer.write("finalRound", finalRoundConfig, MatchConfiguration::serialize);
        writer.write("worldConfig", worldConfiguration, GameWorldConfiguration::serialize);
    }

    public static TournamentGameConfiguration deserialize(DataReader<?> reader) {
        TournamentGameConfiguration cfg = new TournamentGameConfiguration();
        cfg.teamSize = reader.readInt("teamSize", cfg.teamSize);
        cfg.requiredTeamCount = reader.readInt("requiredTeamCount", cfg.requiredTeamCount);
        cfg.allowPlacementMatchDraws = reader.readBoolean("allowPlacementMatchDraws", cfg.allowPlacementMatchDraws);
        cfg.placementWinScore = reader.readInt("placementWinScore", cfg.placementWinScore);
        cfg.placementDrawScore = reader.readInt("placementDrawScore", cfg.placementDrawScore);
        cfg.hardcoreMode = reader.readBoolean("hardcoreMode", cfg.hardcoreMode);
        cfg.matchWaitTime = reader.readInt("matchWaitTime", cfg.matchWaitTime);
        cfg.matchStartTime = reader.readInt("matchStartTime", cfg.matchStartTime);
        cfg.matchEndTime = reader.readInt("matchEndTime", cfg.matchEndTime);
        cfg.availableLoadouts = reader.readStringArray("defaultLoadouts", cfg.availableLoadouts);
        cfg.placementRoundConfig = reader.read("placementRound", MatchConfiguration::deserialize, cfg.placementRoundConfig);
        cfg.finalRoundConfig = reader.read("finalRound", MatchConfiguration::deserialize, cfg.finalRoundConfig);
        cfg.worldConfiguration = reader.read("worldConfig", GameWorldConfiguration::deserialize, cfg.worldConfiguration);
        return cfg;
    }

    public static final class MatchConfiguration {

        public static final MatchConfiguration DEFAULT_PLACEMENT = new MatchConfiguration(3, 2400, 2400, 200, 1, 20);
        public static final MatchConfiguration DEFAULT_FINAL = new MatchConfiguration(5, 3600, 2400, 200, 1, 20);

        public int matchCount;
        public int killRoundDuration;
        public int captureRoundDuration;
        public int captureTime;
        public int endOfRoundDamage;
        public int endOfRoundDamageInterval;

        public MatchConfiguration(int matchCount, int killRoundDuration, int captureRoundDuration, int captureTime, int endOfRoundDamage, int endOfRoundDamageInterval) {
            this.matchCount = matchCount;
            this.killRoundDuration = killRoundDuration;
            this.captureRoundDuration = captureRoundDuration;
            this.captureTime = captureTime;
            this.endOfRoundDamage = endOfRoundDamage;
            this.endOfRoundDamageInterval = endOfRoundDamageInterval;
        }

        public void correct() {
            this.matchCount = Math.max(1, matchCount);
            this.killRoundDuration = Math.max(1200, killRoundDuration);
            this.captureRoundDuration = Math.max(0, captureRoundDuration);
            this.captureTime = captureRoundDuration > 0 ? Math.max(100, captureTime) : captureTime;
            this.endOfRoundDamage = Math.max(0, endOfRoundDamage);
            this.endOfRoundDamageInterval = Math.max(0, endOfRoundDamageInterval);
        }

        public void serialize(DataWriter<?> writer) {
            writer.writeInt("matchCount", matchCount);
            writer.writeInt("killRoundDuration", killRoundDuration);
            writer.writeInt("captureRoundDuration", captureRoundDuration);
            writer.writeInt("captureTime", captureTime);
            writer.writeInt("endOfRoundDamage", endOfRoundDamage);
            writer.writeInt("endOfRoundDamageInterval", endOfRoundDamageInterval);
        }

        public static MatchConfiguration deserialize(DataReader<?> reader) {
            int matchCount = reader.readInt("matchCount");
            int killRoundDuration = reader.readInt("killRoundDuration");
            int captureRoundDuration = reader.readInt("captureRoundDuration");
            int captureTime = reader.readInt("captureTime");
            int endOfRoundDamage = reader.readInt("endOfRoundDamage");
            int endOfRoundDamageInterval = reader.readInt("endOfRoundDamageInterval");
            return new MatchConfiguration(matchCount, killRoundDuration, captureRoundDuration, captureTime, endOfRoundDamage, endOfRoundDamageInterval);
        }
    }
}
