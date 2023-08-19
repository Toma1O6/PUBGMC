package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ColorType;
import dev.toma.configuration.api.type.EnumType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.pubgmc.config.client.game.BattleRoyaleOverlays;
import dev.toma.pubgmc.config.client.game.DominationOverlays;
import dev.toma.pubgmc.config.client.game.FFAOverlays;

public final class CFGOverlaySettings extends ObjectType {

    final ConfigPlugin plugin;
    public BattleRoyaleOverlays battleRoyaleOverlays;
    public FFAOverlays ffaOverlays;
    public DominationOverlays dominationOverlays;
    public EnumType<CFGEnumOverlayStyle> imageBoostOverlay;
    public CFG2DCoords textBoostOverlayPos;
    public CFG2DCoords imgBoostOverlayPos;
    public BooleanType renderArmorIcons;
    public ColorType jmMapBorderColor;
    public ColorType jmBorderColor;
    public ColorType jmShrinkingColor;

    public CFGOverlaySettings(ConfigPlugin plugin) {
        super("Overlays", "Overlay rendering options");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        battleRoyaleOverlays = configCreator.createObject(new BattleRoyaleOverlays(plugin), plugin);
        ffaOverlays = configCreator.createObject(new FFAOverlays(plugin), plugin);
        dominationOverlays = configCreator.createObject(new DominationOverlays(plugin), plugin);
        imageBoostOverlay = configCreator.createEnum("Boost Overlay", CFGEnumOverlayStyle.IMAGE, "Changes boost overlay render style");
        textBoostOverlayPos = configCreator.createObject(new CFG2DCoords("Text Overlay Coords"), plugin);
        imgBoostOverlayPos = configCreator.createObject(new CFG2DCoords("Image Overlay Coords"), plugin);
        renderArmorIcons = configCreator.createBoolean("Render Armor Icons", true);
        jmMapBorderColor = configCreator.createColorRGB("Map Border Color", "#FF0000", "Map Border color for JourneyMap");
        jmBorderColor = configCreator.createColorRGB("Border Playzone Color", "#FFFFFF", "Border playzone color for JourneyMap");
        jmShrinkingColor = configCreator.createColorRGB("Shrinking Playzone Color", "#0000FF", "Shrinking playzone color for JourneyMap");
    }
}
