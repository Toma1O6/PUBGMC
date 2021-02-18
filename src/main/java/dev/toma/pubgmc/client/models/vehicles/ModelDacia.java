package dev.toma.pubgmc.client.models.vehicles;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelDacia extends ModelVehicle {
    private final ModelRenderer dacia;
    private final ModelRenderer core;
    private final ModelRenderer sh;
    private final ModelRenderer sh6;
    private final ModelRenderer sh7;
    private final ModelRenderer sh12;
    private final ModelRenderer sh13;
    private final ModelRenderer details;
    private final ModelRenderer angle3;
    private final ModelRenderer angle4;
    private final ModelRenderer wheel_cover_0;
    private final ModelRenderer sh2;
    private final ModelRenderer sh3;
    private final ModelRenderer wheel_cover_2;
    private final ModelRenderer sh8;
    private final ModelRenderer sh9;
    private final ModelRenderer wheel_cover_3;
    private final ModelRenderer sh10;
    private final ModelRenderer sh11;
    private final ModelRenderer wheel_cover_1;
    private final ModelRenderer sh4;
    private final ModelRenderer sh5;
    private final ModelRenderer wheels;
    private final ModelRenderer wheel_fl;
    private final ModelRenderer angle;
    private final ModelRenderer angle2;
    private final ModelRenderer wheel_fl2;
    private final ModelRenderer angle5;
    private final ModelRenderer angle6;
    private final ModelRenderer wheel_fl3;
    private final ModelRenderer angle7;
    private final ModelRenderer angle8;
    private final ModelRenderer wheel_fr;
    private final ModelRenderer angle9;
    private final ModelRenderer angle10;
    private final ModelRenderer steeringWheel;
    private final ModelRenderer angle11;
    private final ModelRenderer angle12;

    public ModelDacia() {
        textureWidth = 128;
        textureHeight = 128;

        dacia = new ModelRenderer(this);
        dacia.setRotationPoint(0.0F, 24.0F, 0.0F);

        core = new ModelRenderer(this);
        core.setRotationPoint(0.0F, 0.0F, 0.0F);
        dacia.addChild(core);
        core.cubeList.add(new ModelBox(core, 0, 0, -16.0F, -15.5F, -9.85F, 32, 14, 2, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -16.0F, -10.6F, -35.7F, 32, 9, 1, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, 15.0F, -10.6F, -34.7F, 1, 9, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -16.0F, -10.6F, -34.7F, 1, 9, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -16.0F, -10.6F, -19.7F, 32, 2, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -16.0F, -7.5F, -11.85F, 32, 6, 2, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, 15.0F, -15.5F, -7.85F, 1, 14, 43, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -16.0F, -28.5F, 1.75F, 32, 1, 27, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -16.0F, -15.5F, -7.85F, 1, 14, 43, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, 15.0F, -27.5F, 14.75F, 1, 12, 2, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, 15.0F, -27.7F, 2.15F, 1, 1, 14, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -16.0F, -27.5F, 14.75F, 1, 12, 2, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -16.0F, -27.7F, 1.15F, 1, 1, 14, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, 15.0F, -27.7F, 16.15F, 1, 1, 12, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -16.0F, -13.5F, 47.15F, 32, 12, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -16.0F, -10.5F, 36.15F, 32, 3, 12, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -15.0F, -15.5F, 32.15F, 30, 14, 3, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -15.0F, -2.5F, -3.25F, 30, 1, 36, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -15.0F, -2.5F, -8.25F, 30, 1, 5, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 96, 96, -14.0F, -7.0F, 0.0F, 6, 1, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 96, 96, -8.0F, -7.0F, 0.0F, 6, 1, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 96, 96, 8.0F, -7.0F, 0.0F, 6, 1, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 96, 96, 2.0F, -7.0F, 0.0F, 6, 1, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 96, 96, 8.0F, -7.0F, 20.0F, 6, 1, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 96, 96, 2.0F, -7.0F, 20.0F, 6, 1, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 96, 96, -14.0F, -7.0F, 20.0F, 6, 1, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 96, 96, -8.0F, -7.0F, 20.0F, 6, 1, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 96, 96, -2.0F, -7.0F, 20.0F, 4, 1, 10, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -15.0F, -5.5F, 0.75F, 30, 3, 9, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -15.0F, -5.5F, 20.75F, 30, 3, 9, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -4.0F, -5.5F, 9.75F, 8, 3, 12, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 0, 0, -15.0F, -9.5F, -8.25F, 30, 7, 4, 0.0F, false));
        core.cubeList.add(new ModelBox(core, 32, 65, -15.0F, -15.5F, -8.75F, 30, 6, 1, 0.0F, false));

        sh = new ModelRenderer(this);
        sh.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh, 0.1745F, 0.0F, 0.0F);
        core.addChild(sh);
        sh.cubeList.add(new ModelBox(sh, 0, 0, -16.0F, -17.0F, -33.0F, 32, 5, 26, 0.0F, false));

        sh6 = new ModelRenderer(this);
        sh6.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh6, -0.6981F, 0.0F, 0.0F);
        core.addChild(sh6);
        sh6.cubeList.add(new ModelBox(sh6, 0, 0, 14.0F, -23.0F, -17.0F, 2, 17, 1, 0.0F, false));
        sh6.cubeList.add(new ModelBox(sh6, 0, 0, -16.0F, -23.0F, -17.0F, 2, 17, 1, 0.0F, false));
        sh6.cubeList.add(new ModelBox(sh6, 0, 0, -14.0F, -7.0F, -17.0F, 28, 1, 1, 0.0F, false));
        sh6.cubeList.add(new ModelBox(sh6, 0, 0, -14.0F, -23.0F, -17.0F, 28, 2, 1, 0.0F, false));
        sh6.cubeList.add(new ModelBox(sh6, 0, 0, 15.0F, -23.0F, -16.0F, 1, 17, 1, 0.0F, false));
        sh6.cubeList.add(new ModelBox(sh6, 0, 0, -16.0F, -23.0F, -16.0F, 1, 17, 1, 0.0F, false));

        sh7 = new ModelRenderer(this);
        sh7.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh7, 0.5236F, 0.0F, 0.0F);
        core.addChild(sh7);
        sh7.cubeList.add(new ModelBox(sh7, 0, 0, 15.0F, -10.3F, 37.1F, 1, 16, 2, 0.0F, false));
        sh7.cubeList.add(new ModelBox(sh7, 0, 0, -16.0F, -10.3F, 37.1F, 1, 16, 2, 0.0F, false));
        sh7.cubeList.add(new ModelBox(sh7, 0, 0, -15.0F, 3.7F, 37.1F, 30, 2, 2, 0.0F, false));
        sh7.cubeList.add(new ModelBox(sh7, 0, 0, -15.0F, -10.3F, 38.1F, 1, 15, 1, 0.0F, false));
        sh7.cubeList.add(new ModelBox(sh7, 0, 0, -15.0F, -10.3F, 37.1F, 30, 1, 2, 0.0F, false));
        sh7.cubeList.add(new ModelBox(sh7, 0, 0, 14.0F, -10.3F, 38.1F, 1, 15, 1, 0.0F, false));

        sh12 = new ModelRenderer(this);
        sh12.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh12, -0.0873F, 0.0F, 0.0F);
        core.addChild(sh12);
        sh12.cubeList.add(new ModelBox(sh12, 0, 0, -16.0F, -18.4F, 33.5F, 32, 6, 22, 0.0F, false));

        sh13 = new ModelRenderer(this);
        sh13.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh13, -0.0873F, 0.0F, 0.0F);
        core.addChild(sh13);
        sh13.cubeList.add(new ModelBox(sh13, 96, 96, 2.0F, -20.0F, 9.0F, 12, 13, 1, 0.0F, false));
        sh13.cubeList.add(new ModelBox(sh13, 96, 96, -14.0F, -20.0F, 9.0F, 12, 13, 1, 0.0F, false));
        sh13.cubeList.add(new ModelBox(sh13, 96, 96, -14.0F, -22.0F, 29.0F, 15, 13, 1, 0.0F, false));
        sh13.cubeList.add(new ModelBox(sh13, 96, 96, 1.0F, -22.0F, 29.0F, 13, 13, 1, 0.0F, false));

        details = new ModelRenderer(this);
        details.setRotationPoint(0.0F, 0.0F, 0.0F);
        dacia.addChild(details);
        details.cubeList.add(new ModelBox(details, 32, 96, -15.0F, -9.6F, -36.0F, 15, 1, 1, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 32, 96, 0.0F, -9.6F, -36.0F, 15, 1, 1, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 32, 96, 0.0F, -4.6F, -36.0F, 15, 1, 1, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 32, 96, -15.0F, -4.6F, -36.0F, 15, 1, 1, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 32, 96, -15.0F, -8.6F, -36.0F, 1, 4, 1, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 32, 96, 14.0F, -8.6F, -36.0F, 1, 4, 1, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 64, 97, 7.0F, -8.1F, -36.0F, 6, 3, 1, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 64, 97, -13.0F, -8.1F, -36.0F, 6, 3, 1, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 0, 124, 3.0F, -2.6F, -37.0F, 14, 2, 2, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 0, 124, -17.0F, -2.6F, -37.0F, 14, 2, 2, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 0, 124, -3.0F, -2.6F, -37.0F, 6, 2, 2, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 0, 124, 15.0F, -2.6F, -35.0F, 2, 2, 2, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 0, 124, -17.0F, -2.6F, -35.0F, 2, 2, 2, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 0, 124, 3.0F, -2.6F, 56.0F, 14, 2, 2, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 0, 121, 15.0F, -2.6F, 51.0F, 2, 2, 5, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 0, 121, -17.0F, -2.6F, 51.0F, 2, 2, 5, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 0, 124, -17.0F, -2.6F, 56.0F, 14, 2, 2, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 0, 124, -3.0F, -2.6F, 56.0F, 6, 2, 2, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 0, 96, 9.0F, -1.6F, 52.0F, 2, 2, 7, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 32, 64, 9.0F, -10.1F, 57.0F, 5, 5, 1, 0.0F, false));
        details.cubeList.add(new ModelBox(details, 32, 64, -14.0F, -10.1F, 57.0F, 5, 5, 1, 0.0F, false));

        angle3 = new ModelRenderer(this);
        angle3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(angle3, 0.7854F, 0.0F, 0.0F);
        details.addChild(angle3);
        angle3.cubeList.add(new ModelBox(angle3, 0, 0, -16.0F, -23.75F, -13.27F, 32, 3, 4, 0.0F, false));
        angle3.cubeList.add(new ModelBox(angle3, 0, 0, -16.0F, 17.9693F, 28.4493F, 32, 3, 4, 0.0F, false));

        angle4 = new ModelRenderer(this);
        angle4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(angle4, -0.7854F, 0.0F, 0.0F);
        details.addChild(angle4);
        angle4.cubeList.add(new ModelBox(angle4, 0, 0, -16.0F, 0.5929F, -16.0929F, 32, 4, 4, 0.0F, false));
        angle4.cubeList.add(new ModelBox(angle4, 0, 0, -16.0F, -39.1264F, 25.6264F, 32, 2, 4, 0.0F, false));

        wheel_cover_0 = new ModelRenderer(this);
        wheel_cover_0.setRotationPoint(0.0F, 0.0F, 0.0F);
        dacia.addChild(wheel_cover_0);
        wheel_cover_0.cubeList.add(new ModelBox(wheel_cover_0, 0, 97, 16.0F, -6.0F, -24.75F, 1, 5, 1, 0.0F, false));
        wheel_cover_0.cubeList.add(new ModelBox(wheel_cover_0, 0, 97, 16.0F, -6.0F, -12.1F, 1, 5, 1, 0.0F, false));
        wheel_cover_0.cubeList.add(new ModelBox(wheel_cover_0, 0, 97, 16.0F, -8.83F, -21.9F, 1, 1, 8, 0.0F, false));

        sh2 = new ModelRenderer(this);
        sh2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh2, -0.7854F, 0.0F, 0.0F);
        wheel_cover_0.addChild(sh2);
        sh2.cubeList.add(new ModelBox(sh2, 0, 96, 16.0F, 3.5929F, -16.0929F, 1, 1, 4, 0.0F, false));

        sh3 = new ModelRenderer(this);
        sh3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh3, 0.7854F, 0.0F, 0.0F);
        wheel_cover_0.addChild(sh3);
        sh3.cubeList.add(new ModelBox(sh3, 0, 96, 16.0F, -21.75F, -13.27F, 1, 1, 4, 0.0F, false));

        wheel_cover_2 = new ModelRenderer(this);
        wheel_cover_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        dacia.addChild(wheel_cover_2);
        wheel_cover_2.cubeList.add(new ModelBox(wheel_cover_2, 0, 97, 16.0F, -6.0F, 34.25F, 1, 5, 1, 0.0F, false));
        wheel_cover_2.cubeList.add(new ModelBox(wheel_cover_2, 0, 97, 16.0F, -6.0F, 46.9F, 1, 5, 1, 0.0F, false));
        wheel_cover_2.cubeList.add(new ModelBox(wheel_cover_2, 0, 97, 16.0F, -8.83F, 37.1F, 1, 1, 8, 0.0F, false));

        sh8 = new ModelRenderer(this);
        sh8.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh8, -0.7854F, 0.0F, 0.0F);
        wheel_cover_2.addChild(sh8);
        sh8.cubeList.add(new ModelBox(sh8, 0, 96, 16.0F, -38.1264F, 25.6264F, 1, 1, 4, 0.0F, false));

        sh9 = new ModelRenderer(this);
        sh9.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh9, 0.7854F, 0.0F, 0.0F);
        wheel_cover_2.addChild(sh9);
        sh9.cubeList.add(new ModelBox(sh9, 0, 96, 16.0F, 19.9693F, 28.4493F, 1, 1, 4, 0.0F, false));

        wheel_cover_3 = new ModelRenderer(this);
        wheel_cover_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        dacia.addChild(wheel_cover_3);
        wheel_cover_3.cubeList.add(new ModelBox(wheel_cover_3, 0, 97, -17.0F, -6.0F, 34.25F, 1, 5, 1, 0.0F, false));
        wheel_cover_3.cubeList.add(new ModelBox(wheel_cover_3, 0, 97, -17.0F, -6.0F, 46.9F, 1, 5, 1, 0.0F, false));
        wheel_cover_3.cubeList.add(new ModelBox(wheel_cover_3, 0, 97, -17.0F, -8.83F, 37.1F, 1, 1, 8, 0.0F, false));

        sh10 = new ModelRenderer(this);
        sh10.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh10, -0.7854F, 0.0F, 0.0F);
        wheel_cover_3.addChild(sh10);
        sh10.cubeList.add(new ModelBox(sh10, 0, 96, -17.0F, -38.1264F, 25.6264F, 1, 1, 4, 0.0F, false));

        sh11 = new ModelRenderer(this);
        sh11.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh11, 0.7854F, 0.0F, 0.0F);
        wheel_cover_3.addChild(sh11);
        sh11.cubeList.add(new ModelBox(sh11, 0, 96, -17.0F, 19.9693F, 28.4493F, 1, 1, 4, 0.0F, false));

        wheel_cover_1 = new ModelRenderer(this);
        wheel_cover_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        dacia.addChild(wheel_cover_1);
        wheel_cover_1.cubeList.add(new ModelBox(wheel_cover_1, 0, 97, -17.0F, -6.0F, -24.75F, 1, 5, 1, 0.0F, false));
        wheel_cover_1.cubeList.add(new ModelBox(wheel_cover_1, 0, 97, -17.0F, -6.0F, -12.1F, 1, 5, 1, 0.0F, false));
        wheel_cover_1.cubeList.add(new ModelBox(wheel_cover_1, 0, 97, -17.0F, -8.83F, -21.9F, 1, 1, 8, 0.0F, false));

        sh4 = new ModelRenderer(this);
        sh4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh4, -0.7854F, 0.0F, 0.0F);
        wheel_cover_1.addChild(sh4);
        sh4.cubeList.add(new ModelBox(sh4, 0, 96, -17.0F, 3.5929F, -16.0929F, 1, 1, 4, 0.0F, false));

        sh5 = new ModelRenderer(this);
        sh5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sh5, 0.7854F, 0.0F, 0.0F);
        wheel_cover_1.addChild(sh5);
        sh5.cubeList.add(new ModelBox(sh5, 0, 96, -17.0F, -21.75F, -13.27F, 1, 1, 4, 0.0F, false));

        wheels = new ModelRenderer(this);
        wheels.setRotationPoint(0.0F, 0.0F, 0.0F);
        dacia.addChild(wheels);

        wheel_fl = new ModelRenderer(this);
        wheel_fl.setRotationPoint(14.5F, -1.5F, -18.0F);
        wheels.addChild(wheel_fl);
        wheel_fl.cubeList.add(new ModelBox(wheel_fl, 0, 64, -1.5F, -5.1F, -3.0F, 3, 2, 6, 0.0F, false));
        wheel_fl.cubeList.add(new ModelBox(wheel_fl, 0, 64, -1.5F, 3.1F, -2.9F, 3, 2, 6, 0.0F, false));
        wheel_fl.cubeList.add(new ModelBox(wheel_fl, 0, 64, -1.5F, -3.0F, 3.25F, 3, 6, 2, 0.0F, false));
        wheel_fl.cubeList.add(new ModelBox(wheel_fl, 0, 64, -1.5F, -3.0F, -5.0F, 3, 6, 2, 0.0F, false));
        wheel_fl.cubeList.add(new ModelBox(wheel_fl, 0, 96, -1.0F, -3.5F, -4.0F, 2, 7, 8, 0.0F, false));

        angle = new ModelRenderer(this);
        angle.setRotationPoint(-14.5F, 1.5F, 18.0F);
        setRotationAngle(angle, 0.7854F, 0.0F, 0.0F);
        wheel_fl.addChild(angle);
        angle.cubeList.add(new ModelBox(angle, 0, 64, 13.0F, -19.495F, -13.105F, 3, 2, 3, 0.0F, false));
        angle.cubeList.add(new ModelBox(angle, 0, 64, 13.0F, -9.9979F, -13.0607F, 3, 2, 3, 0.0F, false));

        angle2 = new ModelRenderer(this);
        angle2.setRotationPoint(-14.5F, 1.5F, 18.0F);
        setRotationAngle(angle2, -0.7854F, 0.0F, 0.0F);
        wheel_fl.addChild(angle2);
        angle2.cubeList.add(new ModelBox(angle2, 0, 64, 13.0F, 15.3607F, -15.1979F, 3, 2, 3, 0.0F, false));
        angle2.cubeList.add(new ModelBox(angle2, 0, 64, 13.0F, 5.855F, -15.245F, 3, 2, 3, 0.0F, false));

        wheel_fl2 = new ModelRenderer(this);
        wheel_fl2.setRotationPoint(14.5F, -1.5F, 41.0F);
        wheels.addChild(wheel_fl2);
        wheel_fl2.cubeList.add(new ModelBox(wheel_fl2, 0, 64, -1.5F, -5.1F, -3.0F, 3, 2, 6, 0.0F, false));
        wheel_fl2.cubeList.add(new ModelBox(wheel_fl2, 0, 64, -1.5F, 3.1F, -2.9F, 3, 2, 6, 0.0F, false));
        wheel_fl2.cubeList.add(new ModelBox(wheel_fl2, 0, 64, -1.5F, -3.0F, 3.25F, 3, 6, 2, 0.0F, false));
        wheel_fl2.cubeList.add(new ModelBox(wheel_fl2, 0, 64, -1.5F, -3.0F, -5.0F, 3, 6, 2, 0.0F, false));
        wheel_fl2.cubeList.add(new ModelBox(wheel_fl2, 0, 96, -1.0F, -3.5F, -4.0F, 2, 7, 8, 0.0F, false));

        angle5 = new ModelRenderer(this);
        angle5.setRotationPoint(-14.5F, 1.5F, -41.0F);
        setRotationAngle(angle5, 0.7854F, 0.0F, 0.0F);
        wheel_fl2.addChild(angle5);
        angle5.cubeList.add(new ModelBox(angle5, 0, 64, 13.0F, 22.2243F, 28.6143F, 3, 2, 3, 0.0F, false));
        angle5.cubeList.add(new ModelBox(angle5, 0, 64, 13.0F, 31.7214F, 28.6586F, 3, 2, 3, 0.0F, false));

        angle6 = new ModelRenderer(this);
        angle6.setRotationPoint(-14.5F, 1.5F, -41.0F);
        setRotationAngle(angle6, -0.7854F, 0.0F, 0.0F);
        wheel_fl2.addChild(angle6);
        angle6.cubeList.add(new ModelBox(angle6, 0, 64, 13.0F, -26.3586F, 26.5214F, 3, 2, 3, 0.0F, false));
        angle6.cubeList.add(new ModelBox(angle6, 0, 64, 13.0F, -35.8643F, 26.4743F, 3, 2, 3, 0.0F, false));

        wheel_fl3 = new ModelRenderer(this);
        wheel_fl3.setRotationPoint(-14.5F, -1.5F, 41.0F);
        wheels.addChild(wheel_fl3);
        wheel_fl3.cubeList.add(new ModelBox(wheel_fl3, 0, 64, -1.5F, -5.1F, -3.0F, 3, 2, 6, 0.0F, false));
        wheel_fl3.cubeList.add(new ModelBox(wheel_fl3, 0, 64, -1.5F, 3.1F, -2.9F, 3, 2, 6, 0.0F, false));
        wheel_fl3.cubeList.add(new ModelBox(wheel_fl3, 0, 64, -1.5F, -3.0F, 3.25F, 3, 6, 2, 0.0F, false));
        wheel_fl3.cubeList.add(new ModelBox(wheel_fl3, 0, 64, -1.5F, -3.0F, -5.0F, 3, 6, 2, 0.0F, false));
        wheel_fl3.cubeList.add(new ModelBox(wheel_fl3, 0, 96, -1.0F, -3.5F, -4.0F, 2, 7, 8, 0.0F, false));

        angle7 = new ModelRenderer(this);
        angle7.setRotationPoint(14.5F, 1.5F, -41.0F);
        setRotationAngle(angle7, 0.7854F, 0.0F, 0.0F);
        wheel_fl3.addChild(angle7);
        angle7.cubeList.add(new ModelBox(angle7, 0, 64, -16.0F, 22.2243F, 28.6143F, 3, 2, 3, 0.0F, false));
        angle7.cubeList.add(new ModelBox(angle7, 0, 64, -16.0F, 31.7214F, 28.6586F, 3, 2, 3, 0.0F, false));

        angle8 = new ModelRenderer(this);
        angle8.setRotationPoint(14.5F, 1.5F, -41.0F);
        setRotationAngle(angle8, -0.7854F, 0.0F, 0.0F);
        wheel_fl3.addChild(angle8);
        angle8.cubeList.add(new ModelBox(angle8, 0, 64, -16.0F, -26.3586F, 26.5214F, 3, 2, 3, 0.0F, false));
        angle8.cubeList.add(new ModelBox(angle8, 0, 64, -16.0F, -35.8643F, 26.4743F, 3, 2, 3, 0.0F, false));

        wheel_fr = new ModelRenderer(this);
        wheel_fr.setRotationPoint(-14.5F, -1.5F, -18.0F);
        wheels.addChild(wheel_fr);
        wheel_fr.cubeList.add(new ModelBox(wheel_fr, 0, 64, -1.5F, -5.1F, -3.0F, 3, 2, 6, 0.0F, false));
        wheel_fr.cubeList.add(new ModelBox(wheel_fr, 0, 64, -1.5F, 3.1F, -2.9F, 3, 2, 6, 0.0F, false));
        wheel_fr.cubeList.add(new ModelBox(wheel_fr, 0, 64, -1.5F, -3.0F, 3.25F, 3, 6, 2, 0.0F, false));
        wheel_fr.cubeList.add(new ModelBox(wheel_fr, 0, 64, -1.5F, -3.0F, -5.0F, 3, 6, 2, 0.0F, false));
        wheel_fr.cubeList.add(new ModelBox(wheel_fr, 0, 96, -1.0F, -3.5F, -4.0F, 2, 7, 8, 0.0F, false));

        angle9 = new ModelRenderer(this);
        angle9.setRotationPoint(14.5F, 1.5F, 18.0F);
        setRotationAngle(angle9, 0.7854F, 0.0F, 0.0F);
        wheel_fr.addChild(angle9);
        angle9.cubeList.add(new ModelBox(angle9, 0, 64, -16.0F, -19.495F, -13.105F, 3, 2, 3, 0.0F, false));
        angle9.cubeList.add(new ModelBox(angle9, 0, 64, -16.0F, -9.9979F, -13.0607F, 3, 2, 3, 0.0F, false));

        angle10 = new ModelRenderer(this);
        angle10.setRotationPoint(14.5F, 1.5F, 18.0F);
        setRotationAngle(angle10, -0.7854F, 0.0F, 0.0F);
        wheel_fr.addChild(angle10);
        angle10.cubeList.add(new ModelBox(angle10, 0, 64, -16.0F, 15.3607F, -15.1979F, 3, 2, 3, 0.0F, false));
        angle10.cubeList.add(new ModelBox(angle10, 0, 64, -16.0F, 5.855F, -15.245F, 3, 2, 3, 0.0F, false));

        steeringWheel = new ModelRenderer(this);
        steeringWheel.setRotationPoint(8.0F, -13.0F, -4.0F);
        dacia.addChild(steeringWheel);
        steeringWheel.cubeList.add(new ModelBox(steeringWheel, 0, 64, -3.0F, -6.0028F, 0.5374F, 6, 1, 1, 0.0F, false));
        steeringWheel.cubeList.add(new ModelBox(steeringWheel, 0, 64, 5.6F, -2.4028F, 0.5374F, 1, 5, 1, 0.0F, false));
        steeringWheel.cubeList.add(new ModelBox(steeringWheel, 0, 64, -6.5F, -2.4028F, 0.5374F, 1, 5, 1, 0.0F, false));
        steeringWheel.cubeList.add(new ModelBox(steeringWheel, 0, 64, -2.9F, 5.1472F, 0.5374F, 6, 1, 1, 0.0F, false));
        steeringWheel.cubeList.add(new ModelBox(steeringWheel, 0, 64, -3.0F, -2.5028F, 0.5374F, 6, 5, 1, 0.0F, false));
        steeringWheel.cubeList.add(new ModelBox(steeringWheel, 0, 64, -0.5F, 1.5972F, 0.5374F, 1, 4, 1, 0.0F, false));
        steeringWheel.cubeList.add(new ModelBox(steeringWheel, 0, 64, -0.75F, -0.5028F, -4.4626F, 2, 2, 5, 0.0F, false));

        angle11 = new ModelRenderer(this);
        angle11.setRotationPoint(-8.0F, 13.0F, 4.0F);
        setRotationAngle(angle11, 0.0F, 0.0F, -0.7854F);
        steeringWheel.addChild(angle11);
        angle11.cubeList.add(new ModelBox(angle11, 0, 64, 11.9738F, -9.8934F, -3.4626F, 5, 1, 1, 0.0F, false));
        angle11.cubeList.add(new ModelBox(angle11, 0, 64, 12.6738F, 1.9566F, -3.4626F, 5, 1, 1, 0.0F, false));
        angle11.cubeList.add(new ModelBox(angle11, 0, 64, 17.9238F, -3.5434F, -3.4626F, 3, 1, 1, 0.0F, false));

        angle12 = new ModelRenderer(this);
        angle12.setRotationPoint(-8.0F, 13.0F, 4.0F);
        setRotationAngle(angle12, 0.0F, 0.0F, 0.7854F);
        steeringWheel.addChild(angle12);
        angle12.cubeList.add(new ModelBox(angle12, 0, 64, -5.5934F, -21.2238F, -3.4626F, 5, 1, 1, 0.0F, false));
        angle12.cubeList.add(new ModelBox(angle12, 0, 64, -6.2434F, -9.4238F, -3.4626F, 5, 1, 1, 0.0F, false));
        angle12.cubeList.add(new ModelBox(angle12, 0, 64, -9.5934F, -14.9738F, -3.4626F, 3, 1, 1, 0.0F, false));
    }

    @Override
    public void render(float turnModifier) {
        dacia.render(0.625f);
        setupRotatingParts(steeringWheel, wheel_fl, wheel_fr, turnModifier);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
