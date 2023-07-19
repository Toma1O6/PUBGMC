package dev.toma.pubgmc.api.game;

import dev.toma.pubgmc.api.event.ParachuteEvent;
import dev.toma.pubgmc.api.event.SpawnPositionSetEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public interface GameEventListener {

    // Called when player logs in
    default void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {}

    // Called when player logs out
    default void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {}

    // Called when entity dies, but only when game is in started state
    default void onEntityDeath(LivingDeathEvent event) {}

    // Called when entity is hurt, but only when game is in started state
    default void onEntityHurt(LivingHurtEvent event) {}

    default void onEntityAttack(LivingAttackEvent event) {}

    // Called when player respawns, only when game is in started state
    default void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {}

    // Called when LivingGameEntity joins world from EntityJoinWorldEvent. Return false to cancel spawning
    default void onEntitySpawnInWorld(EntityJoinWorldEvent event) {}

    /**
     * Called when entity opens parachute in active game. Event can be cancelled
     * @param event Instance of {@link ParachuteEvent.Open} event
     */
    default void onEntityOpenParachute(ParachuteEvent.Open event) {}

    /**
     * Called when entity lands with parachute
     * @param event Instance of {@link ParachuteEvent.Land} event
     */
    default void onEntityWithParachuteLanded(ParachuteEvent.Land event) {}

    default void setSpawnPosition(SpawnPositionSetEvent event) {}
}
