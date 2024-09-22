package dev.toma.pubgmc.client.renderer.overlay;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.entity.vehicles.EntityLandVehicle;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehiclePart;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

import java.util.Locale;

public class LandVehicleOverlay<D extends EntityLandVehicle> implements DriveableOverlay<D> {

    @Override
    public void renderOverlay(D driveable, Minecraft client, ScaledResolution window, float partialTicks) {
        this.renderDebugInfo(driveable, client, window); // TODO disable before PROD build
        // TODO health, fuel, speed, seats
    }

    private void renderDebugInfo(D driveable, Minecraft client, ScaledResolution window) {
        FontRenderer font = client.fontRenderer;
        int y = -5;
        // Statistics
        font.drawString(String.format(Locale.ROOT, "Health: %.2f / %.2f", driveable.getHealth(), driveable.getMaxHealth()), 5, y += 10, 0xFFFFFF);
        font.drawString(String.format(Locale.ROOT, "Fuel: %.2f / %.2f", driveable.getFuel(), driveable.getFuelTankCapacity()), 5, y += 10, 0xFFFFFF);
        font.drawString(String.format(Locale.ROOT, "Starting / Started: %s / %s", driveable.isStarting(), driveable.isStarted()), 5, y += 10, 0xFFFFFF);

        // parts
        EntityVehiclePart[] parts = driveable.getParts();
        font.drawString("Parts", 5, y += 10, 0xFFFFFF);
        for (EntityVehiclePart part : parts) {
            font.drawString(part.getPartInfo(), 10, y += 10, 0xFFFFFF);
        }
    }
}
