package dev.toma.pubgmc.client.models;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.Collections;
import java.util.List;

public class BakedModelGun implements IBakedModel {

    static TRSRTransformation fprh = new TRSRTransformation(new Vector3f(), new Quat4f(0f, 1f, 0f, 0f), new Vector3f(1f, 1f, 1f), new Quat4f(0f, 1f, 0f, 0f));
    static TRSRTransformation tprh = new TRSRTransformation(new Vector3f(), new Quat4f(0f, 1f, 0f, 0f), new Vector3f(0.65F, 0.65F, 0.65F), new Quat4f(0f, 1f, 0f, 0f));

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
        return Collections.emptyList();
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

        switch (cameraTransformType) {
            case GUI: {
                GlStateManager.translate(0, -0.15, 0);
                GlStateManager.scale(0.5f, 0.5f, 0.5f);
                GlStateManager.rotate(90f, 0f, 1f, 0f);
                GlStateManager.rotate(30f, 1f, 0f, 0f);
                break;
            }

            case FIRST_PERSON_RIGHT_HAND: {
                trsrt = fprh;
                break;
            }

            case THIRD_PERSON_RIGHT_HAND:
            case GROUND: {
                trsrt = tprh;
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
}
