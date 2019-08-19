package com.toma.pubgmc.proxy;

import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.DevUtil;
import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.ClientEvents;
import com.toma.pubgmc.client.RenderHandler;
import com.toma.pubgmc.client.gui.GuiGunWorkbench;
import com.toma.pubgmc.client.models.ModelGhillie;
import com.toma.pubgmc.client.renderer.*;
import com.toma.pubgmc.client.util.KeyBinds;
import com.toma.pubgmc.client.util.ModelHelper;
import com.toma.pubgmc.client.util.RecipeButton;
import com.toma.pubgmc.common.entity.*;
import com.toma.pubgmc.common.entity.vehicles.EntityVehicleDacia;
import com.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;
import com.toma.pubgmc.common.tileentity.TileEntityLootSpawner;
import com.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy implements IProxy {

    private static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, RenderGrenade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySmokeGrenade.class, RenderSmokeGrenade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMolotov.class, RenderMolotov::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFlashbang.class, RenderFlashbang::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityParachute.class, RenderParachute::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAirdrop.class, RenderAirdrop::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityVehicleUAZ.class, RenderUAZ::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPlane.class, RenderPlane::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityVehicleDacia.class, RenderDacia::new);
    }

    @SideOnly(Side.CLIENT)
    public void preInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        MinecraftForge.EVENT_BUS.register(new RenderHandler());
        registerEntityRenderers();
    }

    @SideOnly(Side.CLIENT)
    public void init(FMLInitializationEvent e) {
        KeyBinds.registerKeybinding();
        if (DevUtil.isDev()) {
            ModelHelper.init();
            Pubgmc.logger.info("Initialized model debugger");
        }
        if (ConfigPMC.client.other.lootRenderStyle.ordinal() < 2) {
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLootSpawner.class, new LootSpawnerRenderer());
        }
    }

    @SideOnly(Side.CLIENT)
    public void postInit(FMLPostInitializationEvent e) {
    }

    @Override
    public void notifyWorkbenchUpdate() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.currentScreen instanceof GuiGunWorkbench) {
            GuiGunWorkbench gui = (GuiGunWorkbench) mc.currentScreen;
            gui.getButtonList().stream().filter(b -> b instanceof RecipeButton).forEach(b -> {
                ((RecipeButton) b).performIngredientCheck();
            });
        }
    }

    @Override
    public void playDelayedSound(SoundEvent event, double x, double y, double z, float volume) {
        PositionedSoundRecord sound = new PositionedSoundRecord(event, SoundCategory.MASTER, volume, 1f, (float) x, (float) y, (float) z);
        Minecraft mc = Minecraft.getMinecraft();
        double distance = PUBGMCUtil.getDistanceToBlockPos3D(new BlockPos(x, y, z), mc.getRenderViewEntity().getPosition());
        int ticks = (int) ((distance / 34) * 5);
        mc.getSoundHandler().playDelayedSound(sound, ticks);
    }
}
