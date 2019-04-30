package com.toma.pubgmc.client.models.weapons;

import com.toma.pubgmc.animation.IPartAnimated.MagazineMovementStyle;
import com.toma.pubgmc.animation.ReloadAnimation;
import com.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.util.ModelDebugger;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.util.PUBGMCUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelAKM extends ModelGun
{
	private final ModelRenderer upperReceiver;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone;
	private final ModelRenderer bone4;
	private final ModelRenderer bone6;
	private final ModelRenderer backOfReceiver;
	private final ModelRenderer bone5;
	private final ModelRenderer magazine;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer stock;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer woodenParts;
	private final ModelRenderer upperBarrel;
	private final ModelRenderer lowerBarrel;
	private final ModelRenderer bone16;
	private final ModelRenderer barrels;
	private final ModelRenderer barrel;
	private final ModelRenderer gasConnection;
	private final ModelRenderer ironsights;
	private final ModelRenderer foresight;
	private final ModelRenderer bone17;
	private final ModelRenderer rearsight;
	private final ModelRenderer bone18;
	private final ModelRenderer grip;
	private final ModelRenderer bone19;

	/**
	 * @author OfficialMajonaise
	 */
	public ModelAKM()
	{
		super();
		
		textureWidth = 128;
		textureHeight = 128;

		upperReceiver = new ModelRenderer(this);
		upperReceiver.setRotationPoint(0.0F, 24.0F, 0.0F);
		upperReceiver.cubeList.add(new ModelBox(upperReceiver, 0, 33, -1.5F, -17.15F, -11.0F, 3, 1, 13, 0.0F, false));
		upperReceiver.cubeList.add(new ModelBox(upperReceiver, 0, 33, -1.5F, -17.15F, 2.0F, 3, 1, 13, 0.0F, false));
		upperReceiver.cubeList.add(new ModelBox(upperReceiver, 0, 33, -1.5F, -17.15F, 15.0F, 3, 1, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 1.1345F);
		upperReceiver.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 32, -15.0471F, -8.2599F, -14.0F, 2, 2, 14, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 32, -15.0471F, -8.2599F, 0.0F, 2, 2, 14, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 32, -15.0471F, -8.2599F, 14.0F, 2, 2, 2, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone3, 0.0F, 0.0F, -1.1345F);
		upperReceiver.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 0, 33, 12.3471F, -8.2599F, 2.0F, 2, 2, 14, 0.0F, true));
		bone3.cubeList.add(new ModelBox(bone3, 0, 33, 12.3471F, -8.2599F, -12.0F, 2, 2, 14, 0.0F, true));
		bone3.cubeList.add(new ModelBox(bone3, 0, 33, 12.3471F, -8.2599F, -14.0F, 2, 2, 2, 0.0F, true));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		upperReceiver.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 0, 33, -2.5F, -14.5F, 8.0F, 5, 1, 10, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 33, -2.5F, -13.5F, 8.0F, 5, 2, 10, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 33, -0.5F, -13.5F, -7.0F, 3, 2, 13, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.5F, -13.5F, -7.0F, 1, 2, 13, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, -12.2F, -7.0F, 1, 1, 6, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 33, -2.5F, -13.5F, -11.0F, 5, 2, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.8F, -13.4F, -5.5F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.8F, -13.4F, -5.5F, 1, 2, 12, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 32, -1.5F, -17.25F, -14.0F, 3, 3, 5, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 33, -2.5F, -14.5F, -2.0F, 5, 1, 10, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 33, -2.5F, -14.5F, -11.0F, 5, 1, 9, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 32, -2.5F, -13.5F, 6.0F, 5, 2, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 33, -2.5F, -11.5F, 8.0F, 5, 1, 10, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 33, -2.5F, -11.5F, -2.0F, 5, 1, 10, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 33, -2.5F, -11.5F, -11.0F, 5, 1, 9, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.7854F);
		bone.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 33, 11.0208F, -10.4853F, 2.0F, 1, 2, 15, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 32, 8.4853F, -12.0208F, 3.0F, 2, 1, 14, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 32, 8.4853F, -12.0208F, -11.0F, 2, 1, 14, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 32, 8.4853F, -12.0208F, -14.0F, 2, 1, 3, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 33, 11.0208F, -10.4853F, -11.0F, 1, 2, 13, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone6, 0.0F, -0.4363F, 0.0F);
		bone.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 0, -6.4394F, -13.4F, -3.895F, 2, 1, 1, 0.0F, false));

		backOfReceiver = new ModelRenderer(this);
		backOfReceiver.setRotationPoint(0.0F, 24.0F, 0.0F);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone5, 0.6109F, 0.0F, 0.0F);
		backOfReceiver.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 33, 33, -2.0F, -2.5533F, 22.0616F, 4, 1, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 32, 33, -1.5F, -3.5533F, 22.0616F, 3, 1, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 32, 33, -1.0F, -4.0714F, 22.1042F, 2, 2, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 33, 1, -0.5F, -3.8533F, 22.4616F, 1, 2, 1, 0.0F, false));

		magazine = new ModelRenderer(this);
		magazine.setRotationPoint(0.0F, 23.1F, -2.8F);
		setRotationAngle(magazine, -0.1745F, 0.0F, 0.0F);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
		magazine.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 0, 64, -1.5F, -10.7F, -3.3F, 3, 3, 7, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone8, -0.1745F, 0.0F, 0.0F);
		magazine.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 0, 64, -1.5F, -8.2255F, -4.6933F, 3, 3, 7, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone9, -0.3491F, 0.0F, 0.0F);
		magazine.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 0, 64, -1.5F, -5.5467F, -5.6358F, 3, 3, 7, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone10, -0.5236F, 0.0F, 0.0F);
		magazine.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 0, 64, -1.5F, -2.7449F, -6.0987F, 3, 3, 7, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone11, -0.6981F, 0.0F, 0.0F);
		magazine.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 0, 64, -1.5F, 0.0947F, -6.0681F, 3, 3, 7, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone12, -0.8727F, 0.0F, 0.0F);
		magazine.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 0, 64, -1.5F, 2.8859F, -5.5449F, 3, 3, 7, 0.0F, false));

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 20.4F, -1.0F);
		setRotationAngle(stock, -0.1745F, 0.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 65, 0, -2.0F, -14.0F, 18.5F, 4, 4, 1, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 65, 0, -2.0F, -13.3F, 19.5F, 4, 3, 16, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 64, 0, -0.2071F, -14.0071F, 19.5F, 1, 1, 16, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 64, 0, -1.2929F, -14.0071F, 19.5F, 1, 1, 16, 0.0F, true));
		stock.cubeList.add(new ModelBox(stock, 65, 0, -2.0F, -10.3F, 31.5F, 4, 4, 4, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 64, 0, -2.0F, -10.1626F, 31.1672F, 4, 4, 4, 0.0F, false));
		stock.cubeList.add(new ModelBox(stock, 33, 32, -2.0F, -14.0F, 16.5F, 4, 4, 2, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone13, -0.1745F, 0.0F, 0.0F);
		stock.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 65, 0, -2.0F, -17.1734F, 16.7727F, 4, 4, 7, 0.0F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone14, 0.0F, 0.0F, -0.7854F);
		stock.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 64, 1, 9.8187F, -8.9903F, 19.5F, 1, 1, 16, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 65, 0, 7.9903F, -10.8187F, 19.5F, 1, 1, 16, 0.0F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, -10.8F, 3.3F);
		setRotationAngle(bone15, -0.3491F, 0.0F, 0.0F);
		stock.addChild(bone15);
		bone15.cubeList.add(new ModelBox(bone15, 65, 0, -2.0F, -9.1735F, 17.7727F, 4, 4, 10, 0.0F, false));

		woodenParts = new ModelRenderer(this);
		woodenParts.setRotationPoint(0.0F, 24.0F, 0.0F);

		upperBarrel = new ModelRenderer(this);
		upperBarrel.setRotationPoint(0.0F, 0.0F, 0.0F);
		woodenParts.addChild(upperBarrel);
		upperBarrel.cubeList.add(new ModelBox(upperBarrel, 64, 0, -2.0F, -16.6F, -24.0F, 4, 2, 10, 0.0F, false));
		upperBarrel.cubeList.add(new ModelBox(upperBarrel, 65, 0, -1.5F, -17.2F, -24.0F, 3, 1, 10, 0.0F, false));

		lowerBarrel = new ModelRenderer(this);
		lowerBarrel.setRotationPoint(0.0F, 0.0F, 3.0F);
		woodenParts.addChild(lowerBarrel);
		lowerBarrel.cubeList.add(new ModelBox(lowerBarrel, 64, 0, -2.0F, -14.2F, -24.0F, 4, 3, 10, 0.0F, false));
		lowerBarrel.cubeList.add(new ModelBox(lowerBarrel, 65, 0, -2.0F, -11.7F, -17.0F, 4, 1, 3, 0.0F, false));
		lowerBarrel.cubeList.add(new ModelBox(lowerBarrel, 64, 0, -2.0F, -14.2F, -27.0F, 4, 2, 3, 0.0F, false));

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone16, -0.2618F, 0.0F, 0.0F);
		lowerBarrel.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 64, 0, -2.0F, -6.9355F, -22.1901F, 4, 1, 3, 0.0F, false));
		bone16.cubeList.add(new ModelBox(bone16, 65, 0, -2.0F, -5.6067F, -29.081F, 4, 1, 3, 0.0F, false));

		barrels = new ModelRenderer(this);
		barrels.setRotationPoint(0.0F, 24.0F, 0.0F);

		barrel = new ModelRenderer(this);
		barrel.setRotationPoint(0.0F, 0.0F, 0.0F);
		barrels.addChild(barrel);
		barrel.cubeList.add(new ModelBox(barrel, 33, 33, -1.0F, -14.1F, -45.9F, 2, 2, 13, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 33, 33, -1.5F, -13.6F, -26.9F, 3, 1, 6, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 33, 32, -1.0F, -16.9F, -31.5F, 2, 2, 8, 0.0F, false));
		barrel.cubeList.add(new ModelBox(barrel, 33, 33, -1.0F, -14.1F, -32.9F, 2, 2, 10, 0.0F, false));

		gasConnection = new ModelRenderer(this);
		gasConnection.setRotationPoint(0.0F, 0.0F, -6.0F);
		setRotationAngle(gasConnection, 0.7854F, 0.0F, 0.0F);
		barrel.addChild(gasConnection);
		gasConnection.cubeList.add(new ModelBox(gasConnection, 34, 33, -1.0F, -29.9813F, -10.0811F, 2, 2, 4, 0.0F, false));

		ironsights = new ModelRenderer(this);
		ironsights.setRotationPoint(0.0F, 24.0F, 0.0F);

		foresight = new ModelRenderer(this);
		foresight.setRotationPoint(0.0F, 0.0F, 0.0F);
		ironsights.addChild(foresight);
		foresight.cubeList.add(new ModelBox(foresight, 0, 0, -1.5F, -14.6F, -43.1F, 3, 3, 4, 0.0F, false));
		foresight.cubeList.add(new ModelBox(foresight, 0, 0, -1.5F, -18.6F, -42.5F, 3, 4, 1, 0.0F, false));
		foresight.cubeList.add(new ModelBox(foresight, 0, 0, 0.5F, -19.6F, -42.5F, 1, 1, 1, 0.0F, false));
		foresight.cubeList.add(new ModelBox(foresight, 0, 0, -1.5F, -19.6F, -42.5F, 1, 1, 1, 0.0F, false));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone17, 0.4363F, 0.0F, 0.0F);
		foresight.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 0, 0, -1.5F, -34.396F, -30.7511F, 3, 5, 1, 0.0F, false));

		rearsight = new ModelRenderer(this);
		rearsight.setRotationPoint(0.0F, 0.0F, 0.0F);
		ironsights.addChild(rearsight);
		rearsight.cubeList.add(new ModelBox(rearsight, 0, 32, -1.5F, -19.0F, -9.0F, 3, 1, 1, 0.0F, false));
		rearsight.cubeList.add(new ModelBox(rearsight, 0, 33, -1.6F, -19.5F, -9.0F, 1, 1, 1, 0.0F, false));
		rearsight.cubeList.add(new ModelBox(rearsight, 0, 33, 0.6F, -19.5F, -9.0F, 1, 1, 1, 0.0F, false));
		rearsight.cubeList.add(new ModelBox(rearsight, 0, 32, -1.5F, -17.5804F, -12.3214F, 3, 1, 1, 0.0F, false));
		rearsight.cubeList.add(new ModelBox(rearsight, 33, 33, -2.0F, -17.5804F, -10.3214F, 4, 1, 1, 0.0F, false));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, -1.8F, 3.6F);
		setRotationAngle(bone18, 0.3491F, 0.0F, 0.0F);
		rearsight.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 0, 33, -1.5F, -20.2742F, -9.564F, 3, 1, 4, 0.0F, false));

		grip = new ModelRenderer(this);
		grip.setRotationPoint(0.0F, 24.0F, 0.0F);

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(0.0F, 5.2F, 5.9F);
		setRotationAngle(bone19, 0.3491F, 0.0F, 0.0F);
		grip.addChild(bone19);
		bone19.cubeList.add(new ModelBox(bone19, 65, 0, -1.5F, -13.7285F, 9.6015F, 3, 9, 4, 0.0F, false));
		this.initAnimations();
	}
	
	@Override
	public void initAnimations()
	{
		initAimAnimation(-0.56f, 0.28f, 0.31f);
		initAimingAnimationStates(0.28f, 0.25f, 0.2f);
		reloadAnimation = new ReloadAnimation(magazine, MagazineMovementStyle.DEFAULT, ReloadStyle.MAGAZINE);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
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
				renderAKM(data.isAiming(), stack);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderAKM(boolean aim, ItemStack stack)
	{
		GlStateManager.pushMatrix();
		transform.defaultARTransform();
		GlStateManager.scale(1.5, 1.5, 1.5);
		GlStateManager.translate(0.0, -3.7, -7);

		if(aim && enableADS(stack))
		{
			rotateModelForADSRendering();
		}
		
		renderParts(hasScopeAtachment(stack));
		GlStateManager.popMatrix();
		
		renderSilencer(stack);
		renderRedDot(stack);
		renderHolo(stack);
		render2X(stack);
		render4X(stack);
	}
	
	private void renderSilencer(ItemStack stack)
	{
		renderARSilencer(0, -3.75, -19, 1f, stack);
	}
	
	private void renderRedDot(ItemStack stack)
	{
		renderRedDot(-0.45, -4f, 2, 1.3f, stack);
	}
	
	private void renderHolo(ItemStack stack)
	{
		renderHolo(-1.5, -5.1f, -2, 1.3f, stack);
	}
	
	private void render2X(ItemStack stack)
	{
		renderScope2X(8, 13, -8, 1f, stack);
	}
	
	private void render4X(ItemStack stack)
	{
		renderScope4X(8, 13, -10, 1f, stack);
	}
	
	private void renderParts(boolean hasScope)
	{
		upperReceiver.render(1f);
		backOfReceiver.render(1f);
		magazine.render(1f);
		stock.render(1f);
		woodenParts.render(1f);
		barrels.render(1f);
		ironsights.render(1f);
		grip.render(1f);
	}
}
