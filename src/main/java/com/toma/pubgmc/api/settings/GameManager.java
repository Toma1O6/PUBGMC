package com.toma.pubgmc.api.settings;

import com.google.common.base.Preconditions;
import com.toma.pubgmc.api.interfaces.GameObjective;

import java.util.function.Supplier;

public class GameManager {

    private final int startPhaseLength;
    private final Supplier<GameObjective<?>> gameObjective;

    private GameManager(Builder builder) {
        this.startPhaseLength = builder.wait;
        this.gameObjective = builder.objective;
    }

    public int getStartPhaseLength() {
        return startPhaseLength;
    }

    public Supplier<GameObjective<?>> getGameObjective() {
        return gameObjective;
    }

    public static class Builder {

        private int wait;
        private Supplier<GameObjective<?>> objective;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder waitTime(final int waitTime) {
            this.wait = waitTime;
            return this;
        }

        public Builder objective(final Supplier<GameObjective<?>> objective) {
            this.objective = objective;
            return this;
        }

        public GameManager build() {
            Preconditions.checkNotNull(objective, "Objective cannot be null!");
            return new GameManager(this);
        }
    }
}
