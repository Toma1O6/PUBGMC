package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.util.Nameable;

public enum CFGAimType implements Nameable {
    HOLD, TOGGLE;

    @Override
    public String getFormattedName() {
        return name();
    }

    @Override
    public String getUnformattedName() {
        return name();
    }
}
