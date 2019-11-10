package com.toma.pubgmc.event.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SetupAnglesEvent extends Event {

    private ModelBiped model;
    private Entity entity;

    public SetupAnglesEvent(ModelBiped model, Entity entity) {
        this.model = model;
        this.entity = entity;
    }

    public ModelBiped getModel() {
        return model;
    }

    public Entity getEntity() {
        return entity;
    }
}
