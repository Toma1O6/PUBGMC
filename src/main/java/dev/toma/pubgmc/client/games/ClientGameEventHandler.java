package dev.toma.pubgmc.client.games;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.client.event.ClientWorldTickEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Pubgmc.MOD_ID)
public final class ClientGameEventHandler {

    @SubscribeEvent
    public static void onClientWorldTick(ClientWorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            World world = event.getWorld();
            GameDataProvider.getGameData(world)
                    .ifPresent(GameData::tick);
        }
    }
}
