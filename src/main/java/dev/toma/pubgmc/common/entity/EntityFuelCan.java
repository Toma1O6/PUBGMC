package dev.toma.pubgmc.common.entity;

import com.google.common.base.Predicates;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.block.IBulletReaction;
import dev.toma.pubgmc.api.capability.IPlayerData;
import dev.toma.pubgmc.api.capability.PlayerDataProvider;
import dev.toma.pubgmc.api.entity.IBombReaction;
import dev.toma.pubgmc.api.entity.SynchronizableEntity;
import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.common.items.ItemFuelCan;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.init.PMCDamageSources;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import static dev.toma.pubgmc.DevUtil.getEntityByUUID;

public class EntityFuelCan extends Entity implements GameObject, IBulletReaction, SynchronizableEntity, IBombReaction{
    private float health = ItemFuelCan.initHealth;
    private int fuse = 60;
    protected UUID owner;
    public static float size = 0.75F;
    private int collisionCooldown = 0;
    private static final int COLLISION_COOLDOWN_TIME = 10;
    private static final float burnRadius = 3F;
    private UUID gameId = GameHelper.DEFAULT_UUID;

    public EntityFuelCan(World world) {
        super(world);
        this.setSize(size, size);
    }

    public EntityFuelCan(World world, EntityLivingBase user, float health) {
        super(world);
        this.setPosition(user.posX, user.posY, user.posZ);
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
        fuse = compound.getInteger("fuse");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setFloat("health", health);
        compound.setInteger("fuse", fuse);
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
        if (!this.world.isRemote && !player.onGround && collisionCooldown <= 0 && player.isSprinting()) {
            double kickStrength = 0.2F;
            if (player.motionY > 0.0D) {
                double xz = Math.sqrt(player.motionX * player.motionX + player.motionZ * player.motionZ);
                this.motionX += kickStrength * (player.motionX / xz);
                this.motionY = kickStrength;
                this.motionZ += kickStrength * (player.motionZ / xz);
                this.isAirBorne = true;
                this.velocityChanged = true;
                collisionCooldown = COLLISION_COOLDOWN_TIME;
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.world.isRemote) {
            if (!this.onGround) {
                this.motionY -= 0.1D;
            }
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.92D;
            this.motionZ *= 0.92D;
            this.motionY *= 0.92D;
            if (Math.sqrt(motionX*motionX + motionY*motionY + motionZ*motionZ) < 0.05D) {
                this.motionX = 0;
                this.motionY = 0;
                this.motionZ = 0;
            }
            if (collisionCooldown > 0) {
                collisionCooldown--;
            }

            if (this.health <= 0) {
                if (fuse > 0) {
                    onBurnTick();
                }
                if (--fuse < 0) {
                    onExplode();
                }
            }
            if (this.isBurning()) { // for instant trigger
                this.health = 0;
            }
        }
        handleBurnParticles();
    }

    public void handleBurnParticles() {
        if (world.isRemote && this.health <= 0) { // clinet only
            // TODO make particle visible and looks good
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, true, posX + 0.5 * (world.rand.nextDouble() - 0.5F), posY, posZ + 0.5 * (world.rand.nextDouble() - 0.5F), 0, 0.1d, 0);
            for (int i = 0; i < 50; ++i) {
                world.spawnParticle(EnumParticleTypes.FLAME, true, this.posX, this.posY, this.posZ,
                        0.2 * (world.rand.nextDouble() - 0.5F), 0.1 * world.rand.nextDouble(), 0.2 * (world.rand.nextDouble() - 0.5F));
            }
        }
    }

    public void onBurnTick() {
        if (!world.isRemote) { // server tick
            if (this.ticksExisted % 5 == 0) {
                AxisAlignedBB aabb = new AxisAlignedBB(this.posX - burnRadius, this.posY, this.posZ - burnRadius, this.posX + burnRadius, this.posY + 1.5, this.posZ + burnRadius);
                List<Entity> entities = this.world.getEntitiesWithinAABB(Entity.class, aabb, Predicates.and(EntitySelectors.IS_ALIVE, EntitySelectors.NOT_SPECTATING));
                for (Entity e : entities) {
                    e.setFire(5);
                    if (e instanceof EntityLivingBase) {
                        e.attackEntityFrom(PMCDamageSources.fuelcan(getOwner()), 1);
                    }
                }
            }
        }
    }

    public void onExplode() {
        if (!this.world.isRemote && !this.isDead) {
            this.fuse = -1;
            this.setPosition(this.posX, this.posY + 1, this.posZ);
            boolean canBreakBlocks = ConfigPMC.world().grenadeGriefing.get();
            world.createExplosion(getOwner(), this.posX, this.posY, this.posZ, 2.3F, canBreakBlocks);
            this.notifyNeighboringEntities();
            this.setDead();
        }
    }
    private void notifyNeighboringEntities() {
        if (world.isRemote) {
            return;
        }
        AxisAlignedBB burnAABB = new AxisAlignedBB(this.posX - burnRadius, this.posY, this.posZ - burnRadius,
                this.posX + burnRadius, this.posY + 1.5, this.posZ + burnRadius);
        List<Entity> nearbyFuelCans = this.world.getEntitiesWithinAABB(Entity.class, burnAABB);
        for (Entity e : nearbyFuelCans) {
            if (e instanceof EntityFuelCan) {
                this.explodeOtherFuelCan((EntityFuelCan) e);
            } else if (e instanceof IBombReaction) {
                IBombReaction reaction = (IBombReaction) e;
                if (reaction.allowBombInteraction(world, null, e)) {
                    double vecX = e.posX - this.posX;
                    double vecY = e.posY - this.posY;
                    double vecZ = e.posZ - this.posZ;
                    double distance = Math.sqrt(vecX*vecX + vecY*vecY + vecZ*vecZ);
                    if (distance < burnRadius) {
                        // Provides high vertical thrust, moderate horizontal thrust
                        double bombStrength = Math.min(1.3 - distance / burnRadius, 1);
                        double xz = Math.sqrt(vecX * vecX + vecZ * vecZ);
                        // Expected: xz=1.3, y=2.3, sqrt(1.3)=1.14
                        vecX = (1.3 * vecX / xz) * bombStrength;
                        vecY = 2.3 * bombStrength;
                        vecZ = (1.3 * vecZ / xz) * bombStrength;
                        double multiplier = 1.4F; // already checked the maximum effect
                        reaction.onBomb(this, new Vec3d(vecX, vecY, vecZ).scale(multiplier), null, e);
                    }
                }
            }
        }
    }

    private void explodeOtherFuelCan(EntityFuelCan other) {
        if (!other.isDead && other.canExplode()) {
            other.onExplode();
            other.setDead();
        }
    }

    public Entity getOwner() {
        return getEntityByUUID(this.world, this.owner);
    }

    public boolean canExplode() {
        return this.fuse >= 0;
    }


    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        if (!this.world.isRemote && health > 0) { // can't retrieve burning fuel can
            ItemStack itemStack = new ItemStack(PMCItems.FUELCAN);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setFloat("health", this.health);
            itemStack.setTagCompound(nbt);
            player.inventory.addItemStackToInventory(itemStack);
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            data.getAimInfo().setAiming(false, 1.0F);
            this.setDead();
        }
        return true;
    }

    @Override
    public void onBulletHit(EntityBullet bullet, Vec3d hit, @Nullable IBlockState state, @Nullable Entity entity) {
        if (!this.world.isRemote) {
            float damage = bullet.getDamage();
            this.health = Math.max(this.health - damage, 0);
        }
    }

    @Override
    public boolean allowBulletInteraction(World world, @Nullable IBlockState state, @Nullable Entity entity) {
        if (entity == null) {
            return false;
        }
        return health > 0;
    }

    @Override
    public NBTTagCompound encodeNetworkData() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeEntityToNBT(nbt);
        return nbt;
    }

    @Override
    public void decodeNetworkData(NBTTagCompound nbt) {
        readEntityFromNBT(nbt);
    }

    @Override
    public void onBomb(Entity exploder, Vec3d vec3d, @Nullable IBlockState state, @Nullable Entity entity) {
        this.health = 0;
    }

    @Override
    public boolean allowBombInteraction(World world, @Nullable IBlockState state, @Nullable Entity entity) {
        return this.health > 0;
    }
}
