package dev.toma.pubgmc.api.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;

public interface ParentEntityAccess<E extends Entity & IEntityMultiPart> {

    E getParentEntity();

    void synchronizeClientData();
}
