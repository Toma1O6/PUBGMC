package dev.toma.pubgmc.client.models.equipment;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LargeBackpackModel extends ModelBiped {

    public static final LargeBackpackModel MODEL = new LargeBackpackModel();

    private final ModelRenderer backpack;
    private final ModelRenderer bone5;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;

    public LargeBackpackModel() {
        textureWidth = 64;
        textureHeight = 64;

        backpack = new ModelRenderer(this);
        backpack.setRotationPoint(-4.0F, 0.0F, 2.0F);
        backpack.cubeList.add(new ModelBox(backpack, 14, 14, 5.5F, -0.5F, -4.0F, 1, 1, 4, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 7, 11, 5.5F, 0.366F, -4.5F, 1, 9, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 13, 18, 5.5F, 0.366F, -0.5F, 1, 10, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 33, 50, 1.0F, 1.366F, 0.0F, 6, 10, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 0, 8, 0.5F, 3.196F, 1.0F, 7, 8, 2, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 12, 14, 1.0F, 3.196F, 2.866F, 6, 8, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 0, 8, 1.5F, 6.5F, 3.456F, 5, 4, 2, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 0, 8, 4.3F, 3.5F, 3.866F, 2, 2, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 37, 52, 4.8F, 3.5F, 4.066F, 1, 1, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 0, 8, 1.7F, 3.5F, 3.866F, 2, 2, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 37, 55, 2.2F, 3.5F, 4.066F, 1, 1, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 32, 38, 1.0F, 2.366F, 1.5F, 6, 3, 2, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 50, 35, 5.8F, 2.206F, 2.3F, 1, 1, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 38, 51, 4.7F, 2.036F, 2.3F, 1, 1, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 38, 38, 3.6F, 2.096F, 2.3F, 1, 1, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 43, 40, 1.5F, -1.5F, 2.0F, 1, 4, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 7, 13, 1.5F, -0.5F, -4.0F, 1, 1, 4, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 15, 13, 1.5F, 0.366F, -4.5F, 1, 9, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 39, 60, 2.2F, 1.37F, -4.19F, 2, 1, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 38, 54, 3.8F, 1.55F, -4.09F, 2, 1, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 13, 19, 1.5F, 0.366F, -0.5F, 1, 10, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(3.97F, 6.296F, 4.366F);
        backpack.addChild(bone5);
        setRotationAngle(bone5, -0.1309F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 0, 8, -2.48F, 0.0256F, -0.8842F, 5, 1, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 0, 8, 0.33F, -2.8717F, -0.8607F, 2, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 0, 8, -2.27F, -2.8717F, -0.8607F, 2, 1, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(4.0F, 6.866F, 2.0F);
        backpack.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.5236F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 22, 9, 2.5311F, -3.67F, -0.116F, 1, 8, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 0, 8, -3.5311F, -3.67F, -0.884F, 1, 8, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(4.0F, 6.866F, 2.0F);
        backpack.addChild(bone4);
        setRotationAngle(bone4, 0.0F, -0.5236F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 8, 10, -3.5311F, -3.67F, -0.116F, 1, 8, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 21, 9, 2.5311F, -3.67F, -0.884F, 1, 8, 1, 0.0F, true));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(4.0F, 0.0F, -2.0F);
        backpack.addChild(bone);
        setRotationAngle(bone, -0.5236F, 0.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 17, 20, 1.499F, 0.567F, -1.9821F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 13, 22, -2.499F, 0.567F, -1.9821F, 1, 1, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(4.0F, 0.0F, -2.0F);
        backpack.addChild(bone2);
        setRotationAngle(bone2, 0.5236F, 0.0F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 18, 18, 1.499F, 0.567F, 0.9821F, 1, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 11, 14, -2.499F, 0.567F, 0.9821F, 1, 1, 1, 0.0F, false));
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale) {
        backpack.render(scale);
    }
}
