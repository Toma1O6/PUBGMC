package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.HeldAnimation;
import com.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import com.toma.pubgmc.animation.ReloadAnimation;
import com.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

/**
 * {@code slightly edited by Toma}
 *
 * @author OfficialMajonaise
 */
public class ModelDeagle extends ModelGun {

    private final ModelRenderer deagle;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer bone6;
    private final ModelRenderer bone8;
    private final ModelRenderer bone7;
    private final ModelRenderer bone9;
    private final ModelRenderer bone18;
    private final ModelRenderer bone13;
    private final ModelRenderer bone14;
    private final ModelRenderer bone15;
    private final ModelRenderer bone11;
    private final ModelRenderer bone12;
    private final ModelRenderer bone10;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone21;
    private final ModelRenderer ironsights;
    private final ModelRenderer bone22;
    private final ModelRenderer bone;
    private final ModelRenderer bone23;
    private final ModelRenderer bone24;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;
    private final ModelRenderer magazine;

    public ModelDeagle() {
        super();
        textureWidth = 128;
        textureHeight = 128;

        deagle = new ModelRenderer(this);
        deagle.setRotationPoint(0.0F, 24.0F, 0.0F);
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -5.5F, -1.649F, -12.0027F, 11, 1, 37, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -5.5F, -0.649F, 0.9973F, 11, 2, 20, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -5.5F, 3.351F, 19.216F, 11, 2, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -5.5F, 20.1563F, 21.1754F, 11, 2, 3, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 38, 29, -5.5F, 21.4991F, 8.2021F, 11, 4, 15, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 6, 6, -6.0F, -5.649F, -21.5027F, 12, 4, 47, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -5.5F, -2.649F, -29.0027F, 11, 2, 17, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -5.5F, -4.649F, -30.0027F, 11, 2, 9, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -6.0F, -11.649F, 19.2004F, 12, 6, 4, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 38, 41, -6.0F, -7.649F, 7.2004F, 12, 2, 12, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 38, 41, -6.0F, -11.649F, 7.2004F, 12, 2, 12, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -6.0F, -9.649F, 17.7004F, 12, 2, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -6.0F, -9.649F, 16.2004F, 12, 2, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -6.0F, -9.649F, 13.2004F, 12, 2, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -6.0F, -9.649F, 14.7004F, 12, 2, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -6.0F, -9.649F, 7.2004F, 12, 2, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -6.0F, -9.649F, 8.7004F, 12, 2, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -6.0F, -9.649F, 10.2004F, 12, 2, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -6.0F, -9.649F, 11.7004F, 12, 2, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 4, 71, 4.7031F, -9.649F, 8.2004F, 1, 2, 11, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 4, 71, -5.7031F, -9.649F, 8.2004F, 1, 2, 11, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -0.9392F, -12.3436F, -23.7996F, 3, 1, 47, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -2.0608F, -12.3436F, -23.7996F, 2, 1, 47, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -2.5F, -8.649F, -30.0027F, 5, 4, 9, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 24, 6, -0.5F, -11.024F, -30.0027F, 1, 4, 38, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 8, 76, -1.5F, -9.0709F, -31.0027F, 3, 5, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 6, 75, -2.5F, -8.0709F, -31.0027F, 5, 3, 1, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -2.5F, -1.7115F, -28.0027F, 5, 2, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -5.3978F, -1.488F, -28.0027F, 1, 1, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, 4.3978F, -1.488F, -28.0027F, 1, 1, 2, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -5.3978F, -1.488F, -24.0027F, 1, 1, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -2.5F, -1.7115F, -24.0027F, 5, 2, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, 4.3978F, -1.488F, -24.0027F, 1, 1, 2, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -5.3978F, -1.488F, -20.0027F, 1, 1, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -2.5F, -1.7115F, -20.0027F, 5, 2, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, 4.3978F, -1.488F, -20.0027F, 1, 1, 2, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -5.3978F, -1.488F, -16.0027F, 1, 1, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -2.5F, -1.7115F, -16.0027F, 5, 2, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, 4.3978F, -1.488F, -16.0027F, 1, 1, 2, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -2.5F, -1.7115F, -12.0027F, 5, 2, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 76, 32, -2.5F, -1.7115F, -8.0027F, 5, 2, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 90, 9, -0.9392F, -12.3436F, -29.7996F, 3, 1, 6, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 70, 20, -2.0608F, -12.3436F, -29.7996F, 2, 1, 6, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 38, 41, -6.0F, -11.649F, -29.9996F, 12, 6, 7, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 90, 9, -0.9392F, -12.8436F, -20.9996F, 3, 1, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 70, 20, -2.0608F, -12.8436F, -20.9996F, 2, 1, 2, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 90, 9, -0.9392F, -12.8436F, -16.9996F, 3, 1, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 70, 20, -2.0608F, -12.8436F, -16.9996F, 2, 1, 2, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 90, 9, -0.9392F, -12.8436F, -12.9996F, 3, 1, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 70, 20, -2.0608F, -12.8436F, -12.9996F, 2, 1, 2, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 90, 9, -0.9392F, -12.8436F, -8.9996F, 3, 1, 2, 0.0F, false));
        deagle.cubeList.add(new ModelBox(deagle, 70, 20, -2.0608F, -12.8436F, -8.9996F, 2, 1, 2, 0.0F, true));
        deagle.cubeList.add(new ModelBox(deagle, 0, 0, -6.0F, -11.649F, -23.7996F, 12, 6, 31, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone2, 0.0F, 0.0F, 0.3491F);
        deagle.addChild(bone2);

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.3491F);
        deagle.addChild(bone3);

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone4, 0.0F, 0.0F, -0.2618F);
        deagle.addChild(bone4);

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone5, 0.0F, 0.0F, 0.2618F);
        deagle.addChild(bone5);

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -1.0F, 0.0F);
        setRotationAngle(bone6, -0.3491F, 0.0F, 0.0F);
        deagle.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 0, 0, -5.5F, -0.1319F, -4.2588F, 11, 2, 6, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 88, 50, -5.5F, -4.9723F, 15.5351F, 11, 5, 5, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 58, 24, -5.5F, 13.4913F, 27.6374F, 11, 3, 3, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 8, 72, -2.0F, -17.2692F, 19.5351F, 4, 4, 2, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 8, 80, -2.0F, -18.2692F, 19.5351F, 4, 1, 3, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 12, 73, -1.5F, 1.1856F, -1.0314F, 3, 2, 1, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, 4.0F, 38.0F);
        setRotationAngle(bone8, 0.2618F, 0.0F, 0.0F);
        deagle.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 96, 36, -5.5F, -4.9723F, -20.5351F, 11, 17, 3, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 41, 9, -5.5F, -11.9457F, -34.3469F, 11, 25, 14, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 0, 64, -6.0F, -9.0766F, -31.8215F, 12, 22, 4, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 0, 64, -6.0F, -8.5766F, -27.8215F, 12, 22, 4, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 0, 64, -6.0F, -8.0766F, -23.8215F, 12, 22, 4, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, 1.5184F, 24.5859F);
        setRotationAngle(bone7, 0.4712F, 0.0F, 0.0F);
        deagle.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 0, 0, -5.5F, -3.8374F, -3.1602F, 11, 2, 4, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, -3.649F, -25.5027F);
        setRotationAngle(bone9, 0.4363F, 0.0F, 0.0F);
        deagle.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 76, 32, -5.5F, -0.8705F, -4.5322F, 11, 2, 2, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone18, 0.0F, 0.0F, 0.1745F);
        deagle.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 0, 0, -6.2237F, -5.6234F, -30.0027F, 3, 2, 38, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 0, -0.114F, -12.514F, -23.7996F, 4, 1, 47, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 0, 2.9251F, -7.4129F, -30.0027F, 1, 1, 38, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 0, -5.3736F, -8.4732F, -30.0027F, 4, 3, 38, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 0, -1.4868F, -10.9472F, -30.0027F, 2, 2, 38, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 56, 30, -0.114F, -12.514F, -29.7996F, 4, 1, 6, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 56, 30, -0.2442F, -13.0026F, -20.9996F, 4, 1, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 56, 30, -0.2442F, -13.0026F, -16.9996F, 4, 1, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 56, 30, -0.2442F, -13.0026F, -12.9996F, 4, 1, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 56, 30, -0.2442F, -13.0026F, -8.9996F, 4, 1, 2, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone13, 0.0F, 0.0F, -0.1745F);
        deagle.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 0, 0, 3.2237F, -5.6234F, -30.0027F, 3, 2, 38, 0.0F, true));
        bone13.cubeList.add(new ModelBox(bone13, 0, 0, -3.9251F, -7.4129F, -30.0027F, 1, 1, 38, 0.0F, true));
        bone13.cubeList.add(new ModelBox(bone13, 0, 0, 1.3736F, -8.4732F, -30.0027F, 4, 3, 38, 0.0F, true));
        bone13.cubeList.add(new ModelBox(bone13, 0, 0, -0.5132F, -10.9472F, -30.0027F, 2, 2, 38, 0.0F, true));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone14, 0.0F, 0.0F, -0.7854F);
        deagle.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 0, 0, 6.2618F, -5.8586F, -30.0027F, 3, 2, 38, 0.0F, true));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone15, 0.0F, 0.0F, 0.7854F);
        deagle.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 0, 0, -9.2618F, -5.8586F, -30.0027F, 3, 2, 38, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone11, 0.3491F, 0.0F, 0.0F);
        deagle.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 67, 21, 2.0F, -1.5878F, 21.8917F, 4, 5, 4, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 112, 21, -6.0F, -1.5878F, 21.8917F, 4, 5, 4, 0.0F, true));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone12, 0.8727F, 0.0F, 0.0F);
        deagle.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 0, 0, -6.0F, 10.5708F, 19.2168F, 12, 1, 4, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 8, 83, -2.0F, 13.6268F, 20.7012F, 4, 1, 4, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone10, 0.0F, 0.0F, -0.1745F);
        deagle.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 0, 0, -3.886F, -12.514F, -23.7996F, 4, 1, 47, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 88, 32, -3.886F, -12.514F, -29.7996F, 4, 1, 6, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 88, 32, -3.7558F, -13.0026F, -20.9996F, 4, 1, 2, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 88, 32, -3.7558F, -13.0026F, -16.9996F, 4, 1, 2, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 88, 32, -3.7558F, -13.0026F, -12.9996F, 4, 1, 2, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 88, 32, -3.7558F, -13.0026F, -8.9996F, 4, 1, 2, 0.0F, true));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(-5.0F, -0.649F, -27.0027F);
        setRotationAngle(bone16, 0.0F, 0.0F, 0.2618F);
        deagle.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 76, 32, -0.3425F, -1.7415F, -1.0F, 3, 2, 2, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 76, 32, -0.3425F, -1.7415F, 3.0F, 3, 2, 2, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 76, 32, -0.3425F, -1.7415F, 7.0F, 3, 2, 2, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 76, 32, -0.3425F, -1.7415F, 11.0F, 3, 2, 2, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 76, 32, -0.3425F, -1.7415F, 15.0F, 3, 2, 2, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 76, 32, -0.3425F, -1.7415F, 19.0F, 3, 2, 2, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(5.0F, -0.649F, -27.0027F);
        setRotationAngle(bone17, 0.0F, 0.0F, -0.2618F);
        deagle.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 76, 32, -2.6575F, -1.7415F, -1.0F, 3, 2, 2, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 76, 32, -2.6575F, -1.7415F, 3.0F, 3, 2, 2, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 76, 32, -2.6575F, -1.7415F, 7.0F, 3, 2, 2, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 76, 32, -2.6575F, -1.7415F, 11.0F, 3, 2, 2, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 76, 32, -2.6575F, -1.7415F, 15.0F, 3, 2, 2, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 76, 32, -2.6575F, -1.7415F, 19.0F, 3, 2, 2, 0.0F, true));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(0.0F, 2.2885F, -7.0027F);
        setRotationAngle(bone21, -0.6109F, 0.0F, 0.0F);
        deagle.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 8, 74, -1.5F, -3.6252F, 3.6442F, 3, 2, 1, 0.0F, false));

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(0.0F, 0.0F, 0.0F);
        deagle.addChild(ironsights);
        ironsights.cubeList.add(new ModelBox(ironsights, 4, 76, -1.0F, -13.0F, 20.3438F, 2, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 13, 72, -3.0F, -14.5F, 20.3438F, 2, 3, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 16, 82, 1.0F, -14.5F, 20.3438F, 2, 3, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 4, 76, -1.0F, -15.0F, -28.6563F, 2, 3, 2, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(-2.5F, -13.0F, 17.8438F);
        setRotationAngle(bone22, -0.3491F, 0.0F, 0.0F);
        ironsights.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 13, 72, -0.5F, -2.2646F, 1.8362F, 1, 3, 1, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 13, 72, 4.5F, -2.2646F, 1.8362F, 1, 3, 1, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, -11.5F, -27.1563F);
        setRotationAngle(bone, 0.1745F, 0.0F, 0.0F);
        ironsights.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 4, 76, -1.0F, -3.36F, 0.1002F, 2, 3, 1, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, -11.5F, -27.1563F);
        setRotationAngle(bone23, 0.1745F, 1.5708F, 0.0F);
        ironsights.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 4, 76, -0.5F, -3.2732F, 0.5926F, 2, 3, 1, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -11.5F, -27.1563F);
        setRotationAngle(bone24, 0.1745F, -1.5708F, 0.0F);
        ironsights.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 4, 76, -1.5F, -3.2732F, 0.5926F, 2, 3, 1, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(0.0F, -11.5F, -27.1563F);
        setRotationAngle(bone25, 0.1745F, 0.0F, 0.1745F);
        ironsights.addChild(bone25);
        bone25.cubeList.add(new ModelBox(bone25, 4, 76, -1.5926F, -3.1366F, 0.0608F, 1, 3, 1, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(0.0F, -11.5F, -27.1563F);
        setRotationAngle(bone26, 0.1745F, 0.0F, -0.1745F);
        ironsights.addChild(bone26);
        bone26.cubeList.add(new ModelBox(bone26, 4, 76, 0.5926F, -3.1366F, 0.0608F, 1, 3, 1, 0.0F, true));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, -35.0F, 0.0F);
        deagle.addChild(magazine);
        magazine.cubeList.add(new ModelBox(magazine, 0, 78, -3.5F, 58.4991F, 6.2021F, 7, 3, 7, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 67, -3.5F, 41.4991F, 8.2021F, 7, 17, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 65, -3.5F, 41.4991F, 13.2021F, 7, 20, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 65, -3.5F, 36.4991F, 17.2021F, 7, 5, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 3, 73, -3.5F, 36.4991F, 8.2021F, 7, 5, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 8, 70, 2.5F, 36.4991F, 9.2021F, 1, 5, 8, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 80, -3.5F, 36.4991F, 9.2021F, 1, 5, 8, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 100, -0.5F, 39.9991F, 14.7021F, 1, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 100, -1.0F, 40.4991F, 14.7021F, 2, 1, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 14, 110, -1.5F, 40.4991F, 15.7021F, 3, 1, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 100, -1.0F, 39.9991F, 15.7021F, 2, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 16, 105, -0.5F, 39.4991F, 15.7021F, 1, 3, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 14, 110, -1.5F, 40.4991F, 10.7021F, 3, 1, 4, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 100, -1.0F, 39.9991F, 9.7021F, 2, 2, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 16, 105, -0.5F, 39.4991F, 10.7021F, 1, 3, 4, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 14, 110, -1.5F, 36.7491F, 15.7021F, 3, 1, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 100, -1.0F, 36.2491F, 15.7021F, 2, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 16, 105, -0.5F, 35.7491F, 15.7021F, 1, 3, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 100, -0.5F, 36.2491F, 14.7021F, 1, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 100, -1.0F, 36.7491F, 14.7021F, 2, 1, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 16, 105, -0.5F, 35.7491F, 10.7021F, 1, 3, 4, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 100, -1.0F, 36.2491F, 9.7021F, 2, 2, 5, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 14, 110, -1.5F, 36.7491F, 10.7021F, 3, 1, 4, 0.0F, true));
        this.initAnimations();
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.561f, 0.268f, 0.085f);
        initAimingAnimationStates(0.268f, 0.193f, 0.268f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
        reloadAnimation = new ReloadAnimation(magazine, ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION);
    }

    @Override
    public String textureName() {
        return "deagle";
    }

    @Override
    public void render(ItemStack stack) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null) {
            GlStateManager.pushMatrix();
            this.renderDeagle(stack);
            GlStateManager.popMatrix();
        }
    }

    private void renderDeagle(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultPistolTransform();
        GlStateManager.scale(0.39999992, 0.39999992, 0.39999992);
        GlStateManager.translate(0.0, 11.0, -8.0);
        deagle.render(1f);
        GlStateManager.popMatrix();
        renderRedDot(0, 3, -2, 0.9F, stack);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
