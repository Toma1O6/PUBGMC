package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.EnumType;
import dev.toma.configuration.api.type.ObjectType;

public final class ClientConfig extends ObjectType {

    final ConfigPlugin plugin;
    public EnumType<CFGAimType> aimType;
    public CFGOverlaySettings overlays;
    public CFGOtherSettings other;

    public ClientConfig(ConfigPlugin plugin) {
        super("Client", "All client-side related stuff is here");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        aimType = configCreator.createEnum("Aim type", CFGAimType.TOGGLE);
        overlays = configCreator.createObject(new CFGOverlaySettings(plugin), plugin);
        other = configCreator.createObject(new CFGOtherSettings(), plugin);
    }
}
