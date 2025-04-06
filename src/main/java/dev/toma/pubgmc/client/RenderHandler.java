package dev.toma.pubgmc.client;

import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.common.items.attachment.ScopeZoom;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.config.client.CFGOtherSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHandler {

    private static Float fovBackup;
    private static Float sensBackup;
    private static int viewBackup = -1;

    private double interpolate(double current, double previous, double partial) {
        return previous + (current - previous) * partial;
    }

    public static void saveCurrentOptions() {
        Minecraft minecraft = Minecraft.getMinecraft();
        GameSettings settings = minecraft.gameSettings;
        fovBackup = settings.fovSetting;
        sensBackup = settings.mouseSensitivity;
        viewBackup = settings.thirdPersonView;
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
        if (viewBackup != -1) { // this doesn't work with Shoulder Surfing Reloaded
            CFGOtherSettings config = ConfigPMC.other();
            if (config.backupPerspective.get()) {
                settings.thirdPersonView = viewBackup;
            }
        }
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
