package dev.toma.pubgmc.client.models;

import dev.toma.pubgmc.common.entity.EntityFuelCan;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelFuelCan extends ModelBase {
    private final ModelRenderer main;
    private final ModelRenderer part0;
    private final ModelRenderer part1;
    private final ModelRenderer part2;
    private final ModelRenderer part3;
    private final ModelRenderer part4;
    private final ModelRenderer part5_1;
    private final ModelRenderer part5_2;
    private final ModelRenderer part5_3;

    public ModelFuelCan() {
        textureWidth = 64;
        textureHeight = 64;

        main = new ModelRenderer(this);
        main.setRotationPoint(0.0F, 0.0F, 0.0F);

        part0 = new ModelRenderer(this);
        part0.setRotationPoint(0.0F, 0.0F, 0.0F);
        main.addChild(part0);
        part0.cubeList.add(new ModelBox(part0, 0, 0, -3.0F, 0.0F, -6.0F, 6, 14, 12, 0.0F, false));

        part1 = new ModelRenderer(this);
        part1.setRotationPoint(0.0F, 0.0F, 0.0F);
        main.addChild(part1);
        part1.cubeList.add(new ModelBox(part1, 0, 0, -3.0F, 14.0F, -2.0F, 6, 4, 8, 0.0F, false));

        part2 = new ModelRenderer(this);
        part2.setRotationPoint(8.0F, 18.72F, -0.67F);
        setRotationAngle(part2, -0.7854F, 0.0F, 0.0F);
        main.addChild(part2);
        part2.cubeList.add(new ModelBox(part2, 0, 0, -11.0F, -3.67F, -7.2F, 6, 4, 6, 0.0F, false));

        part3 = new ModelRenderer(this);
        part3.setRotationPoint(8.0F, 18.72F, -0.67F);
        setRotationAngle(part3, -0.7854F, 0.0F, 0.0F);
        main.addChild(part3);
        part3.cubeList.add(new ModelBox(part3, 32, 32, -9.5F, -3.3F, -6.2F, 3, 4, 4, 0.0F, false));

        part4 = new ModelRenderer(this);
        part4.setRotationPoint(8.0F, 18.72F, -0.67F);
        setRotationAngle(part4, -0.7854F, 0.0F, 0.0F);
        main.addChild(part4);
        part4.cubeList.add(new ModelBox(part4, 0, 32, -9.0F, -2.3F, -5.2F, 2, 7, 2, 0.0F, false));

        part5_1 = new ModelRenderer(this);
        part5_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        main.addChild(part5_1);
        part5_1.cubeList.add(new ModelBox(part5_1, 48, 0, -2.5F, 18.0F, 4.0F, 3, 1, 1, 0.0F, false));

        part5_2 = new ModelRenderer(this);
        part5_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        main.addChild(part5_2);
        part5_2.cubeList.add(new ModelBox(part5_2, 48, 0, -2.5F, 18.0F, -1.0F, 3, 1, 1, 0.0F, false));

        part5_3 = new ModelRenderer(this);
        part5_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        main.addChild(part5_3);
        part5_3.cubeList.add(new ModelBox(part5_3, 48, 0, -2.5F, 18.5F, -1.0F, 3, 1, 6, 0.0F, false));
    }


    public void render() {
        main.render(EntityFuelCan.size /16F);
    }

    public void setRotationAngle(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}