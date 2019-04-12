package com.toma.pubgmc.init;

import java.util.HashSet;
import java.util.Set;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.renderer.WeaponTEISR;
import com.toma.pubgmc.common.blocks.BlockAirdrop;
import com.toma.pubgmc.common.blocks.BlockBigAirdrop;
import com.toma.pubgmc.common.blocks.BlockBush;
import com.toma.pubgmc.common.blocks.BlockFurniture;
import com.toma.pubgmc.common.blocks.BlockGlass;
import com.toma.pubgmc.common.blocks.BlockGunWorkbench;
import com.toma.pubgmc.common.blocks.BlockLamp;
import com.toma.pubgmc.common.blocks.BlockLandMine;
import com.toma.pubgmc.common.blocks.BlockLight;
import com.toma.pubgmc.common.blocks.BlockLootSpawner;
import com.toma.pubgmc.common.blocks.BlockLootSpawner.LootType;
import com.toma.pubgmc.common.blocks.BlockOre;
import com.toma.pubgmc.common.blocks.BlockPlant;
import com.toma.pubgmc.common.blocks.BlockPlayerCrate;
import com.toma.pubgmc.common.blocks.BlockProp;
import com.toma.pubgmc.common.blocks.BlockSolid;
import com.toma.pubgmc.common.blocks.BlockSolidRotatable;
import com.toma.pubgmc.common.blocks.PMCBlock;
import com.toma.pubgmc.common.entity.EntityBullet;
import com.toma.pubgmc.common.entity.EntityFlare;
import com.toma.pubgmc.common.entity.EntityGrenade;
import com.toma.pubgmc.common.entity.EntityMolotov;
import com.toma.pubgmc.common.entity.EntityParachute;
import com.toma.pubgmc.common.entity.EntityPlane;
import com.toma.pubgmc.common.entity.EntitySmokeGrenade;
import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;
import com.toma.pubgmc.common.items.ItemAmmo;
import com.toma.pubgmc.common.items.ItemBackpack;
import com.toma.pubgmc.common.items.ItemFuelCan;
import com.toma.pubgmc.common.items.ItemGrenade;
import com.toma.pubgmc.common.items.ItemMolotov;
import com.toma.pubgmc.common.items.ItemParachute;
import com.toma.pubgmc.common.items.ItemSmokeGrenade;
import com.toma.pubgmc.common.items.ItemVehicleSpawner;
import com.toma.pubgmc.common.items.ItemVehicleSpawner.Vehicles;
import com.toma.pubgmc.common.items.PMCItem;
import com.toma.pubgmc.common.items.armor.ArmorBase;
import com.toma.pubgmc.common.items.armor.ArmorBase.ArmorLevel;
import com.toma.pubgmc.common.items.armor.ItemClothing;
import com.toma.pubgmc.common.items.armor.ItemGhillie;
import com.toma.pubgmc.common.items.armor.ItemNVGoggles;
import com.toma.pubgmc.common.items.cases.Case1;
import com.toma.pubgmc.common.items.guns.AmmoType;
import com.toma.pubgmc.common.items.guns.ArAKM;
import com.toma.pubgmc.common.items.guns.ArAUG;
import com.toma.pubgmc.common.items.guns.ArBerylM762;
import com.toma.pubgmc.common.items.guns.ArG36C;
import com.toma.pubgmc.common.items.guns.ArGroza;
import com.toma.pubgmc.common.items.guns.ArM16A4;
import com.toma.pubgmc.common.items.guns.ArM416;
import com.toma.pubgmc.common.items.guns.ArMK47;
import com.toma.pubgmc.common.items.guns.ArQBZ;
import com.toma.pubgmc.common.items.guns.ArScarL;
import com.toma.pubgmc.common.items.guns.DmrMK14;
import com.toma.pubgmc.common.items.guns.DmrMini14;
import com.toma.pubgmc.common.items.guns.DmrQBU;
import com.toma.pubgmc.common.items.guns.DmrSKS;
import com.toma.pubgmc.common.items.guns.DmrSLR;
import com.toma.pubgmc.common.items.guns.DmrVSS;
import com.toma.pubgmc.common.items.guns.FlareGun;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.LmgDP28;
import com.toma.pubgmc.common.items.guns.LmgM249;
import com.toma.pubgmc.common.items.guns.PistolP18C;
import com.toma.pubgmc.common.items.guns.PistolP1911;
import com.toma.pubgmc.common.items.guns.PistolP92;
import com.toma.pubgmc.common.items.guns.PistolR1895;
import com.toma.pubgmc.common.items.guns.PistolR45;
import com.toma.pubgmc.common.items.guns.PistolScorpion;
import com.toma.pubgmc.common.items.guns.PistolWin94;
import com.toma.pubgmc.common.items.guns.ShotgunS12K;
import com.toma.pubgmc.common.items.guns.ShotgunS1897;
import com.toma.pubgmc.common.items.guns.ShotgunS686;
import com.toma.pubgmc.common.items.guns.ShotgunSawedOff;
import com.toma.pubgmc.common.items.guns.SmgBizon;
import com.toma.pubgmc.common.items.guns.SmgMicroUzi;
import com.toma.pubgmc.common.items.guns.SmgTommygun;
import com.toma.pubgmc.common.items.guns.SmgUmp45;
import com.toma.pubgmc.common.items.guns.SmgVector;
import com.toma.pubgmc.common.items.guns.SrAWM;
import com.toma.pubgmc.common.items.guns.SrKar98K;
import com.toma.pubgmc.common.items.guns.SrM24;
import com.toma.pubgmc.common.items.guns.attachments.IAttachment.Type;
import com.toma.pubgmc.common.items.guns.attachments.ItemAttachment;
import com.toma.pubgmc.common.items.heal.ItemAdrenalineSyringe;
import com.toma.pubgmc.common.items.heal.ItemBandage;
import com.toma.pubgmc.common.items.heal.ItemEnergyDrink;
import com.toma.pubgmc.common.items.heal.ItemFirstAidKit;
import com.toma.pubgmc.common.items.heal.ItemMedkit;
import com.toma.pubgmc.common.items.heal.ItemPainkiller;
import com.toma.pubgmc.common.items.melee.ItemPan;
import com.toma.pubgmc.common.tileentity.TileEntityAirdrop;
import com.toma.pubgmc.common.tileentity.TileEntityBigAirdrop;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityLamp;
import com.toma.pubgmc.common.tileentity.TileEntityLandMine;
import com.toma.pubgmc.common.tileentity.TileEntityLootSpawner;
import com.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import com.toma.pubgmc.util.PMCItemBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;

public class PMCRegistry 
{
	@ObjectHolder(Pubgmc.MOD_ID)
	public static final class Items
	{
		public static final Item BACKPACK1 = null;
		public static final Item BACKPACK2 = null;
		public static final Item BACKPACK3 = null;
		public static final Item BANDAGE = null;
		public static final Item FIRSTAIDKIT = null;
		public static final Item MEDKIT = null;
		public static final Item ENERGYDRINK = null;
		public static final Item PAINKILLERS = null;
		public static final Item ADRENALINESYRINGE = null;
		public static final Item IBLOCK = null;
		public static final Item GHILLIE_SUIT = null;
		public static final Item NV_GOGGLES = null;
		public static final Item FLARE_GUN = null;
		public static final Item P92 = null;
		public static final Item P1911 = null;
		public static final Item R1895 = null;
		public static final Item R45 = null;
		public static final Item P18C = null;
		public static final Item SCORPION = null;
		public static final Item WIN94 = null;
		public static final Item SAWED_OFF = null;
		public static final Item S1897 = null;
		public static final Item S686 = null;
		public static final Item S12K = null;
		public static final Item MICROUZI = null;
		public static final Item UMP45 = null;
		public static final Item VECTOR = null;
		public static final Item TOMMY_GUN = null;
		public static final Item BIZON = null;
		public static final Item M16A4 = null;
		public static final Item M416 = null;
		public static final Item SCAR_L = null;
		public static final Item G36C = null;
		public static final Item QBZ = null;
		public static final Item AUG = null;
		public static final Item AKM = null;
		public static final Item BERYL_M762 = null;
		public static final Item MK47_MUTANT = null;
		public static final Item GROZA = null;
		public static final Item DP28 = null;
		public static final Item M249 = null;
		public static final Item VSS = null;
		public static final Item MINI14 = null;
		public static final Item QBU = null;
		public static final Item SKS = null;
		public static final Item SLR = null;
		public static final Item MK14 = null;
		public static final Item KAR98K = null;
		public static final Item M24 = null;
		public static final Item AWM = null;
		public static final Item GRENADE = null;
		public static final Item SMOKE = null;
		public static final Item MOLOTOV = null;
		public static final Item AMMO_9MM = null;
		public static final Item AMMO_45ACP = null;
		public static final Item AMMO_SHOTGUN = null;
		public static final Item AMMO_556 = null;
		public static final Item AMMO_762 = null;
		public static final Item AMMO_300M = null;
		public static final Item AMMO_FLARE = null;
		public static final Item CASE1 = null;
		public static final ItemSword PAN = null;
		//TODO remove - replace with ghillie suit 
		public static final Item GHILLIEHELMET = null;
		public static final Item GHILLIEBODY = null;
		public static final Item GHILLIELEGS = null;
		public static final Item GHILLIEBOOTS = null;
		public static final Item ARMOR1HELMET = null;
		public static final Item ARMOR1BODY = null;
		public static final Item ARMOR2HELMET = null;
		public static final Item ARMOR2BODY = null;
		public static final Item ARMOR3HELMET = null;
		public static final Item ARMOR3BODY = null;
		public static final Item BLACK_GLASSES = null;
		public static final Item YELLOW_TSHIRT = null;
		public static final Item GRAY_TOP = null;
		public static final Item BROWN_CAP = null;
		public static final Item WHITE_BOOTS = null;
		public static final Item OFFICIAL_LEGS = null;
		public static final Item SILENCER_PISTOL = null;
		public static final Item SILENCER_SMG = null;
		public static final Item SILENCER_AR = null;
		public static final Item SILENCER_SNIPER = null;
		public static final Item COMPENSATOR_SMG = null;
		public static final Item COMPENSATOR_AR = null;
		public static final Item COMPENSATOR_SNIPER = null;
		public static final Item RED_DOT = null;
		public static final Item HOLOGRAPHIC = null;
		public static final Item SCOPE2X = null;
		public static final Item SCOPE4X = null;
		public static final Item SCOPE8X = null;
		public static final Item SCOPE15X = null;
		public static final Item GRIP_VERTICAL = null;
		public static final Item GRIP_ANGLED = null;
		public static final Item QUICKDRAW_MAG_PISTOL = null;
		public static final Item EXTENDED_MAG_PISTOL = null;
		public static final Item EXTENDED_QUICKDRAW_MAG_PISTOL = null;
		public static final Item QUICKDRAW_MAG_SMG = null;
		public static final Item EXTENDED_MAG_SMG = null;
		public static final Item EXTENDED_QUICKDRAW_MAG_SMG = null;
		public static final Item QUICKDRAW_MAG_AR = null;
		public static final Item EXTENDED_MAG_AR = null;
		public static final Item EXTENDED_QUICKDRAW_MAG_AR = null;
		public static final Item QUICKDRAW_MAG_SNIPER = null;
		public static final Item EXTENDED_MAG_SNIPER = null;
		public static final Item EXTENDED_QUICKDRAW_MAG_SNIPER = null;
		public static final Item BULLET_LOOPS_SHOTGUN = null;
		public static final Item BULLET_LOOPS_SNIPER = null;
		public static final Item CHEEKPAD = null;
		public static final Item PARACHUTE = null;
		public static final Item STEEL_DUST = null;
		public static final Item STEEL_INGOT = null;
		public static final Item COPPER_INGOT = null;
		public static final Item FUELCAN = null;
		public static final Item VEHICLE_UAZ = null;
	}
	
	@ObjectHolder(Pubgmc.MOD_ID)
	public static final class Blocks
	{
		public static final Block ROADASPHALT = null;
		public static final Block SCHOOLWALL = null;
		public static final Block SCHOOLROOF = null;
		public static final Block SCHOOLWINDOW = null;
		public static final Block AIRDROP = null;
		public static final Block BIG_AIRDROP = null;
		public static final Block DARKWOOD = null;
		public static final Block RUINSWALL = null;
		public static final Block BLUEGLASS = null;
		public static final Block TARGET = null;
		public static final Block LAMPBOTTOM = null;
		public static final Block LAMPPOST = null;
		public static final Block LAMPTOP = null;
		public static final Block LIGHT = null;
		public static final Block CRATE = null;
		public static final Block CRATES = null;
		public static final Block BUSH = null;
		public static final Block WHEAT = null;
		public static final Block PROP1 = null;
		public static final Block PROP2 = null;
		public static final Block PROP3 = null;
		public static final Block PROP4 = null;
		public static final Block PROP5 = null;
		public static final Block FENCE = null;
		public static final Block CONCRETE = null;
		public static final Block ELECTRICPOLE = null;
		public static final Block ELECTRICPOLETOP = null;
		public static final Block ELECTRICCABLE = null;
		public static final Block RADIOTOWER = null;
		public static final Block RADIOTOWERTOP = null;
		public static final Block LOOT_SPAWNER = null;
		public static final Block PLAYER_CRATE = null;
		public static final Block LANDMINE = null;
		public static final Block GUN_WORKBENCH = null;
		public static final Block CHAIR = null;
		public static final Block TABLE = null;
		public static final Block COPPER_ORE = null;
	}
	
	public static final class ToolMaterials
	{
		public static final ToolMaterial MATERIAL_PAN = EnumHelper.addToolMaterial("material_pan", 0, -1, 0.0F, 15.0F, 0);
		public static final ArmorMaterial GHILLIE_SUIT = EnumHelper.addArmorMaterial("ghillie_suit", Pubgmc.MOD_ID + ":ghillie_suit", -1, new int[] {0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0);
		public static final ArmorMaterial GHILLIE = EnumHelper.addArmorMaterial("ghillie", Pubgmc.MOD_ID + ":ghillie", -1, new int[] {1, 1, 1, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
		public static final ArmorMaterial LVL1 = EnumHelper.addArmorMaterial("lvl1", Pubgmc.MOD_ID + ":lvl1", 1, new int[] {0, 0, 3, 3}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
		public static final ArmorMaterial LVL2 = EnumHelper.addArmorMaterial("lvl2", Pubgmc.MOD_ID + ":lvl2", 1, new int[] {0, 0, 6, 6}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
		public static final ArmorMaterial LVL3 = EnumHelper.addArmorMaterial("lvl3", Pubgmc.MOD_ID + ":lvl3", 1, new int[] {0, 0, 10, 10}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
		public static final ArmorMaterial CLOTH1 = EnumHelper.addArmorMaterial("set1", Pubgmc.MOD_ID + ":set1", -1, new int[] {0,  0,  0,  0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
		public static final ArmorMaterial CLOTH2 = EnumHelper.addArmorMaterial("set2", Pubgmc.MOD_ID + ":set2", -1, new int[] {0,  0,  0,  0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
		public static final ArmorMaterial CLOTH3 = EnumHelper.addArmorMaterial("set3", Pubgmc.MOD_ID + ":set3", -1, new int[] {0,  0,  0,  0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
		public static final ArmorMaterial HELMET3 = EnumHelper.addArmorMaterial("l3helmet", Pubgmc.MOD_ID + ":level3helmet", 1, new int[] {0, 0, 0, 10}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);
	}
	
	@Mod.EventBusSubscriber
	public static class Registry
	{
		static int entityID = -1;
		
		@SubscribeEvent
		public static void registerBlocks(Register<Block> event)
		{
			final Block[] BLOCKS = {
					new PMCBlock("roadasphalt", Material.ROCK),
					new PMCBlock("schoolwall", Material.ROCK),
					new PMCBlock("schoolroof", Material.ROCK),
					new BlockGlass("schoolwindow", Material.GLASS, SoundType.GLASS),
					new BlockAirdrop("airdrop", Material.IRON, SoundType.METAL, MapColor.BLUE),
					new PMCBlock("darkwood", Material.WOOD),
					new BlockLootSpawner("loot_spawner", Material.ROCK, SoundType.STONE, MapColor.BLACK, LootType.COMMON),
					new BlockPlayerCrate("player_crate", Material.WOOD, SoundType.WOOD, MapColor.BROWN),
					new BlockFurniture("chair", Material.WOOD, SoundType.WOOD, MapColor.BROWN),
					new BlockFurniture("table", Material.WOOD, SoundType.WOOD, MapColor.BROWN),
					new PMCBlock("ruinswall", Material.ROCK),
					new BlockGlass("blueglass", Material.GLASS, SoundType.GLASS),
					new PMCBlock("target", Material.ROCK),
					new BlockSolid("lampbottom", Material.IRON, SoundType.METAL, MapColor.GRAY),
					new BlockSolid("lamppost", Material.IRON, SoundType.METAL, MapColor.GRAY),
					new BlockLamp("lamptop", Material.IRON),
					new BlockLight("light", Material.IRON, SoundType.METAL, MapColor.AIR),
					new BlockSolid("crate", Material.WOOD, SoundType.WOOD, MapColor.BROWN),
					new BlockSolid("crates", Material.IRON, SoundType.METAL, MapColor.GREEN),
					new BlockBush("bush", Material.PLANTS, SoundType.PLANT, MapColor.GREEN),
					new BlockPlant("wheat", Material.PLANTS, SoundType.PLANT, MapColor.YELLOW),
					new BlockProp("prop1", Material.PLANTS, SoundType.PLANT, MapColor.AIR),
					new BlockProp("prop2", Material.PLANTS, SoundType.PLANT, MapColor.AIR),
					new BlockProp("prop3", Material.IRON, SoundType.METAL, MapColor.AIR),
					new BlockProp("prop4", Material.IRON, SoundType.METAL, MapColor.AIR),
					new BlockProp("prop5", Material.CLOTH, SoundType.CLOTH, MapColor.AIR),
					new BlockSolidRotatable("fence", Material.IRON, SoundType.METAL, MapColor.AIR, true),
					new BlockSolidRotatable("concrete", Material.ROCK, SoundType.STONE, MapColor.AIR, false),
					new BlockSolid("electricpole", Material.WOOD, SoundType.WOOD, MapColor.AIR),
					new BlockSolidRotatable("electricpoletop", Material.WOOD, SoundType.WOOD, MapColor.BROWN, false),
					new BlockBush("electriccable", Material.IRON, SoundType.METAL, MapColor.AIR),
					new BlockSolid("radiotower", Material.IRON, SoundType.METAL, MapColor.AIR),
					new BlockSolid("radiotowertop", Material.IRON, SoundType.METAL, MapColor.AIR),
					new BlockGunWorkbench("gun_workbench"),
					new BlockBigAirdrop("big_airdrop"),
					new BlockOre("copper_ore"),
					new BlockLandMine("landmine")
			};
			
			event.getRegistry().registerAll(BLOCKS);
		}
		
		@SubscribeEvent
		public static void registerItems(Register<Item> event)
		{
			final Item[] ITEMS = {
					new ItemBackpack("backpack1").addDescription("Right Click to equip"),
					new ItemBackpack("backpack2").addDescription("Right Click to equip"),
					new ItemBackpack("backpack3").addDescription("Right Click to equip"),
					new ItemBandage("bandage"),
					new ItemFirstAidKit("firstaidkit"),
					new ItemMedkit("medkit"),
					new ItemEnergyDrink("energydrink"),
					new ItemPainkiller("painkillers"),
					new ItemAdrenalineSyringe("adrenalinesyringe"),
					new PMCItem("iblock").setMaxStackSize(1),
					new ItemGhillie("ghillie_suit"),
					new ItemNVGoggles("nv_goggles").addDescription("Right Click to equip"),
					new FlareGun("flare_gun"),
					new PistolP92("p92"),
					new PistolP1911("p1911"),
					new PistolR1895("r1895"),
					new PistolR45("r45"),
					new PistolP18C("p18c"),
					new PistolScorpion("scorpion"),
					new PistolWin94("win94"),
					new ShotgunSawedOff("sawed_off"),
					new ShotgunS1897("s1897"),
					new ShotgunS686("s686"),
					new ShotgunS12K("s12k"),
					new SmgMicroUzi("microuzi"),
					new SmgUmp45("ump45"),
					new SmgVector("vector"),
					new SmgTommygun("tommy_gun"),
					new SmgBizon("bizon"),
					new ArM16A4("m16a4"),
					new ArM416("m416"),
					new ArScarL("scar_l"),
					new ArG36C("g36c"),
					new ArQBZ("qbz"),
					new ArAUG("aug"),
					new ArAKM("akm"),
					new ArBerylM762("beryl_m762"),
					new ArMK47("mk47_mutant"),
					new ArGroza("groza"),
					new LmgDP28("dp28"),
					new LmgM249("m249"),
					new DmrVSS("vss"),
					new DmrMini14("mini14"),
					new DmrQBU("qbu"),
					new DmrSKS("sks"),
					new DmrSLR("slr"),
					new DmrMK14("mk14"),
					new SrKar98K("kar98k"),
					new SrM24("m24"),
					new SrAWM("awm"),
					new ItemGrenade("grenade"),
					new ItemSmokeGrenade("smoke"),
					new ItemMolotov("molotov"),
					new ItemAmmo("ammo_9mm", AmmoType.AMMO9MM),
					new ItemAmmo("ammo_45acp", AmmoType.AMMO45ACP),
					new ItemAmmo("ammo_shotgun", AmmoType.AMMO12G),
					new ItemAmmo("ammo_556", AmmoType.AMMO556),
					new ItemAmmo("ammo_762", AmmoType.AMMO762),
					new ItemAmmo("ammo_300m", AmmoType.AMMO300M),
					new ItemAmmo("ammo_flare", AmmoType.FLARE),
					new Case1("case1"),
					new ItemPan("pan", PMCRegistry.ToolMaterials.MATERIAL_PAN),
					new ItemClothing("ghilliehelmet", PMCRegistry.ToolMaterials.GHILLIE, 1, EntityEquipmentSlot.HEAD),
					new ItemClothing("ghilliebody", PMCRegistry.ToolMaterials.GHILLIE, 1, EntityEquipmentSlot.CHEST),
					new ItemClothing("ghillielegs", PMCRegistry.ToolMaterials.GHILLIE, 2, EntityEquipmentSlot.LEGS),
					new ItemClothing("ghillieboots", PMCRegistry.ToolMaterials.GHILLIE, 1, EntityEquipmentSlot.FEET),
					new ArmorBase("armor1helmet", PMCRegistry.ToolMaterials.LVL1, 1, EntityEquipmentSlot.HEAD).setArmorLevel(ArmorLevel.LEVEL_ONE),
					new ArmorBase("armor1body", PMCRegistry.ToolMaterials.LVL1, 1, EntityEquipmentSlot.CHEST).setArmorLevel(ArmorLevel.LEVEL_ONE),
					new ArmorBase("armor2helmet", PMCRegistry.ToolMaterials.LVL2, 1, EntityEquipmentSlot.HEAD).setArmorLevel(ArmorLevel.LEVEL_TWO),
					new ArmorBase("armor2body", PMCRegistry.ToolMaterials.LVL2, 1, EntityEquipmentSlot.CHEST).setArmorLevel(ArmorLevel.LEVEL_TWO),
					new ArmorBase("armor3helmet", PMCRegistry.ToolMaterials.LVL3, 1, EntityEquipmentSlot.HEAD).setArmorLevel(ArmorLevel.LEVEL_THREE),
					new ArmorBase("armor3body", PMCRegistry.ToolMaterials.LVL3, 1, EntityEquipmentSlot.CHEST).setArmorLevel(ArmorLevel.LEVEL_THREE),
					new ItemClothing("black_glasses", PMCRegistry.ToolMaterials.CLOTH1, 1, EntityEquipmentSlot.HEAD),
					new ItemClothing("yellow_tshirt", PMCRegistry.ToolMaterials.CLOTH1, 1, EntityEquipmentSlot.CHEST),
					new ItemClothing("gray_top", PMCRegistry.ToolMaterials.CLOTH2, 1, EntityEquipmentSlot.CHEST),
					new ItemClothing("brown_cap", PMCRegistry.ToolMaterials.CLOTH2, 1, EntityEquipmentSlot.HEAD),
					new ItemClothing("white_boots", PMCRegistry.ToolMaterials.CLOTH2, 1, EntityEquipmentSlot.FEET),
					new ItemClothing("official_legs", PMCRegistry.ToolMaterials.CLOTH3, 2, EntityEquipmentSlot.LEGS),
					new ItemAttachment("silencer_pistol", Type.BARREL),
					new ItemAttachment("silencer_smg", Type.BARREL),
					new ItemAttachment("silencer_ar", Type.BARREL),
					new ItemAttachment("silencer_sniper", Type.BARREL),
					new ItemAttachment("compensator_smg", Type.BARREL),
					new ItemAttachment("compensator_ar", Type.BARREL),
					new ItemAttachment("compensator_sniper", Type.BARREL),
					new ItemAttachment("red_dot", Type.SCOPE),
					new ItemAttachment("holographic", Type.SCOPE),
					new ItemAttachment("scope2x", Type.SCOPE),
					new ItemAttachment("scope4x", Type.SCOPE),
					new ItemAttachment("scope8x", Type.SCOPE),
					new ItemAttachment("scope15x", Type.SCOPE),
					new ItemAttachment("grip_vertical", Type.GRIP),
					new ItemAttachment("grip_angled", Type.GRIP),
					new ItemAttachment("quickdraw_mag_pistol", Type.MAGAZINE),
					new ItemAttachment("extended_mag_pistol", Type.MAGAZINE),
					new ItemAttachment("extended_quickdraw_mag_pistol", Type.MAGAZINE),
					new ItemAttachment("quickdraw_mag_smg", Type.MAGAZINE),
					new ItemAttachment("extended_mag_smg", Type.MAGAZINE),
					new ItemAttachment("extended_quickdraw_mag_smg", Type.MAGAZINE),
					new ItemAttachment("quickdraw_mag_ar", Type.MAGAZINE),
					new ItemAttachment("extended_mag_ar", Type.MAGAZINE),
					new ItemAttachment("extended_quickdraw_mag_ar", Type.MAGAZINE),
					new ItemAttachment("quickdraw_mag_sniper", Type.MAGAZINE),
					new ItemAttachment("extended_mag_sniper", Type.MAGAZINE),
					new ItemAttachment("extended_quickdraw_mag_sniper", Type.MAGAZINE),
					new ItemAttachment("bullet_loops_shotgun", Type.STOCK),
					new ItemAttachment("bullet_loops_sniper", Type.STOCK),
					new ItemAttachment("cheekpad", Type.STOCK),
					new ItemParachute("parachute"),
					new PMCItem("steel_dust"),
					new PMCItem("steel_ingot"),
					new PMCItem("copper_ingot"),
					new ItemFuelCan().addDescription("Hold right click while driving vehicle","Vehicle must be stationary!"),
					new ItemVehicleSpawner("vehicle_uaz", Vehicles.UAZ)
			};
			
			final Item[] ITEM_BLOCKS = {
					new PMCItemBlock(PMCRegistry.Blocks.ROADASPHALT),
					new PMCItemBlock(PMCRegistry.Blocks.SCHOOLWALL),
					new PMCItemBlock(PMCRegistry.Blocks.SCHOOLROOF),
					new PMCItemBlock(PMCRegistry.Blocks.SCHOOLWINDOW),
					new PMCItemBlock(PMCRegistry.Blocks.AIRDROP),
					new PMCItemBlock(PMCRegistry.Blocks.DARKWOOD),
					new PMCItemBlock(PMCRegistry.Blocks.LOOT_SPAWNER),
					new PMCItemBlock(PMCRegistry.Blocks.PLAYER_CRATE),
					new PMCItemBlock(PMCRegistry.Blocks.CHAIR),
					new PMCItemBlock(PMCRegistry.Blocks.TABLE),
					new PMCItemBlock(PMCRegistry.Blocks.RUINSWALL),
					new PMCItemBlock(PMCRegistry.Blocks.BLUEGLASS),
					new PMCItemBlock(PMCRegistry.Blocks.TARGET),
					new PMCItemBlock(PMCRegistry.Blocks.LAMPBOTTOM),
					new PMCItemBlock(PMCRegistry.Blocks.LAMPPOST),
					new PMCItemBlock(PMCRegistry.Blocks.LAMPTOP),
					new PMCItemBlock(PMCRegistry.Blocks.LIGHT),
					new PMCItemBlock(PMCRegistry.Blocks.CRATE),
					new PMCItemBlock(PMCRegistry.Blocks.CRATES),
					new PMCItemBlock(PMCRegistry.Blocks.BUSH),
					new PMCItemBlock(PMCRegistry.Blocks.WHEAT),
					new PMCItemBlock(PMCRegistry.Blocks.PROP1),
					new PMCItemBlock(PMCRegistry.Blocks.PROP2),
					new PMCItemBlock(PMCRegistry.Blocks.PROP3),
					new PMCItemBlock(PMCRegistry.Blocks.PROP4),
					new PMCItemBlock(PMCRegistry.Blocks.PROP5),
					new PMCItemBlock(PMCRegistry.Blocks.FENCE),
					new PMCItemBlock(PMCRegistry.Blocks.CONCRETE),
					new PMCItemBlock(PMCRegistry.Blocks.ELECTRICPOLE),
					new PMCItemBlock(PMCRegistry.Blocks.ELECTRICPOLETOP),
					new PMCItemBlock(PMCRegistry.Blocks.ELECTRICCABLE),
					new PMCItemBlock(PMCRegistry.Blocks.RADIOTOWER),
					new PMCItemBlock(PMCRegistry.Blocks.RADIOTOWERTOP),
					new PMCItemBlock(PMCRegistry.Blocks.GUN_WORKBENCH),
					new PMCItemBlock(PMCRegistry.Blocks.BIG_AIRDROP),
					new PMCItemBlock(PMCRegistry.Blocks.COPPER_ORE),
					new PMCItemBlock(PMCRegistry.Blocks.LANDMINE)
			};
			
			event.getRegistry().registerAll(ITEMS);
			event.getRegistry().registerAll(ITEM_BLOCKS);
		}
		
		@SubscribeEvent
		public static void registerEntities(Register<EntityEntry> e)
		{
			final EntityEntry[] entries =
			{
				registerEntity("bullet", EntityBullet.class, 64, 40, true),
				registerEntity("grenade", EntityGrenade.class, 64, 20, true),
				registerEntity("smoke", EntitySmokeGrenade.class, 64, 20, true),
				registerEntity("molotov", EntityMolotov.class, 64, 20, true),
				registerEntity("flare", EntityFlare.class, 64, 20, true),
				registerEntity("parachute", EntityParachute.class, 256, 1, true),
				registerEntity("plane", EntityPlane.class, 128, 25, true),
				registerVehicle("uaz", EntityVehicleUAZ.class)
			};
			
			e.getRegistry().registerAll(entries);
		}
		
		private static EntityEntry registerEntity(String name, Class<? extends Entity> cl, int trackRange, int frequency, boolean velocityUpdates)
		{
			return createEntityBuilder(name).entity(cl).tracker(trackRange, frequency, velocityUpdates).build();
		}
		
		private static EntityEntry registerEntity(String name, Class<? extends Entity> entityClass, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, int eggPrimary, int eggSecondary)
		{
			return createEntityBuilder(name).entity(entityClass).tracker(trackingRange, updateFrequency, sendVelocityUpdates).egg(eggPrimary, eggSecondary).build();
		}
		
		private static EntityEntry registerVehicle(String name, Class<? extends EntityVehicle> vehicleClass)
		{
			return registerEntity(name, vehicleClass, 256, 1, true);
		}
		
		private static <E extends Entity> EntityEntryBuilder<E> createEntityBuilder(String name)
		{
			EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
			ResourceLocation regName = new ResourceLocation(Pubgmc.MOD_ID, name);
			return builder.id(regName, ID()).name(regName.toString());
		}
		
		private static int ID()
		{
			++entityID;
			return entityID;
		}
		
		public static void initTileEntities()
		{
			GameRegistry.registerTileEntity(TileEntityAirdrop.class, new ResourceLocation(Pubgmc.MOD_ID + ":airdrop"));
			GameRegistry.registerTileEntity(TileEntityLamp.class, new ResourceLocation(Pubgmc.MOD_ID + ":lamp"));
			GameRegistry.registerTileEntity(TileEntityLootSpawner.class, new ResourceLocation(Pubgmc.MOD_ID + ":lootspawner"));
			GameRegistry.registerTileEntity(TileEntityPlayerCrate.class, new ResourceLocation(Pubgmc.MOD_ID + ":player_crate"));
			GameRegistry.registerTileEntity(TileEntityGunWorkbench.class, new ResourceLocation(Pubgmc.MOD_ID + ":gun_workbench"));
			GameRegistry.registerTileEntity(TileEntityBigAirdrop.class, new ResourceLocation(Pubgmc.MOD_ID + ":big_airdrop"));
			GameRegistry.registerTileEntity(TileEntityLandMine.class, new ResourceLocation(Pubgmc.MOD_ID + ":landmine"));
		}
	}
	
	@Mod.EventBusSubscriber(Side.CLIENT)
	public static class ModelRegistry
	{
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent e)
		{
			for(ResourceLocation rl : ForgeRegistries.ITEMS.getKeys())
			{
				if(rl.getResourceDomain().equals(Pubgmc.MOD_ID))
					registerModel(ForgeRegistries.ITEMS.getValue(rl));
			}
			
			for(ResourceLocation rl : ForgeRegistries.BLOCKS.getKeys())
			{
				if(rl.getResourceDomain().equals(Pubgmc.MOD_ID))
					registerModel(ForgeRegistries.BLOCKS.getValue(rl));
			}
		}
		
		private static void registerModel(Item item)
		{
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
			
			if(item instanceof GunBase)
			{
				item.setTileEntityItemStackRenderer(new WeaponTEISR());
			}
		}
		
		private static void registerModel(Block block)
		{
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		}
	}
}
