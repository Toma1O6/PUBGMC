package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.api.entity.CustomProjectileBoundingBoxProvider;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public class EntityVehiclePart extends MultiPartEntityPart implements CustomProjectileBoundingBoxProvider {

    private final Vec3d relativePosition;
    private float damageMultiplier = 1.0F;

    public EntityVehiclePart(EntityDriveable parent, String name, float width, float height, Vec3d relativePosition) {
        super(parent, name, width, height);
        this.relativePosition = relativePosition;
    }

    public Vec3d getRelativePosition() {
        return this.relativePosition;
    }

    public Vec3d getWorldPosition() {
        EntityDriveable parent = (EntityDriveable) this.parent;
        return parent.getPositionVector().add(this.relativePosition.rotateYaw(-parent.rotationYaw * (float) (Math.PI / 180.0F)));
    }

    @Override
    public void onUpdate() {
        this.onEntityUpdate();
        Vec3d worldPosition = this.getWorldPosition();
        this.setPosition(worldPosition.x, worldPosition.y, worldPosition.z);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox() {
        return this.getEntityBoundingBox();
    }

    @Nullable
    @Override
    public AxisAlignedBB getBoundingBoxForProjectiles() {
        return this.getCollisionBoundingBox();
    }

    public void setDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    protected float getDamageMultiplier(DamageSource source) {
        return this.damageMultiplier;
    }

    protected boolean canHurtVehicle(DamageSource source, float damage) {
        return true;
    }

    protected void hurt(DamageSource source, float damage) {
    }

    protected boolean isDestroyed() {
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] - Part name: " + this.partName + ", EntityID: " + this.getEntityId();
    }
}
