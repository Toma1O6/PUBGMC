package com.toma.pubgmc.util.handlers;

import com.toma.pubgmc.client.gui.GuiAirdrop;
import com.toma.pubgmc.client.gui.GuiBigAirdrop;
import com.toma.pubgmc.client.gui.GuiGunWorkbench;
import com.toma.pubgmc.client.gui.GuiLootSpawner;
import com.toma.pubgmc.client.gui.GuiPlayerCrate;
import com.toma.pubgmc.common.container.ContainerAirdrop;
import com.toma.pubgmc.common.container.ContainerBigAirdrop;
import com.toma.pubgmc.common.container.ContainerGunWorkbench;
import com.toma.pubgmc.common.container.ContainerLootSpawner;
import com.toma.pubgmc.common.container.ContainerPlayerCrate;
import com.toma.pubgmc.common.items.guns.attachments.ContainerAttachments;
import com.toma.pubgmc.common.items.guns.attachments.GuiAttachments;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.sp.PacketSyncTileEntity;
import com.toma.pubgmc.common.tileentity.TileEntityAirdrop;
import com.toma.pubgmc.common.tileentity.TileEntityBigAirdrop;
import com.toma.pubgmc.common.tileentity.TileEntityGunWorkbench;
import com.toma.pubgmc.common.tileentity.TileEntityLootSpawner;
import com.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	private static int guiID = 0;
	public static final int GUI_AIRDROP = 0;
	public static final int PLAYER_INV = getNextGuiID();
	public static final int LOOT_SPAWNER = getNextGuiID();
	public static final int GUI_CRATE = getNextGuiID();
	public static final int GUI_ATTACHMENTS = getNextGuiID();
	public static final int GUI_GUNCRAFTINGTABLE = getNextGuiID();
	public static final int GUI_BIG_AIRDROP = getNextGuiID();
	
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == GUI_AIRDROP) return new ContainerAirdrop(player.inventory, (TileEntityAirdrop)world.getTileEntity(new BlockPos(x,y,z)));
		
		if(ID == LOOT_SPAWNER) return new ContainerLootSpawner(player.inventory, (TileEntityLootSpawner)world.getTileEntity(new BlockPos(x,y,z)));
		
		if(ID == GUI_CRATE) return new ContainerPlayerCrate(player.inventory, (TileEntityPlayerCrate)world.getTileEntity(new BlockPos(x, y, z)));
		
		if(ID == GUI_ATTACHMENTS) return new ContainerAttachments(player.inventory, player);
		
		if(ID == GUI_GUNCRAFTINGTABLE) { 
			ContainerGunWorkbench c = new ContainerGunWorkbench((TileEntityGunWorkbench)world.getTileEntity(new BlockPos(x, y, z)), player.inventory);
			update(world, player, x, y, z);
			return c;
		}
		
		if(ID == GUI_BIG_AIRDROP) return new ContainerBigAirdrop(player.inventory, (TileEntityBigAirdrop)world.getTileEntity(new BlockPos(x, y, z)));
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == GUI_AIRDROP) return new GuiAirdrop(player.inventory, (TileEntityAirdrop)world.getTileEntity(new BlockPos(x,y,z)));
		
		if(ID == LOOT_SPAWNER) return new GuiLootSpawner(player.inventory, (TileEntityLootSpawner)world.getTileEntity(new BlockPos(x,y,z)));
		
		if(ID == GUI_CRATE) return new GuiPlayerCrate(player.inventory, (TileEntityPlayerCrate)world.getTileEntity(new BlockPos(x, y, z)));
		
		if(ID == GUI_ATTACHMENTS) return new GuiAttachments(player.inventory, player);
		
		if(ID == GUI_GUNCRAFTINGTABLE) return new GuiGunWorkbench((TileEntityGunWorkbench)world.getTileEntity(new BlockPos(x, y, z)), player.inventory);
		
		if(ID == GUI_BIG_AIRDROP) return new GuiBigAirdrop(player.inventory, (TileEntityBigAirdrop)world.getTileEntity(new BlockPos(x, y, z)));
		
		return null;
	}
	
	private static int getNextGuiID()
	{
		return guiID += 1;
	}
	
	public static void update(World world, EntityPlayer player, int x, int y, int z) {
		if(player instanceof EntityPlayerMP) {
			BlockPos p = new BlockPos(x,y,z);
			PacketHandler.sendToClient(new PacketSyncTileEntity(world.getTileEntity(p).serializeNBT(), p), (EntityPlayerMP)player);
		}
	}
}
