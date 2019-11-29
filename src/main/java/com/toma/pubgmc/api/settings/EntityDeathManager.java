package com.toma.pubgmc.api.settings;

import com.toma.pubgmc.api.util.EntityDeathContex;

import java.util.function.Consumer;
import java.util.function.Function;

public final class EntityDeathManager {

    private Function<EntityDeathContex, String> victimNotification;
    private Function<EntityDeathContex, String> sourceNotification;
    private Function<EntityDeathContex, String> othersNotification;
    private Consumer<EntityDeathContex> deathAction;

    private EntityDeathManager(final Builder builder) {
        this.victimNotification = builder.victim;
        this.sourceNotification = builder.source;
        this.othersNotification = builder.others;
        this.deathAction = builder.deathAction;
    }

    public Function<EntityDeathContex, String> getVictimNotification() {
        return victimNotification;
    }

    public Function<EntityDeathContex, String> getSourceNotification() {
        return sourceNotification;
    }

    public Function<EntityDeathContex, String> getOthersNotification() {
        return othersNotification;
    }

    public Consumer<EntityDeathContex> getDeathAction() {
        return deathAction;
    }

    public static class Builder {

        private Function<EntityDeathContex, String> victim, source, others;
        private Consumer<EntityDeathContex> deathAction = a -> {};

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder victimNotification(final Function<EntityDeathContex, String> function) {
            this.victim = function;
            return this;
        }

        public Builder sourceNotification(final Function<EntityDeathContex, String> function) {
            this.source = function;
            return this;
        }

        public Builder othersNotification(final Function<EntityDeathContex, String> function) {
            this.others = function;
            return this;
        }

        public Builder onDeath(final Consumer<EntityDeathContex> deathAction) {
            this.deathAction = deathAction;
            return this;
        }

        public EntityDeathManager build() {
            return new EntityDeathManager(this);
        }
    }
}
