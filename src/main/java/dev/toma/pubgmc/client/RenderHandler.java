package dev.toma.pubgmc.client;

import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.common.items.attachment.ScopeData;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.OptionalDouble;

public class RenderHandler {

    private static OptionalDouble fovBackup = OptionalDouble.empty();
    private static OptionalDouble sensBackup = OptionalDouble.empty();

    private double interpolate(double current, double previous, double partial) {
        return previous + (current - previous) * partial;
    }

    public static void saveCurrentOptions() {
        Minecraft minecraft = Minecraft.getMinecraft();
        GameSettings settings = minecraft.gameSettings;
        fovBackup = OptionalDouble.of(settings.fovSetting);
        sensBackup = OptionalDouble.of(settings.mouseSensitivity);
    }

    public static void restore() {
        fovBackup.ifPresent(fov -> Minecraft.getMinecraft().gameSettings.fovSetting = (float) fov);
        sensBackup.ifPresent(sens -> Minecraft.getMinecraft().gameSettings.mouseSensitivity = (float) sens);
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
                ScopeData scopeData = gunBase.getScopeData(stack);
                if (scopeData != null && scopeData.getZoom() > 0) {
                    settings.fovSetting = scopeData.getZoom();
                }
            }
        }
    }
}
