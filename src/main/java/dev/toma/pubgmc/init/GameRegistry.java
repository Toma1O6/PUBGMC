package dev.toma.pubgmc.init;

import dev.toma.pubgmc.api.games.Game;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameRegistry {

    public static final HashMap<ResourceLocation, Game> REGISTRY = new HashMap<>(3);

    public static void registerGame(ResourceLocation registryName, Game game) {
        if(REGISTRY.containsKey(registryName)) {
            throw new IllegalArgumentException("Duplicate game ID: " + registryName + "!");
        }

        REGISTRY.put(registryName, game);
    }

    /**
     * Find game just by it's name
     * @param mode - the mode name
     * @return - registered game instance
     */
    public static Game findGameInRegistry(String mode) {
        for (ResourceLocation loc : REGISTRY.keySet()) {
            if(loc.getResourcePath().equalsIgnoreCase(mode)) {
                return REGISTRY.get(loc);
            }
        }
        return null;
    }

    public static Game getGame(ResourceLocation location) {
        for(ResourceLocation loc : REGISTRY.keySet()) {
            if(location.equals(loc)) {
                return REGISTRY.get(location);
            }
        }
        return null;
    }

    public static void dispatchRegistryEvent() {
        MinecraftForge.EVENT_BUS.post(new GameRegisterEvent());
    }

    public static List<ResourceLocation> getValuesPaths() {
        return new ArrayList<>(REGISTRY.keySet());
    }

    public static class GameRegisterEvent extends Event {

        public void register(Game game) {
            GameRegistry.registerGame(game.registryName, game);
        }

        public void registerAll(Game... games) {
            for(Game game : games) register(game);
        }
    }
}
