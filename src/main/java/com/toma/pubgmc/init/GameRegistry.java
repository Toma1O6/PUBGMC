package com.toma.pubgmc.init;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.HashMap;

public class GameRegistry {

    // TODO edit for just string names
    public static final HashMap<ResourceLocation, Game> REGISTRY = new HashMap<>();

    public static void registerGame(ResourceLocation resourceLocation, Game game) {
        if(REGISTRY.containsKey(resourceLocation)) {
            throw new IllegalArgumentException("Duplicate game ID: " + resourceLocation + "!");
        }

        REGISTRY.put(resourceLocation, game);
    }

    public static Game findGameInRegistry(String path) {
        return findGameInRegistry(new ResourceLocation(Pubgmc.MOD_ID, path));
    }

    public static Game findGameInRegistry(ResourceLocation resourceLocation) {
        for (ResourceLocation rl : REGISTRY.keySet()) {
            if(rl.getResourcePath().equals(resourceLocation.getResourcePath())) {
                return REGISTRY.get(resourceLocation);
            }
        }
        return null;
    }

    public static void dispatchRegistryEvent() {
        MinecraftForge.EVENT_BUS.post(new GameRegisterEvent());
    }

    public static class GameRegisterEvent extends Event {

        /**
         * You must create new game instance here!
         */
        public void register(Game game) {
            // just for game constructor invocation
        }
    }
}
