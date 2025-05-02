package dev.toma.pubgmc.common.entity.vehicles;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.client.game.CustomEntityNametag;
import dev.toma.pubgmc.api.entity.IControllable;
import dev.toma.pubgmc.api.entity.SynchronizableEntity;
import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.common.entity.vehicles.util.SeatPart;
import dev.toma.pubgmc.common.entity.vehicles.util.VehicleCategory;
import dev.toma.pubgmc.common.entity.vehicles.util.VehicleSoundController;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_PacketSendEntityData;
import dev.toma.pubgmc.util.helper.GameHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
import java.util.*;
import java.util.stream.Collectors;

public abstract class EntityDriveable extends Entity implements IControllable, IEntityAdditionalSpawnData, GameObject, SynchronizableEntity, CustomEntityNametag, IEntityMultiPart {

    // Keybinds
    public static final int KEY_FORWARD = 0b1;
    public static final int KEY_BACK = 0b10;
    public static final int KEY_RIGHT = 0b100;
    public static final int KEY_LEFT = 0b1000;

    // Synhronized data parameters
    protected static final DataParameter<Float> HEALTH = EntityDataManager.createKey(EntityDriveable.class, DataSerializers.FLOAT);

    // speed smoothing
    protected double lastMotionX, lastMotionY, lastMotionZ;
    // input
    protected byte controllerInput;
    // game compatibility
    private UUID gameId = GameHelper.DEFAULT_UUID;
    // passengers
    private final int seatingCapacity;
    private final BiMap<Integer, SeatPart> seatingMap = HashBiMap.create();
    private final SeatPart driverSeat;
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

    public final float getHealthPercentage() {
        return getHealth() / getMaxHealth();
    }

    public abstract float getFuelTankCapacity();

    public abstract VehicleCategory getVehicleCategory();

    public abstract void runVehicleTick();

    public abstract VehicleSoundController getSoundController();

    @Override
    public void onUpdate() {
        this.onEntityUpdate(); // backup motion, update vehicle parts
        this.inputTick();
        this.runVehicleTick();
        this.applyGravity();
        this.getSoundController().update();
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
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setFloat("health", this.getHealth());
        compound.setTag("parts", this.serializeParts());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        this.setHealth(compound.getFloat("health"));
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
        if (seat != null) {
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

    protected void resetCollide() {
        this.collided = false;
        this.collidedHorizontally = false;
        this.collidedVertically = false;
    }

    @Override
    public void move(MoverType type, double x, double y, double z) {
        switch (type) {
            case PISTON:
                this.setEntityBoundingBox(this.getEntityBoundingBox().offset(x, y, z));
                this.resetPositionToBB();
                break;
            case SELF:
            case PLAYER:
            default:
                moveMultipart(x, y, z);
                break;
        }
    }

    public void moveMultipart(double x, double y, double z) {
        this.world.profiler.startSection("moveMultipart");

        AxisAlignedBB originalBB = this.getEntityBoundingBox();

        AxisAlignedBB queryBB = null;
        for (EntityVehiclePart part : this.getParts()) {
            if (part != null && part.getBoundingBoxMode() == EntityVehiclePart.BoundingBoxMode.COLLIDER) {
                AxisAlignedBB expanded = part.getEntityBoundingBox().expand(x, y, z);
                queryBB = (queryBB == null) ? expanded : queryBB.union(expanded);
            }
        }

        List<AxisAlignedBB> collisionBoxes = (queryBB != null)
                ? this.world.getCollisionBoxes(this, queryBB)
                : Collections.emptyList();

        // Try normal movement
        Vec3d normalMotion = calculateAxisOffset(x, y, z, collisionBoxes);

        // Try step-up
        Vec3d finalMotion = normalMotion;
        if ((x != normalMotion.x || z != normalMotion.z) && getStepHeight() > 0.0F) {
            Vec3d steppedMotion = tryStepUp(x, y, z, collisionBoxes);
            if (steppedMotion.squareDistanceTo(0, 0, 0) > finalMotion.squareDistanceTo(0, 0, 0)) {
                finalMotion = steppedMotion;
            }
        }

        // Apply motion
        if (finalMotion.lengthSquared() > 0.0) {
            this.setEntityBoundingBox(originalBB.offset(finalMotion.x, finalMotion.y, finalMotion.z));
        }

        this.resetPositionToBB();

        this.collidedHorizontally = x != finalMotion.x || z != finalMotion.z;
        this.collidedVertically = y != finalMotion.y;
        this.onGround = this.collidedVertically && y < 0.0;

        resetCollide();
        if (x != finalMotion.x) this.collidedX();
        if (y != finalMotion.y) this.collidedY();
        if (z != finalMotion.z) this.collidedZ();

        if (this.onGround) {
            final double STICK_TO_GROUND = -0.05D;
            AxisAlignedBB bb = this.getEntityBoundingBox();
            AxisAlignedBB downBB = bb.offset(0.0, STICK_TO_GROUND, 0.0);

            boolean canLower = true;
            for (AxisAlignedBB box : collisionBoxes) {
                if (box.intersects(downBB)) {
                    canLower = false;
                    break;
                }
            }

            if (canLower) {
                this.setEntityBoundingBox(downBB);
                this.resetPositionToBB();
            }
        }

        this.world.profiler.endSection();
    }

    protected Vec3d calculateAxisOffset(double x, double y, double z, List<AxisAlignedBB> collisionBoxes) {
        double xOffset = x;
        double yOffset = y;
        double zOffset = z;

        EntityVehiclePart[] parts = this.getParts();
        if (parts == null) return new Vec3d(xOffset, yOffset, zOffset);

        for (EntityVehiclePart part : parts) {
            if (part == null || part.getBoundingBoxMode() != EntityVehiclePart.BoundingBoxMode.COLLIDER)
                continue;

            AxisAlignedBB bb = part.getEntityBoundingBox();

            if (yOffset != 0.0) {
                for (AxisAlignedBB box : collisionBoxes) {
                    yOffset = box.calculateYOffset(bb, yOffset);
                }
                bb = bb.offset(0.0, yOffset, 0.0);
            }
            if (xOffset != 0.0) {
                for (AxisAlignedBB box : collisionBoxes) {
                    xOffset = box.calculateXOffset(bb, xOffset);
                }
                bb = bb.offset(xOffset, 0.0, 0.0);
            }
            if (zOffset != 0.0) {
                for (AxisAlignedBB box : collisionBoxes) {
                    zOffset = box.calculateZOffset(bb, zOffset);
                }
                bb = bb.offset(0.0, 0.0, zOffset);
            }
        }

        return new Vec3d(xOffset, yOffset, zOffset);
    }

    protected Vec3d tryStepUp(double x, double y, double z, List<AxisAlignedBB> collisionBoxes) {
        double stepHeight = getStepHeight();
        if (stepHeight <= 0) return Vec3d.ZERO;

        Vec3d offset = calculateAxisOffset(x, stepHeight, z, collisionBoxes);
        if (offset.y > 0.0 && (offset.x != 0.0 || offset.z != 0.0)) {
            return offset;
        }
        return Vec3d.ZERO;
    }

    protected abstract float getStepHeight();

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

    public final double getSpeedPerTick() {
        return Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
    }

    public final double getLastSpeedPerTick() {
        return Math.sqrt(lastMotionX * lastMotionX + lastMotionY * lastMotionY + lastMotionZ * lastMotionZ);
    }

    public final double getMotionSqr() {
        return this.motionX * this.motionX + this.motionZ * this.motionZ;
    }

    public final double getLastMotionSqr() {
        return this.lastMotionX * this.lastMotionX + this.lastMotionZ * this.lastMotionZ;
    }

    public final boolean hasInput(int value) {
        return (this.controllerInput & value) == value;
    }

    public final double getSmoothMotionSqr(float partialTicks) {
        double current = this.getMotionSqr();
        double last = this.getLastMotionSqr();
        return last + (current - last) * partialTicks;
    }

    public final void setMotionAndUpdate(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        this.lastMotionX = x;
        this.lastMotionY = y;
        this.lastMotionZ = z;
    }

    public final void setMotion(Vec3d motion) {
        this.motionX = motion.x;
        this.motionY = motion.y;
        this.motionZ = motion.z;
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

    protected void applyGravity() {
        if (this.hasNoGravity())
            return;
        if (this.onGround)
            this.motionY = Math.max(this.motionY, 0.0); // TODO tiny bounce
        if (!onGround)
            this.motionY *= 0.98F;
        this.motionY -= 0.08;
    }

    protected float getMaxPassengerRotationAngleDegrees() {
        return 115.0F;
    }

    protected boolean handleEntityAttack(DamageSource source, float amount) {
        this.removeHealth(amount);
        return true;
    }



    protected void handleEmptyInputUpdate() {
    }

    protected void collidedXZ() {
        this.collidedHorizontally = true;
    }

    protected void collidedX() {
        this.motionX = 0;
        this.collidedXZ();
    }

    protected void collidedY() {
        this.motionY = 0;
        this.collidedVertically = true;
    }

    protected void collidedZ() {
        this.motionZ = 0;
        this.collidedXZ();
    }

    private void inputTick() {
        Entity controller = this.getControllingPassenger();
        if (controller == null || !controller.isEntityAlive()) {
            this.controllerInput = 0;
            this.handleEmptyInputUpdate();
            return;
        }
        this.handleInputUpdate();
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

    public boolean isSubmergedInWater() {
        int top = MathHelper.floor(this.posY + this.height);
        IBlockState state = this.world.getBlockState(new BlockPos(this.posX, top, this.posZ));
        return state.getMaterial().isLiquid();
    }

    public boolean isMovingForward() {
        Vec3d lookVec = this.getLookVec();
        double horizontalDotProduct  = this.motionX * lookVec.x + this.motionZ * lookVec.z;
        return horizontalDotProduct > 0.001;
    }
}
