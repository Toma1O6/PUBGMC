package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.client.models.ModelGun;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelQBZ extends ModelGun {

    private final ModelRenderer magazine;
    private final ModelRenderer bone2;
    private final ModelRenderer qbz;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer bone13;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone21;
    private final ModelRenderer bone23;
    private final ModelRenderer bone24;
    private final ModelRenderer bone15;
    private final ModelRenderer bone17;
    private final ModelRenderer bone16;
    private final ModelRenderer bone14;
    private final ModelRenderer bone18;
    private final ModelRenderer bone22;
    private final ModelRenderer bone9;
    private final ModelRenderer bone10;
    private final ModelRenderer bone7;
    private final ModelRenderer bone11;
    private final ModelRenderer bone12;
    private final ModelRenderer bone6;
    private final ModelRenderer bone8;
    private final ModelRenderer bone3;
    private final ModelRenderer bone;
    private final ModelRenderer ironsights;
    private final ModelRenderer bone27;
    private final ModelRenderer bone28;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;

    @Override
    public String textureName() {
        return "g36c";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.25f, 0.35f);
        initAimingAnimationStates(0.25f, 0.215f, 0.19f);
        this.reloadAnimation = new ReloadAnimation(magazine, ReloadAnimation.ReloadStyle.MAGAZINE);
    }

    @Override
    public void render(ItemStack stack) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            GlStateManager.pushMatrix();
            {
                renderQBZ(stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderQBZ(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultARTransform();
        GlStateManager.translate(-0.025000004, 11.875002, -11.0);
        this.qbz.render(1f);
        this.magazine.render(1f);
        if(!this.hasScopeAtachment(stack)) this.ironsights.render(1f);
        GlStateManager.popMatrix();
        this.renderARSilencer(0.15, -11, 21, 1.2F, stack);
        this.renderRedDot(-0.05, 8, -8, 0.8F, stack);
        this.renderHolo(-0.05, 7, -6, 0.8F, stack);
        this.renderScope2X(0, 0.75, -4, 1.0F, stack);
        this.renderScope4X(0, 2, -8, 1.0F, stack);
        this.renderVerticalGrip(0, 0, 0, 0.8F, stack);
        this.renderAngledGrip(0, 4, 11, 0.8F, stack);
    }

    public ModelQBZ() {
        textureWidth = 128;
        textureHeight = 128;

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 25.312F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 0, 111, -1.5F, -21.224F, 15.808F, 3, 3, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 111, -1.5F, -21.224F, 10.416F, 3, 3, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 111, -1.0F, -20.724F, 9.0F, 2, 2, 7, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -1.5F, -20.0F, 17.0F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -1.5F, -19.0F, 8.0F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -21.0F, 8.0F, 1, 3, 10, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, 1.5F, -21.0F, 8.0F, 1, 3, 10, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -18.0F, 8.0F, 5, 1, 10, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -14.0F, 8.0F, 5, 1, 10, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -10.0F, 8.0F, 5, 1, 10, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -17.0F, 8.0F, 5, 3, 2, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -13.0F, 8.0F, 5, 3, 2, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -17.0F, 16.0F, 5, 3, 2, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -13.0F, 16.0F, 5, 3, 2, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -17.0F, 12.0F, 5, 3, 2, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.5F, -13.0F, 12.0F, 5, 3, 2, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.0F, -17.0F, 9.0F, 4, 3, 8, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 0, -2.0F, -13.0F, 9.0F, 4, 3, 8, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(2.0F, 0.0F, 0.0F);
        setRotationAngle(bone2, -0.2618F, 0.0F, 0.0F);
        magazine.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -4.5F, -5.3521F, 5.0573F, 5, 1, 10, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -4.5F, -8.3521F, 13.0573F, 5, 3, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -4.5F, -9.3521F, 5.0573F, 5, 1, 10, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -4.5F, -12.3521F, 13.0573F, 5, 3, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -4.5F, -12.3521F, 9.0573F, 5, 3, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -4.5F, -13.3521F, 5.0573F, 5, 1, 10, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -4.5F, -8.3521F, 5.0573F, 5, 3, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -4.5F, -12.3521F, 5.0573F, 5, 3, 2, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -4.0F, -8.3521F, 6.0573F, 4, 3, 8, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -4.0F, -12.3521F, 6.0573F, 4, 3, 8, 0.0F, true));

        qbz = new ModelRenderer(this);
        qbz.setRotationPoint(0.0F, 24.0F, 0.0F);
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -3.0F, -20.0F, 8.5F, 6, 4, 9, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.5F, -23.0F, 7.5F, 5, 7, 11, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.5F, -23.0F, -0.5F, 5, 5, 8, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.5F, -23.0F, -2.5F, 5, 4, 2, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.5F, -23.0F, -18.5F, 5, 2, 16, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 83, 24, -2.5F, -23.0F, -25.5F, 1, 2, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 83, 24, 1.5F, -23.0F, -25.5F, 1, 2, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 83, 24, -2.5F, -23.0F, -22.5F, 1, 2, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 83, 24, -2.5F, -23.0F, -29.5F, 1, 2, 2, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 83, 24, 1.5F, -23.0F, -22.5F, 1, 2, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 83, 24, 1.5F, -23.0F, -29.5F, 1, 2, 2, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 83, 24, -2.5F, -23.0F, -19.5F, 1, 2, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 83, 24, 1.5F, -23.0F, -19.5F, 1, 2, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 85, 29, -2.5F, -22.5F, -21.5F, 1, 1, 2, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 85, 29, -2.5F, -22.5F, -27.5F, 1, 1, 2, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 85, 29, 1.5F, -22.5F, -21.5F, 1, 1, 2, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 85, 29, 1.5F, -22.5F, -27.5F, 1, 1, 2, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 83, 24, -2.5F, -22.5F, -24.5F, 1, 1, 2, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 83, 24, 1.5F, -22.5F, -24.5F, 1, 1, 2, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.5F, -21.6905F, -4.1252F, 5, 2, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.0F, -29.0F, 5.164F, 1, 5, 4, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, 1.0F, -29.0F, 5.164F, 1, 5, 4, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, 1.0F, -31.0F, -12.836F, 1, 2, 22, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, 0.936F, -31.568F, 6.372F, 1, 2, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, 0.936F, -31.568F, -12.084F, 1, 2, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -1.936F, -31.568F, 6.372F, 1, 2, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -1.44F, -31.568F, 6.372F, 1, 2, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, 0.44F, -31.568F, 6.372F, 1, 2, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -0.5F, -30.448F, 6.372F, 1, 2, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -1.936F, -31.568F, -12.084F, 1, 2, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 45, 21, -0.5F, -32.6F, -11.892F, 1, 1, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 45, 21, -0.5F, -32.6F, 6.204F, 1, 1, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.5F, -32.4F, -1.836F, 3, 1, 9, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.5F, -32.4F, -11.836F, 3, 1, 10, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.8669F, -32.8995F, 5.164F, 2, 0, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.8669F, -32.8995F, -0.836F, 2, 0, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.8669F, -32.8995F, -6.836F, 2, 0, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.8669F, -32.8995F, 3.164F, 2, 0, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.8669F, -32.8995F, -2.836F, 2, 0, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.8669F, -32.8995F, -8.836F, 2, 0, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.8669F, -32.8995F, 1.164F, 2, 0, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.8669F, -32.8995F, -4.836F, 2, 0, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.8669F, -32.8995F, -10.836F, 2, 0, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -0.1331F, -32.8995F, 5.164F, 2, 0, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -0.1331F, -32.8995F, -0.836F, 2, 0, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -0.1331F, -32.8995F, -6.836F, 2, 0, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -0.1331F, -32.8995F, 3.164F, 2, 0, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -0.1331F, -32.8995F, -2.836F, 2, 0, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -0.1331F, -32.8995F, -8.836F, 2, 0, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -0.1331F, -32.8995F, 1.164F, 2, 0, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -0.1331F, -32.8995F, -4.836F, 2, 0, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -0.1331F, -32.8995F, -10.836F, 2, 0, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.0F, -33.4F, 6.164F, 2, 1, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.0F, -33.4F, 0.164F, 2, 1, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.0F, -33.4F, -5.836F, 2, 1, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.0F, -33.4F, 4.164F, 2, 1, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.0F, -33.4F, -1.836F, 2, 1, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.0F, -33.4F, -7.836F, 2, 1, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.0F, -33.4F, 2.164F, 2, 1, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.0F, -33.4F, -3.836F, 2, 1, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.0F, -33.4F, -9.836F, 2, 1, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 5, 72, -1.0F, -33.4F, -11.836F, 2, 1, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.0F, -31.0F, -12.836F, 1, 2, 22, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, 1.0F, -28.68F, 9.164F, 1, 5, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.0F, -28.68F, 9.164F, 1, 5, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -1.0F, -30.0F, -12.836F, 2, 1, 19, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.0F, -25.664F, -16.388F, 4, 2, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -1.0F, -30.0F, 6.164F, 2, 6, 3, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 82, 22, -1.5F, -17.1F, 18.3F, 3, 1, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.5F, -23.0F, 20.5F, 5, 1, 11, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.5F, -24.0F, 31.5F, 5, 6, 5, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.5F, -18.0F, 33.5F, 5, 1, 3, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.0F, -17.0F, 31.5F, 4, 2, 5, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -1.816F, -25.8794F, 31.5F, 2, 2, 5, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 68, 16, -1.816F, -24.8794F, 9.5F, 2, 2, 22, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 68, 16, -1.816F, -24.8794F, -18.5F, 2, 2, 28, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 68, 16, -1.816F, -24.8794F, -29.5F, 2, 1, 11, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 68, 16, -1.816F, -20.1206F, -29.5F, 2, 1, 11, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 75, 21, -1.0F, -22.6206F, -37.5F, 2, 2, 19, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 81, 41, -1.5F, -23.1206F, -32.5F, 3, 3, 2, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 68, 16, -0.184F, -21.1206F, -18.5F, 2, 2, 16, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 68, 16, -1.816F, -21.1206F, -18.5F, 2, 2, 16, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 68, 16, -0.184F, -24.8794F, -29.5F, 2, 1, 11, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 68, 16, -0.184F, -20.1206F, -29.5F, 2, 1, 11, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -0.184F, -25.8794F, 31.5F, 2, 2, 5, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 68, 16, -0.184F, -24.8794F, 9.5F, 2, 2, 22, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 68, 16, -0.184F, -24.8794F, -18.5F, 2, 2, 28, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.5F, -23.0F, 18.5F, 5, 5, 2, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -3.0F, -20.0F, 17.5F, 6, 1, 3, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.2929F, -20.0F, 20.2071F, 1, 1, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, 1.2929F, -20.0F, 20.2071F, 1, 1, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.0F, -22.0F, 20.5F, 4, 4, 11, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -1.5F, -18.064F, 20.7F, 3, 1, 10, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, 1.336F, -22.168F, 30.796F, 1, 4, 1, 0.0F, false));
        qbz.cubeList.add(new ModelBox(qbz, 73, 18, -2.336F, -22.168F, 30.796F, 1, 4, 1, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 47, 9, -2.08F, -20.44F, 26.636F, 1, 2, 2, 0.0F, true));
        qbz.cubeList.add(new ModelBox(qbz, 41, 19, 1.08F, -20.44F, 26.636F, 1, 2, 2, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(-1.0F, -7.0F, 18.0F);
        setRotationAngle(bone4, 0.0F, 0.0F, 0.3491F);
        qbz.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 73, 18, -6.3256F, -13.532F, -9.5F, 1, 2, 9, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 75, 17, -2.8414F, -15.2925F, 2.5F, 1, 2, 11, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 75, 17, -1.4733F, -11.5337F, 13.5F, 1, 2, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 79, 26, -1.1313F, -10.594F, 15.5F, 1, 2, 3, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 79, 26, -7.2239F, -17.4617F, 13.5F, 1, 2, 5, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -6.8819F, -16.5221F, -8.5F, 1, 2, 22, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -6.8819F, -16.5221F, -36.5F, 1, 2, 28, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -6.8819F, -16.5221F, -47.5F, 1, 1, 11, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -2.4994F, -13.3528F, -47.5F, 1, 1, 11, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -6.8819F, -15.5221F, -37.5F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -2.4994F, -14.3528F, -37.5F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -6.8819F, -15.5221F, -43.5F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -2.4994F, -14.3528F, -43.5F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -6.8819F, -15.5221F, -40.5F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -6.8819F, -15.5221F, -47.5F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -2.4994F, -14.3528F, -40.5F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -2.4994F, -14.3528F, -47.5F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 68, 16, -2.4994F, -14.3528F, -36.5F, 1, 2, 16, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(1.0F, -7.0F, 18.0F);
        setRotationAngle(bone5, 0.0F, 0.0F, -0.3491F);
        qbz.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 73, 18, 5.3256F, -13.532F, -9.5F, 1, 2, 9, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 75, 17, 1.8414F, -15.2925F, 2.5F, 1, 2, 11, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 75, 17, 0.4733F, -11.5337F, 13.5F, 1, 2, 2, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 79, 26, 0.1313F, -10.594F, 15.5F, 1, 2, 3, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 79, 26, 6.2239F, -17.4617F, 13.5F, 1, 2, 5, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 5.8819F, -16.5221F, -8.5F, 1, 2, 22, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 5.8819F, -16.5221F, -36.5F, 1, 2, 28, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 5.8819F, -15.5221F, -37.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 1.4994F, -14.3528F, -37.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 5.8819F, -16.5221F, -47.5F, 1, 1, 11, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 1.4994F, -13.3528F, -47.5F, 1, 1, 11, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 5.8819F, -15.5221F, -43.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 1.4994F, -14.3528F, -43.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 5.8819F, -15.5221F, -40.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 5.8819F, -15.5221F, -47.5F, 1, 1, 2, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 1.4994F, -14.3528F, -40.5F, 1, 1, 1, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 1.4994F, -14.3528F, -47.5F, 1, 1, 2, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 68, 16, 1.4994F, -14.3528F, -36.5F, 1, 2, 16, 0.0F, true));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(7.5F, 0.0F, 0.0F);
        setRotationAngle(bone13, 0.4363F, 0.0F, 0.0F);
        qbz.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 73, 18, -10.0F, -14.3313F, 13.5592F, 5, 3, 1, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, -9.0F, 3.5F);
        setRotationAngle(bone19, -0.4363F, 0.0F, 0.0F);
        qbz.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 73, 18, -2.5F, -8.4663F, -11.4288F, 5, 2, 4, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.0F, -0.976F, 3.5F);
        setRotationAngle(bone20, 0.2618F, 0.0F, 0.0F);
        qbz.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 10, 72, -2.0F, -20.5889F, -3.5496F, 4, 10, 4, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 85, 28, -1.5F, -22.1771F, -12.2089F, 3, 7, 3, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(8.5F, 9.0F, -3.5F);
        setRotationAngle(bone21, 0.3491F, 0.0F, 0.0F);
        bone20.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 85, 28, -9.5F, -25.8056F, 2.7461F, 2, 1, 7, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, -32.9F, 6.664F);
        setRotationAngle(bone23, 0.0F, 0.0F, -0.5236F);
        qbz.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -0.5F, 1, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -6.5F, 1, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -12.5F, 1, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -2.5F, 1, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -8.5F, 1, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -14.5F, 1, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -4.5F, 1, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -10.5F, 1, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -16.5F, 1, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -18.5F, 1, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -1.5F, 0, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -7.5F, 0, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -13.5F, 0, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -3.5F, 0, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -9.5F, 0, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -15.5F, 0, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -5.5F, 0, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -11.5F, 0, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 5, 72, -1.616F, -0.933F, -17.5F, 0, 1, 1, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -32.9F, 6.664F);
        setRotationAngle(bone24, 0.0F, 0.0F, 0.5236F);
        qbz.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 0.616F, -0.933F, -0.5F, 1, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 0.616F, -0.933F, -6.5F, 1, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 0.616F, -0.933F, -12.5F, 1, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 0.616F, -0.933F, -2.5F, 1, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 0.616F, -0.933F, -8.5F, 1, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 0.616F, -0.933F, -14.5F, 1, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 0.616F, -0.933F, -4.5F, 1, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 0.616F, -0.933F, -10.5F, 1, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 0.616F, -0.933F, -16.5F, 1, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 0.616F, -0.933F, -18.5F, 1, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 1.616F, -0.933F, -1.5F, 0, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 1.616F, -0.933F, -7.5F, 0, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 1.616F, -0.933F, -13.5F, 0, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 1.616F, -0.933F, -3.5F, 0, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 1.616F, -0.933F, -9.5F, 0, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 1.616F, -0.933F, -15.5F, 0, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 1.616F, -0.933F, -5.5F, 0, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 1.616F, -0.933F, -11.5F, 0, 1, 1, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 5, 72, 1.616F, -0.933F, -17.5F, 0, 1, 1, 0.0F, true));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(8.0F, 0.0F, 0.0F);
        setRotationAngle(bone15, 0.6109F, 0.0F, 0.0F);
        qbz.addChild(bone15);

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(8.0F, 0.0F, 0.0F);
        setRotationAngle(bone17, 0.1745F, 0.0F, 0.0F);
        qbz.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 73, 18, -7.0F, -29.0162F, 14.0041F, 1, 7, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 73, 18, -9.0F, -27.9529F, 13.2342F, 2, 7, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 73, 18, -10.0F, -29.0162F, 14.0041F, 1, 7, 1, 0.0F, true));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, -7.7589F, 22.574F);
        setRotationAngle(bone16, 0.6109F, 0.0F, 0.0F);
        qbz.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 73, 18, 1.0F, -13.1977F, 2.2872F, 1, 1, 1, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 73, 18, -2.0F, -13.1977F, 2.2872F, 1, 1, 1, 0.0F, true));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(1.5F, -20.0F, 11.164F);
        setRotationAngle(bone14, 0.0524F, 0.0F, 0.0F);
        qbz.addChild(bone14);

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(8.0F, 0.0F, 0.0F);
        setRotationAngle(bone18, 0.8727F, 0.0F, 0.0F);
        qbz.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 73, 18, -10.0F, -29.743F, 6.5176F, 1, 1, 9, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 73, 18, -7.0F, -29.743F, 6.5176F, 1, 1, 9, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 73, 18, -10.0F, -29.1166F, 6.7305F, 4, 1, 8, 0.0F, true));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(-8.0F, 0.0F, 0.0F);
        setRotationAngle(bone22, 0.2618F, 0.0F, 0.0F);
        qbz.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 73, 18, 6.5F, -16.8448F, 33.1247F, 3, 1, 4, 0.0F, true));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, -4.5F, 42.0F);
        setRotationAngle(bone9, 0.4363F, 0.0F, 0.0F);
        qbz.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 73, 18, -2.0F, -16.9537F, -5.0787F, 4, 3, 1, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, -6.068F, 39.872F);
        setRotationAngle(bone10, -0.3491F, 0.0F, 0.0F);
        qbz.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 73, 18, -2.0F, -8.4726F, -14.5549F, 4, 1, 3, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, -9.0F, 24.5F);
        setRotationAngle(bone7, 0.0F, 0.0F, -0.7854F);
        qbz.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 73, 18, 7.1317F, -5.5962F, -6.0F, 1, 1, 2, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 73, 18, 4.5962F, -8.1317F, -6.0F, 1, 1, 2, 0.0F, true));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(1.836F, -7.168F, 40.296F);
        setRotationAngle(bone11, 0.0F, 0.1745F, 0.0F);
        qbz.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 73, 18, 1.1421F, -15.0F, -11.2688F, 1, 4, 2, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-1.836F, -7.168F, 40.296F);
        setRotationAngle(bone12, 0.0F, -0.1745F, 0.0F);
        qbz.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 73, 18, -2.1421F, -15.0F, -11.2688F, 1, 4, 2, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -8.424F, 30.0F);
        setRotationAngle(bone6, 0.0873F, 0.0F, 0.0F);
        qbz.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 73, 18, -2.0F, -10.3901F, -11.4351F, 4, 1, 8, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, -8.184F, 30.24F);
        setRotationAngle(bone8, 0.1745F, 0.0F, 0.0F);
        qbz.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 83, 36, -1.0F, -10.6859F, -10.3403F, 2, 3, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(1.5F, 0.0F, 0.0F);
        setRotationAngle(bone3, 0.0F, -0.7854F, 0.0F);
        qbz.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 95, 26, 6.0711F, -20.0F, 4.9497F, 1, 4, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 73, 18, 9.1924F, -19.0F, 14.5563F, 1, 3, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 73, 18, 11.3137F, -20.0F, 16.6777F, 1, 1, 1, 0.0F, true));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(-1.5F, 0.0F, 0.0F);
        setRotationAngle(bone, 0.0F, 0.7854F, 0.0F);
        qbz.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 95, 26, -7.0711F, -20.0F, 4.9497F, 1, 4, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 73, 18, -10.1924F, -19.0F, 14.5563F, 1, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 73, 18, -12.3137F, -20.0F, 16.6777F, 1, 1, 1, 0.0F, false));

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(0.0F, 24.0F, 0.0F);
        ironsights.cubeList.add(new ModelBox(ironsights, 85, 21, -2.0F, -33.712F, 3.568F, 4, 2, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 85, 21, -2.0F, -33.712F, -11.432F, 4, 2, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 85, 21, -1.0F, -35.312F, 4.068F, 2, 2, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 85, 21, -1.0F, -35.312F, -10.932F, 2, 2, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 85, 21, -1.866F, -36.678F, 4.068F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 85, 21, 0.866F, -36.678F, 4.068F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 85, 21, -0.5F, -35.678F, -10.932F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 85, 21, -1.5F, -36.678F, -10.932F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 85, 21, 0.5F, -36.678F, -10.932F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 85, 21, -0.5F, -38.0441F, 4.068F, 1, 1, 1, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(1.0F, -36.178F, -10.432F);
        setRotationAngle(bone27, 0.0F, 0.0F, -0.5236F);
        ironsights.addChild(bone27);
        bone27.cubeList.add(new ModelBox(bone27, 85, 21, -2.4151F, -0.817F, -0.5F, 1, 1, 1, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-1.0F, -36.178F, -10.432F);
        setRotationAngle(bone28, 0.0F, 0.0F, 0.5236F);
        ironsights.addChild(bone28);
        bone28.cubeList.add(new ModelBox(bone28, 85, 21, 1.4151F, -0.817F, -0.5F, 1, 1, 1, 0.0F, true));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(0.0F, -36.212F, 4.568F);
        setRotationAngle(bone25, 0.0F, 0.0F, 0.5236F);
        ironsights.addChild(bone25);
        bone25.cubeList.add(new ModelBox(bone25, 85, 21, -0.483F, 0.8954F, -0.5F, 1, 1, 1, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 85, 21, 0.883F, -0.4706F, -0.5F, 1, 1, 1, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 85, 21, -0.483F, -1.8366F, -0.5F, 1, 1, 1, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 85, 21, -1.849F, -0.4706F, -0.5F, 1, 1, 1, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(0.0F, -36.212F, 4.568F);
        setRotationAngle(bone26, 0.0F, 0.0F, -0.5236F);
        ironsights.addChild(bone26);
        bone26.cubeList.add(new ModelBox(bone26, 85, 21, -1.883F, -0.4706F, -0.5F, 1, 1, 1, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 85, 21, 0.849F, -0.4706F, -0.5F, 1, 1, 1, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 85, 21, -0.517F, -1.8366F, -0.5F, 1, 1, 1, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 85, 21, -0.517F, 0.8954F, -0.5F, 1, 1, 1, 0.0F, true));
        this.initAnimations();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
