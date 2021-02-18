package dev.toma.pubgmc.api.util;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.games.Game;
import dev.toma.pubgmc.api.Lobby;
import dev.toma.pubgmc.api.interfaces.BotSpawner;
import dev.toma.pubgmc.api.interfaces.IGameTileEntity;
import dev.toma.pubgmc.common.capability.IGameData;
import dev.toma.pubgmc.common.capability.IPlayerData;
import dev.toma.pubgmc.common.entity.EntityPlane;
import dev.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import dev.toma.pubgmc.common.entity.bot.ai.EntityAIGunAttack;
import dev.toma.pubgmc.common.entity.bot.ai.EntityAIMoveIntoZone;
import dev.toma.pubgmc.common.tileentity.TileEntityPlayerCrate;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.init.PMCRegistry;
import dev.toma.pubgmc.util.math.ZonePos;
import dev.toma.pubgmc.world.BlueZone;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
            player.addItemStackToInventory(new ItemStack(PMCRegistry.PMCItems.PARACHUTE));
            boolean flag = iterator.hasNext();
            ++joined;
            plane.pendingPlayers.add(player);
            if (!flag) {
                BlockPos start = plane.getStartingPosition();
                plane.pendingPlayers.forEach(p -> game.teleportEntityTo(p, start.getX(), ConfigPMC.common.world.planeHeight, start.getZ()));
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
                plane.pendingPlayers.forEach(p -> game.teleportEntityTo(p, start.getX(), ConfigPMC.common.world.planeHeight, start.getZ()));
                if (!world.isRemote) {
                    world.spawnEntity(plane);
                }
                for (EntityPlayer p : plane.pendingPlayers) {
                    if (p != null) {
                        p.startRiding(plane);
                    }
                }
                plane.pendingPlayers = null;
                if (flag) {
                    plane = new EntityPlane(world, gameData);
                    joined = 0;
                }
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
        BlockPos pos = getPosForCrate(player);
        if(pos == null) return;
        player.world.setBlockState(pos, PMCRegistry.PMCBlocks.PLAYER_CRATE.getDefaultState());
        TileEntityPlayerCrate te = (TileEntityPlayerCrate) player.world.getTileEntity(pos);
        for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if(!stack.isEmpty()) {
                te.setInventorySlotContents(i, stack.copy());
            }
        }
        IPlayerData data = IPlayerData.PlayerData.get(player);
        // TODO add backpack and night vision
    }

    public static void createDeathCrate(EntityAIPlayer bot) {
        BlockPos pos = getPosForCrate(bot);
        if(pos == null) return;
        bot.world.setBlockState(pos, PMCRegistry.PMCBlocks.PLAYER_CRATE.getDefaultState());
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

    public static BlockPos getPosForCrate(EntityLivingBase entity) {
        World world = entity.getEntityWorld();
        if(world.isAirBlock(entity.getPosition())) {
            return world.getHeight(entity.getPosition());
        }
        for(int y = 0; y < 2; y++) {
            for(int x = -1; x < 2; x++) {
                for(int z = -1; z < 2; z++) {
                    BlockPos pos = new BlockPos(entity.posX + x, entity.posY + y, entity.posZ + z);
                    if(world.isAirBlock(pos)) {
                        return world.getHeight(pos);
                    }
                }
            }
        }
        return null;
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
