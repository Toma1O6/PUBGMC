package dev.toma.pubgmc.common.entity;

import dev.toma.pubgmc.api.game.playzone.PlayzoneDeliveryVehicle;
import dev.toma.pubgmc.api.util.Position2;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.util.Objects;
import java.util.UUID;

public class EntityPlane extends Entity implements PlayzoneDeliveryVehicle, IEntityAdditionalSpawnData {

    public static final int PLANE_CAPACITY = 128;
    private int flightDelay = 20;
    private int flightHeight = 256;
    private Position2 from = Position2.ORIGIN;
    private Position2 to = Position2.ORIGIN;
    private float speed = 1.0f;
    private float speedMultipler = 1.0f;
    private float flightSpeed = 1.0f;
    private boolean preparedHeading = false;
    private boolean preparedMotion = false;
    private UUID gameId = GameHelper.DEFAULT_UUID;

    public EntityPlane(World world) {
        super(world);
        setSize(15f, 5f);
        this.ignoreFrustumCheck = true;
    }

    public void setPath(Position2 from, Position2 to) {
        this.from = Objects.requireNonNull(from);
        this.to = Objects.requireNonNull(to);
        recalculatePosition();
        updateHeading();
        preparedHeading = true;
    }

    public void setFlightDelay(int flightDelay) {
        this.flightDelay = flightDelay;
    }

    public void setFlightHeight(int flightHeight) {
        this.flightHeight = flightHeight;
        recalculatePosition();
    }

    public void setSpeed(float planeSpeed) {
        this.speed = planeSpeed;
        this.flightSpeed = this.speed * this.speedMultipler;
    }
    public void setSpeedMultipler(float multipler) {
        this.speedMultipler = multipler;
        this.flightSpeed = this.speed * this.speedMultipler;
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
                id %= 32; //If this happens, sit on the same seat better than can't sit
                float x = -6, z = 0;
                x += (int)(id / 2) * 3;
                z += id % 2 == 0 ? 3 : -3;
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
            if (!isBeingRidden()) {
                setDead();
            }
        }

        if (flightDelay <= 0) {
            if (!preparedMotion) {
                Vec3d look = getLookVec();
                motionX = look.x * flightSpeed;
                motionZ = look.z * flightSpeed;
                preparedMotion = true;
            }
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
    public double getCameraOffset() {
        return 60.0D;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("flightDelay", flightDelay);
        compound.setInteger("flightHeight", flightHeight);
        compound.setTag("fromDest", from.toNbt());
        compound.setTag("toDest", to.toNbt());
        compound.setFloat("flightSpeed", flightSpeed);
        compound.setUniqueId("gameId", gameId);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        flightDelay = compound.getInteger("flightDelay");
        flightHeight = compound.getInteger("flightHeight");
        from = new Position2(compound.getCompoundTag("fromDest"));
        to = new Position2(compound.getCompoundTag("toDest"));
        flightSpeed = compound.getFloat("flightSpeed");
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
