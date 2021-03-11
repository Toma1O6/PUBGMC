package dev.toma.pubgmc.client.models.weapons;

import dev.toma.pubgmc.animation.HeldAnimation;
import dev.toma.pubgmc.animation.HeldAnimation.HeldStyle;
import dev.toma.pubgmc.animation.ReloadAnimation;
import dev.toma.pubgmc.animation.ReloadAnimation.ReloadStyle;
import dev.toma.pubgmc.client.util.ModelTransformationHelper;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

public class ModelUmp45 extends ModelGun {
    private final ModelRenderer ironsights;
    private final ModelRenderer bone27;
    private final ModelRenderer bone28;
    private final ModelRenderer magazine;
    private final ModelRenderer ump45;
    private final ModelRenderer rail1;
    private final ModelRenderer bone7;
    private final ModelRenderer bone8;
    private final ModelRenderer rail4;
    private final ModelRenderer bone15;
    private final ModelRenderer bone16;
    private final ModelRenderer rail2;
    private final ModelRenderer bone6;
    private final ModelRenderer bone9;
    private final ModelRenderer rail3;
    private final ModelRenderer bone10;
    private final ModelRenderer bone11;
    private final ModelRenderer bone26;
    private final ModelRenderer bone12;
    private final ModelRenderer bone13;
    private final ModelRenderer bone14;
    private final ModelRenderer bone5;
    private final ModelRenderer bone21;
    private final ModelRenderer bone23;
    private final ModelRenderer bone34;
    private final ModelRenderer bone35;
    private final ModelRenderer bone36;
    private final ModelRenderer bone22;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone;
    private final ModelRenderer bone18;
    private final ModelRenderer bone31;
    private final ModelRenderer bone32;
    private final ModelRenderer bone33;
    private final ModelRenderer bone29;
    private final ModelRenderer bone30;
    private final ModelRenderer bone24;
    private final ModelRenderer bone25;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;
    private final ModelRenderer charging_handle;
    private final ModelRenderer bone17;

    // Created by OfficialMajonaise
    public ModelUmp45() {
        textureWidth = 512;
        textureHeight = 512;

        ironsights = new ModelRenderer(this);
        ironsights.setRotationPoint(0.0F, 24.0F, 0.0F);
        ironsights.cubeList.add(new ModelBox(ironsights, 89, 24, -2.344F, -15.864F, 23.472F, 1, 2, 2, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 73, 22, -1.0F, -16.464F, 23.0F, 2, 3, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 13, 17, -0.5F, -15.964F, 23.232F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 92, 41, 1.344F, -15.864F, 23.472F, 1, 2, 2, 0.0F, true));
        ironsights.cubeList.add(new ModelBox(ironsights, 66, 20, -1.866F, -17.83F, 23.0F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 66, 20, 0.866F, -17.83F, 23.0F, 1, 1, 1, 0.0F, false));
        ironsights.cubeList.add(new ModelBox(ironsights, 66, 20, -0.5F, -19.1961F, 23.0F, 1, 1, 1, 0.0F, false));

        bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(-0.5F, 0.0F, 0.0F);
        ironsights.addChild(bone27);
        setRotationAngle(bone27, 0.0F, 0.0F, -0.5236F);
        bone27.cubeList.add(new ModelBox(bone27, 66, 20, 8.598F, -13.8922F, 23.0F, 1, 1, 1, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 66, 20, 9.9641F, -15.2582F, 23.0F, 1, 1, 1, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 66, 20, 8.598F, -16.6243F, 23.0F, 1, 1, 1, 0.0F, false));
        bone27.cubeList.add(new ModelBox(bone27, 66, 20, 7.232F, -15.2582F, 23.0F, 1, 1, 1, 0.0F, false));

        bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(0.5F, 0.0F, 0.0F);
        ironsights.addChild(bone28);
        setRotationAngle(bone28, 0.0F, 0.0F, 0.5236F);
        bone28.cubeList.add(new ModelBox(bone28, 66, 20, -8.232F, -15.2582F, 23.0F, 1, 1, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 66, 20, -10.9641F, -15.2582F, 23.0F, 1, 1, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 66, 20, -9.598F, -16.6243F, 23.0F, 1, 1, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 66, 20, -9.598F, -13.8922F, 23.0F, 1, 1, 1, 0.0F, true));

        magazine = new ModelRenderer(this);
        magazine.setRotationPoint(0.0F, 24.0F, -1.728F);
        setRotationAngle(magazine, -0.2618F, 0.0F, 0.0F);
        magazine.cubeList.add(new ModelBox(magazine, 1, 64, -2.0F, -8.64F, -1.5F, 4, 22, 2, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 1, 64, -2.0F, -8.64F, 1.5F, 4, 22, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 14, 85, -2.0F, 11.36F, 0.5F, 4, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -1.5F, -8.64F, 0.5F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -1.5F, -2.64F, 0.5F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -1.5F, 3.36F, 0.5F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 30, 42, -1.5F, -6.64F, 0.5F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 30, 42, -1.5F, -0.64F, 0.5F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 30, 42, -1.5F, 5.36F, 0.5F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -1.5F, -5.64F, 0.5F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -1.5F, 0.36F, 0.5F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -1.5F, 6.36F, 0.5F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -1.5F, 9.36F, 0.5F, 3, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 30, 42, -1.5F, -3.64F, 0.5F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 30, 42, -1.5F, 2.36F, 0.5F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 30, 42, -1.5F, 8.36F, 0.5F, 3, 1, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 73, 1.0F, -10.64F, -1.5F, 1, 2, 6, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 73, -2.0F, -10.64F, -1.5F, 1, 2, 6, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 73, -1.0F, -10.04F, 3.5F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -1.0F, -11.16F, 3.044F, 2, 2, 1, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -1.0F, -11.16F, -0.54F, 2, 2, 3, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -0.2F, -10.96F, -1.116F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -0.2F, -10.36F, -1.116F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -0.8F, -10.96F, -1.116F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 494, -0.8F, -10.36F, -1.116F, 1, 1, 5, 0.0F, false));
        magazine.cubeList.add(new ModelBox(magazine, 0, 73, -1.0F, -9.64F, -1.5F, 2, 1, 1, 0.0F, false));

        ump45 = new ModelRenderer(this);
        ump45.setRotationPoint(0.0F, 24.0F, 0.0F);
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -1.5F, -7.0F, -21.0F, 3, 1, 19, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -1.5F, -7.2679F, -23.0F, 3, 2, 2, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -1.0F, -5.5719F, -22.8F, 2, 1, 1, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -9.0F, -23.0F, 5, 2, 21, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 67, 21, -2.5F, -13.0F, -23.0F, 4, 1, 25, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 67, 21, 1.5F, -13.0F, -12.0F, 1, 1, 14, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 67, 21, 1.5F, -13.0F, -23.0F, 1, 1, 2, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 36, 40, 1.388F, -13.0F, -21.0F, 1, 1, 9, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 67, 21, -2.5F, -13.0F, 2.0F, 5, 1, 25, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.0F, -13.866F, -23.0F, 4, 1, 25, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.0F, -13.866F, 2.0F, 4, 1, 25, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -14.0F, 20.584F, 5, 1, 6, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, 1.5F, -15.0F, 20.584F, 1, 1, 4, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, 1.5F, -15.5F, 21.584F, 1, 1, 2, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, 1.5F, -16.5F, 20.584F, 1, 2, 1, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, 1.5F, -16.5F, 21.2539F, 1, 1, 1, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -15.0F, 20.584F, 1, 1, 4, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -15.5F, 21.584F, 1, 1, 2, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -16.5F, 20.584F, 1, 2, 1, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -16.5F, 21.2539F, 1, 1, 1, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -1.5F, -15.0F, 20.584F, 3, 1, 1, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, 1.0F, -16.7321F, -23.0F, 1, 2, 3, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.0F, -16.7321F, -23.0F, 1, 2, 3, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.0F, -15.0F, -23.0F, 4, 2, 4, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -1.0F, -15.776F, -23.0F, 2, 2, 3, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -10.0F, -23.0F, 1, 1, 21, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -12.0F, -23.0F, 1, 1, 21, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -11.0F, -23.0F, 1, 1, 3, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -11.0F, -16.0F, 1, 1, 2, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -11.0F, -10.0F, 1, 1, 2, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -11.0F, -4.0F, 1, 1, 2, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, 1.5F, -10.0F, -23.0F, 1, 1, 21, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 70, 23, 1.5F, -12.0F, -23.0F, 1, 1, 21, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, 1.5F, -11.0F, -23.0F, 1, 1, 3, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 33, 6, 0.2071F, -11.0F, -17.0F, 1, 1, 14, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 33, 6, 0.2071F, -11.0F, -31.0F, 1, 1, 14, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 33, 6, -1.2071F, -11.0F, -17.0F, 1, 1, 14, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 33, 6, -1.2071F, -11.0F, -31.0F, 1, 1, 14, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 33, 6, -0.5F, -10.2929F, -17.0F, 1, 1, 14, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 33, 6, -0.5F, -10.2929F, -31.0F, 1, 1, 14, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 33, 6, -0.5F, -11.7071F, -17.0F, 1, 1, 14, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 33, 6, -0.5F, -11.7071F, -31.0F, 1, 1, 14, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, 1.5F, -11.0F, -16.0F, 1, 1, 2, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, 1.5F, -11.0F, -10.0F, 1, 1, 2, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, 1.5F, -11.0F, -4.0F, 1, 1, 2, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, 1.5F, -12.0F, -2.0F, 1, 5, 13, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, 1.5F, -12.0F, 11.0F, 1, 5, 10, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.0F, -7.0F, -2.0F, 4, 2, 11, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -1.5F, -5.134F, 8.0F, 3, 1, 1, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -1.5F, -2.9019F, 9.866F, 3, 1, 6, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.0F, -7.0F, 9.0F, 4, 1, 11, 0.0F, false));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -9.0F, -2.0F, 1, 2, 13, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -12.0F, -2.0F, 1, 3, 1, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -12.0F, 7.0F, 1, 3, 4, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 5, 12, -2.26F, -12.0F, -1.0F, 1, 3, 8, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 5, 12, -2.38F, -11.0F, -1.0F, 1, 1, 2, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -12.0F, 11.0F, 1, 5, 10, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 66, 20, -2.5F, -12.0F, 21.0F, 5, 3, 6, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 20, 88, -2.0F, -13.552F, 26.68F, 4, 5, 1, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 20, 88, -1.5F, -13.552F, 27.546F, 3, 1, 1, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 20, 88, -1.5F, -9.552F, 27.546F, 3, 1, 1, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 20, 88, -1.5F, -12.5253F, 34.2812F, 3, 1, 12, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 20, 88, -1.0F, -12.0613F, 31.6972F, 2, 5, 2, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 20, 88, -1.0F, -4.2773F, 42.5772F, 2, 2, 2, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 20, 88, -1.0F, -13.1013F, 27.1212F, 2, 4, 2, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 20, 88, -1.5F, -2.4612F, 43.5306F, 3, 1, 3, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 20, 88, -1.501F, -12.1732F, 44.5306F, 3, 10, 2, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 20, 88, -2.0F, -13.0532F, 45.5306F, 4, 12, 2, 0.0F, true));
        ump45.cubeList.add(new ModelBox(ump45, 0, 76, -1.5F, -12.0F, -3.0F, 3, 3, 1, 0.0F, false));

        rail1 = new ModelRenderer(this);
        rail1.setRotationPoint(-0.5F, 0.0F, 0.0F);
        ump45.addChild(rail1);
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -0.5F, -5.0F, -6.0F, 2, 1, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -0.5F, -5.0F, -10.0F, 2, 1, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -0.5F, -5.0F, -14.0F, 2, 1, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -0.5F, -5.0F, -18.0F, 2, 1, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -0.5F, -5.0F, -8.0F, 2, 1, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -0.5F, -5.0F, -12.0F, 2, 1, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -0.5F, -5.0F, -16.0F, 2, 1, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -0.5F, -5.0F, -20.0F, 2, 1, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -1.366F, -4.499F, -7.0F, 2, 0, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -1.366F, -4.499F, -11.0F, 2, 0, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -1.366F, -4.499F, -15.0F, 2, 0, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -1.366F, -4.499F, -19.0F, 2, 0, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -1.366F, -4.499F, -9.0F, 2, 0, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -1.366F, -4.499F, -13.0F, 2, 0, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, -1.366F, -4.499F, -17.0F, 2, 0, 1, 0.0F, false));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, 0.366F, -4.499F, -7.0F, 2, 0, 1, 0.0F, true));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, 0.366F, -4.499F, -11.0F, 2, 0, 1, 0.0F, true));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, 0.366F, -4.499F, -15.0F, 2, 0, 1, 0.0F, true));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, 0.366F, -4.499F, -19.0F, 2, 0, 1, 0.0F, true));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, 0.366F, -4.499F, -9.0F, 2, 0, 1, 0.0F, true));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, 0.366F, -4.499F, -13.0F, 2, 0, 1, 0.0F, true));
        rail1.cubeList.add(new ModelBox(rail1, 104, 93, 0.366F, -4.499F, -17.0F, 2, 0, 1, 0.0F, true));
        rail1.cubeList.add(new ModelBox(rail1, 69, 74, -1.0F, -6.0F, -20.0F, 3, 1, 15, 0.0F, false));

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.5F, -4.5F, -5.5F);
        rail1.addChild(bone7);
        setRotationAngle(bone7, 0.0F, 0.0F, -0.5236F);
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 0.616F, -0.067F, -0.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 0.616F, -0.067F, -4.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 0.616F, -0.067F, -8.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 0.616F, -0.067F, -12.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 0.616F, -0.067F, -2.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 0.616F, -0.067F, -6.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 0.616F, -0.067F, -10.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 0.616F, -0.067F, -14.5F, 1, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 1.616F, -0.067F, -1.5F, 0, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 1.616F, -0.067F, -5.5F, 0, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 1.616F, -0.067F, -9.5F, 0, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 1.616F, -0.067F, -13.5F, 0, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 1.616F, -0.067F, -3.5F, 0, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 1.616F, -0.067F, -7.5F, 0, 1, 1, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 104, 93, 1.616F, -0.067F, -11.5F, 0, 1, 1, 0.0F, false));

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.5F, -4.5F, -5.5F);
        rail1.addChild(bone8);
        setRotationAngle(bone8, 0.0F, 0.0F, 0.5236F);
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -0.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -4.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -8.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -12.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -2.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -6.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -10.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -14.5F, 1, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -1.5F, 0, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -5.5F, 0, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -9.5F, 0, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -13.5F, 0, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -3.5F, 0, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -7.5F, 0, 1, 1, 0.0F, true));
        bone8.cubeList.add(new ModelBox(bone8, 104, 93, -1.616F, -0.067F, -11.5F, 0, 1, 1, 0.0F, true));

        rail4 = new ModelRenderer(this);
        rail4.setRotationPoint(-0.5F, -19.488F, -3.0F);
        ump45.addChild(rail4);
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -0.5F, 4.0F, 14.0F, 2, 1, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -0.5F, 4.0F, 20.0F, 2, 1, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -0.5F, 4.0F, 22.0F, 2, 1, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -0.5F, 4.0F, 10.0F, 2, 1, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -0.5F, 4.0F, 16.0F, 2, 1, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -0.5F, 4.0F, 6.0F, 2, 1, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -0.5F, 4.0F, 2.0F, 2, 1, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -0.5F, 4.0F, 12.0F, 2, 1, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -0.5F, 4.0F, 18.0F, 2, 1, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -0.5F, 4.0F, 8.0F, 2, 1, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -0.5F, 4.0F, 4.0F, 2, 1, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -0.5F, 4.0F, 0.0F, 2, 1, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -1.366F, 4.499F, 13.0F, 2, 0, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -1.366F, 4.499F, 19.0F, 2, 0, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -1.366F, 4.499F, 21.0F, 2, 0, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -1.366F, 4.499F, 9.0F, 2, 0, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -1.366F, 4.499F, 15.0F, 2, 0, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -1.366F, 4.499F, 5.0F, 2, 0, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -1.366F, 4.499F, 1.0F, 2, 0, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -1.366F, 4.499F, 11.0F, 2, 0, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -1.366F, 4.499F, 17.0F, 2, 0, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -1.366F, 4.499F, 7.0F, 2, 0, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, -1.366F, 4.499F, 3.0F, 2, 0, 1, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, 0.366F, 4.499F, 13.0F, 2, 0, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, 0.366F, 4.499F, 19.0F, 2, 0, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, 0.366F, 4.499F, 21.0F, 2, 0, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, 0.366F, 4.499F, 9.0F, 2, 0, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, 0.366F, 4.499F, 15.0F, 2, 0, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, 0.366F, 4.499F, 5.0F, 2, 0, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, 0.366F, 4.499F, 1.0F, 2, 0, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, 0.366F, 4.499F, 11.0F, 2, 0, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, 0.366F, 4.499F, 17.0F, 2, 0, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, 0.366F, 4.499F, 7.0F, 2, 0, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 104, 93, 0.366F, 4.499F, 3.0F, 2, 0, 1, 0.0F, true));
        rail4.cubeList.add(new ModelBox(rail4, 69, 74, -1.0F, 5.0F, 0.0F, 3, 1, 12, 0.0F, false));
        rail4.cubeList.add(new ModelBox(rail4, 69, 74, -1.0F, 5.0F, 12.0F, 3, 1, 11, 0.0F, false));

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.5F, 4.5F, -5.5F);
        rail4.addChild(bone15);
        setRotationAngle(bone15, 0.0F, 0.0F, 0.5236F);
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 0.616F, -0.933F, 19.5F, 1, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 0.616F, -0.933F, 25.5F, 1, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 0.616F, -0.933F, 27.5F, 1, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 0.616F, -0.933F, 15.5F, 1, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 0.616F, -0.933F, 21.5F, 1, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 0.616F, -0.933F, 11.5F, 1, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 0.616F, -0.933F, 7.5F, 1, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 0.616F, -0.933F, 17.5F, 1, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 0.616F, -0.933F, 23.5F, 1, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 0.616F, -0.933F, 13.5F, 1, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 0.616F, -0.933F, 9.5F, 1, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 0.616F, -0.933F, 5.5F, 1, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 1.616F, -0.933F, 18.5F, 0, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 1.616F, -0.933F, 24.5F, 0, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 1.616F, -0.933F, 26.5F, 0, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 1.616F, -0.933F, 14.5F, 0, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 1.616F, -0.933F, 20.5F, 0, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 1.616F, -0.933F, 10.5F, 0, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 1.616F, -0.933F, 6.5F, 0, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 1.616F, -0.933F, 16.5F, 0, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 1.616F, -0.933F, 22.5F, 0, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 1.616F, -0.933F, 12.5F, 0, 1, 1, 0.0F, false));
        bone15.cubeList.add(new ModelBox(bone15, 104, 93, 1.616F, -0.933F, 8.5F, 0, 1, 1, 0.0F, false));

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.5F, 4.5F, -5.5F);
        rail4.addChild(bone16);
        setRotationAngle(bone16, 0.0F, 0.0F, -0.5236F);
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 19.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 25.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 27.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 15.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 21.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 11.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 7.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 17.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 23.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 13.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 9.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 5.5F, 1, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 18.5F, 0, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 24.5F, 0, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 26.5F, 0, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 14.5F, 0, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 20.5F, 0, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 10.5F, 0, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 6.5F, 0, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 16.5F, 0, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 22.5F, 0, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 12.5F, 0, 1, 1, 0.0F, true));
        bone16.cubeList.add(new ModelBox(bone16, 104, 93, -1.616F, -0.933F, 8.5F, 0, 1, 1, 0.0F, true));

        rail2 = new ModelRenderer(this);
        rail2.setRotationPoint(-8.364F, -8.912F, 0.0F);
        ump45.addChild(rail2);
        setRotationAngle(rail2, 0.0F, 0.0F, 1.5708F);
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -0.5F, -5.0F, -6.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -0.5F, -5.0F, -10.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -0.5F, -5.0F, -14.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -0.5F, -5.0F, -18.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -0.5F, -5.0F, -8.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -0.5F, -5.0F, -12.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -0.5F, -5.0F, -16.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -0.5F, -5.0F, -20.0F, 2, 1, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -1.366F, -4.499F, -7.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -1.366F, -4.499F, -11.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -1.366F, -4.499F, -15.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -1.366F, -4.499F, -19.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -1.366F, -4.499F, -9.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -1.366F, -4.499F, -13.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, -1.366F, -4.499F, -17.0F, 2, 0, 1, 0.0F, false));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, 0.366F, -4.499F, -7.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, 0.366F, -4.499F, -11.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, 0.366F, -4.499F, -15.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, 0.366F, -4.499F, -19.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, 0.366F, -4.499F, -9.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, 0.366F, -4.499F, -13.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 104, 93, 0.366F, -4.499F, -17.0F, 2, 0, 1, 0.0F, true));
        rail2.cubeList.add(new ModelBox(rail2, 69, 74, -1.0F, -6.0F, -20.0F, 3, 1, 15, 0.0F, false));

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.5F, -4.5F, -5.5F);
        rail2.addChild(bone6);
        setRotationAngle(bone6, 0.0F, 0.0F, -0.5236F);
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 0.616F, -0.067F, -0.5F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 0.616F, -0.067F, -4.5F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 0.616F, -0.067F, -8.5F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 0.616F, -0.067F, -12.5F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 0.616F, -0.067F, -2.5F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 0.616F, -0.067F, -6.5F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 0.616F, -0.067F, -10.5F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 0.616F, -0.067F, -14.5F, 1, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 1.616F, -0.067F, -1.5F, 0, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 1.616F, -0.067F, -5.5F, 0, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 1.616F, -0.067F, -9.5F, 0, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 1.616F, -0.067F, -13.5F, 0, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 1.616F, -0.067F, -3.5F, 0, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 1.616F, -0.067F, -7.5F, 0, 1, 1, 0.0F, false));
        bone6.cubeList.add(new ModelBox(bone6, 104, 93, 1.616F, -0.067F, -11.5F, 0, 1, 1, 0.0F, false));

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.5F, -4.5F, -5.5F);
        rail2.addChild(bone9);
        setRotationAngle(bone9, 0.0F, 0.0F, 0.5236F);
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -0.5F, 1, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -4.5F, 1, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -8.5F, 1, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -12.5F, 1, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -2.5F, 1, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -6.5F, 1, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -10.5F, 1, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -14.5F, 1, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -1.5F, 0, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -5.5F, 0, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -9.5F, 0, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -13.5F, 0, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -3.5F, 0, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -7.5F, 0, 1, 1, 0.0F, true));
        bone9.cubeList.add(new ModelBox(bone9, 104, 93, -1.616F, -0.067F, -11.5F, 0, 1, 1, 0.0F, true));

        rail3 = new ModelRenderer(this);
        rail3.setRotationPoint(8.364F, -8.912F, 0.0F);
        ump45.addChild(rail3);
        setRotationAngle(rail3, 0.0F, 0.0F, -1.5708F);
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -1.5F, -5.0F, -6.0F, 2, 1, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -1.5F, -5.0F, -10.0F, 2, 1, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -1.5F, -5.0F, -14.0F, 2, 1, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -1.5F, -5.0F, -18.0F, 2, 1, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -1.5F, -5.0F, -8.0F, 2, 1, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -1.5F, -5.0F, -12.0F, 2, 1, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -1.5F, -5.0F, -16.0F, 2, 1, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -1.5F, -5.0F, -20.0F, 2, 1, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -0.634F, -4.499F, -7.0F, 2, 0, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -0.634F, -4.499F, -11.0F, 2, 0, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -0.634F, -4.499F, -15.0F, 2, 0, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -0.634F, -4.499F, -19.0F, 2, 0, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -0.634F, -4.499F, -9.0F, 2, 0, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -0.634F, -4.499F, -13.0F, 2, 0, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -0.634F, -4.499F, -17.0F, 2, 0, 1, 0.0F, true));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -2.366F, -4.499F, -7.0F, 2, 0, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -2.366F, -4.499F, -11.0F, 2, 0, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -2.366F, -4.499F, -15.0F, 2, 0, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -2.366F, -4.499F, -19.0F, 2, 0, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -2.366F, -4.499F, -9.0F, 2, 0, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -2.366F, -4.499F, -13.0F, 2, 0, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 104, 93, -2.366F, -4.499F, -17.0F, 2, 0, 1, 0.0F, false));
        rail3.cubeList.add(new ModelBox(rail3, 69, 74, -2.0F, -6.0F, -20.0F, 3, 1, 15, 0.0F, true));

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(-0.5F, -4.5F, -5.5F);
        rail3.addChild(bone10);
        setRotationAngle(bone10, 0.0F, 0.0F, 0.5236F);
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -0.5F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -4.5F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -8.5F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -12.5F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -2.5F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -6.5F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -10.5F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -14.5F, 1, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -1.5F, 0, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -5.5F, 0, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -9.5F, 0, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -13.5F, 0, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -3.5F, 0, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -7.5F, 0, 1, 1, 0.0F, true));
        bone10.cubeList.add(new ModelBox(bone10, 104, 93, -1.616F, -0.067F, -11.5F, 0, 1, 1, 0.0F, true));

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(-0.5F, -4.5F, -5.5F);
        rail3.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.5236F);
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 0.616F, -0.067F, -0.5F, 1, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 0.616F, -0.067F, -4.5F, 1, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 0.616F, -0.067F, -8.5F, 1, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 0.616F, -0.067F, -12.5F, 1, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 0.616F, -0.067F, -2.5F, 1, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 0.616F, -0.067F, -6.5F, 1, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 0.616F, -0.067F, -10.5F, 1, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 0.616F, -0.067F, -14.5F, 1, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 1.616F, -0.067F, -1.5F, 0, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 1.616F, -0.067F, -5.5F, 0, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 1.616F, -0.067F, -9.5F, 0, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 1.616F, -0.067F, -13.5F, 0, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 1.616F, -0.067F, -3.5F, 0, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 1.616F, -0.067F, -7.5F, 0, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 104, 93, 1.616F, -0.067F, -11.5F, 0, 1, 1, 0.0F, false));

        bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(-0.5F, 0.0F, 0.0F);
        ump45.addChild(bone26);
        setRotationAngle(bone26, -0.5236F, 0.0F, 0.0F);
        bone26.cubeList.add(new ModelBox(bone26, 66, 20, -2.0F, -25.4164F, 11.0224F, 1, 1, 5, 0.0F, false));
        bone26.cubeList.add(new ModelBox(bone26, 66, 20, 2.0F, -25.4164F, 11.0224F, 1, 1, 5, 0.0F, false));

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(1.75F, -14.0F, -21.0F);
        ump45.addChild(bone12);
        setRotationAngle(bone12, 0.5236F, 0.0F, 0.0F);
        bone12.cubeList.add(new ModelBox(bone12, 66, 20, -0.75F, -1.866F, 0.2321F, 1, 2, 2, 0.0F, false));
        bone12.cubeList.add(new ModelBox(bone12, 66, 20, -3.75F, -1.866F, 0.2321F, 1, 2, 2, 0.0F, true));

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-1.5F, -15.7321F, -21.5F);
        ump45.addChild(bone13);
        setRotationAngle(bone13, 0.0F, 0.0F, -0.5236F);
        bone13.cubeList.add(new ModelBox(bone13, 66, 20, -0.067F, -0.616F, -1.5F, 1, 2, 3, 0.0F, true));
        bone13.cubeList.add(new ModelBox(bone13, 66, 20, -0.616F, -0.6651F, 42.084F, 1, 2, 1, 0.0F, true));

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(1.5F, -15.7321F, -21.5F);
        ump45.addChild(bone14);
        setRotationAngle(bone14, 0.0F, 0.0F, 0.5236F);
        bone14.cubeList.add(new ModelBox(bone14, 66, 20, -0.933F, -0.616F, -1.5F, 1, 2, 3, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 66, 20, -0.384F, -0.6651F, 42.084F, 1, 2, 1, 0.0F, false));

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(-0.5F, 0.0F, 0.0F);
        ump45.addChild(bone5);
        setRotationAngle(bone5, 0.0F, 0.0F, -0.7854F);
        bone5.cubeList.add(new ModelBox(bone5, 33, 6, 7.9853F, -7.5711F, -17.0F, 1, 1, 14, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 33, 6, 7.9853F, -7.5711F, -31.0F, 1, 1, 14, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 33, 6, 7.2782F, -8.2782F, -17.0F, 1, 1, 14, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 33, 6, 7.2782F, -8.2782F, -31.0F, 1, 1, 14, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 33, 6, 7.2782F, -6.864F, -17.0F, 1, 1, 14, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 33, 6, 7.2782F, -6.864F, -31.0F, 1, 1, 14, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 33, 6, 6.5711F, -7.5711F, -17.0F, 1, 1, 14, 0.0F, false));
        bone5.cubeList.add(new ModelBox(bone5, 33, 6, 6.5711F, -7.5711F, -31.0F, 1, 1, 14, 0.0F, false));

        bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(0.0F, -4.634F, 8.5F);
        ump45.addChild(bone21);
        setRotationAngle(bone21, 0.5236F, 0.0F, 0.0F);
        bone21.cubeList.add(new ModelBox(bone21, 66, 20, -1.5F, 0.183F, -0.683F, 3, 2, 1, 0.0F, false));
        bone21.cubeList.add(new ModelBox(bone21, 66, 20, -1.5F, 5.049F, 5.0131F, 3, 1, 5, 0.0F, false));

        bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.5F, 4.634F, -8.5F);
        bone21.addChild(bone23);
        setRotationAngle(bone23, -0.0873F, 0.0F, 0.0F);
        bone23.cubeList.add(new ModelBox(bone23, 66, 20, 1.0F, -4.3792F, 15.9963F, 1, 13, 4, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 66, 20, -3.0F, -4.3792F, 15.9963F, 1, 13, 4, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 66, 20, -3.0F, 7.6208F, 14.9963F, 1, 1, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 66, 20, 1.0F, 7.6208F, 14.9963F, 1, 1, 1, 0.0F, true));
        bone23.cubeList.add(new ModelBox(bone23, 66, 20, -2.5F, -4.3792F, 19.8623F, 4, 13, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 40, 17, -2.5F, 7.2208F, 15.9903F, 4, 1, 4, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 66, 20, -2.5F, -3.3792F, 15.1302F, 4, 12, 1, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 66, 20, -2.5F, 7.6208F, 14.1302F, 4, 1, 1, 0.0F, false));

        bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(-0.5F, 1.1208F, 17.9963F);
        bone23.addChild(bone34);
        setRotationAngle(bone34, 0.0F, -0.5236F, 0.0F);
        bone34.cubeList.add(new ModelBox(bone34, 66, 20, 2.1651F, -4.5F, 0.4821F, 1, 12, 1, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 66, 20, -3.1651F, -4.5F, -1.4821F, 1, 12, 1, 0.0F, false));
        bone34.cubeList.add(new ModelBox(bone34, 66, 20, -3.6651F, 6.5F, -2.3481F, 1, 1, 1, 0.0F, false));

        bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(-0.5F, 1.1208F, 17.9963F);
        bone23.addChild(bone35);
        setRotationAngle(bone35, 0.0F, 0.5236F, 0.0F);
        bone35.cubeList.add(new ModelBox(bone35, 66, 20, -3.1651F, -4.5F, 0.4821F, 1, 12, 1, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 66, 20, 2.1651F, -4.5F, -1.4821F, 1, 12, 1, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 66, 20, 2.6651F, 6.5F, -2.3481F, 1, 1, 1, 0.0F, true));

        bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(-0.5F, 8.1208F, 14.6302F);
        bone23.addChild(bone36);
        setRotationAngle(bone36, 0.5236F, 0.0F, 0.0F);
        bone36.cubeList.add(new ModelBox(bone36, 66, 20, -2.0F, -0.683F, -0.183F, 4, 1, 2, 0.0F, false));

        bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, -4.634F, -8.5F);
        ump45.addChild(bone22);
        setRotationAngle(bone22, -0.5236F, 0.0F, 0.0F);
        bone22.cubeList.add(new ModelBox(bone22, 66, 20, -1.5F, -7.817F, 16.2715F, 3, 1, 1, 0.0F, false));

        bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, -4.0F, 8.5F);
        ump45.addChild(bone19);
        setRotationAngle(bone19, 0.0F, 0.0F, 0.5236F);
        bone19.cubeList.add(new ModelBox(bone19, 66, 20, 0.2321F, -1.866F, -0.5F, 1, 1, 1, 0.0F, false));

        bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.0F, -4.0F, 8.5F);
        ump45.addChild(bone20);
        setRotationAngle(bone20, 0.0F, 0.0F, -0.5236F);
        bone20.cubeList.add(new ModelBox(bone20, 66, 20, -1.2321F, -1.866F, -0.5F, 1, 1, 1, 0.0F, true));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 0.0F, 0.0F);
        ump45.addChild(bone);
        setRotationAngle(bone, 0.5236F, 0.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 73, 21, -2.0F, -8.0F, -0.112F, 4, 3, 2, 0.0F, false));

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, 0.0F, -1.728F);
        ump45.addChild(bone18);
        setRotationAngle(bone18, -0.2618F, 0.0F, 0.0F);
        bone18.cubeList.add(new ModelBox(bone18, 66, 20, -2.501F, -9.0F, -2.0F, 5, 4, 7, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 66, 20, 1.5F, -5.0F, -2.0F, 1, 2, 7, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 79, -1.5F, -5.0F, -1.0F, 3, 1, 5, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 66, 20, -2.5F, -5.0F, -2.0F, 1, 2, 7, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 66, 20, -1.5F, -5.0F, -2.0F, 3, 2, 1, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 67, 20, -1.5F, -5.0F, 4.0F, 3, 2, 1, 0.0F, false));

        bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(0.0F, -13.052F, 28.046F);
        ump45.addChild(bone31);
        setRotationAngle(bone31, -0.1745F, 0.0F, 0.0F);
        bone31.cubeList.add(new ModelBox(bone31, 20, 88, -1.5F, -0.5792F, 0.4056F, 3, 1, 6, 0.0F, true));

        bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(0.0F, -13.052F, 28.046F);
        ump45.addChild(bone32);
        setRotationAngle(bone32, -0.4363F, 0.0F, 0.0F);
        bone32.cubeList.add(new ModelBox(bone32, 20, 88, -1.5F, 2.9608F, 1.9323F, 3, 1, 12, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 20, 88, -1.5F, 2.9608F, 13.9323F, 3, 1, 5, 0.0F, true));

        bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(0.0F, -3.2773F, 43.5772F);
        ump45.addChild(bone33);
        setRotationAngle(bone33, -0.2618F, 0.0F, 0.0F);
        bone33.cubeList.add(new ModelBox(bone33, 20, 88, -1.0F, -8.7071F, -1.2247F, 2, 8, 2, 0.0F, true));

        bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(0.0F, -9.052F, 28.18F);
        ump45.addChild(bone29);
        setRotationAngle(bone29, 0.0F, -0.5236F, 0.0F);
        bone29.cubeList.add(new ModelBox(bone29, 20, 88, 0.4821F, -0.5F, -1.433F, 1, 1, 1, 0.0F, true));
        bone29.cubeList.add(new ModelBox(bone29, 20, 88, 0.4821F, -4.5F, -1.433F, 1, 1, 1, 0.0F, true));

        bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(0.0F, -9.052F, 28.18F);
        ump45.addChild(bone30);
        setRotationAngle(bone30, 0.0F, 0.5236F, 0.0F);
        bone30.cubeList.add(new ModelBox(bone30, 20, 88, -1.4821F, -0.5F, -1.433F, 1, 1, 1, 0.0F, false));
        bone30.cubeList.add(new ModelBox(bone30, 20, 88, -1.4821F, -4.5F, -1.433F, 1, 1, 1, 0.0F, false));

        bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, -11.0F, 24.0F);
        ump45.addChild(bone24);
        setRotationAngle(bone24, 0.1745F, 0.0F, 0.0F);
        bone24.cubeList.add(new ModelBox(bone24, 66, 20, -2.5F, 1.4942F, -3.2192F, 5, 2, 5, 0.0F, true));
        bone24.cubeList.add(new ModelBox(bone24, 66, 20, -2.5F, 2.3566F, 1.1071F, 5, 1, 1, 0.0F, true));

        bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(0.0F, -11.0F, 24.0F);
        ump45.addChild(bone25);
        setRotationAngle(bone25, -0.3491F, 0.0F, 0.0F);
        bone25.cubeList.add(new ModelBox(bone25, 66, 20, -2.5F, 0.8533F, 2.5031F, 5, 1, 1, 0.0F, true));

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-0.5F, 0.0F, 0.0F);
        ump45.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, 0.5236F);
        bone2.cubeList.add(new ModelBox(bone2, 66, 20, -1.9019F, -7.5622F, -23.0F, 1, 2, 2, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 66, 20, -8.2321F, -11.2583F, -23.0F, 1, 1, 25, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 66, 20, -8.2321F, -11.2583F, 2.0F, 1, 1, 25, 0.0F, false));

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.5F, 0.0F, 0.0F);
        ump45.addChild(bone3);
        setRotationAngle(bone3, 0.0F, 0.0F, -0.5236F);
        bone3.cubeList.add(new ModelBox(bone3, 66, 20, 0.9019F, -7.5622F, -23.0F, 1, 2, 2, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 66, 20, 7.2321F, -11.2583F, -23.0F, 1, 1, 25, 0.0F, true));
        bone3.cubeList.add(new ModelBox(bone3, 66, 20, 7.2321F, -11.2583F, 2.0F, 1, 1, 25, 0.0F, true));

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.5F, 0.0F, 0.0F);
        ump45.addChild(bone4);
        setRotationAngle(bone4, 0.0F, 0.0F, -0.7854F);
        bone4.cubeList.add(new ModelBox(bone4, 66, 20, 2.8284F, -7.0711F, -21.0F, 1, 1, 19, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 66, 20, 2.8284F, -6.6569F, -21.0F, 1, 1, 19, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 66, 20, 4.9497F, -4.5355F, -21.0F, 1, 1, 19, 0.0F, true));
        bone4.cubeList.add(new ModelBox(bone4, 66, 20, 5.364F, -4.5355F, -21.0F, 1, 1, 19, 0.0F, true));

        charging_handle = new ModelRenderer(this);
        charging_handle.setRotationPoint(-0.5F, 24.0F, 0.0F);
        charging_handle.cubeList.add(new ModelBox(charging_handle, 39, 14, 2.696F, -13.0F, -20.448F, 2, 1, 1, 0.0F, false));

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(3.888F, -12.5F, -20.412F);
        charging_handle.addChild(bone17);
        setRotationAngle(bone17, 0.0F, 0.3491F, 0.0F);
        bone17.cubeList.add(new ModelBox(bone17, 39, 14, -1.5704F, -0.5F, 0.1822F, 2, 1, 1, 0.0F, false));
        this.initAnimations();
    }

    @Override
    public void initAnimations() {
        initAimAnimation(-0.56f, 0.2f, 0.14f);
        initAimingAnimationStates(0.2f, 0.12f, 0.08f);
        heldAnimation = new HeldAnimation(HeldStyle.SMALL);
        reloadAnimation = new ReloadAnimation(magazine, ReloadStyle.MAGAZINE).initMovement(DEFAULT_PART_ANIMATION);
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
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            GlStateManager.pushMatrix();
            renderUmp(data.isAiming(), stack);
            GlStateManager.popMatrix();
        }
    }

    private void renderUmp(boolean aim, ItemStack stack) {
        GlStateManager.pushMatrix();
        ModelTransformationHelper.defaultSMGTransform();
        GlStateManager.translate(0.0, -8.0, 0.0);

        if (aim && enableADS(stack)) {
            rotateModelForADSRendering();
        }

        renderAll();
        GlStateManager.popMatrix();

        /*renderSMGSilencer(0, 0, 0, 1f, stack);
        renderVerticalGrip(0, -13, 21, 0.7f, stack);
        renderAngledGrip(0, -8, 20, 0.8f, stack);
        renderRedDot(0, -5.95, 16, 1f, stack);
        renderHolo(-0.05, -0.05, 3, 0.9f, stack);
        renderScope2X(0, -3, 7, 1f, stack);
        renderScope4X(0, -3.95, 5, 1f, stack);*/
    }

    private void renderAll() {
        ironsights.render(1.0F);
        magazine.render(1.0F);
        ump45.render(1.0F);
        charging_handle.render(1.0F);
    }
}
