package com.toma.pubgmc.init;

import com.toma.pubgmc.client.renderer.WeaponTEISR;
import com.toma.pubgmc.common.items.guns.GunBase;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
		registerBlockModel(PMCBlocks.ROADASPHALT);
		registerBlockModel(PMCBlocks.SCHOOLWALL);
		registerBlockModel(PMCBlocks.SCHOOLROOF);
		registerBlockModel(PMCBlocks.SCHOOLWINDOW);
		registerBlockModel(PMCBlocks.AIRDROP);
		registerBlockModel(PMCBlocks.DARKWOOD);
		registerBlockModel(PMCBlocks.LOOT_SPAWNER);
		registerBlockModel(PMCBlocks.PLAYER_CRATE);
		registerBlockModel(PMCBlocks.CHAIR);
		registerBlockModel(PMCBlocks.TABLE);
		registerBlockModel(PMCBlocks.RUINSWALL);
		registerBlockModel(PMCBlocks.BLUEGLASS);
		registerBlockModel(PMCBlocks.TARGET);
		registerBlockModel(PMCBlocks.LAMPBOTTOM);
		registerBlockModel(PMCBlocks.LAMPPOST);
		registerBlockModel(PMCBlocks.LAMPTOP);
		registerBlockModel(PMCBlocks.LIGHT);
		registerBlockModel(PMCBlocks.CRATE);
		registerBlockModel(PMCBlocks.CRATES);
		registerBlockModel(PMCBlocks.BUSH);
		registerBlockModel(PMCBlocks.WHEAT);
		registerBlockModel(PMCBlocks.PROP1);
		registerBlockModel(PMCBlocks.PROP2);
		registerBlockModel(PMCBlocks.PROP3);
		registerBlockModel(PMCBlocks.PROP4);
		registerBlockModel(PMCBlocks.PROP5);
		registerBlockModel(PMCBlocks.FENCE);
		registerBlockModel(PMCBlocks.CONCRETE);
		registerBlockModel(PMCBlocks.ELECTRICPOLE);
		registerBlockModel(PMCBlocks.ELECTRICPOLETOP);
		registerBlockModel(PMCBlocks.ELECTRICCABLE);
		registerBlockModel(PMCBlocks.RADIOTOWER);
		registerBlockModel(PMCBlocks.RADIOTOWERTOP);
		registerBlockModel(PMCBlocks.GUN_WORKBENCH);
		registerBlockModel(PMCBlocks.BIG_AIRDROP);
		registerBlockModel(PMCBlocks.COPPER_ORE);
		registerBlockModel(PMCBlocks.LANDMINE);
	}
	
	private static void registerModel(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		
		if(item instanceof GunBase)
		{
			item.setTileEntityItemStackRenderer(new WeaponTEISR());
		}
	}
	
	private static void registerBlockModel(Block block)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
}
