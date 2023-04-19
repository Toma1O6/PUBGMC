package dev.toma.pubgmc.common.entity;

import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.util.Objects;
import java.util.UUID;

public class EntityPlane extends Entity implements GameObject, IEntityAdditionalSpawnData {

    public static final int PLANE_CAPACITY = 32;
    private int flightDelay = 20;
    private int flightHeight = 256;
    private Position2 from = Position2.ORIGIN;
    private Position2 to = Position2.ORIGIN;
    private float movementSpeed;
    private float movementSpeedMultiplier;
    private UUID gameId = GameHelper.DEFAULT_UUID;

    public EntityPlane(World world) {
        super(world);
        setSize(15f, 5f);
        this.ignoreFrustumCheck = true;
    }

    public void board(EntityPlayer player) {
        player.setPositionAndUpdate(posX, posY, posZ);
        player.startRiding(this, true);
    }

    public void setPath(Position2 from, Position2 to) {
        this.from = Objects.requireNonNull(from);
        this.to = Objects.requireNonNull(to);
        recalculatePosition();
        updateHeading();
    }

    public void setFlightDelay(int flightDelay) {
        this.flightDelay = flightDelay;
    }

    public void setFlightHeight(int flightHeight) {
        this.flightHeight = flightHeight;
        recalculatePosition();
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setMovementSpeedMultiplier(float movementSpeedMultiplier) {
        this.movementSpeedMultiplier = movementSpeedMultiplier;
    }

    @Override
    public double getMountedYOffset() {
        return 2.3;
    }

    @Override
    protected boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < PLANE_CAPACITY;
    }

    @Override
    public void updatePassenger(Entity passenger) {
        if (this.isPassenger(passenger)) {
            if (!this.getPassengers().isEmpty()) {
                float f1 = (float) ((this.isDead ? 0.01D : this.getMountedYOffset()) + passenger.getYOffset());
                int id = this.getPassengers().indexOf(passenger);
                float x = id >= 16 ? -6 + (id - 16) * 3 : -6 + id * 3;
                float z = id < 16 ? 3 : -3;
                Vec3d vec3d = (new Vec3d(x, 0.0D, z)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
                passenger.setPosition(this.posX + vec3d.x, this.posY + (double) f1, this.posZ + vec3d.z);
            }
        }
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return true;
    }

    @Override
    public boolean isInRangeToRender3d(double x, double y, double z) {
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        double currentDistance = getDestinationDistanceSqr();
        if (currentDistance <= 16.0F) {
            if (isBeingRidden()) {
                removePassengers();
            }
            setDead();
        }

        if (ticksExisted % 20L == 0) {
            GameHelper.validateGameEntityStillValid(this);
        }

        if (flightDelay <= 0) {
            updateHeading();
            Vec3d look = getLookVec();
            motionX = look.x * movementSpeed * movementSpeedMultiplier;
            motionZ = look.z * movementSpeed * movementSpeedMultiplier;

            move(MoverType.SELF, motionX, motionY, motionZ);
        } else {
            --flightDelay;
        }
    }

    @Override
    protected void entityInit() {
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
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("flightDelay", flightDelay);
        compound.setInteger("flightHeight", flightHeight);
        compound.setTag("fromDest", from.toNbt());
        compound.setTag("toDest", to.toNbt());
        compound.setFloat("flightMovementSpeed", movementSpeed);
        compound.setFloat("flightMovementSpeedMultiplier", movementSpeedMultiplier);
        compound.setUniqueId("gameId", gameId);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        flightDelay = compound.getInteger("flightDelay");
        flightHeight = compound.getInteger("flightHeight");
        from = new Position2(compound.getCompoundTag("fromDest"));
        to = new Position2(compound.getCompoundTag("toDest"));
        movementSpeed = compound.getFloat("flightMovementSpeed");
        movementSpeedMultiplier = compound.getFloat("flightMovementSpeedMultiplier");
        gameId = compound.getUniqueId("gameId");
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        NBTTagCompound extraData = new NBTTagCompound();
        writeEntityToNBT(extraData);
        ByteBufUtils.writeTag(buffer, extraData);
    }

    @Override
    public void readSpawnData(ByteBuf buffer) {
        NBTTagCompound extraData = ByteBufUtils.readTag(buffer);
        readEntityFromNBT(extraData);
    }

    private void recalculatePosition() {
        setPosition(from.getX(), flightHeight, from.getZ());
    }

    private double getDestinationDistanceSqr() {
        double x = to.getX() - posX;
        double z = to.getZ() - posZ;
        return x * x + z * z;
    }

    private void updateHeading() {
        double a = PUBGMCUtil.getAngleBetween2Points(posX, posZ, to.getX(), to.getZ());
        this.rotationYaw = this.rotationYaw + (float) MathHelper.wrapDegrees(a - rotationYaw);
        this.prevRotationYaw = this.rotationYaw;
    }
}
