package dev.toma.pubgmc.client.models.atachments;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.models.renderer.ExtendedModelBox;
import dev.toma.pubgmc.common.items.attachment.ItemScope;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class ModelScope8x extends ModelAttachment<ItemScope> {
    static final ResourceLocation RETICLE = Pubgmc.getResource("textures/overlay/scope8x.png");
    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer bone6;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer bone9;
    private final ModelRenderer bone10;
    private final ModelRenderer reticle;
    private final ModelRenderer overlay;

    public ModelScope8x() {
        textureWidth = 128;
        textureHeight = 128;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -10.0F, -3.5F, 4, 1, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, 3.0F, -15.0F, -3.5F, 1, 4, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -4.0F, -15.0F, -3.5F, 1, 4, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -17.0F, -3.5F, 4, 1, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -9.0F, -7.5F, 4, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, 4.0F, -15.0F, -7.5F, 1, 4, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -5.0F, -15.0F, -7.5F, 1, 4, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -18.0F, -7.5F, 4, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -9.0F, 4.5F, 4, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -9.0F, 11.5F, 4, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, 4.0F, -15.0F, 4.5F, 1, 4, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, 4.0F, -15.0F, 11.5F, 1, 4, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -5.0F, -15.0F, 4.5F, 1, 4, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -5.0F, -15.0F, 11.5F, 1, 4, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -18.0F, 4.5F, 4, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -18.0F, 11.5F, 4, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, 3.0F, -15.0F, -13.5F, 1, 4, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -10.0F, -13.5F, 4, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -17.0F, -13.5F, 4, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -4.0F, -15.0F, -13.5F, 1, 4, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, 3.0F, -15.0F, 8.5F, 1, 4, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -10.0F, 8.5F, 4, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -17.0F, 8.5F, 4, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -4.0F, -15.0F, 8.5F, 1, 4, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, 4.0F, -15.0F, -15.5F, 1, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -18.0F, -15.5F, 4, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -5.0F, -15.0F, -15.5F, 1, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -9.0F, -15.5F, 4, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.5F, -18.5F, -20.5F, 5, 1, 5, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, 4.5F, -15.5F, -20.5F, 1, 5, 5, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.5F, -8.5F, -20.5F, 5, 1, 5, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -5.5F, -15.5F, -20.5F, 1, 5, 5, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -17.0F, -3.5F, 4, 1, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, 3.0F, -15.0F, -3.5F, 1, 4, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -10.0F, -3.5F, 4, 1, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -4.0F, -15.0F, -3.5F, 1, 4, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -17.0F, -13.5F, 4, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, 3.0F, -15.0F, -13.5F, 1, 4, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -10.0F, -13.5F, 4, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -4.0F, -15.0F, -13.5F, 1, 4, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 118, 100, 5.1094F, -13.1563F, -6.2813F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, 4.7969F, -12.6563F, -5.7813F, 1, 0, 0, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 118, 100, 5.1094F, -13.2344F, 5.75F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, 4.7969F, -12.7344F, 6.25F, 1, 0, 0, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -1.0F, -8.0F, -7.5F, 2, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -1.0F, -8.0F, 4.5F, 2, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 71, 68, -2.0F, -7.0F, -7.5F, 4, 2, 16, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(8.5F, -4.0F, 16.5F);
        bone.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.7854F);
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 3.5962F, -11.9602F, -8.0F, 1, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 3.5962F, -13.7886F, -8.0F, 1, 2, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 0.7678F, -9.1317F, -8.0F, 1, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.0607F, -9.1317F, -8.0F, 2, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -3.8891F, -12.9602F, -8.0F, 1, 2, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -0.2322F, -16.617F, -8.0F, 2, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -3.8891F, -13.7886F, -8.0F, 1, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.0607F, -16.617F, -8.0F, 1, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.0607F, -16.617F, -20.0F, 1, 1, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -0.2322F, -16.617F, -20.0F, 2, 1, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 3.5962F, -11.9602F, -20.0F, 1, 1, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.0607F, -9.1317F, -20.0F, 2, 1, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 0.7678F, -9.1317F, -20.0F, 1, 1, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 3.5962F, -13.7886F, -20.0F, 1, 2, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -3.8891F, -12.9602F, -20.0F, 1, 2, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -3.8891F, -13.7886F, -20.0F, 1, 1, 8, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.0607F, -16.617F, -30.0F, 1, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -0.2322F, -16.617F, -30.0F, 2, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 3.5962F, -11.9602F, -30.0F, 1, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.0607F, -9.1317F, -30.0F, 2, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 0.7678F, -9.1317F, -30.0F, 1, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 3.5962F, -13.7886F, -30.0F, 1, 2, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -3.8891F, -12.9602F, -30.0F, 1, 2, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -3.8891F, -13.7886F, -30.0F, 1, 1, 6, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 0.4749F, -17.3241F, -24.0F, 2, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.7678F, -17.3241F, -24.0F, 3, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -4.5962F, -14.4957F, -24.0F, 1, 3, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -4.5962F, -12.253F, -24.0F, 1, 2, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.7678F, -8.4246F, -24.0F, 2, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -0.5251F, -8.4246F, -24.0F, 3, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 4.3033F, -13.253F, -24.0F, 1, 3, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 4.3033F, -14.4957F, -24.0F, 1, 2, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.7678F, -17.3241F, -12.0F, 3, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.7678F, -17.3241F, -5.0F, 3, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 0.4749F, -17.3241F, -12.0F, 2, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 0.4749F, -17.3241F, -5.0F, 2, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 4.3033F, -14.4957F, -12.0F, 1, 2, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 4.3033F, -14.4957F, -5.0F, 1, 2, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 4.3033F, -13.253F, -12.0F, 1, 3, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 4.3033F, -13.253F, -5.0F, 1, 3, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -0.5251F, -8.4246F, -12.0F, 3, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -0.5251F, -8.4246F, -5.0F, 3, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.7678F, -8.4246F, -12.0F, 2, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.7678F, -8.4246F, -5.0F, 2, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -4.5962F, -12.253F, -12.0F, 1, 2, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -4.5962F, -12.253F, -5.0F, 1, 2, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -4.5962F, -14.4957F, -12.0F, 1, 3, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -4.5962F, -14.4957F, -5.0F, 1, 3, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.7678F, -17.3241F, -32.0F, 3, 1, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 0.4749F, -17.3241F, -32.0F, 2, 1, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 4.3033F, -14.4957F, -32.0F, 1, 2, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 4.3033F, -13.253F, -32.0F, 1, 3, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -0.5251F, -8.4246F, -32.0F, 3, 1, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.7678F, -8.4246F, -32.0F, 2, 1, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -4.5962F, -12.253F, -32.0F, 1, 2, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -4.5962F, -14.4957F, -32.0F, 1, 3, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.7678F, -18.0312F, -37.0F, 3, 1, 5, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 0.4749F, -18.0312F, -37.0F, 2, 1, 5, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 5.0104F, -14.4957F, -37.0F, 1, 2, 5, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, 5.0104F, -13.253F, -37.0F, 1, 3, 5, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -0.5251F, -7.7175F, -37.0F, 3, 1, 5, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -1.7678F, -7.7175F, -37.0F, 2, 1, 5, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -5.3033F, -12.253F, -37.0F, 1, 2, 5, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 68, -5.3033F, -14.4957F, -37.0F, 1, 3, 5, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(6.0F, -12.75F, -5.75F);
        bone.addChild(bone3);
        setRotationAngle(bone3, -0.1745F, 0.0F, 0.0F);
        bone3.cubeList.add(new ModelBox(bone3, 118, 100, -0.8906F, -0.4419F, -0.5617F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 118, 100, -0.8906F, -2.608F, 11.2732F, 1, 1, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(6.0F, -12.75F, -5.75F);
        bone.addChild(bone4);
        setRotationAngle(bone4, -0.3491F, 0.0F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 118, 100, -0.8906F, -0.4716F, -0.5979F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 118, 100, -0.8906F, -4.66F, 10.6811F, 1, 1, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(6.0F, -12.75F, -5.75F);
        bone.addChild(bone5);
        setRotationAngle(bone5, -0.5236F, 0.0F, 0.0F);
        bone5.cubeList.add(new ModelBox(bone5, 118, 100, -0.8906F, -0.4947F, -0.6387F, 1, 1, 1, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 118, 100, -0.8906F, -6.578F, 9.7416F, 1, 1, 1, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(6.0F, -12.75F, -5.75F);
        bone.addChild(bone6);
        setRotationAngle(bone6, -0.6981F, 0.0F, 0.0F);
        bone6.cubeList.add(new ModelBox(bone6, 118, 100, -0.8906F, -0.5103F, -0.6829F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 118, 100, -0.8906F, -8.3037F, 8.4834F, 1, 1, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(6.0F, -12.75F, -5.75F);
        bone.addChild(bone7);
        setRotationAngle(bone7, -0.8727F, 0.0F, 0.0F);
        bone7.cubeList.add(new ModelBox(bone7, 118, 100, -0.8906F, -0.518F, -0.7291F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 118, 100, -0.8906F, -9.7847F, 6.9446F, 1, 1, 1, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(6.0F, -12.75F, -5.75F);
        bone.addChild(bone8);
        setRotationAngle(bone8, -1.0472F, 0.0F, 0.0F);
        bone8.cubeList.add(new ModelBox(bone8, 118, 100, -0.8906F, -0.5176F, -0.7759F, 1, 1, 1, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 118, 100, -0.8906F, -10.976F, 5.172F, 1, 1, 1, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(6.0F, -12.75F, -5.75F);
        bone.addChild(bone9);
        setRotationAngle(bone9, -1.2217F, 0.0F, 0.0F);
        bone9.cubeList.add(new ModelBox(bone9, 118, 100, -0.8906F, -0.509F, -0.822F, 1, 1, 1, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 118, 100, -0.8906F, -11.8414F, 3.2195F, 1, 1, 1, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(6.0F, -12.75F, -5.75F);
        bone.addChild(bone10);
        setRotationAngle(bone10, -1.3963F, 0.0F, 0.0F);
        bone10.cubeList.add(new ModelBox(bone10, 118, 100, -0.8906F, -0.4926F, -0.8659F, 1, 1, 1, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 118, 100, -0.8906F, -12.3546F, 1.1464F, 1, 1, 1, 0.0F, false));

        reticle = new ModelRenderer(this);
        reticle.setRotationPoint(0.0F, 24.0F, 0.0F);
        reticle.cubeList.add(new ExtendedModelBox(reticle, -3.0F, -16.0F, 13.85F, 6, 6, 0, 0.0F));

        overlay = new ModelRenderer(this);
        overlay.setRotationPoint(0.0F, 24.0F, 0.0F);
        overlay.cubeList.add(new ExtendedModelBox(overlay, -3.0F, -16.0F, 14.15F, 6, 6, 0, 0.0F));
        overlay.cubeList.add(new ExtendedModelBox(overlay, -4.0F, -17.0F, -18.85F, 8, 8, 0, 0.0F));
    }

    @Override
    public void render(float aimPct) {
        renderScope(bone, RETICLE, aimPct, reticle, overlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}