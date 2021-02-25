package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import dev.toma.pubgmc.client.models.ModelGun;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelGroza extends ModelGun {

    private final ModelRenderer bone;
    private final ModelRenderer bone9;
    private final ModelRenderer bone10;
    private final ModelRenderer bone13;
    private final ModelRenderer bone11;
    private final ModelRenderer bone12;
    private final ModelRenderer bone3;
    private final ModelRenderer bone16;
    private final ModelRenderer bone31;
    private final ModelRenderer bone15;
    private final ModelRenderer bone32;
    private final ModelRenderer bone14;
    private final ModelRenderer dust_cover;
    private final ModelRenderer bone29;
    private final ModelRenderer bone28;
    private final ModelRenderer bone17;
    private final ModelRenderer bone22;
    private final ModelRenderer bone21;
    private final ModelRenderer bone46;
    private final ModelRenderer bone48;
    private final ModelRenderer bone47;
    private final ModelRenderer bone45;
    private final ModelRenderer bone43;
    private final ModelRenderer bone44;
    private final ModelRenderer bone39;
    private final ModelRenderer bone40;
    private final ModelRenderer bone41;
    private final ModelRenderer bone42;
    private final ModelRenderer bone51;
    private final ModelRenderer bone52;
    private final ModelRenderer bone33;
    private final ModelRenderer bone34;
    private final ModelRenderer bone35;
    private final ModelRenderer bone36;
    private final ModelRenderer bone37;
    private final ModelRenderer bone53;
    private final ModelRenderer bone54;
    private final ModelRenderer bone57;
    private final ModelRenderer bone55;
    private final ModelRenderer bone56;
    private final ModelRenderer bone30;
    private final ModelRenderer bone27;
    private final ModelRenderer bone18;
    private final ModelRenderer bone20;
    private final ModelRenderer bolt;
    private final ModelRenderer bone23;
    private final ModelRenderer bone26;
    private final ModelRenderer bone24;
    private final ModelRenderer bone19;
    private final ModelRenderer bone38;
    private final ModelRenderer bone25;
    private final ModelRenderer bone2;
    private final ModelRenderer magazine;
    private final ModelRenderer bone4;
    private final ModelRenderer bone49;
    private final ModelRenderer bone50;
    private final ModelRenderer bone5;
    private final ModelRenderer bone6;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;

    @Override
    public String textureName() {
        return "groza";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.135f, 0.28f);
        initAimingAnimationStates(0.135f, 0.0575f, 0.03f);
        reloadAnimation = new ReloadAnimation(magazine, ReloadStyle.MAGAZINE).withSpeed(1.2F);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(ItemStack stack) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            GlStateManager.pushMatrix();
            {
                renderGroza(stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderGroza(ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultARTransform();
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
        GlStateManager.translate(-0.15, 40.675, 11);

        bone.render(1f);
        magazine.render(1f);
        GlStateManager.popMatrix();
        renderARSilencer(0, 1, 45, 1f, stack);
        renderRedDot(0, -12, -29, 0.7F, stack);
        renderHolo(-0.125, 3, -14, 0.7F, stack);
        renderScope2X(0, -3, -15, 0.8F, stack);
        renderScope4X(0, -5.05, -23, 0.8F, stack);
    }

    public ModelGroza() {
        textureWidth = 128;
        textureHeight = 128;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 74, 24, -5.0F, -50.0F, 10.0F, 10, 2, 14, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 74, 24, -3.8906F, -50.2852F, -41.3711F, 4, 2, 8, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 74, 24, -0.1094F, -50.2852F, -41.3711F, 4, 2, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 74, 24, -2.0F, -45.9219F, -50.8086F, 3, 2, 10, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 74, 24, -2.0F, -46.2219F, -52.1086F, 3, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 74, 24, -2.0F, -46.6219F, -52.5086F, 3, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 74, 24, -2.0F, -50.9219F, -52.8086F, 3, 5, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 78, 15, -5.0F, -54.0F, -12.0F, 2, 4, 23, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 78, 33, -5.0F, -54.0F, 11.0F, 2, 4, 15, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 78, 33, -5.0F, -63.0F, 29.0F, 10, 21, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 78, 33, -0.4142F, -64.4142F, 29.0F, 4, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 78, 33, -0.4142F, -42.5858F, 29.0F, 4, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 78, 33, -3.5858F, -64.4142F, 29.0F, 4, 2, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 78, 33, -3.5858F, -42.5858F, 29.0F, 4, 2, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 78, 33, -4.5F, -58.0F, 25.0F, 9, 8, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 78, 33, -3.0F, -50.0F, 24.0F, 6, 8, 5, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 78, 33, -1.5F, -62.8789F, 22.0F, 3, 3, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 78, 33, -1.5F, -59.8789F, 28.0F, 3, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 81, 23, -4.0F, -49.6478F, 1.0731F, 8, 6, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 81, 23, -3.2766F, -43.6478F, 1.0731F, 2, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 81, 23, 1.2766F, -43.6478F, 1.0731F, 2, 2, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -5.0F, -58.0F, 0.0F, 2, 4, 26, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 93, 28, -5.0F, -58.3555F, 12.0664F, 2, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 93, 28, -5.0F, -58.3555F, 0.0664F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 93, 28, -5.0F, -58.3555F, -17.9336F, 2, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 3.0F, -58.0F, 0.0F, 2, 4, 26, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 93, 28, 3.0F, -58.3555F, 12.0664F, 2, 4, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 93, 28, 3.0F, -58.3555F, -0.9336F, 2, 4, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 93, 28, 3.0F, -58.3555F, -17.9336F, 2, 4, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 44, 7, -0.7573F, -62.2158F, -18.0F, 2, 2, 40, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 93, 28, -0.7573F, -62.5713F, 12.0664F, 2, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 93, 28, -0.7573F, -62.5713F, -0.9336F, 2, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 93, 28, -0.7573F, -62.5713F, -17.9336F, 2, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 81, 30, -2.0F, -61.9346F, 20.4453F, 4, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 44, 7, -1.2427F, -62.2158F, -18.0F, 2, 2, 40, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 93, 28, -1.2427F, -62.5713F, 12.0664F, 2, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 93, 28, -1.2427F, -62.5713F, -0.9336F, 2, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 93, 28, -1.2427F, -62.5713F, -17.9336F, 2, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 93, 26, -1.0F, -62.5127F, -29.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -5.0F, -58.0F, -24.0F, 10, 6, 8, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -5.0F, -57.1F, -32.0F, 10, 7, 17, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -5.0F, -57.1F, -49.0F, 10, 7, 17, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -5.0F, -57.1F, -58.0F, 10, 7, 9, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -58.3852F, -79.0F, 4, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -58.3852F, -73.0F, 4, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -58.3852F, -67.0F, 4, 2, 9, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -54.3852F, -79.0F, 4, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -54.3852F, -73.0F, 4, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -54.3852F, -67.0F, 4, 2, 9, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -58.3852F, -71.0F, 1, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -54.3852F, -71.0F, 1, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -58.3852F, -77.0F, 1, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -54.3852F, -77.0F, 1, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -58.3852F, -71.0F, 1, 2, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -54.3852F, -71.0F, 1, 2, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -58.3852F, -77.0F, 1, 2, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -54.3852F, -77.0F, 1, 2, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -57.3852F, -79.0F, 2, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -57.3852F, -73.0F, 2, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -57.3852F, -67.0F, 2, 4, 9, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -57.3852F, -79.0F, 2, 4, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -57.3852F, -73.0F, 2, 4, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -57.3852F, -67.0F, 2, 4, 9, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -57.3852F, -71.0F, 2, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -54.3852F, -71.0F, 2, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -57.3852F, -77.0F, 2, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -54.3852F, -77.0F, 2, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -57.3852F, -71.0F, 2, 1, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -54.3852F, -71.0F, 2, 1, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -57.3852F, -77.0F, 2, 1, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -54.3852F, -77.0F, 2, 1, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 32, 69, 4.0981F, -59.0977F, -33.0F, 1, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 69, -5.0981F, -59.0977F, -33.0F, 1, 2, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 32, 69, -4.5F, -60.0977F, -30.0F, 9, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 69, 4.0981F, -59.0977F, -44.0F, 1, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 69, -5.0981F, -59.0977F, -44.0F, 1, 2, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 32, 69, 4.0981F, -59.0977F, -55.0F, 1, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 88, 32, 4.0981F, -59.0977F, -56.0F, 1, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 69, -5.0981F, -59.0977F, -55.0F, 1, 2, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 88, 32, -5.0981F, -59.0977F, -56.0F, 1, 2, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 32, 69, 4.0981F, -59.0977F, -40.0F, 1, 1, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 69, -5.0981F, -59.0977F, -40.0F, 1, 1, 7, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 32, 69, 4.0981F, -58.3977F, -40.0F, 1, 1, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 69, -5.0981F, -58.3977F, -40.0F, 1, 1, 7, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 32, 69, 4.0981F, -59.0977F, -51.0F, 1, 1, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 69, -5.0981F, -59.0977F, -51.0F, 1, 1, 7, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 32, 69, 4.0981F, -58.3977F, -51.0F, 1, 1, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 69, -5.0981F, -58.3977F, -51.0F, 1, 1, 7, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 82, 47, -1.0F, -63.1957F, -56.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 66, -1.0F, -63.1957F, -55.0F, 2, 1, 15, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 32, 66, -1.0F, -63.1957F, -42.0F, 2, 1, 13, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -5.0F, -61.1406F, -28.0F, 10, 4, 10, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.5F, -63.1406F, -28.0F, 7, 2, 10, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.5F, -66.5859F, -29.0F, 7, 6, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 48, 37, -1.0F, -71.5859F, -26.0F, 2, 4, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 52, 19, -0.5F, -70.5859F, -25.6758F, 1, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -70.5859F, -29.0F, 6, 4, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -72.5859F, -35.0F, 6, 2, 9, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -75.5859F, -30.0F, 2, 3, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 2.6289F, -74.5859F, -29.0F, 1, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.6289F, -74.5859F, -29.0F, 1, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -75.5859F, -30.0F, 2, 3, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.5859F, -30.0F, 2, 1, 4, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -72.5859F, -58.0F, 4, 2, 23, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -73.5859F, -58.0F, 4, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -76.1036F, -57.0681F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -76.1036F, -57.0681F, 1, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -76.1036F, -57.6736F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -76.1036F, -57.6736F, 1, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -76.2833F, -57.3416F, 1, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -76.2833F, -57.3416F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.1953F, -75.8536F, -57.4041F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.1953F, -75.8536F, -57.4041F, 1, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 1.0F, -75.5859F, -58.0F, 1, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.0F, -75.5859F, -58.0F, 1, 2, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 66, 14, -1.5F, -73.1523F, -55.0F, 3, 1, 25, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.3711F, -54.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.3711F, -50.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.3711F, -46.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.3711F, -42.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.3711F, -38.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.3711F, -34.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.3711F, -52.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.3711F, -48.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.3711F, -44.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.3711F, -40.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.3711F, -36.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.0F, -73.3711F, -32.0F, 2, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -72.5859F, -47.0F, 1, 2, 12, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 2.0F, -72.5859F, -47.0F, 1, 2, 12, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -71.5859F, -53.0F, 1, 1, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, 2.0F, -71.5859F, -53.0F, 1, 1, 6, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -63.5859F, -58.5F, 6, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -67.0234F, -58.5F, 6, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -65.5859F, -58.5F, 6, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -69.0234F, -58.5F, 6, 1, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -64.5859F, -56.5F, 6, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -68.0234F, -56.5F, 6, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -64.5859F, -58.5F, 6, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.0F, -68.0234F, -58.5F, 6, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.5F, -64.5859F, -57.5F, 7, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.5F, -71.5859F, -58.0F, 5, 6, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -4.5F, -59.9648F, -58.0F, 9, 3, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -3.5F, -61.9648F, -58.0F, 7, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -2.5F, -62.9648F, -58.0F, 5, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.5F, -64.5859F, -25.0F, 3, 2, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -1.5F, -66.5859F, -25.0F, 3, 2, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -5.0F, -56.0F, -16.0F, 2, 2, 16, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 85, 33, 3.0F, -58.0F, -16.0F, 2, 4, 16, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 67, 16, -5.0F, -54.7734F, 24.0F, 10, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 84, 24, -5.0F, -54.0F, -14.0F, 10, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 67, 17, 3.0F, -54.0F, -12.0F, 2, 4, 38, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 44, 42, -5.1758F, -50.1953F, 19.8789F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 44, 42, 4.1758F, -50.1953F, 19.8789F, 1, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 44, 42, 4.1758F, -49.7656F, 12.1094F, 1, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 44, 42, 4.1758F, -51.8789F, -16.0117F, 1, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 44, 42, -5.1758F, -51.8789F, -16.0117F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 44, 42, -5.1758F, -49.7656F, 12.1094F, 1, 1, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 44, 42, 2.5313F, -43.0859F, 3.7148F, 1, 1, 1, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 44, 42, -3.5313F, -43.0859F, 3.7148F, 1, 1, 1, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, -1.7109F, -1.2344F);
        setRotationAngle(bone9, 0.1222F, 0.0F, 0.0F);
        bone.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 75, 27, -4.0F, -51.2228F, -35.3242F, 4, 2, 10, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 75, 27, 0.0F, -51.2228F, -35.3242F, 4, 2, 10, 0.0F, false));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-0.2734F, 0.0313F, 0.5078F);
        setRotationAngle(bone10, 0.1745F, 0.0F, 0.0F);
        bone9.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 75, 27, -3.7266F, -51.1298F, -25.7606F, 8, 13, 8, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 75, 27, -3.7266F, -38.7156F, -24.3464F, 8, 2, 4, 0.0F, false));
        bone10.cubeList.add(new ModelBox(bone10, 75, 27, -3.7266F, -38.7156F, -21.1748F, 8, 2, 2, 0.0F, false));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.2734F, 24.0224F, 18.818F);
        setRotationAngle(bone13, -0.7854F, 0.0F, 0.0F);
        bone10.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 75, 27, -4.0F, -18.0832F, -70.8132F, 8, 2, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 75, 27, -4.0F, -13.4264F, -75.47F, 8, 1, 2, 0.0F, false));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-0.2734F, 0.0313F, 0.5078F);
        setRotationAngle(bone11, -0.0873F, 0.0F, 0.0F);
        bone9.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 75, 27, -3.7266F, -46.8152F, -38.0265F, 8, 4, 8, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(-0.2734F, 7.1875F, -2.9375F);
        setRotationAngle(bone12, 0.4363F, 0.0F, 0.0F);
        bone9.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 75, 27, -3.7266F, -64.8123F, -5.5123F, 8, 4, 2, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone3, -0.0873F, 0.0F, 0.0F);
        bone.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 72, 18, -5.0F, -52.6889F, -28.2215F, 10, 4, 9, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 72, 18, -5.0F, -52.6889F, -19.2215F, 2, 4, 9, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 72, 18, 3.0F, -52.6889F, -19.2215F, 2, 4, 9, 0.0F, false));
        bone3.cubeList.add(new ModelBox(bone3, 72, 18, -5.0F, -52.6889F, -10.2215F, 10, 4, 16, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(2.0F, -41.0F, 31.0F);
        setRotationAngle(bone16, 0.0F, 0.0F, 0.7854F);
        bone.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 78, 33, -0.5858F, -2.8284F, -2.0F, 2, 2, 4, 0.0F, false));
        bone16.cubeList.add(new ModelBox(bone16, 78, 33, -5.6569F, 2.2426F, -2.0F, 2, 2, 4, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(2.0F, -62.0F, 31.0F);
        setRotationAngle(bone31, 0.0F, 0.0F, 0.7854F);
        bone.addChild(bone31);
        bone31.cubeList.add(new ModelBox(bone31, 78, 33, -0.5858F, -2.8284F, -2.0F, 2, 2, 4, 0.0F, false));
        bone31.cubeList.add(new ModelBox(bone31, 78, 33, -5.6569F, 2.2426F, -2.0F, 2, 2, 4, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, -46.0F, 19.5F);
        setRotationAngle(bone15, 0.4363F, 0.0F, 0.0F);
        bone.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 78, 33, -3.0F, -1.473F, 2.3879F, 6, 7, 2, 0.0F, false));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(0.0F, -46.8945F, 20.4492F);
        setRotationAngle(bone32, -0.4363F, 0.0F, 0.0F);
        bone.addChild(bone32);
        bone32.cubeList.add(new ModelBox(bone32, 78, 33, -2.0F, -2.6644F, 1.3879F, 4, 7, 3, 0.0F, false));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, -61.3789F, 24.0F);
        setRotationAngle(bone14, 0.1745F, 0.0F, 0.0F);
        bone.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 78, 33, -1.5F, -1.8245F, -5.7091F, 3, 3, 4, 0.0F, false));

        dust_cover = new ModelRenderer(this);
        dust_cover.setRotationPoint(20.0F, -31.0F, -4.0F);
        bone.addChild(dust_cover);
        dust_cover.cubeList.add(new ModelBox(dust_cover, 82, 69, -26.0F, -22.0F, 22.0F, 1, 2, 2, 0.0F, false));
        dust_cover.cubeList.add(new ModelBox(dust_cover, 46, 109, -26.1563F, -22.0F, 20.6172F, 1, 2, 2, 0.0F, false));
        dust_cover.cubeList.add(new ModelBox(dust_cover, 82, 69, -26.0F, -23.4142F, 18.5858F, 1, 2, 4, 0.0F, false));
        dust_cover.cubeList.add(new ModelBox(dust_cover, 82, 69, -26.0F, -25.5355F, 6.4645F, 1, 3, 10, 0.0F, false));
        dust_cover.cubeList.add(new ModelBox(dust_cover, 88, 41, -26.3633F, -25.7777F, 5.4645F, 2, 1, 3, 0.0F, false));
        dust_cover.cubeList.add(new ModelBox(dust_cover, 82, 69, -26.0F, -20.5858F, 20.5858F, 1, 2, 2, 0.0F, false));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone29, -0.2618F, 0.0F, 0.0F);
        dust_cover.addChild(bone29);
        bone29.cubeList.add(new ModelBox(bone29, 82, 69, -26.0F, -26.2805F, 0.074F, 1, 3, 15, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-25.0F, -21.0F, 23.0F);
        setRotationAngle(bone28, -0.7854F, 0.0F, 0.0F);
        dust_cover.addChild(bone28);
        bone28.cubeList.add(new ModelBox(bone28, 82, 69, -1.0F, -1.4142F, -2.0F, 1, 2, 2, 0.0F, false));
        bone28.cubeList.add(new ModelBox(bone28, 82, 69, -1.0F, 1.4142F, -7.8284F, 1, 3, 5, 0.0F, false));
        bone28.cubeList.add(new ModelBox(bone28, 82, 69, -1.0F, -0.0F, -0.5858F, 1, 2, 2, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, -40.9837F, 3.0215F);
        setRotationAngle(bone17, -0.192F, 0.0F, 0.0F);
        bone.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 81, 23, -2.0F, -3.0F, -1.0F, 4, 6, 2, 0.0F, true));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(4.0F, -66.0F, 13.0F);
        setRotationAngle(bone22, 0.0F, 0.0F, 1.2217F);
        bone.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 44, 19, 1.7628F, 6.2208F, -31.0F, 2, 2, 40, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 93, 28, 1.4288F, 6.0992F, -0.9336F, 2, 2, 2, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 93, 28, 1.4288F, 6.0992F, -13.9336F, 2, 2, 2, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 93, 28, 1.4288F, 6.0992F, -30.9336F, 2, 2, 2, 0.0F, true));
        bone22.cubeList.add(new ModelBox(bone22, 93, 26, 1.4839F, 6.1193F, -42.0F, 1, 2, 1, 0.0F, true));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-4.0F, -66.0F, 13.0F);
        setRotationAngle(bone21, 0.0F, 0.0F, -1.2217F);
        bone.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 44, 12, -3.7628F, 6.2208F, -31.0F, 2, 2, 40, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 93, 28, -3.4288F, 6.0992F, -0.9336F, 2, 2, 2, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 93, 28, -3.4288F, 6.0992F, -13.9336F, 2, 2, 2, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 93, 28, -3.4288F, 6.0992F, -30.9336F, 2, 2, 2, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 93, 26, -2.4839F, 6.1193F, -42.0F, 1, 2, 1, 0.0F, false));

        bone46 = new ModelRenderer(this);
        bone46.setRotationPoint(0.0F, -58.4023F, -88.1367F);
        setRotationAngle(bone46, 0.3491F, 0.0F, 0.0F);
        bone.addChild(bone46);

        bone48 = new ModelRenderer(this);
        bone48.setRotationPoint(3.5F, -64.4802F, -92.0309F);
        setRotationAngle(bone48, 0.0F, 0.0F, 0.5236F);
        bone.addChild(bone48);

        bone47 = new ModelRenderer(this);
        bone47.setRotationPoint(-3.5F, -64.4802F, -92.0309F);
        setRotationAngle(bone47, 0.0F, 0.0F, -0.5236F);
        bone.addChild(bone47);

        bone45 = new ModelRenderer(this);
        bone45.setRotationPoint(0.0F, -61.5898F, -69.7148F);
        setRotationAngle(bone45, 0.8203F, 0.0F, 0.0F);
        bone.addChild(bone45);

        bone43 = new ModelRenderer(this);
        bone43.setRotationPoint(-3.5F, -49.5F, -55.5F);
        setRotationAngle(bone43, 0.0F, 0.0F, -0.5236F);
        bone.addChild(bone43);
        bone43.cubeList.add(new ModelBox(bone43, 82, 47, 11.245F, -7.0128F, -0.5F, 1, 3, 1, 0.0F, false));
        bone43.cubeList.add(new ModelBox(bone43, 82, 47, 6.0129F, -10.6109F, -0.5F, 3, 1, 1, 0.0F, false));
        bone43.cubeList.add(new ModelBox(bone43, 33, 73, 11.245F, -7.0128F, 0.5F, 1, 3, 13, 0.0F, false));
        bone43.cubeList.add(new ModelBox(bone43, 33, 73, 6.0129F, -10.6109F, 0.5F, 3, 1, 13, 0.0F, false));
        bone43.cubeList.add(new ModelBox(bone43, 33, 73, 11.245F, -7.0128F, 13.5F, 1, 3, 13, 0.0F, false));
        bone43.cubeList.add(new ModelBox(bone43, 33, 73, 6.0129F, -10.6109F, 13.5F, 3, 1, 13, 0.0F, false));

        bone44 = new ModelRenderer(this);
        bone44.setRotationPoint(3.5F, -49.5F, -55.5F);
        setRotationAngle(bone44, 0.0F, 0.0F, 0.5236F);
        bone.addChild(bone44);
        bone44.cubeList.add(new ModelBox(bone44, 82, 47, -12.245F, -7.0128F, -0.5F, 1, 3, 1, 0.0F, true));
        bone44.cubeList.add(new ModelBox(bone44, 82, 47, -9.0129F, -10.6109F, -0.5F, 3, 1, 1, 0.0F, true));
        bone44.cubeList.add(new ModelBox(bone44, 32, 64, -12.245F, -7.0128F, 0.5F, 1, 3, 14, 0.0F, true));
        bone44.cubeList.add(new ModelBox(bone44, 32, 64, -9.0129F, -10.6109F, 0.5F, 3, 1, 14, 0.0F, true));
        bone44.cubeList.add(new ModelBox(bone44, 31, 66, -12.245F, -7.0128F, 14.5F, 1, 3, 12, 0.0F, true));
        bone44.cubeList.add(new ModelBox(bone44, 31, 66, -9.0129F, -10.6109F, 14.5F, 3, 1, 12, 0.0F, true));

        bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(0.0F, -47.5F, -50.5F);
        setRotationAngle(bone39, -0.0698F, 0.0F, 0.0F);
        bone.addChild(bone39);

        bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(-5.0F, -2.1703F, 12.5575F);
        setRotationAngle(bone40, 0.0F, 0.0F, -0.5236F);
        bone39.addChild(bone40);

        bone41 = new ModelRenderer(this);
        bone41.setRotationPoint(5.0F, -2.1703F, 12.5575F);
        setRotationAngle(bone41, 0.0F, 0.0F, 0.5236F);
        bone39.addChild(bone41);

        bone42 = new ModelRenderer(this);
        bone42.setRotationPoint(5.0F, -2.1703F, 12.5575F);
        setRotationAngle(bone42, 0.0F, 0.0F, 0.5236F);
        bone39.addChild(bone42);

        bone51 = new ModelRenderer(this);
        bone51.setRotationPoint(0.0F, -69.5859F, -25.1758F);
        setRotationAngle(bone51, 0.0F, 0.0F, -1.0472F);
        bone.addChild(bone51);
        bone51.cubeList.add(new ModelBox(bone51, 52, 19, -0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F, false));

        bone52 = new ModelRenderer(this);
        bone52.setRotationPoint(0.0F, -69.5859F, -25.1758F);
        setRotationAngle(bone52, 0.0F, 0.0F, -2.0944F);
        bone.addChild(bone52);
        bone52.cubeList.add(new ModelBox(bone52, 52, 19, -0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F, false));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(0.0F, -69.5859F, -25.5F);
        setRotationAngle(bone33, 0.0F, 0.0F, -0.5236F);
        bone.addChild(bone33);
        bone33.cubeList.add(new ModelBox(bone33, 48, 37, -1.0F, -2.0F, -0.5F, 2, 4, 1, 0.0F, false));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(0.0F, -69.5859F, -25.5F);
        setRotationAngle(bone34, 0.0F, 0.0F, -1.0472F);
        bone.addChild(bone34);
        bone34.cubeList.add(new ModelBox(bone34, 48, 37, -1.0F, -2.0F, -0.5F, 2, 4, 1, 0.0F, false));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(0.0F, -69.5859F, -25.5F);
        setRotationAngle(bone35, 0.0F, 0.0F, -1.5708F);
        bone.addChild(bone35);
        bone35.cubeList.add(new ModelBox(bone35, 48, 37, -1.0F, -2.0F, -0.5F, 2, 4, 1, 0.0F, false));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(0.0F, -69.5859F, -25.5F);
        setRotationAngle(bone36, 0.0F, 0.0F, -2.0944F);
        bone.addChild(bone36);
        bone36.cubeList.add(new ModelBox(bone36, 48, 37, -1.0F, -2.0F, -0.5F, 2, 4, 1, 0.0F, false));

        bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(0.0F, -69.5859F, -25.5F);
        setRotationAngle(bone37, 0.0F, 0.0F, -2.618F);
        bone.addChild(bone37);
        bone37.cubeList.add(new ModelBox(bone37, 48, 37, -1.0F, -2.0F, -0.5F, 2, 4, 1, 0.0F, false));

        bone53 = new ModelRenderer(this);
        bone53.setRotationPoint(0.0F, -67.5859F, -30.0F);
        setRotationAngle(bone53, 0.7854F, 0.0F, 0.0F);
        bone.addChild(bone53);
        bone53.cubeList.add(new ModelBox(bone53, 67, 16, -3.0F, -2.4142F, 1.8284F, 6, 2, 2, 0.0F, false));
        bone53.cubeList.add(new ModelBox(bone53, 67, 16, -1.5F, -21.5061F, -17.2635F, 3, 2, 2, 0.0F, false));

        bone54 = new ModelRenderer(this);
        bone54.setRotationPoint(-2.0F, -74.0859F, -32.0F);
        setRotationAngle(bone54, 0.1745F, 0.0F, 0.0F);
        bone.addChild(bone54);
        bone54.cubeList.add(new ModelBox(bone54, 67, 16, -1.0F, -1.1299F, -3.7699F, 1, 3, 6, 0.0F, true));
        bone54.cubeList.add(new ModelBox(bone54, 67, 16, 4.0F, -1.1299F, -3.7699F, 1, 3, 6, 0.0F, false));
        bone54.cubeList.add(new ModelBox(bone54, 67, 16, -1.0F, -1.1299F, -14.7699F, 1, 2, 11, 0.0F, true));
        bone54.cubeList.add(new ModelBox(bone54, 67, 16, 4.0F, -1.1299F, -14.7699F, 1, 2, 11, 0.0F, false));
        bone54.cubeList.add(new ModelBox(bone54, 67, 16, -1.0F, -1.1299F, -20.7699F, 1, 1, 6, 0.0F, true));
        bone54.cubeList.add(new ModelBox(bone54, 67, 16, 4.0F, -1.1299F, -20.7699F, 1, 1, 6, 0.0F, false));

        bone57 = new ModelRenderer(this);
        bone57.setRotationPoint(-1.5F, -76.5859F, -57.0F);
        setRotationAngle(bone57, 0.2618F, 0.0F, 0.0F);
        bone.addChild(bone57);
        bone57.cubeList.add(new ModelBox(bone57, 67, 16, -0.5F, 0.7071F, -1.2247F, 1, 3, 2, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 67, 16, 2.5F, 0.7071F, -1.2247F, 1, 3, 2, 0.0F, false));

        bone55 = new ModelRenderer(this);
        bone55.setRotationPoint(-1.5F, -73.6523F, -57.5F);
        setRotationAngle(bone55, 0.0F, 0.0F, -0.3491F);
        bone.addChild(bone55);
        bone55.cubeList.add(new ModelBox(bone55, 67, 16, -0.4538F, 0.0667F, 3.5F, 1, 1, 1, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 67, 16, -0.4538F, 0.0667F, 7.5F, 1, 1, 1, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 67, 16, -0.4538F, 0.0667F, 11.5F, 1, 1, 1, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 67, 16, -0.4538F, 0.0667F, 15.5F, 1, 1, 1, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 67, 16, -0.4538F, 0.0667F, 19.5F, 1, 1, 1, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 67, 16, -0.4538F, 0.0667F, 23.5F, 1, 1, 1, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 67, 16, -0.4538F, 0.0667F, 5.5F, 1, 1, 1, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 67, 16, -0.4538F, 0.0667F, 9.5F, 1, 1, 1, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 67, 16, -0.4538F, 0.0667F, 13.5F, 1, 1, 1, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 67, 16, -0.4538F, 0.0667F, 17.5F, 1, 1, 1, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 67, 16, -0.4538F, 0.0667F, 21.5F, 1, 1, 1, 0.0F, false));
        bone55.cubeList.add(new ModelBox(bone55, 67, 16, -0.4538F, 0.0667F, 25.5F, 1, 1, 1, 0.0F, false));

        bone56 = new ModelRenderer(this);
        bone56.setRotationPoint(1.5F, -73.6523F, -57.5F);
        setRotationAngle(bone56, 0.0F, 0.0F, 0.3491F);
        bone.addChild(bone56);
        bone56.cubeList.add(new ModelBox(bone56, 67, 16, -0.5462F, 0.0667F, 3.5F, 1, 1, 1, 0.0F, true));
        bone56.cubeList.add(new ModelBox(bone56, 67, 16, -0.5462F, 0.0667F, 7.5F, 1, 1, 1, 0.0F, true));
        bone56.cubeList.add(new ModelBox(bone56, 67, 16, -0.5462F, 0.0667F, 11.5F, 1, 1, 1, 0.0F, true));
        bone56.cubeList.add(new ModelBox(bone56, 67, 16, -0.5462F, 0.0667F, 15.5F, 1, 1, 1, 0.0F, true));
        bone56.cubeList.add(new ModelBox(bone56, 67, 16, -0.5462F, 0.0667F, 19.5F, 1, 1, 1, 0.0F, true));
        bone56.cubeList.add(new ModelBox(bone56, 67, 16, -0.5462F, 0.0667F, 23.5F, 1, 1, 1, 0.0F, true));
        bone56.cubeList.add(new ModelBox(bone56, 67, 16, -0.5462F, 0.0667F, 5.5F, 1, 1, 1, 0.0F, true));
        bone56.cubeList.add(new ModelBox(bone56, 67, 16, -0.5462F, 0.0667F, 9.5F, 1, 1, 1, 0.0F, true));
        bone56.cubeList.add(new ModelBox(bone56, 67, 16, -0.5462F, 0.0667F, 13.5F, 1, 1, 1, 0.0F, true));
        bone56.cubeList.add(new ModelBox(bone56, 67, 16, -0.5462F, 0.0667F, 17.5F, 1, 1, 1, 0.0F, true));
        bone56.cubeList.add(new ModelBox(bone56, 67, 16, -0.5462F, 0.0667F, 21.5F, 1, 1, 1, 0.0F, true));
        bone56.cubeList.add(new ModelBox(bone56, 67, 16, -0.5462F, 0.0667F, 25.5F, 1, 1, 1, 0.0F, true));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(0.0F, -68.0859F, -24.5F);
        setRotationAngle(bone30, 0.2618F, 0.0F, 0.0F);
        bone.addChild(bone30);
        bone30.cubeList.add(new ModelBox(bone30, 67, 16, -1.5F, 1.2852F, -0.9102F, 3, 1, 1, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(0.0F, -65.5859F, -21.5F);
        setRotationAngle(bone27, -0.5236F, 0.0F, 0.0F);
        bone.addChild(bone27);
        bone27.cubeList.add(new ModelBox(bone27, 67, 16, 0.5F, 0.384F, -2.6651F, 1, 2, 5, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 67, 16, -1.5F, 0.384F, -2.6651F, 1, 2, 5, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 67, 16, -0.5F, 0.384F, -2.6651F, 1, 2, 1, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 67, 16, -0.5F, 0.384F, 0.3349F, 1, 1, 1, 0.0F, true));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(-4.0F, -64.0F, 13.0F);
        setRotationAngle(bone18, 0.0F, 0.0F, 0.4887F);
        bone.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 67, 13, 1.9339F, 1.7672F, -13.0F, 2, 4, 22, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 93, 28, 1.767F, 1.4533F, -0.9336F, 2, 4, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 93, 28, 1.767F, 1.4533F, -13.9336F, 2, 2, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 93, 28, 1.767F, 3.4533F, -12.9336F, 2, 2, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 93, 28, 1.767F, 1.4533F, -30.9336F, 2, 4, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 67, 13, 1.9339F, 3.7672F, 9.0F, 4, 2, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 67, 13, 1.9339F, 1.7672F, -31.0F, 2, 4, 2, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 67, 13, 1.7945F, 1.505F, -42.0F, 5, 4, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 67, 13, 1.9339F, 1.7672F, -29.0F, 2, 2, 16, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(4.0F, -64.0F, 13.0F);
        setRotationAngle(bone20, 0.0F, 0.0F, -0.4887F);
        bone.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 67, 13, -3.9339F, 1.7672F, -13.0F, 2, 4, 22, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 93, 28, -3.767F, 1.4533F, -0.9336F, 2, 4, 2, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 93, 28, -3.767F, 1.4533F, -13.9336F, 2, 4, 2, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 93, 28, -3.767F, 1.4533F, -30.9336F, 2, 4, 2, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 67, 13, -5.9339F, 3.7672F, 9.0F, 4, 2, 2, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 67, 13, -3.9339F, 1.7672F, -31.0F, 2, 4, 2, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 67, 13, -6.7945F, 1.505F, -42.0F, 5, 4, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 104, 18, -3.9339F, 1.7672F, -29.0F, 2, 4, 16, 0.0F, true));

        bolt = new ModelRenderer(this);
        bolt.setRotationPoint(-5.2266F, -61.9844F, -3.6563F);
        setRotationAngle(bolt, 0.0F, 0.0F, 0.2443F);
        bone.addChild(bolt);
        bolt.cubeList.add(new ModelBox(bolt, 36, 4, 1.9339F, 1.7672F, -13.0F, 2, 4, 18, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(1.6874F, 3.7672F, -9.191F);
        setRotationAngle(bone23, 0.0F, -0.2618F, 0.0F);
        bolt.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 36, 4, -0.4688F, -1.0F, -2.5F, 2, 2, 4, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 36, 4, -2.4688F, -1.0F, -0.5F, 2, 2, 2, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(1.6874F, 3.7672F, -9.191F);
        setRotationAngle(bone26, 0.0F, -0.6981F, 0.0F);
        bolt.addChild(bone26);
        bone26.cubeList.add(new ModelBox(bone26, 36, 4, -2.6035F, -1.0F, 0.4028F, 1, 2, 2, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(1.6874F, 3.7672F, -9.191F);
        setRotationAngle(bone24, 0.0F, -0.5236F, 0.0F);
        bolt.addChild(bone24);

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(2.8245F, 3.7672F, -9.2656F);
        setRotationAngle(bone19, 0.0F, 0.0698F, 0.0F);
        bolt.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 36, 4, -1.2093F, -1.0F, 0.9927F, 2, 2, 4, 0.0F, false));

        bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(1.0F, 0.0F, 0.0F);
        setRotationAngle(bone38, -0.1745F, 0.0F, 0.0F);
        bone.addChild(bone38);

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(0.0F, -57.0766F, 15.4474F);
        setRotationAngle(bone25, 0.7854F, 0.0F, 0.0F);
        bone.addChild(bone25);
        bone25.cubeList.add(new ModelBox(bone25, 74, 24, -5.0F, 5.8771F, 6.1827F, 10, 2, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 74, 24, -4.0F, 3.8771F, 6.1827F, 8, 2, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 74, 24, -2.0F, 1.3693F, 6.4561F, 4, 6, 2, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 74, 24, -3.0F, 1.4709F, 5.8155F, 6, 4, 2, 0.0F, false));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, -3.0F, 32.0F);
        setRotationAngle(bone2, 0.7854F, 0.0F, 0.0F);
        bone.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 74, 24, -5.0F, -39.4767F, 26.163F, 10, 2, 3, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(-41.0F, 24.0F, -0.4336F);
        setRotationAngle(magazine, -0.0698F, 0.0F, 0.0F);

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, -0.4336F);
        magazine.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 1, 74, 38.0F, -42.4831F, -8.9147F, 6, 2, 8, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 7, 72, 38.0F, -42.4831F, -12.9147F, 6, 2, 4, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 1, 74, 38.0F, -50.4831F, -8.9147F, 6, 2, 8, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 8, 109, 39.0F, -55.4758F, -3.124F, 4, 4, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 8, 109, 39.0F, -55.4758F, -10.417F, 4, 4, 7, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 15, 19, 40.0F, -54.4758F, -14.417F, 2, 2, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 15, 19, 40.5F, -53.9758F, -15.2646F, 1, 1, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 8, 109, 39.5F, -54.9758F, -12.417F, 3, 3, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 8, 109, 39.5F, -54.9758F, -4.124F, 3, 3, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 7, 72, 38.0F, -50.4831F, -12.9147F, 6, 2, 4, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 7, 72, 38.0F, -48.4831F, -12.9147F, 6, 6, 4, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 77, 8, 41.8F, -48.4831F, -8.9147F, 2, 6, 6, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 77, 8, 38.2F, -48.4831F, -8.9147F, 2, 6, 6, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 7, 72, 39.0F, -50.4831F, -16.9147F, 4, 10, 4, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 7, 72, 40.0F, -53.4831F, -16.9147F, 2, 3, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 7, 72, 39.0F, -54.4831F, -16.9147F, 1, 4, 4, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 7, 72, 42.0F, -54.4831F, -16.9147F, 1, 4, 4, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 7, 72, 43.0F, -54.4831F, -12.9147F, 1, 4, 11, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 7, 72, 38.0F, -54.4831F, -1.9147F, 6, 4, 1, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 7, 72, 38.0F, -54.4831F, -12.9147F, 1, 4, 11, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 7, 72, 38.0F, -48.4831F, -2.9147F, 6, 6, 2, 0.0F, false));

        bone49 = new ModelRenderer(this);
        bone49.setRotationPoint(1.5F, -51.4831F, -10.9147F);
        setRotationAngle(bone49, 0.0F, 0.3491F, 0.0F);
        bone4.addChild(bone49);

        bone50 = new ModelRenderer(this);
        bone50.setRotationPoint(-1.5F, -51.4831F, -10.9147F);
        setRotationAngle(bone50, 0.0F, -0.3491F, 0.0F);
        bone4.addChild(bone50);

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, -0.4336F);
        setRotationAngle(bone5, -0.2618F, 0.0F, 0.0F);
        magazine.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 0, 72, 38.0F, -30.7547F, -19.3466F, 6, 2, 8, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 0, 72, 38.0F, -30.7547F, -23.3466F, 6, 2, 4, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 1, 74, 38.0F, -38.7547F, -19.3466F, 6, 2, 8, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 7, 72, 38.0F, -38.7547F, -23.3466F, 6, 2, 4, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 7, 72, 38.0F, -36.7547F, -23.3466F, 6, 6, 4, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 77, 8, 41.8F, -36.7547F, -19.3466F, 2, 6, 6, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 77, 8, 38.2F, -36.7547F, -19.3466F, 2, 6, 6, 0.0F, true));
        bone5.cubeList.add(new ModelBox(bone5, 7, 72, 39.0F, -38.7547F, -27.3466F, 4, 10, 4, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 7, 72, 38.0F, -36.7547F, -13.3466F, 6, 6, 2, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, -0.4336F);
        setRotationAngle(bone6, -0.5236F, 0.0F, 0.0F);
        magazine.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 1, 74, 38.0F, -16.726F, -26.3874F, 6, 2, 8, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 7, 72, 38.0F, -16.726F, -30.3874F, 6, 2, 4, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 1, 74, 38.0F, -24.726F, -26.3874F, 6, 2, 8, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 7, 72, 38.0F, -24.726F, -30.3874F, 6, 2, 4, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 7, 72, 38.0F, -22.726F, -30.3874F, 6, 6, 4, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 77, 8, 41.8F, -22.726F, -26.3874F, 2, 6, 6, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 77, 8, 38.2F, -22.726F, -26.3874F, 2, 6, 6, 0.0F, true));
        bone6.cubeList.add(new ModelBox(bone6, 7, 72, 39.0F, -24.726F, -34.3874F, 4, 10, 4, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 7, 72, 38.0F, -22.726F, -20.3874F, 6, 6, 2, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, 0.0F, -0.4336F);
        setRotationAngle(bone7, -0.7854F, 0.0F, 0.0F);
        magazine.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 1, 74, 38.0F, -1.3529F, -29.5575F, 6, 2, 8, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 7, 72, 38.0F, -1.3529F, -33.5575F, 6, 2, 4, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 1, 74, 38.0F, -9.3529F, -29.5575F, 6, 2, 8, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 7, 72, 38.0F, -9.3529F, -33.5575F, 6, 2, 4, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 7, 72, 38.0F, -7.3529F, -33.5575F, 6, 6, 4, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 77, 8, 41.8F, -7.3529F, -29.5575F, 2, 6, 6, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 77, 8, 38.2F, -7.3529F, -29.5575F, 2, 6, 6, 0.0F, true));
        bone7.cubeList.add(new ModelBox(bone7, 7, 72, 39.0F, -9.3529F, -37.5575F, 4, 10, 4, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 7, 72, 38.0F, -7.3529F, -23.5575F, 6, 6, 2, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, 33.1379F, 5.235F);
        setRotationAngle(bone8, -0.0873F, 0.0F, 0.0F);
        bone7.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 0, 72, 39.0F, -30.4137F, -42.1992F, 4, 2, 12, 0.0F, false));
        this.initAnimations();
    }
}
