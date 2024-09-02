package dev.toma.pubgmc.common.entity.vehicles;

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
import java.util.*;
import java.util.stream.Collectors;

public abstract class EntityDriveable extends Entity implements IControllable, IEntityAdditionalSpawnData, GameObject, SynchronizableEntity, CustomEntityNametag, IEntityMultiPart {

    public static final int KEY_FORWARD = 0b1;
    public static final int KEY_BACK = 0b10;
    public static final int KEY_RIGHT = 0b100;
    public static final int KEY_LEFT = 0b1000;
    public static final float ENGINE_DAMAGE_MULTIPLIER = 1.5F;

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
    private final int seatingCapacity;
    private final Map<SeatPart, Integer> seatingMap = new IdentityHashMap<>();
    // smoothing
    private int lerpSteps;
    private double lerpX;
    private double lerpY;
    private double lerpZ;
    private double lerpYaw;
    private double lerpPitch;
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
        this.tickLerp();
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
        for (EntityVehiclePart part : this.parts) {
            part.onUpdate();
        }
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
        for (Map.Entry<SeatPart, Integer> entry : this.seatingMap.entrySet()) {
            SeatPart seat = entry.getKey();
            if (seat.isDriver()) {
                return this.world.getEntityByID(entry.getValue());
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
        compound.setBoolean("started", this.isStarted());
        compound.setTag("parts", this.serializeParts());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        this.setHealth(compound.getFloat("health"));
        this.setFuel(compound.getFloat("fuel"));
        this.setStarted(compound.getBoolean("started"));
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
        Optional<SeatPart> seatOptional = this.getSeatOccupiedBy(passenger);
        if (!seatOptional.isPresent()) {
            if (!this.world.isRemote) {
                passenger.dismountRidingEntity();
            }
            return;
        }
        SeatPart seat = seatOptional.get();
        Vec3d position = seat.getWorldPosition();
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
        this.getSeatOccupiedBy(passenger).ifPresent(this.seatingMap::remove);
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
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.lerpX = x;
        this.lerpY = y;
        this.lerpZ = z;
        this.lerpYaw = yaw;
        this.lerpPitch = pitch;
        this.lerpSteps = 10;
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
        for (Map.Entry<SeatPart, Integer> entry : this.seatingMap.entrySet()) {
            NBTTagCompound seatNbt = new NBTTagCompound();
            seatNbt.setInteger("seat", entry.getKey().getEntityId());
            seatNbt.setInteger("passenger", entry.getValue());
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
            int seat = seatNbt.getInteger("seat");
            int passenger = seatNbt.getInteger("passenger");
            EntityVehiclePart vehiclePart = this.findPartById(seat);
            if (vehiclePart instanceof SeatPart) {
                this.seatingMap.put((SeatPart) vehiclePart, passenger);
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

    public boolean boardVehicle(SeatPart seat, EntityLivingBase entity, EnumHand hand) {
        if (this.canEntityBoardVehicle(seat, entity)) {
            if (!this.world.isRemote) {
                Optional<SeatPart> currentSeat = this.getSeatOccupiedBy(entity);
                currentSeat.ifPresent(currentSeatPart -> {
                    this.seatingMap.remove(currentSeatPart);
                    entity.dismountRidingEntity();
                });
                this.seatingMap.put(seat, entity.getEntityId());
                entity.startRiding(this);
                this.sendClientData();
            }
            return true;
        }
        return false;
    }

    public boolean canEntityBoardVehicle(SeatPart seat, EntityLivingBase entity) {
        if (entity == null)
            return false;
        Integer passengerInSeat = this.seatingMap.get(seat);
        return this.canBeRidden(entity) && this.canFitPassenger(entity) && passengerInSeat == null;
    }

    public final Optional<SeatPart> getSeatOccupiedBy(@Nullable Entity entity) {
        if (entity == null)
            return Optional.empty();
        int id = entity.getEntityId();
        for (Map.Entry<SeatPart, Integer> entry : this.seatingMap.entrySet()) {
            if (entry.getValue() == id) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
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

    public final void setStarted(boolean started) {
        this.dataManager.set(STARTED, started);
    }

    public final boolean isStarted() {
        return this.dataManager.get(STARTED);
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
        this.motionX *= x;
        this.motionY *= y;
        this.motionZ *= z;
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
        if (this.motionY < -0.5)
            return;
        this.motionY -= 0.05;
    }

    protected void applyDrag() {
        this.multiplyMotion(0.95F);
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

    private NBTTagCompound serializeParts() {
        NBTTagCompound tag = new NBTTagCompound();
        for (EntityVehiclePart part : this.getParts()) {
            NBTTagCompound partTag = new NBTTagCompound();
            part.writeEntityToNBT(partTag);
            tag.setTag(part.partName, partTag);
        }
        return tag;
    }

    private void deserializeParts(NBTTagCompound tag) {
        for (EntityVehiclePart part : this.getParts()) {
            part.readEntityFromNBT(tag.getCompoundTag(part.partName));
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
