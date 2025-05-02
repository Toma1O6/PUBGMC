package dev.toma.pubgmc.client.renderer.overlay;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.entity.vehicles.EntityLandVehicle;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehiclePart;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.config.client.CFG2DRatio;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.Locale;

public class LandVehicleOverlay<D extends EntityLandVehicle> implements DriveableOverlay<D> {

    private static final ResourceLocation VEHICLE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vehicle.png");

    @Override
    public void renderOverlay(D driveable, Minecraft client, ScaledResolution window, RenderGameOverlayEvent e) {
        // this.renderDebugInfo(driveable, client, window);

//        if (e instanceof RenderGameOverlayEvent.Post) {
//            float partialTicks = e.getPartialTicks();
//        }
        // TODO seats
        int screenWidth = window.getScaledWidth();
        int screenHeight = window.getScaledHeight();
        float centerX = screenWidth / 2f;
        float halfWidth = centerX;
        float centerY = screenHeight / 2f;
        float halfHeight = centerY;
        CFG2DRatio vInfoPos = ConfigPMC.client.overlays.vehicleInfoPos;
        float vInfoX = centerX + halfWidth * (vInfoPos.getX() - 0.95f);
        float vInfoY = centerY + halfHeight * (vInfoPos.getY() + 0.25f);

        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            double speed = driveable.getSpeedPerTick() * 20;
            client.fontRenderer.drawStringWithShadow(I18n.format("label.pubgmc.speed") + ": " + (int) (speed * 3.6) + "km/h", vInfoX, vInfoY - 15, 16777215);
        } else if (e.getType() == RenderGameOverlayEvent.ElementType.ALL) {

            int barWidth = 120;
            short barHeight = 5;
            float fuelPercentage = driveable.getFuel() / driveable.getFuelTankCapacity();
            ImageUtil.drawImageWithUV(client, VEHICLE, vInfoX, vInfoY, fuelPercentage * barWidth, barHeight, 0.0, 0.25, 1.0, 0.375, false);
            ImageUtil.drawImageWithUV(client, VEHICLE, vInfoX, vInfoY, barWidth, barHeight, 0.0, 0.375, 1.0, 0.5, true);
            // health background
            ImageUtil.drawImageWithUV(client, VEHICLE, vInfoX, vInfoY - 5, barWidth, barHeight, 0.0, 0.125, 1.0, 0.25, false);
            float healthPercentage = driveable.getHealthPercentage();
            // color
            float r, g, b;
            if (healthPercentage <= 0.2F) { // red
                r = 0.863f; g = 0.34f; b = 0.291f; // #dc564a 220,86,74
            } else if (healthPercentage <= 0.45F) { // yellow
                r = 0.98f; g = 0.895f; b = 0.648f; // #f9e4a5 249.228,165
            } else if (healthPercentage < 1.0f) { // white
                r = 0.95f; g =0.95f; b = 0.95f; // #f2f2f2 242,242,242
            } else { // grey
                r = 0.648f; g = 0.648f; b = 0.648f; // #a5a5a5 165.165,165
            }
            // health
            ImageUtil.drawShape(vInfoX, vInfoY - 5, vInfoX + barWidth * healthPercentage, vInfoY - 5 + barHeight, r, g, b, 1.0f);
        }
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
