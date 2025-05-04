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
//    @Override
//    public ITextComponent getComponent() {
//        String key = EntityList.getEntityString(this);
//        if (key == null) {
//            key = "generic";
//        }
//        return new TextComponentTranslation("entity." + key + ".name");
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
//    public boolean canBeCollidedWith() {
//        return true;
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
//}
