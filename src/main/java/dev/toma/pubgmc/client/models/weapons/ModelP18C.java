package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelP18C extends ModelGun {

    private final ModelRenderer gun;
    private final ModelRenderer bone;
    private final ModelRenderer bone5;
    private final ModelRenderer grip_angle;
    private final ModelRenderer bone6;
    private final ModelRenderer bone7;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer slide;
    private final ModelRenderer bone2;
    private final ModelRenderer magazine;
    private final ModelRenderer bullet;

    @Override
    public void renderModel(ItemStack stack) {
        gun.render(1f);
    }

    @Override
    public void transformModel() {
        ModelTransformationHelper.defaultPistolTransform();
        GlStateManager.translate(-0.9, -1.0, -6.0);
    }

    public ModelP18C() {
        textureWidth = 512;
        textureHeight = 512;

        gun = new ModelRenderer(this);
        gun.setRotationPoint(0.0F, 24.0F, 0.0F);
        gun.cubeList.add(new ModelBox(gun, 12, 92, -2.0F, -1.2929F, -1.0F, 4, 1, 13, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 12, 92, -2.0F, -1.2929F, -13.0F, 4, 1, 12, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 12, 92, -1.5F, -0.2929F, -5.0F, 3, 2, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 12, 92, -1.0F, 1.7071F, -5.0F, 2, 2, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 12, 92, -1.0F, 2.7071F, -4.0F, 2, 1, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 12, 92, -1.0F, -0.2929F, -0.448F, 2, 3, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 12, 92, -1.5F, 7.9509F, -6.8752F, 3, 2, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 12, 92, -1.5F, -0.2929F, -9.7321F, 3, 2, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 12, 92, -1.5F, 7.9509F, -11.6073F, 3, 2, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 12, 92, -2.0F, -0.2929F, -8.866F, 1, 2, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 12, 92, -2.0F, 7.9509F, -10.7413F, 1, 2, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 12, 92, 1.0F, -0.2929F, -8.866F, 1, 2, 4, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 12, 92, 1.0F, 7.9509F, -10.7413F, 1, 2, 4, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 68, 36, -1.5F, -4.528F, -5.0F, 3, 2, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 12, 92, -1.0F, -3.728F, -1.0F, 2, 2, 14, 0.0F, true));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 2.2071F, -0.5F);
        gun.addChild(bone);
        setRotationAngle(bone, 0.5236F, 0.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 12, 92, -1.0F, 0.549F, -0.317F, 2, 1, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.5F, 1.0871F, -10.0F);
        gun.addChild(bone5);
        setRotationAngle(bone5, -0.5236F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 12, 92, -1.5F, -1.5F, -1.952F, 2, 1, 2, 0.0F, false));

        grip_angle = new ModelRenderer(this);
        grip_angle.setRotationPoint(-0.5F, 2.6871F, -10.0F);
        gun.addChild(grip_angle);
        setRotationAngle(grip_angle, -0.2618F, 0.0F, 0.0F);
        grip_angle.cubeList.add(new ModelBox(grip_angle, 12, 92, -1.0F, -2.4995F, 4.5419F, 3, 8, 1, 0.0F, false));
        grip_angle.cubeList.add(new ModelBox(grip_angle, 12, 92, -1.0F, -2.4995F, -0.1901F, 3, 8, 1, 0.0F, false));
        grip_angle.cubeList.add(new ModelBox(grip_angle, 12, 92, -1.5F, -2.4995F, 0.6759F, 1, 8, 4, 0.0F, false));
        grip_angle.cubeList.add(new ModelBox(grip_angle, 12, 92, 1.5F, -2.4995F, 0.6759F, 1, 8, 4, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.5F, 0.0F, 3.5F);
        grip_angle.addChild(bone6);
        setRotationAngle(bone6, 0.0F, -0.5236F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 12, 92, 1.32F, -2.4995F, 0.0183F, 1, 8, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 12, 92, -3.1441F, -2.4995F, -2.4458F, 1, 8, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.5F, 0.0F, 3.5F);
        grip_angle.addChild(bone7);
        setRotationAngle(bone7, 0.0F, 0.5236F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 12, 92, -2.32F, -2.4995F, 0.0183F, 1, 8, 1, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 12, 92, 2.1441F, -2.4995F, -2.4458F, 1, 8, 1, 0.0F, true));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.2071F, -6.5F);
        gun.addChild(bone3);
        setRotationAngle(bone3, 0.0F, -0.5236F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 12, 92, 1.549F, -0.5F, 0.4151F, 1, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 12, 92, 0.6114F, 7.7438F, -1.2089F, 1, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 12, 92, -2.9151F, -0.5F, -2.049F, 1, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 12, 92, -3.8527F, 7.7438F, -3.673F, 1, 2, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.2071F, -6.5F);
        gun.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.5236F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 12, 92, -2.549F, -0.5F, 0.4151F, 1, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 12, 92, -1.6114F, 7.7438F, -1.2089F, 1, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 12, 92, 1.9151F, -0.5F, -2.049F, 1, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 12, 92, 2.8527F, 7.7438F, -3.673F, 1, 2, 1, 0.0F, true));

        slide = new ModelRenderer(this);
        slide.setRotationPoint(-0.5F, 24.0F, 1.0F);
        slide.cubeList.add(new ModelBox(slide, 200, 153, -1.0F, -5.0F, -2.0F, 3, 1, 13, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 200, 153, -1.0F, -5.0F, -14.0F, 3, 1, 8, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 32, 156, -1.25F, -6.3F, -11.5F, 1, 2, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 32, 156, 0.0F, -5.968F, 8.856F, 1, 1, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 32, 156, 1.25F, -6.3F, -11.5F, 1, 2, 1, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 32, 156, -0.5F, -5.3F, -11.516F, 2, 1, 1, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 200, 153, -1.7071F, -4.2929F, -2.0F, 1, 3, 13, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 200, 153, -1.7071F, -3.2929F, -14.0F, 1, 2, 12, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 200, 153, -1.0F, -4.2929F, -14.112F, 3, 3, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 200, 153, -1.7071F, -4.2929F, -14.0F, 1, 1, 8, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 200, 153, 1.7071F, -3.2929F, -14.0F, 1, 2, 12, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 200, 153, 1.7071F, -4.2929F, -14.0F, 1, 1, 8, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 200, 153, 1.7071F, -4.2929F, -2.0F, 1, 3, 13, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        slide.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, 0.7854F);
        bone2.cubeList.add(new ModelBox(bone2, 200, 153, -2.1213F, -4.9497F, -2.0F, 1, 1, 13, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 200, 153, -2.1213F, -4.9497F, -14.0F, 1, 1, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 200, 153, -4.2426F, -2.8284F, -2.0F, 1, 1, 13, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 200, 153, -4.2426F, -2.8284F, -14.0F, 1, 1, 8, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(-0.5F, 33.396F, -7.188F);
        setRotationAngle(magazine, -0.1745F, 0.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 84, 77, -1.0F, -11.396F, -3.5F, 3, 16, 4, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 84, 77, -1.0F, -12.396F, -3.5F, 1, 1, 4, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 84, 77, 1.0F, -12.396F, -3.5F, 1, 1, 4, 0.0F, false));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(-0.5F, 33.396F, -7.188F);
        setRotationAngle(bullet, -0.1745F, 0.0F, 0.0F);
        bullet.cubeList.add(new ModelBox(bullet, 9, 490, -0.5F, -13.296F, -3.0F, 2, 2, 3, 0.0F, false));

        addEntry(AnimationElement.MAGAZINE, stack -> magazine);
        addEntry(AnimationElement.CHARGING, stack -> slide);
        addEntry(AnimationElement.BULLET, stack -> bullet);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
