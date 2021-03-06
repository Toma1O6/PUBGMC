package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.HeldAnimation;
import dev.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ModelMP5K extends ModelGun {

    private final ModelRenderer magazine;
    private final ModelRenderer bone3;
    private final ModelRenderer bone5;
    private final ModelRenderer bone;
    private final ModelRenderer bone4;
    private final ModelRenderer bone2;
    private final ModelRenderer bone6;
    private final ModelRenderer bone7;
    private final ModelRenderer bone9;
    private final ModelRenderer gun;
    private final ModelRenderer bone16;
    private final ModelRenderer bone21;
    private final ModelRenderer bone17;
    private final ModelRenderer bone20;
    private final ModelRenderer bone31;
    private final ModelRenderer bone32;
    private final ModelRenderer bone28;
    private final ModelRenderer bone29;
    private final ModelRenderer bone30;
    private final ModelRenderer bone15;
    private final ModelRenderer bone23;
    private final ModelRenderer bone22;
    private final ModelRenderer bone18;
    private final ModelRenderer bone19;
    private final ModelRenderer bone13;
    private final ModelRenderer bone14;
    private final ModelRenderer bone10;
    private final ModelRenderer ironsights;
    private final ModelRenderer bone26;
    private final ModelRenderer bone27;

    @Override
    public void initAnimations() {
        initAimAnimation(-0.55f, 0.23f, 0.1f);
        initAimingAnimationStates(0.23f, 0.15f, 0.13f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
        reloadAnimation = new ReloadAnimation(magazine, ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION);
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            GlStateManager.pushMatrix();
            renderMP5K(stack);
            GlStateManager.popMatrix();
        }
    }

    private void renderMP5K(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultSMGTransform();
        GlStateManager.translate(0.32499984, 16.450008, -1.0);
        gun.render(1f);
        magazine.render(1f);
        if(!this.hasScopeAtachment(stack)) this.ironsights.render(1f);
        GlStateManager.popMatrix();

        /*this.renderSMGSilencer(0, -7, 2, 1.2F, stack);
        this.renderRedDot(0.725, -4, 9, 1.2F, stack);
        this.renderHolo(0.25, -0.225, 2, 1.0F, stack);
        this.renderScope2X(0, 0, 4, 1.0F, stack);
        this.renderScope4X(0, -3, 2, 1.1F, stack);*/
    }

    public ModelMP5K() {
        textureWidth = 128;
        textureHeight = 128;

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 22.16F, -0.32F);
        setRotationAngle(magazine, -0.0349F, 0.0F, 0.0F);

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, -1.0F, 0.0F);
        magazine.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, -1.0F, -23.9848F, -6.9725F, 2, 2, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, 1.0F, -24.9848F, -5.8725F, 1, 3, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, -2.0F, -24.9848F, -5.8725F, 1, 3, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, 1.0F, -24.5848F, -4.8725F, 1, 1, 4, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, -2.0F, -24.5848F, -4.8725F, 1, 1, 4, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, 0.8F, -25.9848F, -5.8725F, 1, 1, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, -1.8F, -25.9848F, -5.8725F, 1, 1, 2, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, 0.8F, -25.9848F, -1.8725F, 1, 1, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, -1.8F, -25.9848F, -1.8725F, 1, 1, 2, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, 0.8F, -26.4848F, -3.8725F, 1, 2, 2, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, -1.8F, -26.4848F, -3.8725F, 1, 2, 2, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, -1.0F, -25.7768F, -0.8725F, 2, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 14, 104, -0.8F, -26.5848F, -1.8725F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 14, 104, -0.8F, -26.5848F, -3.4725F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 14, 104, -0.8F, -26.5848F, -4.0725F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 14, 104, -0.2F, -26.5848F, -1.8725F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 14, 104, -0.2F, -26.5848F, -3.4725F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 14, 104, -0.2F, -26.5848F, -4.0725F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 14, 104, -0.3F, -26.4848F, -2.6725F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 14, 104, -0.3F, -26.4848F, -4.8725F, 1, 1, 1, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 14, 104, -0.7F, -26.4848F, -2.6725F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 14, 104, -0.7F, -26.4848F, -4.8725F, 1, 1, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, -1.0F, -25.7768F, -5.8725F, 2, 2, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, 0.804F, -24.9848F, -4.8725F, 1, 3, 4, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, -1.804F, -24.9848F, -4.8725F, 1, 3, 4, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 41, 14, -2.0F, -24.9848F, -0.8725F, 4, 3, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -8.2F, 0.0F);
        setRotationAngle(bone5, 0.6981F, 0.0F, 0.0F);
        bone3.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 41, 14, -1.0F, -16.5737F, 4.805F, 2, 2, 2, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(-1.0F, 0.0F, 0.0F);
        setRotationAngle(bone, 0.0F, 0.0F, -0.2618F);
        bone3.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 41, 14, 8.3643F, -24.357F, -0.8725F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 41, 14, 8.3643F, -24.357F, -5.8725F, 1, 1, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(1.0F, 0.0F, 0.0F);
        setRotationAngle(bone4, 0.0F, 0.0F, 0.2618F);
        bone3.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 41, 14, -9.3643F, -24.357F, -0.8725F, 1, 1, 1, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 41, 14, -9.3643F, -24.357F, -5.8725F, 1, 1, 1, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, -1.0F, 0.0F);
        setRotationAngle(bone2, -0.0873F, 0.0F, 0.0F);
        magazine.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 41, 14, -1.0F, -21.9122F, -8.8891F, 2, 4, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 41, 14, 1.0F, -21.9122F, -7.7891F, 1, 4, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 41, 14, 1.0F, -20.5122F, -6.7891F, 1, 1, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 41, 14, -2.0F, -20.5122F, -6.7891F, 1, 1, 4, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 41, 14, 0.804F, -21.9122F, -6.7891F, 1, 4, 4, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 41, 14, -1.804F, -21.9122F, -6.7891F, 1, 4, 4, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 41, 14, -2.0F, -21.9122F, -7.7891F, 1, 4, 1, 0.0F, true));
        bone2.cubeList.add(new ModelBox(bone2, 41, 14, -2.0F, -21.9122F, -2.7891F, 4, 4, 1, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, -1.0F, 0.0F);
        setRotationAngle(bone6, -0.1745F, 0.0F, 0.0F);
        magazine.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 41, 14, -1.0F, -17.6881F, -10.4434F, 2, 4, 2, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 41, 14, 1.0F, -17.6881F, -9.3434F, 1, 4, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 41, 14, 1.0F, -16.2881F, -8.3434F, 1, 1, 4, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 41, 14, -2.0F, -16.2881F, -8.3434F, 1, 1, 4, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 41, 14, 0.804F, -17.6881F, -8.3434F, 1, 4, 4, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 41, 14, -1.804F, -17.6881F, -8.3434F, 1, 4, 4, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 41, 14, -2.0F, -17.6881F, -9.3434F, 1, 4, 1, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 41, 14, -2.0F, -17.6881F, -4.3434F, 4, 4, 1, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, -1.0F, 0.0F);
        setRotationAngle(bone7, -0.2618F, 0.0F, 0.0F);
        magazine.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 41, 14, -1.0F, -13.3447F, -11.6237F, 2, 4, 2, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 41, 14, 1.0F, -13.3447F, -10.5237F, 1, 4, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 41, 14, 1.0F, -11.9447F, -9.5237F, 1, 1, 4, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 41, 14, -2.0F, -11.9447F, -9.5237F, 1, 1, 4, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 41, 14, 0.804F, -13.3447F, -9.5237F, 1, 4, 4, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 41, 14, -1.804F, -13.3447F, -9.5237F, 1, 4, 4, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 41, 14, -2.0F, -13.3447F, -10.5237F, 1, 4, 1, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 41, 14, -2.0F, -13.3447F, -5.5237F, 4, 4, 1, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, -1.0F, 0.0F);
        setRotationAngle(bone9, -0.3491F, 0.0F, 0.0F);
        magazine.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 41, 14, -1.0F, -8.9148F, -12.4209F, 2, 4, 2, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 41, 14, 1.0F, -8.9148F, -11.3209F, 1, 4, 1, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 41, 14, 1.0F, -7.5148F, -10.3209F, 1, 1, 4, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 41, 14, -2.0F, -7.5148F, -10.3209F, 1, 1, 4, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 41, 14, 0.804F, -8.9148F, -10.3209F, 1, 4, 4, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 41, 14, -1.804F, -8.9148F, -10.3209F, 1, 4, 4, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 41, 14, -2.0F, -8.9148F, -11.3209F, 1, 4, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 41, 14, -2.0F, -8.9148F, -6.3209F, 4, 4, 1, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 41, 14, -1.5F, -6.2188F, -10.9209F, 3, 1, 5, 0.0F, false));

        gun = new ModelRenderer(this);
        gun.setRotationPoint(0.0F, 24.0F, 0.0F);
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.5F, -27.0F, -2.0F, 5, 2, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.5F, -27.0F, 0.1704F, 5, 2, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.0F, -28.528F, 1.1704F, 4, 3, 11, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.5F, -29.2941F, -6.8296F, 5, 3, 8, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.5F, -29.4281F, 6.1704F, 5, 1, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.5F, -30.4281F, -7.8296F, 5, 0, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.5F, -31.4281F, -16.8296F, 5, 1, 9, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 11, 74, -1.5F, -22.9281F, -13.6296F, 3, 1, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 11, 74, -1.5F, -24.9281F, -13.6296F, 3, 1, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 11, 74, -1.5F, -26.9281F, -13.6296F, 3, 1, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 11, 74, -1.5F, -28.9281F, -13.6296F, 3, 1, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 11, 74, -2.0F, -23.9281F, -13.6296F, 4, 1, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 11, 74, -2.0F, -25.9281F, -13.6296F, 4, 1, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 11, 74, -2.0F, -27.9281F, -13.6296F, 4, 1, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.0F, -30.9281F, -13.6296F, 4, 2, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 11, 74, -1.5F, -23.9281F, -14.1296F, 3, 1, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 11, 74, -1.5F, -25.9281F, -14.1296F, 3, 1, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 11, 74, -1.5F, -27.9281F, -14.1296F, 3, 1, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -1.5F, -30.9281F, -14.1296F, 3, 2, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.5F, -28.4281F, 1.1704F, 5, 2, 5, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.5F, -33.8601F, 12.5144F, 5, 5, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 109, 38, -0.5F, -33.0681F, 12.9744F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 109, 38, 1.18F, -34.0281F, 12.8144F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 109, 38, -2.18F, -34.0281F, 12.8144F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 109, 38, -1.5F, -34.5081F, 12.4464F, 3, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -1.0F, -32.6521F, 12.9144F, 2, 3, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.0F, -33.6521F, 12.7864F, 4, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, 1.4F, -33.4121F, 12.6584F, 1, 4, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.4F, -33.4121F, 12.6584F, 1, 4, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 0, 8, 0.2F, -31.3521F, 13.0224F, 2, 2, 12, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 80, 21, -2.5F, -31.8521F, 20.0224F, 5, 3, 12, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 80, 21, -0.366F, -32.3521F, 20.0224F, 2, 1, 12, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 80, 21, -1.634F, -32.3521F, 20.0224F, 2, 1, 12, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 80, 21, -2.5F, -28.8521F, 30.0224F, 5, 5, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 74, 31, -1.5F, -24.12F, 30.0224F, 3, 2, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 80, 21, -1.5F, -26.8521F, 27.0224F, 3, 3, 3, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 0, 8, -2.2F, -31.3521F, 13.0224F, 2, 2, 12, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.0F, -30.2941F, -7.8296F, 5, 1, 21, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 21, -2.5F, -30.4281F, -7.8296F, 5, 2, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 85, 23, 2.0F, -32.2941F, -7.8296F, 1, 2, 4, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 85, 23, 2.0F, -33.2941F, -16.8296F, 1, 2, 9, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 85, 23, 2.0F, -32.2941F, 8.1704F, 1, 2, 5, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 85, 23, -3.0F, -32.2941F, 0.1704F, 1, 3, 13, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 40, 40, -2.808F, -32.3581F, -6.8296F, 1, 3, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 40, 40, -3.208F, -31.8581F, -6.2296F, 1, 2, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 85, 23, -3.0F, -32.2941F, -7.8296F, 1, 3, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 85, 23, -3.0F, -33.2941F, -16.8296F, 1, 2, 9, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 75, 25, 1.648F, -32.2941F, -3.8296F, 1, 2, 12, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 74, 21, -3.0F, -33.2941F, -7.8296F, 6, 1, 21, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 11, 0.134F, -35.5261F, -16.8296F, 1, 1, 30, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 64, 15, -1.134F, -35.5261F, -16.8296F, 2, 1, 30, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 66, 4, -1.0F, -35.4941F, -19.8096F, 2, 2, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 66, 4, -0.5F, -34.4941F, -17.8096F, 1, 1, 5, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 66, 4, -0.5F, -34.7741F, -18.8096F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 66, 4, -1.0F, -33.4941F, -21.8096F, 2, 2, 27, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 9, 77, -1.5F, -31.9941F, -25.8096F, 1, 1, 4, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 9, 77, 0.5F, -31.9941F, -25.8096F, 1, 1, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 4, -0.5F, -31.9941F, -22.8096F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 9, 77, -0.5F, -31.9941F, -25.8096F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 9, 77, -1.5F, -33.9941F, -25.8096F, 1, 1, 4, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 9, 77, 0.5F, -33.9941F, -25.8096F, 1, 1, 4, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 9, 77, -0.5F, -33.9941F, -22.8096F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 9, 77, -0.5F, -33.9941F, -25.8096F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 4, 0.5F, -32.9941F, -22.8096F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 9, 77, 0.5F, -32.9941F, -25.8096F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 9, 77, -1.5F, -32.9941F, -22.8096F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 9, 77, -1.5F, -32.9941F, -25.8096F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 4, -1.5F, -37.0021F, 7.1384F, 3, 2, 5, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 66, 4, 1.028F, -37.9541F, 10.5944F, 1, 3, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 66, 4, -2.028F, -37.9541F, 10.5944F, 1, 3, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 66, 4, 1.028F, -38.4541F, 10.5944F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 66, 4, -2.028F, -38.4541F, 10.5944F, 1, 1, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -1.0F, -36.4141F, -12.0856F, 2, 1, 19, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.5F, -37.0301F, 5.9144F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.5F, -37.0301F, -0.0856F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.5F, -37.0301F, -6.0856F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.5F, -37.0301F, -12.0856F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -1.366F, -36.5311F, -11.0856F, 1, 0, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -1.366F, -36.5311F, -9.0856F, 1, 0, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -1.366F, -36.5311F, -7.0856F, 1, 0, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -1.366F, -36.5311F, -5.0856F, 1, 0, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -1.366F, -36.5311F, -3.0856F, 1, 0, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -1.366F, -36.5311F, -1.0856F, 1, 0, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -1.366F, -36.5311F, 0.9144F, 1, 0, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -1.366F, -36.5311F, 2.9144F, 1, 0, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -1.366F, -36.5311F, 4.9144F, 1, 0, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.6348F, -36.5306F, -11.0856F, 2, 0, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.6348F, -36.5306F, -9.0856F, 2, 0, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.6348F, -36.5306F, -7.0856F, 2, 0, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.6348F, -36.5306F, -5.0856F, 2, 0, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.6348F, -36.5306F, -3.0856F, 2, 0, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.6348F, -36.5306F, -1.0856F, 2, 0, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.6348F, -36.5306F, 0.9144F, 2, 0, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.6348F, -36.5306F, 2.9144F, 2, 0, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.6348F, -36.5306F, 4.9144F, 2, 0, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.5F, -37.0301F, 3.9144F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.5F, -37.0301F, -2.0856F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.5F, -37.0301F, -8.0856F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.5F, -37.0301F, 1.9144F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.5F, -37.0301F, -4.0856F, 1, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 67, 69, -0.5F, -37.0301F, -10.0856F, 1, 1, 1, 0.0F, true));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(-1.0F, 0.0F, 0.0F);
        setRotationAngle(bone16, 0.1745F, 0.0F, 0.0F);
        gun.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 75, 21, -1.0F, -25.1482F, 11.5116F, 4, 8, 5, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(0.0F, -29.9281F, -12.1296F);
        setRotationAngle(bone21, 0.2618F, 0.0F, 0.0F);
        gun.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 75, 21, -2.0F, -0.6458F, 1.1901F, 4, 2, 4, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(-1.5F, 0.0F, 0.0F);
        setRotationAngle(bone17, 0.7854F, 0.0F, 0.0F);
        gun.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 75, 21, -1.0F, -16.3244F, 23.0506F, 5, 2, 3, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(1.0F, 0.0F, 0.0F);
        setRotationAngle(bone20, 0.2618F, 0.0F, 0.0F);
        gun.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 75, 21, -3.0F, -29.9869F, 20.841F, 4, 1, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 75, 21, -2.0F, -30.7744F, 20.8717F, 2, 1, 1, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(0.3F, 0.0F, 0.0F);
        setRotationAngle(bone31, 0.0F, 0.0F, 0.5236F);
        gun.addChild(bone31);
        bone31.cubeList.add(new ModelBox(bone31, 80, 21, -15.0208F, -28.6847F, 20.0224F, 1, 1, 12, 0.0F, false));
        bone31.cubeList.add(new ModelBox(bone31, 80, 21, -11.0208F, -21.7565F, 30.0224F, 1, 2, 2, 0.0F, false));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(-0.3F, 0.0F, 0.0F);
        setRotationAngle(bone32, 0.0F, 0.0F, -0.5236F);
        gun.addChild(bone32);
        bone32.cubeList.add(new ModelBox(bone32, 80, 21, 14.0208F, -28.6847F, 20.0224F, 1, 1, 12, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 80, 21, 10.0208F, -21.7565F, 30.0224F, 1, 2, 2, 0.0F, true));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(0.0F, -25.3521F, 28.5224F);
        setRotationAngle(bone28, -0.3491F, 0.0F, 0.0F);
        gun.addChild(bone28);
        bone28.cubeList.add(new ModelBox(bone28, 80, 21, -1.5F, -1.0774F, -3.8965F, 3, 3, 3, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(0.0F, -25.3521F, 28.5224F);
        setRotationAngle(bone29, -0.6981F, 0.0F, 0.0F);
        gun.addChild(bone29);
        bone29.cubeList.add(new ModelBox(bone29, 80, 21, -1.5F, 0.1393F, -6.004F, 3, 3, 3, 0.0F, false));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(0.0F, -25.3521F, 28.5224F);
        setRotationAngle(bone30, -1.0472F, 0.0F, 0.0F);
        gun.addChild(bone30);
        bone30.cubeList.add(new ModelBox(bone30, 80, 21, -1.5F, 2.0035F, -7.5682F, 3, 3, 3, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(-2.0F, 0.0F, 0.0F);
        setRotationAngle(bone15, 0.0F, 0.2618F, 0.0F);
        gun.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 40, 40, 0.1867F, -31.8581F, -5.3641F, 1, 2, 2, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(1.0F, -37.3021F, 8.6384F);
        setRotationAngle(bone23, 0.3491F, 0.0F, 0.0F);
        gun.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 66, 4, -0.5F, -0.906F, -1.5342F, 1, 2, 3, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 66, 4, -2.5F, -0.906F, -1.5342F, 1, 2, 3, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 66, 4, -1.5F, -0.282F, 0.4658F, 1, 2, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 66, 4, -1.5F, -0.906F, -1.5342F, 1, 2, 1, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(1.528F, -36.7541F, 11.8944F);
        setRotationAngle(bone22, -0.4363F, 0.0F, 0.0F);
        gun.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 66, 4, -0.5F, -1.4063F, -0.9226F, 1, 1, 1, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 66, 4, -3.556F, -1.4063F, -0.9226F, 1, 1, 1, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(-0.866F, 0.0F, -2.0F);
        setRotationAngle(bone18, 0.0F, 0.0F, -0.5236F);
        gun.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, 7.9144F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, 1.9144F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, -4.0856F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, -10.0856F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, -9.0856F, 0, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, -7.0856F, 0, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, -5.0856F, 0, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, -3.0856F, 0, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, -1.0856F, 0, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, 0.9144F, 0, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, 2.9144F, 0, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, 4.9144F, 0, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, 6.9144F, 0, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, 5.9144F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, -0.0856F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, -6.0856F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, 3.9144F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, -2.0856F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 67, 69, 17.8321F, -31.886F, -8.0856F, 1, 1, 1, 0.0F, true));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.866F, 0.0F, -2.0F);
        setRotationAngle(bone19, 0.0F, 0.0F, 0.5236F);
        gun.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -18.8321F, -31.886F, 7.9144F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -18.8321F, -31.886F, 1.9144F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -18.8321F, -31.886F, -4.0856F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -18.8321F, -31.886F, -10.0856F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -17.8321F, -31.886F, -9.0856F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -17.8321F, -31.886F, -7.0856F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -17.8321F, -31.886F, -5.0856F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -17.8321F, -31.886F, -3.0856F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -17.8321F, -31.886F, -1.0856F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -17.8321F, -31.886F, 0.9144F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -17.8321F, -31.886F, 2.9144F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -17.8321F, -31.886F, 4.9144F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -17.8321F, -31.886F, 6.9144F, 0, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -18.8321F, -31.886F, 5.9144F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -18.8321F, -31.886F, -0.0856F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -18.8321F, -31.886F, -6.0856F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -18.8321F, -31.886F, 3.9144F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -18.8321F, -31.886F, -2.0856F, 1, 1, 1, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 67, 69, -18.8321F, -31.886F, -8.0856F, 1, 1, 1, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-2.0F, 0.0F, 0.0F);
        setRotationAngle(bone13, 0.0F, 0.0F, 0.5236F);
        gun.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 75, 21, -11.3169F, -27.8694F, -7.8296F, 1, 1, 21, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 75, 21, -12.3169F, -29.6015F, -16.8296F, 1, 1, 9, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 66, 4, -17.5131F, -30.3335F, -16.8296F, 1, 2, 30, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 66, 4, -15.049F, -32.3335F, -16.8296F, 1, 1, 30, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(2.0F, 0.0F, 0.0F);
        setRotationAngle(bone14, 0.0F, 0.0F, -0.5236F);
        gun.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 75, 21, 10.3169F, -27.8694F, -7.8296F, 1, 1, 21, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 75, 21, 11.3169F, -29.6015F, -16.8296F, 1, 1, 9, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 66, 4, 16.5131F, -30.3335F, -16.8296F, 1, 2, 30, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 21, 78, 17.5131F, -29.5095F, -13.8296F, 1, 1, 1, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 21, 78, 18.5131F, -29.5095F, -14.2616F, 2, 1, 2, 0.0F, true));
        bone14.cubeList.add(new ModelBox(bone14, 66, 4, 14.049F, -32.3335F, -16.8296F, 1, 1, 30, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-1.5F, 0.0F, 0.0F);
        setRotationAngle(bone10, -0.2618F, 0.0F, 0.0F);
        gun.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 75, 21, -1.0F, -25.6305F, -13.4023F, 5, 2, 5, 0.0F, false));

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(-0.366F, 24.0F, 0.0F);
        ironsights.cubeList.add(new ModelBox(ironsights, 66, 4, -0.134F, -40.2262F, -19.8096F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 66, 4, 1.2321F, -38.8602F, -19.8096F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 66, 4, -1.5F, -38.8602F, -19.8096F, 1, 1, 1, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 66, 4, -0.634F, -37.4941F, -19.8096F, 2, 2, 1, 0.0F, true));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(0.366F, -39.0581F, -14.6096F);
        setRotationAngle(bone26, 0.0F, 0.0F, -0.5236F);
        ironsights.addChild(bone26);
        bone26.cubeList.add(new ModelBox(bone26, 66, 4, -0.849F, 1.4705F, -5.2F, 1, 1, 1, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 66, 4, 0.517F, 0.1045F, -5.2F, 1, 1, 1, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 66, 4, -0.849F, -1.2616F, -5.2F, 1, 1, 1, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 66, 4, -2.215F, 0.1045F, -5.2F, 1, 1, 1, 0.0F, true));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(0.366F, -39.0581F, -14.6096F);
        setRotationAngle(bone27, 0.0F, 0.0F, 0.5236F);
        ironsights.addChild(bone27);
        bone27.cubeList.add(new ModelBox(bone27, 66, 4, -0.151F, 1.4705F, -5.2F, 1, 1, 1, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 66, 4, 1.215F, 0.1045F, -5.2F, 1, 1, 1, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 66, 4, -1.517F, 0.1045F, -5.2F, 1, 1, 1, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 66, 4, -0.151F, -1.2616F, -5.2F, 1, 1, 1, 0.0F, false));
        this.initAnimations();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
