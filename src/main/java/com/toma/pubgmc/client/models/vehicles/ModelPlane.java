package com.toma.pubgmc.client.models.vehicles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelPlane extends ModelBase
{
	private float rot;
	private final ModelRenderer base;
	private final ModelRenderer sh2;
	private final ModelRenderer wings;
	private final ModelRenderer sh0;
	private final ModelRenderer sh1;
	private final ModelRenderer sh3;
	private final ModelRenderer rotors;
	private final ModelRenderer rotor1;
	private final ModelRenderer rotor2;
	private final ModelRenderer rotor3;
	private final ModelRenderer rotor4;
	private final ModelRenderer seats;
	
	public void renderPlane()
	{
		base.render(1f);
		wings.render(1f);
		rotors.render(1f);
		seats.render(1f);
		updateRotation();
		setRotations();
	}
	
	private void updateRotation()
	{
		rot = rot + 0.03f;
		if(rot > 360) rot = 0;
	}
	
	private void setRotations()
	{
		setRotationAngle(rotor1, 0f, 0f, rot);
		setRotationAngle(rotor2, 0f, 0f, rot);
		setRotationAngle(rotor3, 0f, 0f, rot);
		setRotationAngle(rotor4, 0f, 0f, rot);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public ModelPlane() 
	{
		textureWidth = 256;
		textureHeight = 256;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -11.0F, -31.0F, -82.0F, 22, 3, 100, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -11.0F, -31.0F, 18.0F, 22, 8, 50, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 10.0F, -29.0F, -82.0F, 2, 6, 100, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -12.0F, -29.0F, -82.0F, 2, 6, 100, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 10.0F, -29.0F, 18.0F, 2, 6, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -12.0F, -29.0F, 18.0F, 2, 6, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 11.0F, -23.0F, -82.0F, 2, 6, 100, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -12.0F, -23.0F, 18.0F, 2, 6, 37, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -13.0F, -23.0F, -82.0F, 2, 6, 100, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -12.0F, -29.0F, 18.0F, 2, 17, 27, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -12.0F, -17.0F, -82.0F, 2, 5, 100, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 10.0F, -17.0F, 18.0F, 2, 5, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 10.0F, -17.0F, -82.0F, 2, 5, 100, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -10.0F, -13.0F, 18.0F, 20, 4, 23, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -10.0F, -13.0F, -82.0F, 6, 4, 100, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -10.0F, -30.0F, -89.0F, 20, 3, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -10.0F, -13.0F, -97.0F, 20, 4, 15, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -10.0F, -19.0F, -99.0F, 20, 6, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -10.0F, -28.0F, -96.0F, 20, 3, 7, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -10.0F, -26.0F, -99.0F, 20, 7, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -12.0F, -27.0F, -95.0F, 2, 15, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -12.0F, -29.0F, 23.0F, 2, 14, 29, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -13.0F, -23.0F, -96.0F, 2, 6, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 11.0F, -23.0F, -96.0F, 2, 6, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 10.0F, -27.0F, -95.0F, 2, 15, 13, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -12.0F, -29.0F, 33.0F, 2, 10, 29, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 10.0F, -29.0F, 23.0F, 2, 14, 29, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 10.0F, -29.0F, 33.0F, 2, 10, 29, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 10.0F, -29.0F, 18.0F, 2, 17, 27, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, 4.0F, -13.0F, -82.0F, 6, 4, 100, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -4.0F, -13.0F, 14.0F, 8, 4, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -4.0F, -13.0F, -82.0F, 8, 4, 4, 0.0F, false));

		sh2 = new ModelRenderer(this);
		sh2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(sh2, 0.4363F, 0.0F, 0.0F);
		base.addChild(sh2);
		sh2.cubeList.add(new ModelBox(sh2, 0, 0, -10.0F, 5.0F, 41.0F, 20, 4, 30, 0.0F, false));

		wings = new ModelRenderer(this);
		wings.setRotationPoint(0.0F, 24.0F, 0.0F);
		wings.cubeList.add(new ModelBox(wings, 0, 0, -75.0F, -27.0F, -38.0F, 65, 5, 19, 0.0F, false));
		wings.cubeList.add(new ModelBox(wings, 0, 0, 11.0F, -30.0F, 51.0F, 33, 5, 14, 0.0F, false));
		wings.cubeList.add(new ModelBox(wings, 0, 0, -44.0F, -30.0F, 51.0F, 33, 5, 14, 0.0F, false));
		wings.cubeList.add(new ModelBox(wings, 0, 0, -3.0F, -54.0F, 54.0F, 6, 27, 8, 0.0F, false));
		wings.cubeList.add(new ModelBox(wings, 0, 0, 11.0F, -27.0F, -38.0F, 65, 5, 19, 0.0F, false));

		sh0 = new ModelRenderer(this);
		sh0.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(sh0, 0.0F, 0.0873F, 0.0F);
		wings.addChild(sh0);
		sh0.cubeList.add(new ModelBox(sh0, 0, 0, -71.0F, -27.0F, -44.0F, 63, 5, 14, 0.0F, false));
		sh0.cubeList.add(new ModelBox(sh0, 0, 0, -47.0F, -30.0F, 47.0F, 42, 5, 14, 0.0F, false));

		sh1 = new ModelRenderer(this);
		sh1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(sh1, 0.0F, -0.0873F, 0.0F);
		wings.addChild(sh1);
		sh1.cubeList.add(new ModelBox(sh1, 0, 0, 8.0F, -27.0F, -44.0F, 64, 5, 14, 0.0F, false));
		sh1.cubeList.add(new ModelBox(sh1, 0, 0, 6.0F, -30.0F, 47.0F, 42, 5, 14, 0.0F, false));

		sh3 = new ModelRenderer(this);
		sh3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(sh3, -0.2618F, 0.0F, 0.0F);
		wings.addChild(sh3);
		sh3.cubeList.add(new ModelBox(sh3, 0, 0, -3.0F, -66.0F, 38.0F, 6, 27, 8, 0.0F, false));

		rotors = new ModelRenderer(this);
		rotors.setRotationPoint(0.0F, 24.0F, 0.0F);
		rotors.cubeList.add(new ModelBox(rotors, 0, 0, 30.0F, -22.0F, -49.0F, 7, 7, 20, 0.0F, false));
		rotors.cubeList.add(new ModelBox(rotors, 0, 0, 30.0F, -22.0F, -29.0F, 7, 5, 6, 0.0F, false));
		rotors.cubeList.add(new ModelBox(rotors, 0, 0, 56.0F, -22.0F, -45.0F, 7, 7, 16, 0.0F, false));
		rotors.cubeList.add(new ModelBox(rotors, 0, 0, 56.0F, -22.0F, -29.0F, 7, 5, 6, 0.0F, false));
		rotors.cubeList.add(new ModelBox(rotors, 0, 0, -60.0F, -22.0F, -29.0F, 7, 5, 6, 0.0F, false));
		rotors.cubeList.add(new ModelBox(rotors, 0, 0, -60.0F, -22.0F, -45.0F, 7, 7, 16, 0.0F, false));
		rotors.cubeList.add(new ModelBox(rotors, 0, 0, -34.0F, -22.0F, -49.0F, 7, 7, 20, 0.0F, false));
		rotors.cubeList.add(new ModelBox(rotors, 0, 0, -34.0F, -22.0F, -29.0F, 7, 5, 6, 0.0F, false));

		rotor1 = new ModelRenderer(this);
		rotor1.setRotationPoint(59.5F, -18.5F, -49.0F);
		rotors.addChild(rotor1);
		rotor1.cubeList.add(new ModelBox(rotor1, 124, 222, -2.5F, -2.5F, 1.0F, 5, 5, 3, 0.0F, false));
		rotor1.cubeList.add(new ModelBox(rotor1, 124, 222, -1.5F, -11.5F, 2.0F, 3, 9, 1, 0.0F, false));
		rotor1.cubeList.add(new ModelBox(rotor1, 124, 222, -1.5F, 2.5F, 2.0F, 3, 9, 1, 0.0F, false));
		rotor1.cubeList.add(new ModelBox(rotor1, 124, 222, 2.5F, -1.5F, 2.0F, 9, 3, 1, 0.0F, false));
		rotor1.cubeList.add(new ModelBox(rotor1, 124, 222, -11.5F, -1.5F, 2.0F, 9, 3, 1, 0.0F, false));
		rotor1.cubeList.add(new ModelBox(rotor1, 124, 222, -1.5F, -1.5F, 0.0F, 3, 3, 1, 0.0F, false));

		rotor2 = new ModelRenderer(this);
		rotor2.setRotationPoint(-56.5F, -18.5F, -49.0F);
		rotors.addChild(rotor2);
		rotor2.cubeList.add(new ModelBox(rotor2, 124, 222, -2.5F, -2.5F, 1.0F, 5, 5, 3, 0.0F, false));
		rotor2.cubeList.add(new ModelBox(rotor2, 124, 222, -1.5F, -11.5F, 2.0F, 3, 9, 1, 0.0F, false));
		rotor2.cubeList.add(new ModelBox(rotor2, 124, 222, -1.5F, 2.5F, 2.0F, 3, 9, 1, 0.0F, false));
		rotor2.cubeList.add(new ModelBox(rotor2, 124, 222, 2.5F, -1.5F, 2.0F, 9, 3, 1, 0.0F, false));
		rotor2.cubeList.add(new ModelBox(rotor2, 124, 222, -11.5F, -1.5F, 2.0F, 9, 3, 1, 0.0F, false));
		rotor2.cubeList.add(new ModelBox(rotor2, 124, 222, -1.5F, -1.5F, 0.0F, 3, 3, 1, 0.0F, false));

		rotor3 = new ModelRenderer(this);
		rotor3.setRotationPoint(33.5F, -18.5F, -53.0F);
		rotors.addChild(rotor3);
		rotor3.cubeList.add(new ModelBox(rotor3, 124, 222, -2.5F, -2.5F, 1.0F, 5, 5, 3, 0.0F, false));
		rotor3.cubeList.add(new ModelBox(rotor3, 124, 222, -1.5F, -11.5F, 2.0F, 3, 9, 1, 0.0F, false));
		rotor3.cubeList.add(new ModelBox(rotor3, 124, 222, -1.5F, 2.5F, 2.0F, 3, 9, 1, 0.0F, false));
		rotor3.cubeList.add(new ModelBox(rotor3, 124, 222, 2.5F, -1.5F, 2.0F, 9, 3, 1, 0.0F, false));
		rotor3.cubeList.add(new ModelBox(rotor3, 124, 222, -11.5F, -1.5F, 2.0F, 9, 3, 1, 0.0F, false));
		rotor3.cubeList.add(new ModelBox(rotor3, 124, 222, -1.5F, -1.5F, 0.0F, 3, 3, 1, 0.0F, false));

		rotor4 = new ModelRenderer(this);
		rotor4.setRotationPoint(-30.5F, -18.5F, -53.0F);
		rotors.addChild(rotor4);
		rotor4.cubeList.add(new ModelBox(rotor4, 124, 222, -2.5F, -2.5F, 1.0F, 5, 5, 3, 0.0F, false));
		rotor4.cubeList.add(new ModelBox(rotor4, 124, 222, -1.5F, -11.5F, 2.0F, 3, 9, 1, 0.0F, false));
		rotor4.cubeList.add(new ModelBox(rotor4, 124, 222, -1.5F, 2.5F, 2.0F, 3, 9, 1, 0.0F, false));
		rotor4.cubeList.add(new ModelBox(rotor4, 124, 222, 2.5F, -1.5F, 2.0F, 9, 3, 1, 0.0F, false));
		rotor4.cubeList.add(new ModelBox(rotor4, 124, 222, -11.5F, -1.5F, 2.0F, 9, 3, 1, 0.0F, false));
		rotor4.cubeList.add(new ModelBox(rotor4, 124, 222, -1.5F, -1.5F, 0.0F, 3, 3, 1, 0.0F, false));

		seats = new ModelRenderer(this);
		seats.setRotationPoint(0.0F, 24.0F, 0.0F);
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -80.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -80.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -74.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -74.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -68.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -68.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -62.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -62.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -56.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -56.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -50.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -50.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -44.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -44.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -38.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -38.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -32.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -32.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -26.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -26.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -20.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -20.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -14.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -14.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -8.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -8.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, -2.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, -2.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, 4.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, 4.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 8.0F, -18.0F, 10.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, 5.0F, -15.0F, 10.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, 10.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, 10.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, 4.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, 4.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -2.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -2.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -8.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -14.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -8.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -14.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -20.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -20.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -26.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -32.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -38.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -38.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -44.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -26.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -32.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -44.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -50.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -50.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -56.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -56.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -62.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -62.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -68.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -68.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -74.0F, 4, 1, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -74.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -18.0F, -80.0F, 1, 5, 4, 0.0F, false));
		seats.cubeList.add(new ModelBox(seats, 0, 223, -9.0F, -15.0F, -80.0F, 4, 1, 4, 0.0F, false));
	}
}
