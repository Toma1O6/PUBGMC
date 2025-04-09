package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ColorType;
import dev.toma.configuration.api.type.DoubleType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.configuration.api.util.NumberDisplayType;
import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.config.type.PMCConfigCreator;
import dev.toma.pubgmc.config.type.TextureType;

public class CFGScopes extends ObjectType {

    public DoubleType scope2xSensitivity;
    public DoubleType scope4xSensitivity;
    public DoubleType scope8xSensitivity;
    public DoubleType scope15xSensitivity;
    public ColorType redDotColor;
    public ColorType holographicColor;
    public TextureType redDotVariants;
    public TextureType holographicVariants;
    public BooleanType glowingReticles;

    public CFGScopes() {
        super("Scopes", "Configure your reticles here");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        PMCConfigCreator creator = (PMCConfigCreator) configCreator;
        // glowingReticles = creator.createBoolean("Glowing reticles", true, "Reticles will be rendered with full brightness");
        scope2xSensitivity = creator.createDouble("2X scope sensitivity", 0.85F, 0.01F, 1.0F).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER).setFormatting(DevUtil.THREE_DECIMALS);
        scope4xSensitivity = creator.createDouble("4X scope sensitivity", 0.6F, 0.01F, 1.0F).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER).setFormatting(DevUtil.THREE_DECIMALS);
        scope8xSensitivity = creator.createDouble("8X scope sensitivity", 0.35F, 0.01F, 1.0F).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER).setFormatting(DevUtil.THREE_DECIMALS);
        scope15xSensitivity = creator.createDouble("15X scope sensitivity", 0.15F, 0.01F, 1.0F).setDisplay(NumberDisplayType.TEXT_FIELD_SLIDER).setFormatting(DevUtil.THREE_DECIMALS);
        redDotColor = creator.createColorARGB("Red dot color", "#FFFF0000", "Manages color of red dot reticle");
        holographicColor = creator.createColorARGB("Holographic color", "#FFFF0000", "Manages color of holographic reticle");
        redDotVariants = creator.createTextureArray("Red dot variant", 0, new TextureType.Entry[]{
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/red_dot_dot.png"), "Dot"),
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/red_dot_chevron.png"), "Chevron"),
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/red_dot_cross.png"), "Cross")
        }, () -> redDotColor.getColor());
        holographicVariants = creator.createTextureArray("Holographic variant", 0, new TextureType.Entry[]{
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/holographic_normal.png"), "EOTech"),
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/holographic_large.png"), "EOTech Large"),
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/holographic_holosun.png"), "Holosun"),
        }, () -> holographicColor.getColor());
    }
}
