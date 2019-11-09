package com.toma.pubgmc.event.client;

import net.minecraft.client.model.ModelPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SetupAnglesEvent extends Event {

    private ModelPlayer modelPlayer;

    public SetupAnglesEvent(ModelPlayer modelPlayer) {
        this.modelPlayer = modelPlayer;
    }

    public ModelPlayer getModel() {
        return modelPlayer;
    }
}
