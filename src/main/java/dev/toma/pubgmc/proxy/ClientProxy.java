package dev.toma.pubgmc.proxy;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.client.event.RegisterGameRendererEvent;
import dev.toma.pubgmc.api.client.event.RegisterMapPointRendererEvent;
import dev.toma.pubgmc.client.ClientEvents;
import dev.toma.pubgmc.client.RenderHandler;
import dev.toma.pubgmc.client.animation.AnimationLoader;
import dev.toma.pubgmc.client.games.GameRendererManager;
import dev.toma.pubgmc.client.games.MapPointRendererManager;
import dev.toma.pubgmc.client.gui.GuiGunWorkbench;
import dev.toma.pubgmc.client.layers.LayerBackpack;
import dev.toma.pubgmc.client.layers.LayerNightVision;
import dev.toma.pubgmc.client.models.equipment.LargeBackpackModel;
import dev.toma.pubgmc.client.models.equipment.MediumBackpackModel;
import dev.toma.pubgmc.client.models.equipment.NightVisionModel;
import dev.toma.pubgmc.client.models.equipment.SmallBackpackModel;
import dev.toma.pubgmc.client.renderer.entity.RenderEnemyAIPlayer;
import dev.toma.pubgmc.client.renderer.entity.RenderFuelCan;
import dev.toma.pubgmc.client.renderer.entity.RenderParachute;
import dev.toma.pubgmc.client.renderer.entity.RenderPlane;
import dev.toma.pubgmc.client.renderer.entity.RenderUAZ;
import dev.toma.pubgmc.client.renderer.entity.RenderDacia;
import dev.toma.pubgmc.client.renderer.item.attachment.*;
import dev.toma.pubgmc.client.renderer.item.gun.WeaponRenderer;
import dev.toma.pubgmc.client.renderer.throwable.RenderThrowable;
import dev.toma.pubgmc.client.renderer.tileentity.LootSpawnerRenderer;
import dev.toma.pubgmc.client.renderer.tileentity.RenderAirdrop;
import dev.toma.pubgmc.client.util.KeyBinds;
import dev.toma.pubgmc.client.util.RecipeButton;
import dev.toma.pubgmc.common.entity.*;
import dev.toma.pubgmc.common.entity.throwables.*;
import dev.toma.pubgmc.common.entity.vehicles.VehicleUAZ;
import dev.toma.pubgmc.common.entity.vehicles.VehicleDacia;
import dev.toma.pubgmc.common.items.attachment.ItemMuzzle;
import dev.toma.pubgmc.common.items.equipment.ItemBackpack;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.items.guns.GunBuilder;
import dev.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import dev.toma.pubgmc.init.PMCBlocks;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Callable;

public class ClientProxy extends Proxy {

    public static Optional<NBTTagCompound> CONFIG_STORE = Optional.empty();
    private static AnimationLoader animationLoader = new AnimationLoader();

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        MinecraftForge.EVENT_BUS.register(new RenderHandler());
        registerEntityRenderers();
        Pubgmc.getContentManager().initialize();
        for (GunBase.GunType type : GunBase.GunType.values()) {
            ResourceLocation location = Pubgmc.getResource("equip_" + type.name().toLowerCase(Locale.ROOT));
            animationLoader.registerEntry(location);
        }
    }

    @Override
    public void init(FMLInitializationEvent e) {
        KeyBinds.registerKeybinding();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLootGenerator.class, new LootSpawnerRenderer());

        Minecraft minecraft = Minecraft.getMinecraft();
        BlockColors blockColors = minecraft.getBlockColors();
        registerBlockColors(blockColors);
        registerItemColors(minecraft.getItemColors(), blockColors);

        registerWeaponRenderers();
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(animationLoader);

        LayerNightVision.registerRenderer(PMCItems.NV_GOGGLES, new NightVisionModel(), WeaponRenderer.ATTACHMENT_TEXTURES);
        registerBackpackModels();

        MinecraftForge.EVENT_BUS.post(new RegisterGameRendererEvent(GameRendererManager.INSTANCE::registerGameRenderer));
        MinecraftForge.EVENT_BUS.post(new RegisterMapPointRendererEvent(MapPointRendererManager.INSTANCE::registerRenderer));
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
        RenderingRegistry.registerEntityRenderingHandler(EntityC4.class, manager -> new RenderThrowable<>(manager, PMCItems.C4));
        RenderingRegistry.registerEntityRenderingHandler(EntityFuelCan.class, RenderFuelCan::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityParachute.class, RenderParachute::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAirdrop.class, RenderAirdrop::new);
        RenderingRegistry.registerEntityRenderingHandler(VehicleUAZ.class, RenderUAZ::new);
        RenderingRegistry.registerEntityRenderingHandler(VehicleDacia.class, RenderDacia::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPlane.class, RenderPlane::new);
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

    private static void registerBlockColors(BlockColors colors) {
        colors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> {
            if (tintIndex != 0) {
                return -1;
            }
            if (worldIn != null && pos != null) {
                return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
            } else {
                return ColorizerFoliage.getFoliageColorBasic();
            }
        }, PMCBlocks.BUSH, PMCBlocks.BUSH_HALF);
    }

    private static void registerItemColors(ItemColors colors, BlockColors blockColors) {
        colors.registerItemColorHandler((stack, tintIndex) -> stack.hasTagCompound() && stack.getTagCompound().hasKey("ghillieColor")
                ? stack.getTagCompound().getInteger("ghillieColor")
                : 0x359E35, PMCItems.GHILLIE_SUIT);
        colors.registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex != 0)
                return -1;
            IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
            return blockColors.colorMultiplier(iblockstate, null, null, tintIndex);
        }, PMCBlocks.BUSH, PMCBlocks.BUSH_HALF);
    }
}
