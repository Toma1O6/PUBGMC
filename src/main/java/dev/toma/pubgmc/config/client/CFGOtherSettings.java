package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ColorType;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.ObjectType;

public final class CFGOtherSettings extends ObjectType {

    public BooleanType messagesOnJoin;
    public IntType maxLootRenderDistance;

    public CFGOtherSettings() {
        super("Other");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        messagesOnJoin = configCreator.createBoolean("Log-In Messages", true, "Toggle info messages about updates on log-in");
        maxLootRenderDistance = configCreator.createInt("Loot render distance", 32, 8, 128, "Distance at which is loot being rendered");
    }
}
