package dev.toma.pubgmc.common.entity.vehicles;

import dev.toma.pubgmc.common.entity.vehicles.sounds.VehicleSound;
import dev.toma.pubgmc.common.entity.vehicles.util.VehicleCategory;
import dev.toma.pubgmc.common.entity.vehicles.util.VehicleSoundController;
import dev.toma.pubgmc.config.common.CFGVehicle;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.s2c.S2C_VehicleSoundEvent;
import dev.toma.pubgmc.util.math.Mth;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.init.Blocks;
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

    private float velocity;
    private float turn;

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
        this.fallDistance = 0F;
        super.onEntityUpdate();
    }

    @Override
    public void runVehicleTick() {
        super.runVehicleTick();
        this.updateMotionAndTurning();
        this.updateStepHeight();

        if (world.isRemote) {
            this.particleTick();
        }
    }

    protected void handleVehicleState() {
        super.handleVehicleState();
        handleSpecialEffect();
    }

    protected void updateMotionAndTurning() {
        if (this.turn != 0.0F) {
            this.rotationYaw -= this.turn;
            if (this.rotationYaw > 180.0F) {
                this.rotationYaw -= 360.0F;
            }
            if (this.rotationYaw <= -180.0F) {
                this.rotationYaw += 360.0F;
            }
        }
        if (this.bomb || hasBombMotion()) {
            motionX = bombMotion.x;
            motionY = bombMotion.y;
            motionZ = bombMotion.z;
            dropBombMotion();
            return;
        }

        float yawRad = (float) Math.toRadians(this.rotationYaw);
        Vec3d direction = new Vec3d(-MathHelper.sin(yawRad), 0.0, MathHelper.cos(yawRad)).normalize();

        float enginePwr = getEnginePower();
        float speed = enginePwr * this.velocity;
        Vec3d velocity = direction.scale(speed);
        this.motionX = velocity.x;
        this.motionZ = velocity.z;
    }

    @Override
    protected float getEnginePower() {
        float percentage = this.getHealthPercentage();
        if (percentage >= 0.45) {
            return 1.0F + 0.2F * (percentage-0.45F) / 0.65F;
        } else if (percentage >= 0.2F) {
            return 1.0F;
        } else {
            return 0.8F;
        }
    }

    protected void particleTick() {
        if (this.isStarted()) {
            this.processExhaustParticles(vec -> {
                Vec3d pos = vec.rotateYaw(-this.rotationYaw * (float) (Math.PI / 180F)).add(this.getPositionVector());
                this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.x, pos.y, pos.z, 0.0, 0.01, 0.0);
            });
        }
        float healthPercentage = this.getHealthPercentage();
        if (this.isExploded()) {
           this.processEngineParticles(vec -> {
               Vec3d pos = vec.rotateYaw(-this.rotationYaw * (float) (Math.PI / 180F)).add(this.getPositionVector());
               this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, pos.x, pos.y, pos.z, randomRange(this.rand, 0.1), 0.10, randomRange(this.rand, 0.1));
               this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, pos.x, pos.y, pos.z, randomRange(this.rand, 0.1), 0.15, randomRange(this.rand, 0.1));
               this.world.spawnParticle(EnumParticleTypes.FLAME, true, pos.x + randomRange(this.rand, 1.0), pos.y, pos.z + randomRange(this.rand, 1.0), randomRange(this.rand, 0.03), 0.15, randomRange(this.rand, 0.03));
           });
           return;
        }
        if (healthPercentage < 0.45F) {
            this.processEngineParticles(vec -> {
                Vec3d pos = vec.rotateYaw(-this.rotationYaw * (float) (Math.PI / 180F)).add(this.getPositionVector());
                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, pos.x, pos.y, pos.z, randomRange(this.rand, 0.05), 0.1, randomRange(this.rand, 0.05));
                if (healthPercentage < 0.2F) {
                    this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, pos.x, pos.y, pos.z, randomRange(this.rand, 0.1), 0.05, randomRange(this.rand, 0.1));
                    this.world.spawnParticle(EnumParticleTypes.CLOUD, true, pos.x, pos.y, pos.z, randomRange(this.rand, 0.1), 0.05, randomRange(this.rand, 0.1));
                    if (this.isDestroyed()) {
                        this.world.spawnParticle(EnumParticleTypes.FLAME, true, pos.x, pos.y, pos.z, randomRange(this.rand, 0.05), 0.01, randomRange(this.rand, 0.05));
                    }
                }
            });
        }
    }

    @Override
    protected void handleInputUpdate() {
        // Turn
        if (getMotionSqr() > 1) {
            float turnDiff = 0.0F;
            float turnSpeed = getTurnSpeed();
            if (this.hasInput(KEY_LEFT)) {
                turnDiff = turnSpeed;
            }
            if (this.hasInput(KEY_RIGHT)) {
                turnDiff = -turnSpeed;
            }
            turnVehicle(turnDiff);
        }
        // accelerator, brake
        boolean forward = this.hasInput(KEY_FORWARD);
        boolean back = this.hasInput(KEY_BACK);
        boolean currentForward = this.isMovingForward();
        if (this.isDestroyed() || !this.isStarted() || !hasFuel()) {
            if (forward) {
                brake(true, currentForward);
            }
            if (back) {
                brake(false, currentForward);
            }
            return;
        }

        float acc = getAcceleration();
        if (this.isDestroyed() || !this.isStarted()) {
            this.velocity = Mth.linearDecay(this.velocity, acc);
            this.velocity = Mth.exponentialDecay(this.velocity, 0.95F);
        } else {
            float max = getMaxSpeed();
            if (forward) {
                if (currentForward) {
                    this.velocity = Math.min(max, this.velocity + acc);
                    removeFuel(0.01F);
                } else {
                    brake(true);
                }
                return; // doesn't allow move in both side
            }
            if (back) {
                if (currentForward) {
                    this.velocity = Math.max(-0.3F * max, this.velocity - 0.02F);
                    removeFuel(0.01F);
                } else {
                    brake(false);
                }
            }
        }
    }

    protected void turnVehicle(float turnDiff) {
        float mx = getVehicleConfiguration().maxTurningAngle.getAsFloat();
        turnVehicle(turnDiff, mx);
    }

    protected void turnVehicle(float turnDiff, float maxTurn) {
        if (turnDiff != 0.0F) {
            this.turn = MathHelper.clamp(this.turn + turnDiff, -maxTurn, maxTurn);
        } else {
            this.turn = Mth.exponentialDecay(this.turn, 0.95F);
            this.turn = Mth.linearDecay(this.turn, 0.05F);
        }
    }

    protected void brake() {
        brake(false);
    }

    protected void brake(boolean inputForward) {
        brake(inputForward, !inputForward);
    }

    protected void brake(boolean inputForward, boolean vehicleForward) {
        if (inputForward == vehicleForward) {
            return;
        }
        float brakeMultiplier = getBrakeStrength();
        CFGVehicle cfg = getVehicleConfiguration();
        float acc = cfg.acceleration.getAsFloat() * brakeMultiplier;
        this.velocity += acc * (inputForward ? 1 : -1);
    }

    public float getBrakeStrength() {
        float weatherMultiplier = getWeatherBrakeMultiplier();
        float surfaceMultiplier = getSurfaceBrakeMultiplier();
        return 0.4F * weatherMultiplier * surfaceMultiplier;
    }

    private float getWeatherBrakeMultiplier() {
        if (this.world.isThundering()) {
            return 0.85F;
        } else if (this.world.isRaining()) {
            return 0.9F;
        } else {
            return 1.0F;
        }
    }

    private float getSurfaceBrakeMultiplier() {
        BlockPos below = this.getPosition().down();
        IBlockState state = this.world.getBlockState(below);
        Block block = state.getBlock();

        if (block == Blocks.ICE || block == Blocks.PACKED_ICE) {
            return 0.6F;
        } else if (block == Blocks.SAND || block == Blocks.GRAVEL) {
            return 1.1F;
        } else if (block == Blocks.SLIME_BLOCK) {
            return 0.8F;
        } else if (block == Blocks.GRASS || block == Blocks.DIRT) {
            return 1.0F;
        } else {
            return 0.95F;
        }
    }

    @Override
    protected void handleEmptyInputUpdate() {
        this.velocity = Mth.exponentialDecay(this.velocity, 0.95F);
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

    @Override
    protected float getStepHeight() {
        return this.isSubmergedInWater() ? 0.5F : super.getStepHeight();
    }

    public float getTurn() {
        return turn;
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

    public void handleSpecialEffect() {
    }

    @Override
    protected void applyGravity() {
        if (!bomb) {
            super.applyGravity();
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
                this.play(EntityVehicle.SOUND_ENGINE_STARTING);
            }

            @Override
            public void playStartedSound() {
                this.play(EntityVehicle.SOUND_ENGINE_STARTING);
            }
        }
    }
}
