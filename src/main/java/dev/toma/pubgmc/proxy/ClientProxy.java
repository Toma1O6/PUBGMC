package dev.toma.pubgmc.proxy;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.ClientEvents;
import dev.toma.pubgmc.client.RenderHandler;
import dev.toma.pubgmc.client.gui.GuiGunWorkbench;
import dev.toma.pubgmc.client.renderer.WeaponRenderer;
import dev.toma.pubgmc.client.renderer.entity.*;
import dev.toma.pubgmc.client.renderer.throwable.RenderThrowable;
import dev.toma.pubgmc.client.renderer.tileentity.LootSpawnerRenderer;
import dev.toma.pubgmc.client.renderer.tileentity.RenderAirdrop;
import dev.toma.pubgmc.client.util.KeyBinds;
import dev.toma.pubgmc.client.util.ModelHelper;
import dev.toma.pubgmc.client.util.RecipeButton;
import dev.toma.pubgmc.common.entity.EntityAirdrop;
import dev.toma.pubgmc.common.entity.EntityParachute;
import dev.toma.pubgmc.common.entity.EntityPlane;
import dev.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import dev.toma.pubgmc.common.entity.throwables.EntityFlashBang;
import dev.toma.pubgmc.common.entity.throwables.EntityFragGrenade;
import dev.toma.pubgmc.common.entity.throwables.EntityMolotov;
import dev.toma.pubgmc.common.entity.throwables.EntitySmokeGrenade;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehicleDacia;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.PUBGMCUtil;
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
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends Proxy {

    private static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityFragGrenade.class, manager -> new RenderThrowable<>(manager, PMCItems.GRENADE));
        RenderingRegistry.registerEntityRenderingHandler(EntitySmokeGrenade.class, manager -> new RenderThrowable<>(manager, PMCItems.SMOKE));
        RenderingRegistry.registerEntityRenderingHandler(EntityMolotov.class, manager -> new RenderThrowable<>(manager, PMCItems.MOLOTOV));
        RenderingRegistry.registerEntityRenderingHandler(EntityFlashBang.class, manager -> new RenderThrowable<>(manager, PMCItems.FLASHBANG));
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
        Pubgmc.getContentManager().initialize();
    }

    @SideOnly(Side.CLIENT)
    public void init(FMLInitializationEvent e) {
        KeyBinds.registerKeybinding();
        if (DevUtil.isDev()) {
            ModelHelper.init();
            Pubgmc.logger.info("Initialized model debugger");
        }
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLootGenerator.class, new LootSpawnerRenderer());
        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
        itemColors.registerItemColorHandler((stack, tintIndex) -> stack.hasTagCompound() && stack.getTagCompound().hasKey("ghillieColor") ? stack.getTagCompound().getInteger("ghillieColor") : 0x359E35, PMCItems.GHILLIE_SUIT);
    }

    @SideOnly(Side.CLIENT)
    public void postInit(FMLPostInitializationEvent e) {
        // TODO call once weapons use new system
        /*
        ForgeRegistries.ITEMS.getValuesCollection().stream()
                .filter(it -> it instanceof GunBase)
                .map(it -> (WeaponRenderer) it.getTileEntityItemStackRenderer())
                .forEach(WeaponRenderer::registerAttachmentRenders);
        */
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
}
