package com.toma.pubgmc.common.entity;

import com.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityMolotov extends Entity {
    public EntityLivingBase thrower;
    protected int fuse;
    protected short range;
    protected List<BlockPos> positions = new ArrayList<BlockPos>();
    private int damageDelay = 0;
    private Random rand = new Random();

    public EntityMolotov(World worldIn) {
        super(worldIn);
        this.fuse = 200;
        this.preventEntitySpawning = true;
        this.isImmuneToFire = true;
        this.setSize(0.15f, 0.15f);
    }

    public EntityMolotov(World world, EntityLivingBase thrower, boolean isRightClick) {
        this(world);
        this.setPosition(thrower.posX, thrower.posY + thrower.getEyeHeight(), thrower.posZ);

        Vec3d vec = thrower.getLookVec();
        double modifier = 1;
        float strenght = 0.5f;

        if (isRightClick) {
            strenght = 1f;
        }

        if (thrower.isSprinting()) {
            modifier = 1.3;
        }

        this.motionX = ((vec.x * 1.5) * modifier) * strenght;
        this.motionY = ((vec.y * 1.5) * modifier) * strenght;
        this.motionZ = ((vec.z * 1.5) * modifier) * strenght;

        this.thrower = thrower;
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return true;
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (!this.hasNoGravity()) {
            this.motionY -= 0.04D;
        }

        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.98D;
        this.motionY *= 0.98D;
        this.motionZ *= 0.98D;

        if (this.onGround) {
            if (range == 0) calculateRange();

            this.motionX = 0;
            this.motionZ = 0;
            this.motionY = 0;
            --this.fuse;
            setEntitiesOnFireInRange();

            if (!positions.isEmpty() && ticksExisted % 3 == 0) createParticles();
        }

        if (this.fuse <= 0) {
            this.setDead();
        }

        if (this.isInWater()) {
            this.setDead();
        } else {
            this.handleWaterMovement();
            this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setDouble("posX", this.posX);
        compound.setDouble("posY", this.posY);
        compound.setDouble("posZ", this.posZ);
        compound.setDouble("motionX", this.motionX);
        compound.setDouble("motionY", this.motionY);
        compound.setDouble("motionZ", this.motionZ);
        compound.setInteger("fuse", this.fuse);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        posX = compound.getDouble("posX");
        posY = compound.getDouble("posY");
        posZ = compound.getDouble("posZ");
        motionX = compound.getDouble("motionX");
        motionY = compound.getDouble("motionY");
        motionZ = compound.getDouble("motionZ");
        fuse = compound.getInteger("fuse");
    }

    public void setEntitiesOnFireInRange() {
        if (range > 0) {
            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.posX - range, this.posY + 1, this.posZ - range, this.posX + range, this.posY - 4, this.posZ + range));
            for (Entity entity : list) {
                BlockPos ep = entity.getPosition();
                for (BlockPos pos : positions) {
                    if (ep.getX() == pos.getX() && ep.getZ() == pos.getZ()) {
                        if (entity instanceof EntityLiving) {
                            entity.setFire(10);
                        }

                        if (entity instanceof EntityPlayer) {
                            EntityPlayer player = (EntityPlayer) entity;
                            player.setFire(10);

                            if (damageDelay <= 0 && !player.isDead) {
                                damageDelay = 15;
                                //Use ON_FIRE instead of IN_FIRE otherwise it will damage the armor
                                player.attackEntityFrom(DamageSource.ON_FIRE, 5);
                            }

                            damageDelay--;
                        }
                    }
                }
            }
        }
    }

    public void calculateRange() {
        IBlockState state = world.getBlockState(getPosition().down());
        Material material = state.getMaterial();
        range = (short) (material == Material.WOOD || material == Material.CARPET || material == material.CLOTH ? 5 : 3);
        fillPositions();
    }

    public void fillPositions() {
        BlockPos m = getPosition();
        Iterable<BlockPos> affectedPos = BlockPos.getAllInBox(m.getX() - range, m.getY(), m.getZ() - range, m.getX() + range, m.getY(), m.getZ() + range);
        for (BlockPos p : affectedPos) {
            while (world.getBlockState(p.down()).getBlock().isReplaceable(world, p.down())) {
                p = new BlockPos(p.getX(), p.getY() - 1, p.getZ());
            }

            if (PUBGMCUtil.getDistanceToBlockPos3D(p, getPosition()) < range + 1) {
                RayTraceResult raytrace = world.rayTraceBlocks(new Vec3d(p.getX() + 0.5, p.getY(), p.getZ() + 0.5), PUBGMCUtil.getPositionVec(this), true, true, false);
                if (raytrace == null) {
                    positions.add(p);
                } else if (world.getBlockState(p).getBlock() != Blocks.AIR && world.getBlockState(p).getCollisionBoundingBox(world, p) != null && world.getBlockState(p).getCollisionBoundingBox(world, p).maxY < 0.25) {
                    positions.add(p);
                }
            }
        }
    }

    private void createParticles() {
        for (BlockPos p : positions) {
            spawnParticle(p, 3);
        }
    }

    private void spawnParticle(BlockPos pos, int count) {
        for (int i = 0; i < count; i++) {
            world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5 + (rand.nextDouble() - 0.5), pos.getY() + 0.1, pos.getZ() + 0.5 + (rand.nextDouble() - 0.5), 0, 0.05d, 0, 0);
        }
    }
}
