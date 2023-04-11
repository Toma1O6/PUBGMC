package dev.toma.pubgmc.client.models.equipment;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MediumBackpackModel extends ModelBiped {

    public static final MediumBackpackModel MODEL = new MediumBackpackModel();

    private final ModelRenderer backpack;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer bone6;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bb_main;

    public MediumBackpackModel() {
        textureWidth = 64;
        textureHeight = 64;

        backpack = new ModelRenderer(this);
        backpack.setRotationPoint(-4.0F, 0.0F, 2.0F);
        backpack.cubeList.add(new ModelBox(backpack, 11, 18, 6.0F, -0.5F, -4.0F, 1, 1, 4, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 27, 12, 6.0F, 0.366F, -4.5F, 1, 9, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 17, 17, 6.0F, 0.366F, -0.5F, 1, 3, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 11, 18, 1.0F, -0.5F, -4.0F, 1, 1, 4, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 27, 12, 1.0F, 0.366F, -4.5F, 1, 9, 1, 0.0F, false));
        backpack.cubeList.add(new ModelBox(backpack, 17, 17, 1.0F, 0.366F, -0.5F, 1, 3, 1, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(4.0F, 0.0F, -2.0F);
        backpack.addChild(bone);
        setRotationAngle(bone, -0.5236F, 0.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 16, 19, 2.0F, 0.567F, -1.9821F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 16, 19, -3.0F, 0.567F, -1.9821F, 1, 1, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(4.0F, 0.0F, -2.0F);
        backpack.addChild(bone2);
        setRotationAngle(bone2, 0.5236F, 0.0F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 9, 16, 2.0F, 0.567F, 0.9821F, 1, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 9, 16, -3.0F, 0.567F, 0.9821F, 1, 1, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(-3.46F, 6.636F, 3.52F);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.1309F);
        bone3.cubeList.add(new ModelBox(bone3, 12, 19, -0.5F, -1.5F, -1.0F, 1, 1, 2, 0.0F, true));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(3.46F, 6.636F, 3.52F);
        setRotationAngle(bone4, 0.0F, 0.0F, 0.1309F);
        bone4.cubeList.add(new ModelBox(bone4, 9, 11, -0.5F, -1.5F, -1.0F, 1, 1, 2, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 6.366F, 3.5F);
        setRotationAngle(bone5, 0.0F, 0.0F, 0.5236F);
        bone5.cubeList.add(new ModelBox(bone5, 14, 11, -4.0981F, -3.0981F, -1.502F, 1, 2, 3, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 6.366F, 3.5F);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.5236F);
        bone6.cubeList.add(new ModelBox(bone6, 12, 12, 3.0981F, -3.0981F, -1.502F, 1, 2, 3, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(4.01F, 9.5F, 5.95F);
        setRotationAngle(bone7, -0.1309F, 0.0F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 11, 21, -6.0F, -4.6538F, -1.4026F, 4, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 7, 17, -4.53F, -7.1324F, -1.7289F, 2, 1, 1, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-0.59F, 5.876F, 3.59F);
        setRotationAngle(bone8, 0.0F, 0.0F, 0.2182F);
        bone8.cubeList.add(new ModelBox(bone8, 38, 38, -3.0F, -3.0F, -0.5F, 1, 2, 2, 0.0F, false));

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
        bb_main.cubeList.add(new ModelBox(bb_main, 13, 10, -2.0F, -22.366F, 1.999F, 4, 2, 3, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 13, 17, 3.0F, -18.634F, 2.5F, 1, 3, 2, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 10, 19, -4.0F, -18.634F, 2.5F, 1, 3, 2, 0.0F, true));
        bb_main.cubeList.add(new ModelBox(bb_main, 7, 16, -3.0F, -20.634F, 2.0F, 6, 6, 3, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 7, 18, -2.0F, -19.134F, 5.0F, 4, 4, 1, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 20, 14, -0.51F, -21.634F, 5.0F, 2, 2, 1, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 36, 54, -0.2F, -23.0F, 2.799F, 2, 1, 2, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale) {
        backpack.render(scale);
        bone3.render(scale);
        bone4.render(scale);
        bone5.render(scale);
        bone6.render(scale);
        bone7.render(scale);
        bone8.render(scale);
        bb_main.render(scale);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
