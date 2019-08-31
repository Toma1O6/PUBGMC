package com.toma.pubgmc.init;

import com.toma.pubgmc.api.Game;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

public class GameRegistry {

    public static final HashMap<ResourceLocation, Game> REGISTRY = new HashMap<>();

    public static void registerGame(ResourceLocation resourceLocation, Game game) {
        if(REGISTRY.containsKey(resourceLocation)) {
            throw new IllegalArgumentException("Duplicate game ID: " + resourceLocation + "!");
        }

        REGISTRY.put(resourceLocation, game);
    }

    public static Game findGameInRegistry(String modID, String path) {
        return findGameInRegistry(new ResourceLocation(modID, path));
    }

    public static Game findGameInRegistry(ResourceLocation resourceLocation) {
        for (ResourceLocation rl : REGISTRY.keySet()) {
            if(rl.equals(resourceLocation)) {
                return REGISTRY.get(resourceLocation);
            }
        }
        return null;
    }
}
