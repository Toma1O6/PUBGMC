package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.IntType;
import dev.toma.configuration.api.type.ObjectType;

public class CFG2DCoords extends ObjectType {

    private IntType x;
    private IntType y;

    public CFG2DCoords(String name) {
        super(name);
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        x = configCreator.createInt("X", 0);
        y = configCreator.createInt("Y", 0);
    }

    public int getX() {
        return x.get();
    }

    public int getY() {
        return y.get();
    }
}
