package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.type.ColorType;
import dev.toma.configuration.api.type.ObjectType;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.config.type.PMCConfigCreator;
import dev.toma.pubgmc.config.type.TextureType;

public class CFGReticles extends ObjectType {

    public ColorType redDotColor;
    public ColorType holographicColor;
    public TextureType redDotVariants;

    public CFGReticles() {
        super("Reticles", "Configure your reticles here");
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        PMCConfigCreator creator = (PMCConfigCreator) configCreator;
        redDotColor = creator.createColorARGB("Red dot color", "#FFFF0000", "Manages color of red dot reticle");
        holographicColor = creator.createColorARGB("Holographic color", "#FFFF0000", "Manages color of holographic reticle");
        redDotVariants = creator.createTextureArray("Red dot variant", 0, new TextureType.Entry[] {
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/red_dot_dot.png"), "Dot"),
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/red_dot_chevron.png"), "Chevron"),
                new TextureType.Entry(Pubgmc.getResource("textures/overlay/red_dot_cross.png"), "Cross")
        }, () -> redDotColor.getColor());
    }
}
