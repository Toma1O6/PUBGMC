package com.toma.pubgmc.api.objectives;

import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.interfaces.GameObjective;
import com.toma.pubgmc.api.teams.Team;

public class ObjectiveLastTeamStanding<T extends Game> implements GameObjective<T> {

    public final T game;

    public ObjectiveLastTeamStanding(T game) {
        this.game = game;
    }

    @Override
    public EnumObjectiveType getType() {
        return EnumObjectiveType.LAST_TEAM_STANDING;
    }

    @Override
    public boolean isObjectiveReached(Team team, T game) {
        return game.getTeamList().size() <= 1;
    }

    @Override
    public Team getWinner(T game) {
        return game.getTeamList().get(0);
    }
}
