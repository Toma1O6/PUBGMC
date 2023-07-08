package dev.toma.pubgmc.common.entity.throwables;

import com.google.common.base.Predicates;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityMolotov extends EntityThrowableExplodeable {

    public static final int FIRE_SPREAD_AMOUNT = 6;
    public static final int SPREAD_DELAY = 15;

    private MolotovFireSpreader fireSpreader;
    private List<MolotovFirePosEntry> burningBlocks;

    private int timesSpreaded;
    private int timeLeft;
    private int startedSpreadingAt;
    private boolean hasStartedSpreading;

    public EntityMolotov(World world) {
        super(world);
        this.fireSpreader = new MolotovFireSpreader();
        this.timeLeft = 200;
    }

    public EntityMolotov(World world, EntityLivingBase thrower, EnumEntityThrowState state) {
        super(world, thrower, state, Integer.MAX_VALUE);
        this.fireSpreader = new MolotovFireSpreader();
        this.timeLeft = 200;
    }

    @Override
    public void onThrowableTick() {
        this.setInvisible(this.isFrozen);
        if (world.isRemote && !isFrozen) {
            world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0, 0, 0);
        }
        if (this.world.getBlockState(this.getPosition()).getMaterial().isLiquid()) {
            setDead();
        }
        if (isFrozen) {
            if (hasStartedSpreading) {
                if ((this.ticksExisted - this.startedSpreadingAt) % SPREAD_DELAY == 0 && timesSpreaded < FIRE_SPREAD_AMOUNT) {
                    ++this.timesSpreaded;
                    this.fireSpreader.spread(this, this.burningBlocks);
                }
            }
            if (burningBlocks != null && !burningBlocks.isEmpty()) {
                List<Entity> entities = this.world.getEntitiesInAABBexcluding(this,
                        new AxisAlignedBB(this.posX - FIRE_SPREAD_AMOUNT, this.posY - FIRE_SPREAD_AMOUNT, this.posZ - FIRE_SPREAD_AMOUNT, this.posX + FIRE_SPREAD_AMOUNT, this.posY + FIRE_SPREAD_AMOUNT, this.posZ + FIRE_SPREAD_AMOUNT),
                        Predicates.and(EntitySelectors.IS_ALIVE, EntitySelectors.NOT_SPECTATING));
                for (Entity e : entities) {
                    if (e instanceof EntityLivingBase) {
                        for (MolotovFirePosEntry entry : this.burningBlocks) {
                            BlockPos p0 = new BlockPos(entry.pos.getX(), entry.y, entry.pos.getZ());
                            BlockPos p00 = new BlockPos(p0.getX(), p0.getY() + 1, p0.getZ());
                            BlockPos p1 = e.getPosition();
                            if (p0.equals(p1) || p00.equals(p1)) {
                                e.setFire(5);
                                if (this.ticksExisted % 10 == 0) {
                                    e.attackEntityFrom(DamageSource.ON_FIRE, 4);
                                }
                                break;
                            }
                        }
                    }
                }
                if (world.isRemote) {
                    burningBlocks.forEach(entry -> createMolotovParticles(EntityMolotov.this.world, entry, 2, 1));
                }
            }
            --this.timeLeft;
            if (timeLeft <= 0) this.setDead();
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
    public boolean hasNoGravity() {
        return super.hasNoGravity() || this.hasStartedSpreading;
    }

    @Override
    protected void onEntityFrozen() {
        this.burningBlocks = this.fireSpreader.startSpreading(this);
        this.hasStartedSpreading = true;
        this.startedSpreadingAt = this.ticksExisted;
        this.world.playSound(null, this.getPosition(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.MASTER, 1.0F, 1.0F);
        super.onEntityFrozen();
    }

    protected void createMolotovParticles(World world, MolotovFirePosEntry entry, int amount, int spreadAmount) {
        BlockPos pos = entry.pos;
        for (int i = 0; i < amount; i++) {
            world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + smallDouble(spreadAmount), entry.y, pos.getZ() + smallDouble(spreadAmount), smallDouble(20) - smallDouble(20), 0.01, smallDouble(20) - smallDouble(20));
        }
    }

    protected double smallDouble(int modifier) {
        return rand.nextDouble() / modifier;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("ticksExisted", this.ticksExisted);
        compound.setInteger("timeLeft", this.timeLeft);
        compound.setInteger("startedSpreadingAt", this.startedSpreadingAt);
        compound.setBoolean("hasStarted", this.hasStartedSpreading);
        if (this.burningBlocks != null && !this.burningBlocks.isEmpty()) {
            NBTTagList list = new NBTTagList();
            for (MolotovFirePosEntry entry : this.burningBlocks) {
                list.appendTag(this.writeFireEntryToNBT(entry));
            }
            compound.setTag("burningBlocks", list);
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.ticksExisted = compound.getInteger("ticksExisted");
        this.timeLeft = compound.getInteger("timeLeft");
        this.startedSpreadingAt = compound.getInteger("startedSpreadingAt");
        this.hasStartedSpreading = compound.getBoolean("hasStarted");
        this.fireSpreader = new MolotovFireSpreader();
        this.burningBlocks = new ArrayList<>();
        if (compound.hasKey("burningBlocks")) {
            NBTTagList list = compound.getTagList("burningBlocks", Constants.NBT.TAG_COMPOUND);
            for (int i = 0; i < list.tagCount(); i++) {
                NBTTagCompound nbt = list.getCompoundTagAt(i);
                MolotovFirePosEntry entry = this.readEntryFromNBT(nbt);
                burningBlocks.add(entry);
            }
        }
    }

    protected NBTTagCompound writeFireEntryToNBT(MolotovFirePosEntry entry) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("pos", NBTUtil.createPosTag(entry.pos));
        nbt.setFloat("y", entry.y);
        return nbt;
    }

    protected MolotovFirePosEntry readEntryFromNBT(NBTTagCompound nbt) {
        BlockPos pos = NBTUtil.getPosFromTag(nbt.getCompoundTag("pos"));
        float y = nbt.getFloat("y");
        return new MolotovFirePosEntry(pos, y);
    }

    protected class MolotovFireSpreader {

        final EnumFacing[] FACINGS = new EnumFacing[]{EnumFacing.NORTH, EnumFacing.WEST, EnumFacing.SOUTH, EnumFacing.EAST};

        public List<MolotovFirePosEntry> startSpreading(EntityMolotov molotov) {
            if (molotov.isInWater()) {
                molotov.setDead();
                return Collections.emptyList();
            }
            List<MolotovFirePosEntry> list = new ArrayList<>();
            BlockPos initial = molotov.getPosition();
            BlockPos ground = this.findGround(molotov.world, initial);
            AxisAlignedBB alignedBB = molotov.world.getBlockState(ground).getCollisionBoundingBox(molotov.world, ground);
            if (molotov.world.isAirBlock(ground.up())) {
                list.add(new MolotovFirePosEntry(ground, alignedBB));
            }
            return list;
        }

        public void spread(EntityMolotov molotov, List<MolotovFirePosEntry> list) {
            if (list == null) return;
            List<MolotovFirePosEntry> toBeAdded = new ArrayList<>();
            for (MolotovFirePosEntry entry : list) {
                for (EnumFacing facing : FACINGS) {
                    BlockPos pos = entry.pos.offset(facing);
                    BlockPos ground = this.findGround(molotov.world, pos);
                    Pair<Integer, BlockPos> pair = this.getFireStepHeight(molotov.world, ground);
                    ground = pair.getRight();
                    int stepHeight = pair.getLeft();
                    if (stepHeight > 1) {
                        continue;
                    }
                    IBlockState state = molotov.world.getBlockState(ground);
                    AxisAlignedBB alignedBB = state.getCollisionBoundingBox(molotov.world, ground);
                    if (state.getBlock() == Blocks.AIR || state.getMaterial().isLiquid()) {
                        continue;
                    }
                    MolotovFirePosEntry entry1 = new MolotovFirePosEntry(ground, alignedBB);
                    toBeAdded.add(entry1);
                }
            }
            toBeAdded.stream().filter(entry -> !list.contains(entry)).forEach(list::add);
        }

        public BlockPos findGround(World world, BlockPos pos) {
            BlockPos ground = pos;
            while (ground.getY() > 1 && world.isAirBlock(ground)) {
                ground = new BlockPos(ground.getX(), ground.getY() - 1, ground.getZ());
            }
            return ground;
        }

        public Pair<Integer, BlockPos> getFireStepHeight(World world, BlockPos pos) {
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(pos);
            int stepHeight = 0;
            while (!world.isAirBlock(mutableBlockPos.up()) && pos.getY() < 255) {
                ++stepHeight;
                mutableBlockPos.setY(mutableBlockPos.getY() + 1);
            }
            return Pair.of(stepHeight, mutableBlockPos.toImmutable());
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
            this.y = pos.getY() + (collisionBox != null ? (float) collisionBox.maxY : 0.0F);
        }

        public MolotovFirePosEntry(BlockPos pos, float y) {
            this.pos = pos;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof MolotovFirePosEntry) {
                MolotovFirePosEntry entry = (MolotovFirePosEntry) obj;
                return this.pos.equals(entry.pos) && this.y == entry.y;
            }
            return false;
        }
    }
}
