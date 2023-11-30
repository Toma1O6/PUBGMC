package dev.toma.pubgmc.common.entity.controllable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.config.common.CFGVehicle;
import dev.toma.pubgmc.init.PMCDamageSources;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.util.helper.GameHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public abstract class EntityVehicle extends EntityControllable implements IEntityAdditionalSpawnData, GameObject {
    private static final Predicate<Entity> TARGET = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, Entity::canBeCollidedWith);
    private static final AxisAlignedBB BOX = new AxisAlignedBB(-0.5d, 0d, -0.5d, 1.5d, 1d, 1.5d);

    public float health;
    public float currentSpeed;
    public float turnModifier;
    public float fuel;
    public boolean isBroken = false;
    private short timeInInvalidState;
    private UUID gameId = GameHelper.DEFAULT_UUID;

    public EntityVehicle(World world) {
        super(world);
        stepHeight = 1f;
        preventEntitySpawning = true;
        health = getVehicleConfiguration().maxHealth.getAsFloat();
        fuel = 60 + world.rand.nextInt(40);
    }

    public EntityVehicle(World world, int x, int y, int z) {
        this(world);
        setPosition(x, y, z);
    }

    public abstract int getMaximumCapacity();

    public abstract Vec3d getEnginePosition();

    public abstract Vec3d getExhaustPosition();

    public abstract SoundEvent vehicleSound();

    public abstract CFGVehicle getVehicleConfiguration();

    @Override
    public void updatePre() {
        this.handleEmptyInputs();
        Vec3d look = this.getLookVec();
        motionX = look.x * currentSpeed;
        motionZ = look.z * currentSpeed;
        if (currentSpeed != 0) {
            rotationYaw += currentSpeed > 0 ? turnModifier : -turnModifier;
        }
        if (!isBeingRidden() && (!hasMovementInput() || !hasTurnInput() || !hasFuel() || isBroken)) {
            reset();
        }
        this.handleEntityCollisions();
        this.checkState();
        if (collidedHorizontally) {
            currentSpeed *= 0.6;
        }
    }

    @Override
    public void updatePost() {
        move(MoverType.SELF, motionX, motionY, motionZ);
        playSoundAtVehicle();
        spawnParticles();
        if (ticksExisted % 20 == 0) {
            GameHelper.validateGameEntityStillValid(this);
        }
    }

    protected void handleEmptyInputs() {
        CFGVehicle stats = this.getVehicleConfiguration();
        if (!hasMovementInput() || !hasFuel()) {
            if (Math.abs(currentSpeed) < 0.01)
                currentSpeed = 0f;

            if (currentSpeed != 0) {
                currentSpeed = currentSpeed > 0 ? currentSpeed - stats.acceleration.getAsFloat() * 0.1F : currentSpeed + stats.acceleration.getAsFloat() * 0.1F;
            }
        }
        if (!hasTurnInput()) {
            if (Math.abs(turnModifier) <= 0.5f)
                turnModifier = 0f;

            if (turnModifier != 0) {
                turnModifier = turnModifier > 0 ? turnModifier - 0.5f : turnModifier + 0.5f;
            }
        }
        if (!onGround) {
            motionY -= 0.1;
        }
    }

    public void handleEntityCollisions() {
        Vec3d vec1 = new Vec3d(posX, posY, posZ);
        Vec3d vec2 = new Vec3d(vec1.x + motionX, vec1.y + motionY, vec1.z + motionZ);
        Entity e = findEntityInPath(vec1, vec2);

        if (e != null) {
            e.motionX += motionX * currentSpeed * 3;
            e.motionY += currentSpeed;
            e.motionZ += motionZ * currentSpeed * 3;
            e.attackEntityFrom(PMCDamageSources.vehicle(getControllingPassenger()), Math.abs(currentSpeed) * 15f);
        }
    }

    @Override
    public void handleForward() {
        if (!isBroken) {
            CFGVehicle cfg = getVehicleConfiguration();
            float max = cfg.maxSpeed.getAsFloat();
            if (hasFuel() || currentSpeed < 0) {
                burnFuel();
                currentSpeed = currentSpeed < max ? currentSpeed + cfg.acceleration.getAsFloat() : max;
            }
        }
    }

    @Override
    public void handleBackward() {
        if (!isBroken) {
            CFGVehicle cfg = getVehicleConfiguration();
            if (currentSpeed > 0) {
                currentSpeed -= cfg.acceleration.getAsFloat();
            } else if (hasFuel()) {
                burnFuel();
                float reverseMax = -cfg.maxSpeed.getAsFloat() * 0.3F;
                currentSpeed = currentSpeed > reverseMax ? currentSpeed - 0.02F : reverseMax;
            }
        }
    }

    @Override
    public void handleRight() {
        if (!isBroken) {
            CFGVehicle cfg = getVehicleConfiguration();
            float max = cfg.maxTurningAngle.getAsFloat();
            float partial = cfg.turningSpeed.getAsFloat();
            turnModifier = turnModifier < max ? turnModifier + partial : max;
        }
    }

    @Override
    public void handleLeft() {
        if (!isBroken) {
            CFGVehicle cfg = getVehicleConfiguration();
            float max = cfg.maxTurningAngle.getAsFloat();
            float partial = cfg.turningSpeed.getAsFloat();
            turnModifier = turnModifier > -max ? turnModifier - partial : -max;
        }
    }

    @Nullable
    protected Entity findEntityInPath(Vec3d start, Vec3d end) {
        Entity e = null;
        List<Entity> entityList = world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(motionX, motionY + 1, motionZ).grow(1d), TARGET);
        double d0 = 0;

        for (int i = 0; i < entityList.size(); i++) {
            Entity entity = entityList.get(i);

            if (entity != this && !isPassenger(entity)) {
                AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow(0.30000001192092896D);
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);

                if (raytraceresult != null) {
                    double d1 = start.squareDistanceTo(raytraceresult.hitVec);

                    if (d1 < d0 || d0 == 0.0D) {
                        e = entity;
                        d0 = d1;
                    }
                }
            }
        }

        return e;
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            if (this.canBeRidden(player) && canFitPassenger(player)) {
                player.startRiding(this);
            }
        }

        return true;
    }

    private void explode() {
        if (!world.isRemote) {
            this.world.createExplosion(this, posX, posY, posZ, 3f, false);
            this.setDead();
        }
    }

    // Should be running only on server side in case some client doesn't receive packet
    // containing new health value of this vehicle
    protected void checkState() {
        // if whole vehicle is under water -> can drive in shallow water
        if (this.isInWater() && world.getBlockState(getPosition().up()).getMaterial().isLiquid()) {
            timeInInvalidState++;
            motionX *= 0.4d;
            motionZ *= 0.4d;
            motionY = -0.15d;
        }

        if (timeInInvalidState > 30) {
            isBroken = true;
        }

        if (isInLava() || health <= 0f) explode();
    }

    protected void spawnParticles() {
        float max = getVehicleConfiguration().maxHealth.getAsFloat();
        if (world.isRemote) {
            if (health / max <= 0.35f) {
                Vec3d engineVec = (new Vec3d(getEnginePosition().x, getEnginePosition().y + 0.25d, getEnginePosition().z)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, posX + engineVec.x, posY + engineVec.y, posZ + engineVec.z, 0d, 0.1d, 0d);

                if (health / max <= 0.15f) {
                    double rngX = (rand.nextDouble() - rand.nextDouble()) * 0.1;
                    double rngZ = (rand.nextDouble() - rand.nextDouble()) * 0.1;
                    world.spawnParticle(EnumParticleTypes.FLAME, true, posX + engineVec.x, posY + engineVec.y - 0.2, posZ + engineVec.z, rngX, 0.02d, rngZ);
                }
            }

            if (!isBroken && hasFuel()) {
                Vec3d exhaustVec = (new Vec3d(getExhaustPosition().x, getExhaustPosition().y + 0.25d, getExhaustPosition().z)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, true, posX + exhaustVec.x, posY + exhaustVec.y, posZ + exhaustVec.z, 0, 0.02d, 0);
            }

            if (isBroken) {
                Vec3d engine = (new Vec3d(getEnginePosition().x, getEnginePosition().y, getEnginePosition().z)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2f));
                world.spawnParticle(EnumParticleTypes.CLOUD, true, posX + engine.x, posY + engine.y, posZ + engine.z, 0d, 0.05d, 0d);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (!getPassengers().contains(source.getTrueSource())) {
            this.health -= amount;
        }

        return true;
    }

    @Override
    public void updatePassenger(Entity passenger) {
        if (this.isPassenger(passenger)) {
            float x = 0F;
            float z = -0.55F;
            float f1 = (float) ((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()));

            if (this.getPassengers().size() > 0) {
                int i = this.getPassengers().indexOf(passenger);
                x = getPassengerXOffset(i);
                z = getPassengerZOffset(i);
            }

            Vec3d vec3d = (new Vec3d((double) x, 0.0D, (double) z)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
            passenger.setPosition(this.posX + vec3d.x, this.posY + (double) f1, this.posZ + vec3d.z);

            if (passenger instanceof EntityAnimal && this.getPassengers().size() > 1) {
                int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
                passenger.setRenderYawOffset(((EntityAnimal) passenger).renderYawOffset + (float) j);
                passenger.setRotationYawHead(passenger.getRotationYawHead() + (float) j);
            }
        }
    }

    @Override
    public boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < getMaximumCapacity();
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return true;
    }

    public boolean isPlayerDriver(EntityPlayer player) {
        return player.isRiding() && player.getRidingEntity() instanceof EntityVehicle && player.getRidingEntity().getPassengers().get(0) == player;
    }

    private boolean isVehicleMoving() {
        return currentSpeed != 0;
    }

    private boolean isVehicleMovingForward() {
        return currentSpeed > 0;
    }

    private boolean isVehicleMovingBackward() {
        return currentSpeed < 0;
    }

    private void playSoundAtVehicle() {
        if (!isVehicleMoving()) {
            if (ticksExisted % 5 == 0) playSound(PMCSounds.vehicleIdle, 2f, 1f);
        } else {
            if (ticksExisted % 18 == 0) playSound(vehicleSound(), currentSpeed * 5f, 1f);
        }
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        health = compound.getFloat("health");
        fuel = compound.getFloat("fuel");
        currentSpeed = compound.getFloat("speed");
        isBroken = compound.getBoolean("isBroken");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setFloat("health", this.health);
        compound.setFloat("fuel", this.fuel);
        compound.setFloat("speed", this.currentSpeed);
        compound.setBoolean("isBroken", this.isBroken);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    protected float getPassengerXOffset(int passengerIndex) {
        return passengerIndex % 2 == 0 ? 0.5f : -0.8f;
    }

    protected float getPassengerZOffset(int passengerIndex) {
        return passengerIndex < 2 ? -0.55f : 0.55f;
    }

    protected void applyYawToEntity(Entity entityToUpdate) {
        entityToUpdate.setRenderYawOffset(this.rotationYaw);
        float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
        float f1 = MathHelper.clamp(f, -105.0F, 105.0F);
        entityToUpdate.prevRotationYaw += f1 - f;
        entityToUpdate.rotationYaw += f1 - f;
        entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
    }

    public boolean hasFuel() {
        return fuel > 0;
    }

    public void refill(EntityPlayer source) {
        if (this.getPassengers().contains(source))
            fuel = fuel + 30f < 100f ? fuel + 30f : 100f;
        else
            Pubgmc.logger.warn("{} has attempted to refuel vehicle with ID {}, but he wasn't inside the vehicle!", source, this.getEntityId());
    }

    public void burnFuel() {
        fuel = hasFuel() ? fuel - 0.01f : 0f;
    }

    protected float generateFuel() {
        return 60F + rand.nextInt(40);
    }

    @Override
    public void writeSpawnData(ByteBuf buf) {
        buf.writeFloat(health);
        buf.writeFloat(fuel);
    }

    @Override
    public void readSpawnData(ByteBuf buf) {
        health = buf.readFloat();
        fuel = buf.readFloat();
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
}
