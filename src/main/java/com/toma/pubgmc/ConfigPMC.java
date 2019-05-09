package com.toma.pubgmc;

import com.toma.pubgmc.util.VehicleConfiguration;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresWorldRestart;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Pubgmc.MOD_ID, name = Pubgmc.NAME + " Config")
public class ConfigPMC
{
	@Name("Client-side only options")
	public static ClientConfig client = new ClientConfig();
	
	@Name("Common options")
	public static CommonConfig common = new CommonConfig();
	
	public static class CommonConfig
	{
		@Name("World Settings")
		@Comment("All world related fields are here")
		public WorldSettings worldSettings = new WorldSettings();
		
		@Name("Player Settings")
		@Comment("All player related fields are here")
		public PlayerSettings playerSettings = new PlayerSettings();
		
		@Name("Weapon Settings")
		@Comment("All weapon related fiels are here")
		public WeaponSettings weaponSettings = new WeaponSettings();
		
		@Name("Vehicle Settings")
		@Comment("All vehicle related fiels are here")
		public VehicleSettings vehicleSettings = new VehicleSettings();
	}
	
	/**
	 * Fields which won't be synced from server -> client on player join
	 */
	public static class ClientConfig
	{
		@Name("Overlays")
		@Comment("Overlay rendering options")
		public OverlaySettings overlays = new OverlaySettings();
		
		@Name("Other")
		public OtherSettings other = new OtherSettings();
	}
	
	public static class OtherSettings
	{
		@Config.Name("Messages on world join")
		@Config.Comment("You will receive message when you join world")
		public boolean enableMessagesSentOnJoin = true;
		
		@Config.Name("Loot render quality")
		@Config.Comment({"0 - OFF","1 - Fast","2 - Fancy"})
		@Config.RangeInt(min = 0, max = 2)
		@Config.RequiresMcRestart
		public int lootRenderType = 2;
	}
	
	public static class WorldSettings
	{
		@Name("Airdrop loot generation type")
		@Comment({"Loot is being generated when airdrop is created","0 - No loot","1 - Only armor and healing items","2 - Weapons"})
		@RangeInt(min = 0, max = 2)
		@RequiresWorldRestart
		public int airdropLootGen = 2;
		
		@Name("Gun loot generator")
		@Comment({"Enable gun loot generation.","If false only healing items and grenades will be spawned"})
		@RequiresWorldRestart
		public boolean enableGunLoot = true;
		
		@Name("Airdrop dissapear range")
		@Comment("Define minimum player range from airdrop for making it despawn")
		@RangeInt(min = 10, max = 100)
		@RequiresWorldRestart
		public int aidropRange = 40;
		
		@Name("Enable Guns")
		@Comment("Use this to enable/disable weapons")
		@RequiresWorldRestart
		public boolean enableGuns = true;
		
		@Name("Plane fly height")
		@Comment("Set default height where planes will spawn")
		@RangeInt(min = 25, max = 256)
		@RequiresWorldRestart
		public int planeHeight = 150;
		
		@Name("Plane wait time")
		@Comment("Set time [in seconds] how long will plane wait after spawning. This is for selecting drop locations")
		@RangeInt(min = 0, max = 30)
		@RequiresWorldRestart
		public int planeWaitTime = 5;
	}
	
	public static class PlayerSettings
	{
		@Config.Name("Disable third person perspective")
		@Config.Comment("Enable/disable third person perspective")
		@RequiresWorldRestart
		public boolean enableTP = true;
		
		@Config.Name("Inventory limit")
		@Config.Comment("Your inventory will be limited based on your backpack level")
		@RequiresWorldRestart
		public boolean enableInventoryLimit = true;
		
		@Config.Name("Force brightness level")
		@Config.Comment("Using this you can force players to have defined level of brightness")
		@RequiresWorldRestart
		public boolean forceBrightness = false;
		
		@Config.Name("Brightness level")
		@Config.Comment({"Level of brightness all players will have","You need to enable the Force brightness field!"})
		@Config.RangeInt(min = 0, max = 100)
		@RequiresWorldRestart
		public int brightness = 25;
		
		@Config.Name("Render player nametags")
		@Config.Comment("Use this to disable/enable nametag visibility")
		@Config.RequiresMcRestart
		public boolean renderPlayerNameTags = true;
	}
	
	public static class VehicleSettings
	{
		@Name("UAZ")
		public VehicleConfiguration uaz = new VehicleConfiguration(250F, 1.6F, 3.0F, 0.015F, 0.3F);
		
		@Name("Dacia")
		public VehicleConfiguration dacia = new VehicleConfiguration(200F, 2.35F, 3.3F, 0.01F, 0.3f);
	}
	
	public static class OverlaySettings
	{
		@Name("Use image overlay for boost rendering")
		@Comment("Your boost overlay will be rendered instead of the XP bar. If this is disabled, you'll be able to see numbers above hunger bar which will indicate your boost value")
		public boolean imageBoostOverlay = true;
		
		@Name("Boost overlay x-position offset")
		@Comment("Use this to adjust your boost overlay position if you have problems with it - this is horizontal movement")
		public int imgOverlayX = 0;
		
		@Name("Boost overlay y-position offset")
		@Comment("Use this to adjust your boost overlay position if you have problems with it - this is vertical movement")
		public int imgOverlayY = 0;
		
		@Name("Textured boost bar position x")
		@Comment("Use this to adjust position of the overlay")
		public int overlayX = 0;
		
		@Name("Textured boost bar position y")
		@Comment("Use this to adjust position of the overlay")
		public int overlayY = 0;
		
		@Name("Armor icons in HUD")
		@Comment("Icons indicating your state of gear will be rendered next to your hotbar")
		public boolean armorOverlayIcons = true;
	}
	
	public static class WeaponSettings
	{
		static final String S = "Damage value of this weapon";
		
		@Name("P92")
		@Comment(S)
		@RangeDouble(min = 1, max = 20)
		@Config.RequiresWorldRestart
		public float p92 = 4f;
		
		@Name("P1911")
		@Comment(S)
		@RangeDouble(min = 1, max = 25)
		@Config.RequiresWorldRestart
		public float p1911 = 5f;
		
		@Name("P18C")
		@Comment(S)
		@RangeDouble(min = 1, max = 20)
		@Config.RequiresWorldRestart
		public float p18c = 4f;
		
		@Name("r1895")
		@Comment(S)
		@RangeDouble(min = 1, max = 40)
		@Config.RequiresWorldRestart
		public float r1895 = 8f;
		
		@Name("R45")
		@Comment(S)
		@RangeDouble(min = 1, max = 30)
		@Config.RequiresWorldRestart
		public float r45 = 6f;
		
		@Name("Scorpion")
		@Comment(S)
		@RangeDouble(min = 1, max = 20)
		@Config.RequiresWorldRestart
		public float scorpion = 4f;
		
		@Name("Winchester-94")
		@Comment(S)
		@RangeDouble(min = 1, max = 50)
		@Config.RequiresWorldRestart
		public float win94 = 10f;
		
		@Name("Sawed-off")
		@Comment(S)
		@RangeDouble(min = 1, max = 15)
		@Config.RequiresWorldRestart
		public float sawedoff = 3f;
		
		@Name("S1897")
		@Comment(S)
		@RangeDouble(min = 1, max = 20)
		@Config.RequiresWorldRestart
		public float s1897 = 4f;
		
		@Name("S686")
		@Comment(S)
		@RangeDouble(min = 1, max = 20)
		@Config.RequiresWorldRestart
		public float s686 = 4f;
		
		@Name("S12K")
		@Comment(S)
		@RangeDouble(min = 1, max = 17.5D)
		@Config.RequiresWorldRestart
		public float s12k = 3.5f;
		
		@Name("Micro uzi")
		@Comment(S)
		@RangeDouble(min = 1, max = 20)
		@Config.RequiresWorldRestart
		public float microuzi = 4f;
		
		@Name("UMP-45")
		@Comment(S)
		@RangeDouble(min = 1, max = 25)
		@Config.RequiresWorldRestart
		public float ump9 = 5f;
		
		@Name("PP-19 Bizon")
		@Comment(S)
		@RangeDouble(min = 1, max = 20)
		@Config.RequiresWorldRestart
		public float bizon = 4f;
		
		@Name("Vector")
		@Comment(S)
		@RangeDouble(min = 1, max = 20)
		@Config.RequiresWorldRestart
		public float vector = 4f;
		
		@Name("Tommy-gun")
		@Comment(S)
		@RangeDouble(min = 1, max = 25)
		@Config.RequiresWorldRestart
		public float tommygun = 5f;
		
		@Name("M16A4")
		@Comment(S)
		@RangeDouble(min = 1, max = 40)
		@Config.RequiresWorldRestart
		public float m16a4 = 8f;
		
		@Name("M416")
		@Comment(S)
		@RangeDouble(min = 1, max = 40)
		@Config.RequiresWorldRestart
		public float m416 = 8f;
		
		@Name("SCAR-L")
		@Comment(S)
		@RangeDouble(min = 1, max = 40)
		@Config.RequiresWorldRestart
		public float scarl = 8f;
		
		@Name("QBZ-95")
		@Comment(S)
		@RangeDouble(min = 1, max = 40)
		@Config.RequiresWorldRestart
		public float qbz = 8f;
		
		@Name("G36C")
		@Comment(S)
		@RangeDouble(min = 1, max = 40)
		@Config.RequiresWorldRestart
		public float g36c = 8f;
		
		@Name("AUG")
		@Comment(S)
		@RangeDouble(min = 1, max = 40)
		@Config.RequiresWorldRestart
		public float aug = 8f;
		
		@Name("AKM")
		@Comment(S)
		@RangeDouble(min = 1, max = 47.5)
		@Config.RequiresWorldRestart
		public float akm = 9.5f;
		
		@Name("Beryl M-762")
		@Comment(S)
		@RangeDouble(min = 1, max = 45)
		@Config.RequiresWorldRestart
		public float m762 = 9f;
		
		@Name("MK-47 Mutant")
		@Comment(S)
		@RangeDouble(min = 1, max = 47.5)
		@Config.RequiresWorldRestart
		public float mk47 = 9.5f;
		
		@Name("Groza")
		@Comment(S)
		@RangeDouble(min = 1, max = 47.5)
		@Config.RequiresWorldRestart
		public float groza = 9.5f;
		
		@Name("M249")
		@Comment(S)
		@RangeDouble(min = 1, max = 40)
		@Config.RequiresWorldRestart
		public float m249 = 8f;
		
		@Name("DP-28")
		@Comment(S)
		@RangeDouble(min = 1, max = 47.5)
		@Config.RequiresWorldRestart
		public float dp28 = 9.5f;
		
		@Name("VSS")
		@Comment(S)
		@RangeDouble(min = 1, max = 30)
		@Config.RequiresWorldRestart
		public float vss = 6f;
		
		@Name("Mini-14")
		@Comment(S)
		@RangeDouble(min = 1, max = 45)
		@Config.RequiresWorldRestart
		public float mini14 = 9f;
		
		@Name("QBU")
		@Comment(S)
		@RangeDouble(min = 1, max = 45)
		@Config.RequiresWorldRestart
		public float qbu = 9f;
		
		@Name("SKS")
		@Comment(S)
		@RangeDouble(min = 1, max = 50)
		@Config.RequiresWorldRestart
		public float sks = 10f;
		
		@Name("SLR")
		@Comment(S)
		@RangeDouble(min = 1, max = 55)
		@Config.RequiresWorldRestart
		public float slr = 11f;
		
		@Name("MK-14 EBR")
		@Comment(S)
		@RangeDouble(min = 1, max = 62.5)
		@Config.RequiresWorldRestart
		public float mk14 = 12.5f;
		
		@Name("Kar 98K")
		@Comment(S)
		@RangeDouble(min = 1, max = 100)
		@Config.RequiresWorldRestart
		public float kar98k = 18f;
		
		@Name("M24")
		@Comment(S)
		@RangeDouble(min = 1, max = 105)
		@Config.RequiresWorldRestart
		public float m24 = 19f;
		
		@Name("AWM")
		@Comment(S)
		@RangeDouble(min = 1, max = 170)
		@Config.RequiresWorldRestart
		public float awm = 24f;
	}
	
	@Mod.EventBusSubscriber(modid = Pubgmc.MOD_ID)
	public static class Synchronization
	{
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent e)
		{
			if(e.getModID().equals(Pubgmc.MOD_ID))
			{
				ConfigManager.sync(Pubgmc.MOD_ID, Type.INSTANCE);
			}
		}
	}
}
