package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.entity.IBombReaction;
import dev.toma.pubgmc.common.entity.vehicles.util.SeatPart;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.config.common.CFGVehicle;
import dev.toma.pubgmc.util.math.Mth;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;

public abstract class EntityVehicle extends EntityDriveable implements IBombReaction {

    // TODO configurable?
    public static float ENGINE_DAMAGE_MULTIPLIER = 1.0F;
    public static float ROOF_DAMAGE_MULTIPLIER = 0.5F;
    public static float BODY_DAMAGE_MULTIPLIER = 0.75F;
    public static float WHEEL_DAMAGE_MULTIPLIER = 0.5F;
    // engine
    protected int timeStartingLeft;
    protected int engineIdleTimeTotal;
    protected int MAX_ENGINE_IDLE_TIME = 160;
    protected static final DataParameter<Float> FUEL = EntityDataManager.createKey(EntityVehicle.class, DataSerializers.FLOAT);
    protected static float FUEL_TANK_CAPACITY = 100F;
    // Sound events
    public static final int SOUND_ENGINE_STARTING = 0;
    public static final int SOUND_ENGINE_STARTED = 1;
    public static final int SOUND_ENGINE_STOPPED = 2;

    protected static final DataParameter<Boolean> EXPLODED = EntityDataManager.createKey(EntityVehicle.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> STARTING = EntityDataManager.createKey(EntityVehicle.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> STARTED = EntityDataManager.createKey(EntityVehicle.class, DataSerializers.BOOLEAN);

    protected float velocity;
    protected float turn;
    protected int timeBeforeExplode = 100;
    protected boolean bomb = false;
    protected Vec3d bombMotion = Vec3d.ZERO;

    public EntityVehicle(World world) {
        super(world);
        this.updateStepHeight();
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(FUEL, this.getFuelTankCapacity());
        this.dataManager.register(EXPLODED, Boolean.FALSE);
        this.dataManager.register(STARTED, Boolean.FALSE);
        this.dataManager.register(STARTING, Boolean.FALSE);
    }

    public abstract CFGVehicle getVehicleConfiguration();

    @Override
    protected void handleEmptyInputUpdate() {
        if (isStarted()) {this.engineIdleTimeTotal++;}
    }

    @Override
    public void runVehicleTick() {
        this.engineTick();
        this.handleVehicleState();
        this.handleDestroyedTick();
        this.updateMotionAndTurning();
        this.updateStepHeight();
        this.particleTick();
    }

    protected void engineTick() {
        if (this.world.isRemote)
            return;
        if (!hasFuel()) {
            this.killEngine();
            return;
        }
        // Starting tick
        if (this.isStarting() && --this.timeStartingLeft <= 0) {
            this.startEngine();
            this.getSoundController().playStartedSound();
        }
        // Engine idle tick
        if (this.engineIdleTimeTotal > MAX_ENGINE_IDLE_TIME) {
            this.killEngine();
        }
        // Initiate starting sequence
        if (!this.isStarted() && !this.isStarting() && this.canStartVehicle() && !this.bomb) {
            this.setStarting(true);
        }
        // Engine idle fuel
        if (isStarted()) {
            removeFuel(0.004F); // 20.83 minutes to burn 100.0F fuel
            if (this.velocity > 0)
                removeFuel(0.008F * velocity);
        }
    }

    protected void setStartedState(boolean started) {
        this.dataManager.set(STARTED, started);
    }

    protected void startEngine() {
        this.setStartedState(true);
        this.setStarting(false);
        this.engineIdleTimeTotal = 0;
    }

    protected void killEngine() {
        this.setStartedState(false);
        this.setStarting(false);
        this.engineIdleTimeTotal = 0;
    }

    protected boolean isStartKeyActive() {
        return this.hasInput(KEY_FORWARD) || this.hasInput(KEY_BACK);
    }

    protected boolean canStartVehicle() {
        return this.isStartKeyActive() && !this.isDestroyed() && this.hasFuel();
    }

    public boolean isStarted() {
        return this.dataManager.get(STARTED);
    }

    protected void setStarting(boolean starting) {
        this.dataManager.set(STARTING, starting);
        if (starting) {
            this.engineIdleTimeTotal = 0;
            this.timeStartingLeft = getEngineStartTime();
            this.getSoundController().playStartingSound();
            // TODO play correct starting sound AT THE ENTITY
        }
    }

    public int getEngineStartTime() {
        return 70 - (int) (30 * this.getEnginePower());
    }

    public boolean isStarting() {
        return this.dataManager.get(STARTING);
    }

    public void toggleEngine() {
        if (this.isStarting())
            return;
        if (this.isStarted()) {
            this.killEngine();
        } else if (this.canStartVehicle()) {
            this.setStarting(true);
        }
    }

    protected void handleVehicleState() {
        this.collisionCooldown--;
        this.handleVehicleInLava();
        this.handleVehicleInWater();
    }

    protected void handleVehicleInLava() {
        if (this.world.isRemote || this.ticksExisted % 10 != 0 || !this.isInLava())
            return;
        this.attackEntityFrom(DamageSource.LAVA, 10.0F);
    }

    protected void handleVehicleInWater() {
        if (this.isInWater()) {
            float multiplier = this.isSubmergedInWater() ? 0.85F : 0.97F;
            this.multiplyMotion(multiplier);
        }
    }

    protected void handleDestroyedTick() {
        if (!this.isDestroyed()) {
            return;
        }
        this.killEngine();
        this.timeBeforeExplode--;
        if (this.shouldExplode()) {
            this.explode();
        }
    }

    private boolean shouldExplode() {
        if (this.isExploded()) { // only allow vehicle itself to explode once
            return false;
        }

        if (!this.isDestroyed()) {
            return false;
        } else {
            return this.timeBeforeExplode < 0;
        }
    }

    public final boolean isExploded() {
        return this.dataManager.get(EXPLODED);
    }

    protected void updateMotionAndTurning() {
        if (this.turn != 0.0F) {
            this.rotationYaw -= this.turn;
            if (this.rotationYaw > 180F) {
                this.rotationYaw -= 360F;
            } else if (this.rotationYaw < -180F) {
                this.rotationYaw += 360F;
            }
        }
        if (this.bomb || hasBombMotion()) {
            if (!onGround)
                this.setNoGravity(true);
            setMotion(bombMotion);
            dropBombMotion();
            return;
        }
        if (collidedHorizontally) {
            this.velocity *= 0.6F;
        }
        float yawRad = (float) Math.toRadians(this.rotationYaw);
        Vec3d direction = new Vec3d(-MathHelper.sin(yawRad), 0.0, MathHelper.cos(yawRad)).normalize();
        Vec3d v;
        if (isMovingForward()) {
            v = direction.scale(Math.min(this.velocity, getMaxSpeed()));
        } else {
            v = direction.scale(Math.max(this.velocity, getReverseMaxSpeed()));
        }
        this.motionX = v.x;
        if (collidedVertically)
            this.motionY = 0;
        this.motionZ = v.z;
    }

    protected float getEnginePower() {
        return 1.0F;
    }

    protected final void updateStepHeight() {
        this.stepHeight = this.getStepHeight();
    }

    @Override
    public float getStepHeight() {
        return 1.25F;
    }

    protected void particleTick() {
        ;
    }

    @Override
    protected void applyDrag() {
        this.applyGravity();
        // ground drag
        if (onGround) {
            decelerate(0.00347F); // 1 second to drop 5 km/h
        }
    }

    protected void decelerate(float amount) {
        this.velocity = Mth.linearDecay(this.velocity, amount);
    }

    @Override
    public boolean canEntityBoardVehicle(SeatPart seat, EntityLivingBase entity) {
        return super.canEntityBoardVehicle(seat, entity) && !this.isExploded() && this.getHealth() > 0;
    }

    public final void setFuel(float fuel) {
        this.dataManager.set(FUEL, MathHelper.clamp(fuel, 0.0F, this.getFuelTankCapacity()));
    }

    public final void addFuel(float amount) {
        this.setFuel(this.getFuel() + amount);
    }

    public final void removeFuel(float amount) {
        this.addFuel(-amount);
    }

    public final float getFuel() {
        return this.dataManager.get(FUEL);
    }

    public final boolean hasFuel() {
        return this.getFuel() > 0.0F;
    }

    public float getTurnSpeed() {
        return getVehicleConfiguration().turningSpeed.getAsFloat();
    }

    public float getAcceleration() {
        return getVehicleConfiguration().acceleration.getAsFloat();
    }

    public float getMaxSpeed() {
        return getVehicleConfiguration().maxSpeed.getAsFloat();
    }

    public float getReverseMaxSpeed() {
        return -0.3F * getVehicleConfiguration().maxSpeed.getAsFloat();
    }

    protected void handleVehicleStuck() {
//        if (!ConfigPMC.common.world.classicss.vulnerableVehicle.get()) {
//            return;
//        }
    }

    public void getVehicleWeight() {
        ;
    }

    public float getSpeedPercentage() {
        return (float) (this.getMotionSqr() / getVehicleConfiguration().maxSpeed.getAsFloat());
    }

    public int getEngineIdleTimeTotal() {
        return engineIdleTimeTotal;
    }

    public static boolean isDriver(Entity entity) {
        return entity.getRidingEntity() instanceof EntityDriveable && entity.getRidingEntity().getControllingPassenger() == entity;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setFloat("fuel", this.getFuel());
        compound.setInteger("timeBeforeExplode", this.timeBeforeExplode);
        compound.setBoolean("exploded", this.isExploded());
        compound.setBoolean("started", this.isStarted());
        compound.setBoolean("starting", this.isStarting());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setFuel(compound.getFloat("fuel"));
        this.timeBeforeExplode = compound.getInteger("timeBeforeExplode");
        this.setExploded(compound.getBoolean("exploded"));
        this.setStartedState(compound.getBoolean("started"));
        this.setStarting(compound.getBoolean("starting"));
    }

    @Override
    protected boolean handleEntityAttack(DamageSource source, float amount) {
        // Remove by creative player
        if (this.isExploded() && !this.world.isRemote && source.isCreativePlayer()) {
            this.setDead();
            return true;
        }
        return super.handleEntityAttack(source, amount);
    }

    public final boolean isFunctional() {
        return !this.isDestroyed() && !this.isExploded();
    }

    protected int getTicksBeforeExplode() {
        return timeBeforeExplode;
    }

    protected void explode() {
        if (this.world.isRemote)
            return;
        boolean doMobGriefing = ForgeEventFactory.getMobGriefingEvent(this.world, this);
        this.multiplyMotion(1.2F);
        this.motionY += 0.8F;
        for (EntityVehiclePart part : this.getParts()) {
            part.hurt(DamageSource.GENERIC, Integer.MAX_VALUE);
        }
        this.world.createExplosion(this, this.posX, this.posY, this.posZ, this.getExplosionPower(), doMobGriefing);
        this.removePassengers(); // should remove passenger after explode
        this.setExploded(true);
    }

    protected float getExplosionPower() {
        return 4.0F;
    }

    public final void setExploded(boolean exploded) {
        this.dataManager.set(EXPLODED, exploded);
    }

    public void onBomb(Entity exploder, Vec3d bombVec, @Nullable IBlockState state, @Nullable Entity entity) {
        if (world.isRemote) {
            return;
        }
        double strength = ConfigPMC.common.world.classicss.bombStrength.getAsFloat();
        this.bombMotion = new Vec3d(this.bombMotion.x + bombVec.x, this.bombMotion.y + bombVec.y, this.bombMotion.z + bombVec.z).scale(strength);
        if (hasBombMotion()) {
            this.bomb = true;
        } else {
            this.bomb = false;
            this.bombMotion = Vec3d.ZERO;
        }
        killEngine();
    }

    public boolean hasBombMotion() {
        return Math.abs(bombMotion.x) > 0.01F || Math.abs(bombMotion.z) > 0.01F;
    }

    protected void dropBombMotion() {
        double x = Math.abs(bombMotion.x) > 0.01F ? bombMotion.x * 0.98F : 0F;
        double z = Math.abs(bombMotion.z) > 0.01F ? bombMotion.z * 0.98F : 0F;
        double y = bombMotion.y;
        if (onGround) {
            if (y < 0) {
                y = 0;
            }
            x *= 0.98F;
            z *= 0.98F;
        } else {
            if (y > 0) {
                y *= 0.97F;
            }
            y -= 0.02F;
        }
        if (collided) {
            x *= 0.98F;
            z *= 0.98F;
        }
        bombMotion = new Vec3d(x, y, z);
        if (!hasBombMotion()) {
            this.bomb = false;
            this.bombMotion = Vec3d.ZERO;
            this.setNoGravity(false);
        }
    }

    public boolean allowBombInteraction(World world, @Nullable IBlockState state, @Nullable Entity entity) {
        return true;
    }
}
