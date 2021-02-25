package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.client.models.ModelGun;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelS686 extends ModelGun {
    private final ModelRenderer s686;
    private final ModelRenderer barrels;
    private final ModelRenderer bone2;
    private final ModelRenderer bone11;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer bone;
    private final ModelRenderer bone5;
    private final ModelRenderer bone6;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bone9;
    private final ModelRenderer bone10;
    private final ModelRenderer bone12;
    private final ModelRenderer bone13;
    private final ModelRenderer bone14;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone15;
    private final ModelRenderer bone18;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone21;
    private final ModelRenderer bone24;
    private final ModelRenderer bone22;
    private final ModelRenderer bone23;

    public ModelS686() {
        super();
        textureWidth = 128;
        textureHeight = 128;

        s686 = new ModelRenderer(this);
        s686.setRotationPoint(0.0F, 24.0F, 0.0F);
        s686.cubeList.add(new ModelBox(s686, 0, 36, -2.0F, -9.7656F, -15.6875F, 4, 3, 12, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 2, 42, -2.0F, -10.7656F, -13.6875F, 4, 1, 1, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 2, 42, -2.0F, -11.7656F, -12.6875F, 4, 2, 1, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 2, 42, -2.0F, -12.7656F, -11.6875F, 4, 3, 7, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 2, 42, -2.0F, -13.0055F, -11.4652F, 4, 1, 10, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 0, 40, -0.8452F, -6.953F, -15.6875F, 2, 2, 14, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 0, 40, -1.1548F, -6.953F, -15.6875F, 1, 2, 14, 0.0F, true));
        s686.cubeList.add(new ModelBox(s686, 2, 42, -2.0F, -12.7656F, -4.6875F, 4, 3, 10, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 2, 42, -2.0F, -12.535F, 10.3319F, 4, 3, 1, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 2, 42, -2.0F, -13.0055F, 10.3319F, 4, 1, 1, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 38, 76, -1.5F, -10.9725F, 7.3319F, 3, 5, 6, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 43, 111, 0.5781F, -7.3475F, 9.7069F, 1, 1, 1, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 38, 76, -2.0F, -12.9725F, 11.3319F, 4, 3, 3, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 98, 48, -1.0F, -13.6756F, 11.3319F, 2, 1, 2, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 38, 76, -2.0F, -9.9725F, 13.3319F, 4, 4, 1, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 38, 76, -2.0F, -11.9725F, 14.3319F, 4, 6, 6, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 0, 34, -2.0F, -13.0055F, -1.4652F, 4, 1, 12, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 6, 42, -2.0F, -12.7656F, 5.3125F, 4, 3, 5, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 0, 36, -2.0F, -9.7656F, -3.6875F, 4, 3, 12, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 0, 40, -0.8452F, -6.953F, -1.6875F, 2, 2, 10, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 0, 40, -1.1548F, -6.953F, -1.6875F, 1, 2, 10, 0.0F, true));
        s686.cubeList.add(new ModelBox(s686, 43, 111, -1.5781F, -7.3475F, 9.7069F, 1, 1, 1, 0.0F, true));
        s686.cubeList.add(new ModelBox(s686, 38, 76, -0.6428F, -12.7386F, 14.3319F, 2, 1, 5, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 38, 76, -1.3572F, -12.7386F, 14.3319F, 1, 1, 5, 0.0F, true));
        s686.cubeList.add(new ModelBox(s686, 38, 76, -2.0F, -8.9725F, 20.3319F, 4, 3, 7, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 38, 76, -2.0F, -6.5506F, 31.035F, 4, 4, 8, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 38, 76, -2.0F, -9.2032F, 35.6189F, 4, 5, 6, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 38, 76, -2.0F, -9.2032F, 41.6189F, 4, 7, 3, 0.0F, false));
        s686.cubeList.add(new ModelBox(s686, 6, 71, -2.5F, -9.7032F, 44.6189F, 5, 10, 1, 0.0F, false));

        barrels = new ModelRenderer(this);
        barrels.setRotationPoint(0.0F, 0.0F, 0.0F);
        s686.addChild(barrels);
        barrels.cubeList.add(new ModelBox(barrels, 8, 74, -1.0F, -8.3438F, -18.9219F, 2, 2, 1, 0.0F, false));
        barrels.cubeList.add(new ModelBox(barrels, 8, 74, -1.0F, -12.5625F, -18.9219F, 2, 2, 1, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        barrels.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -6.5F, -24.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, 0.866F, -7.866F, -24.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -1.866F, -7.866F, -24.5F, 1, 1, 15, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -9.2321F, -24.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -6.5F, -39.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, 0.866F, -7.866F, -39.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -1.866F, -7.866F, -39.5F, 1, 1, 15, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -9.2321F, -39.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -6.5F, -54.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, 0.866F, -7.866F, -54.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -1.866F, -7.866F, -54.5F, 1, 1, 15, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -9.2321F, -54.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -10.7031F, -54.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, 0.866F, -12.0692F, -54.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -1.866F, -12.0692F, -54.5F, 1, 1, 15, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -13.4352F, -54.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -10.7031F, -39.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, 0.866F, -12.0692F, -39.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -1.866F, -12.0692F, -39.5F, 1, 1, 15, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -13.4352F, -39.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -10.7031F, -24.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, 0.866F, -12.0692F, -24.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -1.866F, -12.0692F, -24.5F, 1, 1, 15, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -13.4352F, -24.5F, 1, 1, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -13.4352F, -9.6719F, 1, 1, 11, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 53, 28, -0.5F, -13.9196F, -52.3594F, 1, 1, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 32, 33, -0.5F, -13.4352F, 1.3281F, 1, 1, 10, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone11, -0.6981F, 0.0F, 0.0F);
        bone2.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 53, 28, -0.5F, 22.3502F, -48.2909F, 1, 1, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.5236F);
        barrels.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 3.183F, -5.5131F, -24.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 4.549F, -6.8792F, -24.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 1.817F, -6.8792F, -24.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 3.183F, -8.2452F, -24.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 3.183F, -5.5131F, -39.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 4.549F, -6.8792F, -39.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 1.817F, -6.8792F, -39.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 3.183F, -8.2452F, -39.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 3.183F, -5.5131F, -54.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 4.549F, -6.8792F, -54.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 1.817F, -6.8792F, -54.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 3.183F, -8.2452F, -54.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 5.2846F, -9.1532F, -54.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 6.6506F, -10.5192F, -54.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 3.9185F, -10.5192F, -54.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 5.2846F, -11.8852F, -54.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 5.2846F, -9.1532F, -39.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 6.6506F, -10.5192F, -39.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 3.9185F, -10.5192F, -39.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 5.2846F, -11.8852F, -39.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 5.2846F, -9.1532F, -24.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 6.6506F, -10.5192F, -24.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 3.9185F, -10.5192F, -24.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 5.2846F, -11.8852F, -24.5F, 1, 1, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 5.2846F, -11.8852F, -9.6719F, 1, 1, 11, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 32, 33, 5.2846F, -11.8852F, 1.3281F, 1, 1, 10, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone4, 0.0F, 0.0F, -1.0472F);
        barrels.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 5.8792F, -2.817F, -24.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 4.5131F, -4.183F, -24.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 5.8792F, -5.549F, -24.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 7.2452F, -4.183F, -24.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 5.8792F, -2.817F, -39.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 4.5131F, -4.183F, -39.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 5.8792F, -5.549F, -39.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 7.2452F, -4.183F, -39.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 5.8792F, -2.817F, -54.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 4.5131F, -4.183F, -54.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 5.8792F, -5.549F, -54.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 7.2452F, -4.183F, -54.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 9.5192F, -4.9185F, -54.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 8.1532F, -6.2846F, -54.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 9.5192F, -7.6506F, -54.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 10.8852F, -6.2846F, -54.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 9.5192F, -4.9185F, -39.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 8.1532F, -6.2846F, -39.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 9.5192F, -7.6506F, -39.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 10.8852F, -6.2846F, -39.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 9.5192F, -4.9185F, -24.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 8.1532F, -6.2846F, -24.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 9.5192F, -7.6506F, -24.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 10.8852F, -6.2846F, -24.5F, 1, 1, 15, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 10.8852F, -6.2846F, -9.6719F, 1, 1, 11, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 32, 33, 10.8852F, -6.2846F, 1.3281F, 1, 1, 10, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, -7.7031F, -13.1875F);
        setRotationAngle(bone, 1.1345F, 0.0F, 0.0F);
        s686.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 2, 42, -2.0F, -3.1374F, 0.8127F, 4, 2, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 2, 42, -2.0F, 17.8818F, 8.2366F, 4, 2, 3, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -7.7031F, -13.1875F);
        setRotationAngle(bone5, 0.1745F, 0.0F, 0.0F);
        s686.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 2, 42, -2.0F, -4.9227F, -0.3831F, 4, 2, 3, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -7.7031F, -12.1875F);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.4363F);
        s686.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 0, 40, -2.2088F, 0.0044F, -3.5F, 1, 2, 14, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 0, 40, -2.2088F, 0.0044F, 10.5F, 1, 2, 10, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, -7.7031F, -12.1875F);
        setRotationAngle(bone7, 0.0F, 0.0F, 0.4363F);
        s686.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 0, 40, 1.2088F, 0.0044F, -3.5F, 1, 2, 14, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 0, 40, 1.2088F, 0.0044F, 10.5F, 1, 2, 10, 0.0F, true));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, -7.7031F, -2.1875F);
        setRotationAngle(bone8, 0.4363F, 0.0F, 0.0F);
        s686.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 2, 42, -2.0F, 2.0533F, 10.0269F, 4, 2, 3, 0.0F, true));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.8594F, -13.41F, -1.6681F);
        setRotationAngle(bone9, 0.0F, 0.0F, 0.3491F);
        s686.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 98, 48, -0.6697F, -0.6033F, 13.0F, 1, 1, 2, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-0.8594F, -13.41F, -1.6681F);
        setRotationAngle(bone10, 0.0F, 0.0F, -0.3491F);
        s686.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 98, 48, -0.3303F, -0.6033F, 13.0F, 1, 1, 2, 0.0F, true));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-0.0F, -7.4725F, -0.1681F);
        setRotationAngle(bone12, 0.0F, 0.5236F, 0.0F);
        s686.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 38, 76, -6.0179F, -2.5F, 10.6913F, 1, 4, 2, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.0F, -7.4725F, -0.1681F);
        setRotationAngle(bone13, 0.0F, -0.5236F, 0.0F);
        s686.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 38, 76, 5.0179F, -2.5F, 10.6913F, 1, 4, 2, 0.0F, true));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(-0.0F, -11.4725F, -1.1681F);
        setRotationAngle(bone14, 0.0F, 0.0F, 0.5236F);
        s686.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 38, 76, 1.4821F, 0.299F, 12.5F, 1, 1, 2, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(-0.0F, -16.4725F, -1.1681F);
        setRotationAngle(bone16, 0.0F, 0.0F, 0.8727F);
        s686.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 38, 76, 3.7328F, 1.3605F, 15.5F, 1, 1, 5, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, -16.4725F, -1.1681F);
        setRotationAngle(bone17, 0.0F, 0.0F, -0.8727F);
        s686.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 38, 76, -4.7328F, 1.3605F, 15.5F, 1, 1, 5, 0.0F, true));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, -11.4725F, -1.1681F);
        setRotationAngle(bone15, 0.0F, 0.0F, -0.5236F);
        s686.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 38, 76, -2.4821F, 0.299F, 12.5F, 1, 1, 2, 0.0F, true));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, -8.0F, 0.0F);
        setRotationAngle(bone18, -0.5236F, 0.0F, 0.0F);
        s686.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 38, 76, -2.0F, -11.4723F, 9.9255F, 4, 1, 2, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, -8.0F, 0.0F);
        setRotationAngle(bone19, -0.8727F, 0.0F, 0.0F);
        s686.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 38, 76, -0.6428F, -17.855F, 8.7964F, 2, 1, 2, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 38, 76, -1.3572F, -17.855F, 8.7964F, 1, 1, 2, 0.0F, true));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(-0.0F, -12.4725F, 23.8319F);
        setRotationAngle(bone20, -0.3491F, 0.0F, 0.0F);
        s686.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 38, 76, -2.0F, 1.6669F, -3.1179F, 4, 3, 7, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 38, 76, -2.0F, 1.4475F, 16.6724F, 4, 3, 7, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-0.0F, -12.4725F, 23.8319F);
        setRotationAngle(bone21, -1.2217F, 0.0F, 0.0F);
        s686.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 38, 76, -2.0F, -2.4993F, 6.671F, 4, 3, 5, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 38, 76, -2.0F, -9.958F, 7.1035F, 4, 6, 3, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(-0.0F, -15.8631F, 27.535F);
        setRotationAngle(bone24, -1.3963F, 0.0F, 0.0F);
        s686.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 38, 76, -2.0F, -1.8805F, 8.8307F, 4, 3, 5, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(-0.0F, -10.8044F, 29.2651F);
        setRotationAngle(bone22, -0.7854F, 0.0F, 0.0F);
        s686.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 38, 76, -2.0F, 2.5324F, -0.7986F, 4, 1, 1, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 38, 76, -2.0F, 5.1178F, 0.3726F, 4, 1, 1, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(-0.0F, -10.8044F, 32.2651F);
        setRotationAngle(bone23, -1.1345F, 0.0F, 0.0F);
        s686.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 38, 76, -2.0F, 5.0297F, -0.2125F, 4, 2, 2, 0.0F, false));
    }

    @Override
    public String textureName() {
        return "slr";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.525f, 0.23f, 0.35f);
        initAimingAnimationStates(0.23f);
    }

    @Override
    public void render(ItemStack stack) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            GlStateManager.pushMatrix();
            {
                renderS686(data.isAiming(), stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderS686(boolean aim, ItemStack stack) {
        GlStateManager.pushMatrix();
        {
            ModelTransformationHelper.defaultShotgunTransform();
            GlStateManager.translate(-0.15, -5.300001, -10.0);
            if (aim) rotateModelForADSRendering();
            s686.render(1f);
        }
        GlStateManager.popMatrix();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
