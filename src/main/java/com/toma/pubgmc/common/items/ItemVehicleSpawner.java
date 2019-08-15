package com.toma.pubgmc.common.items;

import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.common.entity.vehicles.EntityVehicleDacia;
import com.toma.pubgmc.common.entity.vehicles.EntityVehicleUAZ;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemVehicleSpawner extends PMCItem {
    private Vehicles car;

    public ItemVehicleSpawner(String name, Vehicles vehicle) {
        super(name);
        this.car = vehicle;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);

        if (!worldIn.isRemote && ConfigPMC.common.worldSettings.allowVehicleSpawning) {
            car.spawnEntity(worldIn, pos);

            if (!player.capabilities.isCreativeMode) {
                stack.shrink(1);
            }
        }
        return EnumActionResult.PASS;
    }

    private String formattedInfo(String s1, String value) {
        return TextFormatting.GRAY + s1 + ": " + TextFormatting.YELLOW + value;
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
