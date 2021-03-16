package dev.toma.pubgmc.client.models.atachments;

import dev.toma.pubgmc.common.items.attachment.ItemScope;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelScope4x extends ModelAttachment<ItemScope> {
	private final ModelRenderer acog;
	private final ModelRenderer bone1;
	private final ModelRenderer bone;
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
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone2;

	public ModelScope4x() {
		textureWidth = 128;
		textureHeight = 128;

		acog = new ModelRenderer(this);
		acog.setRotationPoint(0.0F, 24.0F, 0.0F);

		bone1 = new ModelRenderer(this);
		bone1.setRotationPoint(0.0F, 0.0F, 0.0F);
		acog.addChild(bone1);
		bone1.cubeList.add(new ModelBox(bone1, 75, 69, -2.0F, 3.8284F, -10.0F, 4, 1, 17, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 75, 69, -2.0F, -4.8284F, -10.0F, 4, 1, 17, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 75, 69, -4.8284F, -2.0F, -10.0F, 1, 4, 17, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 75, 69, 3.8284F, -2.0F, -10.0F, 1, 4, 17, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -2.0F, -5.3284F, 7.0F, 4, 1, 6, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -5.3284F, -2.0F, 7.0F, 1, 4, 6, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -2.0F, 4.3284F, 7.0F, 4, 1, 6, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, 4.3284F, -2.0F, 7.0F, 1, 4, 6, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 81, 107, -4.5F, -3.0F, 11.0625F, 1, 6, 1, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 81, 107, 3.5F, -3.0F, 11.0625F, 1, 6, 1, 0.0F, true));
		bone1.cubeList.add(new ModelBox(bone1, 81, 107, -3.5F, -4.0F, 11.0625F, 1, 8, 1, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 81, 107, 2.5F, -4.0F, 11.0625F, 1, 8, 1, 0.0F, true));
		bone1.cubeList.add(new ModelBox(bone1, 81, 107, -2.5F, -4.5F, 11.0625F, 5, 9, 1, 0.0F, true));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -5.7426F, -2.5F, -15.0F, 1, 5, 5, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -2.5F, 4.7426F, -15.0F, 5, 1, 16, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, 4.7426F, -2.5F, -15.0F, 1, 5, 5, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -2.5F, -5.7426F, -17.0F, 5, 1, 7, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, 4.7426F, -2.5F, -16.0F, 1, 3, 1, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -5.7426F, -2.5F, -16.0F, 1, 3, 1, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -1.0F, -5.7426F, -18.0F, 2, 1, 1, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -2.0F, -5.8284F, -1.0F, 4, 1, 2, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -5.8284F, -2.0F, -1.0F, 1, 4, 2, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, 4.8284F, -2.0F, -1.0F, 1, 4, 2, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 97, 74, 5.8284F, -1.0F, -1.0F, 1, 2, 2, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -1.5F, -6.8284F, -1.5F, 3, 1, 3, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -2.5F, 5.7426F, -20.0F, 5, 1, 21, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, -2.5F, 6.7426F, -20.0F, 1, 1, 21, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 78, 74, 1.5F, 6.7426F, -20.0F, 1, 1, 21, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 121, 100, 1.5938F, 4.8833F, -8.3281F, 1, 1, 1, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 121, 100, 1.5938F, 4.8833F, -5.7188F, 1, 1, 1, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 81, 107, -3.0F, -5.0F, -12.2344F, 6, 10, 1, 0.0F, true));
		bone1.cubeList.add(new ModelBox(bone1, 81, 107, -4.0F, -4.0F, -12.2344F, 1, 8, 1, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 81, 107, -5.0F, -3.0F, -12.2344F, 1, 6, 1, 0.0F, false));
		bone1.cubeList.add(new ModelBox(bone1, 81, 107, 3.0F, -4.0F, -12.2344F, 1, 8, 1, 0.0F, true));
		bone1.cubeList.add(new ModelBox(bone1, 81, 107, 4.0F, -3.0F, -12.2344F, 1, 6, 1, 0.0F, true));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone, -0.1745F, 0.0F, 0.0F);
		bone1.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 97, 74, 5.8284F, -1.0F, -1.0F, 1, 2, 2, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone3, -0.3491F, 0.0F, 0.0F);
		bone1.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 97, 74, 5.8284F, -1.0F, -1.0F, 1, 2, 2, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone4, -0.5236F, 0.0F, 0.0F);
		bone1.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 97, 74, 5.8284F, -1.0F, -1.0F, 1, 2, 2, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone5, -0.6981F, 0.0F, 0.0F);
		bone1.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 97, 74, 5.8284F, -1.0F, -1.0F, 1, 2, 2, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone6, -0.8727F, 0.0F, 0.0F);
		bone1.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 97, 74, 5.8284F, -1.0F, -1.0F, 1, 2, 2, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone7, -1.0472F, 0.0F, 0.0F);
		bone1.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 97, 74, 5.8284F, -1.0F, -1.0F, 1, 2, 2, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone8, -1.2217F, 0.0F, 0.0F);
		bone1.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 97, 74, 5.8284F, -1.0F, -1.0F, 1, 2, 2, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone9, -1.3963F, 0.0F, 0.0F);
		bone1.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 97, 74, 5.8284F, -1.0F, -1.0F, 1, 2, 2, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone10, 0.0F, -0.1745F, 0.0F);
		bone1.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 78, 74, -1.5F, -6.8284F, -1.5F, 3, 1, 3, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone11, 0.0F, -0.3491F, 0.0F);
		bone1.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 78, 74, -1.5F, -6.8284F, -1.5F, 3, 1, 3, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone12, 0.0F, -0.5236F, 0.0F);
		bone1.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 78, 74, -1.5F, -6.8284F, -1.5F, 3, 1, 3, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone13, 0.0F, -0.6981F, 0.0F);
		bone1.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 78, 74, -1.5F, -6.8284F, -1.5F, 3, 1, 3, 0.0F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone14, 0.0F, -0.8727F, 0.0F);
		bone1.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 78, 74, -1.5F, -6.8284F, -1.5F, 3, 1, 3, 0.0F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone15, 0.0F, -1.0472F, 0.0F);
		bone1.addChild(bone15);
		bone15.cubeList.add(new ModelBox(bone15, 78, 74, -1.5F, -6.8284F, -1.5F, 3, 1, 3, 0.0F, false));

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone16, 0.0F, -1.2217F, 0.0F);
		bone1.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 78, 74, -1.5F, -6.8284F, -1.5F, 3, 1, 3, 0.0F, false));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone17, 0.0F, -1.3963F, 0.0F);
		bone1.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 78, 74, -1.5F, -6.8284F, -1.5F, 3, 1, 3, 0.0F, false));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, 6.7426F, -20.0F);
		setRotationAngle(bone18, 0.1571F, 0.0F, 0.0F);
		bone1.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 78, 74, -2.5F, -0.9877F, 0.1564F, 5, 1, 6, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.7854F);
		acog.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 75, 69, -2.0F, 3.8284F, -10.0F, 4, 1, 17, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 75, 69, -2.0F, -4.8284F, -10.0F, 4, 1, 17, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 75, 69, -4.8284F, -2.0F, -10.0F, 1, 4, 17, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 75, 69, 3.8284F, -2.0F, -10.0F, 1, 4, 17, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, -2.0F, 4.3284F, 7.0F, 4, 1, 6, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, 4.3284F, -2.0F, 7.0F, 1, 4, 6, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, -2.0F, -5.3284F, 7.0F, 4, 1, 6, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, -5.3284F, -2.0F, 7.0F, 1, 4, 6, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, -5.7426F, -2.5F, -16.0F, 1, 5, 6, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, -2.5F, -5.7426F, -16.0F, 5, 1, 6, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, 4.7426F, -2.5F, -15.0F, 1, 5, 5, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, -2.5F, 4.7426F, -15.0F, 5, 1, 5, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, -2.5F, -5.7426F, -17.0F, 2, 1, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, -5.7426F, -2.5F, -17.0F, 1, 2, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, -2.0F, -5.8284F, -1.0F, 4, 1, 2, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, -5.8284F, -2.0F, -1.0F, 1, 4, 2, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, -2.0F, 4.8284F, -1.0F, 4, 1, 2, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 74, 4.8284F, -2.0F, -1.0F, 1, 4, 2, 0.0F, false));
	}

	@Override
	public void render(float aimPct) {
		acog.render(1f);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}