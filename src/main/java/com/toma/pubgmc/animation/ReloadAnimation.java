package com.toma.pubgmc.animation;

import javax.vecmath.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;

public class ReloadAnimation extends Animation implements IPartAnimated
{
	final ModelRenderer magazine;
	final MagazineMovementStyle magazineStyle;
	final ReloadStyle style;
	private float x, y, z;
	private float rx, ry, rz;
	
	public ReloadAnimation(final ModelRenderer partToAnimate, final MagazineMovementStyle magazineStyle, final ReloadStyle styleOfReload)
	{
		this.magazine = partToAnimate;
		this.magazineStyle = magazineStyle;
		this.style = styleOfReload;
	}
	
	public final void process(boolean reloading)
	{
		if(!Minecraft.getMinecraft().isGamePaused())
		{
			this.getReloadStyle().process(reloading);
		}
	}
	
	@Override
	public Vector3f getMovementVec()
	{
		return null;
	}
	
	@Override
	public Vector3f getRotationVector() 
	{
		return null;
	}
	
	@Override
	public Vector3f getPartMovement()
	{
		return this.getMagazineMovementPattern().getMovement();
	}
	
	@Override
	public Vector3f getPartRotation()
	{
		return this.getMagazineMovementPattern().getRotation();
	}
	
	@Override
	public MagazineMovementStyle getMagazineMovementPattern() 
	{
		return magazineStyle;
	}
	
	@Override
	public ModelRenderer getPart() 
	{
		return magazine;
	}
	
	public ReloadStyle getReloadStyle()
	{
		return style;
	}
	
	public enum ReloadStyle
	{
		MAGAZINE(EMPTYVEC, EMPTYVEC),
		REVOLVER(EMPTYVEC, EMPTYVEC),
		SINGLE(EMPTYVEC, EMPTYVEC);
		
		private ReloadStyle(final Vector3f rotation, final Vector3f translation)
		{
			
		}
		
		public final void process(boolean reload)
		{
			
		}
	}
}
