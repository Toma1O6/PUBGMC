package dev.toma.pubgmc.api.game.loadout;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import java.util.UUID;

public interface GameLoadoutManager {

    boolean hasSelectedLoadout(UUID uuid);

    void selectLoadout(UUID uuid, int loadoutIndex, World world);

    void applyLoadout(EntityLivingBase entity);
}
