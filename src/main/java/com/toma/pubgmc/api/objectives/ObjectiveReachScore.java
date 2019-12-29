package com.toma.pubgmc.api.objectives;

import com.toma.pubgmc.api.games.Game;
import com.toma.pubgmc.api.interfaces.GameObjective;
import com.toma.pubgmc.api.teams.Team;

import java.util.function.Function;

public class ObjectiveReachScore<T extends Game> implements GameObjective<T> {

    public final T game;
    public final Function<Team, Integer> scoreGetter;
    protected int winningScore;

    public ObjectiveReachScore(T game, Function<Team, Integer> scoreGetter) {
        this.game = game;
        this.scoreGetter = scoreGetter;
        this.winningScore = 10;
    }

    public ObjectiveReachScore<T> setRequiredScore(final int score) {
        this.winningScore = score;
        return this;
    }

    public int getWinningScore() {
        return winningScore;
    }

    @Override
    public EnumObjectiveType getType() {
        return EnumObjectiveType.SCORE;
    }

    @Override
    public boolean isObjectiveReached(Team team, T game) {
        return this.scoreGetter.apply(team) >= this.winningScore;
    }

    @Override
    public Team getWinner(T game) {
        for(Team team : game.getTeamList()) {
            if(this.winningScore <= this.scoreGetter.apply(team)) {
                return team;
            }
        }
        return null;
    }
}
