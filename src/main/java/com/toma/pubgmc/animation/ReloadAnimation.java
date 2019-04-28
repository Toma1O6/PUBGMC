package com.toma.pubgmc.animation;

import javax.vecmath.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;

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
	
	public final void process(boolean reloading)
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
	
	public enum ReloadStyle
	{
		MAGAZINE(new Vector3f(15f, 0f, 10f), EMPTYVEC),
		REVOLVER(EMPTYVEC, EMPTYVEC),
		SINGLE(EMPTYVEC, EMPTYVEC);
		
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
		}
		
		private void handleMagazineStyle(boolean reload, ReloadAnimation a)
		{
			if(reload && !a.isMagFinished())
			{
				a.rx = a.rx < rotation.x ? a.rx + calculateMovement(2.5f) : rotation.x;
				a.rz = a.rz < rotation.z ? a.rz + calculateMovement(2.1f) : rotation.z;
			}
		}
	}
}
