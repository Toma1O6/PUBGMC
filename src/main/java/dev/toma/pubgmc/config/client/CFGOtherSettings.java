package dev.toma.pubgmc.config.client;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.configuration.api.type.StringType;
import dev.toma.configuration.api.util.Restriction;

import java.util.regex.Pattern;

public final class CFGOtherSettings extends ObjectType {

    public BooleanType messagesOnJoin;
    public IntType maxLootRenderDistance;
    public StringType headshotCharacter;
    public BooleanType backupPerspective;
    public BooleanType shootingReload;

    public CFGOtherSettings() {
        super("Other");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        messagesOnJoin = configCreator.createBoolean("Log-In Messages", true, "Toggle info messages about updates on log-in");
        maxLootRenderDistance = configCreator.createInt("Loot render distance", 32, 8, 128, "Distance at which is loot being rendered");
        headshotCharacter = configCreator.createString("Headshot character", "\u2316", Restriction.newRestriction(Pattern.compile(".")), "Character to be used for displaying headshot kills");
        backupPerspective = configCreator.createBoolean("Backup Perspective before aiming", false, "This is not compatible with Shoulder Surfing Reloaded");
        shootingReload = configCreator.createBoolean("Use attack button to reload", true, "Only triggers when the magazine is empty");
    }
}
