package dev.toma.pubgmc.common.entity.vehicles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Consumer;

import static dev.toma.pubgmc.DevUtil.randomRange;

public abstract class EntityLandVehicle extends EntityVehicle {

    public static final float WHEEL_DAMAGE_MULTIPLIER = 0.2F;

    public EntityLandVehicle(World world) {
        super(world);
    }

    public abstract void processEngineParticles(Consumer<Vec3d> particleOriginRegistration);

    public abstract void processExhaustParticles(Consumer<Vec3d> particleOriginRegistration);

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
    }

    @Override
    public void runVehicleTick() {
        super.runVehicleTick();
        this.updateStepHeight();
        if (this.isInWater()) {
            float multiplier = this.isSubmergedInWater() ? 0.85F : 0.97F;
            this.multiplyMotion(multiplier);
        }

        if (world.isRemote) {
            this.particleTick();
        }
    }

    protected void particleTick() {
        if (!this.isStarted()) {
            this.processExhaustParticles(vec -> {
                Vec3d pos = vec.rotateYaw(-this.rotationYaw * (float) (Math.PI / 180F)).add(this.getPositionVector());
                this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.x, pos.y, pos.z, 0.0, 0.01, 0.0);
            });
        }
        float healthPercentage = this.getHealth() / this.getMaxHealth();
        if (healthPercentage < 0.35F) {
            this.processEngineParticles(vec -> {
                Vec3d pos = vec.rotateYaw(-this.rotationYaw * (float) (Math.PI / 180F)).add(this.getPositionVector());
                if (this.isDestroyed()) {
                    this.world.spawnParticle(EnumParticleTypes.FLAME, true, pos.x, pos.y, pos.z, randomRange(this.rand, 0.05), 0.01, randomRange(this.rand, 0.05));
                    this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, pos.x, pos.y, pos.z, randomRange(this.rand, 0.1), 0.05, randomRange(this.rand, 0.1));
                    this.world.spawnParticle(EnumParticleTypes.CLOUD, true, pos.x, pos.y, pos.z, randomRange(this.rand, 0.1), 0.05, randomRange(this.rand, 0.1));
                }
                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, pos.x, pos.y, pos.z, randomRange(this.rand, 0.05), 0.1, randomRange(this.rand, 0.05));
            });
        }
    }

    @Override
    protected void handleInputUpdate() {
        if (this.hasFuel()) {
            if (!this.isDestroyed()) {
                // TODO acceleration
            }
            // TODO braking
        }
        // TODO turning
    }

    @Override
    public VehicleCategory getVehicleCategory() {
        return VehicleCategory.LAND;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int encode(GameSettings settings) {
        int result = 0;
        if (settings.keyBindForward.isKeyDown())
            result |= KEY_FORWARD;
        if (settings.keyBindBack.isKeyDown())
            result |= KEY_BACK;
        if (settings.keyBindRight.isKeyDown())
            result |= KEY_RIGHT;
        if (settings.keyBindLeft.isKeyDown())
            result |= KEY_LEFT;
        return result;
    }

    public final boolean isSubmergedInWater() {
        int top = MathHelper.floor(this.posY + this.height);
        IBlockState state = this.world.getBlockState(new BlockPos(this.posX, top, this.posZ));
        return state.getMaterial().isLiquid();
    }

    @Override
    protected float getStepHeight() {
        return this.isSubmergedInWater() ? 0.5F : super.getStepHeight();
    }
}
