package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.EnumType;
import dev.toma.configuration.api.type.ObjectType;

public final class CFGOverlaySettings extends ObjectType {

    final ConfigPlugin plugin;
    public EnumType<CFGEnumOverlayStyle> imageBoostOverlay;
    public CFG2DCoords textBoostOverlayPos;
    public CFG2DCoords imgBoostOverlayPos;
    public BooleanType renderArmorIcons;

    public CFGOverlaySettings(ConfigPlugin plugin) {
        super("Overlays", "Overlay rendering options");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        imageBoostOverlay = configCreator.createEnum("Boost Overlay", CFGEnumOverlayStyle.IMAGE, "Changes boost overlay render style");
        textBoostOverlayPos = configCreator.createObject(new CFG2DCoords("Text Overlay Coords"), plugin);
        imgBoostOverlayPos = configCreator.createObject(new CFG2DCoords("Image Overlay Coords"), plugin);
        renderArmorIcons = configCreator.createBoolean("Render Armor Icons", true);
    }
}
