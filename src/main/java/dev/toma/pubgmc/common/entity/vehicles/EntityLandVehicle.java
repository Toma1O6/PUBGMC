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

    public static int TOTAL_ENGINE = 1;

    protected final LandVehicleSoundController soundController;
    private final LandVehicleSoundPack soundPack;
    protected boolean reverseTurn = false;

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
    public float getEnginePower() {
        float percentage = this.getHealthPercentage();
        if (percentage >= 1.0F)
            return 1.05F;
        if (percentage >= 0.45) {
            return 0.9F + 0.1F * (percentage-0.45F) / 0.65F;
        } else if (percentage >= 0.2F) {
            return 0.9F;
        } else {
            return 0.7F;
        }
    }

    @Override
    protected void handleEmptyInputUpdate() {
        reverseTurn = false;
        handleEmptyTurnInput();
        handleEmptyAcceleratorInput();
    }

    protected void handleEmptyTurnInput() {
        this.turn = Mth.exponentialDecay(this.turn, 0.8F);
        this.turn = Mth.linearDecay(this.turn, 0.04F);
    }

    protected void handleEmptyAcceleratorInput() {
        if (isStarted()) {
            engineIdleTimeTotal++;
        }
        decelerate(0.00347F); // 1 second to drop 5 km/h
    }

    @Override
    protected void handleInputUpdate() {
        reverseTurn = !this.hasInput(KEY_FORWARD) && this.hasInput(KEY_BACK);
        handleTurnInputUpdate();
        handleAcceleratorInputUpdate();
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

    protected void handleTurnInputUpdate() {
        if (isStarted() || getMotionSqr() > 0.069F) {
            boolean left = this.hasInput(KEY_LEFT);
            boolean right = this.hasInput(KEY_RIGHT);
            if (!left && !right) {
                handleEmptyTurnInput();
                return;
            }
            boolean currentForward = this.isMovingForward();
            float turnDiff = 0.0F;
            float turnSpeed = getTurnSpeed();
            if (left) {
                if (this.turn < 0)
                    turnSpeed *= 1.2F;
                turnDiff = turnSpeed;
            }
            if (right) {
                if (this.turn > 0)
                    turnSpeed *= 1.2F;
                turnDiff = -turnSpeed;
            }
            turnVehicle(turnDiff, currentForward);
        }
    }

    protected void turnVehicle(float turnDiff, boolean isForward) {
        float mx = getVehicleConfiguration().maxTurningAngle.getAsFloat();
        turnVehicle(turnDiff, isForward, mx);
    }

    protected void turnVehicle(float turnDiff, boolean isForward, float maxAngle) {
        if (turnDiff == 0) {
            this.turn = Mth.exponentialDecay(this.turn, 0.7F);
            return;
        }
        if (reverseTurn) turnDiff = -turnDiff;
        this.turn = MathHelper.clamp(this.turn + turnDiff, -maxAngle, maxAngle);
    }

    protected void handleAcceleratorInputUpdate() {
        boolean forward = this.hasInput(KEY_FORWARD);
        boolean back = this.hasInput(KEY_BACK);
        if (this.isDestroyed() || !this.isStarted() || (!forward && !back)) {
            handleEmptyAcceleratorInput();
            return;
        }
        boolean currentForward = this.isMovingForward();
        float acc = getAcceleration() * getEnginePower();
        engineIdleTimeTotal++;
        if (forward) {
            if (currentForward && hasFuel()) {
                this.velocity = Math.min(getMaxSpeed(), this.velocity + acc);
                engineIdleTimeTotal = 0;
            } else {
                brake(true);
            }
        }
        if (back) { // allow to accelerate and brake at the same time
            if (!currentForward && hasFuel()) {
                this.velocity = Math.max(getReverseMaxSpeed(), this.velocity - acc);
                engineIdleTimeTotal = 0;
            } else {
                brake(false);
            }
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
        return 0.5F * weatherMultiplier * surfaceMultiplier;
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

    protected void handleVehicleState() {
        super.handleVehicleState();
        handleSpecialEffect();
    }

    public void handleSpecialEffect() {
    }

    @Override
    protected void applyGravity() {
        if (!bomb) {
            super.applyGravity();
        }
    }

    @Override
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

    public final boolean isReverseTurn() {
        return this.reverseTurn;
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
    public float getStepHeight() {
        return this.isSubmergedInWater() ? 0.5F : super.getStepHeight();
    }

    public float getTurn() {
        return turn;
    }

    public float getVelocity() {
        return velocity;
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
                this.play(EntityVehicle.SOUND_ENGINE_STARTING);
            }

            @Override
            public void playStartedSound() {
                this.play(EntityVehicle.SOUND_ENGINE_STARTING);
            }
        }
    }
}
