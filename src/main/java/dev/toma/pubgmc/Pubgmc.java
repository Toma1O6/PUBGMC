package dev.toma.pubgmc;

import dev.toma.pubgmc.api.PubgmcRegistries;
import dev.toma.pubgmc.api.capability.GameData;
import dev.toma.pubgmc.api.event.PubgmcRegistryEvent;
import dev.toma.pubgmc.client.content.ContentManager;
import dev.toma.pubgmc.common.CommonEvents;
import dev.toma.pubgmc.common.capability.SimpleStorageImpl;
import dev.toma.pubgmc.common.capability.game.GameDataImpl;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.commands.AirdropCommand;
import dev.toma.pubgmc.common.commands.GameCommand;
import dev.toma.pubgmc.common.commands.LootCommand;
import dev.toma.pubgmc.common.games.util.GameConfigurationManager;
import dev.toma.pubgmc.data.loot.LootManager;
import dev.toma.pubgmc.init.CommonRegistry;
import dev.toma.pubgmc.init.PMCBlocks;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.proxy.Proxy;
import dev.toma.pubgmc.util.handlers.GuiHandler;
import dev.toma.pubgmc.util.recipes.RecipeRegistry;
import dev.toma.pubgmc.world.OreGen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = Pubgmc.MOD_ID, name = Pubgmc.NAME, version = Pubgmc.VERSION, updateJSON = Pubgmc.UPDATEURL, dependencies = Pubgmc.DEPENDENCIES)
public class Pubgmc {

    public static final String MOD_ID = "pubgmc";
    public static final String NAME = "PUBGMC";
    public static final String VERSION = "1.8.1";
    public static final String CLIENT_PROXY_CLASS = "dev.toma.pubgmc.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "dev.toma.pubgmc.proxy.ServerProxy";
    public static final String UPDATEURL = "https://raw.githubusercontent.com/Toma1O6/PUBGMC/master/update.json";
    public static final String DEPENDENCIES = "required-after:configuration@[1.0.3.1,)";
    public static final int CONTENT_DATA_VERSION = 1;

    private static final Random RANDOM = new Random();
    public static final Logger logger = LogManager.getLogger("pubgmc");

    public static boolean isDevEnvironment;
    @Instance
    public static Pubgmc instance;
    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
    public static Proxy proxy;
    private static final ContentManager contentManager = new ContentManager();
    private static Boolean earlyAccess;

    public static Random rng() {
        return RANDOM;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        isDevEnvironment = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
        PMCSounds.registerSounds();
        PacketHandler.initialize();

        MinecraftForge.EVENT_BUS.register(new CommonEvents());

        CapabilityManager.INSTANCE.register(IPlayerData.class, SimpleStorageImpl.instance(), PlayerData::new);
        CapabilityManager.INSTANCE.register(GameData.class, SimpleStorageImpl.instance(), GameDataImpl::new);

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Pubgmc.instance, new GuiHandler());
        CommonRegistry.initTileEntities();
        registerSmeltingRecipes();
        dispatchRegistryEvents();
        proxy.init(event);
        GameRegistry.registerWorldGenerator(new OreGen(), 4);
        LootManager.load();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        RecipeRegistry.registerWorkbenchRecipes();
        proxy.postInit(event);
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        event.registerServerCommand(new AirdropCommand());
        event.registerServerCommand(new LootCommand());
        event.registerServerCommand(new GameCommand());
    }

    private static void registerSmeltingRecipes() {
        FurnaceRecipes rec = FurnaceRecipes.instance();
        rec.addSmeltingRecipeForBlock(PMCBlocks.COPPER_ORE, new ItemStack(PMCItems.COPPER_INGOT, 1), 2f);
        rec.addSmelting(PMCItems.STEEL_DUST, new ItemStack(PMCItems.STEEL_INGOT, 1), 2f);
    }

    private static void dispatchRegistryEvents() {
        MinecraftForge.EVENT_BUS.post(new PubgmcRegistryEvent.LootProvider());
        PubgmcRegistries.LOOT_PROVIDERS.lock();
        MinecraftForge.EVENT_BUS.post(new PubgmcRegistryEvent.LootProcessor());
        PubgmcRegistries.LOOT_PROCESSORS.lock();
        MinecraftForge.EVENT_BUS.post(new PubgmcRegistryEvent.Game());
        PubgmcRegistries.GAME_TYPES.lock();
        MinecraftForge.EVENT_BUS.post(new PubgmcRegistryEvent.Area());
        PubgmcRegistries.GAME_AREA_TYPES.lock();
        MinecraftForge.EVENT_BUS.post(new PubgmcRegistryEvent.PointType());
        PubgmcRegistries.GAME_MAP_POINTS.lock();

        GameConfigurationManager.loadConfigurations();
    }

    public static boolean isOutdated() {
        ModContainer container = Loader.instance().activeModContainer();
        if(container == null)
            return false;
        ForgeVersion.CheckResult result = ForgeVersion.getResult(container);
        ForgeVersion.Status status = result.status;
        return status == ForgeVersion.Status.OUTDATED || status == ForgeVersion.Status.BETA_OUTDATED;
    }

    public static ResourceLocation getResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static ContentManager getContentManager() {
        return contentManager;
    }
}
