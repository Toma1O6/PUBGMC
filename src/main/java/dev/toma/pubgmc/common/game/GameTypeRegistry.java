package dev.toma.pubgmc.common.game;

import com.google.common.collect.ImmutableSet;
import dev.toma.pubgmc.common.game.event.GameTypeRegistryEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class GameTypeRegistry {

    private final Map<ResourceLocation, GameType<?>> typeMap;

    public GameTypeRegistry() {
        typeMap = new HashMap<>();
    }

    public void dispatchRegistryEvent() {
        GameTypeRegistryEvent event = new GameTypeRegistryEvent();
        MinecraftForge.EVENT_BUS.post(event);
        event.forEachType(this::tryRegister);
    }

    public GameType<?> getByID(ResourceLocation ID) {
        return typeMap.get(ID);
    }

    public Set<ResourceLocation> getKeys() {
        return ImmutableSet.copyOf(typeMap.keySet());
    }

    private void tryRegister(GameType<?> type) {
        ResourceLocation location = type.getRegistryKey();
        if(typeMap.put(location, type) != null) {
            throw new IllegalStateException("Duplicate game type for key " + location + "!");
        }
    }
}
