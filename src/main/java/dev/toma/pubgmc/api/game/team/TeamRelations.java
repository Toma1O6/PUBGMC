package dev.toma.pubgmc.api.game.team;

public enum TeamRelations {

    FRIENDLY,
    NEUTRAL,
    ENEMY,
    UNKNOWN;

    public boolean areNameplatesVisible() {
        return this == FRIENDLY;
    }
}
