package dev.toma.pubgmc;

import dev.toma.pubgmc.client.content.ContentManager;
import dev.toma.pubgmc.common.CommonEvents;
import dev.toma.pubgmc.common.capability.IGameData;
import dev.toma.pubgmc.common.capability.IPlayerData;
import dev.toma.pubgmc.common.capability.IWorldData;
import dev.toma.pubgmc.common.commands.*;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.event.GunPostInitializeEvent;
import dev.toma.pubgmc.init.PMCRegistry;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.proxy.Proxy;
import dev.toma.pubgmc.util.handlers.GuiHandler;
import dev.toma.pubgmc.util.recipes.RecipeRegistry;
import dev.toma.pubgmc.world.OreGen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameRules;
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
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = Pubgmc.MOD_ID, name = Pubgmc.NAME, version = Pubgmc.VERSION, updateJSON = Pubgmc.UPDATEURL, dependencies = Pubgmc.DEPENDENCIES)
public class Pubgmc {
    /**
     * Some basic stuff
     **/
    public static final String MOD_ID = "pubgmc";
    public static final String NAME = "PUBGMC";
    public static final String VERSION = "1.6.2";
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final String CLIENT_PROXY_CLASS = "dev.toma.pubgmc.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "dev.toma.pubgmc.proxy.ServerProxy";
    public static final String UPDATEURL = "https://raw.githubusercontent.com/Toma1O6/PUBGMC/master/update.json";
    public static final String DEPENDENCIES = "required-after:configuration@[1.0.2.3,)";
    private static final Random RANDOM = new Random();
    public static final Logger logger = LogManager.getLogger("pubgmc");
    public static boolean isDevEnvironment;
    @Instance
    public static Pubgmc instance;
    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
    public static Proxy proxy;
    private static final ContentManager contentManager = new ContentManager();

    public static Random rng() {
        return RANDOM;
    }

    // TODO move to LoadWorldEvent
    private static void registerGamerules(FMLServerStartingEvent e) {
        GameRules gr = e.getServer().getWorld(0).getGameRules();
        if (!gr.hasRule("weaponCrafting")) {
            gr.addGameRule("weaponCrafting", "true", GameRules.ValueType.BOOLEAN_VALUE);
        }
        if (!gr.hasRule("weaponGriefing")) {
            gr.addGameRule("weaponGriefing", "true", GameRules.ValueType.BOOLEAN_VALUE);
        }
        logger.log(Level.INFO, "Registered gamerules");
    }

    private static void registerSmeltingRecipes() {
        FurnaceRecipes rec = FurnaceRecipes.instance();
        rec.addSmeltingRecipeForBlock(PMCRegistry.PMCBlocks.COPPER_ORE, new ItemStack(PMCRegistry.PMCItems.COPPER_INGOT, 1), 2f);
        rec.addSmelting(PMCRegistry.PMCItems.STEEL_DUST, new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 1), 2f);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        isDevEnvironment = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
        PMCSounds.registerSounds();
        PacketHandler.initialize();

        MinecraftForge.EVENT_BUS.register(new CommonEvents());

        CapabilityManager.INSTANCE.register(IWorldData.class, new IWorldData.WorldDataStorage(), IWorldData.WorldData::new);
        CapabilityManager.INSTANCE.register(IPlayerData.class, new IPlayerData.PlayerDataStorage(), IPlayerData.PlayerData::new);
        CapabilityManager.INSTANCE.register(IGameData.class, new IGameData.GameDataStorage(), IGameData.GameData::new);

        dev.toma.pubgmc.init.GameRegistry.dispatchRegistryEvent();

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Pubgmc.instance, new GuiHandler());
        PMCRegistry.Registry.initTileEntities();

        registerSmeltingRecipes();

        proxy.init(event);
        GameRegistry.registerWorldGenerator(new OreGen(), 4);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        for (ResourceLocation rl : ForgeRegistries.ITEMS.getKeys()) {
            if (rl.getResourceDomain().equals(MOD_ID)) {
                Item item = ForgeRegistries.ITEMS.getValue(rl);
                if (item instanceof GunBase) {
                    MinecraftForge.EVENT_BUS.post(new GunPostInitializeEvent((GunBase) item));
                }
            }
        }
        RecipeRegistry.registerWorkbenchRecipes();
        proxy.postInit(event);
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandLeave());
        event.registerServerCommand(new CommandLootGenerate());
        event.registerServerCommand(new CommandClearPlayerCrates());
        event.registerServerCommand(new CommandAirdrop());
        event.registerServerCommand(new CommandGame());
        event.registerServerCommand(new CommandPlayerData());
        event.registerServerCommand(new CommandManageGhillieVariants());

        registerGamerules(event);

        logger.log(Level.INFO, "Registered commands");
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
