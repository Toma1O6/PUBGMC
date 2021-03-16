package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ColorType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.config.type.PMCConfigCreator;
import dev.toma.pubgmc.config.type.TextureType;

public class CFGReticles extends ObjectType {

    public ColorType redDotColor;
    public ColorType holographicColor;
    public TextureType redDotVariants;
    public TextureType holographicVariants;
    public BooleanType glowingReticles;

    public CFGReticles() {
        super("Reticles", "Configure your reticles here");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        PMCConfigCreator creator = (PMCConfigCreator) configCreator;
        glowingReticles = creator.createBoolean("Glowing reticles", true, "Reticles will be rendered with full brightness");
        redDotColor = creator.createColorARGB("Red dot color", "#FFFF0000", "Manages color of red dot reticle");
        holographicColor = creator.createColorARGB("Holographic color", "#FFFF0000", "Manages color of holographic reticle");
        redDotVariants = creator.createTextureArray("Red dot variant", 0, new TextureType.Entry[] {
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/red_dot_dot.png"), "Dot"),
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/red_dot_chevron.png"), "Chevron"),
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/red_dot_cross.png"), "Cross")
        }, () -> redDotColor.getColor());
        holographicVariants = creator.createTextureArray("Holographic variant", 0, new TextureType.Entry[] {
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/holographic_normal.png"), "EOTech"),
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/holographic_large.png"), "EOTech Large"),
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/holographic_holosun.png"), "Holosun"),
        }, () -> holographicColor.getColor());
    }
}
