package com.toma.pubgmc.api.settings;

import com.google.common.base.Preconditions;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.interfaces.BotAIGetter;
import com.toma.pubgmc.api.interfaces.BotSpawner;
import com.toma.pubgmc.api.util.EntityDeathContex;
import com.toma.pubgmc.api.util.GameUtils;
import com.toma.pubgmc.common.entity.bot.EntityAIPlayer;

import java.util.function.Consumer;
import java.util.function.Predicate;

public final class GameBotManager {

    public int currentBotCount;
    private final boolean botsEnabled;
    private final boolean botDeathCrates;
    private final int maxBotAmount;
    private final Consumer<EntityAIPlayer> lootFactory;
    private final BotAIGetter botLogic;
    private final BotSpawner botSpawner;
    private final Predicate<? super Game> botSpawnValidator;
    private final Consumer<EntityDeathContex> botDeathAction;

    private GameBotManager(Builder builder) {
        this.botsEnabled = builder.botsEnabled;
        this.botDeathCrates = builder.botDeathCrates;
        this.maxBotAmount = builder.maxAmount;
        this.lootFactory = builder.lootFactory;
        this.botLogic = builder.botLogic;
        this.botSpawner = builder.spawner;
        this.botSpawnValidator = builder.spawnValidator;
        this.botDeathAction = builder.botDeath;
    }

    public boolean areBotsEnabled() {
        return botsEnabled;
    }

    public int maxBotAmount() {
        return maxBotAmount;
    }

    public BotAIGetter getBotLogic() {
        return botLogic;
    }

    public Consumer<EntityAIPlayer> getLootFactory() {
        return lootFactory;
    }

    public BotSpawner getBotSpawner() {
        return botSpawner;
    }

    public Predicate<? super Game> getBotSpawnVerification() {
        return botSpawnValidator;
    }

    public boolean allowBotCrates() {
        return botDeathCrates;
    }

    public static class Builder {

        private boolean botsEnabled = true;
        private boolean botDeathCrates = false;
        private int maxAmount = 3;
        private Consumer<EntityAIPlayer> lootFactory;
        private BotAIGetter botLogic;
        private BotSpawner spawner = GameUtils.getDefaultSpawner();
        private Predicate<? super Game> spawnValidator = game -> true;
        private Consumer<EntityDeathContex> botDeath = ctx -> {};

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder disableBots() {
            this.botsEnabled = false;
            return this;
        }

        public Builder allowBotDeathCrates() {
            this.botDeathCrates = true;
            return this;
        }

        public Builder maxBotAmount(final int amount) {
            this.maxAmount = amount;
            return this;
        }

        public Builder lootFactory(final Consumer<EntityAIPlayer> lootFactory) {
            this.lootFactory = lootFactory;
            return this;
        }

        public Builder addBotLogic(final BotAIGetter botLogic) {
            this.botLogic = botLogic;
            return this;
        }

        public Builder botSpawner(final BotSpawner spawner) {
            this.spawner = spawner;
            return this;
        }

        public Builder spawnValidator(final Predicate<? super Game> validator) {
            this.spawnValidator = validator;
            return this;
        }

        public Builder botDeath(final Consumer<EntityDeathContex> botDeath) {
            this.botDeath = botDeath;
            return this;
        }

        public GameBotManager build() {
            Preconditions.checkState(maxAmount > 0, "Max bot amount cannot be <1!");
            Preconditions.checkNotNull(lootFactory, "Loot factory cannot be null!");
            Preconditions.checkNotNull(botLogic, "Bot task array cannot be null!");
            Preconditions.checkNotNull(spawner, "Bot spawner cannot be null!");
            Preconditions.checkNotNull(spawnValidator, "Spawn validator cannot be null!");
            Preconditions.checkNotNull(botDeath, "Bot death handler cannot be null!");
            return new GameBotManager(this);
        }
    }
}
