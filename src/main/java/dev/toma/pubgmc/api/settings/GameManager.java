package dev.toma.pubgmc.api.settings;

import com.google.common.base.Preconditions;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.games.Game;
import dev.toma.pubgmc.api.interfaces.GameObjective;
import dev.toma.pubgmc.api.teams.Team;

import javax.annotation.Nullable;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GameManager<T extends Game> {

    private final int startPhaseLength;
    private final Supplier<GameObjective<T>> gameObjective;
    public boolean isSinglePlayerGame;
    public final Predicate<T> gameStopVerification;

    private GameManager(Builder<T> builder) {
        this.startPhaseLength = builder.wait;
        this.gameObjective = builder.objective;
        this.gameStopVerification = builder.verification;
    }

    public int getStartPhaseLength() {
        return startPhaseLength;
    }

    public Supplier<GameObjective<T>> gameObjective() {
        return gameObjective;
    }

    public Predicate<T> getVerification() {
        return gameStopVerification;
    }

    public boolean shouldStopGame(T game) {
        GameObjective<T> objective = this.gameObjective().get();
        boolean checkAll = objective.checkAllTeams();
        if(checkAll) {
            boolean endGame = false;
            for(Team team : game.getTeamList()) {
                if(objective.isObjectiveReached(team, game)) {
                    endGame = true;
                    break;
                }
            }
            return endGame;
        } else {
            if(game.getTeamList().isEmpty()) {
                return true;
            }
            Team team = game.getTeamList().get(Pubgmc.rng().nextInt(game.getTeamList().size()));
            return objective.isObjectiveReached(team, game);
        }
    }

    @Nullable
    public Team getWinningTeam(T game) {
        switch (this.gameObjective().get().getType()) {
            case LAST_TEAM_STANDING: {
                return game.getTeamList().get(0);
            }
            case SCORE: {
                for (Team t : game.getTeamList()) {
                    if (this.gameObjective().get().isObjectiveReached(t, game)) {
                        return t;
                    }
                }
                return null;
            }
            default: return null;
        }
    }

    public static class Builder<T extends Game> {

        private int wait;
        private Supplier<GameObjective<T>> objective;
        private Predicate<T> verification;

        public Builder() {
        }

        public static <E extends Game> Builder<E> create(E game) {
            return new Builder<>();
        }

        public Builder<T> waitTime(final int waitTime) {
            this.wait = waitTime;
            return this;
        }

        public Builder<T> objective(final Supplier<GameObjective<T>> objective) {
            this.objective = objective;
            return this;
        }

        public Builder<T> verification(final Predicate<T> predicate) {
            this.verification = predicate;
            return this;
        }

        public GameManager<T> build() {
            Preconditions.checkNotNull(objective, "Objective cannot be null!");
            return new GameManager<>(this);
        }
    }
}
