package com.toma.pubgmc.common.entity.throwables;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntitySmokeGrenade extends EntityThrowableExplodeable {

    public static final int SMOKE_EFFECT_DURATION = 400;
    public static final int SMOKE_ANGLE_PER_TICK = 90;

    private boolean startedSmoking;
    private int effectLeft = SMOKE_EFFECT_DURATION;
    private int currentAngle;

    private double smokeHeight = 0;

    public EntitySmokeGrenade(World world) {
        super(world);
    }

    public EntitySmokeGrenade(World world, EntityLivingBase thrower, EnumEntityThrowState state) {
        super(world, thrower, state);
    }

    public EntitySmokeGrenade(World world, EntityLivingBase thrower, EnumEntityThrowState state, int timeLeft) {
        super(world, thrower, state, timeLeft);
    }

    @Override
    public void onExplode() {
        this.startedSmoking = true;
    }

    public void createSmokeParticles() {
        if(this.world.getBlockState(this.getPosition()).getMaterial().isLiquid()) {
            world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX, this.posY, this.posZ, 0, 0.2, 0);
            return;
        }
        this.currentAngle += SMOKE_ANGLE_PER_TICK;
        if(world.isRemote) {
            for(int i = SMOKE_ANGLE_PER_TICK; i > 0; i -= 5) {
                int updatedAngle = (this.currentAngle - i) % 360;
                double angle = Math.toRadians(updatedAngle);
                double x = Math.sin(angle) / 3;
                double z = Math.cos(angle) / 3;
                double y = 0;
                double d = 0;
                for(; d < this.smokeHeight; d += 0.5D) {
                    this.world.spawnParticle(EnumParticleTypes.CLOUD, true, this.posX, this.posY + d, this.posZ, x, y, z);
                }
            }
        }
    }

    @Override
    public void onThrowableTick() {
        if(startedSmoking) {
            if(this.smokeHeight < 3.5D) {
                if(ticksExisted % 5 == 0) {
                    this.smokeHeight += 0.25D;
                }
            }
            this.createSmokeParticles();
            --this.effectLeft;
            if(effectLeft <= 0) {
                this.setDead();
            }
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("smoking", this.startedSmoking);
        compound.setInteger("left", this.effectLeft);
        compound.setInteger("angle", this.currentAngle);
        compound.setDouble("smokeHeight", this.smokeHeight);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.startedSmoking = compound.getBoolean("smoking");
        this.effectLeft = compound.getInteger("left");
        this.currentAngle = compound.getInteger("angle");
        this.smokeHeight = compound.getDouble("smokeHeight");
    }
}
