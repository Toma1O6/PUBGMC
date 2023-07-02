package dev.toma.pubgmc.api.game.team;

import dev.toma.pubgmc.api.game.GameConfiguration;

public interface TeamGameConfiguration extends GameConfiguration {

    default boolean shouldShowTeamNameplates() {
        return true;
    }
}
