//package dev.toma.pubgmc.common.entity.controllable;
//
//import com.google.common.base.Predicate;
//import com.google.common.base.Predicates;
//import dev.toma.pubgmc.Pubgmc;
//import dev.toma.pubgmc.api.capability.IPlayerData;
//import dev.toma.pubgmc.api.capability.PlayerDataProvider;
//import dev.toma.pubgmc.api.client.game.CustomEntityNametag;
//import dev.toma.pubgmc.api.entity.IBombReaction;
//import dev.toma.pubgmc.api.entity.SynchronizableEntity;
//import dev.toma.pubgmc.api.game.GameObject;
//import dev.toma.pubgmc.common.items.ItemFuelCan;
//import dev.toma.pubgmc.config.ConfigPMC;
//import dev.toma.pubgmc.config.common.CFGVehicle;
//import dev.toma.pubgmc.init.PMCDamageSources;
//import dev.toma.pubgmc.init.PMCSounds;
//import dev.toma.pubgmc.util.helper.GameHelper;
//import dev.toma.pubgmc.util.helper.SerializationHelper;
//import io.netty.buffer.ByteBuf;
//import net.minecraft.block.Block;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityList;
//import net.minecraft.entity.MoverType;
//import net.minecraft.entity.passive.EntityAnimal;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.init.SoundEvents;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.util.*;
//import net.minecraft.util.math.*;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.TextComponentTranslation;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
//
//import javax.annotation.Nullable;
//import java.util.List;
//import java.util.UUID;
//
//public abstract class EntityVehicle extends EntityControllable implements IEntityAdditionalSpawnData, GameObject, SynchronizableEntity, CustomEntityNametag, IBombReaction {
//    private static final Predicate<Entity> TARGET = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, Entity::canBeCollidedWith);
//    private static final AxisAlignedBB BOX = new AxisAlignedBB(-0.5d, 0d, -0.5d, 1.5d, 1d, 1.5d);
//
//    public float health;
//    private float currentSpeed;
//    public float turnModifier;
//    public float fuel;
//    public static final float maxFuel = 100F;
//    public boolean isBroken = false;
//    private short timeInInvalidState;
//    private float damageLevel1 = 0.45f;
//    private float damageLevel2 = 0.2f;
//    private float damageLevel3 = 0f;
//    private boolean exploded = false;
//    private int timeBeforeExplode = 100; // 5 seconds
//    private int timeAfterExplode = 400; // 20 seconds
//    private boolean bomb = false;
//    private Vec3d bombMotion = Vec3d.ZERO;
//    private float brakeMultiplier = 0.3F;
//    private int stableTime = 5;
//    private static final int vehicleWeight = 0;
//    private static final float elasticModulus = 0.6F;
//    private int vehicleCollideCooldown = 5;
//    private UUID gameId = GameHelper.DEFAULT_UUID;
//
//    public EntityVehicle(World world) {
//        super(world);
//        stepHeight = 1.25F;
//        preventEntitySpawning = true;
//        health = getVehicleConfiguration().maxHealth.getAsFloat();
//        fuel = 50 + world.rand.nextInt(5) * 10;
//    }
//
//    public EntityVehicle(World world, int x, int y, int z) {
//        this(world);
//        setPosition(x, y, z);
//    }
//
//    public abstract int getMaximumCapacity();
//
//    public abstract Vec3d getEnginePosition();
//
//    public abstract Vec3d getExhaustPosition();
//
//    public abstract SoundEvent vehicleSound();
//
//    public abstract CFGVehicle getVehicleConfiguration();
//
//    @Override
//    public ITextComponent getComponent() {
//        String key = EntityList.getEntityString(this);
//        if (key == null) {
//            key = "generic";
//        }
//        return new TextComponentTranslation("entity." + key + ".name");
//    }
//
//    public float getDamageLevel(int level) {
//        switch (level) {
//            case 1:
//                return damageLevel1;
//            case 2:
//                return damageLevel2;
//            case 3:
//                return damageLevel3;
//        }
//        return -1;
//    }
//
//    public float getHealthPercentage() {
//        return this.health / this.getVehicleConfiguration().maxHealth.getAsFloat();
//    }
//
//    public float getFuelPercentage() {
//        return this.fuel / maxFuel;
//    }
//
//    @Override
//    public void updatePre() {
//        this.handleEmptyInputs();
//        if (!onGround) {
//            motionY -= 0.1;
//        }
//        fallDistance = 0.0F;
//        if (vehicleCollideCooldown > 0) {
//            vehicleCollideCooldown--;
//        }
//        Vec3d look = this.getLookVec();
//        motionX = look.x * currentSpeed;
//        motionZ = look.z * currentSpeed;
//        if (currentSpeed != 0) {
//            rotationYaw += currentSpeed > 0 ? turnModifier : -turnModifier;
//            stableTime = 5;
//        } else {
//            stableTime--;
//        }
//        if (!isBeingRidden() || !hasMovementInput() || !hasTurnInput() || !hasFuel() || isBroken) {
//            reset();
//        }
//        // bomb motion (ignore player input)
//        if (this.bomb || hasBombMotion()) {
//            motionX = bombMotion.x;
//            motionY = bombMotion.y;
//            motionZ = bombMotion.z;
//            dropBombMotion();
//        }
//        handleExplodeTick();
//        this.handleEntityCollisions();
//        this.checkState();
//        if (collidedHorizontally) {
//            currentSpeed *= 0.6;
//        }
//    }
//
//    public boolean hasBombMotion() {
//        return Math.abs(bombMotion.x) > 0.01F || Math.abs(bombMotion.z) > 0.01F;
//    }
//
//    private void dropBombMotion() {
//        double x = Math.abs(bombMotion.x) > 0.01F ? bombMotion.x * 0.98F : 0F;
//        double z = Math.abs(bombMotion.z) > 0.01F ? bombMotion.z * 0.98F : 0F;
//        double y = bombMotion.y;
//        if (onGround) {
//            if (y < 0) {
//                y = 0;
//            }
//            x *= 0.98F;
//            z *= 0.98F;
//        } else {
//            if (y > 0) {
//                y *= 0.97F;
//            }
//            y -= 0.02F;
//        }
//        if (collided) {
//            x *= 0.98F;
//            z *= 0.98F;
//        }
//        bombMotion = new Vec3d(x, y, z);
//        if (!hasBombMotion()) {
//            this.bomb = false;
//            this.bombMotion = Vec3d.ZERO;
//        }
//    }
//
//    @Override
//    public void updatePost() {
//        move(MoverType.SELF, motionX, motionY, motionZ);
//        playSoundAtVehicle();
//        spawnNormalParticles();
//        if (ticksExisted % 20 == 0) {
//            GameHelper.validateGameEntityStillValid(this);
//        }
//    }
//
//    private void handleExplodeTick() {
//        // Remove exploded vehicle
//        if (this.exploded && --this.timeAfterExplode < 0) {
//            this.setDead();
//            return;
//        }
//
//        if (this.health <= 0) {
//            this.removePassengers(); // force remove from vehicle
//            // sound
//            if (timeBeforeExplode % 10 == 0) {
//                playSound(SoundEvents.ENTITY_BLAZE_BURN, 2f, 1f);
//            }
//            // particles
//            if (world.isRemote) { // client only
//                double rngX = (rand.nextDouble() - 0.5F);
//                double rngZ = (rand.nextDouble() - 0.5F);
//                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, posX + rngX * 0.5, posY, posZ + rngZ * 0.5,
//                        rngX * 0.2, 0.5d, rngZ * 0.2);
//            }
//            // explode
//            if (--this.timeBeforeExplode < 0 && !this.exploded) {
//                if (!world.isRemote) { // server only
//                    this.world.createExplosion(this, posX, posY, posZ, 5f, false);
//                }
//                this.exploded = true;
//            }
//        }
//    }
//
//    protected void handleEmptyInputs() {
//        if (!hasMovementInput() || !hasFuel()) {
//            if (Math.abs(currentSpeed) >= 0.01F) {
//                brake();
//            } else {
//                currentSpeed = 0f;
//            }
//        }
//        if (!hasTurnInput()) {
//            if (Math.abs(turnModifier) <= 0.5f)
//                turnModifier = 0f;
//
//            if (turnModifier != 0) {
//                turnModifier = turnModifier > 0 ? turnModifier - 0.5f : turnModifier + 0.5f;
//            }
//        }
//    }
//
//    public void handleEntityCollisions() {
//        Vec3d from = new Vec3d(posX, posY, posZ);
//        Vec3d to = new Vec3d(from.x + motionX, from.y + motionY, from.z + motionZ);
//        Entity hitEntity = findEntityInPath(from, to);
//
//        if (hitEntity != null) {
//            if (hitEntity instanceof EntityVehicle) {
//                handleVehicleCollision(this, (EntityVehicle) hitEntity);
//                return;
//            }
//            hitEntity.attackEntityFrom(PMCDamageSources.vehicle(getControllingPassenger()), Math.abs(currentSpeed) * 15f);
//            if (hitEntity.isDead) {
//                return;
//            }
//            hitEntity.motionX += motionX * currentSpeed * 3;
//            hitEntity.motionY += currentSpeed;
//            hitEntity.motionZ += motionZ * currentSpeed * 3;
//        }
//    }
//
//    public void handleVehicleCollision(EntityVehicle vehicleA, EntityVehicle vehicleB) {
//        // simplified collision: The one with greater momentum hits the other
//        if (vehicleA.vehicleCollideCooldown > 0 || vehicleB.vehicleCollideCooldown > 0) {
//            return;
//        }
//        vehicleA.vehicleCollideCooldown = 5;
//        vehicleB.vehicleCollideCooldown = 5;
//        double mA = vehicleA.getWeight();
//        double mB = vehicleB.getWeight();
//        double vA = vehicleA.getSpeedPerTick() * 20 * 3.6;
//        double vB = vehicleB.getSpeedPerTick() * 20 * 3.6;
//        double pA = mA * vA;
//        double pB = mB * vB;
//        EntityVehicle hitter, target;
//        if (pA > pB) {
//            hitter = vehicleA;
//            target = vehicleB;
//        } else if (pA < pB) {
//            hitter = vehicleB;
//            target = vehicleA;
//        } else {
//            return;
//        }
//        target.motionX += hitter.motionX;
//        target.motionY += Math.abs(hitter.getSpeedPerTick() - target.getSpeedPerTick());
//        target.motionZ += hitter.motionZ;
//        target.attackEntityFrom(PMCDamageSources.vehicle(getControllingPassenger()), (float) target.getSpeedPerTick() * 20 * 3.6F);
//        world.playSound(null, target.posX, target.posY, target.posZ, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 1.0F, 1.0F);
//        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, target.posX, target.posY + 1, target.posZ, 0, 5, 0);
//    }
//
//
//    public int getWeight() {
//        return getPassengers().size() * 50 + vehicleWeight;
//    }
//
//    @Override
//    public void handleForward() {
//        if (!isBroken) {
//            if (!hasFuel()) {
//                brake();
//                return;
//            }
//            CFGVehicle cfg = getVehicleConfiguration();
//            float a = cfg.acceleration.getAsFloat();
//            float max = cfg.maxSpeed.getAsFloat();
//            if (shouldStabilize(currentSpeed, a, true)) {
//                currentSpeed = 0;
//            } else {
//                if (currentSpeed >= 0) {
//                    currentSpeed += a;
//                    burnFuel(0.01F);
//                } else {
//                    currentSpeed += a * 0.8F;
//                    burnFuel(0.008F);
//                }
//                currentSpeed = Math.min(max, currentSpeed);
//            }
//        }
//    }
//
//    @Override
//    public void handleBackward() {
//        if (!isBroken) {
//            if (!hasFuel()) {
//                brake();
//                return;
//            }
//            CFGVehicle cfg = getVehicleConfiguration();
//            float a = cfg.acceleration.getAsFloat();
//            float max = cfg.maxSpeed.getAsFloat();
//            if (shouldStabilize(currentSpeed, a, false)) {
//                currentSpeed = 0;
//            } else {
//                if (currentSpeed <= 0) {
//                    currentSpeed -= a;
//                    burnFuel(0.01F);
//                } else {
//                    currentSpeed -= a * 0.8F;
//                    burnFuel(0.008F);
//                }
//                currentSpeed = Math.max(-max * 0.3F, currentSpeed);
//            }
//        }
//    }
//
//    @Override
//    public void handleRight() {
//        if (!isBroken && onGround) {
//            CFGVehicle cfg = getVehicleConfiguration();
//            float max = cfg.maxTurningAngle.getAsFloat();
//            float partial = cfg.turningSpeed.getAsFloat();
//            turnModifier = turnModifier < max ? turnModifier + partial : max;
//        }
//    }
//
//    @Override
//    public void handleLeft() {
//        if (!isBroken && onGround) {
//            CFGVehicle cfg = getVehicleConfiguration();
//            float max = cfg.maxTurningAngle.getAsFloat();
//            float partial = cfg.turningSpeed.getAsFloat();
//            turnModifier = turnModifier > -max ? turnModifier - partial : -max;
//        }
//    }
//
//    public void brake() {
//        CFGVehicle cfg = getVehicleConfiguration();
//        float a = cfg.acceleration.getAsFloat();
//        if (Math.abs(currentSpeed) <= a * brakeMultiplier) {
//            currentSpeed = 0f;
//        } else {
//            currentSpeed += a * (currentSpeed > 0 ? -1 : 1) * brakeMultiplier;
//        }
//
//    }
//
//    private boolean shouldStabilize(float speed, float a, boolean isForward) {
//        return isForward
//                ? (speed <= 0 && speed > -a && stableTime > 0)
//                : (speed >= 0 && speed < a && stableTime > 0);
//    }
//
//    @Nullable
//    protected Entity findEntityInPath(Vec3d start, Vec3d end) {
//        Entity e = null;
//        List<Entity> entityList = world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(motionX, motionY + 1, motionZ).grow(1d), TARGET);
//        double d0 = 0;
//
//        for (int i = 0; i < entityList.size(); i++) {
//            Entity entity = entityList.get(i);
//            if (entity == this || isPassenger(entity)) {
//                continue;
//            }
//            AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow(0.3d);
//            RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);
//
//            if (raytraceresult != null) {
//                double d1 = start.squareDistanceTo(raytraceresult.hitVec);
//
//                if (d1 < d0 || d0 == 0.0D) {
//                    e = entity;
//                    d0 = d1;
//                }
//            }
//        }
//
//        return e;
//    }
//
//    @Override
//    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
//        if (!world.isRemote) {
//            if (this.canBeRidden(player) && canFitPassenger(player) && !player.isSneaking()) {
//                player.startRiding(this);
//                IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
//                data.getAimInfo().setAiming(false, 1.0F);
//            }
//        }
//
//        return true;
//    }
//
//    // Should be running only on server side in case some client doesn't receive packet
//    // containing new health value of this vehicle
//    protected void checkState() {
//        // if whole vehicle is under water -> can drive in shallow water
//        if (this.isInWater() && world.getBlockState(getPosition().up()).getMaterial().isLiquid()) {
//            timeInInvalidState++;
//            motionX *= 0.1d;
//            motionZ *= 0.1d;
//            motionY = -0.15d;
//        }
//
//        if (timeInInvalidState > 30) {
//            isBroken = true;
//        }
//
//        if (isInLava()) timeBeforeExplode = 0; // force to explode
//    }
//
//    protected void spawnNormalParticles() {
//        // client only
//        if (!world.isRemote) {
//            return;
//        }
//        float max = getVehicleConfiguration().maxHealth.getAsFloat();
//        if (health / max <= getDamageLevel(1)) { // engine smoke
//            Vec3d engineVec = (new Vec3d(getEnginePosition().x, getEnginePosition().y + 0.25d, getEnginePosition().z)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
//            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, posX + engineVec.x, posY + engineVec.y, posZ + engineVec.z,
//                    0d, 0.1d, 0d);
//
//            if (health / max <= getDamageLevel(2)) { // engine flame
//                double rngX = (rand.nextDouble() - rand.nextDouble()) * 0.1;
//                double rngZ = (rand.nextDouble() - rand.nextDouble()) * 0.1;
//                world.spawnParticle(EnumParticleTypes.FLAME, true, posX + engineVec.x, posY + engineVec.y - 0.2, posZ + engineVec.z,
//                        rngX, 0.02d, rngZ);
//            }
//        }
//
//        if (!isBroken && hasFuel()) {
//            Vec3d exhaustVec = (new Vec3d(getExhaustPosition().x, getExhaustPosition().y + 0.25d, getExhaustPosition().z)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
//            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, true, posX + exhaustVec.x, posY + exhaustVec.y, posZ + exhaustVec.z, 0, 0.02d, 0);
//        }
//
//        if (isBroken) {
//            Vec3d engine = (new Vec3d(getEnginePosition().x, getEnginePosition().y, getEnginePosition().z)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2f));
//            world.spawnParticle(EnumParticleTypes.CLOUD, true, posX + engine.x, posY + engine.y, posZ + engine.z, 0d, 0.05d, 0d);
//        }
//    }
//
//    @Override
//    public boolean attackEntityFrom(DamageSource source, float amount) {
//        if (source == DamageSource.FALL)
//            return false;
//
//        if (!getPassengers().contains(source.getTrueSource())) {
//            this.health -= amount;
//            SerializationHelper.syncEntity(this);
//        }
//
//        return true;
//    }
//
//    @Override
//    public void updatePassenger(Entity passenger) {
//        if (this.isPassenger(passenger)) {
//            float x = 0F;
//            float z = -0.55F;
//            float f1 = (float) ((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()));
//
//            if (!this.getPassengers().isEmpty()) {
//                int i = this.getPassengers().indexOf(passenger);
//                x = getPassengerXOffset(i);
//                z = getPassengerZOffset(i);
//            }
//
//            Vec3d vec3d = (new Vec3d(x, 0.0D, z)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
//            passenger.setPosition(this.posX + vec3d.x, this.posY + (double) f1, this.posZ + vec3d.z);
//
//            if (passenger instanceof EntityAnimal && this.getPassengers().size() > 1) {
//                int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
//                passenger.setRenderYawOffset(((EntityAnimal) passenger).renderYawOffset + (float) j);
//                passenger.setRotationYawHead(passenger.getRotationYawHead() + (float) j);
//            }
//        }
//    }
//
//    @Override
//    public boolean canFitPassenger(Entity passenger) {
//        return this.getPassengers().size() < getMaximumCapacity() && this.health > 0;
//    }
//
//    @Override
//    protected void playStepSound(BlockPos pos, Block blockIn) {
//    }
//
//    @Override
//    public boolean isInRangeToRenderDist(double distance) {
//        return true;
//    }
//
//    private boolean isVehicleMoving() {
//        return currentSpeed != 0;
//    }
//
//    private boolean isVehicleMovingForward() {
//        return currentSpeed > 0;
//    }
//
//    private boolean isVehicleMovingBackward() {
//        return currentSpeed < 0;
//    }
//
//    private void playSoundAtVehicle() {
//        if (this.health <= 0) {
//            return;
//        }
//        if (!isVehicleMoving()) {
//            if (ticksExisted % 5 == 0) playSound(PMCSounds.vehicleIdle, 2f, 1f);
//        } else {
//            if (ticksExisted % 18 == 0) playSound(vehicleSound(), Math.abs(currentSpeed) * 5f, 1f);
//        }
//    }
//
//    @Override
//    protected void entityInit() {
//    }
//
//    @Override
//    protected void readEntityFromNBT(NBTTagCompound compound) {
//        health = compound.getFloat("health");
//        fuel = compound.getFloat("fuel");
//        currentSpeed = compound.getFloat("speed");
//        isBroken = compound.getBoolean("isBroken");
//    }
//
//    @Override
//    protected void writeEntityToNBT(NBTTagCompound compound) {
//        compound.setFloat("health", this.health);
//        compound.setFloat("fuel", this.fuel);
//        compound.setFloat("speed", (float)getSpeedPerTick());
//        compound.setBoolean("isBroken", this.isBroken);
//    }
//
//    @Override
//    public NBTTagCompound encodeNetworkData() {
//        NBTTagCompound nbt = new NBTTagCompound();
//        writeEntityToNBT(nbt);
//        return nbt;
//    }
//
//    @Override
//    public void decodeNetworkData(NBTTagCompound nbt) {
//        readEntityFromNBT(nbt);
//    }
//
//    @Override
//    public boolean canBeCollidedWith() {
//        return true;
//    }
//
//    protected float getPassengerXOffset(int passengerIndex) {
//        return passengerIndex % 2 == 0 ? 0.5f : -0.8f;
//    }
//
//    protected float getPassengerZOffset(int passengerIndex) {
//        return passengerIndex < 2 ? -0.55f : 0.55f;
//    }
//
//    protected void applyYawToEntity(Entity entityToUpdate) {
//        entityToUpdate.setRenderYawOffset(this.rotationYaw);
//        float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
//        float f1 = MathHelper.clamp(f, -105.0F, 105.0F);
//        entityToUpdate.prevRotationYaw += f1 - f;
//        entityToUpdate.rotationYaw += f1 - f;
//        entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
//    }
//
//    public boolean hasFuel() {
//        return fuel > 0;
//    }
//
//    public void refill(EntityPlayer source, float percentage) {
//        if (this.getPassengers().contains(source)) {
//            fillFuel(ItemFuelCan.fuelPercentage * percentage);
//        } else {
//            Pubgmc.logger.warn("{} has attempted to refuel vehicle with ID {}, but he wasn't inside the vehicle!", source, this.getEntityId());
//        }
//    }
//
//    public void burnFuel() {
//        fuel = hasFuel() ? fuel - 0.01f : 0f;
//    }
//
//    public void burnFuel(float amount) {
//        fuel = Math.max(0, fuel - amount);
//    }
//
//    public void fillFuel(float amount) {
//        fuel = Math.min(maxFuel, fuel + amount);
//    }
//
//    @Override
//    public void writeSpawnData(ByteBuf buf) {
//        buf.writeFloat(health);
//        buf.writeFloat(fuel);
//    }
//
//    @Override
//    public void readSpawnData(ByteBuf buf) {
//        health = buf.readFloat();
//        fuel = buf.readFloat();
//    }
//
//    @Override
//    public UUID getCurrentGameId() {
//        return gameId;
//    }
//
//    @Override
//    public void assignGameId(UUID gameId) {
//        this.gameId = gameId;
//    }
//
//    @Override
//    public void onNewGameDetected(UUID newGameId) {
//        setDead();
//    }
//
//    @Override
//    public void onBomb(Entity exploder, Vec3d bombVec, @Nullable IBlockState state, @Nullable Entity entity) {
//        if (world.isRemote) {
//            return;
//        }
//        double strength = ConfigPMC.common.world.bombs.bombStrength.getAsFloat();
//        this.bombMotion = new Vec3d(this.bombMotion.x + bombVec.x, this.bombMotion.y + bombVec.y, this.bombMotion.z + bombVec.z).scale(strength);
//        // this.bombMotion.add(bombVec.scale(bombStrength)); // this should work but actually not
//        if (hasBombMotion()) {
//            this.bomb = true;
//        }
//    }
//
//    @Override
//    public boolean allowBombInteraction(World world, @Nullable IBlockState state, @Nullable Entity entity) {
//        return getSpeedPerTick() < 1;
//    }
//}
