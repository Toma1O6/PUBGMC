package com.toma.pubgmc.animation;

import javax.vecmath.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class ReloadAnimation extends Animation// implements IPartAnimated
{
	/*final ModelRenderer magazine;
	final MagazineMovementStyle magazineStyle;*/
	final ReloadStyle style;
	private float x, y, z;
	private float rx, ry, rz;
	
	public ReloadAnimation(final ReloadStyle reloadStyle)
	{
		this.style = reloadStyle;
	}
	
	/*public ReloadAnimation(final ModelRenderer partToAnimate, final MagazineMovementStyle magazineStyle, final ReloadStyle styleOfReload)
	{
		this.magazine = partToAnimate;
		this.magazineStyle = magazineStyle;
		this.style = styleOfReload;
	}*/
	
	public final void processAnimation(boolean reloading)
	{
		if(!Minecraft.getMinecraft().isGamePaused())
		{
			this.getReloadStyle().process(reloading, this);
		}
	}
	
	@Override
	public Vector3f getMovementVec()
	{
		return new Vector3f(x, y, z);
	}
	
	@Override
	public Vector3f getRotationVector() 
	{
		return new Vector3f(rx, ry, rz);
	}
	
	public ReloadStyle getReloadStyle()
	{
		return style;
	}
	
	private boolean isMagFinished()
	{
		return rx == style.rotation.x && rz == style.rotation.z;
	}
	
	private boolean isMagReturned()
	{
		return rx == 0f && rz == 0f;
	}
	
	private boolean isSingleFinished()
	{
		return ry == style.rotation.y && rz == style.rotation.z && x == style.translation.x && z == style.translation.z;
	}
	
	private boolean isSingleReturned()
	{
		return ry == 0f && rz == 0f && x == 0f && z == 0f;
	}
	
	public enum ReloadStyle
	{
		MAGAZINE(new Vector3f(15f, 0f, -20f), EMPTYVEC),
		REVOLVER(EMPTYVEC, EMPTYVEC),
		SINGLE(new Vector3f(0f, 10f, 15f), new Vector3f(-0.05f, 0f, 0.05f));
		
		private final Vector3f rotation, translation;
		
		private ReloadStyle(final Vector3f rotation, final Vector3f translation)
		{
			this.rotation = rotation;
			this.translation = translation;
		}
		
		public final void process(boolean reload, ReloadAnimation animation)
		{
			if(this.equals(MAGAZINE)) {
				handleMagazineStyle(reload, animation);
			}
			else if(this.equals(SINGLE)) {
				handleSingleStyle(reload, animation);
			}
		}
		
		private void handleMagazineStyle(boolean reload, ReloadAnimation a)
		{
			if(reload && !a.isMagFinished())
			{
				a.rx = a.rx < rotation.x ? a.rx + calculateMovement(2.5f) : rotation.x;
				a.rz = a.rz > rotation.z ? a.rz - calculateMovement(2.7f) : rotation.z;
			}
			
			else if(!reload && !a.isMagReturned())
			{
				a.rx = a.rx > 0f ? a.rx - calculateMovement(2.5f) : 0f;
				a.rz = a.rz < 0f ? a.rz + calculateMovement(2.7f) : 0f;
			}
			
			GlStateManager.rotate(a.rx, 1f, 0f, 0f);
			GlStateManager.rotate(a.rz, 0f, 0f, 1f);
		}
		
		private void handleSingleStyle(boolean reload, ReloadAnimation a)
		{
			if(reload && !a.isSingleFinished())
			{
				a.ry = a.ry < rotation.y ? a.ry + calculateMovement(2.5f) : rotation.y;
				a.rz = a.rz < rotation.z ? a.rz + calculateMovement(2.5f) : rotation.z;
				a.x = a.x > translation.x ? a.x - calculateMovement(0.02f) : translation.x;
				a.z = a.z < translation.z ? a.z + calculateMovement(0.02f) : translation.z;
			}
			
			else if(!reload && !a.isSingleReturned())
			{
				a.ry = a.ry > 0f ? a.ry - calculateMovement(2.5f) : 0f;
				a.rz = a.rz > 0f ? a.rz - calculateMovement(2.5f) : 0f;
				a.x = a.x < 0f ? a.x + calculateMovement(0.02f) : 0f;
				a.z = a.z > 0f ? a.z - calculateMovement(0.02f) : 0f;
			}
			
			GlStateManager.rotate(a.ry, 0f, 1f, 0f);
			GlStateManager.rotate(a.rz, 0f, 0f, 1f);
			GlStateManager.translate(a.x, 0f, a.z);
		}
	}
}
