package dev.toma.pubgmc.config.client.game;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.ColorType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.pubgmc.config.client.CFG2DCoords;

public final class FFAOverlays extends ObjectType {

    private final ConfigPlugin plugin;

    public CFG2DCoords timePanel;
    public CFG2DCoords deathMessagesPanel;
    public ColorType playzoneColor;

    public FFAOverlays(ConfigPlugin plugin) {
        super("FFA Overlays");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        timePanel = configCreator.createObject(new CFG2DCoords("Time panel"), plugin);
        deathMessagesPanel = configCreator.createObject(new CFG2DCoords("Death messages panel"), plugin);
        playzoneColor = configCreator.createColorARGB("Playzone color", "#660033FF");
    }
}
