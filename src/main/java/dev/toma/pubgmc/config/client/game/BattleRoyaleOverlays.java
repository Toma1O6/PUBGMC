package dev.toma.pubgmc.config.client.game;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ColorType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.pubgmc.config.client.CFG2DCoords;

public final class BattleRoyaleOverlays extends ObjectType {

    private final ConfigPlugin plugin;

    public CFG2DCoords gameInfoPanelPosition;
    public CFG2DCoords deathMessagesPosition;
    public BooleanType showTeamInformation;
    public CFG2DCoords teamPanelPosition;
    public ColorType playzoneColor;

    public BattleRoyaleOverlays(ConfigPlugin plugin) {
        super("Battle Royale Overlays");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        gameInfoPanelPosition = configCreator.createObject(new CFG2DCoords("Game info panel"), plugin);
        deathMessagesPosition = configCreator.createObject(new CFG2DCoords("Death messages panel"), plugin);
        showTeamInformation = configCreator.createBoolean("Show team information", true);
        teamPanelPosition = configCreator.createObject(new CFG2DCoords("Team panel position"), plugin);
        playzoneColor = configCreator.createColorARGB("Playzone color", "#660033FF");
    }
}
