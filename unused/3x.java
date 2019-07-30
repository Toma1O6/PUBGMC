//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class 3x extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer bone22;
	private final ModelRenderer bone23;
	private final ModelRenderer bone25;
	private final ModelRenderer bone26;
	private final ModelRenderer bone24;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;

	public 3x() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 73, 72, -9.5F, -1.0F, -1.5F, 17, 1, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 73, 72, -9.5F, -7.0981F, -5.5981F, 17, 3, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 73, 72, -9.5F, -7.0981F, 4.5981F, 17, 3, 1, 0.0F, true));
		bone.cubeList.add(new ModelBox(bone, 73, 72, -9.5F, -11.1962F, -1.5F, 17, 1, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 73, 72, -5.0F, -0.5F, -2.5F, 8, 2, 5, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 73, 72, -5.0F, 1.5F, -2.0F, 8, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 73, 72, -5.0F, 1.5F, 1.0F, 8, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 73, 72, -5.5F, -13.1962F, -3.0F, 8, 3, 5, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 73, 72, -5.5F, -11.1962F, 2.0F, 8, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 73, 72, 0.5F, -13.1962F, 2.0F, 2, 2, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 73, 72, -5.5F, -13.1962F, 2.0F, 4, 2, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 73, 72, -1.5F, -13.1962F, 2.0F, 2, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 101, 103, -1.5F, -12.1962F, 1.8438F, 2, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 80, 111, -0.5781F, -12.1962F, 2.125F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 97, 101, 0.4063F, -12.4774F, -3.1875F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 100, 102, -1.5938F, -12.4774F, -3.0781F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 97, 100, -3.5938F, -12.4774F, -3.0781F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 96, 118, -4.0938F, -12.9774F, -3.0156F, 6, 2, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 73, 100, -3.0F, -14.6962F, -1.5F, 3, 2, 3, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone2, -0.5236F, 0.0F, 0.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 73, 72, -9.5F, -6.3481F, 1.799F, 17, 3, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 73, 72, -9.5F, -0.25F, -4.299F, 17, 1, 3, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 73, 72, -9.5F, -6.3481F, -8.3971F, 17, 3, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 73, 72, -9.5F, -10.4462F, -4.299F, 17, 1, 3, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone3, -1.0472F, 0.0F, 0.0F);
		bone.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 73, 72, -9.5F, 1.799F, -6.3481F, 17, 1, 3, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 73, 72, -9.5F, -4.299F, -0.25F, 17, 3, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 73, 72, -9.5F, -8.3971F, -6.3481F, 17, 1, 3, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 73, 72, -9.5F, -4.299F, -10.4462F, 17, 3, 1, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone10, 0.0F, 0.0873F, 0.0F);
		bone.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone11, 0.0F, 0.1745F, 0.0F);
		bone.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone12, 0.0F, 0.2618F, 0.0F);
		bone.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone13, 0.0F, 0.3491F, 0.0F);
		bone.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone14, 0.0F, 0.4363F, 0.0F);
		bone.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone15, 0.0F, 0.5236F, 0.0F);
		bone.addChild(bone15);
		bone15.cubeList.add(new ModelBox(bone15, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone16, 0.0F, 0.6109F, 0.0F);
		bone.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone17, 0.0F, 0.6981F, 0.0F);
		bone.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone18, 0.0F, 0.7854F, 0.0F);
		bone.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone19, 0.0F, 0.8727F, 0.0F);
		bone.addChild(bone19);
		bone19.cubeList.add(new ModelBox(bone19, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone20, 0.0F, 0.9599F, 0.0F);
		bone.addChild(bone20);
		bone20.cubeList.add(new ModelBox(bone20, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone21, 0.0F, 1.0472F, 0.0F);
		bone.addChild(bone21);
		bone21.cubeList.add(new ModelBox(bone21, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone22, 0.0F, 1.1345F, 0.0F);
		bone.addChild(bone22);
		bone22.cubeList.add(new ModelBox(bone22, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone23, 0.0F, 1.2217F, 0.0F);
		bone.addChild(bone23);
		bone23.cubeList.add(new ModelBox(bone23, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone25 = new ModelRenderer(this);
		bone25.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone25, 0.0F, 1.3963F, 0.0F);
		bone.addChild(bone25);
		bone25.cubeList.add(new ModelBox(bone25, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone26 = new ModelRenderer(this);
		bone26.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone26, 0.0F, 1.4835F, 0.0F);
		bone.addChild(bone26);
		bone26.cubeList.add(new ModelBox(bone26, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(-1.5F, -13.6962F, 0.0F);
		setRotationAngle(bone24, 0.0F, 1.309F, 0.0F);
		bone.addChild(bone24);
		bone24.cubeList.add(new ModelBox(bone24, 73, 100, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bone4, 0.0F, 0.0F, 0.6981F);
		bone4.cubeList.add(new ModelBox(bone4, 73, 72, -2.4514F, -18.3977F, -1.5F, 1, 6, 3, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 73, 72, -2.4514F, -20.4957F, -5.5981F, 1, 3, 6, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 73, 72, -2.4514F, -20.4957F, -0.4019F, 1, 3, 6, 0.0F, true));
		bone4.cubeList.add(new ModelBox(bone4, 73, 72, -2.4514F, -24.5938F, -1.5F, 1, 5, 3, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone5, -0.5236F, 0.0F, 0.0F);
		bone4.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 73, 72, -2.4514F, -17.9508F, -7.8998F, 1, 3, 4, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 73, 72, -2.4514F, -14.8527F, -10.9979F, 1, 4, 3, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 73, 72, -2.4514F, -17.9508F, -15.0959F, 1, 3, 4, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 73, 72, -2.4514F, -22.0489F, -10.9979F, 1, 4, 3, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone6, -1.0472F, 0.0F, 0.0F);
		bone4.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 73, 72, -2.4514F, -7.8998F, -17.9508F, 1, 4, 3, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 73, 72, -2.4514F, -10.9979F, -14.8527F, 1, 3, 4, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 73, 72, -2.4514F, -15.0959F, -17.9508F, 1, 4, 3, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 73, 72, -2.4514F, -10.9979F, -22.0489F, 1, 3, 4, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-2.0F, 24.0F, 0.0F);
		setRotationAngle(bone7, 0.0F, 0.0F, -0.6981F);
		bone7.cubeList.add(new ModelBox(bone7, 73, 72, 1.4514F, -18.3977F, -1.5F, 1, 6, 3, 0.0F, true));
		bone7.cubeList.add(new ModelBox(bone7, 73, 72, 1.4514F, -20.4957F, -5.5981F, 1, 3, 6, 0.0F, true));
		bone7.cubeList.add(new ModelBox(bone7, 73, 72, 1.4514F, -20.4957F, -0.4019F, 1, 3, 6, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 73, 72, 1.4514F, -24.5938F, -1.5F, 1, 5, 3, 0.0F, true));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone8, -0.5236F, 0.0F, 0.0F);
		bone7.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 73, 72, 1.4514F, -17.9508F, -7.8998F, 1, 3, 4, 0.0F, true));
		bone8.cubeList.add(new ModelBox(bone8, 73, 72, 1.4514F, -14.8527F, -10.9979F, 1, 4, 3, 0.0F, true));
		bone8.cubeList.add(new ModelBox(bone8, 73, 72, 1.4514F, -17.9508F, -15.0959F, 1, 3, 4, 0.0F, true));
		bone8.cubeList.add(new ModelBox(bone8, 73, 72, 1.4514F, -22.0489F, -10.9979F, 1, 4, 3, 0.0F, true));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone9, -1.0472F, 0.0F, 0.0F);
		bone7.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 73, 72, 1.4514F, -7.8998F, -17.9508F, 1, 4, 3, 0.0F, true));
		bone9.cubeList.add(new ModelBox(bone9, 73, 72, 1.4514F, -10.9979F, -14.8527F, 1, 3, 4, 0.0F, true));
		bone9.cubeList.add(new ModelBox(bone9, 73, 72, 1.4514F, -15.0959F, -17.9508F, 1, 4, 3, 0.0F, true));
		bone9.cubeList.add(new ModelBox(bone9, 73, 72, 1.4514F, -10.9979F, -22.0489F, 1, 3, 4, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bone4.render(f5);
		bone7.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}