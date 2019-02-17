package com.toma.pubgmc.util.handlers;

import java.io.File;

import com.toma.pubgmc.Pubgmc;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler
{
	public static Configuration config;
	
	//PLAYER
	public static boolean enableMessagesSentOnJoin;
	public static boolean enableTP;
	public static boolean enableInventoryLimit;
	public static int lootRenderType;
	public static boolean forceBrightness;
	public static int brightness;
	public static boolean renderPlayerNameTags;
	
	//WORLD
	public static int airdropLootGen;
	public static boolean enableGunLoot;
	public static int aidropRange;
	public static boolean enableGuns;
	
	//OVERLAY
	public static boolean imageBoostOverlay;
	public static int imgOverlayX;
	public static int imgOverlayY;
	public static int overlayX;
	public static int overlayY;
	
	//GUNS
	public static float p92;
	public static float p1911;
	public static float r1895;
	public static float p18c;
	public static float r45;
	public static float scorpion;
	public static float win94;
	public static float sawedoff;
	public static float s1897;
	public static float s686;
	public static float s12k;
	public static float microuzi;
	public static float ump9;
	public static float vector;
	public static float tommygun;
	public static float bizon;
	public static float m16a4;
	public static float m416;
	public static float qbz;
	public static float scarl;
	public static float g36c;
	public static float aug;
	public static float akm;
	public static float mk47;
	public static float m762;
	public static float groza;
	public static float m249;
	public static float dp28;
	public static float mini14;
	public static float qbu;
	public static float sks;
	public static float slr;
	public static float mk14;
	public static float vss;
	public static float kar98k;
	public static float m24;
	public static float awm;
	
	
	public static void init(File file)
	{
		config = new Configuration(file);
		String category;
		
		category = "Player";
		config.addCustomCategoryComment(category, "Player settings");
			
			enableMessagesSentOnJoin = config.getBoolean("Get messages on join", category, true, "Get message when joined world");
			enableInventoryLimit = config.getBoolean("Inventory limit", category, true, "Your inventory will be limited if you won't wear backpack. You need to enable custom inventory for this to work!");
			enableTP = config.getBoolean("Disable third person view", category, true, "You will be forced to play in first person view if this is set to true");
			lootRenderType = config.getInt("Render loot inside loot spawners", category, 2, 0, 2, "Loot render style inside loot generators; 0 = Off, 1 = Fast render, 2 = Fancy render");
			forceBrightness = config.getBoolean("Force brightness", category, true, "This will force everyone to have the same brightness level to avoid getting advantage over others (useful for night vision goggles)");
			brightness = config.getInt("Brightness level", category, 0, 0, 100, "This is the brightness value everybody will be forced to have. In order to work you have to enable the 'force brightness' field!");
			renderPlayerNameTags = config.getBoolean("Render player name tags", category, true, "You can turn off player nametag rendering with this option");
			
		category = "World";
		config.addCustomCategoryComment(category, "World settings");
			
			aidropRange = config.getInt("Airdrop dissapear range", category, 40, 10, 120, "Define minimum player range from airdrop for making it despawn");
			airdropLootGen = config.getInt("Aidrop Loot", category, 2, 0, 2, "0 = No loot; 1 = only medical loot; 2 = Gun and medical loot");
			enableGunLoot = config.getBoolean("Gun loot generator", category, true, "Enable gun loot generation. If false only healing items and grenades will be spawned");
			enableGuns = config.getBoolean("Enable Guns", category, true, "Use this to enable/disable weapons");
			
		category = "Guns";
		config.addCustomCategoryComment(category, "Gun settings");
			
			p92 = config.getFloat("P92 Damage", category, 4.0f, 1.0f, 20.0f, "Set damage for P92");
			p1911 = config.getFloat("P1911 Damage", category, 5.0f, 1.0f, 25.0f, "Set damage for P1911");
			r1895 = config.getFloat("R1895 Damage", category, 7.5f, 1.0f, 37.5f, "Set damage for R1895");
			p18c = config.getFloat("P18C Damage", category, 4.0f, 1.0f, 20.0f, "Set damage for P18C");
			r45 = config.getFloat("R45 Damage", category, 6.0f, 1.0f, 30.0f, "Set damage for R45");
			scorpion = config.getFloat("Scorpion Damage", category, 4.0f, 1.0f, 20.0f, "Set damage for Scorpion");
			win94 = config.getFloat("Win-94 Damage", category, 13.0f, 1.0f, 65.0f, "Set damage for Win-94");
			sawedoff = config.getFloat("Sawed-Off Damage", category, 3.0f, 1.0f, 15.0f, "Set damage for Sawed-Off");
			s1897 = config.getFloat("S1897 Damage", category, 4.0f, 1.0f, 20.0f, "Set damage for 1897");
			s686 = config.getFloat("S686 Damage", category, 4.0f, 1.0f, 20.0f, "Set damage for S686");
			s12k = config.getFloat("S12K Damage", category, 4.0f, 1.0f, 20.0f, "Set damage for S12K");
			microuzi = config.getFloat("Micro-uzi Damage", category, 5.0f, 1.0f, 25.0f, "Set damage for Micro-uzi");
			ump9 = config.getFloat("UMP-9 Damage", category, 5.0f, 1.0f, 25.0f, "Set damage for UMP-9");
			vector = config.getFloat("Vector Damage", category, 5.0f, 1.0f, 25.0f, "Set damage for Vector");
			tommygun = config.getFloat("Tommy-gun Damage", category, 5.0f, 1.0f, 25.0f, "Set damage for Tommy-gun");
			bizon = config.getFloat("PP-19 Bizon Damage", category, 5.0f, 1.0f, 25.0f, "Set damage for PP-19 Bizon");
			m16a4 = config.getFloat("M16A4 Damage", category, 8.0f, 1.0f, 40.0f, "Set damage for M16A4");
			m416 = config.getFloat("M416 Damage", category, 8.0f, 1.0f, 40.0f, "Set damage for M416");
			qbz = config.getFloat("QBZ Damage", category, 8.0f, 1.0f, 40.0f, "Set damage for QBZ");
			scarl = config.getFloat("Scar-L Damage", category, 8.0f, 1.0f, 40.0f, "Set damage for Scar-L");
			g36c = config.getFloat("G36C Damage", category, 8.0f, 1.0f, 40.0f, "Set damage for G36C");
			aug = config.getFloat("AUG Damage", category, 8.0f, 1.0f, 40.0f, "Set damage for AUG");
			akm = config.getFloat("AKM Damage", category, 8.5f, 1.0f, 42.5f, "Set damage for AKM");
			mk47 = config.getFloat("Mk-47 Mutant Damage", category, 8.5f, 1.0f, 42.5f, "Set damage for Mk-47 Mutant");
			m762 = config.getFloat("Beryl M762 Damage", category, 8.0f, 1.0f, 40.0f, "Set damage for Beryl M762");
			groza = config.getFloat("Groza Damage", category, 8.5f, 1.0f, 42.5f, "Set damage for Groza");
			m249 = config.getFloat("M249 Damage", category, 8.0f, 1.0f, 40.0f, "Set damage for M249");
			dp28 = config.getFloat("DP-28 Damage", category, 8.5f, 1.0f, 42.5f, "Set damage for DP-28");
			mini14 = config.getFloat("Mini-14 Damage", category, 9.0f, 1.0f, 45.0f, "Set damage for Mini-14");
			qbu = config.getFloat("QBU Damage", category, 9.0f, 1.0f, 45.0f, "Set damage for QBU");
			sks = config.getFloat("SKS Damage", category, 10.0f, 1.0f, 50.0f, "Set damage for SKS");
			slr = config.getFloat("SLR Damage", category, 11.5f, 1.0f, 57.5f, "Set damage for SLR");
			mk14 = config.getFloat("Mk-14 Damage", category, 13.0f, 1.0f, 65.0f, "Set damage for Mk-14");
			vss = config.getFloat("VSS damage", category, 6.0f, 1.0f, 30.0f, "Set damage for VSS");
			kar98k = config.getFloat("Kar98k Damage", category, 17.0f, 1.0f, 85.0f, "Set damage for Kar98k");
			m24 = config.getFloat("M24 Damage", category, 18.0f, 1.0f, 90.0f, "Set damage for M24");
			awm = config.getFloat("AWM Damage", category, 22.0f, 1.0f, 110.0f, "Set damage for AWM");
			
		category = "Overlays";
		config.addCustomCategoryComment(category, "All overlay modifications can be done here");
			
			imageBoostOverlay = config.getBoolean("Use image overlay for boost rendering", category, true, "Your boost overlay will be rendered instead of the XP bar. If this is disabled, you'll be able to see numbers above hunger bar which will indicate your boost value");
			overlayX = config.getInt("Boost overlay x-position offset", category, 0, -500, 500, "Use this to adjust your boost overlay position if you have problems with it - this is horizontal movement");
			overlayY = config.getInt("Boost overlay y-position offset", category, 0, -500, 500, "Use this to adjust your boost overlay position if you have problems with it - this is vertical movement");
			imgOverlayX = config.getInt("Textured boost bar position x", category, 0, -1000, 1000, "Use this to adjust position of the overlay");
			imgOverlayY = config.getInt("Textured boost bar position y", category, 0, -1000, 1000, "Use this to adjust position of the overlay");
		
		config.save();
	}
	
	public static void registerConfig(FMLPreInitializationEvent ev)
	{
		Pubgmc.config = new File(ev.getModConfigurationDirectory() + "/");
		Pubgmc.config.mkdirs();
		init(new File(Pubgmc.config.getPath(), Pubgmc.MOD_ID + ".cfg"));
	}
}
