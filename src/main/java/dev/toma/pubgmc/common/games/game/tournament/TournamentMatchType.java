package dev.toma.pubgmc.common.games.game.tournament;

import java.util.function.Function;

public enum TournamentMatchType {

    PLACEMENT(t -> t.placementRoundConfig),
    QUALIFICATION(t -> t.qualificationRoundConfig),
    FINAL(t -> t.finalRoundConfig);

    private final Function<TournamentGameConfiguration, TournamentGameConfiguration.MatchConfiguration> matchConfigurationFunction;

    TournamentMatchType(Function<TournamentGameConfiguration, TournamentGameConfiguration.MatchConfiguration> matchConfigurationFunction) {
        this.matchConfigurationFunction = matchConfigurationFunction;
    }

    public TournamentGameConfiguration.MatchConfiguration getMatchConfig(TournamentGameConfiguration configuration) {
        return matchConfigurationFunction.apply(configuration);
    }
}
