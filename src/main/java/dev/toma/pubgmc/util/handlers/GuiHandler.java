package dev.toma.pubgmc.util.handlers;

import dev.toma.pubgmc.client.gui.*;
import dev.toma.pubgmc.common.container.*;
import dev.toma.pubgmc.common.tileentity.*;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.PacketSyncTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_AIRDROP = 0;
    public static final int LOOT_SPAWNER = 1;
    public static final int GUI_CRATE = 2;
    public static final int GUI_GUNCRAFTINGTABLE = 4;
    public static final int GUI_BIG_AIRDROP = 5;
    public static final int GUI_PLAYER_EQUIPMENT = 6;
    public static final int GUI_LOOT_CRATE = 7;

    public static void update(World world, EntityPlayer player, int x, int y, int z) {
        if (player instanceof EntityPlayerMP) {
            BlockPos p = new BlockPos(x, y, z);
            PacketHandler.sendToClient(new PacketSyncTileEntity(world.getTileEntity(p).serializeNBT(), p), (EntityPlayerMP) player);
        }
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_AIRDROP:
                return new ContainerAirdrop(player.inventory, (TileEntityAirdrop) world.getTileEntity(new BlockPos(x, y, z)));
            case LOOT_SPAWNER:
                return new ContainerLootSpawner(player.inventory, (TileEntityLootGenerator) world.getTileEntity(new BlockPos(x, y, z)));
            case GUI_CRATE:
                return new ContainerPlayerCrate(player.inventory, (TileEntityPlayerCrate) world.getTileEntity(new BlockPos(x, y, z)));
            case GUI_GUNCRAFTINGTABLE:
                ContainerGunWorkbench c = new ContainerGunWorkbench((TileEntityGunWorkbench) world.getTileEntity(new BlockPos(x, y, z)), player.inventory);
                update(world, player, x, y, z);
                return c;
            case GUI_BIG_AIRDROP:
                return new ContainerBigAirdrop(player.inventory, (TileEntityAirdrop) world.getTileEntity(new BlockPos(x, y, z)));
            case GUI_PLAYER_EQUIPMENT:
                return new ContainerPlayerEquipment(player.inventory);
            case GUI_LOOT_CRATE:
                return new ContainerLootCrate(player.inventory, (TileEntityLootCrate) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_AIRDROP:
                return new GuiAirdrop(player.inventory, (TileEntityAirdrop) world.getTileEntity(new BlockPos(x, y, z)));
            case LOOT_SPAWNER:
                return new GuiLootSpawner(player.inventory, (TileEntityLootGenerator) world.getTileEntity(new BlockPos(x, y, z)));
            case GUI_CRATE:
                return new GuiPlayerCrate(player.inventory, (TileEntityPlayerCrate) world.getTileEntity(new BlockPos(x, y, z)));
            case GUI_GUNCRAFTINGTABLE:
                return new GuiGunWorkbench((TileEntityGunWorkbench) world.getTileEntity(new BlockPos(x, y, z)), player.inventory);
            case GUI_BIG_AIRDROP:
                return new GuiBigAirdrop(player.inventory, (TileEntityAirdrop) world.getTileEntity(new BlockPos(x, y, z)));
            case GUI_PLAYER_EQUIPMENT:
                return new GuiPlayerEquipment(new ContainerPlayerEquipment(player.inventory));
            case GUI_LOOT_CRATE:
                return new GuiLootCrate(player.inventory, (TileEntityLootCrate) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }
}
