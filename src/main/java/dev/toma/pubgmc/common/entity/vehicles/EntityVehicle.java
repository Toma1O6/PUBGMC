package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.api.entity.IBombReaction;
import dev.toma.pubgmc.common.entity.vehicles.util.SeatPart;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.config.common.CFGVehicle;
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
    public static final float ENGINE_DAMAGE_MULTIPLIER = 1.5F;
    // engine
    protected int timeStartingLeft;
    protected int engineIdleTimeTotal;
    protected int MAX_ENGINE_IDLE_TIME = 160;
    protected static final DataParameter<Float> FUEL = EntityDataManager.createKey(EntityVehicle.class, DataSerializers.FLOAT);
    // Sound events
    public static final int SOUND_ENGINE_STARTING = 0;
    public static final int SOUND_ENGINE_STARTED = 1;
    public static final int SOUND_ENGINE_STOPPED = 2;

    protected static final DataParameter<Boolean> EXPLODED = EntityDataManager.createKey(EntityVehicle.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> STARTING = EntityDataManager.createKey(EntityVehicle.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> STARTED = EntityDataManager.createKey(EntityVehicle.class, DataSerializers.BOOLEAN);

    protected int timeBeforeExplode = 100;
    protected boolean bomb = false;
    protected Vec3d bombMotion = Vec3d.ZERO;

    public EntityVehicle(World world) {
        super(world);
        this.updateStepHeight();
    }

    public abstract CFGVehicle getVehicleConfiguration();

    @Override
    public void runVehicleTick() {
        this.engineTick();
        this.handleVehicleState();
        this.handleDestroyedTick();
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
        if ((this.controllerInput == 0 && this.isStarted() && this.getMotionSqr() < 0.1) || this.engineIdleTimeTotal > MAX_ENGINE_IDLE_TIME) {
            this.killEngine();
        }
        // Initiate starting sequence
        if (!this.isStarted() && !this.isStarting() && this.isStartKeyActive() && this.canStartVehicle() && !this.bomb) {
            this.setStarting(true);
        }
    }

    protected float getEnginePower() {
        return 1.0F;
    }

    protected boolean isStartKeyActive() {
        return this.hasInput(KEY_FORWARD) || this.hasInput(KEY_BACK);
    }

    protected boolean canStartVehicle() {
        return this.getHealth() > 0 && this.getFuel() > 0;
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

    protected void handleVehicleState() {
        this.applyDrag();
        this.handleVehicleInLava();
        this.handleVehicleInWater();
    }

    protected void applyDrag() {
        if (onGround) {
            // 0.985^120 = 0.16, 0.99^120 = 0.30
            this.multiplyMotion(0.985F);
        }
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

    protected float getTurnSpeed() {
        return getVehicleConfiguration().turningSpeed.getAsFloat();
    }

    protected float getAcceleration() {
        return getVehicleConfiguration().acceleration.getAsFloat();
    }

    protected float getMaxSpeed() {
        return getVehicleConfiguration().maxSpeed.getAsFloat();
    }

    protected void handleVehicleStuck() {
//        if (!ConfigPMC.common.world.classicss.vulnerableVehicle.get()) {
//            return;
//        }
    }

    protected void getVehicleWeight() {
        ;
    }

    protected float getSpeedPercentage() {
        return (float) (this.getMotionSqr() / getVehicleConfiguration().maxSpeed.getAsFloat());
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
    public boolean canEntityBoardVehicle(SeatPart seat, EntityLivingBase entity) {
        return super.canEntityBoardVehicle(seat, entity) && !this.isExploded() && this.getHealth() > 0;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(FUEL, this.getFuelTankCapacity());
        this.dataManager.register(EXPLODED, Boolean.FALSE);
        this.dataManager.register(STARTED, Boolean.FALSE);
        this.dataManager.register(STARTING, Boolean.FALSE);
    }
    protected void setStartedState(boolean started) {
        this.dataManager.set(STARTED, started);
    }

    protected void startEngine() {
        this.setStartedState(true);
        this.setStarting(false);
    }

    protected void killEngine() {
        this.setStartedState(false);
        this.setStarting(false);
        this.engineIdleTimeTotal = 0;
    }

    public boolean isStarted() {
        return this.dataManager.get(STARTED);
    }

    protected void setStarting(boolean starting) {
        this.dataManager.set(STARTING, starting);
        if (starting) {
            this.timeStartingLeft = 40 + (int) (1.0F - (30 * this.getHealthPercentage()));
            this.getSoundController().playStartingSound();
            // TODO play correct starting sound AT THE ENTITY
        }
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

    public static boolean isDriver(Entity entity) {
        return entity.getRidingEntity() instanceof EntityDriveable && entity.getRidingEntity().getControllingPassenger() == entity;
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

    public final void setExploded(boolean exploded) {
        this.dataManager.set(EXPLODED, exploded);
    }

    public final boolean isExploded() {
        return this.dataManager.get(EXPLODED);
    }

    @Override
    protected float getStepHeight() {
        return 1.25F;
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

    protected final void updateStepHeight() {
        this.stepHeight = this.getStepHeight();
    }

    private void handleDestroyedTick() {
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
        }
    }

    public boolean allowBombInteraction(World world, @Nullable IBlockState state, @Nullable Entity entity) {
        return true;
    }
}
