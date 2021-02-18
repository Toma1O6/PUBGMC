package dev.toma.pubgmc.client.models.atachments;

import dev.toma.pubgmc.client.models.ModelAtachmentBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class AttachmentScopeHolo extends ModelAtachmentBase {

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
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer bone17;
	private final ModelRenderer bone16;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;

	public AttachmentScopeHolo() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 69, 72, -3.0F, -2.0F, -10.0F, 6, 2, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 69, 72, -2.0F, -2.0F, -11.7321F, 4, 2, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 69, 72, -3.0F, -3.0F, -2.0F, 6, 3, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 69, 72, -3.0F, -1.0F, 0.0F, 6, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 69, 72, -3.0F, -3.5F, -2.0F, 6, 1, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 69, 72, 2.0F, -2.5F, 0.0F, 1, 2, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 72, 70, -3.0F, -2.5F, 0.0F, 4, 2, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 104, 125, 1.0F, -2.5781F, -0.0938F, 1, 2, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 98, 103, 1.0F, -2.4375F, 0.0469F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 69, 72, 2.0F, -5.4375F, -2.0938F, 1, 2, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 69, 72, -3.0F, -5.4375F, -2.0938F, 1, 2, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 69, 72, 0.316F, -7.3169F, -2.0938F, 2, 1, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 69, 72, -2.316F, -7.3169F, -2.0938F, 3, 1, 3, 0.0F, true));
		bone.cubeList.add(new ModelBox(bone, 69, 72, -2.5F, 0.0F, -12.0F, 1, 1, 13, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 69, 72, 1.5F, 0.0F, -12.0F, 1, 1, 13, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-2.5F, -1.0F, -13.0F);
		setRotationAngle(bone2, 0.0F, -0.5236F, 0.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 69, 72, 1.067F, -1.0F, 0.8481F, 1, 2, 2, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(2.5F, -1.0F, -13.0F);
		setRotationAngle(bone3, 0.0F, 0.5236F, 0.0F);
		bone.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 69, 72, -2.067F, -1.0F, 0.8481F, 1, 2, 2, 0.0F, true));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -1.0F, -10.7321F);
		setRotationAngle(bone4, 0.4363F, 0.0F, 0.0F);
		bone.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 69, 72, -1.0F, -0.7876F, 0.0416F, 2, 2, 3, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 119, 127, -0.5F, 0.0405F, 3.0572F, 1, 1, 0, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone5, 0.2618F, 0.0F, 0.0F);
		bone.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 69, 72, -3.0F, -3.8984F, -7.026F, 6, 2, 6, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(2.5F, -6.5F, 0.5F);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.3491F);
		bone.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 69, 72, -0.8936F, -0.8306F, -2.5938F, 1, 2, 3, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-2.5F, -6.5F, 0.5F);
		setRotationAngle(bone7, 0.0F, 0.0F, 0.3491F);
		bone.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 69, 72, -0.1064F, -0.8306F, -2.5938F, 1, 2, 3, 0.0F, true));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 80, 107, -3.3438F, -1.7969F, -1.5469F, 1, 1, 1, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-2.8438F, -1.2969F, -1.0469F);
		setRotationAngle(bone9, 0.3491F, 0.0F, 0.0F);
		bone8.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 80, 107, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-2.8438F, -1.2969F, -1.0469F);
		setRotationAngle(bone10, 0.6981F, 0.0F, 0.0F);
		bone8.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 80, 107, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-2.8438F, -1.2969F, -1.0469F);
		setRotationAngle(bone11, 1.0472F, 0.0F, 0.0F);
		bone8.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 80, 107, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, -1.7031F);
		bone.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 80, 107, -3.3438F, -1.7969F, -1.5469F, 1, 1, 1, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(-2.8438F, -1.2969F, -1.0469F);
		setRotationAngle(bone13, 0.3491F, 0.0F, 0.0F);
		bone12.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 80, 107, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(-2.8438F, -1.2969F, -1.0469F);
		setRotationAngle(bone14, 0.6981F, 0.0F, 0.0F);
		bone12.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 80, 107, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(-2.8438F, -1.2969F, -1.0469F);
		setRotationAngle(bone15, 1.0472F, 0.0F, 0.0F);
		bone12.addChild(bone15);
		bone15.cubeList.add(new ModelBox(bone15, 80, 107, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.3125F, -2.6875F, 0.3125F);
		setRotationAngle(bone17, -0.7854F, 0.0F, 1.5708F);
		bone.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 104, 102, -0.2812F, -0.6657F, -0.6657F, 1, 1, 1, 0.0F, false));

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.8125F, -1.0313F, 0.3125F);
		setRotationAngle(bone16, -0.7854F, 0.0F, 1.5708F);
		bone.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 104, 102, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(-0.1875F, -1.0313F, 0.3125F);
		setRotationAngle(bone18, -0.7854F, 0.0F, 1.5708F);
		bone.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 104, 102, -0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(-0.1875F, -1.0313F, 0.3125F);
		setRotationAngle(bone19, -0.7854F, 0.0F, 3.1416F);
		bone.addChild(bone19);
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, -0.1464F, -0.1464F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, -0.8536F, -0.8536F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, -0.1464F, -1.5607F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 0.5607F, -2.2678F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 0.9142F, -2.6213F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 0.2071F, -1.9142F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, -0.5F, -1.2071F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 3.0355F, -4.7426F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 2.3284F, -4.0355F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 1.6213F, -3.3284F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 1.2678F, -2.9749F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 1.9749F, -3.682F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 2.682F, -4.3891F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 5.1569F, -6.864F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 4.4497F, -6.1569F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 3.7426F, -5.4497F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 3.3891F, -5.0962F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 4.0962F, -5.8033F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 4.8033F, -6.5104F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 6.5711F, -8.2782F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 5.864F, -7.5711F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 5.5104F, -7.2175F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 6.2175F, -7.9246F, 1, 1, 1, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 104, 102, -1.0F, 6.9246F, -8.6317F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render() {
		bone.render(1f);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}