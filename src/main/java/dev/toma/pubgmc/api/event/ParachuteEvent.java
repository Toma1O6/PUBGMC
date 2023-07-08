package dev.toma.pubgmc.api.event;

import dev.toma.pubgmc.common.entity.EntityParachute;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class ParachuteEvent extends Event {

    private final EntityParachute entity;
    private final EntityLivingBase owner;

    public ParachuteEvent(EntityParachute entity, EntityLivingBase owner) {
        this.entity = entity;
        this.owner = owner;
    }

    public EntityParachute getParachuteEntity() {
        return entity;
    }

    public EntityLivingBase getParachuteOwner() {
        return owner;
    }

    @Cancelable
    public static class Open extends ParachuteEvent {

        public Open(EntityParachute entity, EntityLivingBase owner) {
            super(entity, owner);
        }
    }

    public static class Land extends ParachuteEvent {

        public Land(EntityParachute entity, EntityLivingBase owner) {
            super(entity, owner);
        }
    }
}
