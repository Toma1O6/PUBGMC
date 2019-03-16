package com.toma.pubgmc.init;

import com.toma.pubgmc.Pubgmc;
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
import com.toma.pubgmc.common.items.guns.SmgUmp9;
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
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler
{
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
				new SmgUmp9("ump9"),
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
				new ItemPan("pan", PMCMaterials.MATERIAL_PAN),
				new ItemClothing("ghilliehelmet", PMCMaterials.GHILLIE, 1, EntityEquipmentSlot.HEAD),
				new ItemClothing("ghilliebody", PMCMaterials.GHILLIE, 1, EntityEquipmentSlot.CHEST),
				new ItemClothing("ghillielegs", PMCMaterials.GHILLIE, 2, EntityEquipmentSlot.LEGS),
				new ItemClothing("ghillieboots", PMCMaterials.GHILLIE, 1, EntityEquipmentSlot.FEET),
				new ArmorBase("armor1helmet", PMCMaterials.LVL1, 1, EntityEquipmentSlot.HEAD).setArmorLevel(ArmorLevel.LEVEL_ONE),
				new ArmorBase("armor1body", PMCMaterials.LVL1, 1, EntityEquipmentSlot.CHEST).setArmorLevel(ArmorLevel.LEVEL_ONE),
				new ArmorBase("armor2helmet", PMCMaterials.LVL2, 1, EntityEquipmentSlot.HEAD).setArmorLevel(ArmorLevel.LEVEL_TWO),
				new ArmorBase("armor2body", PMCMaterials.LVL2, 1, EntityEquipmentSlot.CHEST).setArmorLevel(ArmorLevel.LEVEL_TWO),
				new ArmorBase("armor3helmet", PMCMaterials.LVL3, 1, EntityEquipmentSlot.HEAD).setArmorLevel(ArmorLevel.LEVEL_THREE),
				new ArmorBase("armor3body", PMCMaterials.LVL3, 1, EntityEquipmentSlot.CHEST).setArmorLevel(ArmorLevel.LEVEL_THREE),
				new ItemClothing("black_glasses", PMCMaterials.CLOTH1, 1, EntityEquipmentSlot.HEAD),
				new ItemClothing("yellow_tshirt", PMCMaterials.CLOTH1, 1, EntityEquipmentSlot.CHEST),
				new ItemClothing("gray_top", PMCMaterials.CLOTH2, 1, EntityEquipmentSlot.CHEST),
				new ItemClothing("brown_cap", PMCMaterials.CLOTH2, 1, EntityEquipmentSlot.HEAD),
				new ItemClothing("white_boots", PMCMaterials.CLOTH2, 1, EntityEquipmentSlot.FEET),
				new ItemClothing("official_legs", PMCMaterials.CLOTH3, 2, EntityEquipmentSlot.LEGS),
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
				new PMCItemBlock(PMCBlocks.ROADASPHALT),
				new PMCItemBlock(PMCBlocks.SCHOOLWALL),
				new PMCItemBlock(PMCBlocks.SCHOOLROOF),
				new PMCItemBlock(PMCBlocks.SCHOOLWINDOW),
				new PMCItemBlock(PMCBlocks.AIRDROP),
				new PMCItemBlock(PMCBlocks.DARKWOOD),
				new PMCItemBlock(PMCBlocks.LOOT_SPAWNER),
				new PMCItemBlock(PMCBlocks.PLAYER_CRATE),
				new PMCItemBlock(PMCBlocks.CHAIR),
				new PMCItemBlock(PMCBlocks.TABLE),
				new PMCItemBlock(PMCBlocks.RUINSWALL),
				new PMCItemBlock(PMCBlocks.BLUEGLASS),
				new PMCItemBlock(PMCBlocks.TARGET),
				new PMCItemBlock(PMCBlocks.LAMPBOTTOM),
				new PMCItemBlock(PMCBlocks.LAMPPOST),
				new PMCItemBlock(PMCBlocks.LAMPTOP),
				new PMCItemBlock(PMCBlocks.LIGHT),
				new PMCItemBlock(PMCBlocks.CRATE),
				new PMCItemBlock(PMCBlocks.CRATES),
				new PMCItemBlock(PMCBlocks.BUSH),
				new PMCItemBlock(PMCBlocks.WHEAT),
				new PMCItemBlock(PMCBlocks.PROP1),
				new PMCItemBlock(PMCBlocks.PROP2),
				new PMCItemBlock(PMCBlocks.PROP3),
				new PMCItemBlock(PMCBlocks.PROP4),
				new PMCItemBlock(PMCBlocks.PROP5),
				new PMCItemBlock(PMCBlocks.FENCE),
				new PMCItemBlock(PMCBlocks.CONCRETE),
				new PMCItemBlock(PMCBlocks.ELECTRICPOLE),
				new PMCItemBlock(PMCBlocks.ELECTRICPOLETOP),
				new PMCItemBlock(PMCBlocks.ELECTRICCABLE),
				new PMCItemBlock(PMCBlocks.RADIOTOWER),
				new PMCItemBlock(PMCBlocks.RADIOTOWERTOP),
				new PMCItemBlock(PMCBlocks.GUN_WORKBENCH),
				new PMCItemBlock(PMCBlocks.BIG_AIRDROP),
				new PMCItemBlock(PMCBlocks.COPPER_ORE),
				new PMCItemBlock(PMCBlocks.LANDMINE)
		};
		
		event.getRegistry().registerAll(ITEMS);
		event.getRegistry().registerAll(ITEM_BLOCKS);
	}
	
	public static void registerTileEntities()
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
