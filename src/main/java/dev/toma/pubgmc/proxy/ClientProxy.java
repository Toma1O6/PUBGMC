package dev.toma.pubgmc.proxy;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.ClientEvents;
import dev.toma.pubgmc.client.RenderHandler;
import dev.toma.pubgmc.client.animation.AnimationLoader;
import dev.toma.pubgmc.client.gui.GuiGunWorkbench;
import dev.toma.pubgmc.client.layers.LayerBackpack;
import dev.toma.pubgmc.client.layers.LayerNightVision;
import dev.toma.pubgmc.client.models.equipment.LargeBackpackModel;
import dev.toma.pubgmc.client.models.equipment.MediumBackpackModel;
import dev.toma.pubgmc.client.models.equipment.NightVisionModel;
import dev.toma.pubgmc.client.models.equipment.SmallBackpackModel;
import dev.toma.pubgmc.client.renderer.entity.*;
import dev.toma.pubgmc.client.renderer.item.attachment.*;
import dev.toma.pubgmc.client.renderer.item.gun.WeaponRenderer;
import dev.toma.pubgmc.client.renderer.throwable.RenderThrowable;
import dev.toma.pubgmc.client.renderer.tileentity.LootSpawnerRenderer;
import dev.toma.pubgmc.client.renderer.tileentity.RenderAirdrop;
import dev.toma.pubgmc.client.util.KeyBinds;
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
import dev.toma.pubgmc.common.items.attachment.ItemMuzzle;
import dev.toma.pubgmc.common.items.equipment.ItemBackpack;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.items.guns.GunBuilder;
import dev.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
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

import java.util.concurrent.Callable;

public class ClientProxy extends Proxy {

    private static AnimationLoader animationLoader = new AnimationLoader();

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        MinecraftForge.EVENT_BUS.register(new RenderHandler());
        registerEntityRenderers();
        Pubgmc.getContentManager().initialize();
        for (GunBase.GunType type : GunBase.GunType.values()) {
            ResourceLocation location = Pubgmc.getResource("equip_" + type.name().toLowerCase());
            animationLoader.registerEntry(location);
        }
    }

    @Override
    public void init(FMLInitializationEvent e) {
        KeyBinds.registerKeybinding();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLootGenerator.class, new LootSpawnerRenderer());
        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
        itemColors.registerItemColorHandler((stack, tintIndex) -> stack.hasTagCompound() && stack.getTagCompound().hasKey("ghillieColor") ? stack.getTagCompound().getInteger("ghillieColor") : 0x359E35, PMCItems.GHILLIE_SUIT);
        registerWeaponRenderers();
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(animationLoader);

        LayerNightVision.registerRenderer(PMCItems.NV_GOGGLES, new NightVisionModel(), WeaponRenderer.ATTACHMENT_TEXTURES);
        registerBackpackModels();
    }

    @Override
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
    public void initWeapon(GunBuilder builder, GunBase gunBase) {
        Callable<WeaponRenderer> callable = builder.getRenderer().get();
        try {
            gunBase.setTileEntityItemStackRenderer(callable.call());
        } catch (Exception e) {
            Pubgmc.logger.fatal(e);
        }
    }

    public static AnimationLoader getAnimationLoader() {
        return animationLoader;
    }

    static void registerWeaponRenderers() {
        AttachmentRenderer.registerRenderer(PMCItems.RED_DOT, new RedDotRenderer());
        AttachmentRenderer.registerRenderer(PMCItems.HOLOGRAPHIC, new HolographicRenderer());
        AttachmentRenderer.registerRenderer(PMCItems.SCOPE2X, new Scope2xRenderer());
        AttachmentRenderer.registerRenderer(PMCItems.SCOPE4X, new Scope4xRenderer());
        AttachmentRenderer.registerRenderer(PMCItems.SCOPE8X, new Scope8xRenderer());
        AttachmentRenderer.registerRenderer(PMCItems.SCOPE15X, new Scope15xRenderer());
        AttachmentRenderer.registerRenderer(PMCItems.GRIP_VERTICAL, new VerticalGripRenderer());
        AttachmentRenderer.registerRenderer(PMCItems.GRIP_ANGLED, new AngledGripRenderer());
        AttachmentRenderer<ItemMuzzle> suppressors = new SuppressorRenderer();
        AttachmentRenderer.registerRenderer(PMCItems.SILENCER_SMG, suppressors);
        AttachmentRenderer.registerRenderer(PMCItems.SILENCER_AR, suppressors);
        AttachmentRenderer.registerRenderer(PMCItems.SILENCER_SNIPER, suppressors);
        ForgeRegistries.ITEMS.getValuesCollection().stream()
                .filter(it -> it instanceof GunBase)
                .map(it -> (GunBase) it)
                .forEach(gun -> ((WeaponRenderer) gun.getTileEntityItemStackRenderer()).init(gun));
    }

    static void registerEntityRenderers() {
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

    public static void playButtonPressSound() {
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    private static void registerBackpackModels() {
        registerBackpackModel(PMCItems.SMALL_BACKPACK_FOREST, SmallBackpackModel.MODEL);
        registerBackpackModel(PMCItems.SMALL_BACKPACK_DESERT, SmallBackpackModel.MODEL);
        registerBackpackModel(PMCItems.SMALL_BACKPACK_SNOW, SmallBackpackModel.MODEL);
        registerBackpackModel(PMCItems.MEDIUM_BACKPACK_FOREST, MediumBackpackModel.MODEL);
        registerBackpackModel(PMCItems.MEDIUM_BACKPACK_DESERT, MediumBackpackModel.MODEL);
        registerBackpackModel(PMCItems.MEDIUM_BACKPACK_SNOW, MediumBackpackModel.MODEL);
        registerBackpackModel(PMCItems.LARGE_BACKPACK_FOREST, LargeBackpackModel.MODEL);
        registerBackpackModel(PMCItems.LARGE_BACKPACK_DESERT, LargeBackpackModel.MODEL);
        registerBackpackModel(PMCItems.LARGE_BACKPACK_SNOW, LargeBackpackModel.MODEL);
    }

    private static void registerBackpackModel(ItemBackpack item, ModelBase model) {
        LayerBackpack.registerRenderer(item, model, item.getVariant().getTexture());
    }
}
