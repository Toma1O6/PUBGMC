package dev.toma.pubgmc.client.models;

import dev.toma.pubgmc.animation_old.Animation;
import dev.toma.pubgmc.client.models.weapons.ModelGun;
import dev.toma.pubgmc.client.renderer.item.gun.WeaponRenderer;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerDataProvider;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.Collections;
import java.util.List;

public class BakedModelGun implements IBakedModel {
    @Override
    public boolean isBuiltInRenderer() {
        return true;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.NONE;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        TRSRTransformation trsrt = new TRSRTransformation(matrix);
        Vector3f transl = new Vector3f(0f, 0f, 0f);
        Vector3f scale = new Vector3f(1f, 1f, 1f);
        Quat4f leftRot = new Quat4f(0f, 1f, 0f, 0f);
        Quat4f rightRot = new Quat4f(0f, 1f, 0f, 0f);

        EntityPlayer player = Minecraft.getMinecraft().player;
        IPlayerData data = null;
        ItemStack held;

        if (player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            data = data == null ? player.getCapability(PlayerDataProvider.PLAYER_DATA, null) : data;
            held = player.getHeldItemMainhand();
            if (held.getItem() instanceof GunBase) {
                GunBase gunBase = (GunBase) held.getItem();
                ModelGun weaponModel = ((WeaponRenderer) gunBase.getTileEntityItemStackRenderer()).getWeaponModel();
                if (weaponModel.aimAnimation.getFinalState().equals(Animation.EMPTYVEC) && held.getItem() != PMCItems.VSS) {
                    weaponModel.initAnimations();
                }
            }
        } else {
            return Pair.of(this, trsrt.getMatrix());
        }

        switch (cameraTransformType) {
            case GUI: {
                GlStateManager.translate(0, -0.15, 0);
                GlStateManager.scale(0.5f, 0.5f, 0.5f);
                GlStateManager.rotate(90f, 0f, 1f, 0f);
                GlStateManager.rotate(30f, 1f, 0f, 0f);
                break;
            }

            // Implement animations here
            case FIRST_PERSON_RIGHT_HAND: {
                held = player.getHeldItemMainhand();
                if (held.getItem() instanceof GunBase) {
                    ModelGun gun = ((WeaponRenderer) held.getItem().getTileEntityItemStackRenderer()).getWeaponModel();
                    gun.preRender(held);
                    this.process(held, data, gun);
                    if (data.isAiming() && !gun.enableADS(held)) {
                        GlStateManager.scale(0, 0, 0);
                    }
                } else break;
                trsrt = new TRSRTransformation(transl, leftRot, scale, rightRot);
                break;
            }

            // Third person animations, sometime later propably
            case THIRD_PERSON_RIGHT_HAND: case GROUND: {
                trsrt = new TRSRTransformation(transl, leftRot, new Vector3f(0.65F, 0.65F, 0.65F), rightRot);
                break;
            }

            case FIXED: {
                GlStateManager.translate(-0.09, 0, 0);
                GlStateManager.scale(0.5, 0.5, 0.5);
                GlStateManager.rotate(-90f, 0f, 1f, 0f);
                GlStateManager.rotate(30f, 1f, 0f, 0f);
                break;
            }

            default:
                break;
        }

        return Pair.of(this, trsrt.getMatrix());
    }

    private void process(ItemStack held, IPlayerData data, ModelGun gun) {
        gun.processAnimations(data.isAiming(), data.isReloading());
    }
}
