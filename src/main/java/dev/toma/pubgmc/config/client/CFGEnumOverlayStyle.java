package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.util.Nameable;

public enum CFGEnumOverlayStyle implements Nameable {

    TEXT, IMAGE;

    @Override
    public String getUnformattedName() {
        return name();
    }

    @Override
    public String getFormattedName() {
        return name();
    }
}
