package dev.toma.pubgmc.client.models.equipment;

import dev.toma.pubgmc.api.client.model.AbstractNightVisionModel;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;

public class NightVisionModel extends AbstractNightVisionModel {

    private final ModelRenderer nightVision;
    private final ModelRenderer mount;
    private final ModelRenderer device_mount;
    private final ModelRenderer device_mount_1;
    private final ModelRenderer device_mount_2;
    private final ModelRenderer b0;
    private final ModelRenderer b1;
    private final ModelRenderer b2;
    private final ModelRenderer b3;

    public NightVisionModel() {
        textureWidth = 128;
        textureHeight = 128;

        nightVision = new ModelRenderer(this);
        nightVision.setRotationPoint(0.0F, 25.0F, 0.0F);
        nightVision.cubeList.add(new ModelBox(nightVision, 73, 77, -4.5F, -31.0F, 3.5F, 9, 1, 1, 0.0F, false));
        nightVision.cubeList.add(new ModelBox(nightVision, 69, 70, 3.5F, -31.0F, -4.0F, 1, 1, 8, 0.0F, false));
        nightVision.cubeList.add(new ModelBox(nightVision, 84, 74, -4.5F, -31.0F, -4.0F, 1, 1, 8, 0.0F, false));
        nightVision.cubeList.add(new ModelBox(nightVision, 82, 81, -4.5F, -31.0F, -4.5F, 9, 1, 1, 0.0F, false));

        mount = new ModelRenderer(this);
        mount.setRotationPoint(0.5F, 0.0F, 0.0F);
        nightVision.addChild(mount);
        setRotationAngle(mount, -0.5236F, 0.0F, 0.0F);
        mount.cubeList.add(new ModelBox(mount, 77, 69, 0.5F, -24.7308F, -20.8971F, 2, 1, 2, 0.0F, false));

        device_mount = new ModelRenderer(this);
        device_mount.setRotationPoint(-0.5F, -24.2308F, -20.3971F);
        mount.addChild(device_mount);
        setRotationAngle(device_mount, 0.5236F, 0.0F, 0.0F);
        device_mount.cubeList.add(new ModelBox(device_mount, 77, 69, -1.0F, -0.5F, -2.5F, 2, 1, 3, 0.0F, false));

        device_mount_1 = new ModelRenderer(this);
        device_mount_1.setRotationPoint(0.0F, 0.0F, -4.0F);
        device_mount.addChild(device_mount_1);
        setRotationAngle(device_mount_1, 0.7854F, 0.0F, 0.0F);
        device_mount_1.cubeList.add(new ModelBox(device_mount_1, 77, 69, -1.0F, 0.7071F, -0.5858F, 2, 1, 2, 0.0F, false));

        device_mount_2 = new ModelRenderer(this);
        device_mount_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        device_mount_1.addChild(device_mount_2);
        setRotationAngle(device_mount_2, 0.7854F, 0.0F, 0.0F);
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 77, 69, -1.0F, 0.0858F, -2.9142F, 2, 1, 2, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 77, 69, -1.0F, -0.9142F, -2.9142F, 2, 3, 2, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 100, 116, 1.0F, -2.05F, -3.5F, 2, 1, 2, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 100, 116, -3.0F, -2.05F, -3.5F, 2, 1, 2, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 76, 77, 1.0F, -2.0F, -3.5F, 2, 7, 2, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 76, 77, 1.0F, -2.0F, -2.0F, 2, 3, 1, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 76, 77, 2.5F, -2.0F, -3.5F, 1, 3, 2, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 76, 77, 0.5F, -2.0F, -3.5F, 1, 3, 2, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 76, 77, 1.0F, -2.0F, -4.0F, 2, 3, 1, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 76, 77, -3.0F, -2.0F, -3.5F, 2, 7, 2, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 76, 77, -3.0F, -2.0F, -2.0F, 2, 3, 1, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 76, 77, -1.5F, -2.0F, -3.5F, 1, 3, 2, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 76, 77, -3.5F, -2.0F, -3.5F, 1, 3, 2, 0.0F, false));
        device_mount_2.cubeList.add(new ModelBox(device_mount_2, 76, 77, -3.0F, -2.0F, -4.0F, 2, 3, 1, 0.0F, false));

        b0 = new ModelRenderer(this);
        b0.setRotationPoint(2.0F, 2.0F, -1.5F);
        device_mount_2.addChild(b0);
        setRotationAngle(b0, -0.4363F, 0.0F, 0.0F);
        b0.cubeList.add(new ModelBox(b0, 74, 75, -1.0F, -1.1176F, -0.9695F, 2, 2, 1, 0.0F, false));
        b0.cubeList.add(new ModelBox(b0, 74, 75, -5.0F, -1.1176F, -0.9695F, 2, 2, 1, 0.0F, false));

        b1 = new ModelRenderer(this);
        b1.setRotationPoint(2.0F, 2.0F, -1.5F);
        device_mount_2.addChild(b1);
        setRotationAngle(b1, 0.4363F, 0.0F, 0.0F);
        b1.cubeList.add(new ModelBox(b1, 74, 75, -1.0F, -1.9629F, -1.8432F, 2, 2, 1, 0.0F, false));
        b1.cubeList.add(new ModelBox(b1, 74, 75, -5.0F, -1.9629F, -1.8432F, 2, 2, 1, 0.0F, false));

        b2 = new ModelRenderer(this);
        b2.setRotationPoint(2.0F, 2.0F, -1.5F);
        device_mount_2.addChild(b2);
        setRotationAngle(b2, 0.0F, 0.0F, 0.4363F);
        b2.cubeList.add(new ModelBox(b2, 74, 75, -0.0632F, -1.5402F, -2.0F, 1, 2, 2, 0.0F, false));
        b2.cubeList.add(new ModelBox(b2, 74, 75, -3.6884F, 0.1502F, -2.0F, 1, 2, 2, 0.0F, false));

        b3 = new ModelRenderer(this);
        b3.setRotationPoint(-2.0F, 2.0F, -1.5F);
        device_mount_2.addChild(b3);
        setRotationAngle(b3, 0.0F, 0.0F, -0.4363F);
        b3.cubeList.add(new ModelBox(b3, 74, 75, 2.6884F, 0.1502F, -2.0F, 1, 2, 2, 0.0F, false));
        b3.cubeList.add(new ModelBox(b3, 74, 75, -0.9368F, -1.5402F, -2.0F, 1, 2, 2, 0.0F, false));

        bipedHead.addChild(nightVision);
    }

    @Override
    public void setupRotations(EntityLivingBase entity, boolean isNightVisionActive) {
        float x = isNightVisionActive ? 30.0F : -70.0F;
        device_mount.rotateAngleX = (float) Math.toRadians(x);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
