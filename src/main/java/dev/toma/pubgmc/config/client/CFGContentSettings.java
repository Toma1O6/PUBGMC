package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.FixedCollectionType;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.configuration.api.util.Nameable;

import java.util.concurrent.TimeUnit;

public class CFGContentSettings extends ObjectType {

    public BooleanType periodicUpdates;
    public IntType period;
    public FixedCollectionType<Nameable.Wrapped<TimeUnit>> timeUnit;

    public CFGContentSettings() {
        super("Content settings");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        periodicUpdates = configCreator.createBoolean("Allow periodic updates", true, "Will peridically check for updates");
        period = configCreator.createInt("Period", 5, 1, 1000, "How often you will receive updates", "This option also depends on your Time Unit");
        Nameable.Wrapped<TimeUnit>[] allowedValues = new Nameable.Wrapped[3];
        allowedValues[0] = new Nameable.Wrapped<>(TimeUnit.SECONDS, Enum::name);
        allowedValues[1] = new Nameable.Wrapped<>(TimeUnit.MINUTES, Enum::name);
        allowedValues[2] = new Nameable.Wrapped<>(TimeUnit.HOURS, Enum::name);
        timeUnit = configCreator.createArray("Time unit", 1, allowedValues);
    }
}
