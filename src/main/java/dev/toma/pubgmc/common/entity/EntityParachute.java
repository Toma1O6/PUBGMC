package dev.toma.pubgmc.common.entity;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.api.event.ParachuteEvent;
import dev.toma.pubgmc.common.entity.controllable.EntityControllable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class EntityParachute extends EntityControllable {

    float turningSpeed;
    int emptyTicks;

    public EntityParachute(World world) {
        super(world);
    }

    public EntityParachute(World world, EntityLivingBase user) {
        this(world);
        this.setPosition(user.posX, user.posY, user.posZ);
        this.setRotation(user.rotationYaw, 0.0F);
        this.motionX = user.motionX;
        this.motionY = user.motionY;
        this.motionZ = user.motionZ;
    }

    @Override
    public double getMountedYOffset() {
        return 0.5D;
    }

    @Override
    public void updatePre() {
        if (!this.hasTurnInput()) {
            float rotationSpeedDrop = 1.0F;
            turningSpeed = Math.abs(turningSpeed - rotationSpeedDrop) <= rotationSpeedDrop ? 0.0F : turningSpeed > 0 ? turningSpeed - rotationSpeedDrop : turningSpeed < 0 ? turningSpeed + rotationSpeedDrop : 0.0F;
        }
        if (!this.hasMovementInput()) {
            rotationPitch = Math.abs(rotationPitch - 1.0F) <= 1.0F ? 0.0F : rotationPitch > 0 ? rotationPitch - 1.0F : rotationPitch < 0 ? rotationPitch + 1.0F : 0.0F;
        }
        if (!this.isBeingRidden()) {
            if (++emptyTicks >= 100) {
                setDead();
            }
        }
        this.rotationYaw += turningSpeed;
        if (isBeingRidden()) {
            if (onGround || collided) {
                Entity passenger = getControllingPassenger();
                if (passenger instanceof EntityLivingBase) {
                    MinecraftForge.EVENT_BUS.post(new ParachuteEvent.Land(this, (EntityLivingBase) passenger));
                }
                removePassengers();
                return;
            }
            if (!isDeployed())
                return;
            this.fallDistance = 0.0F;
            Vec3d look = this.getLookVec();
            double x = look.x / 2;
            double z = look.z / 2;
            double speed = 1 + rotationPitch / 30.0F;
            this.motionX = x;
            this.motionY = -0.25 * speed;
            this.motionZ = z;
        } else {
            motionX = 0;
            motionY = 0;
            motionZ = 0;
        }
    }

    @Override
    protected void removePassenger(Entity passenger) {
        if (passenger instanceof EntityLivingBase) {
            MinecraftForge.EVENT_BUS.post(new ParachuteEvent.Land(this, (EntityLivingBase) passenger));
        }
        super.removePassenger(passenger);
    }

    @Override
    public void updatePost() {
        move(MoverType.SELF, motionX, motionY, motionZ);
        if (!isBeingRidden()) {
            reset();
        }
    }

    @Override
    public void handleForward() {
        if (isDeployed()) rotationPitch = DevUtil.wrap(rotationPitch + 1.5F, -15.0F, 30.0F);
    }

    @Override
    public void handleBackward() {
        if (isDeployed()) rotationPitch = DevUtil.wrap(rotationPitch - 1F, -15.0F, 30.0F);
    }

    @Override
    public void handleRight() {
        if (isDeployed()) turningSpeed = DevUtil.wrap(turningSpeed + 1F, -5F, 5F);
    }

    @Override
    public void handleLeft() {
        if (isDeployed()) turningSpeed = DevUtil.wrap(turningSpeed - 1F, -5F, +5F);
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return distance < 256.0D;
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
    }

    boolean isDeployed() {
        return ticksExisted > 20;
    }

    public int getEmptyTicks() {
        return emptyTicks;
    }
}
