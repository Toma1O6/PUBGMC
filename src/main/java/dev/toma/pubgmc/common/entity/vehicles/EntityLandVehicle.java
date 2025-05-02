package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.common.entity.vehicles.sounds.VehicleSound;
import dev.toma.pubgmc.common.entity.vehicles.util.VehicleCategory;
import dev.toma.pubgmc.common.entity.vehicles.util.VehicleSoundController;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_VehicleSoundEvent;
import dev.toma.pubgmc.util.math.Mth;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
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

    protected final LandVehicleSoundController soundController;
    private final LandVehicleSoundPack soundPack;

    private float acceleration;
    private float turn;
    private Vec3d movement = Vec3d.ZERO;

    public EntityLandVehicle(World world) {
        super(world);
        if (world.isRemote)
            this.soundController = new LandVehicleSoundController.Client(this);
        else
            this.soundController = new LandVehicleSoundController.Server(this);
        this.soundPack = this.createSoundPack();
    }

    public abstract void processEngineParticles(Consumer<Vec3d> particleOriginRegistration);

    public abstract void processExhaustParticles(Consumer<Vec3d> particleOriginRegistration);

    public abstract LandVehicleSoundPack createSoundPack();

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
    }

    @Override
    public void runVehicleTick() {
        super.runVehicleTick();
        this.updateMotionAndTurning();
        this.updateStepHeight();
        if (this.isInWater()) {
            float multiplier = this.isSubmergedInWater() ? 0.85F : 0.97F;
            this.multiplyMotion(multiplier);
        }

        if (world.isRemote) {
            this.particleTick();
        }
    }

    protected void updateMotionAndTurning() {
        Vec3d vehicleFaceVec = this.getLookVec();
        Vec3d rawTurnVec = new Vec3d(0.0, this.turn, 0.0).rotateYaw(-this.rotationYaw * (float) (Math.PI / 180.0F));

        float enginePwr = 1.4F;
        float pwr = enginePwr * this.acceleration;
        Vec3d accelerationVec = new Vec3d(0.0, 0.0, pwr).rotateYaw(-this.rotationYaw * (float) (Math.PI / 180.0F));
        // TODO update movement vec and vehicle rotation

        this.movement = accelerationVec;
        this.motionX = this.movement.x;
        this.motionZ = this.movement.z;
    }

    protected void particleTick() {
        if (this.isStarted()) {
            this.processExhaustParticles(vec -> {
                Vec3d pos = vec.rotateYaw(-this.rotationYaw * (float) (Math.PI / 180F)).add(this.getPositionVector());
                this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.x, pos.y, pos.z, 0.0, 0.01, 0.0);
            });
        }
        float healthPercentage = this.getHealth() / this.getMaxHealth();
        if (this.isBurned()) {
           this.processEngineParticles(vec -> {
               Vec3d pos = vec.rotateYaw(-this.rotationYaw * (float) (Math.PI / 180F)).add(this.getPositionVector());
               this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, pos.x, pos.y, pos.z, randomRange(this.rand, 0.1), 0.10, randomRange(this.rand, 0.1));
               this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, pos.x, pos.y, pos.z, randomRange(this.rand, 0.1), 0.15, randomRange(this.rand, 0.1));
               this.world.spawnParticle(EnumParticleTypes.FLAME, true, pos.x + randomRange(this.rand, 1.0), pos.y, pos.z + randomRange(this.rand, 1.0), randomRange(this.rand, 0.03), 0.15, randomRange(this.rand, 0.03));
           });
        } else if (healthPercentage < 0.35F) {
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
            if (!this.isDestroyed() && this.isStarted() && this.hasInput(KEY_FORWARD)) {
                this.acceleration = Math.min(1.0F, this.acceleration + 0.05F); // TODO vehicle acceleration rate
            } else {
                this.acceleration = Mth.exponentialDecay(this.acceleration, 0.95F); // TODO speed decay
            }
            if (this.hasInput(KEY_BACK)) {
                this.acceleration = Math.max(-0.3F, this.acceleration - 0.02F); // TODO brake speed, max reverse speed
            }
        }
        float turnDiff = 0.0F;
        float turnSpeed = 0.05F; // TODO vehicle turn speed
        float maxTurning = 1.0F; // TODO vehicle controllability
        if (this.hasInput(KEY_LEFT)) {
            turnDiff = turnSpeed;
        }
        if (this.hasInput(KEY_RIGHT)) {
            turnDiff = -turnSpeed;
        }
        if (turnDiff != 0.0F) {
            this.turn = MathHelper.clamp(this.turn + turnDiff, -maxTurning, maxTurning);
        } else {
            this.turn = Mth.linearDecay(this.turn, 0.1F);
        }
    }

    @Override
    protected void handleEmptyInputUpdate() {
        this.acceleration = Mth.exponentialDecay(this.acceleration, 0.95F);
        this.turn = Mth.linearDecay(this.turn, 0.1F);
    }

    @Override
    public final VehicleCategory getVehicleCategory() {
        return VehicleCategory.LAND;
    }

    @Override
    public final LandVehicleSoundController getSoundController() {
        return soundController;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public final int encode(GameSettings settings) {
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

    public float getAcceleration() {
        return acceleration;
    }

    public float getTurn() {
        return turn;
    }

    public Vec3d getMovement() {
        return movement;
    }

    public static final class LandVehicleSoundPack {

        private final SoundEvent startingSound;
        private final SoundEvent startedSound;

        public LandVehicleSoundPack(SoundEvent startingSound, SoundEvent startedSound) {
            this.startingSound = startingSound;
            this.startedSound = startedSound;
        }

        public SoundEvent getStartingSound() {
            return startingSound;
        }

        public SoundEvent getStartedSound() {
            return startedSound;
        }
    }

    public static abstract class LandVehicleSoundController extends VehicleSoundController {

        protected final EntityLandVehicle landVehicle;

        public LandVehicleSoundController(EntityLandVehicle landVehicle) {
            this.landVehicle = landVehicle;
        }

        @SideOnly(Side.CLIENT)
        public static final class Client extends LandVehicleSoundController {

            private VehicleSound<EntityLandVehicle> startingSound;
            private VehicleSound<EntityLandVehicle> startedSound;

            public Client(EntityLandVehicle landVehicle) {
                super(landVehicle);
            }

            @Override
            public void update() {
                // TODO update engine sound pitch
            }

            @Override
            public void play(int eventId) {
                switch (eventId) {
                    case SOUND_ENGINE_STARTING:
                        this.playStartingSound();
                        break;
                    case SOUND_ENGINE_STARTED:
                        this.playStartedSound();
                        break;
                }
            }

            @Override
            public void stop(int eventId) {
                switch (eventId) {
                    case SOUND_ENGINE_STARTING:
                        this.stop(this.startingSound);
                        break;
                    case SOUND_ENGINE_STARTED:
                        this.stop(this.startedSound);
                        break;
                }
            }

            @Override
            public void playStartingSound() {
                this.startingSound = new VehicleSound<>(SOUND_ENGINE_STARTING, this.landVehicle, this.landVehicle.soundPack.getStartingSound());
                this.startingSound.setPlayCondition(vehicle -> !vehicle.isDestroyed() && vehicle.isStarting());
                this.startingSound.setRepeating(false);
                this.startingSound.onSoundStopped(sound -> this.startingSound = null);
                this.play(this.startingSound, true);
            }

            @Override
            public void playStartedSound() {
                this.startedSound = new VehicleSound<>(SOUND_ENGINE_STARTED, this.landVehicle, this.landVehicle.soundPack.getStartedSound());
                this.startedSound.setPlayCondition(vehicle -> !vehicle.isDestroyed());
                this.startedSound.setRepeating(false);
                this.startedSound.onSoundStopped(sound -> this.startedSound = null);
                this.play(this.startedSound, true);
            }

            private void play(ISound sound, boolean force) {
                Minecraft minecraft = Minecraft.getMinecraft();
                SoundHandler handler = minecraft.getSoundHandler();
                if (handler.isSoundPlaying(sound)) {
                    if (force) {
                        handler.stopSound(sound);
                        handler.playSound(sound);
                    }
                } else {
                    handler.playSound(sound);
                }
            }

            private void stop(ISound sound) {
                if (sound == null)
                    return;
                Minecraft minecraft = Minecraft.getMinecraft();
                SoundHandler handler = minecraft.getSoundHandler();
                if (handler.isSoundPlaying(sound)) {
                    handler.stopSound(sound);
                }
            }
        }

        public static final class Server extends LandVehicleSoundController {

            public Server(EntityLandVehicle landVehicle) {
                super(landVehicle);
            }

            @Override
            public void update() {
            }

            @Override
            public void play(int eventId) {
                PacketHandler.sendToAllTracking(new S2C_VehicleSoundEvent(true, this.landVehicle.getEntityId(), eventId), this.landVehicle);
            }

            @Override
            public void stop(int eventId) {
                PacketHandler.sendToAllTracking(new S2C_VehicleSoundEvent(false, this.landVehicle.getEntityId(), eventId), this.landVehicle);
            }

            @Override
            public void playStartingSound() {
                this.play(EntityDriveable.SOUND_ENGINE_STARTING);
            }

            @Override
            public void playStartedSound() {
                this.play(EntityDriveable.SOUND_ENGINE_STARTING);
            }
        }
    }
}
