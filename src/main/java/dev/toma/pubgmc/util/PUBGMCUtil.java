package dev.toma.pubgmc.util;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.capability.IGameData;
import dev.toma.pubgmc.common.entity.EntityAirdrop;
import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.PacketDelayedSound;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import org.apache.commons.lang3.RandomStringUtils;

public class PUBGMCUtil {

    public static void sendSoundPacket(SoundEvent event, float volume, BlockPos pos, TargetPoint target) {
        PacketHandler.INSTANCE.sendToAllAround(new PacketDelayedSound(event, volume, pos.getX(), pos.getY(), pos.getZ()), target);
    }

    public static boolean shouldSendCommandFeedback(World world) {
        return world.getGameRules().getBoolean("sendCommandFeedback");
    }

    public static boolean isPlayerDrivingVehicle(EntityPlayer player) {
        return player.getRidingEntity() instanceof EntityVehicle;
    }

    public static boolean isPlayerDriverOfVehicle(EntityPlayer player) {
        return player.getRidingEntity() instanceof EntityVehicle && player.getRidingEntity().getPassengers().get(0) == player;
    }

    public static <T> boolean contains(T object, T[] array) {
        for(T t : array) {
            if(object == t) {
                return true;
            }
        }
        return false;
    }

    public static int findFirstNull(Object[] array) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == null) return i;
        }
        return -1;
    }

    public static void shiftElementsInArray(Object[] array) {
        for(int i = array.length - 2; i >= 0; i--) {
            array[i+1] = array[i];
        }
        array[0] = null;
    }

    public static boolean isValidNumber(String text) {
        char[] num = text.toCharArray();
        boolean valid = true;
        if (num[0] == '-' || Character.isDigit(num[0])) {
            for (int i = 0; i < num.length; i++) {
                if (i > 0) {
                    if (Character.isDigit(num[i])) {
                        continue;
                    } else valid = false;
                }
            }
        }
        return valid;
    }

    public static boolean isStringDoubleOrFloat(String text) {
        char[] c = text.toCharArray();
        boolean valid = true;
        boolean alreadyUsedDot = false;
        for (int i = 0; i < c.length; i++) {
            if (Character.isDigit(c[i]) || c[i] == '.') {
                if (alreadyUsedDot && c[i] == '.') {
                    valid = false;
                }

                if (c[i] == '.' && !alreadyUsedDot) {
                    alreadyUsedDot = true;
                }

                continue;
            } else {
                valid = false;
            }
        }

        return valid;
    }

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

    public static boolean isMapSetupProperly(IGameData data) {
        boolean properSize = data.getMapSize() > 0;
        boolean hasLocations = !data.getSpawnLocations().isEmpty();

        if (!properSize) Pubgmc.logger.error("Ivalid map size, setup your map!");
        if (!hasLocations) Pubgmc.logger.warn("No locations, add some! (Plane won't spawn)");

        return properSize;
    }

    public static float getAngleBetween2Points(Entity entityToRotate, BlockPos targetPos) {
        return (float) (MathHelper.atan2(entityToRotate.posZ - targetPos.getZ(), entityToRotate.posX - targetPos.getX()) * (180D / Math.PI)) - 90f;
    }

    public static float updateRotation(float prevRotation, float additionalRotation) {
        float f = MathHelper.wrapDegrees(additionalRotation - prevRotation);
        return f;
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

    public static String generateID(int length) {
        return RandomStringUtils.random(length, true, true);
    }

    public static void setModelPosition(ModelRenderer model, float x, float y, float z) {
        model.offsetX = x;
        model.offsetY = y;
        model.offsetZ = z;
    }

    public static void spawnAirdrop(World world, BlockPos pos, boolean bigDrop) {
        if (!world.isRemote && world.isBlockLoaded(pos)) {
            EntityAirdrop drop = new EntityAirdrop(world, pos, bigDrop);
            world.spawnEntity(drop);
        }
    }

    public static float interpolate(float prev, float current, float partial) {
        return prev + partial * (current - prev);
    }

    public static double interpolate(double prev, double current, double partial) {
        return prev + partial * (current - prev);
    }
}
