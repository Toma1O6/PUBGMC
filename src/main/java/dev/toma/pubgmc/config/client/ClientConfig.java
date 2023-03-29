package dev.toma.pubgmc.config.client;

import dev.toma.configuration.api.ConfigCreator;
import dev.toma.configuration.api.ConfigPlugin;
import dev.toma.configuration.api.type.BooleanType;
import dev.toma.configuration.api.type.ObjectType;

public final class ClientConfig extends ObjectType {

    final ConfigPlugin plugin;
    public BooleanType customMainMenu;
    public CFGOverlaySettings overlays;
    public CFGOtherSettings other;
    public CFGContentSettings content;
    public CFGScopes reticles;

    public ClientConfig(ConfigPlugin plugin) {
        super("Client", "All client-side related stuff is here");
        this.plugin = plugin;
    }

    @Override
    public void buildStructure(ConfigCreator configCreator) {
        customMainMenu = configCreator.createBoolean("Custom Main Menu", true);
        overlays = configCreator.createObject(new CFGOverlaySettings(plugin), plugin);
        other = configCreator.createObject(new CFGOtherSettings(), plugin);
        content = configCreator.createObject(new CFGContentSettings(), plugin);
        reticles = configCreator.createObject(new CFGScopes(), plugin);
    }
}
