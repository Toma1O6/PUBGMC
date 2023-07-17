package dev.toma.pubgmc.util;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.api.capability.SpecialEquipmentSlot;
import dev.toma.pubgmc.common.entity.EntityAirdrop;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class PUBGMCUtil {

    /**
     * Position calculated between X and Z coordinate of given positions
     **/
    public static double getDistanceToBlockPos(BlockPos pos1, BlockPos pos2) {
        return Math.sqrt(sqr(Math.abs(pos1.getX() - pos2.getX())) + sqr(Math.abs(pos1.getZ() - pos2.getZ())));
    }

    /**
     * Position calculated between [xyz] of pos1 and pos2
     **/
    public static double getDistanceToBlockPos3D(BlockPos pos1, BlockPos pos2) {
        return Math.sqrt(sqr(pos1.getX() - pos2.getX()) + sqr(pos1.getY() - pos2.getY()) + sqr(pos1.getZ() - pos2.getZ()));
    }

    public static double getDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
        double x = x2 - x1;
        double y = y2 - y1;
        double z = z2 - z1;
        return Math.sqrt(x * x + y * y + z * z);
    }

    public static float getAngleBetween2Points(double x1, double z1, double x2, double z2) {
        return (float) (MathHelper.atan2(z2 - z1, x2 - x1) * (180D / Math.PI)) - 90.0F;
    }

    public static double sqr(double num) {
        return num * num;
    }

    public static Vec3d getPositionVec(Entity entity) {
        return new Vec3d(entity.posX, entity.posY, entity.posZ);
    }

    public static Vec3d getMotionVec(Entity entity) {
        Vec3d base = getPositionVec(entity);
        return new Vec3d(base.x + entity.motionX, base.y + entity.motionY, base.z + entity.motionZ);
    }

    public static void spawnAirdrop(World world, BlockPos pos, boolean bigDrop) {
        if (!world.isRemote && world.isBlockLoaded(pos)) {
            Pubgmc.logger.debug("Generating aidrop at {}. Large: {}", pos, bigDrop);
            EntityAirdrop drop = new EntityAirdrop(world, pos, bigDrop);
            drop.assignGameId(GameHelper.getGameUUID(world));
            world.spawnEntity(drop);
        }
    }

    public static float interpolate(float prev, float current, float partial) {
        return prev + partial * (current - prev);
    }

    public static double interpolate(double prev, double current, double partial) {
        return prev + partial * (current - prev);
    }

    @Nullable
    public static <T> T randomListElement(List<T> list, Random random) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(random.nextInt(list.size()));
    }

    @Nullable
    public static <T> T randomArrayElement(T[] array, Random random) {
        if (array.length == 0) {
            return null;
        }
        return array[random.nextInt(array.length)];
    }

    public static boolean tryQuickEquip(EntityPlayer player, SpecialEquipmentSlot slot, ItemStack stack) {
        IPlayerData data = PlayerDataProvider.get(player);
        if (data == null)
            return false;
        ItemStack oldStack = data.getSpecialItemFromSlot(slot);
        if (!oldStack.isEmpty())
            return false;
        ItemStack insert = stack.copy();
        insert.setCount(1);
        data.setSpecialItemToSlot(slot, insert);
        data.sync();
        player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1.0F, 1.0F);
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        return true;
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

    public static String formatTime(int ticks) {
        int seconds = ticks / 20;
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%d:%02d", minutes, seconds);
        }
    }

    public static IInventory asInventory(EntityLiving living) {
        IInventory inventory = new InventoryBasic(living.getName(), false, EntityEquipmentSlot.values().length);
        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            ItemStack stack = living.getItemStackFromSlot(slot);
            if (!stack.isEmpty()) {
                inventory.setInventorySlotContents(slot.getSlotIndex(), stack.copy());
            }
        }
        return inventory;
    }

    public static void clearEntityInventory(EntityLiving living) {
        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            living.setItemStackToSlot(slot, ItemStack.EMPTY);
        }
    }

    public static BlockPos getClosestPosition(Collection<BlockPos> positions, BlockPos pos) {
        BlockPos selected = null;
        double distance = Double.MAX_VALUE;
        for (BlockPos position : positions) {
            double d = pos.distanceSq(position);
            if (d < distance) {
                distance = d;
                selected = position;
            }
        }
        return selected;
    }
}
