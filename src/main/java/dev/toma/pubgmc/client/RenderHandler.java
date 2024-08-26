package dev.toma.pubgmc.client;

import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.common.items.attachment.ScopeZoom;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHandler {

    private static Float fovBackup;
    private static Float sensBackup;

    private double interpolate(double current, double previous, double partial) {
        return previous + (current - previous) * partial;
    }

    public static void saveCurrentOptions() {
        Minecraft minecraft = Minecraft.getMinecraft();
        GameSettings settings = minecraft.gameSettings;
        fovBackup = settings.fovSetting;
        sensBackup = settings.mouseSensitivity;
    }

    public static void restore() {
        Minecraft minecraft = Minecraft.getMinecraft();
        GameSettings settings = minecraft.gameSettings;
        if (fovBackup != null) {
            settings.fovSetting = fovBackup;
        }
        if (sensBackup != null) {
            settings.mouseSensitivity = sensBackup;
        }
    }

    public static void restoreAndSaveValues() {
        restore();
        saveCurrentOptions();
    }

    @SubscribeEvent
    public void onFOVChanged(FOVUpdateEvent e) {
        EntityPlayer player = e.getEntity();
        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        if (data.getAimInfo().isAiming()) {
            ItemStack stack = player.getHeldItemMainhand();
            if (stack.getItem() instanceof GunBase) {
                GunBase gunBase = (GunBase) stack.getItem();
                ScopeZoom zoom = gunBase.getScopeData(stack);
                if (zoom != null) {
                    float zoomFov = zoom.getCurrentZoom(gunBase, MixinClientHooks.getRenderTickTime());
                    if (zoomFov > 0) {
                        settings.fovSetting = zoomFov;
                    }
                }
            }
        }
    }
}
