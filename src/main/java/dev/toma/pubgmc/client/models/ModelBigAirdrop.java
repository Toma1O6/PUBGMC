package dev.toma.pubgmc.client.models;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelBigAirdrop extends ModelAirdrop {
    private final ModelRenderer drop;
    private final ModelRenderer chute;
    private final ModelRenderer strings;
    private final ModelRenderer a0;
    private final ModelRenderer a1;
    private final ModelRenderer a2;
    private final ModelRenderer a3;
    private final ModelRenderer a4;
    private final ModelRenderer a5;
    private final ModelRenderer a6;
    private final ModelRenderer a7;
    private final ModelRenderer base;
    private final ModelRenderer b0;
    private final ModelRenderer b1;
    private final ModelRenderer b2;
    private final ModelRenderer b3;
    private final ModelRenderer b4;
    private final ModelRenderer b5;
    private final ModelRenderer b6;
    private final ModelRenderer b7;
    private final ModelRenderer base2;

    public ModelBigAirdrop() {
        textureWidth = 128;
        textureHeight = 128;

        drop = new ModelRenderer(this);
        drop.setRotationPoint(0.0F, 24.0F, 0.0F);

        chute = new ModelRenderer(this);
        chute.setRotationPoint(0.0F, 0.0F, 0.0F);
        drop.addChild(chute);

        strings = new ModelRenderer(this);
        strings.setRotationPoint(0.0F, 0.0F, 0.0F);
        chute.addChild(strings);
        strings.cubeList.add(new ModelBox(strings, 0, 70, -1.0F, -13.2F, -1.0F, 2, 1, 2, 0.0F, false));

        a0 = new ModelRenderer(this);
        a0.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(a0, -0.3491F, 0.0F, 0.0F);
        strings.addChild(a0);
        a0.cubeList.add(new ModelBox(a0, 0, 70, -0.5F, -53.0F, -4.0F, 1, 41, 1, 0.0F, false));

        a1 = new ModelRenderer(this);
        a1.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(a1, -0.3491F, 0.0F, -0.3491F);
        strings.addChild(a1);
        a1.cubeList.add(new ModelBox(a1, 0, 70, 3.25F, -52.0F, -4.0F, 1, 40, 1, 0.0F, false));

        a2 = new ModelRenderer(this);
        a2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(a2, 0.0F, 0.0F, -0.3491F);
        strings.addChild(a2);
        a2.cubeList.add(new ModelBox(a2, 0, 70, 3.0F, -52.0F, -0.5F, 1, 40, 1, 0.0F, false));

        a3 = new ModelRenderer(this);
        a3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(a3, 0.3491F, 0.0F, -0.3491F);
        strings.addChild(a3);
        a3.cubeList.add(new ModelBox(a3, 0, 70, 3.0F, -51.0F, 3.0F, 1, 39, 1, 0.0F, false));

        a4 = new ModelRenderer(this);
        a4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(a4, 0.3491F, 0.0F, 0.0F);
        strings.addChild(a4);
        a4.cubeList.add(new ModelBox(a4, 0, 70, -0.5F, -53.0F, 3.0F, 1, 41, 1, 0.0F, false));

        a5 = new ModelRenderer(this);
        a5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(a5, 0.3491F, 0.0F, 0.3491F);
        strings.addChild(a5);
        a5.cubeList.add(new ModelBox(a5, 0, 70, -4.0F, -52.0F, 3.0F, 1, 40, 1, 0.0F, false));

        a6 = new ModelRenderer(this);
        a6.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(a6, 0.0F, 0.0F, 0.3491F);
        strings.addChild(a6);
        a6.cubeList.add(new ModelBox(a6, 0, 70, -4.0F, -53.0F, -0.5F, 1, 41, 1, 0.0F, false));

        a7 = new ModelRenderer(this);
        a7.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(a7, -0.3491F, 0.0F, 0.3491F);
        strings.addChild(a7);
        a7.cubeList.add(new ModelBox(a7, 0, 70, -4.0F, -52.0F, -4.0F, 1, 40, 1, 0.0F, false));

        base = new ModelRenderer(this);
        base.setRotationPoint(0.0F, 0.0F, 0.0F);
        chute.addChild(base);
        base.cubeList.add(new ModelBox(base, 0, 70, -10.0F, -54.0F, 3.0F, 20, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 0, 70, -10.0F, -54.0F, -10.0F, 20, 1, 7, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 0, 70, 3.0F, -54.0F, -3.0F, 7, 1, 6, 0.0F, false));
        base.cubeList.add(new ModelBox(base, 0, 70, -10.0F, -54.0F, -3.0F, 7, 1, 6, 0.0F, false));

        b0 = new ModelRenderer(this);
        b0.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(b0, 0.5236F, 0.0F, 0.0F);
        base.addChild(b0);
        b0.cubeList.add(new ModelBox(b0, 0, 70, -10.0F, -51.7F, -1.6F, 20, 1, 20, 0.0F, false));

        b1 = new ModelRenderer(this);
        b1.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(b1, -0.5236F, 0.0F, 0.0F);
        base.addChild(b1);
        b1.cubeList.add(new ModelBox(b1, 0, 70, -10.0F, -51.8F, -18.3F, 20, 1, 20, 0.0F, false));

        b2 = new ModelRenderer(this);
        b2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(b2, 0.0F, 0.0F, -0.5236F);
        base.addChild(b2);
        b2.cubeList.add(new ModelBox(b2, 0, 70, -1.7F, -51.7F, -10.0F, 20, 1, 20, 0.0F, false));

        b3 = new ModelRenderer(this);
        b3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(b3, 0.0F, 0.0F, 0.5236F);
        base.addChild(b3);
        b3.cubeList.add(new ModelBox(b3, 0, 70, -18.3F, -51.8F, -10.0F, 20, 1, 20, 0.0F, false));

        b4 = new ModelRenderer(this);
        b4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(b4, -0.5236F, -0.0873F, -0.5236F);
        base.addChild(b4);
        b4.cubeList.add(new ModelBox(b4, 0, 70, 0.0F, -49.1F, -18.6F, 19, 1, 19, 0.0F, false));

        b5 = new ModelRenderer(this);
        b5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(b5, 0.5236F, 0.1571F, -0.5236F);
        base.addChild(b5);
        b5.cubeList.add(new ModelBox(b5, 0, 70, 0.6F, -48.5F, 0.7F, 19, 1, 19, 0.0F, false));

        b6 = new ModelRenderer(this);
        b6.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(b6, 0.5236F, -0.1222F, 0.5236F);
        base.addChild(b6);
        b6.cubeList.add(new ModelBox(b6, 0, 70, -19.4F, -48.9F, 0.1F, 19, 1, 19, 0.0F, false));

        b7 = new ModelRenderer(this);
        b7.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(b7, -0.5236F, 0.1745F, 0.5236F);
        base.addChild(b7);
        b7.cubeList.add(new ModelBox(b7, 0, 70, -19.7F, -48.5F, -19.9F, 19, 1, 19, 0.0F, false));

        base2 = new ModelRenderer(this);
        base2.setRotationPoint(0.0F, 0.0F, 0.0F);
        drop.addChild(base2);
        base2.cubeList.add(new ModelBox(base2, 82, 70, 5.0F, 3.0F, -9.0F, 5, 1, 18, 0.0F, false));
        base2.cubeList.add(new ModelBox(base2, 82, 70, -15.0F, 3.0F, -9.0F, 5, 1, 18, 0.0F, false));
        base2.cubeList.add(new ModelBox(base2, 82, 70, -10.0F, 3.0F, -9.0F, 5, 1, 18, 0.0F, false));
        base2.cubeList.add(new ModelBox(base2, 82, 70, 0.0F, 3.0F, -9.0F, 5, 1, 18, 0.0F, false));
        base2.cubeList.add(new ModelBox(base2, 82, 70, -5.0F, 3.0F, -9.0F, 5, 1, 18, 0.0F, false));
        base2.cubeList.add(new ModelBox(base2, 82, 70, 10.0F, 3.0F, -9.0F, 5, 1, 18, 0.0F, false));
        base2.cubeList.add(new ModelBox(base2, 0, 0, -14.5F, -13.0F, -8.5F, 29, 16, 17, 0.0F, false));
    }

    @Override
    public void render() {
        drop.render(1f);
        ;
    }
}
