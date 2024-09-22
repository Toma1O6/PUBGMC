package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.api.entity.CustomProjectileBoundingBoxProvider;
import dev.toma.pubgmc.api.entity.ParentEntityAccess;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public class EntityVehiclePart extends MultiPartEntityPart implements CustomProjectileBoundingBoxProvider {

    public final ParentEntityAccess<EntityDriveable> access;
    private final Vec3d relativePosition;
    private float damageMultiplier = 1.0F;
    private BoundingBoxMode boundingBoxMode = BoundingBoxMode.COLLIDER;

    public EntityVehiclePart(EntityDriveable parent, String name, float width, float height, Vec3d relativePosition) {
        super(parent, name, width, height);
        this.access = createAccessor(parent);
        this.relativePosition = relativePosition;
    }

    public Vec3d getRelativePosition() {
        return this.relativePosition;
    }

    public Vec3d getWorldPosition() {
        EntityDriveable parent = this.access.getParentEntity();
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

    public void setBlockCollisionMode(BoundingBoxMode boundingBoxMode) {
        this.boundingBoxMode = boundingBoxMode;
    }

    public BoundingBoxMode getBoundingBoxMode() {
        return boundingBoxMode;
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
    protected final void writeEntityToNBT(NBTTagCompound compound) {
    }

    @Override
    protected final void readEntityFromNBT(NBTTagCompound compound) {
    }

    public boolean hasCustomSaveData() {
        return false;
    }

    public NBTTagCompound savePartData() {
        throw new UnsupportedOperationException("Called EntityVehiclePart#savePartData on unsupported vehicle part! Either check your validations or serialization implementation");
    }

    public void loadPartData(NBTTagCompound nbt) {
        throw new UnsupportedOperationException("Called EntityVehiclePart#loadPartData on unsupported vehicle part! Either check your validations or deserialization implementation");
    }

    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] - Part name: " + this.partName + ", EntityID: " + this.getEntityId();
    }

    public String getPartInfo() {
        return "Name: " + this.partName;
    }

    public enum BoundingBoxMode {
        NONE,
        COLLIDER
    }

    private static ParentEntityAccess<EntityDriveable> createAccessor(EntityDriveable ref) {
        return new ParentEntityAccess<EntityDriveable>() {
            @Override
            public EntityDriveable getParentEntity() {
                return ref;
            }

            @Override
            public void synchronizeClientData() {
                ref.sendClientData();
            }
        };
    }
}
