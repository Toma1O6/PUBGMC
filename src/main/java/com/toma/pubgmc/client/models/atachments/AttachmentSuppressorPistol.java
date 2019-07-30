package com.toma.pubgmc.client.models.atachments;

import com.toma.pubgmc.client.models.ModelAtachmentBase;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class AttachmentSuppressorPistol extends ModelAtachmentBase {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;

	public AttachmentSuppressorPistol() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 70, 68, -1.0F, 1.7321F, -2.1719F, 2, 2, 20, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 70, 68, -3.7321F, -1.0F, -2.1719F, 3, 2, 20, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 70, 68, 0.7321F, -1.0F, -2.1719F, 3, 2, 20, 0.0F, true));
		bone.cubeList.add(new ModelBox(bone, 70, 68, -1.0F, -3.7321F, -2.1719F, 2, 2, 20, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 70, 68, -1.0F, 0.7321F, -7.1719F, 2, 3, 5, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 70, 68, -3.7321F, -1.0F, -7.1719F, 3, 2, 5, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 70, 68, 0.7321F, -1.0F, -7.1719F, 3, 2, 5, 0.0F, true));
		bone.cubeList.add(new ModelBox(bone, 70, 68, -1.0F, -3.7321F, -7.1719F, 2, 3, 5, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.5236F);
		bone2.cubeList.add(new ModelBox(bone2, 70, 68, -1.0F, -0.2679F, -2.1719F, 2, 4, 20, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 70, 68, 0.7321F, -1.0F, -2.1719F, 3, 2, 20, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 70, 68, -3.7321F, -1.0F, -2.1719F, 3, 2, 20, 0.0F, true));
		bone2.cubeList.add(new ModelBox(bone2, 70, 68, -1.0F, -3.7321F, -2.1719F, 2, 4, 20, 0.0F, true));
		bone2.cubeList.add(new ModelBox(bone2, 70, 68, -1.0F, 0.7321F, -7.1719F, 2, 3, 5, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 70, 68, 0.7321F, -1.0F, -7.1719F, 3, 2, 5, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 70, 68, -3.7321F, -1.0F, -7.1719F, 3, 2, 5, 0.0F, true));
		bone2.cubeList.add(new ModelBox(bone2, 70, 68, -1.0F, -3.7321F, -7.1719F, 2, 3, 5, 0.0F, true));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.5236F);
		bone3.cubeList.add(new ModelBox(bone3, 70, 68, -1.0F, -0.2679F, -2.1719F, 2, 4, 20, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 70, 68, -3.7321F, -1.0F, -2.1719F, 3, 2, 20, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 70, 68, -1.0F, -3.7321F, -2.1719F, 2, 4, 20, 0.0F, true));
		bone3.cubeList.add(new ModelBox(bone3, 70, 68, 0.7321F, -1.0F, -2.1719F, 3, 2, 20, 0.0F, true));
		bone3.cubeList.add(new ModelBox(bone3, 70, 68, -1.0F, 0.7321F, -7.1719F, 2, 3, 5, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 70, 68, -3.7321F, -1.0F, -7.1719F, 3, 2, 5, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 70, 68, -1.0F, -3.7321F, -7.1719F, 2, 3, 5, 0.0F, true));
		bone3.cubeList.add(new ModelBox(bone3, 70, 68, 0.7321F, -1.0F, -7.1719F, 3, 2, 5, 0.0F, true));
	}

	@Override
	public void render() {
		bone.render(1f);
		bone2.render(1f);
		bone3.render(1f);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}