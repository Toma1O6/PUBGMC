package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelScorpion extends ModelGun {

    private final ModelRenderer charging_handle;
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
    private final ModelRenderer bullet;
    private final ModelRenderer magazine;
    private final ModelRenderer bone6;
    private final ModelRenderer bone5;
    private final ModelRenderer bone4;
    private final ModelRenderer bone3;
    private final ModelRenderer bolt;
    private final ModelRenderer gun;
    private final ModelRenderer bone2;
    private final ModelRenderer bone18;
    private final ModelRenderer bone21;
    private final ModelRenderer bone22;
    private final ModelRenderer bone23;
    private final ModelRenderer bone25;
    private final ModelRenderer bone24;
    private final ModelRenderer bone20;
    private final ModelRenderer bone26;
    private final ModelRenderer bone27;
    private final ModelRenderer bone19;
    private final ModelRenderer bone;

    public ModelScorpion() {
        textureWidth = 512;
        textureHeight = 512;

        charging_handle = new ModelRenderer(this);
        charging_handle.setRotationPoint(-7.5F, 24.0F, -11.184F);
        charging_handle.cubeList.add(new ModelBox(charging_handle, 10, 78, 4.0F, -23.0469F, -3.9219F, 7, 1, 1, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 10, 78, 11.0F, -23.0469F, -4.4219F, 1, 1, 2, 0.0F, false));
        charging_handle.cubeList.add(new ModelBox(charging_handle, 10, 78, 3.0F, -23.0469F, -4.4219F, 1, 1, 2, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(11.5F, -7.5F, -3.4219F);
        charging_handle.addChild(bone7);
        setRotationAngle(bone7, -0.2618F, 0.0F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 10, 78, -0.5F, -15.0342F, -4.8944F, 1, 1, 2, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 10, 78, -8.5F, -15.0342F, -4.8944F, 1, 1, 2, 0.0F, true));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(11.5F, -7.5F, -3.4219F);
        charging_handle.addChild(bone8);
        setRotationAngle(bone8, -0.5236F, 0.0F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 10, 78, -0.5F, -13.531F, -8.5234F, 1, 1, 2, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 10, 78, -8.5F, -13.531F, -8.5234F, 1, 1, 2, 0.0F, true));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(11.5F, -7.5F, -3.4219F);
        charging_handle.addChild(bone9);
        setRotationAngle(bone9, -0.7854F, 0.0F, 0.0F);
        bone9.cubeList.add(new ModelBox(bone9, 10, 78, -0.5F, -11.1397F, -11.6397F, 1, 1, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 10, 78, -8.5F, -11.1397F, -11.6397F, 1, 1, 2, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(11.5F, -7.5F, -3.4219F);
        charging_handle.addChild(bone10);
        setRotationAngle(bone10, -1.0472F, 0.0F, 0.0F);
        bone10.cubeList.add(new ModelBox(bone10, 10, 78, -0.5F, -8.0234F, -14.031F, 1, 1, 2, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 10, 78, -8.5F, -8.0234F, -14.031F, 1, 1, 2, 0.0F, true));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(11.5F, -7.5F, -3.4219F);
        charging_handle.addChild(bone11);
        setRotationAngle(bone11, -1.309F, 0.0F, 0.0F);
        bone11.cubeList.add(new ModelBox(bone11, 10, 78, -0.5F, -4.3944F, -15.5342F, 1, 1, 2, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 10, 78, -8.5F, -4.3944F, -15.5342F, 1, 1, 2, 0.0F, true));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(11.5F, -7.5F, -3.4219F);
        charging_handle.addChild(bone12);
        setRotationAngle(bone12, -1.5708F, 0.0F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 10, 78, -0.5F, -0.5F, -16.0469F, 1, 1, 2, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 10, 78, -8.5F, -0.5F, -16.0469F, 1, 1, 2, 0.0F, true));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(11.5F, -7.5F, -3.4219F);
        charging_handle.addChild(bone13);
        setRotationAngle(bone13, -1.8326F, 0.0F, 0.0F);
        bone13.cubeList.add(new ModelBox(bone13, 10, 78, -0.5F, 3.3944F, -15.5342F, 1, 1, 2, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 10, 78, -8.5F, 3.3944F, -15.5342F, 1, 1, 2, 0.0F, true));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(11.5F, -7.5F, -3.4219F);
        charging_handle.addChild(bone14);
        setRotationAngle(bone14, -2.0944F, 0.0F, 0.0F);
        bone14.cubeList.add(new ModelBox(bone14, 10, 78, -0.5F, 7.0234F, -14.031F, 1, 1, 2, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 10, 78, -8.5F, 7.0234F, -14.031F, 1, 1, 2, 0.0F, true));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(11.5F, -7.5F, -3.4219F);
        charging_handle.addChild(bone15);
        setRotationAngle(bone15, -2.3562F, 0.0F, 0.0F);
        bone15.cubeList.add(new ModelBox(bone15, 10, 78, -0.5F, 10.1397F, -11.6397F, 1, 1, 2, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 10, 78, -8.5F, 10.1397F, -11.6397F, 1, 1, 2, 0.0F, true));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(11.5F, -7.5F, -3.4219F);
        charging_handle.addChild(bone16);
        setRotationAngle(bone16, -2.618F, 0.0F, 0.0F);
        bone16.cubeList.add(new ModelBox(bone16, 10, 78, -0.5F, 12.531F, -8.5234F, 1, 1, 2, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 10, 78, -8.5F, 12.531F, -8.5234F, 1, 1, 2, 0.0F, true));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(11.5F, -7.5F, -3.4219F);
        charging_handle.addChild(bone17);
        setRotationAngle(bone17, -2.8798F, 0.0F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 10, 78, -0.5F, 14.0342F, -4.8944F, 1, 1, 2, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 10, 78, -8.5F, 14.0342F, -4.8944F, 1, 1, 2, 0.0F, true));

        bullet = new ModelRenderer(this);
        bullet.setRotationPoint(0.0F, 24.0F, 0.0F);
        bullet.cubeList.add(new ModelBox(bullet, 9, 502, -1.0F, -22.3437F, -6.9492F, 2, 2, 3, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 9, 502, -1.0F, -22.3437F, -3.7266F, 2, 2, 1, 0.0F, true));
        bullet.cubeList.add(new ModelBox(bullet, 9, 502, -0.5F, -21.8437F, -7.582F, 1, 1, 4, 0.0F, true));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 44.1016F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 9, 144, 1.0F, -42.0469F, -8.0F, 1, 3, 6, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 9, 144, -2.0F, -42.0469F, -8.0F, 1, 3, 6, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 9, 144, -1.0F, -41.0469F, -3.0F, 2, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 9, 144, -1.0F, -40.4727F, -8.0F, 2, 2, 1, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 9, 144, -2.5F, -37.0469F, -8.5F, 5, 1, 7, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 9, 144, -2.0F, -39.0469F, -8.0F, 4, 2, 6, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -17.0F, -5.0F);
        magazine.addChild(bone6);
        setRotationAngle(bone6, -0.3491F, 0.0F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 9, 144, -2.5F, -4.3076F, -8.147F, 5, 1, 7, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 9, 144, -2.0F, -6.3076F, -7.647F, 4, 2, 6, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 9, 144, -2.5F, -7.3076F, -8.147F, 5, 1, 7, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -17.0F, -5.0F);
        magazine.addChild(bone5);
        setRotationAngle(bone5, -0.2618F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 9, 144, -2.5F, -8.3797F, -7.5057F, 5, 1, 7, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 9, 144, -2.0F, -10.3797F, -7.0057F, 4, 2, 6, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 9, 144, -2.5F, -11.3797F, -7.5057F, 5, 1, 7, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -17.0F, -5.0F);
        magazine.addChild(bone4);
        setRotationAngle(bone4, -0.1745F, 0.0F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 9, 144, -2.5F, -12.3805F, -6.512F, 5, 1, 7, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 9, 144, -2.0F, -14.3805F, -6.012F, 4, 2, 6, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 9, 144, -2.5F, -15.3805F, -6.512F, 5, 1, 7, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, -17.0F, -5.0F);
        magazine.addChild(bone3);
        setRotationAngle(bone3, -0.0873F, 0.0F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 9, 144, -2.5F, -16.2794F, -5.1734F, 5, 1, 7, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 9, 144, -2.0F, -18.2794F, -4.6734F, 4, 2, 6, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 9, 144, -2.5F, -19.2794F, -5.1734F, 5, 1, 7, 0.0F, false));

        bolt = new ModelRenderer(this);
        bolt.setRotationPoint(0.0F, 24.0F, 0.0F);
        bolt.cubeList.add(new ModelBox(bolt, 66, 10, -1.5F, -25.8469F, -8.0F, 3, 1, 7, 0.0F, false));

        gun = new ModelRenderer(this);
        gun.setRotationPoint(0.0F, 24.0F, 0.0F);
        gun.cubeList.add(new ModelBox(gun, 92, 27, -3.0F, -19.0469F, -8.0F, 1, 1, 3, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 92, 27, -3.0F, -19.0469F, -4.0F, 1, 2, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 88, 38, 2.0F, -19.0469F, -8.0F, 1, 1, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 92, 27, -3.0F, -20.0469F, -12.0F, 6, 2, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 92, 27, -2.5F, -19.4102F, -16.9023F, 5, 2, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 92, 27, -1.2071F, -17.703F, -16.9023F, 3, 1, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 92, 27, -1.7929F, -17.703F, -16.9023F, 1, 1, 7, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 80, 21, -3.0F, -20.0469F, -17.0F, 6, 1, 5, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 92, 27, -3.0F, -20.0469F, -8.0F, 6, 1, 6, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 92, 27, 2.0F, -19.0469F, -4.0F, 1, 2, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 83, 23, -3.0F, -20.0469F, -2.0F, 6, 3, 14, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 83, 23, -2.0F, -17.0469F, -0.2734F, 4, 3, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 83, 23, -2.0F, -14.8469F, 0.0266F, 4, 1, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 83, 23, -3.0F, -25.0469F, 8.0F, 6, 5, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 34, 44, -4.0F, -24.7656F, 9.6055F, 8, 2, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 34, 44, 3.0F, -24.2656F, 11.6055F, 1, 1, 11, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 34, 44, 3.0F, -24.2656F, 22.6055F, 1, 1, 10, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 34, 44, -4.0F, -24.2656F, 11.6055F, 1, 1, 11, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 34, 44, -4.0F, -24.2656F, 22.6055F, 1, 1, 10, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 34, 44, -4.0F, -22.8514F, 33.0197F, 1, 4, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 34, 44, -4.0F, -22.8514F, 38.8481F, 1, 4, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 34, 44, -4.0F, -18.4372F, 34.4339F, 1, 1, 4, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 34, 44, 3.0F, -18.4372F, 34.4339F, 1, 1, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 34, 44, 3.0F, -22.8514F, 33.0197F, 1, 4, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 34, 44, 3.0F, -22.8514F, 38.8481F, 1, 4, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 34, 44, -0.4142F, -24.2656F, 38.8481F, 3, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 34, 44, -2.5858F, -24.2656F, 38.8481F, 3, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 72, 9, -3.5F, -22.0469F, -16.0F, 7, 2, 18, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 73, 12, -3.5F, -25.0469F, -16.0F, 7, 2, 18, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 96, 82, -3.0F, -23.0469F, -7.0F, 6, 1, 9, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 96, 82, -3.0F, -23.0469F, -16.0F, 6, 1, 9, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 91, 24, -3.5F, -25.0469F, 2.0F, 7, 5, 6, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 10, -2.5F, -26.0469F, -1.0F, 5, 1, 9, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 10, -2.5F, -26.0469F, -17.0F, 5, 1, 9, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 10, 1.5F, -26.0469F, -8.0F, 1, 1, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 10, -2.5F, -26.0469F, -8.0F, 1, 1, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 10, -1.5F, -25.1469F, -8.0F, 3, 1, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 10, -1.0F, -27.0469F, 6.0F, 2, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 10, 1.0F, -28.0469F, 6.0F, 1, 2, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 10, -1.0F, -28.0469F, -16.0F, 2, 2, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 10, -2.0F, -28.0469F, 6.0F, 1, 2, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 91, 24, -3.5F, -25.0469F, -17.0F, 7, 5, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 91, 24, -1.0F, -24.8125F, -20.0F, 2, 2, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 91, 24, -1.0F, -21.9841F, -20.0F, 2, 2, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 91, 24, 0.4142F, -23.3983F, -20.0F, 2, 2, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 91, 24, -1.0F, -23.3983F, -26.0F, 2, 2, 6, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 91, 24, -1.5F, -23.8983F, -24.5F, 3, 3, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 91, 24, -2.4142F, -23.3983F, -20.0F, 2, 2, 3, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        gun.addChild(bone2);
        setRotationAngle(bone2, -0.4363F, 0.0F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 92, 27, -3.0F, -15.7592F, -13.8296F, 1, 2, 3, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 92, 27, 2.0F, -15.7592F, -13.8296F, 1, 2, 3, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, -14.4102F, -13.4023F);
        gun.addChild(bone18);
        setRotationAngle(bone18, 0.0F, 0.0F, 0.7854F);
        bone18.cubeList.add(new ModelBox(bone18, 92, 27, -3.8891F, -1.3536F, -3.5F, 1, 1, 7, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 92, 27, -1.3536F, -3.8891F, -3.5F, 1, 1, 7, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(0.0F, 3.4219F, -1.1484F);
        gun.addChild(bone21);
        setRotationAngle(bone21, 0.2618F, 0.0F, 0.0F);
        bone21.cubeList.add(new ModelBox(bone21, 83, 23, -2.5F, -19.4094F, 10.6792F, 5, 10, 6, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, 11.4219F, -1.1484F);
        gun.addChild(bone22);
        setRotationAngle(bone22, 0.0349F, 0.0F, 0.0F);
        bone22.cubeList.add(new ModelBox(bone22, 83, 23, -2.5F, -20.9153F, 8.4142F, 5, 4, 6, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, 5.1836F, 16.2266F);
        gun.addChild(bone23);
        setRotationAngle(bone23, 0.9076F, 0.0F, 0.0F);
        bone23.cubeList.add(new ModelBox(bone23, 83, 23, -2.5F, -19.017F, 12.9157F, 5, 2, 2, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(-1.0F, 0.0F, 0.0F);
        gun.addChild(bone25);
        setRotationAngle(bone25, 0.0F, 0.0F, -0.7854F);
        bone25.cubeList.add(new ModelBox(bone25, 34, 44, 18.6939F, -14.6229F, 38.8481F, 1, 2, 1, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 34, 44, 14.0371F, -18.2797F, 38.8481F, 2, 1, 1, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(1.0F, 0.0F, 0.0F);
        gun.addChild(bone24);
        setRotationAngle(bone24, -0.7854F, 0.0F, 0.0F);
        bone24.cubeList.add(new ModelBox(bone24, 34, 44, -5.0F, -40.2139F, 5.8972F, 1, 1, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 34, 44, -5.0F, -37.6784F, 10.0185F, 1, 1, 2, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 34, 44, -5.0F, -41.5068F, 13.8469F, 1, 2, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 34, 44, 2.0F, -37.6784F, 10.0185F, 1, 1, 2, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 34, 44, 2.0F, -41.5068F, 13.8469F, 1, 2, 1, 0.0F, false));
        bone24.cubeList.add(new ModelBox(bone24, 34, 44, 2.0F, -40.2139F, 5.8972F, 1, 1, 2, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.0F, -31.5469F, 10.0F);
        gun.addChild(bone20);
        setRotationAngle(bone20, -0.1745F, 0.0F, 0.0F);
        bone20.cubeList.add(new ModelBox(bone20, 83, 23, -3.0F, 6.054F, -0.9017F, 6, 1, 4, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-1.5F, -27.0469F, 6.5F);
        gun.addChild(bone26);
        setRotationAngle(bone26, 0.1745F, 0.0F, 0.0F);
        bone26.cubeList.add(new ModelBox(bone26, 66, 10, -0.5F, -0.898F, -0.3339F, 1, 2, 1, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 66, 10, 2.5F, -0.898F, -0.3339F, 1, 2, 1, 0.0F, false));
        bone26.cubeList.add(new ModelBox(bone26, 66, 10, 0.5F, -4.7182F, -21.9997F, 2, 2, 1, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-1.5F, -27.0469F, 6.5F);
        gun.addChild(bone27);
        setRotationAngle(bone27, -0.1745F, 0.0F, 0.0F);
        bone27.cubeList.add(new ModelBox(bone27, 66, 10, -0.5F, -0.898F, -0.6661F, 1, 2, 1, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 66, 10, 2.5F, -0.898F, -0.6661F, 1, 2, 1, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 66, 10, 0.5F, 2.9223F, -22.3318F, 2, 2, 1, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, -27.5469F, -4.5F);
        gun.addChild(bone19);
        setRotationAngle(bone19, 0.0F, 0.0F, 0.7854F);
        bone19.cubeList.add(new ModelBox(bone19, 66, 10, 2.8284F, -0.7071F, -12.5F, 1, 1, 25, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 66, 10, 3.2426F, -0.7071F, -12.5F, 1, 1, 25, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 66, 10, -0.7071F, 3.2426F, -12.5F, 1, 1, 25, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 66, 10, -0.7071F, 2.8284F, -12.5F, 1, 1, 25, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 0.0F, 0.0F);
        gun.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, 0.7854F);
        bone.cubeList.add(new ModelBox(bone, 91, 24, -16.838F, -18.2522F, -20.0F, 2, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 91, 24, -14.4238F, -16.838F, -20.0F, 1, 2, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 91, 24, -18.2522F, -16.838F, -20.0F, 1, 2, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 91, 24, -16.838F, -14.4238F, -20.0F, 2, 1, 3, 0.0F, false));

        addEntry(AnimationElement.MAGAZINE, stack -> magazine);
        addEntry(AnimationElement.CHARGING, stack -> charging_handle);
        addEntry(AnimationElement.BOLT, stack -> bolt);
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
        GlStateManager.translate(0.0, 9.499992, -9.0);
    }

    @Override
    public void renderModel(ItemStack stack) {
        gun.render(1f);
    }
}
