package com.toma.pubgmc.common.entity;

import com.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityAIPlayer extends EntityLiving {

    public NonNullList<ItemStack> inventory = NonNullList.withSize(9, ItemStack.EMPTY);

    public EntityAIPlayer(World worldIn) {
        super(worldIn);
        this.preventEntitySpawning = true;
        this.enablePersistence();
        this.setSize(0.6F, 1.95F);
    }

    public EntityAIPlayer(World world, BlockPos pos) {
        this(world);
        this.setPosition(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    public GunBase getWeapon(boolean primary) {
        return this.inventory.get(primary ? 0 : 1).getItem() instanceof GunBase ? (GunBase) this.inventory.get(primary ? 0 : 1).getItem() : null;
    }

    public boolean hasWeapon(boolean primary) {
        return this.getWeapon(primary) != null;
    }

    public boolean hasWeapon() {
        return this.getWeapon(true) != null || this.getWeapon(false) != null;
    }

    public static void spawnEntity(World world, BlockPos from, boolean rideParachute, @Nonnull NonNullList<ItemStack> inventory) {
        if(inventory == null) throw new NullPointerException("Cannot spawn AI player with NULL inventory!");
        if(!world.isRemote) {
            EntityAIPlayer ai = new EntityAIPlayer(world, from);
            ai.inventory = inventory;
            world.spawnEntity(ai);
            if(rideParachute) {
                EntityParachute parachute = new EntityParachute(world, ai);
                world.spawnEntity(parachute);
                ai.startRiding(parachute);
            }
        }
    }

    public static NonNullList<ItemStack> getBasicInventory() {
        return NonNullList.withSize(9, ItemStack.EMPTY);
    }
}
