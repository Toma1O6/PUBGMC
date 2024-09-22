package dev.toma.pubgmc.client.renderer.overlay;

import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public interface DriveableOverlay<D extends EntityDriveable> {

    void renderOverlay(D driveable, Minecraft client, ScaledResolution window, float partialTicks);
}
