package dev.toma.pubgmc.api.settings;

import com.google.common.base.Preconditions;
import dev.toma.pubgmc.api.util.EntityDeathContex;

import java.util.function.Consumer;

public final class EntityDeathManager {

    private final Consumer<EntityDeathContex> deathAction;
    private final int deathMessagesCount;
    private final int deathMessageRenderLength;

    private EntityDeathManager(final Builder builder) {
        this.deathAction = builder.deathAction;
        this.deathMessagesCount = builder.count;
        this.deathMessageRenderLength = builder.length;
    }

    public Consumer<EntityDeathContex> getDeathAction() {
        return deathAction;
    }

    public int getDeathMessagesCount() {
        return deathMessagesCount;
    }

    public int getDeathMessageRenderLength() {
        return deathMessageRenderLength;
    }

    public static class Builder {

        private Consumer<EntityDeathContex> deathAction = a -> {};
        private int count = 5, length = 60;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder onDeath(final Consumer<EntityDeathContex> deathAction) {
            this.deathAction = deathAction;
            return this;
        }

        public Builder deathIcons(int deathMessageCount, int ticks) {
            this.count = deathMessageCount;
            this.length = ticks;
            return this;
        }

        public EntityDeathManager build() {
            Preconditions.checkNotNull(deathAction, "Death action cannot be null!");
            Preconditions.checkState(this.count > 0 && this.count <= 10, "Death message amount is outside <1;10> interval!");
            Preconditions.checkState(this.length >= 20 && this.length <= 400, "Render ticks are outside <20;400> interval!");
            return new EntityDeathManager(this);
        }
    }
}
