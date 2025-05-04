package dev.toma.pubgmc.client.renderer.overlay;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import dev.toma.pubgmc.common.entity.vehicles.EntityLandVehicle;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehiclePart;
import dev.toma.pubgmc.common.entity.vehicles.VehicleUAZ;
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
        if (ConfigPMC.developerMode.get())
            this.renderDebugInfo(driveable, client, window);

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

    private void renderDebugInfo(D dr, Minecraft client, ScaledResolution window) {
        FontRenderer font = client.fontRenderer;
        int y = -5;
        VehicleUAZ driveable;
        if (dr instanceof VehicleUAZ) {
            driveable = (VehicleUAZ) dr;
        } else {
            return;
        }
        // Statistics
        font.drawString(String.format(Locale.ROOT, "Health: %.2f / %.2f", driveable.getHealth(), driveable.getMaxHealth()), 5, y += 10, 0xFFFFFF);
        boolean w = driveable.hasInput(EntityDriveable.KEY_FORWARD);
        boolean s = driveable.hasInput(EntityDriveable.KEY_BACK);
        boolean a = driveable.hasInput(EntityDriveable.KEY_LEFT);
        boolean d = driveable.hasInput(EntityDriveable.KEY_RIGHT);
        font.drawString("W: " + (w ? "true" : "false"), 5, y += 10, w ? 0xFFFFFF : 0xFF0000);
        font.drawString("S: " + (s ? "true" : "false"), 5, y += 10, s ? 0xFFFFFF : 0xFF0000);
        font.drawString("A: " + (a ? "true" : "false"), 5, y += 10, a ? 0xFFFFFF : 0xFF0000);
        font.drawString("D: " + (d ? "true" : "false"), 5, y += 10, d ? 0xFFFFFF : 0xFF0000);
        font.drawString("isStarting(): " + (driveable.isStarting() ? "true" : "false"), 5, y += 10, driveable.isStarting() ? 0x0000FF : 0xFFFFFF);
        font.drawString("isStarted(): " + (driveable.isStarted() ? "true" : "false"), 5, y += 10, driveable.isStarted() ? 0x00FF00 : 0xFFFFFF);
        font.drawString(String.format(Locale.ROOT, "Fuel: %.2f / %.2f", driveable.getFuel(), driveable.getFuelTankCapacity()), 5, y += 10, 0xFFFFFF);
        font.drawString("engineIdleTimeTotal: " + driveable.getEngineIdleTimeTotal(), 5, y += 10, 0xFFFFFF);
        font.drawString("turn: " + driveable.getTurn(), 5, y += 10, 0xFFFFFF);
        font.drawString("getTurnSpeed: " + driveable.getTurnSpeed(), 5, y += 10, 0xFFFFFF);
        font.drawString("getAcceleration: " + driveable.getAcceleration(), 5, y += 10, 0xFFFFFF);
        font.drawString("getMaxSpeed: " + driveable.getMaxSpeed(), 5, y += 10, 0xFFFFFF);
        font.drawString("getVelocity: " + driveable.getVelocity(), 5, y += 10, 0xFFFFFF);
        font.drawString("isMovingForward: " + driveable.isMovingForward(), 5, y += 10, 0xFFFFFF);
//        EntityVehiclePart[] parts = driveable.getParts();
//        for (EntityVehiclePart part : parts) {
//            font.drawString(part.getPartInfo(), 10, y += 10, part.isDestroyed() ? 0xFF0000 : 0xFFFFFF);
//        }
        font.drawString("collideVertically: " + (driveable.collidedVertically ? "true" : "false"), 5, y += 10, driveable.collidedVertically ? 0x00FF00 : 0xFF0000);
        font.drawString("collidedHorizontally: " + (driveable.collidedHorizontally ? "true" : "false"), 5, y += 10, driveable.collidedHorizontally ? 0x00FF00 : 0xFF0000);
        font.drawString("onGround: " + (driveable.onGround ? "true" : "false"), 5, y += 10, driveable.onGround ? 0x00FF00 : 0xFF0000);
        font.drawString("isSubmergedInWater: " + (driveable.isSubmergedInWater() ? "true" : "false"), 5, y += 10, driveable.isSubmergedInWater() ? 0x00FF00 : 0xFF0000);
        font.drawString("stepHeight: " + driveable.getStepHeight(), 5, y += 10, 0xFFFFFF);
    }
}
