package dev.toma.pubgmc.client.renderer.overlay;

import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public interface DriveableOverlay<D extends EntityDriveable> {

    void renderOverlay(D driveable, Minecraft client, ScaledResolution window, RenderGameOverlayEvent e);
}
