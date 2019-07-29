package com.toma.pubgmc.common.entity;

import com.toma.pubgmc.ConfigPMC;
import com.toma.pubgmc.common.tileentity.IAirdropTileEntity;
import com.toma.pubgmc.init.PMCRegistry.PMCBlocks;
import com.toma.pubgmc.util.PUBGMCUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
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
        super(world);
        this.setSize(1f, 1f);
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
        world.setBlockState(this.getPosition(), state, 3);

        if (world.getTileEntity(this.getPosition()) instanceof IAirdropTileEntity && ConfigPMC.common.worldSettings.airdropLootGen > 0) {
            ((IAirdropTileEntity) world.getTileEntity(getPosition())).generateLoot();
        }
    }

    public boolean getType() {
        return isBigDrop;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        PUBGMCUtil.readBasicEntityNBT(compound, this);
        isBigDrop = compound.getBoolean("dropType");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        PUBGMCUtil.writeBasicEntityNBT(compound, this);
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
