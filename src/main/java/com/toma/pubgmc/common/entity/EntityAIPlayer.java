package com.toma.pubgmc.common.entity;

import com.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityAIPlayer extends EntityLiving {

    protected NonNullList<ItemStack> inventory = NonNullList.withSize(9, ItemStack.EMPTY);

    public EntityAIPlayer(World worldIn) {
        super(worldIn);
        this.preventEntitySpawning = true;
    }

    public EntityAIPlayer(World world, BlockPos pos) {
        this(world);
        this.setPosition(pos.getX(), pos.getY(), pos.getZ());
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
        }
    }

    public static NonNullList<ItemStack> getBasicInventory() {
        return null;
    }
}
