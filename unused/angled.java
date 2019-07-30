//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class angled extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone7;

	public angled() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 71, 69, -2.0F, -6.0F, -10.0F, 4, 2, 19, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 71, 69, 1.7071F, -6.7071F, -10.0F, 1, 2, 19, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 71, 69, -2.7071F, -6.7071F, -10.0F, 1, 2, 19, 0.0F, true));
		bone.cubeList.add(new ModelBox(bone, 78, 70, -2.0F, -6.0F, -11.0F, 4, 3, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 78, 70, 1.7071F, -6.7071F, -11.0F, 1, 3, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 78, 70, -2.7071F, -6.7071F, -11.0F, 1, 3, 1, 0.0F, true));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.7854F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 71, 69, 4.2426F, -2.4142F, -10.0F, 1, 1, 19, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 71, 69, 1.4142F, -5.2426F, -10.0F, 1, 1, 19, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 70, 3.5355F, -1.7071F, -11.0F, 1, 1, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 70, 0.7071F, -4.5355F, -11.0F, 1, 1, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -3.0F, -8.0F);
		setRotationAngle(bone3, -0.3491F, 0.0F, 0.0F);
		bone.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 78, 70, -2.0F, -1.316F, -2.8794F, 4, 2, 1, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -3.0F, -8.0F);
		setRotationAngle(bone4, -0.3491F, 0.0F, 0.0F);
		bone.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 78, 70, 1.7071F, -1.9804F, -3.1212F, 1, 2, 1, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 78, 70, -2.7071F, -1.9804F, -3.1212F, 1, 2, 1, 0.0F, true));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 7.1563F, -2.6719F);
		setRotationAngle(bone5, -0.5236F, 0.0F, 0.0F);
		bone.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 78, 70, -2.5F, -10.0F, -8.0F, 5, 1, 13, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 78, 70, -0.2071F, -10.7071F, -8.0F, 2, 1, 12, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 78, 70, -0.2071F, -9.2929F, -8.0F, 2, 1, 13, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 78, 70, -1.7929F, -9.2929F, -8.0F, 2, 1, 13, 0.0F, true));
		bone5.cubeList.add(new ModelBox(bone5, 78, 70, -1.7929F, -10.7071F, -8.0F, 2, 1, 12, 0.0F, true));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.7854F);
		bone5.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 78, 70, -8.8388F, -6.3033F, -8.0F, 1, 1, 12, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 78, 70, -6.3033F, -8.8388F, -8.0F, 1, 1, 12, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 78, 70, -5.5962F, -8.1317F, -8.0F, 1, 1, 13, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 78, 70, -8.1317F, -5.5962F, -8.0F, 1, 1, 13, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-5.8033F, -8.8388F, 4.0F);
		setRotationAngle(bone8, -0.5236F, 0.0F, 0.0F);
		bone6.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 78, 70, -0.5F, -0.0F, 0.0F, 1, 1, 1, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-5.8033F, -8.8388F, 4.0F);
		setRotationAngle(bone9, -0.5236F, 0.0F, 0.0F);
		bone6.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 78, 70, -0.5F, -0.0F, 0.0F, 1, 1, 1, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-8.8388F, -5.8033F, 4.0F);
		setRotationAngle(bone10, 0.0F, 0.5236F, 0.0F);
		bone6.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 78, 70, 0.0F, -0.5F, -0.0F, 1, 1, 1, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, -10.0F, 5.0F);
		setRotationAngle(bone7, 0.2618F, 0.0F, 0.0F);
		bone5.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 78, 70, -2.0F, -6.0F, -1.0F, 4, 6, 1, 0.0F, false));
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