package dev.toma.pubgmc.common.entity;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.tileentity.TileEntityAirdrop;
import dev.toma.pubgmc.init.PMCBlocks;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.TileEntityUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityAirdrop extends Entity implements IEntityAdditionalSpawnData {

    private boolean isBigDrop;

    public EntityAirdrop(World world) {
        super(world);
        this.setSize(1f, 1f);
        this.isBigDrop = false;
    }

    public EntityAirdrop(World world, BlockPos pos, boolean type) {
        this(world);
        this.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        this.isBigDrop = type;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.handleMotion(0.15);
        this.move(MoverType.SELF, motionX, motionY, motionZ);
    }

    public void onEntityLanded() {
        IBlockState state = isBigDrop ? PMCBlocks.BIG_AIRDROP.getDefaultState() : PMCBlocks.AIRDROP.getDefaultState();
        BlockPos landingPosition = PUBGMCUtil.getEmptyGroundPositionAt(world, this.getPosition());
        if (landingPosition == null) {
            Pubgmc.logger.warn("Failed to find valid ground position for airdrop " + this);
            return;
        }
        world.setBlockState(landingPosition, state, 3);

        TileEntity tileEntity = world.getTileEntity(landingPosition);
        if (tileEntity instanceof TileEntityAirdrop) {
            ((TileEntityAirdrop) tileEntity).onLanded();
            TileEntityUtil.syncToClient(tileEntity);
        }
    }

    public boolean getType() {
        return isBigDrop;
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return true;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        isBigDrop = compound.getBoolean("dropType");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("dropType", isBigDrop);
    }

    @Override
    public void readSpawnData(ByteBuf buf) {
        isBigDrop = buf.readBoolean();
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeBoolean(isBigDrop);
    }

    @Override
    protected void entityInit() {
    }

    private void handleMotion(double motion) {
        if (!onGround) {
            motionY = -motion;
        } else {
            if (!world.isRemote)
                this.onEntityLanded();

            this.setDead();
        }
    }
}
