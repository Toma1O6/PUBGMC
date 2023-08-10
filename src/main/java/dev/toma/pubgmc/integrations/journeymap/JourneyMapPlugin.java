package dev.toma.pubgmc.integrations.journeymap;

import dev.toma.pubgmc.Pubgmc;
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
    }

    @Override
    public void onEvent(ClientEvent event) {
    }

    @Override
    public String getModId() {
        return Pubgmc.MOD_ID;
    }
}
