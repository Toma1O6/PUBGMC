package com.toma.pubgmc.init;

import com.toma.pubgmc.api.Game;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameRegistry {

    public static final HashMap<String, Game> REGISTRY = new HashMap<>(3);

    public static void registerGame(String modeName, Game game) {
        if(REGISTRY.containsKey(modeName)) {
            throw new IllegalArgumentException("Duplicate game ID: " + modeName + "!");
        }

        REGISTRY.put(modeName, game);
    }

    public static Game findGameInRegistry(String mode) {
        for (String s : REGISTRY.keySet()) {
            if(s.equalsIgnoreCase(mode)) {
                return REGISTRY.get(mode);
            }
        }
        return null;
    }

    public static void dispatchRegistryEvent() {
        MinecraftForge.EVENT_BUS.post(new GameRegisterEvent());
    }

    public static List<String> getValuesPaths() {
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
