package dev.toma.pubgmc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collection;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class DevUtil {

    public static final DecimalFormat THREE_DECIMALS = new DecimalFormat("#.###");
    public static final DecimalFormat TWO_DECIMALS = new DecimalFormat("#.##");
    public static final DecimalFormat SINGLE_DECIMAL = new DecimalFormat("#.#");

    public static String formatToThreeDecimals(Number number) {
        return THREE_DECIMALS.format(number.doubleValue());
    }

    public static String formatToTwoDecimals(Number number) {
        return TWO_DECIMALS.format(number.doubleValue());
    }

    public static String formatToSingleDecimal(Number number) {
        return SINGLE_DECIMAL.format(number.doubleValue());
    }

    public static boolean isDev() {
        return Pubgmc.isDevEnvironment;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] expandArray(T[] array, int addedLength) {
        if (addedLength <= 0)
            throw new IllegalArgumentException("Number must be bigger than 0");
        T[] expanded = (T[]) new Object[array.length + addedLength];
        System.arraycopy(array, 0, expanded, 0, array.length);
        return expanded;
    }

    public static <T> T getPrevious(List<T> list, int index, T fallback) {
        int prev = index - 1;
        if (prev < 0)
            return fallback;
        return list.get(prev);
    }

    public static <T> T make(T t, Consumer<T> consumer) {
        if (t != null)
            consumer.accept(t);
        return t;
    }

    public static <E> boolean contains(E[] group, E element) {
        for (E e : group) {
            if (element == e) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean contains(T t, Collection<T> collection) {
        return contains(t, collection, (t1, t2) -> t1 == t2);
    }

    public static <T, U> boolean contains(T t, Collection<U> collection, BiPredicate<T, U> test) {
        for (U u : collection) {
            if (test.test(t, u))
                return true;
        }
        return false;
    }

    public static int wrap(int i, int min, int max) {
        return Math.min(max, Math.max(min, i));
    }

    public static float wrap(float f, float min, float max) {
        return Math.min(max, Math.max(min, f));
    }

    public static double wrap(double d, double min, double max) {
        return Math.min(max, Math.max(min, d));
    }

    public static float lerp(float actual, float previous, float partial) {
        return previous + (actual - previous) * partial;
    }

    public static int getItemCount(Item item, IInventory inventory) {
        int total = 0;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.getItem() == item) {
                total += stack.getCount();
            }
        }
        return total;
    }

    public static boolean hasItem(Item item, IInventory inventory) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack.getItem() == item && itemStack.getCount() > 0) {
                return true;
            }
        }
        return false;
    }


    @Nullable
    public static ItemStack getFirstItem(Item item, IInventory inventory) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack.getItem() == item && itemStack.getCount() > 0) {
                return itemStack;
            }
        }
        return null;
    }

    public static boolean isNearGround(EntityPlayer player, int height) {
        World world = player.world;
        Vec3d startPos = new Vec3d(player.posX + 0.5, player.posY + 0.1, player.posZ + 0.5);
        Vec3d endPos = new Vec3d(player.posX + 0.5, player.posY - height, player.posZ + 0.5);
        RayTraceResult rayTraceResult = world.rayTraceBlocks(startPos, endPos, false, true, false);
        return rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK;
    }

    public static void calculateRotationToEntity(Entity startEntity, Entity endEntity, Entity targetEntity, EnumFacing targetFront) {
        double deltaX = endEntity.posX - startEntity.posX;
        double deltaZ = endEntity.posZ - startEntity.posZ;
        double distanceXZ = MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        float yaw = (float) (MathHelper.atan2(deltaZ, deltaX) * (180D / Math.PI)) - 90.0F;

        double deltaY = endEntity.posY + endEntity.getEyeHeight() / 2.0F - (startEntity.posY + startEntity.height / 2.0F);
        float pitch = (float) (-(MathHelper.atan2(deltaY, distanceXZ) * (180D / Math.PI)));

        adjustRotation(targetEntity, yaw, pitch, targetFront);
    }

    public static void calculateRotationToSelf(Entity startEntity, Entity targetEntity, EnumFacing targetFront) {
        calculateRotationToEntity(startEntity, targetEntity, targetEntity, targetFront);
    }

    public static void calculateRotationToBlockFace(BlockPos blockPos, Entity endEntity, Entity targetEntity, EnumFacing targetFront) {
        double deltaX = endEntity.posX - blockPos.getX() - 0.5D;
        double deltaY = endEntity.posY + endEntity.getEyeHeight() / 2.0F - (blockPos.getY() + 0.5D);
        double deltaZ = endEntity.posZ - blockPos.getZ() - 0.5D;

        EnumFacing facingX = deltaX > 0 ? EnumFacing.EAST : EnumFacing.WEST;
        EnumFacing facingY = deltaY > 0 ? EnumFacing.UP : EnumFacing.DOWN;
        EnumFacing facingZ = deltaZ > 0 ? EnumFacing.SOUTH : EnumFacing.NORTH;

        double absX = Math.abs(deltaX);
        double absY = Math.abs(deltaY);
        double absZ = Math.abs(deltaZ);

        float yaw = targetEntity.rotationYaw;
        float pitch = targetEntity.rotationPitch;

        if (absY >= absX && absY >= absZ) {
            pitch = facingY == EnumFacing.UP ? -90F : 90F;
        } else if (absZ >= absX && absZ >= absY) {
            yaw = facingZ == EnumFacing.SOUTH ? 0F : 180F;
            pitch = 0F;
        } else {
            yaw = facingX == EnumFacing.EAST ? -90F : 90F;
            pitch = 0F;
        }

        adjustRotation(targetEntity, yaw, pitch, targetFront);
    }

    public static void calculateRotationToBlockFace(BlockPos blockPos, Entity targetEntity, EnumFacing targetFront) {
        calculateRotationToBlockFace(blockPos, targetEntity, targetEntity, targetFront);
    }

    private static void adjustRotation(Entity targetEntity, float yaw, float pitch, EnumFacing targetFront) {
        switch (targetFront) {
            case NORTH:
                yaw += 180F;
                break;
            case EAST:
                yaw += 90F;
                break;
            case WEST:
                yaw -= 90F;
                break;
            case DOWN:
            case UP:
            case SOUTH:
                break;
        }

        targetEntity.rotationPitch = pitch;
        targetEntity.rotationYaw = yaw;
    }

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');

        THREE_DECIMALS.setDecimalFormatSymbols(symbols);
        TWO_DECIMALS.setDecimalFormatSymbols(symbols);
        SINGLE_DECIMAL.setDecimalFormatSymbols(symbols);
    }
}
