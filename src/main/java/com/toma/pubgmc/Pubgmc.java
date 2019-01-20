package com.toma.pubgmc;

import java.io.File;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import com.toma.pubgmc.common.CommonEvents;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataStorage;
import com.toma.pubgmc.common.commands.CommandAirdrop;
import com.toma.pubgmc.common.commands.CommandClearPlayerCrates;
import com.toma.pubgmc.common.commands.CommandLeave;
import com.toma.pubgmc.common.commands.CommandLootGenerate;
import com.toma.pubgmc.common.commands.CommandResetPlayerData;
import com.toma.pubgmc.common.entity.EntityBullet;
import com.toma.pubgmc.common.entity.EntityFlare;
import com.toma.pubgmc.common.entity.EntityGrenade;
import com.toma.pubgmc.common.entity.EntityMolotov;
import com.toma.pubgmc.common.entity.EntityParachute;
import com.toma.pubgmc.common.entity.EntitySmokeGrenade;
import com.toma.pubgmc.common.entity.vehicles.EntityTestVehicle;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.init.PMCBlocks;
import com.toma.pubgmc.init.PMCItems;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.init.RegistryHandler;
import com.toma.pubgmc.proxy.IProxy;
import com.toma.pubgmc.tabs.PMCBlocksTab;
import com.toma.pubgmc.tabs.PMCItemsTab;
import com.toma.pubgmc.util.VehicleSpawnerRegistry;
import com.toma.pubgmc.util.handlers.ConfigHandler;
import com.toma.pubgmc.util.handlers.GuiHandler;
import com.toma.pubgmc.world.OreGen;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Pubgmc.MOD_ID, name = Pubgmc.NAME, version = Pubgmc.VERSION, updateJSON = "https://minecraft.curseforge.com/projects/pubgmc-mod/files")
public class Pubgmc
{
	/** Some basic stuff **/
	public static final String MOD_ID = "pubgmc";
	public static final String NAME = "PUBGMC";
	public static final String VERSION = "1.4.0-a";
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";
	public static final String CLIENT_PROXY_CLASS = "com.toma.pubgmc.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.toma.pubgmc.proxy.ServerProxy";
	
	/** Creative Tabs **/
	public static final CreativeTabs pmcitemstab = new PMCItemsTab("pmcitemstab");
	public static final CreativeTabs pmcblockstab = new PMCBlocksTab("pmcblockstab");
	
	/** Config **/
	public static File config;
	
	public static Logger logger;
	
	@Instance
	public static Pubgmc instance;
	
	@SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		PMCSounds.registerSounds();
		PacketHandler.initialize();
		
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
		CapabilityManager.INSTANCE.register(IPlayerData.class, new PlayerDataStorage(), PlayerData.class);
		ConfigHandler.registerConfig(event);
		
		proxy.preInit(event);
		
		logger.log(Level.INFO, "Registered sounds and events");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(Pubgmc.instance, new GuiHandler());
		RegistryHandler.registerTileEntities();
		
		registerSmeltingRecipes();
		
		registerEntities();
		proxy.init(event);
		GameRegistry.registerWorldGenerator(new OreGen(), 4);
		logger.log(Level.INFO, "Initialized");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
	
	
	@EventHandler
	public void serverInit(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandLeave());
		event.registerServerCommand(new CommandLootGenerate());
		event.registerServerCommand(new CommandClearPlayerCrates());
		event.registerServerCommand(new CommandAirdrop());
		event.registerServerCommand(new CommandResetPlayerData());
		
		registerGamerules(event);
		
		logger.log(Level.INFO, "Registered commands");
	}
	
	private static void registerEntities()
	{
		//these values will need some more work
		EntityRegistry.registerModEntity(new ResourceLocation(Pubgmc.MOD_ID + ":bullet"), EntityBullet.class, Pubgmc.MOD_ID + ":bullet_entity", 0, instance, 64, 80, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Pubgmc.MOD_ID + ":grenade"), EntityGrenade.class, Pubgmc.MOD_ID + ":grenade_entity", 1, instance, 64, 20, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Pubgmc.MOD_ID + ":smokegrenade"), EntitySmokeGrenade.class, Pubgmc.MOD_ID + ":smokegrenade_entity", 2, instance, 64, 20, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Pubgmc.MOD_ID + ":molotov"), EntityMolotov.class, Pubgmc.MOD_ID + ":molotov_entity", 3, instance, 64, 20, true);
		//EntityRegistry.registerModEntity(new ResourceLocation(Main.MOD_ID + ":flashbang"), EntityFlashbang.class, Main.MOD_ID + ":flashbang", 4, this, 64, 80, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Pubgmc.MOD_ID + ":flare"), EntityFlare.class, Pubgmc.MOD_ID + ":flare_entity", 5, instance, 64, 80, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Pubgmc.MOD_ID + ":parachute"), EntityParachute.class, Pubgmc.MOD_ID + ":parachute", 6, instance, 256, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Pubgmc.MOD_ID + ":testvehicle"), EntityTestVehicle.class, MOD_ID + ":testvehicle", 7, instance, 256, 50, true);
		
		registerVehicles();
	}
	
	/**
	 * Gamerule registry
	 * @param e - the event
	 */
	private static void registerGamerules(FMLServerStartingEvent e)
	{
		GameRules gr = e.getServer().getWorld(0).getGameRules();
		
		if(!gr.hasRule("weaponCrafting"))
		{
			gr.addGameRule("weaponCrafting", "true", GameRules.ValueType.BOOLEAN_VALUE);
		}
		
		if(!gr.hasRule("weaponGriefing"))
		{
			gr.addGameRule("weaponGriefing", "true", GameRules.ValueType.BOOLEAN_VALUE);
		}
		
		if(!gr.hasRule("weaponKnockback"))
		{
			gr.addGameRule("weaponKnockback", "false", GameRules.ValueType.BOOLEAN_VALUE);
		}

		logger.log(Level.INFO, "Registered gamerules");
	}
	
	private static void registerSmeltingRecipes()
	{
		FurnaceRecipes rec = FurnaceRecipes.instance();
		rec.addSmeltingRecipeForBlock(PMCBlocks.COPPER_ORE, new ItemStack(PMCItems.COPPER_INGOT, 1), 2f);
		rec.addSmelting(PMCItems.STEEL_DUST, new ItemStack(PMCItems.STEEL_INGOT, 1), 2f);
	}
	
	private static void registerVehicles()
	{
		VehicleSpawnerRegistry.registerVehicle(0, new EntityTestVehicle(null));
	}
}
