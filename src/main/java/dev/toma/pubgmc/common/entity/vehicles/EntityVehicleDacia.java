package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.config.common.CFGVehicle;
import dev.toma.pubgmc.init.PMCSounds;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityVehicleDacia extends EntityVehicle implements Variants {

    private static final Vec3d[] VECTORS = new Vec3d[]{new Vec3d(1.5, 0.5, 0d), new Vec3d(-3.8, 0, -0.6)};
    private static final ResourceLocation[] VARIANTS = {
            Pubgmc.getResource("textures/vehicle/dacia_blue.png"),
            Pubgmc.getResource("textures/vehicle/dacia_orange.png"),
            Pubgmc.getResource("textures/vehicle/dacia_white.png"),
            Pubgmc.getResource("textures/vehicle/dacia_yellow.png")
    };
    int textureIndex;

    public EntityVehicleDacia(World world) {
        super(world);
        setSize(2f, 1.5f);
    }

    public EntityVehicleDacia(World world, int x, int y, int z) {
        super(world, x, y, z);
        setSize(2f, 1.5f);
        this.textureIndex = world.rand.nextInt(VARIANTS.length);
    }

    @Override
    public int getActualTexture() {
        return textureIndex;
    }

    @Override
    public void setTexture(int texture) {
        this.textureIndex = texture;
    }

    @Override
    public ResourceLocation[] getTextures() {
        return VARIANTS;
    }

    @Override
    public CFGVehicle getVehicleConfiguration() {
        return ConfigPMC.vehicles().dacia;
    }

    @Override
    public int getMaximumCapacity() {
        return 4;
    }

    @Override
    public double getMountedYOffset() {
        return 0.25d;
    }

    @Override
    public Vec3d getEnginePosition() {
        return VECTORS[0];
    }

    @Override
    public Vec3d getExhaustPosition() {
        return VECTORS[1];
    }

    @Override
    public SoundEvent vehicleSound() {
        return PMCSounds.uaz;
    }

    @Override
    protected float getPassengerXOffset(int passengerIndex) {
        return passengerIndex % 2 == 0 ? -0.3f : -1.5f;
    }

    @Override
    protected float getPassengerZOffset(int passengerIndex) {
        return passengerIndex > 1 ? 0.5f : -0.5f;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("textureIndex", textureIndex);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        textureIndex = compound.getInteger("textureIndex");
    }

    @Override
    public void writeSpawnData(ByteBuf buf) {
        super.writeSpawnData(buf);
        buf.writeInt(textureIndex);
    }

    @Override
    public void readSpawnData(ByteBuf buf) {
        super.readSpawnData(buf);
        textureIndex = buf.readInt();
    }
}
