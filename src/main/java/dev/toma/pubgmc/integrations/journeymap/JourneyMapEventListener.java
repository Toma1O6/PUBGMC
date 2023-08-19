package dev.toma.pubgmc.integrations.journeymap;

import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.capability.GameDataProvider;
import dev.toma.pubgmc.api.game.Game;
import dev.toma.pubgmc.api.game.map.GameMap;
import journeymap.client.api.IClientAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public final class JourneyMapEventListener {

    private final IClientAPI api;
    private final JourneyMapGameOverlay overlay;

    public JourneyMapEventListener(IClientAPI api) {
        this.api = api;
        this.overlay = new JourneyMapGameOverlay(api);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        WorldClient world = minecraft.world;
        if (event.phase == TickEvent.Phase.START || world == null) {
            return;
        }
        GameData data = GameDataProvider.getGameData(world).orElse(null);
        if (data == null)
            return;
        Game<?> game = data.getCurrentGame();
        GameMap instance = data.getActiveGameMap().orElse(null);
        if (game == null || !game.isStarted() || instance == null) {
            reset();
            return;
        }
        overlay.update(data);
    }

    private void reset() {
        api.removeAll(JourneyMapOverlays.OVERLAYS_ID);
    }
}
