package dev.toma.pubgmc.api.game;

import net.minecraft.entity.EntityLiving;

public interface LivingGameEntity extends GameObject {

    EntityLiving getLivingEntity();
}