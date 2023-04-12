package dev.toma.pubgmc.client.event;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientWorldTickEvent extends Event {

    public final TickEvent.Phase phase;
    private final WorldClient world;

    public ClientWorldTickEvent(TickEvent.Phase phase, WorldClient world) {
        this.phase = phase;
        this.world = world;
    }

    public WorldClient getWorld() {
        return world;
    }
}
