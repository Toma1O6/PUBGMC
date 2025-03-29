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
    public CFG2DCoords equipmentInventoryButtonPos;
    public BooleanType renderArmorIcons;
    public BooleanType renderGunCrosshairs;
    public BooleanType renderStatusBars;
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
        equipmentInventoryButtonPos = configCreator.createObject(new CFG2DCoords("Equipment Inventory Button"), plugin);
        renderArmorIcons = configCreator.createBoolean("Render Armor Icons", true);
        renderGunCrosshairs = configCreator.createBoolean("Render Crosshairs while holding gun", false);
        renderStatusBars = configCreator.createBoolean("Render food and experience bar", false);
        jmMapBorderColor = configCreator.createColorRGB("Map Border Color", "#FF0000", "Map Border color for JourneyMap");
        jmBorderColor = configCreator.createColorRGB("Border Playzone Color", "#FFFFFF", "Border playzone color for JourneyMap");
        jmShrinkingColor = configCreator.createColorRGB("Shrinking Playzone Color", "#0000FF", "Shrinking playzone color for JourneyMap");
    }
}
