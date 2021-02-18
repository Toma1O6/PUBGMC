package dev.toma.pubgmc.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelParachute extends ModelBase {
    private final ModelRenderer rope;
    private final ModelRenderer rope2;
    private final ModelRenderer rope3;
    private final ModelRenderer rope4;
    private final ModelRenderer pack;
    private final ModelRenderer top;
    private final ModelRenderer right;
    private final ModelRenderer left;

    public ModelParachute() {
        textureWidth = 64;
        textureHeight = 64;

        rope = new ModelRenderer(this);
        rope.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(rope, 0.0F, 0.0F, -0.1745F);
        rope.cubeList.add(new ModelBox(rope, 0, 48, -3.0F, -33.0F, 1.0F, 1, 15, 1, 0.0F, false));
        rope.cubeList.add(new ModelBox(rope, 0, 48, -3.0F, -18.0F, 1.0F, 1, 15, 1, 0.0F, false));

        rope2 = new ModelRenderer(this);
        rope2.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(rope2, 0.0F, 0.0F, 0.1745F);
        rope2.cubeList.add(new ModelBox(rope2, 0, 48, 1.0F, -18.0F, 1.0F, 1, 15, 1, 0.0F, false));
        rope2.cubeList.add(new ModelBox(rope2, 0, 48, 1.0F, -33.0F, 1.0F, 1, 15, 1, 0.0F, false));

        rope3 = new ModelRenderer(this);
        rope3.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(rope3, 0.0F, 0.0F, -0.3491F);
        rope3.cubeList.add(new ModelBox(rope3, 0, 48, -5.0F, -18.0F, 1.0F, 1, 15, 1, 0.0F, false));
        rope3.cubeList.add(new ModelBox(rope3, 0, 48, -5.0F, -33.0F, 1.0F, 1, 15, 1, 0.0F, false));

        rope4 = new ModelRenderer(this);
        rope4.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(rope4, 0.0F, 0.0F, 0.3491F);
        rope4.cubeList.add(new ModelBox(rope4, 0, 48, 3.0F, -18.0F, 0.0F, 1, 15, 1, 0.0F, false));
        rope4.cubeList.add(new ModelBox(rope4, 0, 48, 3.0F, -33.0F, 0.0F, 1, 15, 1, 0.0F, false));

        pack = new ModelRenderer(this);
        pack.setRotationPoint(0.0F, 24.0F, 0.0F);
        pack.cubeList.add(new ModelBox(pack, 0, 57, -6.0F, -3.0F, 0.0F, 11, 7, 3, 0.0F, false));
        pack.cubeList.add(new ModelBox(pack, 0, 0, -5.0F, -2.5F, 2.5F, 2, 6, 1, 0.0F, false));
        pack.cubeList.add(new ModelBox(pack, 0, 0, 2.0F, -2.5F, 2.5F, 2, 6, 1, 0.0F, false));

        top = new ModelRenderer(this);
        top.setRotationPoint(0.0F, 24.0F, 0.0F);
        top.cubeList.add(new ModelBox(top, 0, 0, -9.0F, -35.0F, -1.0F, 17, 5, 5, 0.0F, false));
        top.cubeList.add(new ModelBox(top, 0, 0, -9.0F, -34.0F, -7.0F, 17, 5, 6, 0.0F, false));
        top.cubeList.add(new ModelBox(top, 0, 0, -9.0F, -33.0F, -13.0F, 17, 5, 6, 0.0F, false));
        top.cubeList.add(new ModelBox(top, 0, 0, -9.0F, -34.0F, 4.0F, 17, 5, 6, 0.0F, false));
        top.cubeList.add(new ModelBox(top, 0, 0, -9.0F, -33.0F, 10.0F, 17, 5, 6, 0.0F, false));

        right = new ModelRenderer(this);
        right.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(right, 0.0F, 0.0F, -0.1745F);
        right.cubeList.add(new ModelBox(right, 0, 0, -16.0F, -35.0F, -1.0F, 14, 5, 5, 0.0F, false));
        right.cubeList.add(new ModelBox(right, 0, 0, -16.0F, -34.0F, -7.0F, 14, 5, 6, 0.0F, false));
        right.cubeList.add(new ModelBox(right, 0, 0, -15.0F, -33.0F, -13.0F, 13, 5, 6, 0.0F, false));
        right.cubeList.add(new ModelBox(right, 0, 0, -15.0F, -33.0F, 10.0F, 13, 5, 6, 0.0F, false));
        right.cubeList.add(new ModelBox(right, 0, 0, -16.0F, -34.0F, 4.0F, 14, 5, 6, 0.0F, false));
        right.cubeList.add(new ModelBox(right, 0, 0, -17.0F, -33.0F, -13.0F, 2, 5, 29, 0.0F, false));

        left = new ModelRenderer(this);
        left.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(left, 0.0F, 0.0F, 0.1745F);
        left.cubeList.add(new ModelBox(left, 0, 0, 1.0F, -35.0F, -1.0F, 15, 5, 5, 0.0F, false));
        left.cubeList.add(new ModelBox(left, 0, 0, 1.0F, -34.0F, -7.0F, 15, 5, 6, 0.0F, false));
        left.cubeList.add(new ModelBox(left, 0, 0, 1.0F, -33.0F, -13.0F, 14, 5, 6, 0.0F, false));
        left.cubeList.add(new ModelBox(left, 0, 0, 1.0F, -33.0F, 10.0F, 14, 5, 6, 0.0F, false));
        left.cubeList.add(new ModelBox(left, 0, 0, 1.0F, -34.0F, 4.0F, 15, 5, 6, 0.0F, false));
        left.cubeList.add(new ModelBox(left, 0, 0, 15.0F, -33.0F, -13.0F, 2, 5, 29, 0.0F, false));
    }

    public void render() {
        rope.render(1f);
        rope2.render(1f);
        rope3.render(1f);
        rope4.render(1f);
        pack.render(1f);
        top.render(1f);
        right.render(1f);
        left.render(1f);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
