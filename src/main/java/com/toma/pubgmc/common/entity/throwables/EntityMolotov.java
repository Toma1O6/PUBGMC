package com.toma.pubgmc.common.entity.throwables;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityMolotov extends EntityThrowableExplodeable {

    public static final int FIRE_SPREAD_AMOUNT = 8;

    private MolotovFireSpreader fireSpreader;
    private List<MolotovFirePosEntry> burningBlocks;

    private int timeLeft;

    public EntityMolotov(World world) {
        super(world);
        this.fireSpreader = new MolotovFireSpreader(FIRE_SPREAD_AMOUNT);
        this.timeLeft = 60;
    }

    public EntityMolotov(World world, EntityLivingBase thrower, EnumEntityThrowState state) {
        super(world, thrower, state, Integer.MAX_VALUE);
        this.fireSpreader = new MolotovFireSpreader(FIRE_SPREAD_AMOUNT);
        this.timeLeft = 60;
    }

    @Override
    public void onThrowableTick() {
        if(isFrozen) {
            if(burningBlocks != null && !burningBlocks.isEmpty()) {
                /*this.world.getEntitiesInAABBexcluding(this,
                        new AxisAlignedBB(this.posX - FIRE_SPREAD_AMOUNT, this.posY - FIRE_SPREAD_AMOUNT, this.posZ - FIRE_SPREAD_AMOUNT, this.posX + FIRE_SPREAD_AMOUNT, this.posY + FIRE_SPREAD_AMOUNT, this.posZ + FIRE_SPREAD_AMOUNT),
                        Predicates.and(EntitySelectors.IS_ALIVE, EntitySelectors.NOT_SPECTATING));*/
                if(world.isRemote) {
                    burningBlocks.forEach(entry -> world.spawnParticle(EnumParticleTypes.FLAME, entry.pos.getX() + 0.5, entry.y, entry.pos.getZ() + 0.5, 0, 0.2, 0));
                }
            }
            --this.timeLeft;
            if(timeLeft <= 0) this.setDead();
        }
    }

    @Override
    public void onExplode() {
    }

    @Override
    public boolean canBounce() {
        return false;
    }

    @Override
    public void writeSpawnData(ByteBuf buf) {
        super.writeSpawnData(buf);
        buf.writeInt(timeLeft);
    }

    @Override
    public void readSpawnData(ByteBuf buf) {
        super.readSpawnData(buf);
        timeLeft = buf.readInt();
    }

    @Override
    protected void onEntityFrozen() {
        this.burningBlocks = this.fireSpreader.spreadFrom(this);
        this.world.playSound(null, this.getPosition(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.MASTER, 1.0F, 1.0F);
    }

    protected class MolotovFireSpreader {

        private final int spreadPower;

        public MolotovFireSpreader(final int spreadPower) {
            this.spreadPower = spreadPower;
        }

        public List<MolotovFirePosEntry> spreadFrom(EntityMolotov molotov) {
            EnumFacing[] FACINGS = new EnumFacing[] {EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.WEST, EnumFacing.SOUTH, EnumFacing.EAST};
            List<MolotovFirePosEntry> list = new ArrayList<>();
            BlockPos initial = molotov.getPosition();
            this.editBlockPos(molotov.world, initial);
            BlockPos i = initial.down();
            int powerLeft = this.spreadPower;
            while(powerLeft > 0) {
                --powerLeft;
                if(list.isEmpty()) {
                    IBlockState state = molotov.world.getBlockState(i);
                    if(state.getBlock() == Blocks.AIR || !molotov.world.isAirBlock(i.up())) {
                        continue;
                    }
                    list.add(new MolotovFirePosEntry(i, world.getBlockState(i).getCollisionBoundingBox(molotov.world, initial)));
                } else {
                    List<MolotovFirePosEntry> toAdd = new ArrayList<>();
                    list.forEach(entry -> {
                        for(EnumFacing facing : FACINGS) {
                            BlockPos p = entry.pos.offset(facing);
                            BlockPos p1 = molotov.world.getHeight(p).down();
                            boolean flag = molotov.world.isAirBlock(p.up());
                            if(!flag) continue;
                            AxisAlignedBB alignedBB = molotov.world.getBlockState(p1).getCollisionBoundingBox(molotov.world, p1);
                            if(alignedBB == null) continue;
                            MolotovFirePosEntry firePosEntry = new MolotovFirePosEntry(p1, alignedBB);
                            if(!toAdd.contains(firePosEntry)) {
                                toAdd.add(firePosEntry);
                            }
                        }
                    });
                    toAdd.stream().filter(entry -> !list.contains(entry)).forEach(list::add);
                }
            }
            return list;
        }

        public void editBlockPos(World world, BlockPos pos) {
            while (pos.getY() > 1 && world.isAirBlock(pos.down())) {
                pos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
            }
        }
    }

    protected class MolotovFirePosEntry {

        public BlockPos pos;
        public float y;

        public MolotovFirePosEntry(BlockPos pos) {
            this.pos = pos;
            this.y = pos.getY() + 0.25F;
        }

        public MolotovFirePosEntry(BlockPos pos, AxisAlignedBB collisionBox) {
            this.pos = pos;
            this.y = pos.getY() + (float) collisionBox.maxY;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this) {
                return true;
            }
            if(obj instanceof MolotovFirePosEntry) {
                MolotovFirePosEntry entry = (MolotovFirePosEntry) obj;
                return this.pos.equals(entry.pos) && this.y == entry.y;
            }
            return false;
        }
    }
}
