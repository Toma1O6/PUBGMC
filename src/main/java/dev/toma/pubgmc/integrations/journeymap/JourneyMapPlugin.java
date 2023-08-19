package dev.toma.pubgmc.integrations.journeymap;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.games.GameTypes;
import journeymap.client.api.ClientPlugin;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.IClientPlugin;
import journeymap.client.api.event.ClientEvent;
import net.minecraftforge.common.MinecraftForge;

@ClientPlugin
public final class JourneyMapPlugin implements IClientPlugin {

    private IClientAPI api;
    private JourneyMapEventListener listener;

    @Override
    public void initialize(IClientAPI jmClientApi) {
        api = jmClientApi;
        listener = new JourneyMapEventListener(api);
        MinecraftForge.EVENT_BUS.register(listener);

        JourneyMapGameOverlay.registerJourneyMapOverlay(GameTypes.BATTLE_ROYALE, new JourneyMapOverlays.BattleRoyaleJourneyMapOverlay());
        JourneyMapGameOverlay.registerJourneyMapOverlay(GameTypes.FFA, new JourneyMapOverlays.FreeForAllJourneyMapOverlay());
        JourneyMapGameOverlay.registerJourneyMapOverlay(GameTypes.DOMINATION, new JourneyMapOverlays.DominationJourneyMapOverlay());
    }

    @Override
    public void onEvent(ClientEvent event) {
    }

    @Override
    public String getModId() {
        return Pubgmc.MOD_ID;
    }
}
