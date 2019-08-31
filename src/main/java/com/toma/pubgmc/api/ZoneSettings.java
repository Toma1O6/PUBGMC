package com.toma.pubgmc.api;

import javafx.util.Pair;
import net.minecraft.util.math.BlockPos;

public final class ZoneSettings {

    public int damagePerSecond;
    public float speedModifier;
    public boolean isStatic;

    private ZoneSettings() {
    }

    public static final class Builder {

        int dmg;
        float speed;
        Pair<BlockPos, BlockPos> corners;
        boolean keepStatic;

        private Builder(){}

        public static Builder create() {
            return new Builder();
        }

        public Builder damage(int damage) {
            dmg = damage;
            return this;
        }

        public Builder speed(float speed) {
            this.speed = speed;
            return this;
        }

        public Builder setStatic() {
            this.keepStatic = true;
            return this;
        }

        public ZoneSettings build() {
            ZoneSettings settings = new ZoneSettings();

        }
    }
}
