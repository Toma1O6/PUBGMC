package dev.toma.pubgmc.config.client.game;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ColorType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.pubgmc.config.client.CFG2DCoords;
import dev.toma.pubgmc.config.client.CFG2DRatio;

public final class BattleRoyaleOverlays extends ObjectType {

    private final ConfigPlugin plugin;

    public CFG2DRatio gameInfoPanelPos;
    public CFG2DRatio deathMessagesPos;
    public BooleanType showTeamInformation;
    public CFG2DRatio teamPanelPosition;
    public ColorType playzoneColor;

    public BattleRoyaleOverlays(ConfigPlugin plugin) {
        super("Battle Royale Overlays");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        gameInfoPanelPos = configCreator.createObject(new CFG2DRatio("Game info panel position"), plugin);
        deathMessagesPos = configCreator.createObject(new CFG2DRatio("Death messages panel"), plugin);
        showTeamInformation = configCreator.createBoolean("Show team information", true);
        teamPanelPosition = configCreator.createObject(new CFG2DRatio("Team panel position"), plugin);
        playzoneColor = configCreator.createColorARGB("Playzone color", "#660033FF");
    }
}
