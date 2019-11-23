package com.toma.pubgmc.proxy;

import com.toma.pubgmc.DevUtil;
import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.ClientEvents;
import com.toma.pubgmc.client.RenderHandler;
import com.toma.pubgmc.client.gui.GuiGunWorkbench;
import com.toma.pubgmc.client.renderer.entity.*;
import com.toma.pubgmc.client.renderer.throwable.RenderThrowable;
import com.toma.pubgmc.client.renderer.tileentity.LootSpawnerRenderer;
import com.toma.pubgmc.client.renderer.tileentity.RenderAirdrop;
import com.toma.pubgmc.client.util.KeyBinds;
import com.toma.pubgmc.client.util.ModelHelper;
import com.toma.pubgmc.client.util.RecipeButton;
import com.toma.pubgmc.common.entity.EntityAirdrop;
import com.toma.pubgmc.common.entity.EntityParachute;
import com.toma.pubgmc.common.entity.EntityPlane;
import com.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import com.toma.pubgmc.common.entity.throwables.EntityFlashBang;
import com.toma.pubgmc.common.entity.throwables.EntityFragGrenade;
import com.toma.pubgmc.common.entity.throwables.EntityMolotov;
import com.toma.pubgmc.common.entity.throwables.EntitySmokeGrenade;
import com.toma.pubgmc.common.entity.vehicles.EntityVehicleDacia;
import com.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;
import com.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.color.ItemColors;
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

public class ClientProxy extends Proxy {

    private static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityFragGrenade.class, manager -> new RenderThrowable(manager, PMCRegistry.PMCItems.GRENADE));
        RenderingRegistry.registerEntityRenderingHandler(EntitySmokeGrenade.class, manager -> new RenderThrowable(manager, PMCRegistry.PMCItems.SMOKE));
        RenderingRegistry.registerEntityRenderingHandler(EntityMolotov.class, manager -> new RenderThrowable(manager, PMCRegistry.PMCItems.MOLOTOV));
        RenderingRegistry.registerEntityRenderingHandler(EntityFlashBang.class, manager -> new RenderThrowable(manager, PMCRegistry.PMCItems.FLASHBANG));
        RenderingRegistry.registerEntityRenderingHandler(EntityParachute.class, RenderParachute::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAirdrop.class, RenderAirdrop::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityVehicleUAZ.class, RenderUAZ::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPlane.class, RenderPlane::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityVehicleDacia.class, RenderDacia::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAIPlayer.class, RenderEnemyAIPlayer::new);
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
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLootGenerator.class, new LootSpawnerRenderer());
        }
        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
        itemColors.registerItemColorHandler((stack, tintIndex) -> stack.hasTagCompound() && stack.getTagCompound().hasKey("ghillieColor") ? stack.getTagCompound().getInteger("ghillieColor") : 0x359E35, PMCRegistry.PMCItems.GHILLIE_SUIT);
    }

    @SideOnly(Side.CLIENT)
    public void postInit(FMLPostInitializationEvent e) {
    }

    @Override
    public void notifyWorkbenchUpdate() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.currentScreen instanceof GuiGunWorkbench) {
            GuiGunWorkbench gui = (GuiGunWorkbench) mc.currentScreen;
            gui.getButtonList().stream().filter(b -> b instanceof RecipeButton).forEach(b -> ((RecipeButton) b).performIngredientCheck());
        }
    }

    @Override
    public void playMCDelayedSound(SoundEvent event, double x, double y, double z, float volume, int delay) {
        SoundHandler handler = Minecraft.getMinecraft().getSoundHandler();
        PositionedSoundRecord sound = new PositionedSoundRecord(event, SoundCategory.MASTER, volume, 1.0F, (float) x, (float) y, (float) z);
        handler.playDelayedSound(sound, delay);
    }

    @Override
    public void playDelayedSound(SoundEvent event, double x, double y, double z, float volume) {
        PositionedSoundRecord sound = new PositionedSoundRecord(event, SoundCategory.MASTER, volume, 1f, (float) x, (float) y, (float) z);
        Minecraft mc = Minecraft.getMinecraft();
        double distance = PUBGMCUtil.getDistanceToBlockPos3D(new BlockPos(x, y, z), mc.getRenderViewEntity().getPosition());
        int ticks = (int) ((distance / 34) * 5);
        mc.getSoundHandler().playDelayedSound(sound, ticks);
    }

    @Override
    public void resetMouseSens() {
        Minecraft.getMinecraft().gameSettings.mouseSensitivity = ClientEvents.mouseSens;
    }
}
