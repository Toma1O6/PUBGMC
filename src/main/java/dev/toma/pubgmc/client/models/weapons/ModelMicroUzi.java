package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.HeldAnimation;
import dev.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.client.models.ModelGun;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelMicroUzi extends ModelGun {

    private final ModelRenderer magazine;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer bone5;
    private final ModelRenderer bone7;
    private final ModelRenderer bone6;
    private final ModelRenderer bone10;
    private final ModelRenderer bone8;
    private final ModelRenderer bone9;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;

    @Override
    public String textureName() {
        return "uzi";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.21f, 0.23f);
        initAimingAnimationStates(0.21f, 0.125f, 0.12f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
        reloadAnimation = new ReloadAnimation(magazine, ReloadAnimation.ReloadStyle.MAGAZINE).withSpeed(1.2F);
    }

    @Override
    public void render(ItemStack stack) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            GlStateManager.pushMatrix();
            {
                renderUzi(stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderUzi(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultSMGTransform();
        GlStateManager.translate(0.0, 0.85, -14.0);
        bone.render(1f);
        magazine.render(1f);
        GlStateManager.popMatrix();

        renderSMGSilencer(0, -6, 0, 1.2F, stack);
        renderRedDot(-0.05, -6.25, -10, 1.2F, stack);
        renderHolo(-0.1, -2.475, -6, 1.1F, stack);
    }

    public ModelMicroUzi() {
        textureWidth = 128;
        textureHeight = 128;

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 24.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 0, 1, -2.0F, -11.0F, 0.0F, 4, 22, 6, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 5, -1.0F, -13.0F, 5.0F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 104, -1.0F, -13.5547F, 4.0F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 104, -1.0F, -13.5547F, 1.6875F, 2, 2, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 104, -0.6F, -13.1547F, 1.1211F, 1, 1, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 104, -0.4F, -13.1547F, 1.1211F, 1, 1, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 4, 104, -0.6F, -12.9547F, 1.1211F, 1, 1, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 4, 104, -0.4F, -12.9547F, 1.1211F, 1, 1, 3, 0.0F, true));
        magazine.cubeList.add(new ModelBox(magazine, 0, 5, -1.0F, -13.0F, 0.0F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 5, 1.0F, -14.0F, 0.0F, 1, 3, 6, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 5, -2.0F, -14.0F, 0.0F, 1, 3, 6, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 95, 30, -3.0F, -14.0F, 0.0F, 1, 9, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 95, 30, 2.0F, -14.0F, 0.0F, 1, 9, 6, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -3.0F, -15.0F, 0.0F, 6, 1, 7, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 19, -3.0F, -17.0F, -8.0F, 6, 2, 21, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 19, -2.0F, -17.0F, -8.0F, 4, 3, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 19, -2.0F, -16.0F, -9.0F, 4, 2, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 19, -1.2071F, -19.1915F, -14.0F, 1, 1, 6, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 19, 0.2071F, -19.1915F, -14.0F, 1, 1, 6, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 19, -0.5F, -18.4844F, -14.0F, 1, 1, 6, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 69, 19, -0.5F, -19.8986F, -14.0F, 1, 1, 6, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 72, 25, -3.0F, -21.0F, -8.0F, 6, 1, 21, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 80, 16, -0.9659F, -21.2588F, 7.0F, 3, 1, 6, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 80, 16, -2.0F, -23.2588F, 11.0F, 4, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 80, 16, -2.0F, -23.2588F, -7.707F, 4, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 80, 16, 1.0F, -24.2588F, 11.0F, 1, 1, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 80, 16, 1.0F, -24.2588F, -7.707F, 1, 1, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 80, 16, -2.0F, -24.2588F, 11.0F, 1, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 80, 16, -2.0F, -24.2588F, -7.707F, 1, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 80, 16, -0.9659F, -21.2588F, -8.0F, 3, 1, 6, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 74, 19, -2.0341F, -21.2588F, 7.0F, 2, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 74, 19, -2.0341F, -21.2588F, -8.0F, 2, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 74, 19, -2.0341F, -21.2588F, -2.0F, 1, 1, 9, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 74, 19, 1.0341F, -21.2588F, -2.0F, 1, 1, 9, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 39, 11, -1.0F, -21.083F, -2.0F, 2, 1, 9, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 39, 11, -1.0F, -23.083F, -1.2734F, 2, 2, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 39, 11, -1.0F, -22.6604F, -0.3671F, 2, 2, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -3.0F, -20.0F, 5.0F, 6, 3, 8, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -2.0F, -20.6719F, 12.375F, 4, 5, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 95, 30, 1.0F, -23.6719F, 12.375F, 1, 3, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 95, 30, 1.0F, -23.6719F, -6.332F, 1, 3, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -2.0F, -23.6719F, 12.375F, 1, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -2.0F, -23.6719F, -6.332F, 1, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 95, 30, 1.0F, -23.6719F, 10.625F, 1, 3, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 95, 30, 1.0F, -23.6719F, -8.082F, 1, 8, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -2.0F, -23.6719F, 10.625F, 1, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -2.0F, -23.6719F, -8.082F, 1, 8, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -3.0F, -20.0F, -8.0F, 6, 3, 8, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -1.0F, -20.0F, 0.0F, 4, 3, 5, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 48, 43, -2.7539F, -20.0F, 0.0F, 1, 3, 5, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -2.0F, -15.0F, -1.0F, 4, 10, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -2.0F, -10.0F, 6.0F, 4, 5, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -2.2929F, -14.0F, 5.7071F, 2, 4, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 95, 30, -0.7071F, -14.0F, 5.7071F, 3, 4, 1, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone2, 0.0F, -0.7854F, 0.0F);
        bone.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 95, 30, -2.1213F, -15.0F, 0.7071F, 1, 10, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 95, 30, 0.7071F, -15.0F, -2.1213F, 1, 10, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 95, 30, 1.1213F, -15.0F, -2.1213F, 1, 10, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 95, 30, 5.364F, -14.0F, 2.1213F, 1, 9, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 95, 30, 5.364F, -10.0F, 2.5355F, 1, 5, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 95, 30, 2.5355F, -10.0F, 5.364F, 1, 5, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 95, 30, -2.1213F, -15.0F, 1.1213F, 1, 10, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 95, 30, 2.1213F, -14.0F, 5.364F, 1, 9, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone5, 0.3491F, 0.0F, 0.0F);
        bone.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 95, 30, -3.0F, -11.7616F, 11.3661F, 6, 1, 3, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 95, 30, -2.0F, -11.6078F, 11.1605F, 4, 1, 4, 0.0F, true));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(-0.5F, 0.0F, 0.0F);
        setRotationAngle(bone7, 0.0F, 0.0F, -0.7854F);
        bone.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 69, 19, 13.0704F, -12.6562F, -14.0F, 1, 1, 6, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 69, 19, 13.7775F, -13.3633F, -14.0F, 1, 1, 6, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 69, 19, 13.0704F, -14.0704F, -14.0F, 1, 1, 6, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 69, 19, 12.3633F, -13.3633F, -14.0F, 1, 1, 6, 0.0F, true));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone6, 0.6981F, 0.0F, 0.0F);
        bone.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 69, 19, -2.0F, -15.5813F, 4.4028F, 4, 1, 2, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-1.0341F, 0.0F, 0.0F);
        setRotationAngle(bone10, -0.4363F, 0.0F, 0.0F);
        bone.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 39, 11, 0.0341F, -20.8048F, -10.0031F, 2, 2, 1, 0.0F, true));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone8, 0.0F, 0.0F, 0.2618F);
        bone.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 72, 25, -3.5374F, -21.0609F, -8.0F, 1, 1, 21, 0.0F, true));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone9, 0.0F, 0.0F, -0.2618F);
        bone.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 72, 25, 2.5374F, -21.0609F, -8.0F, 1, 1, 21, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 5.957F);
        setRotationAngle(bone3, 0.1745F, 0.0F, 0.0F);
        bone.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 95, 30, -2.0F, -11.667F, 1.7636F, 4, 2, 1, 0.0F, false));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -2.0F, 5.957F);
        setRotationAngle(bone4, -0.4363F, 0.0F, 0.0F);
        bone.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 95, 30, -2.0F, -11.3165F, -5.1262F, 4, 1, 1, 0.0F, false));
        this.initAnimations();
    }

    public void setRotationAngle(ModelRenderer r, float x, float y, float z) {
        r.rotateAngleX = x;
        r.rotateAngleY = y;
        r.rotateAngleZ = z;
    }
}
