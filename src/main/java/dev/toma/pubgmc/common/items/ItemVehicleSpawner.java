package dev.toma.pubgmc.common.items;

import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehicleDacia;
import dev.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

// TODO registry factories for new vehicle entities so that it can be used in generators properly
public class ItemVehicleSpawner extends PMCItem {

    private final Vehicles car;

    public ItemVehicleSpawner(String name, Vehicles vehicle) {
        super(name);
        this.car = vehicle;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);

        if (!worldIn.isRemote) {
            car.spawnEntity(worldIn, pos);

            if (!player.capabilities.isCreativeMode) {
                stack.shrink(1);
            }
        }
        return EnumActionResult.PASS;
    }

    public enum Vehicles {

        UAZ,
        DACIA;

        public void spawnEntity(World world, BlockPos pos) {
            EntityVehicle vehicle = null;
            switch (this) {
                case UAZ:
                    vehicle = new EntityVehicleUAZ(world, pos.getX(), pos.getY() + 1, pos.getZ());
                    break;
                case DACIA:
                    vehicle = new EntityVehicleDacia(world, pos.getX(), pos.getY() + 1, pos.getZ());
                    break;
                default:
                    break;
            }

            if (!world.isRemote) {
                if (vehicle == null) {
                    throw new IllegalArgumentException("Fatal error occured while spawning vehicle!");
                }

                world.spawnEntity(vehicle);
            }
        }
    }
}
