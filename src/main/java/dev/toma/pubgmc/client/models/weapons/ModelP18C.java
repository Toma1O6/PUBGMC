package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.HeldAnimation;
import dev.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.client.models.ModelGun;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ModelP18C extends ModelGun {

    private final ModelRenderer glock;
    private final ModelRenderer magazine;

    @Override
    public String textureName() {
        return "p18c";
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.585f, 0.22f, -0.01f, 2.5F);
        initAimingAnimationStates(0.22f, 0.14f, 0f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
        reloadAnimation = new ReloadAnimation(magazine, ReloadAnimation.ReloadStyle.MAGAZINE).withSpeed(2.0F);
    }

    @Override
    public void render(ItemStack stack) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            GlStateManager.pushMatrix();
            {
                renderP18C(data.isAiming(), stack);
            }
            GlStateManager.popMatrix();
        }
    }

    private void renderP18C(boolean aim, ItemStack stack) {
        GlStateManager.pushMatrix();
        {
            ModelTransformationHelper.defaultPistolTransform();
            GlStateManager.translate(-0.9, -1.0, -6.0);
            if (aim) rotateModelForADSRendering();
            glock.render(1f);
        }
        GlStateManager.popMatrix();

        renderRedDot(-2.06, -5.76, -6, 1.3F, stack);
        renderPistolSilencer(0, 0, 0, 1f, stack);
    }

    public ModelP18C() {
        super();
        textureWidth = 128;
        textureHeight = 128;

        glock = new ModelRenderer(this);
        glock.setRotationPoint(0.0F, 24.0F, 0.0F);

        ModelRenderer gun = new ModelRenderer(this);
        gun.setRotationPoint(0.0F, 0.0F, 0.0F);
        glock.addChild(gun);
        gun.cubeList.add(new ModelBox(gun, 16, 38, -3.0F, -10.0F, -15.0F, 6, 1, 6, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 34, 65, -3.5F, -14.4375F, -10.5F, 1, 5, 10, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 7, 75, -3.0F, -13.9688F, -14.875F, 6, 4, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 38, 70, 2.5F, -14.4375F, -10.5F, 1, 5, 10, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 34, 68, -0.4063F, -14.8601F, -2.5F, 3, 1, 11, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 33, 68, -2.5937F, -14.8601F, -2.5F, 3, 1, 11, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 40, 13, -0.5F, -11.1094F, -16.0F, 1, 1, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 40, 13, -1.866F, -12.4754F, -16.0F, 1, 1, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 40, 13, 0.866F, -12.4754F, -16.0F, 1, 1, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 40, 13, -0.5F, -13.8414F, -16.0F, 1, 1, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 42, 76, 2.5F, -11.4375F, -0.5F, 1, 2, 7, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 42, 76, 2.5F, -14.4375F, -0.5F, 1, 1, 9, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 33, 68, -3.5F, -11.4375F, -0.5F, 1, 2, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 43, 74, -3.5F, -14.4375F, -0.5F, 1, 1, 9, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 47, 90, -3.5F, -13.4375F, 0.5F, 7, 2, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 40, 70, -3.5F, -13.4375F, 2.5F, 7, 2, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 47, 90, -3.5F, -13.4375F, 4.5F, 7, 2, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 47, 90, -3.5F, -13.4375F, 6.5F, 7, 4, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 35, 8, -3.0F, -13.4375F, -0.5F, 6, 2, 7, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 18, 47, -3.0F, -11.0625F, 2.0F, 6, 2, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 33, 68, 1.5F, -10.4375F, 8.5F, 2, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 33, 68, -2.5F, -11.4375F, 8.5F, 5, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 33, 68, -3.5F, -10.4375F, 8.5F, 2, 1, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 21, 40, -3.0F, -11.0625F, -5.0F, 6, 2, 7, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 36, 3, 1.2969F, -16.8601F, 5.8281F, 1, 3, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 36, 3, 0.7969F, -16.8601F, 5.8281F, 1, 3, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 36, 3, -1.7969F, -16.8601F, 5.8281F, 1, 3, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 36, 3, -2.2969F, -16.8601F, 5.8281F, 1, 3, 1, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 36, 3, -1.0F, -15.8601F, 5.8438F, 2, 2, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 36, 3, -1.0F, -16.8601F, -14.375F, 2, 3, 1, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 35, 66, -3.5F, -14.4375F, -15.5F, 1, 5, 5, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 35, 66, 2.5F, -14.4375F, -15.5F, 1, 5, 5, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 33, 68, -2.5937F, -14.8601F, -13.5F, 3, 1, 11, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 35, 73, -0.4063F, -14.8601F, -13.5F, 3, 1, 11, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 39, 76, -2.5937F, -14.8601F, -15.5F, 3, 1, 2, 0.0F, true));
        gun.cubeList.add(new ModelBox(gun, 39, 76, -0.4063F, -14.8601F, -15.5F, 3, 1, 2, 0.0F, false));
        gun.cubeList.add(new ModelBox(gun, 16, 38, -3.0F, -10.0F, -8.0F, 6, 2, 3, 0.0F, false));

        ModelRenderer bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, -1.0F);
        setRotationAngle(bone2, -0.1047F, 0.0F, 0.0F);
        gun.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 36, 44, -2.5F, -9.9939F, 6.1147F, 5, 3, 1, 0.0F, false));

        ModelRenderer bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(3.5F, -5.5F, 0.0F);
        setRotationAngle(bone3, 0.0F, 0.0F, -1.1345F);
        gun.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 35, 70, 7.1001F, -4.7772F, -2.5F, 1, 1, 11, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 39, 80, 6.819F, -5.2322F, -15.5F, 1, 1, 2, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 42, 76, 7.1001F, -4.7772F, -13.5F, 1, 1, 11, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 39, 76, 7.1001F, -4.7772F, -15.5F, 1, 1, 2, 0.0F, true));

        ModelRenderer bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(-3.5F, -5.5F, 0.0F);
        setRotationAngle(bone4, 0.0F, 0.0F, 1.1345F);
        gun.addChild(bone4);
        bone4.cubeList.add(new ModelBox(bone4, 33, 68, -8.1001F, -4.7772F, -2.5F, 1, 1, 11, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 39, 80, -7.819F, -5.2322F, -15.5F, 1, 1, 2, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 33, 68, -8.1001F, -4.7772F, -13.5F, 1, 1, 11, 0.0F, false));
        bone4.cubeList.add(new ModelBox(bone4, 39, 76, -8.1001F, -4.7772F, -15.5F, 1, 1, 2, 0.0F, false));

        ModelRenderer bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone5, 0.0F, 0.0F, -0.5236F);
        gun.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 40, 13, 5.4877F, -9.505F, -16.0F, 1, 1, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 40, 13, 4.1217F, -10.871F, -16.0F, 1, 1, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 40, 13, 6.8537F, -10.871F, -16.0F, 1, 1, 2, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 40, 13, 5.4877F, -12.237F, -16.0F, 1, 1, 2, 0.0F, false));

        ModelRenderer bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone6, 0.0F, 0.0F, -1.0472F);
        gun.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 40, 13, 8.505F, -6.4877F, -16.0F, 1, 1, 2, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 40, 13, 9.871F, -5.1217F, -16.0F, 1, 1, 2, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 40, 13, 9.871F, -7.8537F, -16.0F, 1, 1, 2, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 40, 13, 11.237F, -6.4877F, -16.0F, 1, 1, 2, 0.0F, false));

        ModelRenderer bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, -1.4375F, 9.5F);
        setRotationAngle(bone7, 0.2443F, 0.0F, 0.0F);
        gun.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 33, 68, -3.5F, -12.7327F, 1.1773F, 7, 4, 1, 0.0F, true));

        ModelRenderer bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, -2.4375F, 9.5F);
        setRotationAngle(bone8, 0.2793F, 0.0F, 0.0F);
        gun.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 33, 68, -2.5F, -12.1982F, 1.4807F, 5, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 33, 68, -2.5F, -11.6514F, 1.4807F, 5, 3, 1, 0.0F, true));

        ModelRenderer bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone9, 0.0F, 0.2618F, 0.0F);
        bone8.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 33, 68, 1.7728F, -11.8545F, 2.0433F, 1, 3, 1, 0.0F, true));

        ModelRenderer bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone10, 0.0F, -0.2618F, 0.0F);
        bone8.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 33, 68, -2.7727F, -11.8545F, 2.0433F, 1, 3, 1, 0.0F, false));

        ModelRenderer bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, 3.5F, 5.0F);
        setRotationAngle(bone11, 0.2618F, 0.0F, 0.0F);
        gun.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 28, 38, -2.0F, -13.518F, -1.78F, 4, 10, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 28, 38, 1.7071F, -13.518F, -1.0729F, 1, 11, 3, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 28, 38, 1.7071F, -13.518F, 1.5129F, 1, 11, 3, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 28, 38, -2.7071F, -13.518F, -1.0729F, 1, 11, 3, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 28, 38, -2.7071F, -13.518F, 1.5129F, 1, 11, 3, 0.0F, true));
        bone11.cubeList.add(new ModelBox(bone11, 28, 38, -2.0F, -13.518F, 4.22F, 4, 11, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 36, 14, -2.0F, -4.9242F, -0.78F, 4, 2, 5, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 35, 71, -1.5F, -11.2836F, -2.78F, 3, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 90, 32, 1.7969F, -11.518F, -0.28F, 1, 8, 4, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 90, 32, -2.7969F, -11.518F, -0.28F, 1, 8, 4, 0.0F, true));

        ModelRenderer bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone12, 0.0F, 0.7854F, 0.0F);
        bone11.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 28, 38, -2.2769F, -13.518F, 4.1053F, 1, 11, 1, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 28, 38, -5.1053F, -13.518F, 1.2769F, 1, 11, 1, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 28, 38, -1.1555F, -13.518F, -2.6729F, 1, 11, 1, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 28, 38, 1.6729F, -13.518F, 0.1555F, 1, 11, 1, 0.0F, false));

        ModelRenderer bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.0F, -2.0903F, -4.6094F);
        setRotationAngle(bone13, -0.0873F, 0.0F, 0.0F);
        bone11.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 35, 71, -1.5F, -9.3216F, -0.8917F, 3, 1, 2, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 40, 18, -2.7813F, -10.6341F, 2.9833F, 1, 1, 2, 0.0F, false));

        ModelRenderer bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, -2.0903F, -4.6094F);
        setRotationAngle(bone14, -0.3491F, 0.0F, 0.0F);
        bone11.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 35, 71, -1.5F, -8.8073F, -4.0151F, 3, 1, 1, 0.0F, false));

        ModelRenderer bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, -2.0903F, -4.6094F);
        setRotationAngle(bone15, -0.6981F, 0.0F, 0.0F);
        bone11.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 35, 71, -1.5F, -6.9632F, -7.4432F, 3, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 94, 43, -0.5F, -11.4163F, -6.6463F, 1, 3, 1, 0.0F, false));

        ModelRenderer bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, -2.0903F, -4.6094F);
        setRotationAngle(bone16, -0.9599F, 0.0F, 0.0F);
        bone11.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 35, 71, -1.5F, -4.8335F, -9.7329F, 3, 1, 1, 0.0F, false));

        ModelRenderer bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, -2.0903F, -4.6094F);
        setRotationAngle(bone17, -1.309F, 0.0F, 0.0F);
        bone11.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 35, 71, -1.5F, -1.2735F, -12.4571F, 3, 1, 2, 0.0F, false));

        ModelRenderer bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(1.7969F, -16.8601F, 6.8281F);
        setRotationAngle(bone20, 0.1745F, 0.0F, 0.0F);
        gun.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 36, 3, -0.5F, -0.0F, -1.0F, 1, 3, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 36, 3, -1.0F, -0.0F, -1.0F, 1, 3, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 36, 3, -3.5938F, -0.0F, -1.0F, 1, 3, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 36, 3, -4.0938F, -0.0F, -1.0F, 1, 3, 1, 0.0F, true));
        bone20.cubeList.add(new ModelBox(bone20, 36, 3, -2.7969F, 0.9875F, -1.1583F, 2, 2, 1, 0.0F, false));
        bone20.cubeList.add(new ModelBox(bone20, 36, 3, -2.7969F, -3.5082F, -20.8962F, 2, 3, 1, 0.0F, false));

        ModelRenderer bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(-1.7969F, -16.8601F, 5.1719F);
        setRotationAngle(bone21, -0.1745F, 0.0F, 0.0F);
        gun.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 36, 3, -0.5F, -0.1139F, 0.6463F, 1, 3, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 36, 3, 0.0F, -0.1139F, 0.6463F, 1, 3, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 36, 3, 2.5938F, -0.1139F, 0.6463F, 1, 3, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 36, 3, 3.0938F, -0.1139F, 0.6463F, 1, 3, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 36, 3, 0.7969F, 0.8681F, 0.8353F, 2, 2, 1, 0.0F, true));
        bone21.cubeList.add(new ModelBox(bone21, 36, 3, 0.7969F, 3.3943F, -19.2499F, 2, 3, 1, 0.0F, false));

        ModelRenderer bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(3.0F, -9.0625F, -5.0F);
        setRotationAngle(bone22, 0.0F, 0.0F, -1.0472F);
        gun.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 8, 40, 0.0F, -1.0F, 0.0F, 1, 1, 14, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 13, 36, 0.0F, -1.0F, -10.0F, 1, 1, 10, 0.0F, false));

        ModelRenderer bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(-3.0F, -9.0625F, -5.0F);
        setRotationAngle(bone23, 0.0F, 0.0F, 1.0472F);
        gun.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 11, 36, -1.0F, -1.0F, 0.0F, 1, 1, 14, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 14, 36, -1.0F, -1.0F, -10.0F, 1, 1, 10, 0.0F, true));

        ModelRenderer bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, -8.0F, -5.0F);
        setRotationAngle(bone, 1.0472F, 0.0F, 0.0F);
        gun.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 8, 40, -3.0F, -1.0F, 0.0F, 6, 1, 2, 0.0F, false));

        ModelRenderer bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -10.4375F, 9.5F);
        setRotationAngle(bone24, -0.9599F, 0.0F, 0.0F);
        gun.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 44, 86, -1.5F, 0.0F, -1.0F, 3, 1, 1, 0.0F, true));

        ModelRenderer bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(-3.5F, -11.4375F, 3.0F);
        setRotationAngle(bone25, 0.0F, 0.0F, 1.0472F);
        gun.addChild(bone25);
        bone25.cubeList.add(new ModelBox(bone25, 43, 74, 0.0F, -1.0F, -3.5F, 1, 1, 7, 0.0F, false));
        bone25.cubeList.add(new ModelBox(bone25, 43, 78, 0.7679F, -7.0622F, -3.5F, 1, 1, 7, 0.0F, false));

        ModelRenderer bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(3.5F, -11.4375F, 3.0F);
        setRotationAngle(bone26, 0.0F, 0.0F, -1.0472F);
        gun.addChild(bone26);
        bone26.cubeList.add(new ModelBox(bone26, 36, 79, -1.0F, -1.0F, -3.5F, 1, 1, 7, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 40, 75, -1.7679F, -7.0622F, -3.5F, 1, 1, 7, 0.0F, true));

        ModelRenderer bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(3.5F, -11.4375F, -0.5F);
        setRotationAngle(bone27, 0.0F, -1.1345F, 0.0F);
        gun.addChild(bone27);
        bone27.cubeList.add(new ModelBox(bone27, 47, 90, -1.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 49, 74, 0.8126F, -2.0F, 0.8452F, 1, 2, 1, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 47, 90, 2.6252F, -2.0F, 1.6905F, 1, 2, 1, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 47, 90, 4.4379F, -2.0F, 2.5357F, 1, 2, 1, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 36, 74, 3.3858F, -2.0F, 8.3025F, 1, 2, 1, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 43, 74, 1.5732F, -2.0F, 7.4573F, 1, 2, 1, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 36, 74, -0.2394F, -2.0F, 6.612F, 1, 2, 1, 0.0F, true));
        bone27.cubeList.add(new ModelBox(bone27, 43, 74, -2.052F, -2.0F, 5.7668F, 1, 2, 1, 0.0F, true));

        ModelRenderer bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(-3.5F, -11.4375F, -0.5F);
        setRotationAngle(bone28, 0.0F, 1.1345F, 0.0F);
        gun.addChild(bone28);
        bone28.cubeList.add(new ModelBox(bone28, 43, 74, 0.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F, false));
        bone28.cubeList.add(new ModelBox(bone28, 36, 74, -1.8126F, -2.0F, 0.8452F, 1, 2, 1, 0.0F, false));
        bone28.cubeList.add(new ModelBox(bone28, 36, 74, -3.6252F, -2.0F, 1.6905F, 1, 2, 1, 0.0F, false));
        bone28.cubeList.add(new ModelBox(bone28, 48, 73, -5.4378F, -2.0F, 2.5357F, 1, 2, 1, 0.0F, false));
        bone28.cubeList.add(new ModelBox(bone28, 55, 77, -4.3858F, -2.0F, 8.3025F, 1, 2, 1, 0.0F, false));
        bone28.cubeList.add(new ModelBox(bone28, 55, 77, -2.5732F, -2.0F, 7.4573F, 1, 2, 1, 0.0F, false));
        bone28.cubeList.add(new ModelBox(bone28, 55, 77, -0.7606F, -2.0F, 6.612F, 1, 2, 1, 0.0F, false));
        bone28.cubeList.add(new ModelBox(bone28, 55, 77, 1.052F, -2.0F, 5.7668F, 1, 2, 1, 0.0F, false));

        ModelRenderer bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(0.0F, -8.7245F, -8.7979F);
        setRotationAngle(bone29, -0.2618F, 0.0F, 0.0F);
        gun.addChild(bone29);
        bone29.cubeList.add(new ModelBox(bone29, 8, 40, -3.0F, -1.5067F, -3.0418F, 6, 2, 4, 0.0F, false));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(-9.0F, 2.8281F, 0.5469F);
        setRotationAngle(magazine, 0.1745F, 0.0F, 0.0F);
        glock.addChild(magazine);
        magazine.cubeList.add(new ModelBox(magazine, 5, 72, 7.0F, -2.0625F, 2.0F, 4, 1, 6, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 5, 72, 7.0F, -10.0625F, 3.0F, 1, 8, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 5, 72, 10.0F, -10.0625F, 3.0F, 1, 8, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 5, 72, 8.0F, -10.0625F, 7.0F, 2, 8, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 5, 72, 8.0F, -9.0625F, 3.0F, 2, 7, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 6, 101, 8.5F, -9.7656F, 3.0469F, 1, 1, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 6, 101, 8.0F, -10.2656F, 6.0F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 6, 101, 8.0F, -10.2656F, 3.5781F, 2, 2, 2, 0.0F, false));

        ModelRenderer bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(-7.5F, -6.0625F, 5.5F);
        setRotationAngle(bone19, 0.0F, 0.0F, -0.2618F);
        magazine.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 5, 72, 17.9049F, -0.0756F, -2.5F, 1, 1, 5, 0.0F, true));

        ModelRenderer bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(7.5F, -6.0625F, 5.5F);
        setRotationAngle(bone18, 0.0F, 0.0F, 0.2618F);
        magazine.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 5, 72, -1.5182F, -4.7343F, -2.5F, 1, 1, 5, 0.0F, false));

        initAnimations();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
