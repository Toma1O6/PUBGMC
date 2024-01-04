package dev.toma.pubgmc.common.games.game.tournament;

import dev.toma.pubgmc.common.games.util.TeamType;

public enum TournamentMatchStatus {

    RED_WIN,
    BLUE_WIN,
    DRAW,
    WAITING,
    PLAYING,
    CANCELLED,
    ENDED;

    public boolean isFinalState() {
        return this == RED_WIN || this == BLUE_WIN || this == DRAW;
    }

    /*
    Draw/other = null
     */
    public TeamType getWinningTeam() {
        return this == RED_WIN ? TeamType.RED : this == BLUE_WIN ? TeamType.BLUE : null;
    }

    public static TournamentMatchStatus getWinStatusByTeamType(TeamType teamType) {
        return teamType == TeamType.RED ? RED_WIN : BLUE_WIN;
    }
}
