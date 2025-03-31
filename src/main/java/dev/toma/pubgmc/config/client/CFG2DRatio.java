package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.DoubleType;
import dev.toma.configuration.api.type.ObjectType;

public class CFG2DRatio extends ObjectType {

    private DoubleType x;
    private DoubleType y;

    public CFG2DRatio(String name, String... desc) {
        super(name, desc);
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        x = configCreator.createDouble("X", 0);
        y = configCreator.createDouble("Y", 0);
    }

    public float getX() {
        return x.getAsFloat();
    }

    public float getY() {
        return y.getAsFloat();
    }
}
