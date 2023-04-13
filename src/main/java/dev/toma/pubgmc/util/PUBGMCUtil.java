package dev.toma.pubgmc.util;

import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.capability.player.SpecialEquipmentSlot;
import dev.toma.pubgmc.common.entity.EntityAirdrop;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.commons.lang3.RandomStringUtils;

import javax.annotation.Nullable;
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

    public static float getAngleBetween2Points(Entity entityToRotate, BlockPos targetPos) {
        return (float) (MathHelper.atan2(entityToRotate.posZ - targetPos.getZ(), entityToRotate.posX - targetPos.getX()) * (180D / Math.PI)) - 90f;
    }

    public static float updateRotation(float prevRotation, float additionalRotation) {
        return MathHelper.wrapDegrees(additionalRotation - prevRotation);
    }

    public static void updateEntityRotation(Entity entity, BlockPos targetPos) {
        entity.rotationYaw = updateRotation(entity.rotationYaw, getAngleBetween2Points(entity, targetPos));
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

    public static boolean tryQuickEquip(EntityPlayer player, SpecialEquipmentSlot slot, ItemStack stack) {
        IPlayerData data = PlayerData.get(player);
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
}
