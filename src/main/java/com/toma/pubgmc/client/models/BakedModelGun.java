package com.toma.pubgmc.client.models;

import java.util.Collections;
import java.util.List;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import org.apache.commons.lang3.tuple.Pair;

import com.toma.pubgmc.animation.Animation;
import com.toma.pubgmc.client.renderer.WeaponTEISR;
import com.toma.pubgmc.client.util.ModelDebugger;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.items.guns.GunBase;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.model.TRSRTransformation;

public class BakedModelGun implements IBakedModel
{
	IPlayerData data = null;
	EntityPlayerSP player = null;
	ItemStack held = null;
	
	@Override
	public boolean isBuiltInRenderer()
	{
		return true;
	}
	
	@Override
	public ItemOverrideList getOverrides()
	{
		return ItemOverrideList.NONE;
	}
	
	@Override
	public TextureAtlasSprite getParticleTexture()
	{
		TextureMap map = Minecraft.getMinecraft().getTextureMapBlocks();
		return map.getMissingSprite();
	}
	
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand)
	{
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public boolean isAmbientOcclusion() 
	{
		return false;
	}
	
	@Override
	public boolean isGui3d()
	{
		return false;
	}
	
	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType)
	{
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		TRSRTransformation trsrt = new TRSRTransformation(matrix);
		Vector3f transl = new Vector3f(0f, 0f, 0f);
		Vector3f scale = new Vector3f(1f, 1f, 1f);
		Quat4f leftRot = new Quat4f(0f, 1f, 0f, 0f);
		Quat4f rightRot = new Quat4f(0f, 1f, 0f, 0f);
		
		if(player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
		{
			// prevent animations not working after switching world
			if(player != Minecraft.getMinecraft().player)
			{
				player = Minecraft.getMinecraft().player;
				data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			}
			
			data = data == null ? player.getCapability(PlayerDataProvider.PLAYER_DATA, null) : data;
			if(player.getHeldItemMainhand().getItem() instanceof GunBase) {
				held = player.getHeldItemMainhand();
			}
		}
		else
		{
			player = Minecraft.getMinecraft().player;
			return Pair.of(this, trsrt.getMatrix());
		}
		
		switch(cameraTransformType)
		{
			case GUI: {
				leftRot = new Quat4f(-90.0f, 45.0f, 90.0f, 0f);
				rightRot = new Quat4f(15f, 0f, 0f, 0f);
				trsrt = new TRSRTransformation(new Vector3f(0f, -0.1f, 0f), leftRot, new Vector3f(0.5f, 0.5f, 0.5f), rightRot);
				break;
			}
			
			// Implement animations here
			case FIRST_PERSON_RIGHT_HAND: 
			{
				this.process();
				
				transl = this.getTranslation();
				trsrt = new TRSRTransformation(transl, leftRot, scale, rightRot);
			}
			
			// Third person animations, sometime later propably
			case THIRD_PERSON_RIGHT_HAND: {
				trsrt = new TRSRTransformation(transl, leftRot, scale, rightRot);
			}
			
			default: break;
		}
		
		return Pair.of(this, trsrt.getMatrix());
	}
	
	private Quat4f debugQuat0(float x, float y, float z)
	{
		return new Quat4f(x, y, z, 0f);
	}
	
	private Quat4f debugQuat1()
	{
		return new Quat4f(ModelDebugger.X, ModelDebugger.Y, ModelDebugger.Z, 0f);
	}
	
	private void process()
	{
		((GunBase)held.getItem()).getWeaponModel().processAnimations(data.isAiming());
	}
	
	private Vector3f getTranslation()
	{
		return ((GunBase)held.getItem()).getWeaponModel().enableADS(held) ? ((GunBase)held.getItem()).getWeaponModel().getMovementVecFromAnimations() : Animation.EMPTYVEC;
	}
}
