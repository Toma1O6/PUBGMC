package dev.toma.pubgmc.common.entity;

import dev.toma.pubgmc.api.game.GameObject;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

public class EntityFlare extends Entity implements GameObject {

    private int timer;
    private double height;
    private UUID gameId = GameHelper.DEFAULT_UUID;

    public EntityFlare(World worldIn) {
        super(worldIn);
        this.setSize(0.1f, 0.1f);
        this.setInvisible(false);
        preventEntitySpawning = true;
    }

    public EntityFlare(World worldIn, EntityLivingBase shooter) {
        this(worldIn);
        this.timer = 0;
        this.height = shooter.posY;

        Vec3d direct = getVectorForRotation(shooter.rotationPitch, shooter.getRotationYawHead());
        this.motionX = direct.x * 2;
        this.motionY = direct.y * 2;
        this.motionZ = direct.z * 2;

        this.setPosition(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ);

        updateHeading();
    }

    private void updateHeading() {
        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
        this.rotationPitch = (float) (MathHelper.atan2(this.motionY, f) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    @Override
    public void onEntityUpdate() {
        if (this.posY <= 0) {
            this.setDead();
        }

        if (this.ticksExisted >= 500) {
            this.setDead();
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        updateHeading();

        world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, 0.5, 0, 0.5, 0);
        world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, 0.5, 0, -0.5, 0);
        world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, -0.5, 0, 0.5, 0);
        world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, -0.5, 0, -0.5, 0);

        if (this.posY >= height + 100) {
            timer++;
            this.motionX = 0;
            this.motionY = 0;
            this.motionZ = 0;
            world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX, posY, posZ, 0, 0, 0, 0);

            if (timer >= 400 && !world.isRemote) {
                world.playSound(null, posX, posY, posZ, PMCSounds.airdrop_plane_fly_by, SoundCategory.MASTER, 15f, 1f);
                world.createExplosion(this, posX, posY, posZ, 3.0f, false);
                PUBGMCUtil.spawnAirdrop(world, getPosition(), true);
                this.setDead();
            }
        }

        world.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY, posZ, 0, 0, 0, 0);

        move(MoverType.SELF, motionX, motionY, motionZ);
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return true;
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("flareTimer", timer);
        compound.setUniqueId("gameId", gameId);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        timer = compound.getInteger("flare_timer");
        gameId = compound.getUniqueId("gameId");
    }

    @Override
    public UUID getCurrentGameId() {
        return gameId;
    }

    @Override
    public void assignGameId(UUID gameId) {
        this.gameId = gameId;
    }

    @Override
    public void onNewGameDetected(UUID newGameId) {
        setDead();
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }
}
