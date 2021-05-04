package dev.toma.pubgmc.common.game.interfaces;

import net.minecraft.entity.player.EntityPlayer;

public interface IPlayerManager {

    void join(EntityPlayer player);

    void leave(EntityPlayer player);
}
