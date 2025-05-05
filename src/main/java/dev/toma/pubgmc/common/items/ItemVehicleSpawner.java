package dev.toma.pubgmc.common.items;

import dev.toma.pubgmc.common.entity.vehicles.EntityDriveable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class ItemVehicleSpawner<V extends EntityDriveable> extends PMCItem {

    private final VehicleFactory<V> factory;
    private final Consumer<V> configuration;

    public ItemVehicleSpawner(String name, VehicleFactory<V> factory) {
        this(name, factory, vehicle -> {});
    }

    public ItemVehicleSpawner(String name, VehicleFactory<V> factory, Consumer<V> configuration) {
        super(name);
        this.factory = factory;
        this.configuration = configuration;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);

        if (!worldIn.isRemote) {
            V vehicle = this.factory.create(worldIn);
            vehicle.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            vehicle.rotationYaw = MathHelper.wrapDegrees(player.rotationYaw - 90.0F);
            vehicle.prevRotationYaw = vehicle.rotationYaw;
            this.configuration.accept(vehicle);
            worldIn.spawnEntity(vehicle);

            if (!player.capabilities.isCreativeMode) {
                stack.shrink(1);
            }
        }
        return EnumActionResult.PASS;
    }

    @FunctionalInterface
    public interface VehicleFactory<V extends EntityDriveable> {
        V create(World world);
    }
}