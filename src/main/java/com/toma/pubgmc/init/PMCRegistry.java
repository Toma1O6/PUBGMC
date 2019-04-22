package com.toma.pubgmc.init;

import com.toma.pubgmc.ConfigPMC;
import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.models.BakedModelGun;
import com.toma.pubgmc.client.renderer.WeaponTEISR;
import com.toma.pubgmc.common.HorizontalBlockBuilder;
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
import com.toma.pubgmc.common.blocks.PMCBlockHorizontal;
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
import com.toma.pubgmc.common.items.guns.FlareGun;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.GunBase.Firemode;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import com.toma.pubgmc.common.items.guns.GunBase.ReloadType;
import com.toma.pubgmc.common.items.guns.GunBuilder;
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
import com.toma.pubgmc.event.GunModelAttachEvent;
import com.toma.pubgmc.event.GunPostInitializeEvent;
import com.toma.pubgmc.util.AttachmentHelper;
import com.toma.pubgmc.util.PMCItemBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
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
	public static final class PMCItems
	{
		public static PMCRegistry.PMCItems instance;
		
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
		public static final FlareGun FLARE_GUN = null;
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
		public static final ItemAttachment SILENCER_PISTOL = null;
		public static final ItemAttachment SILENCER_SMG = null;
		public static final ItemAttachment SILENCER_AR = null;
		public static final ItemAttachment SILENCER_SNIPER = null;
		public static final ItemAttachment COMPENSATOR_SMG = null;
		public static final ItemAttachment COMPENSATOR_AR = null;
		public static final ItemAttachment COMPENSATOR_SNIPER = null;
		public static final ItemAttachment RED_DOT = null;
		public static final ItemAttachment HOLOGRAPHIC = null;
		public static final ItemAttachment SCOPE2X = null;
		public static final ItemAttachment SCOPE4X = null;
		public static final ItemAttachment SCOPE8X = null;
		public static final ItemAttachment SCOPE15X = null;
		public static final ItemAttachment GRIP_VERTICAL = null;
		public static final ItemAttachment GRIP_ANGLED = null;
		public static final ItemAttachment QUICKDRAW_MAG_PISTOL = null;
		public static final ItemAttachment EXTENDED_MAG_PISTOL = null;
		public static final ItemAttachment EXTENDED_QUICKDRAW_MAG_PISTOL = null;
		public static final ItemAttachment QUICKDRAW_MAG_SMG = null;
		public static final ItemAttachment EXTENDED_MAG_SMG = null;
		public static final ItemAttachment EXTENDED_QUICKDRAW_MAG_SMG = null;
		public static final ItemAttachment QUICKDRAW_MAG_AR = null;
		public static final ItemAttachment EXTENDED_MAG_AR = null;
		public static final ItemAttachment EXTENDED_QUICKDRAW_MAG_AR = null;
		public static final ItemAttachment QUICKDRAW_MAG_SNIPER = null;
		public static final ItemAttachment EXTENDED_MAG_SNIPER = null;
		public static final ItemAttachment EXTENDED_QUICKDRAW_MAG_SNIPER = null;
		public static final ItemAttachment BULLET_LOOPS_SHOTGUN = null;
		public static final ItemAttachment BULLET_LOOPS_SNIPER = null;
		public static final ItemAttachment CHEEKPAD = null;
		public static final Item PARACHUTE = null;
		public static final Item STEEL_DUST = null;
		public static final Item STEEL_INGOT = null;
		public static final Item COPPER_INGOT = null;
		public static final Item FUELCAN = null;
		public static final Item VEHICLE_UAZ = null;
	}
	
	@ObjectHolder(Pubgmc.MOD_ID)
	public static final class PMCBlocks
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
		
		/** Props by OfficialMajonaise **/
		public static final PMCBlockHorizontal DESK = null;
		public static final PMCBlockHorizontal CHAIR1 = null;
		public static final PMCBlockHorizontal STORAGEBASE = null;
		public static final PMCBlockHorizontal STORAGETOP = null;
		
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
					new BlockLandMine("landmine"),
					HorizontalBlockBuilder.create("desk", Material.WOOD).soundType(SoundType.WOOD).build(),
					HorizontalBlockBuilder.create("chair1", Material.WOOD).soundType(SoundType.WOOD).build(),
					HorizontalBlockBuilder.create("storagebase", Material.WOOD).soundType(SoundType.WOOD).build(),
					HorizontalBlockBuilder.create("storagetop", Material.WOOD).soundType(SoundType.WOOD).build()
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
					new PMCItemBlock(PMCRegistry.PMCBlocks.ROADASPHALT),
					new PMCItemBlock(PMCRegistry.PMCBlocks.SCHOOLWALL),
					new PMCItemBlock(PMCRegistry.PMCBlocks.SCHOOLROOF),
					new PMCItemBlock(PMCRegistry.PMCBlocks.SCHOOLWINDOW),
					new PMCItemBlock(PMCRegistry.PMCBlocks.AIRDROP),
					new PMCItemBlock(PMCRegistry.PMCBlocks.DARKWOOD),
					new PMCItemBlock(PMCRegistry.PMCBlocks.LOOT_SPAWNER),
					new PMCItemBlock(PMCRegistry.PMCBlocks.PLAYER_CRATE),
					new PMCItemBlock(PMCRegistry.PMCBlocks.CHAIR),
					new PMCItemBlock(PMCRegistry.PMCBlocks.TABLE),
					new PMCItemBlock(PMCRegistry.PMCBlocks.RUINSWALL),
					new PMCItemBlock(PMCRegistry.PMCBlocks.BLUEGLASS),
					new PMCItemBlock(PMCRegistry.PMCBlocks.TARGET),
					new PMCItemBlock(PMCRegistry.PMCBlocks.LAMPBOTTOM),
					new PMCItemBlock(PMCRegistry.PMCBlocks.LAMPPOST),
					new PMCItemBlock(PMCRegistry.PMCBlocks.LAMPTOP),
					new PMCItemBlock(PMCRegistry.PMCBlocks.LIGHT),
					new PMCItemBlock(PMCRegistry.PMCBlocks.CRATE),
					new PMCItemBlock(PMCRegistry.PMCBlocks.CRATES),
					new PMCItemBlock(PMCRegistry.PMCBlocks.BUSH),
					new PMCItemBlock(PMCRegistry.PMCBlocks.WHEAT),
					new PMCItemBlock(PMCRegistry.PMCBlocks.PROP1),
					new PMCItemBlock(PMCRegistry.PMCBlocks.PROP2),
					new PMCItemBlock(PMCRegistry.PMCBlocks.PROP3),
					new PMCItemBlock(PMCRegistry.PMCBlocks.PROP4),
					new PMCItemBlock(PMCRegistry.PMCBlocks.PROP5),
					new PMCItemBlock(PMCRegistry.PMCBlocks.FENCE),
					new PMCItemBlock(PMCRegistry.PMCBlocks.CONCRETE),
					new PMCItemBlock(PMCRegistry.PMCBlocks.ELECTRICPOLE),
					new PMCItemBlock(PMCRegistry.PMCBlocks.ELECTRICPOLETOP),
					new PMCItemBlock(PMCRegistry.PMCBlocks.ELECTRICCABLE),
					new PMCItemBlock(PMCRegistry.PMCBlocks.RADIOTOWER),
					new PMCItemBlock(PMCRegistry.PMCBlocks.RADIOTOWERTOP),
					new PMCItemBlock(PMCRegistry.PMCBlocks.GUN_WORKBENCH),
					new PMCItemBlock(PMCRegistry.PMCBlocks.BIG_AIRDROP),
					new PMCItemBlock(PMCRegistry.PMCBlocks.COPPER_ORE),
					new PMCItemBlock(PMCRegistry.PMCBlocks.LANDMINE),
					new PMCItemBlock(PMCRegistry.PMCBlocks.DESK),
					new PMCItemBlock(PMCRegistry.PMCBlocks.CHAIR1),
					new PMCItemBlock(PMCRegistry.PMCBlocks.STORAGEBASE),
					new PMCItemBlock(PMCRegistry.PMCBlocks.STORAGETOP)
			};
			
			event.getRegistry().registerAll(ITEMS);
			event.getRegistry().registerAll(ITEM_BLOCKS);
			event.getRegistry().registerAll(registeredGuns());
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
		
		public static GunBase[] registeredGuns()
		{
			GunBase p92 = GunBuilder.create("p92").damage(ConfigPMC.weaponSettings.p92).velocity(7).gravity(0.015, 4).firerate(2)
					.recoil(2f, 0.5f).reload(ReloadType.MAGAZINE, 25, PMCSounds.reload_p92).ammo(AmmoType.AMMO9MM, 15, 20)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.PISTOL)
					.sound(PMCSounds.gun_p92, 6f, PMCSounds.gun_p92_silenced, 4f)
					.build();
			
			GunBase p1911 = GunBuilder.create("p1911").damage(ConfigPMC.weaponSettings.p1911).velocity(7.25).gravity(0.01, 5).firerate(2)
					.recoil(2f, 0.5f).reload(ReloadType.MAGAZINE, 25, PMCSounds.reload_p1911).ammo(AmmoType.AMMO45ACP, 7, 12)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.PISTOL)
					.sound(PMCSounds.gun_p1911, 6f, PMCSounds.gun_p1911_silenced, 4f)
					.build();
			
			GunBase p18c = GunBuilder.create("p18c").damage(ConfigPMC.weaponSettings.p18c).velocity(7).gravity(0.015, 4).firerate(1)
					.recoil(1.5f, 0.75f).reload(ReloadType.MAGAZINE, 34, PMCSounds.reload_p18c).ammo(AmmoType.AMMO9MM, 17, 25)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.PISTOL)
					.sound(PMCSounds.gun_p18c, 6f, PMCSounds.gun_p18c_silenced, 4f)
					.build();
			
			GunBase r1895 = GunBuilder.create("r1895").damage(ConfigPMC.weaponSettings.r1895).velocity(7.5).gravity(0.01, 5).firerate(13)
					.recoil(2.5f, 1.5f).reload(ReloadType.SINGLE, 14, PMCSounds.reload_r1895).ammo(AmmoType.AMMO762, 7)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.PISTOL)
					.sound(PMCSounds.gun_r1895, 6f, PMCSounds.gun_r1895_silenced, 4f)
					.build();
			
			GunBase r45 = GunBuilder.create("r45").damage(ConfigPMC.weaponSettings.r45).velocity(7.25).gravity(0.01, 5).firerate(12)
					.recoil(2f, 1.5f).reload(ReloadType.MAGAZINE, 40, PMCSounds.reload_r45).ammo(AmmoType.AMMO45ACP, 6)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.PISTOL)
					.sound(PMCSounds.gun_r45, 6f)
					.build();
			
			GunBase scorpion = GunBuilder.create("scorpion").damage(ConfigPMC.weaponSettings.scorpion).velocity(7).gravity(0.015, 4).firerate(1)
					.recoil(1.3f, 0.3f).reload(ReloadType.MAGAZINE, 57, PMCSounds.reload_scorpion).ammo(AmmoType.AMMO9MM, 20, 40)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.PISTOL)
					.sound(PMCSounds.gun_scorpion, 6f, PMCSounds.gun_scorpion_silenced, 4f)
					.build();
			
			GunBase win94 = GunBuilder.create("win94").damage(ConfigPMC.weaponSettings.win94).velocity(12).gravity(0.008, 7).firerate(25)
					.recoil(5.5f, 3.5f).reload(ReloadType.SINGLE, 15, PMCSounds.reload_win94).ammo(AmmoType.AMMO45ACP, 8)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.PISTOL)
					.sound(PMCSounds.gun_win94, 10f)
					.build();
			
			GunBase sawedoff = GunBuilder.create("sawed_off").damage(ConfigPMC.weaponSettings.sawedoff).velocity(5).gravity(0.175, 0).firerate(10)
					.recoil(3.5f, 2f).reload(ReloadType.MAGAZINE, 70, PMCSounds.reload_sawedoff).ammo(AmmoType.AMMO12G, 2)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.SHOTGUN)
					.sound(PMCSounds.gun_sawed_off, 8f)
					.build();
			
			GunBase s1897 = GunBuilder.create("s1897").damage(ConfigPMC.weaponSettings.s1897).velocity(5.5).gravity(0.175, 0).firerate(15)
					.recoil(3.5f, 2f).reload(ReloadType.SINGLE, 10, PMCSounds.reload_s1897).ammo(AmmoType.AMMO12G, 5)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.SHOTGUN)
					.sound(PMCSounds.gun_s1897, 8f)
					.build();
			
			GunBase s686 = GunBuilder.create("s686").damage(ConfigPMC.weaponSettings.s686).velocity(5.5).gravity(0.175, 0).firerate(5)
					.recoil(3.5f, 2f).reload(ReloadType.MAGAZINE, 48, PMCSounds.reload_s686).ammo(AmmoType.AMMO12G, 2)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.SHOTGUN)
					.sound(PMCSounds.gun_s686, 6f)
					.build();
			
			GunBase s12k = GunBuilder.create("s12k").damage(ConfigPMC.weaponSettings.s12k).velocity(5.5).gravity(0.175, 0).firerate(7)
					.recoil(6f, 2f).reload(ReloadType.MAGAZINE, 65, PMCSounds.reload_s12k).ammo(AmmoType.AMMO12G, 5)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.SHOTGUN)
					.sound(PMCSounds.gun_s12k, 8f)
					.build();
			
			GunBase uzi = GunBuilder.create("microuzi").damage(ConfigPMC.weaponSettings.microuzi).velocity(8).gravity(0.02, 4).firerate(1)
					.recoil(2f, 1f).reload(ReloadType.MAGAZINE, 56, PMCSounds.reload_microuzi).ammo(AmmoType.AMMO9MM, 25, 35)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.SMG)
					.sound(PMCSounds.gun_micro_uzi, 8f, PMCSounds.gun_micro_uzi_silenced, 4f)
					.build();
			
			GunBase vector = GunBuilder.create("vector").damage(ConfigPMC.weaponSettings.vector).velocity(8).gravity(0.035, 4).firerate(1)
					.recoil(2f, 1f).reload(ReloadType.MAGAZINE, 30, PMCSounds.reload_vector).ammo(AmmoType.AMMO9MM, 19, 33)
					.firemode(Firemode.AUTO, Firemode.all()).weaponType(GunType.SMG).setTwoRoundBurst()
					.sound(PMCSounds.gun_vector, 8f, PMCSounds.gun_vector_silenced, 4f)
					.build();
			
			GunBase bizon = GunBuilder.create("bizon").damage(ConfigPMC.weaponSettings.bizon).velocity(8).gravity(0.035, 4).firerate(2)
					.recoil(2.5f, 1.25f).reload(ReloadType.MAGAZINE, 62, PMCSounds.reload_bizon).ammo(AmmoType.AMMO9MM, 53)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.SMG)
					.sound(PMCSounds.gun_bizon, 8f, PMCSounds.gun_bizon_silenced, 4f)
					.build();
			
			GunBase tommy = GunBuilder.create("tommy_gun").damage(ConfigPMC.weaponSettings.tommygun).velocity(8.5).gravity(0.02, 5).firerate(2)
					.recoil(2f, 0.75f).reload(ReloadType.MAGAZINE, 60, PMCSounds.reload_tommygun).ammo(AmmoType.AMMO45ACP, 30, 50)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.SMG)
					.sound(PMCSounds.gun_tommy_gun, 8f, PMCSounds.gun_tommy_gun_silenced, 4f)
					.build();
			
			GunBase ump = GunBuilder.create("ump45").damage(ConfigPMC.weaponSettings.ump9).velocity(8.5).gravity(0.02, 5).firerate(2)
					.recoil(1.9f, 1.2f).reload(ReloadType.MAGAZINE, 52, PMCSounds.reload_ump9).ammo(AmmoType.AMMO45ACP, 25, 35)
					.firemode(Firemode.AUTO, Firemode.all()).weaponType(GunType.SMG).setTwoRoundBurst()
					.sound(PMCSounds.gun_ump9, 8f, PMCSounds.gun_ump9_silenced, 4f)
					.build();
			
			GunBase m16a4 = GunBuilder.create("m16a4").damage(ConfigPMC.weaponSettings.m16a4).velocity(12).gravity(0.005, 8).firerate(2)
					.recoil(3.5f, 1f).reload(ReloadType.MAGAZINE, 66, PMCSounds.reload_m16a4).ammo(AmmoType.AMMO556, 30, 40)
					.firemode(Firemode.SINGLE, Firemode.noAuto()).weaponType(GunType.AR)
					.sound(PMCSounds.gun_m16a4, 10f, PMCSounds.gun_m16a4_silenced, 7f)
					.build();
			
			GunBase m416 = GunBuilder.create("m416").damage(ConfigPMC.weaponSettings.m416).velocity(12).gravity(0.0065, 7).firerate(2)
					.recoil(3.5f, 1.5f).reload(ReloadType.MAGAZINE, 66, PMCSounds.reload_m416).ammo(AmmoType.AMMO556, 30, 40)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.AR)
					.sound(PMCSounds.gun_m416, 10f, PMCSounds.gun_m416_silenced, 7f)
					.build();
			
			GunBase scarl = GunBuilder.create("scar_l").damage(ConfigPMC.weaponSettings.scarl).velocity(11).gravity(0.007, 7).firerate(2)
					.recoil(3.25f, 1.25f).reload(ReloadType.MAGAZINE, 65, PMCSounds.reload_scarl).ammo(AmmoType.AMMO556, 30, 40)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.AR)
					.sound(PMCSounds.gun_scarl, 10f, PMCSounds.gun_scarl_silenced, 7f)
					.build();
			
			GunBase qbz = GunBuilder.create("qbz").damage(ConfigPMC.weaponSettings.qbz).velocity(11).gravity(0.007, 7).firerate(2)
					.recoil(3.25f, 1.25f).reload(ReloadType.MAGAZINE, 70, PMCSounds.reload_qbz).ammo(AmmoType.AMMO556, 30, 40)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.AR)
					.sound(PMCSounds.gun_qbz, 10f, PMCSounds.gun_qbz_silenced, 7f)
					.build();
			
			GunBase g36c = GunBuilder.create("g36c").damage(ConfigPMC.weaponSettings.g36c).velocity(11).gravity(0.0065, 7).firerate(2)
					.recoil(3.5f, 1.5f).reload(ReloadType.MAGAZINE, 82, PMCSounds.reload_g36c).ammo(AmmoType.AMMO556, 30, 40)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.AR)
					.sound(PMCSounds.gun_g36c, 10f, PMCSounds.gun_g36c_silenced, 7f)
					.build();
			
			GunBase aug = GunBuilder.create("aug").damage(ConfigPMC.weaponSettings.aug).velocity(12).gravity(0.0065, 7).firerate(2)
					.recoil(3.75f, 1.25f).reload(ReloadType.MAGAZINE, 69, PMCSounds.reload_aug).ammo(AmmoType.AMMO556, 30, 40)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.AR)
					.sound(PMCSounds.gun_aug, 10f, PMCSounds.gun_aug_silenced, 7f)
					.build();
			
			GunBase akm = GunBuilder.create("akm").damage(ConfigPMC.weaponSettings.akm).velocity(9).gravity(0.025, 7).firerate(2)
					.recoil(4f, 2f).reload(ReloadType.MAGAZINE, 60, PMCSounds.reload_akm).ammo(AmmoType.AMMO762, 30, 40)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.AR)
					.sound(PMCSounds.gun_akm, 10f, PMCSounds.gun_akm_silenced, 7f)
					.build();
			
			GunBase m762 = GunBuilder.create("beryl_m762").damage(ConfigPMC.weaponSettings.m762).velocity(9.5).gravity(0.025, 7).firerate(2)
					.recoil(4.25f, 2.15f).reload(ReloadType.MAGAZINE, 50, PMCSounds.reload_m762).ammo(AmmoType.AMMO762, 30, 40)
					.firemode(Firemode.AUTO, Firemode.all()).weaponType(GunType.AR)
					.sound(PMCSounds.gun_m762, 10f, PMCSounds.gun_m762_silenced, 7f)
					.build();
			
			GunBase mk47 = GunBuilder.create("mk47_mutant").damage(ConfigPMC.weaponSettings.mk47).velocity(9).gravity(0.025, 7).firerate(2)
					.recoil(4f, 1.75f).reload(ReloadType.MAGAZINE, 66, PMCSounds.reload_mk47).ammo(AmmoType.AMMO762, 20, 30)
					.firemode(Firemode.SINGLE, Firemode.noAuto()).weaponType(GunType.AR).setTwoRoundBurst()
					.sound(PMCSounds.gun_mk47, 10f, PMCSounds.gun_mk47_silenced, 7f)
					.build();
			
			GunBase groza = GunBuilder.create("groza").damage(ConfigPMC.weaponSettings.groza).velocity(9).gravity(0.02, 7).firerate(2)
					.recoil(3.75f, 1.75f).reload(ReloadType.MAGAZINE, 50, PMCSounds.reload_groza).ammo(AmmoType.AMMO762, 30, 40)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.AR)
					.sound(PMCSounds.gun_groza, 10f, PMCSounds.gun_groza_silenced, 7f)
					.build();
			
			GunBase dp28 = GunBuilder.create("dp28").damage(ConfigPMC.weaponSettings.dp28).velocity(9).gravity(0.03, 6).firerate(2)
					.recoil(4.5f, 2.5f).reload(ReloadType.MAGAZINE, 95, PMCSounds.reload_dp28).ammo(AmmoType.AMMO762, 47)
					.firemode(Firemode.AUTO, Firemode.AUTO).weaponType(GunType.LMG)
					.sound(PMCSounds.gun_dp28, 10f)
					.build();
			
			GunBase m249 = GunBuilder.create("m249").damage(ConfigPMC.weaponSettings.m249).velocity(11).gravity(0.007, 6).firerate(2)
					.recoil(3.25f, 1f).reload(ReloadType.MAGAZINE, 148, PMCSounds.reload_m249).ammo(AmmoType.AMMO556, 100)
					.firemode(Firemode.AUTO, Firemode.AUTO).weaponType(GunType.LMG)
					.sound(PMCSounds.gun_m249, 10f)
					.build();
			
			GunBase vss = GunBuilder.create("vss").damage(ConfigPMC.weaponSettings.vss).velocity(7).gravity(0.035, 2).firerate(2)
					.recoil(1.5f, 0.5f).reload(ReloadType.MAGAZINE, 40, PMCSounds.reload_vss).ammo(AmmoType.AMMO9MM, 10, 20)
					.firemode(Firemode.AUTO, Firemode.noBurst()).weaponType(GunType.DMR)
					.sound(PMCSounds.gun_vss, 2.5f)
					.build();
			
			GunBase mini14 = GunBuilder.create("mini14").damage(ConfigPMC.weaponSettings.mini14).velocity(14).gravity(0.015, 8).firerate(2)
					.recoil(3.5f, 2f).reload(ReloadType.MAGAZINE, 62, PMCSounds.reload_mini14).ammo(AmmoType.AMMO556, 20, 30)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.DMR)
					.sound(PMCSounds.gun_mini14, 12f, PMCSounds.gun_mini14_silenced, 8f)
					.build();
			
			GunBase qbu = GunBuilder.create("qbu").damage(ConfigPMC.weaponSettings.qbu).velocity(14).gravity(0.015, 8).firerate(2)
					.recoil(3.5f, 2f).reload(ReloadType.MAGAZINE, 44, PMCSounds.reload_qbu).ammo(AmmoType.AMMO556, 10, 20)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.DMR)
					.sound(PMCSounds.gun_qbu, 12f, PMCSounds.gun_qbu_silenced, 6f)
					.build();
			
			GunBase sks = GunBuilder.create("sks").damage(ConfigPMC.weaponSettings.sks).velocity(10).gravity(0.035, 7).firerate(2)
					.recoil(5.5f, 2.25f).reload(ReloadType.MAGAZINE, 32, PMCSounds.reload_sks).ammo(AmmoType.AMMO762, 10, 20)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.DMR)
					.sound(PMCSounds.gun_sks, 12f, PMCSounds.gun_sks_silenced, 8f)
					.build();
			
			GunBase slr = GunBuilder.create("slr").damage(ConfigPMC.weaponSettings.slr).velocity(10).gravity(0.035, 7).firerate(2)
					.recoil(5.75f, 2.25f).reload(ReloadType.MAGAZINE, 53, PMCSounds.reload_slr).ammo(AmmoType.AMMO762, 10, 20)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.DMR)
					.sound(PMCSounds.gun_slr, 12f, PMCSounds.gun_slr_silenced, 8f)
					.build();
			
			GunBase mk14 = GunBuilder.create("mk14").damage(ConfigPMC.weaponSettings.mk14).velocity(11).gravity(0.025, 7).firerate(2)
					.recoil(7.5f, 3.25f).reload(ReloadType.MAGAZINE, 39, PMCSounds.reload_mk14).ammo(AmmoType.AMMO762, 10, 20)
					.firemode(Firemode.SINGLE, Firemode.noBurst()).weaponType(GunType.DMR)
					.sound(PMCSounds.gun_mk14, 12f, PMCSounds.gun_mk14_silenced, 8f)
					.build();
			
			GunBase kar98k = GunBuilder.create("kar98k").damage(ConfigPMC.weaponSettings.kar98k).velocity(11).gravity(0.04, 8).firerate(30)
					.recoil(4.5f, 1.25f).reload(ReloadType.KAR98K, 63, PMCSounds.reload_kar98k).ammo(AmmoType.AMMO762, 5)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.SR)
					.sound(PMCSounds.gun_kar98k, 15f, PMCSounds.gun_kar98k_silenced, 10f)
					.build();
			
			GunBase m24 = GunBuilder.create("m24").damage(ConfigPMC.weaponSettings.m24).velocity(11.5).gravity(0.03, 7).firerate(35)
					.recoil(4.5f, 1.25f).reload(ReloadType.MAGAZINE, 71, PMCSounds.reload_m24).ammo(AmmoType.AMMO762, 5, 7)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.SR)
					.sound(PMCSounds.gun_m24, 15f, PMCSounds.gun_m24_silenced, 10f)
					.build();
			
			GunBase awm = GunBuilder.create("awm").damage(ConfigPMC.weaponSettings.awm).velocity(17).gravity(0.005, 10).firerate(35)
					.recoil(4.5f, 1.25f).reload(ReloadType.MAGAZINE, 78, PMCSounds.reload_awm).ammo(AmmoType.AMMO300M, 5, 7)
					.firemode(Firemode.SINGLE, Firemode.SINGLE).weaponType(GunType.SR)
					.sound(PMCSounds.gun_awm, 15f, PMCSounds.gun_awm_silenced, 10f)
					.build();
			
			final GunBase[] entry = 
			{
				p92, p1911, p18c, r1895, r45, scorpion, win94,
				sawedoff, s1897, s686, s12k,
				uzi, vector, bizon, tommy, ump,
				m16a4, m416, scarl, qbz, g36c, aug, akm, m762, mk47, groza,
				dp28, m249,
				vss, mini14, qbu, sks, slr, mk14,
				kar98k, m24, awm
			};
			
			return entry;
		}
		
		public static void initTileEntities()
		{
			registerTileEntity(TileEntityAirdrop.class, "airdrop");
			registerTileEntity(TileEntityLamp.class, "lamp");
			registerTileEntity(TileEntityLootSpawner.class, "lootspawner");
			registerTileEntity(TileEntityPlayerCrate.class, "player_crate");
			registerTileEntity(TileEntityGunWorkbench.class, "gun_workbench");
			registerTileEntity(TileEntityBigAirdrop.class, "big_airdrop");
			registerTileEntity(TileEntityLandMine.class, "landmine");
		}
		
		private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String name)
		{
			GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(Pubgmc.MOD_ID + ":" + name));
		}
	}
	
	@Mod.EventBusSubscriber(Side.CLIENT)
	public static class ModelRegistry
	{
		@SubscribeEvent
		public static void bakeModels(ModelBakeEvent e)
		{
			ModelResourceLocation location;

			for(int i = 0; i < GunBase.GUNS.size(); i++)
			{
				GunBase gun = GunBase.GUNS.get(i);
				location = new ModelResourceLocation(gun.getRegistryName(), "inventory");
				e.getModelRegistry().putObject(location, new BakedModelGun());
			}
		}
		
		@SubscribeEvent
		public static void onModelAttach(GunModelAttachEvent e)
		{
			GunBase g = e.getGun();
			
			switch(e.getName())
			{
				case "flare_gun": e.attachModel(e.getTEISR().flareGun); break;
				case "p92": e.attachModel(e.getTEISR().p92); break;
				case "p1911": e.attachModel(e.getTEISR().p1911); break;
				case "p18c": e.attachModel(e.getTEISR().p18c); break;
				case "r1895": e.attachModel(e.getTEISR().r1895); break;
				case "r45": e.attachModel(e.getTEISR().r45); break;
				case "scorpion": e.attachModel(e.getTEISR().scorpion); break;
				case "win94": e.attachModel(e.getTEISR().win94); break;
				case "sawed_off": e.attachModel(e.getTEISR().sawedOff); break;
				case "s1897": e.attachModel(e.getTEISR().s1897); break;
				case "s686": e.attachModel(e.getTEISR().s686); break;
				case "s12k": e.attachModel(e.getTEISR().s12k); break;
				case "microuzi": e.attachModel(e.getTEISR().microuzi); break;
				case "vector": e.attachModel(e.getTEISR().vector); break;
				case "bizon": e.attachModel(e.getTEISR().bizon); break;
				case "tommy_gun": e.attachModel(e.getTEISR().tommygun); break;
				case "ump45": e.attachModel(e.getTEISR().ump); break;
				case "m16a4": e.attachModel(e.getTEISR().m16a4); break;
				case "m416": e.attachModel(e.getTEISR().m416); break;
				case "scar_l": e.attachModel(e.getTEISR().scar); break;
				case "qbz": e.attachModel(e.getTEISR().qbz); break;
				case "g36c": e.attachModel(e.getTEISR().g36c); break;
				case "aug": e.attachModel(e.getTEISR().aug); break;
				case "akm": e.attachModel(e.getTEISR().akm); break;
				case "beryl_m762": e.attachModel(e.getTEISR().m762); break;
				case "mk47_mutant": e.attachModel(e.getTEISR().mk47); break;
				case "groza": e.attachModel(e.getTEISR().groza); break;
				case "dp28": e.attachModel(e.getTEISR().dp28); break;
				case "m249": e.attachModel(e.getTEISR().m249); break;
				case "vss": e.attachModel(e.getTEISR().vss); break;
				case "mini14": e.attachModel(e.getTEISR().mini14); break;
				case "qbu": e.attachModel(e.getTEISR().qbu); break;
				case "sks": e.attachModel(e.getTEISR().sks); break;
				case "slr": e.attachModel(e.getTEISR().slr); break;
				case "mk14": e.attachModel(e.getTEISR().mk14); break;
				case "kar98k": e.attachModel(e.getTEISR().kar98k); break;
				case "m24": e.attachModel(e.getTEISR().m24); break;
				case "awm": e.attachModel(e.getTEISR().awm); break;
			}
		}
		
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
				MinecraftForge.EVENT_BUS.post(new GunModelAttachEvent((GunBase)item, item.getRegistryName()));
			}
		}
		
		private static void registerModel(Block block)
		{
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		}
	}
	
	@Mod.EventBusSubscriber
	public static class GunInit
	{
		@SubscribeEvent
		public static void onGunInitialized(GunPostInitializeEvent e)
		{
			GunBase gun = e.getGun();
			
			if(gun == PMCItems.FLARE_GUN)
			{
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 15));
				e.addCraftingIngredient(new ItemStack(PMCItems.STEEL_INGOT, 20));
				e.addCraftingIngredient(new ItemStack(Items.DYE, 10, 1));
			}
			
			else if(gun == PMCItems.P92)
			{
				e.addCraftingIngredient(new ItemStack(PMCItems.STEEL_INGOT, 10));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 15));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS));
				e.initBarrelAttachments(PMCItems.SILENCER_PISTOL);
				e.initMagazineAttachments(AttachmentHelper.getPistolMagazineAttachments());
				e.initScopeAttachments(PMCItems.RED_DOT);
			}
			
			else if(gun == PMCItems.P1911)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 12));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 20));
				e.addCraftingIngredient(new ItemStack(Blocks.STONE, 1));
				e.initBarrelAttachments(PMCItems.SILENCER_PISTOL);
				e.initMagazineAttachments(AttachmentHelper.getPistolMagazineAttachments());
				e.initScopeAttachments(PMCItems.RED_DOT);
			}
			
			else if(gun == PMCItems.P18C)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 8));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 20));
				e.initBarrelAttachments(PMCItems.SILENCER_PISTOL);
				e.initMagazineAttachments(AttachmentHelper.getPistolMagazineAttachments());
				e.initScopeAttachments(PMCItems.RED_DOT);
			}
			
			else if(gun == PMCItems.R1895)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 20));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 5));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS, 2));
				e.initBarrelAttachments(PMCItems.SILENCER_PISTOL);
			}
			
			else if(gun == PMCItems.R45)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 18));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 5));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS, 2));
				e.initScopeAttachments(PMCItems.RED_DOT);
			}
			
			else if(gun == PMCItems.SCORPION)
			{
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 30));
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 25));
				e.initBarrelAttachments(PMCItems.SILENCER_PISTOL);
				e.initGripAttachments(PMCItems.GRIP_VERTICAL);
				e.initMagazineAttachments(PMCItems.EXTENDED_MAG_PISTOL);
				e.initScopeAttachments(PMCItems.RED_DOT);
			}
			
			else if(gun == PMCItems.WIN94)
			{
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 25));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS, 5));
				e.initStockAttachments(PMCItems.BULLET_LOOPS_SNIPER);
			}
			
			else if(gun == PMCItems.SAWED_OFF)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 25));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 10));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS, 7));
			}
			
			else if(gun == PMCItems.S1897)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 25));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 15));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS, 10));
				e.initStockAttachments(PMCItems.BULLET_LOOPS_SHOTGUN);
			}
			
			else if(gun == PMCItems.S686)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 20));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 20));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS, 10));
				e.initStockAttachments(PMCItems.BULLET_LOOPS_SHOTGUN);
			}
			
			else if(gun == PMCItems.S12K)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 35));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 40));
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.MICROUZI)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 15));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 40));
				e.initBarrelAttachments(AttachmentHelper.getSMGBarrelAttachments());
				e.initMagazineAttachments(AttachmentHelper.getSMGMagazineAttachments());
			}
			
			else if(gun == PMCItems.VECTOR)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 25));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 35));
				e.initBarrelAttachments(AttachmentHelper.getSMGBarrelAttachments());
				e.initGripAttachments(PMCItems.GRIP_VERTICAL);
				e.initMagazineAttachments(AttachmentHelper.getSMGMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.BIZON)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 25));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 40));
				e.initBarrelAttachments(AttachmentHelper.getSMGBarrelAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.TOMMY_GUN)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 15));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 25));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS, 3));
				e.initBarrelAttachments(PMCItems.SILENCER_SMG);
				e.initGripAttachments(PMCItems.GRIP_VERTICAL);
				e.initMagazineAttachments(AttachmentHelper.getSMGMagazineAttachments());
			}
			
			else if(gun == PMCItems.UMP45)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 25));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 40));
				e.initBarrelAttachments(AttachmentHelper.getSMGBarrelAttachments());
				e.initGripAttachments(AttachmentHelper.getGrips());
				e.initMagazineAttachments(AttachmentHelper.getSMGMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.M16A4)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 50));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 35));
				e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
				e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.M416)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 35));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 50));
				e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
				e.initGripAttachments(AttachmentHelper.getGrips());
				e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.SCAR_L)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 35));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 50));
				e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
				e.initGripAttachments(AttachmentHelper.getGrips());
				e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.QBZ)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 35));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 50));
				e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
				e.initGripAttachments(AttachmentHelper.getGrips());
				e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.G36C)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 35));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 50));
				e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
				e.initGripAttachments(AttachmentHelper.getGrips());
				e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.AUG)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 40));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 50));
				e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
				e.initGripAttachments(AttachmentHelper.getGrips());
				e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.AKM)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 40));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 50));
				e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
				e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.BERYL_M762)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 40));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 50));
				e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
				e.initGripAttachments(AttachmentHelper.getGrips());
				e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.MK47_MUTANT)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 40));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 50));
				e.initBarrelAttachments(AttachmentHelper.getARBarrelAttachments());
				e.initGripAttachments(AttachmentHelper.getGrips());
				e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.GROZA)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 40));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 50));
				e.initBarrelAttachments(PMCItems.SILENCER_AR);
				e.initMagazineAttachments(AttachmentHelper.getARMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.DP28)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 50));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 50));
				e.addCraftingIngredient(new ItemStack(Blocks.IRON_BLOCK, 3));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS, 10));
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.M249)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 60));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 60));
				e.addCraftingIngredient(new ItemStack(Blocks.IRON_BLOCK, 3));
				e.addCraftingIngredient(new ItemStack(Blocks.STONE, 5));
				e.initScopeAttachments(AttachmentHelper.getSmallScopes());
			}
			
			else if(gun == PMCItems.VSS)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 30));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 35));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS, 20));
				e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
				e.initStockAttachments(PMCItems.CHEEKPAD);
			}
			
			else if(gun == PMCItems.MINI14)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 50));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 35));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS, 5));
				e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
				e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getScopes());
			}
			
			else if(gun == PMCItems.QBU)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 50));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 35));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS, 5));
				e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
				e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getScopes());
			}
			
			else if(gun == PMCItems.SKS)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 50));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 35));
				e.addCraftingIngredient(new ItemStack(Blocks.STONE, 5));
				e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
				e.initGripAttachments(AttachmentHelper.getGrips());
				e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getScopes());
				e.initStockAttachments(PMCItems.CHEEKPAD);
			}
			
			else if(gun == PMCItems.SLR)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 60));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 40));
				e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
				e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getScopes());
				e.initStockAttachments(PMCItems.CHEEKPAD);
			}
			
			else if(gun == PMCItems.MK14)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 60));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 40));
				e.addCraftingIngredient(new ItemStack(Blocks.STONE, 5));
				e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
				e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getScopes());
				e.initStockAttachments(PMCItems.CHEEKPAD);
			}
			
			else if(gun == PMCItems.KAR98K)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 50));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 40));
				e.addCraftingIngredient(new ItemStack(Blocks.IRON_BLOCK, 1));
				e.addCraftingIngredient(new ItemStack(Blocks.PLANKS, 15));
				e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
				e.initScopeAttachments(AttachmentHelper.getScopes());
				e.initStockAttachments(AttachmentHelper.getStock(true));
			}
			
			else if(gun == PMCItems.M24)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 60));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 50));
				e.addCraftingIngredient(new ItemStack(Blocks.IRON_BLOCK, 1));
				e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
				e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getScopes());
				e.initStockAttachments(PMCItems.CHEEKPAD);
			}
			
			else if(gun == PMCItems.AWM)
			{
				e.addCraftingIngredient(new ItemStack(PMCRegistry.PMCItems.STEEL_INGOT, 50));
				e.addCraftingIngredient(new ItemStack(Items.IRON_INGOT, 50));
				e.addCraftingIngredient(new ItemStack(Blocks.IRON_BLOCK, 2));
				e.addCraftingIngredient(new ItemStack(Items.DIAMOND, 3));
				e.initBarrelAttachments(AttachmentHelper.getSRBarrelAttachments());
				e.initMagazineAttachments(AttachmentHelper.getSRMagazineAttachments());
				e.initScopeAttachments(AttachmentHelper.getScopes());
				e.initStockAttachments(PMCItems.CHEEKPAD);
			}
		}
	}
}
