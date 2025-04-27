package dev.toma.pubgmc.common.entity;

import com.google.common.base.Predicates;
import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.common.items.ItemFuelCan;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.init.PMCDamageSources;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

import static dev.toma.pubgmc.DevUtil.getEntityByUUID;

public class EntityFuelCan extends Entity implements GameObject {
    private float health = ItemFuelCan.initHealth;
    private int fuse = 60;
    protected UUID owner;
    private UUID gameId = GameHelper.DEFAULT_UUID;

    public EntityFuelCan(World world) {
        super(world);
        this.setSize(0.75F, 0.75F);
    }

    public EntityFuelCan(World world, EntityLivingBase user, float health) {
        super(world);
        this.setPosition(user.posX, user.posY + 1, user.posZ);
        this.motionX = 0;
        this.motionY = -0.39F;
        this.motionZ = 0;
        this.health = health;
        this.owner = user.getUniqueID();
    }

    @Override
    protected void entityInit() {
        ;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        health = compound.getFloat("health");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setFloat("health", health);
    }

    @Override
    public UUID getCurrentGameId() {
        return gameId;
    }

    @Override
    public void assignGameId(UUID gameId) {
        this.gameId = gameId;
    }

    @Override
    public void onNewGameDetected(UUID newGameId) {
        setDead();
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
        if (!this.world.isRemote) {
            double kickStrength = 0.4;
            if (player.isSprinting()) {
                kickStrength *= 1.2F;
            }
            if (!player.onGround) {
                kickStrength *= 1.25F;
            }

            Vec3d lookVec = player.getLookVec();
            this.motionX = lookVec.x * kickStrength;
            this.motionY = kickStrength * 0.6;
            this.motionZ = lookVec.z * kickStrength;
            this.isAirBorne = true;
            this.velocityChanged = true;
        }
    }

    @Override
    public void applyEntityCollision(Entity entityIn) {
        if (!this.world.isRemote && entityIn instanceof EntityBullet) {
            float damage = ((EntityBullet) entityIn).getDamage();
            this.health -= damage;
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote || this.health > 0) {
            return;
        }
        // server only
        onBurnTick();
        if (--fuse < 0) {
            onExplode();
            this.setDead();
        }
    }

    public void onBurnTick() {
        if (this.ticksExisted % 10 == 0) {
            AxisAlignedBB aabb = new AxisAlignedBB(this.posX - 1, this.posY, this.posZ - 1, this.posX + 1, this.posY + 1, this.posZ + 1);
            List<EntityLivingBase> entities = this.world.getEntitiesWithinAABB(EntityLivingBase.class, aabb, Predicates.and(EntitySelectors.IS_ALIVE, EntitySelectors.NOT_SPECTATING));
            for (EntityLivingBase entity : entities) {
                entity.setFire(5);
                entity.attackEntityFrom(PMCDamageSources.fuelcan(getOwner()), 2);
            }
            if (world.isRemote) {
                for (int i = 0; i < 5; ++i) {
                    world.spawnParticle(EnumParticleTypes.FLAME, this.posX + (world.rand.nextDouble() - 0.5D) * 0.5, this.posY + world.rand.nextDouble() * 0.5, this.posZ + (world.rand.nextDouble() - 0.5D) * 0.5, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    public void onExplode() {
        if (!this.world.isRemote) {
            boolean canBreakBlocks = ConfigPMC.world().grenadeGriefing.get();
            this.setPosition(this.posX, this.posY + 1, this.posZ);
            world.createExplosion(getOwner(), this.posX, this.posY, this.posZ, 3.0F, canBreakBlocks);
        }
    }

    public Entity getOwner() {
        return getEntityByUUID(this.world, this.owner);
    }
}
