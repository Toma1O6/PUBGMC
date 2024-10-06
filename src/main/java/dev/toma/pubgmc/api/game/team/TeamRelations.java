package dev.toma.pubgmc.api.game.team;

public enum TeamRelations {

    FRIENDLY,
    NEUTRAL,
    ENEMY,
    UNKNOWN;

    public boolean areNameplatesVisible() {
        return this == FRIENDLY;
    }

    public boolean isDefaultAttackable() {
        return this == UNKNOWN || this == ENEMY;
    }

    public boolean isFriendlyOrEnemy() {
        return this == FRIENDLY || this == ENEMY;
    }
}
