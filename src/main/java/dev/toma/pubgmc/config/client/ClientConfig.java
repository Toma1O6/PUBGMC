package dev.toma.pubgmc.config.client;

import net.minecraftforge.common.config.Config;

public final class ClientConfig {

    @Config.Name("Aim type")
    public CFGAimType aimType = CFGAimType.TOGGLE;

    @Config.Name("Overlays")
    @Config.Comment("Overlay rendering options")
    public CFGOverlaySettings overlays = new CFGOverlaySettings();

    @Config.Name("Other")
    public CFGOtherSettings other = new CFGOtherSettings();
}
