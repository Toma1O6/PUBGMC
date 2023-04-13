package dev.toma.pubgmc.common.games;

import dev.toma.pubgmc.Pubgmc;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = Pubgmc.MOD_ID)
public final class CommonGameEventHandler {

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            // TODO get active game and tick
        }
    }

    // TODO other events
}
