package com.toma.pubgmc.common.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.handlers.FlashHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityFlashbang extends Entity implements IEntityAdditionalSpawnData {

	public static final Map<UUID, Integer> FLASHED_PLAYERS = new HashMap<>();
	private EntityLivingBase thrower;
	private int fuse;
	
	public EntityFlashbang(World world) {
		super(world);
		this.setSize(0.15f, 0.15f);
		fuse = 60;
	}

	public EntityFlashbang(World world, EntityLivingBase thrower, boolean rightClick) {
		this(world);
		this.thrower = thrower;
		this.setPositionAndRotation(thrower.posX, thrower.posY, thrower.posZ, thrower.rotationYawHead, thrower.rotationPitch);
		boolean sprint = thrower.isSprinting();
		boolean right = rightClick;
		motionX = (this.getLookVec().x*1.5)*(sprint ? 1.25 : 1)*(right ? 1 : 0.5);
		motionY = (this.getLookVec().y*1.5)*(sprint ? 1.25 : 1)*(right ? 1 : 0.5);
		motionZ = (this.getLookVec().z*1.5)*(sprint ? 1.25 : 1)*(right ? 1 : 0.5);
	}
	
	@Override
	public void onUpdate() {
		motionX *= 0.98;
		motionY -= 0.04;
		motionZ *= 0.98;
		if(onGround) {
			motionX *= 0.8;
			motionZ *= 0.8;
		}
        if(Math.abs(motionX) < 0.1 && Math.abs(motionZ) < 0.1) {
        	motionX = 0;
        	motionZ = 0;
        }
        --fuse;
		move(MoverType.SELF, motionX, motionY, motionZ);
		if(fuse <= 0) {
			if(world.isRemote) {
				Pubgmc.proxy.playDelayedSound(SoundEvents.ENTITY_GENERIC_EXPLODE, posX, posY, posZ, 5f);
				world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, posX, posY, posZ, 0, 0, 0);
				for(int i = 0; i < 7; i++) {
					world.spawnParticle(EnumParticleTypes.CLOUD, posX, posY, posZ, rand.nextDouble()/8-rand.nextDouble()/8, rand.nextDouble()/4, rand.nextDouble()/8-rand.nextDouble()/8);
				}
			}
			this.flashPlayers();
			this.setDead();
		}
		super.onUpdate();
	}
	
	public void flashPlayers() {
		List<EntityPlayer> entityList = world.getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.offset(getPosition()).grow(14));
		Vec3d start = PUBGMCUtil.getPositionVec(this);
		entityList.forEach(e -> {
			Vec3d entityVec = new Vec3d(e.posX, e.posY + e.getEyeHeight(), e.posZ);
			RayTraceResult rayTrace = this.world.rayTraceBlocks(start, entityVec, false, true, false);
			// TODO improve raytracing for opaque and non solid blocks
			if(rayTrace == null) {
				FlashHandler.flashPlayer(e, this);
			}
		});
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		PUBGMCUtil.writeBasicEntityNBT(compound, this);
		compound.setInteger("throwerID", thrower.getEntityId());
		compound.setInteger("fuse", fuse);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		PUBGMCUtil.readBasicEntityNBT(compound, this);
		thrower = (EntityLivingBase) world.getEntityByID(compound.getInteger("throwerID"));
		fuse = compound.getInteger("fuse");
	}
	
	@Override
	public void writeSpawnData(ByteBuf buf) {
		buf.writeInt(thrower.getEntityId());
		buf.writeInt(fuse);
	}
	
	@Override
	public void readSpawnData(ByteBuf buf) {
		thrower = (EntityLivingBase)world.getEntityByID(buf.readInt());
		fuse = buf.readInt();
	}
	
	@Override
	protected void entityInit() {}
}
