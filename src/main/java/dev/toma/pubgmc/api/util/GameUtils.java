package dev.toma.pubgmc.api.util;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.Lobby;
import dev.toma.pubgmc.api.games.Game;
import dev.toma.pubgmc.api.interfaces.BotSpawner;
import dev.toma.pubgmc.api.interfaces.IGameTileEntity;
import dev.toma.pubgmc.common.capability.game.IGameData;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.capability.player.SpecialEquipmentSlot;
import dev.toma.pubgmc.common.entity.EntityPlane;
import dev.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import dev.toma.pubgmc.common.entity.bot.ai.EntityAIGunAttack;
import dev.toma.pubgmc.common.entity.bot.ai.EntityAIMoveIntoZone;
import dev.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.init.PMCBlocks;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.math.ZonePos;
import dev.toma.pubgmc.world.BlueZone;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public final class GameUtils {

    public static final DecimalFormat FORMATTER = new DecimalFormat("##00");

    public static void updateLoadedTileEntities(World world, Game game, boolean forcedUpdate) {
        IGameData gameData = game.getGameData(world);
        if (!game.shouldUpdateTileEntities()) {
            return;
        }
        for (TileEntity tileEntity : world.loadedTileEntityList) {
            if (tileEntity instanceof IGameTileEntity) {
                IGameTileEntity te = (IGameTileEntity) tileEntity;
                if (forcedUpdate) {
                    te.setGameHash(gameData.getGameID());
                    try {
                        te.onLoaded();
                    } catch (Exception e) {
                        Pubgmc.logger.fatal("Fatal error occurred when updating {}, aborting update!", tileEntity);
                    }
                    continue;
                }
                if (te.getGameHash().equals(gameData.getGameID())) {
                    continue;
                }
                te.setGameHash(gameData.getGameID());
                try {
                    te.onLoaded();
                } catch (Exception e) {
                    Pubgmc.logger.fatal("Fatal error occurred when updating {}, aborting update!", tileEntity);
                }
            }
        }
    }

    public static void createAndFillPlanes(World world) {
        IGameData gameData = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        Game game = gameData.getCurrentGame();
        EntityPlane plane = new EntityPlane(world, gameData);
        int joined = 0;
        Iterator<EntityPlayer> iterator = game.getOnlinePlayers(world).iterator();
        while (iterator.hasNext()) {
            EntityPlayer player = iterator.next();
            player.inventory.clear();
            player.addItemStackToInventory(new ItemStack(PMCItems.PARACHUTE));
            boolean flag = iterator.hasNext();
            ++joined;
            plane.pendingPlayers.add(player);
            if (!flag) {
                BlockPos start = plane.getStartingPosition();
                plane.pendingPlayers.forEach(p -> game.teleportEntityTo(p, start.getX(), ConfigPMC.common.world.planeHeight.get(), start.getZ()));
                if (!world.isRemote) {
                    world.spawnEntity(plane);
                }
                for (EntityPlayer p : plane.pendingPlayers) {
                    if (p != null) {
                        p.startRiding(plane);
                    }
                }
                plane.pendingPlayers = null;
            } else if (joined >= 31) {
                BlockPos start = plane.getStartingPosition();
                plane.pendingPlayers.forEach(p -> game.teleportEntityTo(p, start.getX(), ConfigPMC.common.world.planeHeight.get(), start.getZ()));
                if (!world.isRemote) {
                    world.spawnEntity(plane);
                }
                for (EntityPlayer p : plane.pendingPlayers) {
                    if (p != null) {
                        p.startRiding(plane);
                    }
                }
                plane.pendingPlayers = null;
                plane = new EntityPlane(world, gameData);
                joined = 0;
            }
        }
    }

    public static void markBlockForRemoval(World world, BlockPos pos, Block block) {
        world.scheduleUpdate(pos, block, 3);
    }

    public static void teleportPlayersIntoLobby(World world, Game game) {
        Lobby lobby = game.getGameData(world).getLobby();
        int x = lobby.center.getX();
        int y = lobby.center.getY() + 1;
        int z = lobby.center.getZ();
        game.getOnlinePlayers(world).forEach(player -> player.setPositionAndUpdate(x, y, z));
    }

    public static String getFormattedTime(int amount, final boolean isInTicks) {
        if (isInTicks) {
            amount = amount / 20;
        }
        int left = amount;
        int hours = left / 3600;
        left = left % 3600;
        int minutes = left / 60;
        int seconds = left % 60;
        StringBuilder builder = new StringBuilder(8);
        if (hours > 0) builder.append(FORMATTER.format(hours)).append(":");
        builder.append(FORMATTER.format(minutes)).append(":");
        builder.append(FORMATTER.format(seconds));
        return builder.toString();
    }

    public static <T extends Game> BotSpawner<T> getDefaultSpawner() {
        return (world, game) -> {
            BlueZone zone = game.zone;
            if (zone == null) {
                return null;
            }
            ZonePos min = zone.currentBounds.min();
            ZonePos max = zone.currentBounds.max();
            int maxDist = (int) Math.abs(max.x - min.x);
            int x = (int) min.x + world.rand.nextInt(maxDist);
            int z = (int) min.z + world.rand.nextInt(maxDist);
            int y = world.getHeight(x, z);
            BlockPos pos = new BlockPos(x, y, z);
            if (!world.isBlockLoaded(pos)) {
                List<EntityPlayer> playerList = game.getOnlinePlayers(world);
                EntityPlayer player = playerList.get(world.rand.nextInt(playerList.size()));
                x = (int) player.posX + world.rand.nextInt(64) - world.rand.nextInt(64);
                z = (int) player.posZ + world.rand.nextInt(64) - world.rand.nextInt(64);
                y = world.getHeight(x, z);
                pos = new BlockPos(x, y, z);
                if (!world.isBlockLoaded(pos)) {
                    pos = null;
                }
            }
            return pos;
        };
    }

    public static void createDeathCrate(EntityPlayer player) {
        BlockPos pos = getEmptyGroundPositionAt(player.world, player.getPosition());
        if(pos == null) {
            Pubgmc.logger.warn("Failed to find position for player death crate: " + player);
            return;
        }
        player.world.setBlockState(pos, PMCBlocks.PLAYER_CRATE.getDefaultState());
        TileEntityPlayerCrate te = (TileEntityPlayerCrate) player.world.getTileEntity(pos);
        for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if(!stack.isEmpty()) {
                te.setInventorySlotContents(i, stack.copy());
            }
        }
        player.inventory.clear();
        IPlayerData data = PlayerData.get(player);
        for (SpecialEquipmentSlot slot : SpecialEquipmentSlot.values()) {
            ItemStack stack = data.getSpecialItemFromSlot(slot);
            if (!stack.isEmpty()) {
                te.setInventorySlotContents(42 + slot.ordinal(), stack);
            }
        }
        data.getEquipmentInventory().clear();
        data.getBoostStats().reset();
    }

    public static void createDeathCrate(EntityAIPlayer bot) {
        BlockPos pos = getEmptyGroundPositionAt(bot.world, bot.getPosition());
        if(pos == null) {
            Pubgmc.logger.warn("Failed to find position for death crate for entity " + bot);
            return;
        }
        bot.world.setBlockState(pos, PMCBlocks.PLAYER_CRATE.getDefaultState());
        TileEntityPlayerCrate playerCrate = (TileEntityPlayerCrate) bot.world.getTileEntity(pos);
        for(EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            ItemStack stack = bot.getItemStackFromSlot(slot);
            if(!stack.isEmpty()) {
                playerCrate.setInventorySlotContents(slot.ordinal(), stack.copy());
            }
        }
        for(int i = 0; i < bot.inventory.size(); i++) {
            ItemStack stack = bot.inventory.get(i);
            if(!stack.isEmpty()) {
                playerCrate.setInventorySlotContents(i + 6, stack.copy());
            }
        }
    }

    @Nullable
    public static BlockPos getEmptyGroundPositionAt(World world, BlockPos pos) {
        BlockPos.MutableBlockPos possiblePosition = new BlockPos.MutableBlockPos(pos);
        if (world.isAirBlock(possiblePosition)) {
            return findGround(world, possiblePosition);
        }
        int y = possiblePosition.getY() - 1;
        while (y < 255) {
            possiblePosition.setY(y);
            if (world.isAirBlock(possiblePosition)) {
                return findGround(world, possiblePosition);
            }
            for (EnumFacing facing : EnumFacing.HORIZONTALS) {
                BlockPos withOffset = possiblePosition.offset(facing);
                if (world.isAirBlock(withOffset)) {
                    return findGround(world, withOffset);
                }
            }
            ++y;
        }
        return null;
    }

    public static BlockPos findGround(World world, BlockPos pos) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(pos);
        while (world.isAirBlock(mutableBlockPos.down()) && mutableBlockPos.getY() > 0) {
            mutableBlockPos.setY(mutableBlockPos.getY() - 1);
        }
        return mutableBlockPos.toImmutable();
    }

    public static void addBaseTasks(EntityAITasks n, EntityAITasks t, EntityAIPlayer b) {
        n.addTask(0, new EntityAISwimming(b));
        n.addTask(2, new EntityAIGunAttack(b));
        n.addTask(4, new EntityAIMoveIntoZone(b));
        n.addTask(6, new EntityAIWanderAvoidWater(b, 1.0D, 0.0001F));
        n.addTask(8, new EntityAIWatchClosest(b, EntityPlayer.class, 8.0F));
        n.addTask(8, new EntityAILookIdle(b));
        t.addTask(2, new EntityAINearestAttackableTarget(b, EntityPlayer.class, 10, true, false, EntitySelectors.IS_ALIVE));
        t.addTask(2, new EntityAINearestAttackableTarget(b, EntityAIPlayer.class, true));
    }
}
