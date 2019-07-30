//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class unknown extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;

	public unknown() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 67, 68, -6.5156F, -5.0F, 5.0F, 13, 5, 10, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 72, 72, -6.5156F, -4.0F, -6.0F, 13, 4, 11, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 65, 67, -7.5156F, -4.0F, -19.0F, 15, 4, 13, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 65, 67, -7.5156F, -6.0F, -19.0F, 15, 2, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 65, 67, -6.5156F, -6.0F, -20.0F, 13, 6, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 65, 67, 7.5197F, -13.8637F, -20.0F, 1, 4, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 65, 67, -8.5197F, -13.8637F, -20.0F, 1, 4, 3, 0.0F, true));
		bone.cubeList.add(new ModelBox(bone, 65, 67, -7.5156F, -17.7274F, -20.0F, 15, 1, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 65, 67, -7.5156F, -6.7274F, -20.0F, 15, 1, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 67, 68, -3.0F, -6.4011F, -0.1788F, 2, 5, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 67, 68, 1.0F, -6.4011F, -0.1788F, 2, 5, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 67, 68, -1.0F, -6.4011F, -0.1788F, 2, 1, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 98, 104, -1.0F, -5.4011F, 0.0712F, 2, 2, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 100, 105, 2.2031F, -6.1043F, 0.8212F, 1, 1, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(9.5F, -2.0F, -4.5F);
		setRotationAngle(bone2, 0.0F, -0.4363F, 0.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 72, 72, -3.4153F, -1.9994F, -0.3029F, 1, 4, 3, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-9.5F, -2.0F, -4.5F);
		setRotationAngle(bone3, 0.0F, 0.4363F, 0.0F);
		bone.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 72, 72, 2.4778F, -1.9994F, -0.7255F, 1, 4, 3, 0.0F, true));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -6.5F, 3.0F);
		setRotationAngle(bone4, 0.1745F, 0.0F, 0.0F);
		bone.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 72, 72, -6.5156F, 1.8245F, -4.2909F, 13, 1, 6, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, -6.5F, 3.0F);
		setRotationAngle(bone5, -0.6109F, 0.0F, 0.0F);
		bone.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 72, 72, -7.5156F, 11.8811F, -16.0962F, 15, 2, 4, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-15.0F, 0.0F, -10.0F);
		setRotationAngle(bone6, 0.0F, -0.7854F, 0.0F);
		bone.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 65, 67, 8.5349F, -6.0F, -22.2628F, 1, 6, 1, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 65, 67, 8.1207F, -6.0F, -22.2628F, 1, 6, 1, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 65, 67, -1.0717F, -6.0F, -13.0704F, 1, 6, 1, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 65, 67, -1.0717F, -6.0F, -12.6562F, 1, 6, 1, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(6.9844F, -7.0F, -18.0F);
		setRotationAngle(bone7, 0.0F, 0.0F, 0.2618F);
		bone.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 65, 67, -0.2582F, -3.1635F, -2.0F, 1, 8, 3, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 65, 67, -16.7522F, -6.6171F, -2.0F, 1, 4, 3, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-6.9844F, -7.0F, -18.0F);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.2618F);
		bone.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 65, 67, -0.7418F, -3.1635F, -2.0F, 1, 8, 3, 0.0F, true));
		bone8.cubeList.add(new ModelBox(bone8, 65, 67, 15.7522F, -6.6171F, -2.0F, 1, 4, 3, 0.0F, true));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.7813F, -2.6875F, 4.7188F);
		setRotationAngle(bone9, 0.0F, 0.0F, 0.5236F);
		bone.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 67, 68, 3.9074F, -5.1333F, 5.5F, 2, 4, 2, 0.0F, false));
		bone9.cubeList.add(new ModelBox(bone9, 67, 68, 3.9074F, -5.1333F, 1.5F, 2, 4, 2, 0.0F, false));
		bone9.cubeList.add(new ModelBox(bone9, 79, 102, 3.9074F, -6.1333F, 1.0F, 2, 1, 3, 0.0F, false));
		bone9.cubeList.add(new ModelBox(bone9, 79, 102, 3.9074F, -6.1333F, 5.0F, 2, 1, 3, 0.0F, false));
		bone9.cubeList.add(new ModelBox(bone9, 79, 102, 3.4074F, -6.1333F, 1.5F, 3, 1, 2, 0.0F, false));
		bone9.cubeList.add(new ModelBox(bone9, 79, 102, 3.4074F, -6.1333F, 5.5F, 3, 1, 2, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, -3.5625F, 5.3594F);
		setRotationAngle(bone10, -0.4363F, 0.0F, 0.0F);
		bone.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 67, 68, -3.0F, -1.5F, -3.5F, 6, 3, 7, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}