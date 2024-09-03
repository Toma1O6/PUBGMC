package dev.toma.pubgmc.common.entity.vehicles;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.client.game.CustomEntityNametag;
import dev.toma.pubgmc.api.entity.IControllable;
import dev.toma.pubgmc.api.entity.SynchronizableEntity;
import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_PacketSendEntityData;
import dev.toma.pubgmc.util.helper.GameHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class EntityDriveable extends Entity implements IControllable, IEntityAdditionalSpawnData, GameObject, SynchronizableEntity, CustomEntityNametag, IEntityMultiPart {

    public static final int KEY_FORWARD = 0b1;
    public static final int KEY_BACK = 0b10;
    public static final int KEY_RIGHT = 0b100;
    public static final int KEY_LEFT = 0b1000;
    public static final float ENGINE_DAMAGE_MULTIPLIER = 1.5F;

    protected static final DataParameter<Float> HEALTH = EntityDataManager.createKey(EntityDriveable.class, DataSerializers.FLOAT);
    protected static final DataParameter<Float> FUEL = EntityDataManager.createKey(EntityDriveable.class, DataSerializers.FLOAT);
    protected static final DataParameter<Boolean> STARTING = EntityDataManager.createKey(EntityDriveable.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> STARTED = EntityDataManager.createKey(EntityDriveable.class, DataSerializers.BOOLEAN); // TODO implement

    // speed smoothing
    protected double lastMotionX, lastMotionY, lastMotionZ;
    // input
    private byte controllerInput;
    // game compatibility
    private UUID gameId = GameHelper.DEFAULT_UUID;
    // passengers
    private final int seatingCapacity;
    private final BiMap<Integer, SeatPart> seatingMap = HashBiMap.create();
    private final SeatPart driverSeat;
    // engine
    private int timeStartingLeft;
    private int engineIdleTimeTotal;
    // subparts
    private final EntityVehiclePart[] parts;

    public EntityDriveable(World world) {
        super(world);
        List<EntityVehiclePart> partList = new ArrayList<>();
        this.registerVehicleParts(new PartRegistration() {
            @Override
            public <P extends EntityVehiclePart> P register(P part) {
                partList.add(part);
                return part;
            }
        });
        this.parts = partList.toArray(new EntityVehiclePart[0]);
        List<SeatPart> seats = partList.stream().filter(part -> part instanceof SeatPart).map(part -> (SeatPart) part).collect(Collectors.toList());
        this.seatingCapacity = seats.size();
        validate(this, seats);
        this.driverSeat = seats.stream().filter(SeatPart::isDriver).findFirst().orElseThrow(() -> new IllegalStateException("This cannot happen unless some validation is broken!"));
    }

    public abstract EntityVehiclePart getMainBodyPart();

    public abstract void registerVehicleParts(PartRegistration registration);

    public abstract float getMaxHealth();

    public abstract float getFuelTankCapacity();

    public abstract VehicleCategory getVehicleCategory();

    public abstract void runVehicleTick();

    @Override
    public void onUpdate() {
        this.onEntityUpdate();
        this.inputTick();
        this.handleVehicleInLava();
        this.runVehicleTick();
        this.applyDrag();
        this.applyGravity();
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }

    @Override
    public void onEntityUpdate() {
        this.lastMotionX = this.motionX;
        this.lastMotionY = this.motionY;
        this.lastMotionZ = this.motionZ;
        super.onEntityUpdate();
        // Part tick
        for (EntityVehiclePart part : this.parts) {
            part.onUpdate();
        }
        // Game tick
        if (this.ticksExisted % 50L == 0L && !GameHelper.validateGameEntityStillValid(this)) {
            this.setDead();
        }
    }

    @Override
    public boolean canFitPassenger(Entity passenger) {
        int currentPassengers = this.seatingMap.size();
        return this.isPassenger(passenger) || currentPassengers < this.seatingCapacity;
    }

    @Nullable
    @Override
    public Entity getControllingPassenger() {
        Integer driverEntityId = this.seatingMap.inverse().get(this.driverSeat);
        if (driverEntityId != null) {
            return this.world.getEntityByID(driverEntityId);
        }
        return null;
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(HEALTH, this.getMaxHealth());
        this.dataManager.register(FUEL, this.getFuelTankCapacity());
        this.dataManager.register(STARTED, Boolean.FALSE);
        this.dataManager.register(STARTING, Boolean.FALSE);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setFloat("health", this.getHealth());
        compound.setFloat("fuel", this.getFuel());
        compound.setBoolean("started", this.isStarted());
        compound.setBoolean("starting", this.isStarting());
        compound.setTag("parts", this.serializeParts());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        this.setHealth(compound.getFloat("health"));
        this.setFuel(compound.getFloat("fuel"));
        this.setStartedState(compound.getBoolean("started"));
        this.setStarting(compound.getBoolean("starting"));
        this.deserializeParts(compound.getCompoundTag("parts"));
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
        int passengerId = passenger.getEntityId();
        SeatPart seat = this.seatingMap.get(passengerId);
        if (seat == null) {
            if (!this.world.isRemote) {
                passenger.dismountRidingEntity();
            }
            return;
        }
        Vec3d position = seat.getWorldPosition();
        passenger.setPosition(position.x, position.y, position.z);
        // TODO smooth turning
        //passenger.rotationYaw += rotationDelta;
        //passenger.setRotationYawHead(passenger.getRotationYawHead() + rotationDelta);
        this.applyYawRotationToPassenger(passenger);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void applyOrientationToEntity(Entity entityToUpdate) {
        this.applyYawRotationToPassenger(entityToUpdate);
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
        EntityVehiclePart body = this.getMainBodyPart();
        return body != null && this.attackEntityFromPart(body, source, amount);
    }

    @Override
    public boolean attackEntityFromPart(MultiPartEntityPart part, DamageSource source, float damage) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        }
        Entity attacker = source.getTrueSource();
        if (this.isPassenger(attacker)) {
            return false;
        }
        if (part instanceof EntityVehiclePart) {
            EntityVehiclePart vehiclePart = (EntityVehiclePart) part;
            float damageMultiplier = vehiclePart.getDamageMultiplier(source);
            float newDamage = damage * damageMultiplier;
            if (vehiclePart.canHurtVehicle(source, newDamage) && this.handleEntityAttack(source, newDamage)) {
                vehiclePart.hurt(source, newDamage);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return super.isEntityInvulnerable(source) || source == DamageSource.FALL || source == DamageSource.ON_FIRE || source == DamageSource.IN_FIRE;
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (this.world.isRemote)
            return;
        SeatPart seat = this.seatingMap.remove(passenger.getEntityId());
        if (seat != null && !this.world.isRemote) {
            Vec3d dismountPosition = seat.getDismountPosition();
            GameHelper.teleport(passenger, dismountPosition.x, dismountPosition.y, dismountPosition.z);
        }
        this.sendClientData();
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
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
    public World getWorld() {
        return this.world;
    }

    @Nullable
    @Override
    public final EntityVehiclePart[] getParts() {
        return parts;
    }

    @Override
    public NBTTagCompound encodeNetworkData() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList seats = new NBTTagList();
        for (Map.Entry<Integer, SeatPart> entry : this.seatingMap.entrySet()) {
            NBTTagCompound seatNbt = new NBTTagCompound();
            seatNbt.setInteger("passenger", entry.getKey());
            seatNbt.setInteger("seat", entry.getValue().getEntityId());
            seats.appendTag(seatNbt);
        }
        nbt.setTag("seats", seats);
        nbt.setTag("parts", this.serializeParts());
        return nbt;
    }

    @Override
    public void decodeNetworkData(NBTTagCompound nbt) {
        NBTTagList seats = nbt.getTagList("seats", Constants.NBT.TAG_COMPOUND);
        this.seatingMap.clear();
        for (int i = 0; i < seats.tagCount(); i++) {
            NBTTagCompound seatNbt = seats.getCompoundTagAt(i);
            int passenger = seatNbt.getInteger("passenger");
            int seat = seatNbt.getInteger("seat");
            EntityVehiclePart vehiclePart = this.findPartById(seat);
            if (vehiclePart instanceof SeatPart) {
                this.seatingMap.put(passenger, (SeatPart) vehiclePart);
            } else {
                Pubgmc.logger.warn("Failed to resolve seatingMap for vehicle {} as vehicle part ID '{}' is {}", this, seat, vehiclePart);
            }
        }
        this.deserializeParts(nbt.getCompoundTag("parts"));
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        ByteBufUtils.writeTag(buffer, this.encodeNetworkData());
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        this.decodeNetworkData(ByteBufUtils.readTag(additionalData));
    }

    @Override
    public boolean canRiderInteract() {
        return true;
    }

    @Override
    public void move(MoverType type, double x, double y, double z) {
        this.move(x, y, z);
    }

    public void move(double x, double y, double z) {
        this.world.profiler.startSection("moveMultipart");
        List<AxisAlignedBB> collisionBoxes = this.world.getCollisionBoxes(this, this.getEntityBoundingBox().expand(x, y, z));
        double xOffset = x;
        double yOffset = y;
        double zOffset = z;
        for (EntityVehiclePart part : this.getParts()) {
            if (part.getBoundingBoxMode() != EntityVehiclePart.BoundingBoxMode.COLLIDER)
                continue;

            AxisAlignedBB partBB = part.getEntityBoundingBox();
            // Y-axis collision
            if (y != 0.0) {
                for (AxisAlignedBB collisionBox : collisionBoxes) {
                    yOffset = collisionBox.calculateYOffset(partBB, yOffset);
                }
            }

            // X-axis collision
            if (x != 0.0) {
                for (AxisAlignedBB collisionBox : collisionBoxes) {
                    xOffset = collisionBox.calculateXOffset(partBB, xOffset);
                }
            }

            // Z-axis collision
            if (z != 0.0) {
                for (AxisAlignedBB collisionBox : collisionBoxes) {
                    zOffset = collisionBox.calculateZOffset(partBB, zOffset);
                }
            }

            // TODO block step - maybe handle as horizontal collision?
        }

        if (xOffset != 0.0 || yOffset != 0.0 || zOffset != 0.0) {
            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(xOffset, yOffset, zOffset));
        }

        this.resetPositionToBB();
        this.collidedHorizontally = x != xOffset || z != zOffset;
        this.collidedVertically = y != yOffset;
        this.onGround = this.collidedVertically && y < 0.0D;
        this.world.profiler.endSection();
    }

    public boolean boardVehicle(SeatPart seat, EntityLivingBase entity, EnumHand hand) {
        if (this.canEntityBoardVehicle(seat, entity)) {
            if (!this.world.isRemote) {
                if (this.seatingMap.remove(entity.getEntityId()) != null) {
                    entity.dismountRidingEntity();
                }
                entity.startRiding(this);
                this.seatingMap.put(entity.getEntityId(), seat);
                this.sendClientData();
            }
            return true;
        }
        return false;
    }

    public boolean canEntityBoardVehicle(SeatPart seat, EntityLivingBase entity) {
        if (entity == null)
            return false;
        Integer passengerInSeat = this.seatingMap.inverse().get(seat);
        return this.canBeRidden(entity) && this.canFitPassenger(entity) && passengerInSeat == null;
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

    public final void setStartedState(boolean started) {
        this.dataManager.set(STARTED, started);
    }

    public final void startEngine() {
        this.setStartedState(true);
        this.setStarting(false);
    }

    public final void killEngine() {
        this.setStartedState(false);
        this.setStarting(false);
    }

    public final boolean isStarted() {
        return this.dataManager.get(STARTED);
    }

    public final void setStarting(boolean starting) {
        this.dataManager.set(STARTING, starting);
        if (starting) {
            this.timeStartingLeft = 20 + (int) (60 * (this.getHealth() / this.getMaxHealth()));
        }
    }

    public final boolean isStarting() {
        return this.dataManager.get(STARTING);
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

    public final double getSmoothMotionSqr(float partialTicks) {
        double current = this.getCurrentMotionSqr();
        double last = this.getLastMotionSqr();
        return last + (current - last) * partialTicks;
    }

    public final void setMotion(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        this.lastMotionX = x;
        this.lastMotionY = y;
        this.lastMotionZ = z;
    }

    public final void multiplyMotion(double x, double y, double z) {
        if (Math.abs(this.motionX *= x) < 0.0001) {
            this.motionX = 0.0;
        }
        if (Math.abs(this.motionY *= y) < 0.0001) {
            this.motionY = 0.0;
        }
        if (Math.abs(this.motionZ *= z) < 0.0001) {
            this.motionZ = 0.0;
        }
    }

    public final void multiplyMotion(double multiplier) {
        this.multiplyMotion(multiplier, multiplier, multiplier);
    }

    @Nullable
    public final EntityVehiclePart findPartById(int id) {
        for (EntityVehiclePart part : this.getParts()) {
            if (part.getEntityId() == id) {
                return part;
            }
        }
        return null;
    }

    public final void sendClientData() {
        if (this.world.isRemote)
            return;
        PacketHandler.sendToAllTracking(new S2C_PacketSendEntityData(this), this);
    }

    protected void handleInputUpdate() {
    }

    protected void handleVehicleInLava() {
        if (this.world.isRemote || this.ticksExisted % 10 != 0 || !this.isInLava())
            return;
        this.attackEntityFrom(DamageSource.LAVA, 10.0F);
    }

    protected void applyGravity() {
        if (this.hasNoGravity())
            return;
        if (this.onGround)
            this.motionY = Math.max(this.motionY, 0.0);
        if (this.motionY < -2.65)
            return;
        this.motionY -= 0.04905;
    }

    protected void applyDrag() {
        this.multiplyMotion(0.95F);
    }

    protected float getMaxPassengerRotationAngleDegrees() {
        return 115.0F;
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
        this.engineTick();
    }

    private void applyYawRotationToPassenger(Entity entity) {
        entity.setRenderYawOffset(this.rotationYaw);
        float rotationDelta = MathHelper.wrapDegrees(entity.rotationYaw - this.rotationYaw);
        float maxAngle = this.getMaxPassengerRotationAngleDegrees();
        float rotationRange = MathHelper.clamp(rotationDelta, -maxAngle, maxAngle);
        entity.prevRotationYaw += rotationRange - rotationDelta;
        entity.rotationYaw += rotationRange - rotationDelta;
        entity.setRotationYawHead(entity.rotationYaw);
    }

    private NBTTagCompound serializeParts() {
        NBTTagCompound tag = new NBTTagCompound();
        for (EntityVehiclePart part : this.getParts()) {
            if (!part.hasCustomSaveData())
                continue;
            tag.setTag(part.partName, part.savePartData());
        }
        return tag;
    }

    private void deserializeParts(NBTTagCompound tag) {
        for (EntityVehiclePart part : this.getParts()) {
            if (!part.hasCustomSaveData())
                continue;
            part.loadPartData(tag.getCompoundTag(part.partName));
        }
    }

    private void engineTick() {
        // Starting tick
        if (this.isStarting() && --this.timeStartingLeft <= 0) {
            this.startEngine();
        }
        // Engine idle tick
        if (this.controllerInput == 0 && this.isStarted() && this.getCurrentMotionSqr() < 0.1 && ++this.engineIdleTimeTotal > 400) {
            this.killEngine();
        }
    }

    private static void validate(EntityDriveable vehicle, List<SeatPart> seats) {
        long driverPositions = seats.stream().filter(SeatPart::isDriver).count();
        if (driverPositions != 1) {
            throw new IllegalArgumentException("Vehicle must have exactly one driver, but found " + driverPositions + " on vehicle of type " + vehicle.getClass().getSimpleName());
        }
    }

    @FunctionalInterface
    public interface PartRegistration {
        <P extends EntityVehiclePart> P register(P part);
    }
}
