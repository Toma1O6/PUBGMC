package dev.toma.pubgmc.common.tileentity;

import dev.toma.pubgmc.event.LandmineExplodeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

public class TileEntityLandMine extends TileEntity implements ITickable {
    @Override
    public void update() {
        World world = this.getWorld();
        List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos.getX() - 2, pos.getY() - 1, pos.getZ() - 2, pos.getX() + 2, pos.getY() + 3, pos.getZ() + 2));
        if (!entities.isEmpty()) {
            Event event = world.isRemote ? new LandmineExplodeEvent(Side.CLIENT, this.getPos(), this.getWorld(), entities) : new LandmineExplodeEvent(Side.SERVER, this.getPos(), this.getWorld(), entities);
            MinecraftForge.EVENT_BUS.post(event);
            if (!world.isRemote) explode(world, pos);
        }
    }

    public void explode(World world, BlockPos pos) {
        world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 7f, false);
        world.setBlockToAir(pos);
    }
}
