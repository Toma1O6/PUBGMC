package dev.toma.pubgmc.common.entity.controllable;

import dev.toma.pubgmc.common.entity.util.VehicleCategory;
import dev.toma.pubgmc.common.entity.util.Wheel;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class EntityLandVehicle extends EntityVehicle implements IEntityMultiPart {

    private final Wheel[] wheels;

    public EntityLandVehicle(World world) {
        super(world);
        List<Wheel> wheelList = new ArrayList<>();
        this.createWheels(wheelList::add);
        this.wheels = wheelList.toArray(new Wheel[0]);
    }

    public abstract void createWheels(Consumer<Wheel> registration);

    public abstract void doEngineParticles(Consumer<Vec3d> consumer);

    public abstract void doExhaustParticles(Consumer<Vec3d> consumer);

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        for (Wheel wheel : this.wheels) {
            wheel.updatePosition(this);
        }
    }

    @Override
    public void runVehicleTick() {
        super.runVehicleTick();

        if (world.isRemote) {
            // TODO client side tick TBD
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

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public boolean attackEntityFromPart(MultiPartEntityPart part, DamageSource source, float damage) {
        if (super.attackEntityFrom(source, damage)) {
            Wheel wheel = (Wheel) part;
            if (!wheel.isDestroyed()) {
                wheel.applyDamage(damage);
            }
            return true;
        }
        return false;
    }

    public final boolean isSubmergedInWater() {
        int top = MathHelper.floor(this.posY + this.height);
        IBlockState state = this.world.getBlockState(new BlockPos(this.posX, top, this.posZ));
        return state.getMaterial() == Material.WATER;
    }

    @Override
    protected boolean handleEntityAttack(DamageSource source, float amount) {
        // TODO check for collision with wheels and apply wheel damage if applicable
        return super.handleEntityAttack(source, amount);
    }

    @Nullable
    @Override
    public Entity[] getParts() {
        return this.wheels;
    }
}
