package dev.toma.pubgmc.api.client.game;

import dev.toma.pubgmc.api.game.Game;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public interface GameRenderer<G extends Game<?>> {

    /**
     * Will render game overlay for specific client player
     *
     * @param player Client player
     * @param game Currently running game
     * @param resolution Window properties
     * @param elementType Type of element being rendered
     * @param partialTicks Current partial ticks
     * @return {@code true} if the underlying {@link RenderGameOverlayEvent} should be cancelled for this {@link net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType}
     */
    boolean renderHudOverlay(EntityPlayer player, G game, ScaledResolution resolution, RenderGameOverlayEvent.ElementType elementType, float partialTicks);

    /**
     * Will render overlay in world
     *
     * @param world World being rendered
     * @param game Currently running game
     * @param x Interpolated player X position
     * @param y Interpolated player Y position
     * @param z Interpolated player Z position
     * @param partialTicks Current partial ticks
     */
    void renderInWorld(World world, G game, double x, double y, double z, float partialTicks);
}
