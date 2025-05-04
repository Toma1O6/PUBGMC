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
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.*;
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

public abstract class EntityDriveable extends Entity implements IControllable, IEntityAdditionalSpawnData, GameObject, SynchronizableEntity, CustomEntityNametag, IEntityMultiPart, CustomProjectileBoundingBoxProvider {

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

    @Override
    protected void entityInit() {
        this.dataManager.register(HEALTH, this.getMaxHealth());
    }

    private static void validate(EntityDriveable vehicle, List<SeatPart> seats) {
        long driverPositions = seats.stream().filter(SeatPart::isDriver).count();
        if (driverPositions != 1) {
            throw new IllegalArgumentException("Vehicle must have exactly one driver, but found " + driverPositions + " on vehicle of type " + vehicle.getClass().getSimpleName());
        }
    }

    public abstract EntityVehiclePart getMainBodyPart();

    public abstract void registerVehicleParts(PartRegistration registration);

    public abstract float getMaxHealth();

    public final float getHealthPercentage() {
        return getHealth() / getMaxHealth();
    }

    public abstract float getFuelTankCapacity();

    public abstract VehicleCategory getVehicleCategory();

    @Override
    public void onUpdate() {
        this.onEntityUpdate(); // backup motion, auto update vehicle parts position
        this.inputTick();
        this.runVehicleTick();
        this.applyDrag();
        this.getSoundController().update();
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }

    @Override
    public void onEntityUpdate() {
        backupMotion();
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

    protected void inputTick() {
        Entity controller = this.getControllingPassenger();
        if (controller == null || !controller.isEntityAlive() || controllerInput == 0) {
            this.controllerInput = 0;
            this.handleEmptyInputUpdate();
            return;
        }
        this.handleInputUpdate();
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

    protected void handleEmptyInputUpdate() {
    }

    protected void handleInputUpdate() {
    }

    public abstract void runVehicleTick();

    protected void applyDrag() {
        this.applyGravity();
    }

    protected void applyGravity() {
        if (this.hasNoGravity())
            return;
        if (this.onGround) {
            this.motionY = Math.max(this.motionY, 0.0);
        } else {
            this.motionY *= 0.98F;
            this.motionY -= 0.08;
        }
    }

    public abstract VehicleSoundController getSoundController();

    @Override
    public void move(MoverType type, double desiredX, double desiredY, double desiredZ) {
        // Minecraft default move
        if (this.noClip) {
            this.setEntityBoundingBox(super.getEntityBoundingBox().offset(desiredX, desiredY, desiredZ));
            this.resetPositionToBB();
            return;
        }
        if (type == MoverType.PISTON) {
            super.move(MoverType.PISTON, desiredX, desiredY, desiredZ);
            return;
        }

//        d2:desiredX
//        d3:desiredY
//        d4:desiredZ
//        x:movedX
//        y:movedY
//        z:movedZ
//        d10:posX
//        d11:posY
//        d1:posZ
//        list1:nearCollisionBoxes1
//        d14:preStepX
//        d6:preStepY
//        d7:preStepZ
//        axisalignedbb:originalBB
//        axisalignedbb1:beforeStepBB
//        axisalignedbb2:stepPath1BB
//        axisalignedbb3:horizontalExpandedAABB
//        axisalignedbb4:stepPath2BB
//        d8:stepYOffset1
//        d18:stepX1
//        d19:stepPath1Z
//        d20:stepYOffset2
//        list1:initialCollisions
//        list:stepCollisions
//        d21:stepX2
//        d22:stepPath2Z
//        d23:stepPath1SqDist
//        d9:stepPath2SqDist

//        // the center of green box is different with white box, apply the offset calculated with the green box to the white box will cause entity to keep moving
//        // during calculation assumes the green is the original white box, and finally offset back
//        Vec3d w2gOff = getWhiteBoxOffsetToGreen();

        double movedX = desiredX, movedY = desiredY, movedZ = desiredZ;
        List<AxisAlignedBB> initialCollisions = this.world.getCollisionBoxes(this, this.getEntityBoundingBox().expand(desiredX, desiredY, desiredZ));
        AxisAlignedBB originalBB = this.getEntityBoundingBox(); // backup

        // vehicle movement ignores webs

        // minimum move without collide
        if (movedY != 0.0D) {
            for (AxisAlignedBB other : initialCollisions) {
                movedY = other.calculateYOffset(this.getEntityBoundingBox(), movedY);
            }
            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0D, movedY, 0.0D));
        }
        if (movedX != 0.0D) {
            for (AxisAlignedBB other : initialCollisions) {
                movedX = other.calculateXOffset(this.getEntityBoundingBox(), movedX);
            }
            if (movedX != 0.0D) {
                this.setEntityBoundingBox(this.getEntityBoundingBox().offset(movedX, 0.0D, 0.0D));
            }
        }
        if (movedZ != 0.0D) {
            for (AxisAlignedBB other : initialCollisions) {
                movedZ = other.calculateZOffset(this.getEntityBoundingBox(), movedZ);
            }
            if (movedZ != 0.0D) {
                this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0D, 0.0D, movedZ));
            }
        }

        // step up
        float stepHeight = getStepHeight();
        if (stepHeight > 0.0F
                && (this.onGround || desiredY != movedY && desiredY <= 0.0D)
                && (desiredX != movedX || desiredZ != movedZ)) {
            double preStepX = movedX, prevMovedY = movedY, prevMovedZ = movedZ;
            AxisAlignedBB beforeStepBB = this.getEntityBoundingBox();
            this.setEntityBoundingBox(originalBB);
            movedY = stepHeight; // what does casting this to "(double) stepHeight" mean in super.move()?
            List<AxisAlignedBB> stepCollisions = this.world.getCollisionBoxes(this, this.getEntityBoundingBox().expand(desiredX, movedY, desiredZ));
            AxisAlignedBB stepPath1BB = this.getEntityBoundingBox();
            AxisAlignedBB horizontalExpandedAABB = stepPath1BB.expand(desiredX, 0.0D, desiredZ);

            // first: Y -> X -> Z
            // y
            double stepYOffset1 = movedY;
            for (AxisAlignedBB other : stepCollisions) {
                stepYOffset1 = other.calculateYOffset(horizontalExpandedAABB, stepYOffset1);
            }
            stepPath1BB = stepPath1BB.offset(0.0D, stepYOffset1, 0.0D);
            // x
            double stepX1 = desiredX;
            for (AxisAlignedBB other : stepCollisions) {
                stepX1 = other.calculateXOffset(stepPath1BB, stepX1);
            }
            stepPath1BB = stepPath1BB.offset(stepX1, 0.0D, 0.0D);
            // z
            double stepPath1Z = desiredZ;
            for (AxisAlignedBB other : stepCollisions) {
                stepPath1Z = other.calculateZOffset(stepPath1BB, stepPath1Z);
            }
            stepPath1BB = stepPath1BB.offset(0.0D, 0.0D, stepPath1Z);

            // second: Y -> XZ
            // y
            AxisAlignedBB stepPath2BB = this.getEntityBoundingBox();
            double stepYOffset2 = movedY;
            for (AxisAlignedBB other : stepCollisions) {
                stepYOffset2 = other.calculateYOffset(stepPath2BB, stepYOffset2);
            }
            stepPath2BB = stepPath2BB.offset(0.0D, stepYOffset2, 0.0D);
            // x
            double stepX2 = desiredX;
            for (AxisAlignedBB other : stepCollisions) {
                stepX2 = other.calculateXOffset(stepPath2BB, stepX2);
            }
            stepPath2BB = stepPath2BB.offset(stepX2, 0.0D, 0.0D);
            // z
            double stepPath2Z = desiredZ;
            for (AxisAlignedBB other : stepCollisions) {
                stepPath2Z = other.calculateZOffset(stepPath2BB, stepPath2Z);
            }
            stepPath2BB = stepPath2BB.offset(0.0D, 0.0D, stepPath2Z);

            double stepPath1SqDist = stepX1 * stepX1 + stepPath1Z * stepPath1Z;
            double stepPath2SqDist = stepX2 * stepX2 + stepPath2Z * stepPath2Z;
            if (stepPath1SqDist > stepPath2SqDist) {
                movedX = stepX1;
                movedZ = stepPath1Z;
                movedY = -stepYOffset1;
                this.setEntityBoundingBox(stepPath1BB);
            } else {
                movedX = stepX2;
                movedZ = stepPath2Z;
                movedY = -stepYOffset2;
                this.setEntityBoundingBox(stepPath2BB);
            }

            for (AxisAlignedBB other : stepCollisions) {
                movedY = other.calculateYOffset(this.getEntityBoundingBox(), movedY);
            }
            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0D, movedY, 0.0D));

            if (preStepX * preStepX + prevMovedZ * prevMovedZ >= movedX * movedX + movedZ * movedZ) {
                movedX = preStepX;
                movedY = prevMovedY;
                movedZ = prevMovedZ;
                this.setEntityBoundingBox(beforeStepBB);
            }
        }
        this.world.profiler.endSection();

        // rest
        this.world.profiler.startSection("rest");
        this.resetPositionToBB();
        this.collidedHorizontally = desiredX != movedX || desiredZ != movedZ;
        this.collidedVertically = desiredY != movedY;
        this.onGround = this.collidedVertically && desiredY < 0.0D;
        this.collided = this.collidedHorizontally || this.collidedVertically;
        BlockPos blockPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), MathHelper.floor(this.posZ));
        IBlockState blockState = this.world.getBlockState(blockPos);
        if (blockState.getMaterial() == Material.AIR) {
            BlockPos belowPos = blockPos.down();
            IBlockState belowState = this.world.getBlockState(belowPos);
            Block belowBlock = belowState.getBlock();
            if (belowBlock instanceof BlockFence || belowBlock instanceof BlockWall || belowBlock instanceof BlockFenceGate) {
                blockState = belowState;
                blockPos = belowPos;
            }
        }

        // it's common to fall 4 blocks in vehicle, and it's annoying to take fall damage
        this.fallDistance = 0.0F;

        if (desiredX != movedX) {
            this.motionX = 0.0D;
        }
        if (desiredZ != movedZ) {
            this.motionZ = 0.0D;
        }
        Block block = blockState.getBlock();
        if (desiredY != movedY) {
            block.onLanded(this.world, this);
        }

        // this.canTriggerWalking() -> false, skip this part in super.move()

        // the same block collision in super.move()
        try {
            this.doBlockCollisions();
        } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.makeCrashReport(throwable, "Checking driveable entity block collision");
            CrashReportCategory crashReportCategory = crashReport.makeCategory("Driveable entity being checked for collision");
            this.addEntityCrashInfo(crashReportCategory);
            throw new ReportedException(crashReport);
        }

        // water related, some private variable occurred (handle submerge in other places)

        // offest back in the end

        this.world.profiler.endSection();
    }

//    @Override
//    public AxisAlignedBB getEntityBoundingBox() {
//        return getColliderBox();
//    }

    public AxisAlignedBB getEntityBoundingBox(Vec3d off) {
        return getColliderBox().offset(off);
    }

    protected AxisAlignedBB getColliderBox() { // return the minimum union green collider box
        AxisAlignedBB box = null;
        for (EntityVehiclePart part : this.getParts()) {
            if (part != null && part.getBoundingBoxMode() == EntityVehiclePart.BoundingBoxMode.COLLIDER) {
                AxisAlignedBB expanded = part.getEntityBoundingBox();
                box = (box == null) ? expanded : box.union(expanded);
            }
        }
        if (box == null) {
            Pubgmc.logger.warn("In EntityDriveable.getColliderBox() return a null collider box");
            return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        } else {
            return box;
        }
    }

    @Nullable
    protected List<AxisAlignedBB> getColliderBoxes() {
        List<AxisAlignedBB> boxes = new ArrayList<>(Collections.emptyList());
        for (EntityVehiclePart part : this.getParts()) {
            if (part != null && part.getBoundingBoxMode() == EntityVehiclePart.BoundingBoxMode.COLLIDER) {
                boxes.add(part.getCollisionBoundingBox());
            }
        }
        return boxes;
    }

    protected Vec3d getWhiteBoxOffsetToGreen() {
        AxisAlignedBB whiteBox = super.getEntityBoundingBox();
        AxisAlignedBB greenBox = getColliderBox();
        Vec3d whiteCenter = new Vec3d((whiteBox.minX + whiteBox.maxX) / 2, (whiteBox.minY + whiteBox.maxY) / 2, (whiteBox.minZ + whiteBox.maxZ) / 2);
        Vec3d greenCenter = new Vec3d((greenBox.minX + greenBox.maxX) / 2, (greenBox.minY + greenBox.maxY) / 2, (greenBox.minZ + greenBox.maxZ) / 2);
        return greenCenter.subtract(whiteCenter);
    }


    public abstract float getStepHeight();

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

    @Override
    public boolean canFitPassenger(Entity passenger) {
        int currentPassengers = this.seatingMap.size();
        return this.isPassenger(passenger) || currentPassengers < this.seatingCapacity;
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
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (this.world.isRemote)
            return;
        SeatPart seat = this.seatingMap.remove(passenger.getEntityId());
        if (seat != null) {
            Vec3d dismountPosition = seat.getDismountPosition();
            GameHelper.teleport(passenger, dismountPosition.x, dismountPosition.y, dismountPosition.z);
            float damage = calculateDismountDamage();
            passenger.fallDistance = damage == 0 ? 0 : 3 + damage;
        }
        this.sendClientData();
    }

    public float calculateDismountDamage() {
        float damage = 0;
        float horSpeed = (float) Math.sqrt(getMotionSqr());
        if (horSpeed > 0.41667F) { // 30 km/h
            horSpeed -= 0.41667F;
            damage =  20 * (float) (Math.pow(2, horSpeed) / Math.pow(2, 0.41667F));
        }
        return damage;
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
        if (attacker != null && this.isPassenger(attacker)) {
            return false;
        }
        EntityVehiclePart body = this.getMainBodyPart();
        if (body == null) {
            return false;
        }
        return this.attackEntityFromPart(body, source, amount);
    }

    @Override
    public boolean attackEntityFromPart(@Nullable MultiPartEntityPart part, DamageSource source, float amount) {
        if (!(part instanceof EntityVehiclePart)) {
            return false;
        }
        if (this.isEntityInvulnerable(source)) {
            return false;
        }
        Entity attacker = source.getTrueSource();
        if (attacker != null && this.isPassenger(attacker)) {
            return false;
        }
        EntityVehiclePart vehiclePart = (EntityVehiclePart) part;
        float damageMultiplier = vehiclePart.getDamageMultiplier(source);
        float damage = amount * damageMultiplier;
        if (vehiclePart.canHurtVehicle(source, damage) && this.handleEntityAttack(source, damage)) {
            vehiclePart.hurt(source, damage);
            return true;
        }
        return false;
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
    public boolean isInRangeToRender3d(double x, double y, double z) {
        return true;
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
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
    public boolean canRiderInteract() {
        return true;
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

    protected final void setMotionAndUpdate(Vec3d motion) {
        setMotionAndUpdate(motion.x, motion.y, motion.z);
    }

    protected final void setMotionAndUpdate(double x, double y, double z) {
        backupMotion();
        setMotion(x, y, z);
    }

    protected final void setMotion(Vec3d motion) {
        setMotion(motion.x, motion.y, motion.z);
    }

    protected final void setMotion(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
    }

    protected final void backupMotion() {
        this.lastMotionX = this.motionX;
        this.lastMotionY = this.motionY;
        this.lastMotionZ = this.motionZ;
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

    protected float getMaxPassengerRotationAngleDegrees() {
        return 115.0F;
    }

    protected boolean handleEntityAttack(float amount) {
        this.removeHealth(amount);
        return true;
    }

    protected boolean handleEntityAttack(@Nullable DamageSource source, float amount) {
        return handleEntityAttack(amount);
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

    protected void resetCollide() {
        this.collided = false;
        this.collidedHorizontally = false;
        this.collidedVertically = false;
    }

    protected void collidedXZ() {
        this.collided = true;
        this.collidedHorizontally = true;
    }

    protected void collidedX() {
        this.motionX = 0;
        this.collidedXZ();
    }

    protected void collidedY() {
        this.motionY = 0;
        this.collided = true;
        this.collidedVertically = true;
    }

    protected void collidedZ() {
        this.motionZ = 0;
        this.collidedXZ();
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setFloat("health", this.getHealth());
        compound.setTag("parts", this.serializeParts());
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

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        this.setHealth(compound.getFloat("health"));
        this.deserializeParts(compound.getCompoundTag("parts"));
    }

    private void deserializeParts(NBTTagCompound tag) {
        for (EntityVehiclePart part : this.getParts()) {
            if (!part.hasCustomSaveData())
                continue;
            part.loadPartData(tag.getCompoundTag(part.partName));
        }
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

    @Nullable
    public AxisAlignedBB getBoundingBoxForProjectiles() {
        // this is needed, otherwise the white collision box will take damage
        return null;
    }
}
