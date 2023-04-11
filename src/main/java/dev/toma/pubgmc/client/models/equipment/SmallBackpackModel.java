package dev.toma.pubgmc.client.models.equipment;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SmallBackpackModel extends ModelBiped {

    public static final SmallBackpackModel MODEL = new SmallBackpackModel();

    private final ModelRenderer backpack;
    private final ModelRenderer bone;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer left;
    private final ModelRenderer right;

    public SmallBackpackModel() {
        textureWidth = 64;
        textureHeight = 64;

        backpack = new ModelRenderer(this);
        backpack.setRotationPoint(-4.0F, 0.0F, 2.0F);
        backpack.cubeList.add(new ModelBox(backpack, 5, 10, 2.0F, 4.6F, 0.0F, 4, 2, 2, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 12, 17, 2.5F, 6.466F, 0.0F, 3, 1, 2, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 14, 12, 2.5F, 1.6F, 0.5F, 3, 1, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 10, 9, 3.0F, 0.6F, 0.0F, 2, 4, 2, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 10, 14, 2.5F, 5.08F, 1.5F, 3, 1, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 39, 52, 3.5F, 2.35F, 1.25F, 1, 1, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 12, 9, 0.75F, -0.05F, -4.0F, 1, 1, 4, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 12, 9, 6.25F, -0.05F, -4.0F, 1, 1, 4, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 6, 10, 6.25F, 0.0F, -4.01F, 1, 8, 0, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 6, 10, 0.75F, 0.0F, -4.01F, 1, 8, 0, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(4.0F, 5.1F, 1.0F);
        backpack.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, -0.2182F);
        bone.cubeList.add(new ModelBox(bone, 12, 11, 1.0608F, -4.0553F, -1.001F, 1, 4, 2, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(4.0F, 5.1F, 1.0F);
        backpack.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.0F, 0.5236F);
        bone4.cubeList.add(new ModelBox(bone4, 19, 10, 1.4821F, 0.299F, -1.001F, 1, 1, 2, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(4.0F, 5.1F, 1.0F);
        backpack.addChild(bone5);
        setRotationAngle(bone5, 0.0F, 0.0F, -0.5236F);
        bone5.cubeList.add(new ModelBox(bone5, 15, 9, -2.4821F, 0.299F, -1.001F, 1, 1, 2, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(4.0F, 5.1F, 1.0F);
        backpack.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, 0.2182F);
        bone2.cubeList.add(new ModelBox(bone2, 18, 14, -2.0608F, -4.0553F, -1.001F, 1, 4, 2, 0.0F, true));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(4.0F, 5.16F, 2.17F);
        backpack.addChild(bone3);
        setRotationAngle(bone3, -0.3491F, 0.0F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 12, 12, -1.5F, -1.0F, -0.5F, 3, 1, 1, 0.0F, false));

        left = new ModelRenderer(this);
        left.setRotationPoint(-2.0F, 1.3F, 0.1F);
        backpack.addChild(left);
        setRotationAngle(left, 0.0F, 0.0F, 0.3491F);
        left.cubeList.add(new ModelBox(left, 13, 9, 7.2555F, -4.0922F, -1.0F, 1, 4, 1, 0.0F, false));
        left.cubeList.add(new ModelBox(left, 16, 11, 5.3612F, 2.9825F, -1.0F, 1, 2, 1, 0.0F, false));

        right = new ModelRenderer(this);
        right.setRotationPoint(10.0F, 1.3F, 0.1F);
        backpack.addChild(right);
        setRotationAngle(right, 0.0F, 0.0F, -0.3491F);
        right.cubeList.add(new ModelBox(right, 13, 9, -8.2555F, -4.0922F, -1.0F, 1, 4, 1, 0.0F, true));
        right.cubeList.add(new ModelBox(right, 16, 11, -6.3612F, 2.9825F, -1.0F, 1, 2, 1, 0.0F, true));
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale) {
        backpack.render(scale);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
