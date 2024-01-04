package dev.toma.pubgmc.common.games.game.tournament;

public enum TournamentGameState {

    NEW,
    WAITING,
    STARTING,
    KILL_ROUND,
    CAPTURE_ROUND,
    END_ROUND,
    ENDING,
    GAME_FINISHED;

    public boolean isPlayState() {
        return this == STARTING || this == KILL_ROUND || this == CAPTURE_ROUND || this == END_ROUND || this == ENDING;
    }
}
