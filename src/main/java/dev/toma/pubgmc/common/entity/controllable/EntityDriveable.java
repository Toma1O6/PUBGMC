package dev.toma.pubgmc.common.entity.controllable;

import dev.toma.pubgmc.api.client.game.CustomEntityNametag;
import dev.toma.pubgmc.api.entity.IControllable;
import dev.toma.pubgmc.api.entity.SynchronizableEntity;
import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.common.entity.util.Seat;
import dev.toma.pubgmc.common.entity.util.VehicleCategory;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class EntityDriveable extends Entity implements IControllable, IEntityAdditionalSpawnData, GameObject, SynchronizableEntity, CustomEntityNametag {

    public static final int KEY_FORWARD = 0b1;
    public static final int KEY_BACK = 0b10;
    public static final int KEY_RIGHT = 0b100;
    public static final int KEY_LEFT = 0b1000;

    protected static final DataParameter<Float> HEALTH = EntityDataManager.createKey(EntityDriveable.class, DataSerializers.FLOAT);
    protected static final DataParameter<Float> FUEL = EntityDataManager.createKey(EntityDriveable.class, DataSerializers.FLOAT);
    protected static final DataParameter<Boolean> STARTED = EntityDataManager.createKey(EntityDriveable.class, DataSerializers.BOOLEAN); // TODO implement

    // speed smoothing
    protected double lastMotionX, lastMotionY, lastMotionZ;
    // input
    private byte controllerInput;
    // game compatibility
    private UUID gameId = GameHelper.DEFAULT_UUID;
    // passengers
    private final Map<Integer, Seat> seatMap = new HashMap<>();
    // smoothing
    private int lerpSteps;
    private double lerpX;
    private double lerpY;
    private double lerpZ;
    private double lerpYaw;
    private double lerpPitch;

    public EntityDriveable(World world) {
        super(world);
        validate(this);
    }

    public abstract Collection<Seat> getSeats();

    public abstract float getMaxHealth();

    public abstract float getFuelTankCapacity();

    public abstract VehicleCategory getVehicleCategory();

    public abstract void runVehicleTick();

    @Override
    public void onUpdate() {
        this.onEntityUpdate();
        this.tickLerp();
        this.inputTick();
        this.handleVehicleInLava();
        this.runVehicleTick();
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }

    @Override
    public void onEntityUpdate() {
        this.lastMotionX = this.motionX;
        this.lastMotionY = this.motionY;
        this.lastMotionZ = this.motionZ;
        super.onEntityUpdate();
    }

    @Override
    public boolean canFitPassenger(Entity passenger) {
        int currentPassengers = this.seatMap.size();
        int limit = this.getSeats().size();
        return this.isPassenger(passenger) || currentPassengers < limit;
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        if (this.canBeRidden(player) && this.canFitPassenger(player)) {
            player.startRiding(this);
            return true; // TODO find seat and then synchronize to clients
        }
        return false;
    }

    @Nullable
    @Override
    public Entity getControllingPassenger() {
        for (Map.Entry<Integer, Seat> entry : this.seatMap.entrySet()) {
            Seat seat = entry.getValue();
            if (seat.isDriver()) {
                return this.world.getEntityByID(entry.getKey());
            }
        }
        return null;
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(HEALTH, this.getMaxHealth());
        this.dataManager.register(FUEL, this.getFuelTankCapacity());
        this.dataManager.register(STARTED, false);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setFloat("health", this.getHealth());
        compound.setFloat("fuel", this.getFuel());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    public UUID getCurrentGameId() {
        return this.gameId;
    }

    @Override
    public void assignGameId(UUID gameId) {
        this.gameId = gameId;
    }

    @Override
    public void onNewGameDetected(UUID newGameId) {
        this.setDead();
    }

    @Override
    public void handle(byte inputs) {
        this.controllerInput = inputs;
    }

    @Override
    public ITextComponent getComponent() {
        String key = EntityList.getEntityString(this);
        if (key == null) {
            key = "generic";
        }
        return new TextComponentTranslation("entity." + key + ".name");
    }

    @Override
    public void updatePassenger(Entity passenger) {
        if (!this.isPassenger(passenger))
            return;
        Seat seat = this.seatMap.get(passenger.getEntityId());
        if (seat == null) {
            passenger.dismountRidingEntity();
            return;
        }
        Vec3d position = this.adjustSeatPosition(seat.getPosition()).add(this.getPositionVector());
        passenger.setPosition(position.x, position.y, position.z);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        }
        Entity attacker = source.getTrueSource();
        if (this.isPassenger(attacker)) {
            return false;
        }
        return this.handleEntityAttack(source, amount);
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return super.isEntityInvulnerable(source) || source == DamageSource.FALL || source == DamageSource.ON_FIRE || source == DamageSource.IN_FIRE;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox() {
        return this.getEntityBoundingBox();
    }

    @Override
    public boolean isInRangeToRender3d(double x, double y, double z) {
        return true;
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.lerpX = x;
        this.lerpY = y;
        this.lerpZ = z;
        this.lerpYaw = yaw;
        this.lerpPitch = pitch;
        this.lerpSteps = 10;
    }

    public final void setHealth(float health) {
        this.dataManager.set(HEALTH, MathHelper.clamp(health, 0.0F, this.getMaxHealth()));
    }

    public final void addHealth(float amount) {
        this.setHealth(this.getHealth() + amount);
    }

    public final void removeHealth(float amount) {
        this.addHealth(-amount);
    }

    public final float getHealth() {
        return this.dataManager.get(HEALTH);
    }

    public final boolean isDestroyed() {
        return this.getHealth() <= 0.0F;
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

    public final double getCurrentMotionSqr() {
        return this.motionX * this.motionX + this.motionZ * this.motionZ;
    }

    public final double getLastMotionSqr() {
        return this.lastMotionX * this.lastMotionX + this.lastMotionZ * this.lastMotionZ;
    }

    public final boolean hasInput(int value) {
        return (this.controllerInput & value) == value;
    }

    public double getSmoothMotionSqr(float partialTicks) {
        double current = this.getCurrentMotionSqr();
        double last = this.getLastMotionSqr();
        return last + (current - last) * partialTicks;
    }

    protected Vec3d adjustSeatPosition(Vec3d position) {
        return position.rotateYaw(-this.rotationYaw * (float) (Math.PI / 180.0F));
    }

    protected void handleInputUpdate() {
    }

    protected void handleVehicleInLava() {
        if (this.world.isRemote || this.ticksExisted % 10 != 0 || !this.isInLava())
            return;
        this.attackEntityFrom(DamageSource.LAVA, 10.0F);
    }

    protected boolean handleEntityAttack(DamageSource source, float amount) {
        this.removeHealth(amount);
        return true;
    }

    private void inputTick() {
        Entity controller = this.getControllingPassenger();
        if (controller == null || !controller.isEntityAlive()) {
            this.controllerInput = 0;
            return;
        }
        this.handleInputUpdate();
    }

    private void tickLerp() {
        if (this.lerpSteps > 0 && !this.canPassengerSteer()) {
            double px = this.posX + (this.lerpX - this.posX) / (double)this.lerpSteps;
            double py = this.posY + (this.lerpY - this.posY) / (double)this.lerpSteps;
            double pz = this.posZ + (this.lerpZ - this.posZ) / (double)this.lerpSteps;
            double yaw = MathHelper.wrapDegrees(this.lerpYaw - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + yaw / (double)this.lerpSteps);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.lerpPitch - (double)this.rotationPitch) / (double)this.lerpSteps);
            --this.lerpSteps;
            this.setPosition(px, py, pz);
            this.setRotation(this.rotationYaw, this.rotationPitch);
        }
    }

    private static void validate(EntityDriveable vehicle) {
        Collection<Seat> seats = vehicle.getSeats();
        long driverPositions = seats.stream().filter(Seat::isDriver).count();
        if (driverPositions != 1) {
            throw new IllegalArgumentException("Vehicle must have exactly one driver, but found " + driverPositions + " on vehicle of type " + vehicle.getClass().getSimpleName());
        }
    }
}
