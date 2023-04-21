package dev.toma.pubgmc.api.game;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;

public interface GameEventListener {

    // Called when player logs in
    default void onPlayerLoggedIn(EntityPlayerMP player) {}

    // Called when player logs out
    default void onPlayerLoggedOut(EntityPlayerMP player) {}

    // Called when entity dies, but only when game is in started state
    default void onEntityDeath(EntityLivingBase entity, DamageSource source) {}

    // Called when player respawns, only when game is in started state
    default void onPlayerRespawn(EntityPlayer player) {}
}
