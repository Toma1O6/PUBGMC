package com.toma.pubgmc.common.entity.throwables;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityFragGrenade extends EntityThrowableExplodeable {

    public EntityFragGrenade(World world) {
        super(world);
    }

    public EntityFragGrenade(World world, EntityLivingBase thrower, EnumEntityThrowState state) {
        super(world, thrower, state);
    }

    public EntityFragGrenade(World world, EntityLivingBase thrower, EnumEntityThrowState state, int time) {
        super(world, thrower, state, time);
    }

    @Override
    public void onExplode() {
        if(!world.isRemote) {
            this.setPosition(this.posX, this.posY + 1, this.posZ);
            world.createExplosion(null, this.posX, this.posY, this.posZ, 5.0F, false);
        }
        this.setDead();
    }
}
