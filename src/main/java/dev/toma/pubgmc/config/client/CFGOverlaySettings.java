package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.*;
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
    public CFG2DCoords imgNewHealthBarOverlayPos;
    public DoubleType imgNewHealthBarLimit;
    public CFG2DCoords equipmentInventoryButtonPos;
    public BooleanType renderArmorIcons;
    public BooleanType renderGunCrosshairs;
    public BooleanType renderStatusBars;
    public BooleanType renderBoost;
    public BooleanType renderNewHealthBar;
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
        textBoostOverlayPos = configCreator.createObject(new CFG2DCoords("Boost Text Overlay Coords"), plugin);
        imgBoostOverlayPos = configCreator.createObject(new CFG2DCoords("Boost Image Overlay Coords"), plugin);
        imgNewHealthBarOverlayPos = configCreator.createObject(new CFG2DCoords("New Health Bar Coords", "This will also hide food bar"), plugin);
        imgNewHealthBarLimit = configCreator.createDouble("Maximum health in new health bar in a line", 20.0f, 5f ,100f);
        equipmentInventoryButtonPos = configCreator.createObject(new CFG2DCoords("Equipment Inventory Button"), plugin);
        renderArmorIcons = configCreator.createBoolean("Render Armor Icons", true);
        renderGunCrosshairs = configCreator.createBoolean("Render Crosshairs in first person view", true);
        renderStatusBars = configCreator.createBoolean("Render food and experience bar", false);
        renderBoost = configCreator.createBoolean("Render boost", true, "Boost provided by energy drink, painkiller, etc.");
        renderNewHealthBar = configCreator.createBoolean("Render new health bar", true, "Use new health bar instead of the original");
        jmMapBorderColor = configCreator.createColorRGB("Map Border Color", "#FF0000", "Map Border color for JourneyMap");
        jmBorderColor = configCreator.createColorRGB("Border Playzone Color", "#FFFFFF", "Border playzone color for JourneyMap");
        jmShrinkingColor = configCreator.createColorRGB("Shrinking Playzone Color", "#0000FF", "Shrinking playzone color for JourneyMap");
    }
}
