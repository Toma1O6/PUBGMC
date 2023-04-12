package dev.toma.pubgmc.util.helper;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import dev.toma.pubgmc.init.PMCBlocks;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class GameHelper {

    public static final Marker MARKER = MarkerManager.getMarker("GameEngine");
    public static final UUID NIL_UUID = new UUID(0L, 0L);

    public static void spawnPlayerDeathCrate(UUID gameId, EntityPlayer player) {
        World world = player.world;
        List<InventoryProvider> inventoryProviders = new ArrayList<>();
        inventoryProviders.add(InventoryProvider.inventory(0, player.inventory));
        IPlayerData data = PlayerData.get(player);
        if (data != null) {
            inventoryProviders.add(InventoryProvider.inventory(41, data.getEquipmentInventory()));
        }
        spawnDeathCrate(gameId, world, player.getPosition(), inventoryProviders);
        if (data != null) {
            data.sync();
        }
    }

    public static void spawnDeathCrate(UUID gameId, World world, BlockPos pos, List<InventoryProvider> inventories) {
        BlockPos ground = PUBGMCUtil.getEmptyGroundPositionAt(world, pos);
        if (ground == null) {
            Pubgmc.logger.warn(MARKER, "Unable to generate death crate at {} position, no valid position was found", pos);
            return;
        }
        Pubgmc.logger.debug(MARKER, "Generating death crate at {} for game {}", ground, gameId);
        world.setBlockState(ground, PMCBlocks.PLAYER_CRATE.getDefaultState(), 3);
        TileEntity tileEntity = world.getTileEntity(ground);
        if (!(tileEntity instanceof TileEntityPlayerCrate)) {
            Pubgmc.logger.error(MARKER, "Unable to fill death crate contents at {} due to invalid tile entity type: {}", ground, tileEntity);
            return;
        }
        TileEntityPlayerCrate playerCrate = (TileEntityPlayerCrate) tileEntity;
        playerCrate.assignGameId(gameId);
        for (InventoryProvider provider : inventories) {
            int index = provider.getSlotOffset();
            IInventory inventory = provider.getInventory();
            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack stack = inventory.getStackInSlot(i);
                if (!stack.isEmpty()) {
                    playerCrate.setInventorySlotContents(index + i, stack.copy());
                }
            }
            inventory.clear();
        }
        Pubgmc.logger.debug(MARKER, "Death crate generated at {}", ground);
    }

    public static boolean performActiveGameIdValidations(World world, UUID storedGameId, Runnable onInvalidGameAction) {
        UUID currentGameId = NIL_UUID; // TODO implement
        if (currentGameId.equals(storedGameId)) {
            return true;
        }
        onInvalidGameAction.run();
        return false;
    }

    public interface InventoryProvider {

        int getSlotOffset();

        IInventory getInventory();

        static InventoryProvider inventory(int indexStart, IInventory inventory) {
            return new InventoryProvider() {
                @Override
                public int getSlotOffset() {
                    return indexStart;
                }

                @Override
                public IInventory getInventory() {
                    return inventory;
                }
            };
        }
    }
}
