package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ColorType;
import dev.toma.configuration.api.type.ObjectType;

public final class CFGOtherSettings extends ObjectType {
    public BooleanType messagesOnJoin;
    public ColorType zoneColor;

    public CFGOtherSettings() {
        super("Other");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        messagesOnJoin = configCreator.createBoolean("Log-In Messages", true, "Toggle info messages about updates on log-in");
        zoneColor = configCreator.createColorRGB("Zone color", "#00BAFF");
    }
}
