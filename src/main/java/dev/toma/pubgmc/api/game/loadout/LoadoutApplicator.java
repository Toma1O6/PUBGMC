package dev.toma.pubgmc.api.game.loadout;

import net.minecraft.entity.EntityLivingBase;

@FunctionalInterface
public interface LoadoutApplicator<E extends EntityLivingBase> {

    void applyLoadoutOn(E entity, EntityLoadout loadout);
}
