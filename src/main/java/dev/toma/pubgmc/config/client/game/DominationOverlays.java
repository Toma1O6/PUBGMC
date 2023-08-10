package dev.toma.pubgmc.config.client.game;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ColorType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.pubgmc.config.client.CFG2DCoords;

public final class DominationOverlays extends ObjectType {

    private final ConfigPlugin plugin;

    public BooleanType showTicketStatus;
    public BooleanType showCaptureZoneStatus;
    public CFG2DCoords ticketPanel;
    public CFG2DCoords captureZonePanel;
    public CFG2DCoords timePanel;
    public CFG2DCoords deathMessagesPanel;
    public ColorType playzoneColor;

    public DominationOverlays(ConfigPlugin plugin) {
        super("Domination Overlays");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        showTicketStatus = configCreator.createBoolean("Show tickets overlay", true);
        showCaptureZoneStatus = configCreator.createBoolean("Show capture zones overlay", true);
        ticketPanel = configCreator.createObject(new CFG2DCoords("Ticket panel position"), plugin);
        captureZonePanel = configCreator.createObject(new CFG2DCoords("Capture zone panel position"), plugin);
        timePanel = configCreator.createObject(new CFG2DCoords("Time panel position"), plugin);
        deathMessagesPanel = configCreator.createObject(new CFG2DCoords("Death message panel position"), plugin);
        playzoneColor = configCreator.createColorARGB("Playzone color", "#44FFFFFF");
    }
}
