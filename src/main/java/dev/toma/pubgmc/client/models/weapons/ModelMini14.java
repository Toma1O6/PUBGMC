package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

public class ModelMini14 extends ModelGun {

    private final ModelRenderer mini14;
    private final ModelRenderer bone6;
    private final ModelRenderer bone8;
    private final ModelRenderer bone9;
    private final ModelRenderer bone56;
    private final ModelRenderer bone62;
    private final ModelRenderer bone42;
    private final ModelRenderer bone4;
    private final ModelRenderer bone57;
    private final ModelRenderer bone58;
    private final ModelRenderer bone55;
    private final ModelRenderer bone25;
    private final ModelRenderer bone26;
    private final ModelRenderer bone27;
    private final ModelRenderer bone28;
    private final ModelRenderer bone29;
    private final ModelRenderer bone53;
    private final ModelRenderer bone54;
    private final ModelRenderer bone22;
    private final ModelRenderer bone23;
    private final ModelRenderer bone24;
    private final ModelRenderer bone14;
    private final ModelRenderer bone15;
    private final ModelRenderer bone;
    private final ModelRenderer bone5;
    private final ModelRenderer bone7;
    private final ModelRenderer bone40;
    private final ModelRenderer bone39;
    private final ModelRenderer bone38;
    private final ModelRenderer bone33;
    private final ModelRenderer bone34;
    private final ModelRenderer bone30;
    private final ModelRenderer bone31;
    private final ModelRenderer bone2;
    private final ModelRenderer bone35;
    private final ModelRenderer bone3;
    private final ModelRenderer bone37;
    private final ModelRenderer rail3;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer rail4;
    private final ModelRenderer bone18;
    private final ModelRenderer bone19;
    private final ModelRenderer bone12;
    private final ModelRenderer bone13;
    private final ModelRenderer bone32;
    private final ModelRenderer rail5;
    private final ModelRenderer bone20;
    private final ModelRenderer bone21;
    private final ModelRenderer magazine;
    private final ModelRenderer bone36;
    private final ModelRenderer ironsights;
    private final ModelRenderer bone11;
    private final ModelRenderer bone10;

    @Override
    public String textureName() {
        return "mini14";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.275f, 0.23f);
        initAimingAnimationStates(0.275f, 0.21f, 0.18f);
        this.reloadAnimation = new ReloadAnimation(magazine, ReloadAnimation.ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION, 180);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            GlStateManager.pushMatrix();
            {
                renderMini14(stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderMini14(ItemStack stack) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        ModelTransformationHelper.defaultSRTransform();
        GlStateManager.translate(47.64998, 6.8749995, -26.0);
        mini14.render(1f);
        magazine.render(1f);
        if(!hasScopeAtachment(stack)) ironsights.render(1f);
        GlStateManager.popMatrix();

        /*renderSniperSilencer(0, -2.45, 6, 1.0F, stack);
        renderRedDot(-0.125, 3.0, -14, 1.0F, stack);
        renderHolo(-0.1, 3.85, -8, 0.9F, stack);
        renderScope2X(0, 4.65, -27, 0.9F, stack);
        renderScope4X(0, 3, -37, 1.0F, stack);
        renderScope8X(0, 5, -15, 0.9F, stack);
        renderScope15X(0, 5.925, -20, 0.9F, stack);*/
    }

    public ModelMini14() {
        textureWidth = 128;
        textureHeight = 128;

        mini14 = new ModelRenderer(this);
        mini14.setRotationPoint(0.0F, 24.0F, 0.0F);
        mini14.cubeList.add(new ModelBox(mini14, 0, 18, -3.0F, -16.0F, -14.0F, 6, 1, 15, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 18, -3.0F, -19.0F, 18.0F, 6, 1, 20, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 19, -3.0F, -16.0F, -32.0F, 6, 1, 18, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 74, -2.5F, -19.6719F, -37.0F, 5, 5, 5, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 74, -1.0F, -19.9063F, -37.25F, 2, 1, 6, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 74, -2.5F, -18.766F, -37.6428F, 5, 4, 1, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 72, -2.5F, -15.0F, -37.0F, 5, 1, 9, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 72, -2.5F, -15.0F, -28.0F, 5, 1, 5, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 72, -1.5F, -14.0F, -24.5F, 3, 2, 1, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 72, -1.5F, -11.5858F, -29.9142F, 3, 1, 5, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 72, -2.5F, -15.3594F, -16.7344F, 2, 2, 1, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 72, 0.5F, -15.3594F, -16.7344F, 2, 2, 1, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, -1.1874F, -26.4834F, 22.0F, 2, 1, 16, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, -1.1874F, -26.4834F, -1.0F, 2, 1, 23, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 72, 31, -1.1874F, -26.4834F, -2.0F, 2, 1, 1, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, 0.1874F, -26.4834F, 22.0F, 1, 1, 16, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, 0.1874F, -26.4834F, -1.0F, 1, 1, 23, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 72, 31, 0.1874F, -26.4834F, -2.0F, 1, 1, 1, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 66, 7, 0.866F, -23.366F, 26.0F, 1, 1, 14, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 66, 7, 0.866F, -23.366F, 40.0F, 1, 1, 14, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 12, 77, -1.0F, -23.866F, 39.208F, 2, 2, 1, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 66, 7, -1.866F, -23.366F, 26.0F, 1, 1, 14, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 66, 7, -1.866F, -23.366F, 40.0F, 1, 1, 14, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 70, 13, -0.5F, -22.0F, 26.0F, 1, 1, 14, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 70, 13, -0.5F, -22.0F, 40.0F, 1, 1, 14, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 16, -0.5F, -24.7321F, 26.0F, 1, 1, 14, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 66, 7, -0.5F, -24.7321F, 40.0F, 1, 1, 14, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, 3.0261F, -22.8191F, -14.0F, 1, 5, 15, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, 3.0261F, -22.8191F, 1.0F, 1, 3, 17, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, 3.0261F, -22.8191F, 18.0F, 1, 2, 20, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, 2.0261F, -22.8191F, -32.0F, 2, 5, 18, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, 0.0261F, -22.8191F, -32.0F, 2, 7, 3, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, -2.0261F, -22.8191F, -32.0F, 3, 7, 3, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 67, 106, -2.0F, -23.866F, -30.684F, 4, 2, 1, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, -4.0261F, -22.8191F, -14.0F, 1, 5, 15, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, -4.0261F, -22.8191F, 1.0F, 1, 3, 17, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, -4.0261F, -22.8191F, 18.0F, 1, 2, 20, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 0, 2, -4.0261F, -22.8191F, -32.0F, 2, 5, 18, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 33, 72, -5.0261F, -20.8191F, -26.0313F, 1, 1, 7, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 33, 72, 4.0261F, -20.8191F, -26.0313F, 1, 1, 7, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 33, 72, -5.0261F, -20.8191F, -39.0313F, 1, 1, 13, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 33, 72, -5.0261F, -20.8191F, -45.0313F, 1, 1, 6, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 33, 72, 4.0261F, -20.8191F, -39.0313F, 1, 1, 13, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 33, 72, 4.0261F, -20.8191F, -45.0313F, 1, 1, 6, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -5.5F, -21.3191F, -20.4472F, 11, 2, 2, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -5.5F, -21.3191F, -30.4472F, 11, 2, 2, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -5.5F, -21.3191F, -44.4472F, 11, 2, 2, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -5.5F, -21.3191F, -39.4472F, 11, 2, 2, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -3.5F, -22.3191F, -51.4472F, 7, 4, 14, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -3.5F, -22.3191F, -54.4472F, 7, 11, 3, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -1.7679F, -23.3191F, -54.4472F, 2, 1, 17, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -0.2321F, -23.3191F, -54.4472F, 2, 1, 17, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -1.5F, -11.855F, -54.4472F, 3, 4, 3, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -3.0F, -18.3191F, -51.4472F, 6, 6, 4, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 92, 30, -3.0F, -18.4471F, -47.5592F, 6, 5, 2, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 84, 30, -3.0F, -18.5351F, -45.6552F, 6, 4, 2, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -3.0F, -18.5911F, -44.0232F, 6, 3, 2, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -3.0F, -18.8191F, -42.3851F, 6, 3, 1, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 67, 20, -5.5F, -21.3191F, -25.4472F, 11, 2, 2, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 71, 19, -3.684F, -23.7588F, -14.0F, 2, 1, 12, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 71, 19, 1.684F, -23.7588F, -14.0F, 2, 1, 12, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 34, 80, -2.0F, -25.9953F, -6.0F, 4, 1, 4, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 78, 17, -2.0F, -26.4328F, -27.0F, 4, 1, 13, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 67, 106, -2.0F, -25.7453F, -30.0F, 4, 1, 3, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 97, 26, -2.5F, -27.8547F, -27.5F, 1, 1, 2, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 97, 26, 1.5F, -27.8547F, -27.5F, 1, 1, 2, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 97, 26, -2.5F, -26.8547F, -27.5F, 5, 2, 2, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 97, 26, -3.5353F, -23.991F, -27.5F, 1, 2, 2, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 97, 26, 2.5353F, -23.991F, -27.5F, 1, 2, 2, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 34, 80, 2.5156F, -24.8547F, -5.0156F, 1, 1, 1, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 34, 80, 1.8906F, -25.8547F, -5.0156F, 4, 1, 1, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 34, 80, -1.5F, -25.8703F, -14.0F, 3, 1, 8, 0.0F, true));
        mini14.cubeList.add(new ModelBox(mini14, 78, 23, -1.0F, -28.8547F, 40.252F, 2, 2, 3, 0.0F, false));
        mini14.cubeList.add(new ModelBox(mini14, 78, 23, -1.0F, -26.8547F, 41.252F, 2, 3, 2, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.5F, -11.168F, 3.408F);
        setRotationAngle(bone6, 0.0873F, 0.0F, 0.0F);
        mini14.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 0, 2, -4.5261F, -8.8191F, -2.0F, 1, 2, 18, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 0, 2, -4.5261F, -8.8191F, 16.0F, 1, 2, 17, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 0, 2, 2.5261F, -8.8191F, -2.0F, 1, 2, 18, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 0, 2, 2.5261F, -8.8191F, 16.0F, 1, 2, 17, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 0, 18, -3.5F, -5.0F, -2.0F, 6, 1, 18, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 0, 18, -3.5F, -5.0F, 16.0F, 6, 1, 17, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(-1.0F, 0.0F, 0.0F);
        setRotationAngle(bone8, 0.0F, 0.0F, -0.3491F);
        bone6.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 0, 2, -0.9812F, -7.6138F, -2.0F, 1, 3, 18, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 0, 2, -0.9812F, -7.6138F, 16.0F, 1, 3, 17, 0.0F, true));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone9, 0.0F, 0.0F, 0.3491F);
        bone6.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 0, 2, -0.0188F, -7.6138F, -2.0F, 1, 3, 18, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 0, 2, -0.0188F, -7.6138F, 16.0F, 1, 3, 17, 0.0F, false));

        bone56 = new ModelRenderer(this);
        bone56.setRotationPoint(0.0F, -17.766F, -36.1428F);
        setRotationAngle(bone56, -0.4363F, 0.0F, 0.0F);
        mini14.addChild(bone56);
        bone56.cubeList.add(new ModelBox(bone56, 0, 74, -2.5F, -1.3431F, -1.5172F, 5, 1, 1, 0.0F, false));

        bone62 = new ModelRenderer(this);
        bone62.setRotationPoint(0.0F, -13.0F, -27.3594F);
        setRotationAngle(bone62, 0.3491F, 0.0F, 0.0F);
        mini14.addChild(bone62);
        bone62.cubeList.add(new ModelBox(bone62, 41, 103, -1.0F, -1.1719F, -0.1406F, 2, 2, 1, 0.0F, false));

        bone42 = new ModelRenderer(this);
        bone42.setRotationPoint(0.0F, -10.0F, -24.5F);
        setRotationAngle(bone42, -0.7854F, 0.0F, 0.0F);
        mini14.addChild(bone42);
        bone42.cubeList.add(new ModelBox(bone42, 0, 72, -1.5F, -2.1213F, -1.7071F, 3, 2, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -13.3474F, -16.7384F);
        setRotationAngle(bone4, -0.2618F, 0.0F, 0.0F);
        mini14.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 49, 82, -0.5F, -1.8036F, 0.015F, 1, 3, 1, 0.0F, true));

        bone57 = new ModelRenderer(this);
        bone57.setRotationPoint(0.0F, -11.5F, -31.0F);
        setRotationAngle(bone57, -0.3491F, 0.0F, 0.0F);
        mini14.addChild(bone57);
        bone57.cubeList.add(new ModelBox(bone57, 0, 74, -2.5F, -3.2746F, -3.5153F, 5, 10, 5, 0.0F, false));
        bone57.cubeList.add(new ModelBox(bone57, 0, 74, -2.0F, -4.2746F, -4.0153F, 4, 11, 6, 0.0F, false));

        bone58 = new ModelRenderer(this);
        bone58.setRotationPoint(0.0F, -11.5F, -31.0F);
        setRotationAngle(bone58, -0.0873F, 0.0F, 0.0F);
        mini14.addChild(bone58);

        bone55 = new ModelRenderer(this);
        bone55.setRotationPoint(0.0F, -14.5F, -36.5F);
        setRotationAngle(bone55, 0.6981F, 0.0F, 0.0F);
        mini14.addChild(bone55);
        bone55.cubeList.add(new ModelBox(bone55, 0, 74, -2.5F, -0.9384F, -0.7044F, 5, 1, 1, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 0, 74, -2.0F, 1.6575F, 1.421F, 4, 1, 1, 0.0F, false));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(-0.0F, -18.4754F, 52.6563F);
        setRotationAngle(bone25, 0.0F, 0.0F, 0.5236F);
        mini14.addChild(bone25);

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-0.0F, -18.4754F, 52.6563F);
        setRotationAngle(bone26, 0.0F, 0.0F, 1.0472F);
        mini14.addChild(bone26);

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-0.0F, -18.4754F, 52.6563F);
        setRotationAngle(bone27, 0.0F, 0.0F, 1.309F);
        mini14.addChild(bone27);

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-0.0F, -18.4754F, 52.6563F);
        setRotationAngle(bone28, 0.0F, 0.0F, 1.8326F);
        mini14.addChild(bone28);

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(-0.0F, -18.4754F, 52.6563F);
        setRotationAngle(bone29, 0.0F, 0.0F, 2.3562F);
        mini14.addChild(bone29);

        bone53 = new ModelRenderer(this);
        bone53.setRotationPoint(-1.0F, -27.7321F, 60.0F);
        setRotationAngle(bone53, 0.0F, 0.0F, -0.3491F);
        mini14.addChild(bone53);

        bone54 = new ModelRenderer(this);
        bone54.setRotationPoint(1.0F, -27.7321F, 60.0F);
        setRotationAngle(bone54, 0.0F, 0.0F, 0.3491F);
        mini14.addChild(bone54);

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(1.0F, -15.5F, 41.5F);
        setRotationAngle(bone22, 0.0F, 0.0F, -0.5236F);
        mini14.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 66, 7, 2.317F, -6.0131F, -15.5F, 1, 1, 14, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 66, 7, 2.317F, -6.0131F, -1.5F, 1, 1, 14, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 66, 7, 0.951F, -7.3792F, -15.5F, 1, 1, 14, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 66, 7, 0.951F, -7.3792F, -1.5F, 1, 1, 14, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 66, 7, 3.683F, -7.3792F, -15.5F, 1, 1, 14, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 66, 7, 3.683F, -7.3792F, -1.5F, 1, 1, 14, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 66, 7, 2.317F, -8.7452F, -15.5F, 1, 1, 14, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 66, 7, 2.317F, -8.7452F, -1.5F, 1, 1, 14, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(-1.0F, -15.5F, 41.5F);
        setRotationAngle(bone23, 0.0F, 0.0F, 0.5236F);
        mini14.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 66, 7, -3.317F, -6.0131F, -15.5F, 1, 1, 14, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 66, 7, -3.317F, -6.0131F, -1.5F, 1, 1, 14, 0.0F, true));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(-1.0F, -15.5F, 41.5F);
        setRotationAngle(bone24, 0.0F, 0.0F, 0.5236F);
        mini14.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 66, 7, -1.951F, -7.3792F, -15.5F, 1, 1, 14, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 66, 7, -1.951F, -7.3792F, -1.5F, 1, 1, 14, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 66, 7, -4.683F, -7.3792F, -15.5F, 1, 1, 14, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 66, 7, -4.683F, -7.3792F, -1.5F, 1, 1, 14, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 67, 16, -3.317F, -8.7452F, -15.5F, 1, 1, 14, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 66, 7, -3.317F, -8.7452F, -1.5F, 1, 1, 14, 0.0F, true));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.5F, 0.0F, 0.0F);
        setRotationAngle(bone14, 0.0F, 0.0F, -0.4363F);
        mini14.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 0, 2, 7.6631F, -24.7152F, -1.0F, 2, 2, 15, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 72, 31, 7.6631F, -24.7152F, -2.0F, 2, 2, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 0, 2, 7.6631F, -24.7152F, 14.0F, 2, 1, 24, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(-0.5F, 0.0F, 0.0F);
        setRotationAngle(bone15, 0.0F, 0.0F, 0.4363F);
        mini14.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 0, 2, -9.6631F, -24.7152F, -1.0F, 2, 2, 15, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 72, 31, -9.6631F, -24.7152F, -2.0F, 2, 2, 1, 0.0F, true));
        bone15.cubeList.add(new ModelBox(bone15, 0, 2, -9.6631F, -24.7152F, 14.0F, 2, 1, 24, 0.0F, true));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, -8.3191F, -52.9472F);
        setRotationAngle(bone, 0.0F, 0.0F, -0.5236F);
        mini14.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 67, 20, -1.5311F, -4.3481F, -1.5F, 2, 4, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 20, 3.9689F, -13.8744F, -1.5F, 2, 2, 17, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -8.3191F, -52.9472F);
        setRotationAngle(bone5, 0.0F, 0.0F, 0.5236F);
        mini14.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 67, 20, -0.4689F, -4.3481F, -1.5F, 2, 4, 3, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 67, 20, -5.9689F, -13.8744F, -1.5F, 2, 2, 17, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, -15.3191F, -49.4472F);
        setRotationAngle(bone7, 0.5236F, 0.0F, 0.0F);
        mini14.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 67, 20, -3.0F, 2.5981F, 0.2321F, 6, 1, 7, 0.0F, false));

        bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(0.0F, -26.3547F, -27.0F);
        setRotationAngle(bone40, 0.0F, 0.0F, 0.2618F);
        mini14.addChild(bone40);
        bone40.cubeList.add(new ModelBox(bone40, 97, 26, -2.803F, -0.8018F, -0.5F, 1, 4, 2, 0.0F, false));

        bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(0.0F, -26.3547F, -27.0F);
        setRotationAngle(bone39, 0.0F, 0.0F, -0.2618F);
        mini14.addChild(bone39);
        bone39.cubeList.add(new ModelBox(bone39, 97, 26, 1.803F, -0.8018F, -0.5F, 1, 4, 2, 0.0F, true));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(0.0F, -25.2453F, -31.5F);
        setRotationAngle(bone38, 1.2217F, 0.0F, 0.0F);
        mini14.addChild(bone38);
        bone38.cubeList.add(new ModelBox(bone38, 67, 106, -2.0F, 1.2385F, -1.0171F, 4, 1, 2, 0.0F, true));
        bone38.cubeList.add(new ModelBox(bone38, 67, 106, -2.5F, 1.6073F, -3.0302F, 5, 1, 3, 0.0F, true));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(4.5625F, -24.3547F, -4.5156F);
        setRotationAngle(bone33, 0.0F, 0.0F, -0.5236F);
        mini14.addChild(bone33);
        bone33.cubeList.add(new ModelBox(bone33, 34, 80, -1.1566F, -1.0904F, -0.5F, 2, 1, 1, 0.0F, true));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(6.8906F, -25.3547F, -4.5156F);
        setRotationAngle(bone34, 0.0F, -0.4363F, 0.0F);
        mini14.addChild(bone34);
        bone34.cubeList.add(new ModelBox(bone34, 34, 80, -1.1176F, -0.5F, -0.0305F, 1, 1, 1, 0.0F, true));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(-2.5F, -25.4953F, -6.5F);
        setRotationAngle(bone30, 0.0F, 0.0F, 0.4363F);
        mini14.addChild(bone30);
        bone30.cubeList.add(new ModelBox(bone30, 34, 80, 0.2418F, -0.6645F, 0.5F, 1, 3, 4, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 78, 17, 0.0569F, -1.061F, -20.5F, 1, 4, 13, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 67, 106, 0.3475F, -0.4379F, -23.5F, 1, 4, 3, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 34, 80, 0.2418F, 0.8355F, -7.5F, 1, 1, 8, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 34, 80, 4.0161F, -1.3908F, -7.5F, 1, 1, 8, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 34, 80, 0.7478F, -0.7625F, -7.5F, 1, 2, 8, 0.0F, true));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(2.5F, -25.4953F, -6.5F);
        setRotationAngle(bone31, 0.0F, 0.0F, -0.4363F);
        mini14.addChild(bone31);
        bone31.cubeList.add(new ModelBox(bone31, 34, 80, -1.2418F, -0.6645F, 0.5F, 1, 3, 4, 0.0F, false));
        bone31.cubeList.add(new ModelBox(bone31, 78, 17, -1.0569F, -1.061F, -20.5F, 1, 4, 13, 0.0F, false));
        bone31.cubeList.add(new ModelBox(bone31, 67, 106, -1.3475F, -0.4379F, -23.5F, 1, 4, 3, 0.0F, false));
        bone31.cubeList.add(new ModelBox(bone31, 34, 80, -1.2418F, 0.8355F, -7.5F, 1, 1, 8, 0.0F, false));
        bone31.cubeList.add(new ModelBox(bone31, 34, 80, -5.0161F, -1.3908F, -7.5F, 1, 1, 8, 0.0F, false));
        bone31.cubeList.add(new ModelBox(bone31, 34, 80, -1.7478F, -0.7625F, -7.5F, 1, 2, 8, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.5F, 0.0F, 0.0F);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.3491F);
        mini14.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 0, 2, 1.8414F, -18.2925F, -14.0F, 1, 3, 15, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 2, 2.8674F, -21.1115F, 18.0F, 1, 3, 20, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 2, 1.8414F, -18.2925F, -32.0F, 2, 3, 18, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 18, 10.118F, -23.2369F, -1.0F, 1, 3, 19, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 72, 31, 10.118F, -23.2369F, -2.0F, 1, 3, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 18, 10.118F, -23.2369F, 18.0F, 1, 3, 20, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 71, 19, 10.118F, -21.2369F, -14.0F, 1, 1, 12, 0.0F, false));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(10.618F, -20.7369F, -15.5F);
        setRotationAngle(bone35, 0.0F, 0.7854F, 0.0F);
        bone2.addChild(bone35);
        bone35.cubeList.add(new ModelBox(bone35, 71, 19, -1.7071F, -0.5F, 0.4142F, 1, 1, 1, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(-0.5F, 0.0F, 0.0F);
        setRotationAngle(bone3, 0.0F, 0.0F, 0.3491F);
        mini14.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 0, 2, -2.8414F, -18.2925F, -14.0F, 1, 3, 15, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 0, 2, -3.8674F, -21.1115F, 18.0F, 1, 3, 20, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 0, 2, -3.8414F, -18.2925F, -32.0F, 2, 3, 18, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 0, 2, -11.118F, -23.2369F, -1.0F, 1, 3, 19, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 72, 31, -11.118F, -23.2369F, -2.0F, 1, 3, 1, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 0, 2, -11.118F, -23.2369F, 18.0F, 1, 3, 20, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 71, 19, -11.118F, -21.2369F, -14.0F, 1, 1, 12, 0.0F, false));

        bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(-10.618F, -20.7369F, -14.5F);
        setRotationAngle(bone37, 0.0F, -0.7854F, 0.0F);
        bone3.addChild(bone37);
        bone37.cubeList.add(new ModelBox(bone37, 71, 19, -0.0F, -0.5F, -0.2929F, 1, 1, 1, 0.0F, false));

        rail3 = new ModelRenderer(this);
        rail3.setRotationPoint(-1.3906F, -8.0F, 1.1406F);
        setRotationAngle(rail3, 0.0F, 0.0F, 1.5708F);
        mini14.addChild(rail3);
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.9375F, -2.3906F, 35.5781F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.436F, -3.2575F, 34.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.436F, -3.2575F, 32.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.436F, -3.2575F, 30.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.436F, -3.2575F, 28.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.436F, -3.2575F, 26.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.436F, -3.2575F, 24.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.436F, -3.2575F, 22.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.437F, -1.5255F, 34.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.437F, -1.5255F, 32.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.437F, -1.5255F, 30.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.437F, -1.5255F, 28.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.437F, -1.5255F, 26.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.437F, -1.5255F, 24.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.437F, -1.5255F, 22.5781F, 0, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.9375F, -2.3906F, 31.5781F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.9375F, -2.3906F, 27.5781F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.9375F, -2.3906F, 33.5781F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.9375F, -2.3906F, 29.5781F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.9375F, -2.3906F, 25.5781F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -19.9375F, -2.3906F, 23.5781F, 1, 2, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 0, 78, -18.9375F, -2.8906F, 22.5781F, 1, 3, 14, 0.0F, true));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(-4.0261F, -7.3191F, 0.5F);
        setRotationAngle(bone16, 0.0F, 0.0F, 0.5236F);
        rail3.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 35.0781F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 34.0781F, 1, 0, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 32.0781F, 1, 0, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 30.0781F, 1, 0, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 28.0781F, 1, 0, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 26.0781F, 1, 0, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 24.0781F, 1, 0, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 22.0781F, 1, 0, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 31.0781F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 27.0781F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 33.0781F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 29.0781F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 25.0781F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 0, 78, -11.3155F, 11.2239F, 23.0781F, 1, 1, 1, 0.0F, true));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(-4.0261F, -3.3191F, 0.5F);
        setRotationAngle(bone17, 0.0F, 0.0F, -0.5236F);
        rail3.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -5.4196F, 35.0781F, 1, 1, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -4.4196F, 34.0781F, 1, 0, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -4.4196F, 32.0781F, 1, 0, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -4.4196F, 30.0781F, 1, 0, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -4.4196F, 28.0781F, 1, 0, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -4.4196F, 26.0781F, 1, 0, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -4.4196F, 24.0781F, 1, 0, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -4.4196F, 22.0781F, 1, 0, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -5.4196F, 31.0781F, 1, 1, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -5.4196F, 27.0781F, 1, 1, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -5.4196F, 33.0781F, 1, 1, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -5.4196F, 29.0781F, 1, 1, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -5.4196F, 25.0781F, 1, 1, 1, 0.0F, true));
        bone17.cubeList.add(new ModelBox(bone17, 0, 78, -15.2439F, -5.4196F, 23.0781F, 1, 1, 1, 0.0F, true));

        rail4 = new ModelRenderer(this);
        rail4.setRotationPoint(-1.3906F, -8.0F, -12.8594F);
        setRotationAngle(rail4, 0.0F, 0.0F, 1.5708F);
        mini14.addChild(rail4);
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.9375F, -2.3906F, 35.5781F, 1, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.436F, -3.2575F, 34.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.436F, -3.2575F, 32.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.436F, -3.2575F, 30.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.436F, -3.2575F, 28.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.436F, -3.2575F, 26.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.436F, -3.2575F, 24.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.436F, -3.2575F, 22.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.437F, -1.5255F, 34.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.437F, -1.5255F, 32.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.437F, -1.5255F, 30.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.437F, -1.5255F, 28.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.437F, -1.5255F, 26.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.437F, -1.5255F, 24.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.437F, -1.5255F, 22.5781F, 0, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.9375F, -2.3906F, 31.5781F, 1, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.9375F, -2.3906F, 27.5781F, 1, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.9375F, -2.3906F, 33.5781F, 1, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.9375F, -2.3906F, 29.5781F, 1, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.9375F, -2.3906F, 25.5781F, 1, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -19.9375F, -2.3906F, 23.5781F, 1, 2, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 0, 78, -18.9375F, -2.8906F, 22.5781F, 1, 3, 14, 0.0F, true));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(-4.0261F, -7.3191F, 0.5F);
        setRotationAngle(bone18, 0.0F, 0.0F, 0.5236F);
        rail4.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 35.0781F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 34.0781F, 1, 0, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 32.0781F, 1, 0, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 30.0781F, 1, 0, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 28.0781F, 1, 0, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 26.0781F, 1, 0, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 24.0781F, 1, 0, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 22.0781F, 1, 0, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 31.0781F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 27.0781F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 33.0781F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 29.0781F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 25.0781F, 1, 1, 1, 0.0F, true));
        bone18.cubeList.add(new ModelBox(bone18, 0, 78, -11.3155F, 11.2239F, 23.0781F, 1, 1, 1, 0.0F, true));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(-4.0261F, -3.3191F, 0.5F);
        setRotationAngle(bone19, 0.0F, 0.0F, -0.5236F);
        rail4.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -5.4196F, 35.0781F, 1, 1, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -4.4196F, 34.0781F, 1, 0, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -4.4196F, 32.0781F, 1, 0, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -4.4196F, 30.0781F, 1, 0, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -4.4196F, 28.0781F, 1, 0, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -4.4196F, 26.0781F, 1, 0, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -4.4196F, 24.0781F, 1, 0, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -4.4196F, 22.0781F, 1, 0, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -5.4196F, 31.0781F, 1, 1, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -5.4196F, 27.0781F, 1, 1, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -5.4196F, 33.0781F, 1, 1, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -5.4196F, 29.0781F, 1, 1, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -5.4196F, 25.0781F, 1, 1, 1, 0.0F, true));
        bone19.cubeList.add(new ModelBox(bone19, 0, 78, -15.2439F, -5.4196F, 23.0781F, 1, 1, 1, 0.0F, true));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, -25.3547F, 42.252F);
        setRotationAngle(bone12, -0.6109F, 0.0F, 0.0F);
        mini14.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 78, 23, -1.0F, -0.8928F, -2.8992F, 2, 5, 2, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.0F, -28.3547F, -27.0F);
        setRotationAngle(bone13, 0.0F, 0.0F, -1.0472F);
        mini14.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 78, 23, 0.067F, 0.116F, 67.252F, 1, 1, 3, 0.0F, false));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(0.0F, -28.3547F, -27.0F);
        setRotationAngle(bone32, 0.0F, 0.0F, 1.0472F);
        mini14.addChild(bone32);
        bone32.cubeList.add(new ModelBox(bone32, 78, 23, -1.067F, 0.116F, 67.252F, 1, 1, 3, 0.0F, true));

        rail5 = new ModelRenderer(this);
        rail5.setRotationPoint(-1.3906F, -8.0F, -26.8594F);
        setRotationAngle(rail5, 0.0F, 0.0F, 1.5708F);
        mini14.addChild(rail5);
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.9375F, -2.3906F, 35.5781F, 1, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.436F, -3.2575F, 34.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.436F, -3.2575F, 32.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.436F, -3.2575F, 28.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.436F, -3.2575F, 30.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.436F, -3.2575F, 26.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.436F, -3.2575F, 2.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.436F, -3.2575F, 4.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.436F, -3.2575F, 6.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.436F, -3.2575F, 8.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.436F, -3.2575F, 10.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.437F, -1.5255F, 34.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.437F, -1.5255F, 32.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.437F, -1.5255F, 28.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.437F, -1.5255F, 30.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.437F, -1.5255F, 26.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.437F, -1.5255F, 2.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.437F, -1.5255F, 4.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.437F, -1.5255F, 6.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.437F, -1.5255F, 8.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.437F, -1.5255F, 10.5781F, 0, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.9375F, -2.3906F, 31.5781F, 1, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.9375F, -2.3906F, 27.5781F, 1, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.9375F, -2.3906F, 33.5781F, 1, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.9375F, -2.3906F, 29.5781F, 1, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.9375F, -2.3906F, 25.5781F, 1, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.9375F, -2.3906F, 1.5781F, 1, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.9375F, -2.3906F, 3.5781F, 1, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.9375F, -2.3906F, 5.5781F, 1, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.9375F, -2.3906F, 7.5781F, 1, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.9375F, -2.3906F, 9.5781F, 1, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -19.9375F, -2.3906F, 11.5781F, 1, 2, 1, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -18.9375F, -2.8906F, 25.5781F, 1, 3, 11, 0.0F, true));
        rail5.cubeList.add(new ModelBox(rail5, 0, 78, -18.9375F, -2.8906F, 1.5781F, 1, 3, 11, 0.0F, true));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(-4.0261F, -7.3191F, 0.5F);
        setRotationAngle(bone20, 0.0F, 0.0F, 0.5236F);
        rail5.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 35.0781F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 34.0781F, 1, 0, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 32.0781F, 1, 0, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 28.0781F, 1, 0, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 30.0781F, 1, 0, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 26.0781F, 1, 0, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 2.0781F, 1, 0, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 4.0781F, 1, 0, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 6.0781F, 1, 0, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 8.0781F, 1, 0, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 10.0781F, 1, 0, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 31.0781F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 27.0781F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 33.0781F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 29.0781F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 25.0781F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 1.0781F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 3.0781F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 5.0781F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 7.0781F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 9.0781F, 1, 1, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 0, 78, -11.3155F, 11.2239F, 11.0781F, 1, 1, 1, 0.0F, true));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-4.0261F, -3.3191F, 0.5F);
        setRotationAngle(bone21, 0.0F, 0.0F, -0.5236F);
        rail5.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -5.4196F, 35.0781F, 1, 1, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -4.4196F, 34.0781F, 1, 0, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -4.4196F, 32.0781F, 1, 0, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -4.4196F, 28.0781F, 1, 0, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -4.4196F, 30.0781F, 1, 0, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -4.4196F, 26.0781F, 1, 0, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -4.4196F, 2.0781F, 1, 0, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -4.4196F, 4.0781F, 1, 0, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -4.4196F, 6.0781F, 1, 0, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -4.4196F, 8.0781F, 1, 0, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -4.4196F, 10.0781F, 1, 0, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -5.4196F, 31.0781F, 1, 1, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -5.4196F, 27.0781F, 1, 1, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -5.4196F, 33.0781F, 1, 1, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -5.4196F, 29.0781F, 1, 1, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -5.4196F, 25.0781F, 1, 1, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -5.4196F, 1.0781F, 1, 1, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -5.4196F, 3.0781F, 1, 1, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -5.4196F, 5.0781F, 1, 1, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -5.4196F, 7.0781F, 1, 1, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -5.4196F, 9.0781F, 1, 1, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 0, 78, -15.2439F, -5.4196F, 11.0781F, 1, 1, 1, 0.0F, true));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.5F, 15.832F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -2.0F, -15.3594F, -15.5F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, -13.3594F, -15.5F, 5, 1, 8, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, -8.3594F, -15.5F, 5, 2, 8, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, 0.6406F, -15.5F, 5, 2, 8, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, -13.3594F, -7.5F, 5, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, -8.3594F, -7.5F, 5, 2, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, 0.6406F, -7.5F, 5, 2, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, -12.3594F, -10.5F, 5, 4, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, -6.3594F, -10.5F, 5, 7, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, -12.3594F, -15.5F, 5, 4, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, -6.3594F, -15.5F, 5, 7, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, -12.3594F, -5.5F, 5, 4, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, -6.3594F, -5.5F, 5, 7, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -2.5F, -12.3594F, -12.5F, 4, 4, 7, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -2.5F, -6.3594F, -12.5F, 4, 7, 7, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 13, 103, -2.0F, -16.4844F, -14.5F, 3, 3, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 13, 103, -2.0F, -16.4844F, -11.5F, 3, 3, 4, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 48, 106, -1.5F, -15.9844F, -4.5F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 48, 106, -2.0F, -15.9844F, -5.5F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 12, 106, -2.0F, -15.9844F, -7.5F, 3, 2, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 48, 106, -1.5F, -16.4844F, -5.5F, 2, 3, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 12, 106, -1.5F, -16.4844F, -7.5F, 2, 3, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 13, 103, -1.5F, -15.9844F, -12.5F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -2.0F, -14.3594F, -3.5F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, -3.0F, -15.3594F, -15.5F, 1, 2, 13, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 69, 36, 1.0F, -15.3594F, -15.5F, 1, 2, 13, 0.0F, false));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone36, 0.1745F, 0.0F, 0.0F);
        magazine.addChild(bone36);
        bone36.cubeList.add(new ModelBox(bone36, 69, 36, -3.0F, 8.909F, -7.7231F, 5, 2, 5, 0.0F, false));
        bone36.cubeList.add(new ModelBox(bone36, 69, 36, -3.0F, 8.909F, -15.7231F, 5, 2, 8, 0.0F, false));
        bone36.cubeList.add(new ModelBox(bone36, 69, 36, -3.0F, 1.909F, -5.7231F, 5, 7, 3, 0.0F, false));
        bone36.cubeList.add(new ModelBox(bone36, 69, 36, -3.0F, -0.091F, -7.7231F, 5, 2, 5, 0.0F, false));
        bone36.cubeList.add(new ModelBox(bone36, 69, 36, -2.5F, 1.909F, -12.7231F, 4, 7, 7, 0.0F, false));
        bone36.cubeList.add(new ModelBox(bone36, 69, 36, -3.0F, 1.909F, -10.7231F, 5, 7, 3, 0.0F, false));
        bone36.cubeList.add(new ModelBox(bone36, 69, 36, -3.0F, -0.091F, -15.7231F, 5, 2, 8, 0.0F, false));
        bone36.cubeList.add(new ModelBox(bone36, 69, 36, -3.0F, 1.909F, -15.7231F, 5, 7, 3, 0.0F, false));

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(0.0F, 24.0F, 0.0F);
        ironsights.cubeList.add(new ModelBox(ironsights, 41, 80, -1.0F, -28.8547F, -26.748F, 2, 2, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 41, 80, -1.0F, -31.3188F, -26.748F, 2, 1, 1, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, -28.3547F, -27.0F);
        setRotationAngle(bone11, 0.0F, 0.0F, 1.0472F);
        ironsights.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 41, 80, -2.067F, 0.116F, 0.252F, 2, 1, 1, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 41, 80, -2.067F, -2.3481F, 0.252F, 2, 1, 1, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, -28.3547F, -27.0F);
        setRotationAngle(bone10, 0.0F, 0.0F, -1.0472F);
        ironsights.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 41, 80, 0.067F, 0.116F, 0.252F, 2, 1, 1, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 41, 80, 0.067F, -2.3481F, 0.252F, 2, 1, 1, 0.0F, false));
        this.initAnimations();
    }
}
