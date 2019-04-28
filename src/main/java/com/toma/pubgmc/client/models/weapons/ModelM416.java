package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.util.ModelDebugger;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelM416 extends ModelGun
{
	private final ModelRenderer r30;
	private final ModelRenderer r60;
	private final ModelRenderer stock;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer mainPart;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer barrel;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer handguard;
	private final ModelRenderer axis1;
	private final ModelRenderer axis2;
	private final ModelRenderer magazine;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer sights;
	private final ModelRenderer bone22;
	private final ModelRenderer bone23;
	private final ModelRenderer bone24;
	
	/**
	 * @author OfficialMajonaise
	 */
	public ModelM416()
	{
		super();
		
		textureWidth = 128;
		textureHeight = 128;

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, -1.3F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -1.0F, -9.0F, 15.5F, 2, 2, 4, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 39, 44, -1.0F, -9.5F, 14.85F, 2, 3, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 39, 44, -1.5F, -9.0F, 14.85F, 3, 2, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 39, 44, 0.25F, -9.25F, 14.85F, 1, 1, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 39, 44, 0.25F, -7.75F, 14.85F, 1, 1, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 39, 44, -1.25F, -7.75F, 14.85F, 1, 1, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 39, 44, -1.25F, -9.25F, 14.85F, 1, 1, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 41, -1.5F, -9.5F, 16.5F, 3, 3, 12, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 41, -1.5F, -6.5F, 25.5F, 3, 4, 3, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 41, -0.3192F, -10.0736F, 16.5F, 1, 1, 12, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 41, -0.6808F, -10.0736F, 16.5F, 1, 1, 12, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 0, 41, -1.0F, -6.1F, 18.2F, 2, 1, 2, 0.0F, false));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone18, 1.309F, 0.0F, 0.0F);
		stock.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 0, 41, -1.5F, 18.9841F, 9.0147F, 3, 5, 4, 0.0F, false));
		bone18.cubeList.add(new ModelBox(bone18, 0, 41, -1.5F, 14.2555F, 10.549F, 3, 5, 2, 0.0F, false));

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone19, -0.6109F, 0.0F, 0.0F);
		bone18.addChild(bone19);
		bone19.cubeList.add(new ModelBox(bone19, 0, 41, -1.5F, 7.3802F, 18.2732F, 3, 3, 2, 0.0F, false));

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone20, 0.0F, 0.0F, -0.9599F);
		stock.addChild(bone20);
		bone20.cubeList.add(new ModelBox(bone20, 0, 41, 7.6423F, -5.2202F, 16.5F, 1, 1, 12, 0.0F, false));

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone21, 0.0F, 0.0F, 0.9599F);
		stock.addChild(bone21);
		bone21.cubeList.add(new ModelBox(bone21, 0, 41, -8.6423F, -5.2202F, 16.5F, 1, 1, 12, 0.0F, false));

		mainPart = new ModelRenderer(this);
		mainPart.setRotationPoint(0.0F, 24.0F, 2.0F);
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -1.5F, -10.05F, -1.5F, 3, 3, 10, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -2.0F, -8.55F, -1.5F, 4, 1, 10, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -1.0F, -10.466F, -1.5F, 2, 1, 12, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, 1.0F, -9.55F, 1.5F, 1, 1, 1, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -1.5F, -7.05F, -0.5F, 3, 3, 9, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -1.5F, -4.05F, -0.5F, 3, 2, 6, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -1.5F, -3.55F, 4.6F, 3, 1, 1, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -1.5F, -3.3F, 4.7F, 3, 1, 1, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -1.5F, -3.05F, 4.8F, 3, 1, 1, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -1.5F, -4.05F, 8.8F, 3, 1, 3, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, 0.6F, -5.25F, 3.8F, 1, 1, 2, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -1.5F, -3.75F, 11.2F, 3, 1, 1, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -1.0F, -3.35F, 8.8F, 2, 1, 1, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -0.4848F, -10.2236F, 11.5F, 1, 1, 0, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -0.5152F, -10.2236F, 11.5F, 1, 1, 0, 0.0F, true));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -1.5F, -7.05F, 8.5F, 3, 3, 4, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -2.0F, -8.55F, 8.5F, 4, 1, 4, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -1.5F, -10.05F, 8.5F, 3, 3, 4, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -0.4848F, -10.2236F, 10.5F, 1, 1, 2, 0.0F, false));
		mainPart.cubeList.add(new ModelBox(mainPart, 2, 43, -0.5152F, -10.2236F, 10.5F, 1, 1, 2, 0.0F, true));
		
		r30 = new ModelRenderer(this);
		r30.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(r30, 0.0F, 0.0F, -0.5236F);
		mainPart.addChild(r30);
		r30.cubeList.add(new ModelBox(r30, 2, 43, 5.0071F, -7.4045F, -1.5F, 1, 1, 10, 0.0F, false));
		r30.cubeList.add(new ModelBox(r30, 2, 43, 2.0429F, -7.5385F, -1.5F, 1, 1, 10, 0.0F, false));
		r30.cubeList.add(new ModelBox(r30, 2, 43, 2.0429F, -7.5385F, 8.5F, 1, 1, 4, 0.0F, false));
		r30.cubeList.add(new ModelBox(r30, 2, 43, 5.0071F, -7.4045F, 8.5F, 1, 1, 4, 0.0F, false));

		r60 = new ModelRenderer(this);
		r60.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(r60, 0.0F, 0.0F, -1.0472F);
		mainPart.addChild(r60);
		r60.cubeList.add(new ModelBox(r60, 2, 43, 6.4045F, -6.0071F, -1.5F, 1, 1, 10, 0.0F, false));
		r60.cubeList.add(new ModelBox(r60, 2, 43, 6.5385F, -3.0429F, -1.5F, 1, 1, 10, 0.0F, false));
		r60.cubeList.add(new ModelBox(r60, 2, 43, 6.4045F, -6.0071F, 8.5F, 1, 1, 4, 0.0F, false));
		r60.cubeList.add(new ModelBox(r60, 2, 43, 6.5385F, -3.0429F, 8.5F, 1, 1, 4, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(2.0F, 0.0F, 0.0F);
		setRotationAngle(bone4, 0.0F, -0.5236F, 0.0F);
		mainPart.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 2, 43, 0.25F, -9.55F, 2.1651F, 1, 1, 1, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(2.0F, 0.0F, 0.0F);
		setRotationAngle(bone5, 0.0F, 0.5236F, 0.0F);
		mainPart.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 2, 43, -1.75F, -9.55F, 0.299F, 1, 1, 1, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.1745F);
		mainPart.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 2, 43, -1.268F, -10.1578F, -1.5F, 1, 1, 10, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 2, 43, -1.268F, -10.1578F, 8.5F, 1, 1, 4, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone7, 0.0F, 0.0F, -0.1745F);
		mainPart.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 2, 43, 0.268F, -10.1578F, -1.5F, 1, 1, 10, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 2, 43, 0.268F, -10.1578F, 8.5F, 1, 1, 4, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone13, 0.4363F, 0.0F, 0.0F);
		mainPart.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 2, 43, -1.5F, 0.9548F, 9.2645F, 3, 6, 3, 0.0F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, 0.0F, 2.0F);
		setRotationAngle(bone14, 0.2618F, 0.0F, 0.0F);
		mainPart.addChild(bone14);

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, 0.0F, 4.0F);
		setRotationAngle(bone15, 0.6981F, 0.0F, 0.0F);
		mainPart.addChild(bone15);

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, -4.6F, -0.9F);
		setRotationAngle(bone16, -0.3491F, 0.0F, 0.0F);
		mainPart.addChild(bone16);

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone17, -0.6109F, 0.0F, 0.0F);
		mainPart.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 2, 43, -1.5F, -10.4873F, 6.9164F, 3, 1, 1, 0.0F, false));

		barrel = new ModelRenderer(this);
		barrel.setRotationPoint(-4.0F, 24.0F, 19.0F);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		barrel.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 37, 42, 3.5F, -8.5F, -29.5F, 1, 1, 10, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 37, 42, 3.5F, -8.5F, -39.5F, 1, 1, 10, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 37, 42, 3.5F, -8.5F, -43.5F, 1, 1, 4, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.5F, -8.0F, -21.5F);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.5236F);
		barrel.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 37, 42, 2.5311F, 1.25F, -8.0F, 1, 1, 10, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 37, 42, 2.5311F, 1.25F, -18.0F, 1, 1, 10, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 37, 42, 2.5311F, 1.25F, -22.0F, 1, 1, 4, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.5F, -8.0F, -21.5F);
		setRotationAngle(bone3, 0.0F, 0.0F, -1.0472F);
		barrel.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 37, 42, 1.25F, 2.5311F, -8.0F, 1, 1, 10, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 37, 42, 1.25F, 2.5311F, -18.0F, 1, 1, 10, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 37, 42, 1.25F, 2.5311F, -22.0F, 1, 1, 4, 0.0F, false));

		handguard = new ModelRenderer(this);
		handguard.setRotationPoint(0.0F, 24.0F, 0.0F);
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -1.5F, -9.5F, -0.5F, 3, 3, 1, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -1.0F, -10.466F, -10.5F, 2, 1, 11, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, 1.466F, -9.0F, -10.5F, 1, 2, 11, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -1.0F, -6.534F, -10.5F, 2, 1, 11, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -2.466F, -9.0F, -10.5F, 1, 2, 11, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -1.0F, -6.634F, -10.5F, 2, 1, 11, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -2.366F, -9.0F, -10.5F, 1, 2, 11, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, 1.366F, -9.0F, -10.5F, 1, 2, 11, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -1.0F, -10.366F, -10.5F, 2, 1, 11, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, 1.466F, -9.0F, -20.5F, 1, 2, 10, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -1.0F, -6.534F, -20.5F, 2, 1, 10, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -2.466F, -9.0F, -20.5F, 1, 2, 10, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -1.0F, -10.466F, -20.5F, 2, 1, 10, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -2.366F, -9.0F, -20.5F, 1, 2, 10, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -1.0F, -10.366F, -20.5F, 2, 1, 10, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, 1.366F, -9.0F, -20.5F, 1, 2, 10, 0.0F, false));
		handguard.cubeList.add(new ModelBox(handguard, 33, 14, -1.0F, -6.634F, -20.5F, 2, 1, 10, 0.0F, false));

		axis1 = new ModelRenderer(this);
		axis1.setRotationPoint(-1.5F, -6.0F, -3.5F);
		setRotationAngle(axis1, 0.0F, 0.0F, -0.5236F);
		handguard.addChild(axis1);
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, 3.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, 1.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, 0.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -0.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -2.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -3.25F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -4.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -5.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -7.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -9.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -8.25F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -10.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -12.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -13.25F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -14.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -15.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, -0.25F, -1.299F, -17.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -17.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -15.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -14.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -13.25F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -12.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -10.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -9.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -8.25F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -7.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -5.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -4.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -3.25F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -2.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, -0.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, 0.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, 1.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.9821F, 0.567F, 3.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -17.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -15.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -14.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -13.25F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -12.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -10.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -9.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -8.25F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -7.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -5.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -4.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -3.25F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -0.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, 0.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, 1.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, 3.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 3.8481F, -1.6651F, -2.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -17.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -15.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -14.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -13.25F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -12.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -9.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -10.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -8.25F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -7.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -5.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -4.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -3.25F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -2.0F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, -0.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, 0.5F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, 1.75F, 1, 1, 1, 0.0F, false));
		axis1.cubeList.add(new ModelBox(axis1, 33, 14, 1.616F, -3.5311F, 3.0F, 1, 1, 1, 0.0F, false));

		axis2 = new ModelRenderer(this);
		axis2.setRotationPoint(1.5F, -6.0F, -3.5F);
		setRotationAngle(axis2, 0.0F, 0.0F, 0.5236F);
		handguard.addChild(axis2);
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, 3.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, 0.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, 1.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -0.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -2.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -3.25F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -4.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -5.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -7.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -8.25F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -9.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -10.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -12.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -14.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -13.25F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -15.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.9821F, 0.567F, -17.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -17.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -15.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -14.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -13.25F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -12.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -10.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -9.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -8.25F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -7.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -5.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -4.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -3.25F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -2.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, -0.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, 0.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, 1.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -0.75F, -1.299F, 3.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -17.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -15.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -13.25F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -14.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -12.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -8.25F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -7.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -5.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -4.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -3.25F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -2.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -0.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, 0.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, 1.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, 3.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -9.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -2.616F, -3.5311F, -10.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -17.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -15.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -14.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -13.25F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -12.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -10.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -9.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -8.25F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -5.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -7.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -3.25F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -4.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -0.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, -2.0F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, 0.5F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, 1.75F, 1, 1, 1, 0.0F, true));
		axis2.cubeList.add(new ModelBox(axis2, 33, 14, -4.8481F, -1.6651F, 3.0F, 1, 1, 1, 0.0F, true));

		magazine = new ModelRenderer(this);
		magazine.setRotationPoint(0.0F, 24.0F, 2.0F);
		magazine.cubeList.add(new ModelBox(magazine, 6, 42, -1.0F, -8.35F, 0.0F, 2, 8, 5, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone8, -0.0873F, 0.0F, 0.0F);
		magazine.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 6, 42, -1.0F, -0.7844F, -0.0495F, 2, 2, 5, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone9, -0.1745F, 0.0F, 0.0F);
		magazine.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 6, 42, -1.0F, 0.7795F, 0.0376F, 2, 2, 5, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone10, -0.2618F, 0.0F, 0.0F);
		magazine.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 6, 42, -1.0F, 2.3298F, 0.2607F, 2, 2, 5, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone11, -0.3491F, 0.0F, 0.0F);
		magazine.addChild(bone11);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone12, -0.4363F, 0.0F, 0.0F);
		magazine.addChild(bone12);

		sights = new ModelRenderer(this);
		sights.setRotationPoint(0.0F, 24.0F, 0.0F);
		sights.cubeList.add(new ModelBox(sights, 13, 72, -0.75F, -11.05F, 7.3F, 2, 1, 4, 0.0F, false));
		sights.cubeList.add(new ModelBox(sights, 13, 72, -1.25F, -11.05F, 7.3F, 2, 1, 4, 0.0F, false));
		sights.cubeList.add(new ModelBox(sights, 13, 72, -1.0F, -11.45F, 7.3F, 2, 1, 4, 0.0F, false));
		sights.cubeList.add(new ModelBox(sights, 13, 72, -1.0F, -11.95F, 9.3F, 2, 1, 2, 0.0F, false));
		sights.cubeList.add(new ModelBox(sights, 13, 72, 0.15F, -11.85F, 10.0F, 1, 1, 1, 0.0F, false));
		sights.cubeList.add(new ModelBox(sights, 13, 72, -1.15F, -11.85F, 10.0F, 1, 1, 1, 0.0F, true));
		sights.cubeList.add(new ModelBox(sights, 13, 72, 0.3F, -12.8522F, 10.3941F, 1, 1, 1, 0.0F, false));
		sights.cubeList.add(new ModelBox(sights, 13, 72, -1.3F, -12.8522F, 10.3941F, 1, 1, 1, 0.0F, true));
		sights.cubeList.add(new ModelBox(sights, 13, 72, -0.75F, -11.35F, -20.2F, 2, 1, 4, 0.0F, false));
		sights.cubeList.add(new ModelBox(sights, 13, 72, -1.0F, -11.55F, -20.2F, 2, 1, 4, 0.0F, false));
		sights.cubeList.add(new ModelBox(sights, 13, 72, -1.25F, -11.35F, -20.2F, 2, 1, 4, 0.0F, false));
		sights.cubeList.add(new ModelBox(sights, 13, 72, -1.0F, -12.35F, -20.2F, 2, 1, 1, 0.0F, false));
		sights.cubeList.add(new ModelBox(sights, 13, 72, -0.5F, -12.85F, -20.2F, 1, 1, 1, 0.0F, false));

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(0.0F, 2.2F, 3.4F);
		setRotationAngle(bone22, 0.3491F, 0.0F, 0.0F);
		sights.addChild(bone22);
		bone22.cubeList.add(new ModelBox(bone22, 13, 72, 0.3F, -11.4706F, 11.3181F, 1, 1, 1, 0.0F, false));
		bone22.cubeList.add(new ModelBox(bone22, 13, 72, -1.3F, -11.4706F, 11.3181F, 1, 1, 1, 0.0F, false));
		bone22.cubeList.add(new ModelBox(bone22, 13, 72, -0.5F, -11.2405F, 8.3188F, 1, 1, 2, 0.0F, false));
		bone22.cubeList.add(new ModelBox(bone22, 13, 72, -0.8F, -11.6405F, 8.6188F, 1, 1, 1, 0.0F, false));
		bone22.cubeList.add(new ModelBox(bone22, 13, 72, -0.2F, -11.6405F, 8.6188F, 1, 1, 1, 0.0F, true));
		bone22.cubeList.add(new ModelBox(bone22, 13, 72, -0.2F, -11.6405F, 9.2188F, 1, 1, 1, 0.0F, true));
		bone22.cubeList.add(new ModelBox(bone22, 13, 72, -0.8F, -11.6405F, 9.2188F, 1, 1, 1, 0.0F, false));

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(-0.0F, -7.8184F, 13.8628F);
		setRotationAngle(bone23, 0.5236F, 0.0F, 0.0F);
		bone22.addChild(bone23);
		bone23.cubeList.add(new ModelBox(bone23, 13, 72, -1.3F, -4.4781F, -0.8884F, 1, 1, 1, 0.0F, false));
		bone23.cubeList.add(new ModelBox(bone23, 13, 72, 0.3F, -4.4781F, -0.8884F, 1, 1, 1, 0.0F, false));

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone24, -0.4363F, 0.0F, 0.0F);
		sights.addChild(bone24);
		bone24.cubeList.add(new ModelBox(bone24, 13, 72, -1.0F, -3.0786F, -22.6204F, 2, 1, 2, 0.0F, false));
	}
	
	@Override
	public void initAnimations()
	{
		initAimAnimation(-0.5575f, 0.235f, 0.25f);
		initAimingAnimationStates(0.235f, 0.215f, 0.175f);
	}
	
	@Override
	public void render(ItemStack stack)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		
		if(player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
		{
			super.preRender(stack);
			IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			GlStateManager.pushMatrix();
			{
				renderM416(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderM416(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		GlStateManager.scale(2, 2, 2);
		GlStateManager.translate(0.0, -12.0, -10.0);
		
		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		
		renderSilencer(stack);
		renderVerticalGrip(stack);
		renderAngledGrip(stack);
		renderRedDot(stack);
		renderHolo(stack);
		render2X(stack);
		render4X(stack);
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderARSilencer(0, -2.25, -9, 1f, stack);
	}
	
	private void renderVerticalGrip(ItemStack stack)
	{
		renderVerticalGrip(-1, 1.5, -5, 1.4f, stack);
	}
	
	private void renderAngledGrip(ItemStack stack)
	{
		renderAngledGrip(0.4, 2.8, 6, 1.1f, stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-0.3, -6, -2, 1.3f, stack);
	}
	
	private void renderHolo(ItemStack stack)
	{
		renderHolo(-1.45, -6.4, -5, 1.3f, stack);
	}
	
	private void render2X(ItemStack stack)
	{
		renderScope2X(7.2, 8, -8, 1.1f, stack);
	}
	
	private void render4X(ItemStack stack)
	{
		renderScope4X(7, 8, -9, 1.1f, stack);
	}
	
	private void renderParts(boolean scope)
	{
		stock.render(1f);
		mainPart.render(1f);
		barrel.render(1f);
		handguard.render(1f);
		magazine.render(1f);
		
		if(!scope)
			sights.render(1f);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
