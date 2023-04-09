package dev.toma.pubgmc.common.entity;

import dev.toma.pubgmc.common.capability.game.IGameData;
import dev.toma.pubgmc.common.tileentity.TileEntityAirdrop;
import dev.toma.pubgmc.init.PMCBlocks;
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
    private String hash;

    public EntityAirdrop(World world) {
        super(world);
        this.setSize(1f, 1f);
        this.isBigDrop = false;
        IGameData gameData = world.getCapability(IGameData.GameDataProvider.GAMEDATA, null);
        this.hash = gameData == null ? "empty" : gameData.getGameID();
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
        if(!world.isRemote && ticksExisted % 20 == 0) {
            if(!world.getCapability(IGameData.GameDataProvider.GAMEDATA, null).getGameID().equals(hash)) {
                this.setDead();
            }
        }
    }

    public void onEntityLanded() {
        IBlockState state = isBigDrop ? PMCBlocks.BIG_AIRDROP.getDefaultState() : PMCBlocks.AIRDROP.getDefaultState();
        world.setBlockState(this.getPosition(), state, 3);

        if (world.getTileEntity(this.getPosition()) instanceof TileEntityAirdrop) {
            ((TileEntityAirdrop) world.getTileEntity(getPosition())).onLanded();
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
        hash = compound.hasKey("hash") ? compound.getString("hash") : "empty";
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("dropType", isBigDrop);
        compound.setString("hash", this.hash);
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
