package com.toma.pubgmc.api.interfaces;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.teams.Team;

public interface GameObjective<T extends Game> {

    EnumObjectiveType getType();

    boolean isObjectiveReached(Team team, T game);

    Team getWinner(T game);

    default boolean checkAllTeams() {
        return this.getType().checkAll;
    }

    enum EnumObjectiveType {

        LAST_TEAM_STANDING(false),
        SCORE(true);

        public boolean checkAll;

        EnumObjectiveType(final boolean checkAll) {
            this.checkAll = checkAll;
        }
    }
}
