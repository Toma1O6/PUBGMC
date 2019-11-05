package com.toma.pubgmc.common.entity;

import com.toma.pubgmc.common.entity.ai.EntityAISearchLoot;
import jdk.nashorn.internal.objects.Global;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EntityAIPlayer extends EntityCreature {

    public static final HashMap<UUID, List<BlockPos>> GLOBAL_LOOT_CACHE = new HashMap<>();

    public NonNullList<ItemStack> inventory = NonNullList.withSize(9, ItemStack.EMPTY);

    public EntityAIPlayer(World worldIn) {
        super(worldIn);
        this.preventEntitySpawning = true;
        this.enablePersistence();
        this.setSize(0.6F, 1.95F);
        this.setCanPickUpLoot(true);
        if(!worldIn.isRemote) {
            UUID uuid = this.getUniqueID();
            GLOBAL_LOOT_CACHE.put(uuid, new ArrayList<>());
        }
    }

    public EntityAIPlayer(World world, BlockPos pos) {
        this(world);
        this.setPosition(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAISearchLoot(this, 0.01F));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D, 0.0001F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
    }

    @Override
    protected void updateEquipmentIfNeeded(EntityItem itemEntity) {
        super.updateEquipmentIfNeeded(itemEntity);
    }

    @Override
    public boolean canPickUpLoot() {
        return true;
    }

    // TODO check if death crate can be spawn
    @Override
    public void onDeath(DamageSource cause) {
        UUID uuid = this.getUniqueID();
        GLOBAL_LOOT_CACHE.remove(uuid);
        super.onDeath(cause);
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
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
