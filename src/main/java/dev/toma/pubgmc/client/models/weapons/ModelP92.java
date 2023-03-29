package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelP92 extends ModelGun {

    private final ModelRenderer slide;
    private final ModelRenderer bone3;
    private final ModelRenderer bone2;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bone14;
    private final ModelRenderer bone9;
    private final ModelRenderer bone10;
    private final ModelRenderer hammer;
    private final ModelRenderer bone13;
    private final ModelRenderer gun;
    private final ModelRenderer bone16;
    private final ModelRenderer bone15;
    private final ModelRenderer bone;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer bone6;
    private final ModelRenderer magazine;
    private final ModelRenderer bullet;

    public ModelP92() {
        textureWidth = 512;
        textureHeight = 512;

        slide = new ModelRenderer(this);
        slide.setRotationPoint(1.158F, 24.0F, 0.0F);
        slide.cubeList.add(new ModelBox(slide, 21, 100, -2.158F, -4.0F, -11.6641F, 2, 3, 6, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 21, 100, 0.184F, -6.4397F, -12.4F, 1, 2, 13, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 21, 100, 0.184F, -7.4397F, -12.4F, 1, 1, 5, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -1.816F, -8.4397F, -12.4F, 3, 1, 3, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -2.6206F, -8.967F, -12.4F, 3, 1, 3, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -3.5F, -8.4397F, -12.4F, 2, 1, 3, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -3.5F, -6.4397F, -12.4F, 1, 2, 13, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -3.5F, -7.4397F, -12.4F, 1, 1, 5, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -3.158F, -5.5F, -12.4F, 4, 2, 9, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -3.5F, -7.4397F, -7.4F, 1, 1, 11, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -3.5F, -6.4397F, 0.6F, 1, 2, 13, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -3.5F, -8.4397F, 3.6F, 1, 2, 9, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -2.6206F, -8.967F, 3.6F, 3, 1, 9, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 21, 100, 0.184F, -7.4397F, -7.4F, 1, 1, 11, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 21, 100, 0.184F, -6.4397F, 0.6F, 1, 2, 13, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 21, 100, 0.184F, -8.4397F, 3.6F, 1, 2, 9, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -0.658F, -6.4397F, 12.6938F, 1, 2, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -1.658F, -5.4397F, 12.6938F, 1, 1, 1, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -2.658F, -6.4397F, 12.6938F, 1, 2, 1, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 30, 161, -2.158F, -9.4885F, 9.7406F, 2, 1, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 30, 161, -3.0403F, -10.4532F, 9.7406F, 1, 2, 1, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 30, 161, -0.2756F, -10.4532F, 9.7406F, 1, 2, 1, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -1.658F, -8.885F, 11.7172F, 1, 1, 1, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -3.158F, -4.5F, 5.6F, 4, 1, 8, 0.0F, false));
        slide.cubeList.add(new ModelBox(slide, 21, 100, -2.6206F, -8.4397F, 3.6F, 3, 2, 1, 0.0F, true));
        slide.cubeList.add(new ModelBox(slide, 30, 161, -1.658F, -9.9412F, -11.5874F, 1, 1, 1, 0.0F, true));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(-0.158F, 0.0F, 0.0F);
        slide.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.0F, 0.3491F);
        bone3.cubeList.add(new ModelBox(bone3, 21, 100, -1.2574F, -4.6309F, -12.4F, 1, 1, 13, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 21, 100, -1.2574F, -4.6309F, 0.6F, 1, 1, 13, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-2.158F, 0.0F, 0.0F);
        slide.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.3491F);
        bone2.cubeList.add(new ModelBox(bone2, 21, 100, 0.2574F, -4.6309F, -12.4F, 1, 1, 13, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 21, 100, 0.2574F, -4.6309F, 0.6F, 1, 1, 13, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(-0.158F, 0.0F, 0.0F);
        slide.addChild(bone7);
        setRotationAngle(bone7, 0.5236F, 0.0F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 21, 100, -0.658F, -0.7769F, 13.9978F, 2, 2, 1, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 21, 100, -3.342F, -0.7769F, 13.9978F, 2, 2, 1, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-0.158F, 0.0F, 0.0F);
        slide.addChild(bone8);
        setRotationAngle(bone8, 0.6109F, 0.0F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 21, 100, -0.5F, 1.5793F, 13.9109F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 21, 100, -2.5F, 1.5793F, 13.9109F, 1, 1, 1, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(-1.1206F, -9.7092F, -9.7594F);
        slide.addChild(bone14);
        setRotationAngle(bone14, 0.4363F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 30, 161, -0.5373F, -0.5602F, -1.6524F, 1, 2, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 30, 161, -1.9197F, 7.9894F, 17.8937F, 1, 2, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 30, 161, 0.845F, 7.9894F, 17.8937F, 1, 2, 1, 0.0F, true));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(-0.158F, 0.0F, 0.0F);
        slide.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.0F, 0.5236F);
        bone9.cubeList.add(new ModelBox(bone9, 21, 100, -4.0576F, -7.98F, 3.6F, 1, 1, 9, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 21, 100, -4.0576F, -7.98F, -12.4F, 1, 1, 3, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-2.158F, 0.0F, 0.0F);
        slide.addChild(bone10);
        setRotationAngle(bone10, 0.0F, 0.0F, -0.5236F);
        bone10.cubeList.add(new ModelBox(bone10, 21, 100, 3.0576F, -7.98F, 3.6F, 1, 1, 9, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 21, 100, 3.0576F, -7.98F, -12.4F, 1, 1, 3, 0.0F, false));

        hammer = new ModelRenderer(this);
        hammer.setRotationPoint(1.158F, 18.606F, 13.2612F);
        setRotationAngle(hammer, -0.0873F, 0.0F, 0.0F);
        hammer.cubeList.add(new ModelBox(hammer, 38, 42, -1.658F, -3.0864F, -0.5297F, 1, 3, 1, 0.0F, true));
        hammer.cubeList.add(new ModelBox(hammer, 38, 42, -1.658F, -2.3272F, -1.4571F, 1, 1, 1, 0.0F, true));
        hammer.cubeList.add(new ModelBox(hammer, 38, 42, -1.658F, -3.1944F, 0.5429F, 1, 1, 1, 0.0F, true));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-1.158F, -2.6944F, 1.0429F);
        hammer.addChild(bone13);
        setRotationAngle(bone13, 0.1222F, 0.0F, 0.0F);
        bone13.cubeList.add(new ModelBox(bone13, 38, 42, -0.5F, -0.5572F, -1.4353F, 1, 1, 1, 0.0F, true));
        bone13.cubeList.add(new ModelBox(bone13, 38, 42, -0.5F, 0.2503F, -1.393F, 1, 1, 1, 0.0F, true));

        gun = new ModelRenderer(this);
        gun.setRotationPoint(0.0F, 24.0F, 0.0F);
        gun.cubeList.add(new ModelBox(gun, 82, 16, -1.0F, -1.0F, -6.0F, 2, 1, 8, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 82, 16, -1.0F, -1.0234F, 1.3984F, 2, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 82, 16, -1.0F, -4.0F, 0.0F, 2, 3, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 82, 16, -2.0F, -4.0F, 2.0F, 4, 1, 6, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 82, 16, -2.0F, -4.0F, 8.0F, 4, 3, 5, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 82, 16, -1.0F, -0.124F, 3.4321F, 2, 1, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 82, 16, -2.0F, 3.5015F, 9.3825F, 4, 2, 5, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 82, 16, 1.0F, -4.0F, -6.0F, 1, 3, 8, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 82, 16, -2.0F, -4.0F, -6.0F, 1, 3, 8, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 82, 16, -2.0F, -4.5F, -3.4F, 4, 1, 9, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(1.0F, 0.0F, 0.0F);
        gun.addChild(bone16);
        setRotationAngle(bone16, -0.6109F, 0.0F, 0.0F);
        bone16.cubeList.add(new ModelBox(bone16, 82, 16, -2.0F, -2.251F, 0.3139F, 2, 1, 3, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(1.0F, 4.7969F, 0.0F);
        gun.addChild(bone15);
        setRotationAngle(bone15, 0.2618F, 0.0F, 0.0F);
        bone15.cubeList.add(new ModelBox(bone15, 82, 16, -3.0F, -3.5288F, 9.2277F, 4, 6, 5, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 82, 16, -2.0F, -3.1225F, 7.2277F, 2, 1, 2, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(1.0F, 0.0F, 0.0F);
        gun.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, -0.7854F);
        bone.cubeList.add(new ModelBox(bone, 82, 16, 0.4142F, -1.0F, -6.0F, 1, 1, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 16, 0.0F, -1.0F, -6.0F, 1, 1, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 16, -1.4142F, -2.4142F, -6.0F, 1, 1, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 82, 16, -1.4142F, -2.8284F, -6.0F, 1, 1, 8, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(1.0F, 0.0F, 0.0F);
        gun.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 30, 161, -1.5F, -8.0764F, -14.0289F, 1, 1, 13, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 82, 16, -1.5F, -8.0764F, -2.0289F, 1, 1, 11, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 161, -1.5F, -5.3444F, -14.0289F, 1, 1, 13, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 82, 16, -2.0F, -7.3444F, -12.6852F, 2, 2, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 82, 16, -1.5F, -5.3444F, -2.0289F, 1, 1, 11, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 161, -2.866F, -6.7104F, -14.0289F, 1, 1, 13, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 82, 16, -2.866F, -6.7104F, -2.0289F, 1, 1, 11, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 30, 161, -0.134F, -6.7104F, -14.0289F, 1, 1, 13, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 82, 16, -0.134F, -6.7104F, -2.0289F, 1, 1, 11, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(1.842F, -10.9397F, 6.6F);
        gun.addChild(bone5);
        setRotationAngle(bone5, 0.0F, 0.0F, 0.5236F);
        bone5.cubeList.add(new ModelBox(bone5, 30, 161, 0.2694F, 3.1507F, -20.6289F, 1, 1, 13, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 16, 0.2694F, 3.1507F, -8.6289F, 1, 1, 11, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 30, 161, -1.0966F, 4.5167F, -20.6289F, 1, 1, 13, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 16, -1.0966F, 4.5167F, -8.6289F, 1, 1, 11, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 30, 161, 1.6354F, 4.5167F, -20.6289F, 1, 1, 13, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 16, 1.6354F, 4.5167F, -8.6289F, 1, 1, 11, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 30, 161, 0.2694F, 5.8827F, -20.6289F, 1, 1, 13, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 82, 16, 0.2694F, 5.8827F, -8.6289F, 1, 1, 11, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(-1.842F, -10.9397F, 6.6F);
        gun.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.5236F);
        bone6.cubeList.add(new ModelBox(bone6, 30, 161, -1.2694F, 3.1507F, -20.6289F, 1, 1, 13, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 82, 16, -1.2694F, 3.1507F, -8.6289F, 1, 1, 11, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 30, 161, 0.0966F, 4.5167F, -20.6289F, 1, 1, 13, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 82, 16, 0.0966F, 4.5167F, -8.6289F, 1, 1, 11, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 30, 161, -2.6354F, 4.5167F, -20.6289F, 1, 1, 13, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 82, 16, -2.6354F, 4.5167F, -8.6289F, 1, 1, 11, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 30, 161, -1.2694F, 5.8827F, -20.6289F, 1, 1, 13, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 82, 16, -1.2694F, 5.8827F, -8.6289F, 1, 1, 11, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(1.5F, 26.1953F, -1.1953F);
        setRotationAngle(magazine, 0.0873F, 0.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 80, 92, -3.0F, -7.9834F, 10.4225F, 1, 1, 4, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 80, 92, -1.0F, -7.9834F, 10.4225F, 1, 1, 4, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 80, 92, -3.0F, -6.9834F, 10.4225F, 3, 11, 4, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 80, 92, -3.0F, 4.0166F, 9.4225F, 3, 1, 5, 0.0F, false));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(1.5F, 26.1953F, -1.1953F);
        setRotationAngle(bullet, 0.0873F, 0.0F, 0.0F);
        bullet.cubeList.add(new ModelBox(bullet, 8, 505, -2.0F, -7.6865F, 11.0632F, 1, 1, 3, 0.0F, true));

        addEntry(AnimationElement.MAGAZINE, stack -> magazine);
        addEntry(AnimationElement.CHARGING, stack -> slide);
        addEntry(AnimationElement.HAMMER, stack -> hammer);
        addEntry(AnimationElement.BULLET, stack -> bullet);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void transformModel() {
        ModelTransformationHelper.defaultPistolTransform();
        GlStateManager.translate(-3.0750017, -4.0750017, -13.0);
    }

    @Override
    public void renderModel(ItemStack stack) {
        gun.render(1f);
    }
}
