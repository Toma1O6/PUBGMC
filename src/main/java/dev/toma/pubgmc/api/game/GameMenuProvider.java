package dev.toma.pubgmc.api.game;

import net.minecraft.entity.player.EntityPlayerMP;

public interface GameMenuProvider {

    void openMenu(EntityPlayerMP player);
}
