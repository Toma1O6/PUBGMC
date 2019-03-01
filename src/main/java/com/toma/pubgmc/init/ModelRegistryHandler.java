package com.toma.pubgmc.init;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.renderer.WeaponTEISR;
import com.toma.pubgmc.common.items.guns.GunBase;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class ModelRegistryHandler 
{
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent e)
	{
		registerModel(PMCItems.BACKPACK1);
		registerModel(PMCItems.BACKPACK2);
		registerModel(PMCItems.BACKPACK3);
		registerModel(PMCItems.BANDAGE);
		registerModel(PMCItems.FIRSTAIDKIT);
		registerModel(PMCItems.MEDKIT);
		registerModel(PMCItems.ENERGYDRINK);
		registerModel(PMCItems.PAINKILLERS);
		registerModel(PMCItems.ADRENALINESYRINGE);
		registerModel(PMCItems.IBLOCK);
		registerModel(PMCItems.GHILLIE_SUIT);
		registerModel(PMCItems.NV_GOGGLES);
		registerModel(PMCItems.FLARE_GUN);
		registerModel(PMCItems.P92);
		registerModel(PMCItems.P1911);
		registerModel(PMCItems.R1895);
		registerModel(PMCItems.R45);
		registerModel(PMCItems.P18C);
		registerModel(PMCItems.SCORPION);
		registerModel(PMCItems.WIN94);
		registerModel(PMCItems.SAWED_OFF);
		registerModel(PMCItems.S1897);
		registerModel(PMCItems.S686);
		registerModel(PMCItems.S12K);
		registerModel(PMCItems.MICROUZI);
		registerModel(PMCItems.UMP9);
		registerModel(PMCItems.VECTOR);
		registerModel(PMCItems.TOMMY_GUN);
		registerModel(PMCItems.BIZON);
		registerModel(PMCItems.M16A4);
		registerModel(PMCItems.M416);
		registerModel(PMCItems.SCAR_L);
		registerModel(PMCItems.G36C);
		registerModel(PMCItems.QBZ);
		registerModel(PMCItems.AUG);
		registerModel(PMCItems.AKM);
		registerModel(PMCItems.BERYL_M762);
		registerModel(PMCItems.MK47_MUTANT);
		registerModel(PMCItems.GROZA);
		registerModel(PMCItems.DP28);
		registerModel(PMCItems.M249);
		registerModel(PMCItems.VSS);
		registerModel(PMCItems.MINI14);
		registerModel(PMCItems.QBU);
		registerModel(PMCItems.SKS);
		registerModel(PMCItems.SLR);
		registerModel(PMCItems.MK14);
		registerModel(PMCItems.KAR98K);
		registerModel(PMCItems.M24);
		registerModel(PMCItems.AWM);
		registerModel(PMCItems.GRENADE);
		registerModel(PMCItems.SMOKE);
		registerModel(PMCItems.MOLOTOV);
		registerModel(PMCItems.AMMO_9MM);
		registerModel(PMCItems.AMMO_45ACP);
		registerModel(PMCItems.AMMO_SHOTGUN);
		registerModel(PMCItems.AMMO_556);
		registerModel(PMCItems.AMMO_762);
		registerModel(PMCItems.AMMO_300M);
		registerModel(PMCItems.AMMO_FLARE);
		registerModel(PMCItems.CASE1);
		registerModel(PMCItems.PAN);
		registerModel(PMCItems.GHILLIEHELMET);
		registerModel(PMCItems.GHILLIEBODY);
		registerModel(PMCItems.GHILLIELEGS);
		registerModel(PMCItems.GHILLIEBOOTS);
		registerModel(PMCItems.ARMOR1HELMET);
		registerModel(PMCItems.ARMOR1BODY);
		registerModel(PMCItems.ARMOR2HELMET);
		registerModel(PMCItems.ARMOR2BODY);
		registerModel(PMCItems.ARMOR3HELMET);
		registerModel(PMCItems.ARMOR3BODY);
		registerModel(PMCItems.BLACK_GLASSES);
		registerModel(PMCItems.YELLOW_TSHIRT);
		registerModel(PMCItems.GRAY_TOP);
		registerModel(PMCItems.BROWN_CAP);
		registerModel(PMCItems.WHITE_BOOTS);
		registerModel(PMCItems.OFFICIAL_LEGS);
		registerModel(PMCItems.SILENCER_PISTOL);
		registerModel(PMCItems.SILENCER_SMG);
		registerModel(PMCItems.SILENCER_AR);
		registerModel(PMCItems.SILENCER_SNIPER);
		registerModel(PMCItems.COMPENSATOR_SMG);
		registerModel(PMCItems.COMPENSATOR_AR);
		registerModel(PMCItems.COMPENSATOR_SNIPER);
		registerModel(PMCItems.RED_DOT);
		registerModel(PMCItems.HOLOGRAPHIC);
		registerModel(PMCItems.SCOPE2X);
		registerModel(PMCItems.SCOPE4X);
		registerModel(PMCItems.SCOPE8X);
		registerModel(PMCItems.SCOPE15X);
		registerModel(PMCItems.GRIP_VERTICAL);
		registerModel(PMCItems.GRIP_ANGLED);
		registerModel(PMCItems.QUICKDRAW_MAG_PISTOL);
		registerModel(PMCItems.EXTENDED_MAG_PISTOL);
		registerModel(PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL);
		registerModel(PMCItems.QUICKDRAW_MAG_SMG);
		registerModel(PMCItems.EXTENDED_MAG_SMG);
		registerModel(PMCItems.EXTENDED_QUICKDRAW_MAG_SMG);
		registerModel(PMCItems.QUICKDRAW_MAG_AR);
		registerModel(PMCItems.EXTENDED_MAG_AR);
		registerModel(PMCItems.EXTENDED_QUICKDRAW_MAG_AR);
		registerModel(PMCItems.QUICKDRAW_MAG_SNIPER);
		registerModel(PMCItems.EXTENDED_MAG_SNIPER);
		registerModel(PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER);
		registerModel(PMCItems.BULLET_LOOPS_SHOTGUN);
		registerModel(PMCItems.BULLET_LOOPS_SNIPER);
		registerModel(PMCItems.CHEEKPAD);
		registerModel(PMCItems.PARACHUTE);
		registerModel(PMCItems.STEEL_DUST);
		registerModel(PMCItems.STEEL_INGOT);
		registerModel(PMCItems.COPPER_INGOT);
		
		//Blocks
		registerModel(PMCBlocks.ROADASPHALT);
		registerModel(PMCBlocks.SCHOOLWALL);
		registerModel(PMCBlocks.SCHOOLROOF);
		registerModel(PMCBlocks.SCHOOLWINDOW);
		registerModel(PMCBlocks.AIRDROP);
		registerModel(PMCBlocks.DARKWOOD);
		registerModel(PMCBlocks.LOOT_SPAWNER);
		registerModel(PMCBlocks.PLAYER_CRATE);
		registerModel(PMCBlocks.CHAIR);
		registerModel(PMCBlocks.TABLE);
		registerModel(PMCBlocks.RUINSWALL);
		registerModel(PMCBlocks.BLUEGLASS);
		registerModel(PMCBlocks.TARGET);
		registerModel(PMCBlocks.LAMPBOTTOM);
		registerModel(PMCBlocks.LAMPPOST);
		registerModel(PMCBlocks.LAMPTOP);
		registerModel(PMCBlocks.LIGHT);
		registerModel(PMCBlocks.CRATE);
		registerModel(PMCBlocks.CRATES);
		registerModel(PMCBlocks.BUSH);
		registerModel(PMCBlocks.WHEAT);
		registerModel(PMCBlocks.PROP1);
		registerModel(PMCBlocks.PROP2);
		registerModel(PMCBlocks.PROP3);
		registerModel(PMCBlocks.PROP4);
		registerModel(PMCBlocks.PROP5);
		registerModel(PMCBlocks.FENCE);
		registerModel(PMCBlocks.CONCRETE);
		registerModel(PMCBlocks.ELECTRICPOLE);
		registerModel(PMCBlocks.ELECTRICPOLETOP);
		registerModel(PMCBlocks.ELECTRICCABLE);
		registerModel(PMCBlocks.RADIOTOWER);
		registerModel(PMCBlocks.RADIOTOWERTOP);
		registerModel(PMCBlocks.GUN_WORKBENCH);
		registerModel(PMCBlocks.BIG_AIRDROP);
		registerModel(PMCBlocks.COPPER_ORE);
		registerModel(PMCBlocks.LANDMINE);
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
